package com.pravartak.controller.buyercontroller;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.model.buyer_model.Review;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ReviewController {

    private final Firestore db;

    public ReviewController() {
        db = FirebaseConfig.getFirestore();
    }

    // ---------------------------------------------------------
    // ADD REVIEW
    // ---------------------------------------------------------

    public boolean addReview(
            String buyerUid,
            String buyerName,
            String orderId,
            int productId,
            int farmerId,
            double rating,
            String comment) {

        try {

            if (buyerUid == null || buyerUid.trim().isEmpty()) {
                return false;
            }

            if (orderId == null || orderId.trim().isEmpty()) {
                return false;
            }

            if (rating < 1 || rating > 5) {
                return false;
            }

            if (comment == null) {
                comment = "";
            }

            comment = comment.trim();

            // -------------------------------------------------
            // Verify that the order exists
            // -------------------------------------------------

            DocumentSnapshot orderDoc =
                    db.collection("orders")
                            .document(orderId)
                            .get()
                            .get();

            if (!orderDoc.exists()) {
                return false;
            }

            Order order = orderDoc.toObject(Order.class);

            if (order == null) {
                return false;
            }

            // -------------------------------------------------
            // Verify buyer owns this order
            // -------------------------------------------------

            if (!buyerUid.equals(order.getBuyerUid())) {
                return false;
            }

            // -------------------------------------------------
            // Only delivered orders can be reviewed
            // -------------------------------------------------

            if (!"DELIVERED".equalsIgnoreCase(order.getOrderStatus())) {
                return false;
            }

            // -------------------------------------------------
            // Verify product belongs to this order
            // -------------------------------------------------

           boolean productFound = false;
String productName = "Product";

if (order.getItems() != null) {

    for (Map<String, Object> item : order.getItems()) {

        Object productIdObject = item.get("productId");

        if (productIdObject instanceof Number) {

            int itemProductId =
                    ((Number) productIdObject).intValue();

            if (itemProductId == productId) {

                productFound = true;

                Object nameObject = item.get("productName");

                if (nameObject != null) {
                    productName = String.valueOf(nameObject);
                }

                break;
            }
        }
    }
}

            if (!productFound) {
                return false;
            }

            // -------------------------------------------------
            // Prevent duplicate review
            // -------------------------------------------------

            if (hasReviewed(buyerUid, orderId, productId)) {
                return false;
            }

            String reviewId =
                    "REV" + System.currentTimeMillis();


if (order.getItems() != null) {
    for (Map<String, Object> item : order.getItems()) {

        Object itemProductId = item.get("productId");

        if (itemProductId instanceof Number
                && ((Number) itemProductId).intValue() == productId) {

            Object name = item.get("productName");

            if (name != null) {
                productName = String.valueOf(name);
            }

            break;
        }
    }
} 

            Review review = new Review(
                    reviewId,
                    productId,
                    productName,
                    orderId,
                    farmerId,
                    buyerUid,
                    buyerName,
                    rating,
                    comment,
                    com.google.cloud.Timestamp.now()
            );

            db.collection("reviews")
                    .document(reviewId)
                    .set(review.toMap())
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ---------------------------------------------------------
    // CHECK DUPLICATE REVIEW
    // ---------------------------------------------------------

    public boolean hasReviewed(
            String buyerUid,
            String orderId,
            int productId) {

        try {

            QuerySnapshot snapshot =
                    db.collection("reviews")
                            .whereEqualTo("productId", productId)
                            .get()
                            .get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                String existingBuyerUid =
                        doc.getString("buyerUid");

                String existingOrderId =
                        doc.getString("orderId");
                    

                if (buyerUid.equals(existingBuyerUid)
                        && orderId.equals(existingOrderId)) {

                    return true;
                }
            }

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ---------------------------------------------------------
    // GET REVIEWS FOR PRODUCT
    // ---------------------------------------------------------

    public List<Review> getProductReviews(int productId) {

        List<Review> reviews = new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("reviews")
                            .whereEqualTo("productId", productId)
                            .get()
                            .get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                Review review =
                        doc.toObject(Review.class);

                if (review != null) {
                    reviews.add(review);
                }
            }

            // Newest reviews first
            reviews.sort(
                    Comparator.comparing(
                            Review::getCreatedAt,
                            Comparator.nullsLast(
                                    Comparator.reverseOrder()
                            )
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reviews;
    }

    // ---------------------------------------------------------
    // AVERAGE RATING
    // ---------------------------------------------------------

    public double getAverageRating(int productId) {

        List<Review> reviews =
                getProductReviews(productId);

        if (reviews.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (Review review : reviews) {
            total += review.getRating();
        }

        return total / reviews.size();
    }

    // ---------------------------------------------------------
    // REVIEW COUNT
    // ---------------------------------------------------------

    public int getReviewCount(int productId) {

        return getProductReviews(productId).size();
    }
    // =========================================================
// GET FARMER REVIEWS
// =========================================================

public List<Review> getFarmerReviews(int farmerId) {

    List<Review> reviews = new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection("reviews")
                        .whereEqualTo("farmerId", farmerId)
                        .get()
                        .get();

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            Review review =
                    doc.toObject(Review.class);

            if (review != null) {
                reviews.add(review);
            }
        }

        // Newest reviews first
        reviews.sort(
                Comparator.comparing(
                        Review::getCreatedAt,
                        Comparator.nullsLast(
                                Comparator.reverseOrder()
                        )
                )
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return reviews;
}
// =========================================================
// GET ALL REVIEWS - ADMIN
// =========================================================

public List<Review> getAllReviews() {

    List<Review> reviews = new ArrayList<>();

    try {

        QuerySnapshot snapshot =
                db.collection("reviews")
                        .get()
                        .get();

        for (DocumentSnapshot doc :
                snapshot.getDocuments()) {

            Review review =
                    doc.toObject(Review.class);

            if (review != null) {
                reviews.add(review);
            }
        }

        // Newest reviews first
        reviews.sort(
                Comparator.comparing(
                        Review::getCreatedAt,
                        Comparator.nullsLast(
                                Comparator.reverseOrder()
                        )
                )
        );

    } catch (Exception e) {

        e.printStackTrace();
    }

    return reviews;
}
}