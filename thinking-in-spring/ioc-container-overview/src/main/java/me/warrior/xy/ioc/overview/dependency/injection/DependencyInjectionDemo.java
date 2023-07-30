package me.warrior.xy.ioc.overview.dependency.injection;

import me.warrior.xy.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 依赖注入
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject());

        ObjectFactory applicationObjectFactory  = userRepository.getApplicationObjectFactory();
        System.out.println(applicationObjectFactory.getObject());
        System.out.println(applicationObjectFactory.getObject() == beanFactory);

        // 依赖查找 错误
        // System.out.println(beanFactory.getBean(BeanFactory.class));
    }
}
