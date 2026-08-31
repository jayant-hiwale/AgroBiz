package com.pravartak.view.farmer.common;

import com.pravartak.view.farmer.AIAdvisorPage;
import com.pravartak.view.farmer.CommuityPage;
import com.pravartak.view.farmer.ExplorerPage;
import com.pravartak.view.farmer.FarmerDashboard;
import com.pravartak.view.farmer.HomePageFarmer;
import com.pravartak.view.farmer.LearningPage;
import com.pravartak.view.farmer.MarketPlace;
import com.pravartak.view.farmer.SchemesPage;
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
        navbar.setStyle("-fx-background-color: #080c0d;" +"-fx-border-color: #1b2021;" + "-fx-border-width: 0 0 1 0;");

        // Logo
        Label logo = new Label("Agro Biz");
        logo.setStyle("-fx-text-fill: #68d34a;" +"-fx-font-size: 24px;" +"-fx-font-weight: bold;");

        HBox left = new HBox(logo);
        left.setAlignment(Pos.CENTER_LEFT);
        left.setPrefWidth(450);

        // Navigation
        Button home =  navButton("Home");
        Button explorer = navButton("Explorer");
        Button marketplace = navButton("Marketplace");
        Button community = navButton("Community");
        Button learning = navButton("Learning");
        Button schemes = navButton("Schemes");
        Button aiadvisor = navButton("AI Advisor");

        //curent page
        if (currentPage.equals("Home")) {
            home.setStyle(navButtonActive());
        }
        home.setOnAction(e -> {
            HomePageFarmer homePageFarmer = new HomePageFarmer();
            LoginPage.mainStage.setScene(homePageFarmer.getHomePageFarmer());
        });

        // Current page
        if (currentPage.equals("Explorer")) {
            explorer.setStyle(navButtonActive());
        }
        explorer.setOnAction(e -> {
            System.out.println("Explore button clicked");
            ExplorerPage explorerPage = new ExplorerPage();
            LoginPage.mainStage.setScene(explorerPage.getExplorerPage());
        });

        if (currentPage.equals("Marketplace")) {
            marketplace.setStyle(navButtonActive());
        }
        marketplace.setOnAction(e -> {
            System.out.println("MarketButton Clicked");
            MarketPlace marketPlaceScene = new MarketPlace();
            LoginPage.mainStage.setScene(marketPlaceScene.getMarketPlaceScene());
        });

        if (currentPage.equals("Community")) {
            community.setStyle(navButtonActive());
        }
        community.setOnAction(e -> {

            // CommuityPage commuityPageScene = new CommuityPage();
            // LoginPage.mainStage.setScene(commuityPageScene.getCommunityScene());
        });

        if (currentPage.equals("Learning")) {
            learning.setStyle(navButtonActive());
        }
        learning.setOnAction(e->{
            LearningPage learningPage = new LearningPage();
            System.out.println("Learning button Clicked");;
            LoginPage.mainStage.setScene(learningPage.get_learning_pageScene());
        });

        if (currentPage.equals("Schemes")) {
            schemes.setStyle(navButtonActive());
        }
         schemes.setOnAction(e->{
            //LearningPage learningPage = new LearningPage()
            System.out.println("Schemes button Clicked");;
            //LoginPage.mainStage.setScene(learningPage.get_learning_pageScene());
            SchemesPage schemesPage = new SchemesPage();
            LoginPage.mainStage.setScene(schemesPage.getSchemesPage());
        });

        if (currentPage.equals("AI Advisor")) {
            aiadvisor.setStyle(navButtonActive());
        }
        aiadvisor.setOnAction(e->{
            //LearningPage learningPage = new LearningPage();
            System.out.println("AI advisor button Clicked");;
            //LoginPage.mainStage.setScene(learningPage.get_learning_pageScene());
            AIAdvisorPage ai = new AIAdvisorPage();
            LoginPage.mainStage.setScene(ai.getAIAdvisorScene());
        });

        HBox center = new HBox(25,home,explorer,marketplace,community,learning,schemes,aiadvisor);
        center.setAlignment(Pos.CENTER);

        
        Button profile = new Button("◎ Profile");
        if (currentPage.equals("◎ Profile")) {
            profile.setStyle(navButtonActive());
        }
        profile.setOnAction(e->{
            System.out.println("Profile button Clicked");;
            FarmerDashboard fm = new FarmerDashboard();
            LoginPage.mainStage.setScene(fm.getDashboardScene());
        });

       
        profile.setStyle(navButtonActive());

        
        HBox right = new HBox(profile);
        right.setAlignment(Pos.CENTER_RIGHT);
        right.setPrefWidth(450);

        navbar.getChildren().addAll(left, center, right);

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
                "-fx-font-size: 13px;"+
                "-fx-cursor: hand;" +
                "-fx-font-weight: bold;"+
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
