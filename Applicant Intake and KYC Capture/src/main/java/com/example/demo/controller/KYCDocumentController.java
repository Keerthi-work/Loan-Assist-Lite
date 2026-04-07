package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.KYCDocumentDTO;
import com.example.demo.entity.KYCDocument.DocType;
import com.example.demo.service.KYCDocumentService;

@RestController
@RequestMapping("/kyc")
public class KYCDocumentController
{
    @Autowired
    private KYCDocumentService kycService;

    // ✅ UPLOAD KYC DOCUMENT
    @PostMapping("/upload")
    public ResponseEntity<KYCDocumentDTO> uploadDocument(@RequestBody KYCDocumentDTO dto)
    {
        KYCDocumentDTO saved = kycService.uploadDocument(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ FETCH KYC DOCUMENT BY ID
    @GetMapping("/{docId}")
    public ResponseEntity<KYCDocumentDTO> getByDocId(@PathVariable Long docId)
    {
        KYCDocumentDTO document = kycService.fetchByDocId(docId);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    // ✅ FETCH ALL KYC DOCUMENTS
    @GetMapping("/all")
    public ResponseEntity<List<KYCDocumentDTO>> getAllDocuments()
    {
        List<KYCDocumentDTO> documents = kycService.fetchAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // ✅ FETCH DOCUMENTS BY APPLICANT ID
    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<KYCDocumentDTO>> getByApplicantId(@PathVariable Long applicantId)
    {
        List<KYCDocumentDTO> documents = kycService.fetchByApplicantId(applicantId);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // ✅ FETCH DOCUMENTS BY APPLICANT ID & DOCUMENT TYPE
    @GetMapping("/applicant/{applicantId}/type/{docType}")
    public ResponseEntity<List<KYCDocumentDTO>> getByApplicantAndDocType( @PathVariable Long applicantId, @PathVariable DocType docType)
    {
        List<KYCDocumentDTO> documents = kycService.fetchByApplicantIdAndDocType(applicantId, docType);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // ✅ FETCH DOCUMENTS BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<KYCDocumentDTO>> getByStatus(@PathVariable String status)
    {
        List<KYCDocumentDTO> documents = kycService.fetchByStatus(status);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // ✅ VERIFY DOCUMENT
    @PutMapping("/{docId}/verify")
    public ResponseEntity<KYCDocumentDTO> verifyDocument(@PathVariable Long docId,@RequestParam Long verifiedBy)
    {
        KYCDocumentDTO verified = kycService.verifyDocument(docId, verifiedBy);
        return new ResponseEntity<>(verified, HttpStatus.OK);
    }

    // ✅ REJECT DOCUMENT
    @PutMapping("/{docId}/reject")
    public ResponseEntity<KYCDocumentDTO> rejectDocument( @PathVariable Long docId, @RequestParam Long verifiedBy,@RequestParam String reason) {

        KYCDocumentDTO rejected = kycService.rejectDocument(docId, verifiedBy, reason);
        return new ResponseEntity<>(rejected, HttpStatus.OK);
    }

    // ✅ DELETE DOCUMENT
    @DeleteMapping("/delete/{docId}")
    public ResponseEntity<String> deleteDocument( @PathVariable Long docId)
    {
        String message = kycService.deleteByDocumentId(docId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}