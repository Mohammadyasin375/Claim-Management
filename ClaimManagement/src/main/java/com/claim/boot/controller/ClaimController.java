package com.claim.boot.controller;

import java.security.Principal;

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

import com.claim.boot.model.Claim;
import com.claim.boot.model.Document;
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

	// To Insert Claim
	@PostMapping("/add/{pId}")
	public ResponseEntity<String> addClaim(Principal principal, @RequestBody Claim claim,
			@PathVariable("pId") Long pId) {
		String username = principal.getName();
		return claimService.insertClaim(username, claim, pId);
	}

	// Upload document
	@PostMapping("/upload/{cId}")
	public ResponseEntity<String> uploadMultipleFiles(@RequestParam("file") MultipartFile file,
			@PathVariable("cId") Long cId, Principal principal) {
		return documentService.saveFile(file, cId);

	}

	// Download or view Documents
	@GetMapping("/document/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
		Document doc = documentService.getFile(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDocName() + "\"")
				.body(new ByteArrayResource(doc.getData()));

	}

}