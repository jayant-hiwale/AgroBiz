package com.pravartak.config;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {

    private static Firestore firestore;

    private static final String SERVICE_ACCOUNT = "agrobizlogino\\src\\main\\resources\\agrobiz_authentication.json";


    public static synchronized Firestore getFirestore() {

        if (firestore != null) {
            return firestore;
        }

        try {

            // =====================================================
            // CHECK IF FIREBASE IS ALREADY INITIALIZED
            // =====================================================

            if (FirebaseApp.getApps().isEmpty()) {

                FileInputStream serviceAccount = new FileInputStream(SERVICE_ACCOUNT);

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(
                                GoogleCredentials.fromStream(
                                        serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);

                serviceAccount.close();

                System.out.println("Firebase initialized successfully.");

            } else {

                System.out.println("Firebase was already initialized.");
            }

            // =====================================================
            // GET FIRESTORE
            // =====================================================

            firestore = FirestoreClient.getFirestore();

            System.out.println("Firestore connection successful.");

            return firestore;

        } catch (Exception e) {

            System.err.println("Firebase initialization failed.");

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to initialize Firebase/Firestore.",
                    e);
        }
    }
}