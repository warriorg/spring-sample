package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.OtherService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author warrior
 */
public class ConsumerRunSync {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-consumer.xml");
        OtherService service = (OtherService) applicationContext.getBean("otherService");
        long syncStart = System.currentTimeMillis();
        String first = service.doFirst();
        System.out.println("同步， doFirst()直接获取返回值：" + first);

        String second = service.doSecond();
        System.out.println("同步， doSecond()直接获取返回值：" + first);

        System.out.println("连个同步操作共计用时：" + (System.currentTimeMillis() - syncStart));
    }
}
