package com.demo.app.configuration.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.app.queue.Receiver;
import com.demo.app.util.Constants;

@Configuration
public class QueueConfiguration {

	//Exchange, queue and binding for everyone
	Queue queueAll() {
		return new Queue(Constants.QUEUE_ALL, true);
	}

	TopicExchange exchangeAll() {
		return new TopicExchange(Constants.EXCHANGE_ALL);
	}

	Binding bindingAll(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Constants.QUEUE_ALL);
	}

	//Exchange to route messages to specific users
	@Bean
	DirectExchange userExchange(){
		return new DirectExchange(Constants.EXCHANGE_USER);
	}

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);
		//Exchange and Queue for everyone
		Queue queueDifussion = queueAll();
		admin.declareQueue(queueDifussion);
		TopicExchange exchangeDifussion = exchangeAll();
		admin.declareExchange(exchangeDifussion);
		admin.declareBinding(bindingAll(queueDifussion, exchangeDifussion));
		//Per user
		DirectExchange directExchange = userExchange();
		admin.declareExchange(directExchange);
		return admin;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setRoutingKey(Constants.QUEUE_ALL);
		rabbitTemplate.setQueue(Constants.QUEUE_ALL);
		return rabbitTemplate;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
