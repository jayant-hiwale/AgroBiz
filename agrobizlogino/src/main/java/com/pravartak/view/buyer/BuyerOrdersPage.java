package com.pravartak.view.buyer;


import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.model.buyer_model.Order;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class BuyerOrdersPage {

    private final OrderController orderController;

    private VBox ordersContainer;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BuyerOrdersPage() {
        orderController = new OrderController();
    }

    // =====================================================
    // PAGE
    // =====================================================

    public BorderPane getOrdersPage() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // NAVBAR
        // =================================================

        root.setTop(
                new buyerTop().createBuyerTop("Orders")
        );

        // =================================================
        // FOOTER
        // =================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =================================================
        // MAIN
        // =================================================

        VBox main = new VBox(18);

        main.setPadding(
                new Insets(
                        25,
                        35,
                        30,
                        35
                )
        );

        // =================================================
        // HEADER
        // =================================================

        HBox titleRow = new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label title =
                new Label("📦 My Orders");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Track your orders and delivery status."
                );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );

        // =================================================
        // REFRESH BUTTON
        // =================================================

        Button refresh =
                new Button("↻ Refresh");

        refresh.setPrefHeight(38);

        refresh.setStyle(
                "-fx-background-color:#1E2A21;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-padding:8 16;" +
                "-fx-cursor:hand;"
        );

        refresh.setOnAction(
                e -> loadOrders()
        );

        titleRow.getChildren().addAll(
                titleBox,
                refresh
        );

        // =================================================
        // ORDERS CONTAINER
        // =================================================

        ordersContainer =
                new VBox(18);

        ordersContainer.setPadding(
                new Insets(5)
        );

        ordersContainer.setAlignment(
                Pos.TOP_CENTER
        );

        ScrollPane scroll =
                new ScrollPane(
                        ordersContainer
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
                titleRow,
                scroll
        );

        root.setCenter(main);

        loadOrders();

        return root;
    }

    // =====================================================
    // LOAD ORDERS
    // =====================================================

    private void loadOrders() {

        if (ordersContainer == null) {
            return;
        }

        ordersContainer
                .getChildren()
                .clear();

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        if (buyerUid == null ||
                buyerUid.trim().isEmpty()) {

            showEmptyState(
                    "Buyer account information is unavailable."
            );

            return;
        }

        try {

            List<Order> orders =
                    orderController.getBuyerOrders(
                            buyerUid
                    );

            if (orders == null ||
                    orders.isEmpty()) {

                showEmptyState(
                        "You have not placed any orders yet."
                );

                return;
            }

            Label count =
                    new Label(
                            orders.size()
                                    + " Order"
                                    + (orders.size() == 1
                                    ? ""
                                    : "s")
                    );

            count.setStyle(
                    "-fx-text-fill:#68D34A;" +
                    "-fx-font-size:14px;" +
                    "-fx-font-weight:bold;"
            );

            ordersContainer
                    .getChildren()
                    .add(count);

            for (Order order : orders) {

                ordersContainer
                        .getChildren()
                        .add(
                                createOrderCard(
                                        order
                                )
                        );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Unable to Load Orders",
                    "Something went wrong while loading your orders."
            );
        }
    }

    // =====================================================
    // EMPTY STATE
    // =====================================================

    private void showEmptyState(
            String message) {

        VBox empty =
                new VBox(12);

        empty.setAlignment(
                Pos.CENTER
        );

        empty.setPadding(
                new Insets(80)
        );

        Label icon =
                new Label("📦");

        icon.setStyle(
                "-fx-font-size:48px;"
        );

        Label title =
                new Label("No Orders");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:22px;" +
                "-fx-font-weight:bold;"
        );

        Label text =
                new Label(message);

        text.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:14px;"
        );

        empty.getChildren().addAll(
                icon,
                title,
                text
        );

        ordersContainer
                .getChildren()
                .add(empty);
    }

    // =====================================================
    // ORDER CARD
    // =====================================================

    private VBox createOrderCard(
            Order order) {

        VBox card =
                new VBox(15);

        card.setMaxWidth(1000);

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        // =================================================
        // HEADER
        // =================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label orderId =
                new Label(
                        "Order #"
                                + safe(
                                order.getOrderId()
                        )
                );

        orderId.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:19px;" +
                "-fx-font-weight:bold;"
        );

        Label status =
                createStatusLabel(
                        order.getOrderStatus()
                );

        HBox.setHgrow(
                orderId,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                orderId,
                status
        );

        // =================================================
        // FARMER
        // =================================================

        VBox farmerBox =
                new VBox(6);

        Label farmerHeading =
                new Label("👨‍🌾 Farmer");

        farmerHeading.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        Label farmer =
                new Label(
                        safe(
                                order.getFarmerName()
                        )
                );

        farmer.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );

        farmerBox.getChildren().addAll(
                farmerHeading,
                farmer
        );

        // =================================================
        // PRODUCTS
        // =================================================

        VBox itemsBox =
                new VBox(7);

        Label itemsHeading =
                new Label(
                        "Ordered Products"
                );

        itemsHeading.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        itemsBox.getChildren().add(
                itemsHeading
        );

        List<Map<String, Object>> items =
                order.getItems();

        if (items != null &&
                !items.isEmpty()) {

            for (Map<String, Object> item :
                    items) {

                itemsBox.getChildren().add(
                        createItemRow(item)
                );
            }
        }

        // =================================================
        // DELIVERY
        // =================================================

        VBox deliveryBox =
                new VBox(6);

        Label deliveryHeading =
                new Label(
                        "📍 Delivery Address"
                );

        deliveryHeading.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        Label address =
                new Label(
                        safe(
                                order.getBuyerAddress()
                        )
                );

        address.setWrapText(true);

        address.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );

        deliveryBox.getChildren().addAll(
                deliveryHeading,
                address
        );

        // =================================================
        // PAYMENT / TOTAL
        // =================================================

        HBox paymentRow =
                new HBox();

        paymentRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label payment =
                new Label(
                        "Payment: "
                                + safe(
                                order.getPaymentMethod()
                        )
                );

        payment.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        Label total =
                new Label(
                        "₹"
                                + String.format(
                                "%.2f",
                                order.getTotalAmount()
                        )
                );

        total.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        HBox.setHgrow(
                payment,
                Priority.ALWAYS
        );

        paymentRow.getChildren().addAll(
                payment,
                total
        );

        // =================================================
        // TRACKING
        // =================================================

        VBox tracking =
                createTrackingSection(
                        order.getOrderStatus()
                );

        // =================================================
        // STATUS MESSAGE
        // =================================================

        Label statusMessage =
                createStatusMessage(
                        order.getOrderStatus()
                );

        // =================================================
        // CANCEL
        // =================================================

        // =================================================
