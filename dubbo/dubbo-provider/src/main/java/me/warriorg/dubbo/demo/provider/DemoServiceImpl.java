package me.warriorg.dubbo.demo.provider;

import me.warriorg.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
