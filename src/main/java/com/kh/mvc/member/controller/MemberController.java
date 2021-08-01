package com.kh.mvc.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("loginMember")
public class MemberController {
	@Autowired MemberService service;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ModelAndView login(ModelAndView model,
			@RequestParam("userId")String userId, @RequestParam("userPwd")String userPwd) {
		
		log.info("{}, {}", userId, userPwd);
		
		Member loginMember =  service.login(userId, userPwd);
		
		if(loginMember != null) {
			model.addObject("loginMember", loginMember);
			model.setViewName("redirect:/");
		} else {
			model.addObject("msg", "아이디나 패스워드가 일치하지 않습니다.");
			model.addObject("location", "/");
			model.setViewName("common/msg");
		}
		
		return model;
	}
	
	@GetMapping("/member/enroll")
	public String enrollView() {
		log.info("회원가입 페이지 요청");
		
		return "member/enroll";
	}	
	
	@RequestMapping(value = "/member/enroll", method = {RequestMethod.POST})
	public ModelAndView enroll(ModelAndView model, @ModelAttribute Member member) {

		int result = service.save(member);		
		
		if(result > 0) {
			model.addObject("msg", "회원가입이 정상적으로 완료되었습니다.");
			model.addObject("location", "/");
		} else {
			model.addObject("msg", "회원가입을 실패하였습니다.");
			model.addObject("location", "/member/enroll");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
	
	@GetMapping("/member/idCheck")
	@ResponseBody
	public Map<String, Object> idCheck(@RequestParam("id") String userId) {
		log.info("User ID : {}", userId);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("validate", service.validate(userId));
		
		return map;
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		
		log.info("status.isComplete() : " + status.isComplete());

		status.setComplete();
		
		log.info("status.isComplete() : " + status.isComplete());		
		
		return "redirect:/";
	}
	

}
