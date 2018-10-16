package com.ktds.reply.service;

import java.util.List;

import com.ktds.reply.vo.ReplyVO;

public interface ReplyService {

	public ReplyVO createReply(ReplyVO replyVO);
	
	public List<ReplyVO> readReplyListByGoalId(String goalId);
	
	public int readReplyCountByGoalId(String goalId);
	
	public boolean updateIsDelete(String replyId);
}
