// package com.pravartak.view.buyer;

// import com.pravartak.controller.buyercontroller.OrderController;
// import com.pravartak.model.buyer_model.Order;
// import com.pravartak.view.buyer.common.buyerTop;
// import com.pravartak.view.login.LoginPage;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;

// import java.util.List;
// import java.util.Map;

// public class BuyerNotificationPage {

//     private BorderPane root;

//     private VBox notificationContainer;

//     private final OrderController orderController;

//     public BuyerNotificationPage() {

//         orderController =
//                 new OrderController();
//     }

//     // =========================================================
//     // MAIN PAGE
//     // =========================================================

//     public BorderPane getNotificationPage() {

//         root = new BorderPane();

//         root.setStyle(
//                 "-fx-background-color:#0D1117;"
//         );

//         // -----------------------------------------------------
//         // NAVBAR
//         // -----------------------------------------------------

//         root.setTop(
//                 new buyerTop()
//                         .createBuyerTop("Notifications")
//         );

//         // -----------------------------------------------------
//         // MAIN CONTENT
//         // -----------------------------------------------------

//         VBox main =
//                 new VBox(20);

//         main.setPadding(
//                 new Insets(30, 50, 30, 50)
//         );

//         Label title =
//                 new Label("🔔 Notifications");

//         title.setStyle(
//                 "-fx-text-fill:white;" +
//                 "-fx-font-size:28px;" +
//                 "-fx-font-weight:bold;"
//         );

//         Label subtitle =
//                 new Label(
//                         "Updates about your orders"
//                 );

//         subtitle.setStyle(
//                 "-fx-text-fill:#8b949e;" +
//                 "-fx-font-size:14px;"
//         );

//         notificationContainer =
//                 new VBox(15);

//         loadNotifications();

//         main.getChildren().addAll(
//                 title,
//                 subtitle,
//                 notificationContainer
//         );

//         root.setCenter(main);

//         return root;
//     }

//     // =========================================================
//     // LOAD NOTIFICATIONS
//     // =========================================================

//     private void loadNotifications() {

//         notificationContainer
//                 .getChildren()
//                 .clear();

//         String buyerUid =
//                 BuyerProfilePage.currentBuyerUid;

//         if (buyerUid == null ||
//                 buyerUid.trim().isEmpty()) {

//             showEmpty(
//                     "Please login again to view notifications."
//             );

//             return;
//         }

//         List<Order> notifications =
//                 orderController
//                         .getBuyerUnreadNotifications(
//                                 buyerUid
//                         );

//         if (notifications.isEmpty()) {

//             showEmpty(
//                     "No new notifications"
//             );

//             return;
//         }

//         for (Order order : notifications) {

//             notificationContainer
//                     .getChildren()
//                     .add(
//                             createNotificationCard(
//                                     order
//                             )
//                     );
//         }
//     }

//     // =========================================================
//     // NOTIFICATION CARD
//     // =========================================================

//     private VBox createNotificationCard(
//             Order order) {

//         VBox card =
//                 new VBox(12);

//         card.setPadding(
//                 new Insets(20)
//         );

//         card.setStyle(
//                 "-fx-background-color:#161B22;" +
//                 "-fx-background-radius:12;" +
//                 "-fx-border-color:#2A3138;" +
//                 "-fx-border-radius:12;"
//         );

//         // -----------------------------------------------------
//         // TOP
//         // -----------------------------------------------------

//         HBox top =
//                 new HBox();

//         top.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         Label icon =
//                 new Label("✅");

//         icon.setStyle(
//                 "-fx-font-size:25px;"
//         );

//         Label title =
//                 new Label(
//                         " Order Accepted"
//                 );

//         title.setStyle(
//                 "-fx-text-fill:#68d34a;" +
//                 "-fx-font-size:18px;" +
//                 "-fx-font-weight:bold;"
//         );

//         top.getChildren().addAll(
//                 icon,
//                 title
//         );

//         // -----------------------------------------------------
//         // MESSAGE
//         // -----------------------------------------------------

//         Label message =
//                 new Label(
//                         "The farmer has accepted your order."
//                 );

