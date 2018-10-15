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
import org.springframework.stereotype.Repository;

import com.ktds.goal.vo.GoalVOForMongo;
import com.ktds.member.dao.MemberDaoForMongoImpl;
import com.ktds.reply.vo.ReplyVOForMongo;

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
	public List<ReplyVOForMongo> findReplyVOForMongoListByMentionedEmail(String email) {
		Query query = new Query(new Criteria("mentionedEmailList").all(email));
		query.with(new Sort(Direction.DESC, "modifyDate"));
		
		return this.mongoTemplate.find(query, ReplyVOForMongo.class, "reply");
	}

	@Override
	public List<ReplyVOForMongo> findReplyVOForMongoListByMentionedEmail(String email, int page, int size) {
		Query query = new Query(new Criteria("mentionedEmailList").all(email));
		query.with(PageRequest.of(page, size, Direction.DESC, "modifyDate"));
		
		return this.mongoTemplate.find(query, ReplyVOForMongo.class, "reply");
	}

}
