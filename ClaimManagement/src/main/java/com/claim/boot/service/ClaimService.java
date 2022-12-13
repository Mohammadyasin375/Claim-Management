package com.claim.boot.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.claim.boot.enums.ClaimStatusEnum;
import com.claim.boot.model.Claim;
import com.claim.boot.model.Member;
import com.claim.boot.model.Plan;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.DocumentRepository;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.repository.PlanRepository;
import com.claim.boot.repository.UserRepository;


@Service
public class ClaimService {
	
	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ClaimRepository claimRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private DocumentRepository documentRepository;
	
	public ResponseEntity<String> insertClaim(String username,Claim claim,Long pId) {

	
		Member member = memberRepository.getMemberByUsername(username);

		claim.setMember(member);
		claim.setStatus(ClaimStatusEnum.PENDING);
		
		Optional<Plan> optional=planRepository.findById(pId);
		if(!optional.isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plan ID is Invalid");
		
		Plan plan=optional.get();
		claim.setPlan(plan);
		
        claimRepository.save(claim);
		return ResponseEntity.status(HttpStatus.OK).body("Claim Submitted Successfully!");
	}
	

	
	public ResponseEntity<String> approveClaim(String status,Long cId) {
		ClaimStatusEnum approvalStatus = null;
		try {
			approvalStatus = ClaimStatusEnum.valueOf(status);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Status");
		}
		
		
		Optional<Claim> optional = claimRepository.findById(cId);

		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Claim ID is Invalid");

		Claim claim = optional.get();
		
		if(claim.getClaimAmount()<claim.getPlan().getInsuredAmount())
		{claim.setStatus(approvalStatus);
		 double insuredAmtLeft=0;
		 insuredAmtLeft=claim.getPlan().getInsuredAmount()-claim.getClaimAmount();
		
		
		
		claimRepository.save(claim);
		return ResponseEntity.status(HttpStatus.OK).body("Claim status approved!");}
		
		return ResponseEntity.status(HttpStatus.OK).body("Not eligible for approval");
		
	}
}
