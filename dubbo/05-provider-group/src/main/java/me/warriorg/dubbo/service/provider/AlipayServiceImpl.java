package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.FooService;

public class AlipayServiceImpl implements FooService {

    @Override
    public String hello(String name) {
        System.out.println("支付宝");
        return "支付宝" + name;
    }
}
