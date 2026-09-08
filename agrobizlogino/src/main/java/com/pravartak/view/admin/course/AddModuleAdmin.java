package com.pravartak.view.admin.course;

import com.pravartak.controller.admincontroller.ModuleController;
import com.pravartak.model.admin.Course;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import java.io.File;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

public class AddModuleAdmin {

        private final ModuleController moduleController = new ModuleController();

        private final Course course;

        // =========================================================
        // FIELDS
        // =========================================================

        private TextField moduleTitleField;
        private TextArea descriptionField;
        private TextField orderField;
        private ComboBox<String> statusBox;

        // IMAGE
        private File selectedImageFile;
        private ImageView imagePreview;
        private Label imageNameLabel;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public AddModuleAdmin(Course course) {

                this.course = course;
        }

        // =========================================================
        // SCENE
        // =========================================================

        public Scene getAddModuleScene() {

                VBox root = new VBox(16);

                root.setPadding(
                                new Insets(15, 30, 20, 30));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // TOP BAR
                // =====================================================

                HBox topBar = new HBox(14);

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                Button backButton = new Button("← Back");

                backButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:6 14;" +
                                                "-fx-cursor:hand;");

                Label pageTitle = new Label("Add Module");

                pageTitle.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                Label courseLabel = new Label(
                                course != null
                                                ? "Course: "
                                                                + safe(course.getTitle())
                                                : "Course not found");

                courseLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;");

                topBar.getChildren().addAll(
                                backButton,
                                pageTitle,
                                courseLabel);

                backButton.setOnAction(
                                e -> goBack());

                // =====================================================
                // PAGE DESCRIPTION
                // =====================================================

                Label description = new Label(
                                "Create a new module for this agricultural course.");

                description.setStyle(
                                "-fx-text-fill:#888888;" +
                                                "-fx-font-size:11px;");

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                HBox content = new HBox(16);

                // =====================================================
                // LEFT
                // =====================================================

                VBox informationCard = createInformationCard();

                informationCard.setPrefWidth(650);
                informationCard.setMinWidth(500);

                HBox.setHgrow(
                                informationCard,
                                Priority.ALWAYS);

                // =====================================================
                // RIGHT
                // =====================================================

                VBox settingsCard = createSettingsCard();

                settingsCard.setPrefWidth(300);
                settingsCard.setMinWidth(260);

                content.getChildren().addAll(
                                informationCard,
                                settingsCard);

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                HBox actions = createActionButtons();

                // =====================================================
                // PAGE CONTENT
                // =====================================================

                VBox pageContent = new VBox(16);

                pageContent.setPadding(
                                new Insets(5, 0, 20, 0));

                pageContent.getChildren().addAll(
                                content,
                                actions);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(pageContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-background:#080C0D;" +
                                                "-fx-border-color:transparent;");

                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                // =====================================================
                // ROOT
                // =====================================================

                root.getChildren().addAll(
                                topBar,
                                description,
                                scrollPane);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // INFORMATION CARD
        // =========================================================

