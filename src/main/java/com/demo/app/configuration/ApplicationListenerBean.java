package com.demo.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.app.service.MessageService;
import com.demo.app.service.PersonService;

@Component
public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent>  {

	@Autowired
	MessageService messageService;
	@Autowired
	PersonService personService;


	/**
	 * Repopulate the cache after a reload event
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		messageService.cacheAll();
		personService.cacheAll();
	}

}
