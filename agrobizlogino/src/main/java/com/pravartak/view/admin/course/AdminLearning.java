package com.pravartak.view.admin.course;

import java.util.List;

import com.pravartak.controller.admincontroller.CourseController;
import com.pravartak.model.admin.Course;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AdminLearning {

        // =========================================================
        // CONTROLLER
        // =========================================================

        private final CourseController courseController;

        public AdminLearning() {
                courseController = new CourseController();
        }

        // =========================================================
        // MAIN PAGE
        // =========================================================

        public static VBox getLearningPage() {

                VBox root = new VBox(18);

                root.setPadding(new Insets(25, 35, 25, 35));

                root.setStyle("-fx-background-color:#080C0D;");

                // =====================================================
                // HEADER
                // =====================================================

                HBox header = createHeader();

                // =====================================================
                // CONTENT AREA
                // =====================================================

                VBox contentArea = new VBox();

                contentArea.setMaxWidth(Double.MAX_VALUE);

                VBox.setVgrow(contentArea, Priority.ALWAYS);

                // =====================================================
                // TABS
                // =====================================================

                HBox tabs = createTabs(contentArea);

                // =====================================================
                // DEFAULT COURSES PAGE
                // =====================================================

                VBox coursesPage = CourseTab.getCoursesPage();

                if (coursesPage != null) {

                        contentArea.getChildren().add(coursesPage);

                } else {

                        contentArea.getChildren().add(createComingSoonPage("Courses"));
                }

                // =====================================================
                // ADD TO ROOT
                // =====================================================

                addIfNotNull(
                                root,
                                header);

                addIfNotNull(
                                root,
                                tabs);

                addIfNotNull(
                                root,
                                contentArea);

                return root;
        }

        // =========================================================
        // SAFE ADD
        // =========================================================

        private static void addIfNotNull(VBox parent, Node child) {

                if (child != null) {

                        parent.getChildren().add(child);

                } else {

                        System.out.println(
                                        "WARNING: Tried to add a NULL node.");
                }
        }

        // =========================================================
        // HEADER
        // =========================================================

        private static HBox createHeader() {

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setPadding(
                                new Insets(10, 2, 15, 2));

                header.setStyle(
                                "-fx-border-color:#1B2021;" +
                                                "-fx-border-width:1 0 1 0;");

                // =====================================================
                // TITLE
                // =====================================================

                VBox titleBox = new VBox(3);

                Label title = new Label("Course Management");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:30px;" +
                                                "-fx-font-weight:bold;");

                Label subtitle = new Label(
                                "Oversee agricultural training programs and curriculum.");

                subtitle.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:12px;");

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                // =====================================================
                // SPACE
                // =====================================================

                Region space = new Region();

                HBox.setHgrow(
                                space,
                                Priority.ALWAYS);

                // =====================================================
                // CREATE COURSE BUTTON
                // =====================================================

                Button createCourseBtn = new Button("+ Create Course");

                createCourseBtn.setPrefHeight(34);

                createCourseBtn.setPrefWidth(120);

                setCreateButtonStyle(
                                createCourseBtn,
                                false);

                // =====================================================
                // HOVER
                // =====================================================

                createCourseBtn.setOnMouseEntered(
                                e -> setCreateButtonStyle(createCourseBtn, true));

                createCourseBtn.setOnMouseExited(
                                e -> setCreateButtonStyle(createCourseBtn, false));

                // =====================================================
                // CREATE COURSE
                // =====================================================

                createCourseBtn.setOnAction(e -> {

                        System.out.println("Opening Create Course page...");

                        CreateCourseAdmin createCourseAdmin = new CreateCourseAdmin();

                        LoginPage.mainStage.setScene(createCourseAdmin.getCreateCourseScene());

                        LoginPage.mainStage.show();
                });

                // =====================================================
                // HEADER CHILDREN
                // =====================================================

                header.getChildren().addAll(
                                titleBox,
                                space,
                                createCourseBtn);

                return header;
        }

        // =========================================================
        // CREATE BUTTON STYLE
        // =========================================================

        private static void setCreateButtonStyle(
                        Button button,
                        boolean hover) {

                if (hover) {

                        button.setStyle(
                                        "-fx-background-color:#245D35;" +
                                                        "-fx-text-fill:#68D34A;" +
                                                        "-fx-font-size:10px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-border-color:#68D34A;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:7 12;" +
                                                        "-fx-cursor:hand;");

                } else {

                        button.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-text-fill:#68D34A;" +
                                                        "-fx-font-size:10px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-border-color:#68D34A;" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-border-radius:5;" +
                                                        "-fx-background-radius:5;" +
                                                        "-fx-padding:7 12;" +
                                                        "-fx-cursor:hand;");
                }
        }

        // =========================================================
        // TABS
        // =========================================================

        private static HBox createTabs(VBox contentArea) {

                HBox tabs = new HBox(25);

                tabs.setAlignment(Pos.CENTER_LEFT);

                tabs.setPadding(new Insets(10, 0, 10, 0));

                String[] tabNames = {
                                "Courses",
                                "Categories",
                                "Lessons",
                };

                for (int i = 0; i < tabNames.length; i++) {

                        Button tab = new Button(tabNames[i]);

                        setNormalTabStyle(tab);

                        if (i == 0) {
                                setActiveTabStyle(tab);
                        }

                        final int index = i;

                        tab.setOnAction(e -> {

                                // =================================================
                                // ACTIVE TAB
                                // =================================================

                                for (int j = 0; j < tabs.getChildren().size(); j++) {

                                        Button button = (Button) tabs.getChildren().get(j);

                                        if (j == index) {

                                                setActiveTabStyle(button);

                                        } else {

                                                setNormalTabStyle(button);
                                        }
                                }

                                // =================================================
                                // CLEAR CONTENT
                                // =================================================

                                contentArea.getChildren().clear();

                                // =================================================
                                // GET PAGE
                                // =================================================

                                VBox page = null;

                                switch (tab.getText()) {

                                        case "Courses":
                                                page = CourseTab.getCoursesPage();
                                                break;

                                        case "Categories":
                                                page = CategoriesTab.getCategoriesPage();
                                                break;

                                        case "Lessons":
                                                page = createComingSoonPage("Lessons");
                                                break;

                                }

                                // =================================================
                                // IMPORTANT
                                // =================================================

                                if (page != null) {

                                        contentArea.getChildren().add(page);

                                } else {

                                        System.out.println("ERROR: " + tab.getText() + " returned NULL.");

                                        contentArea.getChildren().add(createComingSoonPage(tab.getText()));
                                }
                        });

                        tabs.getChildren().add(tab);
                }

                return tabs;
        }

        // =========================================================
        // ACTIVE TAB
        // =========================================================

        private static void setActiveTabStyle(
                        Button tab) {

                tab.setStyle(
                                "-fx-background-color: #245D35;" +
                                                "-fx-text-fill: #68D34A;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:12;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-padding:5 12;" +
                                                "-fx-cursor:hand;");
        }

        // =========================================================
        // NORMAL TAB
        // =========================================================

        private static void setNormalTabStyle(
                        Button tab) {

                tab.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill: #AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:5 0;" +
                                                "-fx-cursor:hand;");
        }

        // =========================================================
        // COMING SOON
        // =========================================================

        private static VBox createComingSoonPage(String name) {

                VBox root = new VBox(10);

                root.setPadding(new Insets(20, 0, 0, 0));

                Label title = new Label(name);

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:22px;" +
                                                "-fx-font-weight:bold;");

                Label message = new Label(name + " management will be available soon.");

                message.setStyle("-fx-text-fill: #777777; -fx-font-size:12px;");

                root.getChildren().addAll(
                                title,
                                message);

                return root;
        }
}