package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.ChatController;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
//import com.pravartak.view.farmer.FarmerChatPage;
import com.pravartak.view.buyer.BuyerChatPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class BuyerChatsPage {

    private final ChatController chatController;

    public BuyerChatsPage() {

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
                new buyerTop()
                        .createBuyerTop("Chats")
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
                new VBox(20);

        main.setPadding(
                new Insets(
                        30,
                        45,
                        30,
                        45
                )
        );

        main.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "💬 My Chats"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle =
                new Label(
                        "Chat with farmers about products you're interested in."
                );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        // =====================================================
        // CHAT LIST
        // =====================================================

        VBox chatList =
                new VBox(15);

        chatList.setPadding(
                new Insets(5)
        );

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        List<Map<String, Object>> chats =
                chatController.getBuyerChats(
                        buyerUid
                );

        if (chats == null ||
                chats.isEmpty()) {

            Label empty =
                    new Label(
                            "💬 No chats yet.\n\n" +
                            "Open a product and start chatting with a farmer."
                    );

            empty.setStyle(
                    "-fx-text-fill:#888888;" +
                    "-fx-font-size:15px;"
            );

            empty.setPadding(
                    new Insets(30)
            );

            chatList.getChildren()
                    .add(empty);

        } else {

            for (Map<String, Object> chat :
                    chats) {

                chatList.getChildren()
                        .add(
                                createChatCard(chat)
                        );
            }
        }

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll =
                new ScrollPane(
                        chatList
                );

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        main.getChildren()
                .addAll(
                        heading,
                        scroll
                );

        root.setCenter(main);

        return root;
    }

    // =========================================================
    // CHAT CARD
    // =========================================================

//     private VBox createChatCard(
//             Map<String, Object> chat) {

//         VBox card =
//                 new VBox(10);

//         card.setPadding(
//                 new Insets(20)
//         );

//         card.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         card.setStyle(
//                 "-fx-background-color:#101516;" +
//                 "-fx-background-radius:12;" +
//                 "-fx-border-color:#242B2C;" +
//                 "-fx-border-radius:12;"
//         );

//         // =====================================================
//         // FARMER NAME
//         // =====================================================
// String farmerName =
//         getString(
//                 chat.get("farmerName")
//         );

// final String displayFarmerName =
//         farmerName.isEmpty()
//                 ? "Farmer"
//                 : farmerName;

//         Label farmer =
//                 new Label(
//                         "👨‍🌾  " + displayFarmerName
//                 );

//         farmer.setStyle(
//                 "-fx-text-fill:#EEEEEE;" +
//                 "-fx-font-size:18px;" +
//                 "-fx-font-weight:bold;"
//         );

//         // =====================================================
//         // LAST MESSAGE
//         // =====================================================


//         // =====================================================
//         // FARMER ID
//         // =====================================================

//         int farmerId =
//                 getInt(
//                         chat.get("farmerId")
//                 );

//         Label farmerIdLabel =
//                 new Label(
//                         "Farmer ID: " + farmerId
//                 );

//         farmerIdLabel.setStyle(
//                 "-fx-text-fill:#68D34A;" +
//                 "-fx-font-size:12px;"
//         );

//         // =====================================================
//         // BOTTOM ROW
//         // =====================================================

//         HBox bottom =
//                 new HBox(15);

//         bottom.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         Label updated =
//                 new Label(
//                         formatTime(
//                                 chat.get("updatedAt")
//                         )
//                 );

//         updated.setStyle(
//                 "-fx-text-fill:#666666;" +
//                 "-fx-font-size:12px;"
//         );

//         Button openChat =
//                 new Button(
//                         "Open Chat →"
//                 );

//         openChat.setStyle(
//                 "-fx-background-color:#68D34A;" +
//                 "-fx-text-fill:#081008;" +
//                 "-fx-font-weight:bold;" +
//                 "-fx-background-radius:8;" +
//                 "-fx-padding:10 18;" +
//                 "-fx-cursor:hand;"
//         );

//         openChat.setOnAction(e -> {

//             String buyerUid =
//                     BuyerProfilePage.currentBuyerUid;

//             String buyerName =
//                     BuyerProfilePage.buyerName;

//            BuyerChatPage chatPage =
//         new BuyerChatPage(
//                 buyerUid,
//                 buyerName,
//                 farmerId,
//                 displayFarmerName
//         );

// LoginPage.mainStage.setScene(
//         new Scene(
//                 chatPage.getChatPage(),
//                 1400,
//                 850
//         )
// );

// LoginPage.mainStage.show();
//             LoginPage.mainStage.show();
//         });

//         bottom.getChildren()
//                 .addAll(
//                         updated,
//                         openChat
//                 );

//         card.getChildren()
//                 .addAll(
//                         farmer,
//                         farmerIdLabel,
                      
//                         bottom
//                 );

//         return card;
//     }
private VBox createChatCard(
        Map<String, Object> chat) {

    VBox card =
            new VBox(15);

    card.setPadding(
            new Insets(20)
    );

    card.setMaxWidth(
            Double.MAX_VALUE
    );

    card.setStyle(
            "-fx-background-color:#101516;" +
            "-fx-background-radius:12;" +
            "-fx-border-color:#242B2C;" +
            "-fx-border-radius:12;"
    );

    // =====================================================
    // FARMER NAME
    // =====================================================

    String farmerName =
            getString(
                    chat.get("farmerName")
            );

    final String displayFarmerName =
            farmerName.isEmpty()
                    ? "Farmer"
                    : farmerName;

    Label farmer =
            new Label(
                    "👨‍🌾  " + displayFarmerName
            );

    farmer.setStyle(
            "-fx-text-fill:#EEEEEE;" +
            "-fx-font-size:18px;" +
            "-fx-font-weight:bold;"
    );

    // =====================================================
    // FARMER ID
    // =====================================================

    int farmerId =
            getInt(
                    chat.get("farmerId")
            );

    Label farmerIdLabel =
            new Label(
                    "Farmer ID: " + farmerId
            );

    farmerIdLabel.setStyle(
            "-fx-text-fill:#68D34A;" +
            "-fx-font-size:12px;"
    );

    // =====================================================
    // BOTTOM ROW
    // =====================================================

    HBox bottom =
            new HBox(15);

    bottom.setAlignment(
            Pos.CENTER_LEFT
    );

    Label updated =
            new Label(
                    formatTime(
                            chat.get("updatedAt")
                    )
            );

    updated.setStyle(
            "-fx-text-fill:#666666;" +
            "-fx-font-size:12px;"
    );

    Button openChat =
            new Button(
                    "Open Chat →"
            );

    openChat.setStyle(
            "-fx-background-color:#68D34A;" +
            "-fx-text-fill:#081008;" +
            "-fx-font-weight:bold;" +
            "-fx-background-radius:8;" +
            "-fx-padding:10 18;" +
            "-fx-cursor:hand;"
    );

    openChat.setOnAction(e -> {

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        String buyerName =
                BuyerProfilePage.buyerName;

        BuyerChatPage chatPage =
                new BuyerChatPage(
                        buyerUid,
                        buyerName,
                        farmerId,
                        displayFarmerName
                );

        LoginPage.mainStage.setScene(
                new Scene(
                        chatPage.getChatPage(),
                        1400,
                        850
                )
        );

        LoginPage.mainStage.show();
    });

    bottom.getChildren().addAll(
            updated,
            openChat
    );

    // =====================================================
    // IMPORTANT:
    // DO NOT ADD LAST MESSAGE HERE
    // =====================================================

    card.getChildren().addAll(
            farmer,
            farmerIdLabel,
            bottom
    );

    return card;
}

    // =========================================================
    // STRING
    // =========================================================

    private String getString(
            Object value) {

        if (value == null) {
            return "";
        }

        return value.toString().trim();
    }

    // =========================================================
    // INTEGER
    // =========================================================

    private int getInt(
            Object value) {

        if (value instanceof Number) {

            return ((Number) value)
                    .intValue();
        }

        try {

            return Integer.parseInt(
                    value.toString()
            );

        } catch (Exception e) {

            return 0;
        }
    }

    // =========================================================
    // TIME
    // =========================================================

    private String formatTime(
            Object value) {

        if (value == null) {

            return "";
        }

        return value.toString();
    }
}