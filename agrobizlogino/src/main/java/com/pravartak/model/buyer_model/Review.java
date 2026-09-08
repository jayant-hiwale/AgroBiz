package com.pravartak.model.buyer_model;

import com.google.cloud.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class Review {

    private String reviewId;
    private int productId;
    private String orderId;
    private int farmerId;
    private String productName;

    private String buyerUid;
    private String buyerName;

    private double rating;
    private String comment;

    private Timestamp createdAt;

    public Review() {
    }

    public Review(
            String reviewId,
            int productId,
            String productName,
            String orderId,
            int farmerId,
            String buyerUid,
            String buyerName,
            double rating,
            String comment,
            Timestamp createdAt) {

        this.reviewId = reviewId;
        this.productId = productId;
         this.productName = productName;
        this.orderId = orderId;
        this.farmerId = farmerId;
        this.buyerUid = buyerUid;
        this.buyerName = buyerName;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Map<String, Object> toMap() {

        Map<String, Object> map = new HashMap<>();

        map.put("reviewId", reviewId);
        map.put("productId", productId);
        map.put("orderId", orderId);
        map.put("farmerId", farmerId);

        map.put("buyerUid", buyerUid);
        map.put("buyerName", buyerName);

        map.put("rating", rating);
        map.put("comment", comment);

        map.put("createdAt", createdAt);
        map.put("productName", productName);

        return map;
    }
    public String getProductName() {
    return productName;
}

public void setProductName(String productName) {
    this.productName = productName;
}

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        this.buyerUid = buyerUid;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}