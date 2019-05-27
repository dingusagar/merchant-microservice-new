package com.example.merchantmicroservice.model;

public class MerchantDTO {
    private String merchantId;
    private String merchantName;
    private double merchantRating;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
    }
}
