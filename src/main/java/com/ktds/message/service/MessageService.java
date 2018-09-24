package com.ktds.message.service;

import java.util.List;

import com.ktds.member.vo.MemberVO;
import com.ktds.message.vo.MessageVO;

public interface MessageService {
	
	public boolean createMessage(MessageVO messageVO);
	
	public List<MessageVO> readAllMessages();
	
	public List<MessageVO> readSendMessages(MemberVO memberVO);
	
	public List<MessageVO> readReceiveMessages(MemberVO memberVO);
	
	public MessageVO readOneMessageById(String messageId, MemberVO memberVO);
	
	public boolean updateIsDelete(String messageId, MemberVO memberVO);
	
}
