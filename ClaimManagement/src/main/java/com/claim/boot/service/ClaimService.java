package com.claim.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	
}
