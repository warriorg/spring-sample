package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.FooService;

public class FooServiceImpl implements FooService {

    @Override
    public String hello(String name) {
        System.out.println("v1 提供者");
        return "Hello Dubbo world! " + name;
    }
}
