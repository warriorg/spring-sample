package me.warriorg.dubbo.service.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author warrior
 *
 * dubbo 推荐的启动方式在01-provider2中
 */
public class ProviderApp {

    public static void main(String[] args) throws IOException {
        // 创建spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-provider.xml");
        // 启动spring容器
        ((ClassPathXmlApplicationContext)applicationContext).start();
        // 阻塞主线程
        System.in.read();
    }
}
