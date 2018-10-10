package com.ktds.goal.dao;

import java.util.List;

import com.ktds.goal.vo.GoalVOForMongo;

public interface GoalDaoForMongo {

	public String insertGoalVOForMongo(GoalVOForMongo goalVOForMongo);
	
	public GoalVOForMongo findGoalVOForMongo(String id);
	
	public List<GoalVOForMongo> findGoalVOForMongoListByTag(String tag);
	
}
