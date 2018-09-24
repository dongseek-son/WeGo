package com.ktds.member.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.member.Member;
import com.ktds.common.session.Session;
import com.ktds.member.service.MemberService;
import com.ktds.member.vo.MemberVO;
import com.ktds.security.User;

import io.github.seccoding.web.mimetype.ExtFilter;
import io.github.seccoding.web.mimetype.ExtensionFilter;
import io.github.seccoding.web.mimetype.ExtensionFilterFactory;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@GetMapping("/member/login.go")
	public String viewLoginPage() {
		return "member/login";
	}
	
	@GetMapping("/member/logout.go")
	public String doMemberLogoutAction( HttpSession session ) {
		session.invalidate();
		return "redirect:/member/login";
	}
	
	@GetMapping("/member/regist.go")
	public String viewRegistPage() {
		return "member/regist";
	}
	
	@PostMapping("/member/regist.go")
	public String doRegistAction(@ModelAttribute MemberVO memberVO) {
		
		MultipartFile uploadFile = memberVO.getProfileFile();
		
		if ( !uploadFile.isEmpty() ) {
			String originFileName = uploadFile.getOriginalFilename();
			String fileName = UUID.randomUUID().toString();
			
			File uploadDir = new File(this.uploadPath);
			if ( !uploadDir.exists() ) {
				uploadDir.mkdirs();
			}
			
			File destFile = new File(this.uploadPath, fileName);
			
			try {
				uploadFile.transferTo(destFile);
				memberVO.setProfileOriginFilename(originFileName);
				memberVO.setProfileFilename(fileName);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				if ( destFile.exists() ) {
					ExtensionFilter filter = ExtensionFilterFactory.getFilter(ExtFilter.APACHE_TIKA);
					boolean isImageFile = filter.doFilter(
							destFile.getAbsolutePath()
							,"image/jpg"
							,"image/bmp"
							,"image/jpeg"
							,"image/gif"
							,"image/png" );
					
					if ( !isImageFile ) {
						destFile.delete();
						memberVO.setProfileOriginFilename(Member.DEFAULT_PROFILE_ORIGIN);
						memberVO.setProfileFilename(Member.DEFAULT_PROFILE);
					}
				}
			}
		}
		this.memberService.createMember(memberVO);
		return "member/login";
	}
	
	@RequestMapping("member/loginSuccess.go")
	public ModelAndView doMemberLoginAction( 
			@ModelAttribute MemberVO memberVO
			, Errors errors
			, HttpSession session ) {
		System.out.println("loginSuccess.go 진입");
		ModelAndView view = new ModelAndView("member/login");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
		System.out.println(user.toString());
		memberVO.setId(user.getUsername());
		memberVO.setPassword(user.getPassword());
		
		if ( errors.hasErrors() ) {
			view.addObject("message", "로그인에 오류가 생겼습니다.");
			return view;
		}
		
		if ( !user.isAccountNonExpired() ) {
			System.out.println("!user.isAccountNonExpired() : " + !user.isAccountNonExpired());
			view.addObject("message", "1년이상 접속하지않아 휴면계정이 되었습니다.");
		}
		
		if ( !user.isAccountNonLocked() ) {
			System.out.println("!user.isAccountNonLocked() : " + !user.isAccountNonLocked());
			view.addObject("message", "해당 계정은 3회이상 비밀번호가 틀렸습니다. 1시간 이후에 다시 시도해주세요.");
			return view;
		}
		
		if ( !user.isCredentialsNonExpired() ) {
			view.addObject("message", "3개월 동안 비밀번호를 변경하지 않았습니다.");
		}
		
		if ( !user.isEnabled() ) {
			view.addObject("message", "신고로 인하여 접속이 차단된 계정입니다.");
			return view;
		}
		
		System.out.println("Userservice loginMemeber전");
		MemberVO loginMemberVO = this.memberService.loginMember(memberVO);
		
		System.out.println("로그인멤버 / " + loginMemberVO);
		
		if( loginMemberVO != null ) {
			session.setAttribute(Session.USER, loginMemberVO);
			session.setAttribute(Session.CSRF, UUID.randomUUID().toString());
			view.setViewName("main");
			System.out.println("로그인 성공 / "  + view.getViewName());
			return view;
		}
		else {
			view.addObject("message", "이메일과 아이디가 올바르지 않습니다.");
		}
		
		return view;
	}
	
}
