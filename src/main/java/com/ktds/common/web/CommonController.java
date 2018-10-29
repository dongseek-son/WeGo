package com.ktds.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.util.CarouselListUtil;
import com.ktds.goal.service.GoalService;
import com.ktds.goal.vo.GoalVO;

@Controller
public class CommonController {

	@Autowired
	GoalService goalService;
	
	@RequestMapping("/")
	public String viewMainPage(HttpServletRequest request) {
		return "main";
	}
	
	
	
	@RequestMapping("test2")
	public ModelAndView viewTest2Page() {
		ModelAndView view = new ModelAndView("test2");
		List<GoalVO> goalVOList = this.goalService.readGoalListByLevel("admin@wego.com", 1);
		
		int firstIndex = 0;
		int size = goalVOList.size();
		
		view.addObject("goalVOList", CarouselListUtil.extractShowingGoalVO(goalVOList, firstIndex, 5));
		view.addObject("size", size); 
		return view;
	}
	
	@PostMapping("test2/listChange")
	@ResponseBody
	public Map<String, Object> doListChangeAction(@RequestParam int firstIndex, @RequestParam int size, @RequestParam String firstId) {
		Map<String, Object> result = new HashMap<>();
		List<GoalVO> goalVOList = this.goalService.readGoalListByLevel("admin@wego.com", 1);
		
		if( size != goalVOList.size() || !firstId.equals(goalVOList.get(0).getId() )) {
			result.put("status", false);
			return result;
		}
		
		goalVOList = CarouselListUtil.extractShowingGoalVO(goalVOList, firstIndex, 5);
		
		result.put("status", "OK");
		result.put("goalVOList", goalVOList);
		return result;
	}
	
	@PostMapping("test2/childrenList")
	@ResponseBody
	public Map<String, Object> doChildrenListAction(@RequestParam String id) {
		Map<String, Object> result = new HashMap<>();
		List<GoalVO> goalVOList = this.goalService.readChildrenGoalList(id);
		
		int firstIndex = 0;
		int size = goalVOList.size();
		
		if ( size == 0 ) {
			result.put("status", false);
			return result;
		}
		
		result.put("status", "OK");
		result.put("goalVOList", CarouselListUtil.extractShowingGoalVO(goalVOList, firstIndex, 5));
		result.put("size", size); 
		return result;
	}
	
}
