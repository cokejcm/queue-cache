package com.demo.app.configuration.fakedata;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RUNTIME)
public @interface FakerData {

	int position() default -1; // Order to follow when creating the data

	String fakerMethod() default "";
}
