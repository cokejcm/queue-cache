package com.demo.app.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.security.SecretService;

@Path("/login")
@Component
public class LoginController {

	@Autowired
	SecretService secretService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String authenticate() {
		//Recibe usuario y contraseña
		//Valida y genera token
		//Manda token de vuelta en la cabecera
		//
		return "hola";
	}

}
