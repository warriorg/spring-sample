package me.warrior.xy.spring.bean.definition;

import java.util.Map;

import me.warrior.xy.spring.ioc.overview.domain.SuperUser;
import me.warrior.xy.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 注解的方式
 */

// 3. 通过 @Import
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 按照类型依赖查找
        System.out.println("Config 类型的所有 Beans: " + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans: " + applicationContext.getBeansOfType(User.class));

        // 关闭应用上下文
        applicationContext.close();
    }

    // 命名 Bean 的注册方式
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanName);
        beanDefinitionBuilder.addPropertyValue("id", 1);
        
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    // 2. 通过 @Component 方法
    @Component
    public static class Config {

        // 1. 通过 @Bean 方式定义
        @Bean(name = {"user", "zhangSan"})
        public User user() {
            User user = new SuperUser();
            user.setId(1);
            user.setName("张三");
            return user;
        }
    }


}
