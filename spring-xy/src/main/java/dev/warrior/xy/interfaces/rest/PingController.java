package dev.warrior.xy.interfaces.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ping")
public class PingController {

    @RequestMapping
    public String pong() {
        return "pong";
    }
}
