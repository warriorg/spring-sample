package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.FooService;

public class FooServiceV2Impl implements FooService {

    @Override
    public String hello(String name) {
        System.out.println("v2 提供者");
        return "v2 Hello Dubbo world! " + name;
    }
}
