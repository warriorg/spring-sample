package me.warriorg.spring.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class SomeConsumer {
    private final Logger log = LoggerFactory.getLogger(SomeConsumer.class);

    /***
     * 方式4
     * @param data
     * @param acknowledgment
     */
    @KafkaListener(topics = "${kafka.topic}")
    public void onMsg(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
        log.debug("Kafka data:[{}] ", data);
        acknowledgment.acknowledge();
    }

    // 方式2
//    @KafkaListener(topics = "${kafka.topic}")
//    public void onMsg(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String  msgKey, @Headers MessageHeaders headers) {
//        log.debug("Kafka message:[{}], key:[{}] ", message, msgKey);
//        headers.keySet().forEach(key -> log.info("{}: {}", key, headers.get(key)));
//    }

//    方式一
//    @KafkaListener(topics = "${kafka.topic}")
//    public void onMsg(String message) {
//        log.debug("消费者接受到消息 " + message);
//    }

    @KafkaListener(topics= "#{'${kafka.multiple-topic}'.split(',')}", groupId = "dc-ems")
    public void onReceive(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {

    }

}
