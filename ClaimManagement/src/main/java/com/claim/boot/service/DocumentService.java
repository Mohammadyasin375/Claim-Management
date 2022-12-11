package com.claim.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.claim.boot.model.Document;
import com.claim.boot.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	public Document saveFile(MultipartFile file) {
		String docName = file.getOriginalFilename();
		try {
			Document document = new Document(docName, file.getContentType(), file.getBytes());
			return documentRepository.save(document);
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