//         message.setStyle(
//                 "-fx-text-fill:#d0d7de;" +
//                 "-fx-font-size:14px;"
//         );

//         // -----------------------------------------------------
//         // ORDER ID
//         // -----------------------------------------------------

//         Label orderId =
//                 new Label(
//                         "Order ID: "
//                                 + order.getOrderId()
//                 );

//         orderId.setStyle(
//                 "-fx-text-fill:#8b949e;" +
//                 "-fx-font-size:13px;"
//         );

//         // -----------------------------------------------------
//         // FARMER
//         // -----------------------------------------------------

//         Label farmer =
//                 new Label(
//                         "Farmer: "
//                                 + getFarmerName(order)
//                 );

//         farmer.setStyle(
//                 "-fx-text-fill:#8b949e;" +
//                 "-fx-font-size:13px;"
//         );

//         // -----------------------------------------------------
//         // TOTAL
//         // -----------------------------------------------------

//         Label amount =
//                 new Label(
//                         String.format(
//                                 "Order Amount: ₹%.2f",
//                                 order.getTotalAmount()
//                         )
//                 );

//         amount.setStyle(
//                 "-fx-text-fill:white;" +
//                 "-fx-font-size:15px;" +
//                 "-fx-font-weight:bold;"
//         );

//         // -----------------------------------------------------
//         // VIEW ORDER BUTTON
//         // -----------------------------------------------------

//         Button viewOrder =
//                 new Button(
//                         "View Order"
//                 );

//         viewOrder.setStyle(
//                 "-fx-background-color:#68d34a;" +
//                 "-fx-text-fill:#081008;" +
//                 "-fx-font-weight:bold;" +
//                 "-fx-background-radius:8;" +
//                 "-fx-padding:9 18;" +
//                 "-fx-cursor:hand;"
//         );

//         viewOrder.setOnAction(e -> {

//             markAsRead(order);

//             openOrdersPage();
//         });

//         // -----------------------------------------------------
//         // MARK AS READ
//         // -----------------------------------------------------

//         Button markRead =
//                 new Button(
//                         "Mark as Read"
//                 );

//         markRead.setStyle(
//                 "-fx-background-color:#21262D;" +
//                 "-fx-text-fill:#c9d1d9;" +
//                 "-fx-background-radius:8;" +
//                 "-fx-padding:9 18;" +
//                 "-fx-cursor:hand;"
//         );

//         markRead.setOnAction(e -> {

//             markAsRead(order);

//             loadNotifications();
//         });

//         HBox buttons =
//                 new HBox(10);

//         buttons.getChildren().addAll(
//                 viewOrder,
//                 markRead
//         );

//         card.getChildren().addAll(
//                 top,
//                 message,
//                 orderId,
//                 farmer,
//                 amount,
//                 buttons
//         );

//         return card;
//     }

//     // =========================================================
//     // FARMER NAME
//     // =========================================================

//     private String getFarmerName(Order order) {

//         if (order.getFarmerName() != null &&
//                 !order.getFarmerName().trim().isEmpty()) {

//             return order.getFarmerName();
//         }

//         return "Farmer " + order.getFarmerId();
//     }

//     // =========================================================
//     // MARK READ
//     // =========================================================

//     private void markAsRead(Order order) {

//         orderController
//                 .markBuyerNotificationRead(
//                         order.getOrderId()
//                 );
//     }

//     // =========================================================
//     // OPEN ORDERS
//     // =========================================================

//     private void openOrdersPage() {

//         try {

//             BuyerOrdersPage ordersPage =
//                     new BuyerOrdersPage();

//             BorderPane page =
//                     ordersPage.getOrdersPage();

//             Scene scene =
//                     new Scene(
//                             page,
//                             1400,
//                             850
//                     );

//             LoginPage.mainStage
//                     .setScene(scene);

//             LoginPage.mainStage.show();

//         } catch (Exception e) {

//             e.printStackTrace();

//             showAlert(
//                     Alert.AlertType.ERROR,
//                     "Unable to open Orders."
//             );
//         }
//     }

//     // =========================================================
//     // EMPTY STATE
//     // =========================================================

//     private void showEmpty(String text) {

//         Label empty =
//                 new Label(text);

