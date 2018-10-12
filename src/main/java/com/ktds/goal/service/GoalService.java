package com.ktds.goal.service;

import java.util.List;

import com.ktds.goal.vo.GoalVO;
import com.ktds.goal.vo.GoalVOForForm;

public interface GoalService {

	public boolean createGoal(GoalVOForForm goalVOForForm);
	
	public GoalVO readOneGoal(String id);
	
	public List<GoalVO> readAllGoals(int page, int size);
	
	public GoalVO readParentGoal(String id);
	
	public List<GoalVO> readChildrenGoalList(String id);
	
	public GoalVO readLatestModifyGoalByEmail(String email);
	
	public List<GoalVO> readGoalListByLevel(String email, int level);
	
	public List<GoalVO> readGoalVOListByTag(String tag);
	
	public List<GoalVO> readGoalVOListByTag(String tag, int page, int size);
	
}
