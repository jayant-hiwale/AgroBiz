package com.pravartak.view.admin.marketplace;

import com.pravartak.model.farmer_model.FarmerProfile;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FarmerDetailsDialog {

    private final FarmerProfile farmer;

    public FarmerDetailsDialog(
            FarmerProfile farmer) {

        this.farmer = farmer;
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

        root.setPrefWidth(
                420
        );

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        // =================================================
        // TITLE
        // =================================================

        Label title =
                new Label(
                        "Farmer Details"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // FARMER NAME
        // =================================================

        String name =
                getString(
                        "getFullName",
                        "getName"
                );

        Label nameLabel =
                createValue(
                        "Name",
                        name
                );

        // =================================================
        // EMAIL
        // =================================================

        String email =
                getString(
                        "getEmail"
                );

        Label emailLabel =
                createValue(
                        "Email",
                        email
                );

        // =================================================
        // PHONE
        // =================================================

        String phone =
                getString(
                        "getPhone",
                        "getPhoneNumber",
                        "getMobile"
                );

        Label phoneLabel =
                createValue(
                        "Phone",
                        phone
                );

        // =================================================
        // LOCATION
        // =================================================

        String location =
                getString(
                        "getLocation",
                        "getAddress"
                );

        Label locationLabel =
                createValue(
                        "Location",
                        location
                );

        // =================================================
        // FARMER ID
        // =================================================

        Label farmerIdLabel =
                createValue(
                        "Farmer ID",
                        String.valueOf(
                                farmer.getFarmerId()
                        )
                );

        // =================================================
        // CLOSE
        // =================================================

        Button close =
                new Button(
                        "Close"
                );

        close.setPrefWidth(
                100
        );

        close.setStyle(
                "-fx-background-color:#245D35;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-background-radius:5;" +
                "-fx-padding:8 15;" +
                "-fx-cursor:hand;"
        );

        close.setOnAction(
                e -> stage.close()
        );

        HBox buttonBox =
                new HBox(
                        close
                );

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        root.getChildren().addAll(
                title,
                nameLabel,
                emailLabel,
                phoneLabel,
                locationLabel,
                farmerIdLabel,
                buttonBox
        );

        Scene scene =
                new Scene(
                        root
                );

        stage.setScene(
                scene
        );

        stage.showAndWait();
    }

    // =====================================================
    // CREATE VALUE
    // =====================================================

    private Label createValue(
            String label,
            String value) {

        Label result =
                new Label(
                        label + ": " + safe(value)
                );

        result.setWrapText(
                true
        );

        result.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        return result;
    }

    // =====================================================
    // GET STRING
    // =====================================================

    private String getString(
            String... methodNames) {

        for (String methodName :
                methodNames) {

            try {

                var method =
                        farmer.getClass()
                                .getMethod(
                                        methodName
                                );

                Object value =
                        method.invoke(
                                farmer
                        );

                if (value != null &&
                        !value.toString()
                                .trim()
                                .isEmpty()) {

                    return value.toString();
                }

            } catch (Exception ignored) {
            }
        }

        return "Not provided";
    }

    // =====================================================
    // SAFE
    // =====================================================

    private String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }
}