        private VBox createInformationCard() {

                VBox card = createCard();

                Label heading = createHeading("Module Information");

                Separator separator = new Separator();

                // =====================================================
                // TITLE
                // =====================================================

                Label titleLabel = createFieldLabel("Module Title");

                moduleTitleField = new TextField();

                moduleTitleField.setPromptText(
                                "Enter module title");

                styleTextField(
                                moduleTitleField);

                // =====================================================
                // DESCRIPTION
                // =====================================================

                Label descriptionLabel = createFieldLabel(
                                "Module Description");

                descriptionField = new TextArea();

                descriptionField.setPromptText(
                                "Describe what students will learn in this module...");

                descriptionField.setPrefHeight(250);
                descriptionField.setWrapText(true);

                styleTextArea(
                                descriptionField);

                // =====================================================
                // IMAGE
                // =====================================================

                Label imageLabel = createFieldLabel(
                                "Module Image");

                Button uploadImageButton = new Button("＋  Upload Image");

                uploadImageButton.setMaxWidth(
                                Double.MAX_VALUE);

                uploadImageButton.setPrefHeight(38);

                uploadImageButton.setStyle(
                                "-fx-background-color:#0D1511;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                uploadImageButton.setOnAction(
                                e -> chooseImage());

                // =====================================================
                // IMAGE NAME
                // =====================================================

                imageNameLabel = new Label("No image selected");

                imageNameLabel.setStyle(
                                "-fx-text-fill:#666666;" +
                                                "-fx-font-size:10px;");

                // =====================================================
                // IMAGE PREVIEW
                // =====================================================

                imagePreview = new ImageView();

                imagePreview.setFitWidth(220);
                imagePreview.setFitHeight(120);

                imagePreview.setPreserveRatio(true);

                imagePreview.setSmooth(true);

                imagePreview.setVisible(false);
                imagePreview.setManaged(false);

                imagePreview.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                card.getChildren().addAll(
                                heading,
                                separator,
                                titleLabel,
                                moduleTitleField,
                                descriptionLabel,
                                descriptionField,
                                imageLabel,
                                uploadImageButton,
                                imageNameLabel,
                                imagePreview);

                return card;
        }

        // =========================================================
        // CHOOSE IMAGE
        // =========================================================

        private void chooseImage() {

                FileChooser fileChooser = new FileChooser();

                fileChooser.setTitle(
                                "Select Module Image");

                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                                "Image Files",
                                "*.png",
                                "*.jpg",
                                "*.jpeg",
                                "*.webp");

                fileChooser.getExtensionFilters().add(
                                imageFilter);

                Window window = LoginPage.mainStage;

                File file = fileChooser.showOpenDialog(window);

                if (file == null) {
                        return;
                }

                selectedImageFile = file;

                imageNameLabel.setText(
                                file.getName());

                imageNameLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:10px;");

                // =====================================================
                // PREVIEW
                // =====================================================

