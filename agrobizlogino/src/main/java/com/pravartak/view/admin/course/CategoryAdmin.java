package com.pravartak.view.admin.course;


import com.pravartak.controller.admincontroller.CategoryController;
import com.pravartak.model.admin.Category;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class CategoryAdmin {

        private final CategoryController categoryController = new CategoryController();

        private VBox categoryList;

        // =========================================================
        // MAIN SCENE
        // =========================================================

        public Scene getCategoryScene() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                VBox content = new VBox(20);

                content.setPadding(
                                new Insets(25, 35, 30, 35));

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
                                                "-fx-padding:7 14;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-cursor:hand;");

                backButton.setOnAction(
                                e -> goBack());

                VBox titleBox = new VBox(3);

                Label title = new Label("Categories");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:24px;" +
                                                "-fx-font-weight:bold;");

                Label subtitle = new Label(
                                "Create and manage course categories.");

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
                // TOP ACTION BAR
                // =====================================================

                HBox actionBar = createActionBar();

                // =====================================================
                // CATEGORY LIST
                // =====================================================

                categoryList = new VBox(10);

                categoryList.setPadding(
                                new Insets(5, 0, 20, 0));

                loadCategories();

                ScrollPane scrollPane = new ScrollPane(categoryList);

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

                content.getChildren().addAll(
                                header,
                                actionBar,
                                scrollPane);

                root.setCenter(content);

                return new Scene(
                                root,
                                1100,
                                700);
        }

        // =========================================================
        // ACTION BAR
        // =========================================================

        private HBox createActionBar() {

                HBox bar = new HBox(10);

                bar.setAlignment(
                                Pos.CENTER_LEFT);

                bar.setPadding(
                                new Insets(12));

                bar.setStyle(
                                "-fx-background-color:#101612;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                Label info = new Label(
                                "Manage the categories used by your courses.");

                info.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:11px;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button addButton = new Button("+ Add Category");

                addButton.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#071009;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 15;" +
                                                "-fx-cursor:hand;");

                addButton.setOnAction(
                                e -> showAddCategoryForm());

                bar.getChildren().addAll(
                                info,
                                spacer,
                                addButton);

                return bar;
        }

        // =========================================================
        // LOAD CATEGORIES
        // =========================================================

        private void loadCategories() {

                categoryList.getChildren().clear();

                List<Category> categories = categoryController.getAllCategories();

                if (categories == null ||
                                categories.isEmpty()) {

                        categoryList.getChildren().add(
                                        createEmptyView());

                        return;
                }

                for (Category category : categories) {

                        categoryList.getChildren().add(
                                        createCategoryCard(category));
                }
        }

        // =========================================================
        // CATEGORY CARD
        // =========================================================

        private HBox createCategoryCard(
                        Category category) {

                HBox card = new HBox(15);

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setPadding(
                                new Insets(15));

                card.setStyle(
                                "-fx-background-color:#0D1511;" +
                                                "-fx-border-color:#202A25;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                // =====================================================
                // ID
                // =====================================================

                Label id = new Label(
                                "#" + category.getCategoryId());

                id.setPrefWidth(60);

                id.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // ICON
                // =====================================================

                Label icon = new Label("▦");

                icon.setPrefSize(
                                35,
                                35);

                icon.setAlignment(
                                Pos.CENTER);

                icon.setStyle(
                                "-fx-background-color:#14251A;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // NAME
                // =====================================================

                VBox info = new VBox(3);

                Label name = new Label(
                                safe(category.getCategoryName()));

                name.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                Label description = new Label(
                                "Course category");

                description.setStyle(
                                "-fx-text-fill:#68756E;" +
                                                "-fx-font-size:10px;");

                info.getChildren().addAll(
                                name,
                                description);

                HBox.setHgrow(
                                info,
                                Priority.ALWAYS);

                // =====================================================
                // DELETE
                // =====================================================

                Button deleteButton = new Button("Delete");

                deleteButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-border-color:#422020;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-text-fill:#CC7777;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-padding:6 10;" +
                                                "-fx-cursor:hand;");

                deleteButton.setOnAction(
                                e -> {

                                        boolean deleted = categoryController
                                                        .deleteCategory(
                                                                        category.getCategoryId());

                                        if (deleted) {

                                                loadCategories();
                                        }
                                });

                card.getChildren().addAll(
                                id,
                                icon,
                                info,
                                deleteButton);

                // =====================================================
                // HOVER
                // =====================================================

                card.setOnMouseEntered(
                                e -> card.setStyle(
                                                "-fx-background-color:#141B16;" +
                                                                "-fx-border-color:#2B4435;" +
                                                                "-fx-border-radius:7;" +
                                                                "-fx-background-radius:7;"));

                card.setOnMouseExited(
                                e -> card.setStyle(
                                                "-fx-background-color:#0D1511;" +
                                                                "-fx-border-color:#202A25;" +
                                                                "-fx-border-radius:7;" +
                                                                "-fx-background-radius:7;"));

                return card;
        }

        // =========================================================
        // ADD CATEGORY FORM
        // =========================================================

        private void showAddCategoryForm() {

                VBox form = new VBox(12);

                form.setPadding(
                                new Insets(20));

                form.setStyle(
                                "-fx-background-color:#101612;" +
                                                "-fx-border-color:#245D35;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                Label title = new Label("Add New Category");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                Label label = new Label("Category Name");

                label.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                TextField nameField = new TextField();

                nameField.setPromptText(
                                "e.g. Floriculture");

                nameField.setPrefHeight(38);

                nameField.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#555555;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 10;");

                // =====================================================
                // BUTTONS
                // =====================================================

                HBox buttons = new HBox(10);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                Button cancel = new Button("Cancel");

                cancel.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-padding:7 14;" +
                                                "-fx-cursor:hand;");

                cancel.setOnAction(
                                e -> loadCategories());

                Button save = new Button("Save Category");

                save.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#071009;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-padding:8 15;" +
                                                "-fx-cursor:hand;");

                save.setOnAction(
                                e -> {

                                        String name = nameField
                                                        .getText()
                                                        .trim();

                                        if (name.isEmpty()) {

                                                nameField.setPromptText(
                                                                "Category name is required");

                                                return;
                                        }

                                        boolean success = categoryController
                                                        .addCategory(name);

                                        if (success) {

                                                loadCategories();

                                        } else {

                                                nameField.setPromptText(
                                                                "Failed to save category");
                                        }
                                });

                buttons.getChildren().addAll(
                                cancel,
                                save);

                form.getChildren().addAll(
                                title,
                                label,
                                nameField,
                                buttons);

                categoryList
                                .getChildren()
                                .add(0, form);

                nameField.requestFocus();
        }

        // =========================================================
        // EMPTY VIEW
        // =========================================================

        private VBox createEmptyView() {

                VBox box = new VBox(8);

                box.setAlignment(
                                Pos.CENTER);

                box.setPadding(
                                new Insets(50));

                Label icon = new Label("＋");

                icon.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:28px;");

                Label title = new Label("No Categories Yet");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                Label message = new Label(
                                "Create your first course category.");

                message.setStyle(
                                "-fx-text-fill:#777777;" +
                                                "-fx-font-size:10px;");

                box.getChildren().addAll(
                                icon,
                                title,
                                message);

                return box;
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