package com.pravartak.view.buyer;

import com.pravartak.services.GroqService;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.application.Platform;
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
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ai {

    // =========================================================
    // SERVICES
    // =========================================================

    private final GroqService groqService;

    // One background thread so the JavaFX UI does not freeze
    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    // =========================================================
    // UI REFERENCES
    // =========================================================

    private VBox chatBox;
    private ScrollPane chatScrollPane;
    private TextField questionField;
    private Label statusLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Ai() {
        groqService = new GroqService();
    }

    // =========================================================
    // MAIN SCENE
    // =========================================================

    public Scene gatAiScene() {

        BorderPane out = new BorderPane();

        out.setStyle(
                "-fx-background-color: #F4F8F3;"
        );

        out.setTop(
                new buyerTop().createBuyerTop(
                        "AI Advisor"
                )
        );

        out.setBottom(
                new Footer().createFooter()
        );

        BorderPane root = new BorderPane();

        root.setPadding(
                new Insets(0)
        );

        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #001D14, #003523);" +
                "-fx-border-color: #1C4F37;"
        );

        root.setTop(
                createHeader()
        );

        root.setCenter(
                createChatArea()
        );

        root.setBottom(
                createInputArea(root)
        );

        out.setCenter(root);

        return new Scene(out);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        Label icon = new Label("✦");

        icon.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #69E878;" +
                "-fx-font-weight: bold;"
        );

        Label title =
                new Label("AI Buyer Advisor");

        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        HBox titleRow =
                new HBox(
                        14,
                        icon,
                        title
                );

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox leftHeader =
                new VBox(titleRow);

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button pastSessions =
                new Button(
                        "↶   Past Sessions"
                );

        pastSessions.setStyle(
                "-fx-background-color: #073E29;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 16;"
        );

        // =====================================================
        // PAST SESSIONS
        // =====================================================

        pastSessions.setOnAction(
                event -> showPastSessions()
        );

        HBox headerRow =
                new HBox(
                        leftHeader,
                        spacer,
                        pastSessions
                );

        headerRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox header =
                new VBox(headerRow);

        header.setPadding(
                new Insets(
                        14,
                        18,
                        10,
                        18
                )
        );

        return header;
    }

    // =========================================================
    // CHAT AREA
    // =========================================================

    private StackPane createChatArea() {

        chatBox =
                new VBox(12);

        chatBox.setAlignment(
                Pos.TOP_CENTER
        );

        chatBox.setPadding(
                new Insets(20)
        );

        // =====================================================
        // WELCOME ICON
        // =====================================================

        Label plantIcon =
                new Label("🛒");

        plantIcon.setStyle(
                "-fx-font-size: 62px;" +
                "-fx-background-color: #0B3D27;" +
                "-fx-background-radius: 50%;" +
                "-fx-padding: 24;"
        );

        // =====================================================
        // WELCOME TITLE
        // =====================================================

        Label welcome =
                new Label(
                        "Hello! I'm your AI Buyer Advisor"
                );

        welcome.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        // =====================================================
        // WELCOME DESCRIPTION
        // =====================================================

        Label helpText =
                new Label(
                        "Ask about agricultural markets, crop prices,\n"
                        + "produce quality, procurement, storage, or logistics."
                );

        helpText.setAlignment(
                Pos.CENTER
        );

        helpText.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: #A8B9B0;"
        );

        VBox welcomeBox =
                new VBox(
                        16,
                        plantIcon,
                        welcome,
                        helpText
                );

        welcomeBox.setAlignment(
                Pos.CENTER
        );

        chatBox.getChildren().add(
                welcomeBox
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        chatScrollPane =
                new ScrollPane(
                        chatBox
                );

        chatScrollPane.setFitToWidth(
                true
        );

        chatScrollPane.setFitToHeight(
                false
        );

        chatScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        chatScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        chatScrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: #0D1117;"
        );

        StackPane center =
                new StackPane(
                        chatScrollPane
                );

        center.setPadding(
                new Insets(
                        0,
                        10,
                        5,
                        10
                )
        );

        return center;
    }

    // =========================================================
    // INPUT AREA
    // =========================================================

    private VBox createInputArea(
            BorderPane root) {

        // =====================================================
        // UPLOAD BUTTON
        // =====================================================

        Button uploadButton =
                new Button("▧");

        uploadButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #377653;" +
                "-fx-border-radius: 8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-padding: 7 12;"
        );

        uploadButton.setOnAction(
                event ->
                        uploadImage(
                                root.getScene().getWindow()
                        )
        );

        // =====================================================
        // QUESTION FIELD
        // =====================================================

        questionField =
                new TextField();

        questionField.setPromptText(
                "Ask about agricultural markets, prices, or buying..."
        );

        questionField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #A0B3A8;" +
                "-fx-font-size: 16px;"
        );

        // Press ENTER
        questionField.setOnAction(
                event ->
                        sendQuestion()
        );

        // =====================================================
        // SEND BUTTON
        // =====================================================

        Button sendButton =
                new Button("➤");

        sendButton.setStyle(
                "-fx-background-color: #63E66D;" +
                "-fx-text-fill: #002D16;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 7 14;"
        );

        sendButton.setOnAction(
                event ->
                        sendQuestion()
        );

        // =====================================================
        // INPUT BOX
        // =====================================================

        HBox inputBox =
                new HBox(
                        12,
                        uploadButton,
                        questionField,
                        sendButton
                );

        inputBox.setAlignment(
                Pos.CENTER_LEFT
        );

        inputBox.setPadding(
                new Insets(
                        8,
                        12,
                        8,
                        12
                )
        );

        HBox.setHgrow(
                questionField,
                Priority.ALWAYS
        );

        inputBox.setStyle(
                "-fx-background-color: #06351F;" +
                "-fx-border-color: #29633F;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;"
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusLabel =
                new Label();

        statusLabel.setStyle(
                "-fx-text-fill: #98B5A2;" +
                "-fx-font-size: 13px;"
        );

        // =====================================================
        // WARNING
        // =====================================================

        Label warning =
                new Label(
                        "🔒 AI Assistant can make mistakes. "
                        + "Please verify important market information."
                );

        warning.setStyle(
                "-fx-text-fill: #91A79A;" +
                "-fx-font-size: 13px;"
        );

        // =====================================================
        // BOTTOM
        // =====================================================

        VBox bottom =
                new VBox(
                        7,
                        inputBox,
                        statusLabel,
                        warning
                );

        bottom.setAlignment(
                Pos.CENTER
        );

        bottom.setPadding(
                new Insets(
                        3,
                        8,
                        6,
                        8
                )
        );

        return bottom;
    }

    // =========================================================
    // SEND QUESTION
    // =========================================================

    private void sendQuestion() {

        if (questionField == null) {
            return;
        }

        String question =
                questionField
                        .getText()
                        .trim();

        // =====================================================
        // EMPTY QUESTION
        // =====================================================

        if (question.isEmpty()) {

            setStatus(
                    "Please type a question first."
            );

            return;
        }

        // =====================================================
        // ADD USER MESSAGE
        // =====================================================

        addUserMessage(question);

        // Clear input immediately
        questionField.clear();

        // =====================================================
        // STATUS
        // =====================================================

        setStatus(
                "AI is preparing your answer..."
        );

        // =====================================================
        // DISABLE INPUT WHILE REQUEST IS RUNNING
        // =====================================================

        questionField.setDisable(true);

        // =====================================================
        // CALL GROQ IN BACKGROUND
        // =====================================================

        executor.submit(
                () -> {

                    try {

                        String answer =
                                groqService.askBuyerQuestion(
                                        question
                                );

                        Platform.runLater(
                                () -> {

                                    addAIMessage(answer);

                                    setStatus(
                                            "Response generated."
                                    );

                                    questionField.setDisable(
                                            false
                                    );

                                    questionField.requestFocus();

                                    scrollToBottom();
                                }
                        );

                    } catch (Exception e) {

                        e.printStackTrace();

                        Platform.runLater(
                                () -> {

                                    addAIMessage(
                                            "I couldn't generate a response right now.\n\n"
                                            + "Please try again in a moment."
                                    );

                                    setStatus(
                                            "AI request failed: "
                                            + getSafeErrorMessage(e)
                                    );

                                    questionField.setDisable(
                                            false
                                    );

                                    questionField.requestFocus();

                                    scrollToBottom();
                                }
                        );
                    }
                }
        );
    }

    // =========================================================
    // USER MESSAGE
    // =========================================================

    private void addUserMessage(
            String question) {

        Label userMessage =
                new Label(
                        "You\n\n" + question
                );

        userMessage.setWrapText(
                true
        );

        userMessage.setMaxWidth(
                650
        );

        userMessage.setStyle(
                "-fx-background-color: #125C31;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12;" +
                "-fx-font-size: 15px;"
        );

        HBox container =
                new HBox(
                        userMessage
                );

        container.setAlignment(
                Pos.CENTER_RIGHT
        );

        container.setMaxWidth(
                Double.MAX_VALUE
        );

        chatBox.getChildren().add(
                container
        );

        scrollToBottom();
    }

    // =========================================================
    // AI MESSAGE
    // =========================================================

    private void addAIMessage(
            String answer) {

        if (answer == null ||
                answer.isBlank()) {

            answer =
                    "I couldn't generate a useful answer for that question.";
        }

        Label aiMessage =
                new Label(
                        "AI Buyer Advisor\n\n"
                        + answer
                );

        aiMessage.setWrapText(
                true
        );

        aiMessage.setMaxWidth(
                700
        );

        aiMessage.setStyle(
                "-fx-background-color: #163C29;" +
                "-fx-text-fill: #E8F5E9;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 14;" +
                "-fx-font-size: 15px;"
        );

        HBox container =
                new HBox(
                        aiMessage
                );

        container.setAlignment(
                Pos.CENTER_LEFT
        );

        container.setMaxWidth(
                Double.MAX_VALUE
        );

        chatBox.getChildren().add(
                container
        );

        scrollToBottom();
    }

    // =========================================================
    // AUTOMATIC SCROLL
    // =========================================================

    private void scrollToBottom() {

        if (chatScrollPane == null) {
            return;
        }

        Platform.runLater(
                () -> {

                    chatScrollPane.layout();

                    chatScrollPane.setVvalue(
                            1.0
                    );
                }
        );
    }

    // =========================================================
    // PAST SESSIONS
    // =========================================================

    private void showPastSessions() {

        // For now we keep the existing project behaviour
        // without requiring a separate controller.

        addAIMessage(
                "Past Sessions\n\n"
                + "No saved buyer AI sessions are available yet."
        );

        setStatus(
                "Past Sessions opened."
        );

        scrollToBottom();
    }

    // =========================================================
    // IMAGE UPLOAD
    // =========================================================

    private void uploadImage(
            Window owner) {

        FileChooser chooser =
                new FileChooser();

        chooser.setTitle(
                "Select Crop or Produce Image"
        );

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files",
                        "*.png",
                        "*.jpg",
                        "*.jpeg",
                        "*.webp"
                )
        );

        File selectedFile =
                chooser.showOpenDialog(
                        owner
                );

        if (selectedFile == null) {

            return;
        }

        Path imagePath =
                selectedFile.toPath();

        setStatus(
                "Selected image: "
                + selectedFile.getName()
        );

        // =====================================================
        // IMAGE QUESTION
        // =====================================================

        String question =
                "I have uploaded this agricultural produce image. "
                + "As a buyer, help me understand the visible "
                + "quality, condition, possible defects, grading "
                + "considerations, and buying-related points. "
                + "Do not make a certain diagnosis from the image alone.";

        addUserMessage(
                "Image uploaded: "
                + selectedFile.getName()
        );

        setStatus(
                "Analyzing uploaded image..."
        );

        questionField.setDisable(
                true
        );

        // =====================================================
        // SEND IMAGE TO GROQ
        // =====================================================

        executor.submit(
                () -> {

                    try {

                        String answer =
                                groqService.askBuyerQuestionWithImage(
                                        question,
                                        imagePath
                                );

                        Platform.runLater(
                                () -> {

                                    addAIMessage(
                                            answer
                                    );

                                    setStatus(
                                            "Image analysis completed."
                                    );

                                    questionField.setDisable(
                                            false
                                    );

                                    questionField.requestFocus();

                                    scrollToBottom();
                                }
                        );

                    } catch (Exception e) {

                        e.printStackTrace();

                        Platform.runLater(
                                () -> {

                                    addAIMessage(
                                            "I couldn't analyze the image right now."
                                    );

                                    setStatus(
                                            "Image analysis failed: "
                                            + getSafeErrorMessage(e)
                                    );

                                    questionField.setDisable(
                                            false
                                    );

                                    questionField.requestFocus();

                                    scrollToBottom();
                                }
                        );
                    }
                }
        );
    }

    // =========================================================
    // STATUS
    // =========================================================

    private void setStatus(
            String message) {

        Platform.runLater(
                () -> {

                    if (statusLabel != null) {

                        statusLabel.setText(
                                message
                        );
                    }
                }
        );
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private String getSafeErrorMessage(
            Exception e) {

        if (e == null ||
                e.getMessage() == null ||
                e.getMessage().isBlank()) {

            return "Please try again.";
        }

        String message =
                e.getMessage();

        // Avoid dumping a huge API response into the UI
        if (message.length() > 180) {

            message =
                    message.substring(
                            0,
                            180
                    )
                    + "...";
        }

        return message;
    }
}