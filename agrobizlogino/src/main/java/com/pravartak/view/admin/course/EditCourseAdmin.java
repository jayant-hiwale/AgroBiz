package com.pravartak.view.admin.course;

import java.io.File;
import java.util.Map;

import com.cloudinary.utils.ObjectUtils;
import com.pravartak.config.CloudinaryConfig;
import com.pravartak.controller.admincontroller.CourseController;
import com.pravartak.model.admin.Course;
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
import javafx.scene.control.ScrollPane;
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

public class EditCourseAdmin {

        // =========================================================
        // COURSE
        // =========================================================

        private final Course course;

        // =========================================================
        // CONTROLLER
        // =========================================================

        private final CourseController courseController = new CourseController();

        // =========================================================
        // FORM FIELDS
        // =========================================================

        private TextField titleField;

        private ComboBox<String> categoryBox;

        private ComboBox<String> languageBox;

        private ComboBox<String> statusBox;

        private ToggleGroup difficultyGroup;

        // =========================================================
        // THUMBNAIL
        // =========================================================

        /*
         * Stores the currently selected image.
         *
         * If the user selects a new image:
         * local file path is stored temporarily here.
         *
         * If the course already has an image:
         * Cloudinary URL is stored here.
         */
        private String thumbnailPath = "";

        /*
         * True only when the user selected a NEW image.
         *
         * This is important because we don't want to upload the
         * existing Cloudinary URL again.
         */
        private boolean newThumbnailSelected = false;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public EditCourseAdmin(Course course) {

                this.course = course;

                if (course != null) {

                        thumbnailPath = safe(course.getThumbnailUrl());
                }
        }

        // =========================================================
        // GET EDIT COURSE SCENE
        // =========================================================

