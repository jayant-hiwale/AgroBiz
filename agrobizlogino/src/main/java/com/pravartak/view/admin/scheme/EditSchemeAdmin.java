package com.pravartak.view.admin.scheme;

import com.pravartak.controller.admincontroller.SchemeController;
import com.pravartak.model.admin.Scheme;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

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

public class EditSchemeAdmin {

        private final Scheme scheme;

        private final SchemeController controller = new SchemeController();

        private TextField nameField;

        private TextArea eligibilityArea;

        private TextArea informationArea;
        
        private TextField applyUrlField;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public EditSchemeAdmin(
                        Scheme scheme) {

                this.scheme = scheme;
        }

        // =========================================================
        // SCENE
        // =========================================================

        public Scene getEditSchemeScene() {

                VBox root = new VBox(15);

                root.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                20,
                                                30));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // BACK
                // =====================================================

                Button back = new Button("← Back");

                back.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-padding:6 14;" +
                                                "-fx-cursor:hand;");

                back.setOnAction(
                                e -> goBack());

                Label title = new Label("Edit Scheme");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // CARD
                // =====================================================

                VBox card = new VBox(12);

                card.setMaxWidth(800);

                card.setPadding(
                                new Insets(25));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                // =====================================================
                // NAME
                // =====================================================

                Label nameLabel = createLabel("Scheme Name");

                nameField = new TextField(
                                safe(
                                                scheme.getSchemeName()));

                styleTextField(
                                nameField);

                // =====================================================
                // ELIGIBILITY
                // =====================================================

                Label eligibilityLabel = createLabel("Eligibility");

                eligibilityArea = new TextArea(
                                safe(
                                                scheme.getEligibility()));

                eligibilityArea.setWrapText(true);

                eligibilityArea.setPrefRowCount(8);

                styleTextArea(
                                eligibilityArea);

                // =====================================================
                // INFORMATION
                // =====================================================

                Label informationLabel = createLabel(
                                "Scheme Information");

                informationArea = new TextArea(
                                safe(
                                                scheme.getInformation()));

                informationArea.setWrapText(true);

                informationArea.setPrefRowCount(10);

                styleTextArea(
                                informationArea);

                // =====================================================
// APPLY URL
// =====================================================

Label applyUrlLabel =
        createLabel(
                "Official Application URL");

applyUrlField =
        new TextField(
                safe(
                        scheme.getApplyUrl()));

applyUrlField.setPromptText(
        "https://example.gov.in/apply");

styleTextField(
        applyUrlField);

                // =====================================================
                // SAVE
                // =====================================================

                Button save = new Button(
                                "✓  Save Changes");

                save.setPrefWidth(170);

                save.setPrefHeight(42);

                save.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-cursor:hand;");

                save.setOnAction(
                                e -> saveChanges());

                // =====================================================
                // CANCEL
                // =====================================================

                Button cancel = new Button("Cancel");

                cancel.setPrefWidth(120);

                cancel.setPrefHeight(42);

                cancel.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-cursor:hand;");

                cancel.setOnAction(
                                e -> goBack());

                card.getChildren().addAll(
                                nameLabel,
                                nameField,
                                eligibilityLabel,
                                eligibilityArea,
                                informationLabel,
                                informationArea,
                                applyUrlLabel,
                                applyUrlField,
                                save,
                                cancel);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scroll = new ScrollPane(card);

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
                                back,
                                title,
                                scroll);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // SAVE
        // =========================================================

        private void saveChanges() {

                String name = nameField.getText()
                                .trim();

                String eligibility = eligibilityArea.getText()
                                .trim();

                String information = informationArea.getText()
                                .trim();

                String applyUrl =
        applyUrlField.getText()
                .trim();

                if (name.isEmpty() ||
                                eligibility.isEmpty() ||
                                information.isEmpty() ||
                                applyUrl.isEmpty()) {

                        showError(
                                        "Please fill all fields.");

                        return;
                }

                scheme.setSchemeName(
                                name);

                scheme.setEligibility(
                                eligibility);

                scheme.setInformation(
                                information);
                
                scheme.setApplyUrl(
                                applyUrl);

                boolean success = controller.updateScheme(
                                scheme);

                if (success) {

                        AdminPage adminPage =
                 new AdminPage();

        LoginPage.mainStage.setScene(
                adminPage.getAdminPage("Government Schemes"));
                } else {

                        showError(
                                        "Scheme could not be updated.");
                }
        }

        // =========================================================
        // LABEL
        // =========================================================

        private Label createLabel(
                        String text) {

                Label label = new Label(text);

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
                                                "-fx-padding:8 12;");
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
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:12px;");
        }

        // =========================================================
        // ERROR
        // =========================================================

        private void showError(
                        String message) {

                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                                javafx.scene.control.Alert.AlertType.ERROR);

                alert.setTitle("Error");

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
        }

        // =========================================================
        // SAFE
        // =========================================================

        private String safe(
                        String value) {

                return value == null
                                ? ""
                                : value;
        }
}
