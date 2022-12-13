package me.warriorg.dubbo.springboot.controller;

import me.warriorg.dubbo.model.Employee;
import me.warriorg.dubbo.service.EmployeeService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumer/employee")
public class EmployeeController {

    @Reference
    private EmployeeService employeeService;

    @PostMapping("/register")
    public String register(Employee employee, Model model) {
        employeeService.add(employee);
        model.addAttribute("employee", employee);
        return "welcome.jsp";
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @RequestMapping("/count")
    @ResponseBody
    public int count() {
        return employeeService.count();
    }
}
