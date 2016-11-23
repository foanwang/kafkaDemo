package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import org.springframework.kafka.support.KafkaHeaders;

@Service
public class KafkaConsumer{
	 private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	    @KafkaListener(topics = "test")
	    public void Listener(String message, @Header(KafkaHeaders.OFFSET) Integer offset,
	                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
	        log.info("Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
	                topic, partition, offset, message);
        }


}
