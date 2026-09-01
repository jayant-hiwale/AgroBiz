// package com.pravartak.view.buyer;

// import com.pravartak.view.buyer.common.buyerTop;
// import com.pravartak.view.farmer.common.Footer;
// import com.pravartak.view.login.LoginPage;

// import javafx.animation.FadeTransition;
// import javafx.animation.TranslateTransition;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Cursor;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

// public class Watchlist {

    
//     private boolean hasLikedProducts = true; 

//     public Scene getWatchlistPage() {
//         BorderPane root = new BorderPane();
        
//         // 🖤 Pure Modern Black Theme Background
//         root.setStyle("-fx-background-color: #0D1117;");
        
//         root.setTop(new buyerTop().createBuyerTop("Watchlist"));
//         root.setBottom(new Footer().createFooter());

//         // --- Title Header ---
//         Label title = new Label("My Watchlist");
//         title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
//         title.setStyle("-fx-text-fill: #FFFFFF;");

//         VBox contentBox = new VBox(24);
//         contentBox.setPadding(new Insets(30, 50, 40, 50));
//         contentBox.getChildren().add(title);

//         if (hasLikedProducts) {
//             // ❤️ १. जर युजरने प्रॉडक्ट्स Like केले असतील तर हे ग्रिड दिसेल
//             GridPane likedProductsGrid = new GridPane();
//             likedProductsGrid.setHgap(24);
//             likedProductsGrid.setVgap(24);

//             // Liked Item 1: Tomato
//             VBox item1 = createLikedItemCard(
//                 "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=500&auto=format&fit=crop",
//                 "Fresh Red Tomatoes", "₹35 / kg", "Ramesh Patil", "Pune, Maharashtra"
//             );

//             // Liked Item 2: Mango
//             VBox item2 = createLikedItemCard(
//                 "https://images.unsplash.com/photo-1553279768-865429fa0078?w=500&auto=format&fit=crop",
//                 "Alphonso Mangoes", "₹80 / kg", "Ajay Pawar", "Ratnagiri, Maharashtra"
//             );

//             likedProductsGrid.add(item1, 0, 0);
//             likedProductsGrid.add(item2, 1, 0);

//             contentBox.getChildren().add(likedProductsGrid);
//         } else {
//             // 🚫 २. जर एकही प्रॉडक्ट Like केला नसेल तर हा Empty UI दिसेल
//             VBox emptyCard = createEmptyStateView();
//             contentBox.getChildren().add(emptyCard);
//         }

//         // Dynamic ScrollPane for Black Theme
//         ScrollPane scrollPane = new ScrollPane(contentBox);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setStyle(
//             "-fx-background: #0D1117; " +
//             "-fx-background-color: #0D1117; " +
//             "-fx-border-color: transparent;"
//         );

//         root.setCenter(scrollPane);

//         // Fade Entrance Animation
//         contentBox.setOpacity(0);
//         contentBox.setTranslateY(20);
//         FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(500), contentBox);
//         fadeIn.setToValue(1.0);
//         TranslateTransition slideUp = new TranslateTransition(javafx.util.Duration.millis(500), contentBox);
//         slideUp.setToY(0);
//         fadeIn.play();
//         slideUp.play();

//         return new Scene(root, 1000, 700);
//     }

//     // --- 🖤 Helper 1: Like केलेल्या प्रॉडक्टचे आकर्षक कार्ड ---
//     private VBox createLikedItemCard(String imageUrl, String name, String price, String farmer, String location) {
//         VBox card = new VBox(10);
//         card.setPrefWidth(280);
//         card.setCursor(Cursor.HAND);

//         // Product Image
//         ImageView imageView = new ImageView();
//         try {
//             Image img = new Image(imageUrl, 280, 150, false, true);
//             imageView.setImage(img);
//         } catch (Exception e) {}
//         imageView.setFitWidth(280);
//         imageView.setFitHeight(150);

//         Rectangle clip = new Rectangle(280, 150);
//         clip.setArcWidth(16);
//         clip.setArcHeight(16);
//         imageView.setClip(clip);

