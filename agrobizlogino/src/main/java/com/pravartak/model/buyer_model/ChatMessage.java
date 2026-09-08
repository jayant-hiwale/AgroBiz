package com.pravartak.model.buyer_model;

import com.google.cloud.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class ChatMessage {

    private String messageId;
    private String senderId;
    private String senderType;
    private String message;
    private Timestamp timestamp;

    // =========================================================
    // EMPTY CONSTRUCTOR
    // =========================================================

    public ChatMessage() {
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ChatMessage(
            String messageId,
            String senderId,
            String senderType,
            String message,
            Timestamp timestamp) {

        this.messageId = messageId;
        this.senderId = senderId;
        this.senderType = senderType;
        this.message = message;
        this.timestamp = timestamp;
    }

    // =========================================================
    // GETTERS / SETTERS
    // =========================================================

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    // =========================================================
    // FIRESTORE MAP
    // =========================================================

    public Map<String, Object> toMap() {

        Map<String, Object> map =
                new HashMap<>();

        map.put(
                "messageId",
                messageId
        );

        map.put(
                "senderId",
                senderId
        );

        map.put(
                "senderType",
                senderType
        );

        map.put(
                "message",
                message
        );

        map.put(
                "timestamp",
                timestamp
        );

        return map;
    }
}