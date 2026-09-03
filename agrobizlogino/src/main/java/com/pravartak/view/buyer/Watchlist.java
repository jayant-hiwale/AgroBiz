package com.pravartak.view.buyer;

import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.List;

public class Watchlist {

    // =====================================================
    // PAGE
    // =====================================================

    public Scene getWatchlistPage() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        root.setTop(
                new buyerTop().createBuyerTop("Watchlist")
        );

        root.setBottom(
                new Footer().createFooter()
        );

        // =================================================
        // TITLE
        // =================================================

        Label title = new Label(
                "My Watchlist"
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        title.setStyle(
                "-fx-text-fill:#FFFFFF;"
        );

        VBox contentBox = new VBox(24);

        contentBox.setPadding(
                new Insets(
                        30,
                        50,
                        40,
                        50
                )
        );

        contentBox.getChildren().add(title);

        // =================================================
        // GET LIKED PRODUCTS
        // =================================================

        List<Product> likedProducts =
                WatchlistManager.getProducts();

        if (!likedProducts.isEmpty()) {

            FlowPane likedProductsGrid =
                    new FlowPane();

            likedProductsGrid.setHgap(24);

            likedProductsGrid.setVgap(24);

            likedProductsGrid.setAlignment(
                    Pos.TOP_LEFT
            );

            for (Product product :
                    likedProducts) {

                likedProductsGrid
                        .getChildren()
                        .add(
                                createLikedItemCard(
                                        product,
                                        likedProductsGrid,
                                        contentBox
                                )
                        );
            }

            contentBox
                    .getChildren()
                    .add(
                            likedProductsGrid
                    );

        } else {

            contentBox
                    .getChildren()
                    .add(
                            createEmptyStateView()
                    );
        }

        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scrollPane =
                new ScrollPane(contentBox);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        root.setCenter(scrollPane);

        // =================================================
        // ANIMATION
        // =================================================

        contentBox.setOpacity(0);

        contentBox.setTranslateY(20);

        FadeTransition fadeIn =
                new FadeTransition(
                        Duration.millis(500),
                        contentBox
                );

        fadeIn.setToValue(1.0);

        TranslateTransition slideUp =
                new TranslateTransition(
                        Duration.millis(500),
                        contentBox
                );

        slideUp.setToY(0);

        fadeIn.play();

        slideUp.play();

        return new Scene(
                root,
                1000,
                700
        );
    }

    // =====================================================
    // PRODUCT CARD
    // =====================================================

    private VBox createLikedItemCard(
            Product product,
            FlowPane grid,
            VBox contentBox) {

        VBox card = new VBox(10);

        card.setPrefWidth(280);

        // =================================================
        // IMAGE
        // =================================================

        ImageView imageView =
                new ImageView();

        String imagePath =
                product.getImagePath();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imagePath,
                                280,
                                150,
                                false,
                                true
                        );

