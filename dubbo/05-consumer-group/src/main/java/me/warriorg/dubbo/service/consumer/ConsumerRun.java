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
        FooService fooService = (FooService) applicationContext.getBean("alipay");
        String hello = fooService.hello("Warrior");
        System.out.println(hello);


        fooService = (FooService) applicationContext.getBean("wechat");
        hello = fooService.hello("WarriorV2");
        System.out.println(hello);
    }
}
