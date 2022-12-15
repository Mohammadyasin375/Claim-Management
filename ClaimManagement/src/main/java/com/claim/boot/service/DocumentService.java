package com.claim.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.claim.boot.model.Document;
import com.claim.boot.model.Member;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.DocumentRepository;
import com.claim.boot.repository.MemberRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ClaimRepository claimRepository;

	public ResponseEntity<String> saveFile(MultipartFile file,String username) {
		String docName = file.getOriginalFilename();
		Member member = memberRepository.getMemberByUsername(username);
		
		
		
		try {
			Document document = new Document(docName, file.getContentType(), file.getBytes());

			double size = (file.getSize()) / 1024;
			if (size > 200.0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size should beless than 200KB!");

			document.setMember(member);
			documentRepository.save(document);
			return ResponseEntity.status(HttpStatus.OK).body("Docs upload successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Optional<Document> getFile(Long fileId) {
		return documentRepository.findById(fileId);
	}

	public List<Document> getFiles() {
		return documentRepository.findAll();
	}
}
