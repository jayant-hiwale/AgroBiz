package com.pravartak.dao.admindao;

import com.google.cloud.firestore.Firestore;
import com.pravartak.model.admin.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Firestore db;

    public UserDAO(Firestore db) {

        if (db == null) {
            throw new IllegalArgumentException(
                    "Firestore cannot be null."
            );
        }

        this.db = db;
    }

    // =====================================================
    // GET ALL USERS
    // =====================================================

    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        try {

            var snapshot =
                    db.collection("users")
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                User user =
                        document.toObject(
                                User.class
                        );

                if (user != null) {

                    // Firestore document ID
                    user.setUserId(
                            document.getId()
                    );

                    users.add(user);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }

    // =====================================================
    // GET FARMERS
    // =====================================================

    public List<User> getFarmers() {

        List<User> farmers =
                new ArrayList<>();

        try {

            var snapshot =
                    db.collection("users")
                            .whereEqualTo(
                                    "role",
                                    "FARMER"
                            )
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                User user =
                        document.toObject(
                                User.class
                        );

                if (user != null) {

                    user.setUserId(
                            document.getId()
                    );

                    farmers.add(user);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return farmers;
    }

    // =====================================================
    // GET BUYERS
    // =====================================================

    public List<User> getBuyers() {

        List<User> buyers =
                new ArrayList<>();

        try {

            var snapshot =
                    db.collection("users")
                            .whereEqualTo(
                                    "role",
                                    "BUYER"
                            )
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                User user =
                        document.toObject(
                                User.class
                        );

                if (user != null) {

                    user.setUserId(
                            document.getId()
                    );

                    buyers.add(user);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return buyers;
    }
}