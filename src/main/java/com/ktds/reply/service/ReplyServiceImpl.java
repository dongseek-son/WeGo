package com.ktds.reply.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.reply.dao.ReplyDao;
import com.ktds.reply.dao.ReplyDaoForMongo;
import com.ktds.reply.vo.ReplyVO;
import com.ktds.reply.vo.ReplyVOForMongo;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private ReplyDaoForMongo replyDaoForMongo;
	
	private ReplyVO combineReplyVO(ReplyVO replyVO) {
		if ( replyVO != null ) {
			replyVO.setReplyVOForMongo(this.replyDaoForMongo.findReplyVOForMongo(replyVO.getMongoId()));
		}
		return replyVO;
	}
	
	private List<ReplyVO> combineReplyVOList(List<ReplyVO> replyVOList) {
		if ( replyVOList.size() > 0 ) {
			for ( ReplyVO replyVO : replyVOList ) {
				this.combineReplyVO(replyVO);
			}
		}
		return replyVOList;
	}
	
	private List<ReplyVO> combineReplyVOListByReplyVOForMongoList(List<ReplyVOForMongo> replyVOForMongoList) {
		if ( replyVOForMongoList.size() > 0 ) {
			List<ReplyVO> replyVOList = new ArrayList<ReplyVO>();
			for ( ReplyVOForMongo replyVOForMongo : replyVOForMongoList ) {
				ReplyVO replyVO = this.replyDao.selectReplyByMongoId(replyVOForMongo.getId());
				if ( !replyVO.isDelete() || !replyVO.isBlock() ) {
					replyVO.setReplyVOForMongo(replyVOForMongo);
					replyVOList.add(replyVO);
				}
			}
			return replyVOList;			
		}
		return null;
	}
	
	@Override
	public ReplyVO createReply(ReplyVO replyVO) {
		String mongoId = this.replyDaoForMongo.insertReplyVOForMongo(new ReplyVOForMongo(new DateTime().plusHours(9)));
		replyVO.setMongoId(mongoId);
		this.replyDao.insertReply(replyVO);
		replyVO = this.replyDao.selectReplyByMongoId(mongoId);
		this.replyDaoForMongo.setReplyId(mongoId, replyVO.getId());
		return replyVO;
	}
	
	@Override
	public List<ReplyVO> readReplyListByGoalId(String goalId) {
		List<ReplyVO> replyVOList = this.replyDao.selectReplyListByGoalId(goalId);
		
		Iterator<ReplyVO> replyVOListIterator = replyVOList.iterator();
		while (replyVOListIterator.hasNext()) {
		   ReplyVO replyVO = replyVOListIterator.next(); // must be called before you can call i.remove()
		   this.combineReplyVO(replyVO);
		   replyVO.setChildrenReplyVOList(this.combineReplyVOList(this.replyDao.selectChildrenReplyListByReplyId(replyVO.getId())));
		   
		   if ( replyVO.isDelete() ) {
			   if ( replyVO.getChildrenReplyVOList() == null 
			   		|| replyVO.getChildrenReplyVOList().size() == 0 
			   		|| this.replyDao.isChildrenListAllDeleted(replyVO.getId()) ) {
				   replyVOListIterator.remove();
			   }
		   }
		}
		
		return replyVOList;
	}

	@Override
	public int readReplyCountByGoalId(String goalId) {
		return this.replyDao.selectReplyCountByGoalId(goalId);
	}
	
	@Override
	public boolean updateIsDelete(String replyId) {
		return this.replyDao.updateIsDelete(replyId) > 0;
	}
}
