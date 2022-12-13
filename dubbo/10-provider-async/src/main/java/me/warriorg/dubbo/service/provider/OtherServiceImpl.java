package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.OtherService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author warrior
 */
public class OtherServiceImpl implements OtherService {

    // 耗时操作
    private void sleep(String method) {
        long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;
        System.out.println(method + "方法执行用时：" + useTime);
    }


    @Override
    public String doFirst() {
        sleep("doFirst");
        return "doFirst";
    }

    @Override
    public String doSecond() {
        sleep("doSecond");
        return "doSecond";
    }

    @Override
    public String doThird() {
        sleep("doThird");
        return "doThird";
    }

    @Override
    public String doFourth() {
        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            asyncContext.signalContextSwitch();
            sleep("doFourth");
            asyncContext.write("doFourth");
        }).start();

        return null;
    }

    @Override
    public CompletableFuture<String> doFifth() {
        long startTime = System.currentTimeMillis();
        sleep("doFifth");
        CompletableFuture<String> future = CompletableFuture.completedFuture("doFifth()-----");
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;
        System.out.println("doFifth方法执行用时：" + useTime);
        return future;
    }

    @Override
    public CompletableFuture<String> doSixth() {
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep("doSixth");
            return "doSixth";
        });
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;
        System.out.println("doSixth方法执行用时：" + useTime);
        return future;
    }
}
