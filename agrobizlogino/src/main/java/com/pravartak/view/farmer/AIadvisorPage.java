package com.pravartak.view.farmer;

import java.io.File;

import com.pravartak.services.GroqService;
import com.pravartak.view.farmer.common.NavBar;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class AIAdvisorPage {

    // ============================================================
    // EXISTING COLORS - NOT CHANGED
    // ============================================================

    private static final Color BG =
            Color.rgb(3, 18, 14);

    private static final Color CARD =
            Color.rgb(7, 39, 30);

    private static final Color DARK_GREEN =
            Color.rgb(10, 55, 40);

    private static final Color GREEN =
            Color.rgb(45, 190, 75);

    private static final Color LIGHT_GREEN =
            Color.rgb(20, 65, 45);

    private static final Color DARK_TEXT =
            Color.rgb(236, 240, 225);

    private static final Color GREY =
            Color.rgb(150, 175, 160);

    private static final Color BORDER =
            Color.rgb(88, 243, 186);


    // ============================================================
    // IMAGE
    // ============================================================

    private static File selectedImage;


    // ============================================================
    // GROQ SERVICE
    // ============================================================

    private static final GroqService groqService =
            new GroqService();


    // ============================================================
    // CHAT COMPONENTS
    // ============================================================

    private static VBox chatContainer;

    private static ScrollPane chatScrollPane;

    private static TextField questionField;

    private static Button sendButton;

    private static Button attachButton;

    private static HBox attachmentArea;

    private static Label attachmentLabel;


    // ============================================================
    // CREATE SCENE
    // ============================================================

    public static Scene getAIAdvisorScene() {

        BorderPane root =
                new BorderPane();

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                BG,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // Existing navbar
        NavBar navBar =
                new NavBar();

        root.setTop(
                navBar.createNavbar(
                        "AI Advisor"
                )
        );


        root.setCenter(
                createContent()
        );


        return new Scene(
                root,
                1368,
                768
        );
    }


    // ============================================================
    // MAIN CONTENT
    // ============================================================

    private static VBox createContent() {

        VBox content =
                new VBox(15);

        content.setPadding(
                new Insets(
                        20,
                        30,
                        20,
                        30
                )
        );


        // ========================================================
        // HEADER
        // ========================================================

        VBox header =
                new VBox(5);

        Label heading =
                new Label(
                        "🌱 Your Personal AI Farming Advisor"
                );

        heading.setTextFill(
                Color.WHITE
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );


        Label description =
                new Label(
                        "Ask questions about crops, irrigation, soil, fertilizers, diseases and farming decisions."
                );

        description.setTextFill(
                Color.rgb(
                        220,
                        235,
                        220
                )
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        description.setWrapText(
                true
        );


        header.getChildren().addAll(
                heading,
                description
        );


        // ========================================================
        // CHAT CONTAINER
        // ========================================================

        chatContainer =
                new VBox(18);

        chatContainer.setPadding(
                new Insets(20)
        );

        chatContainer.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD,
                                new CornerRadii(14),
                                Insets.EMPTY
                        )
                )
        );

        chatContainer.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(14),
                                new BorderWidths(1)
                        )
                )
        );


        // Welcome message
        addWelcomeMessage();


        // ========================================================
        // SCROLL PANE
        // ========================================================

        chatScrollPane =
                new ScrollPane(
                        chatContainer
                );

        chatScrollPane.setFitToWidth(
                true
        );

        chatScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        chatScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        chatScrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );

        VBox.setVgrow(
                chatScrollPane,
                Priority.ALWAYS
        );


        // ========================================================
        // INPUT AREA
        // ========================================================

        VBox inputArea =
                createInputArea();


        content.getChildren().addAll(
                header,
                chatScrollPane,
                inputArea
        );


        return content;
    }


    // ============================================================
    // WELCOME MESSAGE
    // ============================================================

    private static void addWelcomeMessage() {

        VBox message =
                new VBox(6);

        message.setAlignment(
                Pos.TOP_LEFT
        );


        Label aiName =
                new Label(
                        "🌱 AgroBiz AI"
                );

        aiName.setTextFill(
                GREEN
        );

        aiName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        Label welcome =
                new Label(
                        "Hello Farmer! 👋\n\n"
                        + "I am your AI Farming Advisor. "
                        + "Ask me anything about crops, irrigation, soil, fertilizers, "
                        + "pests, diseases or other farming decisions."
                );

        welcome.setTextFill(
                DARK_TEXT
        );

        welcome.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        welcome.setWrapText(
                true
        );

        welcome.setMaxWidth(
                900
        );


        message.getChildren().addAll(
                aiName,
                welcome
        );


        chatContainer.getChildren().add(
                message
        );
    }


    // ============================================================
    // INPUT AREA
    // ============================================================

    private static VBox createInputArea() {

        VBox inputArea =
                new VBox(8);


        // ========================================================
        // ATTACHMENT AREA
        // ========================================================

        attachmentArea =
                new HBox(8);

        attachmentArea.setAlignment(
                Pos.CENTER_LEFT
        );

        attachmentArea.setVisible(
                false
        );

        attachmentArea.setManaged(
                false
        );


        attachmentLabel =
                new Label(
                        "📎 Image attached"
                );

        attachmentLabel.setTextFill(
                GREY
        );

        attachmentLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );


        Button removeButton =
                new Button(
                        "Remove"
                );

        styleRemoveButton(
                removeButton
        );


        removeButton.setOnAction(
                event -> removeSelectedImage()
        );


        attachmentArea.getChildren().addAll(
                attachmentLabel,
                removeButton
        );


        // ========================================================
        // CHAT INPUT BAR
        // ========================================================

        HBox inputBar =
                new HBox(10);

        inputBar.setAlignment(
                Pos.CENTER_LEFT
        );

        inputBar.setPadding(
                new Insets(
                        8,
                        10,
                        8,
                        12
                )
        );

        inputBar.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD,
                                new CornerRadii(25),
                                Insets.EMPTY
                        )
                )
        );

        inputBar.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(25),
                                new BorderWidths(1)
                        )
                )
        );


        // ========================================================
        // ATTACH BUTTON
        // ========================================================

        attachButton =
                new Button(
                        "📎"
                );

        styleAttachButton(
                attachButton
        );

        attachButton.setOnAction(
                event -> chooseImage()
        );


        // ========================================================
        // TEXT FIELD
        // ========================================================

        questionField =
                new TextField();

        questionField.setPromptText(
                "Ask your farming question..."
        );

        questionField.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        questionField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #e1f0e4;" +
                "-fx-prompt-text-fill: #96afa0;" +
                "-fx-border-color: transparent;" +
                "-fx-background-insets: 0;" +
                "-fx-padding: 8 5 8 5;"
        );

        HBox.setHgrow(
                questionField,
                Priority.ALWAYS
        );


        // ========================================================
        // SEND BUTTON
        // ========================================================

        sendButton =
                new Button(
                        "➤"
                );

        styleSendButton(
                sendButton
        );


        sendButton.setOnAction(
                event -> sendQuestion()
        );


        // Press Enter to send
        questionField.setOnAction(
                event -> sendQuestion()
        );


        inputBar.getChildren().addAll(
                attachButton,
                questionField,
                sendButton
        );


        inputArea.getChildren().addAll(
                attachmentArea,
                inputBar
        );


        return inputArea;
    }


    // ============================================================
    // CHOOSE IMAGE
    // ============================================================

    private static void chooseImage() {

        FileChooser chooser =
                new FileChooser();

        chooser.setTitle(
                "Select Farm Image"
        );

        chooser.getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "Image Files",
                                "*.png",
                                "*.jpg",
                                "*.jpeg"
                        )
                );


        File file =
                chooser.showOpenDialog(
                        null
                );


        if (file == null) {
            return;
        }


        selectedImage =
                file;


        attachmentLabel.setText(
                "📎 " + file.getName()
        );


        attachmentArea.setVisible(
                true
        );

        attachmentArea.setManaged(
                true
        );
    }


    // ============================================================
    // REMOVE IMAGE
    // ============================================================

    private static void removeSelectedImage() {

        selectedImage =
                null;


        attachmentArea.setVisible(
                false
        );

        attachmentArea.setManaged(
                false
        );
    }


    // ============================================================
    // SEND QUESTION
    // ============================================================

    private static void sendQuestion() {

        String question =
                questionField
                        .getText()
                        .trim();


        if (question.isEmpty()) {
            return;
        }


        // Save image before clearing
        File imageToSend =
                selectedImage;


        // Add user question to conversation
        addUserMessage(
                question,
                imageToSend
        );


        // Clear input
        questionField.clear();


        // Disable controls while AI responds
        questionField.setDisable(
                true
        );

        sendButton.setDisable(
                true
        );

        attachButton.setDisable(
                true
        );


        // Add temporary thinking message
        VBox thinkingMessage =
                createThinkingMessage();


        chatContainer.getChildren().add(
                thinkingMessage
        );


        scrollToBottom();


        // ========================================================
        // GROQ TASK
        // ========================================================

        Task<String> task =
                new Task<String>() {

                    @Override
                    protected String call()
                            throws Exception {

                        if (imageToSend != null) {

                            return groqService
                                    .askQuestionWithImage(
                                            question,
                                            imageToSend.toPath()
                                    );

                        } else {

                            return groqService
                                    .askQuestion(
                                            question
                                    );
                        }
                    }
                };


        // ========================================================
        // SUCCESS
        // ========================================================

        task.setOnSucceeded(
                event -> {

                    String response =
                            task.getValue();


                    Platform.runLater(
                            () -> {

                                chatContainer
                                        .getChildren()
                                        .remove(
                                                thinkingMessage
                                        );


                                addAIMessage(
                                        response
                                );


                                questionField
                                        .setDisable(
                                                false
                                        );

                                sendButton
                                        .setDisable(
                                                false
                                        );

                                attachButton
                                        .setDisable(
                                                false
                                        );


                                // Remove selected image
                                removeSelectedImage();


                                questionField.requestFocus();


                                scrollToBottom();
                            }
                    );
                }
        );


        // ========================================================
        // ERROR
        // ========================================================

        task.setOnFailed(
                event -> {

                    Throwable error =
                            task.getException();


                    Platform.runLater(
                            () -> {

                                chatContainer
                                        .getChildren()
                                        .remove(
                                                thinkingMessage
                                        );


                                addAIMessage(
                                        "Sorry, I could not process your request.\n\n"
                                        + getErrorMessage(error)
                                );


                                questionField
                                        .setDisable(
                                                false
                                        );

                                sendButton
                                        .setDisable(
                                                false
                                        );

                                attachButton
                                        .setDisable(
                                                false
                                        );


                                questionField.requestFocus();


                                scrollToBottom();
                            }
                    );
                }
        );


        Thread thread =
                new Thread(
                        task
                );

        thread.setDaemon(
                true
        );

        thread.start();
    }


    // ============================================================
    // USER MESSAGE
    // ============================================================

    private static void addUserMessage(
            String question,
            File image
    ) {

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_RIGHT
        );


        VBox message =
                new VBox(6);

        message.setAlignment(
                Pos.TOP_RIGHT
        );

        message.setMaxWidth(
                800
        );


        Label userName =
                new Label(
                        "You"
                );

        userName.setTextFill(
                GREY
        );

        userName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );


        Label questionLabel =
                new Label(
                        question
                );

        questionLabel.setTextFill(
                Color.WHITE
        );

        questionLabel.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        questionLabel.setWrapText(
                true
        );

        questionLabel.setMaxWidth(
                760
        );

        questionLabel.setPadding(
                new Insets(
                        12,
                        16,
                        12,
                        16
                )
        );

        questionLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(
                                        16,
                                        16,
                                        4,
                                        16,
                                        false
                                ),
                                Insets.EMPTY
                        )
                )
        );


        message.getChildren().addAll(
                userName,
                questionLabel
        );


        // Show image in conversation
        if (image != null &&
                image.exists()) {

            try {

                Image farmImage =
                        new Image(
                                image.toURI()
                                        .toString()
                        );


                ImageView imageView =
                        new ImageView(
                                farmImage
                        );

                imageView.setFitWidth(
                        220
                );

                imageView.setFitHeight(
                        150
                );

                imageView.setPreserveRatio(
                        true
                );

                imageView.setSmooth(
                        true
                );


                HBox imageBox =
                        new HBox(
                                imageView
                        );

                imageBox.setAlignment(
                        Pos.CENTER_RIGHT
                );


                message.getChildren().add(
                        imageBox
                );

            } catch (Exception ignored) {
                // Ignore image preview error
            }
        }


        row.getChildren().add(
                message
        );


        chatContainer.getChildren().add(
                row
        );


        scrollToBottom();
    }


    // ============================================================
    // AI MESSAGE
    // ============================================================

    private static void addAIMessage(
            String response
    ) {

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox message =
                new VBox(6);

        message.setAlignment(
                Pos.TOP_LEFT
        );

        message.setMaxWidth(
                900
        );


        Label aiName =
                new Label(
                        "🌱 AgroBiz AI"
                );

        aiName.setTextFill(
                GREEN
        );

        aiName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        Label responseLabel =
                new Label(
                        response
                );

        responseLabel.setTextFill(
                DARK_TEXT
        );

        responseLabel.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        responseLabel.setWrapText(
                true
        );

        responseLabel.setMaxWidth(
                850
        );

        responseLabel.setPadding(
                new Insets(
                        12,
                        16,
                        12,
                        16
                )
        );


        /*
         * Same existing CARD color.
         */
        responseLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD,
                                new CornerRadii(
                                        16,
                                        16,
                                        16,
                                        4,
                                        false
                                ),
                                Insets.EMPTY
                        )
                )
        );


        message.getChildren().addAll(
                aiName,
                responseLabel
        );


        row.getChildren().add(
                message
        );


        chatContainer.getChildren().add(
                row
        );


        scrollToBottom();
    }


    // ============================================================
    // THINKING MESSAGE
    // ============================================================

    private static VBox createThinkingMessage() {

        VBox message =
                new VBox(6);

        message.setAlignment(
                Pos.TOP_LEFT
        );

        message.setMaxWidth(
                900
        );


        Label aiName =
                new Label(
                        "🌱 AgroBiz AI"
                );

        aiName.setTextFill(
                GREEN
        );

        aiName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        Label thinking =
                new Label(
                        "Thinking..."
                );

        thinking.setTextFill(
                GREY
        );

        thinking.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        thinking.setPadding(
                new Insets(
                        12,
                        16,
                        12,
                        16
                )
        );


        message.getChildren().addAll(
                aiName,
                thinking
        );


        return message;
    }


    // ============================================================
    // SCROLL TO BOTTOM
    // ============================================================

    private static void scrollToBottom() {

        Platform.runLater(
                () -> {

                    if (chatScrollPane != null) {

                        chatScrollPane.setVvalue(
                                1.0
                        );
                    }
                }
        );
    }


    // ============================================================
    // ERROR MESSAGE
    // ============================================================

    private static String getErrorMessage(
            Throwable error
    ) {

        if (error == null) {

            return "Unknown error occurred.";
        }


        String message =
                error.getMessage();


        if (message == null ||
                message.isBlank()) {

            return error.toString();
        }


        return message;
    }


    // ============================================================
    // ATTACH BUTTON STYLE
    // ============================================================

    private static void styleAttachButton(
            Button button
    ) {

        button.setPrefSize(
                42,
                42
        );

        button.setTextFill(
                DARK_TEXT
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                LIGHT_GREEN,
                                new CornerRadii(20),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(20),
                                new BorderWidths(1)
                        )
                )
        );
    }


    // ============================================================
    // SEND BUTTON STYLE
    // ============================================================

    private static void styleSendButton(
            Button button
    ) {

        button.setPrefSize(
                44,
                44
        );

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(22),
                                Insets.EMPTY
                        )
                )
        );
    }


    // ============================================================
    // REMOVE BUTTON STYLE
    // ============================================================

    private static void styleRemoveButton(
            Button button
    ) {

        button.setPrefHeight(
                30
        );

        button.setTextFill(
                Color.rgb(
                        230,
                        120,
                        120
                )
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        55,
                                        25,
                                        25
                                ),
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        100,
                                        50,
                                        50
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(15),
                                new BorderWidths(1)
                        )
                )
        );
    }
}