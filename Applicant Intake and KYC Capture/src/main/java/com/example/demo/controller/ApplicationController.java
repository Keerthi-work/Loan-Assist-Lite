package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.dto.RequiredResponseDTO;
import com.example.demo.service.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;

    // ✅ CREATE NEW APPLICATION
    @PostMapping
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationDTO dto)
    {
        ApplicationDTO created =
                applicationService.createNewApplication(dto);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ✅ FETCH APPLICATION BY ID
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable Long applicationId)
    {
        ApplicationDTO application =
                applicationService.fetchByApplicationId(applicationId);

        return new  ResponseEntity<ApplicationDTO>(application, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RequiredResponseDTO>> getAllApplications()
    {
        List<RequiredResponseDTO> list = applicationService.fetchAllApplications();
        return new  ResponseEntity<List<RequiredResponseDTO>>(list, HttpStatus.OK);
    }

    //UPDATE APPLICATION
    @PutMapping("/{applicationId}")
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable Long applicationId,@RequestBody ApplicationDTO dto)
    {
        ApplicationDTO updated = applicationService.updateApplication(applicationId, dto);
        return new ResponseEntity<ApplicationDTO>(updated, HttpStatus.OK);
    }

    // ✅ DELETE APPLICATION
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long applicationId)
    {
        String response = applicationService.deleteApplication(applicationId);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    // ✅ UPDATE APPLICATION STATUS
    @PutMapping("/{applicationId}/status/{status}")
    public ResponseEntity<ApplicationDTO> updateStatus(@PathVariable Long applicationId,@PathVariable String status)
    {
        ApplicationDTO updated = applicationService.updateStatus(applicationId, status);
        return new ResponseEntity<ApplicationDTO>(updated, HttpStatus.OK);
    }
    
    // ✅ FIND BY STATUS (WITH PRODUCT DETAILS)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<RequiredResponseDTO>> findByStatus(@PathVariable String status)
    {
        List<RequiredResponseDTO> list = applicationService.findByStatus(status);
        return new ResponseEntity<List<RequiredResponseDTO>>(list, HttpStatus.OK);
    }

    // ✅ FIND BY APPLICANT ID (WITH PRODUCT DETAILS)
    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<RequiredResponseDTO>>findByApplicantId(@PathVariable Long applicantId)
    {
        List<RequiredResponseDTO> list = applicationService.findByApplicantId(applicantId);
        return new ResponseEntity<List<RequiredResponseDTO>>(list, HttpStatus.OK);
    }

    // ✅ FIND BY PRODUCT ID (WITH PRODUCT DETAILS)
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<RequiredResponseDTO>> findByProductId(@PathVariable Long productId)
    {
        List<RequiredResponseDTO> list = applicationService.findByProductId(productId);
        return new ResponseEntity<List<RequiredResponseDTO>>(list, HttpStatus.OK);
    }

    // ✅ SUBMIT APPLICATION
    @PutMapping("/{applicationId}/submit")
    public ResponseEntity<ApplicationDTO> submitApplication(@PathVariable Long applicationId)
    {
        ApplicationDTO submitted = applicationService.submitApplication(applicationId);
        return new ResponseEntity<ApplicationDTO>(submitted, HttpStatus.OK) ;
    }
}