package com.pravartak.controller.buyercontroller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import javafx.application.Platform;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class RazorpayBrowserServer {

    private HttpServer server;

    private final RazorpayController razorpay;

    private final double amount;
    private final String buyerName;
    private final String buyerEmail;
    private final String buyerPhone;
    private final String orderId;

    private final Runnable onPaymentSuccess;
    private final Runnable onPaymentFailed;

    private int port;

    public RazorpayBrowserServer(
            RazorpayController razorpay,
            double amount,
            String buyerName,
            String buyerEmail,
            String buyerPhone,
            String orderId,
            Runnable onPaymentSuccess,
            Runnable onPaymentFailed) {

        this.razorpay = razorpay;
        this.amount = amount;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerPhone = buyerPhone;
        this.orderId = orderId;

        this.onPaymentSuccess = onPaymentSuccess;
        this.onPaymentFailed = onPaymentFailed;
    }

    public void start() {

        try {

            /*
             * Start local server on an available port.
             */
            server = HttpServer.create(
                    new InetSocketAddress(
                            "127.0.0.1",
                            0
                    ),
                    0
            );

            port =
                    server.getAddress().getPort();

            /*
             * Main payment page.
             */
            server.createContext(
                    "/",
                    this::handlePaymentPage
            );

            /*
             * Receives successful payment information
             * from the Chrome Razorpay page.
             */
            server.createContext(
                    "/payment-success",
                    this::handlePaymentSuccess
            );

            /*
             * Payment failure.
             */
            server.createContext(
                    "/payment-failed",
                    this::handlePaymentFailed
            );

            server.setExecutor(
                    Executors.newCachedThreadPool()
            );

            server.start();

            String url =
                    "http://127.0.0.1:"
                    + port
                    + "/";

            System.out.println(
                    "AgroBiz payment server started:"
            );

            System.out.println(url);

            openChrome(url);

        } catch (Exception e) {

            e.printStackTrace();

            Platform.runLater(() -> {

                if (onPaymentFailed != null) {
                    onPaymentFailed.run();
                }

            });
        }
    }

    private void handlePaymentPage(
            HttpExchange exchange)
            throws IOException {

        String html =
                createPaymentHtml();

        sendResponse(
                exchange,
                200,
                html
        );
    }

    private String createPaymentHtml() {

        String safeName =
                escapeJavaScript(
                        buyerName
                );

        String safeEmail =
                escapeJavaScript(
                        buyerEmail
                );

        String safePhone =
                buyerPhone == null
                        ? ""
                        : buyerPhone.replaceAll(
                                "[^0-9+]",
                                ""
                        );

        long amountPaise =
                Math.round(
                        amount * 100
                );

        return "<!DOCTYPE html>"

                + "<html>"

                + "<head>"

                + "<meta charset='UTF-8'>"

                + "<meta name='viewport' "
                + "content='width=device-width,"
                + "initial-scale=1.0'>"

                + "<title>AgroBiz Payment</title>"

                + "<script src="
                + "'https://checkout.razorpay.com/v1/checkout.js'>"
                + "</script>"

                + "<style>"

                + "* {"
                + "box-sizing:border-box;"
                + "}"

                + "body {"
                + "margin:0;"
                + "font-family:Arial,sans-serif;"
                + "background:#0D1117;"
                + "color:white;"
                + "min-height:100vh;"
                + "display:flex;"
                + "align-items:center;"
                + "justify-content:center;"
                + "}"

                + ".container {"
                + "width:450px;"
                + "background:#161B22;"
                + "padding:40px;"
                + "border-radius:20px;"
                + "text-align:center;"
                + "box-shadow:"
                + "0 15px 40px rgba(0,0,0,.5);"
                + "}"

                + ".logo {"
                + "font-size:34px;"
                + "font-weight:bold;"
                + "color:#66BB6A;"
                + "margin-bottom:10px;"
                + "}"

                + ".title {"
                + "font-size:20px;"
                + "margin-bottom:25px;"
                + "}"

                + ".amount {"
                + "font-size:38px;"
                + "font-weight:bold;"
                + "margin:20px 0;"
                + "}"

                + ".order {"
                + "font-size:13px;"
                + "color:#999;"
                + "word-break:break-all;"
                + "margin-bottom:25px;"
                + "}"

                + ".pay-button {"
                + "width:100%;"
                + "padding:16px;"
                + "border:0;"
                + "border-radius:10px;"
                + "background:#66BB6A;"
                + "color:white;"
                + "font-size:18px;"
                + "font-weight:bold;"
                + "cursor:pointer;"
                + "}"

                + ".pay-button:hover {"
                + "background:#4CAF50;"
                + "}"

                + ".info {"
                + "margin-top:20px;"
                + "font-size:13px;"
                + "color:#999;"
                + "}"

                + "#status {"
                + "margin-top:15px;"
                + "color:#66BB6A;"
                + "}"

                + "</style>"

                + "</head>"

                + "<body>"

                + "<div class='container'>"

                + "<div class='logo'>AgroBiz</div>"

                + "<div class='title'>"
                + "Secure Test Payment"
                + "</div>"

                + "<div class='amount'>"
                + "₹"
                + String.format(
                        "%.2f",
                        amount
                )
                + "</div>"

                + "<div class='order'>"
                + "Order ID: "
                + orderId
                + "</div>"

                + "<button class='pay-button' "
                + "onclick='payNow()'>"

                + "Pay Now with Razorpay"

                + "</button>"

                + "<div id='status'></div>"

                + "<div class='info'>"
                + "Test Mode • No real money will be charged"
                + "</div>"

                + "</div>"

                + "<script>"

                + "function payNow() {"

                + "document.getElementById('status')"
                + ".innerText="
                + "'Opening Razorpay Checkout...';"

                + "if(typeof Razorpay === 'undefined') {"

                + "document.getElementById('status')"
                + ".innerText="
                + "'Razorpay could not be loaded. Check internet connection.';"

                + "return;"

                + "}"

                + "var options = {"

                + "key: '"
                + razorpay.getKeyId()
                + "',"

                + "amount: '"
                + amountPaise
                + "',"

                + "currency: 'INR',"

                + "name: 'AgroBiz',"

                + "description: "
                + "'Agricultural Product Order',"

                + "order_id: '"
                + orderId
                + "',"

                + "prefill: {"

                + "name: '"
                + safeName
                + "',"

                + "email: '"
                + safeEmail
                + "',"

                + "contact: '"
                + safePhone
                + "'"

                + "},"

                + "theme: {"
                + "color: '#66BB6A'"
                + "},"

                + "handler: function(response) {"

                + "document.getElementById('status')"
                + ".innerText="
                + "'Verifying payment...';"

                + "var data = "
                + "new URLSearchParams();"

                + "data.append("
                + "'razorpay_payment_id',"
                + "response.razorpay_payment_id"
                + ");"

                + "data.append("
                + "'razorpay_order_id',"
                + "response.razorpay_order_id"
                + ");"

                + "data.append("
                + "'razorpay_signature',"
                + "response.razorpay_signature"
                + ");"

                + "fetch('/payment-success', {"

                + "method:'POST',"

                + "headers:{"
                + "'Content-Type':"
                + "'application/x-www-form-urlencoded'"
                + "},"

                + "body:data.toString()"

                + "})"

                + ".then(function(r){"
                + "return r.text();"
                + "})"

                + ".then(function(text){"

                + "document.body.innerHTML=text;"

                + "})"

                + ".catch(function(error){"

                + "document.getElementById('status')"
                + ".innerText="
                + "'Could not contact AgroBiz.';"

                + "});"

                + "},"

                + "modal: {"

                + "ondismiss: function(){"

                + "document.getElementById('status')"
                + ".innerText="
                + "'Payment cancelled. You can try again.';"

                + "}"

                + "}"

                + "};"

                + "var rzp = "
                + "new Razorpay(options);"

                + "rzp.on("
                + "'payment.failed',"
                + "function(response){"

                + "document.getElementById('status')"
                + ".innerText="
                + "'Payment failed. Please try again.';"

                + "});"

                + "rzp.open();"

                + "}"

                + "</script>"

                + "</body>"

                + "</html>";
    }

    private void handlePaymentSuccess(
            HttpExchange exchange)
            throws IOException {

        String body =
                new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        Map<String, String> data =
                parseFormData(body);

        String paymentId =
                data.get(
                        "razorpay_payment_id"
                );

        String receivedOrderId =
                data.get(
                        "razorpay_order_id"
                );

        String signature =
                data.get(
                        "razorpay_signature"
                );

        boolean verified =
                receivedOrderId != null
                && receivedOrderId.equals(orderId)
                && razorpay.verifyPaymentSignature(
                        orderId,
                        paymentId,
                        signature
                );

        if (verified) {

            sendResponse(
                    exchange,
                    200,
                    successHtml()
            );

            stopServer();

            Platform.runLater(() -> {

                if (onPaymentSuccess != null) {
                    onPaymentSuccess.run();
                }

            });

        } else {

            sendResponse(
                    exchange,
                    400,
                    failureHtml(
                            "Payment verification failed."
                    )
            );

            Platform.runLater(() -> {

                if (onPaymentFailed != null) {
                    onPaymentFailed.run();
                }

            });
        }
    }

    private void handlePaymentFailed(
            HttpExchange exchange)
            throws IOException {

        sendResponse(
                exchange,
                400,
                failureHtml(
                        "Payment failed."
                )
        );

        Platform.runLater(() -> {

            if (onPaymentFailed != null) {
                onPaymentFailed.run();
            }

        });
    }

    private String successHtml() {

        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Payment Successful</title>"
                + "<style>"
                + "body{"
                + "background:#0D1117;"
                + "color:white;"
                + "font-family:Arial;"
                + "display:flex;"
                + "justify-content:center;"
                + "align-items:center;"
                + "height:100vh;"
                + "text-align:center;"
                + "}"
                + ".box{"
                + "background:#161B22;"
                + "padding:50px;"
                + "border-radius:20px;"
                + "}"
                + "h1{"
                + "color:#66BB6A;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<h1>✓ Payment Successful</h1>"
                + "<p>Your AgroBiz payment has been verified.</p>"
                + "<p>You can return to the AgroBiz application.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private String failureHtml(
            String message) {

        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<style>"
                + "body{"
                + "background:#0D1117;"
                + "color:white;"
                + "font-family:Arial;"
                + "display:flex;"
                + "justify-content:center;"
                + "align-items:center;"
                + "height:100vh;"
                + "text-align:center;"
                + "}"
                + ".box{"
                + "background:#161B22;"
                + "padding:50px;"
                + "border-radius:20px;"
                + "}"
                + "h1{color:#EF5350;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<h1>Payment Failed</h1>"
                + "<p>"
                + escapeHtml(message)
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private Map<String, String> parseFormData(
            String body) {

        Map<String, String> map =
                new HashMap<>();

        if (body == null
                || body.isEmpty()) {

            return map;
        }

        String[] pairs =
                body.split("&");

        for (String pair : pairs) {

            String[] parts =
                    pair.split(
                            "=",
                            2
                    );

            if (parts.length == 2) {

                String key =
                        URLDecoder.decode(
                                parts[0],
                                StandardCharsets.UTF_8
                        );

                String value =
                        URLDecoder.decode(
                                parts[1],
                                StandardCharsets.UTF_8
                        );

                map.put(
                        key,
                        value
                );
            }
        }

        return map;
    }

    private void openChrome(
            String url) {

        try {

            if (System.getProperty(
                    "os.name"
            ).toLowerCase().contains("win")) {

                /*
                 * Windows will open the default browser.
                 * If Chrome is the default browser,
                 * Chrome will be used.
                 */
                new ProcessBuilder(
                        "cmd",
                        "/c",
                        "start",
                        "",
                        url
                ).start();

            } else if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().browse(
                        new URI(url)
                );

            } else {

                throw new RuntimeException(
                        "No browser available."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            Platform.runLater(() -> {

                if (onPaymentFailed != null) {
                    onPaymentFailed.run();
                }

            });
        }
    }

    private void stopServer() {

        if (server != null) {

            server.stop(1);

            server = null;
        }
    }

    private void sendResponse(
            HttpExchange exchange,
            int status,
            String response)
            throws IOException {

        byte[] bytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "text/html; charset=UTF-8"
                );

        exchange.sendResponseHeaders(
                status,
                bytes.length
        );

        try (OutputStream output =
                     exchange.getResponseBody()) {

            output.write(bytes);
        }
    }

    private String escapeJavaScript(
            String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private String escapeHtml(
            String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}