package com.demo.app.domain.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(schema = "security", name = "user_authorities")
public class Authority implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 9171141200000360825L;

	@Id
	private String id;

	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User username;

	@Column(name = "AUTHORITY")
	private String authority;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
