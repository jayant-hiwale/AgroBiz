// package com.pravartak.controller.buyercontroller;

// import java.awt.Desktop;
// import java.net.URI;
// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
// import java.nio.charset.StandardCharsets;
// import java.util.Base64;

// public class RazorpayController {

//     private final String keyId;
//     private final String keySecret;

//     private String lastPaymentLinkId;

//     public RazorpayController() {
//         keyId = System.getenv("RAZORPAY_KEY_ID");
//         keySecret = System.getenv("RAZORPAY_KEY_SECRET");
//     }

//     public boolean isConfigured() {
//         return keyId != null
//                 && !keyId.trim().isEmpty()
//                 && keySecret != null
//                 && !keySecret.trim().isEmpty();
//     }

//     public String getKeyId() {
//         return keyId;
//     }

//     public String getKeySecret() {
//         return keySecret;
//     }

//     public String getLastPaymentLinkId() {
//         return lastPaymentLinkId;
//     }

//     /**
//      * Creates a Razorpay Test Payment Link.
//      *
//      * amountInRupees = total amount from AgroBiz cart
//      */
//     public String createPaymentLink(
//             double amountInRupees,
//             String buyerName,
//             String buyerPhone,
//             String buyerEmail,
//             String description) {

//         if (!isConfigured()) {
//             throw new IllegalStateException(
//                     "Razorpay keys are not configured.\n"
//                     + "Please set RAZORPAY_KEY_ID and RAZORPAY_KEY_SECRET."
//             );
//         }

//         if (amountInRupees <= 0) {
//             throw new IllegalArgumentException("Payment amount must be greater than zero.");
//         }

//         try {
//             // Razorpay uses paise.
//             long amountInPaise = Math.round(amountInRupees * 100);

//             String safeName = jsonEscape(
//                     buyerName == null ? "AgroBiz Buyer" : buyerName
//             );

//             String safePhone = buyerPhone == null
//         ? ""
//         : buyerPhone.replaceAll("[^0-9+]", "");

// if (safePhone.length() > 14) {
//     safePhone = safePhone.substring(0, 14);
// }
//             String safeEmail = jsonEscape(
//                     buyerEmail == null ? "" : buyerEmail
//             );

//             String safeDescription = jsonEscape(
//                     description == null ? "AgroBiz Order Payment" : description
//             );

//             String referenceId =
//                     "AGRO" + System.currentTimeMillis();

//             String json =
//                     "{"
//                     + "\"amount\":" + amountInPaise + ","
//                     + "\"currency\":\"INR\","
//                     + "\"accept_partial\":false,"
//                     + "\"description\":\"" + safeDescription + "\","
//                     + "\"reference_id\":\"" + referenceId + "\","
//                     + "\"customer\":{"
//                     + "\"name\":\"" + safeName + "\","
//                     + "\"contact\":\"" + safePhone + "\","
//                     + "\"email\":\"" + safeEmail + "\""
//                     + "},"
//                     + "\"notify\":{"
//                     + "\"sms\":false,"
//                     + "\"email\":false"
//                     + "},"
//                     + "\"reminder_enable\":false"
//                     + "}";

//             String credentials =
//                     keyId + ":" + keySecret;

//             String auth = Base64.getEncoder()
//                     .encodeToString(
//                             credentials.getBytes(StandardCharsets.UTF_8)
//                     );

//             HttpRequest request = HttpRequest.newBuilder()
//                     .uri(URI.create(
//                             "https://api.razorpay.com/v1/payment_links"
//                     ))
//                     .header(
//                             "Authorization",
//                             "Basic " + auth
//                     )
//                     .header(
//                             "Content-Type",
//                             "application/json"
//                     )
//                     .POST(
//                             HttpRequest.BodyPublishers.ofString(json)
//                     )
//                     .build();

//             HttpClient client = HttpClient.newHttpClient();

//             HttpResponse<String> response =
//                     client.send(
//                             request,
//                             HttpResponse.BodyHandlers.ofString()
//                     );

//             if (response.statusCode() < 200
//                     || response.statusCode() >= 300) {

