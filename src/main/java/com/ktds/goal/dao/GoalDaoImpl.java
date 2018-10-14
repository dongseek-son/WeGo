package com.ktds.goal.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

import com.ktds.goal.vo.GoalPageVO;
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
	
	@Override
	public GoalVO selectGoal(String id) {
		return this.getSqlSession().selectOne("GoalDao.selectGoal", id);
	}
	
	@Override
	public List<GoalVO> selectGoalList(GoalPageVO goalPageVO) {
		return this.getSqlSession().selectList("GoalDao.selectGoalList", goalPageVO);
	}
	
	@Override
	public List<GoalVO> selectGoalListByEamil(String email) {
		return this.getSqlSession().selectList("GoalDao.selectGoalListByEmail", email);
	}
	
	@Override
	public GoalVO selectParentGoal(String id) {
		return this.getSqlSession().selectOne("GoalDao.selectParentGoal", id);
	}
	
	@Override
	public List<GoalVO> selectChildrenGoalList(String id) {
		return this.getSqlSession().selectList("GoalDao.selectChildrenGoalList", id);
	}
	
	@Override
	public GoalVO selectLatestModifyGoalByEmail(String email) {
		return this.getSqlSession().selectOne("GoalDao.selectLatestModifyGoalByEmail", email);
	}
	
	@Override
	public List<GoalVO> selectGoalListByLevel(Map<String, Object> param) {
		return this.getSqlSession().selectList("GoalDao.selectGoalListByLevel", param);
	}
	
	@Override
	public GoalVO selectGoalByMongoId(String mongoId) {
		return this.getSqlSession().selectOne("GoalDao.selectGoalByMongoId", mongoId);
	}
	
}
