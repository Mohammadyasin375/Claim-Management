package com.claim.boot.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.claim.boot.dto.MemberResponseDto;
import com.claim.boot.model.Document;
import com.claim.boot.model.Member;
import com.claim.boot.model.Plan;
import com.claim.boot.model.User;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.repository.PlanRepository;
import com.claim.boot.repository.UserRepository;
import com.claim.boot.service.DocumentService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// To Post Member details
	@PostMapping("/add/{id}")
	public ResponseEntity<String> addMember(@RequestBody Member member,@PathVariable("id") Long id) {

		User user = member.getUser();
		user.setRole("MEMBER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		member.setUser(user);
		
		Plan plan = planRepository.findById(id).get();
		plan = planRepository.save(plan);
		member.setPlan(plan);

		memberRepository.save(member);

		return ResponseEntity.status(HttpStatus.OK).body("Member Sign up Success");
	}

	// To get Member details By Id
	@GetMapping("/get")
	public MemberResponseDto getMemberById(Principal principal) {
		
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		
		Member member = memberRepository.getMemberByUsername(username);
		
		MemberResponseDto dto = new MemberResponseDto();

		dto.setMemberId(member.getMemberId());
		dto.setMemberName(member.getMemberName());
		dto.setDob(member.getDob());
		dto.setMobileNo(member.getMobileNo());
		dto.sethNo(member.gethNo());
		dto.setCity(member.getCity());
		dto.setState(member.getState());
		System.out.println(member.toString());
		return dto;
	}
	
	//To get All Members
	@GetMapping("/getAll")
	public List<MemberResponseDto> getAllMembers() {
		List<Member> list = memberRepository.findAll();
		
		List<MemberResponseDto> listDto = new ArrayList<MemberResponseDto>();
		
		for(Member m:list) {
			MemberResponseDto d = new MemberResponseDto();
			d.setMemberId(m.getMemberId());
			d.setMemberName(m.getMemberName());
			d.setDob(m.getDob());
			d.setMobileNo(m.getMobileNo());
			d.sethNo(m.gethNo());
			d.setCity(m.getCity());
			d.setState(m.getState());
			listDto.add(d);
		}
		return listDto;
	}
	
	//To update member personal details
	@PutMapping("/update")
	public ResponseEntity<String> editMember(Principal principal,@RequestBody Member m){
		
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		
		Member member = memberRepository.getMemberByUsername(username);
		
		
		member.setMemberName(m.getMemberName());
		member.setDob(m.getDob());
		member.setMobileNo(m.getMobileNo());
		member.setCity(m.getCity());
		member.setState(m.getState());
		member.sethNo(m.gethNo());
		member.setUser(m.getUser());
		member.setPlan(m.getPlan());
		
		memberRepository.save(member);
		
		return ResponseEntity.status(HttpStatus.OK).body("Member details updated Successfully!");
	}

	//To update change Plan into Member table
	@PutMapping("/changePlan/{oldPlanId}/{newPlanId}")
	public ResponseEntity<String> changePlan(Principal principal,
			                                 @PathVariable("oldPlanId") Long oldPlanId,
			                                 @PathVariable("newPlanId") Long newPlanId){
		
		String username = principal.getName();
//		User user = userRepository.findByUsername(username);
		
		Member member = memberRepository.getMemberByUsername(username);
		
		
		Optional<Plan> optionalNP = planRepository.findById(newPlanId);
		if(!optionalNP.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plan ID you want to update is Invalid!");
		
		Plan plan = optionalNP.get();
		
		member.setPlan(plan);
		memberRepository.save(member);
		
		return ResponseEntity.status(HttpStatus.OK).body("Plan changed succesfully!");
	}
//	
//	@PostMapping("/uploadPhoto")
//	public ResponseEntity<String> uploadMultipleFiles(@RequestParam("file") MultipartFile file,Principal principal) {
//		String username = principal.getName();
//		Member member = memberRepository.getMemberByUsername(username);
//		Document document = null;;
//		try {
//			document = new Document(file.getName(),file.getContentType(),file.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		member.setDocument(document);
//		memberRepository.save(member);
//		return ResponseEntity.status(HttpStatus.OK).body("Document uploaded successfully!");
//	}

}