        public Scene getEditCourseScene() {

                VBox root = new VBox(16);

                root.setPadding(
                                new Insets(
                                                15,
                                                30,
                                                18,
                                                30));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // TOP BAR
                // =====================================================

                HBox topBar = createTopBar();

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                HBox content = new HBox(16);

                content.setAlignment(
                                Pos.TOP_LEFT);

                VBox basicInformation = createBasicInformation();

                VBox thumbnailSection = createThumbnailSection();

                VBox settingsSection = createSettingsSection();

                basicInformation.setPrefWidth(500);
                basicInformation.setMinWidth(500);

                thumbnailSection.setPrefWidth(300);
                thumbnailSection.setMinWidth(300);

                settingsSection.setPrefWidth(260);
                settingsSection.setMinWidth(260);

                content.getChildren().addAll(
                                basicInformation,
                                thumbnailSection,
                                settingsSection);

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                HBox actionButtons = createActionButtons();

                // =====================================================
                // PAGE CONTENT
                // =====================================================

                VBox pageContent = new VBox(16);

                pageContent.setPadding(
                                new Insets(
                                                0,
                                                5,
                                                20,
                                                0));

                pageContent.getChildren().addAll(
                                content,
                                actionButtons);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(pageContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setFitToHeight(false);

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
                                scrollPane);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // TOP BAR
        // =========================================================

        private HBox createTopBar() {

                HBox topBar = new HBox(12);

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // BACK
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

                backButton.setOnAction(
                                e -> goBack());

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label("Edit Course");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // COURSE ID
                // =====================================================

                Label courseId = new Label(
                                course != null
                                                ? "Course ID: "
                                                                + course.getCourseId()
                                                : "");

                courseId.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                topBar.getChildren().addAll(
                                backButton,
                                title,
                                courseId);

                return topBar;
        }

        // =========================================================
        // BASIC INFORMATION
        // =========================================================

        private VBox createBasicInformation() {

                VBox card = createCard();

                Label heading = createHeading(
                                "Course Information");

                Separator separator = new Separator();

                // =====================================================
                // TITLE
                // =====================================================

                Label titleLabel = createFieldLabel(
                                "Course Title");

                titleField = new TextField();

                titleField.setText(
                                course != null
                                                ? safe(course.getTitle())
                                                : "");

                titleField.setPromptText(
                                "Enter course title");

                styleTextField(
                                titleField);

                // =====================================================
                // CATEGORY
                // =====================================================

                Label categoryLabel = createFieldLabel(
                                "Category");

                categoryBox = new ComboBox<>();

                categoryBox.getItems().addAll(
                                "Crop Farming",
                                "Water Management",
                                "Hydroponics",
                                "Organic Farming");

                if (course != null &&
                                course.getCategory() != null &&
                                !course.getCategory()
                                                .trim()
                                                .isEmpty()) {

                        categoryBox.setValue(
                                        course.getCategory());

                } else {

                        categoryBox.setValue(
                                        "Crop Farming");
                }

                styleComboBox(
                                categoryBox);

                // =====================================================
                // ADD
                // =====================================================

                card.getChildren().addAll(
                                heading,
                                separator,
                                titleLabel,
                                titleField,
                                categoryLabel,
                                categoryBox);

                return card;
        }

        // =========================================================
        // THUMBNAIL SECTION
        // =========================================================

        private VBox createThumbnailSection() {

                VBox card = createCard();

                Label heading = createHeading(
                                "Course Thumbnail");

                VBox uploadArea = new VBox(8);

                uploadArea.setAlignment(
                                Pos.CENTER);

                uploadArea.setPrefHeight(220);

                uploadArea.setMinHeight(220);

                uploadArea.setMaxHeight(220);

                uploadArea.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // LOAD CURRENT IMAGE
                // =====================================================

                loadThumbnail(
                                uploadArea);

                // =====================================================
                // CLICK TO CHANGE
                // =====================================================

                uploadArea.setOnMouseClicked(
                                e -> selectThumbnail(
                                                uploadArea));

                // =====================================================
                // HINT
                // =====================================================

                Label hint = new Label(
                                "Click thumbnail to change image");

                hint.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:9px;");

                hint.setWrapText(true);

                hint.setAlignment(
                                Pos.CENTER);

                hint.setMaxWidth(
                                Double.MAX_VALUE);

                card.getChildren().addAll(
                                heading,
                                uploadArea,
                                hint);

                return card;
        }

        // =========================================================
        // SELECT THUMBNAIL
        // =========================================================

        private void selectThumbnail(
                        VBox uploadArea) {

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

                if (file != null) {

                        /*
                         * Store LOCAL path temporarily.
                         *
                         * It will be uploaded to Cloudinary
                         * when Save Changes is pressed.
                         */
                        thumbnailPath = file.getAbsolutePath();

                        newThumbnailSelected = true;

                        // -------------------------------------------------
                        // Show preview
                        // -------------------------------------------------

                        loadThumbnail(
                                        uploadArea);
                }
        }

        // =========================================================
        // LOAD THUMBNAIL
        // =========================================================

        private void loadThumbnail(
                        VBox uploadArea) {

                uploadArea.getChildren()
                                .clear();

                // =====================================================
                // NO IMAGE
                // =====================================================

                if (thumbnailPath == null ||
                                thumbnailPath.trim().isEmpty()) {

                        showThumbnailPlaceholder(
                                        uploadArea);

                        return;
                }

                try {

                        Image image;

                        // =================================================
                        // CLOUDINARY / HTTP IMAGE
                        // =================================================

                        if (thumbnailPath.startsWith(
                                        "http://") ||
                                        thumbnailPath.startsWith(
                                                        "https://")) {

                                image = new Image(
                                                thumbnailPath,
                                                260,
                                                200,
                                                false,
                                                true,
                                                true);
                        }

                        // =================================================
                        // LOCAL IMAGE
                        // =================================================

                        else {

                                File file = new File(
                                                thumbnailPath);

                                if (!file.exists()) {

                                        showThumbnailPlaceholder(
                                                        uploadArea);

                                        return;
                                }

                                image = new Image(
                                                file.toURI()
                                                                .toString(),
                                                260,
                                                200,
                                                false,
                                                true,
                                                true);
                        }

                        // =================================================
                        // IMAGE VALID
                        // =================================================

                        if (!image.isError()) {

                                ImageView imageView = new ImageView(
                                                image);

                                imageView.setFitWidth(
                                                260);

                                imageView.setFitHeight(
                                                200);

                                imageView.setPreserveRatio(
                                                true);

                                imageView.setSmooth(
                                                true);

                                uploadArea.getChildren()
                                                .add(
                                                                imageView);

                                return;
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                showThumbnailPlaceholder(
                                uploadArea);
        }

        // =========================================================
        // THUMBNAIL PLACEHOLDER
        // =========================================================

        private void showThumbnailPlaceholder(
                        VBox uploadArea) {

                Label icon = new Label("☁");

                icon.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:30px;");

                Label text = new Label(
                                "Click to select image");

                text.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:10px;");

                uploadArea.getChildren()
                                .addAll(
                                                icon,
                                                text);
        }

        // =========================================================
        // SETTINGS
        // =========================================================

        private VBox createSettingsSection() {

                VBox card = createCard();

                Label heading = createHeading(
                                "Course Settings");

                Separator separator = new Separator();

                // =====================================================
                // DIFFICULTY
                // =====================================================

                Label difficultyLabel = createFieldLabel(
                                "Difficulty Level");

                difficultyGroup = new ToggleGroup();

                RadioButton beginner = new RadioButton(
                                "Beginner");

                RadioButton intermediate = new RadioButton(
                                "Intermediate");

                RadioButton advanced = new RadioButton(
                                "Advanced");

                beginner.setToggleGroup(
                                difficultyGroup);

                intermediate.setToggleGroup(
                                difficultyGroup);

                advanced.setToggleGroup(
                                difficultyGroup);

                String difficulty = course != null
                                ? safe(course.getDifficulty())
                                : "Intermediate";

                if (difficulty.equalsIgnoreCase(
                                "Beginner")) {

                        beginner.setSelected(
                                        true);

                } else if (difficulty.equalsIgnoreCase(
                                "Advanced")) {

                        advanced.setSelected(
                                        true);

                } else {

                        intermediate.setSelected(
                                        true);
                }

                String radioStyle = "-fx-text-fill:#AAAAAA;" +
                                "-fx-font-size:13px;";

                beginner.setStyle(
                                radioStyle);

                intermediate.setStyle(
                                radioStyle);

                advanced.setStyle(
                                radioStyle);

                // =====================================================
                // LANGUAGE
                // =====================================================

                Label languageLabel = createFieldLabel(
                                "Language");

                languageBox = new ComboBox<>();

                languageBox.getItems().addAll(
                                "English",
                                "Hindi",
                                "Marathi");

                String language = course != null
                                ? safe(course.getLanguage())
                                : "English";

                if (language.isEmpty()) {

                        language = "English";
                }

                languageBox.setValue(
                                language);

                styleComboBox(
                                languageBox);

                // =====================================================
                // STATUS
                // =====================================================

                Label statusLabel = createFieldLabel(
                                "Course Status");

                statusBox = new ComboBox<>();

                statusBox.getItems().addAll(
                                "Published",
                                "Draft");

                statusBox.setValue(
                                course != null &&
                                                course.getStatus()
                                                                ? "Published"
                                                                : "Draft");

                styleComboBox(
                                statusBox);

                // =====================================================
                // ADD
                // =====================================================

                card.getChildren().addAll(
                                heading,
                                separator,
                                difficultyLabel,
                                beginner,
                                intermediate,
                                advanced,
                                languageLabel,
                                languageBox,
                                statusLabel,
                                statusBox);

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
                // CANCEL
                // =====================================================

                Button cancel = new Button("Cancel");

                cancel.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-padding:8 20;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                cancel.setOnAction(
                                e -> goBack());

                // =====================================================
                // SAVE
                // =====================================================

                Button save = new Button(
                                "✓  Save Changes");

                save.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 22;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                save.setOnAction(
                                e -> saveChanges());

                buttons.getChildren().addAll(
                                cancel,
                                save);

                return buttons;
        }

        // =========================================================
        // SAVE CHANGES
        // =========================================================

        private void saveChanges() {

                try {

                        // =================================================
                        // COURSE CHECK
                        // =================================================

                        if (course == null) {

                                showPopup(
                                                "Error",
                                                "Course information not found.",
                                                false);

                                return;
                        }

                        // =================================================
                        // TITLE
                        // =================================================

                        String title = titleField
                                        .getText()
                                        .trim();

                        if (title.isEmpty()) {

                                showPopup(
                                                "Missing Information",
                                                "Please enter course title.",
                                                false);

                                titleField.requestFocus();

                                return;
                        }

                        // =================================================
                        // CATEGORY
                        // =================================================

                        String category = categoryBox.getValue();

                        if (category == null ||
                                        category.trim().isEmpty()) {

                                showPopup(
                                                "Missing Information",
                                                "Please select a category.",
                                                false);

                                return;
                        }

                        // =================================================
                        // LANGUAGE
                        // =================================================

                        String language = languageBox.getValue();

                        if (language == null ||
                                        language.trim().isEmpty()) {

                                language = "English";
                        }

                        // =================================================
                        // DIFFICULTY
                        // =================================================

                        RadioButton selected = (RadioButton) difficultyGroup
                                        .getSelectedToggle();

                        String difficulty = selected != null
                                        ? selected.getText()
                                        : "Intermediate";

                        // =================================================
                        // STATUS
                        // =================================================

                        boolean published = "Published".equals(
                                        statusBox.getValue());

                        // =================================================
                        // CLOUDINARY IMAGE
                        // =================================================

                        String finalThumbnailUrl = thumbnailPath;

                        /*
                         * Only upload when the user selected
                         * a NEW local image.
                         */
                        if (newThumbnailSelected) {

                                showPopup(
                                                "Uploading Image",
                                                "Uploading thumbnail to Cloudinary...",
                                                true);

                                finalThumbnailUrl = uploadThumbnailToCloudinary(
                                                thumbnailPath);

                                /*
                                 * Upload failed.
                                 */
                                if (finalThumbnailUrl == null ||
                                                finalThumbnailUrl
                                                                .trim()
                                                                .isEmpty()) {

                                        showPopup(
                                                        "Upload Failed",
                                                        "Course thumbnail could not be uploaded to Cloudinary.",
                                                        false);

                                        return;
                                }
                        }

                        // =================================================
                        // UPDATE COURSE OBJECT
                        // =================================================

                        course.setTitle(
                                        title);

                        course.setCategory(
                                        category);

                        course.setDifficulty(
                                        difficulty);

                        course.setLanguage(
                                        language);

                        course.setThumbnailUrl(
                                        finalThumbnailUrl);

                        course.setStatus(
                                        published);

                        // =================================================
                        // UPDATE FIRESTORE
                        // =================================================

                        boolean success = courseController.updateCourse(
                                        course);

                        // =================================================
                        // SUCCESS
                        // =================================================

                        if (success) {

                                showPopup(
                                                "Course Updated",
                                                "Course details have been updated successfully.",
                                                true);

                                PauseTransition delay = new PauseTransition(
                                                Duration.seconds(
                                                                1.5));

                                delay.setOnFinished(
                                                e -> goBack());

                                delay.play();

                        } else {

                                showPopup(
                                                "Update Failed",
                                                "Course could not be updated.",
                                                false);
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        showPopup(
                                        "Error",
                                        "Something went wrong while updating the course.",
                                        false);
                }
        }

        // =========================================================
        // CLOUDINARY UPLOAD
        // =========================================================

        private String uploadThumbnailToCloudinary(
                        String filePath) {

                try {

                        // =================================================
                        // FILE CHECK
                        // =================================================

                        if (filePath == null ||
                                        filePath.trim().isEmpty()) {

                                return "";
                        }

                        File file = new File(filePath);

                        if (!file.exists()) {

                                System.out.println(
                                                "Thumbnail file not found: "
                                                                + filePath);

                                return "";
                        }

                        // =================================================
                        // CLOUDINARY
                        // =================================================

                        System.out.println(
                                        "Uploading course thumbnail to Cloudinary...");

                        /*
                         * Uses your existing CloudinaryConfig.
                         *
                         * No CloudinaryService is required.
                         */
                        Map<?, ?> result = CloudinaryConfig
                                        .getCloudinary()
                                        .uploader()
                                        .upload(
                                                        file,
                                                        ObjectUtils.asMap(
                                                                        "folder",
                                                                        "agrobiz/courses"));

                        // =================================================
                        // GET SECURE URL
                        // =================================================

                        Object secureUrl = result.get(
                                        "secure_url");

                        if (secureUrl != null) {

                                String url = secureUrl.toString();

                                System.out.println(
                                                "Course thumbnail uploaded successfully.");

                                System.out.println(
                                                "Cloudinary URL: "
                                                                + url);

                                return url;
                        }

                        System.out.println(
                                        "Cloudinary upload completed but secure_url was not returned.");

                        return "";

                } catch (Exception e) {

                        System.out.println(
                                        "Cloudinary upload failed.");

                        e.printStackTrace();

                        return "";
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
                                new Insets(16));

                card.setStyle(
                                "-fx-background-color:#101516;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

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
                                                "-fx-font-size:15px;" +
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
                                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;");

                return label;
        }

        // =========================================================
        // TEXT FIELD STYLE
        // =========================================================

        private void styleTextField(
                        TextField field) {

                field.setPrefHeight(
                                34);

                field.setMaxWidth(
                                Double.MAX_VALUE);

                field.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#777777;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-padding:8;");
        }

        // =========================================================
        // COMBO BOX STYLE
        // =========================================================

        private void styleComboBox(
                        ComboBox<String> box) {

                box.setPrefHeight(
                                34);

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;");
        }

        // =========================================================
        // SAFE STRING
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

                box.setPrefWidth(
                                320);

                box.setPrefHeight(
                                135);

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

                // =====================================================
                // TITLE
                // =====================================================

                Label titleLabel = new Label(
                                title);

                titleLabel.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // MESSAGE
                // =====================================================

                Label messageLabel = new Label(
                                message);

                messageLabel.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:11px;");

                messageLabel.setWrapText(
                                true);

                messageLabel.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // ADD
                // =====================================================

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

                popup.getContent()
                                .add(box);

                // =====================================================
                // SHOW
                // =====================================================

                Window window = LoginPage.mainStage;

                popup.show(
                                window,
                                window.getX()
                                                + (window.getWidth()
                                                                - 320) / 2,
                                window.getY()
                                                + (window.getHeight()
                                                                - 135) / 2);

                // =====================================================
                // AUTO HIDE
                // =====================================================

                PauseTransition delay = new PauseTransition(
                                Duration.seconds(1.5));

                delay.setOnFinished(
                                e -> popup.hide());

                delay.play();
        }
}