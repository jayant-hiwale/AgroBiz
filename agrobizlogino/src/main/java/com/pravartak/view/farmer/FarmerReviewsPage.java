package com.pravartak.view.farmer;

import com.google.cloud.Timestamp;
import com.pravartak.controller.buyercontroller.ReviewController;
import com.pravartak.model.buyer_model.Review;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;
import com.pravartak.view.buyer.common.buyerTop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class FarmerReviewsPage {

    private final int farmerId;

    private final ReviewController reviewController;

    private VBox reviewsContainer;

    private Label averageRatingLabel;
    private Label totalReviewsLabel;

    public FarmerReviewsPage(int farmerId) {

        this.farmerId = farmerId;
        this.reviewController = new ReviewController();
    }

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public BorderPane getReviewsPage() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #050B0A;"
        );
        //  root.setTop(
        //         new NavBar(
        //                 farmerId,
        //                 LoginPage.getLoggedInFirebaseUid()
        //         ).createNavbar("⭐ Reviews")
        // );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox(15);

        header.setAlignment(Pos.CENTER_LEFT);

        header.setPadding(
                new Insets(25, 35, 20, 35)
        );

        Label title = new Label(
                "⭐ Customer Reviews"
        );

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        28
                )
        );

        title.setTextFill(Color.WHITE);

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button refreshButton =
                new Button("🔄 Refresh");

        refreshButton.setPrefHeight(40);

        refreshButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 0 18 0 18;"
        );

        refreshButton.setOnAction(
                e -> loadReviews()
        );

        header.getChildren().addAll(
                title,
                spacer,
                refreshButton
        );

        // =====================================================
        // SUMMARY CARDS
        // =====================================================

        HBox summaryBox = new HBox(20);

        summaryBox.setPadding(
                new Insets(0, 35, 20, 35)
        );

        summaryBox.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox ratingCard =
                createSummaryCard(
                        "⭐",
                        "Average Rating"
                );

        averageRatingLabel =
                new Label("0.0");

        styleSummaryValue(
                averageRatingLabel
        );

        ratingCard.getChildren().add(
                averageRatingLabel
        );

        VBox reviewCard =
                createSummaryCard(
                        "💬",
                        "Total Reviews"
                );

        totalReviewsLabel =
                new Label("0");

        styleSummaryValue(
                totalReviewsLabel
        );

        reviewCard.getChildren().add(
                totalReviewsLabel
        );

        summaryBox.getChildren().addAll(
                ratingCard,
                reviewCard
        );

        // =====================================================
        // REVIEWS CONTAINER
        // =====================================================

        reviewsContainer =
                new VBox(15);

        reviewsContainer.setPadding(
                new Insets(5, 35, 30, 35)
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        reviewsContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background-color: #050B0A;" +
                "-fx-background: #050B0A;"
        );

        VBox mainContent =
                new VBox();

        mainContent.getChildren().addAll(
                header,
                summaryBox,
                scrollPane
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.setCenter(mainContent);

        // =====================================================
        // FOOTER
        // =====================================================

        // root.setBottom(
        //         new Footer().createFooter()
        // );

        // =====================================================
        // LOAD DATA
        // =====================================================

        loadReviews();

        return root;
    }

    // =========================================================
    // LOAD REVIEWS
    // =========================================================

    private void loadReviews() {

        reviewsContainer.getChildren().clear();

        try {

            List<Review> reviews =
                    reviewController.getFarmerReviews(
                            farmerId
                    );

            if (reviews == null ||
                    reviews.isEmpty()) {

                averageRatingLabel.setText(
                        "0.0"
                );

                totalReviewsLabel.setText(
                        "0"
                );

                showEmptyState();

                return;
            }

            // =================================================
            // CALCULATE AVERAGE
            // =================================================

            double totalRating = 0;

            for (Review review : reviews) {

                totalRating +=
                        review.getRating();
            }

            double average =
                    totalRating / reviews.size();

            averageRatingLabel.setText(
                    String.format(
                            Locale.US,
                            "%.1f / 5",
                            average
                    )
            );

            totalReviewsLabel.setText(
                    String.valueOf(
                            reviews.size()
                    )
            );

            // =================================================
            // REVIEW CARDS
            // =================================================

            for (Review review : reviews) {

                reviewsContainer.getChildren().add(
                        createReviewCard(review)
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showErrorState(
                    "Unable to load customer reviews."
            );
        }
    }

    // =========================================================
    // CREATE REVIEW CARD
    // =========================================================

    private VBox createReviewCard(
            Review review) {

        VBox card = new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: #101516;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #30363D;" +
                "-fx-border-radius: 15;"
        );

        // =====================================================
        // TOP ROW
        // =====================================================

        HBox topRow = new HBox(12);

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // Buyer icon
        Label buyerIcon =
                new Label("👤");

        buyerIcon.setFont(
                Font.font(24)
        );

        // Buyer name
        String buyerName =
                review.getBuyerName();

        if (buyerName == null ||
                buyerName.trim().isEmpty()) {

            buyerName = "Customer";
        }

        Label buyerLabel =
                new Label(
                        buyerName
                );

        buyerLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16
                )
        );

        buyerLabel.setTextFill(
                Color.WHITE
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // Date
        Label dateLabel =
                new Label(
                        formatDate(
                                review.getCreatedAt()
                        )
                );

        dateLabel.setFont(
                Font.font(12)
        );

        dateLabel.setTextFill(
                Color.web("#8B949E")
        );

        topRow.getChildren().addAll(
                buyerIcon,
                buyerLabel,
                spacer,
                dateLabel
        );

        // =====================================================
        // RATING
        // =====================================================

        HBox ratingBox =
                new HBox(2);

        ratingBox.setAlignment(
                Pos.CENTER_LEFT
        );

        int rating =
                (int) Math.round(
                        review.getRating()
                );

        for (int i = 1; i <= 5; i++) {

            Label star =
                    new Label(
                            i <= rating
                                    ? "★"
                                    : "☆"
                    );

            star.setFont(
                    Font.font(20)
            );

            star.setTextFill(
                    Color.web("#F5C542")
            );

            ratingBox.getChildren().add(
                    star
            );
        }

        Label ratingNumber =
                new Label(
                        String.format(
                                Locale.US,
                                "%.1f / 5",
                                review.getRating()
                        )
                );

        ratingNumber.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13
                )
        );

        ratingNumber.setTextFill(
                Color.web("#F5C542")
        );

        ratingBox.getChildren().add(
                ratingNumber
        );

        // =====================================================
        // PRODUCT
        // =====================================================

        Label productLabel =
                new Label(
                        "Product ID: "
                                + review.getProductId()
                );

        productLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13
                )
        );

        productLabel.setTextFill(
                Color.web("#58A6FF")
        );

        // =====================================================
        // COMMENT
        // =====================================================

        String comment =
                review.getComment();

        if (comment == null ||
                comment.trim().isEmpty()) {

            comment =
                    "No written comment provided.";
        }

        Label commentLabel =
                new Label(
                        "\"" + comment + "\""
                );

        commentLabel.setWrapText(true);

        commentLabel.setFont(
                Font.font(14)
        );

        commentLabel.setTextFill(
                Color.web("#C9D1D9")
        );

        // =====================================================
        // ORDER
        // =====================================================

        Label orderLabel =
                new Label(
                        "Order: "
                                + safe(
                                        review.getOrderId()
                                )
                );

        orderLabel.setFont(
                Font.font(12)
        );

        orderLabel.setTextFill(
                Color.web("#8B949E")
        );
        Label productnameLabel = new Label(
        "Product: " + review.getProductName()
);

