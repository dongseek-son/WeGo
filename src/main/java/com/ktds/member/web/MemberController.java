package com.ktds.member.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.member.Member;
import com.ktds.member.service.MemberService;
import com.ktds.member.vo.MemberVO;

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
	
	@PostMapping("/member/login.go") 
	public ModelAndView doLoginAction() {
		return new ModelAndView("member/login");
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
	
}
