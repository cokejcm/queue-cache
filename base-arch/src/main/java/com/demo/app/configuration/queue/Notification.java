package com.demo.app.configuration.queue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The AOP aspect will check if the annotation is present in order to send the notification storeInDb() also indicates if the notifications need to be persisted on the Db
 *
 * @author coke
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Notification {
	public boolean storeInDb() default false;
}
