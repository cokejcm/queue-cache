package com.demo.app.configuration.swagger;

import java.util.Map;

import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.properties.Property;

public class IterableFormModel implements Model {

	@Override
	public String getDescription() {
		return "List of Forms";
	}

	@Override
	public Object getExample() {
		return "[{\"name\":\"Form 1\",\"id\":\"15\"},{\"name\":\"Form 2\",\"id\":\"16\"}]";
	}

	@Override
	public ExternalDocs getExternalDocs() {
		return null;
	}

	@Override
	public Map<String, Property> getProperties() {
		return null;
	}

	@Override
	public String getReference() {
		return null;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public Map<String, Object> getVendorExtensions() {
		return null;
	}

	@Override
	public void setDescription(String arg0) {

	}

	@Override
	public void setExample(Object arg0) {

	}

	@Override
	public void setProperties(Map<String, Property> arg0) {

	}

	@Override
	public void setReference(String arg0) {

	}

	@Override
	public void setTitle(String arg0) {

	}

	@Override
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			//
		}
		return o;
	}

}
