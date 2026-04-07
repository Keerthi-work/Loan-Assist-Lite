package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.KYCDocument;
import com.example.demo.entity.KYCDocument.DocType;

@Repository
public interface KYCDocumentRepository extends JpaRepository<KYCDocument, Long> {

	public List<KYCDocument> findByApplicantIdAndDocType(Long applicantId, DocType docType);

	public List<KYCDocument> findByStatus(String status);

	public List<KYCDocument> findByApplicantId_ApplicantIdAndDocType(Long applicantId, DocType docType);

	public List<KYCDocument> findByApplicantId_ApplicantId(Long applicantId);
}
