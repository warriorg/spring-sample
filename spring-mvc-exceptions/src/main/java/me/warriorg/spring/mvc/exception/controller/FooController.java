package me.warriorg.spring.mvc.exception.controller;

import me.warriorg.spring.mvc.exception.error.ArgumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warrior
 */
@RestController
@RequestMapping("/foo")
public class FooController {

    @GetMapping
    public String hello() {
        throw new ArgumentException("就是错误了");
    }
}
