package com.claim.boot.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.claim.boot.dto.PlanResponseDto;
import com.claim.boot.model.Member;
import com.claim.boot.model.Plan;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.repository.PlanRepository;

@Service
public class PlanService {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private MemberRepository memberRepository;

	public ResponseEntity<String> postPlan(String username, @RequestBody Plan plan) {

		/* Fetch Member by username */
		Member member = memberRepository.getMemberByUsername(username);

		/* Attach this member to plan */
		plan.setMember(member);

		/* Post Leave */
		planRepository.save(plan);
		return ResponseEntity.status(HttpStatus.OK).body("Plan added Successfully");

	}

	public List<PlanResponseDto> getAllPlansByUsername(String username) {
		List<Plan> list = planRepository.getAllPlansByUsername(username);

		List<PlanResponseDto> listDto = new ArrayList<>();
		Long id;
		for (Plan p : list) {
			PlanResponseDto dto = new PlanResponseDto();
			dto.setPlanId(p.getPlanId());
			dto.setInsuredAmount(p.getInsuredAmount());
			dto.setStartDate(p.getStartDate());
			dto.setPlanType(p.getPlanType().toString());
			dto.setEndDate(p.getEndDate());
			listDto.add(dto);
		}
		return listDto;
	}

}
