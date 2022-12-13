package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.OtherService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author warrior
 */
public class ConsumerRunAsync {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");
        OtherService service = (OtherService) ac.getBean("otherService");

        long asyncStart = System.currentTimeMillis();
        service.doThird();
        service.doFourth();
        long syncInvokeTime = System.currentTimeMillis() - asyncStart;
        System.out.println("两个异步调用耗时：" + syncInvokeTime);
    }
}
