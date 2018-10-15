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
			System.out.println( i + " : " + goalVOList.get(i));
		}
		
		return showingList;
	}
	
	@RequestMapping("test2")
	public ModelAndView viewTest2Page() {
		ModelAndView view = new ModelAndView("test2");
		List<GoalVO> goalVOList = this.goalService.readGoalListByLevel("admin@wego.com", 1);
		
		int firstIndex = 0;
		int size = goalVOList.size();
		
		view.addObject("goalVOList", this.extractShowingGoalVO(goalVOList, firstIndex, 5));
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
		
		goalVOList = this.extractShowingGoalVO(goalVOList, firstIndex, 5);
		
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
		result.put("goalVOList", this.extractShowingGoalVO(goalVOList, firstIndex, 5));
		result.put("size", size); 
		return result;
	}
	
}
