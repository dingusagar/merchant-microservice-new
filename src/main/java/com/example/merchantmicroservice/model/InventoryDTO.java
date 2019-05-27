package com.example.merchantmicroservice.model;

public class InventoryDTO {
    private String productId;
    private String productName;
    private String productImage;
    private String productCategory;
    private String variantId;
    private String merchantId;
    private String merchantName;
    private Double price;
    private int stockAvailable;
    private int stockSold;
    private double averageUserRating;
    private double aggregatedScore;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVariantId() {
        return variantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public int getStockSold() {
        return stockSold;
    }

    public void setStockSold(int stockSold) {
        this.stockSold = stockSold;
    }

    public double getAverageUserRating() {
        return averageUserRating;
    }

    public void setAverageUserRating(double averageUserRating) {
        this.averageUserRating = averageUserRating;
    }

    public double getAggregatedScore() {
        return aggregatedScore;
    }

    public void setAggregatedScore(double aggregatedScore) {
        this.aggregatedScore = aggregatedScore;
    }
}
