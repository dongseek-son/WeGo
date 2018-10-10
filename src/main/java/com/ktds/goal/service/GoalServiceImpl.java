package com.ktds.goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.goal.dao.GoalDao;
import com.ktds.goal.dao.GoalDaoForMongo;
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
		
		goalVOForMongo.setTagList(goalVOForForm.getTagList());
		
		return goalVOForMongo;
	}
	
	private GoalVO combineGoalVOForMongoToGoalVO(GoalVO goalVO) {
	
		if ( goalVO != null ) {
			goalVO.setGoalVOForMongo(this.goalDaoForMongo.findGoalVOForMongo(goalVO.getMongoId()));
		}
		
		return goalVO;
	}
	
	@Override
	public boolean createGoal(GoalVOForForm goalVOForForm) {
		String mongoId = this.goalDaoForMongo.insertGoalVOForMongo(this.separateGoalVOForMongoFromGoalVOForForm(goalVOForForm));
		return this.goalDao.insertGoal(this.separateGoalVOFromGoalVOForForm(goalVOForForm, mongoId)) > 0;
	}
	
	@Override
	public GoalVO readOneGoal(String id) {
		GoalVO goalVO = this.goalDao.selectGoal(id);
		return this.combineGoalVOForMongoToGoalVO(goalVO);
	}

	@Override
	public GoalVO readParentGoal(String id) {
		return this.combineGoalVOForMongoToGoalVO(this.goalDao.selectParentGoal(id));
	}

	@Override
	public List<GoalVO> readChildrenGoalList(String id) {
		List<GoalVO> childrenGoalList = this.goalDao.selectChildrenGoalList(id);
		for ( GoalVO goalVO : childrenGoalList ) {
			this.combineGoalVOForMongoToGoalVO(goalVO);
		}
		return childrenGoalList;
	}

	@Override
	public GoalVO readLatestModifyGoalByEmail(String email) {
		return this.combineGoalVOForMongoToGoalVO(this.goalDao.selectLatestModifyGoalByEmail(email));
	}

}
