package me.warriorg.spring.mybatis.mapper;

import me.warriorg.spring.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User findOne(int sid);
}
