package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.FooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author warrior
 */
public class ConsumerRun {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-consumer.xml");
        FooService fooService = (FooService) applicationContext.getBean("fooService");
        int i = 0;
        while (i < 10) {
            String hello = fooService.hello("Warrior");
            System.out.println(hello);
            i++;
        }

    }
}
