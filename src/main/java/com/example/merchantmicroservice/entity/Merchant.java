package com.example.merchantmicroservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Merchant.COLLECTION_NAME)
public class Merchant {
    public static final String COLLECTION_NAME="merchant";
    @Id
    private String merchantId;
    private String merchantName;
    private double merchantRating;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merhantId) {
        this.merchantId = merhantId;
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
