package com.pravartak.view.buyer;

import com.pravartak.controller.Buyercontroller;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Watchlist {

    private final Buyercontroller controller;

    public Watchlist(Buyercontroller controller) {
        this.controller = controller;
    }

    public Parent createView() {

        VBox page = new VBox(18);
        page.setPadding(new Insets(55));
        page.setStyle("-fx-background-color: #F4F8F3;");

        Label title = new Label("My Watchlist");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setStyle("-fx-text-fill: #173B24;");

        Label message = new Label("No products saved in your watchlist.");
        message.setStyle("-fx-font-size: 16px; -fx-text-fill: #526154;");

        Button marketButton = new Button("Go To Market");
        marketButton.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 11 18;"
        );
        marketButton.setOnAction(e ->
                controller.show(new Market(controller).createView())
        );

        page.getChildren().addAll(title, message, marketButton);

        return page;
    }
}