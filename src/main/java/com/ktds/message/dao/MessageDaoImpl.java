package com.ktds.message.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.message.vo.MessageVO;

@Repository
public class MessageDaoImpl extends SqlSessionDaoSupport implements MessageDao {

	private Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);

	@Override
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertMessage(MessageVO messageVO) {
		return this.getSqlSession().insert("MessageDao.insertMessage", messageVO);
	}

	@Override
	public List<MessageVO> selectMessageList() {
		return this.getSqlSession().selectList("MessageDao.selectMessageList");
	}

	@Override
	public List<MessageVO> selectSendMessageList(String email) {
		return this.getSqlSession().selectList("MessageDao.selectSendMessageList", email);
	}

	@Override
	public List<MessageVO> selectReceiveMessageList(String email) {
		return this.getSqlSession().selectList("MessageDao.selectReceiveMessageList", email);
	}

	@Override
	public MessageVO selectOneMessageById(String messageId) {
		return this.getSqlSession().selectOne("MessageDao.selectOneMessageById", messageId);
	}

	@Override
	public int updateReadDate(String messageId) {
		return this.getSqlSession().update("MessageDao.updateReadDate", messageId);
	}

	@Override
	public int updateSenderDelete(String messageId) {
		return this.getSqlSession().update("MessageDao.updateSenderDelete", messageId);
	}

	@Override
	public int updateReceiverDelete(String messageId) {
		return this.getSqlSession().update("MessageDao.updateReceiverDelete", messageId);
	}

}
