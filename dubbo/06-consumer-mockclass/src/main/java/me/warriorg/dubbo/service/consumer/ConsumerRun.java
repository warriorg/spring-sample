package me.warriorg.dubbo.service.consumer;

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

        String username = userService.getUsernameById(3);
        System.out.println("username=" + username);
        userService.addUser("China");
    }
}
