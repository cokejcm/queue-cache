package com.demo.app.controller;

import java.io.Serializable;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.app.domain.Entity;
import com.demo.app.service.Service;

public abstract class Controller<T extends Entity, K extends Serializable> {

	@Autowired
	public abstract Service<T, K> getService();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<T> findAll() {
		return getService().findAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public T findOne(@PathParam("id") K id) {
		return getService().findOne(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T save(@Valid T entity) {
		return getService().saveOne(entity);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T update(@Valid T entity) {
		return getService().saveOne(entity);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") K id) {
		getService().deleteOne(id);
	}

}
