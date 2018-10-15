package com.ktds.reply.vo;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReplyVOForMongo {

	@Id
	private String id;

	private List<String> mentionedEmailList;
	private List<String> recommendEmailList;
	private DateTime modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getMentionedEmailList() {
		return mentionedEmailList;
	}

	public void setMentionedEmailList(List<String> mentionedEmailList) {
		this.mentionedEmailList = mentionedEmailList;
	}

	public List<String> getRecommendEmailList() {
		return recommendEmailList;
	}

	public void setRecommendEmailList(List<String> recommendEmailList) {
		this.recommendEmailList = recommendEmailList;
	}

	public DateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(DateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

}
