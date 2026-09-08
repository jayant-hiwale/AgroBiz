package com.pravartak.model.buyer_model;

import com.google.cloud.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private String orderId;

    private String buyerUid;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;

    private int farmerId;
    private String farmerName;

    private List<Map<String, Object>> items = new ArrayList<>();

    private double totalAmount;

    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;

    private Timestamp createdAt;

    // =========================================================
    // BUYER NOTIFICATION
    // =========================================================

    private boolean buyerNotified;

    // =========================================================
    // EMPTY CONSTRUCTOR
    // =========================================================

    public Order() {
    }

    // =========================================================
    // FULL CONSTRUCTOR
    // =========================================================

    public Order(
            String orderId,
            String buyerUid,
            String buyerName,
            String buyerPhone,
            String buyerAddress,
            int farmerId,
            String farmerName,
            List<Map<String, Object>> items,
            double totalAmount,
            String paymentMethod,
            String paymentStatus,
            String orderStatus,
            Timestamp createdAt) {

        this.orderId = orderId;
        this.buyerUid = buyerUid;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.items = items != null
                ? items
                : new ArrayList<>();
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;

        // New orders have not yet been notified
        this.buyerNotified = false;
    }

    // =========================================================
    // GETTERS / SETTERS
    // =========================================================

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // =========================================================
    // BUYER NOTIFIED
    // =========================================================

    public boolean isBuyerNotified() {
        return buyerNotified;
    }

    public void setBuyerNotified(boolean buyerNotified) {
        this.buyerNotified = buyerNotified;
    }

    // =========================================================
    // FIRESTORE MAP
    // =========================================================

    public Map<String, Object> toMap() {

        Map<String, Object> map =
                new HashMap<>();

        map.put("orderId", orderId);

        map.put("buyerUid", buyerUid);
        map.put("buyerName", buyerName);
        map.put("buyerPhone", buyerPhone);
        map.put("buyerAddress", buyerAddress);

        map.put("farmerId", farmerId);
        map.put("farmerName", farmerName);

        map.put("items", items);

        map.put("totalAmount", totalAmount);

        map.put("paymentMethod", paymentMethod);
        map.put("paymentStatus", paymentStatus);
        map.put("orderStatus", orderStatus);

        map.put("createdAt", createdAt);

        // Notification state
        map.put("buyerNotified", buyerNotified);

        return map;
    }
}