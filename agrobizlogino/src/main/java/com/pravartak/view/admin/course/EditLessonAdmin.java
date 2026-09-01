package com.pravartak.view.admin.course;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.pravartak.config.CloudinaryConfig;
import com.pravartak.controller.admincontroller.LessonController;
import com.pravartak.model.admin.ContentBlock;
import com.pravartak.model.admin.Course;
import com.pravartak.model.admin.Lesson;
import com.pravartak.model.admin.Module;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;

public class EditLessonAdmin {

        private final Course course;
        private final Module module;
        private final Lesson lesson;

        private final LessonController lessonController = new LessonController();

        private TextField titleField;
        private TextArea descriptionField;
        private TextField mediaField;
        private VBox contentBlocks;

        private final List<VBox> blocks = new ArrayList<>();

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

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                root.setTop(createHeader());

                VBox page = new VBox(15);

                page.setPadding(
                                new Insets(10, 30, 30, 30));

                page.getChildren().add(
                                createCourseInfo());

                HBox information = new HBox(15);

                VBox lessonInformation = createLessonInformation();

                HBox.setHgrow(
                                lessonInformation,
                                Priority.ALWAYS);

                information.getChildren().add(
                                lessonInformation);

                page.getChildren().add(
                                information);

                page.getChildren().add(
                                createMediaSection());

                page.getChildren().add(
                                createContentEditor());

                page.getChildren().add(
                                createActions());

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

                VBox titleBox = new VBox(3);

                Label title = new Label("Edit Lesson");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                Label subtitle = new Label(
                                "Edit and manage lesson content.");

