package me.warriorg.dubbo.service;

import me.warriorg.dubbo.model.Employee;

public interface EmployeeService {

    void add(Employee employee);
    Employee findById(int id);
    Integer count();
}
