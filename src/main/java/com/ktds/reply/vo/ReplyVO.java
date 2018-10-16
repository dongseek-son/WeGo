package com.ktds.reply.vo;

import java.util.List;

import com.ktds.member.vo.MemberVO;

public class ReplyVO {

	private String id;
	private String parentReplyId;
	private String email;
	private String writeDate;
	private boolean isDelete;
	private String goalId;
	private String detail;
	private boolean isAdvice;
	private boolean isBlock;
	private String mongoId;
	private String mentionEmail;
	
	private MemberVO memberVO;
	private String token;
	private List<ReplyVO> childrenReplyVOList;
	private ReplyVOForMongo replyVOForMongo;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMentionEmail() {
		return mentionEmail;
	}

	public void setMentionEmail(String mentionEmail) {
		this.mentionEmail = mentionEmail;
	}

	public List<ReplyVO> getChildrenReplyVOList() {
		return childrenReplyVOList;
	}

	public void setChildrenReplyVOList(List<ReplyVO> childrenReplyVOList) {
		this.childrenReplyVOList = childrenReplyVOList;
	}

	public ReplyVOForMongo getReplyVOForMongo() {
		return replyVOForMongo;
	}

	public void setReplyVOForMongo(ReplyVOForMongo replyVOForMongo) {
		this.replyVOForMongo = replyVOForMongo;
	}

}
