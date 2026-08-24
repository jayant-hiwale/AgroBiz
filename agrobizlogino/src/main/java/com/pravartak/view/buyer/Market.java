package com.pravartak.view.buyer;

import com.pravartak.controller.Buyercontroller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Market {

    private final Buyercontroller controller;

    public Market(Buyercontroller controller) {
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
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;"
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
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
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

        Label title = new Label("Market Products");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setStyle("-fx-text-fill: #173B24;");

        VBox tomatoCard = new VBox(12);
        tomatoCard.setPadding(new Insets(20));
        tomatoCard.setPrefWidth(290);
        tomatoCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #D6E5D5;" +
                "-fx-border-radius: 12;"
        );

        Label tomato = new Label("Tomato");
        tomato.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label tomatoInfo = new Label("₹35 / kg\nFarmer: Ramesh Patil");
        tomatoInfo.setStyle("-fx-font-size: 15px; -fx-text-fill: #526154;");

        Button tomatoContact = new Button("Farmer Contact Details");
        tomatoContact.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7;"
        );
        tomatoContact.setOnAction(e ->
                controller.show(new FarmerContact(
                        controller, "Tomato", "Ramesh Patil",
                        "9876543210", "Pune, Maharashtra"
                ).createView())
        );

        tomatoCard.getChildren().addAll(tomato, tomatoInfo, tomatoContact);

        VBox mangoCard = new VBox(12);
        mangoCard.setPadding(new Insets(20));
        mangoCard.setPrefWidth(290);
        mangoCard.setStyle(tomatoCard.getStyle());

        Label mango = new Label("Mango");
        mango.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label mangoInfo = new Label("₹80 / kg\nFarmer: Ajay Pawar");
        mangoInfo.setStyle("-fx-font-size: 15px; -fx-text-fill: #526154;");

        Button mangoContact = new Button("Farmer Contact Details");
        mangoContact.setStyle(
                "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7;"
        );
        mangoContact.setOnAction(e ->
                controller.show(new FarmerContact(
                        controller, "Mango", "Ajay Pawar",
                        "9988776655", "Ratnagiri, Maharashtra"
                ).createView())
        );

        mangoCard.getChildren().addAll(mango, mangoInfo, mangoContact);

        GridPane products = new GridPane();
        products.setHgap(22);
        products.add(tomatoCard, 0, 0);
        products.add(mangoCard, 1, 0);

        VBox content = new VBox(25, title, products);
        content.setPadding(new Insets(40, 65, 40, 65));

        root.setTop(navbar);
        root.setCenter(content);

        return root;
    }
}