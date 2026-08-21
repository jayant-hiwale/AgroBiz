package com.pravartak.view.farmer;

import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MarketPlace {
        private Scene marketPlaceScene;

        public Scene getMarketPlaceScene() {
                BorderPane root = new BorderPane();

                // TOP
                root.setTop(createNavbar());

                // CENTER
                root.setCenter(createMarketplaceContent());

                // BOTTOM
                root.setBottom(createFooter());
                marketPlaceScene = new Scene(root);
                return marketPlaceScene;
        }

        // ################## NAVBAR -- TOP ##############################
        private HBox createNavbar() {

                HBox navbar = new HBox();

                navbar.setPadding(new Insets(10, 20, 10, 20));
                navbar.setAlignment(Pos.CENTER);

                navbar.setStyle(
                                "-fx-background-color: #080c0d;" +
                                                "-fx-border-color: #1b2021;" +
                                                "-fx-border-width: 0 0 1 0;");

                // ------------------ LEFT : LOGO --------------------------------

                Label logo = new Label("AgroBiz");

                logo.setStyle(
                                "-fx-text-fill: #68d34a;" +
                                                "-fx-font-size: 24px;" +
                                                "-fx-font-weight: bold;");

                HBox left = new HBox();
                left.setAlignment(Pos.CENTER_LEFT);

                left.getChildren().add(logo);

                // Give LEFT same width as RIGHT
                left.setPrefWidth(450);
                left.setMinWidth(450);
                left.setMaxWidth(450);

                // ---------------- CENTER : NAVIGATION --------------------------

                Button explorerButton = navButton("Explorer");
                Button marketplaceButton = navButton("Marketplace");
                Button communityButton = navButton("Community");
                Button learningButton = navButton("Learning");
                Button schemesButton = navButton("Schemes");

                // ---------------- COMMUNITY ACTION -----------------------------

                communityButton.setOnAction(e -> {

                        System.out.println("community button clicked ");

                        CommuityPage communityPage = new CommuityPage();

                        Runnable callBackActionMarket = () -> {
                                backToMarket();
                        };

                        LoginPage.mainStage.setScene(
                                        communityPage.getCommunityScene(callBackActionMarket));
                });

                // ---------------- CENTER HBOX ----------------------------------

                HBox center = new HBox(25);

                center.setAlignment(Pos.CENTER);

                center.getChildren().addAll(
                                explorerButton,
                                marketplaceButton,
                                communityButton,
                                learningButton,
                                schemesButton);

                // ---------------- RIGHT : ACTIONS -------------------------------

                Button sellButton = new Button("◇ List for Sale");

                sellButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #68d34a;" +
                                                "-fx-border-color: #68d34a;" +
                                                "-fx-border-radius: 5;" +
                                                "-fx-cursor: hand;");

                Label notification = new Label("♧");
                Label profile = new Label("◎");
                Label login = new Label("Login");

                notification.setStyle(
                                "-fx-text-fill: #bbbbbb;" +
                                                "-fx-font-size: 18px;");

                profile.setStyle(
                                "-fx-text-fill: #bbbbbb;" +
                                                "-fx-font-size: 18px;");

                login.setStyle(
                                "-fx-text-fill: #bbbbbb;");

                HBox right = new HBox(15);

                right.setAlignment(Pos.CENTER_RIGHT);

                right.getChildren().addAll(
                                sellButton,
                                notification,
                                profile,
                                login);

                // Give RIGHT same width as LEFT
                right.setPrefWidth(450);
                right.setMinWidth(450);
                right.setMaxWidth(450);

                // ---------------- ADD ALL TO NAVBAR ----------------------------

                navbar.getChildren().addAll(
                                left,
                                center,
                                right);

                return navbar;
        }

        // button global style
        private static Button navButton(String text) {
                Button button = new Button(text);
                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #aaaaaa;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 5 0 5 0;");

                button.setOnMouseEntered(e -> {
                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #68d34a;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 5 0 5 0;" +
                                                        "-fx-border-color: #68d34a;" +
                                                        "-fx-border-width: 0 0 2 0;");
                });

                button.setOnMouseExited(e -> {
                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #aaaaaa;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-cursor: hand;" +
                                                        "-fx-padding: 5 0 5 0;" +
                                                        "-fx-border-color: transparent;" +
                                                        "-fx-border-width: 0 0 2 0;");
                });
                return button;
        }

        // ##################### Main Marketplace area - Center #####################
        private static VBox createMarketplaceContent() {

                VBox content = new VBox(10);

                content.setPadding(new Insets(25, 20, 30, 20));

                content.setStyle("-fx-background-color: #080c0d;");

                Label title = new Label("Marketplace");

                title.setStyle("-fx-text-fill: #eeeeee; -fx-font-size: 40px; -fx-font-weight: bold;");

                Label description = new Label(
                                "Browse high-quality livestock, premium feed, and advanced farming equipment from\n" +
                                                "verified sellers.");

                description.setStyle(
                                "-fx-text-fill: #aaaaaa;" +
                                                "-fx-font-size: 14px;");

                content.getChildren().addAll(
                                title,
                                description);

                return content;
        }

        // ############################## FOOTER ##############################
        private static HBox createFooter() {

                HBox footer = new HBox();

                footer.setPadding(new Insets(15, 20, 15, 20));
                footer.setAlignment(Pos.CENTER);

                footer.setStyle(
                                "-fx-background-color: #080c0d;" +
                                                "-fx-border-color: #1b2021;" +
                                                "-fx-border-width: 1 0 0 0;");

                Label text = new Label(
                                "© 2026 AgriBiz Hub | Empowering Modern Agriculture");

                text.setStyle(
                                "-fx-text-fill: #777777;" +
                                                "-fx-font-size: 12px;");

                footer.getChildren().add(text);

                return footer;
        }

        public void backToMarket() {
                LoginPage.mainStage.setScene(marketPlaceScene);
        }
}
