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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

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

        private TextField durationField;

        private TextField mediaField;

        private VBox contentBlocks;

        private Label mediaPreviewLabel;

        // =========================================================
        // CONTENT BLOCKS
        // =========================================================

        private final List<VBox> blocks = new ArrayList<>();

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

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // HEADER
                // =====================================================

                VBox header = createHeader();

                root.setTop(header);

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                VBox page = new VBox(15);

                page.setPadding(
                                new Insets(10, 30, 30, 30));

                // Course / module information

                page.getChildren().add(
                                createCourseInfo());

                // =====================================================
                // BASIC INFORMATION
                // =====================================================

                HBox information = new HBox(15);

                VBox lessonInformation = createLessonInformation();

                // VBox lessonSettings = createLessonSettings();

                HBox.setHgrow(
                                lessonInformation,
                                Priority.ALWAYS);

                // lessonSettings.setPrefWidth(280);

                information.getChildren().addAll(
                                lessonInformation
                // lessonSettings
                );

                page.getChildren().add(
                                information);

                // =====================================================
                // MEDIA
                // =====================================================

                page.getChildren().add(
                                createMediaSection());

                // =====================================================
                // CONTENT
                // =====================================================

                page.getChildren().add(
                                createContentEditor());

                // =====================================================
                // ACTIONS
                // =====================================================

                page.getChildren().add(
                                createActions());

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scroll = new ScrollPane(page);

                scroll.setFitToWidth(true);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scroll.setPannable(true);

                scroll.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-background:#080C0D;" +
                                                "-fx-border-color:transparent;");

                root.setCenter(scroll);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // HEADER
        // =========================================================

        private VBox createHeader() {

                VBox container = new VBox();

                container.setPadding(
                                new Insets(15, 30, 10, 30));

                HBox header = new HBox(15);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // BACK
                // =====================================================

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

                // =====================================================
                // TITLE
                // =====================================================

                VBox titleBox = new VBox(3);

                Label title = new Label("Add Lesson");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                Label subtitle = new Label(
                                "Create and manage lesson content.");

                subtitle.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                // =====================================================
                // SPACER
                // =====================================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =====================================================
                // SAVE BUTTON
                // =====================================================

                Button save = new Button("✓  Save Lesson");

                save.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 18;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                save.setOnAction(
                                e -> saveLesson());

                header.getChildren().addAll(
                                back,
                                titleBox,
                                spacer,
                                save);

                container.getChildren().add(
                                header);

                return container;
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

                Label heading = createHeading(
                                "Lesson Information");

                Separator separator = new Separator();

                // =====================================================
                // TITLE
                // =====================================================

                Label titleLabel = createFieldLabel(
                                "Lesson Title");

                titleField = new TextField();

                titleField.setPromptText(
                                "Enter lesson title");

                styleTextField(
                                titleField);

                // =====================================================
                // DESCRIPTION
                // =====================================================

                Label descriptionLabel = createFieldLabel(
                                "Lesson Description");

                descriptionField = new TextArea();

                descriptionField.setPromptText(
                                "Describe what students will learn in this lesson...");

                descriptionField.setWrapText(true);

                descriptionField.setPrefHeight(160);

                descriptionField.setMinHeight(160);

                descriptionField.setMaxHeight(220);

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

        // // =========================================================
        // // LESSON SETTINGS
        // // =========================================================

        // private VBox createLessonSettings() {

        // VBox card = createCard();

        // card.setPrefWidth(
        // 280);

        // Label heading = createHeading(
        // "Lesson Settings");

        // Separator separator = new Separator();

        // // =====================================================
        // // TYPE
        // // =====================================================

        // Label typeLabel = createFieldLabel(
        // "Lesson Type");

        // typeBox = new ComboBox<>();

        // typeBox.getItems().addAll(
        // "VIDEO",
        // "READING",
        // "QUIZ",
        // "ACTIVITY");

        // typeBox.setValue(
        // "VIDEO");

        // styleComboBox(
        // typeBox);

        // // =====================================================
        // // DURATION
        // // =====================================================

        // Label durationLabel = createFieldLabel(
        // "Duration");

        // HBox durationBox = new HBox(8);

        // durationField = new TextField();

        // durationField.setPromptText(
        // "15");

        // styleTextField(
        // durationField);

        // ComboBox<String> durationUnit = new ComboBox<>();

        // durationUnit.getItems().addAll(
        // "Minutes",
        // "Hours");

        // durationUnit.setValue(
        // "Minutes");

        // styleComboBox(
        // durationUnit);

        // HBox.setHgrow(
        // durationField,
        // Priority.ALWAYS);

        // durationBox.getChildren().addAll(
        // durationField,
        // durationUnit);

        // // =====================================================
        // // ORDER
        // // =====================================================

        // Label orderLabel = createFieldLabel(
        // "Lesson Order");

        // Label order = new Label(
        // "Automatically assigned");

        // order.setStyle(
        // "-fx-text-fill:#777777;" +
        // "-fx-font-size:10px;");

        // card.getChildren().addAll(
        // heading,
        // separator,
        // typeLabel,
        // typeBox,
        // durationLabel,
        // durationBox,
        // orderLabel,
        // order);

        // return card;
        // }

        // =========================================================
        // MEDIA SECTION
        // =========================================================

        private VBox createMediaSection() {

                VBox card = createCard();

                Label heading = createHeading(
                                "Media Gallery");

                Separator separator = new Separator();

                // =====================================================
                // MEDIA CARDS
                // =====================================================

                FlowPane mediaCards = new FlowPane();

                mediaCards.setHgap(10);

                mediaCards.setVgap(10);

                mediaCards.getChildren().addAll(
                                createMediaCard(
                                                "IMAGE",
                                                "▧",
                                                "Add Image"),

                                createMediaCard(
                                                "VIDEO",
                                                "▶",
                                                "Add Video"),

                                createMediaCard(
                                                "DOCUMENT",
                                                "▤",
                                                "Add Document"));

                card.getChildren().addAll(
                                heading,
                                separator,
                                mediaCards);

                return card;
        }

        // =========================================================
        // MEDIA CARD
        // =========================================================

        private VBox createMediaCard(
                        String type,
                        String iconText,
                        String text) {

                VBox card = new VBox(7);

                card.setAlignment(
                                Pos.CENTER);

                card.setPrefSize(
                                180,
                                110);

                card.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-cursor:hand;");

                Label icon = new Label(iconText);

                icon.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:24px;");

                Label label = new Label(text);

                label.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:10px;");

                card.getChildren().addAll(
                                icon,
                                label);

                card.setOnMouseClicked(
                                e -> {

                                        if (type.equals("IMAGE")) {

                                                addImageBlock();

                                        } else if (type.equals("VIDEO")) {

                                                addVideoBlock();

                                        } else {

                                                addDocumentBlock();
                                        }
                                });

                return card;
        }

        // =========================================================
        // CONTENT EDITOR
        // =========================================================

        private VBox createContentEditor() {

                VBox card = createCard();

                Label heading = createHeading(
                                "Lesson Content");

                Separator separator = new Separator();

                // =====================================================
                // BLOCKS
                // =====================================================

                contentBlocks = new VBox(10);

                contentBlocks.setFillWidth(
                                true);

                // Initial text block

                addTextBlock();

                // =====================================================
                // ADD BLOCK BUTTONS
                // =====================================================

                HBox addButtons = new HBox(8);

                addButtons.setAlignment(
                                Pos.CENTER);

                Button text = createAddButton(
                                "+ Text");

                text.setOnAction(
                                e -> addTextBlock());

                Button image = createAddButton(
                                "+ Image");

                image.setOnAction(
                                e -> addImageBlock());

                Button video = createAddButton(
                                "+ Video");

                video.setOnAction(
                                e -> addVideoBlock());

                Button document = createAddButton(
                                "+ Document");

                document.setOnAction(
                                e -> addDocumentBlock());

                addButtons.getChildren().addAll(
                                text,
                                image,
                                video,
                                document);

                card.getChildren().addAll(
                                heading,
                                separator,
                                contentBlocks,
                                addButtons);

                return card;
        }

        // =========================================================
        // TEXT BLOCK
        // =========================================================

        private void addTextBlock() {

                VBox block = createContentBlock(
                                "TEXT");

                TextArea textArea = new TextArea();

                textArea.setPromptText(
                                "Write lesson content here...");

                textArea.setWrapText(true);

                textArea.setPrefHeight(
                                150);

                styleTextArea(
                                textArea);

                block.getChildren().add(
                                textArea);

                addBlock(
                                block);
        }

        // =========================================================
        // IMAGE BLOCK
        // =========================================================

        private void addImageBlock() {

                VBox block = createContentBlock(
                                "IMAGE");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter image URL");

                styleTextField(
                                url);

                ImageView preview = new ImageView();

                preview.setFitWidth(
                                300);

                preview.setFitHeight(
                                160);

                preview.setPreserveRatio(
                                true);

                StackPane previewBox = new StackPane(
                                preview);

                previewBox.setPrefHeight(
                                170);

                previewBox.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-radius:5;");

                Button previewButton = new Button(
                                "Preview Image");

                previewButton.setStyle(
                                "-fx-background-color:#14251A;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-radius:4;");

                previewButton.setOnAction(
                                e -> {

                                        try {

                                                if (!url.getText()
                                                                .trim()
                                                                .isEmpty()) {

                                                        preview.setImage(
                                                                        new Image(
                                                                                        url.getText()
                                                                                                        .trim(),
                                                                                        true));
                                                }

                                        } catch (Exception ex) {

                                                System.out.println(
                                                                "Invalid image URL");
                                        }
                                });

                block.getChildren().addAll(
                                url,
                                previewButton,
                                previewBox);

                addBlock(
                                block);
        }

        // =========================================================
        // VIDEO BLOCK
        // =========================================================

        private void addVideoBlock() {

                VBox block = createContentBlock(
                                "VIDEO");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter YouTube or video URL");

                styleTextField(
                                url);

                Label preview = new Label(
                                "▶\n\nVideo Preview");

                preview.setAlignment(
                                Pos.CENTER);

                preview.setPrefHeight(
                                180);

                preview.setMaxWidth(
                                Double.MAX_VALUE);

                preview.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#555555;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-font-size:12px;");

                block.getChildren().addAll(
                                url,
                                preview);

                addBlock(
                                block);
        }

        // =========================================================
        // DOCUMENT BLOCK
        // =========================================================

        private void addDocumentBlock() {

                VBox block = createContentBlock(
                                "DOCUMENT");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter PDF / document URL");

                styleTextField(
                                url);

                Label info = new Label(
                                "Students will be able to open this document.");

                info.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                block.getChildren().addAll(url, info);

                addBlock(block);
        }

        // =========================================================
        // CONTENT BLOCK
        // =========================================================

        private VBox createContentBlock(String type) {

                VBox block = new VBox(8);
                block.setPadding(new Insets(12));

                block.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#202A25;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox(8);

                header.setAlignment(Pos.CENTER_LEFT);

                Region spacer = new Region();

                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button up = new Button("↑");

                Button down = new Button("↓");

                Button delete = new Button("×");

                styleSmallButton(up);

                styleSmallButton(down);

                styleSmallButton(delete);

                up.setOnAction(
                                e -> moveBlock(block, -1));

                down.setOnAction(
                                e -> moveBlock(block, 1));

                delete.setOnAction(
                                e -> removeBlock(block));

                header.getChildren().addAll(
                                spacer,
                                up,
                                down,
                                delete);

                block.getChildren().add(
                                header);

                return block;
        }

        // =========================================================
        // ADD BLOCK
        // =========================================================

        private void addBlock(VBox block) {

                blocks.add(block);

                contentBlocks.getChildren().add(block);
        }

        // =========================================================
        // REMOVE BLOCK
        // =========================================================

        private void removeBlock(VBox block) {

                blocks.remove(block);

                contentBlocks.getChildren().remove(block);
        }

        // =========================================================
        // MOVE BLOCK
        // =========================================================

        private void moveBlock(VBox block, int direction) {

                int index = blocks.indexOf(block);

                if (index < 0) {
                        return;
                }

                int newIndex = index + direction;

                if (newIndex < 0 || newIndex >= blocks.size()) {

                        return;
                }

                blocks.remove(index);

                blocks.add(newIndex, block);

                contentBlocks.getChildren().clear();

                contentBlocks.getChildren().addAll(blocks);
        }

        // =========================================================
        // ACTIONS
        // =========================================================

        private HBox createActions() {

                HBox box = new HBox(10);

                box.setAlignment(Pos.CENTER_RIGHT);

                Button cancel = new Button("Cancel");

                cancel.setStyle("-fx-background-color:#101516;" +
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

                save.setStyle("-fx-background-color:#68D34A;" +
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

                        System.out.println("Course or module not found.");

                        return;
                }

                String title = titleField.getText().trim();

                String description = descriptionField.getText().trim();

                String media = mediaField != null ? mediaField.getText().trim() : "";

                // =====================================================
                // VALIDATION
                // =====================================================

                if (title.isEmpty()) {

                        System.out.println("Lesson title required.");

                        titleField.requestFocus();

                        return;
                }

                if (description.isEmpty()) {

                        System.out.println("Lesson description required.");

                        descriptionField.requestFocus();

                        return;
                }

                // =====================================================
                // SAVE BASIC LESSON
                // =====================================================

                boolean saved = lessonController.addLesson(
                                module.getModuleId(),
                                title,
                                description,
                                media);

                if (!saved) {
                        System.out.println("Lesson could not be created.");
                        return;
                }

                System.out.println("Lesson created successfully.");

                // =====================================================
                // RETURN
                // =====================================================

                ModulePage modulePage = new ModulePage(course);

                LoginPage.mainStage.setScene(modulePage.getModuleScene());
        }

        // =========================================================
        // ADD BUTTON
        // =========================================================

        private Button createAddButton(String text) {

                Button button = new Button(text);

                button.setStyle("-fx-background-color:#101612;" +
                                "-fx-text-fill:#68D34A;" +
                                "-fx-border-color:#245D35;" +
                                "-fx-border-width:1;" +
                                "-fx-border-radius:5;" +
                                "-fx-background-radius:5;" +
                                "-fx-padding:7 14;" +
                                "-fx-font-size:10px;" +
                                "-fx-font-weight:bold;" +
                                "-fx-cursor:hand;");

                return button;
        }

        // =========================================================
        // SMALL BUTTON
        // =========================================================

        private void styleSmallButton(Button button) {

                button.setStyle("-fx-background-color:transparent;" +
                                "-fx-text-fill:#777777;" +
                                "-fx-border-color:#242B2C;" +
                                "-fx-border-radius:4;" +
                                "-fx-background-radius:4;" +
                                "-fx-padding:3 7;" +
                                "-fx-cursor:hand;");
        }

        // =========================================================
        // CARD
        // =========================================================

        private VBox createCard() {

                VBox card = new VBox(10);

                card.setPadding(new Insets(18));

                card.setMaxWidth(Double.MAX_VALUE);

                card.setStyle("-fx-background-color:#101516;" +
                                "-fx-border-color:#242B2C;" +
                                "-fx-border-width:1;" +
                                "-fx-border-radius:8;" +
                                "-fx-background-radius:8;");

                return card;
        }

        // =========================================================
        // HEADING
        // =========================================================

        private Label createHeading(String text) {

                Label label = new Label(text);

                label.setStyle("-fx-text-fill:#EEEEEE;" +
                                "-fx-font-size:15px;" +
                                "-fx-font-weight:bold;");

                return label;
        }

        // =========================================================
        // FIELD LABEL
        // =========================================================

        private Label createFieldLabel(String text) {

                Label label = new Label(text);

                label.setStyle("-fx-text-fill:#AAAAAA;" +
                                "-fx-font-size:10px;" +
                                "-fx-font-weight:bold;");

                return label;
        }

        // =========================================================
        // TEXT FIELD
        // =========================================================

        private void styleTextField(TextField field) {

                field.setPrefHeight(36);

                field.setMaxWidth(Double.MAX_VALUE);

                field.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#666666;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-focus-color:transparent;" +
                                                "-fx-faint-focus-color:transparent;" +
                                                "-fx-padding:8;" +
                                                "-fx-font-size:12px;");
        }

        // =========================================================
        // TEXT AREA
        // =========================================================

        private void styleTextArea(TextArea area) {

                area.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#666666;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-focus-color:transparent;" +
                                                "-fx-faint-focus-color:transparent;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-padding:10px;" +
                                                "-fx-control-inner-background:#0D1213;" +
                                                "-fx-control-inner-background-alt:#0D1213;");

                area.setWrapText(true);

                area.focusedProperty().addListener(
                                (obs, oldValue, focused) -> {
                                        if (focused) {
                                                area.setStyle("-fx-background-color:#0D1213;" +
                                                                "-fx-text-fill:#EEEEEE;" +
                                                                "-fx-prompt-text-fill:#666666;" +
                                                                "-fx-border-color:#39FF72;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-focus-color:transparent;" +
                                                                "-fx-faint-focus-color:transparent;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-padding:10px;" +
                                                                "-fx-control-inner-background:#0D1213;" +
                                                                "-fx-control-inner-background-alt:#0D1213;");

                                        } else {
                                                area.setStyle("-fx-background-color:#0D1213;" +
                                                                "-fx-text-fill:#EEEEEE;" +
                                                                "-fx-prompt-text-fill:#666666;" +
                                                                "-fx-border-color:#242B2C;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-focus-color:transparent;" +
                                                                "-fx-faint-focus-color:transparent;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-padding:10px;" +
                                                                "-fx-control-inner-background:#0D1213;"
                                                                +
                                                                "-fx-control-inner-background-alt:#0D1213;");
                                        }
                                });
        }

        // =========================================================
        // SAFE
        // =========================================================

        private String safe(String value) {
                return value == null ? "" : value;
        }

        private void goBack() {
                ModulePage modulePage = new ModulePage(course);
                LoginPage.mainStage.setScene(modulePage.getModuleScene());
        }
}