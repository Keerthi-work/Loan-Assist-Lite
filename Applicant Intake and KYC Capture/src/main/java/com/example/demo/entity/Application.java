package com.example.demo.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Application")
public class Application {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long applicationId;
	@ManyToOne
	@JoinColumn(name="applicant_ID")
	private Applicant applicant;
	
	private Long productID;
	
	private Double requestedAmount;
	
	private Integer termMonths;
	
	private String purpose;
	
	private LocalDateTime submittedAt;
    
	private String status;
	
	public Application()
	{}

	public Application(Long applicationId, Applicant applicant, Long productID, Double requestedAmount, Integer termMonths,
			String purpose, LocalDateTime submittedAt, String status) 
	{
		super();
		this.applicationId = applicationId;
		this.applicant = applicant;
		this.productID = productID;
		this.requestedAmount = requestedAmount;
		this.termMonths = termMonths;
		this.purpose = purpose;
		this.submittedAt = submittedAt;
		this.status = status;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(Double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public Integer getTermMonths() {
		return termMonths;
	}

	public void setTermMonths(Integer termMonths) {
		this.termMonths = termMonths;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicantID=" + applicant + ", productID="
				+ productID + ", requestedAmount=" + requestedAmount + ", termMonths=" + termMonths + ", purpose="
				+ purpose + ", submittedAt=" + submittedAt + ", status=" + status + "]";
	}

	
}

	