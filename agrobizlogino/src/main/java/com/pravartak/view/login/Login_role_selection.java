package com.pravartak.view.login;

import com.pravartak.view.admin.AdminPage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
// import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Login_role_selection {
    
    private  Scene Login_role_selectionScene;

       public Scene getLogin_role_selectionScene(){

          // =========================================================
        // HEADER
        // =========================================================

        Label logoIcon = new Label("🚜");

        logoIcon.setStyle("-fx-font-size: 25px;");

        Label logo = new Label("Agro Biz");

        logo.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #7ED957;");

        HBox header = new HBox(
                10,
                logoIcon,
                logo);

        header.setStyle(
                "-fx-alignment: center-left;");

        // =========================================================
        // TITLE
        // =========================================================

        Label title = new Label(
                "Choose Your Path");

        title.setStyle(
                "-fx-font-size: 48px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #F1F3F1;");

        // =========================================================
        // SUBTITLE
        // =========================================================

        Text subtitle = new Text(
                "Join our agricultural ecosystem. Select how you want to interact with\n" +
                        "                the platform to get tailored tools and insights.");

        subtitle.setStyle(
                "-fx-font-size: 19px;" +
                        "-fx-fill: #A8AEAA;");

        VBox heading = new VBox(18, title, subtitle);

        heading.setStyle("-fx-alignment: center;");

        // =========================================================
        // FARMER ICON
        // =========================================================

        Circle farmerCircle = new Circle(45);

        farmerCircle.setFill(Color.web("#18351D"));

        Label farmerIcon = new Label("   🚜");

        farmerIcon.setStyle("-fx-font-size: 30px; -fx-text-fill: #7ED957");

        StackPane farmerIconBox = new StackPane(
                farmerCircle,
                farmerIcon);

        farmerIconBox.setStyle("-fx-alignment: center-left;");

        // =========================================================
        // FARMER TITLE
        // =========================================================

        Label farmerTitle = new Label("Farmer/Learner");

        farmerTitle.setStyle(
                "-fx-font-size: 34px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #F1F3F1;");

        // =========================================================
        // FARMER TAGLINE
        // =========================================================

        Label farmerTagline = new Label("Learn ,Grow & Sell");

        farmerTagline.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-text-fill: #A8AEAA;");

        // =========================================================
        // FARMER DESCRIPTION
        // =========================================================

        Text farmerDescription = new Text(
                "Access AI advisory, list your crops on the\n" +
                        "marketplace, manage your inventory, and\n" +
                        "connect directly with verified buyers.");

        farmerDescription.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-fill: #737C77;");

        Label farmerLabel = new Label("Continue as Farmer  →");

        farmerLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #7ED957;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 0;");

        // =========================================================
        // FARMER CARD
        // =========================================================

        VBox farmerCard = new VBox(
                15,
                farmerIconBox,
                farmerTitle,
                farmerTagline,
                farmerDescription,
                farmerLabel);

        farmerCard.setPrefWidth(465);
        farmerCard.setPrefHeight(385);

        farmerCard.setStyle(
                "-fx-background-color: #101718;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #293334;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 14;" +
                        "-fx-padding: 35px;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.45), 12, 0, 0, 5);");

        farmerCard.setOnMouseClicked(e -> {

            System.out.println(
                    "Farmer selected");

            // Later:
            // homeStage.setScene(farmerScene);

        });

        farmerCard.setOnMouseEntered(e -> {

            farmerCard.setStyle(
                    "-fx-background-color: #101718;" +
                            "-fx-background-radius: 14;" +
                            "-fx-border-color: #7ED957;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 14;" +
                            "-fx-padding: 35px;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(126,217,87,0.20), 18, 0, 0, 6);");
        });

        farmerCard.setOnMouseExited(e -> {

            farmerCard.setStyle(
                    "-fx-background-color: #101718;" +
                            "-fx-background-radius: 14;" +
                            "-fx-border-color: #293334;" +
                            "-fx-border-width: 1px;" +
                            "-fx-border-radius: 14;" +
                            "-fx-padding: 35px;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.45), 12, 0, 0, 5);");
        });

        // =========================================================
        // BUYER ICON
        // =========================================================

        Circle buyerCircle = new Circle(45);

        buyerCircle.setFill(Color.web("#18351D"));

        Label buyerIcon = new Label("   🛒");

        buyerIcon.setStyle("-fx-font-size: 30px; -fx-text-fill: #7ED957");

        StackPane buyerIconBox = new StackPane(
                buyerCircle,
                buyerIcon);

        buyerIconBox.setStyle("-fx-alignment: center-left;");

        // =========================================================
        // BUYER TITLE
        // =========================================================

        Label buyerTitle = new Label("Buyer");

        buyerTitle.setStyle(
                "-fx-font-size: 34px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #F1F3F1;");

        // =========================================================
        // BUYER TAGLINE
        // =========================================================

        Label buyerTagline = new Label(
                "Buy Fresh");

        buyerTagline.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-text-fill: #A8AEAA;");

        // =========================================================
        // BUYER DESCRIPTION
        // =========================================================

        Text buyerDescription = new Text(
                "Discover fresh, local produce, track market\n" +
                        "trends, negotiate directly with farmers, and\n" +
                        "secure reliable supply chains.");

        buyerDescription.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-fill: #737C77;");

        Label buyerLabel = new Label("Continue as Buyer  →");

        buyerLabel.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #7ED957;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 0;");

        // =========================================================
        // BUYER CARD
        // =========================================================

        VBox buyerCard = new VBox(
                15,
                buyerIconBox,
                buyerTitle,
                buyerTagline,
                buyerDescription,
                buyerLabel);

        buyerCard.setPrefWidth(465);
        buyerCard.setPrefHeight(385);

        buyerCard.setStyle(
                "-fx-background-color: #101718;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #293334;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 14;" +
                        "-fx-padding: 35px;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.45), 12, 0, 0, 5);");

        buyerCard.setOnMouseClicked(e -> {
            System.out.println(
                    "Buyer selected");
            // Later:
            // homeStage.setScene(buyerScene);
        });

        buyerCard.setOnMouseEntered(e -> {
            buyerCard.setStyle(
                    "-fx-background-color: #101718;" +
                            "-fx-background-radius: 14;" +
                            "-fx-border-color: #7ED957;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 14;" +
                            "-fx-padding: 35px;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(126,217,87,0.20), 18, 0, 0, 6);");
        });

        buyerCard.setOnMouseExited(e -> {
            buyerCard.setStyle(
                    "-fx-background-color: #101718;" +
                            "-fx-background-radius: 14;" +
                            "-fx-border-color: #293334;" +
                            "-fx-border-width: 1px;" +
                            "-fx-border-radius: 14;" +
                            "-fx-padding: 35px;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.45), 12, 0, 0, 5);");
        });

        // =========================================================
        // CARDS
        // =========================================================

        HBox cards = new HBox(
                35,
                farmerCard,
                buyerCard);

        cards.setStyle("-fx-alignment: center;");

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        VBox content = new VBox(
                45,
                heading,
                cards);

        content.setStyle("-fx-alignment: center-top;");

        // =========================================================
        // ROOT
        // =========================================================

        VBox root = new VBox(
                40,
                header,
                content);

        root.setStyle(
                "-fx-background-color: linear-gradient(" +
                        "to bottom right," +
                        "#080D0E 0%," +
                        "#0C1513 50%," +
                        "#101B14 100%);" +
                        "-fx-padding: 28px;");

                

        Login_role_selectionScene = new Scene(root);

        return Login_role_selectionScene;
       }
}
