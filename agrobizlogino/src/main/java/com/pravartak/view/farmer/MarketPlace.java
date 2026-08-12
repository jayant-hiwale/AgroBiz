package com.pravartak.view.farmer;

import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
    private  HBox createNavbar() {

        HBox navbar = new HBox(25);

        navbar.setPadding(new Insets(10, 20, 10, 20));
        navbar.setAlignment(Pos.CENTER_LEFT);

        navbar.setStyle(
                "-fx-background-color: #080c0d;" +
                        "-fx-border-color: #1b2021;" +
                        "-fx-border-width: 0 0 1 0;");

        Label logo = new Label("AgroBiz ");

        logo.setStyle(
                "-fx-text-fill: #68d34a;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;");

        Button explorerButton = navButton("Explorer");
        Button marketplaceButton = navButton("Marketplace") ;
        Button communityButton = navButton("Community");
        Button learningButton = navButton("Learning");
        Button schemesButton = navButton("Schemes");
        
        communityButton .setOnAction(e->{
            System.out.println("community button clicked ");
            CommuityPage communityPage = new CommuityPage();

            Runnable callBackActionMarket = ()->{
                backToMarket();
            };

            LoginPage.mainStage.setScene(communityPage.getCommunityScene(callBackActionMarket));
        }            
        );

        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        TextField search = new TextField();
        search.setPromptText("Search marketplace...");

        search.setPrefWidth(170);

        search.setStyle(
                "-fx-background-color: #101516;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: #777;" +
                        "-fx-border-color: #303738;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;");

        Button sellButton = new Button("◇ List for Sale");

        sellButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #68d34a;" +
                        "-fx-border-color: #68d34a;" +
                        "-fx-border-radius: 5;");

        Label notification = new Label("♧");
        Label profile = new Label("◎");
        Label login = new Label("Login");

        notification.setStyle("-fx-text-fill: #bbbbbb; -fx-font-size: 18px;");
        profile.setStyle("-fx-text-fill: #bbbbbb; -fx-font-size: 18px;");
        login.setStyle("-fx-text-fill: #bbbbbb;");

        navbar.getChildren().addAll(
                logo,
                explorerButton ,
                marketplaceButton ,
                communityButton ,
                learningButton ,
                schemesButton,
                spacer,
                search,
                sellButton,
                notification,
                profile,
                login);

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
                "-fx-border-width: 0 0 2 0;"
        );
    });

    button.setOnMouseExited(e -> {
        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #aaaaaa;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 0 5 0;" +
                "-fx-border-color: transparent;" +
                "-fx-border-width: 0 0 2 0;"
        );
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
    public  void backToMarket(){
        LoginPage.mainStage.setScene(marketPlaceScene);
    }
}
