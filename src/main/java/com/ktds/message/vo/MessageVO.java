package com.ktds.message.vo;

public class MessageVO {

	private String id;
	private String title;
	private String detail;
	private String sendDate;
	private String readDate;
	private boolean isSenderDelete;
	private boolean isReceiverDelete;
	private String senderId;
	private String receiverId;

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

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getReadDate() {
		return readDate;
	}

	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}

	public boolean isSenderDelete() {
		return isSenderDelete;
	}

	public void setSenderDelete(boolean isSenderDelete) {
		this.isSenderDelete = isSenderDelete;
	}

	public boolean isReceiverDelete() {
		return isReceiverDelete;
	}

	public void setReceiverDelete(boolean isReceiverDelete) {
		this.isReceiverDelete = isReceiverDelete;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

}