productnameLabel.setTextFill(Color.WHITE);
        

        card.getChildren().addAll(
                topRow,
                ratingBox,
                productLabel,
                productnameLabel,
                commentLabel,
                orderLabel
        );

        return card;
    }

    // =========================================================
    // SUMMARY CARD
    // =========================================================

    private VBox createSummaryCard(
            String icon,
            String title) {

        VBox card = new VBox(5);

        card.setPrefWidth(240);

        card.setPadding(
                new Insets(18, 22, 18, 22)
        );

        card.setStyle(
                "-fx-background-color: #101516;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #30363D;" +
                "-fx-border-radius: 14;"
        );

        HBox heading =
                new HBox(8);

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(20)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13
                )
        );

        titleLabel.setTextFill(
                Color.web("#8B949E")
        );

        heading.getChildren().addAll(
                iconLabel,
                titleLabel
        );

        card.getChildren().add(
                heading
        );

        return card;
    }

    private void styleSummaryValue(
            Label label) {

        label.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        24
                )
        );

        label.setTextFill(
                Color.web("#3FB950")
        );
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private void showEmptyState() {

        VBox emptyBox =
                new VBox(12);

        emptyBox.setAlignment(
                Pos.CENTER
        );

        emptyBox.setPadding(
                new Insets(60)
        );

        Label icon =
                new Label("💬");

        icon.setFont(
                Font.font(45)
        );

        Label title =
                new Label(
                        "No Customer Reviews Yet"
                );

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        20
                )
        );

        title.setTextFill(
                Color.WHITE
        );

        Label message =
                new Label(
                        "Reviews from your customers will appear here."
                );

        message.setFont(
                Font.font(14)
        );

        message.setTextFill(
                Color.web("#8B949E")
        );

        emptyBox.getChildren().addAll(
                icon,
                title,
                message
        );

        reviewsContainer.getChildren().add(
                emptyBox
        );
    }

    // =========================================================
    // ERROR STATE
    // =========================================================

    private void showErrorState(
            String message) {

        Label error =
                new Label(
                        "⚠ " + message
                );

        error.setFont(
                Font.font(15)
        );

        error.setTextFill(
                Color.web("#F85149")
        );

        error.setPadding(
                new Insets(30)
        );

        reviewsContainer.getChildren().add(
                error
        );
    }

    // =========================================================
    // FORMAT DATE
    // =========================================================

    private String formatDate(
            Timestamp timestamp) {

        if (timestamp == null) {
            return "";
        }

        try {

            LocalDateTime dateTime =
                    timestamp.toDate()
                            .toInstant()
                            .atZone(
                                    ZoneId.systemDefault()
                            )
                            .toLocalDateTime();

            return dateTime.format(
                    DateTimeFormatter.ofPattern(
                            "dd MMM yyyy, hh:mm a"
                    )
            );

        } catch (Exception e) {

            return "";
        }
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "-";
        }

        return value;
    }
}