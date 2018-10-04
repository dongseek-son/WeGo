package com.ktds.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {

	@RequestMapping("/")
	public String viewMainPage() {
		return "main";
	}
	
}
