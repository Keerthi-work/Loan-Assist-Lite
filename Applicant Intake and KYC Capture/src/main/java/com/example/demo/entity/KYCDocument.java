package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "KYC_Document")
public class KYCDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;

    @ManyToOne
    @JoinColumn(name="applicant_Id")
    private Applicant applicantId;

    @Enumerated(EnumType.STRING)
    private DocType docType;

    private String fileURI;

    private LocalDateTime uploadedAt;

    private Long verifiedBy;   // UserID (LoanOfficer / Underwriter)

    private LocalDateTime verifiedAt;
    private String status;

    // ✅ ENUMS
    public enum DocType {
        ID,
        ADDRESS,
        INCOME
    }

    // ✅ Constructors
    public KYCDocument() {}

    public KYCDocument(Long docId, Applicant applicantId, DocType docType, String fileURI,
                       LocalDateTime uploadedAt, Long verifiedBy,
                       LocalDateTime verifiedAt, String status) {
        this.docId = docId;
        this.applicantId = applicantId;
        this.docType = docType;
        this.fileURI = fileURI;
        this.uploadedAt = uploadedAt;
        this.verifiedBy = verifiedBy;
        this.verifiedAt = verifiedAt;
        this.status = status;
    }

    // ✅ Getters & Setters
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Applicant getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Applicant applicantId) {
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

    @Override
    public String toString() {
        return "KYCDocument [docId=" + docId + ", applicantId=" + applicantId +
               ", docType=" + docType + ", fileURI=" + fileURI +
               ", uploadedAt=" + uploadedAt + ", verifiedBy=" + verifiedBy +
               ", verifiedAt=" + verifiedAt + ", status=" + status + "]";
    }
}