                subtitle.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button save = new Button("✓  Save Changes");

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
                                e -> updateLesson());

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

                Label separator2 = new Label("•");

                separator2.setStyle(
                                "-fx-text-fill:#555555;");

                Label lessonLabel = new Label(
                                "Lesson: "
                                                + safe(lesson.getTitle()));

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

                Label heading = createHeading(
                                "Lesson Information");

                Separator separator = new Separator();

                Label titleLabel = createFieldLabel(
                                "Lesson Title");

                titleField = new TextField();

                titleField.setPromptText(
                                "Enter lesson title");

                titleField.setText(
                                safe(lesson.getTitle()));

                styleTextField(
                                titleField);

                Label descriptionLabel = createFieldLabel(
                                "Lesson Description");

                descriptionField = new TextArea();

                descriptionField.setPromptText(
                                "Describe what students will learn in this lesson...");

                descriptionField.setText(
                                safe(lesson.getDescription()));

                descriptionField.setWrapText(true);

                descriptionField.setPrefHeight(
                                160);

                descriptionField.setMinHeight(
                                160);

                descriptionField.setMaxHeight(
                                220);

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
        // MEDIA
        // =========================================================

        private VBox createMediaSection() {

                VBox card = createCard();

                Label heading = createHeading(
                                "Media Gallery");

                Separator separator = new Separator();

                mediaField = new TextField();

                mediaField.setPromptText(
                                "Enter media URL");

                mediaField.setText(
                                safe(lesson.getMediaUrl()));

                styleTextField(
                                mediaField);

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
                                mediaField,
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

                contentBlocks = new VBox(10);

                contentBlocks.setFillWidth(
                                true);

                List<ContentBlock> existingBlocks = lesson.getContentBlocks();

                if (existingBlocks != null &&
                                !existingBlocks.isEmpty()) {

                        System.out.println(
                                        "Loading "
                                                        + existingBlocks.size()
                                                        + " existing content blocks.");

                        for (ContentBlock block : existingBlocks) {

                                if (block != null) {

                                        addExistingBlock(
                                                        block);
                                }
                        }

                } else {

                        addTextBlock();
                }

                HBox addButtons = new HBox(8);

                addButtons.setAlignment(
                                Pos.CENTER);

                Button text = createAddButton("+ Text");

                text.setOnAction(
                                e -> addTextBlock());

                Button image = createAddButton("+ Image");

                image.setOnAction(
                                e -> addImageBlock());

                Button video = createAddButton("+ Video");

                video.setOnAction(
                                e -> addVideoBlock());

                Button document = createAddButton("+ Document");

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
        // EXISTING BLOCK
        // =========================================================

        private void addExistingBlock(
                        ContentBlock contentBlock) {

                String type = safe(contentBlock.getType())
                                .toUpperCase();

                String content = safe(contentBlock.getContent());

                switch (type) {

                        case "TEXT":
                                addExistingTextBlock(
                                                content);
                                break;

                        case "IMAGE":
                                addExistingImageBlock(
                                                content);
                                break;

                        case "VIDEO":
                                addExistingVideoBlock(
                                                content);
                                break;

                        case "DOCUMENT":
                                addExistingDocumentBlock(
                                                content);
                                break;

                        default:
                                System.out.println(
                                                "Unknown content block type: "
                                                                + type);
                }
        }

        // =========================================================
        // EXISTING TEXT
        // =========================================================

        private void addExistingTextBlock(
                        String value) {

                VBox block = createContentBlock(
                                "TEXT");

                TextArea textArea = new TextArea();

                textArea.setPromptText(
                                "Write lesson content here...");

                textArea.setText(value);

                textArea.setWrapText(true);

                textArea.setPrefHeight(
                                150);

                styleTextArea(
                                textArea);

                block.getChildren().add(
                                textArea);

                addBlock(block);
        }

        // =========================================================
        // EXISTING IMAGE
        // =========================================================

        private void addExistingImageBlock(
                        String value) {

                VBox block = createContentBlock("IMAGE");

                TextField url = new TextField();

                url.setPromptText(
                                "Cloudinary image URL");

                url.setText(value);

                styleTextField(url);

                Button chooseImage = new Button("Choose New Image");

                chooseImage.setStyle(
                                "-fx-background-color:#14251A;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;" +
                                                "-fx-padding:7 14;" +
                                                "-fx-cursor:hand;");

                ImageView preview = new ImageView();

                preview.setFitWidth(300);
                preview.setFitHeight(160);
                preview.setPreserveRatio(true);

                StackPane previewBox = new StackPane(preview);

                previewBox.setPrefHeight(170);

                previewBox.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-radius:5;");

                chooseImage.setOnAction(
                                e -> chooseAndUploadImage(
                                                url,
                                                preview));

                block.getChildren().addAll(
                                url,
                                chooseImage,
                                previewBox);

                if (!value.trim().isEmpty()) {

                        loadImage(
                                        url,
                                        preview);
                }

                addBlock(block);
        }

        // =========================================================
        // EXISTING VIDEO
        // =========================================================

        private void addExistingVideoBlock(
                        String value) {

                VBox block = createContentBlock(
                                "VIDEO");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter YouTube or video URL");

                url.setText(value);

                styleTextField(url);

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

                addBlock(block);
        }

        // =========================================================
        // EXISTING DOCUMENT
        // =========================================================

        private void addExistingDocumentBlock(
                        String value) {

                VBox block = createContentBlock(
                                "DOCUMENT");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter PDF / document URL");

                url.setText(value);

                styleTextField(url);

                Label info = new Label(
                                "Students will be able to open this document.");

                info.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                block.getChildren().addAll(
                                url,
                                info);

                addBlock(block);
        }

        // =========================================================
        // ADD TEXT
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

                addBlock(block);
        }

        // =========================================================
        // ADD IMAGE
        // =========================================================

        private void addImageBlock() {

                VBox block = createContentBlock("IMAGE");

                TextField url = new TextField();

                url.setPromptText(
                                "Cloudinary image URL");

                styleTextField(url);

                Button chooseImage = new Button("Choose Image");

                chooseImage.setStyle(
                                "-fx-background-color:#14251A;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-radius:4;" +
                                                "-fx-background-radius:4;" +
                                                "-fx-padding:7 14;" +
                                                "-fx-cursor:hand;");

                ImageView preview = new ImageView();

                preview.setFitWidth(300);
                preview.setFitHeight(160);
                preview.setPreserveRatio(true);

                StackPane previewBox = new StackPane(preview);

                previewBox.setPrefHeight(170);

                previewBox.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-radius:5;");

                chooseImage.setOnAction(
                                e -> chooseAndUploadImage(
                                                url,
                                                preview));

                block.getChildren().addAll(
                                url,
                                chooseImage,
                                previewBox);

                addBlock(block);
        }

        // =========================================================
        // ADD VIDEO
        // =========================================================

        private void addVideoBlock() {

                VBox block = createContentBlock(
                                "VIDEO");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter YouTube or video URL");

                styleTextField(url);

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

                addBlock(block);
        }

        // =========================================================
        // ADD DOCUMENT
        // =========================================================

        private void addDocumentBlock() {

                VBox block = createContentBlock(
                                "DOCUMENT");

                TextField url = new TextField();

                url.setPromptText(
                                "Enter PDF / document URL");

                styleTextField(url);

                Label info = new Label(
                                "Students will be able to open this document.");

                info.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                block.getChildren().addAll(
                                url,
                                info);

                addBlock(block);
        }

        // =========================================================
        // CONTENT BLOCK UI
        // =========================================================

        private VBox createContentBlock(
                        String type) {

                VBox block = new VBox(8);

                block.setPadding(
                                new Insets(12));

                block.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#202A25;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                HBox header = new HBox(8);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                Label typeLabel = new Label(type);

                typeLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button up = new Button("↑");

                Button down = new Button("↓");

                Button delete = new Button("×");

                styleSmallButton(up);
                styleSmallButton(down);
                styleSmallButton(delete);

                up.setOnAction(
                                e -> moveBlock(
                                                block,
                                                -1));

                down.setOnAction(
                                e -> moveBlock(
                                                block,
                                                1));

                delete.setOnAction(
                                e -> removeBlock(
                                                block));

                header.getChildren().addAll(
                                typeLabel,
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

        private void addBlock(
                        VBox block) {

                if (block == null) {
                        return;
                }

                blocks.add(block);

                contentBlocks.getChildren().add(
                                block);
        }

        // =========================================================
        // REMOVE BLOCK
        // =========================================================

        private void removeBlock(
                        VBox block) {

                blocks.remove(block);

                contentBlocks.getChildren().remove(
                                block);
        }

        // =========================================================
        // MOVE BLOCK
        // =========================================================

        private void moveBlock(
                        VBox block,
                        int direction) {

                int index = blocks.indexOf(block);

                if (index < 0) {
                        return;
                }

                int newIndex = index + direction;

                if (newIndex < 0 ||
                                newIndex >= blocks.size()) {

                        return;
                }

                blocks.remove(index);

                blocks.add(
                                newIndex,
                                block);

                contentBlocks
                                .getChildren()
                                .clear();

                contentBlocks
                                .getChildren()
                                .addAll(blocks);
        }

        // =========================================================
        // COLLECT CONTENT BLOCKS
        // =========================================================

        private List<ContentBlock> collectContentBlocks() {

                List<ContentBlock> result = new ArrayList<>();

                int order = 1;

                for (VBox block : blocks) {

                        if (block == null ||
                                        block.getChildren().isEmpty()) {

                                continue;
                        }

                        String type = "";

                        Node first = block.getChildren()
                                        .get(0);

                        if (first instanceof HBox) {

                                HBox header = (HBox) first;

                                for (Node node : header.getChildren()) {

                                        if (node instanceof Label) {

                                                String text = ((Label) node)
                                                                .getText();

                                                if (text.equals("TEXT") ||
                                                                text.equals("IMAGE") ||
                                                                text.equals("VIDEO") ||
                                                                text.equals("DOCUMENT")) {

                                                        type = text;

                                                        break;
                                                }
                                        }
                                }
                        }

                        String content = "";

                        for (Node node : block.getChildren()) {

                                if (node instanceof TextArea) {

                                        content = safe(
                                                        ((TextArea) node)
                                                                        .getText());

                                        break;
                                }

                                if (node instanceof TextField) {

                                        content = safe(
                                                        ((TextField) node)
                                                                        .getText());

                                        break;
                                }
                        }

                        if (type.isEmpty() ||
                                        content.trim().isEmpty()) {

                                continue;
                        }

                        result.add(
                                        new ContentBlock(
                                                        type,
                                                        content,
                                                        order));

                        order++;
                }

                return result;
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

                Button save = new Button(
                                "✓  Save Changes");

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
        // UPDATE LESSON
        // =========================================================

        private void updateLesson() {

                if (lesson == null ||
                                course == null ||
                                module == null) {

                        System.out.println(
                                        "Course, module or lesson not found.");

                        return;
                }

                String title = titleField.getText().trim();

                String description = descriptionField
                                .getText()
                                .trim();

                String media = mediaField != null
                                ? mediaField.getText().trim()
                                : safe(lesson.getMediaUrl());

                if (title.isEmpty()) {

                        System.out.println(
                                        "Lesson title required.");

                        titleField.requestFocus();

                        return;
                }

                if (description.isEmpty()) {

                        System.out.println(
                                        "Lesson description required.");

                        descriptionField.requestFocus();

                        return;
                }

                List<ContentBlock> contentBlockList = collectContentBlocks();

                System.out.println(
                                "================================");

                System.out.println(
                                "Updating lesson");

                System.out.println(
                                "Course ID: "
                                                + course.getCourseId());

                System.out.println(
                                "Module ID: "
                                                + module.getModuleId());

                System.out.println(
                                "Lesson ID: "
                                                + lesson.getLessonId());

                System.out.println(
                                "Content blocks: "
                                                + contentBlockList.size());

                boolean updated = lessonController.updateLesson(
                                lesson.getLessonId(),
                                course.getCourseId(),
                                module.getModuleId(),
                                title,
                                description,
                                media,
                                contentBlockList);

                if (!updated) {

                        System.out.println(
                                        "Lesson could not be updated.");

                        return;
                }

                lesson.setCourseId(
                                course.getCourseId());

                lesson.setModuleId(
                                module.getModuleId());

                lesson.setTitle(title);

                lesson.setDescription(
                                description);

                lesson.setMediaUrl(
                                media);

                lesson.setContentBlocks(
                                contentBlockList);

                System.out.println(
                                "Lesson updated successfully.");

                System.out.println(
                                "================================");

                goBack();
        }

        // =========================================================
        // LOAD IMAGE
        // =========================================================

        private void loadImage(
                        TextField urlField,
                        ImageView preview) {

                try {

                        String url = safe(urlField.getText())
                                        .trim();

                        if (url.isEmpty()) {

                                preview.setImage(null);

                                return;
                        }

                        Image image = new Image(
                                        url,
                                        true);

                        preview.setImage(
                                        image);

                } catch (Exception e) {

                        System.out.println(
                                        "Invalid image URL.");

                        preview.setImage(null);
                }
        }

        // =========================================================
        // ADD BUTTON
        // =========================================================

        private Button createAddButton(
                        String text) {

                Button button = new Button(text);

                button.setStyle(
                                "-fx-background-color:#101612;" +
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

        private void styleSmallButton(
                        Button button) {

                button.setStyle(
                                "-fx-background-color:transparent;" +
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

                card.setPadding(
                                new Insets(18));

                card.setMaxWidth(
                                Double.MAX_VALUE);

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
                                                "-fx-font-size:10px;" +
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
                                                "-fx-focus-color:transparent;" +
                                                "-fx-faint-focus-color:transparent;" +
                                                "-fx-padding:8;" +
                                                "-fx-font-size:12px;");
        }

        // =========================================================
        // TEXT AREA STYLE
        // =========================================================

        private void styleTextArea(
                        TextArea area) {

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
        // BACK
        // =========================================================

        private void goBack() {

                AdminModulePage modulePage = new AdminModulePage(course);

                LoginPage.mainStage.setScene(
                                modulePage.getModuleScene());
        }

        private void chooseAndUploadImage(
                        TextField urlField,
                        ImageView preview) {

                FileChooser fileChooser = new FileChooser();

                fileChooser.setTitle(
                                "Choose Lesson Image");

                fileChooser.getExtensionFilters()
                                .add(
                                                new FileChooser.ExtensionFilter(
                                                                "Image Files",
                                                                "*.png",
                                                                "*.jpg",
                                                                "*.jpeg",
                                                                "*.webp"));

                File file = fileChooser.showOpenDialog(
                                LoginPage.mainStage);

                if (file == null) {
                        return;
                }

                try {

                        System.out.println(
                                        "Uploading image to Cloudinary...");

                        Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

                        Map<?, ?> result = cloudinary.uploader().upload(
                                        file,
                                        Map.of(
                                                        "folder",
                                                        "agrobiz/lessons"));

                        String imageUrl = result.get("secure_url")
                                        .toString();

                        System.out.println(
                                        "Cloudinary URL:");

                        System.out.println(
                                        imageUrl);

                        urlField.setText(imageUrl);

                        Image image = new Image(
                                        imageUrl,
                                        true);

                        preview.setImage(image);

                        System.out.println(
                                        "Image uploaded successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "Error uploading image to Cloudinary:");

                        e.printStackTrace();
                }
        }

}