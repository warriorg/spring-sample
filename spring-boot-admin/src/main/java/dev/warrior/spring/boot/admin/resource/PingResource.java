package dev.warrior.spring.boot.admin.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gao shiyong
 * @since 2022/8/22 13:55
 */
@RestController
@RequestMapping("ping")
public class PingResource {

    @RequestMapping
    public String ping() {
        return "pong";
    }
}
