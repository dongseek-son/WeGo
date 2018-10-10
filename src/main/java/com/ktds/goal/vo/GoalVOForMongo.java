package com.ktds.goal.vo;

import java.util.List;

import org.springframework.data.annotation.Id;

public class GoalVOForMongo {

	@Id
	private String id;

	private String goalId;
	private List<String> tagList;
	private List<String> recomendEmailList;
	
	

	@Override
	public String toString() {
		return "GoalVOForMongo [id=" + id + ", goalId=" + goalId + ", tagList=" + tagList + ", recomendEmailList="
				+ recomendEmailList + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoalId() {
		return goalId;
	}

	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public List<String> getRecomendEmailList() {
		return recomendEmailList;
	}

	public void setRecomendEmailList(List<String> recomendEmailList) {
		this.recomendEmailList = recomendEmailList;
	}

}
