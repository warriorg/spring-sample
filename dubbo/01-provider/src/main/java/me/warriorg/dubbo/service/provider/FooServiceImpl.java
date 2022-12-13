package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.FooService;

public class FooServiceImpl implements FooService {

    @Override
    public String hello(String name) {
        return "Hello Dubbo world! " + name;
    }
}
