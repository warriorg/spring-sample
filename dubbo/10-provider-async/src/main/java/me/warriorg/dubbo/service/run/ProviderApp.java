package me.warriorg.dubbo.service.run;

import org.apache.dubbo.container.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author warrior
 *
 * dubbo 推荐的启动方式在01-provider2中
 */
public class ProviderApp {

    public static void main(String[] args) {
        Main.main(args);
    }
}
