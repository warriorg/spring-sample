package dev.warriorg.rabbitmq.consumer;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer2 {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Random random = new Random();

    @RabbitListener(queues = "test_queue", concurrency = "12")  // 允许3个线程并发消费
    public void receiveMessage(String message) {

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken("2-user-" + random.nextInt(100000), "ROLE_USER");
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            String beforeName = SecurityContextHolder.getContext().getAuthentication().getName();
            if (logger.isDebugEnabled()) {
                logger.debug(Thread.currentThread().getName() +
                        " | User: " + beforeName +
                        " | Message: " + message);
            }

            try {
                Thread.sleep(2000); // 模拟线程运行时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            String afterName = SecurityContextHolder.getContext().getAuthentication().getName();
            logger.info(Thread.currentThread().getName() +
                    " | AFTER SLEEP " +
                    " | User: " +  beforeName + ":" +afterName +
                    " | Message: " + message);
            if (!Objects.equals(beforeName, afterName)) {
                logger.error("===============================================================================");
                logger.error(beforeName, afterName);
                logger.error("===============================================================================");
            }
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
