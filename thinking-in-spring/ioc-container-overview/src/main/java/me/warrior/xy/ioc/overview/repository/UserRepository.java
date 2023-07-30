package me.warrior.xy.ioc.overview.repository;

import java.util.Collection;

import me.warrior.xy.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

public class UserRepository {
    // 自定义 Bean
    private Collection<User> users;
    // 内建的非 Bean 对象
    private BeanFactory beanFactory;

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> applicationObjectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(
            ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public ObjectFactory<ApplicationContext> getApplicationObjectFactory() {
        return applicationObjectFactory;
    }

    public void setApplicationObjectFactory(
            ObjectFactory<ApplicationContext> applicationObjectFactory) {
        this.applicationObjectFactory = applicationObjectFactory;
    }
}
