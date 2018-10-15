package com.ktds.goal.dao;

import java.util.List;

import com.ktds.goal.vo.GoalVOForMongo;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface GoalDaoForMongo {

	public String insertGoalVOForMongo(GoalVOForMongo goalVOForMongo);
	
	public GoalVOForMongo findGoalVOForMongo(String id);
	
	public List<GoalVOForMongo> findGoalVOForMongoListByTag(String tag);
	
	public List<GoalVOForMongo> findGoalVOForMongoListByTag(String tag, int page, int size);
	
	public UpdateResult upsertGoalId(String mongoId, String goalId);
	
	public boolean isRecommendEmail(String goalId, String email);
	
	public UpdateResult addRecommendEmailList(String goalId, String email);
	
	public UpdateResult pullRecommendEmailList(String goalId, String email);
	
}
