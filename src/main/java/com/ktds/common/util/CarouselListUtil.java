package com.ktds.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ktds.goal.vo.GoalVO;

public class CarouselListUtil {

	public static List<GoalVO> extractShowingGoalVO(List<GoalVO> goalVOList, int firstIndex, int showingSize) {
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
	
	public static Map<String, Object> inputNewList(List<GoalVO> goalVOList) {
		Map<String, Object> result = new HashMap<>();
		
		int firstIndex = 0;
		int size = goalVOList.size();
		
		if ( size == 0 ) {
			result.put("status", false);
			return result;
		}
		
		result.put("status", "OK");
		result.put("goalVOList", extractShowingGoalVO(goalVOList, firstIndex, 5));
		result.put("size", size); 
		return result;
	}
	
	public static Map<String, Object> changeList(List<GoalVO> goalVOList, int firstIndex, int size, String firstId) {
		Map<String, Object> result = new HashMap<>();
		
		if( size != goalVOList.size() || !firstId.equals(goalVOList.get(0).getId() )) {
			result.put("status", false);
			return result;
		}
		
		goalVOList = extractShowingGoalVO(goalVOList, firstIndex, 5);
		
		result.put("status", "OK");
		result.put("goalVOList", goalVOList);
		return result;
	}
	
	
}
