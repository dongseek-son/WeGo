package com.ktds.member.service;

import com.ktds.member.vo.EmailAuthVO;
import com.ktds.member.vo.MemberMongoVO;
import com.ktds.member.vo.MemberVO;

public interface MemberService {

	public boolean createMember(MemberVO memberVO);
	
	public MemberVO readOneMemberByEmail(String email);
	
	public MemberVO loginMember(MemberVO memberVO);
	
	public MemberVO authMember(MemberVO memberVO);
	
	public String readSaltByEmail(String email);
	
	public boolean isExpired(String email);
	
	public boolean isLoginBlock(String email);
	
	public boolean isExpiredPassword(String email);
	
	public boolean isBlock(String email);
	
	public boolean isWithdrawal(String email);
	
	public boolean isNotEmailAuth(String email);
	
	public String createEmailAuth(String email);
	
	public EmailAuthVO readOneEmailAuth(String authUrl);
	
	public boolean removeOneEmailAuth(String authUrl);
	
	public boolean updateRegistDate(String email);

	public void createMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public void modifyMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public MemberMongoVO readOntMemberMongoVOByEmail(String email);
	
	public boolean modifyPassword(String email, String password);
	
	public boolean increaseLoginFailCount(MemberVO memberVO);
	
	public boolean isDuplicateEmail(String email);

}
