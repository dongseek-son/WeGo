package com.ktds.member.vo;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {

	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", password=" + password + ", name=" + name + ", tel=" + tel + ", salt="
				+ salt + ", profileFilename=" + profileFilename + ", profileOriginFilename=" + profileOriginFilename
				+ ", authority=" + authority + ", latestLogin=" + latestLogin + ", loginFailCount=" + loginFailCount
				+ ", latestPasswordChange=" + latestPasswordChange + ", profileFile=" + profileFile + "]";
	}

	private String email;
	private String password;
	private String name;
	private String tel;
	private String salt;
	private String profileFilename;
	private String profileOriginFilename;
	private int authority;
	private String latestLogin;
	private int loginFailCount;
	private String latestPasswordChange;
	
	private MultipartFile profileFile;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getProfileFilename() {
		return profileFilename;
	}

	public void setProfileFilename(String profileFilename) {
		this.profileFilename = profileFilename;
	}

	public String getProfileOriginFilename() {
		return profileOriginFilename;
	}

	public void setProfileOriginFilename(String profileOriginFilename) {
		this.profileOriginFilename = profileOriginFilename;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getLatestLogin() {
		return latestLogin;
	}

	public void setLatestLogin(String latestLogin) {
		this.latestLogin = latestLogin;
	}

	public int getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public String getLatestPasswordChange() {
		return latestPasswordChange;
	}

	public void setLatestPasswordChange(String latestPasswordChange) {
		this.latestPasswordChange = latestPasswordChange;
	}

	public MultipartFile getProfileFile() {
		return profileFile;
	}

	public void setProfileFile(MultipartFile profileFile) {
		this.profileFile = profileFile;
	}

}
