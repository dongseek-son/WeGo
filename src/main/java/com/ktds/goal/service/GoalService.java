package com.ktds.goal.service;

import java.util.List;

import com.ktds.goal.vo.GoalVO;
import com.ktds.goal.vo.GoalVOForForm;
import com.mongodb.client.result.UpdateResult;

public interface GoalService {

	public boolean createGoal(GoalVOForForm goalVOForForm);
	
	public GoalVO readOneGoal(String id);
	
	public List<GoalVO> readAllGoals(int page, int size);
	
	public List<GoalVO> readAllGoalsByEmail(String email);
	
	public GoalVO readParentGoal(String id);
	
	public List<GoalVO> readChildrenGoalList(String id);
	
	public GoalVO readLatestModifyGoalByEmail(String email);
	
	public List<GoalVO> readGoalListByLevel(String email, int level);
	
	public List<GoalVO> readGoalVOListByTag(String tag);
	
	public List<GoalVO> readGoalVOListByTag(String tag, int page, int size);
	
	public boolean modifyGoalIdInGoalVOForMongo(String mongoId, String goalId);
	
	public boolean isRecommendEmail(String goalId, String email);
	
	public boolean addRecommendEmailList(String goalId, String email);
	
	public boolean pullRecommendEmailList(String goalId, String email);
	
	public boolean modifyDelete(String id);
	
	public boolean modifySuccess(String id);
	
	public boolean modifyGoal(GoalVOForForm goalVOForForm);
	
}
