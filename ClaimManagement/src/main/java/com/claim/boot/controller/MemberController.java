package com.claim.boot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.boot.dto.MemberResponseDto;
import com.claim.boot.dto.MessageDto;
import com.claim.boot.model.Member;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.service.MemberService;

@RestController
@RequestMapping("/api/member")
@CrossOrigin("http://localhost:8903/")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// To Post Member details
	@PostMapping("/add")
    public ResponseEntity<MessageDto> addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

	// To get All Members
	@GetMapping("/all")
	public List<MemberResponseDto> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("/details")
	public MemberResponseDto getMemberDetails(Principal principal) {
		String username = principal.getName();
		return memberService.getMemberDetails(username);
	}


}
