package com.ktds.member.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.member.Member;
import com.ktds.common.session.Session;
import com.ktds.common.web.DownloadUtil;
import com.ktds.member.service.MemberService;
import com.ktds.member.vo.EmailAuthVO;
import com.ktds.member.vo.MemberMongoVO;
import com.ktds.member.vo.MemberVO;
import com.ktds.security.User;

import io.github.seccoding.web.mimetype.ExtFilter;
import io.github.seccoding.web.mimetype.ExtensionFilter;
import io.github.seccoding.web.mimetype.ExtensionFilterFactory;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Value("${upload.profile.path}")
	private String uploadPath;
	
	@RequestMapping("/")
	public String viewMainPage() {
		return "main";
	}
	
	@GetMapping("/member/login")
	public String viewLoginPage() {
		return "member/login";
	}
	
	@GetMapping("/member/logout")
	public String doMemberLogoutAction( HttpSession session ) {
		session.invalidate();
		return "redirect:/member/login";
	}
	
	@GetMapping("/member/regist")
	public String viewRegistPage() {
		return "member/regist";
	}
	
	@PostMapping("/member/regist")
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
		return "redirect:/member/emailAuth/" + this.memberService.createEmailAuth(memberVO.getEmail());
	}
	
	@RequestMapping("/member/loginSuccess")
	public ModelAndView doMemberLoginAction( 
			@ModelAttribute MemberVO memberVO
			, Errors errors
			, HttpSession session ) {
		ModelAndView view = new ModelAndView("redirect:/");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
		System.out.println(user.toString());
		memberVO.setEmail(user.getUsername());
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
		
		if ( !user.isEmailAuth() ) {
			view.addObject("message", "이메일 인증이 완료되지않았습니다.");
			return view;
		}
		
		MemberVO loginMemberVO = this.memberService.loginMember(memberVO);
		
		if( loginMemberVO != null ) {
			session.setAttribute(Session.USER, loginMemberVO);
			session.setAttribute(Session.CSRF, UUID.randomUUID().toString());
			view.setViewName("redirect:/message/receivelist");
			return view;
		}
		else {
			view.addObject("message", "이메일과 아이디가 올바르지 않습니다.");
		}
		
		return view;
	}
	
	@GetMapping("/member/emailAuth/{authUrl}")
	public ModelAndView viewEmailAuthPage(@PathVariable String authUrl) {
		ModelAndView view = new ModelAndView("redirect:/");
		EmailAuthVO emailAuthVO = this.memberService.readOneEmailAuth(authUrl);
		
		if ( emailAuthVO == null ) {
			view.addObject("message", "잘못된 접근입니다.");
		}
		else {
			view.setViewName("member/emailAuth");
			view.addObject("emailAuthVO", emailAuthVO);			
		}
		return view;
	}
	
	@PostMapping("/member/emailAuth")
	public ModelAndView doEmailAuthAction(@ModelAttribute EmailAuthVO emailAuthVO) {
		ModelAndView view = new ModelAndView("redirect:/");
		if ( this.memberService.updateRegistDate(emailAuthVO.getEmail())
				&& this.memberService.removeOneEmailAuth(emailAuthVO.getAuthUrl()) ) {
			view.addObject("message", "이메일 인증이 완료되었습니다.");
		}
		else {
			view.addObject("message", "오류가 발생하였습니다. 관리자에게 문의 바랍니다.");
		}
		return view;
	}
	
	@PostMapping("/member/check/password")
	@ResponseBody
	public Map<String, Object> doCheckPasswordPattern( @RequestParam String password ) {
		Map<String, Object> result = new HashMap<>();
		
		String passwordPolicy = "((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{8,})";
		
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		
		result.put("status", "OK");
		result.put("available", matcher.matches() );
		
		return result;
	}
	
	@PostMapping("/member/check/email")
	@ResponseBody
	public Map<String, Object> doCheckEmailPattern( @RequestParam String email ) {
		Map<String, Object> result = new HashMap<>();
		
		String emailPolicy = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		Pattern pattern = Pattern.compile(emailPolicy);
		Matcher matcher = pattern.matcher(email);
		
		result.put("status", "OK");
		result.put("available", matcher.matches() );
		
		return result;
	}
	
	@PostMapping("/member/check/name")
	@ResponseBody
	public Map<String, Object> doCheckNamePattern( @RequestParam String name ) {
		Map<String, Object> result = new HashMap<>();
		
		String namePolicy = "(?=.*[0-9a-zA-Z가-힣]).{2,18}";
		
		Pattern pattern = Pattern.compile(namePolicy);
		Matcher matcher = pattern.matcher(name);
		
		result.put("status", "OK");
		result.put("available", matcher.matches() );
		
		return result;
	}
	
	@PostMapping("/member/check/tel")
	@ResponseBody
	public Map<String, Object> doCheckTelPattern( @RequestParam String tel ) {
		Map<String, Object> result = new HashMap<>();
		
		String telPolicy = "(?=.*[0-9]).{10,11}";
		
		Pattern pattern = Pattern.compile(telPolicy);
		Matcher matcher = pattern.matcher(tel);
		
		result.put("status", "OK");
		result.put("available", matcher.matches() );
		
		return result;
	}
	
	@RequestMapping("/member/profiledownload/{fileName}")
	public void imageDownload(@PathVariable String fileName,HttpServletRequest req, HttpServletResponse res) {
		try {
			new DownloadUtil(this.uploadPath + File.separator + fileName).download(req, res, fileName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@GetMapping("/member/passwordInit/{authUrl}")
	public ModelAndView viewPasswordInitPage(@PathVariable String authUrl) {
		ModelAndView view = new ModelAndView("redirect:/");
		EmailAuthVO emailAuthVO = this.memberService.readOneEmailAuth(authUrl);
		
		if ( emailAuthVO == null ) {
			view.addObject("message", "잘못된 접근입니다.");
		}
		else {
			view.setViewName("member/passwordInit");
			view.addObject("emailAuthVO", emailAuthVO);			
		}
		return view;
	}
	
	@PostMapping("/member/passwordInit")
	public ModelAndView doPasswordInitAction(
			@RequestParam String authUrl
			, @RequestParam String email
			, @RequestParam String password) {
		ModelAndView view = new ModelAndView("redirect:/");
		if ( this.memberService.modifyPassword(email, password) 
				&& this.memberService.removeOneEmailAuth(authUrl) ) {
			view.addObject("message", "비밀번호가 변경 되었습니다.");
		}
		else {
			view.addObject("message", "오류가 발생하였습니다. 관리자에게 문의 바랍니다.");
		}
		return view;
	}
	
	

}
