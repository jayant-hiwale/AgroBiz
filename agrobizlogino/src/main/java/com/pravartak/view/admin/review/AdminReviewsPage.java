package com.pravartak.view.admin.review;

import com.pravartak.controller.buyercontroller.ReviewController;
import com.pravartak.model.buyer_model.Review;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class AdminReviewsPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BACKGROUND = "#080C0D";
    private static final String CARD = "#111719";
    private static final String BORDER = "#263336";

    private static final String TEXT_PRIMARY = "#F4F7F7";
    private static final String TEXT_SECONDARY = "#91A0A3";

    private static final String GREEN = "#68D34A";
    private static final String GREEN_DARK = "#193D2A";
    private static final String RED = "#EF6B73";

    private VBox reviewsContainer;

    private Label totalReviewsLabel;
    private Label averageRatingLabel;

    // =========================================================
    // GET PAGE
    // =========================================================

    public VBox getReviewsPage() {

        VBox root =
                new VBox(20);

        root.setPadding(
                new Insets(28, 32, 28, 32)
        );

        root.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox =
                new VBox(5);

        Label title =
                new Label("Customer Reviews");

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

        Label subtitle =
                new Label(
                        "View and manage reviews submitted by buyers"
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

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button refreshButton =
                new Button("⟳  Refresh");

        refreshButton.setPrefHeight(40);

        refreshButton.setStyle(
                "-fx-background-color:" +
                        GREEN_DARK + ";" +
                "-fx-text-fill:" +
                        GREEN + ";" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:" +
                        GREEN + ";" +
                "-fx-border-radius:8;" +
                "-fx-cursor:hand;"
        );

        refreshButton.setOnAction(
                e -> loadReviews()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                refreshButton
        );

        // =====================================================
        // STATS
        // =====================================================

        HBox stats =
                createStats();

        // =====================================================
        // REVIEWS
        // =====================================================

        reviewsContainer =
                new VBox(14);

        ScrollPane scrollPane =
                new ScrollPane(
                        reviewsContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color:" +
                        BACKGROUND + ";" +
                "-fx-background:" +
                        BACKGROUND + ";"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                header,
                stats,
                scrollPane
        );

        loadReviews();

        return root;
    }

    // =========================================================
    // STAT CARDS
    // =========================================================

    private HBox createStats() {

        HBox box =
                new HBox(16);

        totalReviewsLabel =
                new Label("0");

        averageRatingLabel =
                new Label("0.0 ⭐");

        box.getChildren().addAll(
                createStatCard(
                        "Total Reviews",
                        totalReviewsLabel
                ),
                createStatCard(
                        "Average Rating",
                        averageRatingLabel
                )
        );

        return box;
    }

    private VBox createStatCard(
            String title,
            Label value) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(18)
        );

        card.setPrefWidth(220);

        card.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                "-fx-background-radius:12;" +
                "-fx-border-color:" +
                        BORDER + ";" +
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
                Color.web(GREEN)
        );

        value.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        24
                )
        );

        card.getChildren().addAll(
                titleLabel,
                value
        );

        return card;
    }

    // =========================================================
    // LOAD REVIEWS
    // =========================================================

    private void loadReviews() {

        if (reviewsContainer == null) {
            return;
        }

        reviewsContainer.getChildren().clear();

        ReviewController controller =
                new ReviewController();

        List<Review> reviews =
                controller.getAllReviews();

        updateStatistics(reviews);

        if (reviews.isEmpty()) {

            Label empty =
                    new Label(
                            "No customer reviews found."
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

            reviewsContainer.getChildren().add(
                    empty
            );

            return;
        }

        for (Review review : reviews) {

            reviewsContainer.getChildren().add(
                    createReviewCard(review)
            );
        }
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics(
            List<Review> reviews) {

        totalReviewsLabel.setText(
                String.valueOf(
                        reviews.size()
                )
        );

        if (reviews.isEmpty()) {

            averageRatingLabel.setText(
                    "0.0 ⭐"
            );

            return;
        }

        double total = 0;

        for (Review review : reviews) {
            total += review.getRating();
        }

        double average =
                total / reviews.size();

        averageRatingLabel.setText(
                String.format(
                        "%.1f ⭐",
                        average
                )
        );
    }

    // =========================================================
    // REVIEW CARD
    // =========================================================

    private VBox createReviewCard(
            Review review) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color:" +
                        CARD + ";" +
                "-fx-background-radius:12;" +
                "-fx-border-color:" +
                        BORDER + ";" +
                "-fx-border-radius:12;"
        );

        // =====================================================
        // TOP
        // =====================================================

        HBox top =
                new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox info =
                new VBox(5);

        String productName =
                review.getProductName();

        if (productName == null
                || productName.trim().isEmpty()) {

            productName =
                    "Product ID: " +
                    review.getProductId();
        }

        Label product =
                new Label(
                        "🌾 " + productName
                );

        product.setTextFill(
                Color.web(TEXT_PRIMARY)
        );

        product.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        17
                )
        );

        Label buyer =
                new Label(
                        "Buyer: " +
                        safe(review.getBuyerName())
                );

        buyer.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        buyer.setFont(
                Font.font(13)
        );

        Label farmer =
                new Label(
                        "Farmer ID: " +
                        review.getFarmerId()
                );

        farmer.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        farmer.setFont(
                Font.font(12)
        );

        info.getChildren().addAll(
                product,
                buyer,
                farmer
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label rating =
                new Label(
                        createStars(
                                review.getRating()
                        )
                        + "  "
                        + String.format(
                                "%.1f",
                                review.getRating()
                        )
                );

        rating.setTextFill(
                Color.web("#F5C542")
        );

        rating.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16
                )
        );

        top.getChildren().addAll(
                info,
                spacer,
                rating
        );

        // =====================================================
        // COMMENT
        // =====================================================

        Label comment =
                new Label(
                        safe(review.getComment())
                );

        comment.setTextFill(
                Color.web(TEXT_PRIMARY)
        );

        comment.setWrapText(true);

        comment.setFont(
                Font.font(14)
        );

        comment.setStyle(
                "-fx-background-color:#172022;" +
                "-fx-background-radius:8;" +
                "-fx-padding:12;"
        );

        // =====================================================
        // DATE
        // =====================================================

        Label date =
                new Label(
                        formatDate(
                                review
                        )
                );

        date.setTextFill(
                Color.web(TEXT_SECONDARY)
        );

        date.setFont(
                Font.font(12)
        );

        // =====================================================
        // DELETE BUTTON
        // =====================================================

        Button deleteButton =
                new Button("Delete Review");

        deleteButton.setStyle(
                "-fx-background-color:#48272A;" +
                "-fx-text-fill:" + RED + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8 13;" +
                "-fx-cursor:hand;"
        );

        deleteButton.setOnAction(
                e -> deleteReview(review)
        );

        HBox bottom =
                new HBox();

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        bottom.getChildren().add(
                date
        );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        bottom.getChildren().add(
                bottomSpacer
        );

        bottom.getChildren().add(
                deleteButton
        );

        card.getChildren().addAll(
                top,
                comment,
                bottom
        );

        return card;
    }

    // =========================================================
    // DELETE REVIEW
    // =========================================================

    private void deleteReview(
            Review review) {

        Alert confirm =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirm.setTitle(
                "Delete Review"
        );

        confirm.setHeaderText(
                "Delete this customer review?"
        );

        confirm.setContentText(
                "This action cannot be undone."
        );

        ButtonType result =
                confirm.showAndWait()
                        .orElse(
                                ButtonType.CANCEL
                        );

        if (result != ButtonType.OK) {
            return;
        }

        try {

            // We will connect this to
            // ReviewController in the next step.

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Coming Next",
                    "Review deletion will be connected next."
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // STARS
    // =========================================================

    private String createStars(
            double rating) {

        StringBuilder stars =
                new StringBuilder();

        int rounded =
                (int) Math.round(rating);

        for (int i = 1; i <= 5; i++) {

            if (i <= rounded) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }

        return stars.toString();
    }

    // =========================================================
    // DATE
    // =========================================================

    private String formatDate(
            Review review) {

        if (review.getCreatedAt() == null) {
            return "Date unavailable";
        }

        return review.getCreatedAt()
                .toDate()
                .toString();
    }

    // =========================================================
    // SAFE
    // =========================================================

    private String safe(
            String value) {

        return value == null
                ? ""
                : value;
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