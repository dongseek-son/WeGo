package com.ktds.goal.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktds.common.session.Session;
import com.ktds.goal.service.GoalService;
import com.ktds.member.vo.MemberVO;
@Controller
public class GoalController {

	@Autowired
	private GoalService goalService;
	
	@PostMapping("goal/recommend/up")
	@ResponseBody
	private Map<String, Object> doRecommendUpAction( @RequestParam String goalId
			, @SessionAttribute(name=Session.USER) MemberVO memberVO ) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("status", "OK");
		result.put("isSuccess", this.goalService.addRecommendEmailList(goalId, memberVO.getEmail()));
		return result;
	}
	
	@PostMapping("goal/recommend/down")
	@ResponseBody
	private Map<String, Object> doRecommendDownAction( @RequestParam String goalId
			, @SessionAttribute(name=Session.USER) MemberVO memberVO ) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("status", "OK");
		result.put("isSuccess", this.goalService.pullRecommendEmailList(goalId, memberVO.getEmail()));
		return result;
	}
}
