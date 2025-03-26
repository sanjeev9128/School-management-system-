package com.example.management.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	  @KafkaListener(topics = "school-topic", groupId = "school_management_group")
	    public void consume(String message) {
	        System.out.println("Consumed message: " + message);
	    }

}
