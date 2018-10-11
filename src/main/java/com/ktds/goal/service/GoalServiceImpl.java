package com.ktds.goal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
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
		goalVOForMongo.setModifyDate(new DateTime().plusHours(9));
		
		return goalVOForMongo;
	}
	
	private GoalVO combineGoalVOForMongoToGoalVO(GoalVO goalVO) {
	
		if ( goalVO != null ) {
			goalVO.setGoalVOForMongo(this.goalDaoForMongo.findGoalVOForMongo(goalVO.getMongoId()));
		}
		
		return goalVO;
	}
	
	private List<GoalVO> combineGoalVOForMongoToGoalVOList(List<GoalVO> goalVOList) {
		
		if ( goalVOList.size() > 0 ) {
			for ( GoalVO goalVO : goalVOList ) {
				this.combineGoalVOForMongoToGoalVO(goalVO);
			}
		}
		
		return goalVOList;
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
		return this.combineGoalVOForMongoToGoalVOList(this.goalDao.selectChildrenGoalList(id));
	}

	@Override
	public GoalVO readLatestModifyGoalByEmail(String email) {
		return this.combineGoalVOForMongoToGoalVO(this.goalDao.selectLatestModifyGoalByEmail(email));
	}
	
	@Override
	public List<GoalVO> readGoalListByLevel(String email, int level) {
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		param.put("level", level);
		return this.combineGoalVOForMongoToGoalVOList(this.goalDao.selectGoalListByLevel(param));
	}

	@Override
	public List<GoalVO> readGoalVOListByTag(String tag) {
		List<GoalVOForMongo> goalVOForMongoList = this.goalDaoForMongo.findGoalVOForMongoListByTag(tag);
		
		if ( goalVOForMongoList.size() > 0 ) {
			List<GoalVO> goalVOList = new ArrayList<GoalVO>();
			for ( GoalVOForMongo goalVOForMongo : goalVOForMongoList ) {
				GoalVO goalVO = this.goalDao.selectGoalByMongoId(goalVOForMongo.getId());
				goalVO.setGoalVOForMongo(goalVOForMongo);
				goalVOList.add(goalVO);
			}
			return goalVOList;			
		}
		return null;
	}
	
}
