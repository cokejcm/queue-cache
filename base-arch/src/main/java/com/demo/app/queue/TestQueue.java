package com.demo.app.queue;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.demo.app.util.Constants;

//@Component
public class TestQueue implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;
	private final ConfigurableApplicationContext context;

	public TestQueue(Receiver receiver, RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
		this.context = context;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(Constants.QUEUE_ALL, "Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		context.close();
	}

}
