package com.ktds.goal.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ktds.goal.vo.GoalVOForMongo;
import com.ktds.member.dao.MemberDaoForMongoImpl;
import com.ktds.member.vo.MemberVOForMongo;

@Repository
public class GoalDaoForMongoImpl implements GoalDaoForMongo {

	private Logger logger = LoggerFactory.getLogger(MemberDaoForMongoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String insertGoalVOForMongo(GoalVOForMongo goalVOForMongo) {
		return this.mongoTemplate.insert(goalVOForMongo, "goal").getId();
	}
	
	@Override
	public GoalVOForMongo findGoalVOForMongo(String id) {
		Query query = new Query(new Criteria("_id").is(id));
		return this.mongoTemplate.findOne(query, GoalVOForMongo.class, "goal");
	}
	
	@Override
	public List<GoalVOForMongo> findGoalVOForMongoListByTag(String tag) {
		Query query = new Query(new Criteria("tagList").all(tag));
		return this.mongoTemplate.find(query, GoalVOForMongo.class, "goal");
	}

}
