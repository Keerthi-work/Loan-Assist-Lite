package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KYCDocumentDTO;
import com.example.demo.entity.Applicant;
import com.example.demo.entity.KYCDocument;
import com.example.demo.entity.KYCDocument.DocType;
import com.example.demo.exception.InvalidKYCDocumentException;
import com.example.demo.exception.KYCDocumentNotFoundException;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.repository.KYCDocumentRepository;
import com.example.demo.service.KYCDocumentService;

@Service
public class KYCDocumentServiceImpl implements KYCDocumentService {

    @Autowired
    private KYCDocumentRepository kycDocumentRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    // ✅ ENTITY → DTO
    private KYCDocumentDTO convertToDTO(KYCDocument entity) {

        KYCDocumentDTO dto = new KYCDocumentDTO();
        dto.setDocId(entity.getDocId());
        dto.setApplicantId(entity.getApplicantId().getApplicantId());
        dto.setDocType(KYCDocumentDTO.DocType.valueOf(entity.getDocType().name()));
        dto.setFileURI(entity.getFileURI());
        dto.setUploadedAt(entity.getUploadedAt());
        dto.setVerifiedBy(entity.getVerifiedBy());
        dto.setVerifiedAt(entity.getVerifiedAt());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    // ✅ DTO → ENTITY
    private KYCDocument convertToEntity(KYCDocumentDTO dto) {

        Applicant applicant = applicantRepository.findById(dto.getApplicantId())
                .orElseThrow(() ->
                        new InvalidKYCDocumentException(
                                "Applicant not found with ID: " + dto.getApplicantId()));

        KYCDocument entity = new KYCDocument();
        entity.setDocId(dto.getDocId());
        entity.setApplicantId(applicant);
        entity.setDocType(DocType.valueOf(dto.getDocType().name()));
        entity.setFileURI(dto.getFileURI());
        entity.setUploadedAt(dto.getUploadedAt());
        entity.setVerifiedBy(dto.getVerifiedBy());
        entity.setVerifiedAt(dto.getVerifiedAt());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    // ✅ UPLOAD DOCUMENT
    @Override
    public KYCDocumentDTO uploadDocument(KYCDocumentDTO dto) {

        if (dto.getDocType() == null || dto.getFileURI() == null) {
            throw new InvalidKYCDocumentException(
                    "Document type and file URI are mandatory");
        }

        KYCDocument entity = convertToEntity(dto);
        entity.setUploadedAt(LocalDateTime.now());
        entity.setStatus("UPLOADED");

        KYCDocument saved = kycDocumentRepository.save(entity);
        return convertToDTO(saved);
    }

    // ✅ FETCH BY DOC ID
    @Override
    public KYCDocumentDTO fetchByDocId(Long docId) {

        KYCDocument document = kycDocumentRepository.findById(docId)
                .orElseThrow(() ->
                        new KYCDocumentNotFoundException(
                                "KYC Document not found with ID: " + docId));

        return convertToDTO(document);
    }

    // ✅ FETCH ALL DOCUMENTS
    @Override
    public List<KYCDocumentDTO> fetchAllDocuments() {

        List<KYCDocument> documents = kycDocumentRepository.findAll();
        List<KYCDocumentDTO> dtoList = new ArrayList<>();

        for (KYCDocument doc : documents) {
            dtoList.add(convertToDTO(doc));
        }
        return dtoList;
    }

    // ✅ FETCH BY APPLICANT ID
    @Override
    public List<KYCDocumentDTO> fetchByApplicantId(Long applicantId) {

        List<KYCDocument> documents =
                kycDocumentRepository.findByApplicantId_ApplicantId(applicantId);

        List<KYCDocumentDTO> dtoList = new ArrayList<>();
        for (KYCDocument doc : documents) {
            dtoList.add(convertToDTO(doc));
        }
        return dtoList;
    }

    // ✅ FETCH BY APPLICANT ID + DOC TYPE
    @Override
    public List<KYCDocumentDTO> fetchByApplicantIdAndDocType(
            Long applicantId, DocType docType) {

        List<KYCDocument> documents =
                kycDocumentRepository.findByApplicantId_ApplicantIdAndDocType(
                        applicantId, docType);

        List<KYCDocumentDTO> dtoList = new ArrayList<>();
        for (KYCDocument doc : documents) {
            dtoList.add(convertToDTO(doc));
        }
        return dtoList;
    }

    // ✅ FETCH BY STATUS
    @Override
    public List<KYCDocumentDTO> fetchByStatus(String status) {

        List<KYCDocument> documents =
                kycDocumentRepository.findByStatus(status);

        List<KYCDocumentDTO> dtoList = new ArrayList<>();
        for (KYCDocument doc : documents) {
            dtoList.add(convertToDTO(doc));
        }
        return dtoList;
    }

    // ✅ VERIFY DOCUMENT
    @Override
    public KYCDocumentDTO verifyDocument(Long docId, Long verifiedBy) {

        KYCDocument document = kycDocumentRepository.findById(docId)
                .orElseThrow(() ->
                        new KYCDocumentNotFoundException(
                                "KYC Document not found with ID: " + docId));

        document.setStatus("VERIFIED");
        document.setVerifiedBy(verifiedBy);
        document.setVerifiedAt(LocalDateTime.now());

        KYCDocument updated = kycDocumentRepository.save(document);
        return convertToDTO(updated);
    }

    // ✅ REJECT DOCUMENT
    @Override
    public KYCDocumentDTO rejectDocument(
            Long docId, Long verifiedBy, String rejectionReason) {

        if (rejectionReason == null || rejectionReason.isBlank()) {
            throw new InvalidKYCDocumentException(
                    "Rejection reason must be provided");
        }

        KYCDocument document = kycDocumentRepository.findById(docId)
                .orElseThrow(() ->
                        new KYCDocumentNotFoundException(
                                "KYC Document not found with ID: " + docId));

        document.setStatus("REJECTED");
        document.setVerifiedBy(verifiedBy);
        document.setVerifiedAt(LocalDateTime.now());

        document.setFileURI(
                document.getFileURI() + " | REJECTION_REASON: " + rejectionReason);

        KYCDocument updated = kycDocumentRepository.save(document);
        return convertToDTO(updated);
    }

    // ✅ DELETE DOCUMENT
    @Override
    public String deleteByDocumentId(Long docId) {

        if (!kycDocumentRepository.existsById(docId)) {
            throw new KYCDocumentNotFoundException(
                    "KYC Document not found with ID: " + docId);
        }

        kycDocumentRepository.deleteById(docId);
        return "KYC Document deleted successfully with ID: " + docId;
    }
}