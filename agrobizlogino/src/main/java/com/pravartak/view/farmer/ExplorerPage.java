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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ExplorerPage {
        private Scene explorepageScene;
    public Scene getExplorerPage(Runnable callbacktohome) {

        // MAIN BORDER PANE
        BorderPane borderPane = new BorderPane();

        // TOP BORDER PANE
        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setPadding(new Insets(10, 25, 10, 25));
        topBorderPane.setStyle("-fx-background-color: white;" + "-fx-border-color: #dddddd;" + "-fx-border-width: 0 0 1 0;");

        // LOGO
        Label logo = new Label("AgroBiz ");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        logo.setTextFill(Color.rgb(10, 80, 35));

        // above buttons
        Button homeButton = new Button("Home");
        Button exploreButton = new Button("Explore");
        Button marketplaceButton = new Button("Marketplace");
        Button schemesButton = new Button("Schemes");
        Button newsButton = new Button("AI Advisor");
        Button communityButton = new Button("Community");
        Button learningButton = new Button("Learning");

        learningButton.setOnAction(event -> {
            LearningPage learningpage = new LearningPage();
            Runnable callbacktoexplorer = new Runnable() {
                @Override
                public void run() {
                    backtoexplorer();
                }
            };

            LoginPage.mainStage.setScene(learningpage.get_learning_pageScene(callbacktoexplorer));
        });

        // call back to home button
        homeButton.setOnAction(event -> {
            callbacktohome.run();
        });

        exploreButton.setStyle("-fx-background-color: transparent;" + "-fx-font-weight: bold;" + "-fx-border-color: #0b5427;" + "-fx-border-width: 0 0 2 0;");

        HBox navigationBox = new HBox(18);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(homeButton, exploreButton, marketplaceButton, schemesButton, newsButton, communityButton,learningButton);

        // TOP RIGHT
        // Label searchIcon = new Label("⌕");
        // searchIcon.setFont(Font.font("Arial", 24));

        // Label notificationIcon = new Label("♧");
        // notificationIcon.setFont(Font.font("Arial", 22));

        Label profileIcon = new Label("◉ Profile");
        profileIcon.setFont(Font.font("Arial", 22));

       
        HBox topRight = new HBox(15);
        topRight.setAlignment(Pos.CENTER_RIGHT);
        topRight.getChildren().addAll(/*searchIcon, notificationIcon,*/ profileIcon);

        // ADD TOP PARTS
        topBorderPane.setLeft(logo);
        topBorderPane.setCenter(navigationBox);
        topBorderPane.setRight(topRight);

        // MAIN VBOX
        VBox mainVBox = new VBox(25);
        mainVBox.setPadding(new Insets(35, 45, 45, 45));
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setBackground(new Background(new BackgroundFill(Color.rgb(249, 249, 246), CornerRadii.EMPTY, Insets.EMPTY)));

        // MAIN TITLE
        Label mainTitle = new Label("Discover Livestock\n" + "Innovations");
        mainTitle.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        mainTitle.setTextFill(Color.rgb(20, 20, 20));
        mainTitle.setAlignment(Pos.CENTER);
        mainTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // DESCRIPTION
        Label description = new Label("Explore high-tech farming guides, trending animal " + "husbandry businesses, and advanced\n" + "livestock categories to elevate your agricultural enterprise.");
        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.rgb(55, 55, 55));
        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);
        description.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // SEARCH BAR
        TextField searchField = new TextField();
        searchField.setPromptText("Search breeds, technologies, or guides...");
        searchField.setPrefHeight(48);
        searchField.setPrefWidth(620);
        searchField.setPadding(new Insets(0, 18, 0, 18));

        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(90);
        searchButton.setPrefHeight(42);
        searchButton.setStyle("-fx-background-color: #075a2b;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 20;");

        HBox searchHBox = new HBox(8);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.setMaxWidth(720);
        searchHBox.setPadding(new Insets(5, 8, 5, 8));
        searchHBox.setStyle("-fx-background-color: white;" + "-fx-background-radius: 30;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 30;");
        searchHBox.getChildren().addAll(searchField, searchButton);

        // CATEGORY FILTER BUTTONS
        Button allCategories = new Button("All Categories");
        Button poultry = new Button("Poultry");
        Button dairy = new Button("Dairy Cattle");
        Button aquatic = new Button("Aquaculture");
        Button swine = new Button("Swine");
        Button smallRuminants = new Button("Small Ruminants");
        Button plantNursery = new Button("Plant Nursery");

        allCategories.setStyle("-fx-background-color: #075a2b;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-background-radius: 20;");

        String categoryStyle = "-fx-background-color: white;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;";

        poultry.setStyle(categoryStyle);
        dairy.setStyle(categoryStyle);
        aquatic.setStyle(categoryStyle);
        swine.setStyle(categoryStyle);
        smallRuminants.setStyle(categoryStyle);
        plantNursery.setStyle(categoryStyle);

        HBox categoryFilterBox = new HBox(10);
        categoryFilterBox.setAlignment(Pos.CENTER);
        categoryFilterBox.getChildren().addAll(allCategories, poultry, dairy, aquatic, swine, smallRuminants, plantNursery);

        // CATEGORY TITLE
        Label categoryTitle = new Label("Explore Categories");
        categoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        categoryTitle.setTextFill(Color.rgb(30, 30, 30));

        // FIRST CATEGORY HBOX
        HBox firstCategoryHBox = new HBox(18);
        firstCategoryHBox.setAlignment(Pos.CENTER);

        // POULTRY CARD
        VBox poultryCard = new VBox(10);
        poultryCard.setPadding(new Insets(10));
        poultryCard.setPrefWidth(220);
        poultryCard.setPrefHeight(220);
        poultryCard.setAlignment(Pos.TOP_LEFT);
        poultryCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        URL poultryURL = getClass().getResource("/poltry.png");

        if (poultryURL == null) {
            throw new RuntimeException("poltry.png not found!");
        }

        Image poultryImage = new Image(poultryURL.toExternalForm());

        ImageView poultryImageView = new ImageView(poultryImage);
        poultryImageView.setFitWidth(198);
        poultryImageView.setFitHeight(105);
        poultryImageView.setPreserveRatio(false);

        Label poultryTitle = new Label("Poultry Farming");
        poultryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label poultryDescription = new Label("Advanced systems for optimal bird health.");
        poultryDescription.setFont(Font.font("Arial", 12));
        poultryDescription.setTextFill(Color.GRAY);
        poultryDescription.setWrapText(true);

        poultryCard.getChildren().addAll(poultryImageView, poultryTitle, poultryDescription);

        // DAIRY CARD
        VBox dairyCard = new VBox(10);
        dairyCard.setPadding(new Insets(10));
        dairyCard.setPrefWidth(220);
        dairyCard.setPrefHeight(220);
        dairyCard.setAlignment(Pos.TOP_LEFT);
        dairyCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        URL dairyURL = getClass().getResource("/Dairy.png");

        if (dairyURL == null) {
            throw new RuntimeException("Dairy.png not found!");
        }

        Image dairyImage = new Image(dairyURL.toExternalForm());

        ImageView dairyImageView = new ImageView(dairyImage);
        dairyImageView.setFitWidth(198);
        dairyImageView.setFitHeight(105);
        dairyImageView.setPreserveRatio(false);

        Label dairyTitle = new Label("Dairy Cattle");
        dairyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label dairyDescription = new Label("Modern techniques for sustainable milk production.");
        dairyDescription.setFont(Font.font("Arial", 12));
        dairyDescription.setTextFill(Color.GRAY);
        dairyDescription.setWrapText(true);

        dairyCard.getChildren().addAll(dairyImageView, dairyTitle, dairyDescription);

        // AQUACULTURE CARD
        VBox aquaticCard = new VBox(10);
        aquaticCard.setPadding(new Insets(10));
        aquaticCard.setPrefWidth(220);
        aquaticCard.setPrefHeight(220);
        aquaticCard.setAlignment(Pos.TOP_LEFT);
        aquaticCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        Image aquaticImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView aquaticImageView = new ImageView(aquaticImage);
        aquaticImageView.setFitWidth(198);
        aquaticImageView.setFitHeight(105);

        Label aquaticTitle = new Label("Aquaculture");
        aquaticTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label aquaticDescription = new Label("Smart aquatic farming and sustainable fish production.");
        aquaticDescription.setFont(Font.font("Arial", 12));
        aquaticDescription.setTextFill(Color.GRAY);
        aquaticDescription.setWrapText(true);

        aquaticCard.getChildren().addAll(aquaticImageView, aquaticTitle, aquaticDescription);

        // SWINE CARD
        VBox swineCard = new VBox(10);
        swineCard.setPadding(new Insets(10));
        swineCard.setPrefWidth(220);
        swineCard.setPrefHeight(220);
        swineCard.setAlignment(Pos.TOP_LEFT);
        swineCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        Image swineImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView swineImageView = new ImageView(swineImage);
        swineImageView.setFitWidth(198);
        swineImageView.setFitHeight(105);

        Label swineTitle = new Label("Swine Farming");
        swineTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label swineDescription = new Label("Efficient livestock systems and smart feeding.");
        swineDescription.setFont(Font.font("Arial", 12));
        swineDescription.setTextFill(Color.GRAY);
        swineDescription.setWrapText(true);

        swineCard.getChildren().addAll(swineImageView, swineTitle, swineDescription);

        firstCategoryHBox.getChildren().addAll(poultryCard, dairyCard, aquaticCard, swineCard);

        // SECOND CATEGORY HBOX
        HBox secondCategoryHBox = new HBox(18);
        secondCategoryHBox.setAlignment(Pos.CENTER);

        // SMALL RUMINANTS CARD
        VBox ruminantsCard = new VBox(10);
        ruminantsCard.setPadding(new Insets(10));
        ruminantsCard.setPrefWidth(220);
        ruminantsCard.setPrefHeight(220);
        ruminantsCard.setAlignment(Pos.TOP_LEFT);
        ruminantsCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        Image ruminantsImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView ruminantsImageView = new ImageView(ruminantsImage);
        ruminantsImageView.setFitWidth(198);
        ruminantsImageView.setFitHeight(105);

        Label ruminantsTitle = new Label("Small Ruminants");
        ruminantsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label ruminantsDescription = new Label("Modern goat and sheep farming solutions.");
        ruminantsDescription.setFont(Font.font("Arial", 12));
        ruminantsDescription.setTextFill(Color.GRAY);
        ruminantsDescription.setWrapText(true);

        ruminantsCard.getChildren().addAll(ruminantsImageView, ruminantsTitle, ruminantsDescription);

        // PLANT NURSERY CARD
        VBox nurseryCard = new VBox(10);
        nurseryCard.setPadding(new Insets(10));
        nurseryCard.setPrefWidth(220);
        nurseryCard.setPrefHeight(220);
        nurseryCard.setAlignment(Pos.TOP_LEFT);
        nurseryCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        Image nurseryImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView nurseryImageView = new ImageView(nurseryImage);
        nurseryImageView.setFitWidth(198);
        nurseryImageView.setFitHeight(105);

        Label nurseryTitle = new Label("Plant Nursery");
        nurseryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label nurseryDescription = new Label("Healthy seedlings and modern nursery management.");
        nurseryDescription.setFont(Font.font("Arial", 12));
        nurseryDescription.setTextFill(Color.GRAY);
        nurseryDescription.setWrapText(true);

        nurseryCard.getChildren().addAll(nurseryImageView, nurseryTitle, nurseryDescription);

        // SMART MACHINERY CARD
        VBox machineryCard = new VBox(10);
        machineryCard.setPadding(new Insets(10));
        machineryCard.setPrefWidth(220);
        machineryCard.setPrefHeight(220);
        machineryCard.setAlignment(Pos.TOP_LEFT);
        machineryCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        Image machineryImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView machineryImageView = new ImageView(machineryImage);
        machineryImageView.setFitWidth(198);
        machineryImageView.setFitHeight(105);

        Label machineryTitle = new Label("Smart Machinery");
        machineryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label machineryDescription = new Label("Automated agricultural equipment and technology.");
        machineryDescription.setFont(Font.font("Arial", 12));
        machineryDescription.setTextFill(Color.GRAY);
        machineryDescription.setWrapText(true);

        machineryCard.getChildren().addAll(machineryImageView, machineryTitle, machineryDescription);

        // PRECISION AGRICULTURE CARD
        VBox precisionCard = new VBox(10);
        precisionCard.setPadding(new Insets(10));
        precisionCard.setPrefWidth(220);
        precisionCard.setPrefHeight(220);
        precisionCard.setAlignment(Pos.TOP_LEFT);
        precisionCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-color: #dddddd;" + "-fx-border-radius: 15;");

        Image precisionImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView precisionImageView = new ImageView(precisionImage);
        precisionImageView.setFitWidth(198);
        precisionImageView.setFitHeight(105);

        Label precisionTitle = new Label("Precision Agriculture");
        precisionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        Label precisionDescription = new Label("Data-driven technology for better farm decisions.");
        precisionDescription.setFont(Font.font("Arial", 12));
        precisionDescription.setTextFill(Color.GRAY);
        precisionDescription.setWrapText(true);

        precisionCard.getChildren().addAll(precisionImageView, precisionTitle, precisionDescription);

        secondCategoryHBox.getChildren().addAll(ruminantsCard, nurseryCard, machineryCard, precisionCard);

        // ADD ALL CONTENT
        mainVBox.getChildren().addAll(mainTitle, description, searchHBox, categoryFilterBox, categoryTitle, firstCategoryHBox, secondCategoryHBox);

        // SCROLL PANE
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #f9f9f6;");

        // BOTTOM BORDER PANE
        BorderPane bottomBorderPane = new BorderPane();
        bottomBorderPane.setPadding(new Insets(18, 30, 18, 30));
        bottomBorderPane.setStyle("-fx-background-color: #eeeeea;");

        VBox footerLeft = new VBox(5);

        Label footerLogo = new Label("AgriBiz Hub");
        footerLogo.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        footerLogo.setTextFill(Color.rgb(10, 80, 35));

        Label footerText = new Label("Empowering modern farmers with intelligent tools.");
        footerText.setFont(Font.font("Arial", 11));
        footerText.setTextFill(Color.GRAY);

        footerLeft.getChildren().addAll(footerLogo, footerText);

        Label government = new Label("Government Schemes");
        Label marketNews = new Label("Market News");
        Label farmerCommunity = new Label("Farmer Community");
        Label terms = new Label("Terms of Service");
        Label privacy = new Label("Privacy Policy");

        HBox footerRight = new HBox(25);
        footerRight.setAlignment(Pos.CENTER_RIGHT);
        footerRight.getChildren().addAll(government, marketNews, farmerCommunity, terms, privacy);

        bottomBorderPane.setLeft(footerLeft);
        bottomBorderPane.setRight(footerRight);

        // SET BORDER PANE
        borderPane.setTop(topBorderPane);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(bottomBorderPane);

        // SCENE
        Scene scene = new Scene(borderPane, 1100, 768);

        return scene;
    }
    public void backtoexplorer(){
        LoginPage.mainStage.setScene(explorepageScene);
    }
}