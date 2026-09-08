
package com.pravartak.view.farmer;

import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.Map;

public class FarmerOrderRequestsPage {

    private final int farmerId;
    private final OrderController orderController;

    private VBox ordersContainer;

    public FarmerOrderRequestsPage(int farmerId) {
        this.farmerId = farmerId;
        this.orderController = new OrderController();
    }

    // =========================================================
    // PAGE
    // =========================================================

    public BorderPane getOrderRequestsPage() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // ================= TOP NAVBAR =================

        try {

            NavBar navBar = new NavBar(
                    farmerId,
                    LoginPage.getLoggedInFirebaseUid()
            );

            root.setTop(
                    navBar.createNavbar("Order Requests")
            );

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "Navbar could not be loaded."
            );
        }

        // ================= HEADER =================

        Label title =
                new Label("📦 Order Requests");

        title.setTextFill(
                Color.WHITE
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        Label subtitle =
                new Label(
                        "Review incoming orders from buyers and accept or reject them."
                );

        subtitle.setTextFill(
                Color.web("#A8B3C2")
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        VBox header =
                new VBox(
                        7,
                        title,
                        subtitle
                );

        header.setPadding(
                new Insets(
                        25,
                        35,
                        20,
                        35
                )
        );

        // ================= ORDERS CONTAINER =================

        ordersContainer =
                new VBox(18);

        ordersContainer.setPadding(
                new Insets(
                        0,
                        35,
                        30,
                        35
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        ordersContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        VBox center =
                new VBox(
                        header,
                        scrollPane
                );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.setCenter(
                center
        );

        // ================= FOOTER =================

        try {

            Footer footer =
                    new Footer();

            root.setBottom(
                    footer.createFooter()
            );

        } catch (Exception e) {

            System.out.println(
                    "Footer could not be loaded."
            );
        }

        loadOrders();

        return root;
    }

    // =========================================================
    // LOAD PENDING ORDERS
    // =========================================================

    private void loadOrders() {

        ordersContainer
                .getChildren()
                .clear();

        try {

            List<Order> orders =
                    orderController
                            .getFarmerOrderRequests(
                                    farmerId
                            );

            if (orders == null ||
                    orders.isEmpty()) {

                Label empty =
                        new Label(
                                "📦  No new order requests"
                        );

                empty.setTextFill(
                        Color.web("#A8B3C2")
                );

                empty.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                StackPane emptyBox =
                        new StackPane(
                                empty
                        );

                emptyBox.setPadding(
                        new Insets(80)
                );

                ordersContainer
                        .getChildren()
                        .add(emptyBox);

                return;
            }

            Label count =
                    new Label(
                            orders.size()
                                    + " New Order"
                                    + (orders.size() == 1
                                    ? ""
                                    : "s")
                    );

            count.setTextFill(
                    Color.web("#8BC34A")
            );

            count.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            16
                    )
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

            Label error =
                    new Label(
                            "Unable to load order requests."
                    );

            error.setTextFill(
                    Color.web("#FF6B6B")
            );

            error.setFont(
                    Font.font(
                            "Arial",
                            16
                    )
            );

            ordersContainer
                    .getChildren()
                    .add(error);
        }
    }

    // =========================================================
    // ORDER CARD
    // =========================================================

    private VBox createOrderCard(Order order) {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(22)
        );

        card.setMaxWidth(
                1100
        );

        card.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#263238;" +
                "-fx-border-radius:14;" +
                "-fx-border-width:1;"
        );

        // ================= ORDER HEADER =================

        HBox orderHeader =
                new HBox();

        orderHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox orderInfo =
                new VBox(5);

        Label orderTitle =
                new Label(
                        "Order Request"
                );

        orderTitle.setTextFill(
                Color.WHITE
        );

        orderTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label orderId =
                new Label(
                        "Order ID: "
                                + safe(
                                order.getOrderId()
                        )
                );

        orderId.setTextFill(
                Color.web("#8BC34A")
        );

        orderId.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        orderInfo
                .getChildren()
                .addAll(
                        orderTitle,
                        orderId
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label status =
                new Label(
                        "PENDING"
                );

        status.setTextFill(
                Color.web("#FFD166")
        );

        status.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        status.setStyle(
                "-fx-background-color:#3A321C;" +
                "-fx-background-radius:20;" +
                "-fx-padding:7 15;"
        );

        orderHeader
                .getChildren()
                .addAll(
                        orderInfo,
                        spacer,
                        status
                );

        // ================= BUYER =================

        Label buyerTitle =
                sectionTitle(
                        "Buyer Information"
                );

        GridPane buyerGrid =
                new GridPane();

        buyerGrid.setHgap(35);
        buyerGrid.setVgap(10);

        addInfo(
                buyerGrid,
                "Buyer Name",
                safe(order.getBuyerName()),
                0,
                0
        );

        addInfo(
                buyerGrid,
                "Phone",
                safe(order.getBuyerPhone()),
                1,
                0
        );

        addInfo(
                buyerGrid,
                "Delivery Address",
                safe(order.getBuyerAddress()),
                0,
                1
        );

        addInfo(
                buyerGrid,
                "Payment",
                safe(order.getPaymentMethod()),
                1,
                1
        );

        // ================= PRODUCTS =================

        Label productTitle =
                sectionTitle(
                        "Ordered Products"
                );

        VBox productsBox =
                new VBox(8);

        if (order.getItems() != null) {

            for (
                    Map<String, Object> item :
                    order.getItems()
            ) {

                String productName =
                        String.valueOf(
                                item.getOrDefault(
                                        "productName",
                                        "Product"
                                )
                        );

                double quantity =
                        getDouble(
                                item.get("quantity")
                        );

                String unit =
                        String.valueOf(
                                item.getOrDefault(
                                        "unit",
                                        ""
                                )
                        );

                double itemTotal =
                        getDouble(
                                item.get("itemTotal")
                        );

                Label product =
                        new Label(
                                "• "
                                        + productName
                                        + "   × "
                                        + formatNumber(quantity)
                                        + " "
                                        + unit
                                        + "   —   ₹"
                                        + String.format(
                                        "%.2f",
                                        itemTotal
                                )
                        );

                product.setTextFill(
                        Color.web("#D7DEE8")
                );

                product.setFont(
                        Font.font(
                                "Arial",
                                14
                        )
                );

                productsBox
                        .getChildren()
                        .add(product);
            }
        }

        // ================= TOTAL =================

        HBox totalBox =
                new HBox();

        totalBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label totalText =
                new Label(
                        "Total Amount: "
                );

        totalText.setTextFill(
                Color.web("#A8B3C2")
        );

        totalText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label total =
                new Label(
                        "₹"
                                + String.format(
                                "%.2f",
                                order.getTotalAmount()
                        )
                );

        total.setTextFill(
                Color.web("#8BC34A")
        );

        total.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        totalBox
                .getChildren()
                .addAll(
                        totalText,
                        total
                );

        // ================= BUTTONS =================

        HBox buttons =
                new HBox(12);

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button rejectButton =
                new Button(
                        "❌ Reject"
                );

        rejectButton.setPrefWidth(120);
        rejectButton.setPrefHeight(42);

        rejectButton.setStyle(
                "-fx-background-color:#3A2024;" +
                "-fx-text-fill:#FF6B6B;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );

        Button acceptButton =
                new Button(
                        "✅ Accept Order"
                );

        acceptButton.setPrefWidth(155);
        acceptButton.setPrefHeight(42);

        acceptButton.setStyle(
                "-fx-background-color:#2E7D32;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );

        acceptButton.setOnAction(
                e -> acceptOrder(order)
        );

        rejectButton.setOnAction(
                e -> rejectOrder(order)
        );

        buttons
                .getChildren()
                .addAll(
                        rejectButton,
                        acceptButton
                );

        card.getChildren().addAll(
                orderHeader,
                separator(),
                buyerTitle,
                buyerGrid,
                productTitle,
                productsBox,
                separator(),
                totalBox,
                buttons
        );

        return card;
    }

    // =========================================================
    // ACCEPT
    // =========================================================

    private void acceptOrder(Order order) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Accept Order"
        );

        confirmation.setHeaderText(
                "Accept this order?"
        );

        confirmation.setContentText(
                "The buyer will be notified that the order has been accepted."
        );

        confirmation
                .showAndWait()
                .ifPresent(response -> {

                    if (response ==
                            ButtonType.OK) {

                        boolean success =
                                orderController
                                        .acceptOrder(
                                                order.getOrderId()
                                        );

                        if (success) {

                            showAlert(
                                    Alert.AlertType.INFORMATION,
                                    "AgroBiz",
                                    "Order accepted successfully.\n\n"
                                            + "The buyer will be notified."
                            );

                            loadOrders();

                        } else {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "AgroBiz",
                                    "Unable to accept the order."
                            );
                        }
                    }
                });
    }

    // =========================================================
    // REJECT
    // =========================================================

    private void rejectOrder(Order order) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Reject Order"
        );

        confirmation.setHeaderText(
                "Reject this order?"
        );

        confirmation.setContentText(
                "This order request will be rejected."
        );

        confirmation
                .showAndWait()
                .ifPresent(response -> {

                    if (response ==
                            ButtonType.OK) {

                        boolean success =
                                orderController
                                        .rejectOrder(
                                                order.getOrderId()
                                        );

                        if (success) {

                            showAlert(
                                    Alert.AlertType.INFORMATION,
                                    "AgroBiz",
                                    "Order rejected."
                            );

                            loadOrders();

                        } else {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "AgroBiz",
                                    "Unable to reject the order."
                            );
                        }
                    }
                });
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private Label sectionTitle(String text) {

        Label label =
                new Label(text);

        label.setTextFill(
                Color.WHITE
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        return label;
    }

    private void addInfo(
            GridPane grid,
            String title,
            String value,
            int column,
            int row
    ) {

        VBox box =
                new VBox(3);

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web("#7F8C9A")
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setTextFill(
                Color.web("#E6EDF3")
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        box.getChildren()
                .addAll(
                        titleLabel,
                        valueLabel
                );

        grid.add(
                box,
                column,
                row
        );
    }

    private Separator separator() {

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color:#263238;"
        );

        return separator;
    }

    private double getDouble(Object value) {

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

    private String safe(String value) {

        return value == null ||
                value.trim().isEmpty()
                ? "Not provided"
                : value;
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}