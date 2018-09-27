package com.ktds.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.common.authority.Authority;
import com.ktds.common.member.Member;
import com.ktds.common.web.SHA256Util;
import com.ktds.member.dao.MemberDao;
import com.ktds.member.vo.EmailAuthVO;
import com.ktds.member.vo.MemberMongoVO;
import com.ktds.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean createMember(MemberVO memberVO) {
		String salt = SHA256Util.generateSalt();
		memberVO.setPassword(SHA256Util.getEncrypt(memberVO.getPassword(), salt));
		memberVO.setSalt(salt);
		
		if( memberVO.getProfileOriginFilename() == null ) {
			memberVO.setProfileFilename(Member.DEFAULT_PROFILE);
			memberVO.setProfileOriginFilename(Member.DEFAULT_PROFILE_ORIGIN);
		}
		
		return this.memberDao.insertMember(memberVO) > 0;
	}

	@Override
	public MemberVO readOneMemberByEmail(String email) {
		return this.memberDao.selectOneMemberByEmail(email);
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		return this.memberDao.selectOneMember(memberVO);
	}
	
	@Override
	public MemberVO authMember(MemberVO memberVO) {
		System.out.println(memberVO.getEmail());
		String salt = this.memberDao.selectSaltByEmail(memberVO.getEmail());
		System.out.println(memberVO.toString());
		memberVO.setPassword(SHA256Util.getEncrypt(memberVO.getPassword(), salt));
		return this.memberDao.selectOneMember(memberVO);
	}

	@Override
	public String readSaltByEmail(String email) {
		return this.memberDao.selectSaltByEmail(email);
	}

	@Override
	public boolean isExpired(String email) {
		return this.memberDao.isExpired(email);
	}

	@Override
	public boolean isLoginBlock(String email) {
		return this.memberDao.isLoginBlock(email);
	}

	@Override
	public boolean isExpiredPassword(String email) {
		return this.memberDao.isExpiredPassword(email);
	}

	@Override
	public boolean isBlock(String email) {
		return this.readOneMemberByEmail(email).getAuthority() == Authority.BLOCK;
	}

	@Override
	public boolean isWithdrawal(String email) {
		return this.readOneMemberByEmail(email).getAuthority() == Authority.WITHDRAWAL;
	}
	
	@Override
	public boolean isEmailAuth(String email) {
		return this.memberDao.isEmailAuth(email);
	}
	
	@Override
	public String createEmailAuth(String email) {
		EmailAuthVO emailAuthVO = new EmailAuthVO(UUID.randomUUID().toString(), email);
		if ( this.memberDao.insertEmailAuth(emailAuthVO) > 0 ) {
			return emailAuthVO.getAuthUrl();
		}
		return null;
	}
	
	@Override
	public EmailAuthVO readOneEmailAuth(String authUrl) {
		return this.memberDao.selectOneEmailAuth(authUrl);
	}
	
	@Override
	public boolean removeOneEmailAuth(String authUrl) {
		return this.memberDao.deleteOneEmailAuth(authUrl) > 0;
	}
	
	@Override
	public boolean updateRegistDate(String email) {
		return this.memberDao.updateRegistDate(email) > 0;
	}
	
	@Override
	public void createMemberMongoVO(MemberMongoVO memberMongoVO) {
		this.memberDao.insertMemberMongoVO(memberMongoVO);
	}

}
