package com.pravartak.view.farmer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.pravartak.controller.admincontroller.LessonController;
import com.pravartak.controller.admincontroller.ModuleController;
import com.pravartak.model.admin.ContentBlock;
import com.pravartak.model.admin.Course;
import com.pravartak.model.admin.Lesson;
import com.pravartak.model.admin.Module;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FarmerModulesPage {

        private final Course course;

        private Scene modulesPageScene;

        private BorderPane mainBorderPane;

        private final ModuleController moduleController = new ModuleController();

        private final LessonController lessonController = new LessonController();

        // =========================================================
        // LOCAL YOUTUBE SERVER
        // =========================================================

        private static ServerSocket youtubeServerSocket;

        private static int youtubePort = -1;

        private static boolean youtubeServerStarted = false;

        // =========================================================
        // CURRENT YOUTUBE PLAYER
        // =========================================================

        private WebView currentYouTubeWebView;

        private WebEngine currentYouTubeEngine;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public FarmerModulesPage(Course course) {
                this.course = course;
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public Scene getModulesPageScene() {

                mainBorderPane = new BorderPane();

                mainBorderPane.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // NAVBAR
                // =====================================================

                mainBorderPane.setTop(
                                new NavBar().createNavbar("Learning"));

                // =====================================================
                // FOOTER
                // =====================================================

                mainBorderPane.setBottom(
                                new Footer().createFooter());

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                VBox mainContent = new VBox(20);

                mainContent.setPadding(
                                new Insets(
                                                25,
                                                40,
                                                30,
                                                40));

                mainContent.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = new Button("← Back to Learning");

                backButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                backButton.setTextFill(
                                Color.web("#DCEBDD"));

                backButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-border-color:#4B7354;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-cursor:hand;");

                backButton.setOnAction(e -> {

                        stopCurrentYouTubePlayer();

                        LearningPage learningPage = new LearningPage();

                        LoginPage.mainStage.setScene(learningPage.get_learning_pageScene());
                });

                // =====================================================
                // COURSE HEADER
                // =====================================================

                VBox courseHeader = new VBox(7);

                Label courseLabel = new Label("COURSE");

                courseLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                courseLabel.setTextFill(
                                Color.web("#78C47E"));

                Label courseName = new Label(
                                course != null
                                                ? safe(course.getTitle())
                                                : "Course");

                courseName.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                30));

                courseName.setTextFill(
                                Color.WHITE);

                courseHeader.getChildren().addAll(
                                courseLabel,
                                courseName);

                // =====================================================
                // MODULE HEADING
                // =====================================================

                Label moduleHeading = new Label("Course Modules");

                moduleHeading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));

                moduleHeading.setTextFill(
                                Color.WHITE);

                Label moduleSubHeading = new Label(
                                "Select a module to view its lessons.");

                moduleSubHeading.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                moduleSubHeading.setTextFill(
                                Color.web("#9FB5A2"));

                // =====================================================
                // MODULE CONTAINER
                // =====================================================

                VBox modulesContainer = new VBox(15);

                modulesContainer.setPadding(
                                new Insets(
                                                5,
                                                0,
                                                20,
                                                0));

                // =====================================================
                // LOAD MODULES
                // =====================================================

                if (course == null) {

                        modulesContainer
                                        .getChildren()
                                        .add(
                                                        createMessage(
                                                                        "Course not found."));

                } else {

                        int courseId = course.getCourseId();

                        List<Module> modules;

                        try {

                                modules = moduleController
                                                .getModulesByCourse(
                                                                courseId);

                        } catch (Exception e) {

                                e.printStackTrace();

                                modules = null;
                        }

                        // =================================================
                        // NO MODULES
                        // =================================================

                        if (modules == null ||
                                        modules.isEmpty()) {

                                modulesContainer
                                                .getChildren()
                                                .add(
                                                                createMessage(
                                                                                "No modules have been added to this course yet."));

                        } else {

                                // =================================================
                                // DISPLAY MODULES
                                // =================================================

                                for (int i = 0; i < modules.size(); i++) {

                                        Module module = modules.get(i);

                                        if (module == null) {
                                                continue;
                                        }

                                        modulesContainer
                                                        .getChildren()
                                                        .add(
                                                                        createModule(
                                                                                        i + 1,
                                                                                        module));
                                }
                        }
                }

                // =====================================================
                // ADD CONTENT
                // =====================================================

                mainContent
                                .getChildren()
                                .addAll(
                                                backButton,
                                                courseHeader,
                                                moduleHeading,
                                                moduleSubHeading,
                                                modulesContainer);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                mainContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background:#080C0D;" +
                                                "-fx-background-color:#080C0D;" +
                                                "-fx-border-color:transparent;");

                mainBorderPane.setCenter(
                                scrollPane);

                // =====================================================
                // SCENE
                // =====================================================

                modulesPageScene = new Scene(
                                mainBorderPane,
                                1200,
                                750);

                // =====================================================
                // STOP VIDEO WHEN SCENE CHANGES
                // =====================================================

                modulesPageScene
                                .windowProperty()
                                .addListener(
                                                (obs, oldWindow, newWindow) -> {

                                                        if (newWindow != null) {

                                                                newWindow
                                                                                .setOnCloseRequest(
                                                                                                event -> {
                                                                                                        stopCurrentYouTubePlayer();
                                                                                                        stopYouTubeServer();
                                                                                                });
                                                        }
                                                });

                return modulesPageScene;
        }

        // =========================================================
        // CREATE MODULE
        // =========================================================

        private VBox createModule(
                        int moduleNumber,
                        Module module) {

                VBox moduleBox = new VBox();

                moduleBox.setMaxWidth(
                                Double.MAX_VALUE);

                moduleBox.setStyle(
                                "-fx-background-color:#193522;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#31583A;" +
                                                "-fx-border-radius:15;" +
                                                "-fx-border-width:1;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // MODULE HEADER
                // =====================================================

                HBox moduleHeader = new HBox(14);

                moduleHeader.setAlignment(
                                Pos.CENTER_LEFT);

                moduleHeader.setPadding(
                                new Insets(16));

                moduleHeader.setMaxWidth(
                                Double.MAX_VALUE);

                moduleHeader.setStyle(
                                "-fx-background-color:#193522;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // MODULE IMAGE
                // =====================================================

                StackPane imageContainer = new StackPane();

                imageContainer.setMinSize(110, 75);
                imageContainer.setPrefSize(110, 75);
                imageContainer.setMaxSize(110, 75);

                imageContainer.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-background-radius:10;" +
                                                "-fx-border-color:#31583A;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-border-width:1;");

                ImageView moduleImageView = new ImageView();

                moduleImageView.setFitWidth(110);
                moduleImageView.setFitHeight(75);
                moduleImageView.setPreserveRatio(false);

                moduleImageView.setSmooth(true);

                // =====================================================
                // LOAD IMAGE FROM MODULE
                // =====================================================

                String imageUrl = safe(
                                module.getImageUrl()).trim();

                if (!imageUrl.isEmpty()) {

                        try {

                                Image image = new Image(
                                                imageUrl,
                                                110,
                                                75,
                                                false,
                                                true,
                                                true);

                                moduleImageView.setImage(image);

                                // =================================================
                                // IMAGE LOAD ERROR
                                // =================================================

                                image.errorProperty().addListener(
                                                (obs, oldValue, newValue) -> {

                                                        if (newValue) {

                                                                moduleImageView
                                                                                .setImage(null);

                                                                addImagePlaceholder(
                                                                                imageContainer);
                                                        }
                                                });

                                imageContainer
                                                .getChildren()
                                                .add(moduleImageView);

                        } catch (Exception e) {

                                System.out.println(
                                                "Unable to load module image: "
                                                                + imageUrl);

                                addImagePlaceholder(
                                                imageContainer);
                        }

                } else {

                        addImagePlaceholder(
                                        imageContainer);
                }

                // =====================================================
                // NUMBER CIRCLE
                // =====================================================

                StackPaneCircle numberCircle = new StackPaneCircle(moduleNumber);

                // =====================================================
                // MODULE INFORMATION
                // =====================================================

                VBox moduleInfo = new VBox(5);

                Label title = new Label(safe(module.getTitle()));

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                title.setTextFill(
                                Color.WHITE);

                title.setWrapText(true);

                Label description = new Label(
                                safe(
                                                module.getDescription()));

                description.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                description.setTextFill(
                                Color.web("#AFC4B2"));

                description.setWrapText(true);

                // =====================================================
                // LOAD LESSONS
                // =====================================================

                List<Lesson> lessons = null;

                if (course != null) {

                        try {

                                lessons = lessonController
                                                .getLessonsByModule(
                                                                course.getCourseId(),
                                                                module.getModuleId());

                        } catch (Exception e) {

                                e.printStackTrace();
                        }
                }

                int lessonCount = lessons == null
                                ? 0
                                : lessons.size();

                Label lessonCountLabel = new Label(
                                lessonCount
                                                + (lessonCount == 1
                                                                ? " lesson"
                                                                : " lessons"));

                lessonCountLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                lessonCountLabel.setTextFill(
                                Color.web("#75C77D"));

                moduleInfo
                                .getChildren()
                                .addAll(
                                                title,
                                                description,
                                                lessonCountLabel);

                HBox.setHgrow(
                                moduleInfo,
                                Priority.ALWAYS);

                // =====================================================
                // SHOW MORE
                // =====================================================

                Button showMoreButton = new Button(
                                lessonCount == 0
                                                ? "No Lessons"
                                                : "Show More  ▼");

                showMoreButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                showMoreButton.setTextFill(
                                Color.web("#CFE4D2"));

                showMoreButton.setStyle(
                                "-fx-background-color:#285532;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-cursor:hand;");

                if (lessonCount == 0) {

                        showMoreButton.setDisable(true);

                        showMoreButton.setOpacity(0.6);
                }

                // =====================================================
                // HEADER CHILDREN
                // =====================================================

                moduleHeader
                                .getChildren()
                                .addAll(
                                                imageContainer,
                                                numberCircle,
                                                moduleInfo,
                                                showMoreButton);

                // =====================================================
                // LESSON CONTAINER
                // =====================================================

                VBox lessonsContainer = new VBox(9);

                lessonsContainer.setPadding(
                                new Insets(
                                                0,
                                                18,
                                                16,
                                                75));

                lessonsContainer.setVisible(false);

                lessonsContainer.setManaged(false);

                // =====================================================
                // CREATE LESSON CARDS
                // =====================================================

                if (lessons != null) {

                        for (Lesson lesson : lessons) {

                                if (lesson == null) {
                                        continue;
                                }

                                lessonsContainer
                                                .getChildren()
                                                .add(
                                                                createLesson(
                                                                                lesson));
                        }
                }

                // =====================================================
                // TOGGLE LESSONS
                // =====================================================

                Runnable toggleLessons = () -> {

                        boolean visible = lessonsContainer.isVisible();

                        if (visible) {

                                lessonsContainer.setVisible(false);

                                lessonsContainer.setManaged(false);

                                showMoreButton.setText(
                                                "Show More  ▼");

                        } else {

                                if (lessonCount == 0) {
                                        return;
                                }

                                lessonsContainer.setVisible(true);

                                lessonsContainer.setManaged(true);

                                showMoreButton.setText(
                                                "Show Less  ▲");
                        }
                };

                // =====================================================
                // MODULE HEADER CLICK
                // =====================================================

                moduleHeader.setOnMouseClicked(e -> {

                        toggleLessons.run();

                        e.consume();
                });

                // =====================================================
                // HOVER
                // =====================================================

                moduleHeader.setOnMouseEntered(e -> {

                        moduleHeader.setStyle(
                                        "-fx-background-color:#21452B;" +
                                                        "-fx-background-radius:15;" +
                                                        "-fx-cursor:hand;");
                });

                moduleHeader.setOnMouseExited(e -> {

                        moduleHeader.setStyle(
                                        "-fx-background-color:#193522;" +
                                                        "-fx-background-radius:15;" +
                                                        "-fx-cursor:hand;");
                });

                // =====================================================
                // SHOW MORE BUTTON
                // =====================================================

                showMoreButton.setOnAction(e -> {

                        toggleLessons.run();

                        e.consume();
                });

                showMoreButton.setOnMouseClicked(
                                e -> e.consume());

                // =====================================================
                // ADD
                // =====================================================

                moduleBox
                                .getChildren()
                                .addAll(
                                                moduleHeader,
                                                lessonsContainer);

                return moduleBox;
        }

        // =========================================================
        // IMAGE PLACEHOLDER
        // =========================================================

        private void addImagePlaceholder(
                        StackPane imageContainer) {

                imageContainer.getChildren().clear();

                Label imageLabel = new Label("IMAGE");

                imageLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

                imageLabel.setTextFill(
                                Color.web("#6F8172"));

                imageContainer
                                .getChildren()
                                .add(imageLabel);
        }

        // =========================================================
        // CREATE LESSON
        // =========================================================

        private HBox createLesson(
                        Lesson lesson) {

                HBox lessonBox = new HBox(12);

                lessonBox.setAlignment(
                                Pos.CENTER_LEFT);

                lessonBox.setPadding(
                                new Insets(
                                                11,
                                                12,
                                                11,
                                                12));

                lessonBox.setMaxWidth(
                                Double.MAX_VALUE);

                lessonBox.setStyle(
                                "-fx-background-color:#223F2A;" +
                                                "-fx-background-radius:10;" +
                                                "-fx-border-color:#345A3C;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // STATUS
                // =====================================================

                Circle statusCircle = new Circle(9);

                statusCircle.setFill(
                                Color.web("#536A58"));

                // =====================================================
                // NUMBER
                // =====================================================

                Label number = new Label(
                                "Lesson "
                                                + lesson.getLessonOrder());

                number.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                number.setTextFill(
                                Color.web("#82C989"));

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label(
                                safe(
                                                lesson.getTitle()));

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                title.setTextFill(
                                Color.WHITE);

                title.setWrapText(true);

                HBox.setHgrow(
                                title,
                                Priority.ALWAYS);

                // =====================================================
                // ARROW
                // =====================================================

                Label arrow = new Label("→");

                arrow.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));

                arrow.setTextFill(
                                Color.web("#7ED184"));

                lessonBox
                                .getChildren()
                                .addAll(
                                                statusCircle,
                                                number,
                                                title,
                                                arrow);

                // =====================================================
                // CLICK
                // =====================================================

                lessonBox.setOnMouseClicked(e -> {

                        openLessonContent(
                                        lesson);

                        e.consume();
                });

                // =====================================================
                // HOVER
                // =====================================================

                lessonBox.setOnMouseEntered(e -> {

                        lessonBox.setStyle(
                                        "-fx-background-color:#2B5134;" +
                                                        "-fx-background-radius:10;" +
                                                        "-fx-border-color:#63A86B;" +
                                                        "-fx-border-radius:10;" +
                                                        "-fx-cursor:hand;");
                });

                lessonBox.setOnMouseExited(e -> {

                        lessonBox.setStyle(
                                        "-fx-background-color:#223F2A;" +
                                                        "-fx-background-radius:10;" +
                                                        "-fx-border-color:#345A3C;" +
                                                        "-fx-border-radius:10;" +
                                                        "-fx-cursor:hand;");
                });

                return lessonBox;
        }

        // =========================================================
        // OPEN LESSON CONTENT
        // =========================================================

        private void openLessonContent(
                        Lesson lesson) {

                stopCurrentYouTubePlayer();

                BorderPane lessonPane = new BorderPane();

                lessonPane.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // TOP
                // =====================================================

                VBox top = new VBox(6);

                top.setPadding(
                                new Insets(
                                                25,
                                                40,
                                                20,
                                                40));

                Label courseLabel = new Label(
                                course != null
                                                ? safe(
                                                                course.getTitle())
                                                : "Course");

                courseLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                courseLabel.setTextFill(
                                Color.web("#78C47E"));

                Label moduleLabel = new Label(
                                "Module "
                                                + lesson.getModuleId());

                moduleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                moduleLabel.setTextFill(
                                Color.web("#9FB5A2"));

                Label lessonHeading = new Label(
                                "Lesson "
                                                + lesson.getLessonOrder()
                                                + ": "
                                                + safe(
                                                                lesson.getTitle()));

                lessonHeading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                27));

                lessonHeading.setTextFill(
                                Color.WHITE);

                lessonHeading.setWrapText(true);

                top.getChildren()
                                .addAll(
                                                courseLabel,
                                                moduleLabel,
                                                lessonHeading);

                lessonPane.setTop(top);

                // =====================================================
                // CONTENT
                // =====================================================

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                25,
                                                50,
                                                35,
                                                50));

                // =====================================================
                // DESCRIPTION
                // =====================================================

                String descriptionValue = safe(
                                lesson.getDescription());

                if (!descriptionValue.isEmpty()) {

                        Label description = new Label(
                                        descriptionValue);

                        description.setFont(
                                        Font.font(
                                                        "Arial",
                                                        15));

                        description.setTextFill(
                                        Color.web("#C7D8C9"));

                        description.setWrapText(true);

                        description.setLineSpacing(5);

                        content
                                        .getChildren()
                                        .add(
                                                        description);
                }

                // =====================================================
                // CONTENT BLOCKS
                // =====================================================

                List<ContentBlock> blocks = lesson.getContentBlocks();

                if (blocks != null &&
                                !blocks.isEmpty()) {

                        for (ContentBlock block : blocks) {

                                if (block == null) {
                                        continue;
                                }

                                content
                                                .getChildren()
                                                .add(
                                                                createFarmerContentBlock(
                                                                                block));
                        }

                } else {

                        Label noContent = new Label(
                                        "No additional content available.");

                        noContent.setFont(
                                        Font.font(
                                                        "Arial",
                                                        13));

                        noContent.setTextFill(
                                        Color.web("#777777"));

                        content
                                        .getChildren()
                                        .add(
                                                        noContent);
                }

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = new Button(
                                "← Back to Modules");

                backButton.setPrefHeight(38);

                backButton.setPrefWidth(170);

                backButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                backButton.setTextFill(
                                Color.WHITE);

                backButton.setStyle(
                                "-fx-background-color:#32683B;" +
                                                "-fx-background-radius:9;" +
                                                "-fx-cursor:hand;");

                backButton.setOnAction(e -> {

                        stopCurrentYouTubePlayer();

                        FarmerModulesPage page = new FarmerModulesPage(course);

                        LoginPage.mainStage.setScene(page.getModulesPageScene());
                });

                content
                                .getChildren()
                                .add(
                                                backButton);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                content);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background:#080C0D;" +
                                                "-fx-background-color:#080C0D;" +
                                                "-fx-border-color:transparent;");

                lessonPane.setCenter(
                                scrollPane);

                // =====================================================
                // FOOTER
                // =====================================================

                lessonPane.setBottom(
                                new Footer()
                                                .createFooter());

                // =====================================================
                // CHANGE ROOT
                // =====================================================

                if (mainBorderPane != null &&
                                mainBorderPane.getScene() != null) {

                        mainBorderPane
                                        .getScene()
                                        .setRoot(
                                                        lessonPane);
                }
        }

        // =========================================================
        // FARMER CONTENT BLOCK
        // =========================================================

        private VBox createFarmerContentBlock(
                        ContentBlock block) {

                VBox box = new VBox(10);

                box.setPadding(
                                new Insets(16));

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color:#193522;" +
                                                "-fx-background-radius:12;" +
                                                "-fx-border-color:#31583A;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-border-width:1;");

                String type = safe(
                                block.getType())
                                .toUpperCase();

                String value = safe(
                                block.getContent())
                                .trim();

                // =====================================================
                // TEXT
                // =====================================================

                if (type.equals("TEXT")) {

                        Label text = new Label(value);

                        text.setFont(
                                        Font.font(
                                                        "Arial",
                                                        15));

                        text.setTextFill(
                                        Color.web("#D5E5D7"));

                        text.setWrapText(true);

                        text.setLineSpacing(5);

                        box.getChildren()
                                        .add(
                                                        text);
                }

                // =====================================================
                // IMAGE
                // =====================================================

                else if (type.equals("IMAGE")) {

                        Label heading = createContentHeading(
                                        "Image");

                        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView();

                        imageView.setFitWidth(1200);

                        imageView.setPreserveRatio(true);

                        try {

                                if (!value.isEmpty()) {

                                        imageView.setImage(
                                                        new javafx.scene.image.Image(
                                                                        value,
                                                                        true));
                                }

                        } catch (Exception e) {

                                System.out.println(
                                                "Unable to load image: "
                                                                + value);
                        }

                        box.getChildren()
                                        .addAll(
                                                        heading,
                                                        imageView);
                }

                // =====================================================
                // VIDEO
                // =====================================================

                else if (type.equals("VIDEO")) {

                        Label heading = createContentHeading(
                                        "Video");

                        WebView youtubePlayer = createYouTubePlayer(
                                        value);

                        box.getChildren()
                                        .addAll(
                                                        heading,
                                                        youtubePlayer);
                }

                // =====================================================
                // DOCUMENT
                // =====================================================

                else if (type.equals("DOCUMENT")) {

                        Label heading = createContentHeading(
                                        "Document");

                        Label documentUrl = new Label(value);

                        documentUrl.setFont(
                                        Font.font(
                                                        "Arial",
                                                        13));

                        documentUrl.setTextFill(
                                        Color.web("#C7D8C9"));

                        documentUrl.setWrapText(true);

                        box.getChildren()
                                        .addAll(
                                                        heading,
                                                        documentUrl);
                }

                // =====================================================
                // UNKNOWN
                // =====================================================

                else {

                        Label unknown = new Label(value);

                        unknown.setFont(
                                        Font.font(
                                                        "Arial",
                                                        13));

                        unknown.setTextFill(
                                        Color.web("#C7D8C9"));

                        unknown.setWrapText(true);

                        box.getChildren()
                                        .add(
                                                        unknown);
                }

                return box;
        }

        // =========================================================
        // CONTENT HEADING
        // =========================================================

        private Label createContentHeading(
                        String text) {

                Label heading = new Label(text);

                heading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                heading.setTextFill(
                                Color.web("#78C47E"));

                return heading;
        }

        // =========================================================
        // CREATE YOUTUBE PLAYER
        // =========================================================

        private WebView createYouTubePlayer(
                        String url) {

                WebView webView = new WebView();

                webView.setPrefHeight(400);

                webView.setMinHeight(300);

                webView.setMaxWidth(
                                Double.MAX_VALUE);

                WebEngine engine = webView.getEngine();

                // =====================================================
                // SAVE CURRENT PLAYER
                // =====================================================

                currentYouTubeWebView = webView;

                currentYouTubeEngine = engine;

                // =====================================================
                // USER AGENT
                // =====================================================

                engine.setUserAgent(
                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                                                + "AppleWebKit/537.36 "
                                                + "(KHTML, like Gecko) "
                                                + "Chrome/151.0.0.0 "
                                                + "Safari/537.36");

                // =====================================================
                // EXTRACT VIDEO ID
                // =====================================================

                String videoId = extractYouTubeVideoId(
                                url);

                if (videoId.isEmpty()) {

                        engine.loadContent(
                                        createErrorHtml(
                                                        "Invalid YouTube video URL."));

                        return webView;
                }

                // =====================================================
                // START LOCAL SERVER
                // =====================================================

                try {

                        startYouTubeServer();

                        String playerUrl = "http://127.0.0.1:"
                                        + youtubePort
                                        + "/youtube/"
                                        + videoId;

                        System.out.println(
                                        "Loading YouTube video: "
                                                        + videoId);

                        System.out.println(
                                        "Player URL: "
                                                        + playerUrl);

                        engine.load(
                                        playerUrl);

                } catch (Exception e) {

                        e.printStackTrace();

                        engine.loadContent(
                                        createErrorHtml(
                                                        "Unable to load YouTube video."));
                }

                // =====================================================
                // STOP PLAYER WHEN WEBVIEW IS REMOVED
                // =====================================================

                webView
                                .sceneProperty()
                                .addListener(
                                                (obs, oldScene, newScene) -> {

                                                        if (newScene == null) {

                                                                pauseAndStopWebView(
                                                                                webView);
                                                        }
                                                });

                return webView;
        }

        // =========================================================
        // PAUSE AND STOP CURRENT PLAYER
        // =========================================================

        private void stopCurrentYouTubePlayer() {

                if (currentYouTubeWebView == null ||
                                currentYouTubeEngine == null) {

                        return;
                }

                pauseAndStopWebView(
                                currentYouTubeWebView);

                currentYouTubeWebView = null;

                currentYouTubeEngine = null;
        }

        // =========================================================
        // PAUSE / STOP WEBVIEW
        // =========================================================

        private void pauseAndStopWebView(
                        WebView webView) {

                if (webView == null) {
                        return;
                }

                try {

                        WebEngine engine = webView.getEngine();

                        // =================================================
                        // CALL YOUTUBE API
                        // =================================================

                        engine.executeScript(
                                        "try {" +
                                                        "if (window.pauseYouTubePlayer) {" +
                                                        "window.pauseYouTubePlayer();" +
                                                        "}" +
                                                        "} catch(e) {}");

                } catch (Exception ignored) {
                }

                try {

                        // =================================================
                        // STOP LOADING
                        // =================================================

                        webView.getEngine()
                                        .getLoadWorker()
                                        .cancel();

                } catch (Exception ignored) {
                }

                try {

                        // =================================================
                        // CLEAR WEB PAGE
                        // =================================================

                        webView.getEngine()
                                        .loadContent(
                                                        "<html><body></body></html>");

                } catch (Exception ignored) {
                }
        }

        // =========================================================
        // START LOCAL YOUTUBE SERVER
        // =========================================================

        private static synchronized void startYouTubeServer()
                        throws IOException {

                if (youtubeServerStarted) {
                        return;
                }

                youtubeServerSocket = new ServerSocket(
                                0,
                                50,
                                InetAddress
                                                .getByName(
                                                                "127.0.0.1"));

                youtubePort = youtubeServerSocket
                                .getLocalPort();

                youtubeServerStarted = true;

                System.out.println(
                                "================================");

                System.out.println(
                                "Local YouTube server started.");

                System.out.println(
                                "Port: "
                                                + youtubePort);

                System.out.println(
                                "================================");

                Thread serverThread = new Thread(
                                () -> {

                                        while (youtubeServerStarted &&
                                                        youtubeServerSocket != null &&
                                                        !youtubeServerSocket.isClosed()) {

                                                try {

                                                        Socket socket = youtubeServerSocket
                                                                        .accept();

                                                        Thread clientThread = new Thread(
                                                                        () -> handleYouTubeRequest(
                                                                                        socket));

                                                        clientThread.setDaemon(
                                                                        true);

                                                        clientThread.start();

                                                } catch (IOException e) {

                                                        if (youtubeServerStarted) {

                                                                System.out.println(
                                                                                "YouTube server stopped.");
                                                        }
                                                }
                                        }
                                });

                serverThread.setDaemon(true);

                serverThread.setName(
                                "AgroBiz-YouTube-Server");

                serverThread.start();
        }

        // =========================================================
        // HANDLE LOCAL HTTP REQUEST
        // =========================================================

        private static void handleYouTubeRequest(
                        Socket socket) {

                try {

                        InputStream input = socket.getInputStream();

                        OutputStream output = socket.getOutputStream();

                        byte[] buffer = new byte[8192];

                        StringBuilder request = new StringBuilder();

                        int read;

                        while ((read = input.read(buffer)) > 0) {

                                request.append(
                                                new String(
                                                                buffer,
                                                                0,
                                                                read,
                                                                StandardCharsets.UTF_8));

                                if (request
                                                .toString()
                                                .contains(
                                                                "\r\n\r\n")) {

                                        break;
                                }

                                if (request.length() > 20000) {
                                        break;
                                }
                        }

                        String requestText = request.toString();

                        String firstLine = requestText
                                        .split("\r\n")[0];

                        String[] parts = firstLine.split(" ");

                        String path = parts.length >= 2
                                        ? parts[1]
                                        : "/";

                        String videoId = "";

                        if (path.startsWith(
                                        "/youtube/")) {

                                videoId = path.substring(
                                                "/youtube/"
                                                                .length());
                        }

                        videoId = cleanVideoId(
                                        videoId);

                        String html;

                        if (videoId.isEmpty()) {

                                html = createErrorHtml(
                                                "Invalid YouTube video.");

                        } else {

                                String embedUrl = "https://www.youtube-nocookie.com/embed/"
                                                + videoId
                                                + "?rel=0"
                                                + "&playsinline=1"
                                                + "&modestbranding=1"
                                                + "&enablejsapi=1"
                                                + "&origin=http://127.0.0.1:"
                                                + youtubePort;

                                html = createYouTubeHtml(
                                                embedUrl);
                        }

                        byte[] data = html.getBytes(
                                        StandardCharsets.UTF_8);

                        String headers = "HTTP/1.1 200 OK\r\n"
                                        + "Content-Type: text/html; charset=UTF-8\r\n"
                                        + "Content-Length: "
                                        + data.length
                                        + "\r\n"
                                        + "Referrer-Policy: strict-origin-when-cross-origin\r\n"
                                        + "Connection: close\r\n"
                                        + "\r\n";

                        output.write(
                                        headers.getBytes(
                                                        StandardCharsets.UTF_8));

                        output.write(data);

                        output.flush();

                        socket.close();

                } catch (Exception e) {

                        try {
                                socket.close();
                        } catch (Exception ignored) {
                        }
                }
        }

        // =========================================================
        // YOUTUBE HTML
        // =========================================================

        private static String createYouTubeHtml(
                        String embedUrl) {

                return "<!DOCTYPE html>"
                                + "<html>"
                                + "<head>"

                                + "<meta charset=\"UTF-8\">"

                                + "<meta name=\"viewport\" "
                                + "content=\"width=device-width, initial-scale=1.0\">"

                                + "<meta name=\"referrer\" "
                                + "content=\"strict-origin-when-cross-origin\">"

                                + "<style>"

                                + "html,body{"
                                + "margin:0;"
                                + "padding:0;"
                                + "width:100%;"
                                + "height:100%;"
                                + "overflow:hidden;"
                                + "background:#0D1213;"
                                + "}"

                                + "iframe{"
                                + "width:100%;"
                                + "height:100%;"
                                + "border:0;"
                                + "display:block;"
                                + "}"

                                + "</style>"

                                + "</head>"

                                + "<body>"

                                + "<iframe "
                                + "id=\"youtubePlayer\" "
                                + "src=\"" + embedUrl + "\" "
                                + "title=\"YouTube video player\" "

                                + "allow=\"accelerometer; "
                                + "autoplay; "
                                + "clipboard-write; "
                                + "encrypted-media; "
                                + "gyroscope; "
                                + "picture-in-picture; "
                                + "web-share\" "

                                + "allowfullscreen "

                                + "referrerpolicy=\"strict-origin-when-cross-origin\">"

                                + "</iframe>"

                                // =================================================
                                // YOUTUBE API
                                // =================================================

                                + "<script>"

                                + "var player;"

                                + "var playerReady=false;"

                                + "function onYouTubeIframeAPIReady(){"
                                + "player=new YT.Player('youtubePlayer',{"
                                + "events:{"
                                + "'onReady':function(event){"
                                + "playerReady=true;"
                                + "},"

                                + "'onStateChange':function(event){"

                                // =============================================
                                // PAUSED
                                // =============================================

                                + "if(event.data===YT.PlayerState.PAUSED){"

                                + "try{"
                                + "player.pauseVideo();"
                                + "}catch(e){}"

                                + "}"

                                + "}"

                                + "}"
                                + "});"
                                + "}"

                                // =================================================
                                // PAUSE FUNCTION
                                // =================================================

                                + "function pauseYouTubePlayer(){"

                                + "try{"

                                + "if(playerReady && player){"

                                + "player.pauseVideo();"

                                + "}"

                                + "}catch(e){}"

                                + "}"

                                // =================================================
                                // STOP FUNCTION
                                // =================================================

                                + "function stopYouTubePlayer(){"

                                + "try{"

                                + "if(playerReady && player){"

                                + "player.stopVideo();"

                                + "}"

                                + "}catch(e){}"

                                + "}"

                                + "</script>"

                                // =================================================
                                // LOAD API
                                // =================================================

                                + "<script "
                                + "src=\"https://www.youtube.com/iframe_api\">"
                                + "</script>"

                                + "</body>"

                                + "</html>";
        }

        // =========================================================
        // EXTRACT YOUTUBE VIDEO ID
        // =========================================================

        private String extractYouTubeVideoId(
                        String url) {

                if (url == null ||
                                url.trim().isEmpty()) {

                        return "";
                }

                String value = url.trim();

                try {

                        URI uri = URI.create(value);

                        String host = uri.getHost();

                        if (host == null) {
                                return "";
                        }

                        host = host.toLowerCase();

                        // =================================================
                        // youtu.be/VIDEO_ID
                        // =================================================

                        if (host.equals(
                                        "youtu.be")) {

                                return cleanVideoId(
                                                uri.getPath());
                        }

                        // =================================================
                        // youtube.com
                        // =================================================

                        if (host.contains(
                                        "youtube.com")) {

                                String query = uri.getRawQuery();

                                // =================================================
                                // WATCH URL
                                // =================================================

                                if (query != null) {

                                        String[] parameters = query.split("&");

                                        for (String parameter : parameters) {

                                                String[] pair = parameter.split(
                                                                "=",
                                                                2);

                                                if (pair.length == 2 &&
                                                                pair[0].equals("v")) {

                                                        return cleanVideoId(
                                                                        URLDecoder.decode(
                                                                                        pair[1],
                                                                                        StandardCharsets.UTF_8));
                                                }
                                        }
                                }

                                // =================================================
                                // EMBED URL
                                // =================================================

                                String path = uri.getPath();

                                if (path != null &&
                                                path.startsWith(
                                                                "/embed/")) {

                                        return cleanVideoId(
                                                        path.substring(
                                                                        "/embed/"
                                                                                        .length()));
                                }

                                // =================================================
                                // SHORTS URL
                                // =================================================

                                if (path != null &&
                                                path.startsWith(
                                                                "/shorts/")) {

                                        return cleanVideoId(
                                                        path.substring(
                                                                        "/shorts/"
                                                                                        .length()));
                                }
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Unable to extract YouTube ID.");

                        e.printStackTrace();
                }

                return "";
        }

        // =========================================================
        // CLEAN VIDEO ID
        // =========================================================

        private static String cleanVideoId(
                        String value) {

                if (value == null) {
                        return "";
                }

                String id = value;

                int end = id.length();

                int question = id.indexOf("?");

                int ampersand = id.indexOf("&");

                int slash = id.indexOf("/");

                if (question >= 0) {

                        end = Math.min(
                                        end,
                                        question);
                }

                if (ampersand >= 0) {

                        end = Math.min(
                                        end,
                                        ampersand);
                }

                if (slash >= 0) {

                        end = Math.min(
                                        end,
                                        slash);
                }

                id = id.substring(
                                0,
                                end);

                return id.replaceAll(
                                "[^a-zA-Z0-9_-]",
                                "");
        }

        // =========================================================
        // STOP YOUTUBE SERVER
        // =========================================================

        private static synchronized void stopYouTubeServer() {

                youtubeServerStarted = false;

                if (youtubeServerSocket != null) {

                        try {

                                youtubeServerSocket.close();

                        } catch (IOException ignored) {
                        }
                }

                youtubeServerSocket = null;

                youtubePort = -1;
        }

        // =========================================================
        // ERROR HTML
        // =========================================================

        private static String createErrorHtml(
                        String message) {

                return "<!DOCTYPE html>"
                                + "<html>"
                                + "<body style=\""
                                + "margin:0;"
                                + "background:#0D1213;"
                                + "color:#AAAAAA;"
                                + "font-family:Arial;"
                                + "text-align:center;"
                                + "padding-top:150px;"
                                + "\">"
                                + message
                                + "</body>"
                                + "</html>";
        }

        // =========================================================
        // MESSAGE
        // =========================================================

        private VBox createMessage(
                        String message) {

                VBox box = new VBox();

                box.setAlignment(
                                Pos.CENTER);

                box.setPadding(
                                new Insets(40));

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color:#193522;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#31583A;" +
                                                "-fx-border-radius:15;");

                Label label = new Label(message);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                label.setTextFill(
                                Color.web("#AFC4B2"));

                box.getChildren()
                                .add(
                                                label);

                return box;
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
        // NUMBER CIRCLE
        // =========================================================

        private static class StackPaneCircle
                        extends javafx.scene.layout.StackPane {

                StackPaneCircle(
                                int number) {

                        Circle circle = new Circle(21);

                        circle.setFill(
                                        Color.web("#32683B"));

                        Label label = new Label(
                                        String.valueOf(
                                                        number));

                        label.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        label.setTextFill(
                                        Color.WHITE);

                        setMinSize(
                                        42,
                                        42);

                        setPrefSize(
                                        42,
                                        42);

                        setMaxSize(
                                        42,
                                        42);

                        getChildren()
                                        .addAll(
                                                        circle,
                                                        label);
                }
        }
}