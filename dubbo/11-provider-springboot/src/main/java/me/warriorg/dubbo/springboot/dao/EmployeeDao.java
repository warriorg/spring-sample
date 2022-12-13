package me.warriorg.dubbo.springboot.dao;


import me.warriorg.dubbo.model.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDao  {
    void insertEmployee(Employee employee);
    Integer selectCount();
    Employee selectById(int id);
}
