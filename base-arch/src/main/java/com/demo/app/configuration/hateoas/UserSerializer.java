package com.demo.app.configuration.hateoas;

import java.io.IOException;

import javax.ws.rs.Path;

import com.demo.app.domain.security.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		Class<?> currClass = value.getClass();
		// Get the controller associated to this entity
		Class<?> controllerClass = currClass.getAnnotation(ControllerClass.class).value();
		String entityUrl = controllerClass.getAnnotation(Path.class).value();
		// Build the uri
		StringBuilder uri = new StringBuilder();
		uri.append(entityUrl).append("/").append(value.getUsername());
		// Write the uri
		jgen.writeStartObject();
		jgen.writeStringField("href", uri.toString());
		jgen.writeEndObject();
	}
}
