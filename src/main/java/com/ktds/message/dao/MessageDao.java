package com.ktds.message.dao;

import java.util.List;

import com.ktds.message.vo.MessageVO;

public interface MessageDao {

	public int insertMessage(MessageVO messageVO);
	
	public List<MessageVO> selectMessageList();
	
	public List<MessageVO> selectSendMessageList(String memberId);
	
	public List<MessageVO> selectReceiveMessageList(String memberId);
	
	public MessageVO selectOneMessageById(String messageId);
	
	public int updateReadDate(String messageId);
	
	public int updateSenderDelete(String messageId);
	
	public int updateReceiverDelete(String messageId);
	
}
