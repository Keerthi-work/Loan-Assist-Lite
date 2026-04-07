package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApplicantDTO;
import com.example.demo.entity.Applicant;
import com.example.demo.exception.ApplicantNotFoundException;
import com.example.demo.exception.InvalidApplicantException;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository repository;

    private Applicant dtoToEntity(ApplicantDTO dto)
    {
        Applicant entity = new Applicant();
        entity.setApplicantId(dto.getApplicantId());
        entity.setName(dto.getName());
        entity.setDob(dto.getDob());
        entity.setNationalId(dto.getNationalId());
        entity.setAddress(dto.getAddress());
        entity.setContactInfo(dto.getContactInfo());
        entity.setKycStatus(dto.getKycStatus());
        return entity;
    }

    private ApplicantDTO entityToDto(Applicant entity) {
        ApplicantDTO dto = new ApplicantDTO();
        dto.setApplicantId(entity.getApplicantId());
        dto.setName(entity.getName());
        dto.setDob(entity.getDob());
        dto.setNationalId(entity.getNationalId());
        dto.setAddress(entity.getAddress());
        dto.setContactInfo(entity.getContactInfo());
        dto.setKycStatus(entity.getKycStatus());
        return dto;
    }

    @Override
    public ApplicantDTO addNewApplicant(ApplicantDTO applicantDto) {

        if (applicantDto.getName() == null) {
            throw new InvalidApplicantException("Applicant name cannot be empty");
        }
        if (applicantDto.getNationalId() == null) {
            throw new InvalidApplicantException("National ID cannot be empty");
        }

        Applicant entity = dtoToEntity(applicantDto);
        Applicant saved = repository.save(entity);
        return entityToDto(saved);
    }

    @Override
    public ApplicantDTO fetchByApplicantId(Long applicantId) {

        Applicant applicant = repository.findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant not found with ID: " + applicantId));

        return entityToDto(applicant);
    }

    @Override
    public List<ApplicantDTO> fetchAllApplicants() {

        List<Applicant> applicantList = repository.findAll();
        List<ApplicantDTO> dtoList = new ArrayList<>();

        for (Applicant applicant : applicantList) {
            dtoList.add(entityToDto(applicant));
        }
        return dtoList;
    }

    @Override
    public ApplicantDTO updateApplicant(Long applicantId, ApplicantDTO dto) {

        Applicant existing = repository.findById(applicantId)
                .orElseThrow(() ->
                        new ApplicantNotFoundException("Applicant not found with ID: " + applicantId));

        existing.setName(dto.getName());
        existing.setDob(dto.getDob());
        existing.setNationalId(dto.getNationalId());
        existing.setAddress(dto.getAddress());
        existing.setContactInfo(dto.getContactInfo());
        existing.setKycStatus(dto.getKycStatus());

        Applicant updated = repository.save(existing);
        return entityToDto(updated);
    }

    @Override
    public String deleteApplicantById(Long applicantId) {

        if (!repository.existsById(applicantId)) {
            throw new ApplicantNotFoundException("Applicant not found with ID: " + applicantId);
        }

        repository.deleteById(applicantId);
        return "Applicant with applicantId " + applicantId + " was deleted successfully";
    }

    @Override
    public String updateKycStatus(Long applicantId, String status) {

        Applicant applicant = repository.findById(applicantId)
                .orElseThrow(() ->
                        new ApplicantNotFoundException("Applicant not found with ID: " + applicantId));
        applicant.setKycStatus(status);
        repository.save(applicant);

        return "KYC Status updated to: " + status + " for applicantId " + applicantId;
    }

    @Override
    public List<ApplicantDTO> findByKycStatus(String status)
    {
        List<Applicant> applicants = repository.findByKycStatus(status);
        List<ApplicantDTO> dtoList = new ArrayList<>();
        for (Applicant applicant : applicants)
        {
            dtoList.add(entityToDto(applicant));
        }
        return dtoList;
    }

    @Override
    public boolean verifyKycDocuments(Long applicantId) 
    {
        Applicant applicant = repository.findById(applicantId)
                .orElseThrow(() ->
                        new ApplicantNotFoundException("Applicant not found with ID: " + applicantId));

        boolean hasNationalId = applicant.getNationalId() != null && !applicant.getNationalId().isEmpty();
        boolean hasAddress = applicant.getAddress() != null && !applicant.getAddress().isEmpty();
        boolean hasContactInfo = applicant.getContactInfo() != null && !applicant.getContactInfo().isEmpty();
        boolean isKycValid = hasNationalId && hasAddress && hasContactInfo;

        applicant.setKycStatus(isKycValid ? "VERIFIED" : "INCOMPLETE");
        repository.save(applicant);
        return isKycValid;
    }
}