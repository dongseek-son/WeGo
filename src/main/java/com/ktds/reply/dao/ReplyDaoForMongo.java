package com.ktds.reply.dao;

import java.util.List;

import com.ktds.reply.vo.ReplyVOForMongo;

public interface ReplyDaoForMongo {

	public String insertReplyVOForMongo(ReplyVOForMongo replyVOForMongo);
	
	public ReplyVOForMongo findReplyVOForMongo(String id);
	
	public List<ReplyVOForMongo> findReplyVOForMongoListByMentionedEmail(String email);
	
	public List<ReplyVOForMongo> findReplyVOForMongoListByMentionedEmail(String email, int page, int size);
	
}
