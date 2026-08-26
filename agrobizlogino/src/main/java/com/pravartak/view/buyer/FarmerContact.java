package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.Contactcontroller;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FarmerContact {

    private final Contactcontroller controller;
    private final String product;
    private final String farmer;
    private final String mobile;
    private final String location;

    public FarmerContact(Contactcontroller controller, String product,
                         String farmer, String mobile, String location) {
        this.controller = controller;
        this.product = product;
        this.farmer = farmer;
        this.mobile = mobile;
        this.location = location;
    }

    public Parent createView() {

        BorderPane root = new BorderPane();
        
        // 🌑 Pure Modern Dark Theme Background
        root.setStyle("-fx-background-color: #0D1117;");
        
        root.setTop(new buyerTop().createBuyerTop("Market"));
        root.setBottom(new Footer().createFooter());

        // --- Header Title ---
        Label title = new Label("Farmer Contact Details");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #FFFFFF;");

        // --- Detail Items Creation ---
        VBox productBox = createDetailRow("📦", "Product", product);
        VBox farmerBox = createDetailRow("👤", "Farmer Name", farmer);
        VBox mobileBox = createDetailRow("📞", "Mobile Number", mobile);
        VBox locationBox = createDetailRow("📍", "Location", location);

        // --- Back Button ---
        Button backButton = new Button("← Back to Market");
        String btnStyle = 
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;";
        
        backButton.setStyle(btnStyle);
        backButton.setMaxWidth(Double.MAX_VALUE);

        // Hover Effect on Button
        backButton.setOnMouseEntered(e -> backButton.setStyle(btnStyle + "-fx-background-color: #2ea043;"));
        backButton.setOnMouseExited(e -> backButton.setStyle(btnStyle));

        backButton.setOnAction(e -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Contactcontroller activeController = (controller != null) ? controller : new Contactcontroller(stage);
            if (activeController.getStage() == null) {
                activeController.setStage(stage);
            }
            activeController.show(new Market(activeController).getMarketPage());
        });

        // --- Contact Card Container ---
        VBox card = new VBox(18,
                title, productBox, farmerBox,
                mobileBox, locationBox, backButton
        );
        card.setPadding(new Insets(32));
        card.setMaxWidth(460);
        card.setStyle(
                "-fx-background-color: #161B22;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #30363D;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 16;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 6);"
        );

        VBox center = new VBox(card);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(40));

        root.setCenter(center);

        // --- Entrance Fade & Slide Animation ---
        card.setOpacity(0);
        card.setTranslateY(20);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), card);
        fadeIn.setToValue(1.0);

        TranslateTransition slideUp = new TranslateTransition(Duration.millis(500), card);
        slideUp.setToY(0);

        fadeIn.play();
        slideUp.play();

        return root;
    }

    // --- Helper Method to Create Clean Detail Rows ---
    private VBox createDetailRow(String icon, String labelText, String valueText) {
        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(18));

        Label tagLbl = new Label(labelText);
        tagLbl.setStyle("-fx-font-size: 12px; -fx-text-fill: #8B949E; -fx-font-weight: bold;");

        Label valLbl = new Label(valueText);
        valLbl.setStyle("-fx-font-size: 15px; -fx-text-fill: #E6EDF3; -fx-font-weight: bold;");

        VBox textContainer = new VBox(2, tagLbl, valLbl);
        
        HBox row = new HBox(12, iconLbl, textContainer);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(8, 12, 8, 12));
        row.setStyle(
                "-fx-background-color: #21262D;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #30363D;" +
                "-fx-border-radius: 8;"
        );

        return new VBox(row);
    }
}