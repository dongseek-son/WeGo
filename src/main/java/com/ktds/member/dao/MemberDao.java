package com.ktds.member.dao;

import com.ktds.member.vo.MemberVO;

public interface MemberDao {
	
	public int insertMember(MemberVO memberVO);
	
	public MemberVO selectOneMemberByEmail(String email);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
	public String selectSaltByEmail(String email);
	
	public boolean isExpired(String email);
	
	public boolean isExpiredPassword(String email);
	
	public boolean isLoginBlock(String email);

}
