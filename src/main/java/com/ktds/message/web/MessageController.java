package com.ktds.message.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.session.Session;
import com.ktds.member.vo.MemberVO;
import com.ktds.message.service.MessageService;
import com.ktds.message.vo.MessageVO;
import com.nhncorp.lucy.security.xss.XssFilter;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/message/sendlist.go")
	public ModelAndView viewSendMessageListPage(
			@SessionAttribute(name=Session.USER) MemberVO memberVO) {
		ModelAndView view = new ModelAndView("message/list");
		view.addObject("messageList", this.messageService.readSendMessages(memberVO));
		return view;
	}
	
	@RequestMapping("/message/receivelist.go")
	public ModelAndView viewReceiveMessageListPage(
			@SessionAttribute(name=Session.USER) MemberVO memberVO) {
		System.out.println(memberVO.toString());
		ModelAndView view = new ModelAndView("message/list");
		view.addObject("messageList", this.messageService.readReceiveMessages(memberVO));
		return view;
	}
	
	@GetMapping("/message/detail.go/{id}")
	public ModelAndView viewDetailMessagePage(
			@PathVariable String id
			, @SessionAttribute(name=Session.USER) MemberVO memberVO) {
		ModelAndView view = new ModelAndView("message/detail");
		view.addObject("messageVO", this.messageService.readOneMessageById(id, memberVO));
		return view;
	}
	
	@GetMapping("/message/delete.go/{id}") 
	public String doDeleteMessageAction(
			@PathVariable String id
			, @SessionAttribute(name=Session.USER) MemberVO memberVO) {
		this.messageService.updateIsDelete(id, memberVO);
		return "redirect:/message/receivelist.go";
	}
	
	@GetMapping("/message/write.go")
	public String viewWriteMessagePage() {
		return "message/write";
	}
	
	@GetMapping("/message/write.go/{receiverId}")
	public ModelAndView viewWriteMessagePage2(@PathVariable String receiverId) {
		ModelAndView view = new ModelAndView("message/write");
		view.addObject("receiverId", receiverId);
		return view;
	}
	
	@PostMapping("/message/write.go")
	public String doWriteMessageAction( 
			@ModelAttribute MessageVO messageVO
			, HttpServletRequest request ) {
		
		System.out.println(messageVO.toString());
		
		String sessionToken = (String)request.getSession().getAttribute(Session.CSRF);
		if ( !messageVO.getToken().equals(sessionToken) ) {
			throw new RuntimeException("잘못된 접근 입니다.");
		}
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		messageVO.setTitle( filter.doFilter(messageVO.getTitle()) );
		messageVO.setDetail( filter.doFilter(messageVO.getDetail()) );
		
		boolean isSuccess = this.messageService.createMessage(messageVO);
		
		return "redirect:/message/sendlist.go";
	}
	
}
