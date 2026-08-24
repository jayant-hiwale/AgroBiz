package com.pravartak.view.admin.course;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

public class CreateCourseAdmin {

    public Scene createCouresScene;

    public Scene getCreateCourseScene() {

        VBox root = new VBox(16);

        root.setPadding(
                new Insets(15, 30, 18, 30));

        root.setAlignment(
                Pos.TOP_LEFT);

        root.setStyle(
                "-fx-background-color:#080C0D;");

        // =========================================
        // TOP BAR
        // =========================================

        HBox topBar = new HBox(12);

        topBar.setAlignment(
                Pos.CENTER_LEFT);

        // =========================================
        // BACK BUTTON
        // =========================================

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

        // Hover

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

        // =========================================
        // TITLE
        // =========================================

        Label title =
                new Label("Create New Course");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                        "-fx-font-size:24px;" +
                        "-fx-font-weight:bold;");

        topBar.getChildren().addAll(
                backButton,
                title);

        // =========================================
        // BACK ACTION
        // =========================================

        backButton.setOnAction(e -> {

            AdminLearning learning =
                    new AdminLearning();

            LoginPage.mainStage.setScene(
                    new Scene(
                            learning.getLearningPage(),
                            1100,
                            700));
        });

        // =========================================
        // COURSE INFORMATION
        // =========================================

        HBox courseInformation =
                new HBox(16);

        courseInformation.setAlignment(
                Pos.TOP_LEFT);

        VBox courseBasics =
                createCourseBasics();

        VBox courseThumbnail =
                createCourseThumbnail();

        VBox courseSettings =
                createCourseSettings();

        // =========================================
        // WIDTH
        // =========================================

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

        // =========================================
        // ADD TO ROOT
        // =========================================

        root.getChildren().addAll(
                topBar,
                courseInformation);

        // =========================================
        // SCENE
        // =========================================

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

        // =========================================
        // HEADING
        // =========================================

        Label heading =
                new Label("Course Basics");

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                        "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;");

        // =========================================
        // LINE
        // =========================================

        Separator separator =
                new Separator();

        separator.setMaxWidth(
                Double.MAX_VALUE);

        separator.setStyle(
                "-fx-background-color:#242B2C;");

        // =========================================
        // COURSE TITLE
        // =========================================

        Label courseTitleLabel =
                new Label("Course Title");

        courseTitleLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;");

        TextField courseTitle =
                new TextField();

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

        // =========================================
        // CATEGORY
        // =========================================

        HBox fields =
                new HBox(10);

        fields.setPrefWidth(
                Double.MAX_VALUE);

        VBox categoryBox =
                new VBox(5);

        HBox.setHgrow(
                categoryBox,
                Priority.ALWAYS);

        Label categoryLabel =
                new Label("Category");

        categoryLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;");

        ComboBox<String> category =
                new ComboBox<>();

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

        // =========================================
        // ADD TO CARD
        // =========================================

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

    private static VBox createCourseThumbnail() {

        VBox card =
                new VBox(10);

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

        // =========================================
        // HEADING
        // =========================================

        Label heading =
                new Label("▣  Course Thumbnail");

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;");

        // =========================================
        // UPLOAD AREA
        // =========================================

        VBox uploadArea =
                new VBox(7);

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

        // =========================================
        // ICON
        // =========================================

        Label icon =
                new Label("☁");

        icon.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:25px;");

        // =========================================
        // TEXT
        // =========================================

        Label uploadText =
                new Label(
                        "Drag and drop image here");

        uploadText.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:10px;");

        Label browseText =
                new Label(
                        "or click to browse");

        browseText.setStyle(
                "-fx-text-fill:#777777;" +
                        "-fx-font-size:8px;");

        uploadArea.getChildren().addAll(
                icon,
                uploadText,
                browseText);

        // =========================================
        // CLICK
        // =========================================

        uploadArea.setOnMouseClicked(e -> {

            System.out.println(
                    "Select course thumbnail");

            // Later:
            // FileChooser
            // ↓
            // Cloudinary
            // ↓
            // thumbnailUrl
        });

        // =========================================
        // ADD TO CARD
        // =========================================

        card.getChildren().addAll(
                heading,
                uploadArea);

        return card;
    }

    // =========================================================
    // COURSE SETTINGS
    // =========================================================

    private static VBox createCourseSettings() {

        VBox card =
                new VBox(8);

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

        // =========================================
        // HEADING
        // =========================================

        Label heading =
                new Label("Course Settings");

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                        "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;");

        // =========================================
        // SEPARATOR
        // =========================================

        Separator separator =
                new Separator();

        separator.setMaxWidth(
                Double.MAX_VALUE);

        separator.setStyle(
                "-fx-background-color:#242B2C;");

        // =========================================
        // DIFFICULTY
        // =========================================

        Label difficultyLabel =
                new Label("Difficulty Level");

        difficultyLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;");

        ToggleGroup difficultyGroup =
                new ToggleGroup();

        RadioButton beginner =
                new RadioButton("Beginner");

        RadioButton intermediate =
                new RadioButton("Intermediate");

        RadioButton advanced =
                new RadioButton("Advanced");

        beginner.setToggleGroup(
                difficultyGroup);

        intermediate.setToggleGroup(
                difficultyGroup);

        advanced.setToggleGroup(
                difficultyGroup);

        intermediate.setSelected(true);

        String radioStyle =
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:14px;";

        beginner.setStyle(
                radioStyle);

        intermediate.setStyle(
                radioStyle);

        advanced.setStyle(
                radioStyle);

        VBox difficultyBox =
                new VBox(4);

        difficultyBox.getChildren().addAll(
                difficultyLabel,
                beginner,
                intermediate,
                advanced);

        // =========================================
        // DURATION
        // =========================================

        Label durationLabel =
                new Label(
                        "Estimated Duration (Hours)");

        durationLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;");

        TextField duration =
                new TextField();

        duration.setPromptText(
                "e.g., 12");

        duration.setPrefHeight(32);

        duration.setMaxWidth(
                Double.MAX_VALUE);

        duration.setStyle(
                "-fx-background-color:#0D1213;" +
                        "-fx-text-fill:#EEEEEE;" +
                        "-fx-prompt-text-fill:#AAAAAA;" +
                        "-fx-border-color:#242B2C;" +
                        "-fx-border-radius:4;" +
                        "-fx-background-radius:4;" +
                        "-fx-font-size:14px;" +
                        "-fx-padding:8;");

        // =========================================
        // LANGUAGE
        // =========================================

        Label languageLabel =
                new Label("Language");

        languageLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;");

        ComboBox<String> language =
                new ComboBox<>();

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

        // =========================================
        // ADD TO CARD
        // =========================================

        card.getChildren().addAll(
                heading,
                separator,
                difficultyBox,
                durationLabel,
                duration,
                languageLabel,
                language);

        return card;
    }
}