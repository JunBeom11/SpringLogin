package com.kh.mvc.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mvc.member.model.mapper.MemberMapper;
import com.kh.mvc.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Member login(String userId, String userPwd) {
		Member member = this.findById(userId);
		
		
		return member != null && 
				passwordEncoder.matches(userPwd, member.getPassword()) ? member : null;
	}

	private Member findById(String userId) {
		
		return mapper.selectMember(userId);
	}

	@Override
	@Transactional
	public int save(Member member) {
		int result = 0;
		
		if(member.getNo() != 0) {
			result = mapper.updateMember(member);
		} else {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			
			result = mapper.insertMember(member);
		}
		
		
		return result;
	}

	@Override
	public boolean validate(String userId) {
		
		return this.findById(userId) != null;
	}


}
