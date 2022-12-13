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

        // 对于有返回值的返回null
        String username = userService.getUsernameById(3);
        System.out.println("username=" + username);
        // 没有返回值的方法，没有任何结果
        userService.addUser("China");
    }
}
