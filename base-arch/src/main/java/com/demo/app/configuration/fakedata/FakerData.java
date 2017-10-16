package com.demo.app.configuration.fakedata;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RUNTIME)
public @interface FakerData {

	int position() default -1;       // CLASS LEVEL. Order to follow when creating the data
	int numItems() default 0;		 // CLASS LEVEL. Num entities to be created.
	int numIterations() default 1;	 // CLASS LEVEL. For each iteration numItems() elements will be created.
	boolean reuse() default false;   // CLASS LEVEL. If the class will be reused as field property in other classes.

	String fakerMethod() default ""; // FIELD LEVEL. Faker method used to populate the data
	boolean ignore() default false;  // FIELD LEVEL.  Ignore field

}
