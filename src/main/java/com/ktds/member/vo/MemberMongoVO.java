package com.ktds.member.vo;

import java.util.List;

import org.springframework.data.annotation.Id;

public class MemberMongoVO {

	@Id
	private String id;
	
	private String email;
	private List<String> adviceHashtags;
	private List<String> concernHashtags;
	private List<String> bookmarkGoalIds;

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

	public List<String> getAdviceHashtags() {
		return adviceHashtags;
	}

	public void setAdviceHashtags(List<String> adviceHashtags) {
		this.adviceHashtags = adviceHashtags;
	}

	public List<String> getConcernHashtags() {
		return concernHashtags;
	}

	public void setConcernHashtags(List<String> concernHashtags) {
		this.concernHashtags = concernHashtags;
	}

	public List<String> getBookmarkGoalIds() {
		return bookmarkGoalIds;
	}

	public void setBookmarkGoalIds(List<String> bookmarkGoalIds) {
		this.bookmarkGoalIds = bookmarkGoalIds;
	}
	
}
