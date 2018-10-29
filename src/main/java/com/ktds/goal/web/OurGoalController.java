package com.ktds.goal.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.goal.service.GoalService;
import com.ktds.goal.vo.GoalVO;

@Controller
public class OurGoalController {

	@Autowired
	private GoalService goalService;
	
	@GetMapping("ourgoal/list")
	public ModelAndView viewListPage() {
		ModelAndView view = new ModelAndView("ourgoal/list");
		view.addObject("goalVOList", this.goalService.readAllGoals(0, 5));
		view.addObject("tag", false);
		return view;
	}
	
	@GetMapping("ourgoal/list/{tag}")
	public ModelAndView viewListPage( @PathVariable String tag) {
		ModelAndView view = new ModelAndView("ourgoal/list");
		view.addObject("goalVOList", this.goalService.readGoalVOListByTag(tag, 0, 5));
		view.addObject("tag", tag);
		return view;
	}
	
	@RequestMapping("ourgoal/list/page")
	@ResponseBody
	public Map<String, Object> doListPageAction(@RequestParam int page) {
		Map<String, Object> result = new HashMap<>();
		List<GoalVO> goalVOList = this.goalService.readAllGoals(page, 5);
		result.put("status", "OK");
		if ( goalVOList != null && goalVOList.size() > 0 ) {			
			result.put("goalVOList", goalVOList);
		}
		return result;
	}
	
	@RequestMapping("ourgoal/list/tag/page")
	@ResponseBody
	public Map<String, Object> doListPageByTagAction(@RequestParam String tag, @RequestParam int page) {
		Map<String, Object> result = new HashMap<>();
		List<GoalVO> goalVOList = this.goalService.readGoalVOListByTag(tag, page, 5);
		result.put("status", "OK");
		result.put("goalVOList", goalVOList);
		return result;
	}
	
}
