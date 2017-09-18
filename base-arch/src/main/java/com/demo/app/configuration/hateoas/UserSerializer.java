package com.demo.app.configuration.hateoas;

import java.io.IOException;

import com.demo.app.domain.security.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		value.setPassword("********");
		provider.defaultSerializeValue(value, jgen);
	}
}
