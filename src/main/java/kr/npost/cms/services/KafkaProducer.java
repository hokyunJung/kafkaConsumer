package kr.npost.cms.services;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload, String uuid) {
        ProducerRecord<String, String> message = new ProducerRecord<>(topic, payload);
        message.headers().add(new RecordHeader("RES-UUID", uuid.getBytes(StandardCharsets.UTF_8)));
        kafkaTemplate.send(message);
    }
}