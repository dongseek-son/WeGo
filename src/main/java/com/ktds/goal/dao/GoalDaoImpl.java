package com.ktds.goal.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

import com.ktds.goal.vo.GoalVO;
import com.ktds.member.dao.MemberDaoImpl;

@Repository
public class GoalDaoImpl extends SqlSessionDaoSupport implements GoalDao {

	private Logger logger = LoggerFactory.getLogger(GoalDaoImpl.class);
	
	@Override
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertGoal(GoalVO goalVO) {
		return this.getSqlSession().insert("GoalDao.insertGoal", goalVO);
	}

}
