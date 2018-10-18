package com.ktds.goal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.goal.dao.GoalDao;
import com.ktds.goal.dao.GoalDaoForMongo;
import com.ktds.goal.vo.GoalPageVO;
import com.ktds.goal.vo.GoalVO;
import com.ktds.goal.vo.GoalVOForForm;
import com.ktds.goal.vo.GoalVOForMongo;

@Service
public class GoalServiceImpl implements GoalService {

	@Autowired
	private GoalDao goalDao;
	
	@Autowired
	private GoalDaoForMongo goalDaoForMongo;
	
	private GoalVO separateGoalVOFromGoalVOForForm(GoalVOForForm goalVOForForm, String mongoId) {
		GoalVO goalVO = new GoalVO();
		
		goalVO.setTitle(goalVOForForm.getTitle());
		goalVO.setDetail(goalVOForForm.getDetail());
		goalVO.setParentGoalId(goalVOForForm.getParentGoalId());
		goalVO.setEmail(goalVOForForm.getEmail());
		goalVO.setOpen(goalVOForForm.getIsOpen() != null);
		goalVO.setDurablity(goalVOForForm.getIsDurablity() != null);
		goalVO.setMongoId(mongoId);
		
		return goalVO;
	}
	
	private GoalVOForMongo separateGoalVOForMongoFromGoalVOForForm(GoalVOForForm goalVOForForm) {
		GoalVOForMongo goalVOForMongo = new GoalVOForMongo();
		
		List<String> tagList = goalVOForForm.getTagList();
		
		Iterator<String> tagListIterator = tagList.iterator();
		while ( tagListIterator.hasNext() ) {
			String tag = tagListIterator.next();
			if ( tag == null || tag.equals("") ) {
				tagListIterator.remove();
			}
		}
		
		goalVOForMongo.setTagList(tagList);
		goalVOForMongo.setModifyDate(new DateTime().plusHours(9));
		
		return goalVOForMongo;
	}
	
	private GoalVO combineGoalVO(GoalVO goalVO) {
		if ( goalVO != null ) {
			goalVO.setGoalVOForMongo(this.goalDaoForMongo.findGoalVOForMongo(goalVO.getMongoId()));
		}
		return goalVO;
	}
	
	private List<GoalVO> combineGoalVOList(List<GoalVO> goalVOList) {
		if ( goalVOList.size() > 0 ) {
			for ( GoalVO goalVO : goalVOList ) {
				this.combineGoalVO(goalVO);
			}
		}
		return goalVOList;
	}
	
	private List<GoalVO> combineGoalVOListByGoalVOForMongoList(List<GoalVOForMongo> goalVOForMongoList) {
		if ( goalVOForMongoList.size() > 0 ) {
			List<GoalVO> goalVOList = new ArrayList<GoalVO>();
			for ( GoalVOForMongo goalVOForMongo : goalVOForMongoList ) {
				GoalVO goalVO = this.goalDao.selectGoalByMongoId(goalVOForMongo.getId());
				if ( !goalVO.isDelete() || !goalVO.isBlock() ) {
					goalVO.setGoalVOForMongo(goalVOForMongo);
					goalVOList.add(goalVO);
				}
			}
			return goalVOList;			
		}
		return null;
	}
	
	
	
	@Override
	public boolean createGoal(GoalVOForForm goalVOForForm) {
		String mongoId = this.goalDaoForMongo.insertGoalVOForMongo(this.separateGoalVOForMongoFromGoalVOForForm(goalVOForForm));
		return this.goalDao.insertGoal(this.separateGoalVOFromGoalVOForForm(goalVOForForm, mongoId)) > 0;
	}
	
	@Override
	public GoalVO readOneGoal(String id) {
		GoalVO goalVO = this.goalDao.selectGoal(id);
		return this.combineGoalVO(goalVO);
	}
	
	@Override
	public List<GoalVO> readAllGoals(int page, int size) {
		return this.combineGoalVOList(this.goalDao.selectGoalList(new GoalPageVO(page, size)));
	}
	
	@Override
	public List<GoalVO> readAllGoalsByEmail(String email) {
		return this.combineGoalVOList(this.goalDao.selectGoalListByEamil(email));
	}

	@Override
	public GoalVO readParentGoal(String id) {
		return this.combineGoalVO(this.goalDao.selectParentGoal(id));
	}

	@Override
	public List<GoalVO> readChildrenGoalList(String id) {
		return this.combineGoalVOList(this.goalDao.selectChildrenGoalList(id));
	}

	@Override
	public GoalVO readLatestModifyGoalByEmail(String email) {
		return this.combineGoalVO(this.goalDao.selectLatestModifyGoalByEmail(email));
	}
	
	@Override
	public List<GoalVO> readGoalListByLevel(String email, int level) {
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		param.put("level", level);
		return this.combineGoalVOList(this.goalDao.selectGoalListByLevel(param));
	}

	@Override
	public List<GoalVO> readGoalVOListByTag(String tag) {
		return this.combineGoalVOListByGoalVOForMongoList(this.goalDaoForMongo.findGoalVOForMongoListByTag(tag));
	}
	
	@Override
	public List<GoalVO> readGoalVOListByTag(String tag, int page, int size) {
		return this.combineGoalVOListByGoalVOForMongoList(this.goalDaoForMongo.findGoalVOForMongoListByTag(tag, page, size));
	}
	
	@Override
	public boolean modifyGoalIdInGoalVOForMongo(String mongoId, String goalId) {
		return this.goalDaoForMongo.setGoalId(mongoId, goalId) != null;
	}
	
	@Override
	public boolean isRecommendEmail(String goalId, String email) {
		return this.goalDaoForMongo.isRecommendEmail(goalId, email);
	}
	
	@Override
	public boolean addRecommendEmailList(String goalId, String email) {
		return this.goalDaoForMongo.addRecommendEmailList(goalId, email).getModifiedCount() > 0;
	}
	
	@Override
	public boolean pullRecommendEmailList(String goalId, String email) {
		return this.goalDaoForMongo.pullRecommendEmailList(goalId, email).getModifiedCount() > 0;
	}
	
	@Override
	public boolean modifyDelete(String id) {
		return this.goalDao.updateDelete(id) > 0;
	}
	
	@Override
	public boolean modifySuccess(String id) {
		return this.goalDao.updateSuccess(id) > 0;
	}
}
