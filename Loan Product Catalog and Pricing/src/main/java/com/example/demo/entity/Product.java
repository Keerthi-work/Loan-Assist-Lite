package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private Double maxAmount;
    private Double minAmount;
    private Integer maxTermMonths;
    private Double interestRateAnnual;

    // JSON string for flexible fee definitions
    private String feeStructureJSON;

    private String eligibilityNotes;

    @Enumerated(EnumType.STRING)
    private Status status;

    // ENUM for Product life cycle
    public enum Status {
        ACTIVE,
        INACTIVE
    }

    public Product() {}

    public Product(Long productId, String name, Double maxAmount, Double minAmount,
                   Integer maxTermMonths, Double interestRateAnnual,
                   String feeStructureJSON, String eligibilityNotes, Status status) {
        this.productId = productId;
        this.name = name;
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.maxTermMonths = maxTermMonths;
        this.interestRateAnnual = interestRateAnnual;
        this.feeStructureJSON = feeStructureJSON;
        this.eligibilityNotes = eligibilityNotes;
        this.status = status;
    }

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

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", maxAmount=" + maxAmount + ", minAmount="
				+ minAmount + ", maxTermMonths=" + maxTermMonths + ", interestRateAnnual=" + interestRateAnnual
				+ ", feeStructureJSON=" + feeStructureJSON + ", eligibilityNotes=" + eligibilityNotes + ", status="
				+ status + "]";
	}

}