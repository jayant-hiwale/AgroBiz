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

                VBox root = new VBox(16);

                root.setPadding(
                                new Insets(
                                                15,
                                                30,
                                                18,
                                                30));

                root.setAlignment(
                                Pos.TOP_LEFT);

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

                Button backButton = new Button("← Back");

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

                VBox courseBasics = createCourseBasics(form);

                VBox courseThumbnail = createCourseThumbnail(form);

                VBox courseSettings = createCourseSettings(form);

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

                HBox actionButtons = createActionButtons(form);

                // =====================================================
                // ROOT
                // =====================================================

                root.getChildren().addAll(
                                topBar,
                                courseInformation,
                                actionButtons);

                createCouresScene = new Scene(
                                root,
                                1100,
                                700);

                return createCouresScene;
        }

        // =========================================================
        // COURSE BASICS
        // =========================================================

        private VBox createCourseBasics(
                        CourseForm form) {

                VBox card = new VBox(10);

                card.setPadding(
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                Label heading = new Label("Course Basics");

                heading.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                Separator separator = new Separator();

                // =====================================================
                // COURSE TITLE
                // =====================================================

                Label courseTitleLabel = new Label("Course Title");

                courseTitleLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                form.title = new TextField();

                form.title.setPromptText(
                                "e.g., Advanced Hydroponics Systems");

                form.title.setPrefHeight(32);

                form.title.setStyle(
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

                Label categoryLabel = new Label("Category");

                categoryLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                form.category = new ComboBox<>();

                // =====================================================
                // LOAD CATEGORIES
                // =====================================================

                List<Category> categories = categoryController
                                .getAllCategories();

                form.category
                                .getItems()
                                .setAll(categories);

                if (!categories.isEmpty()) {

                        form.category.setValue(
                                        categories.get(0));
                }

                form.category.setMaxWidth(
                                Double.MAX_VALUE);

                form.category.setPrefHeight(32);

                form.category.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;");

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

                VBox card = new VBox(10);

                card.setPadding(
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                Label heading = new Label(
                                "▣  Course Thumbnail");

                heading.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                VBox uploadArea = new VBox(7);

                uploadArea.setAlignment(
                                Pos.CENTER);

                uploadArea.setPrefHeight(170);

                uploadArea.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;");

                Label icon = new Label("☁");

                icon.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:25px;");

                Label uploadText = new Label(
                                "Click to select image");

                uploadText.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:10px;");

                uploadArea.getChildren().addAll(
                                icon,
                                uploadText);

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
                                                file.toURI()
                                                                .toString());

                                ImageView imageView = new ImageView(image);

                                imageView.setFitWidth(240);
                                imageView.setFitHeight(150);
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
                                uploadArea);

                return card;
        }

        // =========================================================
        // SETTINGS
        // =========================================================

        private VBox createCourseSettings(
                        CourseForm form) {

                VBox card = new VBox(8);

                card.setPadding(
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                Label heading = new Label(
                                "Course Settings");

                heading.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                Separator separator = new Separator();

                // =====================================================
                // DIFFICULTY
                // =====================================================

                Label difficultyLabel = new Label(
                                "Difficulty Level");

                difficultyLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
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

                String radioStyle = "-fx-text-fill:#AAAAAA;" +
                                "-fx-font-size:14px;";

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
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                form.language = new ComboBox<>();

                form.language.getItems().addAll(
                                "English",
                                "Hindi",
                                "Marathi");

                form.language.setValue(
                                "English");

                form.language.setMaxWidth(
                                Double.MAX_VALUE);

                form.language.setPrefHeight(32);

                form.language.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;");

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

                HBox buttons = new HBox(10);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                Button draftButton = new Button("Save as Draft");

                Button publishButton = new Button("Publish Course");

                draftButton.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-padding:7 18;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                publishButton.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-padding:7 20;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // DRAFT
                // =====================================================

                draftButton.setOnAction(e -> {

                        saveCourse(
                                        form,
                                        false);
                });

                // =====================================================
                // PUBLISH
                // =====================================================

                publishButton.setOnAction(e -> {

                        saveCourse(
                                        form,
                                        true);
                });

                buttons.getChildren().addAll(
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
                                                Duration.seconds(
                                                                1.3));

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

                        Map<?, ?> result = cloudinary.uploader().upload(
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
                                "-fx-background-color:#245D35;" +
                                                "-fx-text-fill:#68D34A;" +
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

                box.getChildren().addAll(
                                icon,
                                titleLabel,
                                messageLabel);

                box.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

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