package com.ktds.member.vo;

import java.util.List;

import org.springframework.data.annotation.Id;

public class MemberVOForMongo {

	@Id
	private String id;
	
	private String email;
	private List<String> adviceTagList;
	private List<String> concernTagList;
	private List<String> bookmarkGoalIdList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAdviceTagList() {
		return adviceTagList;
	}

	public void setAdviceTagList(List<String> adviceTagList) {
		this.adviceTagList = adviceTagList;
	}

	public List<String> getConcernTagList() {
		return concernTagList;
	}

	public void setConcernTagList(List<String> concernTagList) {
		this.concernTagList = concernTagList;
	}

	public List<String> getBookmarkGoalIdList() {
		return bookmarkGoalIdList;
	}

	public void setBookmarkGoalIdList(List<String> bookmarkGoalIdList) {
		this.bookmarkGoalIdList = bookmarkGoalIdList;
	}
	
}
