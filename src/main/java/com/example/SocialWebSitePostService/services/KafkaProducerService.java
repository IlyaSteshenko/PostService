package com.example.SocialWebSitePostService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String TOPIC;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

}
