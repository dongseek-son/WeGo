package com.ktds.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {

	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", password=" + password + ", name=" + name + ", tel=" + tel + ", salt="
				+ salt + ", profileFilename=" + profileFilename + ", profileOriginFilename=" + profileOriginFilename
				+ ", authority=" + authority + ", latestLogin=" + latestLogin + ", loginFailCount=" + loginFailCount
				+ ", latestPasswordChange=" + latestPasswordChange + ", registDate=" + registDate + ", profileFile="
				+ profileFile + "]";
	}

	
	
	
	@NotEmpty( message="이메일은 필수 입력 값입니다." )
	@Email( message="이메일형식으로 작성해주세요." )
	private String email;
	@NotEmpty( message="비밀번호는 필수 입력 값입니다." )
	@Pattern( 
			regexp="((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{8,})"
			, message="패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요." )
	private String password;
	@NotEmpty( message="이름은 필수 입력 값입니다." )
	@Pattern( 
			regexp="(?=.*[0-9]).{10,11}"
			, message="이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)" )
	private String name;
	@NotEmpty( message="전화번호는 필수 입력 값입니다." )
	@Pattern( 
			regexp="(?=.*[0-9a-zA-Z가-힣]).{2,18}"
			, message="전화번호는 숫자만 입력해주세요.(10-11자)" )
	private String tel;
	private String salt;
	private String profileFilename;
	private String profileOriginFilename;
	private int authority;
	private String latestLogin;
	private int loginFailCount;
	private String latestPasswordChange;
	private String registDate;
	
	private MemberMongoVO memberMongoVO;

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
	
	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public MemberMongoVO getMemberMongoVO() {
		return memberMongoVO;
	}

	public void setMemberMongoVO(MemberMongoVO memberMongoVO) {
		this.memberMongoVO = memberMongoVO;
	}

}
