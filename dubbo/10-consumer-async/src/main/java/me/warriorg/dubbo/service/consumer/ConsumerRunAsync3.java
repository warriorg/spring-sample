package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.OtherService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;

public class ConsumerRunAsync3 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");
        OtherService service = (OtherService) ac.getBean("otherService");
        long asyncStart = System.currentTimeMillis();
        CompletableFuture<String> doFifthFuture = service.doFifth();
        CompletableFuture<String> doSixth = service.doSixth();
        long syncInvokeTime = System.currentTimeMillis() - asyncStart;
        System.out.println("两个异步调用耗时：" + syncInvokeTime);

        doFifthFuture.whenComplete((result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                System.out.println("异步调用doFifth返回值：" + result);
            }
        });

        doSixth.whenComplete((result, throwable)-> {
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                System.out.println("异步调用doSixth返回值：" + result);
            }
        });
    }
}
