package com.ktds.member.dao;

import java.util.List;

import com.ktds.member.vo.MemberMongoVO;

public interface MemberDaoForMongo {

	public void insertMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public void updateMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public void deleteMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public MemberMongoVO findOneMemberMongoVOByEmail(String email);
	
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String hashtag);
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String h1, String h2);
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String h1, String h2, String h3);
	
}
