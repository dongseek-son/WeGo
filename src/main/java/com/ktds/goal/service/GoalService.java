package com.ktds.goal.service;

import java.util.List;

import com.ktds.goal.vo.GoalVO;
import com.ktds.goal.vo.GoalVOForForm;

public interface GoalService {

	public boolean createGoal(GoalVOForForm goalVOForForm);
	
	public GoalVO readOneGoal(String id);
	
	public GoalVO readParentGoal(String id);
	
	public List<GoalVO> readChildrenGoalList(String id);
	
	public GoalVO readLatestModifyGoalByEmail(String email);
	
}
