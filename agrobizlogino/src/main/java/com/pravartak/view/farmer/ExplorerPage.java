package com.pravartak.view.farmer;

import java.net.URL;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ExplorerPage {

    public Scene getExplorerPage() {

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane borderPane =
                new BorderPane();


        // =====================================================
        // TOP BORDER PANE
        // =====================================================

        BorderPane topBorderPane =
                new BorderPane();

        topBorderPane.setPadding(
                new Insets(
                        10,
                        25,
                        10,
                        25
                )
        );

        topBorderPane.setStyle(
                "-fx-background-color: white;"
                + "-fx-border-color: #dddddd;"
                + "-fx-border-width: 0 0 1 0;"
        );


        // =====================================================
        // LOGO
        // =====================================================

        Label logo =
                new Label(
                        "AgriBiz Hub"
                );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        logo.setTextFill(
                Color.rgb(
                        10,
                        80,
                        35
                )
        );


        // =====================================================
        // TOP NAVIGATION
        // =====================================================
        Button homeButton =
                new Button("Home");

                homeButton.setOnAction(
        event -> {

            Stage stage =
                    (Stage) homeButton
                            .getScene()
                            .getWindow();

            HomePageFarmer homePageFarmer =
                    new HomePageFarmer();

            stage.setScene(
                    homePageFarmer.getHomePageFarmer()
            );
        }
);

        Button exploreButton =
                new Button(
                        "Explore"
                );

        Button marketplaceButton =
                new Button(
                        "Marketplace"
                );

        Button schemesButton =
                new Button(
                        "Schemes"
                );

        Button newsButton =
                new Button(
                        "AI Advisor"
                );

        Button communityButton =
                new Button(
                        "Community"
                );


        exploreButton.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-font-weight: bold;"
                + "-fx-border-color: #0b5427;"
                + "-fx-border-width: 0 0 2 0;"
        );


        HBox navigationBox =
                new HBox(18);

        navigationBox.setAlignment(
                Pos.CENTER
        );

        navigationBox.getChildren().addAll(
                homeButton,
                exploreButton,
                marketplaceButton,
                schemesButton,
                newsButton,
                communityButton
        );


        // =====================================================
        // TOP RIGHT
        // =====================================================

        Label searchIcon =
                new Label(
                        "⌕"
                );

        searchIcon.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );


        Label notificationIcon =
                new Label(
                        "♧"
                );

        notificationIcon.setFont(
                Font.font(
                        "Arial",
                        22
                )
        );


        Label profileIcon =
                new Label(
                        "◉"
                );

        profileIcon.setFont(
                Font.font(
                        "Arial",
                        22
                )
        );


        Button loginButton =
                new Button(
                        "Login"
                );

        loginButton.setPrefWidth(
                65
        );

        loginButton.setStyle(
                "-fx-background-color: #0b5427;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 7;"
        );


        HBox topRight =
                new HBox(15);

        topRight.setAlignment(
                Pos.CENTER_RIGHT
        );

        topRight.getChildren().addAll(
                searchIcon,
                notificationIcon,
                profileIcon,
                loginButton
        );


        // =====================================================
        // ADD TOP COMPONENTS
        // =====================================================

        topBorderPane.setLeft(
                logo
        );

        topBorderPane.setCenter(
                navigationBox
        );

        topBorderPane.setRight(
                topRight
        );


        // =====================================================
        // MAIN VBOX
        // =====================================================

        VBox mainVBox =
                new VBox(25);

        mainVBox.setPadding(
                new Insets(
                        35,
                        45,
                        45,
                        45
                )
        );

        mainVBox.setAlignment(
                Pos.TOP_CENTER
        );

        mainVBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        249,
                                        249,
                                        246
                                ),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // =====================================================
        // MAIN TITLE
        // =====================================================

        Label mainTitle =
                new Label(
                        "Discover Livestock\n"
                        + "Innovations"
                );

        mainTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        42
                )
        );

        mainTitle.setTextFill(
                Color.rgb(
                        20,
                        20,
                        20
                )
        );

        mainTitle.setAlignment(
                Pos.CENTER
        );

        mainTitle.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label description =
                new Label(
                        "Explore high-tech farming guides, trending animal "
                        + "husbandry businesses, and advanced\n"
                        + "livestock categories to elevate your agricultural enterprise."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        description.setTextFill(
                Color.rgb(
                        55,
                        55,
                        55
                )
        );

        description.setWrapText(
                true
        );

        description.setAlignment(
                Pos.CENTER
        );

        description.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );


        // =====================================================
        // SEARCH BAR
        // =====================================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search breeds, technologies, or guides..."
        );

        searchField.setPrefHeight(
                48
        );

        searchField.setPrefWidth(
                620
        );

        searchField.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );


        Button searchButton =
                new Button(
                        "Search"
                );

        searchButton.setPrefWidth(
                90
        );

        searchButton.setPrefHeight(
                42
        );

        searchButton.setStyle(
                "-fx-background-color: #075a2b;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 20;"
        );


        HBox searchHBox =
                new HBox(8);

        searchHBox.setAlignment(
                Pos.CENTER
        );

        searchHBox.setMaxWidth(
                720
        );

        searchHBox.setPadding(
                new Insets(
                        5,
                        8,
                        5,
                        8
                )
        );

        searchHBox.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 30;"
                + "-fx-border-color: #dddddd;"
                + "-fx-border-radius: 30;"
        );


        searchHBox.getChildren().addAll(
                searchField,
                searchButton
        );


        // =====================================================
        // CATEGORY FILTER BUTTONS
        // =====================================================

        Button allCategories =
                new Button(
                        "All Categories"
                );

        Button poultry =
                new Button(
                        "Poultry"
                );

        Button dairy =
                new Button(
                        "Dairy Cattle"
                );

        Button aquatic =
                new Button(
                        "Aquaculture"
                );

        Button swine =
                new Button(
                        "Swine"
                );

        Button smallRuminants =
                new Button(
                        "Small Ruminants"
                );

        Button plantNursery =
                new Button(
                        "Plant Nursery"
                );


        allCategories.setStyle(
                "-fx-background-color: #075a2b;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 20;"
        );


        String categoryStyle =
                "-fx-background-color: white;"
                + "-fx-border-color: #dddddd;"
                + "-fx-border-radius: 20;"
                + "-fx-background-radius: 20;";


        poultry.setStyle(categoryStyle);
        dairy.setStyle(categoryStyle);
        aquatic.setStyle(categoryStyle);
        swine.setStyle(categoryStyle);
        smallRuminants.setStyle(categoryStyle);
        plantNursery.setStyle(categoryStyle);


        HBox categoryFilterBox =
                new HBox(10);

        categoryFilterBox.setAlignment(
                Pos.CENTER
        );

        categoryFilterBox.getChildren().addAll(
                allCategories,
                poultry,
                dairy,
                aquatic,
                swine,
                smallRuminants,
                plantNursery
        );


        // =====================================================
        // CATEGORY TITLE
        // =====================================================

        Label categoryTitle =
                new Label(
                        "Explore Categories"
                );

        categoryTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        categoryTitle.setTextFill(
                Color.rgb(
                        30,
                        30,
                        30
          )  );


        // =====================================================
        // CATEGORY GRID
        // =====================================================

        GridPane categoryGrid =
                new GridPane();

        categoryGrid.setHgap(
                18
        );

        categoryGrid.setVgap(
                18
        );

        categoryGrid.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // CATEGORY CARDS
        // =====================================================

        VBox poultryCard =
                createCategoryCard(
                        "/poltry.png",
                        "Poultry Farming",
                        "Advanced systems for optimal bird health."
                );


        VBox dairyCard =
                createCategoryCard(
                        "/Dairy.png",
                        "Dairy Cattle",
                        "Modern techniques for sustainable milk production."
                );


        VBox aquaticCard =
                createCategoryCard(
                        "/any.png",
                        "Aquaculture",
                        "Smart aquatic farming and sustainable fish production."
                );


        VBox swineCard =
                createCategoryCard(
                        "/any.png",
                        "Swine Farming",
                        "Efficient livestock systems and smart feeding."
                );


        VBox smallRuminantCard =
                createCategoryCard(
                        "/any.png",
                        "Small Ruminants",
                        "Modern goat and sheep farming solutions."
                );


        VBox nurseryCard =
                createCategoryCard(
                        "/any.png",
                        "Plant Nursery",
                        "Healthy seedlings and modern nursery management."
                );


        VBox machineryCard =
                createCategoryCard(
                        "/any.png",
                        "Smart Machinery",
                        "Automated agricultural equipment and technology."
                );


        VBox precisionCard =
                createCategoryCard(
                        "/any.png",
                        "Precision Agriculture",
                        "Data-driven technology for better farm decisions."
                );


        categoryGrid.add(
                poultryCard,
                0,
                0
        );

        categoryGrid.add(
                dairyCard,
                1,
                0
        );

        categoryGrid.add(
                aquaticCard,
                2,
                0
        );

        categoryGrid.add(
                swineCard,
                3,
                0
        );

        categoryGrid.add(
                smallRuminantCard,
                0,
                1
        );

        categoryGrid.add(
                nurseryCard,
                1,
                1
        );

        categoryGrid.add(
                machineryCard,
                2,
                1
        );

        categoryGrid.add(
                precisionCard,
                3,
                1
        );


        // =====================================================
        // ADD CONTENT TO MAIN VBOX
        // =====================================================

        mainVBox.getChildren().addAll(
                mainTitle,
                description,
                searchHBox,
                categoryFilterBox,
                categoryTitle,
                categoryGrid
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                mainVBox
        );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: #f9f9f6;"
        );


        // =====================================================
        // BOTTOM BORDER PANE
        // =====================================================

        BorderPane bottomBorderPane =
                new BorderPane();

        bottomBorderPane.setPadding(
                new Insets(
                        18,
                        30,
                        18,
                        30
                )
        );

        bottomBorderPane.setStyle(
                "-fx-background-color: #eeeeea;"
        );


        // =====================================================
        // FOOTER LEFT
        // =====================================================

        VBox footerLeft =
                new VBox(5);

        Label footerLogo =
                new Label(
                        "AgriBiz Hub"
                );

        footerLogo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        footerLogo.setTextFill(
                Color.rgb(
                        10,
                        80,
                        35
                )
        );


        Label footerText =
                new Label(
                        "Empowering modern farmers with intelligent tools."
                );

        footerText.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        footerText.setTextFill(
                Color.GRAY
        );


        footerLeft.getChildren().addAll(
                footerLogo,
                footerText
        );


        // =====================================================
        // FOOTER RIGHT
        // =====================================================

        Label government =
                new Label(
                        "Government Schemes"
                );

        Label marketNews =
                new Label(
                        "Market News"
                );

        Label farmerCommunity =
                new Label(
                        "Farmer Community"
                );

        Label terms =
                new Label(
                        "Terms of Service"
                );

        Label privacy =
                new Label(
                        "Privacy Policy"
                );


        HBox footerRight =
                new HBox(25);

        footerRight.setAlignment(
                Pos.CENTER_RIGHT
        );

        footerRight.getChildren().addAll(
                government,
                marketNews,
                farmerCommunity,
                terms,
                privacy
        );


        bottomBorderPane.setLeft(
                footerLeft
        );

        bottomBorderPane.setRight(
                footerRight
        );


        // =====================================================
        // SET BORDER PANE
        // =====================================================

        borderPane.setTop(
                topBorderPane
        );

        borderPane.setCenter(
                scrollPane
        );

        borderPane.setBottom(
                bottomBorderPane
        );


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        borderPane,
                        1100,
                        850
                );


        // =====================================================
        // TOP EXPLORE BUTTON
        // =====================================================

        exploreButton.setOnAction(
                event -> {

                    Stage stage =
                            (Stage) exploreButton
                                    .getScene()
                                    .getWindow();

                    stage.setScene(
                            getExplorerPage()
                    );
                }
        );


        return scene;
    }


    // =========================================================
    // CATEGORY CARD METHOD
    // =========================================================

    public VBox createCategoryCard(
            String imagePath,
            String title,
            String description) {


        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(10)
        );

        card.setPrefWidth(
                220
        );

        card.setPrefHeight(
                220
        );

        card.setAlignment(
                Pos.TOP_LEFT
        );

        card.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #dddddd;"
                + "-fx-border-radius: 15;"
        );


        // =====================================================
        // IMAGE
        // =====================================================

        URL imageURL =
                getClass().getResource(
                        imagePath
                );

        if (imageURL == null) {

            throw new RuntimeException(
                    "Image not found: "
                    + imagePath
            );
        }


        Image image =
                new Image(
                        imageURL.toExternalForm()
                );


        ImageView imageView =
                new ImageView(
                        image
                );

        imageView.setFitWidth(
                198
        );

        imageView.setFitHeight(
                105
        );

        imageView.setPreserveRatio(
                false
        );


        // =====================================================
        // TITLE
        // =====================================================

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        descriptionLabel.setTextFill(
                Color.GRAY
        );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setMaxWidth(
                195
        );


        // =====================================================
        // VIEW BUTTON
        // =====================================================

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        Button viewButton =
                new Button(
                        "View →"
                );

        viewButton.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-text-fill: #075a2b;"
                + "-fx-font-weight: bold;"
        );


        HBox bottomBox =
                new HBox();

        bottomBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        bottomBox.getChildren().addAll(
                space,
                viewButton
        );


        // =====================================================
        // ADD CARD COMPONENTS
        // =====================================================

        card.getChildren().addAll(
                imageView,
                titleLabel,
                descriptionLabel,
                bottomBox
        );


        return card;
    }
}