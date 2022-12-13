package me.warriorg.dubbo.service;

/**
 * @author warrior
 */
public interface UserService {

    String getUsernameById(int id);
    void addUser(String username);
}
