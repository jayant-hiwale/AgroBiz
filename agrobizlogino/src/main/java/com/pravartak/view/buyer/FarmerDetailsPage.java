package com.pravartak.view.buyer;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.farmer_model.FarmerProfile;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FarmerDetailsPage {

    private final int farmerId;
    private final String productName;

    public FarmerDetailsPage(
            int farmerId,
            String productName) {

        this.farmerId = farmerId;
        this.productName = productName;
    }

    // =========================================================
    // SHOW PAGE
    // =========================================================

    public void show() {

        Stage stage =
                new Stage();

        stage.initModality(
                Modality.APPLICATION_MODAL
        );

        stage.setTitle(
                "Farmer Details"
        );

        stage.setWidth(
                600
        );

        stage.setHeight(
                700
        );

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Farmer Details"
                );

        title.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:27px;" +
                "-fx-font-weight:bold;"
        );

        title.setPadding(
                new Insets(
                        25,
                        30,
                        15,
                        30
                )
        );

        root.setTop(
                title
        );


        // =====================================================
        // DETAILS
        // =====================================================

        VBox details =
                createDetails();


        ScrollPane scroll =
                new ScrollPane(
                        details
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scroll.setStyle(
                "-fx-background-color:#080C0D;" +
                "-fx-background:#080C0D;" +
                "-fx-control-inner-background:#080C0D;"
        );


        root.setCenter(
                scroll
        );


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root
                );

        stage.setScene(
                scene
        );

        stage.showAndWait();
    }


    // =========================================================
    // CREATE DETAILS
    // =========================================================

    private VBox createDetails() {

        VBox main =
                new VBox(
                        18
                );

        main.setPadding(
                new Insets(
                        10,
                        25,
                        30,
                        25
                )
        );


        try {

            // =================================================
            // FIREBASE
            // =================================================

            Firestore db =
                    FirebaseConfig.getFirestore();


            var document =
                    db.collection("farmers")
                            .document(
                                    String.valueOf(
                                            farmerId
                                    )
                            )
                            .get()
                            .get();


            if (!document.exists()) {

                return createError(
                        "Farmer details not found.\n\n"
                        + "Farmer ID: "
                        + farmerId
                );
            }


            FarmerProfile farmer =
                    document.toObject(
                            FarmerProfile.class
                    );


            if (farmer == null) {

                return createError(
                        "Farmer profile could not be loaded."
                );
            }


            // =================================================
            // PROFILE HEADER
            // =================================================

            VBox profileCard =
                    new VBox(
                            12
                    );

            profileCard.setAlignment(
                    Pos.CENTER
            );

            profileCard.setPadding(
                    new Insets(
                            25
                    )
            );

            profileCard.setStyle(
                    "-fx-background-color:#0D1512;" +
                    "-fx-border-color:#26382B;" +
                    "-fx-border-radius:14;" +
                    "-fx-background-radius:14;"
            );


            // =================================================
            // IMAGE
            // =================================================

            StackPane imageContainer =
                    createProfileImage(
                            farmer.getImageBase64()
                    );


            profileCard.getChildren()
                    .add(
                            imageContainer
                    );


            // =================================================
            // FARMER NAME
            // =================================================

            String farmerName =
                    safeValue(
                            farmer.getName()
                    );


            Label name =
                    new Label(
                            farmerName
                    );

            name.setTextFill(
                    Color.WHITE
            );

            name.setFont(
                    javafx.scene.text.Font.font(
                            "Arial",
                            javafx.scene.text.FontWeight.BOLD,
                            23
             ) );


            name.setWrapText(
                    true
            );


            Label farmerRole =
                    new Label(
                            "Farmer / Farm Owner"
                    );

            farmerRole.setTextFill(
                    Color.web(
                            "#68D34A"
                    )
            );

            farmerRole.setFont(
                    javafx.scene.text.Font.font(
                            "Arial",
                            13
                    )
            );


            profileCard.getChildren()
                    .addAll(
                            name,
                            farmerRole
                    );


            // =================================================
            // PRODUCT CARD
            // =================================================

            VBox productCard =
                    createInfoCard(
                            "🌾 Product Information"
                    );


            productCard.getChildren()
                    .add(
                            createField(
                                    "Product",
                                    productName
                            )
                    );


            // =================================================
            // PERSONAL INFORMATION
            // =================================================

            VBox personalCard =
                    createInfoCard(
                            "👤 Personal Information"
                    );


            personalCard.getChildren()
                    .addAll(

                            createField(
                                    "📞 Phone",
                                    farmer.getPhone()
                            ),

                            createField(
                                    "✉ Email",
                                    farmer.getEmail()
                            ),

                            createField(
                                    "🏠 Address",
                                    farmer.getAddress()
                            ),

                            createField(
                                    "📍 Village",
                                    farmer.getVillage()
                            ),

                            createField(
                                    "📍 District",
                                    farmer.getDistrict()
                            ),

                            createField(
                                    "📍 State",
                                    farmer.getState()
                            )
                    );


            // =================================================
            // FARM INFORMATION
            // =================================================

            VBox farmCard =
                    createInfoCard(
                            "🌱 Farm Information"
                    );


            farmCard.getChildren()
                    .addAll(

                            createField(
                                    "Farm Name",
                                    farmer.getFarmName()
                            ),

                            createField(
                                    "Farm Area",
                                    farmer.getFarmArea()
                            ),

                            createField(
                                    "Farming Type",
                                    farmer.getFarmingType()
                            ),

                            createField(
                                    "Primary Crops",
                                    farmer.getPrimaryCrops()
                            )
                    );


            // =================================================
            // ADD EVERYTHING
            // =================================================

            main.getChildren()
                    .addAll(
                            profileCard,
                            productCard,
                            personalCard,
                            farmCard
                    );


        } catch (Exception e) {

            e.printStackTrace();

            main.getChildren()
                    .add(
                            createError(
                                    "Unable to load farmer details.\n\n"
                                    + e.getMessage()
                            )
                    );
        }


        return main;
    }


    // =========================================================
    // PROFILE IMAGE
    // =========================================================

    private StackPane createProfileImage(
            String imageBase64) {

        StackPane container =
                new StackPane();

        container.setPrefSize(
                150,
                150
        );

        container.setMinSize(
                150,
                150
        );

        container.setMaxSize(
                150,
                150
        );

        container.setStyle(
                "-fx-background-color:#1B2520;" +
                "-fx-background-radius:100;"
        );


        // =====================================================
        // NO IMAGE
        // =====================================================

        if (imageBase64 == null ||
                imageBase64.trim().isEmpty()) {

            Label placeholder =
                    new Label(
                            "👨‍🌾"
                    );

            placeholder.setStyle(
                    "-fx-font-size:60px;"
            );

            container.getChildren()
                    .add(
                            placeholder
                    );

            return container;
        }


        try {

            // =================================================
            // DECODE BASE64
            // =================================================

            byte[] imageBytes =
                    Base64.getDecoder()
                            .decode(
                                    imageBase64
                            );


            Image image =
                    new Image(
                            new ByteArrayInputStream(
                                    imageBytes
                            )
                    );


            if (image.isError()) {

                throw new Exception(
                        "Invalid profile image"
                );
            }


            // =================================================
            // IMAGE VIEW
            // =================================================

            ImageView imageView =
                    new ImageView(
                            image
                    );


            imageView.setFitWidth(
                    150
            );

            imageView.setFitHeight(
                    150
            );

            imageView.setPreserveRatio(
                    false
            );


            // =================================================
            // CIRCLE CLIP
            // =================================================

            Circle clip =
                    new Circle(
                            75,
                            75,
                            75
                    );


            imageView.setClip(
                    clip
            );


            container.getChildren()
                    .add(
                            imageView
                    );


        } catch (Exception e) {

            e.printStackTrace();


            Label placeholder =
                    new Label(
                            "👨‍🌾"
                    );

            placeholder.setStyle(
                    "-fx-font-size:60px;"
            );


            container.getChildren()
                    .add(
                            placeholder
                    );
        }


        return container;
    }


    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private VBox createInfoCard(
            String titleText) {

        VBox card =
                new VBox(
                        12
                );

        card.setPadding(
                new Insets(
                        20
                )
        );

        card.setStyle(
                "-fx-background-color:#0D1512;" +
                "-fx-border-color:#26382B;" +
                "-fx-border-radius:14;" +
                "-fx-background-radius:14;"
        );


        Label title =
                new Label(
                        titleText
                );

        title.setTextFill(
                Color.web(
                        "#68D34A"
                )
        );

        title.setFont(
                javafx.scene.text.Font.font(
                        "Arial",
                        javafx.scene.text.FontWeight.BOLD,
                        17
                )
        );


        card.getChildren()
                .add(
                        title
                );


        return card;
    }


    // =========================================================
    // FIELD
    // =========================================================

    private HBox createField(
            String title,
            String value) {

        HBox row =
                new HBox(
                        12
                );

        row.setAlignment(
                Pos.TOP_LEFT
        );

        row.setPadding(
                new Insets(
                        8,
                        5,
                        8,
                        5
                )
        );


        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setPrefWidth(
                130
        );

        titleLabel.setMinWidth(
                130
        );

        titleLabel.setTextFill(
                Color.web(
                        "#A9B7AC"
                )
        );

        titleLabel.setFont(
                javafx.scene.text.Font.font(
                        "Arial",
                        javafx.scene.text.FontWeight.BOLD,
                        13
                )
        );


        Label valueLabel =
                new Label(
                        safeValue(value)
                );

        valueLabel.setTextFill(
                Color.WHITE
        );

        valueLabel.setFont(
                javafx.scene.text.Font.font(
                        "Arial",
                        14
                )
        );


        valueLabel.setWrapText(
                true
        );


        HBox.setHgrow(
                valueLabel,
                Priority.ALWAYS
        );


        row.getChildren()
                .addAll(
                        titleLabel,
                        valueLabel
                );


        return row;
    }


    // =========================================================
    // ERROR
    // =========================================================

    private VBox createError(
            String message) {

        VBox box =
                new VBox();

        box.setPadding(
                new Insets(
                        25
                )
        );

        box.setAlignment(
                Pos.CENTER
        );

        box.setStyle(
                "-fx-background-color:#0D1512;" +
                "-fx-border-color:#26382B;" +
                "-fx-border-radius:14;" +
                "-fx-background-radius:14;"
        );


        Label error =
                new Label(
                        message
                );

        error.setTextFill(
                Color.web(
                        "#FF6B6B"
                )
        );

        error.setFont(
                javafx.scene.text.Font.font(
                        "Arial",
                        14
                )
        );

        error.setWrapText(
                true
        );


        box.getChildren()
                .add(
                        error
                );


        return box;
    }


    // =========================================================
    // SAFE VALUE
    // =========================================================

    private String safeValue(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }
}