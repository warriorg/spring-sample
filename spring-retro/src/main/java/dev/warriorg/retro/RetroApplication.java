package dev.warriorg.retro;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RetroApplication {
   /**
    * logger
    */
   private final static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    public static void main(String[] args) {
//        SpringApplication.run(RetroApplication.class, args);
        new SpringApplicationBuilder()
                .sources(RetroApplication.class)
                .logStartupInfo(false)
                .bannerMode(Banner.Mode.OFF)
                .listeners(event -> {
                    LOGGER.info("Event: {}", event);
                })
                .run(args);
    }

}
