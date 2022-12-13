package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.FooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerRun {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-consumer.xml");
        FooService fooService = (FooService) applicationContext.getBean("fooService");
        String hello = fooService.hello("Warrior");
        System.out.println(hello);
    }
}
