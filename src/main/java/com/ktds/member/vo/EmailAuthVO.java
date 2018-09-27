package com.ktds.member.vo;

public class EmailAuthVO {

	private String authUrl;
	private String email;
	private String registDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public EmailAuthVO(String authUrl, String email) {
		this.authUrl = authUrl;
		this.email = email;
		this.registDate = null;
	}

	public EmailAuthVO() {

	}

}
