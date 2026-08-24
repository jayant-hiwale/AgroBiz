package com.pravartak.view.buyer;

import com.pravartak.controller.Buyercontroller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BuyerHomepage {

    private final Buyercontroller controller;

    public BuyerHomepage(Buyercontroller controller) {
        this.controller = controller;
    }

    public Parent createView() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F4F8F3;");

        Label logo = new Label("AgroBiz");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        logo.setStyle("-fx-text-fill: #9BE29B;");

        Button marketButton = new Button("Market");
        marketButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );
        marketButton.setOnAction(e ->
                controller.show(new Market(controller).createView())
        );

        Button watchlistButton = new Button("Watchlist");
        watchlistButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );
        watchlistButton.setOnAction(e ->
                controller.show(new Watchlist(controller).createView())
        );

        Button HomepageButton = new Button("Homepage");
        HomepageButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;"
        );
        HomepageButton.setOnAction(e ->
                controller.show(new BuyerHomepage(controller).createView())
        );

        HBox menu = new HBox(18, marketButton, watchlistButton, HomepageButton);
        menu.setAlignment(Pos.CENTER);

        Button profileButton = new Button("◉  Profile");
        profileButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-padding: 8 15;"
        );
        profileButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Profile");
            alert.setHeaderText("Buyer Profile");
            alert.setContentText("Name: Buyer\nMobile: 9876543210");
            alert.showAndWait();
        });

        BorderPane navbar = new BorderPane();
        navbar.setPadding(new Insets(18, 40, 18, 40));
        navbar.setStyle("-fx-background-color: #173B24;");
        navbar.setLeft(logo);
        navbar.setCenter(menu);
        navbar.setRight(profileButton);

        Label title = new Label("Fresh From Farmers,\nDirect To You.");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        title.setStyle("-fx-text-fill: #173B24;");
        title.setAlignment(Pos.CENTER);

        Label subtitle = new Label(
                "Discover fresh vegetables, fruits and grains from local farmers."
        );
        subtitle.setStyle("-fx-font-size: 17px; -fx-text-fill: #526154;");

        TextField search = new TextField();
        search.setPromptText("Search products...");
        search.setMaxWidth(470);
        search.setPrefHeight(44);
        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 22;" +
                "-fx-border-color: #B9CFB8;" +
                "-fx-border-radius: 22;" +
                "-fx-padding: 0 18;"
        );

        Button viewMarketButton = new Button("View Market Products  →");
        viewMarketButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 13 25;"
        );
        viewMarketButton.setOnAction(e ->
                controller.show(new Market(controller).createView())
        );

        Label category = new Label("Popular Categories");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        category.setStyle("-fx-text-fill: #173B24;");

        Label vegetables = new Label("🥬\nVegetables");
        vegetables.setAlignment(Pos.CENTER);
        vegetables.setStyle(
                "-fx-background-color: white;" +
                "-fx-padding: 20;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #D6E5D5;" +
                "-fx-border-radius: 12;" +
                "-fx-font-size: 16px;"
        );

        Label fruits = new Label("🍎\nFruits");
        fruits.setAlignment(Pos.CENTER);
        fruits.setStyle(vegetables.getStyle());

        Label grains = new Label("🌾\nGrains");
        grains.setAlignment(Pos.CENTER);
        grains.setStyle(vegetables.getStyle());

        HBox categories = new HBox(20, vegetables, fruits, grains);
        categories.setAlignment(Pos.CENTER);

        VBox content = new VBox(
                20, title, subtitle, search,
                viewMarketButton, category, categories
        );
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(55));

        root.setTop(navbar);
        root.setCenter(content);

        return root;
    }
}