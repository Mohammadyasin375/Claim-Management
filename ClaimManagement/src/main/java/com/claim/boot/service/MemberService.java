package com.claim.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.claim.boot.dto.MemberResponseDto;
import com.claim.boot.dto.MessageDto;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<MessageDto> addMember(@RequestBody Member member) {

		User user = member.getUser();
		user.setRole("MEMBER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		member.setUser(user);

		memberRepository.save(member);

		return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("Member Sign up Success"));
	}

	public List<MemberResponseDto> getAllMembers() {
		List<Member> list = memberRepository.findAll();

		List<MemberResponseDto> listDto = new ArrayList<MemberResponseDto>();

		for (Member m : list) {
			MemberResponseDto d = new MemberResponseDto();
			d.setMemberId(m.getMemberId());
			d.setMemberName(m.getMemberName());
			d.setDob(m.getDob());
			d.setMobileNo(m.getMobileNo());
			d.setAddress(m.getAddress());
			d.setCity(m.getCity());
			d.setState(m.getState());
			listDto.add(d);
		}
		return listDto;
	}

	public MemberResponseDto getMemberDetails(String username) {

		Member m = memberRepository.getMemberByUsername(username);

		MemberResponseDto dto = new MemberResponseDto();

		dto.setMemberId(m.getMemberId());
		dto.setMemberName(m.getMemberName());
		dto.setDob(m.getDob());
		dto.setMobileNo(m.getMobileNo());
		dto.setAddress(m.getAddress());
		dto.setCity(m.getCity());
		dto.setState(m.getState());

		return dto;

	}

}