                try {

                        Image image = new Image(
                                        file.toURI().toString());

                        imagePreview.setImage(image);

                        imagePreview.setVisible(true);
                        imagePreview.setManaged(true);

                } catch (Exception e) {

                        e.printStackTrace();

                        imagePreview.setVisible(false);
                        imagePreview.setManaged(false);
                }
        }

        // =========================================================
        // SETTINGS CARD
        // =========================================================

        private VBox createSettingsCard() {

                VBox card = createCard();

                Label heading = createHeading("Module Settings");

                Separator separator = new Separator();

                // =====================================================
                // ORDER
                // =====================================================

                Label orderLabel = createFieldLabel(
                                "Module Order");

                orderField = new TextField();

                orderField.setPromptText(
                                "e.g. 1");

                styleTextField(
                                orderField);

                // =====================================================
                // STATUS
                // =====================================================

                Label statusLabel = createFieldLabel(
                                "Module Status");

                statusBox = new ComboBox<>();

                statusBox.getItems().addAll(
                                "Draft",
                                "Published");

                statusBox.setValue(
                                "Draft");

                styleComboBox(
                                statusBox);

                // =====================================================
                // COURSE
                // =====================================================

                Label courseHeading = createFieldLabel("Course");

                Label selectedCourse = new Label(
                                course != null
                                                ? safe(course.getTitle())
                                                : "Unknown Course");

                selectedCourse.setWrapText(true);

                selectedCourse.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // COURSE ID
                // =====================================================

                Label idLabel = createFieldLabel(
                                "Course ID");

                Label courseId = new Label(
                                course != null
                                                ? String.valueOf(
                                                                course.getCourseId())
                                                : "-");

                courseId.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;");

                card.getChildren().addAll(
                                heading,
                                separator,
                                orderLabel,
                                orderField,
                                statusLabel,
                                statusBox,
                                courseHeading,
                                selectedCourse,
                                idLabel,
                                courseId);

                return card;
        }

        // =========================================================
        // ACTION BUTTONS
        // =========================================================

        private HBox createActionButtons() {

                HBox buttons = new HBox(10);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                Button cancel = new Button("Cancel");

                cancel.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 22;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                Button save = new Button(
                                "✓  Save Module");

                save.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 24;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                cancel.setOnAction(
                                e -> goBack());

                save.setOnAction(
                                e -> saveModule());

                buttons.getChildren().addAll(
                                cancel,
                                save);

                return buttons;
        }

        // =========================================================
        // SAVE MODULE
        // =========================================================

        private void saveModule() {

                try {

                        // =====================================================
                        // COURSE
                        // =====================================================

                        if (course == null) {

                                showPopup(
                                                "Error",
                                                "Course information not found.",
                                                false);

                                return;
                        }

                        // =====================================================
                        // TITLE
                        // =====================================================

                        String title = moduleTitleField
                                        .getText()
                                        .trim();

                        if (title.isEmpty()) {

                                showPopup(
                                                "Missing Information",
                                                "Please enter a module title.",
                                                false);

                                moduleTitleField.requestFocus();

                                return;
                        }

                        // =====================================================
                        // DESCRIPTION
                        // =====================================================

                        String description = descriptionField
                                        .getText()
                                        .trim();

                        if (description.isEmpty()) {

                                showPopup(
                                                "Missing Information",
                                                "Please enter a module description.",
                                                false);

                                descriptionField.requestFocus();

                                return;
                        }

                        // =====================================================
                        // ORDER
                        // =====================================================

                        String orderText = orderField
                                        .getText()
                                        .trim();

                        int order = 1;

                        if (!orderText.isEmpty()) {

                                try {

                                        order = Integer.parseInt(
                                                        orderText);

                                        if (order < 1) {

                                                showPopup(
                                                                "Invalid Order",
                                                                "Module order must be greater than 0.",
                                                                false);

                                                orderField.requestFocus();

                                                return;
                                        }

                                } catch (NumberFormatException ex) {

                                        showPopup(
                                                        "Invalid Order",
                                                        "Module order must be a number.",
                                                        false);

                                        orderField.requestFocus();

                                        return;
                                }
                        }

                        // =====================================================
                        // STATUS
                        // =====================================================

                        boolean published = "Published".equals(
                                        statusBox.getValue());

                        // =====================================================
                        // COURSE DATA
                        // =====================================================

                        int courseId = course.getCourseId();

                        System.out.println(
                                        "================================");

                        System.out.println(
                                        "ADDING MODULE");

                        System.out.println(
                                        "Course ID     : "
                                                        + courseId);

                        System.out.println(
                                        "Course        : "
                                                        + course.getTitle());

                        System.out.println(
                                        "Module Title  : "
                                                        + title);

                        System.out.println(
                                        "Description   : "
                                                        + description);

                        System.out.println(
                                        "Order         : "
                                                        + order);

                        System.out.println(
                                        "Published     : "
                                                        + published);

                        System.out.println(
                                        "Image         : "
                                                        + (selectedImageFile != null
                                                                        ? selectedImageFile.getName()
                                                                        : "No image"));

                        System.out.println(
                                        "================================");

                        // =====================================================
                        // SAVE MODULE
                        // =====================================================

                        boolean success;

                        if (selectedImageFile != null) {

                                success = moduleController.addModule(
                                                courseId,
                                                title,
                                                description,
                                                selectedImageFile);

                        } else {

                                success = moduleController.addModule(
                                                courseId,
                                                title,
                                                description);
                        }

                        // =====================================================
                        // FAILED
                        // =====================================================

                        if (!success) {

                                showPopup(
                                                "Error",
                                                "Unable to create the module.",
                                                false);

                                return;
                        }

                        // =====================================================
                        // SUCCESS
                        // =====================================================

                        showPopup(
                                        "Module Created",
                                        "The module has been added successfully.",
                                        true);

                        // =====================================================
                        // RETURN
                        // =====================================================

                        PauseTransition delay = new PauseTransition(
                                        Duration.seconds(1.0));

                        delay.setOnFinished(e -> {

                                AdminModulePage modulePage = new AdminModulePage(course);

                                LoginPage.mainStage.setScene(
                                                modulePage.getModuleScene());
                        });

                        delay.play();

                } catch (Exception e) {

                        e.printStackTrace();

                        showPopup(
                                        "Error",
                                        "Something went wrong while creating the module.",
                                        false);
                }
        }

        // =========================================================
        // BACK
        // =========================================================

        private void goBack() {

                AdminPage adminPage = new AdminPage();

                LoginPage.mainStage.setScene(
                                adminPage.getAdminPage(
                                                "Manage Course"));
        }

        // =========================================================
        // CARD
        // =========================================================

        private VBox createCard() {

                VBox card = new VBox(10);

                card.setPadding(
                                new Insets(18));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                return card;
        }

        // =========================================================
        // HEADING
        // =========================================================

        private Label createHeading(
                        String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                return label;
        }

        // =========================================================
        // FIELD LABEL
        // =========================================================

        private Label createFieldLabel(
                        String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;");

                return label;
        }

        // =========================================================
        // TEXT FIELD STYLE
        // =========================================================

        private void styleTextField(
                        TextField field) {

                field.setPrefHeight(36);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                field.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#666666;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-padding:8;");
        }

        // =========================================================
        // TEXT AREA STYLE
        // =========================================================

        private void styleTextArea(
                        TextArea area) {

                area.setPrefHeight(160);
                area.setMinHeight(120);
                area.setWrapText(true);

                area.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-control-inner-background:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#666666;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-padding:8;" +
                                                "-fx-focus-color:transparent;" +
                                                "-fx-faint-focus-color:transparent;");
        }

        // =========================================================
        // COMBO BOX STYLE
        // =========================================================

        private void styleComboBox(
                        ComboBox<String> box) {

                box.setPrefHeight(36);

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;");
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

        // =========================================================
        // POPUP
        // =========================================================

        private void showPopup(
                        String title,
                        String message,
                        boolean success) {

                Popup popup = new Popup();

                VBox box = new VBox(8);

                box.setAlignment(
                                Pos.CENTER);

                box.setPrefWidth(320);
                box.setPrefHeight(135);

                box.setPadding(
                                new Insets(15));

                Label icon = new Label(
                                success
                                                ? "✓"
                                                : "!");

                icon.setPrefSize(
                                42,
                                42);

                icon.setAlignment(
                                Pos.CENTER);

                icon.setStyle(
                                "-fx-background-color:"
                                                + (success
                                                                ? "#245D35;"
                                                                : "#3A2525;")
                                                +
                                                "-fx-text-fill:"
                                                + (success
                                                                ? "#68D34A;"
                                                                : "#FF6B6B;")
                                                +
                                                "-fx-font-size:22px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:50%;");

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                Label messageLabel = new Label(message);

                messageLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:11px;");

                messageLabel.setWrapText(true);

                box.getChildren().addAll(
                                icon,
                                titleLabel,
                                messageLabel);

                box.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:"
                                                + (success
                                                                ? "#68D34A;"
                                                                : "#FF6B6B;")
                                                +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                popup.getContent().add(box);

                Window window = LoginPage.mainStage;

                popup.show(
                                window,
                                window.getX()
                                                + (window.getWidth() - 320) / 2,
                                window.getY()
                                                + (window.getHeight() - 135) / 2);

                PauseTransition delay = new PauseTransition(
                                Duration.seconds(1.5));

                delay.setOnFinished(
                                e -> popup.hide());

                delay.play();
        }
}