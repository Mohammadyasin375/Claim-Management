package com.claim.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.boot.model.Member;
import com.claim.boot.model.User;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.repository.UserRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	public void deleteMemberById(long id) {
		memberRepository.deleteById(id);
	}
	
	public void updateMemberById(long id) {
		Member member = memberRepository.findById(id).get();
		memberRepository.save(member);
	}
	
	
}
