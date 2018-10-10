package com.ktds.goal.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ktds.goal.vo.GoalVOForMongo;
import com.ktds.member.dao.MemberDaoForMongoImpl;

@Repository
public class GoalDaoForMongoImpl implements GoalDaoForMongo {

private Logger logger = LoggerFactory.getLogger(MemberDaoForMongoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void insertGoalDaoForMongo(GoalVOForMongo goalVOForMongo) {
		System.out.println(goalVOForMongo.toString());
		this.mongoTemplate.insert(goalVOForMongo, "goal");
	}

}
