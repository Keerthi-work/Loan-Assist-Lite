package com.example.demo.dto;

import java.time.LocalDate;

public class PricingVersionDTO {

    private Long pricingId;
    private Long productId;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Double interestRateAnnual;
    private String feesJSON;

    public Long getPricingId() {
        return pricingId;
    }
    public void setPricingId(Long pricingId) {
        this.pricingId = pricingId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
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
}