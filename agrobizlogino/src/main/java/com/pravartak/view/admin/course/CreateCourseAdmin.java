package com.pravartak.view.admin.course;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.pravartak.config.CloudinaryConfig;
import com.pravartak.controller.admincontroller.CategoryController;
import com.pravartak.controller.admincontroller.CourseController;
import com.pravartak.model.admin.Category;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.animation.PauseTransition;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

public class CreateCourseAdmin {

        public Scene createCouresScene;

        // =========================================================
        // CATEGORY CONTROLLER
        // =========================================================

        private final CategoryController categoryController = new CategoryController();

        // =========================================================
        // CLOUDINARY
        // =========================================================

        private final Cloudinary cloudinary;

        // =========================================================
        // FORM HOLDER
        // =========================================================

        private static class CourseForm {

                TextField title;

                ComboBox<Category> category;

                ComboBox<String> language;

                TextField duration;

                ToggleGroup difficultyGroup;

                // Local file selected by user
                File thumbnailFile;

                // Cloudinary URL
                String thumbnailUrl = "";
        }

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public CreateCourseAdmin() {

                cloudinary = CloudinaryConfig.getCloudinary();
        }

        // =========================================================
        // CREATE COURSE SCENE
        // =========================================================

        public Scene getCreateCourseScene() {

                VBox root = new VBox(18);

                root.setPadding(
                                new Insets(
                                                22,
                                                35,
                                                25,
                                                35));

                root.setAlignment(
                                Pos.TOP_LEFT);

                root.setFillWidth(true);

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                CourseForm form = new CourseForm();

                // =====================================================
                // TOP BAR
                // =====================================================

                HBox topBar = new HBox(12);

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = new Button("←  Back");

                backButton.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-border-color:#263332;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:7 14;" +
                                                "-fx-cursor:hand;");

                backButton.setOnMouseEntered(
                                e -> backButton.setStyle(
                                                "-fx-background-color:#13221D;" +
                                                                "-fx-text-fill:#68D34A;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-border-color:#68D34A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:7 14;" +
                                                                "-fx-cursor:hand;"));

                backButton.setOnMouseExited(
                                e -> backButton.setStyle(
                                                "-fx-background-color:#0D1213;" +
                                                                "-fx-text-fill:#AAAAAA;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-border-color:#263332;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:7 14;" +
                                                                "-fx-cursor:hand;"));

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label("Create New Course");

                title.setStyle(
                                "-fx-text-fill:#F1F5F3;" +
                                                "-fx-font-size:25px;" +
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
                //
                // IMPORTANT:
                // Changed from HBox to VBox.
                //
                // Order:
                // 1. Course Basics
                // 2. Course Thumbnail
                // 3. Course Settings
                //
                // =====================================================

                VBox courseInformation = new VBox(18);

                courseInformation.setFillWidth(true);
                courseInformation.setStyle("-fx-alignment: center");

                // =====================================================
                // COURSE BASICS
                // =====================================================

                VBox courseBasics = createCourseBasics(form);

                courseBasics.setMaxWidth(450);
                courseBasics.setMaxHeight(200);

                // =====================================================
                // COURSE THUMBNAIL
                // =====================================================

                VBox courseThumbnail = createCourseThumbnail(form);

                courseThumbnail.setMaxWidth(450);
                courseThumbnail.setMaxHeight(200);

                // =====================================================
                // COURSE SETTINGS
                // =====================================================

                VBox courseSettings = createCourseSettings(form);

                courseSettings.setMaxWidth(450);
                courseSettings.setMaxHeight(200);

                // =====================================================
                // ADD VERTICAL CARDS
                // =====================================================

                courseInformation.getChildren().addAll(
                                courseBasics,
                                courseThumbnail,
                                courseSettings);

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                HBox actionButtons = createActionButtons(form);

                // =====================================================
                // ROOT
                // =====================================================

                root.getChildren().addAll(
                                topBar,
                                courseInformation,
                                actionButtons);

                // =====================================================
                // SCROLLABLE ROOT
                // =====================================================

                javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();

                scrollPane.setContent(root);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-background:#080C0D;" +
                                                "-fx-control-inner-background:#080C0D;" +
                                                "-fx-border-color:transparent;");

