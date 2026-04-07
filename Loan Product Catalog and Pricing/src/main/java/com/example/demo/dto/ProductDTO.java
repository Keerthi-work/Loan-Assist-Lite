package com.example.demo.dto;

public class ProductDTO {

    private Long productId;
    private String name;
    private Double maxAmount;
    private Double minAmount;
    private Integer maxTermMonths;
    private Double interestRateAnnual;
    private String feeStructureJSON;
    private String eligibilityNotes;
    private String status;

    // ✅ Getters & Setters
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }
    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }
    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxTermMonths() {
        return maxTermMonths;
    }
    public void setMaxTermMonths(Integer maxTermMonths) {
        this.maxTermMonths = maxTermMonths;
    }

    public Double getInterestRateAnnual() {
        return interestRateAnnual;
    }
    public void setInterestRateAnnual(Double interestRateAnnual) {
        this.interestRateAnnual = interestRateAnnual;
    }

    public String getFeeStructureJSON() {
        return feeStructureJSON;
    }
    public void setFeeStructureJSON(String feeStructureJSON) {
        this.feeStructureJSON = feeStructureJSON;
    }

    public String getEligibilityNotes() {
        return eligibilityNotes;
    }
    public void setEligibilityNotes(String eligibilityNotes) {
        this.eligibilityNotes = eligibilityNotes;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}