//         empty.setStyle(
//                 "-fx-text-fill:#8b949e;" +
//                 "-fx-font-size:16px;"
//         );

//         notificationContainer
//                 .getChildren()
//                 .add(empty);
//     }

//     // =========================================================
//     // ALERT
//     // =========================================================

//     private void showAlert(
//             Alert.AlertType type,
//             String message) {

//         Alert alert =
//                 new Alert(type);

//         alert.setTitle("AgroBiz");

//         alert.setHeaderText(null);

//         alert.setContentText(
//                 message
//         );

//         alert.showAndWait();
//     }
// }
package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class BuyerNotificationPage {

    private BorderPane root;

    private VBox notificationContainer;

    private final OrderController orderController;

    public BuyerNotificationPage() {

        orderController =
                new OrderController();
    }

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public BorderPane getNotificationPage() {

        root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =====================================================
        // NAVBAR
        // =====================================================

        root.setTop(
                new buyerTop()
                        .createBuyerTop("Notifications")
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(
                        30,
                        50,
                        30,
                        50
                )
        );

        Label title =
                new Label(
                        "🔔 Notifications"
                );

        title.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Updates about your orders"
                );

        subtitle.setStyle(
                "-fx-text-fill:#8b949e;" +
                "-fx-font-size:14px;"
        );

        notificationContainer =
                new VBox(15);

        loadNotifications();

        main.getChildren().addAll(
                title,
                subtitle,
                notificationContainer
        );

        root.setCenter(main);

        return root;
    }

    // =========================================================
    // LOAD NOTIFICATIONS
    // =========================================================

    private void loadNotifications() {

        notificationContainer
                .getChildren()
                .clear();

        String buyerUid =
                BuyerProfilePage.currentBuyerUid;

        if (buyerUid == null ||
                buyerUid.trim().isEmpty()) {

            showEmpty(
                    "Please login again to view notifications."
            );

            return;
        }

        List<Order> notifications =
                orderController
                        .getBuyerUnreadNotifications(
                                buyerUid
                        );

        if (notifications == null ||
                notifications.isEmpty()) {

            showEmpty(
                    "No new notifications"
            );

            return;
        }

        for (Order order :
                notifications) {

            notificationContainer
                    .getChildren()
                    .add(
                            createNotificationCard(
                                    order
                            )
                    );
        }
    }

    // =========================================================
    // NOTIFICATION CARD
    // =========================================================

    private VBox createNotificationCard(
            Order order) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#2A3138;" +
                "-fx-border-radius:12;"
        );

        // =====================================================
        // DETERMINE STATUS
        // =====================================================

        boolean accepted =
                "ACCEPTED".equalsIgnoreCase(
                        order.getOrderStatus()
                );

        boolean rejected =
                "REJECTED".equalsIgnoreCase(
                        order.getOrderStatus()
                );

        // =====================================================
        // TOP
        // =====================================================

        HBox top =
                new HBox(10);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon;

        Label title;

        if (accepted) {

            icon =
                    new Label("✅");

            title =
                    new Label(
                            "Order Accepted"
                    );

            title.setStyle(
                    "-fx-text-fill:#68d34a;" +
                    "-fx-font-size:18px;" +
                    "-fx-font-weight:bold;"
            );

        } else if (rejected) {

            icon =
                    new Label("❌");

            title =
                    new Label(
                            "Order Rejected"
                    );

            title.setStyle(
                    "-fx-text-fill:#ff5c67;" +
                    "-fx-font-size:18px;" +
                    "-fx-font-weight:bold;"
            );

        } else {

            icon =
                    new Label("🔔");

            title =
                    new Label(
                            "Order Update"
                    );

            title.setStyle(
                    "-fx-text-fill:#68d34a;" +
                    "-fx-font-size:18px;" +
                    "-fx-font-weight:bold;"
            );
        }

        icon.setStyle(
                "-fx-font-size:25px;"
        );

        top.getChildren().addAll(
                icon,
                title
        );

        // =====================================================
        // MESSAGE
        // =====================================================

        String messageText;

        if (accepted) {

            messageText =
                    "The farmer has accepted your order.";

        } else if (rejected) {

            messageText =
                    "The farmer has rejected your order.";

        } else {

            messageText =
                    "There is an update on your order.";
        }

        Label message =
                new Label(
                        messageText
                );

        message.setStyle(
                "-fx-text-fill:#d0d7de;" +
                "-fx-font-size:14px;"
        );

        // =====================================================
        // ORDER ID
        // =====================================================

        Label orderId =
                new Label(
                        "Order ID: "
                                + getSafe(
                                order.getOrderId()
                        )
                );

        orderId.setStyle(
                "-fx-text-fill:#8b949e;" +
                "-fx-font-size:13px;"
        );

        // =====================================================
        // FARMER
        // =====================================================

        Label farmer =
                new Label(
                        "Farmer: "
                                + getFarmerName(order)
                );

        farmer.setStyle(
                "-fx-text-fill:#8b949e;" +
                "-fx-font-size:13px;"
        );

        // =====================================================
        // TOTAL
        // =====================================================

        Label amount =
                new Label(
                        String.format(
                                "Order Amount: ₹%.2f",
                                order.getTotalAmount()
                        )
                );

        amount.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons =
                new HBox(10);

        // -----------------------------------------------------
        // VIEW ORDER
        // -----------------------------------------------------

        Button viewOrder =
                new Button(
                        "View Order"
                );

        if (rejected) {

            viewOrder.setStyle(
                    "-fx-background-color:#3A1518;" +
                    "-fx-text-fill:#FF6B6B;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:8;" +
                    "-fx-padding:9 18;" +
                    "-fx-cursor:hand;"
            );

        } else {

            viewOrder.setStyle(
                    "-fx-background-color:#68d34a;" +
                    "-fx-text-fill:#081008;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:8;" +
                    "-fx-padding:9 18;" +
                    "-fx-cursor:hand;"
            );
        }

        viewOrder.setOnAction(e -> {

            markAsRead(order);

            openOrdersPage();
        });

        // -----------------------------------------------------
        // MARK AS READ
        // -----------------------------------------------------

        Button markRead =
                new Button(
                        "Mark as Read"
                );

        markRead.setStyle(
                "-fx-background-color:#21262D;" +
                "-fx-text-fill:#c9d1d9;" +
                "-fx-background-radius:8;" +
                "-fx-padding:9 18;" +
                "-fx-cursor:hand;"
        );

        markRead.setOnAction(e -> {

            markAsRead(order);

            loadNotifications();
        });

        buttons.getChildren().addAll(
                viewOrder,
                markRead
        );

        // =====================================================
        // ADD TO CARD
        // =====================================================

        card.getChildren().addAll(
                top,
                message,
                orderId,
                farmer,
                amount,
                buttons
        );

        return card;
    }

    // =========================================================
    // FARMER NAME
    // =========================================================

    private String getFarmerName(
            Order order) {

        if (order.getFarmerName() != null &&
                !order.getFarmerName()
                        .trim()
                        .isEmpty()) {

            return order.getFarmerName();
        }

        return "Farmer "
                + order.getFarmerId();
    }

    // =========================================================
    // MARK READ
    // =========================================================

    private void markAsRead(
            Order order) {

        if (order == null ||
                order.getOrderId() == null) {

            return;
        }

        orderController
                .markBuyerNotificationRead(
                        order.getOrderId()
                );
    }

    // =========================================================
    // OPEN ORDERS
    // =========================================================

    private void openOrdersPage() {

        try {

            BuyerOrdersPage ordersPage =
                    new BuyerOrdersPage();

            BorderPane page =
                    ordersPage.getOrdersPage();

            Scene scene =
                    new Scene(
                            page,
                            1400,
                            850
                    );

            LoginPage.mainStage
                    .setScene(scene);

            LoginPage.mainStage.show();

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Unable to open Orders."
            );
        }
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private void showEmpty(
            String text) {

        Label empty =
                new Label(text);

        empty.setStyle(
                "-fx-text-fill:#8b949e;" +
                "-fx-font-size:16px;"
        );

        notificationContainer
                .getChildren()
                .add(empty);
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String getSafe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not available";
        }

        return value;
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "AgroBiz"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}