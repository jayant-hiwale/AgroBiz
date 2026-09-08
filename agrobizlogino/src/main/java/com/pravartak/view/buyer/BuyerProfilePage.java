package com.pravartak.view.buyer;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuyerProfilePage {

        // =========================================================
        // CURRENT LOGGED-IN BUYER UID
        // =========================================================

        /*
         * This is set from LoginPage after successful Firebase login.
         *
         * IMPORTANT:
         * Firestore will use this UID as the document ID.
         */

        public static String currentBuyerUid = "";

        // =========================================================
        // BUYER PROFILE DATA
        // =========================================================

        public static String buyerName = "Buyer User";
        public static String phoneNumber = "+91 98765 43210";
        public static String email = "buyer@agrobiz.com";
        public static String location = "Maharashtra, India";
        public static String buyerType = "Wholesale Buyer";

        // Cloudinary image URL
        public static String profileImageUrl = "";

        // =========================================================
        // FIRESTORE COLLECTION
        // =========================================================

        private static final String COLLECTION_NAME = "buyers";

        // =========================================================
        // UI VARIABLES
        // =========================================================

        private ImageView profileImage;

        private Label nameLabel;
        private Label phoneLabel;
        private Label emailLabel;
        private Label locationLabel;
        private Label buyerTypeLabel;

        private Label imageStatusLabel;

        // =========================================================
        // BACKGROUND THREAD
        // =========================================================

        private final ExecutorService executor = Executors.newCachedThreadPool();

        public BuyerProfilePage() {
        }

        // =========================================================
        // PROFILE PAGE SCENE
        // =========================================================

        public Scene getProfilePageScene() {

                BorderPane out = new BorderPane();

                out.setTop(
                                new buyerTop().createBuyerTop("◎ Profile"));

                out.setBottom(
                                new Footer().createFooter());

                out.setPrefSize(
                                1368,
                                768);

                out.setStyle(
                                "-fx-background-color: #06110c;");

                BorderPane root = new BorderPane();

                out.setCenter(root);

                // =====================================================
                // HEADER
                // =====================================================

                VBox header = new VBox(4);

                header.setPadding(
                                new Insets(18, 35, 18, 35));

                header.setStyle(
                                "-fx-background-color: #024302;");

                Label title = new Label(
                                "Buyer Profile");

                title.setStyle(
                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                Label subtitle = new Label(
                                "Manage your personal and buying information.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: #7f9987;");

                header.getChildren().addAll(
                                title,
                                subtitle);

                root.setTop(header);

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                VBox mainContent = new VBox(22);

                mainContent.setPadding(
                                new Insets(30, 35, 30, 35));

                // =====================================================
                // PROFILE TOP CARD
                // =====================================================

                HBox profileCard = new HBox(22);

                profileCard.setAlignment(
                                Pos.CENTER_LEFT);

                profileCard.setPadding(
                                new Insets(20, 25, 20, 25));

                profileCard.setPrefHeight(
                                150);

                profileCard.setStyle(
                                "-fx-background-color: #024302;" +
                                                "-fx-background-radius: 15;");

                // =====================================================
                // PROFILE IMAGE
                // =====================================================

                StackPane imageContainer = createProfileImage();

                // =====================================================
                // BUYER BASIC INFORMATION
                // =====================================================

                VBox buyerInfo = new VBox(5);

                buyerInfo.setAlignment(
                                Pos.CENTER_LEFT);

                nameLabel = new Label(
                                buyerName);

                nameLabel.setStyle(
                                "-fx-font-size: 23px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                Label roleLabel = new Label(
                                "Buyer");

                roleLabel.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: #e5ffe5;");

                Label infoLabel = new Label(
                                "Manage your personal and buying information.");

                infoLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #d4f0d4;");

                imageStatusLabel = new Label("");

                imageStatusLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #d4f0d4;");

                buyerInfo.getChildren().addAll(
                                nameLabel,
                                roleLabel,
                                infoLabel,
                                imageStatusLabel);

                // =====================================================
                // EDIT BUTTON
                // =====================================================

                VBox buttonBox = new VBox(8);

                buttonBox.setAlignment(
                                Pos.CENTER);

                Button editButton = new Button(
                                "Edit Profile");

                editButton.setPrefWidth(
                                140);

                editButton.setPrefHeight(
                                40);

                editButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #024302;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7;" +
                                                "-fx-cursor: hand;");

                editButton.setOnAction(e -> {

                        BuyerEditProfilePage editPage = new BuyerEditProfilePage();

                        Stage currentStage = (Stage) editButton
                                        .getScene()
                                        .getWindow();

                        currentStage.setScene(
                                        editPage.getEditProfileScene());
                });

                buttonBox.getChildren().add(
                                editButton);

                profileCard.getChildren().addAll(
                                imageContainer,
                                buyerInfo,
                                buttonBox);

                // =====================================================
                // INFORMATION BOX
                // =====================================================

                HBox informationBox = new HBox(22);

                informationBox.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // PERSONAL INFORMATION CARD
                // =====================================================

                VBox personalCard = createInformationCard();

                Label personalTitle = createCardTitle(
                                "Personal Information");

                VBox personalDetails = new VBox(16);

                phoneLabel = createValueLabel(
                                phoneNumber);

                emailLabel = createValueLabel(
                                email);

                locationLabel = createValueLabel(
                                location);

                personalDetails.getChildren().addAll(

                                createInfoRow(
                                                "Phone Number",
                                                phoneLabel),

                                createInfoRow(
                                                "Gmail",
                                                emailLabel),

                                createInfoRow(
                                                "Location",
                                                locationLabel));

                personalCard.getChildren().addAll(
                                personalTitle,
                                personalDetails);

                // =====================================================
                // BUYER INFORMATION CARD
                // =====================================================

                VBox buyerCard = createInformationCard();

                Label buyerTitle = createCardTitle(
                                "Buyer Information");

                VBox buyerDetails = new VBox(16);

                buyerTypeLabel = createValueLabel(
                                buyerType);

                buyerDetails.getChildren().add(
                                createInfoRow(
                                                "Buyer Type",
                                                buyerTypeLabel));

                buyerCard.getChildren().addAll(
                                buyerTitle,
                                buyerDetails);

                informationBox.getChildren().addAll(
                                personalCard,
                                buyerCard);

                mainContent.getChildren().addAll(
                                profileCard,
                                informationBox);

                root.setCenter(
                                mainContent);

                Scene scene = new Scene(
                                out,
                                1368,
                                768);

                // =====================================================
                // LOAD FIREBASE PROFILE
                // =====================================================

                loadProfileFromFirebase();

                return scene;
        }

        // =========================================================
        // CREATE PROFILE IMAGE
        // =========================================================

        private StackPane createProfileImage() {

                StackPane container = new StackPane();

                container.setPrefSize(
                                105,
                                105);

                container.setMinSize(
                                105,
                                105);

                container.setMaxSize(
                                105,
                                105);

                // =====================================================
                // DARK CIRCULAR BACKGROUND
                // =====================================================

                Circle backgroundCircle = new Circle(
                                52.5);

                backgroundCircle.setStyle(
                                "-fx-fill: #092d13;");

                // =====================================================
                // IMAGE VIEW
                // =====================================================

                profileImage = new ImageView();

                profileImage.setFitWidth(
                                105);

                profileImage.setFitHeight(
                                105);

                profileImage.setPreserveRatio(
                                false);

                Circle imageClip = new Circle(
                                52.5,
                                52.5,
                                52.5);

                profileImage.setClip(
                                imageClip);

                // =====================================================
                // DEFAULT INITIAL
                // =====================================================

                Label initial = new Label(
                                "B");

                initial.setStyle(
                                "-fx-font-size: 35px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                container.getChildren().addAll(
                                backgroundCircle,
                                initial,
                                profileImage);

                // =====================================================
                // LOAD EXISTING IMAGE
                // =====================================================

                if (profileImageUrl != null
                                && !profileImageUrl.trim().isEmpty()) {

                        setProfileImage(
                                        profileImageUrl);
                }

                return container;
        }

        // =========================================================
        // SET PROFILE IMAGE
        // =========================================================

        private void setProfileImage(
                        String imageUrl) {

                if (imageUrl == null
                                || imageUrl.trim().isEmpty()) {

                        return;
                }

                try {

                        Image image = new Image(
                                        imageUrl,
                                        105,
                                        105,
                                        false,
                                        true);

                        profileImage.setImage(
                                        image);

                } catch (Exception e) {

                        System.err.println(
                                        "Unable to load profile image.");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // LOAD PROFILE FROM FIREBASE
        // =========================================================

        private void loadProfileFromFirebase() {

                // =====================================================
                // CHECK UID
                // =====================================================

                if (currentBuyerUid == null
                                || currentBuyerUid.trim().isEmpty()) {

                        System.err.println(
                                        "Buyer UID is not available.");

                        Platform.runLater(() -> {

                                if (imageStatusLabel != null) {

                                        imageStatusLabel.setText(
                                                        "Buyer authentication information unavailable.");
                                }
                        });

                        return;
                }

                executor.submit(() -> {

                        try {

                                Firestore db = FirebaseConfig.getFirestore();

                                // =================================================
                                // IMPORTANT:
                                // UID IS THE DOCUMENT ID
                                // =================================================

                                String documentId = currentBuyerUid.trim();

                                ApiFuture<DocumentSnapshot> future = db.collection(
                                                COLLECTION_NAME)
                                                .document(
                                                                documentId)
                                                .get();

                                DocumentSnapshot document = future.get();

                                if (!document.exists()) {

                                        System.out.println(
                                                        "No buyer profile found for UID: "
                                                                        + documentId);

                                        return;
                                }

                                String loadedName = getString(
                                                document,
                                                "name",
                                                buyerName);

                                String loadedPhone = getString(
                                                document,
                                                "phone",
                                                phoneNumber);

                                String loadedEmail = getString(
                                                document,
                                                "email",
                                                email);

                                String loadedLocation = getString(
                                                document,
                                                "location",
                                                location);

                                String loadedBuyerType = getString(
                                                document,
                                                "buyerType",
                                                buyerType);

                                String loadedImageUrl = getString(
                                                document,
                                                "profileImageUrl",
                                                profileImageUrl);

                                // =================================================
                                // UPDATE LOCAL VALUES
                                // =================================================

                                buyerName = loadedName;

                                phoneNumber = loadedPhone;

                                email = loadedEmail;

                                location = loadedLocation;

                                buyerType = loadedBuyerType;

                                profileImageUrl = loadedImageUrl;

                                // =================================================
                                // UPDATE UI
                                // =================================================

                                Platform.runLater(() -> {

                                        if (nameLabel != null) {

                                                nameLabel.setText(
                                                                buyerName);
                                        }

                                        if (phoneLabel != null) {

                                                phoneLabel.setText(
                                                                phoneNumber);
                                        }

                                        if (emailLabel != null) {

                                                emailLabel.setText(
                                                                email);
                                        }

                                        if (locationLabel != null) {

                                                locationLabel.setText(
                                                                location);
                                        }

                                        if (buyerTypeLabel != null) {

                                                buyerTypeLabel.setText(
                                                                buyerType);
                                        }

                                        if (profileImageUrl != null
                                                        && !profileImageUrl
                                                                        .trim()
                                                                        .isEmpty()) {

                                                setProfileImage(
                                                                profileImageUrl);
                                        }
                                });

                        } catch (Exception e) {

                                System.err.println(
                                                "Unable to load buyer profile from Firebase.");

                                e.printStackTrace();
                        }
                });
        }

        // =========================================================
        // GET FIRESTORE STRING
        // =========================================================

        private String getString(
                        DocumentSnapshot document,
                        String field,
                        String defaultValue) {

                String value = document.getString(
                                field);

                if (value == null
                                || value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value;
        }

        // =========================================================
        // INFORMATION CARD
        // =========================================================

        private VBox createInformationCard() {

                VBox card = new VBox(20);

                card.setPadding(
                                new Insets(25));

                card.setPrefWidth(
                                630);

                card.setMinHeight(
                                280);

                card.setStyle(
                                "-fx-background-color: #024302;" +
                                                "-fx-background-radius: 15;");

                return card;
        }

        // =========================================================
        // CARD TITLE
        // =========================================================

        private Label createCardTitle(
                        String text) {

                Label label = new Label(
                                text);

                label.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                return label;
        }

        // =========================================================
        // INFORMATION ROW
        // =========================================================

        private VBox createInfoRow(
                        String title,
                        Label value) {

                VBox box = new VBox(4);

                Label titleLabel = new Label(
                                title);

                titleLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #d5efd5;");

                box.getChildren().addAll(
                                titleLabel,
                                value);

                return box;
        }

        // =========================================================
        // VALUE LABEL
        // =========================================================

        private Label createValueLabel(
                        String text) {

                Label label = new Label(
                                text);

                label.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-text-fill: white;");

                return label;
        }
}
