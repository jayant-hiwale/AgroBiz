
package com.pravartak.view.farmer;

import java.net.URL;

import com.pravartak.view.login.LoginPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomePageFarmer {

    private Scene homepagescene;

    public Scene getHomePageFarmer() {

        // main border pane for all border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #1a2f1c;");

        // top borgderpane
        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setPadding(new Insets(10, 18, 10, 18));
        topBorderPane.setStyle("-fx-background-color: #1a2f1c;-fx-border-color: #283028;-fx-border-width: 0 0 1 0;");

        // logo name 
        Label logo = new Label("AgroBiz ");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 21));
        logo.setTextFill(Color.rgb(86, 157, 113));

        //explorer
        Button exploreButton = new Button("Explorer ");
        exploreButton.setStyle("-fx-background-color: transparent;"+ "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;"+"-fx-font-weight:bold;-fx-text-fill:white;");
        exploreButton.setOnAction(event -> {
            ExplorerPage explorerpage = new ExplorerPage();
            Runnable callbacktohome = new Runnable() {
                @Override
                public void run() {
                    backtohome();
                }
            };
            LoginPage.mainStage.setScene(explorerpage.getExplorerPage(callbacktohome));
        });
        Button marketButton = new Button("MarketPlace ");
        marketButton.setStyle("-fx-background-color: transparent;"+ "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;"+"-fx-font-weight:bold;-fx-text-fill:white;");
        Button communityButton = new Button(" Community");
        communityButton.setStyle("-fx-background-color: transparent;"+ "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;"+"-fx-font-weight:bold;-fx-text-fill:white;");
        Button aiButton = new Button("  AI Advisor");
        aiButton.setStyle("-fx-background-color: transparent;"+ "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;"+"-fx-font-weight:bold;-fx-text-fill:white;");
        Button learningButton = new Button(" Learning ");
        learningButton.setStyle("-fx-background-color: transparent;"+ "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;"+"-fx-font-weight:bold;-fx-text-fill:white;");
        learningButton.setOnAction(event -> {
            LearningPage learningpage = new LearningPage();
            Runnable callbacktolearning = new Runnable() {
                @Override
                public void run() {
                    backtohome();
                }
            };

            LoginPage.mainStage.setScene(learningpage.get_learning_pageScene(callbacktolearning));
        });

        Button schemesButton = new Button(" Schemes ");
        schemesButton.setStyle("-fx-background-color: transparent;"+ "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;"+"-fx-font-weight:bold;-fx-text-fill:white;");

        HBox navigationBox = new HBox(8);
        //navigationBox.setStyle("-fx-background-color: transparent;");
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(exploreButton, marketButton, communityButton, aiButton, learningButton, schemesButton);

        // right
        // Button loginButton = new Button("Login");
        // loginButton.setPrefHeight(32);

        Button profile = new Button("◯ Profile");
        profile.setFont(Font.font("Arial", 22));
        profile.setTextFill(Color.WHITE);
        profile.setStyle("-fx-background-color: transparent;");

       

        HBox profileBox = new HBox(4);
        profileBox.setAlignment(Pos.CENTER);
        profileBox.getChildren().addAll(profile);

        HBox rightHeader = new HBox(12);
        rightHeader.setAlignment(Pos.CENTER_RIGHT);
        rightHeader.getChildren().addAll(/*loginButton ,*/ profileBox);

        //top border
        topBorderPane.setLeft(logo);
        topBorderPane.setCenter(navigationBox);
        topBorderPane.setRight(rightHeader);

        VBox mainVBox = new VBox(28);
        mainVBox.setPadding(new Insets(18, 18, 30, 18));
        mainVBox.setFillWidth(true);

