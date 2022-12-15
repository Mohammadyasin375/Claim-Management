package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claim.boot.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

	@Query("select d.documentId from Document d where d.member.memberId=?1")
	Long findDocIdByMemberId(Long memberId);

}
