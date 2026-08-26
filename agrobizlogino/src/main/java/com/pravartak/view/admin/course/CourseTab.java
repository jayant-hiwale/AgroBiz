package com.pravartak.view.admin.course;

import java.io.File;
import java.util.List;

import com.pravartak.controller.admincontroller.CourseController;
import com.pravartak.model.admin.Course;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CourseTab {

        // =========================================================
        // COURSE CONTROLLER
        // =========================================================

        private static final CourseController courseController = new CourseController();

        // =========================================================
        // COURSES PAGE
        // =========================================================

        public static VBox getCoursesPage() {

                VBox root = new VBox(15);

                root.setPadding(
                                new Insets(15, 0, 20, 0));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label("Courses");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:22px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // SUBTITLE
                // =====================================================

                Label subtitle = new Label(
                                "Manage and monitor all agricultural courses.");

                subtitle.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:10px;");

                // =====================================================
                // COURSE LIST
                // =====================================================

                VBox courseList = new VBox(12);

                courseList.setPadding(
                                new Insets(10, 5, 20, 5));

                courseList.setFillWidth(true);

                /*
                 * IMPORTANT
                 *
                 * Do NOT put VBox.setVgrow(courseList, ALWAYS)
                 * here.
                 *
                 * The ScrollPane should grow instead.
                 */

                // =====================================================
                // LOAD COURSES
                // =====================================================

                List<Course> courses;

                try {

                        courses = courseController.getAllCourses();

                } catch (Exception e) {

                        e.printStackTrace();

                        courses = null;
                }

                // =====================================================
                // EMPTY / NULL
                // =====================================================

                if (courses == null || courses.isEmpty()) {

                        courseList.getChildren().add(
                                        createEmptyCourseView());

                } else {

                        System.out.println(
                                        "Courses loaded: " + courses.size());

                        // =================================================
                        // CREATE COURSE CARDS
                        // =================================================

                        for (Course course : courses) {

                                if (course == null) {
                                        continue;
                                }

                                HBox card = createCourseCard(course);

                                if (card != null) {

                                        courseList.getChildren().add(card);
                                }
                        }

                        // =================================================
                        // SAFETY CHECK
                        // =================================================

                        if (courseList.getChildren().isEmpty()) {

                                courseList.getChildren().add(
                                                createEmptyCourseView());
                        }
                }

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(courseList);

                /*
                 * Allow cards to use complete width.
                 */
                scrollPane.setFitToWidth(true);

                /*
                 * Vertical scrolling enabled.
                 */
                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                /*
                 * No horizontal scrolling.
                 */
                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                /*
                 * Allow mouse dragging.
                 */
                scrollPane.setPannable(true);

                /*
                 * ScrollPane fills remaining height.
                 */
                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                /*
                 * Remove default ugly background.
                 */
                scrollPane.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-background:#080C0D;" +
                                                "-fx-border-color:transparent;");

                /*
                 * Make viewport background dark.
                 */
                scrollPane.setFitToHeight(false);

                // =====================================================
                // ADD TO ROOT
                // =====================================================

                root.getChildren().addAll(
                                title,
                                subtitle,
                                scrollPane);

                return root;
        }

        // =========================================================
        // EMPTY COURSE VIEW
        // =========================================================

        private static VBox createEmptyCourseView() {

                VBox box = new VBox(8);

                box.setAlignment(
                                Pos.CENTER);

                box.setPadding(
                                new Insets(60));

                Label icon = new Label("🌱");

                icon.setStyle(
                                "-fx-font-size:32px;");

                Label title = new Label(
                                "No courses available");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:18px;" +
                                                "-fx-font-weight:bold;");

                Label message = new Label(
                                "Create your first agricultural course.");

                message.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:12px;");

                box.getChildren().addAll(
                                icon,
                                title,
                                message);

                return box;
        }

        // =========================================================
        // COURSE CARD
        // =========================================================

        private static HBox createCourseCard(
                        Course course) {

                if (course == null) {
                        return null;
                }

                HBox card = new HBox(18);

                card.setAlignment(
                                Pos.CENTER_LEFT);

                /*
                 * Fixed card height.
                 *
                 * This prevents VBox cards from becoming compressed.
                 */
                card.setPrefHeight(105);
                card.setMinHeight(105);
                card.setMaxHeight(105);

                /*
                 * Card uses full available width.
                 */
                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setPadding(
                                new Insets(14));

                card.setStyle(
                                "-fx-background-color:#101612;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                // =====================================================
                // THUMBNAIL
                // =====================================================

                VBox thumbnailBox = createThumbnail(course);

                // =====================================================
                // INFORMATION
                // =====================================================

                VBox information = new VBox(6);

                HBox.setHgrow(
                                information,
                                Priority.ALWAYS);

                // =====================================================
                // TAGS
                // =====================================================

                HBox tags = new HBox(7);

                tags.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // STATUS
                // =====================================================

                boolean published = course.getStatus();

                Label status = new Label(
                                published
                                                ? "PUBLISHED"
                                                : "DRAFT");

                if (published) {

                        status.setStyle(
                                        "-fx-background-color:#0D3B25;" +
                                                        "-fx-text-fill:#68D34A;" +
                                                        "-fx-font-size:8px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-padding:4 7;" +
                                                        "-fx-background-radius:3;");

                } else {

                        status.setStyle(
                                        "-fx-background-color:#332B1C;" +
                                                        "-fx-text-fill:#E0A458;" +
                                                        "-fx-font-size:8px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-padding:4 7;" +
                                                        "-fx-background-radius:3;");
                }

                // =====================================================
                // CATEGORY
                // =====================================================

                String categoryText = safeText(
                                course.getCategory(),
                                "Uncategorized");

                Label category = new Label(categoryText);

                category.setStyle(
                                "-fx-background-color:#10302A;" +
                                                "-fx-text-fill:#68D3B0;" +
                                                "-fx-font-size:8px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:4 7;" +
                                                "-fx-background-radius:3;");

                tags.getChildren().addAll(
                                status,
                                category);

                // =====================================================
                // COURSE TITLE
                // =====================================================

                String titleText = safeText(
                                course.getTitle(),
                                "Untitled Course");

                Label courseTitle = new Label(titleText);

                courseTitle.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                courseTitle.setMaxWidth(500);

               

                // =====================================================
                // ADD INFORMATION
                // =====================================================

                information.getChildren().addAll(
                                tags,
                                courseTitle
                                );

                // =====================================================
                // ACTIONS
                // =====================================================

                VBox actions = createActions(course);

                // =====================================================
                // ADD COMPONENTS
                // =====================================================

                card.getChildren().add(
                                thumbnailBox);

                card.getChildren().add(
                                information);

                card.getChildren().add(
                                actions);

                // =====================================================
                // HOVER EFFECT
                // =====================================================

                card.setOnMouseEntered(e -> {

                        card.setStyle(
                                        "-fx-background-color:#101612;" +
                                                        "-fx-border-color:#242B2C;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:8;" +
                                                        "-fx-background-radius:8;" +
                                                        "-fx-cursor:hand;");
                });

                card.setOnMouseExited(e -> {

                        card.setStyle(
                                        "-fx-background-color:#101612;" +
                                                        "-fx-border-color:#242B2C;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:8;" +
                                                        "-fx-background-radius:8;");
                });

                card.setOnMouseClicked(e -> {

                        // Ignore clicks coming from buttons
                        if (e.getTarget() instanceof Button) {
                                return;
                        }

                        ModulePage modulePage = new ModulePage(course);

                        Scene moduleScene = modulePage.getModuleScene();

                        LoginPage.mainStage.setScene(moduleScene);
                });

                return card;
        }

        // =========================================================
        // THUMBNAIL
        // =========================================================

        private static VBox createThumbnail(Course course) {

                VBox box = new VBox();

                box.setPrefWidth(100);
                box.setMinWidth(100);
                box.setMaxWidth(100);

                box.setPrefHeight(75);
                box.setMinHeight(75);
                box.setMaxHeight(75);

                box.setAlignment(Pos.CENTER);

                box.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-background-radius:6;");

                String thumbnail = course.getThumbnailUrl();

                // =====================================================
                // LOAD IMAGE
                // =====================================================

                if (thumbnail != null &&
                                !thumbnail.trim().isEmpty()) {

                        try {

                                Image image = null;

                                // =================================================
                                // CLOUDINARY / INTERNET URL
                                // =================================================

                                if (thumbnail.startsWith("http://") ||
                                                thumbnail.startsWith("https://")) {

                                        image = new Image(
                                                        thumbnail,
                                                        100,
                                                        75,
                                                        false,
                                                        true,
                                                        true);
                                }

                                // =================================================
                                // LOCAL IMAGE
                                // =================================================

                                else {

                                        File file = new File(thumbnail);

                                        if (file.exists()) {

                                                image = new Image(
                                                                file.toURI().toString(),
                                                                100,
                                                                75,
                                                                false,
                                                                true,
                                                                true);
                                        }
                                }

                                // =================================================
                                // ADD IMAGE
                                // =================================================

                                if (image != null && !image.isError()) {

                                        ImageView imageView = new ImageView(image);

                                        imageView.setFitWidth(100);
                                        imageView.setFitHeight(75);

                                        imageView.setPreserveRatio(false);

                                        imageView.setSmooth(true);

                                        box.getChildren().add(imageView);
                                }

                        } catch (Exception e) {

                                System.out.println("Thumbnail loading failed: " + e.getMessage());
                        }
                }

                // =====================================================
                // PLACEHOLDER
                // =====================================================

                if (box.getChildren().isEmpty()) {

                        Label placeholder = new Label("🌱");

                        placeholder.setStyle(
                                        "-fx-text-fill:#68D34A;" +
                                                        "-fx-font-size:28px;");

                        box.getChildren().add(placeholder);
                }

                return box;
        }

        // =========================================================
        // ACTIONS
        // =========================================================

        private static VBox createActions(Course course) {

                VBox actions = new VBox(8);

                actions.setAlignment(Pos.CENTER_RIGHT);

                actions.setPrefWidth(190);

                actions.setMinWidth(190);

                // =====================================================
                // EDIT BUTTON
                // =====================================================

                Button editButton = new Button("Edit Details");

                editButton.setPrefWidth(110);
                editButton.setPrefHeight(30);

                editButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // EDIT ACTION
                // =====================================================

                editButton.setOnAction(e -> {

                        if (course == null) {
                                return;
                        }

                        System.out.println(
                                        "Edit course: "
                                                        + course.getCourseId());

                        /*
                         * Open Edit Course page
                         */
                        EditCourseAdmin editPage = new EditCourseAdmin(course);

                        LoginPage.mainStage.setScene(
                                        editPage.getEditCourseScene());
                });

                // =====================================================
                // ADD MODULE BUTTON
                // =====================================================

                Button moduleButton = new Button("⊕  Add Module");

                moduleButton.setPrefWidth(110);
                moduleButton.setPrefHeight(30);

                moduleButton.setStyle(
                                "-fx-background-color:#087A2D;" +
                                                "-fx-text-fill:#FFFFFF;" +
                                                "-fx-border-color:#087A2D;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // MODULE ACTION
                // =====================================================

                moduleButton.setOnAction(e -> {

                        AddModuleAdmin addModulePage = new AddModuleAdmin(course);

                        LoginPage.mainStage.setScene(addModulePage.getAddModuleScene());
                });

                // =====================================================
                // ADD BUTTONS
                // =====================================================

                actions.getChildren().addAll(
                                editButton,
                                moduleButton);

                return actions;
        }

        // =========================================================
        // NULL SAFE TEXT
        // =========================================================

        private static String safeText(
                        String value,
                        String defaultValue) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value;
        }
}