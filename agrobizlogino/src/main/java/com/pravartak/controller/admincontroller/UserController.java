package com.pravartak.controller.admincontroller;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.dao.admindao.UserDAO;
import com.pravartak.model.admin.User;

import java.util.List;

public class UserController {

    private final UserDAO userDAO;

    public UserController() {

        Firestore db =
                FirebaseConfig.getFirestore();

        userDAO =
                new UserDAO(db);
    }

    // =====================================================
    // ALL USERS
    // =====================================================

    public List<User> getAllUsers() {

        return userDAO.getAllUsers();
    }

    // =====================================================
    // FARMERS
    // =====================================================

    public List<User> getFarmers() {

        return userDAO.getFarmers();
    }

    // =====================================================
    // BUYERS
    // =====================================================

    public List<User> getBuyers() {

        return userDAO.getBuyers();
    }
}