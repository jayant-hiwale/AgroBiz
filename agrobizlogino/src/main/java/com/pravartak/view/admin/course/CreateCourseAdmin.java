package com.pravartak.view.admin.course;

import java.io.File;

import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
// import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

public class CreateCourseAdmin {

        public Scene createCouresScene;
        private String thumbnailPath = "";

        // =========================================================
        // CREATE COURSE SCENE
        // =========================================================

        public Scene getCreateCourseScene() {

                VBox root = new VBox(16);

                root.setPadding(  new Insets(15, 30, 18, 30));

                root.setAlignment(Pos.TOP_LEFT);

                root.setStyle("-fx-background-color:#080C0D;");

                // =====================================================
                // TOP BAR
                // =====================================================

                HBox topBar = new HBox(12);

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = new Button("← Back");

                backButton.setPrefHeight(30);

                backButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:5 12;" +
                                                "-fx-cursor:hand;");

                backButton.setOnMouseEntered(e -> {

                        backButton.setStyle(
                                        "-fx-background-color:#245D35;" +
                                                        "-fx-text-fill:#68D34A;" +
                                                        "-fx-font-size:11px;" +
                                                        "-fx-border-color:#68D34A;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:5 12;" +
                                                        "-fx-cursor:hand;");
                });

                backButton.setOnMouseExited(e -> {

                        backButton.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-text-fill:#AAAAAA;" +
                                                        "-fx-font-size:11px;" +
                                                        "-fx-border-color:#242B2C;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:5 12;" +
                                                        "-fx-cursor:hand;");
                });

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label(
                                "Create New Course");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                topBar.getChildren().addAll(
                                backButton,
                                title);

                // =====================================================
                // BACK ACTION
                // =====================================================

                backButton.setOnAction(e -> {

                        AdminPage adminPage = new AdminPage();

                        LoginPage.mainStage.setScene(
                                        adminPage.getAdminPage(
                                                        "Manage Course"));
                });

                // =====================================================
                // COURSE INFORMATION
                // =====================================================

                HBox courseInformation = new HBox(16);

                courseInformation.setAlignment(
                                Pos.TOP_LEFT);

                // =====================================================
                // COURSE BASICS
                // =====================================================

                VBox courseBasics = createCourseBasics();

                // =====================================================
                // COURSE THUMBNAIL
                // =====================================================

                VBox courseThumbnail = createCourseThumbnail();

                // =====================================================
                // COURSE SETTINGS
                // =====================================================

                VBox courseSettings = createCourseSettings();

                // =====================================================
                // WIDTH
                // =====================================================

                courseBasics.setPrefWidth(620);
                courseBasics.setMinWidth(620);
                courseBasics.setMaxWidth(620);

                courseThumbnail.setPrefWidth(300);
                courseThumbnail.setMinWidth(300);
                courseThumbnail.setMaxWidth(300);

                courseSettings.setPrefWidth(260);
                courseSettings.setMinWidth(260);
                courseSettings.setMaxWidth(260);

                courseInformation.getChildren().addAll(
                                courseBasics,
                                courseThumbnail,
                                courseSettings);

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                HBox actionButtons = createActionButtons();

                // =====================================================
                // ROOT
                // =====================================================

                root.getChildren().addAll(
                                topBar,
                                courseInformation,
                                actionButtons);

                // =====================================================
                // SCENE
                // =====================================================

                createCouresScene = new Scene(
                                root,
                                1100,
                                700);

                return createCouresScene;
        }

        // =========================================================
        // COURSE BASICS
        // =========================================================

        private static VBox createCourseBasics() {

                VBox card = new VBox(10);

                card.setPrefWidth(620);
                card.setMinWidth(620);
                card.setMaxWidth(620);

                card.setPrefHeight(250);

                card.setPadding(
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // HEADING
                // =====================================================

                Label heading = new Label("Course Basics");

                heading.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // SEPARATOR
                // =====================================================

                Separator separator = new Separator();

                separator.setMaxWidth(
                                Double.MAX_VALUE);

                separator.setStyle(
                                "-fx-background-color:#242B2C;");

                // =====================================================
                // COURSE TITLE
                // =====================================================

                Label courseTitleLabel = new Label("Course Title");

                courseTitleLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                TextField courseTitle = new TextField();

                courseTitle.setPromptText(
                                "e.g., Advanced Hydroponics Systems");

                courseTitle.setPrefHeight(32);

                courseTitle.setMaxWidth(
                                Double.MAX_VALUE);

                courseTitle.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-padding:8;");

                // =====================================================
                // CATEGORY
                // =====================================================

                HBox fields = new HBox(10);

                fields.setPrefWidth(
                                Double.MAX_VALUE);

                VBox categoryBox = new VBox(5);

                HBox.setHgrow(
                                categoryBox,
                                Priority.ALWAYS);

                Label categoryLabel = new Label("Category");

                categoryLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                ComboBox<String> category = new ComboBox<>();

                category.getItems().addAll(
                                "Crop Farming",
                                "Water Management",
                                "Hydroponics",
                                "Organic Farming");

                category.setValue(
                                "Crop Farming");

                category.setPrefHeight(32);

                category.setMaxWidth(
                                Double.MAX_VALUE);

                category.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;" +
                                                "-fx-font-size:14px;");

                categoryBox.getChildren().addAll(
                                categoryLabel,
                                category);

                fields.getChildren().add(
                                categoryBox);

                // =====================================================
                // ADD TO CARD
                // =====================================================

                card.getChildren().addAll(
                                heading,
                                separator,
                                courseTitleLabel,
                                courseTitle,
                                fields);

                return card;
        }

        // =========================================================
        // COURSE THUMBNAIL
        // =========================================================

        private VBox createCourseThumbnail() {

                VBox card = new VBox(10);

                card.setPrefWidth(300);
                card.setMinWidth(300);
                card.setMaxWidth(300);

                card.setPrefHeight(250);

                card.setPadding(
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // HEADING
                // =====================================================

                Label heading = new Label(
                                "▣  Course Thumbnail");

                heading.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // UPLOAD AREA
                // =====================================================

                VBox uploadArea = new VBox(7);

                uploadArea.setAlignment(
                                Pos.CENTER);

                uploadArea.setPrefHeight(170);

                uploadArea.setMaxWidth(
                                Double.MAX_VALUE);

                uploadArea.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;");

                // =====================================================
                // ICON
                // =====================================================

                Label icon = new Label("☁");

                icon.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:25px;");

                // =====================================================
                // TEXT
                // =====================================================

                Label uploadText = new Label(
                                "Drag and drop image here");

                uploadText.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:10px;");

                Label browseText = new Label(
                                "or click to browse");

                browseText.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:8px;");

                uploadArea.getChildren().addAll(
                                icon,
                                uploadText,
                                browseText);

                // =====================================================
                // CLICK TO SELECT IMAGE
                // =====================================================

                uploadArea.setOnMouseClicked(e -> {

                        FileChooser fileChooser = new FileChooser();

                        fileChooser.setTitle(
                                        "Select Course Thumbnail");

                        // =================================================
                        // IMAGE FILTER
                        // =================================================

                        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                                        "Image Files",
                                        "*.png",
                                        "*.jpg",
                                        "*.jpeg",
                                        "*.webp");

                        fileChooser.getExtensionFilters().add(
                                        imageFilter);

                        // =================================================
                        // OPEN FILE CHOOSER
                        // =================================================

                        File selectedFile = fileChooser.showOpenDialog(
                                        LoginPage.mainStage);

                        // =================================================
                        // CHECK FILE
                        // =================================================

                        if (selectedFile != null) {

                                // Save image path

                                thumbnailPath = selectedFile.getAbsolutePath();

                                System.out.println(
                                                "Selected Image: "
                                                                + thumbnailPath);

                                // =================================================
                                // DISPLAY IMAGE
                                // =================================================

                                ImageView imageView = new ImageView(
                                                new Image(
                                                                selectedFile
                                                                                .toURI()
                                                                                .toString()));

                                imageView.setFitWidth(240);

                                imageView.setFitHeight(150);

                                imageView.setPreserveRatio(true);

                                imageView.setSmooth(true);

                                // =================================================
                                // CLEAR OLD CONTENT
                                // =================================================

                                uploadArea.getChildren().clear();

                                // =================================================
                                // ADD IMAGE
                                // =================================================

                                uploadArea.getChildren().add(
                                                imageView);
                        }
                });

                // =====================================================
                // ADD TO CARD
                // =====================================================

                card.getChildren().addAll(
                                heading,
                                uploadArea);

                return card;
        }

        // =========================================================
        // COURSE SETTINGS
        // =========================================================

        private static VBox createCourseSettings() {

                VBox card = new VBox(8);

                card.setPrefWidth(260);
                card.setMinWidth(260);
                card.setMaxWidth(260);

                card.setPrefHeight(250);

                card.setPadding(
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // HEADING
                // =====================================================

                Label heading = new Label("Course Settings");

                heading.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // SEPARATOR
                // =====================================================

                Separator separator = new Separator();

                separator.setMaxWidth(
                                Double.MAX_VALUE);

                separator.setStyle(
                                "-fx-background-color:#242B2C;");

                // =====================================================
                // DIFFICULTY
                // =====================================================

                Label difficultyLabel = new Label(
                                "Difficulty Level");

                difficultyLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                ToggleGroup difficultyGroup = new ToggleGroup();

                RadioButton beginner = new RadioButton("Beginner");

                RadioButton intermediate = new RadioButton("Intermediate");

                RadioButton advanced = new RadioButton("Advanced");

                beginner.setToggleGroup(
                                difficultyGroup);

                intermediate.setToggleGroup(
                                difficultyGroup);

                advanced.setToggleGroup(
                                difficultyGroup);

                intermediate.setSelected(
                                true);

                String radioStyle = "-fx-text-fill:#AAAAAA;" +
                                "-fx-font-size:14px;";

                beginner.setStyle(
                                radioStyle);

                intermediate.setStyle(
                                radioStyle);

                advanced.setStyle(
                                radioStyle);

                VBox difficultyBox = new VBox(4);

                difficultyBox.getChildren().addAll(
                                difficultyLabel,
                                beginner,
                                intermediate,
                                advanced);

                // =====================================================
                // LANGUAGE
                // =====================================================

                Label languageLabel = new Label("Language");

                languageLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                ComboBox<String> language = new ComboBox<>();

                language.getItems().addAll(
                                "English",
                                "Hindi",
                                "Marathi");

                language.setValue(
                                "English");

                language.setPrefHeight(32);

                language.setMaxWidth(
                                Double.MAX_VALUE);

                language.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;" +
                                                "-fx-font-size:14px;");

                // =====================================================
                // ADD TO CARD
                // =====================================================

                card.getChildren().addAll(
                                heading,
                                separator,
                                difficultyBox,
                                languageLabel,
                                language);

                return card;
        }

        // =========================================================
        // ACTION BUTTONS
        // =========================================================

        private HBox createActionButtons() {

                HBox buttons = new HBox(10);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                // =====================================================
                // SAVE AS DRAFT
                // =====================================================

                Button draftButton = new Button("Save as Draft");

                draftButton.setPrefHeight(36);

                draftButton.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:7 18;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                draftButton.setOnMouseEntered(e -> {

                        draftButton.setStyle(
                                        "-fx-background-color:#1B2021;" +
                                                        "-fx-text-fill:#EEEEEE;" +
                                                        "-fx-border-color:#68D34A;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:7 18;" +
                                                        "-fx-font-size:11px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-cursor:hand;");
                });

                draftButton.setOnMouseExited(e -> {

                        draftButton.setStyle(
                                        "-fx-background-color:#101516;" +
                                                        "-fx-text-fill:#AAAAAA;" +
                                                        "-fx-border-color:#242B2C;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:7 18;" +
                                                        "-fx-font-size:11px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-cursor:hand;");
                });

                // =====================================================
                // PUBLISH
                // =====================================================

                Button publishButton = new Button("Publish Course");

                publishButton.setPrefHeight(36);

                publishButton.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:7 20;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                publishButton.setOnMouseEntered(e -> {

                        publishButton.setStyle(
                                        "-fx-background-color:#7BE65B;" +
                                                        "-fx-text-fill:#080C0D;" +
                                                        "-fx-border-color:#7BE65B;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:7 20;" +
                                                        "-fx-font-size:11px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-cursor:hand;");
                });

                publishButton.setOnMouseExited(e -> {

                        publishButton.setStyle(
                                        "-fx-background-color:#68D34A;" +
                                                        "-fx-text-fill:#080C0D;" +
                                                        "-fx-border-color:#68D34A;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:7 20;" +
                                                        "-fx-font-size:11px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-cursor:hand;");
                });

                // =====================================================
                // SAVE AS DRAFT ACTION
                // =====================================================

                draftButton.setOnAction(e -> {

                        showCourseStatusPopup(
                                        "Draft Saved",
                                        "Your course has been saved as a draft.",
                                        false);
                });

                // =====================================================
                // PUBLISH ACTION
                // =====================================================

                publishButton.setOnAction(e -> {

                        showCourseStatusPopup(
                                        "Course Published",
                                        "Your course is now available to learners.",
                                        true);
                });

                buttons.getChildren().addAll(
                                draftButton,
                                publishButton);

                return buttons;
        }

        // =========================================================
        // COURSE STATUS POPUP
        // =========================================================

        private void showCourseStatusPopup(
                        String title,
                        String message,
                        boolean published) {

                Popup popup = new Popup();

                // =====================================================
                // MAIN BOX
                // =====================================================

                VBox box = new VBox(8);

                box.setAlignment(
                                Pos.CENTER);

                box.setPrefWidth(300);

                box.setPrefHeight(130);

                box.setPadding(
                                new Insets(15));

                // =====================================================
                // ICON
                // =====================================================

                Label icon = new Label(
                                published
                                                ? "✓"
                                                : "✓");

                icon.setAlignment(
                                Pos.CENTER);

                icon.setPrefSize(
                                42,
                                42);

                icon.setStyle(
                                "-fx-background-color:#245D35;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:22px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:50%;");

                // =====================================================
                // TITLE
                // =====================================================

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // MESSAGE
                // =====================================================

                Label messageLabel = new Label(message);

                messageLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:11px;");

                // =====================================================
                // ADD COMPONENTS
                // =====================================================

                box.getChildren().addAll(
                                icon,
                                titleLabel,
                                messageLabel);

                // =====================================================
                // BOX STYLE
                // =====================================================

                box.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                // =====================================================
                // POPUP
                // =====================================================

                popup.getContent().add(
                                box);

                Window window = LoginPage.mainStage;

                // =====================================================
                // CENTER POPUP
                // =====================================================

                popup.show(
                                window,
                                window.getX()
                                                + (window.getWidth() - 300) / 2,
                                window.getY()
                                                + (window.getHeight() - 130) / 2);

                // =====================================================
                // AUTO CLOSE
                // =====================================================

                PauseTransition delay = new PauseTransition(
                                Duration.seconds(1.3));

                delay.setOnFinished(
                                e -> popup.hide());

                delay.play();
        }
}