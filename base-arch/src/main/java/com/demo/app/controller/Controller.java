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
import org.springframework.security.access.prepost.PreAuthorize;

import com.demo.app.configuration.AppException;
import com.demo.app.configuration.internationalization.MessageSourceLocale;
import com.demo.app.domain.Entity;
import com.demo.app.service.Service;

import io.swagger.annotations.ApiOperation;

public abstract class Controller<T extends Entity, K extends Serializable> {

	@Autowired
	public abstract Service<T, K> getService();

	@Autowired
	private MessageSourceLocale messageSource;

	@SuppressWarnings("unchecked")
	protected void addToCache(Entity e) {
		Thread t = new Thread() {
			@Override
			public void run() {
				findOne((K) e.getId());
			}
		};
		t.start();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAnyAuthority('APP_ROLE')")
	@ApiOperation(value = "Returns an iterable with all the items", responseContainer = "List")
	public Iterable<T> findAll() {
		return getService().findAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@ApiOperation(value = "Returns a single item by its id")
	public T findOne(@PathParam("id") K id) {
		return getService().findOne(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Persists a new item")
	public T save(@Valid T entity) throws AppException {
		if (entity.getId() == null) {
			return getService().saveOne(entity);
		} else {
			throw new AppException(messageSource.getMessage("exception.id_not_null", null));
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Updates an existing item")
	public T update(@Valid T entity) throws AppException {
		if (entity.getId() != null) {
			return getService().saveOne(entity);
		} else {
			throw new AppException(messageSource.getMessage("exception.entity_not_exist", null));
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Deletes an item")
	public void delete(@PathParam("id") K id) throws AppException {
		getService().deleteOne(id);
	}

}
