package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.FooService;

public class FooServiceImpl implements FooService {

    @Override
    public String hello(String name) {
        System.out.println("00 服务提供者 " + name);
        return "00 服务提供者 Hello Dubbo world! " + name;
    }
}
