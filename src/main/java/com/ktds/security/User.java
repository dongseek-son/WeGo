package com.ktds.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktds.common.authority.Authority;

public class User implements UserDetails{

	private String id;
	private String password;
	private String authority;
	private boolean isExpired;
	private boolean isLoginBlock;
	private boolean isExpiredPassword;
	private boolean isBlock;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", authority=" + authority + ", isExpired=" + isExpired
				+ ", isLoginBlock=" + isLoginBlock + ", isExpiredPassword=" + isExpiredPassword + ", isBlock=" + isBlock
				+ "]";
	}

	public User(String id, String password, int authority
			, boolean isExpired, boolean isLoginBlock, boolean isExpiredPassword, boolean isBlock) {
		this.id = id;
		this.password = password;
		this.authority = Authority.AUTHORITIES[authority];
		this.isExpired = isExpired;
		this.isLoginBlock = isLoginBlock;
		this.isExpiredPassword = isExpiredPassword;
		this.isBlock = isBlock;
		this.toString();
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

	public void setId(String id) {
		this.id = id;
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
		return this.id;
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
	
}
