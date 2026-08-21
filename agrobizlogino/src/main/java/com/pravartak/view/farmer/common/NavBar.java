package com.pravartak.view.farmer.common;

import com.pravartak.view.farmer.CommuityPage;
import com.pravartak.view.farmer.MarketPlace;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NavBar {

    public HBox createNavbar(String currentPage) {

        HBox navbar = new HBox();
        navbar.setPadding(new Insets(10, 20, 10, 20));
        navbar.setAlignment(Pos.CENTER);

        navbar.setStyle(
                "-fx-background-color: #080c0d;" +
                        "-fx-border-color: #1b2021;" +
                        "-fx-border-width: 0 0 1 0;");

        // Logo
        Label logo = new Label("AgroBiz");

        logo.setStyle(
                "-fx-text-fill: #68d34a;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;");

        HBox left = new HBox(logo);
        left.setAlignment(Pos.CENTER_LEFT);
        left.setPrefWidth(450);

        // Navigation
        Button explorer = navButton("Explorer");
        Button marketplace = navButton("Marketplace");
        Button community = navButton("Community");
        Button learning = navButton("Learning");
        Button schemes = navButton("Schemes");

        // Current page
        if (currentPage.equals("Explorer")) {
            explorer.setStyle(navButtonActive());
        }

        if (currentPage.equals("Marketplace")) {
            marketplace.setStyle(navButtonActive());
        }
        marketplace.setOnAction(e -> {

            MarketPlace marketPlaceScene = new MarketPlace();
            LoginPage.mainStage.setScene(marketPlaceScene.getMarketPlaceScene());
        });

        if (currentPage.equals("Community")) {
            community.setStyle(navButtonActive());

            // CommuityPage commuityPageScene = new CommuityPage();
            // LoginPage.mainStage.setScene(commuityPageScene.getCommunityScene());
        }
        community.setOnAction(e -> {

            CommuityPage commuityPageScene = new CommuityPage();
            LoginPage.mainStage.setScene(commuityPageScene.getCommunityScene());
        });

        if (currentPage.equals("Learning")) {
            learning.setStyle(navButtonActive());
        }

        if (currentPage.equals("Schemes")) {
            schemes.setStyle(navButtonActive());
        }

        HBox center = new HBox(
                25,
                explorer,
                marketplace,
                community,
                learning,
                schemes);

        center.setAlignment(Pos.CENTER);

        // Right
        Button sell = new Button("◇ List for Sale");

        sell.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #68d34a;" +
                        "-fx-border-color: #68d34a;" +
                        "-fx-border-radius: 5;" +
                        "-fx-cursor: hand;");

        // sell.setOnAction(e -> openAddProductPage());

        Label notification = new Label("♧");
        Label profile = new Label("◎");
        Label login = new Label("Login");

        notification.setStyle(
                "-fx-text-fill: #bbbbbb; -fx-font-size: 18px;");

        profile.setStyle(
                "-fx-text-fill: #bbbbbb; -fx-font-size: 18px;");

        login.setStyle(
                "-fx-text-fill: #bbbbbb;");

        HBox right = new HBox(
                15,
                sell,
                notification,
                profile,
                login);

        right.setAlignment(Pos.CENTER_RIGHT);
        right.setPrefWidth(450);

        navbar.getChildren().addAll(
                left,
                center,
                right);

        return navbar;
    }

    private String navButtonActive() {

        return "-fx-background-color: transparent;" +
                "-fx-text-fill: #68d34a;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 0 5 0;" +
                "-fx-border-color: #68d34a;" +
                "-fx-border-width: 0 0 2 0;";
    }

    public Button navButton(String text) {

        Button button = new Button(text);

        String normal = "-fx-background-color: transparent;" +
                "-fx-text-fill: #aaaaaa;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 0 5 0;";

        String hover = "-fx-background-color: transparent;" +
                "-fx-text-fill: #68d34a;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 0 5 0;" +
                "-fx-border-color: #68d34a;" +
                "-fx-border-width: 0 0 2 0;";

        button.setStyle(normal);

        button.setOnMouseEntered(e -> {

            button.setStyle(hover);

        });
        button.setOnMouseExited(e -> button.setStyle(normal));

        return button;
    }
}
