package com.pravartak.view.admin.course;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.pravartak.config.CloudinaryConfig;
import com.pravartak.controller.admincontroller.LessonController;
import com.pravartak.model.admin.ContentBlock;
import com.pravartak.model.admin.Course;
import com.pravartak.model.admin.Module;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private VBox contentBlocks;

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

        root.setTop(
                createHeader());

        // =====================================================
        // MAIN PAGE
        // =====================================================

        VBox page = new VBox(15);

        page.setPadding(
                new Insets(
                        10,
                        30,
                        30,
                        30));

        // =====================================================
        // COURSE INFORMATION
        // =====================================================

        page.getChildren().add(
                createCourseInfo());

        // =====================================================
        // LESSON INFORMATION
        // =====================================================

        VBox lessonInformation = createLessonInformation();

        HBox.setHgrow(
                lessonInformation,
                Priority.ALWAYS);

        HBox information = new HBox(15);

        information.getChildren().add(
                lessonInformation);

        page.getChildren().add(
                information);

        // =====================================================
        // MEDIA GALLERY
        // =====================================================

        page.getChildren().add(
                createMediaSection());

        // =====================================================
        // CONTENT EDITOR
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

        // =====================================================
        // SCENE
        // =====================================================

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
                new Insets(
                        15,
                        30,
                        10,
                        30));

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
        // SAVE
        // =====================================================

        Button save = new Button(
                "✓  Save Lesson");

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

    // =========================================================
    // MEDIA SECTION
    // =========================================================

    private VBox createMediaSection() {

        VBox card = createCard();

        Label heading = createHeading(
                "Media Gallery");

        Separator separator = new Separator();

        FlowPane mediaCards = new FlowPane();

        mediaCards.setHgap(10);
        mediaCards.setVgap(10);

        // =====================================================
        // IMAGE
        // =====================================================

        mediaCards.getChildren().add(
                createMediaCard(
                        "IMAGE",
                        "▧",
                        "Add Image"));

        // =====================================================
        // VIDEO
        // =====================================================

        mediaCards.getChildren().add(
                createMediaCard(
                        "VIDEO",
                        "▶",
                        "Add Video"));

        // =====================================================
        // DOCUMENT
        // =====================================================

        mediaCards.getChildren().add(
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

        // =====================================================
        // CLICK
        // =====================================================

        card.setOnMouseClicked(
                e -> {

                    if (type.equals("IMAGE")) {

                        addImageBlock();

                    } else if (type.equals("VIDEO")) {

                        addVideoBlock();

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

        contentBlocks.setFillWidth(true);

        // =====================================================
        // INITIAL TEXT BLOCK
        // =====================================================

        addTextBlock();

        // =====================================================
        // ADD BUTTONS
        // =====================================================

        HBox addButtons = new HBox(8);

        addButtons.setAlignment(
                Pos.CENTER);

        // =====================================================
        // TEXT
        // =====================================================

        Button text = createAddButton(
                "+ Text");

        text.setOnAction(
                e -> addTextBlock());

        // =====================================================
        // IMAGE
        // =====================================================

        Button image = createAddButton(
                "+ Image");

        image.setOnAction(
                e -> addImageBlock());

        // =====================================================
        // VIDEO
        // =====================================================

        Button video = createAddButton(
                "+ Video");

        video.setOnAction(
                e -> addVideoBlock());

        // =====================================================
        // DOCUMENT
        // =====================================================

        // Button document = createAddButton(
        //         "+ Document");

        // document.setOnAction(
        //         e -> addDocumentBlock());

        addButtons.getChildren().addAll(
                text,
                image,
                video
                // document
        );

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

        textArea.setPrefHeight(150);

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

        // =====================================================
        // FILE NAME
        // =====================================================

        Label fileName = new Label(
                "No image selected");

        fileName.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:11px;");

        // =====================================================
        // PREVIEW
        // =====================================================

        ImageView preview = new ImageView();

        preview.setFitWidth(350);
        preview.setFitHeight(200);

        preview.setPreserveRatio(true);
        preview.setSmooth(true);

        StackPane previewBox = new StackPane();

        previewBox.setPrefHeight(220);

        previewBox.setMaxWidth(
                Double.MAX_VALUE);

        previewBox.setAlignment(
                Pos.CENTER);

        previewBox.setStyle(
                "-fx-background-color:#0D1213;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-style:dashed;" +
                "-fx-border-radius:5;");

        previewBox.getChildren().add(
                preview);

        // =====================================================
        // SELECT BUTTON
        // =====================================================

        Button selectButton = new Button(
                "Choose Image");

        selectButton.setStyle(
                "-fx-background-color:#14251A;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#245D35;" +
                "-fx-border-radius:4;" +
                "-fx-background-radius:4;" +
                "-fx-padding:7 14;" +
                "-fx-cursor:hand;");

        // =====================================================
        // CLICK
        // =====================================================

        selectButton.setOnAction(
                e -> {

                    FileChooser chooser = new FileChooser();

                    chooser.setTitle(
                            "Choose Lesson Image");

                    // =================================================
                    // IMAGE FILTERS
                    // =================================================

                    chooser.getExtensionFilters().addAll(

                            new FileChooser.ExtensionFilter(
                                    "Image Files",
                                    "*.png",
                                    "*.jpg",
                                    "*.jpeg",
                                    "*.webp"),

                            new FileChooser.ExtensionFilter(
                                    "PNG",
                                    "*.png"),

                            new FileChooser.ExtensionFilter(
                                    "JPG / JPEG",
                                    "*.jpg",
                                    "*.jpeg"),

                            new FileChooser.ExtensionFilter(
                                    "WebP",
                                    "*.webp"));

                    // =================================================
                    // OPEN FILE CHOOSER
                    // =================================================

                    File file = chooser.showOpenDialog(
                            LoginPage.mainStage);

                    if (file == null) {
                        return;
                    }

                    // =================================================
                    // DISPLAY LOCAL PREVIEW
                    // =================================================

                    try {

                        Image localImage = new Image(
                                file.toURI().toString());

                        if (localImage.isError()) {

                            showError(
                                    "Image Error",
                                    "The selected image could not be loaded.");

                            return;
                        }

                        preview.setImage(
                                localImage);

                        fileName.setText(
                                file.getName());

                        fileName.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                "-fx-font-size:11px;" +
                                "-fx-font-weight:bold;");

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Image Error",
                                "Could not preview the selected image.");

                        return;
                    }

                    // =================================================
                    // UPLOAD TO CLOUDINARY
                    // =================================================

                    String uploadedUrl =
                            uploadImageToCloudinary(file);

                    if (uploadedUrl == null ||
                            uploadedUrl.trim().isEmpty()) {

                        preview.setImage(null);

                        fileName.setText(
                                "Upload failed");

                        fileName.setStyle(
                                "-fx-text-fill:#E57373;" +
                                "-fx-font-size:11px;" +
                                "-fx-font-weight:bold;");

                        showError(
                                "Upload Failed",
                                "The image could not be uploaded to Cloudinary.");

                        return;
                    }

                    // =================================================
                    // STORE CLOUDINARY URL INSIDE BLOCK
                    // =================================================

                    block.setUserData(
                            uploadedUrl);

                    fileName.setText(
                            file.getName()
                            + "  ✓ Uploaded");

                    fileName.setStyle(
                            "-fx-text-fill:#68D34A;" +
                            "-fx-font-size:11px;" +
                            "-fx-font-weight:bold;");
                });

        // =====================================================
        // INFORMATION
        // =====================================================

        Label info = new Label(
                "Select an image from your computer. "
                + "It will be uploaded to Cloudinary automatically.");

        info.setWrapText(true);

        info.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:10px;");

        block.getChildren().addAll(
                selectButton,
                fileName,
                previewBox,
                info);

        addBlock(
                block);
    }

    // =========================================================
    // UPLOAD IMAGE TO CLOUDINARY
    // =========================================================

    private String uploadImageToCloudinary(
            File file) {

        if (file == null ||
                !file.exists()) {

            System.out.println(
                    "Image file does not exist.");

            return null;
        }

        try {

            System.out.println(
                    "================================");

            System.out.println(
                    "Uploading image to Cloudinary");

            System.out.println(
                    "File: "
                    + file.getName());

            // =================================================
            // GET CLOUDINARY
            // =================================================

            Cloudinary cloudinary =
                    CloudinaryConfig.getCloudinary();

            if (cloudinary == null) {

                System.out.println(
                        "Cloudinary is not configured.");

                return null;
            }

            // =================================================
            // OPTIONS
            // =================================================

            Map<String, Object> options =
                    new HashMap<>();

            options.put(
                    "folder",
                    "agrobiz/lessons");

            options.put(
                    "resource_type",
                    "image");

            // =================================================
            // UPLOAD
            // =================================================

            Map<?, ?> result = cloudinary.uploader().upload(file, options);

            // =================================================
            // SECURE URL
            // =================================================

            Object secureUrl =
                    result.get("secure_url");

            if (secureUrl == null) {

                System.out.println(
                        "Cloudinary did not return secure_url.");

                return null;
            }

            String url =
                    secureUrl.toString();

            System.out.println(
                    "Image uploaded successfully.");

            System.out.println(
                    "Cloudinary URL: "
                    + url);

            System.out.println(
                    "================================");

            return url;

        } catch (Exception e) {

            System.out.println(
                    "Cloudinary image upload failed.");

            e.printStackTrace();

            return null;
        }
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

        Label info = new Label(
                "Example: https://www.youtube.com/watch?v=...");

        info.setWrapText(true);

        info.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:10px;");

        block.getChildren().addAll(
                url,
                info);

        addBlock(
                block);
    }

    // =========================================================
    // DOCUMENT BLOCK
    // =========================================================

//     private void addDocumentBlock() {

//         VBox block = createContentBlock(
//                 "DOCUMENT");

//         TextField url = new TextField();

//         url.setPromptText(
//                 "Enter PDF / document URL");

//         styleTextField(
//                 url);

//         Label info = new Label(
//                 "Students will be able to open this document.");

//         info.setWrapText(true);

//         info.setStyle(
//                 "-fx-text-fill:#777777;" +
//                 "-fx-font-size:10px;");

//         block.getChildren().addAll(
//                 url,
//                 info);

//         addBlock(
//                 block);
//     }

    // =========================================================
    // CONTENT BLOCK
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

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox(8);

        header.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // TYPE
        // =====================================================

        Label typeLabel = new Label(type);

        typeLabel.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;");

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        // =====================================================
        // UP
        // =====================================================

        Button up = new Button("↑");

        // =====================================================
        // DOWN
        // =====================================================

        Button down = new Button("↓");

        // =====================================================
        // DELETE
        // =====================================================

        Button delete = new Button("×");

        styleSmallButton(up);
        styleSmallButton(down);
        styleSmallButton(delete);

        // =====================================================
        // ACTIONS
        // =====================================================

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

        blocks.add(
                block);

        contentBlocks
                .getChildren()
                .add(block);
    }

    // =========================================================
    // REMOVE BLOCK
    // =========================================================

    private void removeBlock(
            VBox block) {

        if (block == null) {
            return;
        }

        blocks.remove(
                block);

        contentBlocks
                .getChildren()
                .remove(
                        block);
    }

    // =========================================================
    // MOVE BLOCK
    // =========================================================

    private void moveBlock(
            VBox block,
            int direction) {

        int index = blocks.indexOf(
                block);

        if (index < 0) {
            return;
        }

        int newIndex =
                index + direction;

        if (newIndex < 0 ||
                newIndex >= blocks.size()) {

            return;
        }

        blocks.remove(
                index);

        blocks.add(
                newIndex,
                block);

        contentBlocks
                .getChildren()
                .clear();

        contentBlocks
                .getChildren()
                .addAll(
                        blocks);
    }

    // =========================================================
    // COLLECT CONTENT BLOCKS
    // =========================================================

    private List<ContentBlock> collectContentBlocks() {

        List<ContentBlock> result =
                new ArrayList<>();

        int order = 1;

        for (VBox block : blocks) {

            if (block == null ||
                    block.getChildren().isEmpty()) {

                continue;
            }

            // =================================================
            // GET HEADER
            // =================================================

            HBox header = null;

            if (block.getChildren()
                    .get(0) instanceof HBox) {

                header =
                        (HBox) block.getChildren()
                                .get(0);
            }

            if (header == null) {
                continue;
            }

            // =================================================
            // GET TYPE
            // =================================================

            String type = "";

            for (javafx.scene.Node node :
                    header.getChildren()) {

                if (node instanceof Label) {

                    Label label =
                            (Label) node;

                    String text =
                            safe(label.getText())
                                    .trim()
                                    .toUpperCase();

                    if (text.equals("TEXT") ||
                            text.equals("IMAGE") ||
                            text.equals("VIDEO") ||
                            text.equals("DOCUMENT")) {

                        type = text;
                        break;
                    }
                }
            }

            if (type.isEmpty()) {
                continue;
            }

            // =================================================
            // GET CONTENT
            // =================================================

            String content = "";

            // =================================================
            // IMAGE
            // =================================================

            if (type.equals("IMAGE")) {

                Object userData =
                        block.getUserData();

                if (userData != null) {

                    content =
                            safe(userData.toString());
                }

            }

            // =================================================
            // TEXT / VIDEO / DOCUMENT
            // =================================================

            else {

                for (javafx.scene.Node node :
                        block.getChildren()) {

                    if (node instanceof TextArea) {

                        TextArea area =
                                (TextArea) node;

                        content =
                                safe(area.getText());

                        break;
                    }

                    if (node instanceof TextField) {

                        TextField field =
                                (TextField) node;

                        content =
                                safe(field.getText());

                        break;
                    }
                }
            }

            // =================================================
            // IGNORE EMPTY BLOCK
            // =================================================

            if (content.trim().isEmpty()) {

                System.out.println(
                        "Skipping empty "
                        + type
                        + " block.");

                continue;
            }

            // =================================================
            // CREATE CONTENT BLOCK
            // =================================================

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

        // =====================================================
        // CANCEL
        // =====================================================

        Button cancel = new Button(
                "Cancel");

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

        // =====================================================
        // SAVE
        // =====================================================

        Button save = new Button(
                "✓  Save Lesson");

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

        // =====================================================
        // SAFETY
        // =====================================================

        if (course == null ||
                module == null) {

            showError(
                    "Save Failed",
                    "Course or module was not found.");

            return;
        }

        // =====================================================
        // GET DATA
        // =====================================================

        String title =
                safe(titleField.getText())
                        .trim();

        String description =
                safe(descriptionField.getText())
                        .trim();

        // =====================================================
        // VALIDATION
        // =====================================================

        if (title.isEmpty()) {

            showError(
                    "Validation",
                    "Lesson title is required.");

            titleField.requestFocus();

            return;
        }

        if (description.isEmpty()) {

            showError(
                    "Validation",
                    "Lesson description is required.");

            descriptionField.requestFocus();

            return;
        }

        // =====================================================
        // COLLECT BLOCKS
        // =====================================================

        List<ContentBlock> lessonContent =
                collectContentBlocks();

        // =====================================================
        // DEBUG
        // =====================================================

        System.out.println(
                "================================");

        System.out.println(
                "Saving Lesson");

        System.out.println(
                "Course ID: "
                + course.getCourseId());

        System.out.println(
                "Module ID: "
                + module.getModuleId());

        System.out.println(
                "Title: "
                + title);

        System.out.println(
                "Content Blocks: "
                + lessonContent.size());

        for (ContentBlock block :
                lessonContent) {

            System.out.println(
                    block.getOrder()
                    + " | "
                    + block.getType()
                    + " | "
                    + block.getContent());
        }

        System.out.println(
                "================================");

        // =====================================================
        // SAVE TO FIRESTORE
        // =====================================================

        boolean saved =
                lessonController.addLesson(
                        course.getCourseId(),
                        module.getModuleId(),
                        title,
                        description,
                        "",
                        lessonContent);

        // =====================================================
        // FAILURE
        // =====================================================

        if (!saved) {

            showError(
                    "Save Failed",
                    "Lesson could not be created.");

            return;
        }

        // =====================================================
        // SUCCESS
        // =====================================================

        System.out.println(
                "Lesson created successfully.");

        // =====================================================
        // RETURN TO MODULE PAGE
        // =====================================================

        goBack();
    }

    // =========================================================
    // ADD BUTTON
    // =========================================================

    private Button createAddButton(
            String text) {

        Button button =
                new Button(text);

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

        Label label =
                new Label(text);

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
                "-fx-focus-color:transparent;" +
                "-fx-faint-focus-color:transparent;" +
                "-fx-padding:8;" +
                "-fx-font-size:12px;");
    }

    // =========================================================
    // TEXT AREA
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
    // ERROR ALERT
    // =========================================================

    private void showError(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR);

        alert.setTitle(
                title);

        alert.setHeaderText(
                null);

        alert.setContentText(
                message);

        alert.showAndWait();
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

        AdminModulePage modulePage =
                new AdminModulePage(course);

        LoginPage.mainStage.setScene(
                modulePage.getModuleScene());
    }
}