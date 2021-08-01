package com.kh.mvc.member.model.service;

import com.kh.mvc.member.model.vo.Member;

public interface MemberService {
		Member login(String userId, String userPwd);

		int save(Member member);

		boolean validate(String userId);

}
