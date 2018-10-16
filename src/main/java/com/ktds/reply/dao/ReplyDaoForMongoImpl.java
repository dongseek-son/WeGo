package com.ktds.reply.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ktds.goal.vo.GoalVOForMongo;
import com.ktds.member.dao.MemberDaoForMongoImpl;
import com.ktds.reply.vo.ReplyVOForMongo;
import com.mongodb.client.result.UpdateResult;

import org.springframework.data.domain.Sort.Direction;

@Repository
public class ReplyDaoForMongoImpl implements ReplyDaoForMongo {

	private Logger logger = LoggerFactory.getLogger(ReplyDaoForMongoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String insertReplyVOForMongo(ReplyVOForMongo replyVOForMongo) {
		return this.mongoTemplate.insert(replyVOForMongo, "reply").getId();
	}

	@Override
	public ReplyVOForMongo findReplyVOForMongo(String id) {
		Query query = new Query(new Criteria("_id").is(id));
		return this.mongoTemplate.findOne(query, ReplyVOForMongo.class, "reply");
	}

	@Override
	public List<ReplyVOForMongo> findReplyVOForMongoListByRecommendEmail(String email) {
		Query query = new Query(new Criteria("mentionedEmailList").all(email));
		query.with(new Sort(Direction.DESC, "createDate"));
		
		return this.mongoTemplate.find(query, ReplyVOForMongo.class, "reply");
	}

	@Override
	public List<ReplyVOForMongo> findReplyVOForMongoListByRecommendEmail(String email, int page, int size) {
		Query query = new Query(new Criteria("mentionedEmailList").all(email));
		query.with(PageRequest.of(page, size, Direction.DESC, "createDate"));
		
		return this.mongoTemplate.find(query, ReplyVOForMongo.class, "reply");
	}
	
	@Override
	public UpdateResult addRecommendEmailList(String replyId, String email) {
		Query query = new Query(new Criteria("replyId").is(replyId));
		
		Update update = new Update();
		update.addToSet("recommendEmailList", email);
		
		return this.mongoTemplate.updateFirst(query, update, ReplyVOForMongo.class, "reply");
	}
	
	@Override
	public UpdateResult pullRecommendEmailList(String replyId, String email) {
		Query query = new Query(new Criteria("replyId").is(replyId));
		
		Update update = new Update();
		update.pull("recommendEmailList", email);

		return this.mongoTemplate.updateFirst(query, update, ReplyVOForMongo.class, "reply");
	}
	
	@Override
	public UpdateResult setReplyId(String mongoId, String replyId) {
		Query query = new Query(new Criteria("_id").is(mongoId));
		
		Update update = new Update();
		update.set("replyId", replyId);
	
		return this.mongoTemplate.updateFirst(query, update, ReplyVOForMongo.class, "reply");
	}

}
