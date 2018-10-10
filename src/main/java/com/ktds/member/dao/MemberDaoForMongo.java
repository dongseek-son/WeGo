package com.ktds.member.dao;

import java.util.List;

import com.ktds.member.vo.MemberVOForMongo;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface MemberDaoForMongo {

	public MemberVOForMongo insertMemberVOForMongo(MemberVOForMongo memberVOForMongo);
	
	public UpdateResult updateMemberVOForMongo(MemberVOForMongo memberVOForMongo);
	
	public DeleteResult deleteMemberVOForMongo(MemberVOForMongo memberVOForMongo);
	
	public MemberVOForMongo findOneMemberVOForMongoByEmail(String email);
	
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag);
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag1, String tag2);
	public List<MemberVOForMongo> findAllMemberVOForMongoByAdviceTag(String tag1, String tag2, String tag3);
	
	public UpdateResult upsertAddConcernTag(String email, String concernTag);
	public UpdateResult upsertAddAdviceTag(String email, String adviceTag);
	public UpdateResult upsertAddBookmarkGoalId(String email, String bookmarkGoalId);
	
}
