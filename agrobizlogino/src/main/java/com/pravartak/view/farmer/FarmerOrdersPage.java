package com.pravartak.view.farmer;

import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class FarmerOrdersPage {

    private final int farmerId;

    private final OrderController orderController;

    private VBox ordersContainer;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FarmerOrdersPage(int farmerId) {

        this.farmerId = farmerId;

        this.orderController =
                new OrderController();
    }

    // =====================================================
    // PAGE
    // =====================================================

    public BorderPane getOrdersPage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // NAVBAR
        // =================================================

        // try {

        //     NavBar navBar =
        //             new NavBar(
        //                     farmerId,
        //                     LoginPage.getLoggedInFirebaseUid()
        //             );

        //     root.setTop(
        //             navBar.createNavbar(
        //                     "My Orders"
        //             )
        //     );

        // } catch (Exception e) {

        //     e.printStackTrace();

        //     System.out.println(
        //             "Navbar could not be loaded."
        //     );
        // }

        // =================================================
        // ORDERS CONTAINER
        // =================================================

        ordersContainer =
                new VBox(18);

        ordersContainer.setPadding(
                new Insets(
                        25,
                        35,
                        30,
                        35
                )
        );

        ordersContainer.setAlignment(
                Pos.TOP_CENTER
        );

        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scroll =
                new ScrollPane(
                        ordersContainer
                );

        scroll.setFitToWidth(true);

        scroll.setFitToHeight(false);

        scroll.setStyle(
                "-fx-background:#050B0A;" +
                "-fx-background-color:#050B0A;" +
                "-fx-control-inner-background:#050B0A;" +
                "-fx-border-color:transparent;"
        );

        root.setCenter(scroll);

        // =================================================
        // FOOTER
        // =================================================

        // root.setBottom(
        //         new Footer().createFooter()
        // );

        // =================================================
        // LOAD
        // =================================================

        loadOrders();

        return root;
    }

    // =====================================================
    // LOAD ORDERS
    // =====================================================

    private void loadOrders() {

        ordersContainer
                .getChildren()
                .clear();

        try {

            List<Order> orders =
                    orderController
                            .getFarmerOrders(
                                    farmerId
                            );

            if (orders == null ||
                    orders.isEmpty()) {

                showEmptyState();

                return;
            }

            // =================================================
            // PAGE HEADER
            // =================================================

            VBox pageHeader =
                    new VBox(5);

            pageHeader.setMaxWidth(
                    1000
            );

            Label title =
                    new Label(
                            "📋 My Orders"
                    );

            title.setStyle(
                    "-fx-text-fill:#EEEEEE;" +
                    "-fx-font-size:30px;" +
                    "-fx-font-weight:bold;"
            );

            Label subtitle =
                    new Label(
                            "Manage accepted orders and update their delivery status."
                    );

            subtitle.setStyle(
                    "-fx-text-fill:#888888;" +
                    "-fx-font-size:14px;"
            );

            Label count =
                    new Label(
                            orders.size()
                                    + " Active Order"
                                    + (orders.size() == 1
                                    ? ""
                                    : "s")
                    );

            count.setStyle(
                    "-fx-text-fill:#68D34A;" +
                    "-fx-font-size:14px;" +
                    "-fx-font-weight:bold;"
            );

            pageHeader
                    .getChildren()
                    .addAll(
                            title,
                            subtitle,
                            count
                    );

            ordersContainer
                    .getChildren()
                    .add(
                            pageHeader
                    );

            // =================================================
            // ORDER CARDS
            // =================================================

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
                    "Error",
                    "Unable to load your orders."
            );
        }
    }

    // =====================================================
    // EMPTY STATE
    // =====================================================

    private void showEmptyState() {

        VBox wrapper =
                new VBox(12);

        wrapper.setAlignment(
                Pos.TOP_CENTER
        );

        wrapper.setPadding(
                new Insets(40)
        );

        Label title =
                new Label(
                        "📋 My Orders"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );

        Label icon =
                new Label(
                        "📦"
                );

        icon.setStyle(
                "-fx-font-size:50px;"
        );

        Label message =
                new Label(
                        "No active orders"
                );

        message.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:22px;" +
                "-fx-font-weight:bold;"
        );

        Label subMessage =
                new Label(
                        "Accepted buyer orders will appear here."
                );

        subMessage.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:14px;"
        );

        wrapper
                .getChildren()
                .addAll(
                        title,
                        icon,
                        message,
                        subMessage
                );

        ordersContainer
                .getChildren()
                .add(
                        wrapper
                );
    }

    // =====================================================
    // ORDER CARD
    // =====================================================

    private VBox createOrderCard(
            Order order) {

        VBox card =
                new VBox(15);

        card.setMaxWidth(
                1000
        );

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

        header
                .getChildren()
                .addAll(
                        orderId,
                        status
                );

        // =================================================
        // BUYER
        // =================================================

        VBox buyerBox =
                new VBox(7);

        Label buyerHeading =
                new Label(
                        "Buyer Information"
                );

        buyerHeading.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        Label buyerName =
                new Label(
                        "👤 "
                                + safe(
                                order.getBuyerName()
                        )
                );

        Label buyerPhone =
                new Label(
                        "📞 "
                                + safe(
                                order.getBuyerPhone()
                        )
                );

        Label buyerAddress =
                new Label(
                        "📍 "
                                + safe(
                                order.getBuyerAddress()
                        )
                );

        buyerName.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );

        buyerPhone.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );

        buyerAddress.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );

        buyerAddress.setWrapText(true);

        buyerBox
                .getChildren()
                .addAll(
                        buyerHeading,
                        buyerName,
                        buyerPhone,
                        buyerAddress
                );

        // =================================================
        // ITEMS
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

        itemsBox
                .getChildren()
                .add(
                        itemsHeading
                );

        List<Map<String, Object>> items =
                order.getItems();

        if (items != null) {

            for (
                    Map<String, Object> item :
                    items
            ) {

                itemsBox
                        .getChildren()
                        .add(
                                createItemRow(
                                        item
                                )
                        );
            }
        }

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

        paymentRow
                .getChildren()
                .addAll(
                        payment,
                        total
                );

        // =================================================
        // ACTIONS
        // =================================================

        HBox actionBox =
                new HBox(10);

        actionBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        addStatusButtons(
                actionBox,
                order
        );

        card.getChildren().addAll(
                header,
                createSeparator(),
                buyerBox,
                createSeparator(),
                itemsBox,
                createSeparator(),
                paymentRow,
                actionBox
        );

        return card;
    }

    // =====================================================
    // STATUS BUTTONS
    // =====================================================

    private void addStatusButtons(
            HBox box,
            Order order) {

        String status =
                safe(
                        order.getOrderStatus()
                );

        // =================================================
        // ACCEPTED
        // =================================================

        if ("ACCEPTED".equalsIgnoreCase(
                status
        )) {

            Button processing =
                    createGreenButton(
                            "⚙ Mark Processing"
                    );

            processing.setOnAction(
                    e -> updateStatus(
                            order,
                            "PROCESSING"
                    )
            );

            box.getChildren()
                    .add(
                            processing
                    );

        }

        // =================================================
        // PROCESSING
        // =================================================

        else if (
                "PROCESSING".equalsIgnoreCase(
                        status
                )
        ) {

            Button delivery =
                    createGreenButton(
                            "🚚 Out for Delivery"
                    );

            delivery.setOnAction(
                    e -> updateStatus(
                            order,
                            "OUT_FOR_DELIVERY"
                    )
            );

            box.getChildren()
                    .add(
                            delivery
                    );
        }

        // =================================================
        // OUT FOR DELIVERY
        // =================================================

        else if (
                "OUT_FOR_DELIVERY".equalsIgnoreCase(
                        status
                )
        ) {

            Button delivered =
                    createGreenButton(
                            "✓ Mark Delivered"
                    );

            delivered.setOnAction(
                    e -> updateStatus(
                            order,
                            "DELIVERED"
                    )
            );

            box.getChildren()
                    .add(
                            delivered
                    );
        }

        // =================================================
        // DELIVERED
        // =================================================

        else if (
                "DELIVERED".equalsIgnoreCase(
                        status
                )
        ) {

            Label completed =
                    new Label(
                            "✓ Order Completed"
                    );

            completed.setStyle(
                    "-fx-text-fill:#68D34A;" +
                    "-fx-font-size:13px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-padding:10 15;"
            );

            box.getChildren()
                    .add(
                            completed
                    );
        }
    }

    // =====================================================
    // UPDATE STATUS
    // =====================================================

    private void updateStatus(
            Order order,
            String newStatus) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Update Order"
        );

        confirmation.setHeaderText(
                "Change order status?"
        );

        confirmation.setContentText(
                "Change status to "
                        + newStatus.replace(
                        "_",
                        " "
                )
                        + "?"
        );

        confirmation
                .showAndWait()
                .ifPresent(response -> {

                    if (response !=
                            javafx.scene.control.ButtonType.OK) {

                        return;
                    }

                    try {

                        boolean updated =
                                orderController
                                        .updateOrderStatus(
                                                order.getOrderId(),
                                                newStatus
                                        );

                        if (updated) {

                            showAlert(
                                    Alert.AlertType.INFORMATION,
                                    "Order Updated",
                                    "Order status changed to "
                                            + newStatus.replace(
                                            "_",
                                            " "
                                    )
                            );

                            loadOrders();

                        } else {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "Update Failed",
                                    "The order status could not be updated."
                            );
                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Error",
                                "Something went wrong while updating the order."
                        );
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

        String nameText =
                safeObject(
                        item.get(
                                "productName"
                        )
                );

        double quantity =
                getDouble(
                        item.get(
                                "quantity"
                        )
                );

        double itemTotal =
                getDouble(
                        item.get(
                                "itemTotal"
                        )
                );

        String unit =
                safeObject(
                        item.get(
                                "unit"
                        )
                );

        Label name =
                new Label(
                        nameText
                );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        Label quantityLabel =
                new Label(
                        "× "
                                + formatNumber(
                                quantity
                        )
                                + " "
                                + unit
                );

        quantityLabel.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:12px;"
        );

        Label price =
                new Label(
                        "₹"
                                + String.format(
                                "%.2f",
                                itemTotal
                        )
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        HBox.setHgrow(
                name,
                Priority.ALWAYS
        );

        row.getChildren()
                .addAll(
                        name,
                        quantityLabel,
                        price
                );

        return row;
    }

    // =====================================================
    // STATUS LABEL
    // =====================================================

    private Label createStatusLabel(
            String status) {

        String text =
                safe(status)
                        .replace(
                                "_",
                                " "
                        );

        Label label =
                new Label(
                        "  "
                                + text
                                .toUpperCase()
                                + "  "
                );

        label.setStyle(
                "-fx-background-color:#263A29;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:12;" +
                "-fx-padding:6 10;"
        );

        return label;
    }

    // =====================================================
    // GREEN BUTTON
    // =====================================================

    private Button createGreenButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(
                42
        );

        button.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:9 18;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =====================================================
    // SEPARATOR
    // =====================================================

    private Label createSeparator() {

        Label separator =
                new Label();

        separator.setPrefHeight(
                1
        );

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

    // =====================================================
    // SAFE OBJECT
    // =====================================================

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
    // DOUBLE
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

    // =====================================================
    // FORMAT NUMBER
    // =====================================================

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