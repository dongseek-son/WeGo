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
	private String replyId;
	private DateTime createDate;
	
	public ReplyVOForMongo(DateTime createDate) {
		this.createDate = createDate;
	}

	public ReplyVOForMongo() {
	}

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

	public DateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(DateTime modifyDate) {
		this.createDate = modifyDate;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

}