//         // Labels
//         Label nameLabel = new Label(name);
//         nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         nameLabel.setStyle("-fx-text-fill: #FFFFFF;");

//         Label priceLabel = new Label(price);
//         priceLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #2EA043; -fx-font-weight: bold;");

//         Label farmerLabel = new Label("Farmer: " + farmer);
//         farmerLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #8B949E;");

//         // Action Buttons Box (Remove ❤️ & View Details)
//         Button removeBtn = new Button("❤️ Remove");
//         removeBtn.setStyle(
//             "-fx-background-color: #21262D; " +
//             "-fx-text-fill: #DA3633; " +
//             "-fx-border-color: #30363D; " +
//             "-fx-border-radius: 6; " +
//             "-fx-background-radius: 6; " +
//             "-fx-cursor: hand;"
//         );

//         Button buyBtn = new Button("View Product");
//         buyBtn.setStyle(
//             "-fx-background-color: #238636; " +
//             "-fx-text-fill: white; " +
//             "-fx-font-weight: bold; " +
//             "-fx-background-radius: 6; " +
//             "-fx-cursor: hand;"
//         );

//         HBox actionBox = new HBox(10, removeBtn, buyBtn);
//         actionBox.setAlignment(Pos.CENTER_LEFT);

//         VBox details = new VBox(8, nameLabel, priceLabel, farmerLabel, actionBox);
//         details.setPadding(new Insets(12));

//         card.getChildren().addAll(imageView, details);

//         // Black Card Styling
//         String normalStyle = 
//             "-fx-background-color: #161B22; " +
//             "-fx-background-radius: 12; " +
//             "-fx-border-color: #30363D; " +
//             "-fx-border-radius: 12; " +
//             "-fx-border-width: 1;";

//         String hoverStyle = 
//             "-fx-background-color: #1C2128; " +
//             "-fx-background-radius: 12; " +
//             "-fx-border-color: #2EA043; " +
//             "-fx-border-radius: 12; " +
//             "-fx-border-width: 1;";

//         card.setStyle(normalStyle);

//         card.setOnMouseEntered(e -> {
//             card.setStyle(hoverStyle);
//             card.setTranslateY(-4);
//         });
//         card.setOnMouseExited(e -> {
//             card.setStyle(normalStyle);
//             card.setTranslateY(0);
//         });

//         return card;
//     }

//     // --- 🖤 Helper 2: वॉचलिस्ट रिकामा असताना दाखवण्याचा Black Theme UI ---
//     private VBox createEmptyStateView() {
//         Label iconLabel = new Label("💔");
//         iconLabel.setFont(Font.font(48));

//         Label message = new Label("Your Watchlist is Empty");
//         message.setStyle("-fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

//         Label subMessage = new Label("Explore the marketplace and tap ❤️ to save items here.");
//         subMessage.setStyle("-fx-font-size: 14px; -fx-text-fill: #8B949E;");

//         Button marketBtn = new Button("Go To Marketplace →");
//         marketBtn.setStyle(
//             "-fx-background-color: #238636; " +
//             "-fx-text-fill: white; " +
//             "-fx-font-weight: bold; " +
//             "-fx-padding: 10 20; " +
//             "-fx-background-radius: 8; " +
//             "-fx-cursor: hand;"
//         );

//         marketBtn.setOnAction(e -> {
//             BuyerMarketPlace market = new BuyerMarketPlace();
//             LoginPage.mainStage.setScene(new Scene(market.getMarketplacePage()));
//         });

//         VBox emptyBox = new VBox(14, iconLabel, message, subMessage, marketBtn);
//         emptyBox.setAlignment(Pos.CENTER);
//         emptyBox.setPadding(new Insets(50));
//         emptyBox.setStyle(
//             "-fx-background-color: #161B22; " +
//             "-fx-background-radius: 16; " +
//             "-fx-border-color: #30363D; " +
//             "-fx-border-radius: 16;"
//         );

//         return emptyBox;
//     }
// }
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