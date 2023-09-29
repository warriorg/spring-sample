package me.warrior.xy.spring.ioc.overview.dependency.injection;

import me.warrior.xy.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
//                "classpath:/META-INF/dependency-injection-context.xml");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-injection-context.xml");

        // 依赖查找
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 依赖注入
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        ObjectFactory userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject());

        ObjectFactory applicationObjectFactory  = userRepository.getApplicationObjectFactory();
        System.out.println(applicationObjectFactory.getObject());
        System.out.println(applicationObjectFactory.getObject() == applicationContext);

        // 依赖查找 错误
        // System.out.println(beanFactory.getBean(BeanFactory.class));

        // 内建Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {
        // 为什么这个表达式不想等
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        // ApplicationContext is BeanFactory
    }
}
