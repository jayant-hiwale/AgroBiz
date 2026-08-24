package com.pravartak.view.admin.course;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CategoriesTab {
    public static VBox getCategoriesPage() {

        VBox root = new VBox(15);

        root.setPadding(new Insets(10, 0, 0, 0));

        Label title = new Label("Categories");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                        "-fx-font-size:22px;" +
                        "-fx-font-weight:bold;");

        Label subtitle = new Label(
                "Create and manage course categories.");

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                        "-fx-font-size:10px;");

        Button add = new Button("+ Add Category");

        add.setStyle(
                "-fx-background-color:#68D34A;" +
                        "-fx-text-fill:#080C0D;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:5;");

        root.getChildren().addAll(
                title,
                subtitle,
                add);

        return root;
    }
}
