package com.demo.app.configuration.hateoas;

import java.io.IOException;

import javax.ws.rs.Path;

import org.hibernate.collection.internal.PersistentBag;

import com.demo.app.domain.Entity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IterableSerializer extends JsonSerializer<Iterable<?>> {

	@Override
	public void serialize(Iterable<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		if (value instanceof PersistentBag) {
			PersistentBag bag = (PersistentBag) value;
			Object owner = bag.getOwner();
			StringBuilder uri = new StringBuilder();
			if (owner instanceof Entity) {
				// Get the parent that owns the Collection
				Entity parent = (Entity) owner;
				Class<?> parentClass = parent.getClass();
				// Get the controller associated to this entity
				Class<?> controllerClass = parentClass.getAnnotation(ControllerClass.class).value();
				// Get the name of the field
				String field = bag.getRole().substring(bag.getRole().lastIndexOf('.') + 1, bag.getRole().length());
				// Build the uri
				String entityUrl = controllerClass.getAnnotation(Path.class).value();
				uri.append(entityUrl).append("/").append(parent.getId()).append("/").append(field);
				// Write
				jgen.writeStartArray();
				jgen.writeStartObject();
				jgen.writeStringField("href", uri.toString());
				jgen.writeEndObject();
				jgen.writeEndArray();
			}
		}
	}
}
