package com.pravartak.view.buyer;

import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;
import javafx.scene.layout.BorderPane;
import com.pravartak.controller.buyercontroller.ReviewController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ProductDetailsPage {

    private final Product product;

    private Spinner<Integer> quantitySpinner;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ProductDetailsPage(Product product) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Product cannot be null."
            );
        }

        this.product = product;
    }

    // =====================================================
    // PAGE
    // =====================================================

    public BorderPane getProductDetailsPage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // NAVBAR
        // =================================================

        root.setTop(
                new buyerTop().createBuyerTop("Market")
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

        VBox main =
                new VBox();

        main.setPadding(
                new Insets(
                        25,
                        40,
                        30,
                        40
                )
        );

        // =================================================
        // BACK BUTTON
        // =================================================

        Button backButton =
                new Button(
                        "← Back to Marketplace"
                );

        backButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 12 0;"
        );

        backButton.setOnAction(
                e -> goBackToMarketplace()
        );

        // =================================================
        // PRODUCT CONTENT
        // =================================================

        HBox productContent =
                new HBox(35);

        productContent.setPadding(
                new Insets(10)
        );

        // =================================================
        // IMAGE SECTION
        // =================================================

        VBox imageSection =
                createImageSection();

        imageSection.setPrefWidth(
                500
        );

        // =================================================
        // DETAILS SECTION
        // =================================================

        VBox detailsSection =
                createDetailsSection();

        HBox.setHgrow(
                detailsSection,
                Priority.ALWAYS
        );

        productContent.getChildren().addAll(
                imageSection,
                detailsSection
        );

        // =================================================
        // ADD
        // =================================================

        main.getChildren().addAll(
                backButton,
                productContent
        );


        
        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scroll =
                new ScrollPane(
                        main
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        root.setCenter(
                scroll
        );

        return root;
    }

    // =====================================================
    // IMAGE SECTION
    // =====================================================

    private VBox createImageSection() {

        VBox section =
                new VBox();

        section.setAlignment(
                Pos.TOP_CENTER
        );

        section.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:15;" +
                "-fx-background-radius:15;"
        );

        section.setPadding(
                new Insets(15)
        );

        VBox imageBox =
                new VBox();

        imageBox.setPrefHeight(
                430
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color:#1B2425;" +
                "-fx-background-radius:10;"
        );

        String imagePath =
                product.getImagePath();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imagePath,
                                470,
                                420,
                                true,
                                true
                        );

                if (!image.isError()) {

                    ImageView imageView =
                            new ImageView(
                                    image
                            );

                    imageView.setPreserveRatio(
                            true
                    );

                    imageView.setFitWidth(
                            470
                    );

                    imageView.setFitHeight(
                            420
                    );

                    imageBox.getChildren().add(
                            imageView
                    );

                } else {

                    addImagePlaceholder(
                            imageBox
                    );
                }

            } catch (Exception e) {

                addImagePlaceholder(
                        imageBox
                );
            }

        } else {

            addImagePlaceholder(
                    imageBox
            );
        }

        // =================================================
        // IMAGE CAPTION
        // =================================================

        Label caption =
                new Label(
                        "Product Image"
                );

        caption.setStyle(
                "-fx-text-fill:#666666;" +
                "-fx-font-size:11px;"
        );

        VBox.setMargin(
                caption,
                new Insets(8, 0, 2, 0)
        );

        section.getChildren().addAll(
                imageBox,
                caption
        );

        return section;
    }

    // =====================================================
    // DETAILS SECTION
    // =====================================================

    private VBox createDetailsSection() {

        VBox details =
                new VBox(15);

        details.setPadding(
                new Insets(10, 15, 10, 5)
        );

        // =================================================
        // CATEGORY
        // =================================================

        Label category =
                new Label(
                        safe(
                                product.getCategory()
                        ).toUpperCase()
                );

        category.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // PRODUCT NAME
        // =================================================

        Label name =
                new Label(
                        safe(
                                product.getProductName()
                        )
                );

        name.setWrapText(
                true
        );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // PRICE
        // =================================================

        Label price =
                new Label(
                        String.format(
                                "₹%.2f / %s",
                                product.getPrice(),
                                safe(
                                        product.getUnit()
                                )
                        )
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
// PRODUCT RATING
// =================================================

ReviewController reviewController =
        new ReviewController();

double averageRating =
        reviewController.getAverageRating(
                product.getProductId()
        );

int reviewCount =
        reviewController.getReviewCount(
                product.getProductId()
        );

Label ratingLabel;

if (reviewCount == 0) {

    ratingLabel =
            new Label("⭐ No reviews yet");

} else {

    ratingLabel =
            new Label(
                    String.format(
                            "⭐ %.1f / 5  (%d reviews)",
                            averageRating,
                            reviewCount
                    )
            );
}

ratingLabel.setStyle(
        "-fx-text-fill:#F5C542;" +
        "-fx-font-size:16px;" +
        "-fx-font-weight:bold;"
);

        // =================================================
        // STOCK
        // =================================================

        Label stock =
                new Label(
                        "Available: "
                                + formatNumber(
                                product.getQuantity()
                        )
                                + " "
                                + safe(
                                product.getUnit()
                        )
                );

        if (product.getQuantity() > 0) {

            stock.setStyle(
                    "-fx-text-fill:#68D34A;" +
                    "-fx-font-size:14px;"
            );

        } else {

            stock.setText(
                    "Out of Stock"
            );

            stock.setStyle(
                    "-fx-text-fill:#FF4D5A;" +
                    "-fx-font-size:14px;" +
                    "-fx-font-weight:bold;"
            );
        }

        // =================================================
        // LOCATION
        // =================================================

        Label location =
                new Label(
                        "📍 "
                                + safe(
                                product.getLocation()
                        )
                );

        location.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        // =================================================
        // SEPARATOR
        // =================================================

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color:#30363D;"
        );

        // =================================================
        // DESCRIPTION TITLE
        // =================================================

        Label descriptionTitle =
                new Label(
                        "About this product"
                );

        descriptionTitle.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // DESCRIPTION
        // =================================================

        Label description =
                new Label(
                        safeDescription(
                                product.getDescription()
                        )
                );

        description.setWrapText(
                true
        );

        description.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;" +
                "-fx-line-spacing:3px;"
        );

        // =================================================
        // FARMER INFORMATION
        // =================================================

        VBox farmerBox =
                createFarmerBox();

        // =================================================
        // QUANTITY
        // =================================================

        Label quantityTitle =
                new Label(
                        "Select Quantity"
                );

        quantityTitle.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        quantitySpinner =
                new Spinner<>();

        int maxQuantity =
                getMaxSpinnerQuantity();

        quantitySpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        maxQuantity,
                        1
                )
        );

        quantitySpinner.setPrefWidth(
                120
        );

        quantitySpinner.setPrefHeight(
                40
        );

        quantitySpinner.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;"
        );

        // =================================================
        // BUTTONS
        // =================================================

        HBox buttons =
                new HBox(10);

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        Button addToCart =
                new Button(
                        "🛒 Add to Cart"
                );

        addToCart.setPrefHeight(
                45
        );

        addToCart.setPrefWidth(
                180
        );

        addToCart.setStyle(
                "-fx-background-color:#212627;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-cursor:hand;"
        );

        addToCart.setOnAction(
                e -> addProductToCart()
        );

        Button buyNow =
                new Button(
                        "⚡ Buy Now"
                );

        buyNow.setPrefHeight(
                45
        );

        buyNow.setPrefWidth(
                160
        );

        buyNow.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-cursor:hand;"
        );

        buyNow.setOnAction(
                e -> buyNow()
        );

        buttons.getChildren().addAll(
                addToCart,
                buyNow
        );

        // =================================================
        // CHAT BUTTON
        // =================================================

        Button chatButton =
                new Button(
                        "💬 Chat with Farmer"
                );

        chatButton.setPrefHeight(
                42
        );

        chatButton.setMaxWidth(
                Double.MAX_VALUE
        );

        chatButton.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:10;" +
                "-fx-cursor:hand;"
        );

       chatButton.setOnAction(e -> {

    try {

        BuyerFarmerChatPage chatPage =
                new BuyerFarmerChatPage(
                        product
                );

        BorderPane page =
                chatPage.getChatPage();

        Scene scene =
                new Scene(
                        page,
                        1400,
                        850
                );

        LoginPage.mainStage
                .setScene(scene);

        LoginPage.mainStage.show();

    } catch (Exception ex) {

        ex.printStackTrace();

        showAlert(
                Alert.AlertType.ERROR,
                "Unable to open farmer chat."
        );
    }
});

        // =================================================
        // ADD ALL
        // =================================================

        details.getChildren().addAll(
                category,
                name,
                price,
                ratingLabel,
                stock,
                location,
                separator,
                descriptionTitle,
                description,
                farmerBox,
                quantityTitle,
                quantitySpinner,
                buttons,
                chatButton
        );

        return details;
    }

    // =====================================================
    // FARMER BOX
    // =====================================================

    private VBox createFarmerBox() {

        VBox box =
                new VBox(5);

        box.setPadding(
                new Insets(12)
        );

        box.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );

        Label title =
                new Label(
                        "👨‍🌾 Seller Information"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;"
        );

        Label farmer =
                new Label(
                        "Farmer ID: "
                                + product.getFarmerId()
                );

        farmer.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        box.getChildren().addAll(
                title,
                farmer
        );

        return box;
    }

    // =====================================================
    // ADD TO CART
    // =====================================================

    private void addProductToCart() {

        if (product.getQuantity() <= 0) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "This product is currently out of stock."
            );

            return;
        }

        int selectedQuantity =
                quantitySpinner
                        .getValue();

        if (selectedQuantity >
                product.getQuantity()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Selected quantity is greater than available stock."
            );

            return;
        }

        CartManager.addProduct(
                product,
                selectedQuantity
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                selectedQuantity
                        + " "
                        + safe(product.getUnit())
                        + " of "
                        + product.getProductName()
                        + " added to cart."
        );
    }

    // =====================================================
    // BUY NOW
    // =====================================================

    private void buyNow() {

        if (product.getQuantity() <= 0) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "This product is currently out of stock."
            );

            return;
        }

        int selectedQuantity =
                quantitySpinner
                        .getValue();

        if (selectedQuantity >
                product.getQuantity()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Selected quantity is greater than available stock."
            );

            return;
        }

        /*
         * Clear previous cart items so Buy Now
         * represents only this product.
         */

        CartManager.clear();

        CartManager.addProduct(
                product,
                selectedQuantity
        );

        /*
         * CheckoutPage will read the current
         * CartManager contents.
         */

        try {

            CheckoutPage checkoutPage =
                    new CheckoutPage();

            LoginPage.mainStage.setScene(
                    new Scene(
                            checkoutPage.getCheckoutPage()
                    )
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Checkout page is not available yet."
            );
        }
    }


   
    // =====================================================
    // BACK
    // =====================================================

    private void goBackToMarketplace() {

        BuyerMarketPlace marketplace =
                new BuyerMarketPlace();

        LoginPage.mainStage.setScene(
                new Scene(
                        marketplace.getMarketplacePage()
                )
        );
    }

    // =====================================================
    // MAX SPINNER QUANTITY
    // =====================================================

    private int getMaxSpinnerQuantity() {

        double quantity =
                product.getQuantity();

        if (quantity <= 0) {
            return 1;
        }

        if (quantity > 999999) {
            return 999999;
        }

        return Math.max(
                1,
                (int) Math.floor(quantity)
        );
    }

    // =====================================================
    // IMAGE PLACEHOLDER
    // =====================================================

    private void addImagePlaceholder(
            VBox box) {

        Label label =
                new Label(
                        "Product Image"
                );

        label.setStyle(
                "-fx-text-fill:#666666;" +
                "-fx-font-size:16px;"
        );

        box.getChildren().add(
                label
        );
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
    // SAFE DESCRIPTION
    // =====================================================

    private String safeDescription(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "No description provided by the farmer.";
        }

        return value;
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "AgroBiz Marketplace"
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