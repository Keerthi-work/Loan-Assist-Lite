package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ApplicationDTO;
import com.example.demo.dto.RequiredResponseDTO;

public interface ApplicationService 
{
	    public ApplicationDTO createNewApplication(ApplicationDTO dto);
	    public ApplicationDTO fetchByApplicationId(Long applicationId);
	    public List<RequiredResponseDTO> fetchAllApplications();
	    public ApplicationDTO updateApplication(Long applicationId, ApplicationDTO dto);
	    public String deleteApplication(Long applicationId);
	    public ApplicationDTO updateStatus(Long applicationId, String status);
	    public List<RequiredResponseDTO> findByStatus(String status);
	    public List<RequiredResponseDTO> findByApplicantId(Long applicantId);
	    public List<RequiredResponseDTO> findByProductId(Long productId);
	    public ApplicationDTO submitApplication(Long applicationId);
}



