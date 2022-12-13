package me.warriorg.dubbo.springboot.service;

import me.warriorg.dubbo.model.Employee;
import me.warriorg.dubbo.service.EmployeeService;
import me.warriorg.dubbo.springboot.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


/**
 * @author warrior
 */
@Service
@org.apache.dubbo.config.annotation.Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @CacheEvict(value = "realTimeCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    @Cacheable(value = "realTimeCache", key = "'employee_'+#id")
    @Override
    public Employee findById(int id) {
        System.out.println("从DB查询:" + id);
        return employeeDao.selectById(id);
    }

    // 双重检测锁机制解决redis的热点缓存问题
    @Override
    public Integer count() {
        BoundValueOperations<Object, Object> ops = redisTemplate.boundValueOps("count");
        Object count = ops.get();
        if (count == null) {
            synchronized (this) {
                count = ops.get();
                if (count == null) {
                    System.out.println("db 查询");
                    count = employeeDao.selectCount();
                    ops.set(count, 10, TimeUnit.SECONDS);
                }
            }
        }
        return (Integer)count;
    }
}
