package me.warriorg.dubbo.service.consumer;

import me.warriorg.dubbo.service.OtherService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author warrior
 */
public class ConsumerRunAsync2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");
        OtherService service = (OtherService) ac.getBean("otherService");

        long asyncStart = System.currentTimeMillis();
        String result1 = service.doThird();
        System.out.println("调用结果1=" + result1);

        String result3 = service.doFourth();
        System.out.println("调用结果3=" + result3);

        Future<String> thirdFuture = RpcContext.getContext().getFuture();
        // 阻塞
        String result2 = thirdFuture.get();
        System.out.println("调用结果2=" + result2);

        Future<String> fourthFuture = RpcContext.getContext().getFuture();
        String result4 = fourthFuture.get();
        System.out.println("调用结果4=" + result4);

        long syncInvokeTime = System.currentTimeMillis() - asyncStart;
        System.out.println("两个异步调用耗时：" + syncInvokeTime);
    }
}
