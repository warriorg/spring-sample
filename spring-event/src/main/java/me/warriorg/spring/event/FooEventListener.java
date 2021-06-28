package me.warriorg.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author warrior
 */
@Component
public class FooEventListener {

    @EventListener()
    public void foo(FooEvent event) {
        System.out.println(event.toString());
    }

    @EventListener
    public void fooTwo(FooEvent event) {
        System.out.println("footTwo " + event);
    }
}