package com.ktds.goal.service;

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
	
	@Override
	public boolean createGoal(GoalVOForForm goalVOForForm) {
		String mongoId = this.goalDaoForMongo.insertGoalDaoForMongo(this.separateGoalVOForMongoFromGoalVOForForm(goalVOForForm));
		return this.goalDao.insertGoal(this.separateGoalVOFromGoalVOForForm(goalVOForForm, mongoId)) > 0;
	}

}
