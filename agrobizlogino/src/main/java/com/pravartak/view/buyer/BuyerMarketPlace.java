package com.pravartak.view.buyer;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class BuyerMarketPlace {

    private ProductController controller;

    private FlowPane productGrid;

    private Label resultLabel;

    public BuyerMarketPlace() {

        controller =
                new ProductController();
    }

    public VBox getMarketplacePage() {

        VBox root =
                new VBox(20);

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        Label title =
                new Label(
                        "Marketplace"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Buy fresh agricultural products directly from farmers."
                );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        HBox searchBar =
                new HBox(10);

        TextField search =
                new TextField();

        search.setPromptText(
                "Search products..."
        );

        search.setPrefWidth(
                300
        );

        resultLabel =
                new Label(
                        "Products"
                );

        resultLabel.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        search.setOnKeyReleased(
                e ->
                        searchProducts(
                                search.getText()
                        )
        );

        searchBar.getChildren()
                .addAll(
                        search,
                        resultLabel
                );

        productGrid =
                new FlowPane();

        productGrid.setHgap(15);

        productGrid.setVgap(15);

        productGrid.setPadding(
                new Insets(5)
        );

        ScrollPane scroll =
                new ScrollPane(
                        productGrid
                );

        scroll.setFitToWidth(true);

        scroll.setStyle(
                "-fx-background:#080C0D;" +
                "-fx-background-color:#080C0D;" +
                "-fx-control-inner-background:#080C0D;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        root.getChildren()
                .addAll(
                        title,
                        subtitle,
                        searchBar,
                        scroll
                );

        loadProducts();

        return root;
    }

    // =====================================================
    // ALL PRODUCTS
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
    // SEARCH
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

                ImageView imageView =
                        new ImageView(image);

                imageView.setPreserveRatio(
                        true
                );

                imageView.setFitWidth(
                        280
                );

                imageBox
                        .getChildren()
                        .add(
                                imageView
                        );

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
                        product.getProductName()
                );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label category =
                new Label(
                        product.getCategory()
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
                        + product.getUnit()
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
                        + product.getUnit()
                );

        quantity.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        Label location =
                new Label(
                        "📍 "
                        + product.getLocation()
                );

        location.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        Button contact =
                new Button(
                        "Contact Farmer"
                );

        contact.setMaxWidth(
                Double.MAX_VALUE
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
                e ->
                        showFarmerDetails(
                                product
                        )
        );

        details.getChildren()
                .addAll(
                        name,
                        category,
                        price,
                        quantity,
                        location,
                        contact
                );

        card.getChildren()
                .addAll(
                        imageBox,
                        details
                );

        return card;
    }

    private void addPlaceholder(
            VBox box) {

        Label label =
                new Label(
                        "Product Image"
                );

        label.setStyle(
                "-fx-text-fill:#666666;"
        );

        box.getChildren()
                .add(label);
    }

    // =====================================================
    // FARMER DETAILS
    // =====================================================

    private void showFarmerDetails(
            Product product) {

        FarmerDetailsPage page =
                new FarmerDetailsPage(
                        product.getFarmerId(),
                        product.getProductName()
                );

        page.show();
    }
}