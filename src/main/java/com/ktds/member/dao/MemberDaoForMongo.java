package com.ktds.member.dao;

import java.util.List;

import com.ktds.member.vo.MemberVOForMongo;

public interface MemberDaoForMongo {

	public void insertMemberVOForMongo(MemberVOForMongo memberVOForMongo);
	
	public void updateMemberVOForMongo(MemberVOForMongo memberVOForMongo);
	
	public void deleteMemberVOForMongo(MemberVOForMongo memberVOForMongo);
	
	public MemberVOForMongo findOneMemberVOForMongoByEmail(String email);
	
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag);
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag1, String tag2);
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag1, String tag2, String tag3);
	
}
