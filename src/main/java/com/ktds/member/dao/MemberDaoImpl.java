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
	

}
