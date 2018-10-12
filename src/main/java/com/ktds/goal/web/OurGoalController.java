package com.ktds.goal.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.goal.service.GoalService;
import com.ktds.goal.vo.GoalVO;

public class OurGoalController {

	@Autowired
	private GoalService goalService;
	
	@GetMapping("ourgoal/list")
	public ModelAndView viewListPage() {
		ModelAndView view = new ModelAndView("ourgoal/list");
		
		return view;
	}
	
	@RequestMapping("ourgoal/list/tag")
	@ResponseBody
	public Map<String, Object> doListPageByTagAction(@RequestParam String tag, @RequestParam int page) {
		Map<String, Object> result = new HashMap<>();
		List<GoalVO> goalVOList = this.goalService.readGoalVOListByTag(tag, page, 2);
		result.put("status", "OK");
		result.put("goalVOList", goalVOList);
		return result;
	}
	
}
