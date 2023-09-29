package me.warrior.xy.spring.ioc.overview.container;

import java.util.Map;

import me.warrior.xy.spring.ioc.overview.domain.SuperUser;
import me.warrior.xy.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * IoC 容器示例
 */
@Configuration
public class AnnotionApplicationContextAsIocContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册当前类为配置类
        applicationContext.register(AnnotionApplicationContextAsIocContainerDemo.class);
        applicationContext.refresh();


        lookupByCollectionType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new SuperUser();
        user.setId(1);
        user.setName("张三");
        return user;
    }

    /**
     * 按照类型查找集合对象
     * @param beanFactory
     */
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory listableBeanFactory) {
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的 User 集合对象：" + userMap);
        }
    }
}
