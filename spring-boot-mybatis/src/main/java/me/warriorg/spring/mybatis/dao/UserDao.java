package me.warriorg.spring.mybatis.dao;

import me.warriorg.spring.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User findOne(int sid);
}
