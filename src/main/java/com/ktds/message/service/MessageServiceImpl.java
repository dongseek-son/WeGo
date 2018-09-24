package com.ktds.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.common.authority.Authority;
import com.ktds.member.vo.MemberVO;
import com.ktds.message.dao.MessageDao;
import com.ktds.message.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	@Override
	public boolean createMessage(MessageVO messageVO) {
		return this.messageDao.insertMessage(messageVO) > 0;
	}

	@Override
	public List<MessageVO> readAllMessages() {
		return this.messageDao.selectMessageList();
	}

	@Override
	public List<MessageVO> readSendMessages(MemberVO memberVO) {
		return this.messageDao.selectSendMessageList(memberVO.getId());
	}

	@Override
	public List<MessageVO> readReceiveMessages(MemberVO memberVO) {
		return this.messageDao.selectReceiveMessageList(memberVO.getId());
	}

	@Override
	public MessageVO readOneMessageById(String messageId, MemberVO memberVO) {
		MessageVO messageVO = this.messageDao.selectOneMessageById(messageId);
		
		if ( messageVO.getReceiverId().equals(memberVO.getId()) && messageVO.getReadDate() == null ) {
			this.messageDao.updateReadDate(messageId);
		}
		
		return messageVO;
	}

	@Override
	public boolean updateIsDelete(String messageId, MemberVO memberVO) {
		MessageVO messageVO = this.messageDao.selectOneMessageById(messageId);
		
		if ( messageVO.getReceiverId().equals(memberVO.getId()) ) {
			return this.messageDao.updateReceiverDelete(messageId) > 0;
		}
		else if ( messageVO.getSenderId().equals(memberVO.getId()) ) {
			return this.messageDao.updateSenderDelete(messageId) > 0;
		}
		else if ( memberVO.getAuthority() == Authority.ADMIN ) {
			this.messageDao.updateReceiverDelete(messageId);
			this.messageDao.updateSenderDelete(messageId);
			return true;
		}
		
		return false;
	}

}
