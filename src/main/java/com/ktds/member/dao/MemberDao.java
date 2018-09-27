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
	
	public void insertMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public void updateMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public void deleteMemberMongoVO(MemberMongoVO memberMongoVO);
	
	public MemberMongoVO findOneMemberMongoVOByEmail(String email);
	
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String hashtag);
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String h1, String h2);
	public List<MemberMongoVO> findAllMemberMongoVOByAdviceHashtag(String h1, String h2, String h3);

}
