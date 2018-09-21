package com.ktds.member.dao;

import com.ktds.member.vo.MemberVO;

public interface MemberDao {
	
	public int insertMember(MemberVO memberVO);
	
	public MemberVO selectOneMemberById(String id);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
	public String selectSaltById(String id);
	
	public boolean isExpired(String id);
	
	public boolean isExpiredPassword(String id);
	
	public boolean isLoginBlock(String id);

}
