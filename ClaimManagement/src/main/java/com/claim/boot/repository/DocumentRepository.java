package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.boot.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