// ACTIONS
// =================================================

HBox actionBox =
        new HBox(10);

actionBox.setAlignment(
        Pos.CENTER_RIGHT
);

String currentStatus =
        order.getOrderStatus();

// =================================================
// RATE & REVIEW - DELIVERED ORDERS ONLY
// =================================================

if ("DELIVERED".equalsIgnoreCase(currentStatus)) {

    Button reviewButton =
            new Button("⭐ Rate & Review");

    reviewButton.setPrefHeight(38);

    reviewButton.setStyle(
            "-fx-background-color:#D29922;" +
            "-fx-text-fill:white;" +
            "-fx-font-size:13px;" +
            "-fx-font-weight:bold;" +
            "-fx-background-radius:8;" +
            "-fx-padding:9 18;" +
            "-fx-cursor:hand;"
    );

    reviewButton.setOnAction(e -> {

        try {

            ReviewPage reviewPage =
                    new ReviewPage(order);

            BorderPane page =
                    reviewPage.getReviewPage();

            Scene scene =
                    new Scene(
                            page,
                            1400,
                            850
                    );

            LoginPage.mainStage.setScene(
                    scene
            );

            LoginPage.mainStage.show();

        } catch (Exception ex) {

            ex.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Unable to Open Review",
                    "Something went wrong while opening the review page."
            );
        }
    });

    actionBox.getChildren().add(
            reviewButton
    );
}

// =================================================
// CANCEL PENDING ORDER
// =================================================

