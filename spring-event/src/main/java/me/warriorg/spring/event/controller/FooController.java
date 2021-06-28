package me.warriorg.spring.event.controller;

import me.warriorg.spring.event.FooEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warrior
 */
@RestController
@RequestMapping("foo")
public class FooController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping()
    public String foo(String val) {
        FooEvent event = new FooEvent();
        event.setVal(val);
        applicationEventPublisher.publishEvent(event);
        return val;
    }
}
