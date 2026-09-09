package com.pravartak.dao.admindao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.admin.Scheme;

public class SchemeDAO {

        // =========================================================
        // FIRESTORE COLLECTION
        // =========================================================

        private static final String COLLECTION = "schemes";

        // =========================================================
        // FIRESTORE
        // =========================================================

        private Firestore getFirestore() {

                return FirebaseConfig.getFirestore();
        }

        // =========================================================
        // ADD SCHEME
        // =========================================================

        public boolean addScheme(
                        Scheme scheme) {

                try {

                        if (scheme == null) {

                                System.out.println(
                                                "Scheme is null.");

                                return false;
                        }

                        // -------------------------------------------------
                        // CREATE ID IF NEEDED
                        // -------------------------------------------------

                        String schemeId = scheme.getSchemeId();

                        if (schemeId == null ||
                                        schemeId.trim().isEmpty()) {

                                schemeId = UUID.randomUUID()
                                                .toString();

                                scheme.setSchemeId(
                                                schemeId);
                        }

                        // -------------------------------------------------
                        // FIRESTORE
                        // -------------------------------------------------

                        Firestore db = getFirestore();

                        DocumentReference document = db.collection(COLLECTION)
                                        .document(schemeId);

                        // -------------------------------------------------
                        // SAVE
                        // -------------------------------------------------

                        document
                                        .set(scheme)
                                        .get();

                        System.out.println(
                                        "Scheme saved successfully.");

                        System.out.println(
                                        "Scheme ID: " + schemeId);

                        return true;

                } catch (Exception e) {

                        System.err.println(
                                        "Error adding scheme:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // GET ALL SCHEMES
        // =========================================================

        public List<Scheme> getAllSchemes() {

                List<Scheme> schemes = new ArrayList<>();

                try {

                        Firestore db = getFirestore();

                        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION)
                                        .orderBy(
                                                        "schemeName",
                                                        Query.Direction.ASCENDING)
                                        .get();

                        QuerySnapshot snapshot = future.get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                Scheme scheme = document.toObject(
                                                Scheme.class);

                                if (scheme == null) {
                                        continue;
                                }

                                // -------------------------------------------------
                                // SAFETY: USE FIRESTORE DOCUMENT ID
                                // -------------------------------------------------

                                if (scheme.getSchemeId() == null ||
                                                scheme.getSchemeId()
                                                                .trim()
                                                                .isEmpty()) {

                                        scheme.setSchemeId(
                                                        document.getId());
                                }

                                schemes.add(scheme);
                        }

                        System.out.println(
                                        "Schemes loaded: "
                                                        + schemes.size());

                } catch (Exception e) {

                        System.err.println(
                                        "Error loading schemes:");

                        e.printStackTrace();
                }

                return schemes;
        }

        // =========================================================
        // GET ONE SCHEME
        // =========================================================

        public Scheme getScheme(
                        String schemeId) {

                try {

                        if (schemeId == null ||
                                        schemeId.trim().isEmpty()) {

                                return null;
                        }

                        Firestore db = getFirestore();

                        DocumentSnapshot document = db.collection(COLLECTION)
                                        .document(
                                                        schemeId.trim())
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                return null;
                        }

                        Scheme scheme = document.toObject(
                                        Scheme.class);

                        if (scheme != null) {

                                if (scheme.getSchemeId() == null ||
                                                scheme.getSchemeId()
                                                                .trim()
                                                                .isEmpty()) {

                                        scheme.setSchemeId(
                                                        document.getId());
                                }
                        }

                        return scheme;

                } catch (Exception e) {

                        System.err.println(
                                        "Error getting scheme:");

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // UPDATE SCHEME
        // =========================================================

        public boolean updateScheme(
                        Scheme scheme) {

                try {

                        if (scheme == null) {

                                return false;
                        }

                        String schemeId = scheme.getSchemeId();

                        if (schemeId == null ||
                                        schemeId.trim().isEmpty()) {

                                return false;
                        }

                        Firestore db = getFirestore();

                        db.collection(COLLECTION)
                                        .document(
                                                        schemeId.trim())
                                        .set(scheme)
                                        .get();

                        System.out.println(
                                        "Scheme updated successfully: "
                                                        + schemeId);

                        return true;

                } catch (Exception e) {

                        System.err.println(
                                        "Error updating scheme:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // DELETE SCHEME
        // =========================================================

        public boolean deleteScheme(
                        String schemeId) {

                try {

                        if (schemeId == null ||
                                        schemeId.trim().isEmpty()) {

                                return false;
                        }

                        Firestore db = getFirestore();

                        db.collection(COLLECTION)
                                        .document(
                                                        schemeId.trim())
                                        .delete()
                                        .get();

                        System.out.println(
                                        "Scheme deleted successfully: "
                                                        + schemeId);

                        return true;

                } catch (Exception e) {

                        System.err.println(
                                        "Error deleting scheme:");

                        e.printStackTrace();

                        return false;
                }
        }
}