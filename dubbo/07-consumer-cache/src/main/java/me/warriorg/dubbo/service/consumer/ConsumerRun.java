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

        for (int i = 1; i <= 1000; i++) {
            fooService.hello("i==" + i);
        }
        // 调用提供者，并将结果方到缓存，根据LRU策略淘汰缓存
        System.out.println(fooService.hello("warrior"));
        System.out.println("i==1");
        System.out.println("i==3");

    }
}
