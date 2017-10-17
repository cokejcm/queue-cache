package com.demo.app.configuration.fakedata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "dev"})
@Component
@ConfigurationProperties(prefix="fakedata")
public class FakeDataProperties {

	private int numEntities;
	private int numIterations;

	public int getNumEntities() {
		return numEntities;
	}
	public void setNumEntities(int numEntities) {
		this.numEntities = numEntities;
	}
	public int getNumIterations() {
		return numIterations;
	}
	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}


}
