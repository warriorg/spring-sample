package me.warriorg.spring.kafka;

import me.warriorg.spring.kafka.consumer.SomeConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class KafkaApplication {
    private final Logger log = LoggerFactory.getLogger(KafkaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }


    /*************** 方式三 ******************/
    @Value("${kafka.topic}")
    private String topic;

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaConatiner() {
        ContainerProperties containerProperties = new ContainerProperties(topic);
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL);
        containerProperties.setMessageListener(new ConsumerAwareMessageListener<String, String>() {
            @Override
            public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
                log.debug("data:[{}]", data);
                consumer.commitSync();
            }
        });

        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testManual");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(properties);
        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(cf, containerProperties);
        return container;
    }
}


