package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pricing_Version")
public class PricingVersion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pricingId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    private Double interestRateAnnual;

    // JSON string to store fee details (processing fee, late fee, etc.)
    private String feesJSON;

    public PricingVersion() {}

    public PricingVersion(Long pricingId, Product product, LocalDate effectiveFrom, LocalDate effectiveTo, Double interestRateAnnual, String feesJSON)
    {
        this.pricingId = pricingId;
        this.product = product;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.interestRateAnnual = interestRateAnnual;
        this.feesJSON = feesJSON;
    }

    // ✅ Getters & Setters
    public Long getPricingId() {
        return pricingId;
    }
    public void setPricingId(Long pricingId) {
        this.pricingId = pricingId;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }
    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }
    public void setEffectiveTo(LocalDate effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public Double getInterestRateAnnual() {
        return interestRateAnnual;
    }
    public void setInterestRateAnnual(Double interestRateAnnual) {
        this.interestRateAnnual = interestRateAnnual;
    }

    public String getFeesJSON() {
        return feesJSON;
    }
    public void setFeesJSON(String feesJSON) {
        this.feesJSON = feesJSON;
    }

	@Override
	public String toString() {
		return "PricingVersion [pricingId=" + pricingId + ", product=" + product + ", effectiveFrom="
				+ effectiveFrom + ", effectiveTo=" + effectiveTo + ", interestRateAnnual=" + interestRateAnnual
				+ ", feesJSON=" + feesJSON + "]";
	}

    
    
     
}
