package com.pravartak.controller.buyercontroller;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;

import java.util.Base64;

public class RazorpayController {

        private final String keyId;
        private final String keySecret;

        public RazorpayController() {
                keyId = System.getenv("RAZORPAY_KEY_ID");
                keySecret = System.getenv("RAZORPAY_KEY_SECRET");
        }

        public boolean isConfigured() {
                return keyId != null
                                && !keyId.trim().isEmpty()
                                && keySecret != null
                                && !keySecret.trim().isEmpty();
        }

        public String getKeyId() {
                return keyId;
        }

        /**
         * Creates a Razorpay Order.
         *
         * Amount is passed in rupees and converted to paise.
         */
        public String createOrder(
                        double amountInRupees,
                        String receipt) {

                if (!isConfigured()) {
                        throw new IllegalStateException(
                                        "Razorpay keys are not configured.");
                }

                if (amountInRupees <= 0) {
                        throw new IllegalArgumentException(
                                        "Payment amount must be greater than zero.");
                }

                try {

                        long amountInPaise = Math.round(amountInRupees * 100);

                        String safeReceipt = jsonEscape(receipt);

                        String json = "{"
                                        + "\"amount\":" + amountInPaise + ","
                                        + "\"currency\":\"INR\","
                                        + "\"receipt\":\"" + safeReceipt + "\","
                                        + "\"payment_capture\":1"
                                        + "}";

                        String credentials = keyId + ":" + keySecret;

                        String auth = Base64.getEncoder().encodeToString(
                                        credentials.getBytes(
                                                        StandardCharsets.UTF_8));

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create(
                                                                        "https://api.razorpay.com/v1/orders"))
                                        .header(
                                                        "Authorization",
                                                        "Basic " + auth)
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers
                                                                        .ofString(json))
                                        .build();

                        HttpClient client = HttpClient.newHttpClient();

                        HttpResponse<String> response = client.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        if (response.statusCode() < 200
                                        || response.statusCode() >= 300) {

                                throw new RuntimeException(
                                                "Razorpay Order Error ("
                                                                + response.statusCode()
                                                                + "):\n"
                                                                + response.body());
                        }

                        String orderId = extractJsonValue(
                                        response.body(),
                                        "id");

                        if (orderId == null
                                        || orderId.isEmpty()) {

                                throw new RuntimeException(
                                                "Razorpay did not return an Order ID.\n"
                                                                + response.body());
                        }

                        return orderId;

                } catch (Exception e) {

                        throw new RuntimeException(
                                        "Unable to create Razorpay order.\n"
                                                        + e.getMessage(),
                                        e);
                }
        }

        /**
         * Verifies Razorpay payment signature.
         */
        public boolean verifyPaymentSignature(
                        String orderId,
                        String paymentId,
                        String signature) {

                if (!isConfigured()) {
                        return false;
                }

                try {

                        String payload = orderId + "|" + paymentId;

                        Mac mac = Mac.getInstance("HmacSHA256");

                        SecretKeySpec secretKey = new SecretKeySpec(
                                        keySecret.getBytes(
                                                        StandardCharsets.UTF_8),
                                        "HmacSHA256");

                        mac.init(secretKey);

                        byte[] hash = mac.doFinal(
                                        payload.getBytes(
                                                        StandardCharsets.UTF_8));

                        String generatedSignature = bytesToHex(hash);

                        return constantTimeEquals(
                                        generatedSignature,
                                        signature);

                } catch (Exception e) {

                        e.printStackTrace();
                        return false;
                }
        }

        private String extractJsonValue(
                        String json,
                        String key) {

                if (json == null) {
                        return null;
                }

                String search = "\"" + key + "\"";

                int keyIndex = json.indexOf(search);

                if (keyIndex == -1) {
                        return null;
                }

                int colonIndex = json.indexOf(
                                ":",
                                keyIndex);

                if (colonIndex == -1) {
                        return null;
                }

                int start = colonIndex + 1;

                while (start < json.length()
                                && Character.isWhitespace(
                                                json.charAt(start))) {

                        start++;
                }

                if (start >= json.length()) {
                        return null;
                }

                if (json.charAt(start) == '"') {

                        start++;

                        int end = json.indexOf(
                                        "\"",
                                        start);

                        if (end == -1) {
                                return null;
                        }

                        return json.substring(
                                        start,
                                        end);
                }

                int end = start;

                while (end < json.length()
                                && json.charAt(end) != ','
                                && json.charAt(end) != '}'
                                && !Character.isWhitespace(
                                                json.charAt(end))) {

                        end++;
                }

                return json.substring(
                                start,
                                end);
        }

        private String jsonEscape(String value) {

                if (value == null) {
                        return "";
                }

                return value
                                .replace("\\", "\\\\")
                                .replace("\"", "\\\"")
                                .replace("\n", "\\n")
                                .replace("\r", "\\r");
        }

        private String bytesToHex(byte[] bytes) {

                StringBuilder result = new StringBuilder();

                for (byte b : bytes) {

                        result.append(
                                        String.format(
                                                        "%02x",
                                                        b));
                }

                return result.toString();
        }

        private boolean constantTimeEquals(
                        String a,
                        String b) {

                if (a == null || b == null) {
                        return false;
                }

                if (a.length() != b.length()) {
                        return false;
                }

                int result = 0;

                for (int i = 0; i < a.length(); i++) {

                        result |= a.charAt(i) ^ b.charAt(i);
                }

                return result == 0;
        }
}