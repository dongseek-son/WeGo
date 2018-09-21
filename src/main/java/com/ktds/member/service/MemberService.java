package com.ktds.member.service;

import com.ktds.member.vo.MemberVO;

public interface MemberService {

	public boolean createMember(MemberVO memberVO);
	
	public MemberVO readOneMemberById(String id);
	
	public MemberVO loginMember(MemberVO memberVO);
	
	public String readSaltById(String id);
	
	public boolean isExpired(String id);
	
	public boolean isLoginBlock(String id);
	
	public boolean isExpiredPassword(String id);
	
	public boolean isBlock(String id);
	
	public boolean isWithdrawal(String id);
}
