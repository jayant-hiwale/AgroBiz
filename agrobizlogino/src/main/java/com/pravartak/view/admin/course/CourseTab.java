package com.pravartak.view.admin.course;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CourseTab {
    public static VBox getCoursesPage() {

        VBox root = new VBox(15);

        root.setPadding(
                new Insets(15, 0, 0, 0));

        root.setStyle(
                "-fx-background-color:#080C0D;");

        Label title = new Label("Courses");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                        "-fx-font-size:22px;" +
                        "-fx-font-weight:bold;");

        Label subtitle = new Label(
                "Manage and monitor all agricultural courses.");

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:10px;");

        root.getChildren().addAll(
                title,
                subtitle);

        return root;
    }
}