//stackpane
        StackPane firstHBox = new StackPane();
        firstHBox.setPrefHeight(300);
        firstHBox.setMinHeight(400);
        firstHBox.setPrefWidth(500);

        //above backimg
        URL imageURL = getClass().getResource("/image.png");

        if (imageURL == null) {
            throw new RuntimeException("image.png not found!\n" + "Put it inside:\n" + "src/main/resources/image.png");
        }

        Image farmImage = new Image(imageURL.toExternalForm());
    
        ImageView farmImageView = new ImageView(farmImage);
        farmImageView.setFitHeight(400);
        farmImageView.setFitWidth(1300);
        // farmImageView.setPreserveRatio(false);
        // farmImageView.fitWidthProperty().bind(firstHBox.widthProperty());
        // farmImageView.fitHeightProperty().bind(firstHBox.heightProperty());

    //back image font or clear
        // Rectangle heroOverlay = new Rectangle();
        // heroOverlay.setFill(Color.rgb(255, 255, 255, 0.58));
        // heroOverlay.widthProperty().bind(firstHBox.widthProperty());
        // heroOverlay.heightProperty().bind(firstHBox.heightProperty());

        // HERO TITLE
        Label mainTitle = new Label("     Revolutionize Your\n" + "Livestock Management with AI");
        mainTitle.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        mainTitle.setTextFill(Color.rgb(20, 21, 20));
        mainTitle.setWrapText(true);
        mainTitle.setAlignment(Pos.CENTER);
        mainTitle.setMaxWidth(850);

        
  
        // above search field
        TextField farmSearch = new TextField();
        farmSearch.setPromptText("Search products, livestock, or farming guides...");
        farmSearch.setPrefWidth(430);
        farmSearch.setPrefHeight(42);

        Button explorerButton = new Button("Explore");
        explorerButton.setPrefHeight(42);
        explorerButton.setPrefWidth(90);
        explorerButton.setStyle("-fx-background-color: #1f2921;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;");

        // search explorer button
        explorerButton.setOnAction(event -> {
            ExplorerPage explorerpage = new ExplorerPage();
            Runnable callbacktohome = new Runnable() {
                @Override
                public void run() {
                    backtohome();
                }};

            LoginPage.mainStage.setScene(explorerpage.getExplorerPage(callbacktohome));
        });

        HBox searchHBox = new HBox(8);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.getChildren().addAll(farmSearch, explorerButton);

        // above VBox which contain above section 
        VBox heroContent = new VBox(15);
        heroContent.setAlignment(Pos.CENTER);
        heroContent.getChildren().addAll(mainTitle, searchHBox);

        firstHBox.getChildren().addAll(farmImageView, /*heroOverlay ,*/ heroContent);
        StackPane.setAlignment(heroContent, Pos.CENTER);

        //tranding catagory section
        VBox secondVBox = new VBox(14);
        secondVBox.setPrefWidth(1000);

        Label trendingLabel = new Label("Trending Categories");
        trendingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        trendingLabel.setTextFill(Color.rgb(242, 240, 240));

        Region headingSpace = new Region();
        HBox.setHgrow(headingSpace, Priority.ALWAYS);

        Button viewButton = new Button("View All");
        viewButton.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: #d0d8d3;" + "-fx-font-weight: bold;");
        viewButton.setTextFill(Color.WHITE);
        viewButton.setOnMouseEntered(e -> {
            viewButton.setStyle("-fx-background-color: #101718;" + "-fx-background-radius: 12;" + "-fx-border-color: #7ED957;" + "-fx-border-width: 2px;" + "-fx-border-radius: 12;" + "-fx-padding: 10px;" + "-fx-cursor: hand;" + "-fx-effect: dropshadow(gaussian, rgba(126,217,87,0.20), 18, 0, 0, 6);");
        });

        viewButton.setOnMouseExited(e -> {
           viewButton.setStyle("-fx-background-color: #101718;" + "-fx-background-radius: 12;" + "-fx-border-color: #293334;" + "-fx-border-width: 1px;" + "-fx-border-radius: 12;" + "-fx-padding: 10px;" + "-fx-cursor: hand;" + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.45), 12, 0, 0, 5);");
        });

        // view all Explorer
        viewButton.setOnAction(event -> {
            ExplorerPage explorerpage = new ExplorerPage();

            Runnable callbacktohome = new Runnable() {
                @Override
                public void run() {
                    backtohome();
                }
            };

            LoginPage.mainStage.setScene(explorerpage.getExplorerPage(callbacktohome));
        });

        HBox headingHBox = new HBox();
        headingHBox.setAlignment(Pos.CENTER_LEFT);
        headingHBox.getChildren().addAll(trendingLabel, headingSpace, viewButton);

    // Second HBOX which contain tranding catogory section
        HBox secondHBox = new HBox(16);
        secondHBox.setAlignment(Pos.CENTER);
        secondHBox.setFillHeight(true);
        secondHBox.setMaxWidth(1000);

        // poultry
        VBox poultryBox = new VBox(8);
        poultryBox.setPadding(new Insets(8));
        poultryBox.setPrefWidth(230);
        poultryBox.setPrefHeight(205);
        poultryBox.setAlignment(Pos.TOP_LEFT);
        poultryBox.setStyle("-fx-background-color: darkgreen;" + "-fx-background-radius: 12;" + "-fx-border-color: #eff6f3;" + "-fx-border-radius: 12;");

        Image poultryImage = new Image(getClass().getResource("/poltry.png").toExternalForm());

        ImageView poultryImageView = new ImageView(poultryImage);
        poultryImageView.setFitWidth(180);
        poultryImageView.setFitHeight(95);

        Label poultryTitle = new Label("Poultry Farming");
        poultryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        poultryTitle.setTextFill(Color.WHITE);

        Label poultryDescription = new Label("Advanced systems for optimal bird health.");
        poultryDescription.setFont(Font.font("Arial", 11));
        poultryDescription.setWrapText(true);
        poultryDescription.setTextFill(Color.WHITE);

        poultryBox.getChildren().addAll(poultryImageView, poultryTitle, poultryDescription);

        // dairy
        VBox dairyBox = new VBox(8);
        dairyBox.setPadding(new Insets(8));
        dairyBox.setPrefWidth(230);
        dairyBox.setPrefHeight(205);
        dairyBox.setAlignment(Pos.TOP_LEFT);
        dairyBox.setStyle("-fx-background-color: darkgreen;" + "-fx-background-radius: 12;" + "-fx-border-color: #dddddd;"+"-fx-border-radius: 12;");

        Image dairyImage = new Image(getClass().getResource("/Dairy.png").toExternalForm());

        ImageView dairyImageView = new ImageView(dairyImage);
        dairyImageView.setFitWidth(180);
        dairyImageView.setFitHeight(95);

        Label dairyTitle = new Label("Dairy Farming");
        dairyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        dairyTitle.setTextFill(Color.WHITE);

        Label dairyDescription = new Label("Modern techniques for sustainable milk production.");
        dairyDescription.setFont(Font.font("Arial", 11));
        dairyDescription.setWrapText(true);
        dairyDescription.setTextFill(Color.WHITE);

        dairyBox.getChildren().addAll(dairyImageView, dairyTitle, dairyDescription);

