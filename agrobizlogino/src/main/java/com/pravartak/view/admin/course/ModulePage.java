package com.pravartak.view.admin.course;

import com.pravartak.model.admin.Course;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ModulePage {

        private final Course course;

        public ModulePage(Course course) {
                this.course = course;
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public Scene getModuleScene() {

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
                                new Insets(5, 0, 20, 0));

                moduleList.setFillWidth(true);

                /*
                 * Currently showing sample modules.
                 *
                 * Later replace this with:
                 *
                 * ModuleController.getModulesByCourseId(
                 * course.getCourseId()
                 * );
                 */

                moduleList.getChildren().add(
                                createModuleCard(
                                                1,
                                                "Introduction to Hydroponics",
                                                "Understanding the basics of soil-less agriculture."));

                moduleList.getChildren().add(
                                createModuleCard(
                                                2,
                                                "Nutrient Solutions",
                                                "Chemistry and management of plant nutrients."));

                // =====================================================
                // ADD MODULE BUTTON
                // =====================================================

                Button addModule = new Button("+  ADD NEW MODULE");

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

                        System.out.println(
                                        "Add module to course: "
                                                        + course.getCourseId());

                        AddModuleAdmin addModulePage = new AddModuleAdmin(course);

                        LoginPage.mainStage.setScene(
                                        addModulePage.getAddModuleScene());
                });

                moduleList.getChildren().add(
                                addModule);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(moduleList);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(  ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

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

                Label category = new Label(course != null ? safe(course.getCategory()) : "Category");

                category.setStyle(
                                "-fx-background-color:#10302A;" +
                                                "-fx-text-fill:#68D3B0;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:5 8;" +
                                                "-fx-background-radius:3;");

                Label status = new Label(course != null &&course.getStatus()? "PUBLISHED"  : "DRAFT");

                status.setStyle(
                                "-fx-background-color:#0D3B25;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:5 8;" +
                                                "-fx-background-radius:3;");

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
                        String moduleTitle,
                        String description) {

                VBox module = new VBox(8);

                module.setPadding(
                                new Insets(15));

                module.setStyle(
                                "-fx-background-color:#101612;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                // =====================================================
                // MODULE HEADER
                // =====================================================

                HBox header = new HBox(10);

                header.setAlignment(Pos.CENTER_LEFT);

                VBox titleBox = new VBox(3);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                Label moduleLabel = new Label(
                                "MODULE "
                                                + moduleNumber);

                moduleLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-font-weight:bold;");

                Label title = new Label(
                                moduleTitle);

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                Label desc = new Label(
                                description);

                desc.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                titleBox.getChildren().addAll(
                                moduleLabel,
                                title,
                                desc);

                Button addLesson = new Button("+ Lesson");

                addLesson.setStyle(
                                "-fx-background-color:#101A14;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:9px;" +
                                                "-fx-cursor:hand;");

                addLesson.setOnAction(e -> {

                        System.out.println("Add lesson to module " + moduleNumber);
                });

                Button delete = new Button("🗑");

                delete.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-cursor:hand;");

                header.getChildren().addAll(
                                titleBox,
                                addLesson,
                                delete);

                // =====================================================
                // LESSONS
                // =====================================================

                VBox lessons = new VBox(6);

                lessons.getChildren().add(
                                createLesson(
                                                "1.1",
                                                "History and Evolution",
                                                "VIDEO • 12 MIN"));

                lessons.getChildren().add(
                                createLesson(
                                                "1.2",
                                                "Types of Systems Overview",
                                                "READING • 5 PAGES"));

                lessons.getChildren().add(
                                createLesson(
                                                "1.3",
                                                "Module 1 Knowledge Check",
                                                "QUIZ • 10 Q"));

                module.getChildren().addAll(
                                header,
                                lessons);

                return module;
        }

        // =========================================================
        // LESSON
        // =========================================================

        private HBox createLesson(
                        String number,
                        String title,
                        String type) {

                HBox lesson = new HBox(10);

                lesson.setAlignment(
                                Pos.CENTER_LEFT);

                lesson.setPadding(
                                new Insets(10));

                lesson.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-background-radius:5;");

                Label numberLabel = new Label(number);

                numberLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                HBox.setHgrow(
                                titleLabel,
                                Priority.ALWAYS);

                Label typeLabel = new Label(type);

                typeLabel.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:8px;");

                lesson.getChildren().addAll(
                                numberLabel,
                                titleLabel,
                                typeLabel);

                return lesson;
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
        // SAFE
        // =========================================================

        private String safe(String value) {

                return value == null
                                ? ""
                                : value;
        }
}