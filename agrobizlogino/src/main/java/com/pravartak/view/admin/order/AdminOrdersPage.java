package com.pravartak.view.admin.order;

import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.Map;

public class AdminOrdersPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BACKGROUND = "#080C0D";
    private static final String CARD = "#111719";
    private static final String CARD_HOVER = "#172022";
    private static final String BORDER = "#263336";

    private static final String TEXT_PRIMARY = "#F4F7F7";
    private static final String TEXT_SECONDARY = "#91A0A3";

    private static final String GREEN = "#68D34A";
    private static final String GREEN_DARK = "#193D2A";

    private static final String BLUE = "#5B9DF9";
    private static final String ORANGE = "#F2A65A";
    private static final String RED = "#EF6B73";

    private VBox ordersContainer;

    private Label totalOrdersLabel;
    private Label pendingLabel;
    private Label deliveredLabel;
    private Label cancelledLabel;

    private TextField searchField;

    private ComboBox<String> statusFilter;

    // =========================================================
    // GET PAGE
    // =========================================================

    public VBox getOrdersPage() {

        VBox root = new VBox(20);

        root.setPadding(
                new Insets(28, 32, 28, 32)
        );

        root.setStyle(
                "-fx-background-color:" + BACKGROUND + ";"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label title = new Label(
                "Orders Management"
        );

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        28
                )
        );

        title.setTextFill(
                Color.web(TEXT_PRIMARY)
        );

        Label subtitle = new Label(
                "Manage and monitor all buyer orders"
        );

        subtitle.setFont(
                Font.font(14)
        );

        subtitle.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button refreshButton =
                new Button("⟳  Refresh");

        refreshButton.setPrefHeight(40);

        refreshButton.setStyle(
                "-fx-background-color:" + GREEN_DARK + ";" +
                "-fx-text-fill:" + GREEN + ";" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:" + GREEN + ";" +
                "-fx-border-radius:8;" +
                "-fx-cursor:hand;"
        );

        refreshButton.setOnAction(
                e -> loadOrders()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                refreshButton
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        HBox stats =
                createStatsCards();

        // =====================================================
        // SEARCH + FILTER
        // =====================================================

        HBox filterBar =
                createFilterBar();

        // =====================================================
        // ORDERS CONTAINER
        // =====================================================

        ordersContainer =
                new VBox(14);

        ordersContainer.setFillWidth(true);

        ScrollPane scrollPane =
                new ScrollPane(ordersContainer);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color:" + BACKGROUND + ";" +
                "-fx-background:" + BACKGROUND + ";"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                header,
                stats,
                filterBar,
                scrollPane
        );

        loadOrders();

        return root;
    }

    // =========================================================
    // STAT CARDS
    // =========================================================

    private HBox createStatsCards() {

        HBox box = new HBox(16);

        box.setFillHeight(true);

        totalOrdersLabel =
                new Label("0");

        pendingLabel =
                new Label("0");

        deliveredLabel =
                new Label("0");

        cancelledLabel =
                new Label("0");

        box.getChildren().addAll(
                createStatCard(
                        "Total Orders",
                        totalOrdersLabel,
                        BLUE
                ),
                createStatCard(
                        "Pending",
                        pendingLabel,
                        ORANGE
                ),
                createStatCard(
                        "Delivered",
                        deliveredLabel,
                        GREEN
                ),
                createStatCard(
                        "Cancelled",
                        cancelledLabel,
                        RED
                )
        );

        return box;
    }

    private VBox createStatCard(
            String title,
            Label value,
            String accent) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(18)
        );

        card.setPrefHeight(90);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        titleLabel.setFont(
                Font.font(13)
        );

        value.setTextFill(
                Color.web(accent)
        );

        value.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        25
                )
        );

        card.getChildren().addAll(
                titleLabel,
                value
        );

        return card;
    }

    // =========================================================
    // SEARCH + FILTER
    // =========================================================

    private HBox createFilterBar() {

        HBox bar = new HBox(12);

        bar.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "⌕  Search by order ID, buyer or farmer..."
        );

        searchField.setPrefWidth(400);

        searchField.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                "-fx-prompt-text-fill:#687477;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;" +
                "-fx-padding:10 14;"
        );

        searchField.textProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                loadOrders()
                );

        statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All Status",
                "PENDING",
                "ACCEPTED",
                "PROCESSING",
                "OUT_FOR_DELIVERY",
                "DELIVERED",
                "REJECTED",
                "CANCELLED"
        );

        statusFilter.setValue(
                "All Status"
        );

        statusFilter.setPrefWidth(190);

        statusFilter.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );

        statusFilter.setOnAction(
                e -> loadOrders()
        );

        bar.getChildren().addAll(
                searchField,
                statusFilter
        );

        return bar;
    }

    // =========================================================
    // LOAD ORDERS
    // =========================================================

    private void loadOrders() {

        if (ordersContainer == null) {
            return;
        }

        ordersContainer.getChildren().clear();

        try {

            OrderController controller =
                    new OrderController();

            List<Order> orders =
                    controller.getAllOrders();

            updateStatistics(orders);

            String search =
                    searchField == null
                            ? ""
                            : searchField.getText()
                                    .trim()
                                    .toLowerCase();

            String selectedStatus =
                    statusFilter == null
                            ? "All Status"
                            : statusFilter.getValue();

            int displayedOrders = 0;

            for (Order order : orders) {

                if (order == null) {
                    continue;
                }

                String status =
                        order.getOrderStatus() == null
                                ? ""
                                : order.getOrderStatus();

                // ---------------------------------------------
                // STATUS FILTER
                // ---------------------------------------------

                if (!"All Status".equals(selectedStatus)
                        && !selectedStatus.equalsIgnoreCase(status)) {

                    continue;
                }

                // ---------------------------------------------
                // SEARCH FILTER
                // ---------------------------------------------

                if (!search.isEmpty()) {

                  String orderId =
        safe(order.getOrderId()).toLowerCase();

String buyer =
        safe(order.getBuyerName()).toLowerCase();

String farmer =
        safe(order.getFarmerName()).toLowerCase();

                    if (!orderId.contains(search)
                            && !buyer.contains(search)
                            && !farmer.contains(search)) {

                        continue;
                    }
                }

                ordersContainer.getChildren().add(
                        createOrderCard(order)
                );

                displayedOrders++;
            }

            if (displayedOrders == 0) {

                Label empty =
                        new Label(
                                "No orders found."
                        );

                empty.setTextFill(
                        Color.web(TEXT_SECONDARY)
                );

                empty.setFont(
                        Font.font(16)
                );

                empty.setPadding(
                        new Insets(40)
                );

                ordersContainer.getChildren().add(
                        empty
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load orders."
                    );

            error.setTextFill(
                    Color.web(RED)
            );

            ordersContainer.getChildren().add(
                    error
            );
        }
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics(
            List<Order> orders) {

        int total = 0;
        int pending = 0;
        int delivered = 0;
        int cancelled = 0;

        for (Order order : orders) {

            if (order == null) {
                continue;
            }

            total++;

            String status =
                    order.getOrderStatus();

            if (status == null) {
                continue;
            }

            if ("PENDING".equalsIgnoreCase(status)) {
                pending++;
            }

            if ("DELIVERED".equalsIgnoreCase(status)) {
                delivered++;
            }

            if ("CANCELLED".equalsIgnoreCase(status)
                    || "REJECTED".equalsIgnoreCase(status)) {

                cancelled++;
            }
        }

        totalOrdersLabel.setText(
                String.valueOf(total)
        );

        pendingLabel.setText(
                String.valueOf(pending)
        );

        deliveredLabel.setText(
                String.valueOf(delivered)
        );

        cancelledLabel.setText(
                String.valueOf(cancelled)
        );
    }

    // =========================================================
    // ORDER CARD
    // =========================================================

    private VBox createOrderCard(
            Order order) {

        VBox card =
                new VBox(14);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12;"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox orderInfo =
                new VBox(5);

        Label orderId =
                new Label(
                        "Order #" +
                        safe(order.getOrderId())
                );

        orderId.setTextFill(
                Color.web(TEXT_PRIMARY)
        );

        orderId.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        17
                )
        );

        Label date =
                new Label(
                        formatDate(order)
                );

        date.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        date.setFont(
                Font.font(12)
        );

        orderInfo.getChildren().addAll(
                orderId,
                date
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label status =
                createStatusLabel(
                        order.getOrderStatus()
                );

        header.getChildren().addAll(
                orderInfo,
                spacer,
                status
        );

        // =====================================================
        // BUYER / FARMER
        // =====================================================

        HBox people =
                new HBox(35);

        people.getChildren().addAll(
                createInfoBox(
                        "BUYER",
                        order.getBuyerName(),
                        order.getBuyerPhone()
                ),
                createInfoBox(
                        "FARMER",
                        order.getFarmerName(),
                        "Farmer ID: " +
                                order.getFarmerId()
                )
        );

        // =====================================================
        // PRODUCTS
        // =====================================================

        VBox products =
                new VBox(7);

        Label productsTitle =
                new Label("PRODUCTS");

        productsTitle.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        productsTitle.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        11
                )
        );

        products.getChildren().add(
                productsTitle
        );

        if (order.getItems() != null) {

            for (Map<String, Object> item :
                    order.getItems()) {

                String productName =
                        String.valueOf(
                                item.getOrDefault(
                                        "productName",
                                        "Product"
                                )
                        );

                String quantity =
                        String.valueOf(
                                item.getOrDefault(
                                        "quantity",
                                        0
                                )
                        );

                String unit =
                        String.valueOf(
                                item.getOrDefault(
                                        "unit",
                                        ""
                                )
                        );

                Object itemTotalObject =
                        item.get("itemTotal");

                double itemTotal = 0;

                if (itemTotalObject instanceof Number) {
                    itemTotal =
                            ((Number) itemTotalObject)
                                    .doubleValue();
                }

                Label product =
                        new Label(
                                "• " +
                                productName +
                                "   × " +
                                quantity +
                                (unit.isEmpty()
                                        ? ""
                                        : " " + unit) +
                                "   ₹" +
                                String.format(
                                        "%.2f",
                                        itemTotal
                                )
                        );

                product.setTextFill(
                        Color.web(TEXT_PRIMARY)
                );

                product.setFont(
                        Font.font(13)
                );

                products.getChildren().add(
                        product
                );
            }
        }

        // =====================================================
        // PAYMENT + TOTAL
        // =====================================================

        HBox payment =
                new HBox(30);

        payment.setAlignment(
                Pos.CENTER_LEFT
        );

        Label paymentLabel =
                new Label(
                        "Payment: " +
                        safe(order.getPaymentMethod()) +
                        "  •  " +
                        safe(order.getPaymentStatus())
                );

        paymentLabel.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        paymentLabel.setFont(
                Font.font(13)
        );

        Region paymentSpacer =
                new Region();

        HBox.setHgrow(
                paymentSpacer,
                Priority.ALWAYS
        );

        Label total =
                new Label(
                        "₹" +
                        String.format(
                                "%.2f",
                                order.getTotalAmount()
                        )
                );

        total.setTextFill(
                Color.web(GREEN)
        );

        total.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        20
                )
        );

        payment.getChildren().addAll(
                paymentLabel,
                paymentSpacer,
                total
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        HBox actions =
                new HBox(10);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button detailsButton =
                new Button("View Details");

        detailsButton.setStyle(
                buttonStyle(BLUE)
        );

        detailsButton.setOnAction(
                e -> showOrderDetails(order)
        );

        // actions.getChildren().add(
        //         detailsButton
        // );

        // Admin can change status
        if (order.getOrderStatus() != null
                && !"DELIVERED".equalsIgnoreCase(
                        order.getOrderStatus())
                && !"CANCELLED".equalsIgnoreCase(
                        order.getOrderStatus())
                && !"REJECTED".equalsIgnoreCase(
                        order.getOrderStatus())) {

            ComboBox<String> statusBox =
                    new ComboBox<>();

            statusBox.getItems().addAll(
                    "PENDING",
                    "ACCEPTED",
                    "PROCESSING",
                    "OUT_FOR_DELIVERY",
                    "DELIVERED",
                    "CANCELLED"
            );

            statusBox.setValue(
                    order.getOrderStatus()
            );

            statusBox.setStyle(
                    "-fx-background-color:" + CARD_HOVER + ";" +
                    "-fx-text-fill:" + TEXT_PRIMARY + ";" +
                    "-fx-border-color:" + BORDER + ";" +
                    "-fx-border-radius:7;" +
                    "-fx-background-radius:7;"
            );

            Button updateButton =
                    new Button("Update");

            updateButton.setStyle(
                    buttonStyle(GREEN)
            );

            updateButton.setOnAction(e -> {

                String newStatus =
                        statusBox.getValue();

                if (newStatus == null) {
                    return;
                }

                OrderController controller =
                        new OrderController();

                boolean success =
                        controller.updateOrderStatus(
                                order.getOrderId(),
                                newStatus
                        );

                if (success) {

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Order Updated",
                            "Order status changed to "
                                    + newStatus
                    );

                    loadOrders();

                } else {

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Update Failed",
                            "Unable to update order status."
                    );
                }
            });

            actions.getChildren().addAll(
                    statusBox,
                    updateButton
            );
        }

        card.getChildren().addAll(
                header,
                new Separator(),
                people,
                products,
                payment,
                actions
        );

        return card;
    }

    // =========================================================
    // INFO BOX
    // =========================================================

    private VBox createInfoBox(
            String title,
            String value,
            String secondary) {

        VBox box =
                new VBox(4);

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        titleLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        11
                )
        );

        Label valueLabel =
                new Label(
                        safe(value)
                );

        valueLabel.setTextFill(
                Color.web(TEXT_PRIMARY)
        );

        valueLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        Label secondaryLabel =
                new Label(
                        safe(secondary)
                );

        secondaryLabel.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        secondaryLabel.setFont(
                Font.font(12)
        );

        box.getChildren().addAll(
                titleLabel,
                valueLabel,
                secondaryLabel
        );

        return box;
    }

    // =========================================================
    // STATUS LABEL
    // =========================================================

    private Label createStatusLabel(
            String status) {

        if (status == null) {
            status = "UNKNOWN";
        }

        String background = GREEN_DARK;
        String text = GREEN;

        if ("PENDING".equalsIgnoreCase(status)) {

            background = "#4A3820";
            text = ORANGE;

        } else if ("PROCESSING".equalsIgnoreCase(status)) {

            background = "#203A4A";
            text = BLUE;

        } else if ("OUT_FOR_DELIVERY".equalsIgnoreCase(status)) {

            background = "#293A25";
            text = GREEN;

        } else if ("REJECTED".equalsIgnoreCase(status)
                || "CANCELLED".equalsIgnoreCase(status)) {

            background = "#48272A";
            text = RED;
        }

        Label label =
                new Label(
                        status.replace(
                                "_",
                                " "
                        )
                );

        label.setTextFill(
                Color.web(text)
        );

        label.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setPadding(
                new Insets(
                        7,
                        12,
                        7,
                        12
                )
        );

        label.setStyle(
                "-fx-background-color:" +
                        background +
                        ";" +
                "-fx-background-radius:20;"
        );

        return label;
    }

    // =========================================================
    // ORDER DETAILS
    // =========================================================

    private void showOrderDetails(
            Order order) {

        Dialog<Void> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Order Details"
        );

        dialog.getDialogPane()
                .setStyle(
                        "-fx-background-color:" +
                                BACKGROUND + ";"
                );

        VBox content =
                new VBox(12);

        content.setPadding(
                new Insets(20)
        );

        addDetail(
                content,
                "Order ID",
                order.getOrderId()
        );

        addDetail(
                content,
                "Buyer",
                order.getBuyerName()
        );

        addDetail(
                content,
                "Phone",
                order.getBuyerPhone()
        );

        addDetail(
                content,
                "Address",
                order.getBuyerAddress()
        );

        addDetail(
                content,
                "Farmer",
                order.getFarmerName()
        );

        addDetail(
                content,
                "Farmer ID",
                String.valueOf(
                        order.getFarmerId()
                )
        );

        addDetail(
                content,
                "Payment",
                order.getPaymentMethod()
        );

        addDetail(
                content,
                "Payment Status",
                order.getPaymentStatus()
        );

        addDetail(
                content,
                "Order Status",
                order.getOrderStatus()
        );

        addDetail(
                content,
                "Total",
                "₹" +
                        String.format(
                                "%.2f",
                                order.getTotalAmount()
                        )
        );

        Label itemsTitle =
                new Label("Products");

        itemsTitle.setTextFill(
                Color.web(GREEN)
        );

        itemsTitle.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        content.getChildren().add(
                itemsTitle
        );

        if (order.getItems() != null) {

            for (Map<String, Object> item :
                    order.getItems()) {

                Label itemLabel =
                        new Label(
                                String.valueOf(
                                        item.getOrDefault(
                                                "productName",
                                                "Product"
                                        )
                                )
                                + " × "
                                + String.valueOf(
                                        item.getOrDefault(
                                                "quantity",
                                                0
                                        )
                                )
                        );

                itemLabel.setTextFill(
                        Color.web(TEXT_PRIMARY)
                );

                content.getChildren().add(
                        itemLabel
                );
            }
        }

        ScrollPane scroll =
                new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setPrefHeight(500);

        scroll.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        dialog.getDialogPane()
                .setContent(scroll);

        ButtonType close =
                new ButtonType(
                        "Close",
                        ButtonBar.ButtonData.CANCEL_CLOSE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .add(close);

        dialog.showAndWait();
    }

    private void addDetail(
            VBox box,
            String title,
            String value) {

        VBox row =
                new VBox(3);

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        titleLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        11
                )
        );

        Label valueLabel =
                new Label(
                        safe(value)
                );

        valueLabel.setTextFill(
                Color.web(TEXT_PRIMARY)
        );

        valueLabel.setWrapText(true);

        valueLabel.setFont(
                Font.font(13)
        );

        row.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        box.getChildren().add(
                row
        );
    }

    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private String buttonStyle(
            String color) {

        return
                "-fx-background-color:" +
                        color + ";" +
                "-fx-text-fill:#FFFFFF;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8 13;" +
                "-fx-cursor:hand;";
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

   private String safe(String value) {

    if (value == null) {
        return "";
    }

    return value;
}

    // =========================================================
    // DATE
    // =========================================================

    private String formatDate(
            Order order) {

        if (order.getCreatedAt() == null) {
            return "Date unavailable";
        }

        return order.getCreatedAt()
                .toDate()
                .toString();
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}