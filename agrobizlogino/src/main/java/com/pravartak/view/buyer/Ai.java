package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.Controller;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Ai {

    private final Controller controller;

    public Ai() {
        this.controller = new Controller();
    }

    public Scene gatAiScene() {
        BorderPane out = new BorderPane();
        out.setStyle("-fx-background-color: #F4F8F3;");
        out.setTop(new buyerTop().createBuyerTop("AI Advisor"));
        out.setBottom(new Footer().createFooter());

        BorderPane root = new BorderPane();

        // Outer corner padding removed
        root.setPadding(new Insets(0));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #001D14, #003523);" +
                "-fx-border-color: #1C4F37;"
        );

        root.setTop(createHeader());
        root.setCenter(createChatArea());
        root.setBottom(createInputArea(root));

        out.setCenter(root);

        return new Scene(out);
    }

    private VBox createHeader() {
        Label icon = new Label("✦");
        icon.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #69E878;" +
                "-fx-font-weight: bold;"
        );

        Label title = new Label("AI Assistant");
        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        HBox titleRow = new HBox(14, icon, title);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        VBox leftHeader = new VBox(titleRow);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button pastSessions = new Button("↶   Past Sessions");
        pastSessions.setStyle(
                "-fx-background-color: #073E29;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 16;"
        );
        pastSessions.setOnAction(event -> controller.showPastSessions());

        HBox headerRow = new HBox(leftHeader, spacer, pastSessions);
        headerRow.setAlignment(Pos.CENTER_LEFT);

        VBox header = new VBox(headerRow);

        // AI Assistant area padding reduced
        header.setPadding(new Insets(14, 18, 10, 18));

        return header;
    }

    private StackPane createChatArea() {
        VBox chatBox = new VBox(12);
        chatBox.setAlignment(Pos.CENTER);
        chatBox.setPadding(new Insets(20));

        Label plantIcon = new Label("🌱");
        plantIcon.setStyle(
                "-fx-font-size: 62px;" +
                "-fx-background-color: #0B3D27;" +
                "-fx-background-radius: 50%;" +
                "-fx-padding: 24;"
        );

        Label welcome = new Label("Hello! I'm your AI Assistant");
        welcome.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label helpText = new Label(
                "Ask me anything about crops, diseases, livestock,\n"
                        + "soil health, or farming techniques."
        );
        helpText.setAlignment(Pos.CENTER);
        helpText.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: #A8B9B0;"
        );

        VBox welcomeBox = new VBox(16, plantIcon, welcome, helpText);
        welcomeBox.setAlignment(Pos.CENTER);

        chatBox.getChildren().add(welcomeBox);
        controller.setChatBox(chatBox);

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: #001A12;"
        );

        StackPane center = new StackPane(scrollPane);

        // Chat area side padding reduced
        center.setPadding(new Insets(0, 10, 5, 10));

        return center;
    }

    private VBox createInputArea(BorderPane root) {
        Button uploadButton = new Button("▧");
        uploadButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #377653;" +
                "-fx-border-radius: 8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-padding: 7 12;"
        );
        uploadButton.setOnAction(event ->
                controller.uploadImage(root.getScene().getWindow())
        );

        TextField questionField = new TextField();
        questionField.setPromptText("Describe your question or upload an image...");
        questionField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #A0B3A8;" +
                "-fx-font-size: 16px;"
        );
        questionField.setOnAction(event -> controller.sendQuestion());
        controller.setQuestionField(questionField);

        Button sendButton = new Button("➤");
        sendButton.setStyle(
                "-fx-background-color: #63E66D;" +
                "-fx-text-fill: #002D16;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 7 14;"
        );
        sendButton.setOnAction(event -> controller.sendQuestion());

        HBox inputBox = new HBox(12, uploadButton, questionField, sendButton);
        inputBox.setAlignment(Pos.CENTER_LEFT);

        // Describe-question box padding and border reduced
        inputBox.setPadding(new Insets(8, 12, 8, 12));
        HBox.setHgrow(questionField, Priority.ALWAYS);
        inputBox.setStyle(
                "-fx-background-color: #06351F;" +
                "-fx-border-color: #29633F;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;"
        );

        Label statusLabel = new Label();
        statusLabel.setStyle(
                "-fx-text-fill: #98B5A2;" +
                "-fx-font-size: 13px;"
        );
        controller.setStatusLabel(statusLabel);

        Label warning = new Label(
                "🔒  AI Assistant can make mistakes. Please verify important information."
        );
        warning.setStyle(
                "-fx-text-fill: #91A79A;" +
                "-fx-font-size: 13px;"
        );

        VBox bottom = new VBox(7, inputBox, statusLabel, warning);
        bottom.setAlignment(Pos.CENTER);

        // Bottom outer padding reduced
        bottom.setPadding(new Insets(3, 8, 6, 8));

        return bottom;
    }
}