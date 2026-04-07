package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApplicantDTO;

public interface ApplicantService {
	
	public ApplicantDTO addNewApplicant(ApplicantDTO applicantDto);
	
	public ApplicantDTO fetchByApplicantId(Long applicantId);
	public List<ApplicantDTO> fetchAllApplicants();
    
	public ApplicantDTO updateApplicant(Long applicantId, ApplicantDTO dto);
    public String deleteApplicantById(Long applicantId);

    public String updateKycStatus(Long applicantId, String status);
    public boolean verifyKycDocuments(Long applicantId);

    public List<ApplicantDTO> findByKycStatus(String status);


}
