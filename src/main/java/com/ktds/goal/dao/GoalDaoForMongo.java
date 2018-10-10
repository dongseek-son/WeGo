package com.ktds.goal.dao;

import com.ktds.goal.vo.GoalVOForMongo;

public interface GoalDaoForMongo {

	public String insertGoalVOForMongo(GoalVOForMongo goalVOForMongo);
	
	public GoalVOForMongo findGoalVOForMongo(String id);
	
}
