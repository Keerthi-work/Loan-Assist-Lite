package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApplicantDTO;
import com.example.demo.service.ApplicantService;

@RestController
@RequestMapping("/applicant")
public class ApplicantController 
{
	@Autowired
	private ApplicantService applicantService;
	
	@PostMapping("/new")
	public ResponseEntity<ApplicantDTO> insertNewApplicant(@RequestBody ApplicantDTO applicantDto)
	{
        ApplicantDTO details = applicantService.addNewApplicant(applicantDto);
		return new ResponseEntity<ApplicantDTO>(details, HttpStatus.CREATED);	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ApplicantDTO>> getAllApplicants()
	{
		List<ApplicantDTO> allApplicants = applicantService.fetchAllApplicants();
		return new ResponseEntity<List<ApplicantDTO>>(allApplicants, HttpStatus.OK);
	}
	
	@GetMapping("/id/{applicantId}")
	public ResponseEntity<ApplicantDTO> getByApplicantId(@PathVariable Long applicantId)
	{
		ApplicantDTO details = applicantService.fetchByApplicantId(applicantId);
		return new ResponseEntity<ApplicantDTO>(details, HttpStatus.OK);
	}
	
	@PutMapping("/update/{applicantId}")
	public ResponseEntity<ApplicantDTO> update(@PathVariable Long applicantId, @RequestBody ApplicantDTO dto)
	{
		ApplicantDTO updated = applicantService.updateApplicant(applicantId, dto);
		return new ResponseEntity<ApplicantDTO>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{applicantId}")
	public ResponseEntity<String> deleteApplicant(@PathVariable Long applicantId)
	{
	    String result = applicantService.deleteApplicantById(applicantId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/updatestatus/{applicantId}")
	public ResponseEntity<String> updateStatus(@PathVariable Long applicantId,  @RequestParam String status)
	{
	    String result = applicantService.updateKycStatus(applicantId, status);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/verify/{applicantId}")
	public ResponseEntity<Boolean> verifyKycDocuments(@PathVariable Long applicantId)
	{
	    boolean result = applicantService.verifyKycDocuments(applicantId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}

    @GetMapping("status")
    public ResponseEntity<List<ApplicantDTO>> getByKycStatus(@RequestParam String status)
    {
    	List<ApplicantDTO> kycStatusList = applicantService.findByKycStatus(status);
    	return new ResponseEntity<List<ApplicantDTO>>(kycStatusList, HttpStatus.OK);
    }
   
}
