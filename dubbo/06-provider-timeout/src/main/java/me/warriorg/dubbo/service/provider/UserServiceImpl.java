package me.warriorg.dubbo.service.provider;

import me.warriorg.dubbo.service.UserService;

import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements UserService {

    @Override
    public String getUsernameById(int id) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "张三";
    }

    @Override
    public void addUser(String username) {
        System.out.println("添加用户成功：" + username);
    }
}
