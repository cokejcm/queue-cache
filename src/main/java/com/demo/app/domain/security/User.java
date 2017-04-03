package com.demo.app.domain.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "security", name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -1833543647066464068L;

	@Id
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACCOUNT_NON_EXPIRED")
	private boolean accountNonExpired;

	@Column(name = "ACCOUNT_NON_LOCKED")
	private boolean accountNonLocked;

	@Column(name = "ENABLED")
	private boolean enabled;

	@OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
	private List<Authority> authorities;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public org.springframework.security.core.userdetails.User createSpringUser() {
		return new org.springframework.security.core.userdetails.User(this.username, this.password, this.enabled, this.accountNonExpired, true, this.accountNonLocked, this.authorities);
	}
}