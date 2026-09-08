package com.pravartak.controller.buyercontroller;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.buyer_model.CartItem;
import com.pravartak.model.buyer_model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {

   private final Firestore db;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    // public OrderController() {

    //     db =
    //             FirebaseConfig.getFirestore();

    //     if (db == null) {

    //         throw new IllegalStateException(
    //                 "Firestore could not be initialized."
    //         );
    //     }
    // }
   public OrderController() {

    db = FirebaseConfig.getFirestore();

    if (db == null) {

        throw new IllegalStateException(
                "Firestore could not be initialized."
        );
    }
}
    

    // =====================================================
    // CREATE ORDER REQUEST
    // =====================================================

    public boolean createOrder(
            String buyerUid,
            String buyerName,
            String buyerPhone,
            String buyerAddress,
            String paymentMethod,
            List<CartItem> cartItems) {

        try {

            // =================================================
            // VALIDATION
            // =================================================

            if (buyerUid == null ||
                    buyerUid.trim().isEmpty()) {

                return false;
            }

            if (buyerName == null ||
                    buyerName.trim().isEmpty()) {

                return false;
            }

            if (buyerPhone == null ||
                    buyerPhone.trim().isEmpty()) {

                return false;
            }

            if (buyerAddress == null ||
                    buyerAddress.trim().isEmpty()) {

                return false;
            }

            if (cartItems == null ||
                    cartItems.isEmpty()) {

                return false;
            }

            // =================================================
            // GROUP PRODUCTS BY FARMER
            // =================================================

            Map<Integer, List<CartItem>> farmerItems =
                    new HashMap<>();

            for (CartItem item :
                    cartItems) {

                if (item == null ||
                        item.getProduct() == null) {

                    continue;
                }

                int farmerId =
                        item.getProduct()
                                .getFarmerId();

                farmerItems
                        .computeIfAbsent(
                                farmerId,
                                k -> new ArrayList<>()
                        )
                        .add(item);
            }

            // =================================================
            // CHECK
            // =================================================

            if (farmerItems.isEmpty()) {

                return false;
            }

            // =================================================
            // CREATE ONE ORDER PER FARMER
            // =================================================

            for (Map.Entry<Integer, List<CartItem>> entry :
                    farmerItems.entrySet()) {

                int farmerId =
                        entry.getKey();

                List<CartItem> items =
                        entry.getValue();

                // =============================================
                // ORDER ID
                // =============================================

                String orderId =
                        "ORD"
                                + System.currentTimeMillis()
                                + "_"
                                + farmerId;

                // =============================================
                // ITEMS
                // =============================================

                List<Map<String, Object>> orderItems =
                        new ArrayList<>();

                double total =
                        0;

                for (CartItem cartItem :
                        items) {

                    Map<String, Object> itemMap =
                            new HashMap<>();

                    itemMap.put(
                            "productId",
                            cartItem
                                    .getProduct()
                                    .getProductId()
                    );

                    itemMap.put(
                            "productName",
                            cartItem
                                    .getProduct()
                                    .getProductName()
                    );

                    itemMap.put(
                            "category",
                            cartItem
                                    .getProduct()
                                    .getCategory()
                    );

                    itemMap.put(
                            "price",
                            cartItem
                                    .getProduct()
                                    .getPrice()
                    );

                    itemMap.put(
                            "unit",
                            cartItem
                                    .getProduct()
                                    .getUnit()
                    );

                    itemMap.put(
                            "quantity",
                            cartItem.getQuantity()
                    );

                    itemMap.put(
                            "imagePath",
                            cartItem
                                    .getProduct()
                                    .getImagePath()
                    );

                    itemMap.put(
                            "location",
                            cartItem
                                    .getProduct()
                                    .getLocation()
                    );

                    double itemTotal =
                            cartItem.getTotal();

                    itemMap.put(
                            "itemTotal",
                            itemTotal
                    );

                    total +=
                            itemTotal;

                    orderItems.add(
                            itemMap
                    );
                }

                // =============================================
                // CREATE ORDER
                // =============================================

                Order order =
                        new Order();

                order.setOrderId(
                        orderId
                );

                order.setBuyerUid(
                        buyerUid
                );

                order.setBuyerName(
                        buyerName
                );

                order.setBuyerPhone(
                        buyerPhone
                );

                order.setBuyerAddress(
                        buyerAddress
                );

                order.setFarmerId(
                        farmerId
                );

                /*
                 * Farmer name is optional here.
                 * Farmer can be loaded later from FarmerProfile.
                 */

                order.setFarmerName(
                        "Farmer " + farmerId
                );

                order.setItems(
                        orderItems
                );

                order.setTotalAmount(
                        total
                );

                order.setPaymentMethod(
                        paymentMethod
                );

                // =============================================
                // PAYMENT STATUS
                // =============================================

                if ("COD".equalsIgnoreCase(
                        paymentMethod
                )) {

                    order.setPaymentStatus(
                            "PENDING"
                    );

                } else {

                    /*
                     * Razorpay will change this to PAID
                     * after successful payment.
                     */

                    order.setPaymentStatus(
                            "PENDING"
                    );
                }

                // =============================================
                // ORDER STATUS
                // =============================================

                order.setOrderStatus(
                        "PENDING"
                );

                order.setBuyerNotified(
        false
);

                order.setCreatedAt(
                        Timestamp.now()
                );

                // =============================================
                // SAVE TO FIRESTORE
                // =============================================

                db.collection("orders")
                        .document(orderId)
                        .set(order.toMap())
                        .get();
            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // GET BUYER ORDERS
    // =====================================================

    public List<Order> getBuyerOrders(
            String buyerUid) {

        List<Order> orders =
                new ArrayList<>();

        try {

            if (buyerUid == null ||
                    buyerUid.trim().isEmpty()) {

                return orders;
            }

            QuerySnapshot snapshot =
                    db.collection("orders")
                            .whereEqualTo(
                                    "buyerUid",
                                    buyerUid
                            )
                            .orderBy(
                                    "createdAt",
                                    Query.Direction.DESCENDING
                            )
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                Order order =
                        document.toObject(
                                Order.class
                        );

                if (order != null) {

                    orders.add(
                            order
                    );
                }
            }

        } catch (Exception e) {

            /*
             * Firestore may require an index for the
             * buyerUid + createdAt query.
             *
             * If that happens, we can remove the
             * orderBy and sort locally.
             */

            e.printStackTrace();
        }

        return orders;
    }

    // =====================================================
    // GET FARMER ORDER REQUESTS
    // =====================================================

    public List<Order> getFarmerOrderRequests(
            int farmerId) {

        List<Order> orders =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("orders")
                            .whereEqualTo(
                                    "farmerId",
                                    farmerId
                            )
                            .whereEqualTo(
                                    "orderStatus",
                                    "PENDING"
                            )
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                Order order =
                        document.toObject(
                                Order.class
                        );

                if (order != null) {

                    orders.add(
                            order
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }

    // =====================================================
    // ACCEPT ORDER
    // =====================================================

    public boolean acceptOrder(String orderId) {

    try {

        db.collection("orders")
                .document(orderId)
                .update(
                        "orderStatus",
                        "ACCEPTED",
                        "buyerNotified",
                        false
                )
                .get();

        return true;

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}
    // =========================================================
// GET BUYER UNREAD NOTIFICATIONS
// =========================================================

// =========================================================
// GET BUYER UNREAD NOTIFICATIONS
// =========================================================

public List<Order> getBuyerUnreadNotifications(
        String buyerUid) {

    List<Order> orders =
            new ArrayList<>();

    if (buyerUid == null ||
            buyerUid.trim().isEmpty()) {

        return orders;
    }

    try {

        QuerySnapshot snapshot =
                db.collection("orders")
                        .whereEqualTo(
                                "buyerUid",
                                buyerUid
                        )
                        .get()
                        .get();

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            Order order =
                    doc.toObject(Order.class);

            if (order == null) {
                continue;
            }

            String status =
                    order.getOrderStatus();

            if ((
                    "ACCEPTED".equalsIgnoreCase(status)
                    ||
                    "REJECTED".equalsIgnoreCase(status)
                )
                &&
                !order.isBuyerNotified()) {

                orders.add(order);
            }
        }

        // =====================================================
        // NEWEST FIRST
        // =====================================================

        orders.sort(
                (a, b) -> {

                    if (a.getCreatedAt() == null) {
                        return 1;
                    }

                    if (b.getCreatedAt() == null) {
                        return -1;
                    }

                    return b.getCreatedAt()
                            .compareTo(
                                    a.getCreatedAt()
                            );
                }
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return orders;
}

    // =====================================================
    // REJECT ORDER
    // =====================================================

   // =====================================================
// REJECT ORDER
// =====================================================

public boolean rejectOrder(
        String orderId) {

    try {

        if (orderId == null ||
                orderId.trim().isEmpty()) {

            return false;
        }

        db.collection("orders")
                .document(orderId)
                .update(
                        "orderStatus",
                        "REJECTED",
                        "buyerNotified",
                        false
                )
                .get();

        return true;

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}

    // =====================================================
    // PROCESSING
    // =====================================================

    public boolean markProcessing(
            String orderId) {

        return updateOrderStatus(
                orderId,
                "PROCESSING"
        );
    }

    // =====================================================
    // OUT FOR DELIVERY
    // =====================================================

    public boolean markOutForDelivery(
            String orderId) {

        return updateOrderStatus(
                orderId,
                "OUT_FOR_DELIVERY"
        );
    }

    // =====================================================
    // DELIVERED
    // =====================================================

    public boolean markDelivered(
            String orderId) {

        return updateOrderStatus(
                orderId,
                "DELIVERED"
        );
    }

    // =====================================================
    // UPDATE ORDER STATUS
    // =====================================================

    public boolean updateOrderStatus(
            String orderId,
            String status) {

        try {

            if (orderId == null ||
                    orderId.trim().isEmpty()) {

                return false;
            }

            if (status == null ||
                    status.trim().isEmpty()) {

                return false;
            }

            db.collection("orders")
                    .document(orderId)
                    .update(
                            "orderStatus",
                            status
                    )
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // GET SINGLE ORDER
    // =====================================================

    public Order getOrder(
            String orderId) {

        try {

            if (orderId == null ||
                    orderId.trim().isEmpty()) {

                return null;
            }

            var document =
                    db.collection("orders")
                            .document(orderId)
                            .get()
                            .get();

            if (!document.exists()) {

                return null;
            }

            return document.toObject(
                    Order.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // CANCEL ORDER
    // =====================================================

    public boolean cancelOrder(
            String orderId) {

        return updateOrderStatus(
                orderId,
                "CANCELLED"
        );
    }
    // =====================================================
// GET ALL FARMER ORDERS
// =====================================================

public List<Order> getFarmerOrders(int farmerId) {

    List<Order> orders = new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection("orders")
                        .whereEqualTo(
                                "farmerId",
                                farmerId
                        )
                        .get()
                        .get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            Order order =
                    document.toObject(Order.class);

            if (order == null) {
                continue;
            }

            /*
             * Show only orders that have been accepted.
             * Pending requests are handled separately
             * in FarmerOrderRequestsPage.
             */
            if ("ACCEPTED".equalsIgnoreCase(
                    order.getOrderStatus()
            )
                    ||
                    "PROCESSING".equalsIgnoreCase(
                            order.getOrderStatus()
                    )
                    ||
                    "OUT_FOR_DELIVERY".equalsIgnoreCase(
                            order.getOrderStatus()
                    )
                    ||
                    "DELIVERED".equalsIgnoreCase(
                            order.getOrderStatus()
                    )) {

                orders.add(order);
            }
        }

        // Sort newest orders first
        orders.sort(
                (a, b) -> {

                    if (a.getCreatedAt() == null) {
                        return 1;
                    }

                    if (b.getCreatedAt() == null) {
                        return -1;
                    }

                    return b.getCreatedAt()
                            .compareTo(
                                    a.getCreatedAt()
                            );
                }
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return orders;
}
// =========================================================
// MARK BUYER NOTIFICATION AS READ
// =========================================================

public boolean markBuyerNotificationRead(String orderId) {

    try {

        db.collection("orders")
                .document(orderId)
                .update("buyerNotified", true)
                .get();

        return true;

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}
// =====================================================
// GET ALL ORDERS - ADMIN
// =====================================================

public List<Order> getAllOrders() {

    List<Order> orders = new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection("orders")
                        .get()
                        .get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            Order order =
                    document.toObject(Order.class);

            if (order != null) {
                orders.add(order);
            }
        }

        // Newest orders first
        orders.sort(
                (a, b) -> {

                    if (a.getCreatedAt() == null) {
                        return 1;
                    }

                    if (b.getCreatedAt() == null) {
                        return -1;
                    }

                    return b.getCreatedAt()
                            .compareTo(
                                    a.getCreatedAt()
                            );
                }
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return orders;
}
}