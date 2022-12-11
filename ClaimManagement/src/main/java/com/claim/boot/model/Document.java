package com.claim.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long documentId;
	
	private String docName;
	private String docType;
	
	@Lob
	private byte[] data;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(String docName, String docType, byte[] data) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
	}
}
