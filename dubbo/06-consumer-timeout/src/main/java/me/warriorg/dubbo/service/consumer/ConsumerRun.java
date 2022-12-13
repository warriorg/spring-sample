package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.FooService;
import me.warriorg.dubbo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author warrior
 */
public class ConsumerRun {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-consumer.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        String username = userService.getUsernameById(1);
        System.out.println("返回的用户名：" + username);
        userService.addUser(username);
    }
}
