
package com.pravartak.model;

import java.util.HashMap;
import java.util.Map;

public class UserModel {

    private String uid;
    private String fullName;
    private String email;
    private String role;

    // Farmer ID
    private int farmerId;

    // =====================================================
    // CONSTRUCTOR - EXISTING
    // =====================================================

    public UserModel(
            String uid,
            String fullName,
            String email,
            String role) {

        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.farmerId = 0;
    }

    // =====================================================
    // CONSTRUCTOR - FARMER ID
    // =====================================================

    public UserModel(
            String uid,
            String fullName,
            String email,
            String role,
            int farmerId) {

        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.farmerId = farmerId;
    }

    // =====================================================
    // GET UID
    // =====================================================

    public String getUid() {
        return uid;
    }

    // =====================================================
    // GET FARMER ID
    // =====================================================

    public int getFarmerId() {
        return farmerId;
    }

    // =====================================================
    // SET FARMER ID
    // =====================================================

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    // =====================================================
    // GET FULL NAME
    // =====================================================

    public String getFullName() {
        return fullName;
    }

    // =====================================================
    // GET EMAIL
    // =====================================================

    public String getEmail() {
        return email;
    }

    // =====================================================
    // GET ROLE
    // =====================================================

    public String getRole() {
        return role;
    }

    // =====================================================
    // FIRESTORE MAP
    // =====================================================

    public Map<String, Object> toMap() {

        Map<String, Object> map =
                new HashMap<>();

        map.put(
                "fullName",
                fullName
        );

        map.put(
                "email",
                email
        );

        map.put(
                "role",
                role
        );

        // VERY IMPORTANT
        map.put(
                "farmerId",
                farmerId
        );

        return map;
    }
}