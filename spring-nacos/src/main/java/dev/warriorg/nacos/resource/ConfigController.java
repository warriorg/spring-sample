package dev.warriorg.nacos.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("config")
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Value("${title:}")
    private String value;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return value + "-" + useLocalCache;
    }
}
