package com.relcare.authenticator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RelUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private int userid;
	private String username;
	private String password;
	private ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

	public RelUserDetails() {
		super();
	}

	public RelUserDetails(int userid, String username, String password, String userRoles) {
		super();
		this.setUserid(userid);
		this.username = username;
		this.password = password;
		for (String role : userRoles.split(",")) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
		}
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// default never expires
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// default not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// default never expiring
		return true;
	}

	@Override
	public boolean isEnabled() {
		// default
		return true;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
