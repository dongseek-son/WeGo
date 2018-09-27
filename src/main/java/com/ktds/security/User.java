package com.ktds.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktds.common.authority.Authority;

public class User implements UserDetails{

	private String email;
	private String password;
	private String authority;
	private boolean isExpired;
	private boolean isLoginBlock;
	private boolean isExpiredPassword;
	private boolean isBlock;
	private boolean isEmailAuth;

	public User(String email, String password, int authority
			, boolean isExpired, boolean isLoginBlock, boolean isExpiredPassword, boolean isBlock, boolean isEmailAuth) {
		this.email = email;
		this.password = password;
		this.authority = Authority.AUTHORITIES[authority];
		this.isExpired = isExpired;
		this.isLoginBlock = isLoginBlock;
		this.isExpiredPassword = isExpiredPassword;
		this.isBlock = isBlock;
		this.isEmailAuth = isEmailAuth;
	}
	
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
	
	public void setLoginBlock(boolean isLoginBlock) {
		this.isLoginBlock = isLoginBlock;
	}
	
	public void setExpiredPassword(boolean isExpiredPassword) {
		this.isExpiredPassword = isExpiredPassword;
	}
	
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}
	
	public void setEmailAuth(boolean isEmailAuth) {
		this.isEmailAuth = isEmailAuth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add( new SimpleGrantedAuthority( authority ) );
		if ( authority.equals("ROLE_ADMIN") ) {
			authorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.isLoginBlock;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.isExpiredPassword;
	}

	@Override
	public boolean isEnabled() {
		return !this.isBlock;
	}
	
	public boolean isEmailAuth() {
		return this.isEmailAuth;
	}
	
}
