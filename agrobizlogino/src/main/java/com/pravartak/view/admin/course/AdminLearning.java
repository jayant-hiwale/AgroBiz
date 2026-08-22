package com.pravartak.view.admin.course;

// import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AdminLearning {

        public static VBox getLearningPage() {

                VBox root = new VBox(18);

                root.setPadding(
                                new Insets(25, 35, 25, 35));

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =========================================
                // HEADER
                // =========================================

                HBox header = createHeader();

                // =========================================
                // CONTENT AREA
                // =========================================

                VBox contentArea = new VBox();

                contentArea.setMaxWidth(
                                Double.MAX_VALUE);

                VBox.setVgrow(
                                contentArea,
                                Priority.ALWAYS);

                // =========================================
                // TABS
                // =========================================

                HBox tabs = createTabs(contentArea);

                // =========================================
                // DEFAULT PAGE
                // =========================================

                contentArea.getChildren().add(CourseTab.getCoursesPage());

                // =========================================
                // ADD
                // =========================================

                root.getChildren().addAll(
                                header,
                                tabs,
                                contentArea);

                return root;
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

                Region space = new Region();

                HBox.setHgrow(
                                space,
                                Priority.ALWAYS);

                Button createCourseBtn = new Button("+ Create Course");

                createCourseBtn.setPrefHeight(34);

                createCourseBtn.setPrefWidth(110);

                createCourseBtn.setStyle(
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

                createCourseBtn.setOnMouseEntered(
                                e -> createCourseBtn.setStyle(
                                                "-fx-background-color:#245D35;" +
                                                                "-fx-text-fill:#68D34A;" +
                                                                "-fx-font-size:10px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-border-color:#68D34A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:5;" +
                                                                "-fx-background-radius:5;" +
                                                                "-fx-padding:7 12;" +
                                                                "-fx-cursor:hand;"));

                createCourseBtn.setOnMouseExited(
                                e -> createCourseBtn.setStyle(
                                                "-fx-background-color:transparent;" +
                                                                "-fx-text-fill:#68D34A;" +
                                                                "-fx-font-size:10px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-border-color:#68D34A;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:5;" +
                                                                "-fx-background-radius:5;" +
                                                                "-fx-padding:7 12;" +
                                                                "-fx-cursor:hand;"));

                createCourseBtn.setOnAction(e -> {

                        CreateCourseAdmin createCourseAdmin = new CreateCourseAdmin();

                        LoginPage.mainStage.setScene(createCourseAdmin.getCreateCourseScene());

                });

                header.getChildren().addAll(
                                titleBox,
                                space,
                                createCourseBtn);

                return header;
        }

        // =========================================================
        // TABS
        // =========================================================

        private static HBox createTabs(
                        VBox contentArea) {

                HBox tabs = new HBox(25);

                tabs.setAlignment(
                                Pos.CENTER_LEFT);

                tabs.setPadding(
                                new Insets(10, 0, 10, 0));

                String[] tabNames = {
                                "Courses",
                                "Categories",
                                "Lessons",
                                "Quizzes",
                                "Enrollments",
                                "Analytics"
                };

                for (int i = 0; i < tabNames.length; i++) {

                        Button tab = new Button(tabNames[i]);

                        setNormalTabStyle(tab);

                        if (i == 0) {
                                setActiveTabStyle(tab);
                        }

                        final int index = i;

                        tab.setOnAction(e -> {

                                // =====================================
                                // CHANGE ACTIVE TAB
                                // =====================================

                                for (int j = 0; j < tabs.getChildren().size(); j++) {

                                        Button button = (Button) tabs.getChildren().get(j);

                                        if (j == index) {

                                                setActiveTabStyle(button);

                                        } else {

                                                setNormalTabStyle(button);
                                        }
                                }

                                // =====================================
                                // CHANGE CONTENT
                                // =====================================

                                contentArea.getChildren().clear();

                                switch (tab.getText()) {

                                        case "Courses":

                                                contentArea.getChildren().add(CourseTab.getCoursesPage());

                                                break;

                                        case "Categories":

                                                contentArea.getChildren().add(CategoriesTab.getCategoriesPage());

                                                break;

                                        case "Lessons":

                                                // contentArea.getChildren() .add(getLessonsPage());

                                                break;

                                        case "Quizzes":

                                                // contentArea.getChildren().add(getQuizzesPage());

                                                break;

                                        case "Enrollments":

                                                // contentArea.getChildren().add(getEnrollmentsPage());

                                                break;

                                        case "Analytics":

                                                contentArea.getChildren().add(AnalyticsTab.getAnalyticsPage());

                                                break;
                                }
                        });

                        tabs.getChildren().add(tab);
                }

                return tabs;
        }

        // =========================================================
        // ACTIVE TAB STYLE
        // =========================================================

        private static void setActiveTabStyle(
                        Button tab) {

                tab.setStyle(
                                "-fx-background-color:#245D35;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:12;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-padding:5 12;" +
                                                "-fx-cursor:hand;");
        }

        // =========================================================
        // NORMAL TAB STYLE
        // =========================================================

        private static void setNormalTabStyle(
                        Button tab) {

                tab.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-padding:5 0;" +
                                                "-fx-cursor:hand;");
        }

        // =========================================================
        // LESSONS
        // =========================================================

        // private static VBox getLessonsPage() {

        // VBox root = new VBox(15);

        // root.setPadding(
        // new Insets(10, 0, 0, 0));

        // Label title = new Label("Lessons");

        // title.setStyle(
        // "-fx-text-fill:#EEEEEE;" +
        // "-fx-font-size:22px;" +
        // "-fx-font-weight:bold;");

        // Label subtitle = new Label(
        // "Manage lessons and course content.");

        // subtitle.setStyle(
        // "-fx-text-fill:#AAAAAA;" +
        // "-fx-font-size:10px;");

        // Button add = new Button("+ Add Lesson");

        // add.setStyle(
        // "-fx-background-color:#68D34A;" +
        // "-fx-text-fill:#080C0D;" +
        // "-fx-font-weight:bold;" +
        // "-fx-background-radius:5;");

        // root.getChildren().addAll(
        // title,
        // subtitle,
        // add);

        // return root;
        // }

        // // =========================================================
        // // ENROLLMENTS
        // // =========================================================

        // private static VBox getEnrollmentsPage() {

        // VBox root = new VBox(15);

        // root.setPadding(
        // new Insets(10, 0, 0, 0));

        // Label title = new Label("Enrollments");

        // title.setStyle(
        // "-fx-text-fill:#EEEEEE;" +
        // "-fx-font-size:22px;" +
        // "-fx-font-weight:bold;");

        // Label subtitle = new Label(
        // "Monitor farmer enrollment and participation.");

        // subtitle.setStyle(
        // "-fx-text-fill:#AAAAAA;" +
        // "-fx-font-size:10px;");

        // root.getChildren().addAll(
        // title,
        // subtitle);

        // return root;
        // }

}