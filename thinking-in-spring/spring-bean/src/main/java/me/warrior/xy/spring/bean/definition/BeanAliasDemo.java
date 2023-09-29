package me.warrior.xy.spring.bean.definition;

import me.warrior.xy.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        // 通过别名获取用户
        User zhangSan = beanFactory.getBean("zhangSan", User.class);
        User user = beanFactory.getBean("zhangSan", User.class);
        System.out.println(user.equals(zhangSan));
    }
}
