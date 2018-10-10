package com.ktds.goal.vo;

public class GoalVO {

	private String id;
	private String title;
	private String detail;
	private String parentGoalId;
	private String email;
	private boolean isSuccess;
	private boolean isBlock;
	private String writeDate;
	private boolean isOpen;
	private boolean isDelete;
	private boolean isDurablity;
	private boolean isBoast;
	private String modifyDate;
	private String mongoId;
	
	private GoalVOForMongo goalVOForMongo;

	@Override
	public String toString() {
		return "GoalVO [id=" + id + ", title=" + title + ", detail=" + detail + ", parentGoalId=" + parentGoalId
				+ ", email=" + email + ", isSuccess=" + isSuccess + ", isBlock=" + isBlock + ", writeDate=" + writeDate
				+ ", isOpen=" + isOpen + ", isDelete=" + isDelete + ", isDurablity=" + isDurablity + ", isBoast="
				+ isBoast + ", modifyDate=" + modifyDate + ", mongoId=" + mongoId + ", goalVOForMongo=" + goalVOForMongo
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isDurablity() {
		return isDurablity;
	}

	public void setDurablity(boolean isDurablity) {
		this.isDurablity = isDurablity;
	}

	public boolean isBoast() {
		return isBoast;
	}

	public void setBoast(boolean isBoast) {
		this.isBoast = isBoast;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public GoalVOForMongo getGoalVOForMongo() {
		return goalVOForMongo;
	}

	public void setGoalVOForMongo(GoalVOForMongo goalVOForMongo) {
		this.goalVOForMongo = goalVOForMongo;
	}

}
