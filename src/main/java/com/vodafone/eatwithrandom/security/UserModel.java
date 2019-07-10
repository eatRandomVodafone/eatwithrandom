package com.vodafone.eatwithrandom.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * class describes user model received in body of authentication request in case
 * of wifi login
 * 
 */
public class UserModel implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private String password;

	private Collection<GrantedAuthority> authorities;

	public UserModel(String username, Collection<GrantedAuthority> authorities) {

		super();
		this.authorities = authorities;
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}