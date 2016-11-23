package com.example.controller;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.KafkaConsumer;
import com.example.service.KafkaDispatcher;

@RestController
@RequestMapping("/test")
public class DemoKafkaController {
	
	@Autowired
	KafkaDispatcher producer;
	
	@Autowired
	KafkaConsumer kconsumer;
	
	
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void SendMessage(@RequestBody String message){
		System.out.println("Controller....message:"+message);
		producer.sendMessage("test", message);
	}	
	
	@RequestMapping(method = RequestMethod.GET) 
	public void hello(){
		System.out.println("Controller....Hello world!!");
	}
}
