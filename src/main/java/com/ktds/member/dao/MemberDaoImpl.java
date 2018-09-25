package com.ktds.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
