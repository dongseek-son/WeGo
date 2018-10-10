package com.ktds.member.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ktds.member.vo.MemberVOForMongo;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Repository
public class MemberDaoForMongoImpl implements MemberDaoForMongo {

	private Logger logger = LoggerFactory.getLogger(MemberDaoForMongoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public MemberVOForMongo insertMemberVOForMongo(MemberVOForMongo memberVOForMongo) {
		return this.mongoTemplate.insert(memberVOForMongo, "member");
	}
	
	@Override
	public UpdateResult updateMemberVOForMongo(MemberVOForMongo memberVOForMongo) {
		Query query = new Query(new Criteria("email").is(memberVOForMongo.getEmail()));
		
		Update update = new Update();
		update.set("adviceTagList", memberVOForMongo.getAdviceTagList());
		update.set("concernTagList", memberVOForMongo.getConcernTagList());
		
		return this.mongoTemplate.updateFirst(query, update, MemberVOForMongo.class, "member");
	}
	
	@Override
	public DeleteResult deleteMemberVOForMongo(MemberVOForMongo memberVOForMongo) {
		Query query = new Query(new Criteria("email").is(memberVOForMongo.getEmail()));
		return this.mongoTemplate.remove(query, MemberVOForMongo.class, "member");
	}
	
	@Override
	public MemberVOForMongo findOneMemberVOForMongoByEmail(String email) {
		Query query = new Query(new Criteria("email").is(email));
		return this.mongoTemplate.findOne(query, MemberVOForMongo.class, "member");
	}
	
	@Override
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag) {
		Query query = new Query(new Criteria("adviceTagList").all(tag));
		return this.mongoTemplate.find(query, MemberVOForMongo.class, "member");
	}
	
	@Override
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag1, String tag2) {
		Query query = new Query(new Criteria("adviceTagList").all(tag1, tag2));
		return this.mongoTemplate.find(query, MemberVOForMongo.class, "member");
	}
	
	@Override
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag1, String tag2, String tag3) {
		Query query = new Query(new Criteria("adviceTagList").all(tag1, tag2, tag3));
		return this.mongoTemplate.find(query, MemberVOForMongo.class, "member");
	}
	
	@Override
	public UpdateResult upsertAddConcernTag(String email, String concernTag) {
		Query query = new Query(new Criteria("email").is(email));
		
		Update update = new Update();
		update.addToSet("concernTagList", concernTag);
		
		return this.mongoTemplate.upsert(query, update, MemberVOForMongo.class, "member");
	}
	
	@Override
	public UpdateResult upsertAddAdviceTag(String email, String adviceTag) {
		Query query = new Query(new Criteria("email").is(email));
		
		Update update = new Update();
		update.addToSet("adviceTagList", adviceTag);
		
		return this.mongoTemplate.upsert(query, update, MemberVOForMongo.class, "member");
	}
	
	@Override
	public UpdateResult upsertAddBookmarkGoalId(String email, String bookmarkGoalId) {
		Query query = new Query(new Criteria("email").is(email));
		
		Update update = new Update();
		update.addToSet("bookmarkGoalIdList", bookmarkGoalId);
		
		return this.mongoTemplate.upsert(query, update, MemberVOForMongo.class, "member");
	}
	
}
