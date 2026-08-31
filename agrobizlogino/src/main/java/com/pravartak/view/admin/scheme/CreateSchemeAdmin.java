package com.pravartak.view.admin.scheme;

import com.pravartak.controller.admincontroller.SchemeController;
import com.pravartak.model.admin.Scheme;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.admin.course.AdminLearning;
import com.pravartak.view.login.LoginPage;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CreateSchemeAdmin {

    private final SchemeController controller =
            new SchemeController();

    private TextField schemeNameField;

    private TextArea eligibilityArea;

    private TextArea informationArea;

        private TextField applyUrlField;
    // =========================================================
    // SCENE
    // =========================================================

    public Scene getCreateSchemeScene() {

        VBox root =
                new VBox(18);

        root.setPadding(
                new Insets(
                        20,
                        30,
                        20,
                        30));

        root.setStyle(
                "-fx-background-color:#080C0D;");

        // =====================================================
        // HEADER
        // =====================================================

        Button backButton =
                new Button("← Back");

        backButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#AAAAAA;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;" +
                "-fx-padding:6 14;" +
                "-fx-cursor:hand;");

        backButton.setOnAction(
                e -> goBack());

        Label title =
                new Label("Create New Scheme");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;");

        VBox heading =
                new VBox(8);

        heading.getChildren().addAll(
                backButton,
                title);

        // =====================================================
        // FORM CARD
        // =====================================================

        VBox card =
                new VBox(12);

        card.setMaxWidth(800);

        card.setPadding(
                new Insets(25));

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;");

        // =====================================================
        // SCHEME NAME
        // =====================================================

        Label nameLabel =
                createLabel("Scheme Name");

        schemeNameField =
                new TextField();

        schemeNameField.setPromptText(
                "e.g. Sub-Mission on Agricultural Mechanization (SMAM)");

        styleTextField(
                schemeNameField);

        // =====================================================
        // ELIGIBILITY
        // =====================================================

        Label eligibilityLabel =
                createLabel("Eligibility");

        eligibilityArea =
                new TextArea();

        eligibilityArea.setPromptText(
                "Enter who is eligible for this scheme.\n\n" +
                "Example:\n" +
                "• Farmers\n" +
                "• Farmer groups\n" +
                "• Registered agricultural organisations");

        eligibilityArea.setPrefRowCount(7);

        eligibilityArea.setWrapText(true);

        styleTextArea(
                eligibilityArea);

        // =====================================================
        // INFORMATION
        // =====================================================

        Label informationLabel =
                createLabel(
                        "Scheme Information");

        informationArea =
                new TextArea();

        informationArea.setPromptText(
                "Enter complete information about the scheme.");

        informationArea.setPrefRowCount(10);

        informationArea.setWrapText(true);

        styleTextArea(
                informationArea);


        // =====================================================
        // APPLY URL
        // =====================================================

        Label applyUrlLabel =
        createLabel(
                "Official Application URL");

        applyUrlField =
        new TextField();

        applyUrlField.setPromptText(
        "https://example.gov.in/apply");

        styleTextField(
                applyUrlField);

        // =====================================================
        // SAVE
        // =====================================================

        Button saveButton =
                new Button("✓  Save Scheme");

        saveButton.setPrefHeight(42);

        saveButton.setPrefWidth(180);

        saveButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;");

        saveButton.setOnAction(
                e -> saveScheme());

        // =====================================================
        // CANCEL
        // =====================================================

        Button cancelButton =
                new Button("Cancel");

        cancelButton.setPrefHeight(42);

        cancelButton.setPrefWidth(120);

        cancelButton.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#AAAAAA;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;");

        cancelButton.setOnAction(
                e -> goBack());

        VBox.setMargin(
                saveButton,
                new Insets(10, 0, 0, 0));

        card.getChildren().addAll(
                nameLabel,
                schemeNameField,
                eligibilityLabel,
                eligibilityArea,
                informationLabel,
                informationArea,
                applyUrlLabel,
                applyUrlField,
                saveButton,
                cancelButton);

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll =
                new ScrollPane(card);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scroll.setStyle(
                "-fx-background-color:#080C0D;" +
                "-fx-background:#080C0D;" +
                "-fx-border-color:transparent;");

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);

        root.getChildren().addAll(
                heading,
                scroll);

        return new Scene(
                root,
                1100,
                700);
    }

    // =========================================================
    // SAVE SCHEME
    // =========================================================

    private void saveScheme() {

        String name =
                schemeNameField.getText()
                        .trim();

        String eligibility =
                eligibilityArea.getText()
                        .trim();

        String information =
                informationArea.getText()
                        .trim();

        String applyUrl =
                applyUrlField.getText()
                        .trim();
        // =====================================================
        // VALIDATION
        // =====================================================

        if (name.isEmpty()) {

            showMessage(
                    "Please enter scheme name.",
                    false);

            return;
        }

        if (eligibility.isEmpty()) {

            showMessage(
                    "Please enter eligibility.",
                    false);

            return;
        }

        if (information.isEmpty()) {

            showMessage(
                    "Please enter scheme information.",
                    false);

            return;
        }
        if (applyUrl.isEmpty()) {

    showMessage(
            "Please enter official application URL.",
            false);

    return;
}

        // =====================================================
        // DATABASE
        // =====================================================

        boolean success =
                controller.addScheme(
                        name,
                        eligibility,
                        information,
                        applyUrl);

        if (success) {

            showMessage(
                    "Scheme added successfully.",
                    true);

            PauseTransition pause =
                    new PauseTransition(
                            Duration.seconds(1));

            pause.setOnFinished(
                    e -> goBack());

            pause.play();

        } else {

            showMessage(
                    "Scheme could not be saved.",
                    false);
        }
    }

    // =========================================================
    // LABEL
    // =========================================================

    private Label createLabel(
            String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;");

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private void styleTextField(
            TextField field) {

        field.setPrefHeight(38);

        field.setStyle(
                "-fx-background-color:#0D1213;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#666666;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-padding:8 12;" +
                "-fx-font-size:12px;");
    }

    // =========================================================
    // TEXT AREA
    // =========================================================

    private void styleTextArea(
            TextArea area) {

        area.setStyle(
                "-fx-control-inner-background:#0D1213;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#666666;" +
                "-fx-highlight-fill:#245D35;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-font-size:12px;" +
                "-fx-padding:8;");
    }

    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String message,
            boolean success) {

        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        success
                                ? javafx.scene.control.Alert.AlertType.INFORMATION
                                : javafx.scene.control.Alert.AlertType.ERROR);

        alert.setTitle(
                success
                        ? "Success"
                        : "Error");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================================================
    // BACK
    // =========================================================

    private void goBack() {

         AdminPage adminPage =
                 new AdminPage();

        LoginPage.mainStage.setScene(
                adminPage.getAdminPage("Government Schemes"));
        //SchemeTab st = new SchemeTab();
        // LoginPage.mainStage.setScene(st.getCreateSchemeScene());
         

            //LoginPage.mainStage.setScene(st.getSchemesPage());
        


    }
}
