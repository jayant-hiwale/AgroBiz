package com.pravartak.view.farmer;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class MarketPlace {

    private Scene marketPlaceScene;

    private ProductController productController;

    private FlowPane productGrid;

    private Label resultLabel;

    // Replace this later with actual logged-in farmer ID
    private int farmerId = 101;

    public MarketPlace() {

        productController =
                new ProductController();
    }

    // =====================================================
    // SCENE
    // =====================================================

    public Scene getMarketPlaceScene() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        root.setTop(
                new NavBar()
                        .createNavbar("Marketplace")
        );

        root.setCenter(
                createMarketplaceContent()
        );

        root.setBottom(
                new Footer()
                        .createFooter()
        );

        marketPlaceScene =
                new Scene(
                        root,
                        1200,
                        750
                );

        return marketPlaceScene;
    }

    // =====================================================
    // CONTENT
    // =====================================================

    private VBox createMarketplaceContent() {

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
                        "Marketplace"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:40px;" +
                "-fx-font-weight:bold;"
        );

        Label description =
                new Label(
                        "Buy and sell agricultural products directly between farmers and buyers."
                );

        description.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        VBox productArea =
                createProductArea();

        VBox.setVgrow(
                productArea,
                Priority.ALWAYS
        );

        content.getChildren()
                .addAll(
                        title,
                        description,
                        productArea
                );

        return content;
    }

    // =====================================================
    // PRODUCT AREA
    // =====================================================

    private VBox createProductArea() {

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
        // TOP
        // =================================================

        HBox top =
                new HBox(12);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        resultLabel =
                new Label(
                        "My Products"
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
                "Search your products..."
        );

        searchBox.setPrefWidth(
                220
        );

        searchBox.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#242B2C;"
        );

        searchBox.setOnKeyReleased(
                e ->
                        searchProducts(
                                searchBox.getText()
                        )
        );

        Button addButton =
                new Button(
                        "+ Add Product"
                );

        addButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:5;" +
                "-fx-padding:7 12;" +
                "-fx-cursor:hand;"
        );

        addButton.setOnAction(
                e ->
                        openAddProductPage()
        );

        // NO SORT BOX
        top.getChildren()
                .addAll(
                        resultLabel,
                        spacer,
                        searchBox,
                        addButton
                );

        // =================================================
        // GRID
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

        scroll.setFitToWidth(true);

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

        area.getChildren()
                .addAll(
                        top,
                        scroll
                );

        return area;
    }

    // =====================================================
    // LOAD FARMER PRODUCTS
    // =====================================================

    private void loadProducts() {

        productGrid
                .getChildren()
                .clear();

        List<Product> products =
                productController
                        .getFarmerProducts(
                                farmerId
                        );

        resultLabel.setText(
                "My Products ("
                + products.size()
                + ")"
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

        card.setPrefWidth(270);

        card.setMaxWidth(270);

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
                160
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
                                270,
                                160,
                                true,
                                true
                        );

                ImageView imageView =
                        new ImageView(
                                image
                        );

                imageView.setFitWidth(
                        270
                );

                imageView.setFitHeight(
                        160
                );

                imageView.setPreserveRatio(
                        true
                );

                imageBox
                        .getChildren()
                        .add(
                                imageView
                        );

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
                "-fx-font-size:17px;" +
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

        Label available =
                new Label(
                        "Available: "
                        + product.getQuantity()
                        + " "
                        + product.getUnit()
                );

        available.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:12px;"
        );

        Label location =
                new Label(
                        "📍 "
                        + product.getLocation()
                );

        location.setStyle(
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

        edit.setMaxWidth(
                Double.MAX_VALUE
        );

        delete.setMaxWidth(
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

        edit.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:5;" +
                "-fx-cursor:hand;"
        );

        delete.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#E57373;" +
                "-fx-border-color:#E57373;" +
                "-fx-border-radius:5;" +
                "-fx-cursor:hand;"
        );

        edit.setOnAction(
                e ->
                        editProduct(product)
        );

        delete.setOnAction(
                e ->
                        deleteProduct(product)
        );

        HBox buttons =
                new HBox(
                        8,
                        edit,
                        delete
                );

        details.getChildren()
                .addAll(
                        name,
                        category,
                        price,
                        available,
                        location,
                        buttons
                );

        card.getChildren()
                .addAll(
                        imageBox,
                        details
                );

        return card;
    }

    // =====================================================
    // IMAGE PLACEHOLDER
    // =====================================================

    private void addImagePlaceholder(
            VBox imageBox) {

        Label label =
                new Label(
                        "Product Image"
                );

        label.setStyle(
                "-fx-text-fill:#666666;" +
                "-fx-font-size:13px;"
        );

        imageBox
                .getChildren()
                .add(label);
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
                        .getFarmerProducts(
                                farmerId
                        );

        productGrid
                .getChildren()
                .clear();

        String search =
                text.toLowerCase();

        int count = 0;

        for (Product product :
                products) {

            if (product.getProductName()
                    .toLowerCase()
                    .contains(search)
                    ||
                    product.getCategory()
                            .toLowerCase()
                            .contains(search)) {

                productGrid
                        .getChildren()
                        .add(
                                createProductCard(
                                        product
                                )
                        );

                count++;
            }
        }

        resultLabel.setText(
                "Found "
                + count
                + " products"
        );
    }

    // =====================================================
    // ADD
    // =====================================================

    private void openAddProductPage() {

        AddProductPage page =
                new AddProductPage();

        Scene scene =
                page.getAddProductScene(
                        () -> {

                            loadProducts();

                            backToMarket();
                        }
                );

        LoginPage.mainStage
                .setScene(scene);
    }

    // =====================================================
    // EDIT
    // =====================================================

    private void editProduct(
            Product product) {

        AddProductPage page =
                new AddProductPage();

        LoginPage.mainStage
                .setScene(
                        page.getAddProductScene(
                                () ->
                                        backToMarket()
                        )
                );
    }

    // =====================================================
    // DELETE
    // =====================================================

    private void deleteProduct(
            Product product) {

        boolean deleted =
                productController
                        .deleteProduct(
                                product.getProductId()
                        );

        if (deleted) {

            loadProducts();
        }
    }

    // =====================================================
    // BACK
    // =====================================================

    public void backToMarket() {

        LoginPage.mainStage
                .setScene(
                        marketPlaceScene
                );
    }
}