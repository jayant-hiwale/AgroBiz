package com.pravartak.view.farmer;

// import java.io.InputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
import java.util.List;

import com.pravartak.dao.admindao.FirebaseCourseDAO;
import com.pravartak.model.admin.Course;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LearningPage {

    private Scene learningpagScene;

    // =========================================================
    // FIREBASE DAO
    // =========================================================

    private final FirebaseCourseDAO courseDAO;

    // =========================================================
    // MAIN CONTAINER
    // =========================================================

    private FlowPane courseContainer;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LearningPage() {

        courseDAO = new FirebaseCourseDAO();
    }

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public Scene get_learning_pageScene() {

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #080c0d;");

        // =====================================================
        // NAVBAR
        // =====================================================

        borderPane.setTop(
                new NavBar().createNavbar("Learning"));

        // =====================================================
        // FOOTER
        // =====================================================

        borderPane.setBottom(
                new Footer().createFooter());

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent = new VBox(22);

        mainContent.setPadding(
                new Insets(28, 40, 35, 40));

        mainContent.setStyle(
                "-fx-background-color: #080c0d;");

        // =====================================================
        // PAGE HEADER
        // =====================================================

        VBox headingBox = createPageHeader();

        // =====================================================
        // SECTION HEADER
        // =====================================================

        HBox sectionHeader = createSectionHeader();

        // =====================================================
        // COURSE CONTAINER
        // =====================================================

        courseContainer = new FlowPane();

        courseContainer.setHgap(22);
        courseContainer.setVgap(22);

        courseContainer.setPrefWrapLength(1050);

        courseContainer.setPadding(
                new Insets(5, 0, 25, 0));

        // =====================================================
        // LOAD COURSES
        // =====================================================

        loadCourses();

        // =====================================================
        // ADD CONTENT
        // =====================================================

        mainContent.getChildren().addAll(
                headingBox,
                sectionHeader,
                courseContainer);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(
                mainContent);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle(
                "-fx-background: #080c0d;" +
                        "-fx-background-color: #080c0d;");

        // =====================================================
        // CENTER
        // =====================================================

        borderPane.setCenter(
                scrollPane);

        // =====================================================
        // SCENE
        // =====================================================

        learningpagScene = new Scene(
                borderPane,
                1200,
                750);

        return learningpagScene;
    }

    // =========================================================
    // PAGE HEADER
    // =========================================================

    private VBox createPageHeader() {

        VBox headingBox = new VBox(6);

        Label title = new Label(
                "Learning");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30));

        title.setTextFill(
                Color.WHITE);

        Label subtitle = new Label(
                "Explore farming courses and improve your knowledge.");

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14));

        subtitle.setTextFill(
                Color.web("#AFC4B2"));

        headingBox.getChildren().addAll(
                title,
                subtitle);

        return headingBox;
    }

    // =========================================================
    // SECTION HEADER
    // =========================================================

    private HBox createSectionHeader() {

        HBox sectionHeader = new HBox();

        sectionHeader.setAlignment(
                Pos.CENTER_LEFT);

        Label sectionTitle = new Label(
                "Available Courses");

        sectionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        sectionTitle.setTextFill(
                Color.WHITE);

        sectionHeader.getChildren().add(
                sectionTitle);

        return sectionHeader;
    }

    // =========================================================
    // LOAD COURSES FROM FIREBASE
    // =========================================================

    private void loadCourses() {

        courseContainer.getChildren().clear();

        // =====================================================
        // LOADING INDICATOR
        // =====================================================

        ProgressIndicator loading = new ProgressIndicator();

        loading.setPrefSize(
                45,
                45);

        courseContainer.getChildren().add(
                loading);

        try {

            // =================================================
            // GET ALL COURSES
            // =================================================

            List<Course> courses = courseDAO.getAllCourses();

            courseContainer.getChildren().clear();

            // =================================================
            // CHECK EMPTY
            // =================================================

            if (courses == null ||
                    courses.isEmpty()) {

                showEmptyMessage(
                        "No courses are available yet.");

                return;
            }

            // =================================================
            // ADD ACTIVE COURSES
            // =================================================

            int activeCourseCount = 0;

            for (Course course : courses) {

                if (course == null) {
                    continue;
                }

                // -------------------------------------------------
                // ONLY ACTIVE COURSES
                // -------------------------------------------------

                if (!course.getStatus()) {
                    continue;
                }

                VBox courseCard = createCourseCard(course);

                courseContainer.getChildren().add(
                        courseCard);

                activeCourseCount++;
            }

            // =================================================
            // NO ACTIVE COURSES
            // =================================================

            if (activeCourseCount == 0) {

                showEmptyMessage(
                        "No active courses are available.");
            }

        } catch (Exception e) {

            e.printStackTrace();

            courseContainer.getChildren().clear();

            showEmptyMessage(
                    "Unable to load courses.");
        }
    }

    // =========================================================
    // EMPTY MESSAGE
    // =========================================================

    private void showEmptyMessage(
            String message) {

        VBox emptyBox = new VBox(10);

        emptyBox.setAlignment(
                Pos.CENTER);

        emptyBox.setPrefWidth(
                1050);

        emptyBox.setPadding(
                new Insets(50));

        Label icon = new Label(
                "📚");

        icon.setFont(
                Font.font(
                        "Arial",
                        35));

        Label messageLabel = new Label(
                message);

        messageLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        messageLabel.setTextFill(
                Color.web("#AFC4B2"));

        emptyBox.getChildren().addAll(
                icon,
                messageLabel);

        courseContainer.getChildren().add(
                emptyBox);
    }

    // =========================================================
    // CREATE COURSE CARD
    // =========================================================

    private VBox createCourseCard(
            Course course) {

        VBox card = new VBox(11);

        card.setPrefWidth(285);
        card.setPrefHeight(365);

        card.setPadding(
                new Insets(12));

        card.setStyle(
                "-fx-background-color: #193522;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: #31583A;" +
                        "-fx-border-radius: 16;" +
                        "-fx-border-width: 1;");

        // =====================================================
        // IMAGE
        // =====================================================

        StackPane imageContainer = createCourseImage(
                course.getThumbnailUrl());

        // =====================================================
        // COURSE TITLE
        // =====================================================

        Label title = new Label(
                course.getTitle() == null
                        ? "Untitled Course"
                        : course.getTitle());

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17));

        title.setTextFill(
                Color.WHITE);

        title.setWrapText(true);

        title.setMaxWidth(
                255);

        // =====================================================
        // CATEGORY
        // =====================================================

        Label category = new Label(
                safeValue(
                        course.getCategory(),
                        "General"));

        category.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        category.setTextFill(
                Color.web("#7ED184"));

        // =====================================================
        // COURSE INFORMATION
        // =====================================================

        HBox information = createCourseInformation(
                course);

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS);

        // =====================================================
        // START BUTTON
        // =====================================================

        Button startButton = new Button(
                "Start Learning  →");

        startButton.setPrefWidth(
                261);

        startButton.setPrefHeight(
                40);

        startButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        startButton.setTextFill(
                Color.WHITE);

        startButton.setStyle(
                "-fx-background-color: #3F8F4A;" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;");

        // =====================================================
        // BUTTON HOVER
        // =====================================================

        startButton.setOnMouseEntered(e -> {

            startButton.setStyle(
                    "-fx-background-color: #55A85E;" +
                            "-fx-background-radius: 9;" +
                            "-fx-cursor: hand;");
        });

        startButton.setOnMouseExited(e -> {

            startButton.setStyle(
                    "-fx-background-color: #3F8F4A;" +
                            "-fx-background-radius: 9;" +
                            "-fx-cursor: hand;");
        });

        // =====================================================
        // OPEN COURSE
        // =====================================================

        startButton.setOnAction(e -> {

            openCourse(
                    course);
        });

        // =====================================================
        // CARD CLICK
        // =====================================================

        card.setOnMouseClicked(e -> {

            if (e.getTarget() != startButton) {

                openCourse(
                        course);
            }
        });

        // =====================================================
        // CARD HOVER
        // =====================================================

        card.setOnMouseEntered(e -> {

            card.setStyle(
                    "-fx-background-color: #21452B;" +
                            "-fx-background-radius: 16;" +
                            "-fx-border-color: #63A86B;" +
                            "-fx-border-radius: 16;" +
                            "-fx-border-width: 1;" +
                            "-fx-cursor: hand;");
        });

        card.setOnMouseExited(e -> {

            card.setStyle(
                    "-fx-background-color: #193522;" +
                            "-fx-background-radius: 16;" +
                            "-fx-border-color: #31583A;" +
                            "-fx-border-radius: 16;" +
                            "-fx-border-width: 1;");
        });

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        card.getChildren().addAll(
                imageContainer,
                title,
                category,
                information,
                spacer,
                startButton);

        return card;
    }

    // =========================================================
    // COURSE INFORMATION
    // =========================================================

    private HBox createCourseInformation(
            Course course) {

        HBox information = new HBox(8);

        information.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // DIFFICULTY
        // =====================================================

        Label difficulty = createInfoLabel(
                "● " +
                        safeValue(
                                course.getDifficulty(),
                                "Beginner"));

        // =====================================================
        // LANGUAGE
        // =====================================================

        Label language = createInfoLabel(
                "• " +
                        safeValue(
                                course.getLanguage(),
                                "English"));

        information.getChildren().addAll(
                difficulty,
                language);

        return information;
    }

    // =========================================================
    // INFO LABEL
    // =========================================================

    private Label createInfoLabel(
            String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        label.setTextFill(
                Color.web("#AFC4B2"));

        return label;
    }

    // =========================================================
    // COURSE IMAGE
    // =========================================================

    private StackPane createCourseImage(
            String imageUrl) {

        StackPane container = new StackPane();

        container.setPrefWidth(
                261);

        container.setPrefHeight(
                135);

        container.setMaxWidth(
                261);

        container.setMaxHeight(
                135);

        // =====================================================
        // CLIPPING
        // =====================================================

        Rectangle clip = new Rectangle(
                261,
                135);

        clip.setArcWidth(18);
        clip.setArcHeight(18);

        container.setClip(
                clip);

        // =====================================================
        // DEFAULT BACKGROUND
        // =====================================================

        container.setStyle(
                "-fx-background-color: #284B30;");

        // =====================================================
        // IMAGE VIEW
        // =====================================================

        ImageView imageView = new ImageView();

        imageView.setFitWidth(
                261);

        imageView.setFitHeight(
                135);

        imageView.setPreserveRatio(
                false);

        // =====================================================
        // LOAD IMAGE
        // =====================================================

        if (imageUrl != null &&
                !imageUrl.trim().isEmpty()) {

            try {

                Image image = new Image(
                        imageUrl,
                        261,
                        135,
                        false,
                        true,
                        true);

                imageView.setImage(
                        image);

                // -------------------------------------------------
                // FALLBACK IF IMAGE FAILED
                // -------------------------------------------------

                if (image.isError()) {

                    setImagePlaceholder(
                            container);
                }

            } catch (Exception e) {

                setImagePlaceholder(
                        container);
            }

        } else {

            setImagePlaceholder(
                    container);
        }

        // =====================================================
        // ADD IMAGE
        // =====================================================

        container.getChildren().add(
                imageView);

        return container;
    }

    // =========================================================
    // IMAGE PLACEHOLDER
    // =========================================================

    private void setImagePlaceholder(
            StackPane container) {

        Label placeholder = new Label(
                "🌱");

        placeholder.setFont(
                Font.font(
                        "Arial",
                        38));

        placeholder.setTextFill(
                Color.web("#AFC4B2"));

        container.getChildren().add(
                placeholder);
    }

    // =========================================================
    // OPEN COURSE
    // =========================================================

    private void openCourse(
            Course course) {

        if (course == null) {
            return;
        }

        System.out.println(
                "Opening course: "
                        + course.getTitle()
                        + " | Course ID: "
                        + course.getCourseId());

        FarmerModulesPage modulesPage = new FarmerModulesPage(
                course);


        LoginPage.mainStage.setScene(
                modulesPage.getModulesPageScene());
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safeValue(
            String value,
            String defaultValue) {

        if (value == null ||
                value.trim().isEmpty()) {

            return defaultValue;
        }

        return value.trim();
    }

}