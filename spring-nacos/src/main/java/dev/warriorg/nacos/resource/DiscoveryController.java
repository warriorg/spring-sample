package dev.warriorg.nacos.resource;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("discovery")
public class DiscoveryController {
    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @PostConstruct
    private void init() throws NacosException {
        namingService.subscribe("spring-nacos", event -> {
            System.out.println(((NamingEvent)event).getServiceName());
            System.out.println(((NamingEvent)event).getInstances());
        });
    }

    @RequestMapping(value = "/get/{serviceName}", method = GET)
    @ResponseBody
    public List<Instance> get(@PathVariable String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @GetMapping("/register")
    @ResponseBody
    public List<Instance> register() throws Exception {
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        namingService.registerInstance(applicationName, InetAddress.getLocalHost().getHostAddress(), serverPort);
        return namingService.getAllInstances(applicationName);
    }
}
