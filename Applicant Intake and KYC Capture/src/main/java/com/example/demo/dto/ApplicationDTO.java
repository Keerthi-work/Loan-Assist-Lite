package com.example.demo.dto;

import java.time.LocalDateTime;



public class ApplicationDTO {
	
	private Long applicationId;
	
	private Long applicantId;
	
	private Long productID;
	
	private Double requestedAmount;
	
	private Integer termMonths;
	
	private String purpose;
	
	private LocalDateTime submittedAt;
  
	private String status;
	

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public Long getProductId() {
		return productID;
	}

	public void setProductId(Long productID) {
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

}