//change
        VBox precisionBox = new VBox(8);
        precisionBox.setPadding(new Insets(8));
        precisionBox.setPrefWidth(230);
        precisionBox.setPrefHeight(205);
        precisionBox.setAlignment(Pos.TOP_LEFT);
        precisionBox.setStyle("-fx-background-color: darkgreen;" + "-fx-background-radius: 12;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 12;");

        Image precisionImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView precisionImageView = new ImageView(precisionImage);
        precisionImageView.setFitWidth(180);
        precisionImageView.setFitHeight(95);

        Label precisionTitle = new Label("Precision Dairy");
        precisionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        precisionTitle.setTextFill(Color.WHITE);

        Label precisionDescription = new Label("Data-driven livestock management.");
        precisionDescription.setFont(Font.font("Arial", 11));
        precisionDescription.setWrapText(true);
        precisionDescription.setTextFill(Color.WHITE);

        precisionBox.getChildren().addAll(precisionImageView, precisionTitle, precisionDescription);

//random change later
        VBox machineryBox = new VBox(8);
        machineryBox.setPadding(new Insets(8));
        machineryBox.setPrefWidth(230);
        machineryBox.setPrefHeight(205);
        machineryBox.setAlignment(Pos.TOP_LEFT);
        machineryBox.setStyle("-fx-background-color: darkgreen;" + "-fx-background-radius: 12;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 12;");

        Image machineryImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView machineryImageView = new ImageView(machineryImage);
        machineryImageView.setFitWidth(180);
        machineryImageView.setFitHeight(95);

        Label machineryTitle = new Label("Smart Machinery");
        machineryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        machineryTitle.setTextFill(Color.WHITE);

        Label machineryDescription = new Label("Automated equipment for scale.");
        machineryDescription.setFont(Font.font("Arial", 11));
        machineryDescription.setWrapText(true);
        machineryDescription.setTextFill(Color.WHITE);

        machineryBox.getChildren().addAll(machineryImageView, machineryTitle, machineryDescription);

        secondHBox.getChildren().addAll(poultryBox, dairyBox, precisionBox, machineryBox);
        secondVBox.getChildren().addAll(headingHBox, secondHBox);
        secondVBox.setPrefWidth(500);



        // THIRD SECTION
        HBox thirdHBox = new HBox(20);
        thirdHBox.setPadding(new Insets(28));
        thirdHBox.setAlignment(Pos.CENTER_LEFT);
        thirdHBox.setPrefHeight(250);
        thirdHBox.setMaxWidth(800);
        //thirdHBox.setMinHeight(135);
        thirdHBox.setStyle("-fx-background-color: #075a2b;" + "-fx-background-radius: 15;");

        VBox thirdTextVBox = new VBox(8);
        thirdTextVBox.setAlignment(Pos.CENTER_LEFT);

        Label intelligentLabel = new Label("INTELLIGENT PLANNING");
        intelligentLabel.setTextFill(Color.LIGHTGREEN);
        intelligentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Label planTitle = new Label("AI Business Plan Generator");
        planTitle.setTextFill(Color.WHITE);
        planTitle.setFont(Font.font("Arial", FontWeight.BOLD, 23));

        Label planDescription = new Label("Leverage predictive analytics and local market data " + "to craft an optimal business strategy for your next " + "herd expansion. Minimize risk, maximize yield.");
        planDescription.setTextFill(Color.WHITE);
        planDescription.setFont(Font.font("Arial", 13));
        planDescription.setWrapText(true);
        planDescription.setMaxWidth(500);

        thirdTextVBox.getChildren().addAll(intelligentLabel, planTitle, planDescription);

        Region thirdSpace = new Region();
        HBox.setHgrow(thirdSpace, Priority.ALWAYS);

        Button generateButton = new Button("Generate Plan  ✨");
        generateButton.setPrefWidth(125);
        generateButton.setPrefHeight(45);
        generateButton.setStyle("-fx-background-color: #a8f08c;" + "-fx-text-fill: #064d23;" + "-fx-font-weight: bold;" + "-fx-background-radius: 8;");

        thirdHBox.getChildren().addAll(thirdTextVBox, thirdSpace, generateButton);

        // ADD MAIN CONTENT
        mainVBox.getChildren().addAll(firstHBox, secondVBox, thirdHBox);
        mainVBox.setStyle("-fx-background-color: linear-gradient(" + "to bottom right," + "#080D0E 0%," + "#0C1513 50%," + "#101B14 100%);" + "-fx-padding: 28px;");
        mainVBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        mainVBox.setPrefWidth(Double.MAX_VALUE);

        // Scrollpane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMaxWidth(Double.MAX_VALUE);
        scrollPane.setStyle("-fx-background-color: #0a3812;");

