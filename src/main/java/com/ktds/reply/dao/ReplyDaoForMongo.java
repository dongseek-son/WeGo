package com.ktds.reply.dao;

import java.util.List;

import com.ktds.reply.vo.ReplyVOForMongo;
import com.mongodb.client.result.UpdateResult;

public interface ReplyDaoForMongo {

	public String insertReplyVOForMongo(ReplyVOForMongo replyVOForMongo);
	
	public ReplyVOForMongo findReplyVOForMongo(String id);
	
	public List<ReplyVOForMongo> findReplyVOForMongoListByRecommendEmail(String email);
	
	public List<ReplyVOForMongo> findReplyVOForMongoListByRecommendEmail(String email, int page, int size);

	public UpdateResult addRecommendEmailList(String replyId, String email);

	public UpdateResult pullRecommendEmailList(String replyId, String email);

	public UpdateResult setReplyId(String mongoId, String replyId);
	
}
