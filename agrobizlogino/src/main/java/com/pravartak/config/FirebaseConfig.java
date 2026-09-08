

package com.pravartak.config;

import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

public class FirebaseConfig {

    private static FirebaseApp firebaseApp;

    static {
        initializeFirebase();
    }

    private static void initializeFirebase() {

        try {

            // Prevent duplicate initialization
            if (!FirebaseApp.getApps().isEmpty()) {
                firebaseApp = FirebaseApp.getInstance();
                return;
            }

            InputStream serviceAccount =
                    FirebaseConfig.class
                            .getClassLoader()
                            .getResourceAsStream("agrobiz_authentication.json");

            if (serviceAccount == null) {

                throw new RuntimeException(
                        "Firebase service account file not found!\n"
                        + "Make sure agrobiz_authentication.json is inside:\n"
                        + "src/main/resources/");
            }

            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(serviceAccount))
                            .build();

            firebaseApp = FirebaseApp.initializeApp(options);

            System.out.println("Firebase initialized successfully.");

        } catch (Exception e) {

            System.err.println("Firebase initialization failed.");
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {

        if (firebaseApp == null) {
            throw new IllegalStateException(
                    "Firebase was not initialized.");
        }

        return FirestoreClient.getFirestore(firebaseApp);
    }
}