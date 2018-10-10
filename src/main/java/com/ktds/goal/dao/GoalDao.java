package com.ktds.goal.dao;

import java.util.List;

import com.ktds.goal.vo.GoalVO;

public interface GoalDao {

	public int insertGoal(GoalVO goalVO);
	
	public GoalVO selectGoal(String id);
	
	public GoalVO selectParentGoal(String id);
	
	public List<GoalVO> selectChildrenGoalList(String id);
	
	public GoalVO selectLatestModifyGoalByEmail(String email);
	
}
