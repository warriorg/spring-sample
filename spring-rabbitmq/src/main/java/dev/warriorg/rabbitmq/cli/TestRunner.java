package dev.warriorg.rabbitmq.cli;

import dev.warriorg.rabbitmq.producer.RabbitMQProducer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {
    private final RabbitMQProducer producer;

    public TestRunner(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 1; i <= 1000; i++) {
            producer.sendMessage("Test Message " + i);
        }
    }
}
