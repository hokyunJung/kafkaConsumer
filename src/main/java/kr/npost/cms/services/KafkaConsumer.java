package kr.npost.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private final ObjectMapper objectMapper;
    //private List<RegisteredPostEvent> eventRepo = new ArrayList<>();

    @Autowired
    private KafkaProducer kafkaProducer;


    @KafkaListener(topics = "askToAds")
    //public void batchListener(@Payload String payload, @Headers MessageHeaders messageHeaders) {
    public void batchListener(@Payload String payload, @Header(value = KafkaHeaders.RECEIVED_PARTITION_ID) int partition, 
        @Header(value = "REQ-UUID") String uuid) {
        log.info("received message='{}', partition='{}', REQ-UUID='{}'", payload, partition, uuid);
        
        //JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sendEaddr", "AAA");
        jsonObject.addProperty("reqType", "I");

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        kafkaProducer.send("resFromAds", jsonObject.toString(), uuid);
    }

/*
    @KafkaListener(topics = "testTopic", groupId = "testGroup")
    public void batchListener(ConsumerRecords<String, String> records) {
        records.forEach(record -> log.info("batchListener : " + record.toString()));
    }
    


    
    @KafkaListener(topics = "testTopic", groupId = "testGroup")
    protected void consume(@Payload String payload) throws Exception {
        log.info("recive event : {}", payload);
        //RegisteredPostEvent event = objectMapper.readValue(payload, RegisteredPostEvent.class);
        //eventRepo.add(event);

        // Process
        //acknowledgment.acknowledge();
    }
    */
/*
    @KafkaListener(topics = "testTopic", groupId = "testGroup")
    public void messageListener(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println("testEEEEEEEEEEEEEEEEE");
        log.info("### record: " + record.toString());
        log.info("### topic: " + record.topic() + ", value: " + record.value() + ", offset: " + record.offset());

        // kafka 메시지 읽어온 곳까지 commit. (이 부분을 하지 않으면 메시지를 소비했다고 commit 된 것이 아니므로 계속 메시지를 읽어온다)
        //enable.auto.commit=false 이면서 ack-mode: MANUAL_IMMEDIATE 일때 Acknowledgment을 통해 커밋전략을 가진다..
        acknowledgment.acknowledge();
    }
    */
    //public List<RegisteredPostEvent> getEventRepo() {
    //    return eventRepo;
    //}
}