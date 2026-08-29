

package com.pravartak.model;

import java.util.HashMap;
import java.util.Map;

public class UserModel {

    private String uid;
    private String fullName;
    private String email;
    private String role;

    public UserModel(
            String uid,
            String fullName,
            String email,
            String role) {

        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Map<String, Object> toMap() {

        Map<String, Object> map =
                new HashMap<>();

        map.put("fullName", fullName);
        map.put("email", email);
        map.put("role", role);

        return map;
    }
}