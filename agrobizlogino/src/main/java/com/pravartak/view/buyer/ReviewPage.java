package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.ReviewController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewPage {

    private final Order order;

    private ComboBox<ProductReviewItem> productComboBox;

    private int selectedRating = 0;

    private HBox starsBox;

    public ReviewPage(Order order) {
        this.order = order;
    }

    public BorderPane getReviewPage() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #0D1117;"
        );

        // -----------------------------------------------------
        // NAVBAR
        // -----------------------------------------------------

        root.setTop(
                new buyerTop().createBuyerTop("Review")
        );

        // -----------------------------------------------------
        // MAIN CONTENT
        // -----------------------------------------------------

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(35, 80, 35, 80)
        );

        content.setAlignment(Pos.TOP_CENTER);

        Label title = new Label(
                "⭐ Rate & Review"
        );

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "Share your experience with the product"
        );

        subtitle.setFont(
                Font.font(15)
        );

        subtitle.setTextFill(
                Color.web("#9CA3AF")
        );

        // -----------------------------------------------------
        // CARD
        // -----------------------------------------------------

        VBox card = new VBox(18);

        card.setMaxWidth(650);

        card.setPadding(
                new Insets(30)
        );

        card.setStyle(
                "-fx-background-color: #161B22;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #30363D;" +
                "-fx-border-radius: 18;"
        );

        Label orderLabel = new Label(
                "Order: " + order.getOrderId()
        );

        orderLabel.setTextFill(
                Color.web("#9CA3AF")
        );

        orderLabel.setFont(
                Font.font(13)
        );

        // -----------------------------------------------------
        // PRODUCT SELECTOR
        // -----------------------------------------------------

        Label productLabel = new Label(
                "Select Product"
        );

        productLabel.setTextFill(Color.WHITE);

        productLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        productComboBox =
                new ComboBox<>();

        productComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        productComboBox.setPromptText(
                "Choose a product"
        );

        productComboBox.setStyle(
                "-fx-background-color: #21262D;" +
                "-fx-text-fill: white;"
        );

        loadProducts();

        // -----------------------------------------------------
        // STAR RATING
        // -----------------------------------------------------

        Label ratingLabel = new Label(
                "Your Rating"
        );

        ratingLabel.setTextFill(Color.WHITE);

        ratingLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        starsBox = new HBox(8);

        starsBox.setAlignment(
                Pos.CENTER_LEFT
        );

        createStars();

        // -----------------------------------------------------
        // COMMENT
        // -----------------------------------------------------

        Label commentLabel = new Label(
                "Your Review"
        );

        commentLabel.setTextFill(Color.WHITE);

        commentLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        TextArea commentArea = new TextArea();

        commentArea.setPromptText(
                "Write your experience with this product..."
        );

        commentArea.setWrapText(true);

        commentArea.setPrefRowCount(5);

        commentArea.setStyle(
                "-fx-control-inner-background: #21262D;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #8B949E;"
        );

        // -----------------------------------------------------
        // SUBMIT BUTTON
        // -----------------------------------------------------

        Button submitButton =
                new Button("⭐ Submit Review");

        submitButton.setMaxWidth(
                Double.MAX_VALUE
        );

        submitButton.setPrefHeight(48);

        submitButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;"
        );

        submitButton.setOnAction(e -> {

            ProductReviewItem selected =
                    productComboBox.getValue();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Please select a product."
                );

                return;
            }

            if (selectedRating == 0) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Please select a rating."
                );

                return;
            }

            ReviewController controller =
                    new ReviewController();

            boolean success =
                    controller.addReview(
                            BuyerProfilePage.currentBuyerUid,
                            BuyerProfilePage.buyerName,
                            order.getOrderId(),
                            selected.productId,
                            selected.farmerId,
                            selectedRating,
                            commentArea.getText()
                    );

            if (success) {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Review Submitted",
                        "Thank you! Your review has been submitted successfully."
                );

                goBack();

            } else {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Unable to submit review.\n\n"
                                + "You may have already reviewed this product."
                );
            }
        });

        // -----------------------------------------------------
        // BACK BUTTON
        // -----------------------------------------------------

        Button backButton =
                new Button("← Back to Orders");

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #58A6FF;" +
                "-fx-font-size: 14px;"
        );

        backButton.setOnAction(
                e -> goBack()
        );

        card.getChildren().addAll(
                orderLabel,
                productLabel,
                productComboBox,
                ratingLabel,
                starsBox,
                commentLabel,
                commentArea,
                submitButton,
                backButton
        );

        content.getChildren().addAll(
                title,
                subtitle,
                card
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background: #0D1117;" +
                "-fx-background-color: #0D1117;"
        );

        root.setCenter(scrollPane);

        root.setBottom(
                new Footer().createFooter()
        );

        return root;
    }

    // =========================================================
    // LOAD PRODUCTS
    // =========================================================

    private void loadProducts() {

        if (order.getItems() == null) {
            return;
        }

        for (Map<String, Object> item :
                order.getItems()) {

            Object productIdObj =
                    item.get("productId");

            if (!(productIdObj instanceof Number)) {
                continue;
            }

            int productId =
                    ((Number) productIdObj).intValue();

            String productName =
                    String.valueOf(
                            item.getOrDefault(
                                    "productName",
                                    "Product"
                            )
                    );

            int farmerId = 0;

            Object farmerIdObj =
                    item.get("farmerId");

            if (farmerIdObj instanceof Number) {

                farmerId =
                        ((Number) farmerIdObj).intValue();

            } else {

                farmerId = order.getFarmerId();
            }

            productComboBox.getItems().add(
                    new ProductReviewItem(
                            productId,
                            productName,
                            farmerId
                    )
            );
        }
    }

    // =========================================================
    // CREATE STAR BUTTONS
    // =========================================================

    private void createStars() {

        for (int i = 1; i <= 5; i++) {

            final int rating = i;

            Button star =
                    new Button("☆");

            star.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #F5C542;" +
                    "-fx-font-size: 32px;" +
                    "-fx-padding: 0;"
            );

            star.setOnAction(
                    e -> {

                        selectedRating = rating;

                        updateStars();
                    }
            );

            starsBox.getChildren().add(star);
        }
    }

    private void updateStars() {

        for (int i = 0;
             i < starsBox.getChildren().size();
             i++) {

            Button star =
                    (Button) starsBox.getChildren().get(i);

            if (i < selectedRating) {

                star.setText("★");

            } else {

                star.setText("☆");
            }
        }
    }

    // =========================================================
    // GO BACK
    // =========================================================

    private void goBack() {

        try {

            BuyerOrdersPage ordersPage =
                    new BuyerOrdersPage();

            BorderPane page =
                    ordersPage.getOrdersPage();

            Stage stage =
                    LoginPage.mainStage;

            if (stage != null) {

                stage.setScene(
                        new Scene(
                                page,
                                1400,
                                850
                        )
                );

                stage.show();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        showAlert(
                type,
                "AgroBiz",
                message
        );
    }

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

    // =========================================================
    // PRODUCT REVIEW ITEM
    // =========================================================

    private static class ProductReviewItem {

        int productId;
        String productName;
        int farmerId;

        ProductReviewItem(
                int productId,
                String productName,
                int farmerId) {

            this.productId = productId;
            this.productName = productName;
            this.farmerId = farmerId;
        }

        @Override
        public String toString() {

            return productName;
        }
    }
}