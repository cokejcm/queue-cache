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

	//@Bean
	Queue queueAll() {
		return new Queue(Constants.QUEUE_ALL, false);
	}

	//@Bean
	TopicExchange exchangeAll() {
		return new TopicExchange(Constants.EXCHANGE_ALL);
	}

	//@Bean
	DirectExchange userExchange(){
		return new DirectExchange(Constants.EXCHANGE_USER);
	}

	//@Bean
	Binding bindingAll(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Constants.QUEUE_ALL);
	}

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);
		Queue queueDifussion = queueAll();
		admin.declareQueue(queueDifussion);
		TopicExchange exchangeDifussion = exchangeAll();
		admin.declareExchange(exchangeDifussion);
		admin.declareBinding(bindingAll(queueDifussion, exchangeDifussion));
		return admin;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		rabbitTemplate.setRoutingKey(Constants.QUEUE_ALL);
		rabbitTemplate.setQueue(Constants.QUEUE_ALL);
		return rabbitTemplate;
	}

	//¿NEcesario?????????????????????????????? usar rabbitadmin¿???????????????????????????
	/*@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Constants.QUEUE_ALL);
		container.setMessageListener(listenerAdapter);
		return container;
	}*/

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
