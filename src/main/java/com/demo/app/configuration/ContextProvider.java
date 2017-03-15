package com.demo.app.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Used to get beans pragmatically instead of using @Autowired
 * @author coke
 *
 */
@Component
public class ContextProvider  implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	/**
	 * Get a Spring bean by type.
	 **/
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

	/**
	 * Get a Spring bean by name.
	 **/
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

}