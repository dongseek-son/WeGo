package com.ktds.reply.web;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.session.Session;
import com.ktds.member.vo.MemberVO;
import com.ktds.reply.service.ReplyService;
import com.ktds.reply.vo.ReplyVO;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@PostMapping("reply/check")
	@ResponseBody
	public Map<String, Object> doCheckReplyPattern( @RequestParam String detail ) {
		Map<String, Object> result = new HashMap<>();
		
/*		String replyPolicy = ".{9,1000}";
		
		Pattern pattern = Pattern.compile(replyPolicy);
		Matcher matcher = pattern.matcher(detail);*/
		
		result.put("status", "OK");
		result.put("available", detail.length() > 9 && detail.length() <= 1000 );
		
		return result;
	}
	
	@PostMapping("reply/write")
	public ModelAndView doReplyWriteAction( @ModelAttribute ReplyVO replyVO
			, @SessionAttribute(name=Session.USER) MemberVO memberVO
			, @SessionAttribute(name=Session.CSRF) String token) {
		ModelAndView view = new ModelAndView("redirect:/mygoal/detail/" + replyVO.getGoalId());
		
		String sessionToken = token;
		if ( !replyVO.getToken().equals(sessionToken) ) {
			throw new RuntimeException("잘못된 접근 입니다.");
		}
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		replyVO.setDetail( filter.doFilter(replyVO.getDetail()) );		
		replyVO.setEmail(memberVO.getEmail());
		
		replyVO = this.replyService.createReply(replyVO);
		
		if ( replyVO != null ) {
			view.addObject("message", "댓글이 등록되었습니다.");
			view.addObject("isReplyModalOpen", true);
		}
		else {
			view.addObject("message", "오류가 발생하였습니다. \\n다시 시도해주세요.");
		}
		
		return view;
	}
	
}
