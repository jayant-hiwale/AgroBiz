package com.pravartak.view.buyer;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.farmer_model.FarmerProfile;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
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

    public void show() {

        Stage stage =
                new Stage();

        stage.initModality(
                Modality.APPLICATION_MODAL
        );

        stage.setTitle(
                "Farmer Details"
        );

        VBox root =
                new VBox(15);

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        Label title =
                new Label(
                        "Farmer Details"
                );

        title.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;"
        );

        VBox details =
                createDetails();

        root.getChildren()
                .addAll(
                        title,
                        details
                );

        Scene scene =
                new Scene(
                        root,
                        500,
                        550
                );

        stage.setScene(
                scene
        );

        stage.showAndWait();
    }

    private VBox createDetails() {

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(20)
        );

        box.setStyle(
                "-fx-background-color:#0D1213;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        try {

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

            FarmerProfile farmer =
                    document.toObject(
                            FarmerProfile.class
                    );

            if (farmer == null) {

                Label error =
                        new Label(
                                "Farmer details not found."
                        );

                error.setTextFill(
                        javafx.scene.paint.Color.RED
                );

                box.getChildren()
                        .add(error);

                return box;
            }

            Label name =
                    createField(
                            "👨‍🌾 Farmer",
                            farmer.getName()
                    );

            Label product =
                    createField(
                            "🌾 Product",
                            productName
                    );

            Label phone =
                    createField(
                            "📞 Phone",
                            farmer.getPhone()
                    );

            Label email =
                    createField(
                            "✉ Email",
                            farmer.getEmail()
                    );

            Label address =
                    createField(
                            "🏠 Address",
                            farmer.getAddress()
                    );

            Label village =
                    createField(
                            "Village",
                            farmer.getVillage()
                    );

            Label district =
                    createField(
                            "District",
                            farmer.getDistrict()
                    );

            Label state =
                    createField(
                            "State",
                            farmer.getState()
                    );

            box.getChildren()
                    .addAll(
                            name,
                            product,
                            phone,
                            email,
                            address,
                            village,
                            district,
                            state
                    );

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load farmer details."
                    );

            error.setTextFill(
                    javafx.scene.paint.Color.RED
            );

            box.getChildren()
                    .add(error);
        }

        return box;
    }

    private Label createField(
            String title,
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            value = "Not provided";
        }

        Label label =
                new Label(
                        title
                        + "\n"
                        + value
                );

        label.setWrapText(
                true
        );

        label.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:14px;" +
                "-fx-padding:5;"
        );

        return label;
    }
}