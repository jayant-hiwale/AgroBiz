package com.pravartak.view.farmer;

import com.pravartak.controller.buyercontroller.ChatController;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class FarmerChatsPage {

    private final int farmerId;

    private final ChatController chatController;

    private VBox chatsContainer;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public FarmerChatsPage(int farmerId) {

        this.farmerId = farmerId;

        chatController =
                new ChatController();
    }

    // =========================================================
    // PAGE
    // =========================================================

    public BorderPane getChatsPage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =====================================================
        // NAVBAR
        // =====================================================

        root.setTop(
                new NavBar(
                        farmerId,
                        LoginPage.getLoggedInFirebaseUid()
                ).createNavbar("Chats")
        );

        // =====================================================
        // FOOTER
        // =====================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =====================================================
        // MAIN
        // =====================================================

        VBox main =
                new VBox(18);

        main.setPadding(
                new Insets(
                        30,
                        40,
                        30,
                        40
                )
        );

        Label title =
                new Label(
                        "💬 Farmer Chats"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Chat with buyers interested in your products."
                );

        subtitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:14px;"
        );

        chatsContainer =
                new VBox(14);

        chatsContainer.setPadding(
                new Insets(5)
        );

        ScrollPane scroll =
                new ScrollPane(
                        chatsContainer
                );

        scroll.setFitToWidth(true);

        scroll.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                title,
                subtitle,
                scroll
        );

        root.setCenter(
                main
        );

        // =====================================================
        // LOAD CHATS
        // =====================================================

        loadChats();

        return root;
    }

    // =========================================================
    // LOAD CHATS
    // =========================================================

    private void loadChats() {

        chatsContainer
                .getChildren()
                .clear();

        try {

            List<Map<String, Object>> chats =
                    chatController
                            .getFarmerChats(
                                    farmerId
                            );

            if (chats == null ||
                    chats.isEmpty()) {

                showEmpty(
                        "No buyer chats yet."
                );

                return;
            }

            for (Map<String, Object> chat :
                    chats) {

                chatsContainer
                        .getChildren()
                        .add(
                                createChatCard(
                                        chat
                                )
                        );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showEmpty(
                    "Unable to load chats."
            );
        }
    }

    // =========================================================
    // CHAT CARD
    // =========================================================

    private VBox createChatCard(
            Map<String, Object> chat) {

        VBox card =
                new VBox(10);

        card.setMaxWidth(
                1000
        );

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;"
        );

        // =====================================================
        // BUYER NAME
        // =====================================================

        String buyerName =
                getString(
                        chat.get("buyerName"),
                        "Buyer"
                );

        Label buyer =
                new Label(
                        "👤  " + buyerName
                );

        buyer.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // BUYER UID
        // =====================================================

        String buyerUid =
                getString(
                        chat.get("buyerUid"),
                        ""
                );

        Label uid =
                new Label(
                        "Buyer ID: "
                                + shortUid(buyerUid)
                );

        uid.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:12px;"
        );

        // =====================================================
        // LAST MESSAGE
        // =====================================================

        String lastMessage =
                getString(
                        chat.get("lastMessage"),
                        "No messages yet"
                );

        Label message =
                new Label(
                        lastMessage
                );

        message.setWrapText(true);

        message.setMaxWidth(
                700
        );

        message.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        // =====================================================
        // UPDATED TIME
        // =====================================================

        String timeText =
                "";

        Object updatedAt =
                chat.get("updatedAt");

        if (updatedAt instanceof
                com.google.cloud.Timestamp) {

            com.google.cloud.Timestamp timestamp =
                    (com.google.cloud.Timestamp)
                            updatedAt;

            timeText =
                    timestamp.toString();
        }

        Label time =
                new Label(
                        timeText
                );

        time.setStyle(
                "-fx-text-fill:#666666;" +
                "-fx-font-size:11px;"
        );

        // =====================================================
        // OPEN BUTTON
        // =====================================================

        Button open =
                new Button(
                        "Open Chat →"
                );

        open.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#081008;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-padding:9 18;" +
                "-fx-cursor:hand;"
        );

        int chatFarmerId =
                getInt(
                        chat.get("farmerId")
                );

        open.setOnAction(e -> {

            openChat(
                    buyerUid,
                    buyerName,
                    chatFarmerId
            );
        });

        HBox bottom =
                new HBox(15);

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                time,
                Priority.ALWAYS
        );

        bottom.getChildren().addAll(
                time,
                open
        );

        card.getChildren().addAll(
                buyer,
                uid,
                message,
                bottom
        );

        return card;
    }

    // =========================================================
    // OPEN CHAT
    // =========================================================

    private void openChat(
            String buyerUid,
            String buyerName,
            int farmerId) {

        try {

            FarmerChatPage chatPage =
                    new FarmerChatPage(
                            buyerUid,
                            buyerName,
                            farmerId
                    );

            BorderPane page =
                    chatPage.getChatPage();

            Scene scene =
                    new Scene(
                            page,
                            1400,
                            850
                    );

            LoginPage.mainStage
                    .setScene(scene);

            LoginPage.mainStage.show();

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Unable to open chat."
            );
        }
    }

    // =========================================================
    // EMPTY
    // =========================================================

    private void showEmpty(
            String text) {

        VBox box =
                new VBox(10);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(80)
        );

        Label icon =
                new Label("💬");

        icon.setStyle(
                "-fx-font-size:45px;"
        );

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:16px;"
        );

        box.getChildren().addAll(
                icon,
                label
        );

        chatsContainer
                .getChildren()
                .add(box);
    }

    // =========================================================
    // STRING
    // =========================================================

    private String getString(
            Object value,
            String defaultValue) {

        if (value == null) {
            return defaultValue;
        }

        String text =
                String.valueOf(value);

        if (text.trim().isEmpty()) {
            return defaultValue;
        }

        return text;
    }

    // =========================================================
    // INT
    // =========================================================

    private int getInt(
            Object value) {

        if (value instanceof Number) {

            return ((Number) value)
                    .intValue();
        }

        try {

            return Integer.parseInt(
                    String.valueOf(value)
            );

        } catch (Exception e) {

            return 0;
        }
    }

    // =========================================================
    // SHORT UID
    // =========================================================

    private String shortUid(
            String uid) {

        if (uid == null ||
                uid.isEmpty()) {

            return "Not available";
        }

        if (uid.length() <= 12) {
            return uid;
        }

        return uid.substring(
                0,
                12
        ) + "...";
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "AgroBiz"
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