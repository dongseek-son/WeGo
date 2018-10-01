package com.ktds.member.dao;

import java.util.List;

import com.ktds.member.vo.EmailAuthVO;
import com.ktds.member.vo.MemberMongoVO;
import com.ktds.member.vo.MemberVO;

public interface MemberDao {
	
	public int insertMember(MemberVO memberVO);
	
	public MemberVO selectOneMemberByEmail(String email);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
	public String selectSaltByEmail(String email);
	
	public boolean isExpired(String email);
	
	public boolean isExpiredPassword(String email);
	
	public boolean isLoginBlock(String email);
	
	public boolean isEmailAuth(String email);
	
	public int insertEmailAuth(EmailAuthVO emailAuthVO);
	
	public EmailAuthVO selectOneEmailAuth(String authUrl);
	
	public int deleteOneEmailAuth(String authUrl);
	
	public int updateRegistDate(String email);
	


}