//borderpane for footer
        BorderPane footer = new BorderPane();
        footer.setPadding(new Insets(18, 25, 18, 25));
        footer.setStyle("-fx-background-color: #1a2f1c;");

        VBox footerLeft = new VBox(6);

        Label footerLogo = new Label("Agro Biz ");
        footerLogo.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        footerLogo.setTextFill(Color.rgb(229, 231, 230));

        Label footerDescription = new Label("Empowering modern farmers with \n intelligent tools and premium market access.");
        footerDescription.setFont(Font.font("Arial", 10));
        footerDescription.setTextFill(Color.WHITE);

        Label copyright = new Label("© 2026 AgroBiz . Empowering sustainable agriculture.");
        copyright.setFont(Font.font("Arial", 9));
        copyright.setTextFill(Color.WHITE);

        footerLeft.getChildren().addAll(footerLogo, footerDescription, copyright);

        VBox footerRight = new VBox(7);
        footerRight.setAlignment(Pos.CENTER_RIGHT);

        HBox footerLinks1 = new HBox(25);
        footerLinks1.setAlignment(Pos.CENTER_RIGHT);

        Label government = new Label("Government Schemes");
        government.setStyle("-fx-text-fill:white");
        Label farmerCommunity = new Label("Farmer Community");
        farmerCommunity.setStyle("-fx-text-fill:white");
        Label privacy = new Label("Privacy Policy");
        privacy.setStyle("-fx-text-fill:white");

        footerLinks1.getChildren().addAll(government, farmerCommunity, privacy);

        HBox footerLinks2 = new HBox(25);
        footerLinks2.setAlignment(Pos.CENTER_RIGHT);

        Label marketNews = new Label("Market News");
        marketNews.setStyle("-fx-text-fill:white");
        Label terms = new Label("Terms of Service");
        terms.setStyle("-fx-text-fill:white");

        footerLinks2.getChildren().addAll(marketNews, terms);
        footerRight.getChildren().addAll(footerLinks1, footerLinks2);

        footer.setLeft(footerLeft);
        footer.setRight(footerRight);

       
        borderPane.setTop(topBorderPane);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(footer);

        
        Scene scene = new Scene(borderPane, 800, 768);
        homepagescene = scene;
        return scene;
    }

    // bback to home
    public void backtohome() {
        LoginPage.mainStage.setScene(homepagescene);
    }
}