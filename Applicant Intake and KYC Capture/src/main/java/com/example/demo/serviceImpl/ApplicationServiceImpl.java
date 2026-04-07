package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApplicantDTO;
import com.example.demo.dto.ApplicationDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.RequiredResponseDTO;
import com.example.demo.entity.Applicant;
import com.example.demo.entity.Application;
import com.example.demo.exception.ApplicationNotFoundException;
import com.example.demo.exception.InvalidApplicationException;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.repository.ApplicationRepository;
import com.example.demo.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String PRODUCT_SERVICE_URL =
            "http://localhost:1111/products/";

    private ApplicationDTO convertToDTO(Application entity) {

        ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(entity.getApplicationId());
        dto.setApplicantId(entity.getApplicant().getApplicantId());
        dto.setProductId(entity.getProductID());
        dto.setRequestedAmount(entity.getRequestedAmount());
        dto.setTermMonths(entity.getTermMonths());
        dto.setPurpose(entity.getPurpose());
        dto.setSubmittedAt(entity.getSubmittedAt());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    // ✅ DTO → ENTITY
    private Application convertToEntity(ApplicationDTO dto) {

    	Applicant applicant = applicantRepository.findById(dto.getApplicantId())
    		    .orElseThrow(() ->
    		        new InvalidApplicationException(
    		            "Applicant not found with ID: " + dto.getApplicantId()));

        Application entity = new Application();
        entity.setApplicant(applicant);
        entity.setProductID(dto.getProductId());
        entity.setRequestedAmount(dto.getRequestedAmount());
        entity.setTermMonths(dto.getTermMonths());
        entity.setPurpose(dto.getPurpose());

        return entity;
    }
    
    private ApplicantDTO convertApplicantToDTO(Applicant applicant) {

        ApplicantDTO dto = new ApplicantDTO();
        dto.setApplicantId(applicant.getApplicantId());
        dto.setName(applicant.getName());
        dto.setDob(applicant.getDob());
        dto.setNationalId(applicant.getNationalId());
        dto.setAddress(applicant.getAddress());
        dto.setContactInfo(applicant.getContactInfo());
        dto.setKycStatus(applicant.getKycStatus());

        return dto;
    }

    // ✅ CREATE APPLICATION
    @Override
    public ApplicationDTO createNewApplication(ApplicationDTO dto) {

        Application entity = convertToEntity(dto);

        if (entity.getStatus() == null) {
            entity.setStatus("INACTIVE");
        }

        Application saved = applicationRepository.save(entity);
        return convertToDTO(saved);
    }

    // ✅ FETCH BY APPLICATION ID
    @Override
    public ApplicationDTO fetchByApplicationId(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with ID: " + applicationId));

        return convertToDTO(application);
    }

    // ✅ FETCH ALL APPLICATIONS (WITH PRODUCT DETAILS)
    @Override
    public List<RequiredResponseDTO> fetchAllApplications() {

        List<Application> applications = applicationRepository.findAll();
        List<RequiredResponseDTO> responseList = new ArrayList<>();

        for (Application app : applications) {
            responseList.add(buildRequiredResponse(app));
        }
        return responseList;
    }

    // ✅ UPDATE APPLICATION
    @Override
    public ApplicationDTO updateApplication(Long applicationId, ApplicationDTO dto) {

        Application existing = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with ID: " + applicationId));

        existing.setProductID(dto.getProductId());
        existing.setRequestedAmount(dto.getRequestedAmount());
        existing.setTermMonths(dto.getTermMonths());
        existing.setPurpose(dto.getPurpose());
        existing.setStatus(dto.getStatus());

        Application updated = applicationRepository.save(existing);
        return convertToDTO(updated);
    }

    // ✅ DELETE APPLICATION
    @Override
    public String deleteApplication(Long applicationId) {

        if (!applicationRepository.existsById(applicationId)) {
            throw new ApplicationNotFoundException(
                    "Application not found with ID: " + applicationId);
        }

        applicationRepository.deleteById(applicationId);
        return "Application deleted successfully with ID: " + applicationId;
    }

    // ✅ UPDATE STATUS
    @Override
    public ApplicationDTO updateStatus(Long applicationId, String status) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with ID: " + applicationId));

        application.setStatus(status);
        return convertToDTO(applicationRepository.save(application));
    }

    // ✅ FIND BY STATUS
    @Override
    public List<RequiredResponseDTO> findByStatus(String status) {

        List<Application> applications =
                applicationRepository.findByStatus(status);

        List<RequiredResponseDTO> responseList = new ArrayList<>();
        for (Application app : applications) {
            responseList.add(buildRequiredResponse(app));
        }
        return responseList;
    }

    @Override
    public List<RequiredResponseDTO> findByApplicantId(Long applicantId) {

        List<Application> applications =
                applicationRepository.findByApplicant_ApplicantId(applicantId);

        List<RequiredResponseDTO> responseList = new ArrayList<>();
        for (Application app : applications) {
            responseList.add(buildRequiredResponse(app));
        }
        return responseList;
    }

    // ✅ FIND BY PRODUCT ID
    @Override
    public List<RequiredResponseDTO> findByProductId(Long productId) {

        List<Application> applications =
                applicationRepository.findByProductID(productId);

        List<RequiredResponseDTO> responseList = new ArrayList<>();
        for (Application app : applications) {
            responseList.add(buildRequiredResponse(app));
        }
        return responseList;
    }

    // ✅ SUBMIT APPLICATION
    @Override
    public ApplicationDTO submitApplication(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with ID: " + applicationId));

        if (application.getRequestedAmount() == null || application.getRequestedAmount() <= 0) {
            throw new InvalidApplicationException(
                    "Requested amount must be greater than zero");
        }

        if (application.getTermMonths() == null || application.getTermMonths() <= 0) {
            throw new InvalidApplicationException(
                    "Term months must be greater than zero");
        }

        application.setSubmittedAt(LocalDateTime.now());
        application.setStatus("ACTIVE");

        Application updated = applicationRepository.save(application);
        return convertToDTO(updated);
    }
    
    private RequiredResponseDTO buildRequiredResponse(Application application) {

        RequiredResponseDTO response = new RequiredResponseDTO();
        response.setApplication(convertToDTO(application));
        
         // ✅ Applicant (same microservice)
         response.setApplicant(convertApplicantToDTO(application.getApplicant())
         );


        try {
            ProductDTO product = restTemplate.getForObject(
                    PRODUCT_SERVICE_URL + application.getProductID(),
                    ProductDTO.class);
            response.setProduct(product);

        } catch (Exception ex) {
            // ✅ TEMPORARY: log the real error
            ex.printStackTrace();

            // ✅ Fallback if product service fails
            ProductDTO fallback = new ProductDTO();
            fallback.setProductId(application.getProductID());
            fallback.setStatus("UNAVAILABLE");
            response.setProduct(fallback);
        }

        return response;
    }
}