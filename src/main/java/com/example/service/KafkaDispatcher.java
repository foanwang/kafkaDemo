package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaDispatcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDispatcher.class);
	 
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessage(String Topic, String message) {
		try {
	        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Topic, message);

	        // you can register a callback with the listener to receive the result
	        // of the send asynchronously
	        future.addCallback(
	                new ListenableFutureCallback<SendResult<String, Object>>() {

	                    @Override
	                    public void onSuccess(
	                            SendResult<String, Object> result) {
	                        LOGGER.info("sent message='{}' with offset={}",
	                                message,
	                                result.getRecordMetadata().offset());
	                    }

	                    @Override
	                    public void onFailure(Throwable ex) {
	                        LOGGER.error("unable to send message='{}'",
	                                message, ex);
	                    }
	                });
		 } catch (Exception e) {
	            throw new RuntimeException(e);
	        }

	    }
}
