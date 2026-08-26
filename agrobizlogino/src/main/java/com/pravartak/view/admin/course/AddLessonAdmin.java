package com.pravartak.view.admin.course;

import com.pravartak.controller.admincontroller.LessonController;
import com.pravartak.model.admin.Course;
import com.pravartak.model.admin.Lesson;
import com.pravartak.model.admin.Module;
import com.pravartak.view.login.LoginPage;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AddLessonAdmin {

        // =========================================================
        // DATA
        // =========================================================

        private final Course course;
        private final Module module;

        private final LessonController lessonController = new LessonController();

        // =========================================================
        // FIELDS
        // =========================================================

        private TextField titleField;
        private TextArea descriptionField;
        private ComboBox<String> typeBox;
        private TextField mediaField;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public AddLessonAdmin(
                        Course course,
                        Module module) {

                this.course = course;
                this.module = module;
        }

        // =========================================================
        // SCENE
        // =========================================================

        public Scene getAddLessonScene() {

                VBox root = new VBox(15);

                root.setPadding(
                                new Insets(20, 30, 20, 30));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox(15);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                Button back = new Button("← Back");

                back.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:7 14;" +
                                                "-fx-cursor:hand;");

                back.setOnAction(
                                e -> goBack());

                VBox heading = new VBox(3);

                Label title = new Label(
                                "Add Lesson");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                Label subtitle = new Label(
                                "Create a new lesson for this module.");

                subtitle.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;");

                heading.getChildren().addAll(
                                title,
                                subtitle);

                header.getChildren().addAll(
                                back,
                                heading);

                // =====================================================
                // INFORMATION
                // =====================================================

                HBox courseInfo = createCourseInfo();

                // =====================================================
                // CONTENT
                // =====================================================

                HBox content = new HBox(15);

                VBox lessonInfo = createLessonInformation();

                VBox settings = createLessonSettings();

                HBox.setHgrow(
                                lessonInfo,
                                Priority.ALWAYS);

                content.getChildren().addAll(
                                lessonInfo,
                                settings);

                // =====================================================
                // ACTIONS
                // =====================================================

                HBox actions = createActions();

                // =====================================================
                // PAGE
                // =====================================================

                VBox page = new VBox(15);

                page.getChildren().addAll(
                                courseInfo,
                                content,
                                actions);

                ScrollPane scroll = new ScrollPane(page);

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
                                header,
                                scroll);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // COURSE INFORMATION
        // =========================================================

        private HBox createCourseInfo() {

                HBox box = new HBox(10);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                box.setPadding(
                                new Insets(12));

                box.setStyle(
                                "-fx-background-color:#101612;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                Label courseLabel = new Label(
                                "Course: "
                                                + safe(course.getTitle()));

                courseLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                Label separator = new Label("•");

                separator.setStyle(
                                "-fx-text-fill:#555555;");

                Label moduleLabel = new Label(
                                "Module: "
                                                + safe(module.getTitle()));

                moduleLabel.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                box.getChildren().addAll(
                                courseLabel,
                                separator,
                                moduleLabel);

                return box;
        }

        // =========================================================
        // LESSON INFORMATION
        // =========================================================

        private VBox createLessonInformation() {

                VBox card = createCard();

                Label heading = createHeading("Lesson Information");

                Separator separator = new Separator();

                Label titleLabel = createFieldLabel("Lesson Title");

                titleField = new TextField();

                titleField.setPromptText(
                                "Enter lesson title");

                styleTextField(
                                titleField);

                Label descriptionLabel = createFieldLabel("Lesson Description");

                descriptionField = new TextArea();

                descriptionField.setPromptText(
                                "Describe what students will learn in this lesson...");

                descriptionField.setPrefRowCount(5);
                descriptionField.setWrapText(true);

                styleTextArea(
                                descriptionField);

                card.getChildren().addAll(
                                heading,
                                separator,
                                titleLabel,
                                titleField,
                                descriptionLabel,
                                descriptionField);

                return card;
        }

        // =========================================================
        // SETTINGS
        // =========================================================

        private VBox createLessonSettings() {

                VBox card = createCard();

                card.setPrefWidth(280);

                Label heading = createHeading("Lesson Settings");

                Separator separator = new Separator();

                Label typeLabel = createFieldLabel("Lesson Type");

                typeBox = new ComboBox<>();

                typeBox.getItems().addAll(
                                "VIDEO",
                                "READING",
                                "QUIZ",
                                "ACTIVITY");

                typeBox.setValue(
                                "VIDEO");

                styleComboBox(
                                typeBox);

                Label mediaLabel = createFieldLabel("Topic Media");

                mediaField = new TextField();

                mediaField.setPromptText(
                                "Image URL / media path");

                styleTextField(
                                mediaField);

                Label orderLabel = createFieldLabel("Lesson Order");

                Label order = new Label(
                                "Automatically assigned");

                order.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                card.getChildren().addAll(
                                heading,
                                separator,
                                typeLabel,
                                typeBox,
                                mediaLabel,
                                mediaField,
                                orderLabel,
                                order);

                return card;
        }

        // =========================================================
        // ACTIONS
        // =========================================================

        private HBox createActions() {

                HBox box = new HBox(10);

                box.setAlignment(
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
                                                "-fx-cursor:hand;");

                cancel.setOnAction(
                                e -> goBack());

                Button save = new Button("✓  Save Lesson");

                save.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 24;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                save.setOnAction(
                                e -> saveLesson());

                box.getChildren().addAll(
                                cancel,
                                save);

                return box;
        }

        // =========================================================
        // SAVE LESSON
        // =========================================================

        private void saveLesson() {

                if (course == null ||
                                module == null) {

                        System.out.println(
                                        "Course or module not found.");

                        return;
                }

                String title = titleField.getText().trim();

                String description = descriptionField.getText().trim();

                String type = typeBox.getValue();

                String media = mediaField.getText().trim();

                // =====================================================
                // VALIDATION
                // =====================================================

                if (title.isEmpty()) {

                        System.out.println(
                                        "Lesson title required.");

                        return;
                }

                if (description.isEmpty()) {

                        System.out.println(
                                        "Lesson description required.");

                        return;
                }

                // =====================================================
                // SAVE
                // =====================================================

                boolean saved = lessonController.addLesson(
                                module.getModuleId(),
                                title,
                                description,
                                type,
                                media);

                if (!saved) {

                        System.out.println(
                                        "Lesson could not be created.");

                        return;
                }

                System.out.println(
                                "Lesson created successfully.");

                // =====================================================
                // RETURN TO MODULE PAGE
                // =====================================================

                ModulePage modulePage = new ModulePage(course);

                LoginPage.mainStage.setScene(
                                modulePage.getModuleScene());
        }

        // =========================================================
        // BACK
        // =========================================================

        private void goBack() {

                ModulePage modulePage = new ModulePage(course);

                LoginPage.mainStage.setScene(
                                modulePage.getModuleScene());
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
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                return label;
        }

        // =========================================================
        // TEXT FIELD
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
                                                "-fx-padding:8;" +
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
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-padding:8;");

                area.setPrefHeight(150);
        }

        // =========================================================
        // COMBO BOX
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

        private String safe(String value) {

                return value == null ? "" : value;
        }
}