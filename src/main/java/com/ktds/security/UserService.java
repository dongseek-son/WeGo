package com.ktds.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ktds.common.authority.Authority;
import com.ktds.member.dao.MemberDaoImpl;
import com.ktds.member.service.MemberService;
import com.ktds.member.vo.MemberVO;

public class UserService implements AuthenticationProvider{
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		MemberVO memberVO = new MemberVO();
		String id = authentication.getPrincipal().toString();
		memberVO.setId(id);
		memberVO.setPassword(authentication.getCredentials().toString());
		
		memberVO = this.memberService.authMember(memberVO);

		UsernamePasswordAuthenticationToken result = null;
		
		// Token 값 생성 및 등록 코드 작성
		if ( memberVO != null ) {
			String grade = Authority.AUTHORITIES[memberVO.getAuthority()];
			
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority(grade));
			if ( grade.equals("ROLE_ADMIN") ) {
				roles.add(new SimpleGrantedAuthority("ROLE_DEFAULT") );
			}
			
			result = new UsernamePasswordAuthenticationToken(id, memberVO.getPassword(), roles);
			
			User user = new User( id, memberVO.getPassword(), memberVO.getAuthority()
					, this.memberService.isExpired(id)
					, this.memberService.isLoginBlock(id)
					, this.memberService.isExpiredPassword(id)
					, this.memberService.isBlock(id));
			logger.debug(user.toString());
			result.setDetails(user);
		}
		else {
			throw new BadCredentialsException("잘못된 인증");
		}
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
