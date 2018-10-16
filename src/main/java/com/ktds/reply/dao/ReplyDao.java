package com.ktds.reply.dao;

import java.util.List;

import com.ktds.reply.vo.ReplyVO;

public interface ReplyDao {

	public int insertReply(ReplyVO replyVO);
	
	public ReplyVO selectReplyByMongoId(String mongoId);
	
	public ReplyVO selectReplyById(String id);
	
	public List<ReplyVO> selectReplyListByGoalId(String goalId);
	
	public List<ReplyVO> selectChildrenReplyListByReplyId(String replyId);
	
	public int selectReplyCountByGoalId(String goalId);
	
	public int updateIsDelete(String replyId);
}