                if (!image.isError()) {

                    imageView.setImage(image);
                }

            } catch (Exception e) {

                System.out.println(
                        "Unable to load product image"
                );
            }
        }

        imageView.setFitWidth(280);

        imageView.setFitHeight(150);

        imageView.setPreserveRatio(false);

        Rectangle clip =
                new Rectangle(
                        280,
                        150
                );

        clip.setArcWidth(16);

        clip.setArcHeight(16);

        imageView.setClip(clip);

        // =================================================
        // NAME
        // =================================================

        Label nameLabel =
                new Label(
                        safe(
                                product.getProductName()
                        )
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        nameLabel.setStyle(
                "-fx-text-fill:#FFFFFF;"
        );

        // =================================================
        // PRICE
        // =================================================

        Label priceLabel =
                new Label(
                        "₹"
                                + product.getPrice()
                                + " / "
                                + safe(
                                product.getUnit()
                        )
                );

        priceLabel.setStyle(
                "-fx-font-size:15px;" +
                "-fx-text-fill:#2EA043;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // CATEGORY
        // =================================================

        Label categoryLabel =
                new Label(
                        "Category: "
                                + safe(
                                product.getCategory()
                        )
                );

        categoryLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8B949E;"
        );

        // =================================================
        // QUANTITY
        // =================================================

        Label quantityLabel =
                new Label(
                        "Available: "
                                + product.getQuantity()
                                + " "
                                + safe(
                                product.getUnit()
                        )
                );

        quantityLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8B949E;"
        );

        // =================================================
        // LOCATION
        // =================================================

        Label locationLabel =
                new Label(
                        "📍 "
                                + safe(
                                product.getLocation()
                        )
                );

        locationLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8B949E;"
        );

        // =================================================
        // REMOVE BUTTON
        // =================================================

        Button removeBtn =
                new Button(
                        "❤️ Remove"
                );

        removeBtn.setStyle(
                "-fx-background-color:#21262D;" +
                "-fx-text-fill:#DA3633;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;"
        );

        removeBtn.setOnAction(e -> {

            WatchlistManager.removeProduct(
                    product
            );

            refreshWatchlist();
        });

        // =================================================
        // VIEW PRODUCT
        // =================================================

        Button buyBtn =
                new Button(
                        "View Product"
                );

        buyBtn.setStyle(
                "-fx-background-color:#238636;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;"
        );

        buyBtn.setOnAction(e -> {

            // You can open your product details
            // page here later.

            System.out.println(
                    "Viewing product: "
                            + product.getProductName()
            );
        });

        HBox actionBox =
                new HBox(
                        10,
                        removeBtn,
                        buyBtn
                );

        actionBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // DETAILS
        // =================================================

        VBox details =
                new VBox(
                        8,
                        nameLabel,
                        priceLabel,
                        categoryLabel,
                        quantityLabel,
                        locationLabel,
                        actionBox
                );

        details.setPadding(
                new Insets(12)
        );

        card.getChildren().addAll(
                imageView,
                details
        );

        // =================================================
        // CARD STYLE
        // =================================================

        String normalStyle =
                "-fx-background-color:#161B22;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:12;" +
                "-fx-border-width:1;";

        String hoverStyle =
                "-fx-background-color:#1C2128;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#2EA043;" +
                "-fx-border-radius:12;" +
                "-fx-border-width:1;";

        card.setStyle(
                normalStyle
        );

        card.setOnMouseEntered(e -> {

            card.setStyle(
                    hoverStyle
            );

            card.setTranslateY(-4);
        });

        card.setOnMouseExited(e -> {

            card.setStyle(
                    normalStyle
            );

            card.setTranslateY(0);
        });

        return card;
    }

    // =====================================================
    // REFRESH WATCHLIST
    // =====================================================

    private void refreshWatchlist() {

        if (LoginPage.mainStage != null) {

            LoginPage.mainStage.setScene(
                    getWatchlistPage()
            );
        }
    }

    // =====================================================
    // EMPTY STATE
    // =====================================================

    private VBox createEmptyStateView() {

        Label iconLabel =
                new Label("💔");

        iconLabel.setFont(
                Font.font(48)
        );

        Label message =
                new Label(
                        "Your Watchlist is Empty"
                );

        message.setStyle(
                "-fx-font-size:18px;" +
                "-fx-text-fill:#FFFFFF;" +
                "-fx-font-weight:bold;"
        );

        Label subMessage =
                new Label(
                        "Explore the marketplace and tap ❤️ to save items here."
                );

        subMessage.setStyle(
                "-fx-font-size:14px;" +
                "-fx-text-fill:#8B949E;"
        );

        Button marketBtn =
                new Button(
                        "Go To Marketplace →"
                );

        marketBtn.setStyle(
                "-fx-background-color:#238636;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:10 20;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );

        marketBtn.setOnAction(e -> {

            BuyerMarketPlace market =
                    new BuyerMarketPlace();

            LoginPage.mainStage.setScene(
                    new Scene(
                            market.getMarketplacePage()
                    )
            );
        });

        VBox emptyBox =
                new VBox(
                        14,
                        iconLabel,
                        message,
                        subMessage,
                        marketBtn
                );

        emptyBox.setAlignment(
                Pos.CENTER
        );

        emptyBox.setPadding(
                new Insets(50)
        );

        emptyBox.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:16;"
        );

        return emptyBox;
    }

    // =====================================================
    // SAFE
    // =====================================================

    private String safe(String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }
}