if ("PENDING".equalsIgnoreCase(currentStatus)) {

    Button cancel =
            new Button(
                    "Cancel Order"
            );

    cancel.setStyle(
            "-fx-background-color:#3A1518;" +
            "-fx-text-fill:#FF6B6B;" +
            "-fx-font-size:13px;" +
            "-fx-font-weight:bold;" +
            "-fx-background-radius:7;" +
            "-fx-padding:9 18;" +
            "-fx-cursor:hand;"
    );

    cancel.setOnAction(
            e -> cancelOrder(order)
    );

    actionBox.getChildren().add(
            cancel
    );
}

        // =================================================
        // CARD
        // =================================================

        card.getChildren().addAll(
                header,
                createSeparator(),
                farmerBox,
                itemsBox,
                createSeparator(),
                deliveryBox,
                createSeparator(),
                paymentRow,
                createSeparator(),
                tracking,
                statusMessage,
                actionBox
        );

        return card;
    }

    // =====================================================
    // TRACKING SECTION
    // =====================================================

    private VBox createTrackingSection(
            String status) {

        VBox box =
                new VBox(12);

        Label heading =
                new Label(
                        "📍 Order Tracking"
                );

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;"
        );

        box.getChildren().add(
                heading
        );

        if ("REJECTED".equalsIgnoreCase(status) ||
                "CANCELLED".equalsIgnoreCase(status)) {

            HBox failed =
                    new HBox(10);

            failed.setAlignment(
                    Pos.CENTER_LEFT
            );

            Label icon =
                    new Label(
                            "✕"
                    );

            icon.setStyle(
                    "-fx-text-fill:#FF6B6B;" +
                    "-fx-font-size:22px;" +
                    "-fx-font-weight:bold;"
            );

            Label text =
                    new Label(
                            "REJECTED".equalsIgnoreCase(status)
                                    ? "Order Rejected"
                                    : "Order Cancelled"
                    );

            text.setStyle(
                    "-fx-text-fill:#FF6B6B;" +
                    "-fx-font-size:14px;" +
                    "-fx-font-weight:bold;"
            );

            failed.getChildren().addAll(
                    icon,
                    text
            );

            box.getChildren().add(
                    failed
            );

            return box;
        }

        HBox timeline =
                new HBox();

        timeline.setAlignment(
                Pos.CENTER
        );

        timeline.setFillHeight(
                true
        );

        int currentStep =
                getCurrentStep(status);

        String[] steps = {
                "Placed",
                "Accepted",
                "Processing",
                "Out for Delivery",
                "Delivered"
        };

        for (int i = 0; i < steps.length; i++) {

            VBox step =
                    new VBox(6);

            step.setAlignment(
                    Pos.CENTER
            );

            Label circle =
                    new Label(
                            i <= currentStep
                                    ? "✓"
                                    : "○"
                    );

            if (i <= currentStep) {

                circle.setStyle(
                        "-fx-background-color:#68D34A;" +
                        "-fx-text-fill:#0D1117;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:30;" +
                        "-fx-min-width:30px;" +
                        "-fx-min-height:30px;" +
                        "-fx-alignment:center;"
                );

            } else {

                circle.setStyle(
                        "-fx-text-fill:#666666;" +
                        "-fx-font-size:24px;"
                );
            }

            Label text =
                    new Label(
                            steps[i]
                    );

            text.setWrapText(true);

            text.setAlignment(
                    Pos.CENTER
            );

            text.setStyle(
                    "-fx-text-fill:"
                            + (i <= currentStep
                            ? "#68D34A;"
                            : "#666666;")
                            +
                            "-fx-font-size:11px;" +
                            "-fx-font-weight:bold;"
            );

            step.getChildren().addAll(
                    circle,
                    text
            );

            HBox.setHgrow(
                    step,
                    Priority.ALWAYS
            );

            timeline.getChildren().add(
                    step
            );
        }

        box.getChildren().add(
                timeline
        );

        return box;
    }

    // =====================================================
    // CURRENT STEP
    // =====================================================

    private int getCurrentStep(
            String status) {

        if ("PENDING".equalsIgnoreCase(status)) {
            return 0;
        }

        if ("ACCEPTED".equalsIgnoreCase(status)) {
            return 1;
        }

        if ("PROCESSING".equalsIgnoreCase(status)) {
            return 2;
        }

        if ("OUT_FOR_DELIVERY".equalsIgnoreCase(status)) {
            return 3;
        }

        if ("DELIVERED".equalsIgnoreCase(status)) {
            return 4;
        }

        return 0;
    }

    // =====================================================
    // STATUS MESSAGE
    // =====================================================

    private Label createStatusMessage(
            String status) {

        String message;

        if ("PENDING".equalsIgnoreCase(status)) {

            message =
                    "⏳ Waiting for the farmer to accept your order.";

        } else if ("ACCEPTED".equalsIgnoreCase(status)) {

            message =
                    "✓ Farmer accepted your order.";

        } else if ("PROCESSING".equalsIgnoreCase(status)) {

            message =
                    "⚙ Your order is being prepared.";

        } else if ("OUT_FOR_DELIVERY".equalsIgnoreCase(status)) {

            message =
                    "🚚 Your order is on the way.";

        } else if ("DELIVERED".equalsIgnoreCase(status)) {

            message =
                    "🎉 Your order has been delivered.";

        } else if ("REJECTED".equalsIgnoreCase(status)) {

            message =
                    "✕ The farmer rejected this order.";

        } else if ("CANCELLED".equalsIgnoreCase(status)) {

            message =
                    "✕ This order was cancelled.";

        } else {

            message =
                    "Order status: "
                            + safe(status);
        }

        Label label =
                new Label(message);

        label.setWrapText(true);

        label.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:12px;" +
                "-fx-padding:10 12;" +
                "-fx-background-radius:7;"
        );

        return label;
    }

    // =====================================================
    // STATUS LABEL
    // =====================================================

    private Label createStatusLabel(
            String status) {

        String display =
                safe(status)
                        .replace("_", " ")
                        .toUpperCase();

        Label label =
                new Label(
                        "  "
                                + display
                                + "  "
                );

        String background = "#30363D";
        String text = "#AAAAAA";

        if ("PENDING".equalsIgnoreCase(status)) {

            background = "#3A3215";
            text = "#FFD54F";

        } else if ("ACCEPTED".equalsIgnoreCase(status)) {

            background = "#263A29";
            text = "#68D34A";

        } else if ("PROCESSING".equalsIgnoreCase(status)) {

            background = "#203448";
            text = "#64B5F6";

        } else if ("OUT_FOR_DELIVERY".equalsIgnoreCase(status)) {

            background = "#392B19";
            text = "#FFB74D";

        } else if ("DELIVERED".equalsIgnoreCase(status)) {

            background = "#263A29";
            text = "#68D34A";

            

        } else if ("REJECTED".equalsIgnoreCase(status) ||
                "CANCELLED".equalsIgnoreCase(status)) {

            background = "#3A2024";
            text = "#FF6B6B";
        }

        label.setStyle(
                "-fx-background-color:"
                        + background
                        + ";" +
                "-fx-text-fill:"
                        + text
                        + ";" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:12;" +
                "-fx-padding:6 10;"
        );

        return label;
    }

    // =====================================================
    // CANCEL ORDER
    // =====================================================

    private void cancelOrder(
            Order order) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Cancel Order"
        );

        confirmation.setHeaderText(
                "Cancel this order?"
        );

        confirmation.setContentText(
                "Are you sure you want to cancel this order?"
        );

        confirmation.showAndWait()
                .ifPresent(response -> {

                    if (response ==
                            javafx.scene.control.ButtonType.OK) {

                        try {

                            boolean cancelled =
                                    orderController.cancelOrder(
                                            order.getOrderId()
                                    );

                            if (cancelled) {

                                showAlert(
                                        Alert.AlertType.INFORMATION,
                                        "Order Cancelled",
                                        "Your order has been cancelled."
                                );

                                loadOrders();

                            } else {

                                showAlert(
                                        Alert.AlertType.ERROR,
                                        "Cancellation Failed",
                                        "The order could not be cancelled."
                                );
                            }

                        } catch (Exception e) {

                            e.printStackTrace();

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "Error",
                                    "Something went wrong while cancelling the order."
                            );
                        }
                    }
                });
    }

    // =====================================================
    // ITEM ROW
    // =====================================================

    private HBox createItemRow(
            Map<String, Object> item) {

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(10)
        );

        row.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-background-radius:7;"
        );

        String name =
                safeObject(
                        item.get("productName")
                );

        double quantity =
                getDouble(
                        item.get("quantity")
                );

        double itemTotal =
                getDouble(
                        item.get("itemTotal")
                );

        String unit =
                safeObject(
                        item.get("unit")
                );

        Label nameLabel =
                new Label(name);

        nameLabel.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        Label quantityLabel =
                new Label(
                        "× "
                                + formatNumber(quantity)
                                + " "
                                + unit
                );

        quantityLabel.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:12px;"
        );

        Label priceLabel =
                new Label(
                        "₹"
                                + String.format(
                                "%.2f",
                                itemTotal
                        )
                );

        priceLabel.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        HBox.setHgrow(
                nameLabel,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                nameLabel,
                quantityLabel,
                priceLabel
        );

        return row;
    }

    // =====================================================
    // SEPARATOR
    // =====================================================

    private Label createSeparator() {

        Label separator =
                new Label();

        separator.setPrefHeight(1);

        separator.setMaxWidth(
                Double.MAX_VALUE
        );

        separator.setStyle(
                "-fx-background-color:#30363D;"
        );

        return separator;
    }

    // =====================================================
    // SAFE
    // =====================================================

    private String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }

    private String safeObject(
            Object value) {

        if (value == null) {
            return "Not provided";
        }

        String text =
                String.valueOf(value);

        if (text.trim().isEmpty()) {
            return "Not provided";
        }

        return text;
    }

    // =====================================================
    // NUMBER
    // =====================================================

    private double getDouble(
            Object value) {

        if (value instanceof Number) {

            return ((Number) value)
                    .doubleValue();
        }

        try {

            return Double.parseDouble(
                    String.valueOf(value)
            );

        } catch (Exception e) {

            return 0;
        }
    }

    private String formatNumber(
            double number) {

        if (number == (long) number) {

            return String.valueOf(
                    (long) number
            );
        }

        return String.format(
                "%.2f",
                number
        );
    }

    // =====================================================
    // ALERT
    // =====================================================

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