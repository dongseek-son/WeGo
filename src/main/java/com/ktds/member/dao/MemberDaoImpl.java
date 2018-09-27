package com.ktds.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ktds.member.vo.EmailAuthVO;
import com.ktds.member.vo.MemberMongoVO;
import com.ktds.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {
	
	private Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		logger.debug("Initiate MyBatis");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertMember(MemberVO memberVO) {
		return getSqlSession().insert("MemberDao.insertMember", memberVO);
	}

	@Override
	public MemberVO selectOneMemberByEmail(String email) {
		return getSqlSession().selectOne("MemberDao.selectOneMemberByEmail", email);
	}

	@Override
	public MemberVO selectOneMember(MemberVO memberVO) {
		return getSqlSession().selectOne("MemberDao.selectOneMember", memberVO);
	}

	@Override
	public String selectSaltByEmail(String email) {
		return getSqlSession().selectOne("MemberDao.selectSaltByEmail", email);
	}

	@Override
	public boolean isExpired(String email) {
		return getSqlSession().selectOne("MemberDao.isExpired", email);
	}

	@Override
	public boolean isExpiredPassword(String email) {
		return getSqlSession().selectOne("MemberDao.isExpiredPassword", email);
	}

	@Override
	public boolean isLoginBlock(String email) {
		return getSqlSession().selectOne("MemberDao.isLoginBlock", email);
	}
	
	@Override
	public boolean isEmailAuth(String email) {
		return getSqlSession().selectOne("isEmailAuth", email);
	}
	
	@Override
	public int insertEmailAuth(EmailAuthVO emailAuthVO) {
		return getSqlSession().insert("insertEmailAuth", emailAuthVO);
	}
	
	@Override
	public EmailAuthVO selectOneEmailAuth(String authUrl) {
		return getSqlSession().selectOne("selectOneEmailAuth", authUrl);
	}
	
	@Override
	public int deleteOneEmailAuth(String authUrl) {
		return getSqlSession().delete("deleteOneEmailAuth", authUrl);
	}
	
	@Override
	public int updateRegistDate(String email) {
		return getSqlSession().update("updateRegistDate", email);
	}
	
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
