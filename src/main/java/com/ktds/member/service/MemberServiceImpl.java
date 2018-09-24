package com.ktds.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.common.authority.Authority;
import com.ktds.common.web.SHA256Util;
import com.ktds.member.dao.MemberDao;
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
			memberVO.setProfileFilename("default.jpg");
		}
		
		return this.memberDao.insertMember(memberVO) > 0;
	}

	@Override
	public MemberVO readOneMemberById(String id) {
		return this.memberDao.selectOneMemberById(id);
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		return this.memberDao.selectOneMember(memberVO);
	}
	
	@Override
	public MemberVO authMember(MemberVO memberVO) {
		String salt = this.memberDao.selectSaltById(memberVO.getId());
		memberVO.setPassword(SHA256Util.getEncrypt(memberVO.getPassword(), salt));
		return this.memberDao.selectOneMember(memberVO);
	}

	@Override
	public String readSaltById(String id) {
		return this.memberDao.selectSaltById(id);
	}

	@Override
	public boolean isExpired(String id) {
		return this.memberDao.isExpired(id);
	}

	@Override
	public boolean isLoginBlock(String id) {
		return this.memberDao.isLoginBlock(id);
	}

	@Override
	public boolean isExpiredPassword(String id) {
		return this.memberDao.isExpiredPassword(id);
	}

	@Override
	public boolean isBlock(String id) {
		return this.readOneMemberById(id).getAuthority() == Authority.BLOCK;
	}

	@Override
	public boolean isWithdrawal(String id) {
		return this.readOneMemberById(id).getAuthority() == Authority.WITHDRAWAL;
	}

}
