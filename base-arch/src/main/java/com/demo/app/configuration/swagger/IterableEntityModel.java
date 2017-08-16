package com.demo.app.configuration.swagger;

import java.util.Map;

import io.swagger.models.ExternalDocs;
import io.swagger.models.Model;
import io.swagger.models.properties.Property;

public class IterableEntityModel implements Model {

	@Override
	public String getDescription() {
		return "List of Entities. Swagger struggles with Iterable of T being T a generic. At the end is a List of the corresponding entity";
	}

	@Override
	public Object getExample() {
		return "[{pojo1}, {pojo2} ...]";
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
