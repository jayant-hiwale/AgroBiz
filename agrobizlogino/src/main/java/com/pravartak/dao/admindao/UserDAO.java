package com.pravartak.dao.admindao;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.pravartak.model.admin.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

        private final Firestore db;

        public UserDAO(Firestore db) {

                if (db == null) {
                        throw new IllegalArgumentException(
                                        "Firestore cannot be null.");
                }

                this.db = db;
        }

        // =====================================================
        // GET ALL USERS
        // =====================================================

        public List<User> getAllUsers() {

                List<User> users = new ArrayList<>();

                try {

                        QuerySnapshot snapshot = db.collection("users")
                                        .get()
                                        .get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                User user = document.toObject(
                                                User.class);

                                if (user != null) {

                                        // -------------------------------------------------
                                        // FIRESTORE DOCUMENT ID
                                        // -------------------------------------------------

                                        user.setUserId(
                                                        document.getId());

                                        // -------------------------------------------------
                                        // CREATED DATE
                                        //
                                        // First use the createdAt field.
                                        // If old document doesn't have createdAt,
                                        // use Firestore document creation time.
                                        // -------------------------------------------------

                                        if (user.getCreatedAt() == null) {

                                                Timestamp createTime = document.getCreateTime();

                                                if (createTime != null) {

                                                        user.setCreatedAt(
                                                                        createTime);
                                                }
                                        }

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

                List<User> farmers = new ArrayList<>();

                try {

                        QuerySnapshot snapshot = db.collection("users")
                                        .whereEqualTo(
                                                        "role",
                                                        "FARMER")
                                        .get()
                                        .get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                User user = document.toObject(
                                                User.class);

                                if (user != null) {

                                        // -------------------------------------------------
                                        // FIRESTORE DOCUMENT ID
                                        // -------------------------------------------------

                                        user.setUserId(
                                                        document.getId());

                                        // -------------------------------------------------
                                        // CREATED DATE FALLBACK
                                        // -------------------------------------------------

                                        if (user.getCreatedAt() == null) {

                                                Timestamp createTime = document.getCreateTime();

                                                if (createTime != null) {

                                                        user.setCreatedAt(
                                                                        createTime);
                                                }
                                        }

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

                List<User> buyers = new ArrayList<>();

                try {

                        QuerySnapshot snapshot = db.collection("users")
                                        .whereEqualTo(
                                                        "role",
                                                        "BUYER")
                                        .get()
                                        .get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                User user = document.toObject(
                                                User.class);

                                if (user != null) {

                                        // -------------------------------------------------
                                        // FIRESTORE DOCUMENT ID
                                        // -------------------------------------------------

                                        user.setUserId(
                                                        document.getId());

                                        // -------------------------------------------------
                                        // CREATED DATE FALLBACK
                                        // -------------------------------------------------

                                        if (user.getCreatedAt() == null) {

                                                Timestamp createTime = document.getCreateTime();

                                                if (createTime != null) {

                                                        user.setCreatedAt(
                                                                        createTime);
                                                }
                                        }

                                        buyers.add(user);
                                }
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return buyers;
        }

        // =====================================================
        // DELETE USER
        // =====================================================

        public boolean deleteUser(User user) {

                if (user == null) {

                        return false;
                }

                if (user.getUserId() == null
                                ||
                                user.getUserId().trim().isEmpty()) {

                        return false;
                }

                try {

                        db.collection("users")
                                        .document(
                                                        user.getUserId())
                                        .delete()
                                        .get();

                        return true;

                } catch (Exception e) {

                        e.printStackTrace();

                        return false;
                }
        }
}