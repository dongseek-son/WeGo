package com.ktds.goal.dao;

import java.util.List;
import java.util.Map;

import com.ktds.goal.vo.GoalPageVO;
import com.ktds.goal.vo.GoalVO;

public interface GoalDao {

	public int insertGoal(GoalVO goalVO);
	
	public GoalVO selectGoal(String id);
	
	public List<GoalVO> selectGoalList(GoalPageVO goalPageVO);
	
	public List<GoalVO> selectGoalListByEamil(String email);
	
	public GoalVO selectParentGoal(String id);
	
	public List<GoalVO> selectChildrenGoalList(String id);
	
	public GoalVO selectLatestModifyGoalByEmail(String email);
	
	public List<GoalVO> selectGoalListByLevel(Map<String, Object> param);
	
	public GoalVO selectGoalByMongoId(String mongoId);
	
	public int updateDelete(String id);
	
	public int updateSuccess(String id);
	
	public int updateGoal(GoalVO goalVO);
}
