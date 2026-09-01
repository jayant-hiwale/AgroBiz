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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class CommuityPage {

    // =====================================================
    // COLORS - AGROBIZ DARK THEME
    // =====================================================

    private static final Color BACKGROUND =
            Color.web("#080C0D");

    private static final Color CARD_BACKGROUND =
            Color.web("#0D1213");

    private static final Color SECONDARY_CARD =
            Color.web("#101617");

    private static final Color GREEN =
            Color.web("#68D34A");

    private static final Color DARK_GREEN =
            Color.web("#14532D");

    private static final Color LIGHT_GREEN =
            Color.web("#B8E8A8");

    private static final Color WHITE =
            Color.web("#FFFFFF");

    private static final Color TEXT =
            Color.web("#F4F7F4");

    private static final Color SECONDARY_TEXT =
            Color.web("#A9B7AC");

    private static final Color MUTED_TEXT =
            Color.web("#777F79");

    private static final Color BORDER =
            Color.web("#26382B");


    // =====================================================
    // VARIABLES
    // =====================================================

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

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                BACKGROUND,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // =================================================
        // NAVBAR
        // =================================================

        root.setTop(
                new NavBar()
                        .createNavbar("Community")
        );


        // =================================================
        // FOOTER
        // =================================================

        root.setBottom(
                new Footer()
                        .createFooter()
        );


        // =================================================
        // MAIN CONTENT
        // =================================================

        VBox mainContent =
                createCommunityContent();


        // =================================================
        // SCROLL PANE
        // =================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color:#080C0D;" +
                "-fx-background:#080C0D;" +
                "-fx-control-inner-background:#080C0D;"
        );


        root.setCenter(
                scrollPane
        );


        communityScene =
                new Scene(
                        root,
                        1200,
                        750
                );


        // =================================================
        // LOAD POSTS
        // =================================================

        loadPosts();


        return communityScene;
    }


    // =====================================================
    // COMMUNITY CONTENT
    // =====================================================

    private VBox createCommunityContent() {

        VBox main =
                new VBox(22);

        main.setPadding(
                new Insets(
                        30,
                        50,
                        45,
                        50
                )
        );

        main.setAlignment(
                Pos.TOP_CENTER
        );

        main.setFillWidth(
                true
        );

        main.setBackground(
                new Background(
                        new BackgroundFill(
                                BACKGROUND,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // =================================================
        // PAGE HEADER
        // =================================================

        VBox header =
                new VBox(5);

        header.setMaxWidth(
                800
        );

        header.setAlignment(
                Pos.CENTER_LEFT
        );


        Label title =
                new Label(
                        "🌾  Farmer Community"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                WHITE
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
                SECONDARY_TEXT
        );

        subtitle.setWrapText(
                true
        );


        header.getChildren()
                .addAll(
                        title,
                        subtitle
                );


        // =================================================
        // CREATE POST CARD
        // =================================================

        VBox createPostCard =
                new VBox(16);

        createPostCard.setMaxWidth(
                800
        );

        createPostCard.setPadding(
                new Insets(24)
        );

        createPostCard.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        createPostCard.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(15),
                                new BorderWidths(1)
                        )
                )
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
                WHITE
        );


        // =================================================
        // CREATE POST SUBTITLE
        // =================================================

        Label createSubtitle =
                new Label(
                        "Share something useful with the farming community."
                );

        createSubtitle.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        createSubtitle.setTextFill(
                MUTED_TEXT
        );


        // =================================================
        // TEXT AREA
        // =================================================

        postTextArea =
                new TextArea();

        postTextArea.setPromptText(
                "What's happening on your farm?"
        );

        postTextArea.setWrapText(
                true
        );

        postTextArea.setPrefRowCount(
                4
        );

        postTextArea.setStyle(
                "-fx-background-color:#101617;" +
                "-fx-control-inner-background:#101617;" +
                "-fx-text-fill:#F4F7F4;" +
                "-fx-prompt-text-fill:#777F79;" +
                "-fx-border-color:#303B33;" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-padding:10;"
        );


        // =================================================
        // IMAGE BUTTON
        // =================================================

        Button uploadButton =
                new Button(
                        "📷  Upload Image"
                );

        uploadButton.setPrefHeight(
                40
        );

        uploadButton.setStyle(
                "-fx-background-color:#16251A;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:#2D5232;" +
                "-fx-border-radius:8;" +
                "-fx-padding:8 14;" +
                "-fx-cursor:hand;"
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
                MUTED_TEXT
        );

        selectedImageLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
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
                "-fx-background-color:#2A1717;" +
                "-fx-text-fill:#E57373;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );

        removeImageButton.setOnAction(
                event -> {

                    selectedImage = null;

                    selectedImageLabel.setText(
                            "No image selected"
                    );

                    selectedImageLabel.setTextFill(
                            MUTED_TEXT
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
                        "✓  Post"
                );

        postButton.setPrefWidth(
                120
        );

        postButton.setPrefHeight(
                42
        );

        postButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
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
                .add(
                        postButton
                );


        // =================================================
        // ADD TO CREATE CARD
        // =================================================

        createPostCard.getChildren()
                .addAll(
                        createTitle,
                        createSubtitle,
                        postTextArea,
                        imageRow,
                        postRow
                );


        // =================================================
        // COMMUNITY POSTS TITLE
        // =================================================

        HBox feedHeader =
                new HBox();

        feedHeader.setMaxWidth(
                800
        );

        feedHeader.setAlignment(
                Pos.CENTER_LEFT
        );


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
                WHITE
        );


        Region feedSpacer =
                new Region();

        HBox.setHgrow(
                feedSpacer,
                Priority.ALWAYS
        );


        Label feedInfo =
                new Label(
                        "Connect • Share • Learn"
                );

        feedInfo.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        feedInfo.setTextFill(
                MUTED_TEXT
        );


        feedHeader.getChildren()
                .addAll(
                        feedTitle,
                        feedSpacer,
                        feedInfo
                );


        // =================================================
        // POSTS CONTAINER
        // =================================================

        postsContainer =
                new VBox(15);

        postsContainer.setMaxWidth(
                800
        );

        postsContainer.setFillWidth(
                true
        );


        // =================================================
        // ADD EVERYTHING
        // =================================================

        main.getChildren()
                .addAll(
                        header,
                        createPostCard,
                        feedHeader,
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

            selectedImage =
                    file;

            selectedImageLabel.setText(
                    "✓  " + file.getName()
            );

            selectedImageLabel.setTextFill(
                    GREEN
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


        // =================================================
        // VALIDATION
        // =================================================

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
             */

            String farmerId =
                    "CURRENT_FARMER_ID";

            String farmerName =
                    "Current Farmer";


            /*
             * Firebase Storage image URL
             *
             * Add Storage upload here when implemented.
             */

            String imageUrl =
                    "";


            // =================================================
            // SAVE POST
            // =================================================

            controller.createPost(
                    farmerId,
                    farmerName,
                    content,
                    imageUrl
            );


            // =================================================
            // CLEAR TEXT
            // =================================================

            postTextArea.clear();


            // =================================================
            // CLEAR IMAGE
            // =================================================

            selectedImage =
                    null;

            selectedImageLabel.setText(
                    "No image selected"
            );

            selectedImageLabel.setTextFill(
                    MUTED_TEXT
            );


            // =================================================
            // REFRESH POSTS
            // =================================================

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


            // =================================================
            // NO POSTS
            // =================================================

            if (posts.isEmpty()) {

                VBox emptyCard =
                        new VBox(8);

                emptyCard.setAlignment(
                        Pos.CENTER
                );

                emptyCard.setPadding(
                        new Insets(35)
                );

                emptyCard.setBackground(
                        new Background(
                                new BackgroundFill(
                                        CARD_BACKGROUND,
                                        new CornerRadii(15),
                                        Insets.EMPTY
                                )
                        )
                );

                emptyCard.setBorder(
                        new Border(
                                new BorderStroke(
                                        BORDER,
                                        BorderStrokeStyle.SOLID,
                                        new CornerRadii(15),
                                        new BorderWidths(1)
                                )
                  )  );


                Label icon =
                        new Label(
                                "🌱"
                        );

                icon.setFont(
                        Font.font(
                                "Arial",
                                35
                        )
                );


                Label empty =
                        new Label(
                                "No posts yet"
                        );

                empty.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                17
                        )
                );

                empty.setTextFill(
                        WHITE
                );


                Label message =
                        new Label(
                                "Be the first farmer to share something with the community!"
                        );

                message.setFont(
                        Font.font(
                                "Arial",
                                12
                        )
                );

                message.setTextFill(
                        MUTED_TEXT
                );

                message.setWrapText(
                        true
                );

                message.setAlignment(
                        Pos.CENTER
                );


                emptyCard.getChildren()
                        .addAll(
                                icon,
                                empty,
                                message
                        );


                postsContainer
                        .getChildren()
                        .add(
                                emptyCard
                        );

                return;
            }


            // =================================================
            // SHOW POSTS
            // =================================================

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


            VBox errorCard =
                    new VBox(10);

            errorCard.setPadding(
                    new Insets(25)
            );

            errorCard.setAlignment(
                    Pos.CENTER
            );

            errorCard.setBackground(
                    new Background(
                            new BackgroundFill(
                                    CARD_BACKGROUND,
                                    new CornerRadii(15),
                                    Insets.EMPTY
                            )
                    )
            );

            errorCard.setBorder(
                    new Border(
                            new BorderStroke(
                                    Color.web("#633333"),
                                    BorderStrokeStyle.SOLID,
                                    new CornerRadii(15),
                                    new BorderWidths(1)
                            )
            ));


            Label errorIcon =
                    new Label(
                            "⚠"
                    );

            errorIcon.setFont(
                    Font.font(
                            "Arial",
                            28
                    )
            );


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
                    Color.web("#E57373")
            );


            errorCard.getChildren()
                    .addAll(
                            errorIcon,
                            error
                    );


            postsContainer
                    .getChildren()
                    .add(
                            errorCard
                    );
        }
    }


    // =====================================================
    // CREATE POST CARD
    // =====================================================

    private VBox createPostCard(
            CommunityPost post) {

        VBox card =
                new VBox(13);

        card.setMaxWidth(
                800
        );

        card.setPadding(
                new Insets(20)
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(15),
                                new BorderWidths(1)
                        )
                )
        );


        // =================================================
        // POST HEADER
        // =================================================

        HBox postHeader =
                new HBox();

        postHeader.setAlignment(
                Pos.CENTER_LEFT
        );


        Label farmer =
                new Label(
                        "👨‍🌾  "
                        + safeText(
                                post.getFarmerName(),
                                "Farmer"
                        )
                );

        farmer.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        farmer.setTextFill(
                WHITE
        );


        Region headerSpacer =
                new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );


        Label communityLabel =
                new Label(
                        "COMMUNITY"
                );

        communityLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9
                )
        );

        communityLabel.setTextFill(
                DARK_GREEN
        );

        communityLabel.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9
                )
        );

        communityLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#D8F0D0"),
                                new CornerRadii(12),
                                Insets.EMPTY
                        )
                )
        );


        postHeader.getChildren()
                .addAll(
                        farmer,
                        headerSpacer,
                        communityLabel
                );


        // =================================================
        // POST CONTENT
        // =================================================

        Label content =
                new Label(
                        safeText(
                                post.getContent(),
                                ""
                        )
                );

        content.setWrapText(
                true
        );

        content.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        15
                )
        );

        content.setTextFill(
                TEXT
        );

        content.setStyle(
                "-fx-text-fill:#F4F7F4;"
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
                        new ImageView(
                                image
                        );

                imageView.setPreserveRatio(
                        true
                );

                imageView.setFitWidth(
                        700
                );


                imageBox
                        .getChildren()
                        .add(
                                imageView
                        );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }


        // =================================================
        // SEPARATOR
        // =================================================

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color:#26382B;"
        );


        // =================================================
        // LIKE COUNT
        // =================================================

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
                        13
                )
        );

        likes.setTextFill(
                SECONDARY_TEXT
        );


        // =================================================
        // LIKE BUTTON
        // =================================================

        Button likeButton =
                new Button(
                        "👍  Like"
                );

        likeButton.setPrefHeight(
                36
        );

        likeButton.setStyle(
                "-fx-background-color:#16251A;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:#2D5232;" +
                "-fx-border-radius:8;" +
                "-fx-padding:7 18;" +
                "-fx-cursor:hand;"
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
                        15,
                        likes,
                        likeButton
                );

        actionRow.setAlignment(
                Pos.CENTER_LEFT
        );


        // =================================================
        // ADD CONTENT
        // =================================================

        card.getChildren()
                .add(
                        postHeader
                );


        // Add text only if available
        if (post.getContent() != null
                && !post.getContent()
                        .trim()
                        .isEmpty()) {

            card.getChildren()
                    .add(
                            content
                    );
        }


        // Add image if available
        if (!imageBox
                .getChildren()
                .isEmpty()) {

            card.getChildren()
                    .add(
                            imageBox
                    );
        }


        card.getChildren()
                .addAll(
                        separator,
                        actionRow
                );


        return card;
    }


    // =====================================================
    // SAFE TEXT
    // =====================================================

    private String safeText(
            String value,
            String defaultValue) {

        if (value == null
                || value.trim().isEmpty()) {

            return defaultValue;
        }

        return value.trim();
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
                "AgroBiz Community"
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