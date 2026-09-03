package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.ChatController;
import com.pravartak.model.buyer_model.ChatMessage;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class BuyerFarmerChatPage {

    private final Product product;

    private final ChatController chatController;

    private VBox messagesContainer;

    private ScrollPane scrollPane;

    private TextField messageField;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BuyerFarmerChatPage(Product product) {

        this.product = product;

        chatController =
                new ChatController();
    }

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public BorderPane getChatPage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =====================================================
        // NAVBAR
        // =====================================================

        root.setTop(
                new buyerTop()
                        .createBuyerTop("Market")
        );

        // =====================================================
        // HEADER
        // =====================================================

        VBox header =
                createChatHeader();

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
        // MESSAGE INPUT
        // =====================================================

        HBox inputArea =
                createInputArea();

        VBox center =
                new VBox();

        center.getChildren().addAll(
                header,
                scrollPane,
                inputArea
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.setCenter(
                center
        );

        // =====================================================
        // FOOTER
        // =====================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =====================================================
        // LOAD MESSAGES
        // =====================================================

        loadMessages();

        return root;
    }

    // =========================================================
    // CHAT HEADER
    // =========================================================

    private VBox createChatHeader() {

        VBox header =
                new VBox(6);

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
                        "👨‍🌾 "
                                + getFarmerName()
                );

        farmer.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;"
        );

        Label productName =
                new Label(
                        "Product: "
                                + safe(
                                product.getProductName()
                        )
                );

        productName.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        Label location =
                new Label(
                        "📍 "
                                + safe(
                                product.getLocation()
                        )
                );

        location.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:12px;"
        );

        header.getChildren().addAll(
                title,
                farmer,
                productName,
                location
        );

        return header;
    }

    // =========================================================
    // INPUT AREA
    // =========================================================

    private HBox createInputArea() {

        HBox inputArea =
                new HBox(10);

        inputArea.setPadding(
                new Insets(
                        15,
                        25,
                        15,
                        25
                )
        );

        inputArea.setAlignment(
                Pos.CENTER
        );

        inputArea.setStyle(
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

        // Press ENTER to send
        messageField.setOnAction(
                e -> sendMessage()
        );

        Button sendButton =
                new Button(
                        "Send ➤"
                );

        sendButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#081008;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-padding:11 22;" +
                "-fx-cursor:hand;"
        );

        sendButton.setOnAction(
                e -> sendMessage()
        );

        inputArea.getChildren().addAll(
                messageField,
                sendButton
        );

        return inputArea;
    }

    // =========================================================
    // LOAD MESSAGES
    // =========================================================

    private void loadMessages() {

        messagesContainer
                .getChildren()
                .clear();

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        if (buyerUid == null ||
                buyerUid.trim().isEmpty()) {

            showMessage(
                    "Please login again to use chat."
            );

            return;
        }

        try {

            List<ChatMessage> messages =
                    chatController.getMessages(
                            buyerUid,
                            product.getFarmerId()
                    );

            if (messages == null ||
                    messages.isEmpty()) {

                showMessage(
                        "No messages yet. Start a conversation with the farmer."
                );

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

            showMessage(
                    "Unable to load messages."
            );
        }
    }

    // =========================================================
    // MESSAGE BUBBLE
    // =========================================================

    private HBox createMessageBubble(
            ChatMessage message) {

        HBox row =
                new HBox();

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        boolean isBuyer =
                buyerUid != null &&
                buyerUid.equals(
                        message.getSenderId()
                );

        row.setAlignment(
                isBuyer
                        ? Pos.CENTER_RIGHT
                        : Pos.CENTER_LEFT
        );

        VBox bubble =
                new VBox(5);

        bubble.setMaxWidth(
                600
        );

        bubble.setPadding(
                new Insets(12, 16, 12, 16)
        );

        if (isBuyer) {

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
                        isBuyer
                                ? "You"
                                : getFarmerName()
                );

        sender.setStyle(
                "-fx-text-fill:"
                        + (
                        isBuyer
                                ? "#68D34A"
                                : "#64B5F6"
                )
                        + ";" +
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

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        if (buyerUid == null ||
                buyerUid.trim().isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Chat Error",
                    "Buyer account information is unavailable."
            );

            return;
        }

        try {

            boolean sent =
                    chatController.sendMessage(
                            buyerUid,
                            BuyerProfilePage.buyerName,
                            product.getFarmerId(),
                            getFarmerName(),
                            buyerUid,
                            "BUYER",
                            message
                    );

            if (sent) {

                messageField.clear();

                loadMessages();

            } else {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Message Failed",
                        "Unable to send the message."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Chat Error",
                    "Something went wrong while sending the message."
            );
        }
    }

    // =========================================================
    // FARMER NAME
    // =========================================================

    private String getFarmerName() {

        return "Farmer "
                + product.getFarmerId();
    }

    // =========================================================
    // EMPTY / INFO MESSAGE
    // =========================================================

    private void showMessage(
            String text) {

        Label label =
                new Label(text);

        label.setWrapText(true);

        label.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:14px;" +
                "-fx-padding:30;"
        );

        messagesContainer
                .getChildren()
                .add(label);
    }

    // =========================================================
    // SCROLL BOTTOM
    // =========================================================

    private void scrollToBottom() {

        if (scrollPane != null) {

            javafx.application.Platform.runLater(
                    () -> scrollPane.setVvalue(1.0)
            );
        }
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

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "AgroBiz"
        );

        alert.setHeaderText(
                title
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}