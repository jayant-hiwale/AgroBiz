package com.pravartak.view.admin.community;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.farmer_model.CommunityPost;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class AdminCommunityPage {

        // =========================================================
        // THEME COLORS
        // =========================================================

        private static final String BG_COLOR = "#080C0D";
        private static final String CARD_COLOR = "#101718";
        private static final String INNER_COLOR = "#0D1213";

        private static final String BORDER_COLOR = "#293334";
        private static final String BORDER_LIGHT = "#242B2C";

        private static final String GREEN = "#68D34A";
        private static final String GREEN_DARK = "#163D24";
        private static final String GREEN_HOVER = "#245D35";

        private static final String TEXT_PRIMARY = "#EEEEEE";
        private static final String TEXT_SECONDARY = "#8F999A";
        private static final String TEXT_MUTED = "#737D7E";

        private static final String RED = "#FF6B6B";
        private static final String RED_DARK = "#3A1517";
        private static final String RED_BORDER = "#6B2528";

        // =========================================================
        // FIRESTORE
        // =========================================================

        private Firestore db;

        // =========================================================
        // POSTS CONTAINER
        // =========================================================

        private VBox postsContainer;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public AdminCommunityPage() {

                db = FirebaseConfig.getFirestore();

                if (db == null) {

                        throw new IllegalStateException(
                                        "Firestore is not initialized.");
                }
        }

        // =========================================================
        // MAIN COMMUNITY PAGE
        // =========================================================

        public VBox getCommunityPage() {

                VBox main = new VBox(20);

                main.setPadding(
                                new Insets(30));

                main.setStyle(
                                "-fx-background-color:" + BG_COLOR + ";");

                // =====================================================
                // PAGE TITLE
                // =====================================================

                Label title = new Label(
                                "Community Management");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                30));

                title.setTextFill(
                                Color.web(GREEN));

                // =====================================================
                // SUBTITLE
                // =====================================================

                Label subtitle = new Label(
                                "View, edit and manage farmer community posts.");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                subtitle.setTextFill(
                                Color.web(TEXT_SECONDARY));

                // =====================================================
                // POSTS HEADER
                // =====================================================

                HBox postsHeader = createPostsHeader();

                // =====================================================
                // POSTS CONTAINER
                // =====================================================

                postsContainer = new VBox(16);

                postsContainer.setFillWidth(true);

                postsContainer.setPadding(
                                new Insets(0));

                // =====================================================
                // POSTS CARD
                // =====================================================

                VBox postsBox = new VBox(16);

                postsBox.setPadding(
                                new Insets(20));

                postsBox.setStyle(
                                "-fx-background-color:" + CARD_COLOR + ";" +
                                                "-fx-border-color:" + BORDER_COLOR + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:14;" +
                                                "-fx-background-radius:14;");

                postsBox.getChildren().addAll(
                                postsHeader,
                                postsContainer);

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                postsBox);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background:#080C0D;" +
                                                "-fx-background-color:#080C0D;" +
                                                "-fx-control-inner-background:#080C0D;" +
                                                "-fx-border-color:transparent;");

                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                // =====================================================
                // ADD COMPONENTS
                // =====================================================

                main.getChildren().addAll(
                                title,
                                subtitle,
                                scrollPane);

                // =====================================================
                // LOAD POSTS
                // =====================================================

                loadPosts();

                return main;
        }

        // =========================================================
        // POSTS HEADER
        // =========================================================

        private HBox createPostsHeader() {

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // TITLE SECTION
                // =====================================================

                VBox titleBox = new VBox(3);

                Label title = new Label(
                                "Community Posts");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(TEXT_PRIMARY));

                Label description = new Label(
                                "Manage posts shared by farmers");

                description.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                description.setTextFill(
                                Color.web(TEXT_MUTED));

                titleBox.getChildren().addAll(
                                title,
                                description);

                // =====================================================
                // POST COUNT
                // =====================================================

                Label countLabel = new Label();

                countLabel.setText(
                                "Posts");

                countLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                countLabel.setTextFill(
                                Color.web(GREEN));

                countLabel.setStyle(
                                "-fx-background-color:" + GREEN_DARK + ";" +
                                                "-fx-background-radius:20;" +
                                                "-fx-padding:7 14 7 14;");

                header.getChildren().addAll(
                                titleBox,
                                createSpacer(),
                                countLabel);

                return header;
        }

        // =========================================================
        // LOAD POSTS
        // =========================================================

        private void loadPosts() {

                if (postsContainer == null) {
                        return;
                }

                postsContainer
                                .getChildren()
                                .clear();

                try {

                        List<CommunityPost> posts = getAllPosts();

                        if (posts == null || posts.isEmpty()) {

                                VBox emptyBox = new VBox(10);

                                emptyBox.setAlignment(
                                                Pos.CENTER);

                                emptyBox.setPadding(
                                                new Insets(40));

                                Label icon = new Label(
                                                "☁");

                                icon.setFont(
                                                Font.font(
                                                                "Arial",
                                                                30));

                                icon.setTextFill(
                                                Color.web(TEXT_MUTED));

                                Label empty = new Label(
                                                "No community posts available.");

                                empty.setFont(
                                                Font.font(
                                                                "Arial",
                                                                FontWeight.BOLD,
                                                                15));

                                empty.setTextFill(
                                                Color.web(TEXT_SECONDARY));

                                emptyBox.getChildren().addAll(
                                                icon,
                                                empty);

                                postsContainer
                                                .getChildren()
                                                .add(emptyBox);

                                return;
                        }

                        for (CommunityPost post : posts) {

                                postsContainer
                                                .getChildren()
                                                .add(
                                                                createPostCard(post));
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        VBox errorBox = new VBox();

                        errorBox.setAlignment(
                                        Pos.CENTER);

                        errorBox.setPadding(
                                        new Insets(30));

                        Label error = new Label(
                                        "Unable to load community posts.");

                        error.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

                        error.setTextFill(
                                        Color.web("#E57373"));

                        errorBox
                                        .getChildren()
                                        .add(error);

                        postsContainer
                                        .getChildren()
                                        .add(errorBox);
                }
        }

        // =========================================================
        // GET ALL POSTS
        // =========================================================

        private List<CommunityPost> getAllPosts()
                        throws Exception {

                List<CommunityPost> posts = new ArrayList<>();

                var snapshot = db.collection(
                                "communityPosts")
                                .orderBy(
                                                "timestamp",
                                                Query.Direction.DESCENDING)
                                .get()
                                .get();

                for (var document : snapshot.getDocuments()) {

                        CommunityPost post = document.toObject(
                                        CommunityPost.class);

                        if (post != null) {

                                post.setPostId(
                                                document.getId());

                                posts.add(post);
                        }
                }

                return posts;
        }

        // =========================================================
        // CREATE POST CARD
        // =========================================================

        private VBox createPostCard(
                        CommunityPost post) {

                VBox card = new VBox(14);

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setPadding(
                                new Insets(20));

                card.setStyle(
                                "-fx-background-color:" + INNER_COLOR + ";" +
                                                "-fx-border-color:" + BORDER_LIGHT + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // FARMER HEADER
                // =====================================================

                HBox farmerHeader = new HBox(12);

                farmerHeader.setAlignment(
                                Pos.CENTER_LEFT);

                // -----------------------------------------------------
                // FARMER ICON
                // -----------------------------------------------------

                Label farmerIcon = new Label("👨‍🌾");

                farmerIcon.setFont(
                                Font.font(
                                                "Arial",
                                                20));

                farmerIcon.setMinWidth(
                                35);

                farmerIcon.setAlignment(
                                Pos.CENTER);

                farmerIcon.setStyle(
                                "-fx-background-color:" + GREEN_DARK + ";" +
                                                "-fx-background-radius:50;" +
                                                "-fx-padding:7;");

                // -----------------------------------------------------
                // FARMER INFORMATION
                // -----------------------------------------------------

                VBox farmerInfo = new VBox(2);

                String farmerName = post.getFarmerName();

                if (farmerName == null ||
                                farmerName.trim().isEmpty()) {

                        farmerName = "Unknown Farmer";
                }

                Label farmer = new Label(
                                farmerName);

                farmer.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));

                farmer.setTextFill(
                                Color.web(TEXT_PRIMARY));

                Label postId = new Label(
                                "Post ID: "
                                                + safe(
                                                                post.getPostId()));

                postId.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                postId.setTextFill(
                                Color.web(TEXT_MUTED));

                farmerInfo.getChildren().addAll(
                                farmer,
                                postId);

                farmerHeader.getChildren().addAll(
                                farmerIcon,
                                farmerInfo);

                // =====================================================
                // POST TEXT
                // =====================================================

                String postContent = post.getContent();

                if (postContent == null) {
                        postContent = "";
                }

                Label content = new Label(
                                postContent);

                content.setWrapText(
                                true);

                content.setMaxWidth(
                                Double.MAX_VALUE);

                content.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                15));

                content.setTextFill(
                                Color.web(TEXT_PRIMARY));

                content.setStyle(
                                "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                                                "-fx-line-spacing:3;");

                // =====================================================
                // IMAGE
                // =====================================================

                VBox imageBox = new VBox();

                String imageUrl = post.getImageUrl();

                if (imageUrl != null &&
                                !imageUrl.trim().isEmpty()) {

                        try {

                                Image image = new Image(
                                                imageUrl,
                                                700,
                                                400,
                                                true,
                                                true,
                                                true);

                                ImageView imageView = new ImageView(image);

                                imageView.setPreserveRatio(
                                                true);

                                imageView.setFitWidth(
                                                700);

                                imageView.setSmooth(
                                                true);

                                imageView.setStyle(
                                                "-fx-border-radius:10;");

                                imageBox
                                                .getChildren()
                                                .add(
                                                                imageView);

                        } catch (Exception e) {

                                e.printStackTrace();

                                Label imageError = new Label(
                                                "Unable to display image.");

                                imageError.setFont(
                                                Font.font(
                                                                "Arial",
                                                                12));

                                imageError.setTextFill(
                                                Color.web("#E57373"));

                                imageBox
                                                .getChildren()
                                                .add(
                                                                imageError);
                        }
                }

                // =====================================================
                // LIKES
                // =====================================================

                Label likes = new Label(
                                "👍  "
                                                + post.getLikes()
                                                + " Likes");

                likes.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                likes.setTextFill(
                                Color.web(TEXT_SECONDARY));

                // =====================================================
                // EDIT BUTTON
                // =====================================================

                Button editButton = new Button(
                                "✎  Edit");

                editButton.setPrefWidth(
                                95);

                editButton.setPrefHeight(
                                35);

                editButton.setStyle(
                                "-fx-background-color:" + GREEN_DARK + ";" +
                                                "-fx-text-fill:" + GREEN + ";" +
                                                "-fx-border-color:" + GREEN_HOVER + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                editButton.setOnMouseEntered(
                                event -> editButton.setStyle(
                                                "-fx-background-color:" + GREEN_HOVER + ";" +
                                                                "-fx-text-fill:#FFFFFF;" +
                                                                "-fx-border-color:" + GREEN + ";" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:7;" +
                                                                "-fx-background-radius:7;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-cursor:hand;"));

                editButton.setOnMouseExited(
                                event -> editButton.setStyle(
                                                "-fx-background-color:" + GREEN_DARK + ";" +
                                                                "-fx-text-fill:" + GREEN + ";" +
                                                                "-fx-border-color:" + GREEN_HOVER + ";" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:7;" +
                                                                "-fx-background-radius:7;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-cursor:hand;"));

                editButton.setOnAction(
                                event -> editPost(post));

                // =====================================================
                // DELETE BUTTON
                // =====================================================

                Button deleteButton = new Button(
                                "▣  Delete");

                deleteButton.setPrefWidth(
                                95);

                deleteButton.setPrefHeight(
                                35);

                deleteButton.setStyle(
                                "-fx-background-color:" + RED_DARK + ";" +
                                                "-fx-text-fill:" + RED + ";" +
                                                "-fx-border-color:" + RED_BORDER + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                deleteButton.setOnMouseEntered(
                                event -> deleteButton.setStyle(
                                                "-fx-background-color:" + RED_BORDER + ";" +
                                                                "-fx-text-fill:#FFFFFF;" +
                                                                "-fx-border-color:" + RED + ";" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:7;" +
                                                                "-fx-background-radius:7;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-cursor:hand;"));

                deleteButton.setOnMouseExited(
                                event -> deleteButton.setStyle(
                                                "-fx-background-color:" + RED_DARK + ";" +
                                                                "-fx-text-fill:" + RED + ";" +
                                                                "-fx-border-color:" + RED_BORDER + ";" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:7;" +
                                                                "-fx-background-radius:7;" +
                                                                "-fx-font-size:12px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-cursor:hand;"));

                deleteButton.setOnAction(
                                event -> deletePost(post));

                // =====================================================
                // ACTION ROW
                // =====================================================

                HBox actionRow = new HBox(
                                12);

                actionRow.setAlignment(
                                Pos.CENTER_LEFT);

                actionRow.getChildren().addAll(
                                likes,
                                createSpacer(),
                                editButton,
                                deleteButton);

                // =====================================================
                // SEPARATOR
                // =====================================================

                Separator separator = new Separator();

                separator.setStyle(
                                "-fx-background-color:" + BORDER_LIGHT + ";");

                // =====================================================
                // ADD CONTENT
                // =====================================================

                card.getChildren()
                                .add(
                                                farmerHeader);

                if (!postContent
                                .trim()
                                .isEmpty()) {

                        card.getChildren()
                                        .add(
                                                        content);
                }

                if (!imageBox
                                .getChildren()
                                .isEmpty()) {

                        card.getChildren()
                                        .add(
                                                        imageBox);
                }

                card.getChildren()
                                .addAll(
                                                separator,
                                                actionRow);

                // =====================================================
                // HOVER EFFECT
                // =====================================================

                card.setOnMouseEntered(
                                event -> card.setStyle(
                                                "-fx-background-color:#111A1B;" +
                                                                "-fx-border-color:#344041;" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:12;" +
                                                                "-fx-background-radius:12;"));

                card.setOnMouseExited(
                                event -> card.setStyle(
                                                "-fx-background-color:" + INNER_COLOR + ";" +
                                                                "-fx-border-color:" + BORDER_LIGHT + ";" +
                                                                "-fx-border-width:1;" +
                                                                "-fx-border-radius:12;" +
                                                                "-fx-background-radius:12;"));

                return card;
        }

        // =========================================================
        // EDIT POST
        // =========================================================

        private void editPost(
                        CommunityPost post) {

                Dialog<String> dialog = new Dialog<>();

                dialog.setTitle(
                                "Edit Community Post");

                dialog.setHeaderText(
                                "Edit post by "
                                                + safe(
                                                                post.getFarmerName()));

                // =====================================================
                // TEXT AREA
                // =====================================================

                TextArea textArea = new TextArea(
                                post.getContent());

                textArea.setWrapText(
                                true);

                textArea.setPrefRowCount(
                                7);

                textArea.setPrefColumnCount(
                                45);

                textArea.setStyle(
                                "-fx-control-inner-background:" + INNER_COLOR + ";" +
                                                "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                                                "-fx-prompt-text-fill:#777777;" +
                                                "-fx-highlight-fill:" + GREEN + ";" +
                                                "-fx-highlight-text-fill:#000000;" +
                                                "-fx-border-color:" + BORDER_COLOR + ";" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                dialog.getDialogPane()
                                .setContent(
                                                textArea);

                // =====================================================
                // BUTTONS
                // =====================================================

                ButtonType saveButton = new ButtonType(
                                "Save",
                                ButtonBar.ButtonData.OK_DONE);

                ButtonType cancelButton = new ButtonType(
                                "Cancel",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                                saveButton,
                                                cancelButton);

                // =====================================================
                // DIALOG STYLE
                // =====================================================

                styleDialog(
                                dialog);

                // =====================================================
                // RESULT
                // =====================================================

                dialog.setResultConverter(
                                button -> {

                                        if (button == saveButton) {

                                                return textArea
                                                                .getText()
                                                                .trim();
                                        }

                                        return null;
                                });

                var result = dialog.showAndWait();

                // =====================================================
                // SAVE
                // =====================================================

                if (result.isPresent()) {

                        String newContent = result.get();

                        if (newContent.isEmpty()) {

                                showAlert(
                                                "Post cannot be empty.",
                                                Alert.AlertType.WARNING);

                                return;
                        }

                        try {

                                db.collection(
                                                "communityPosts")
                                                .document(
                                                                post.getPostId())
                                                .update(
                                                                "content",
                                                                newContent)
                                                .get();

                                loadPosts();

                                showAlert(
                                                "Post updated successfully.",
                                                Alert.AlertType.INFORMATION);

                        } catch (Exception e) {

                                e.printStackTrace();

                                showAlert(
                                                "Unable to update post.\n\n"
                                                                + e.getMessage(),
                                                Alert.AlertType.ERROR);
                        }
                }
        }

        // =========================================================
        // DELETE POST
        // =========================================================

        private void deletePost(
                        CommunityPost post) {

                Alert confirmation = new Alert(
                                Alert.AlertType.CONFIRMATION);

                confirmation.setTitle(
                                "Delete Community Post");

                confirmation.setHeaderText(
                                "Delete this farmer's post?");

                confirmation.setContentText(
                                "This post will be permanently deleted.");

                styleAlert(
                                confirmation);

                var result = confirmation.showAndWait();

                if (result.isPresent()
                                &&
                                result.get() == ButtonType.OK) {

                        try {

                                db.collection(
                                                "communityPosts")
                                                .document(
                                                                post.getPostId())
                                                .delete()
                                                .get();

                                loadPosts();

                                showAlert(
                                                "Post deleted successfully.",
                                                Alert.AlertType.INFORMATION);

                        } catch (Exception e) {

                                e.printStackTrace();

                                showAlert(
                                                "Unable to delete post.\n\n"
                                                                + e.getMessage(),
                                                Alert.AlertType.ERROR);
                        }
                }
        }

        // =========================================================
        // SHOW ALERT
        // =========================================================

        private void showAlert(
                        String message,
                        Alert.AlertType type) {

                Alert alert = new Alert(
                                type);

                alert.setTitle(
                                "Community Management");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                styleAlert(
                                alert);

                alert.showAndWait();
        }

        // =========================================================
        // STYLE ALERT
        // =========================================================

        private void styleAlert(
                        Alert alert) {

                alert.getDialogPane()
                                .setStyle(
                                                "-fx-background-color:" + CARD_COLOR + ";" +
                                                                "-fx-border-color:" + BORDER_COLOR + ";" +
                                                                "-fx-border-width:1;");

                // -----------------------------------------------------
                // CONTENT
                // -----------------------------------------------------

                var content = alert.getDialogPane()
                                .lookup(
                                                ".content.label");

                if (content != null) {

                        content.setStyle(
                                        "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                                                        "-fx-font-size:13px;");
                }

                // -----------------------------------------------------
                // HEADER
                // -----------------------------------------------------

                var header = alert.getDialogPane()
                                .lookup(
                                                ".header-panel");

                if (header != null) {

                        header.setStyle(
                                        "-fx-background-color:" + CARD_COLOR + ";");
                }

                // -----------------------------------------------------
                // HEADER LABEL
                // -----------------------------------------------------

                var headerLabel = alert.getDialogPane()
                                .lookup(
                                                ".header-panel .label");

                if (headerLabel != null) {

                        headerLabel.setStyle(
                                        "-fx-text-fill:" + GREEN + ";" +
                                                        "-fx-font-size:17px;" +
                                                        "-fx-font-weight:bold;");
                }

                // -----------------------------------------------------
                // BUTTONS
                // -----------------------------------------------------

                Button okButton = (Button) alert.getDialogPane()
                                .lookupButton(
                                                ButtonType.OK);

                if (okButton != null) {

                        okButton.setStyle(
                                        "-fx-background-color:" + GREEN_DARK + ";" +
                                                        "-fx-text-fill:" + GREEN + ";" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                }

                Button cancelButton = (Button) alert.getDialogPane()
                                .lookupButton(
                                                ButtonType.CANCEL);

                if (cancelButton != null) {

                        cancelButton.setStyle(
                                        "-fx-background-color:#202829;" +
                                                        "-fx-text-fill:" + TEXT_SECONDARY + ";" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                }
        }

        // =========================================================
        // STYLE DIALOG
        // =========================================================

        private void styleDialog(
                        Dialog<?> dialog) {

                dialog.getDialogPane()
                                .setStyle(
                                                "-fx-background-color:" + CARD_COLOR + ";" +
                                                                "-fx-border-color:" + BORDER_COLOR + ";" +
                                                                "-fx-border-width:1;");

                // -----------------------------------------------------
                // HEADER
                // -----------------------------------------------------

                var header = dialog.getDialogPane()
                                .lookup(
                                                ".header-panel");

                if (header != null) {

                        header.setStyle(
                                        "-fx-background-color:" + CARD_COLOR + ";");
                }

                // -----------------------------------------------------
                // HEADER LABEL
                // -----------------------------------------------------

                var headerLabel = dialog.getDialogPane()
                                .lookup(
                                                ".header-panel .label");

                if (headerLabel != null) {

                        headerLabel.setStyle(
                                        "-fx-text-fill:" + GREEN + ";" +
                                                        "-fx-font-size:17px;" +
                                                        "-fx-font-weight:bold;");
                }

                // -----------------------------------------------------
                // BUTTONS
                // -----------------------------------------------------

                Button saveButton = (Button) dialog.getDialogPane()
                                .lookupButton(
                                                dialog.getDialogPane()
                                                                .getButtonTypes()
                                                                .get(0));

                if (saveButton != null) {

                        saveButton.setStyle(
                                        "-fx-background-color:" + GREEN_DARK + ";" +
                                                        "-fx-text-fill:" + GREEN + ";" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                }

                Button cancelButton = (Button) dialog.getDialogPane()
                                .lookupButton(
                                                dialog.getDialogPane()
                                                                .getButtonTypes()
                                                                .get(1));

                if (cancelButton != null) {

                        cancelButton.setStyle(
                                        "-fx-background-color:#202829;" +
                                                        "-fx-text-fill:" + TEXT_SECONDARY + ";" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                }
        }

        // =========================================================
        // SAFE STRING
        // =========================================================

        private String safe(
                        String value) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return "Unknown";
                }

                return value;
        }

        // =========================================================
        // SPACER
        // =========================================================

        private HBox createSpacer() {

                HBox spacer = new HBox();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                return spacer;
        }
}