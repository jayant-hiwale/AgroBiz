package com.pravartak.view.admin.course;

import com.pravartak.controller.admincontroller.LessonController;
import com.pravartak.controller.admincontroller.ModuleController;
import com.pravartak.model.admin.Course;
import com.pravartak.model.admin.Lesson;
import com.pravartak.model.admin.Module;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AdminModulePage {

        // =========================================================
        // VARIABLES
        // =========================================================

        private final Course course;

        private final ModuleController moduleController = new ModuleController();

        private final LessonController lessonController = new LessonController();

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public AdminModulePage(Course course) {

                this.course = course;
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public Scene getModuleScene() {

                VBox root = new VBox(15);

                root.setPadding(
                                new Insets(
                                                20,
                                                30,
                                                20,
                                                30));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = new HBox(15);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // BACK BUTTON
                // =====================================================

                Button backButton = new Button("← Back");

                backButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:6 14;" +
                                                "-fx-cursor:hand;");

                backButton.setOnAction(
                                e -> goBack());

                // =====================================================
                // TITLE
                // =====================================================

                VBox titleBox = new VBox(3);

                Label title = new Label(
                                course != null
                                                ? safe(course.getTitle())
                                                : "Course");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                Label subtitle = new Label(
                                "Manage course modules and lessons");

                subtitle.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                header.getChildren().addAll(
                                backButton,
                                titleBox);

                // =====================================================
                // COURSE INFORMATION
                // =====================================================

                HBox courseInfo = createCourseInfo();

                // =====================================================
                // MODULE LIST
                // =====================================================

                VBox moduleList = new VBox(12);

                moduleList.setPadding(
                                new Insets(
                                                5,
                                                0,
                                                20,
                                                0));

                moduleList.setFillWidth(true);

                // =====================================================
                // LOAD MODULES
                // =====================================================

                if (course == null) {

                        moduleList.getChildren().add(
                                        createEmptyModuleView());

                } else {

                        int courseId = course.getCourseId();

                        List<Module> modules = moduleController
                                        .getModulesByCourse(
                                                        courseId);

                        // =================================================
                        // DISPLAY MODULES
                        // =================================================

                        if (modules.isEmpty()) {

                                moduleList.getChildren().add(
                                                createEmptyModuleView());

                        } else {

                                for (int i = 0; i < modules.size(); i++) {

                                        Module module = modules.get(i);

                                        moduleList.getChildren().add(
                                                        createModuleCard(
                                                                        i + 1,
                                                                        module));
                                }
                        }
                }

                // =====================================================
                // ADD MODULE BUTTON
                // =====================================================

                Button addModule = new Button(
                                "+  ADD NEW MODULE");

                addModule.setMaxWidth(
                                Double.MAX_VALUE);

                addModule.setPrefHeight(45);

                addModule.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-style:dashed;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                addModule.setOnAction(e -> {

                        if (course == null) {

                                return;
                        }

                        System.out.println(
                                        "Add module to course: "
                                                        + course.getCourseId());

                        AddModuleAdmin addModulePage = new AddModuleAdmin(course);

                        LoginPage.mainStage.setScene(
                                        addModulePage
                                                        .getAddModuleScene());
                });

                moduleList.getChildren().add(
                                addModule);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(moduleList);

                scrollPane.setFitToWidth(true);

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
                                header,
                                courseInfo,
                                scrollPane);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // COURSE INFO
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
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                // =====================================================
                // CATEGORY
                // =====================================================

                Label category = new Label(
                                course != null
                                                ? safe(course.getCategory())
                                                : "Category");

                category.setStyle(
                                "-fx-background-color:#10302A;" +
                                                "-fx-text-fill:#68D3B0;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:5 8;" +
                                                "-fx-background-radius:3;");

                // =====================================================
                // STATUS
                // =====================================================

                Label status = new Label(
                                course != null
                                                && course.getStatus()
                                                                ? "PUBLISHED"
                                                                : "DRAFT");

                status.setStyle(
                                "-fx-background-color:#0D3B25;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:5 8;" +
                                                "-fx-background-radius:3;");

                // =====================================================
                // COURSE ID
                // =====================================================

                Label id = new Label(
                                course != null
                                                ? "Course ID: "
                                                                + course.getCourseId()
                                                : "");

                id.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                box.getChildren().addAll(
                                category,
                                status,
                                id);

                return box;
        }

        // =========================================================
        // MODULE CARD
        // =========================================================

        private VBox createModuleCard(
                        int moduleNumber,
                        Module module) {

                VBox moduleBox = new VBox(8);

                moduleBox.setFillWidth(true);

                moduleBox.setPadding(
                                new Insets(12));

                moduleBox.setStyle(
                                "-fx-background-color:#0D1511;" +
                                                "-fx-border-color:#202A25;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // MODULE HEADER
                // =====================================================

                HBox header = new HBox(12);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setFillHeight(true);

                // =====================================================
                // MODULE IMAGE
                // =====================================================

                ImageView moduleImage = createModuleImage(
                                module.getImageUrl());

                // =====================================================
                // MODULE INFORMATION
                // =====================================================

                VBox moduleInfo = new VBox(4);

                moduleInfo.setMinWidth(100);

                // =====================================================
                // MODULE NUMBER
                // =====================================================

                Label moduleNumberLabel = new Label(
                                "MODULE "
                                                + moduleNumber);

                moduleNumberLabel.setStyle(
                                "-fx-text-fill:#39FF72;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // MODULE TITLE
                // =====================================================

                Label moduleTitle = new Label(
                                safe(
                                                module.getTitle()));

                moduleTitle.setStyle(
                                "-fx-text-fill:#FFFFFF;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                moduleTitle.setWrapText(true);

                // =====================================================
                // MODULE DESCRIPTION
                // =====================================================

                Label moduleDescription = new Label(
                                safe(
                                                module.getDescription()));

                moduleDescription.setStyle(
                                "-fx-text-fill:#88958E;" +
                                                "-fx-font-size:11px;");

                moduleDescription.setWrapText(true);

                moduleDescription.setMaxWidth(
                                Double.MAX_VALUE);

                moduleInfo.getChildren().addAll(
                                moduleNumberLabel,
                                moduleTitle,
                                moduleDescription);

                // =====================================================
                // MODULE INFO GETS REMAINING SPACE
                // =====================================================

                HBox.setHgrow(
                                moduleInfo,
                                Priority.ALWAYS);

                // =====================================================
                // ACTION BUTTONS
                // =====================================================

                VBox actionButtons = new VBox(6);

                actionButtons.setAlignment(
                                Pos.CENTER);

                /*
                 * Fixed width for the complete
                 * action-button area.
                 */
                actionButtons.setMinWidth(90);
                actionButtons.setPrefWidth(90);
                actionButtons.setMaxWidth(90);

                // =====================================================
                // ADD LESSON BUTTON
                // =====================================================

                Button addLesson = new Button("+ Lesson");

                addLesson.setMinWidth(78);
                addLesson.setPrefWidth(78);
                addLesson.setMaxWidth(78);

                addLesson.setMinHeight(30);
                addLesson.setPrefHeight(30);
                addLesson.setMaxHeight(30);

                addLesson.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-border-color:#1F8F46;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-text-fill:#39FF72;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;" +
                                                "-fx-padding:5 8;");

                /*
                 * Prevent this click from
                 * reaching the module card.
                 */
                addLesson.addEventFilter(
                                MouseEvent.MOUSE_CLICKED,
                                MouseEvent::consume);

                addLesson.setOnAction(e -> {

                        System.out.println(
                                        "Add lesson to module: "
                                                        + module.getModuleId());

                        AddLessonAdmin addLessonPage = new AddLessonAdmin(
                                        course,
                                        module);

                        LoginPage.mainStage.setScene(
                                        addLessonPage
                                                        .getAddLessonScene());
                });

                // =====================================================
                // DELETE MODULE BUTTON
                // =====================================================

                Button deleteButton = new Button("Delete");

                deleteButton.setMinWidth(78);
                deleteButton.setPrefWidth(78);
                deleteButton.setMaxWidth(78);

                deleteButton.setMinHeight(28);
                deleteButton.setPrefHeight(28);
                deleteButton.setMaxHeight(28);

                deleteButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-border-color:#5A2525;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-text-fill:#D66A6A;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;" +
                                                "-fx-padding:5 8;");

                /*
                 * Prevent this click from
                 * reaching the module card.
                 */
                deleteButton.addEventFilter(
                                MouseEvent.MOUSE_CLICKED,
                                MouseEvent::consume);

                deleteButton.setOnAction(e -> {

                        e.consume();

                        Alert alert = new Alert(
                                        Alert.AlertType.CONFIRMATION);

                        alert.setTitle(
                                        "Delete Module");

                        alert.setHeaderText(
                                        "Delete this module?");

                        alert.setContentText(
                                        "This will delete the module and its lessons.");

                        ButtonType yesButton = new ButtonType(
                                        "Delete");

                        ButtonType cancelButton = new ButtonType(
                                        "Cancel",
                                        ButtonBar.ButtonData.CANCEL_CLOSE);

                        alert.getButtonTypes().setAll(
                                        yesButton,
                                        cancelButton);

                        alert.showAndWait().ifPresent(
                                        response -> {

                                                if (response == yesButton) {

                                                        System.out.println(
                                                                        "Delete module: "
                                                                                        + module.getModuleId());

                                                        boolean deleted = moduleController
                                                                        .deleteModule(
                                                                                        course.getCourseId(),
                                                                                        module.getModuleId());

                                                        if (deleted) {

                                                                refreshPage();

                                                        } else {

                                                                Alert error = new Alert(
                                                                                Alert.AlertType.ERROR);

                                                                error.setTitle(
                                                                                "Delete Failed");

                                                                error.setHeaderText(
                                                                                "Unable to delete module");

                                                                error.setContentText(
                                                                                "The module could not be deleted.");

                                                                error.showAndWait();
                                                        }
                                                }
                                        });
                });

                // =====================================================
                // ADD BUTTONS TO VERTICAL BOX
                // =====================================================

                actionButtons.getChildren().addAll(
                                addLesson,
                                deleteButton);

                // =====================================================
                // EXPAND / COLLAPSE ARROW
                // =====================================================

                Label arrow = new Label("›");

                arrow.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:20px;" +
                                                "-fx-font-weight:bold;");

                arrow.setMinWidth(20);
                arrow.setPrefWidth(20);
                arrow.setMaxWidth(20);

                arrow.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // HEADER CHILDREN
                // =====================================================

                header.getChildren().addAll(
                                moduleImage,
                                moduleInfo,
                                actionButtons,
                                arrow);

                // =====================================================
                // LESSON LIST
                // =====================================================

                VBox lessonList = createLessons(
                                module,
                                moduleNumber);

                lessonList.setPadding(
                                new Insets(
                                                10,
                                                0,
                                                0,
                                                20));

                // =====================================================
                // INITIALLY COLLAPSED
                // =====================================================

                lessonList.setVisible(false);

                lessonList.setManaged(false);

                // =====================================================
                // MODULE CLICK
                // =====================================================

                moduleBox.setOnMouseClicked(e -> {

                        boolean expanded = lessonList.isVisible();

                        lessonList.setVisible(
                                        !expanded);

                        lessonList.setManaged(
                                        !expanded);

                        if (expanded) {

                                arrow.setText("›");

                        } else {

                                arrow.setText("⌄");
                        }
                });

                // =====================================================
                // HOVER
                // =====================================================

                moduleBox.setOnMouseEntered(e -> {

                        moduleBox.setStyle(
                                        "-fx-background-color:#111A15;" +
                                                        "-fx-border-color:#2B4735;" +
                                                        "-fx-border-radius:8;" +
                                                        "-fx-background-radius:8;" +
                                                        "-fx-cursor:hand;");
                });

                moduleBox.setOnMouseExited(e -> {

                        moduleBox.setStyle(
                                        "-fx-background-color:#0D1511;" +
                                                        "-fx-border-color:#202A25;" +
                                                        "-fx-border-radius:8;" +
                                                        "-fx-background-radius:8;" +
                                                        "-fx-cursor:hand;");
                });

                // =====================================================
                // ADD COMPONENTS
                // =====================================================

                moduleBox.getChildren().addAll(
                                header,
                                lessonList);

                return moduleBox;
        }

        // =========================================================
        // CREATE MODULE IMAGE
        // =========================================================

        private ImageView createModuleImage(
                        String imageUrl) {

                ImageView imageView = new ImageView();

                // =====================================================
                // IMAGE SIZE
                // =====================================================

                imageView.setFitWidth(120);

                imageView.setFitHeight(75);

                imageView.setPreserveRatio(false);

                imageView.setSmooth(true);

                // =====================================================
                // DEFAULT IMAGE
                // =====================================================

                if (imageUrl == null ||
                                imageUrl.trim().isEmpty()) {

                        imageView.setImage(
                                        createDefaultModuleImage());

                        return imageView;
                }

                // =====================================================
                // LOAD CLOUDINARY IMAGE
                // =====================================================

                try {

                        Image image = new Image(
                                        imageUrl,
                                        120,
                                        75,
                                        false,
                                        true,
                                        true);

                        imageView.setImage(
                                        image);

                        // =================================================
                        // IMAGE ERROR
                        // =================================================

                        image.errorProperty().addListener(
                                        (obs,
                                                        oldValue,
                                                        newValue) -> {

                                                if (newValue) {

                                                        imageView.setImage(
                                                                        createDefaultModuleImage());
                                                }
                                        });

                } catch (Exception e) {

                        System.out.println(
                                        "Unable to load module image: "
                                                        + e.getMessage());

                        imageView.setImage(
                                        createDefaultModuleImage());
                }

                // =====================================================
                // STYLE
                // =====================================================

                imageView.setStyle(
                                "-fx-background-color:#14251A;" +
                                                "-fx-background-radius:6;");

                return imageView;
        }

        // =========================================================
        // DEFAULT MODULE IMAGE
        // =========================================================

        private Image createDefaultModuleImage() {

                /*
                 * No image URL was saved.
                 *
                 * Returning null keeps the module card
                 * working without crashing.
                 */

                return null;
        }

        // =========================================================
        // CREATE LESSONS
        // =========================================================

        private VBox createLessons(
                        Module module,
                        int moduleNumber) {

                VBox lessons = new VBox(6);

                List<Lesson> lessonList = lessonController
                                .getLessonsByModule(
                                                course.getCourseId(),
                                                module.getModuleId());

                // =====================================================
                // NO LESSONS
                // =====================================================

                if (lessonList.isEmpty()) {

                        Label empty = new Label(
                                        "No lessons added yet.");

                        empty.setStyle(
                                        "-fx-text-fill:#555555;" +
                                                        "-fx-font-size:10px;" +
                                                        "-fx-padding:10;");

                        lessons.getChildren().add(
                                        empty);

                } else {

                        // =================================================
                        // LESSON ROWS
                        // =================================================

                        for (Lesson lesson : lessonList) {

                                lessons.getChildren().add(
                                                createLesson(
                                                                lesson,
                                                                module,
                                                                moduleNumber));
                        }
                }

                return lessons;
        }

        // =========================================================
        // CREATE SINGLE LESSON
        // =========================================================

        private HBox createLesson(
                        Lesson lesson,
                        Module module,
                        int moduleNumber) {

                HBox box = new HBox(10);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                box.setPadding(
                                new Insets(10));

                box.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // LESSON NUMBER
                // =====================================================

                Label number = new Label(
                                moduleNumber
                                                + "."
                                                + lesson.getLessonOrder());

                number.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                number.setMinWidth(30);

                number.setPrefWidth(30);

                // =====================================================
                // LESSON TITLE
                // =====================================================

                Label title = new Label(
                                safe(
                                                lesson.getTitle()));

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;");

                title.setWrapText(true);

                HBox.setHgrow(
                                title,
                                Priority.ALWAYS);

                // =====================================================
                // DELETE LESSON BUTTON
                // =====================================================

                Button delete = new Button("Remove");

                delete.setMinWidth(65);
                delete.setPrefWidth(65);
                delete.setMaxWidth(65);

                delete.setMinHeight(28);
                delete.setPrefHeight(28);
                delete.setMaxHeight(28);

                delete.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-border-color:#4A2525;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-text-fill:#D66A6A;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // PREVENT LESSON CLICK
                // =====================================================

                delete.addEventFilter(
                                MouseEvent.MOUSE_CLICKED,
                                MouseEvent::consume);

                // =====================================================
                // DELETE LESSON ACTION
                // =====================================================

                delete.setOnAction(e -> {

                        e.consume();

                        boolean deleted = lessonController.deleteLesson(
                                        course.getCourseId(),
                                        module.getModuleId(),
                                        lesson.getLessonId());

                        if (deleted) {

                                refreshPage();

                        } else {

                                Alert error = new Alert(
                                                Alert.AlertType.ERROR);

                                error.setTitle(
                                                "Delete Failed");

                                error.setHeaderText(
                                                "Unable to delete lesson");

                                error.setContentText(
                                                "The lesson could not be deleted.");

                                error.showAndWait();
                        }
                });

                // =====================================================
                // ADD LESSON COMPONENTS
                // =====================================================

                box.getChildren().addAll(
                                number,
                                title,
                                delete);

                // =====================================================
                // LESSON CLICK → EDIT LESSON
                // =====================================================

                box.addEventFilter(
                                MouseEvent.MOUSE_CLICKED,
                                e -> {

                                        /*
                                         * Stop this click from
                                         * reaching the module card.
                                         */

                                        e.consume();

                                        System.out.println(
                                                        "Opening lesson editor: "
                                                                        + lesson.getLessonId());

                                        System.out.println(
                                                        "Module ID: "
                                                                        + module.getModuleId());

                                        // =========================================
                                        // OPEN EDIT LESSON PAGE
                                        // =========================================

                                        EditLessonAdmin editLessonPage = new EditLessonAdmin(
                                                        course,
                                                        module,
                                                        lesson);

                                        LoginPage.mainStage.setScene(
                                                        editLessonPage
                                                                        .getEditLessonScene());
                                });

                // =====================================================
                // HOVER
                // =====================================================

                box.setOnMouseEntered(e -> {

                        box.setStyle(
                                        "-fx-background-color:#141B16;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-cursor:hand;");
                });

                box.setOnMouseExited(e -> {

                        box.setStyle(
                                        "-fx-background-color:#0D1213;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-cursor:hand;");
                });

                return box;
        }

        // =========================================================
        // EMPTY MODULE VIEW
        // =========================================================

        private VBox createEmptyModuleView() {

                VBox box = new VBox(8);

                box.setAlignment(
                                Pos.CENTER);

                box.setPadding(
                                new Insets(40));

                // =====================================================
                // ICON
                // =====================================================

                Label icon = new Label("＋");

                icon.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:28px;");

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label( "No modules yet");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // MESSAGE
                // =====================================================

                Label message = new Label( "Create your first module for this course.");

                message.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                box.getChildren().addAll(icon,title, message);

                return box;
        }

        // =========================================================
        // REFRESH PAGE
        // =========================================================

        private void refreshPage() {

                AdminModulePage page = new AdminModulePage( course);

                LoginPage.mainStage.setScene(page.getModuleScene());
        }

        // =========================================================
        // BACK
        // =========================================================

        private void goBack() {

                AdminPage adminPage = new AdminPage();

                LoginPage.mainStage.setScene(adminPage.getAdminPage("Manage Course"));
        }

        private String safe( String value) {

                return value == null? ""  : value;
        }
}