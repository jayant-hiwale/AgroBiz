package com.pravartak.dao.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.pravartak.controller.admincontroller.SchemeController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import com.pravartak.model.adminmodel.Scheme;

public class SchemeDAO {

    private static final String COLLECTION = "schemes";

    // =========================================================
    // GET FIRESTORE
    // =========================================================

    private Firestore getFirestore() {

        return FirestoreClient.getFirestore();
    }

    // =========================================================
    // ADD SCHEME
    // =========================================================

    public boolean addScheme(Scheme scheme) {

        try {

            Firestore db = getFirestore();

            String schemeId = scheme.getSchemeId();

            if (schemeId == null ||
                    schemeId.trim().isEmpty()) {

                schemeId =
                        UUID.randomUUID().toString();

                scheme.setSchemeId(schemeId);
            }

            DocumentReference document =
                    db.collection(COLLECTION)
                            .document(schemeId);

            document.set(scheme).get();

            return true;

        } catch (Exception e) {

            System.err.println(
                    "Error adding scheme: "
                            + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL SCHEMES
    // =========================================================

    public List<Scheme> getAllSchemes() {

        List<Scheme> schemes =
                new ArrayList<>();

        try {

            Firestore db =
                    getFirestore();

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                            .orderBy(
                                    "schemeName",
                                    Query.Direction.ASCENDING)
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                Scheme scheme =
                        document.toObject(
                                Scheme.class);

                if (scheme != null) {

                    if (scheme.getSchemeId() == null ||
                            scheme.getSchemeId().isEmpty()) {

                        scheme.setSchemeId(
                                document.getId());
                    }

                    schemes.add(scheme);
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Error loading schemes: "
                            + e.getMessage());

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

            Firestore db =
                    getFirestore();

            DocumentSnapshot document =
                    db.collection(COLLECTION)
                            .document(schemeId)
                            .get()
                            .get();

            if (document.exists()) {

                Scheme scheme =
                        document.toObject(
                                Scheme.class);

                if (scheme != null) {

                    if (scheme.getSchemeId() == null ||
                            scheme.getSchemeId().isEmpty()) {

                        scheme.setSchemeId(
                                document.getId());
                    }
                }

                return scheme;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateScheme(
            Scheme scheme) {

        try {

            if (scheme == null ||
                    scheme.getSchemeId() == null ||
                    scheme.getSchemeId().trim().isEmpty()) {

                return false;
            }

            Firestore db =
                    getFirestore();

            db.collection(COLLECTION)
                    .document(
                            scheme.getSchemeId())
                    .set(scheme)
                    .get();

            return true;

        } catch (Exception e) {

            System.err.println(
                    "Error updating scheme: "
                            + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteScheme(
            String schemeId) {

        try {

            if (schemeId == null ||
                    schemeId.trim().isEmpty()) {

                return false;
            }

            Firestore db =
                    getFirestore();

            db.collection(COLLECTION)
                    .document(schemeId)
                    .delete()
                    .get();

            return true;

        } catch (Exception e) {

            System.err.println(
                    "Error deleting scheme: "
                            + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }
}