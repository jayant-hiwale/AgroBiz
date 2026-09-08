package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.ChatController;
import com.pravartak.model.buyer_model.ChatMessage;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.login.LoginPage;

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
import javafx.scene.layout.VBox;

import java.util.List;

public class BuyerChatPage {

    private final String buyerUid;
    private final String buyerName;
    private final int farmerId;
    private final String farmerName;

    private final ChatController chatController;

    private VBox messagesContainer;
    private ScrollPane scrollPane;
    private TextField messageField;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BuyerChatPage(
            String buyerUid,
            String buyerName,
            int farmerId,
            String farmerName) {

        this.buyerUid = buyerUid;
        this.buyerName = buyerName;
        this.farmerId = farmerId;
        this.farmerName = farmerName;

        chatController =
                new ChatController();
    }

    // =========================================================
    // PAGE
    // =========================================================

    public BorderPane getChatPage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =====================================================
        // BUYER NAVBAR
        // =====================================================

        root.setTop(
                new buyerTop()
                        .createBuyerTop("Chats")
        );

        // =====================================================
        // HEADER
        // =====================================================

        VBox header =
                createHeader();

        // =====================================================
        // MESSAGES
        // =====================================================

        messagesContainer =
                new VBox(12);

        messagesContainer.setPadding(
                new Insets(20)
        );

        messagesContainer.setAlignment(
                Pos.TOP_CENTER
        );

        scrollPane =
                new ScrollPane(
                        messagesContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        // =====================================================
        // INPUT
        // =====================================================

        HBox input =
                createInputArea();

        VBox center =
                new VBox();

        center.getChildren().addAll(
                header,
                scrollPane,
                input
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.setCenter(
                center
        );

        // =====================================================
        // LOAD MESSAGES
        // =====================================================

        loadMessages();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header =
                new VBox(5);

        header.setPadding(
                new Insets(
                        18,
                        30,
                        18,
                        30
                )
        );

        header.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-width:0 0 1 0;"
        );

        Label title =
                new Label(
                        "💬 Chat with Farmer"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:23px;" +
                "-fx-font-weight:bold;"
        );

        Label farmer =
                new Label(
                        "👨‍🌾 " +
                        safe(farmerName)
                );

        farmer.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        header.getChildren().addAll(
                title,
                farmer
        );

        return header;
    }

    // =========================================================
    // INPUT
    // =========================================================

    private HBox createInputArea() {

        HBox input =
                new HBox(10);

        input.setPadding(
                new Insets(
                        15,
                        25,
                        15,
                        25
                )
        );

        input.setAlignment(
                Pos.CENTER
        );

        input.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-width:1 0 0 0;"
        );

        messageField =
                new TextField();

        messageField.setPromptText(
                "Type your message..."
        );

        messageField.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;" +
                "-fx-padding:12;"
        );

        HBox.setHgrow(
                messageField,
                Priority.ALWAYS
        );

        messageField.setOnAction(
                e -> sendMessage()
        );

        Button send =
                new Button(
                        "Send ➤"
                );

        send.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#081008;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-padding:11 22;" +
                "-fx-cursor:hand;"
        );

        send.setOnAction(
                e -> sendMessage()
        );

        input.getChildren().addAll(
                messageField,
                send
        );

        return input;
    }

    // =========================================================
    // LOAD MESSAGES
    // =========================================================

    private void loadMessages() {

        messagesContainer
                .getChildren()
                .clear();

        try {

            List<ChatMessage> messages =
                    chatController.getMessages(
                            buyerUid,
                            farmerId
                    );

            if (messages == null ||
                    messages.isEmpty()) {

                Label empty =
                        new Label(
                                "No messages yet."
                        );

                empty.setStyle(
                        "-fx-text-fill:#777777;" +
                        "-fx-font-size:14px;"
                );

                messagesContainer
                        .getChildren()
                        .add(empty);

                return;
            }

            for (ChatMessage message :
                    messages) {

                messagesContainer
                        .getChildren()
                        .add(
                                createMessageBubble(
                                        message
                                )
                        );
            }

            scrollToBottom();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // MESSAGE BUBBLE
    // =========================================================

    private HBox createMessageBubble(
            ChatMessage message) {

        HBox row =
                new HBox();

        String loggedBuyerUid =
                LoginPage.getLoggedInFirebaseUid();

        boolean fromBuyer =
                loggedBuyerUid != null &&
                loggedBuyerUid.equals(
                        message.getSenderId()
                );

        row.setAlignment(
                fromBuyer
                        ? Pos.CENTER_RIGHT
                        : Pos.CENTER_LEFT
        );

        VBox bubble =
                new VBox(5);

        bubble.setMaxWidth(
                600
        );

        bubble.setPadding(
                new Insets(
                        12,
                        16,
                        12,
                        16
                )
        );

        if (fromBuyer) {

            bubble.setStyle(
                    "-fx-background-color:#263A29;" +
                    "-fx-background-radius:12 12 2 12;"
            );

        } else {

            bubble.setStyle(
                    "-fx-background-color:#161B22;" +
                    "-fx-border-color:#30363D;" +
                    "-fx-border-radius:12;" +
                    "-fx-background-radius:12;"
            );
        }

        Label sender =
                new Label(
                        fromBuyer
                                ? "You"
                                : safe(farmerName)
                );

        sender.setStyle(
                "-fx-text-fill:" +
                        (
                                fromBuyer
                                        ? "#68D34A"
                                        : "#64B5F6"
                        ) +
                        ";" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
        );

        Label text =
                new Label(
                        safe(
                                message.getMessage()
                        )
                );

        text.setWrapText(true);

        text.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:14px;"
        );

        bubble.getChildren().addAll(
                sender,
                text
        );

        row.getChildren().add(
                bubble
        );

        return row;
    }

    // =========================================================
    // SEND MESSAGE
    // =========================================================

    private void sendMessage() {

        String message =
                messageField
                        .getText()
                        .trim();

        if (message.isEmpty()) {
            return;
        }

        String loggedBuyerUid =
                LoginPage.getLoggedInFirebaseUid();

        if (loggedBuyerUid == null ||
                loggedBuyerUid.trim().isEmpty()) {

            return;
        }

        boolean sent =
                chatController.sendMessage(
                        buyerUid,
                        buyerName,
                        farmerId,
                        farmerName,
                        loggedBuyerUid,
                        "BUYER",
                        message
                );

        if (sent) {

            messageField.clear();

            loadMessages();
        }
    }

    // =========================================================
    // SCROLL
    // =========================================================

    private void scrollToBottom() {

        Platform.runLater(
                () -> {

                    if (scrollPane != null) {

                        scrollPane.setVvalue(
                                1.0
                        );
                    }
                }
        );
    }

    // =========================================================
    // SAFE
    // =========================================================

    private String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not available";
        }

        return value;
    }
}