                // =====================================================
                // SCENE
                // =====================================================

                createCouresScene = new Scene(
                                scrollPane,
                                900,
                                750);

                return createCouresScene;
        }

        // =========================================================
        // COURSE BASICS
        // =========================================================

        private VBox createCourseBasics(
                        CourseForm form) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(20));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color:#101716;" +
                                                "-fx-border-color:#263833;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                // =====================================================
                // HEADING
                // =====================================================

                Label heading = new Label("Course Basics");

                heading.setStyle(
                                "-fx-text-fill:#F1F5F3;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                Separator separator = new Separator();

                separator.setStyle(
                                "-fx-background-color:#294239;");

                // =====================================================
                // COURSE TITLE
                // =====================================================

                Label courseTitleLabel = new Label("Course Title");

                courseTitleLabel.setStyle(
                                "-fx-text-fill:#C7D1CC;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                form.title = new TextField();

                form.title.setPromptText(
                                "e.g., Advanced Hydroponics Systems");

                form.title.setPrefHeight(38);

                form.title.setMaxWidth(
                                Double.MAX_VALUE);

                form.title.setStyle(
                                "-fx-background-color:#0B1211;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#68736E;" +
                                                "-fx-border-color:#263833;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-padding:9;");

                // =====================================================
                // CATEGORY
                // =====================================================

                Label categoryLabel = new Label("Category");

                categoryLabel.setStyle(
                                "-fx-text-fill:#C7D1CC;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                form.category = new ComboBox<>();

                // =====================================================
                // LOAD CATEGORIES
                // =====================================================

                List<Category> categories = categoryController.getAllCategories();

                form.category
                                .getItems()
                                .setAll(categories);

                if (!categories.isEmpty()) {

                        form.category.setValue(
                                        categories.get(0));
                }

                form.category.setMaxWidth(
                                Double.MAX_VALUE);

                form.category.setPrefHeight(38);

                form.category.setStyle(
                                "-fx-background-color:#0B1211;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#263833;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:13px;");

                // =====================================================
                // ADD
                // =====================================================

                card.getChildren().addAll(
                                heading,
                                separator,
                                courseTitleLabel,
                                form.title,
                                categoryLabel,
                                form.category);

                return card;
        }

        // =========================================================
        // THUMBNAIL
        // =========================================================

        private VBox createCourseThumbnail(
                        CourseForm form) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(20));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color:#101716;" +
                                                "-fx-border-color:#263833;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                // =====================================================
                // HEADING
                // =====================================================

                Label heading = new Label(
                                " Course Thumbnail");

                heading.setStyle(
                                "-fx-text-fill:#F1F5F3;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                Separator separator = new Separator();

                separator.setStyle(
                                "-fx-background-color:#294239;");

                // =====================================================
                // UPLOAD AREA
                // =====================================================

                VBox uploadArea = new VBox(8);

                uploadArea.setAlignment(
                                Pos.CENTER);

                uploadArea.setPrefHeight(230);

                uploadArea.setMaxWidth(
                                Double.MAX_VALUE);

                uploadArea.setStyle(
                                "-fx-background-color:#0B1211;" +
                                                "-fx-border-color:#315045;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                Label icon = new Label("☁");

                icon.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:32px;");

                Label uploadText = new Label(
                                "Click to select image");

                uploadText.setStyle(
                                "-fx-text-fill:#9DA9A4;" +
                                                "-fx-font-size:12px;");

                Label supportedText = new Label(
                                "PNG, JPG, JPEG or WEBP");

                supportedText.setStyle(
                                "-fx-text-fill:#596660;" +
                                                "-fx-font-size:10px;");

                uploadArea.getChildren().addAll(
                                icon,
                                uploadText,
                                supportedText);

                // =====================================================
                // IMAGE SELECT
                // =====================================================

                uploadArea.setOnMouseClicked(e -> {

                        FileChooser chooser = new FileChooser();

                        chooser.setTitle(
                                        "Select Course Thumbnail");

                        chooser.getExtensionFilters()
                                        .add(
                                                        new FileChooser.ExtensionFilter(
                                                                        "Image Files",
                                                                        "*.png",
                                                                        "*.jpg",
                                                                        "*.jpeg",
                                                                        "*.webp"));

                        File file = chooser.showOpenDialog(
                                        LoginPage.mainStage);

                        if (file == null) {
                                return;
                        }

                        // =================================================
                        // STORE LOCAL FILE
                        // =================================================

                        form.thumbnailFile = file;

                        // =================================================
                        // PREVIEW IMAGE
                        // =================================================

                        try {

                                Image image = new Image(
                                                file.toURI().toString());

                                ImageView imageView = new ImageView(image);

                                imageView.setFitWidth(650);
                                imageView.setFitHeight(210);
                                imageView.setPreserveRatio(true);

                                uploadArea
                                                .getChildren()
                                                .clear();

                                uploadArea
                                                .getChildren()
                                                .add(imageView);

                        } catch (Exception ex) {

                                ex.printStackTrace();

                                showCourseStatusPopup(
                                                "Image Error",
                                                "Unable to preview the selected image.",
                                                false);
                        }
                });

                card.getChildren().addAll(
                                heading,
                                separator,
                                uploadArea);

                return card;
        }

        // =========================================================
        // SETTINGS
        // =========================================================

        private VBox createCourseSettings(
                        CourseForm form) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(20));

                card.setMaxWidth(
                                300);

                card.setStyle(
                                "-fx-background-color: #101716;" +
                                                "-fx-border-color: #263833;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                // =====================================================
                // HEADING
                // =====================================================

                Label heading = new Label("Course Settings");

                heading.setStyle(
                                "-fx-text-fill: #F1F5F3;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                Separator separator = new Separator();

                separator.setStyle(
                                "-fx-background-color:#294239;");

                // =====================================================
                // DIFFICULTY
                // =====================================================

                Label difficultyLabel = new Label("Difficulty Level");

                difficultyLabel.setStyle(
                                "-fx-text-fill:#C7D1CC;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                form.difficultyGroup = new ToggleGroup();

                RadioButton beginner = new RadioButton("Beginner");

                RadioButton intermediate = new RadioButton("Intermediate");

                RadioButton advanced = new RadioButton("Advanced");

                beginner.setToggleGroup(
                                form.difficultyGroup);

                intermediate.setToggleGroup(
                                form.difficultyGroup);

                advanced.setToggleGroup(
                                form.difficultyGroup);

                intermediate.setSelected(true);

                String radioStyle = "-fx-text-fill:#B8C4BE;" +
                                "-fx-font-size:13px;" +
                                "-fx-padding:4 0;";

                beginner.setStyle(
                                radioStyle);

                intermediate.setStyle(
                                radioStyle);

                advanced.setStyle(
                                radioStyle);

                // =====================================================
                // LANGUAGE
                // =====================================================

                Label languageLabel = new Label("Language");

                languageLabel.setStyle(
                                "-fx-text-fill:#C7D1CC;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                form.language = new ComboBox<>();

                form.language.getItems().addAll(
                                "English",
                                "Hindi",
                                "Marathi");

                form.language.setValue(
                                "English");

                form.language.setMaxWidth(
                                300);

                form.language.setPrefHeight(25);

                form.language.setStyle(
                                "-fx-background-color:#0B1211;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#263833;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:13px;");

                card.getChildren().addAll(
                                heading,
                                separator,
                                difficultyLabel,
                                beginner,
                                intermediate,
                                advanced,
                                languageLabel,
                                form.language);

                return card;
        }

        // =========================================================
        // ACTION BUTTONS
        // =========================================================

        private HBox createActionButtons(
                        CourseForm form) {

                HBox buttons = new HBox(12);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                // =====================================================
                // SPACER
                // =====================================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =====================================================
                // DRAFT BUTTON
                // =====================================================

                Button draftButton = new Button("Save as Draft");

                draftButton.setPrefHeight(38);

                draftButton.setStyle(
                                "-fx-background-color:#101716;" +
                                                "-fx-text-fill:#AEB9B4;" +
                                                "-fx-border-color:#304039;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8 20;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-cursor:hand;");

                draftButton.setOnMouseEntered(
                                e -> draftButton.setStyle(
                                                "-fx-background-color:#16221E;" +
                                                                "-fx-text-fill:#68D34A;" +
                                                                "-fx-border-color:#68D34A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:8 20;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-cursor:hand;"));

                draftButton.setOnMouseExited(
                                e -> draftButton.setStyle(
                                                "-fx-background-color:#101716;" +
                                                                "-fx-text-fill:#AEB9B4;" +
                                                                "-fx-border-color:#304039;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:8 20;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-cursor:hand;"));

                // =====================================================
                // PUBLISH BUTTON
                // =====================================================

                Button publishButton = new Button("Publish Course");

                publishButton.setPrefHeight(38);

                publishButton.setStyle(
                                "-fx-background-color:#53D74A;" +
                                                "-fx-text-fill:#07100B;" +
                                                "-fx-border-color:#53D74A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8 22;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-cursor:hand;");

                publishButton.setOnMouseEntered(
                                e -> publishButton.setStyle(
                                                "-fx-background-color:#68E65A;" +
                                                                "-fx-text-fill:#07100B;" +
                                                                "-fx-border-color:#68E65A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:8 22;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-cursor:hand;"));

                publishButton.setOnMouseExited(
                                e -> publishButton.setStyle(
                                                "-fx-background-color:#53D74A;" +
                                                                "-fx-text-fill:#07100B;" +
                                                                "-fx-border-color:#53D74A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:8 22;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-cursor:hand;"));

                // =====================================================
                // DRAFT ACTION
                // =====================================================

                draftButton.setOnAction(e -> {

                        saveCourse(
                                        form,
                                        false);
                });

                // =====================================================
                // PUBLISH ACTION
                // =====================================================

                publishButton.setOnAction(e -> {

                        saveCourse(
                                        form,
                                        true);
                });

                buttons.getChildren().addAll(
                                spacer,
                                draftButton,
                                publishButton);

                return buttons;
        }

        // =========================================================
        // SAVE COURSE
        // =========================================================

        private void saveCourse(
                        CourseForm form,
                        boolean published) {

                try {

                        // =================================================
                        // COURSE TITLE
                        // =================================================

                        String title = form.title
                                        .getText()
                                        .trim();

                        if (title.isEmpty()) {

                                showCourseStatusPopup(
                                                "Missing Information",
                                                "Please enter course title.",
                                                false);

                                return;
                        }

                        // =================================================
                        // CATEGORY
                        // =================================================

                        Category selectedCategory = form.category.getValue();

                        if (selectedCategory == null) {

                                showCourseStatusPopup(
                                                "Missing Information",
                                                "Please select a category.",
                                                false);

                                return;
                        }

                        String category = selectedCategory
                                        .getCategoryName();

                        // =================================================
                        // LANGUAGE
                        // =================================================

                        String language = form.language.getValue();

                        // =================================================
                        // DIFFICULTY
                        // =================================================

                        RadioButton selected = (RadioButton) form.difficultyGroup
                                        .getSelectedToggle();

                        String difficulty = selected != null
                                        ? selected.getText()
                                        : "Intermediate";

                        // =================================================
                        // CLOUDINARY UPLOAD
                        // =================================================

                        String thumbnailUrl = "";

                        if (form.thumbnailFile != null) {

                                showCourseStatusPopup(
                                                "Uploading Image",
                                                "Uploading course thumbnail...",
                                                true);

                                thumbnailUrl = uploadThumbnailToCloudinary(
                                                form.thumbnailFile);

                                if (thumbnailUrl == null ||
                                                thumbnailUrl.isEmpty()) {

                                        showCourseStatusPopup(
                                                        "Upload Failed",
                                                        "Course thumbnail could not be uploaded.",
                                                        false);

                                        return;
                                }
                        }

                        // =================================================
                        // COURSE CONTROLLER
                        // =================================================

                        CourseController controller = new CourseController();

                        boolean success = controller.addCourse(
                                        title,
                                        category,
                                        difficulty,
                                        language,
                                        thumbnailUrl,
                                        published);

                        // =================================================
                        // SUCCESS
                        // =================================================

                        if (success) {

                                if (published) {

                                        showCourseStatusPopup(
                                                        "Course Published",
                                                        "Your course is now available to learners.",
                                                        true);

                                } else {

                                        showCourseStatusPopup(
                                                        "Draft Saved",
                                                        "Your course has been saved as a draft.",
                                                        true);
                                }

                                // =================================================
                                // RETURN TO COURSE PAGE
                                // =================================================

                                PauseTransition delay = new PauseTransition(
                                                Duration.seconds(1.3));

                                delay.setOnFinished(e -> {

                                        AdminPage adminPage = new AdminPage();

                                        LoginPage.mainStage.setScene(
                                                        adminPage.getAdminPage(
                                                                        "Manage Course"));
                                });

                                delay.play();

                        } else {

                                showCourseStatusPopup(
                                                "Error",
                                                "Course could not be saved.",
                                                false);
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        showCourseStatusPopup(
                                        "Error",
                                        "Something went wrong while saving the course.",
                                        false);
                }
        }

        // =========================================================
        // CLOUDINARY UPLOAD
        // =========================================================

        private String uploadThumbnailToCloudinary(
                        File file) {

                try {

                        if (file == null ||
                                        !file.exists()) {

                                System.out.println(
                                                "Thumbnail file does not exist.");

                                return null;
                        }

                        // =================================================
                        // UPLOAD OPTIONS
                        // =================================================

                        Map<String, Object> options = ObjectUtils.asMap(
                                        "folder",
                                        "agrobiz/courses",
                                        "resource_type",
                                        "image");

                        // =================================================
                        // UPLOAD
                        // =================================================

                        Map<?, ?> result = cloudinary
                                        .uploader()
                                        .upload(
                                                        file,
                                                        options);

                        // =================================================
                        // SECURE URL
                        // =================================================

                        Object secureUrl = result.get("secure_url");

                        if (secureUrl == null) {

                                System.out.println(
                                                "Cloudinary secure_url is null.");

                                return null;
                        }

                        String url = secureUrl.toString();

                        System.out.println(
                                        "Course thumbnail uploaded successfully.");

                        System.out.println(
                                        "Cloudinary URL: "
                                                        + url);

                        return url;

                } catch (Exception e) {

                        System.out.println(
                                        "Cloudinary upload failed.");

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // STATUS POPUP
        // =========================================================

        private void showCourseStatusPopup(
                        String title,
                        String message,
                        boolean success) {

                Popup popup = new Popup();

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
                                success
                                                ? "✓"
                                                : "!");

                icon.setPrefSize(
                                42,
                                42);

                icon.setAlignment(
                                Pos.CENTER);

                icon.setStyle(
                                success
                                                ? "-fx-background-color:#173A26;" +
                                                                "-fx-text-fill:#68D34A;" +
                                                                "-fx-font-size:22px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-background-radius:50%;"
                                                : "-fx-background-color:#3A211F;" +
                                                                "-fx-text-fill:#E57373;" +
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

                messageLabel.setWrapText(
                                true);

                messageLabel.setStyle(
                                "-fx-text-fill:#AAB5B0;" +
                                                "-fx-font-size:11px;");

                box.getChildren().addAll(
                                icon,
                                titleLabel,
                                messageLabel);

                // =====================================================
                // POPUP STYLE
                // =====================================================

                box.setStyle(
                                success
                                                ? "-fx-background-color:#101716;" +
                                                                "-fx-border-color:#53D74A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:9;" +
                                                                "-fx-background-radius:9;"
                                                : "-fx-background-color:#101716;" +
                                                                "-fx-border-color:#A84B45;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:9;" +
                                                                "-fx-background-radius:9;");

                popup.getContent()
                                .add(box);

                Window window = LoginPage.mainStage;

                popup.show(
                                window,
                                window.getX()
                                                + (window.getWidth() - 300) / 2,
                                window.getY()
                                                + (window.getHeight() - 130) / 2);

                PauseTransition delay = new PauseTransition(
                                Duration.seconds(1.3));

                delay.setOnFinished(
                                e -> popup.hide());

                delay.play();
        }
}