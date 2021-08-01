package com.kh.mvc.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.mvc.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member selectMember(String userId);

	int updateMember(Member member);

	int insertMember(Member member);
	
}
