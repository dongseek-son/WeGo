package com.ktds.reply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.reply.vo.ReplyVO;

@Repository
public class ReplyDaoImpl extends SqlSessionDaoSupport implements ReplyDao {

	private Logger logger = LoggerFactory.getLogger(ReplyDaoImpl.class);
	
	@Override
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertReply(ReplyVO replyVO) {
		return this.getSqlSession().insert("ReplyDao.insertReply", replyVO);
	}
	
	@Override
	public ReplyVO selectReplyByMongoId(String mongoId) {
		return this.getSqlSession().selectOne("ReplyDao.selectReplyByMongoId", mongoId);
	}
	
	@Override
	public ReplyVO selectReplyById(String id) {
		return this.getSqlSession().selectOne("ReplyDao.selectReplyById", id);
	}

	@Override
	public List<ReplyVO> selectReplyListByGoalId(String goalId) {
		return this.getSqlSession().selectList("ReplyDao.selectReplyListByGoalId", goalId);
	}
	
	@Override
	public List<ReplyVO> selectChildrenReplyListByReplyId(String replyId) {
		return this.getSqlSession().selectList("ReplyDao.selectChildrenReplyListByReplyId", replyId);
	}
	
	@Override
	public int selectReplyCountByGoalId(String goalId) {
		return this.getSqlSession().selectOne("ReplyDao.selectReplyCountByGoalId", goalId);
	}
	
}
