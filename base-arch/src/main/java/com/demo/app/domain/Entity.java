package com.demo.app.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.SerializationUtils;

import com.demo.app.configuration.exceptions.AppException;
import com.demo.app.util.Util;

@MappedSuperclass
public abstract class Entity implements Serializable, Cloneable {

	private static final long serialVersionUID = 5908519522358747038L;

	public abstract String getId();

	public abstract void setId(String id);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getId() == null ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Entity other = (Entity) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	public Entity clone(String[] notCloneableFieldNames, Object[] notCloneableFieldValues) throws Exception {
		if (notCloneableFieldNames.length != notCloneableFieldValues.length) {
			throw new AppException("exception");
		}

		Entity e = SerializationUtils.clone(this);
		for (int i = 0; i < notCloneableFieldNames.length; i++) {
			Field field = e.getClass().getDeclaredField(notCloneableFieldNames[i]);
			Method getMethod = field.getDeclaringClass().getMethod("get" + Util.capitalize(field.getName()));
			Class<?> c = getMethod.getReturnType();
			if (!c.isAssignableFrom(notCloneableFieldValues[i].getClass())) {
				throw new AppException("exception");
			}
			Method method = field.getDeclaringClass().getMethod("set" + Util.capitalize(field.getName()), c);
			method.invoke(e, new Object[] { notCloneableFieldValues[i] });
		}
		// set id to null
		Field fieldId = e.getClass().getDeclaredField("id");
		Method method = fieldId.getDeclaringClass().getMethod("set" + Util.capitalize(fieldId.getName()), String.class);
		method.invoke(e, new Object[] { null });
		return e;
	}

}
