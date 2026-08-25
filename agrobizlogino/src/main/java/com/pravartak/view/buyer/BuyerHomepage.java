package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.Contactcontroller;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import com.pravartak.controller.Buyercontroller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class BuyerHomepage {

    private final Contactcontroller controller;

    public BuyerHomepage(Contactcontroller controller) {
        this.controller = controller;
    }

    public Scene getBuyerHomePage() {

        BorderPane root = new BorderPane();
        
        // 🌾 1. High-Resolution Farm Background Image
        // टीप: तुमच्या प्रोजेक्टमध्ये assets/farm_bg.jpg किंवा शेताचा फोटो जोडा.
        // जर ऑनलाईन फोटो वापरायचा नसेल, तर स्थानिक इमेजचा Path द्या.
        try {
            Image bgImage = new Image("https://images.unsplash.com/photo-1500382017468-9049fed747ef?q=80&w=1600&auto=format&fit=crop");
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            root.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            // Fallback Gradient Background जर इमेज लोड झाली नाही तर
            root.setStyle("-fx-background: linear-gradient(to bottom, #A2D2A4, #3B7A57);");
        }

        root.setTop(new buyerTop().createBuyerTop("Home"));
        root.setBottom(new Footer().createFooter());

        // --- Title Section ---
        Label title = new Label("Fresh From Farmers,\nDirect To You.");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        title.setStyle(
            "-fx-text-fill: #0F381E; " +
            "-fx-text-alignment: center; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0, 0, 2);"
        );
        title.setAlignment(Pos.CENTER);

        Label subtitle = new Label(
                "Discover fresh vegetables, fruits and grains directly from local fields."
        );
        subtitle.setStyle(
            "-fx-font-size: 17px; " +
            "-fx-text-fill: #1B4D3E; " +
            "-fx-font-weight: bold;"
        );

        // --- Search Bar ---
        TextField search = new TextField();
        search.setPromptText("🌾 Search fresh crops, fruits, vegetables...");
        search.setMaxWidth(500);
        search.setPrefHeight(48);
        
        String defaultSearchStyle = 
                "-fx-background-color: rgba(255, 255, 255, 0.85);" +
                "-fx-background-radius: 25;" +
                "-fx-border-color: rgba(255, 255, 255, 0.9);" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 25;" +
                "-fx-padding: 0 22;" +
                "-fx-font-size: 15px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 4);";
                
        search.setStyle(defaultSearchStyle);
        search.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                search.setStyle(defaultSearchStyle + "-fx-border-color: #1B5E20; -fx-background-color: #FFFFFF;");
            } else {
                search.setStyle(defaultSearchStyle);
            }
        });

        // --- View Market Button ---
        Button viewMarketButton = new Button("Explore Fresh Market  🌾");
        String buttonBaseStyle = 
                "-fx-background-color: linear-gradient(to right, #1B5E20, #2E7D32);" +
                "-fx-text-fill: #FFFFFF;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 25;" +
                "-fx-padding: 13 32;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 12, 0, 0, 5);";
                
        viewMarketButton.setStyle(buttonBaseStyle);
        
        // Hover Animation for Button
        viewMarketButton.setOnMouseEntered(e -> {
            viewMarketButton.setStyle(buttonBaseStyle + "-fx-background-color: linear-gradient(to right, #2E7D32, #388E3C);");
            ScaleTransition st = new ScaleTransition(Duration.millis(150), viewMarketButton);
            st.setToX(1.04);
            st.setToY(1.04);
            st.play();
        });
        
        viewMarketButton.setOnMouseExited(e -> {
            viewMarketButton.setStyle(buttonBaseStyle);
            ScaleTransition st = new ScaleTransition(Duration.millis(150), viewMarketButton);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });

        viewMarketButton.setOnAction(e -> {
            Market market = new Market(controller);
            LoginPage.mainStage.setScene(market.getMarketPage());
        });

        // --- Category Header ---
        Label category = new Label("Popular Farm Categories");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        category.setStyle("-fx-text-fill: #0F381E; -fx-padding: 10 0 5 0;");

        // Glassmorphic Category Cards
        Label vegetables = createCategoryCard("🥬\nVegetables");
        Label fruits = createCategoryCard("🍎\nFruits");
        Label grains = createCategoryCard("🌾\nGrains");
        Label pulses = createCategoryCard("🌱\nPulses");

        HBox categories = new HBox(20, vegetables, fruits, grains, pulses);
        categories.setAlignment(Pos.CENTER);

        // --- 🧊 2. Frosted Glass Container (Center Card) ---
        VBox content = new VBox(
                20, title, subtitle, search,
                viewMarketButton, category, categories
        );
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(35, 50, 35, 50));
        
        // Semi-transparent frosted glass design
        content.setStyle(
            "-fx-background-color: rgba(240, 248, 240, 0.55);" +
            "-fx-background-radius: 28;" +
            "-fx-border-color: rgba(255, 255, 255, 0.8);" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 28;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.2), 25, 0, 0, 10);"
        );
        
        BorderPane centerWrapper = new BorderPane(content);
        centerWrapper.setPadding(new Insets(25, 60, 25, 60));

        root.setCenter(centerWrapper);

        // --- Entrance Animation ---
        content.setOpacity(0);
        content.setTranslateY(30);
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), content);
        fadeIn.setToValue(1.0);
        
        TranslateTransition slideUp = new TranslateTransition(Duration.millis(800), content);
        slideUp.setToY(0);
        
        fadeIn.play();
        slideUp.play();

        return new Scene(root, 1000, 700);
    }

    // Helper Method for Glassmorphic Category Cards
    private Label createCategoryCard(String text) {
        Label card = new Label(text);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(120);
        card.setPrefHeight(100);
        card.setCursor(Cursor.HAND);

        String normalStyle = 
                "-fx-background-color: rgba(255, 255, 255, 0.75);" +
                "-fx-padding: 12;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: rgba(255, 255, 255, 0.9);" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 18;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1B5E20;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);";

        String hoverStyle = 
                "-fx-background-color: rgba(255, 255, 255, 0.95);" +
                "-fx-padding: 12;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #2E7D32;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 18;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #2E7D32;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(46,125,50,0.35), 18, 0, 0, 8);";

        card.setStyle(normalStyle);

        // Hover Effect
        card.setOnMouseEntered(e -> {
            card.setStyle(hoverStyle);
            TranslateTransition tt = new TranslateTransition(Duration.millis(180), card);
            tt.setToY(-8);
            tt.play();
        });

        card.setOnMouseExited(e -> {
            card.setStyle(normalStyle);
            TranslateTransition tt = new TranslateTransition(Duration.millis(180), card);
            tt.setToY(0);
            tt.play();
        });

        return card;
    }
}