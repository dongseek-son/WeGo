package com.ktds.goal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoalController {
	
	@GetMapping("mygoal/write.go")
	private String viewMyGoalWirtePage() {
		return "common/wegoeditor";
	}
	
}
