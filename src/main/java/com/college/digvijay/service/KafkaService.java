package com.college.digvijay.service;

import com.college.digvijay.dto.SendStudent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    KafkaTemplate<String, SendStudent> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public KafkaService(KafkaTemplate<String, SendStudent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendStudent(SendStudent student){
        kafkaTemplate.send("student", student);
        logger.info("sendStudent success.");
    }
}
