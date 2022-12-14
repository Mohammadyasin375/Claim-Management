package com.claim.boot.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

import com.claim.boot.dto.ClaimResponseDto;
import com.claim.boot.enums.ClaimStatusEnum;
import com.claim.boot.model.Claim;
import com.claim.boot.model.Document;
import com.claim.boot.model.Member;
import com.claim.boot.model.Plan;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.DocumentRepository;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.service.ClaimService;
import com.claim.boot.service.DocumentService;

@RestController
@RequestMapping("/api/claim")
@CrossOrigin("http://localhost:8903/")
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private ClaimRepository claimRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private DocumentRepository documentRepository;

	// To Insert Claim
	@PostMapping("/add/{pId}")
	public ResponseEntity<String> addClaim(Principal principal, @RequestBody Claim claim,
			@PathVariable("pId") Long pId) {
		String username = principal.getName();
		return claimService.insertClaim(username, claim, pId);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,Principal principal) {
		String username = principal.getName();
		return documentService.saveFile(file,username);
	}

	// Download or view Documents
	@GetMapping("/document/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
		Document doc = documentService.getFile(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDocName() + "\"")
				.body(new ByteArrayResource(doc.getData()));

	}

	// To get All Claims By Username
	@GetMapping("/all")
	public List<ClaimResponseDto> getAllClaims(Principal principal) {
		String username = principal.getName();
		List<Claim> list = claimRepository.getAllClaimsByUsername(username);

		List<ClaimResponseDto> listDto = new ArrayList<ClaimResponseDto>();
		Long planId;
		for (Claim c : list) {
			ClaimResponseDto dto = new ClaimResponseDto();
			dto.setClaimId(c.getClaimId());
			dto.setClaimAmount(c.getClaimAmount());
			Plan p = c.getPlan();
			planId = p.getPlanId();
			dto.setPlanId(planId);
			dto.setRemarks(c.getRemarks());
			dto.setClaimDate(c.getClaimDate());
			dto.setStatus(c.getStatus());
			listDto.add(dto);
		}
		return listDto;
	}
	
	@GetMapping("/getAllFromDb")
	public List<ClaimResponseDto> getAllClaims(){
		ClaimStatusEnum status = ClaimStatusEnum.PENDING;
		List<Claim> claim = claimRepository.findAllPendingClaims(status);
		List<ClaimResponseDto> listDto = new ArrayList<ClaimResponseDto>();
		Long planId;
		for(Claim c:claim) {
			ClaimResponseDto dto = new ClaimResponseDto();
			dto.setClaimId(c.getClaimId());
			dto.setClaimAmount(c.getClaimAmount());
			Plan p = c.getPlan();
			planId = p.getPlanId();
			dto.setPlanId(planId);
			dto.setRemarks(c.getRemarks());
			dto.setClaimDate(c.getClaimDate());
			dto.setStatus(c.getStatus());
			listDto.add(dto);
			
		}
		return listDto;
	}
	
	@GetMapping("/getById/{claimId}")
	public ClaimResponseDto getClaimById(@PathVariable("claimId") Long claimId) {
		Claim claim = claimRepository.findById(claimId).get();
		ClaimResponseDto dto = new ClaimResponseDto();
		dto.setClaimId(claim.getClaimId());
		dto.setMemberId(claim.getMember().getMemberId());
		dto.setClaimAmount(claim.getClaimAmount());
		dto.setPlanId(claim.getPlan().getPlanId());
		dto.setClaimDate(claim.getClaimDate());
		dto.setStatus(claim.getStatus());
		dto.setRemarks(dto.getRemarks());
		return dto;
		
	}
	
	//To get Doc Id By Claim Id
	@GetMapping("/getDocId/{claimId}")
	public Long getDocIdByClaimId(@PathVariable("claimId") Long claimId) {
		Long memberId = claimRepository.findMemberId(claimId);
		return documentRepository.findDocIdByMemberId(memberId);
	}
}