package com.ktds.reply.vo;

public class ReplyVO {

	private String id;
	private String parentReplyId;
	private String memberId;
	private String writeDate;
	private boolean isDelete;
	private String goalId;
	private String detail;
	private boolean isAdvice;
	private boolean isBlock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentReplyId() {
		return parentReplyId;
	}

	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getGoalId() {
		return goalId;
	}

	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}

	public boolean isAdvice() {
		return isAdvice;
	}

	public void setAdvice(boolean isAdvice) {
		this.isAdvice = isAdvice;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

}
