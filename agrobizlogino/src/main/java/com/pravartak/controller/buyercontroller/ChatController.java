package com.pravartak.controller.buyercontroller;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.buyer_model.ChatMessage;
import com.google.cloud.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatController {

    private final Firestore db;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ChatController() {

        db = FirebaseConfig.getFirestore();

        if (db == null) {

            throw new IllegalStateException(
                    "Firestore could not be initialized."
            );
        }
    }

    // =========================================================
    // CREATE CHAT ID
    // =========================================================

    public String createChatId(
            String buyerUid,
            int farmerId) {

        return buyerUid
                + "_"
                + farmerId;
    }

    // =========================================================
    // SEND MESSAGE
    // =========================================================

    public boolean sendMessage(
            String buyerUid,
            String buyerName,
            int farmerId,
            String farmerName,
            String senderId,
            String senderType,
            String message) {

        try {

            if (buyerUid == null ||
                    buyerUid.trim().isEmpty()) {

                return false;
            }

            if (message == null ||
                    message.trim().isEmpty()) {

                return false;
            }

            String chatId =
                    createChatId(
                            buyerUid,
                            farmerId
                    );

            String messageId =
                    "MSG"
                            + System.currentTimeMillis();

            ChatMessage chatMessage =
                    new ChatMessage(
                            messageId,
                            senderId,
                            senderType,
                            message.trim(),
                            Timestamp.now()
                    );

            // =================================================
            // SAVE MESSAGE
            // =================================================

            db.collection("chats")
                    .document(chatId)
                    .collection("messages")
                    .document(messageId)
                    .set(chatMessage.toMap())
                    .get();

            // =================================================
            // UPDATE CHAT INFORMATION
            // =================================================

            Map<String, Object> chatData =
        new java.util.HashMap<>();

chatData.put(
        "buyerUid",
        buyerUid
);

chatData.put(
        "buyerName",
        buyerName
);

chatData.put(
        "farmerId",
        farmerId
);

chatData.put(
        "farmerName",
        farmerName
);

chatData.put(
        "lastMessage",
        message.trim()
);

chatData.put(
        "updatedAt",
        Timestamp.now()
);

db.collection("chats")
        .document(chatId)
        .set(
                chatData,
                com.google.cloud.firestore.SetOptions.merge()
        )
        .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET MESSAGES
    // =========================================================

    public List<ChatMessage> getMessages(
            String buyerUid,
            int farmerId) {

        List<ChatMessage> messages =
                new ArrayList<>();

        try {

            String chatId =
                    createChatId(
                            buyerUid,
                            farmerId
                    );

            QuerySnapshot snapshot =
                    db.collection("chats")
                            .document(chatId)
                            .collection("messages")
                            .get()
                            .get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                ChatMessage message =
                        document.toObject(
                                ChatMessage.class
                        );

                if (message != null) {

                    messages.add(
                            message
                    );
                }
            }

            // Newest message last
            messages.sort(
                    (a, b) -> {

                        if (a.getTimestamp() == null) {
                            return -1;
                        }

                        if (b.getTimestamp() == null) {
                            return 1;
                        }

                        return a.getTimestamp()
                                .compareTo(
                                        b.getTimestamp()
                                );
                    }
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return messages;
    }
    // =========================================================
// GET FARMER CHATS
// =========================================================

public List<java.util.Map<String, Object>> getFarmerChats(
        int farmerId) {

    List<java.util.Map<String, Object>> chats =
            new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection("chats")
                        .whereEqualTo(
                                "farmerId",
                                farmerId
                        )
                        .get()
                        .get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            java.util.Map<String, Object> data =
                    document.getData();

            if (data != null) {

                data.put(
                        "chatId",
                        document.getId()
                );

                chats.add(data);
            }
        }

        // Newest chats first
        chats.sort(
                (a, b) -> {

                    Object timeA =
                            a.get("updatedAt");

                    Object timeB =
                            b.get("updatedAt");

                    if (!(timeA instanceof Timestamp)) {
                        return 1;
                    }

                    if (!(timeB instanceof Timestamp)) {
                        return -1;
                    }

                    return ((Timestamp) timeB)
                            .compareTo(
                                    (Timestamp) timeA
                            );
                }
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return chats;
}
// =========================================================
// GET BUYER CHATS
// =========================================================

public List<Map<String, Object>> getBuyerChats(
        String buyerUid) {

    List<Map<String, Object>> chats =
            new ArrayList<>();

    try {

        if (buyerUid == null ||
                buyerUid.trim().isEmpty()) {

            return chats;
        }

        QuerySnapshot snapshot =
                db.collection("chats")
                        .whereEqualTo(
                                "buyerUid",
                                buyerUid
                        )
                        .get()
                        .get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            Map<String, Object> data =
                    document.getData();

            if (data != null) {

                data.put(
                        "chatId",
                        document.getId()
                );

                chats.add(data);
            }
        }

        // Newest chats first
        chats.sort(
                (a, b) -> {

                    Object timeA =
                            a.get("updatedAt");

                    Object timeB =
                            b.get("updatedAt");

                    if (!(timeA instanceof Timestamp)) {
                        return 1;
                    }

                    if (!(timeB instanceof Timestamp)) {
                        return -1;
                    }

                    return ((Timestamp) timeB)
                            .compareTo(
                                    (Timestamp) timeA
                            );
                }
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return chats;
}
}