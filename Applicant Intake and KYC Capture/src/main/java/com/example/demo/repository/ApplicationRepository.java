package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

	public List<Application> findByStatus(String status);

	public List<Application> findByProductID(Long productId);

	public List<Application> findByApplicant_ApplicantId(Long applicantId);

}

