package com.demo.app.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity<I> implements Serializable {

	private static final long serialVersionUID = 5908519522358747038L;

	public abstract I getId();

	public abstract void setId(I id);

}
