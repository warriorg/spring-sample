package me.warriorg.dubbo.service;

/***
 * 用户服务降级
 * 要求和接口在同一个包中，并且class名字为 [接口名]Mock
 */
public class UserServiceMock implements UserService {
    @Override
    public String getUsernameById(int id) {
        return "没有该用户的ID";
    }

    @Override
    public void addUser(String username) {
        System.out.println("新增用户失败");
    }
}
