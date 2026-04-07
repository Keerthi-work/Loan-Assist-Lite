package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.KYCDocumentDTO;
import com.example.demo.entity.KYCDocument.DocType;

public interface KYCDocumentService 
{
    public KYCDocumentDTO uploadDocument(KYCDocumentDTO dto);
    public KYCDocumentDTO fetchByDocId(Long docId);
    public List<KYCDocumentDTO> fetchAllDocuments();
    public List<KYCDocumentDTO> fetchByApplicantId(Long applicantId);
    public List<KYCDocumentDTO> fetchByApplicantIdAndDocType(Long applicantId, DocType docType);
    public List<KYCDocumentDTO> fetchByStatus(String status);
    public KYCDocumentDTO verifyDocument(Long docId, Long verifiedBy);
    public KYCDocumentDTO rejectDocument(Long docId, Long verifiedBy, String rejectionReason);
    public String deleteByDocumentId(Long docId);
}
