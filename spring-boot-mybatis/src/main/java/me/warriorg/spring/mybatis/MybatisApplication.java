package me.warriorg.spring.mybatis;

import lombok.extern.slf4j.Slf4j;
import me.warriorg.spring.mybatis.mapper.CoffeeMapper;
import me.warriorg.spring.mybatis.mapper.UserDao;
import me.warriorg.spring.mybatis.model.Coffee;
import me.warriorg.spring.mybatis.model.User;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
public class MybatisApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class);
    }

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Resource
    UserDao userDao;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            User user = userDao.findOne(1);
            log.info(user.toString());
        };
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        int count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
        count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = coffeeMapper.findById(c.getId());
        log.info("Find Coffee: {}", c);
    }
}
