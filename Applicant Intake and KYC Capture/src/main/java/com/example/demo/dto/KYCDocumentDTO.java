package com.example.demo.dto;

import java.time.LocalDateTime;

public class KYCDocumentDTO {

    private Long docId;
    private Long applicantId;
    private DocType docType;
    private String fileURI;
    private LocalDateTime uploadedAt;
    private Long verifiedBy;
    private LocalDateTime verifiedAt;
    private String status;

    // ✅ ENUMS (same meaning as entity, but DTO‑safe)
    public enum DocType {
        ID,
        ADDRESS,
        INCOME
    }


    // ✅ Getters & Setters
    public Long getDocId() {
        return docId;
    }
    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public DocType getDocType() {
        return docType;
    }
    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public String getFileURI() {
        return fileURI;
    }
    public void setFileURI(String fileURI) {
        this.fileURI = fileURI;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Long getVerifiedBy() {
        return verifiedBy;
    }
    public void setVerifiedBy(Long verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }
    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}