//                 throw new RuntimeException(
//                         "Razorpay Error (" + response.statusCode() + "):\n"
//                         + response.body()
//                 );
//             }

//             String responseBody = response.body();

//             String paymentLinkId =
//                     extractJsonValue(responseBody, "id");

//             String shortUrl =
//                     extractJsonValue(responseBody, "short_url");

//             if (paymentLinkId == null
//                     || shortUrl == null) {

//                 throw new RuntimeException(
//                         "Razorpay did not return a valid payment link.\n"
//                         + responseBody
//                 );
//             }

//             lastPaymentLinkId = paymentLinkId;

//             return shortUrl;

//         } catch (Exception e) {
//             throw new RuntimeException(
//                     "Unable to create Razorpay payment link.\n"
//                     + e.getMessage(),
//                     e
//             );
//         }
//     }

//     /**
//      * Opens Razorpay Payment Link in the user's default browser.
//      */
//     public void openPaymentPage(String paymentUrl) {

//     if (paymentUrl == null
//             || paymentUrl.trim().isEmpty()) {

//         throw new IllegalArgumentException(
//                 "Payment URL is empty."
//         );
//     }

//     try {

//         // Windows browser opening
//         if (System.getProperty("os.name")
//                 .toLowerCase()
//                 .contains("win")) {

//             new ProcessBuilder(
//                     "cmd",
//                     "/c",
//                     "start",
//                     "",
//                     paymentUrl
//             ).start();

//             return;
//         }

//         // Fallback for other operating systems
//         if (Desktop.isDesktopSupported()) {

//             Desktop.getDesktop().browse(
//                     new URI(paymentUrl)
//             );

//             return;
//         }

//         throw new RuntimeException(
//                 "Unable to open the default browser."
//         );

//     } catch (Exception e) {

//         throw new RuntimeException(
//                 "Could not open Razorpay payment page.\n"
//                 + e.getMessage(),
//                 e
//         );
//     }
// }

//     /**
//      * Gets the current status of a Payment Link.
//      */
//     public String getPaymentLinkStatus(String paymentLinkId) {

//         if (!isConfigured()) {
//             throw new IllegalStateException(
//                     "Razorpay keys are not configured."
//             );
//         }

//         if (paymentLinkId == null
//                 || paymentLinkId.trim().isEmpty()) {

//             throw new IllegalArgumentException(
//                     "Payment Link ID is empty."
//             );
//         }

//         try {

//             String credentials =
//                     keyId + ":" + keySecret;

//             String auth = Base64.getEncoder()
//                     .encodeToString(
//                             credentials.getBytes(StandardCharsets.UTF_8)
//                     );

//             HttpRequest request = HttpRequest.newBuilder()
//                     .uri(
//                             URI.create(
//                                     "https://api.razorpay.com/v1/payment_links/"
//                                     + paymentLinkId
//                             )
//                     )
//                     .header(
//                             "Authorization",
//                             "Basic " + auth
//                     )
//                     .GET()
//                     .build();

//             HttpClient client =
//                     HttpClient.newHttpClient();

//             HttpResponse<String> response =
//                     client.send(
//                             request,
//                             HttpResponse.BodyHandlers.ofString()
//                     );

//             if (response.statusCode() < 200
//                     || response.statusCode() >= 300) {

//                 throw new RuntimeException(
//                         "Razorpay Error (" + response.statusCode() + "):\n"
//                         + response.body()
//                 );
//             }

//             return response.body();

//         } catch (Exception e) {
//             throw new RuntimeException(
//                     "Unable to verify Razorpay payment.\n"
//                     + e.getMessage(),
//                     e
//             );
//         }
//     }

//     /**
//      * Returns true when Razorpay says the Payment Link is paid.
//      */
//     public boolean isPaymentSuccessful(String paymentLinkId) {

//         String response =
//                 getPaymentLinkStatus(paymentLinkId);

//         String status =
//                 extractJsonValue(response, "status");

//         String amountPaid =
//                 extractJsonValue(response, "amount_paid");

//         return "paid".equalsIgnoreCase(status)
//                 && amountPaid != null
//                 && !amountPaid.equals("0");
//     }

//     private String extractJsonValue(
//             String json,
//             String key) {

