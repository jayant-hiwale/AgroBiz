package com.pravartak.view.admin.marketplace;

import java.util.List;

import com.pravartak.controller.farmercontoller.FarmerProfileController;
import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.FarmerProfile;
import com.pravartak.model.farmer_model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AdminMarketplacePage {

    private final ProductController productController;
    private final FarmerProfileController farmerProfileController;

    private FlowPane productGrid;
    private Label resultLabel;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminMarketplacePage() {

        productController =
                new ProductController();

        farmerProfileController =
                new FarmerProfileController();
    }

    // =====================================================
    // MAIN PAGE
    // =====================================================

    public VBox getMarketplacePage() {

        VBox content =
                new VBox(15);

        content.setPadding(
                new Insets(
                        25,
                        20,
                        30,
                        20
                )
        );

        content.setStyle(
                "-fx-background-color:#080C0D;"
        );

        Label title =
                new Label(
                        "Marketplace Management"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:34px;" +
                "-fx-font-weight:bold;"
        );

        Label description =
                new Label(
                        "View and manage all products listed by farmers."
                );

        description.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        VBox marketplaceArea =
                createMarketplaceArea();

        VBox.setVgrow(
                marketplaceArea,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                title,
                description,
                marketplaceArea
        );

        return content;
    }

    // =====================================================
    // MARKETPLACE AREA
    // =====================================================

    private VBox createMarketplaceArea() {

        VBox area =
                new VBox(15);

        area.setPadding(
                new Insets(15)
        );

        area.setStyle(
                "-fx-background-color:#0D1213;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        // =================================================
        // TOP BAR
        // =================================================

        HBox top =
                new HBox(12);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        resultLabel =
                new Label(
                        "All Products"
                );

        resultLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        TextField searchBox =
                new TextField();

        searchBox.setPromptText(
                "Search products..."
        );

        searchBox.setPrefWidth(
                230
        );

        searchBox.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-padding:9 12;"
        );

        searchBox.setOnKeyReleased(
                e -> searchProducts(
                        searchBox.getText()
                )
        );

        Button refreshButton =
                new Button(
                        "Refresh"
                );

        refreshButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:5;" +
                "-fx-padding:7 12;" +
                "-fx-cursor:hand;"
        );

        refreshButton.setOnAction(
                e -> loadProducts()
        );

        top.getChildren().addAll(
                resultLabel,
                spacer,
                searchBox,
                refreshButton
        );

        // =================================================
        // PRODUCT GRID
        // =================================================

        productGrid =
                new FlowPane();

        productGrid.setHgap(15);
        productGrid.setVgap(15);

        productGrid.setPadding(
                new Insets(5)
        );

        productGrid.setAlignment(
                Pos.TOP_LEFT
        );

        ScrollPane scroll =
                new ScrollPane(
                        productGrid
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setStyle(
                "-fx-background:#0D1213;" +
                "-fx-background-color:#0D1213;" +
                "-fx-control-inner-background:#0D1213;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        loadProducts();

        area.getChildren().addAll(
                top,
                scroll
        );

        return area;
    }

    // =====================================================
    // LOAD ALL PRODUCTS
    // =====================================================

    private void loadProducts() {

        productGrid
                .getChildren()
                .clear();

        List<Product> products =
                productController
                        .getAllProducts();

        resultLabel.setText(
                "All Products ("
                + products.size()
                + ")"
        );

        if (products.isEmpty()) {

            Label empty =
                    new Label(
                            "No products available."
                    );

            empty.setStyle(
                    "-fx-text-fill:#777777;" +
                    "-fx-font-size:15px;"
            );

            productGrid
                    .getChildren()
                    .add(empty);

            return;
        }

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
                250
        );

        card.setMaxWidth(
                250
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

        StackPane image =
                new StackPane();

        image.setPrefHeight(
                135
        );

        image.setMinHeight(
                135
        );

        image.setStyle(
                "-fx-background-color:#1B2425;" +
                "-fx-background-radius:12 12 0 0;"
        );

        String imagePath =
                product.getImagePath();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                Image img =
                        new Image(
                                imagePath,
                                250,
                                135,
                                true,
                                true
                        );

                ImageView imageView =
                        new ImageView(img);

                imageView.setFitWidth(
                        250
                );

                imageView.setFitHeight(
                        135
                );

                imageView.setPreserveRatio(
                        true
                );

                image.getChildren()
                        .add(imageView);

            } catch (Exception e) {

                addImagePlaceholder(
                        image
                );
            }

        } else {

            addImagePlaceholder(
                    image
            );
        }

        // =================================================
        // STATUS
        // =================================================

        String statusText =
                product.getStatus();

        if (statusText == null ||
                statusText.trim().isEmpty()) {

            statusText =
                    "Active";
        }

        Label status =
                new Label(
                        statusText
                );

        status.setStyle(
                "-fx-background-color:#245D35;" +
                "-fx-text-fill:white;" +
                "-fx-padding:5 8;" +
                "-fx-background-radius:5;"
        );

        StackPane.setAlignment(
                status,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                status,
                new Insets(10)
        );

        image.getChildren()
                .add(status);

        // =================================================
        // DETAILS
        // =================================================

        VBox details =
                new VBox(7);

        details.setPadding(
                new Insets(12)
        );

        Label name =
                new Label(
                        safe(
                                product.getProductName()
                        )
                );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:16px;" +
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
                "-fx-font-size:11px;"
        );

        Label price =
                new Label(
                        "₹"
                        + product.getPrice()
                        + " / "
                        + safe(product.getUnit())
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        Label quantity =
                new Label(
                        "Stock: "
                        + product.getQuantity()
                        + " "
                        + safe(product.getUnit())
                );

        quantity.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:12px;"
        );

        // =================================================
        // FARMER
        // =================================================

        FarmerProfile farmer =
                farmerProfileController
                        .getProfile(
                                product.getFarmerId()
                        );

        String farmerName =
                "Unknown Farmer";

        if (farmer != null) {

            farmerName =
                    getFarmerName(
                            farmer
                    );
        }

        Label farmerLabel =
                new Label(
                        "Farmer: "
                        + farmerName
                );

        farmerLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:12px;"
        );

        // =================================================
        // BUTTONS
        // =================================================

        Button edit =
                new Button(
                        "Edit"
                );

        Button delete =
                new Button(
                        "Delete"
                );

        Button contact =
                new Button(
                        "Contact Farmer"
                );

        edit.setMaxWidth(
                Double.MAX_VALUE
        );

        delete.setMaxWidth(
                Double.MAX_VALUE
        );

        contact.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                edit,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                delete,
                Priority.ALWAYS
        );

        // =================================================
        // EDIT STYLE
        // =================================================

        edit.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:5;" +
                "-fx-padding:7 8;" +
                "-fx-cursor:hand;"
        );

        // =================================================
        // DELETE STYLE
        // =================================================

        delete.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#E57373;" +
                "-fx-border-color:#E57373;" +
                "-fx-border-radius:5;" +
                "-fx-padding:7 8;" +
                "-fx-cursor:hand;"
        );

        // =================================================
        // CONTACT STYLE
        // =================================================

        contact.setStyle(
                "-fx-background-color:#245D35;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-border-color:#245D35;" +
                "-fx-border-radius:5;" +
                "-fx-padding:7 8;" +
                "-fx-cursor:hand;"
        );

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        edit.setOnAction(
                e -> editProduct(
                        product
                )
        );

        delete.setOnAction(
                e -> deleteProduct(
                        product
                )
        );

        contact.setOnAction(
                e -> showFarmerDetails(
                        product
                )
        );

        HBox firstButtons =
                new HBox(
                        8,
                        edit,
                        delete
                );

        firstButtons.setFillHeight(
                true
        );

        details.getChildren().addAll(
                name,
                category,
                price,
                quantity,
                farmerLabel,
                firstButtons,
                contact
        );

        card.getChildren().addAll(
                image,
                details
        );

        return card;
    }

    // =====================================================
    // CONTACT FARMER
    // =====================================================

    private void showFarmerDetails(
            Product product) {

        FarmerProfile farmer =
                farmerProfileController
                        .getProfile(
                                product.getFarmerId()
                        );

        if (farmer == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Farmer Not Found",
                    "Farmer details could not be found."
            );

            return;
        }

        FarmerDetailsDialog dialog =
                new FarmerDetailsDialog(
                        farmer
                );

        dialog.show();
    }

    // =====================================================
    // DELETE PRODUCT
    // =====================================================

    private void deleteProduct(
            Product product) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Product"
        );

        confirmation.setHeaderText(
                "Delete this product?"
        );

        confirmation.setContentText(
                "Are you sure you want to delete \""
                + safe(product.getProductName())
                + "\"?"
        );

        confirmation.showAndWait()
                .ifPresent(response -> {

                    if (response ==
                            javafx.scene.control.ButtonType.OK) {

                        boolean deleted =
                                productController
                                        .deleteProduct(
                                                product.getProductId()
                                        );

                        if (deleted) {

                            loadProducts();

                        } else {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "Delete Failed",
                                    "Unable to delete the product."
                            );
                        }
                    }
                });
    }

    // =====================================================
    // EDIT PRODUCT
    // =====================================================

    private void editProduct(
        Product product) {

    AdminEditProductPage editPage =
            new AdminEditProductPage(
                    product
            );

    editPage.show();

    // Refresh marketplace after
    // edit window is closed.
    loadProducts();
}

    // =====================================================
    // SEARCH
    // =====================================================

    private void searchProducts(
            String text) {

        if (text == null ||
                text.trim().isEmpty()) {

            loadProducts();

            return;
        }

        List<Product> products =
                productController
                        .searchAllProducts(
                                text
                        );

        productGrid
                .getChildren()
                .clear();

        resultLabel.setText(
                "Found "
                + products.size()
                + " products"
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
    // FARMER NAME
    // =====================================================

    private String getFarmerName(
            FarmerProfile farmer) {

        /*
         * This method uses reflection so this class
         * does not assume which name getter your
         * FarmerProfile currently has.
         */

        try {

            var method =
                    farmer.getClass()
                            .getMethod(
                                    "getFullName"
                            );

            Object value =
                    method.invoke(farmer);

            if (value != null) {

                return value.toString();
            }

        } catch (Exception ignored) {
        }

        try {

            var method =
                    farmer.getClass()
                            .getMethod(
                                    "getName"
                            );

            Object value =
                    method.invoke(farmer);

            if (value != null) {

                return value.toString();
            }

        } catch (Exception ignored) {
        }

        return "Farmer #" +
                farmer.getFarmerId();
    }

    // =====================================================
    // IMAGE PLACEHOLDER
    // =====================================================

    private void addImagePlaceholder(
            StackPane box) {

        Label label =
                new Label(
                        "Product Image"
                );

        label.setStyle(
                "-fx-text-fill:#666666;" +
                "-fx-font-size:13px;"
        );

        box.getChildren()
                .add(label);
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
    // ALERT
    // =====================================================

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