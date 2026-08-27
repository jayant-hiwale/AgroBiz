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

public class EditLessonAdmin {

    // =========================================================
    // DATA
    // =========================================================

    private final Course course;
    private final Module module;
    private final Lesson lesson;

    private final LessonController lessonController =
            new LessonController();

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

    public EditLessonAdmin(
            Course course,
            Module module,
            Lesson lesson) {

        this.course = course;
        this.module = module;
        this.lesson = lesson;
    }

    // =========================================================
    // SCENE
    // =========================================================

    public Scene getEditLessonScene() {

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
                "Edit Lesson");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;");

        Label subtitle = new Label(
                "Edit the content of this lesson.");

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
        // COURSE / MODULE INFO
        // =====================================================

        HBox courseInfo = createCourseInfo();

        // =====================================================
        // CONTENT
        // =====================================================

        HBox content = new HBox(15);

        VBox lessonInformation =
                createLessonInformation();

        VBox lessonSettings =
                createLessonSettings();

        HBox.setHgrow(
                lessonInformation,
                Priority.ALWAYS);

        content.getChildren().addAll(
                lessonInformation,
                lessonSettings);

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
                "Course: " +
                safe(course.getTitle()));

        courseLabel.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;");

        Label separator = new Label("•");

        separator.setStyle(
                "-fx-text-fill:#555555;");

        Label moduleLabel = new Label(
                "Module: " +
                safe(module.getTitle()));

        moduleLabel.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;");

        Label separator2 = new Label("•");

        separator2.setStyle(
                "-fx-text-fill:#555555;");

        Label lessonLabel = new Label(
                "Lesson: " +
                safe(lesson.getTitle()));

        lessonLabel.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;");

        box.getChildren().addAll(
                courseLabel,
                separator,
                moduleLabel,
                separator2,
                lessonLabel);

        return box;
    }

    // =========================================================
    // LESSON INFORMATION
    // =========================================================

    private VBox createLessonInformation() {

        VBox card = createCard();

        Label heading =
                createHeading("Lesson Information");

        Separator separator =
                new Separator();

        // =====================================================
        // TITLE
        // =====================================================

        Label titleLabel =
                createFieldLabel("Lesson Title");

        titleField = new TextField();

        titleField.setPromptText(
                "Enter lesson title");

        titleField.setText(
                safe(lesson.getTitle()));

        styleTextField(
                titleField);

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label descriptionLabel =
                createFieldLabel(
                        "Lesson Description");

        descriptionField =
                new TextArea();

        descriptionField.setPromptText(
                "Describe what students will learn in this lesson...");

        descriptionField.setText(
                safe(lesson.getDescription()));

        descriptionField.setWrapText(true);

        descriptionField.setPrefRowCount(10);

        descriptionField.setPrefHeight(220);

        descriptionField.setMinHeight(180);

        descriptionField.setMaxWidth(
                Double.MAX_VALUE);

        styleTextArea(
                descriptionField);

        // =====================================================
        // CONTENT
        // =====================================================

        card.getChildren().addAll(
                heading,
                separator,
                titleLabel,
                titleField,
                descriptionLabel,
                descriptionField);

        VBox.setVgrow(
                descriptionField,
                Priority.ALWAYS);

        return card;
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    private VBox createLessonSettings() {

        VBox card = createCard();

        card.setPrefWidth(280);

        card.setMinWidth(280);

        Label heading =
                createHeading("Lesson Settings");

        Separator separator =
                new Separator();

        // =====================================================
        // TYPE
        // =====================================================

        Label typeLabel =
                createFieldLabel(
                        "Lesson Type");

        typeBox = new ComboBox<>();

        typeBox.getItems().addAll(
                "VIDEO",
                "READING",
                "QUIZ",
                "ACTIVITY");

        // String existingType =lesson.getType();

        // if (existingType != null &&
        //         typeBox.getItems()
        //                 .contains(existingType)) {

        //     typeBox.setValue(
        //             existingType);

        // } else {

        //     typeBox.setValue(
        //             "READING");
        // }

        // styleComboBox(
        //         typeBox);

        // =====================================================
        // MEDIA
        // =====================================================

        Label mediaLabel =
                createFieldLabel(
                        "Topic Media");

        mediaField =
                new TextField();

        mediaField.setPromptText(
                "Image / video URL");

        mediaField.setText(
                safe(lesson.getMediaUrl()));

        styleTextField(
                mediaField);

        // =====================================================
        // ORDER
        // =====================================================

        Label orderLabel =
                createFieldLabel(
                        "Lesson Order");

        Label order =
                new Label(
                        String.valueOf(
                                module.getModuleOrder()
                                        + "."
                                        + lesson.getLessonOrder()));

        order.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;");

        // =====================================================
        // ADD
        // =====================================================

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

        Button cancel =
                new Button("Cancel");

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

        Button save =
                new Button("✓  Save Changes");

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
                e -> updateLesson());

        box.getChildren().addAll(
                cancel,
                save);

        return box;
    }

    // =========================================================
    // UPDATE
    // =========================================================

    private void updateLesson() {

        String title =
                titleField.getText().trim();

        String description =
                descriptionField.getText().trim();

        String type =
                typeBox.getValue();

        String media =
                mediaField.getText().trim();

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

        if (type == null ||
                type.trim().isEmpty()) {

            type = "READING";
        }

        // =====================================================
        // UPDATE
        // =====================================================

        boolean updated =
                lessonController.updateLesson(
                        lesson.getLessonId(),
                        title,
                        description,
                        type,
                        media);

        if (!updated) {

            System.out.println(
                    "Lesson could not be updated.");

            return;
        }

        System.out.println(
                "Lesson updated successfully.");

        // =====================================================
        // RETURN
        // =====================================================

        goBack();
    }

    // =========================================================
    // BACK
    // =========================================================

    private void goBack() {

        ModulePage modulePage =
                new ModulePage(course);

        LoginPage.mainStage.setScene(
                modulePage.getModuleScene());
    }

    // =========================================================
    // CARD
    // =========================================================

    private VBox createCard() {

        VBox card =
                new VBox(10);

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

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;");

        return label;
    }

    // =========================================================
    // LABEL
    // =========================================================

    private Label createFieldLabel(
            String text) {

        Label label =
                new Label(text);

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

        area.setWrapText(true);

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

        area.setPrefHeight(220);
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

    private String safe(
            String value) {

        return value == null
                ? ""
                : value;
    }
}