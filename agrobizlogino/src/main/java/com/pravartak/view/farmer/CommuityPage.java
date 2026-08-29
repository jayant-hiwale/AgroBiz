package com.pravartak.view.farmer;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.controller.farmercontoller.CommunityController;
import com.pravartak.dao.farmer.CommunityDAO;
import com.pravartak.model.farmer_model.CommunityPost;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class CommuityPage {

    private Scene communityScene;

    private CommunityController controller;

    private VBox postsContainer;

    private TextArea postTextArea;

    private Label selectedImageLabel;

    private File selectedImage;

    private Firestore db;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public CommuityPage() {

        // Get existing Firebase Firestore
        this.db = FirebaseConfig.getFirestore();

        if (this.db == null) {

            throw new IllegalStateException(
                    "Firestore is not initialized."
            );
        }

        // Create DAO
        CommunityDAO dao =
                new CommunityDAO(this.db);

        // Create Controller
        this.controller =
                new CommunityController(dao);
    }

    // =====================================================
    // COMMUNITY SCENE
    // =====================================================

    public Scene getCommunityScene() {

        BorderPane root =
                new BorderPane();

        // Main page background
        root.setStyle(
                "-fx-background-color: #F4F8F3;"
        );

        // NAVBAR
        root.setTop(
                new NavBar()
                        .createNavbar("Community")
        );

        // FOOTER
        root.setBottom(
                new Footer()
                        .createFooter()
        );

        // MAIN CONTENT
        VBox mainContent =
                createCommunityContent();

        // SCROLL PANE
        ScrollPane scrollPane =
                new ScrollPane(mainContent);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: #F4F8F3;" +
                "-fx-background-color: #F4F8F3;" +
                "-fx-control-inner-background: #F4F8F3;"
        );

        root.setCenter(scrollPane);

        communityScene =
                new Scene(
                        root,
                        1200,
                        750
                );

        // LOAD POSTS
        loadPosts();

        return communityScene;
    }

    // =====================================================
    // COMMUNITY CONTENT
    // =====================================================

    private VBox createCommunityContent() {

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(
                        30,
                        50,
                        40,
                        50
                )
        );

        main.setAlignment(
                Pos.TOP_CENTER
        );

        main.setStyle(
                "-fx-background-color: #F4F8F3;"
        );

        // =================================================
        // TITLE
        // =================================================

        Label title =
                new Label(
                        "🌾 Farmer Community"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web("#14532D")
        );

        Label subtitle =
                new Label(
                        "Share your farming experience, knowledge and updates with other farmers."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#555555")
        );

        // =================================================
        // CREATE POST CARD
        // =================================================

        VBox createPostCard =
                new VBox(15);

        createPostCard.setMaxWidth(800);

        createPostCard.setPadding(
                new Insets(22)
        );

        createPostCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #D5E5D3;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 1;"
        );

        // =================================================
        // CREATE POST TITLE
        // =================================================

        Label createTitle =
                new Label(
                        "Create a Post"
                );

        createTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        createTitle.setTextFill(
                Color.web("#14532D")
        );

        // =================================================
        // TEXT AREA
        // =================================================

        postTextArea =
                new TextArea();

        postTextArea.setPromptText(
                "What's happening on your farm?"
        );

        postTextArea.setWrapText(true);

        postTextArea.setPrefRowCount(4);

        postTextArea.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-control-inner-background: #FFFFFF;" +
                "-fx-text-fill: #222222;" +
                "-fx-prompt-text-fill: #888888;" +
                "-fx-border-color: #CCCCCC;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        // =================================================
        // IMAGE BUTTON
        // =================================================

        Button uploadButton =
                new Button(
                        "📷 Upload Image"
                );

        uploadButton.setPrefHeight(40);

        uploadButton.setStyle(
                "-fx-background-color: #E8F5E9;" +
                "-fx-text-fill: #14532D;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        uploadButton.setOnAction(
                event -> selectImage()
        );

        // =================================================
        // SELECTED IMAGE LABEL
        // =================================================

        selectedImageLabel =
                new Label(
                        "No image selected"
                );

        selectedImageLabel.setTextFill(
                Color.web("#777777")
        );

        // =================================================
        // REMOVE IMAGE BUTTON
        // =================================================

        Button removeImageButton =
                new Button(
                        "Remove"
                );

        removeImageButton.setVisible(
                false
        );

        removeImageButton.setStyle(
                "-fx-background-color: #FFEBEE;" +
                "-fx-text-fill: #C62828;" +
                "-fx-background-radius: 8;"
        );

        removeImageButton.setOnAction(
                event -> {

                    selectedImage = null;

                    selectedImageLabel.setText(
                            "No image selected"
                    );

                    removeImageButton.setVisible(
                            false
                    );
                }
        );

        HBox imageRow =
                new HBox(
                        10,
                        uploadButton,
                        selectedImageLabel,
                        removeImageButton
                );

        imageRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // POST BUTTON
        // =================================================

        Button postButton =
                new Button(
                        "+ Post"
                );

        postButton.setPrefWidth(120);

        postButton.setPrefHeight(42);

        postButton.setStyle(
                "-fx-background-color: #2E7D32;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        postButton.setOnAction(
                event -> createPost()
        );

        HBox postRow =
                new HBox();

        postRow.setAlignment(
                Pos.CENTER_RIGHT
        );

        postRow.getChildren()
                .add(postButton);

        // =================================================
        // ADD TO CREATE CARD
        // =================================================

        createPostCard.getChildren()
                .addAll(
                        createTitle,
                        postTextArea,
                        imageRow,
                        postRow
                );

        // =================================================
        // COMMUNITY POSTS TITLE
        // =================================================

        Label feedTitle =
                new Label(
                        "Community Posts"
                );

        feedTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        feedTitle.setTextFill(
                Color.web("#14532D")
        );

        // =================================================
        // POSTS CONTAINER
        // =================================================

        postsContainer =
                new VBox(15);

        postsContainer.setMaxWidth(800);

        postsContainer.setFillWidth(true);

        // =================================================
        // ADD EVERYTHING TO MAIN
        // =================================================

        main.getChildren()
                .addAll(
                        title,
                        subtitle,
                        createPostCard,
                        feedTitle,
                        postsContainer
                );

        return main;
    }

    // =====================================================
    // SELECT IMAGE
    // =====================================================

    private void selectImage() {

        FileChooser fileChooser =
                new FileChooser();

        fileChooser.setTitle(
                "Select Farm Image"
        );

        fileChooser
                .getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "Image Files",
                                "*.png",
                                "*.jpg",
                                "*.jpeg"
                        )
                );

        File file =
                fileChooser.showOpenDialog(
                        communityScene.getWindow()
                );

        if (file != null) {

            selectedImage = file;

            selectedImageLabel.setText(
                    file.getName()
            );

            // Show image name
            selectedImageLabel.setTextFill(
                    Color.web("#2E7D32")
            );
        }
    }

    // =====================================================
    // CREATE POST
    // =====================================================

    private void createPost() {

        String content =
                postTextArea
                        .getText()
                        .trim();

        // Check empty post
        if (content.isEmpty()
                && selectedImage == null) {

            showAlert(
                    "Please write something or upload an image."
            );

            return;
        }

        try {

            /*
             * IMPORTANT:
             *
             * Replace these values with your actual
             * logged-in farmer information.
             *
             * For testing, these values are used.
             */

            String farmerId =
                    "CURRENT_FARMER_ID";

            String farmerName =
                    "Current Farmer";

            /*
             * Image URL
             *
             * Firebase Storage upload should be done here.
             *
             * For now:
             */

            String imageUrl = "";

            // SAVE POST
            controller.createPost(
                    farmerId,
                    farmerName,
                    content,
                    imageUrl
            );

            // CLEAR TEXT
            postTextArea.clear();

            // CLEAR IMAGE
            selectedImage = null;

            selectedImageLabel.setText(
                    "No image selected"
            );

            selectedImageLabel.setTextFill(
                    Color.web("#777777")
            );

            // REFRESH POSTS
            loadPosts();

            showAlert(
                    "Post created successfully!"
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            showAlert(
                    "Unable to create post.\n\n"
                    + ex.getMessage()
            );
        }
    }

    // =====================================================
    // LOAD POSTS
    // =====================================================

    private void loadPosts() {

        if (postsContainer == null) {
            return;
        }

        postsContainer
                .getChildren()
                .clear();

        try {

            List<CommunityPost> posts =
                    controller.getPosts();

            // NO POSTS
            if (posts.isEmpty()) {

                Label empty =
                        new Label(
                                "No posts yet. Be the first farmer to post!"
                        );

                empty.setFont(
                        Font.font(
                                "Arial",
                                14
                        )
                );

                empty.setTextFill(
                        Color.web("#666666")
                );

                postsContainer
                        .getChildren()
                        .add(empty);

                return;
            }

            // SHOW POSTS
            for (CommunityPost post :
                    posts) {

                postsContainer
                        .getChildren()
                        .add(
                                createPostCard(post)
                        );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load community posts."
                    );

            error.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            14
                    )
            );

            error.setTextFill(
                    Color.web("#C62828")
            );

            postsContainer
                    .getChildren()
                    .add(error);
        }
    }

    // =====================================================
    // CREATE POST CARD
    // =====================================================

    private VBox createPostCard(
            CommunityPost post) {

        VBox card =
                new VBox(12);

        card.setMaxWidth(800);

        card.setPadding(
                new Insets(20)
        );

        /*
         * WHITE CARD
         *
         * This is important because the page background
         * is light green.
         */

        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #D5E5D3;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 1;"
        );

        // =================================================
        // FARMER NAME
        // =================================================

        Label farmer =
                new Label(
                        "👨‍🌾 "
                        + post.getFarmerName()
                );

        farmer.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        farmer.setTextFill(
                Color.web("#14532D")
        );

        farmer.setStyle(
                "-fx-text-fill: #14532D;" +
                "-fx-font-weight: bold;"
        );

        // =================================================
        // POST CONTENT
        // =================================================

        Label content =
                new Label(
                        post.getContent()
                );

        content.setWrapText(true);

        content.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        /*
         * IMPORTANT:
         * Dark text so it does NOT merge with background.
         */

        content.setTextFill(
                Color.web("#222222")
        );

        content.setStyle(
                "-fx-text-fill: #222222;"
        );

        // =================================================
        // IMAGE
        // =================================================

        VBox imageBox =
                new VBox();

        String imageUrl =
                post.getImageUrl();

        if (imageUrl != null
                && !imageUrl.trim().isEmpty()) {

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

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        // =================================================
        // LIKE COUNT
        // =================================================

        Label likes =
                new Label(
                        "👍 "
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

        /*
         * IMPORTANT:
         * Dark color so Likes is clearly visible.
         */

        likes.setTextFill(
                Color.web("#333333")
        );

        likes.setStyle(
                "-fx-text-fill: #333333;" +
                "-fx-font-weight: bold;"
        );

        // =================================================
        // LIKE BUTTON
        // =================================================

        Button likeButton =
                new Button(
                        "👍 Like"
                );

        likeButton.setPrefHeight(
                36
        );

        likeButton.setStyle(
                "-fx-background-color: #E8F5E9;" +
                "-fx-text-fill: #14532D;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 18;" +
                "-fx-cursor: hand;"
        );

        likeButton.setOnAction(
                event -> {

                    try {

                        controller.likePost(
                                post.getPostId()
                        );

                        // Reload to show new count
                        loadPosts();

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        showAlert(
                                "Unable to like post."
                        );
                    }
                }
        );

        // =================================================
        // ACTION ROW
        // =================================================

        HBox actionRow =
                new HBox(
                        20,
                        likes,
                        likeButton
                );

        actionRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // SEPARATOR
        // =================================================

        Separator separator =
                new Separator();

        // =================================================
        // ADD CONTENT
        // =================================================

        card.getChildren()
                .add(farmer);

        // Add text only if available
        if (post.getContent() != null
                && !post.getContent()
                        .trim()
                        .isEmpty()) {

            card.getChildren()
                    .add(content);
        }

        // Add image if available
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

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Community"
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