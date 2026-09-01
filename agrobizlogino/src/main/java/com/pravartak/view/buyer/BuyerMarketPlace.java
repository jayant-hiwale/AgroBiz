package com.pravartak.view.buyer;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class BuyerMarketPlace {

    private final ProductController controller;

    private FlowPane productGrid;

    private Label resultLabel;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BuyerMarketPlace() {

        controller = new ProductController();
    }

    // =====================================================
    // PAGE
    // =====================================================

    public BorderPane getMarketplacePage() {

        // =================================================
        // MAIN BORDERPANE
        // =================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // TOP NAVBAR
        // =================================================

        root.setTop(
                new buyerTop().createBuyerTop("Marketplace")
        );

        // =================================================
        // FOOTER
        // =================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =================================================
        // MAIN CONTENT
        // =================================================

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(
                        25,
                        35,
                        25,
                        35
                )
        );

        // =================================================
        // TITLE
        // =================================================

        Label title = new Label(
                "Marketplace"
        );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // SUBTITLE
        // =================================================

        Label subtitle = new Label(
                "Buy fresh agricultural products directly from farmers."
        );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        // =================================================
        // SEARCH BAR
        // =================================================

        HBox searchBar = new HBox(10);

        searchBar.setAlignment(
                Pos.CENTER_LEFT
        );

        TextField search = new TextField();

        search.setPromptText(
                "Search products..."
        );

        search.setPrefWidth(
                350
        );

        search.setPrefHeight(
                38
        );

        search.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#FFFFFF;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8 12;"
        );

        resultLabel = new Label(
                "Products"
        );

        resultLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        search.setOnKeyReleased(
                e -> searchProducts(
                        search.getText()
                )
        );

        searchBar.getChildren().addAll(
                search,
                resultLabel
        );

        // =================================================
        // PRODUCT GRID
        // =================================================

        productGrid = new FlowPane();

        productGrid.setHgap(
                20
        );

        productGrid.setVgap(
                20
        );

        productGrid.setPadding(
                new Insets(5)
        );

        productGrid.setAlignment(
                Pos.TOP_LEFT
        );

        // =================================================
        // SCROLL PANE
        // =================================================

        ScrollPane scroll = new ScrollPane(
                productGrid
        );

        scroll.setFitToWidth(
                true
        );

        scroll.setFitToHeight(
                false
        );

        scroll.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        // =================================================
        // ADD CONTENT
        // =================================================

        content.getChildren().addAll(
                title,
                subtitle,
                searchBar,
                scroll
        );

        // =================================================
        // CENTER
        // =================================================

        root.setCenter(
                content
        );

        // =================================================
        // LOAD PRODUCTS
        // =================================================

        loadProducts();

        return root;
    }

    // =====================================================
    // LOAD ALL PRODUCTS
    // =====================================================

    private void loadProducts() {

        productGrid
                .getChildren()
                .clear();

        List<Product> products =
                controller.getAllProducts();

        resultLabel.setText(
                products.size()
                        + " Products"
        );

        for (Product product :
                products) {

            productGrid
                    .getChildren()
                    .add(
                            createProductCard(
                                    product
                            )
                    );
        }
    }

    // =====================================================
    // SEARCH PRODUCTS
    // =====================================================

    private void searchProducts(
            String text) {

        List<Product> products;

        if (text == null ||
                text.trim().isEmpty()) {

            products =
                    controller.getAllProducts();

        } else {

            products =
                    controller.searchAllProducts(
                            text
                    );
        }

        productGrid
                .getChildren()
                .clear();

        resultLabel.setText(
                products.size()
                        + " Products"
        );

        for (Product product :
                products) {

            productGrid
                    .getChildren()
                    .add(
                            createProductCard(
                                    product
                            )
                    );
        }
    }

    // =====================================================
    // PRODUCT CARD
    // =====================================================

    private VBox createProductCard(
            Product product) {

        VBox card =
                new VBox();

        card.setPrefWidth(
                280
        );

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        // =================================================
        // IMAGE
        // =================================================

        VBox imageBox =
                new VBox();

        imageBox.setPrefHeight(
                165
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color:#1B2425;" +
                "-fx-background-radius:12 12 0 0;"
        );

        String imagePath =
                product.getImagePath();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imagePath,
                                280,
                                165,
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
                            280
                    );

                    imageView.setFitHeight(
                            165
                    );

                    imageBox
                            .getChildren()
                            .add(
                                    imageView
                            );

                } else {

                    addPlaceholder(
                            imageBox
                    );
                }

            } catch (Exception e) {

                addPlaceholder(
                        imageBox
                );
            }

        } else {

            addPlaceholder(
                    imageBox
            );
        }

        // =================================================
        // DETAILS
        // =================================================

        VBox details =
                new VBox(8);

        details.setPadding(
                new Insets(15)
        );

        Label name =
                new Label(
                        safe(
                                product.getProductName()
                        )
                );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label category =
                new Label(
                        safe(
                                product.getCategory()
                        )
                );

        category.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:12px;"
        );

        Label price =
                new Label(
                        "₹"
                                + product.getPrice()
                                + " / "
                                + safe(
                                product.getUnit()
                        )
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label quantity =
                new Label(
                        "Available: "
                                + product.getQuantity()
                                + " "
                                + safe(
                                product.getUnit()
                        )
                );

        quantity.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        Label location =
                new Label(
                        "📍 "
                                + safe(
                                product.getLocation()
                        )
                );

        location.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        // =================================================
        // BUTTON BOX
        // =================================================

        HBox buttonBox =
                new HBox(8);

        buttonBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // LIKE BUTTON
        // =================================================

        Button likeButton =
                new Button();

        likeButton.setPrefWidth(
                55
        );

        updateLikeButton(
                likeButton,
                product
        );

        likeButton.setOnAction(e -> {

            if (WatchlistManager.isLiked(
                    product
            )) {

                WatchlistManager.removeProduct(
                        product
                );

            } else {

                WatchlistManager.addProduct(
                        product
                );
            }

            updateLikeButton(
                    likeButton,
                    product
            );
        });

        // =================================================
        // CONTACT FARMER
        // =================================================

        Button contact =
                new Button(
                        "Contact Farmer"
                );

        contact.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                contact,
                Priority.ALWAYS
        );

        contact.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:9;" +
                "-fx-cursor:hand;"
        );

        contact.setOnAction(
                e -> showFarmerDetails(
                        product
                )
        );

        buttonBox.getChildren().addAll(
                likeButton,
                contact
        );

        // =================================================
        // DETAILS CHILDREN
        // =================================================

        details.getChildren().addAll(
                name,
                category,
                price,
                quantity,
                location,
                buttonBox
        );

        card.getChildren().addAll(
                imageBox,
                details
        );

        return card;
    }

    // =====================================================
    // LIKE BUTTON UI
    // =====================================================

    private void updateLikeButton(
            Button button,
            Product product) {

        if (WatchlistManager.isLiked(
                product
        )) {

            button.setText(
                    "❤️"
            );

            button.setStyle(
                    "-fx-background-color:#3A1518;" +
                    "-fx-text-fill:#FF4D5A;" +
                    "-fx-font-size:18px;" +
                    "-fx-background-radius:7;" +
                    "-fx-cursor:hand;"
            );

        } else {

            button.setText(
                    "♡"
            );

            button.setStyle(
                    "-fx-background-color:#212627;" +
                    "-fx-text-fill:#AAAAAA;" +
                    "-fx-font-size:22px;" +
                    "-fx-background-radius:7;" +
                    "-fx-cursor:hand;"
            );
        }
    }

    // =====================================================
    // FARMER DETAILS
    // =====================================================

    private void showFarmerDetails(
            Product product) {

        int farmerId =
                product.getFarmerId();

        System.out.println(
                "Opening farmer details for ID = "
                        + farmerId
        );

        FarmerDetailsPage page =
                new FarmerDetailsPage(
                        farmerId,
                        product.getProductName()
                );

        page.show();
    }

    // =====================================================
    // PLACEHOLDER
    // =====================================================

    private void addPlaceholder(
            VBox box) {

        Label label =
                new Label(
                        "Product Image"
                );

        label.setStyle(
                "-fx-text-fill:#666666;"
        );

        box.getChildren().add(
                label
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
    public void searchByCategory(String category) {

    if (category == null || category.trim().isEmpty()) {
        return;
    }

    searchProducts(category);
}
}