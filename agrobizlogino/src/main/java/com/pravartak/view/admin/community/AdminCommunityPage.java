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

    private Firestore db;

    private VBox postsContainer;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminCommunityPage() {

        db = FirebaseConfig.getFirestore();

        if (db == null) {

            throw new IllegalStateException(
                    "Firestore is not initialized."
            );
        }
    }

    // =========================================================
    // MAIN COMMUNITY PAGE
    // =========================================================

    public VBox getCommunityPage() {

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(30)
        );

        main.setStyle(
                "-fx-background-color:#080C0D;"
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Community Management"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web("#68D34A")
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle =
                new Label(
                        "View, edit and delete farmer community posts."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#AAAAAA")
        );

        // =====================================================
        // POSTS CONTAINER
        // =====================================================

        postsContainer =
                new VBox(16);

        postsContainer.setFillWidth(true);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        postsContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background:#080C0D;" +
                "-fx-background-color:#080C0D;" +
                "-fx-control-inner-background:#080C0D;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        main.getChildren().addAll(
                title,
                subtitle,
                scrollPane
        );

        // =====================================================
        // LOAD POSTS
        // =====================================================

        loadPosts();

        return main;
    }

    // =========================================================
    // LOAD POSTS
    // =========================================================

    private void loadPosts() {

        postsContainer
                .getChildren()
                .clear();

        try {

            List<CommunityPost> posts =
                    getAllPosts();

            if (posts == null || posts.isEmpty()) {

                Label empty =
                        new Label(
                                "No community posts available."
                        );

                empty.setFont(
                        Font.font(
                                "Arial",
                                15
                        )
                );

                empty.setTextFill(
                        Color.web("#AAAAAA")
                );

                postsContainer
                        .getChildren()
                        .add(empty);

                return;
            }

            for (CommunityPost post : posts) {

                postsContainer
                        .getChildren()
                        .add(
                                createPostCard(post)
                        );
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load community posts."
                    );

            error.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            15
                    )
            );

            error.setTextFill(
                    Color.web("#E57373")
            );

            postsContainer
                    .getChildren()
                    .add(error);
        }
    }

    // =========================================================
    // GET ALL POSTS
    // =========================================================

    private List<CommunityPost> getAllPosts()
            throws Exception {

        List<CommunityPost> posts =
                new ArrayList<>();

        var snapshot =
                db.collection(
                                "communityPosts"
                        )
                        .orderBy(
                                "timestamp",
                                Query.Direction.DESCENDING
                        )
                        .get()
                        .get();

        for (var document :
                snapshot.getDocuments()) {

            CommunityPost post =
                    document.toObject(
                            CommunityPost.class
                    );

            if (post != null) {

                post.setPostId(
                        document.getId()
                );

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

        VBox card =
                new VBox(12);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setPadding(
                new Insets(20)
        );

        // =====================================================
        // CARD THEME
        // =====================================================

        card.setStyle(
                "-fx-background-color:#0D1213;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;"
        );

        // =====================================================
        // FARMER NAME
        // =====================================================

        String farmerName =
                post.getFarmerName();

        if (farmerName == null ||
                farmerName.trim().isEmpty()) {

            farmerName = "Unknown Farmer";
        }

        Label farmer =
                new Label(
                        "👨‍🌾  " + farmerName
                );

        farmer.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        farmer.setTextFill(
                Color.web("#68D34A")
        );

        // =====================================================
        // POST ID
        // =====================================================

        Label postId =
                new Label(
                        "Post ID: "
                        + post.getPostId()
                );

        postId.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        postId.setTextFill(
                Color.web("#666666")
        );

        // =====================================================
        // POST TEXT
        // =====================================================

        String postContent =
                post.getContent();

        if (postContent == null) {
            postContent = "";
        }

        Label content =
                new Label(
                        postContent
                );

        content.setWrapText(true);

        content.setMaxWidth(
                Double.MAX_VALUE
        );

        content.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        // IMPORTANT:
        // White text for dark Admin card

        content.setTextFill(
                Color.web("#EEEEEE")
        );

        content.setStyle(
                "-fx-text-fill:#EEEEEE;"
        );

        // =====================================================
        // IMAGE
        // =====================================================

        VBox imageBox =
                new VBox();

        String imageUrl =
                post.getImageUrl();

        if (imageUrl != null &&
                !imageUrl.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                700,
                                400,
                                true,
                                true
                        );

                ImageView imageView =
                        new ImageView(image);

                imageView.setPreserveRatio(
                        true
                );

                imageView.setFitWidth(
                        700
                );

                imageBox
                        .getChildren()
                        .add(imageView);

            } catch (Exception e) {

                e.printStackTrace();

                Label imageError =
                        new Label(
                                "Unable to display image."
                        );

                imageError.setTextFill(
                        Color.web("#E57373")
                );

                imageBox
                        .getChildren()
                        .add(imageError);
            }
        }

        // =====================================================
        // LIKES
        // =====================================================

        Label likes =
                new Label(
                        "👍  "
                        + post.getLikes()
                        + " Likes"
                );

        likes.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        likes.setTextFill(
                Color.web("#AAAAAA")
        );

        // =====================================================
        // EDIT BUTTON
        // =====================================================

        Button editButton =
                new Button(
                        "✎  Edit"
                );

        editButton.setPrefWidth(
                95
        );

        editButton.setPrefHeight(
                35
        );

        editButton.setStyle(
                "-fx-background-color:#E8F5E9;" +
                "-fx-text-fill:#245D35;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-cursor:hand;"
        );

        editButton.setOnAction(
                event ->
                        editPost(post)
        );

        // =====================================================
        // DELETE BUTTON
        // =====================================================

        Button deleteButton =
                new Button(
                        "▣  Delete"
                );

        deleteButton.setPrefWidth(
                95
        );

        deleteButton.setPrefHeight(
                35
        );

        deleteButton.setStyle(
                "-fx-background-color:#633333;" +
                "-fx-text-fill:#E57373;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-cursor:hand;"
        );

        deleteButton.setOnAction(
                event ->
                        deletePost(post)
        );

        // =====================================================
        // ACTION ROW
        // =====================================================

        HBox actionRow =
                new HBox(
                        12,
                        likes,
                        editButton,
                        deleteButton
                );

        actionRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // SEPARATOR
        // =====================================================

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color:#242B2C;"
        );

        // =====================================================
        // ADD CONTENT
        // =====================================================

        card.getChildren()
                .addAll(
                        farmer,
                        postId
                );

        // Add post text
        if (!postContent
                .trim()
                .isEmpty()) {

            card.getChildren()
                    .add(content);
        }

        // Add image
        if (!imageBox
                .getChildren()
                .isEmpty()) {

            card.getChildren()
                    .add(imageBox);
        }

        card.getChildren()
                .addAll(
                        separator,
                        actionRow
                );

        return card;
    }

    // =========================================================
    // EDIT POST
    // =========================================================

    private void editPost(
            CommunityPost post) {

        Dialog<String> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Community Post"
        );

        dialog.setHeaderText(
                "Edit post by "
                + post.getFarmerName()
        );

        TextArea textArea =
                new TextArea(
                        post.getContent()
                );

        textArea.setWrapText(true);

        textArea.setPrefRowCount(6);

        textArea.setPrefColumnCount(45);

        textArea.setStyle(
                "-fx-control-inner-background:#0D1213;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;"
        );

        dialog.getDialogPane()
                .setContent(
                        textArea
                );

        ButtonType saveButton =
                new ButtonType(
                        "Save",
                        ButtonBar.ButtonData.OK_DONE
                );

        ButtonType cancelButton =
                new ButtonType(
                        "Cancel",
                        ButtonBar.ButtonData.CANCEL_CLOSE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        saveButton,
                        cancelButton
                );

        dialog.setResultConverter(
                button -> {

                    if (button == saveButton) {

                        return textArea
                                .getText()
                                .trim();
                    }

                    return null;
                }
        );

        var result =
                dialog.showAndWait();

        if (result.isPresent()) {

            String newContent =
                    result.get();

            if (newContent.isEmpty()) {

                showAlert(
                        "Post cannot be empty."
                );

                return;
            }

            try {

                db.collection(
                                "communityPosts"
                        )
                        .document(
                                post.getPostId()
                        )
                        .update(
                                "content",
                                newContent
                        )
                        .get();

                loadPosts();

                showAlert(
                        "Post updated successfully."
                );

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        "Unable to update post.\n\n"
                        + e.getMessage()
                );
            }
        }
    }

    // =========================================================
    // DELETE POST
    // =========================================================

    private void deletePost(
            CommunityPost post) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Community Post"
        );

        confirmation.setHeaderText(
                "Delete this farmer's post?"
        );

        confirmation.setContentText(
                "This post will be permanently deleted."
        );

        var result =
                confirmation.showAndWait();

        if (result.isPresent()
                && result.get()
                        == ButtonType.OK) {

            try {

                db.collection(
                                "communityPosts"
                        )
                        .document(
                                post.getPostId()
                        )
                        .delete()
                        .get();

                loadPosts();

                showAlert(
                        "Post deleted successfully."
                );

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        "Unable to delete post.\n\n"
                        + e.getMessage()
                );
            }
        }
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Community Management"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}