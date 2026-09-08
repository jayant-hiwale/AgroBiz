package com.pravartak.model.admin;

import com.google.cloud.Timestamp;

public class User {

    private String userId;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private Timestamp createdAt;

    // Required by Firestore
    public User() {
    }

    // =====================================================
    // USER ID
    // =====================================================

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // =====================================================
    // FULL NAME
    // =====================================================

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // =====================================================
    // EMAIL
    // =====================================================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // =====================================================
    // PHONE
    // =====================================================

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // =====================================================
    // ROLE
    // =====================================================

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}