package com.ktds.goal.vo;

import java.util.List;

public class GoalVOForForm {

	private String id;
	private String title;
	private String detail;
	private String parentGoalId;
	private String email;
	private String isOpen;
	private String isDurablity;

	private List<String> tagList;
	
	private String token;
	
	@Override
	public String toString() {
		return "GoalVOForForm [id=" + id + ", title=" + title + ", detail=" + detail + ", parentGoalId=" + parentGoalId
				+ ", email=" + email + ", isOpen=" + isOpen + ", isDurablity=" + isDurablity + ", tagList=" + tagList
				+ "]";
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getParentGoalId() {
		return parentGoalId;
	}

	public void setParentGoalId(String parentGoalId) {
		this.parentGoalId = parentGoalId;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getIsDurablity() {
		return isDurablity;
	}

	public void setIsDurablity(String isDurablity) {
		this.isDurablity = isDurablity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
