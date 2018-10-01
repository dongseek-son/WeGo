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

import com.ktds.member.vo.MemberMongoVO;

@Repository
public class MemberDaoForMongoImpl implements MemberDaoForMongo {

	private Logger logger = LoggerFactory.getLogger(MemberDaoForMongoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void insertMemberMongoVO(MemberMongoVO memberMongoVO) {
		this.mongoTemplate.insert(memberMongoVO, "member");	
	}
	
	@Override
	public void updateMemberMongoVO(MemberMongoVO memberMongoVO) {
		Query query = new Query(new Criteria("email").is(memberMongoVO.getEmail()));
		
		Update update = new Update();
		update.set("adviceHashtags", memberMongoVO.getAdviceHashtags());
		update.set("concernHashtags", memberMongoVO.getConcernHashtags());
		
		this.mongoTemplate.updateFirst(query, update, "member");
	}
	
	@Override
	public void deleteMemberMongoVO(MemberMongoVO memberMongoVO) {
		Query query = new Query(new Criteria("email").is(memberMongoVO.getEmail()));
		this.mongoTemplate.remove(query, "member");
	}
	
	@Override
	public MemberMongoVO findOneMemberMongoVOByEmail(String email) {
		Query query = new Query(new Criteria("email").is(email));
		return this.mongoTemplate.findOne(query, MemberMongoVO.class, "member");
	}
	
	@Override
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String hashtag) {
		Query query = new Query(new Criteria("adviceHashtags").all(hashtag));
		return this.mongoTemplate.find(query, MemberMongoVO.class);
	}
	
	@Override
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String h1, String h2) {
		Query query = new Query(new Criteria("adviceHashtags").all(h1, h2));
		return this.mongoTemplate.find(query, MemberMongoVO.class);
	}
	
	@Override
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String h1, String h2, String h3) {
		Query query = new Query(new Criteria("adviceHashtags").all(h1, h2, h3));
		return this.mongoTemplate.find(query, MemberMongoVO.class);
	}
	
}
