package com.claim.boot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.claim.boot.dto.MemberResponseDto;
import com.claim.boot.dto.MessageDto;
import com.claim.boot.model.Document;
import com.claim.boot.model.Member;
import com.claim.boot.service.DocumentService;
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
