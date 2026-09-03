package com.pravartak.model.farmer_model;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.Timestamp;

public class Product {

    private int productId;
    private int farmerId;

    private String productName;
    private String category;
    private String description;

    private double price;
    private String unit;
    private double quantity;

    private String location;
    private String imagePath;

    private String status;
    private int orders;

    // =====================================================
    // ACTIVITY TIMESTAMP
    // =====================================================

    private Timestamp createdAt;

    // =====================================================
    // EMPTY CONSTRUCTOR
    // Required by Firestore
    // =====================================================

    public Product() {
    }

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Product(
            int productId,
            int farmerId,
            String productName,
            String category,
            String description,
            double price,
            String unit,
            double quantity,
            String location,
            String imagePath) {

        this.productId = productId;
        this.farmerId = farmerId;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.location = location;
        this.imagePath = imagePath;

        this.status = "Active";
        this.orders = 0;

        // Timestamp will be set when the product
        // is actually saved to Firebase.
        this.createdAt = null;
    }

    // =====================================================
    // PRODUCT ID
    // =====================================================

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // =====================================================
    // FARMER ID
    // =====================================================

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    // =====================================================
    // PRODUCT NAME
    // =====================================================

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // =====================================================
    // CATEGORY
    // =====================================================

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // =====================================================
    // DESCRIPTION
    // =====================================================

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // =====================================================
    // PRICE
    // =====================================================

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // =====================================================
    // UNIT
    // =====================================================

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // =====================================================
    // QUANTITY
    // =====================================================

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // =====================================================
    // LOCATION
    // =====================================================

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // =====================================================
    // IMAGE PATH
    // =====================================================

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // =====================================================
    // STATUS
    // =====================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =====================================================
    // ORDERS
    // =====================================================

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    // =====================================================
    // CREATED AT
    // =====================================================

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // =====================================================
    // FIRESTORE MAP
    // =====================================================

    public Map<String, Object> toMap() {

        Map<String, Object> map =
                new HashMap<>();

        map.put(
                "productId",
                productId
        );

        map.put(
                "farmerId",
                farmerId
        );

        map.put(
                "productName",
                productName
        );

        map.put(
                "category",
                category
        );

        map.put(
                "description",
                description
        );

        map.put(
                "price",
                price
        );

        map.put(
                "unit",
                unit
        );

        map.put(
                "quantity",
                quantity
        );

        map.put(
                "location",
                location
        );

        map.put(
                "imagePath",
                imagePath
        );

        map.put(
                "status",
                status
        );

        map.put(
                "orders",
                orders
        );

        // =================================================
        // ACTIVITY TIMESTAMP
        // =================================================

        if (createdAt != null) {

            map.put(
                    "createdAt",
                    createdAt
            );
        }

        return map;
    }
}