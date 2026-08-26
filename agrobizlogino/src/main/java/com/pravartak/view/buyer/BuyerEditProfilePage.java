package com.pravartak.view.buyer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuyerEditProfilePage {

   
    private TextField nameField;
    private TextField phoneField;
    private TextField emailField;
    private TextField locationField;

    private ComboBox<String> buyerTypeBox;

    public BuyerEditProfilePage() {
    }

    // =========================================================
    // EDIT PROFILE SCENE
    // =========================================================

    public Scene getEditProfileScene() {

        BorderPane root =
                new BorderPane();

        root.setPrefSize(
                1368,
                768
        );

        root.setStyle(
                "-fx-background-color: #06110c;"
        );

        // =====================================================
        // HEADER
        // =====================================================

        VBox header =
                new VBox(4);

        header.setPadding(
                new Insets(18, 35, 18, 35)
        );

        header.setStyle(
                "-fx-background-color: #0b2613;"
        );

        Label title =
                new Label("Edit Buyer Profile");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label subtitle =
                new Label(
                        "Update your personal and buying information."
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #7f9987;"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        root.setTop(header);

        // =====================================================
        // FORM CARD
        // =====================================================

        VBox card =
                new VBox(25);

        card.setMaxWidth(850);

        card.setPadding(
                new Insets(30)
        );

        card.setStyle(
                "-fx-background-color: #007d00;" +
                "-fx-background-radius: 15;"
        );

        // =====================================================
        // FORM GRID
        // =====================================================

        GridPane form =
                new GridPane();

        form.setHgap(25);
        form.setVgap(18);

        // =====================================================
        // NAME
        // =====================================================

        Label nameLabel =
                createLabel("Full Name");

        nameField =
                createTextField(
                        BuyerProfilePage.buyerName
                );

        // =====================================================
        // PHONE
        // =====================================================

        Label phoneLabel =
                createLabel("Phone Number");

        phoneField =
                createTextField(
                        BuyerProfilePage.phoneNumber
                );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                createLabel("Gmail");

        emailField =
                createTextField(
                        BuyerProfilePage.email
                );

        // =====================================================
        // LOCATION
        // =====================================================

        Label locationLabel =
                createLabel("Location");

        locationField =
                createTextField(
                        BuyerProfilePage.location
                );

        // =====================================================
        // BUYER TYPE
        // =====================================================

        Label buyerTypeLabel =
                createLabel("Buyer Type");

        buyerTypeBox =
                new ComboBox<>();

        buyerTypeBox.getItems().addAll(
                "Wholesale Buyer",
                "Retail Buyer",
                "Regular Buyer",
                "Local Buyer"
        );

        buyerTypeBox.setValue(
                BuyerProfilePage.buyerType
        );

        buyerTypeBox.setPrefWidth(350);
        buyerTypeBox.setPrefHeight(40);

        buyerTypeBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;"
        );

        // =====================================================
        // ADD TO GRID
        // =====================================================

        form.add(
                nameLabel,
                0,
                0
        );

        form.add(
                nameField,
                0,
                1
        );

        form.add(
                phoneLabel,
                1,
                0
        );

        form.add(
                phoneField,
                1,
                1
        );

        form.add(
                emailLabel,
                0,
                2
        );

        form.add(
                emailField,
                0,
                3
        );

        form.add(
                locationLabel,
                1,
                2
        );

        form.add(
                locationField,
                1,
                3
        );

        form.add(
                buyerTypeLabel,
                0,
                4
        );

        form.add(
                buyerTypeBox,
                0,
                5
        );

        // =====================================================
        // BUTTON BOX
        // =====================================================

        HBox buttonBox =
                new HBox(15);

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // CANCEL BUTTON
        // =====================================================

        Button cancelButton =
                new Button("Cancel");

        cancelButton.setPrefWidth(120);
        cancelButton.setPrefHeight(40);

        cancelButton.setStyle(
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

       cancelButton.setOnAction(e -> {

    BuyerProfilePage profilePage =
            new BuyerProfilePage();

    Stage currentStage =
            (Stage) cancelButton.getScene().getWindow();

    currentStage.setScene(
            profilePage.getProfilePageScene()
    );
});

        // =====================================================
        // SAVE BUTTON
        // =====================================================

        Button saveButton =
                new Button("Save Profile");

        saveButton.setPrefWidth(140);
        saveButton.setPrefHeight(40);

        saveButton.setStyle(
                "-fx-background-color: #063b0f;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

//         saveButton.setOnAction(e -> {

//     BuyerProfilePage.buyerName =
//             nameField.getText();

//     BuyerProfilePage.phoneNumber =
//             phoneField.getText();

//     BuyerProfilePage.email =
//             emailField.getText();

//     BuyerProfilePage.location =
//             locationField.getText();

//     BuyerProfilePage.buyerType =
//             buyerTypeBox.getValue();

//     BuyerProfilePage profilePage =
//             new BuyerProfilePage();

//     saveButton.getScene().setRoot(
//             profilePage.getProfilePageScene().getRoot()
//     );
// });
saveButton.setOnAction(e -> {

    BuyerProfilePage.buyerName =
            nameField.getText();

    BuyerProfilePage.phoneNumber =
            phoneField.getText();

    BuyerProfilePage.email =
            emailField.getText();

    BuyerProfilePage.location =
            locationField.getText();

    BuyerProfilePage.buyerType =
            buyerTypeBox.getValue();

    BuyerProfilePage profilePage =
            new BuyerProfilePage();

    Stage currentStage =
            (Stage) saveButton.getScene().getWindow();

    currentStage.setScene(
            profilePage.getProfilePageScene()
    );
});
        buttonBox.getChildren().addAll(
                cancelButton,
                saveButton
        );

        card.getChildren().addAll(
                form,
                buttonBox
        );

        // =====================================================
        // CENTER
        // =====================================================

        VBox centerBox =
                new VBox();

        centerBox.setAlignment(
                Pos.TOP_CENTER
        );

        centerBox.setPadding(
                new Insets(35)
        );

        centerBox.getChildren().add(card);

        root.setCenter(centerBox);

        return new Scene(
                root,
                1368,
                768
        );
    }

    // =========================================================
    // SAVE PROFILE
    // =========================================================

//     private void saveProfile() {

//     BuyerProfilePage.buyerName =
//             nameField.getText();

//     BuyerProfilePage.phoneNumber =
//             phoneField.getText();

//     BuyerProfilePage.email =
//             emailField.getText();

//     BuyerProfilePage.location =
//             locationField.getText();

//     BuyerProfilePage.buyerType =
//             buyerTypeBox.getValue();

//     BuyerProfilePage profilePage =
//             new BuyerProfilePage();

//     saveProfileButton.getScene().setRoot(
//             profilePage.getProfilePageScene().getRoot()
//     );
// }

    // =========================================================
    // CREATE LABEL
    // =========================================================

    private Label createLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        return label;
    }

    // =========================================================
    // CREATE TEXT FIELD
    // =========================================================

    private TextField createTextField(
            String value
    ) {

        TextField field =
                new TextField(value);

        field.setPrefWidth(350);
        field.setPrefHeight(40);

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;" +
                "-fx-border-radius: 7;" +
                "-fx-padding: 8;"
        );

        return field;
    }
}
