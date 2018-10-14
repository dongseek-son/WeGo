package com.ktds.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.goal.service.GoalService;
import com.ktds.goal.vo.GoalVO;

@Controller
public class CommonController {

	@Autowired
	GoalService goalService;
	
	@RequestMapping("/")
	public String viewMainPage() {
		return "main";
	}
	
	private List<GoalVO> extractShowingGoalVO(List<GoalVO> goalVOList, int firstIndex, int showingSize) {
		List<GoalVO> showingList = new ArrayList<>();
		int index = firstIndex;
		
		List<Integer> indexList = new ArrayList<Integer>();
		for( int count = 0; count < showingSize; count++ ) {
			if ( index >= goalVOList.size() ) {
				index = 0;
			}
			indexList.add(index++);
		}
		
		for( int i : indexList ) {
			showingList.add(goalVOList.get(i));
		}
		
		return showingList;
	}
	
	@RequestMapping("test2")
	public ModelAndView viewTest2Page() {
		ModelAndView view = new ModelAndView("test2");
		List<GoalVO> goalVOList = this.goalService.readAllGoalsByEmail("admin@wego.com");
		
		int firstIndex = 0;
		int size = goalVOList.size();
		
		view.addObject("goalVOList", this.extractShowingGoalVO(goalVOList, firstIndex, 5));
		view.addObject("firstIndex", firstIndex); // of ShowingList
		view.addObject("size", size); 
		view.addObject("firstId", goalVOList.get(0).getId()); // of WholeList
		return view;
	}
	
	@PostMapping("test2/prev")
	@ResponseBody
	public Map<String, Object> doPrevAction(@RequestParam int firstIndex, @RequestParam int size, @RequestParam String firstId) {
		Map<String, Object> result = new HashMap<>();
		List<GoalVO> goalVOList = this.goalService.readAllGoalsByEmail("admin@wego.com");
		
		if ( --firstIndex < 0 ) {
			firstIndex = goalVOList.size() - 1;
		}
		
		if( size != goalVOList.size() || !firstId.equals(goalVOList.get(0).getId() )) {
			result.put("status", false);
			return result;
		}
		
		goalVOList = this.extractShowingGoalVO(goalVOList, firstIndex, 5);
		
		result.put("status", "OK");
		result.put("goalVOList", this.extractShowingGoalVO(goalVOList, firstIndex, 5));
		result.put("firstIndex", firstIndex); // of ShowingList
		return result;
	}
	
}