//         if (json == null) {
//             return null;
//         }

//         String search =
//                 "\"" + key + "\"";

//         int keyIndex =
//                 json.indexOf(search);

//         if (keyIndex == -1) {
//             return null;
//         }

//         int colonIndex =
//                 json.indexOf(":", keyIndex);

//         if (colonIndex == -1) {
//             return null;
//         }

//         int start =
//                 colonIndex + 1;

//         while (start < json.length()
//                 && Character.isWhitespace(
//                         json.charAt(start))) {
//             start++;
//         }

//         if (start >= json.length()) {
//             return null;
//         }

//         if (json.charAt(start) == '"') {

//             start++;

//             int end =
//                     json.indexOf("\"", start);

//             if (end == -1) {
//                 return null;
//             }

//             return json.substring(start, end);
//         }

//         int end =
//                 start;

//         while (end < json.length()
//                 && json.charAt(end) != ','
//                 && json.charAt(end) != '}'
//                 && !Character.isWhitespace(
//                         json.charAt(end))) {

//             end++;
//         }

//         return json.substring(start, end);
//     }

//     private String jsonEscape(String value) {

//         return value
//                 .replace("\\", "\\\\")
//                 .replace("\"", "\\\"")
//                 .replace("\n", "\\n")
//                 .replace("\r", "\\r");
//     }
// }


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
                    "Razorpay keys are not configured."
            );
        }

        if (amountInRupees <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero."
            );
        }

        try {

            long amountInPaise =
                    Math.round(amountInRupees * 100);

            String safeReceipt =
                    jsonEscape(receipt);

            String json =
                    "{"
                    + "\"amount\":" + amountInPaise + ","
                    + "\"currency\":\"INR\","
                    + "\"receipt\":\"" + safeReceipt + "\","
                    + "\"payment_capture\":1"
                    + "}";

            String credentials =
                    keyId + ":" + keySecret;

            String auth =
                    Base64.getEncoder().encodeToString(
                            credentials.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            "https://api.razorpay.com/v1/orders"
                                    )
                            )
                            .header(
                                    "Authorization",
                                    "Basic " + auth
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(json)
                            )
                            .build();

            HttpClient client =
                    HttpClient.newHttpClient();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() < 200
                    || response.statusCode() >= 300) {

                throw new RuntimeException(
                        "Razorpay Order Error ("
                        + response.statusCode()
                        + "):\n"
                        + response.body()
                );
            }

            String orderId =
                    extractJsonValue(
                            response.body(),
                            "id"
                    );

            if (orderId == null
                    || orderId.isEmpty()) {

                throw new RuntimeException(
                        "Razorpay did not return an Order ID.\n"
                        + response.body()
                );
            }

            return orderId;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to create Razorpay order.\n"
                    + e.getMessage(),
                    e
            );
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

            String payload =
                    orderId + "|" + paymentId;

            Mac mac =
                    Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKey =
                    new SecretKeySpec(
                            keySecret.getBytes(
                                    StandardCharsets.UTF_8
                            ),
                            "HmacSHA256"
                    );

            mac.init(secretKey);

            byte[] hash =
                    mac.doFinal(
                            payload.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            String generatedSignature =
                    bytesToHex(hash);

            return constantTimeEquals(
                    generatedSignature,
                    signature
            );

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

        String search =
                "\"" + key + "\"";

        int keyIndex =
                json.indexOf(search);

        if (keyIndex == -1) {
            return null;
        }

        int colonIndex =
                json.indexOf(
                        ":",
                        keyIndex
                );

        if (colonIndex == -1) {
            return null;
        }

        int start =
                colonIndex + 1;

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

            int end =
                    json.indexOf(
                            "\"",
                            start
                    );

            if (end == -1) {
                return null;
            }

            return json.substring(
                    start,
                    end
            );
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
                end
        );
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

        StringBuilder result =
                new StringBuilder();

        for (byte b : bytes) {

            result.append(
                    String.format(
                            "%02x",
                            b
                    )
            );
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

            result |=
                    a.charAt(i) ^ b.charAt(i);
        }

        return result == 0;
    }
}