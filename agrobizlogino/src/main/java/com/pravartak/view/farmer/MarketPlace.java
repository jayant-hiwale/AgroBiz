package com.pravartak.view.farmer;

import java.util.List;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MarketPlace {

        private Scene marketPlaceScene;
        private ProductController productController;
        private FlowPane productGrid;
        private Label resultLabel;
        private int farmerId = 101;

        public MarketPlace() {
                productController = new ProductController();
        }

        // ================= SCENE =================

        public Scene getMarketPlaceScene() {

                BorderPane root = new BorderPane();

                root.setTop(new NavBar().createNavbar("MarketPlace"));
                root.setCenter(createMarketplaceContent());
                root.setBottom(new Footer().createFooter());

                marketPlaceScene = new Scene(root);

                return marketPlaceScene;
        }

        // ================= MARKETPLACE CONTENT =================

        private VBox createMarketplaceContent() {

                VBox content = new VBox(15);

                content.setPadding(new Insets(25, 20, 30, 20));

                content.setStyle("-fx-background-color: #080c0d;");

                Label title = new Label("Marketplace");

                title.setStyle(
                                "-fx-text-fill: #eeeeee;" +
                                                "-fx-font-size: 40px;" +
                                                "-fx-font-weight: bold;");

                Label description = new Label("Manage and sell your agricultural products directly to buyers.");

                description.setStyle(
                                "-fx-text-fill: #aaaaaa;" +
                                                "-fx-font-size: 14px;");

                VBox productArea = createProductArea();

                VBox.setVgrow(productArea, Priority.ALWAYS);

                content.getChildren().addAll(
                                title,
                                description,
                                productArea);

                return content;
        }

        // ================= PRODUCT AREA =================

        private VBox createProductArea() {

                VBox area = new VBox(15);

                area.setPadding(new Insets(15));

                area.setStyle(
                                "-fx-background-color: #0d1213;" +
                                                "-fx-border-color: #242b2c;" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-background-radius: 12;");

                // Top bar
                HBox top = new HBox(12);

                top.setAlignment(Pos.CENTER_LEFT);

                resultLabel = new Label("My Products");
                resultLabel.setStyle("-fx-text-fill: #aaaaaa;" + "-fx-font-size: 13px;");

                Region spacer = new Region();

                HBox.setHgrow(spacer, Priority.ALWAYS);

                TextField searchBox = new TextField();
                searchBox.setPromptText("Search your products...");
                searchBox.setPrefWidth(200);
                searchBox.setOnKeyReleased(e -> searchProducts(searchBox.getText()));

                Button addButton = new Button("+ Add Product");

                addButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #68d34a;" +
                                                "-fx-border-color: #68d34a;" +
                                                "-fx-border-radius: 5;" +
                                                "-fx-padding: 7 12;" +
                                                "-fx-cursor: hand;");

                addButton.setOnAction(
                                e -> openAddProductPage());

                ComboBox<String> sortBox = new ComboBox<>();

                sortBox.getItems().addAll(
                                "Recommended",
                                "Price: Low to High",
                                "Price: High to Low",
                                "Newest");

                sortBox.setValue("Recommended");

                sortBox.setOnAction(
                                e -> sortProducts(sortBox.getValue()));

                top.getChildren().addAll(
                                resultLabel,
                                spacer,
                                searchBox,
                                addButton,
                                sortBox);

                // Product grid
                productGrid = new FlowPane();

                productGrid.setHgap(15);
                productGrid.setVgap(15);
                productGrid.setPadding(new Insets(5));
                productGrid.setAlignment(Pos.TOP_LEFT);

                ScrollPane scroll = new ScrollPane(productGrid);

                scroll.setFitToWidth(true);

                scroll.setStyle("-fx-background: #0d1213;" + "-fx-background-color: #0d1213;");

                VBox.setVgrow(scroll, Priority.ALWAYS);

                loadProducts();

                area.getChildren().addAll(top, scroll);

                return area;
        }

        // ================= LOAD PRODUCTS =================

        private void loadProducts() {

                productGrid.getChildren().clear();

                List<Product> products = productController.getFarmerProducts(farmerId);

                resultLabel.setText("My Products (" + products.size() + ")");

                for (Product product : products) {
                        productGrid.getChildren().add(createProductCard(product));
                }
        }

        // ================= PRODUCT CARD =================

        private VBox createProductCard(Product product) {

                VBox card = new VBox();

                card.setPrefWidth(250);
                card.setMaxWidth(250);

                card.setStyle(
                                "-fx-background-color: #101516;" +
                                                "-fx-border-color: #242b2c;" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-background-radius: 12;");

                // // Image
                // StackPane image = new StackPane();

                // image.setPrefHeight(135);

                // image.setStyle("-fx-background-color: #1b2425;-fx-background-radius: 12 12 0 0;");

                // Label imageText = new Label("Product Image");

                // imageText.setStyle("-fx-text-fill: #666666;-fx-font-size: 13px;");

                // image.getChildren().add(imageText);

                // Image
                StackPane image = new StackPane();

                image.setPrefHeight(135);

                image.setStyle(
                        "-fx-background-color: #1b2425;" +
                        "-fx-background-radius: 12 12 0 0;");

                // =====================================================
                // DISPLAY PRODUCT IMAGE
                // =====================================================

                String imagePath = product.getImagePath();

                if (imagePath != null
                        && !imagePath.trim().isEmpty()) {

                try {

                        Image productImage =
                                new Image(
                                        new File(imagePath)
                                                .toURI()
                                                .toString());

                        ImageView imageView =
                                new ImageView(productImage);

                        imageView.setFitWidth(250);
                        imageView.setFitHeight(135);

                        imageView.setPreserveRatio(false);

                        image.getChildren().add(imageView);

                } catch (Exception ex) {

                        Label imageText =
                                new Label("Product Image");

                        imageText.setStyle(
                                "-fx-text-fill: #666666;" +
                                "-fx-font-size: 13px;");

                        image.getChildren().add(imageText);
                }

                } else {

                Label imageText =
                        new Label("Product Image");

                imageText.setStyle(
                        "-fx-text-fill: #666666;" +
                        "-fx-font-size: 13px;");

                image.getChildren().add(imageText);
                }

                // Status
                Label status = new Label(product.getStatus());

                status.setStyle(product.getStatus().equals("Active")
                                ? "-fx-background-color: #245d35;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-padding: 5 8;" +
                                                "-fx-background-radius: 5;"
                                : "-fx-background-color: #633333;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-padding: 5 8;" +
                                                "-fx-background-radius: 5;");

                StackPane.setAlignment(status, Pos.TOP_LEFT);

                StackPane.setMargin(status, new Insets(10));

                image.getChildren().add(status);

                // Details
                VBox details = new VBox(7);

                details.setPadding(
                                new Insets(12));

                Label name = new Label(product.getProductName());

                name.setStyle(
                                "-fx-text-fill: #eeeeee;" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;");

                Label category = new Label(product.getCategory());

                category.setStyle("-fx-text-fill: #888888;" + "-fx-font-size: 11px;");

                Label price = new Label("₹" + product.getPrice() + " / " + product.getUnit());

                price.setStyle(
                                "-fx-text-fill: #68d34a;" +
                                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;");

                Label quantity = new Label("Stock: " + product.getQuantity() + " " + product.getUnit());

                quantity.setStyle("-fx-text-fill: #aaaaaa;" + "-fx-font-size: 12px;");

               // Label orders = new Label("Orders: " + product.getOrders());

                        // orders.setStyle(
                        //                 "-fx-text-fill: #888888;" +
                        //                                 "-fx-font-size: 11px;");

                // Buttons
                Button edit = new Button("Edit");

                Button delete = new Button("Delete");

                edit.setMaxWidth(Double.MAX_VALUE);
                delete.setMaxWidth(Double.MAX_VALUE);

                HBox.setHgrow(edit, Priority.ALWAYS);

                HBox.setHgrow(delete, Priority.ALWAYS);

                edit.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #68d34a;" +
                                                "-fx-border-color: #68d34a;" +
                                                "-fx-border-radius: 5;" +
                                                "-fx-cursor: hand;");

                delete.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #e57373;" +
                                                "-fx-border-color: #e57373;" +
                                                "-fx-border-radius: 5;" +
                                                "-fx-cursor: hand;");

                edit.setOnAction(e -> {
                        editProduct(product);
                });

                delete.setOnAction(e -> {
                        deleteProduct(product);
                });

                HBox buttons = new HBox(8, edit, delete);

                details.getChildren().addAll(
                                name,
                                category,
                                price,
                                quantity,
                                // orders,
                                buttons);

                card.getChildren().addAll(
                                image,
                                details);

                return card;
        }

        // ================= SEARCH =================

        private void searchProducts(String text) {

                if (text.isEmpty()) {
                        loadProducts();
                        return;
                }

                List<Product> products = productController.searchProducts(
                                farmerId,
                                text);

                productGrid.getChildren().clear();

                resultLabel.setText("Found " + products.size() + " products");

                for (Product product : products) {
                        productGrid.getChildren().add(createProductCard(product));
                }
        }

        // ================= SORT =================

        private void sortProducts(String sortType) {

                List<Product> products = productController.getFarmerProducts(farmerId);

                products = productController.sortProducts(
                                products,
                                sortType);

                productGrid.getChildren().clear();

                for (Product product : products) {
                        productGrid.getChildren().add(createProductCard(product));
                }
        }

        // ================= ADD PRODUCT =================

        private void openAddProductPage() {
                AddProductPage page = new AddProductPage(productController);

                Scene scene = page.getAddProductScene(() -> {
                        // Reload products after adding
                        loadProducts();
                        // Go back to marketplace
                        backToMarket();
                });
                LoginPage.mainStage.setScene(scene);
        }

        // ================= EDIT PRODUCT =================

        private void editProduct(Product product) {

                System.out.println("Edit product: " + product.getProductName());

                AddProductPage page = new AddProductPage(productController);

                Runnable callback = () -> {
                        backToMarket();
                };

                LoginPage.mainStage.setScene(page.getAddProductScene(callback));
        }

        // ================= DELETE PRODUCT =================

        private void deleteProduct(Product product) {

                boolean deleted = productController.deleteProduct(product.getProductId());

                if (deleted) {

                        System.out.println("Product deleted: " + product.getProductName());

                        loadProducts();
                }
        }

        // ================= BACK =================

        public void backToMarket() {

                LoginPage.mainStage.setScene(marketPlaceScene);
        }

}