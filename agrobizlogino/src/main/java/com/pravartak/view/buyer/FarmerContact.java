package com.pravartak.view.buyer;

import com.pravartak.controller.Buyercontroller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FarmerContact {

    private final Buyercontroller controller;
    private final String product;
    private final String farmer;
    private final String mobile;
    private final String location;

    public FarmerContact(Buyercontroller controller, String product,
                         String farmer, String mobile, String location) {
        this.controller = controller;
        this.product = product;
        this.farmer = farmer;
        this.mobile = mobile;
        this.location = location;
    }

    public Parent createView() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F4F8F3;");

        Label title = new Label("Farmer Contact Details");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setStyle("-fx-text-fill: #173B24;");

        Label productLabel = new Label("Product: " + product);
        Label farmerLabel = new Label("Farmer Name: " + farmer);
        Label mobileLabel = new Label("Mobile Number: " + mobile);
        Label locationLabel = new Label("Location: " + location);

        productLabel.setStyle("-fx-font-size: 17px;");
        farmerLabel.setStyle("-fx-font-size: 17px;");
        mobileLabel.setStyle("-fx-font-size: 17px;");
        locationLabel.setStyle("-fx-font-size: 17px;");

        Button backButton = new Button("← Back to Market");
        backButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 11 18;"
        );
        backButton.setOnAction(e ->
                controller.show(new Market(controller).createView())
        );

        VBox card = new VBox(17,
                title, productLabel, farmerLabel,
                mobileLabel, locationLabel, backButton
        );
        card.setPadding(new Insets(35));
        card.setMaxWidth(480);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #D6E5D5;" +
                "-fx-border-radius: 15;"
        );

        VBox center = new VBox(card);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(50));

        root.setCenter(center);

        return root;
    }
}