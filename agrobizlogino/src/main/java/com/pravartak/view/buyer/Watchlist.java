package com.pravartak.view.buyer;

import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Watchlist {

    //private final Buyercontroller controller;

    // public Watchlist(Buyercontroller controller) {
    //     this.controller = controller;
    // }


    public Scene getWatchlistPage() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F4F8F3;");
        root.setTop(new buyerTop().createBuyerTop("Watchlist"));
        root.setBottom(new Footer().createFooter());

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
        
        page.getChildren().addAll(title, message, marketButton);
        root.setCenter(page);
        Scene scene = new Scene(root, 800, 600);
        return scene;
    }
}