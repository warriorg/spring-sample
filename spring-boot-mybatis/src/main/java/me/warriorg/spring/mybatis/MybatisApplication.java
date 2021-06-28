package me.warriorg.spring.mybatis;

import lombok.extern.slf4j.Slf4j;
import me.warriorg.spring.mybatis.dao.UserDao;
import me.warriorg.spring.mybatis.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class);
    }

    @Resource
    UserDao userDao;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            User user = userDao.findOne(1);
            log.info(user.toString());
        };
    }
}
