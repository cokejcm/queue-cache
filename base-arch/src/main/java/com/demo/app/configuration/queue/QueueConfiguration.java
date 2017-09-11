package com.demo.app.configuration.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.app.queue.Receiver;
import com.demo.app.util.Constants;

@Configuration
public class QueueConfiguration {

	@Bean
	Queue queueAll() {
		return new Queue(Constants.QUEUE_ALL, false);
	}

	@Bean
	TopicExchange exchangeAll() {
		return new TopicExchange(Constants.EXCHANGE_ALL);
	}

	@Bean
	Binding bindingAll(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Constants.QUEUE_ALL);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Constants.QUEUE_ALL);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	public Queue createQueue(String queueName) {
		return new Queue(queueName);
	}

	@Bean
	public Binding createBinding(Queue queue, TopicExchange exchange, String routingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	public TopicExchange createExchange(String name) {
		return new TopicExchange(name);
	}

}
