package com.pravartak.dao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.UserModel;

public class UserDAO {

    private final Firestore db;

    public UserDAO() {

        db = FirebaseConfig.getFirestore();
    }

    public boolean saveUser(UserModel user) {

        try {

            db.collection("users")
                    .document(user.getUid())
                    .set(user.toMap())
                    .get();

            System.out.println("User saved to Firestore.");

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public UserModel getUserByUid(String uid) {

        try {

            DocumentSnapshot document =
                    db.collection("users")
                            .document(uid)
                            .get()
                            .get();

            if (!document.exists()) {

                System.out.println("User document not found.");

                return null;
            }

            String fullName =
                    document.getString("fullName");

            String email =
                    document.getString("email");

            String role =
                    document.getString("role");

            return new UserModel(
                    uid,
                    fullName,
                    email,
                    role);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}