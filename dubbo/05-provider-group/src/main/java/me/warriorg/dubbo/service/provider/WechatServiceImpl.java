package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.FooService;

public class WechatServiceImpl implements FooService {

    @Override
    public String hello(String name) {
        System.out.println("wechat");
        return "wechat" + name;
    }
}
