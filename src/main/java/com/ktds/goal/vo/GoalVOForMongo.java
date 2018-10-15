package com.ktds.goal.vo;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GoalVOForMongo {

	@Id
	private String id;

	private List<String> tagList;
	private List<String> recommendEmailList;
	private DateTime modifyDate;
	private String goalId;


	public DateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(DateTime modifyDate) {
		this.modifyDate = modifyDate;
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

	public List<String> getRecommendEmailList() {
		return recommendEmailList;
	}

	public void setRecommendEmailList(List<String> recommendEmailList) {
		this.recommendEmailList = recommendEmailList;
	}

	public String getGoalId() {
		return goalId;
	}

	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}


}
