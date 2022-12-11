package com.claim.boot.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.claim.boot.model.User;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.DocumentRepository;
import com.claim.boot.repository.MemberRepository;
import com.claim.boot.repository.UserRepository;
import com.claim.boot.service.DocumentService;

@RestController
@RequestMapping("/api/claim")
public class ClaimController {

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

	// To Insert Claim
	@PostMapping("/add")
	public ResponseEntity<String> insertClaim(Principal principal, @RequestBody Claim claim) {

		String username = principal.getName();
		Member member = memberRepository.getMemberByUsername(username);

		claim.setMember(member);
		claim.setStatus(ClaimStatusEnum.PENDING);

		claimRepository.save(claim);
		return ResponseEntity.status(HttpStatus.OK).body("Claim inserted Successfully!");
	}

	// To delete claim by Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteClaim(@PathVariable("id") Long id) {
		claimRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).body("Claim deleted!");
	}

	// To get Claim By ID
	@GetMapping("/{id}")
	public ClaimResponseDto getClaimById(@PathVariable("id") Long id) {

		Optional<Claim> optional = claimRepository.findById(id);
		if (!optional.isPresent())
			System.out.println(ResponseEntity.status(HttpStatus.OK).body("Invalid Claim ID!"));

		Claim claim = optional.get();

		ClaimResponseDto claimDto = new ClaimResponseDto();
		claimDto.setClaimId(claim.getClaimId());

		Member m = claim.getMember();
		id = m.getMemberId();
		claimDto.setMemberId(id);

		claimDto.setClaimType(claim.getClaimType());
		claimDto.setClaimAmount(claim.getClaimAmount());
		claimDto.setClaimDate(claim.getClaimDate());
		claimDto.setStatus(claim.getStatus());

		return claimDto;
	}

	// To get All Claims By Username
	@GetMapping("/getAllByUsername")
	public List<ClaimResponseDto> getAllClaims(Principal principal) {

		String username = principal.getName();
		List<Claim> list = claimRepository.getAllClaimsByUsername(username);

		Member member = memberRepository.getMemberByUsername(username);

		List<ClaimResponseDto> listDto = new ArrayList<ClaimResponseDto>();
		Long id;
		for (Claim c : list) {

			ClaimResponseDto dto = new ClaimResponseDto();
			dto.setClaimId(c.getClaimId());

			Member m = c.getMember();
			id = m.getMemberId();
			dto.setMemberId(id);

			dto.setClaimType(c.getClaimType());
			dto.setClaimAmount(c.getClaimAmount());
			dto.setClaimDate(c.getClaimDate());
			dto.setStatus(c.getStatus());

			listDto.add(dto);
		}
		return listDto;
	}

	// To edit/update claim by ID
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editClaimDetails(@PathVariable("id") Long id, @RequestBody Claim c) {

		Optional<Claim> optional = claimRepository.findById(id);

		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Claim ID is Invalid");

		Claim claim = optional.get();

		claim.setClaimAmount(c.getClaimAmount());
		claim.setClaimType(c.getClaimType());
		claim.setClaimDate(c.getClaimDate());

		claimRepository.save(claim);

		return ResponseEntity.status(HttpStatus.OK).body("Claim details updated Successfully!");
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadMultipleFiles(@RequestParam("file") MultipartFile file, Principal principal) {
		
		
		if ((file.getContentType() != "pdf"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only PDF format allowed!");

		double size = (file.getSize()) / 1024;
		if (size > 200.0 || size < 40.0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size should be between 40-200KB!");

		documentService.saveFile(file);
		return ResponseEntity.status(HttpStatus.OK).body("Uploaded Successfully!");
	}

	@GetMapping("/document/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
		Document doc = documentService.getFile(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDocName() + "\"")
				.body(new ByteArrayResource(doc.getData()));
	}

}
