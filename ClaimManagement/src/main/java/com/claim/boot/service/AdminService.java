package com.claim.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.boot.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
//	public void approveClaim(long submissionId){
//		Submission submission = submissionRepository.findById(submissionId).get();
//		submission.setStatus("Approved");
//		submissionRepository.save(submission);
//	}
//	
//	public void rejectClaim(long submissionId){
//		Submission submission = submissionRepository.findById(submissionId).get();
//		submission.setStatus("Rejected");
//		submission.setRemarks("Documents not uploaded");
//		submissionRepository.save(submission);
//	}
	
}
