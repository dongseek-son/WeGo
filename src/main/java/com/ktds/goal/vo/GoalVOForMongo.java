package com.ktds.goal.vo;

import java.util.List;

import org.springframework.data.annotation.Id;

public class GoalVOForMongo {

	@Id
	private String id;

	private List<String> tagList;
	private List<String> recomendEmailList;

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

	public List<String> getRecomendEmailList() {
		return recomendEmailList;
	}

	public void setRecomendEmailList(List<String> recomendEmailList) {
		this.recomendEmailList = recomendEmailList;
	}

}
