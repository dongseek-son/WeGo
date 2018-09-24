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
	public MemberVO selectOneMemberById(String id) {
		return getSqlSession().selectOne("MemberDao.selectOneMemberById", id);
	}

	@Override
	public MemberVO selectOneMember(MemberVO memberVO) {
		return getSqlSession().selectOne("MemberDao.selectOneMember", memberVO);
	}

	@Override
	public String selectSaltById(String id) {
		return getSqlSession().selectOne("MemberDao.selectSaltById", id);
	}

	@Override
	public boolean isExpired(String id) {
		return getSqlSession().selectOne("MemberDao.isExpired", id);
	}

	@Override
	public boolean isExpiredPassword(String id) {
		return getSqlSession().selectOne("MemberDao.isExpiredPassword", id);
	}

	@Override
	public boolean isLoginBlock(String id) {
		return getSqlSession().selectOne("MemberDao.isLoginBlock", id);
	}

}
