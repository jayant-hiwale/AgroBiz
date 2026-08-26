package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.Contactcontroller;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Market {

    private final Contactcontroller controller;

    public Market(Contactcontroller controller) {
        this.controller = controller;
    }

    public Scene getMarketPage() {

        BorderPane root = new BorderPane();
        
        // 🌑 Modern Agri Dark Background
        root.setStyle("-fx-background-color: #0D1117;");

        root.setTop(new buyerTop().createBuyerTop("Market"));
        root.setBottom(new Footer().createFooter());

        // --- Header Title Section ---
        Label title = new Label("Marketplace Products");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setStyle("-fx-text-fill: #FFFFFF;");

        Label subtitle = new Label("Explore fresh produce directly listed by verified local farmers.");
        subtitle.setStyle("-fx-font-size: 15px; -fx-text-fill: #8B949E;");

        VBox headerBox = new VBox(8, title, subtitle);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        // --- Product Cards Grid ---
        GridPane productsGrid = new GridPane();
        productsGrid.setHgap(24);
        productsGrid.setVgap(24);
        productsGrid.setAlignment(Pos.TOP_LEFT);

        // 🍅 Tomato Card with Real Image
        VBox tomatoCard = createProductCardWithImage(
                "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=500&auto=format&fit=crop",
                "Fresh Red Tomatoes", "₹35 / kg", "Ramesh Patil", "Pune, Maharashtra", "9876543210"
        );

        // 🥭 Mango Card with Real Image
        VBox mangoCard = createProductCardWithImage(
                "https://images.unsplash.com/photo-1553279768-865429fa0078?w=500&auto=format&fit=crop",
                "Alphonso Mangoes", "₹80 / kg", "Ajay Pawar", "Ratnagiri, Maharashtra", "9988776655"
        );

        productsGrid.add(tomatoCard, 0, 0);
        productsGrid.add(mangoCard, 1, 0);

        // --- Main Content Assembly ---
        VBox content = new VBox(30, headerBox, productsGrid);
        content.setPadding(new Insets(40, 60, 40, 60));

        // ScrollPane for continuous scrolling
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
            "-fx-background: #0D1117; " +
            "-fx-background-color: #0D1117; " +
            "-fx-border-color: transparent;"
        );

        root.setCenter(scrollPane);

        // Entrance Animation
        content.setOpacity(0);
        content.setTranslateY(20);
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(600), content);
        fadeIn.setToValue(1.0);
        
        TranslateTransition slideUp = new TranslateTransition(Duration.millis(600), content);
        slideUp.setToY(0);
        
        fadeIn.play();
        slideUp.play();

        return new Scene(root, 1000, 700);
    }

    // --- Helper Method: Creates Image Card with Rounded Corners & Dark Theme ---
    private VBox createProductCardWithImage(String imageUrl, String productName, String price, String farmerName, String location, String contact) {
        VBox card = new VBox(12);
        card.setPrefWidth(290);
        card.setCursor(Cursor.HAND);

        // 🖼️ 1. Product Image View Container
        ImageView imageView = new ImageView();
        try {
            Image img = new Image(imageUrl, 290, 160, false, true);
            imageView.setImage(img);
        } catch (Exception e) {
            // Placeholder background on image load error
        }
        imageView.setFitWidth(290);
        imageView.setFitHeight(160);

        // Rounded corners for Image Top-left and Top-right
        Rectangle clip = new Rectangle(290, 160);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        // 📝 2. Card Content Box
        Label nameLabel = new Label(productName);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nameLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #3FB950; -fx-font-weight: bold;");

        Label farmerLabel = new Label("Farmer: " + farmerName);
        farmerLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #C9D1D9;");

        Label locationLabel = new Label("📍 " + location);
        locationLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #8B949E;");

        // Contact Button
        Button contactBtn = new Button("Contact Farmer →");
        String btnDefaultStyle = 
                "-fx-background-color: #238636;" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 9 14;" +
                "-fx-cursor: hand;";
                
        contactBtn.setStyle(btnDefaultStyle);
        contactBtn.setMaxWidth(Double.MAX_VALUE);

        contactBtn.setOnAction(e -> {
            Stage stage = (Stage) contactBtn.getScene().getWindow();
            Contactcontroller activeController = (controller != null) ? controller : new Contactcontroller(stage);
            if (activeController.getStage() == null) {
                activeController.setStage(stage);
            }
            activeController.show(new FarmerContact(
                    activeController, productName, farmerName,
                    contact, location
            ).createView());
        });

        VBox cardDetails = new VBox(8, nameLabel, priceLabel, farmerLabel, locationLabel, contactBtn);
        cardDetails.setPadding(new Insets(14, 16, 16, 16));

        card.getChildren().addAll(imageView, cardDetails);

        // Card Dark Theme Style
        String normalCardStyle = 
                "-fx-background-color: #161B22;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #30363D;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 4);";

        String hoverCardStyle = 
                "-fx-background-color: #1C2128;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #2EA043;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(46,160,67,0.3), 16, 0, 0, 6);";

        card.setStyle(normalCardStyle);

        // Hover Effect Animation
        card.setOnMouseEntered(e -> {
            card.setStyle(hoverCardStyle);
            TranslateTransition tt = new TranslateTransition(Duration.millis(180), card);
            tt.setToY(-6);
            tt.play();
        });

        card.setOnMouseExited(e -> {
            card.setStyle(normalCardStyle);
            TranslateTransition tt = new TranslateTransition(Duration.millis(180), card);
            tt.setToY(0);
            tt.play();
        });

        return card;
    }
}