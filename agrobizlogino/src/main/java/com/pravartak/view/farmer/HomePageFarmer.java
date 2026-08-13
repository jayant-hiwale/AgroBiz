
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomePageFarmer {

    public Scene getHomePageFarmer() {

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane borderPane = new BorderPane();


        // =====================================================
        // TOP HEADER
        // =====================================================

        BorderPane topBorderPane = new BorderPane();

        topBorderPane.setPadding(new Insets(10, 18, 10, 18));

        topBorderPane.setStyle("-fx-background-color: white;-fx-border-color: #eeeeee; -fx-border-width: 0 0 1 0;");


        // =====================================================
        // LOGO - LEFT
        // =====================================================

        Label logo = new Label("AgroBiz Hub");

        logo.setFont(Font.font("Arial",FontWeight.BOLD,21));

        logo.setTextFill(Color.rgb(11, 84, 39));


        // =====================================================
        // NAVIGATION BUTTONS - CENTER
        // =====================================================
        Button exploreButton =new Button("Explorer");
                exploreButton.setOnAction(
        event -> {

            Stage stage =
                    (Stage) exploreButton
                            .getScene()
                            .getWindow();

            ExplorerPage explorerPage =
                    new ExplorerPage();

            stage.setScene(
                    explorerPage.getExplorerPage()
            );
        }
);

        Button marketButton =
                new Button("MarketPlace");

        Button communityButton =
                new Button("Community");

        Button aiButton =
                new Button("AI Advisor");

        Button learningButton =
                new Button("Learning");

        Button schemesButton =
                new Button("Schemes");


        HBox navigationBox =
                new HBox(8);

        navigationBox.setAlignment(Pos.CENTER);

        navigationBox.getChildren().addAll(
                exploreButton,
                marketButton,
                communityButton,
                aiButton,
                learningButton,
                schemesButton
        );


        // =====================================================
        // RIGHT HEADER
        // =====================================================

        Button loginButton =
                new Button("Login");

        loginButton.setPrefHeight(32);


        Label profile =
                new Label("◯");

        profile.setFont(
                Font.font(
                        "Arial",
                        22
                )
        );

        profile.setTextFill(
                Color.DARKGREEN
        );


        Label profileText =
                new Label("Profile");

        profileText.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );


        HBox profileBox =
                new HBox(4);

        profileBox.setAlignment(
                Pos.CENTER
        );

        profileBox.getChildren().addAll(
                profile,
                profileText
        );


        HBox rightHeader =
                new HBox(12);

        rightHeader.setAlignment(
                Pos.CENTER_RIGHT
        );

        rightHeader.getChildren().addAll(loginButton,profileBox);


        // =====================================================
        // ADD HEADER PARTS
        // =====================================================

        topBorderPane.setLeft(logo);

        topBorderPane.setCenter(navigationBox);

        topBorderPane.setRight(rightHeader);


        // =====================================================
        // MAIN VBOX
        // =====================================================

        VBox mainVBox =new VBox(28);

        mainVBox.setPadding(
                new Insets(
                        18,
                        18,
                        30,
                        18
                )
        );

        mainVBox.setFillWidth(true);


        // =====================================================
        // HERO SECTION - STACKPANE
        // =====================================================

        StackPane firstHBox =
                new StackPane();

        firstHBox.setPrefHeight(
                350
        );

        firstHBox.setMinHeight(
                330
        );


        // =====================================================
        // HERO BACKGROUND IMAGE
        // =====================================================

        URL imageURL =
                getClass().getResource(
                        "/image.png"
                );

        if (imageURL == null) {

            throw new RuntimeException(
                    "image.png not found!\n"
                    + "Put it inside:\n"
                    + "src/main/resources/image.png"
            );
        }


        Image farmImage =
                new Image(
                        imageURL.toExternalForm()
                );


        ImageView farmImageView =
                new ImageView(
                        farmImage
                );

        farmImageView.setPreserveRatio(
                false
        );

        farmImageView.fitWidthProperty().bind(
                firstHBox.widthProperty()
        );

        farmImageView.fitHeightProperty().bind(
                firstHBox.heightProperty()
        );


        // =====================================================
        // HERO OVERLAY
        // =====================================================

        Rectangle heroOverlay =
                new Rectangle();

        heroOverlay.setFill(
                Color.rgb(
                        255,
                        255,
                        255,
                        0.58
                )
        );

        heroOverlay.widthProperty().bind(
                firstHBox.widthProperty()
        );

        heroOverlay.heightProperty().bind(
                firstHBox.heightProperty()
        );


        // =====================================================
        // HERO TEXT
        // =====================================================

        Label mainTitle =
                new Label(
                        "Revolutionize Your\n"
                        + "Livestock Management with AI"
                );

        mainTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        38
                )
        );

        mainTitle.setTextFill(
                Color.rgb(
                        5,
                        88,
                        31
                )
        );

        mainTitle.setWrapText(
                true
        );

        mainTitle.setAlignment(
                Pos.CENTER
        );

        mainTitle.setMaxWidth(850);
        // =====================================================
        // HERO DESCRIPTION
        // =====================================================

        Label description =new Label("Empowering modern animal husbandry with data-driven insights,\n"+ "premium market access, and intelligent planning.");

        description.setFont(Font.font("Arial",15)
        );

        description.setTextFill(Color.rgb(45,45,45 ));

        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);


        // =====================================================
        // HERO SEARCH
        // =====================================================

        TextField farmSearch =new TextField();

        farmSearch.setPromptText("Search products, livestock, or farming guides...");
        farmSearch.setPrefWidth(430);
        farmSearch.setPrefHeight(42);

        Button explorerButton =new Button("Explore");

        explorerButton.setPrefHeight(42);
        explorerButton.setPrefWidth(90);

        explorerButton.setStyle(
                "-fx-background-color: #08752b;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 6;"
        );
        explorerButton.setOnAction(
        event -> {

            Stage stage =
                    (Stage) explorerButton
                            .getScene()
                            .getWindow();

            ExplorerPage explorerPage =
                    new ExplorerPage();

            stage.setScene(
                    explorerPage.getExplorerPage()
            );
        }
);


        HBox searchHBox =
                new HBox(8);

        searchHBox.setAlignment(
                Pos.CENTER
        );

        searchHBox.getChildren().addAll(
                farmSearch,
                explorerButton
        );


        // =====================================================
        // HERO CONTENT
        // =====================================================

        VBox heroContent =
                new VBox(15);

        heroContent.setAlignment(
                Pos.CENTER
        );

        heroContent.getChildren().addAll(
                mainTitle,
                description,
                searchHBox
        );


        // =====================================================
        // ADD EVERYTHING TO HERO STACKPANE
        // =====================================================

        firstHBox.getChildren().addAll(
                farmImageView,
                heroOverlay,
                heroContent
        );

        StackPane.setAlignment(
                heroContent,
                Pos.CENTER
        );


        // =====================================================
        // TRENDING SECTION
        // =====================================================

        VBox secondVBox =
                new VBox(14);


        // =====================================================
        // TRENDING HEADER
        // =====================================================

        Label trendingLabel =
                new Label(
                        "Trending Categories"
                );

        trendingLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        trendingLabel.setTextFill(
                Color.rgb(
                        35,
                        35,
                        35
                )
        );


        Region headingSpace =
                new Region();

        HBox.setHgrow(
                headingSpace,
                Priority.ALWAYS
        );


        Button viewButton =
                new Button(
                        "View All"
                );

        viewButton.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-text-fill: #0b5427;"
                + "-fx-font-weight: bold;"
        );
        viewButton.setOnAction(
        event -> {

            Stage stage =
                    (Stage) viewButton
                            .getScene()
                            .getWindow();

            ExplorerPage explorerPage =
                    new ExplorerPage();

            stage.setScene(
                    explorerPage.getExplorerPage()
            );
        }
);


        HBox headingHBox =
                new HBox();

        headingHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        headingHBox.getChildren().addAll(
                trendingLabel,
                headingSpace,
                viewButton
        );


        // =====================================================
        // CATEGORY CARDS
        // =====================================================

        HBox secondHBox =
                new HBox(16);

        secondHBox.setAlignment(
                Pos.CENTER
        );


        VBox poultryBox =
                createFarmBox(
                        "/poltry.png",
                        "Poultry Farming",
                        "Advanced systems for optimal bird health."
                );


        VBox dairyBox =
                createFarmBox(
                        "/Dairy.png",
                        "Dairy Farming",
                        "Modern techniques for sustainable milk production."
                );


        VBox precisionBox =
                createFarmBox(
                        "/any.png",
                        "Precision Dairy",
                        "Data-driven livestock management."
                );


        VBox machineryBox =
                createFarmBox(
                        "/any.png",
                        "Smart Machinery",
                        "Automated equipment for scale."
                );


        secondHBox.getChildren().addAll(
                poultryBox,
                dairyBox,
                precisionBox,
                machineryBox
        );


        secondVBox.getChildren().addAll(
                headingHBox,
                secondHBox
        );


        // =====================================================
        // THIRD SECTION - AI BUSINESS PLAN
        // =====================================================

        HBox thirdHBox =
                new HBox(20);

        thirdHBox.setPadding(
                new Insets(28)
        );

        thirdHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        thirdHBox.setMinHeight(
                135
        );

        thirdHBox.setStyle(
                "-fx-background-color: #075a2b;"
                + "-fx-background-radius: 15;"
        );


        // =====================================================
        // THIRD TEXT
        // =====================================================

        VBox thirdTextVBox =
                new VBox(8);

        thirdTextVBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Label intelligentLabel =
                new Label(
                        "INTELLIGENT PLANNING"
                );

        intelligentLabel.setTextFill(
                Color.LIGHTGREEN
        );

        intelligentLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );


        Label planTitle =
                new Label(
                        "AI Business Plan Generator"
                );

        planTitle.setTextFill(
                Color.WHITE
        );

        planTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );


        Label planDescription =
                new Label(
                        "Leverage predictive analytics and local market data "
                        + "to craft an optimal business strategy for your next "
                        + "herd expansion. Minimize risk, maximize yield."
                );

        planDescription.setTextFill(
                Color.WHITE
        );

        planDescription.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        planDescription.setWrapText(
                true
        );

        planDescription.setMaxWidth(
                700
        );


        thirdTextVBox.getChildren().addAll(
                intelligentLabel,
                planTitle,
                planDescription
        );


        // =====================================================
        // SPACE BETWEEN TEXT AND BUTTON
        // =====================================================

        Region thirdSpace =
                new Region();

        HBox.setHgrow(
                thirdSpace,
                Priority.ALWAYS
        );


        // =====================================================
        // GENERATE PLAN BUTTON - RIGHT
        // =====================================================

        Button generateButton =
                new Button(
                        "Generate Plan  ✨"
                );

        generateButton.setPrefWidth(
                145
        );

        generateButton.setPrefHeight(
                45
        );

        generateButton.setStyle(
                "-fx-background-color: #a8f08c;"
                + "-fx-text-fill: #064d23;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 8;"
        );


        thirdHBox.getChildren().addAll(
                thirdTextVBox,
                thirdSpace,
                generateButton
        );


        // =====================================================
        // ADD CONTENT TO MAIN VBOX
        // =====================================================

        mainVBox.getChildren().addAll(
                firstHBox,
                secondVBox,
                thirdHBox
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
                "-fx-background-color: #f7f7f2;"
        );


        // =====================================================
        // FOOTER - BOTTOM
        // =====================================================

        BorderPane footer =
                new BorderPane();

        footer.setPadding(
                new Insets(
                        18,
                        25,
                        18,
                        25
                )
        );

        footer.setStyle(
                "-fx-background-color: #eeeeea;"
        );


        // =====================================================
        // FOOTER LEFT
        // =====================================================

        VBox footerLeft =
                new VBox(6);


        Label footerLogo =
                new Label(
                        "AgroBiz Hub"
                );

        footerLogo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        footerLogo.setTextFill(
                Color.rgb(
                        11,
                        84,
                        39
                )
        );


        Label footerDescription =
                new Label(
                        "Empowering modern farmers with\n"
                        + "intelligent tools and premium market access."
                );

        footerDescription.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );

        footerDescription.setTextFill(
                Color.GRAY
        );


        Label copyright =
                new Label(
                        "© 2026 AgroBiz Hub. Empowering sustainable agriculture."
                );

        copyright.setFont(
                Font.font(
                        "Arial",
                        9
                )
        );

        copyright.setTextFill(
                Color.GRAY
        );


        footerLeft.getChildren().addAll(
                footerLogo,
                footerDescription,
                copyright
        );


        // =====================================================
        // FOOTER RIGHT
        // =====================================================

        VBox footerRight =
                new VBox(7);

        footerRight.setAlignment(
                Pos.CENTER_RIGHT
        );


        HBox footerLinks1 =
                new HBox(25);

        footerLinks1.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label government =
                new Label("Government Schemes");

        Label farmerCommunity =
                new Label("Farmer Community");

        Label privacy =
                new Label("Privacy Policy");


        footerLinks1.getChildren().addAll(
                government,
                farmerCommunity,
                privacy
        );


        HBox footerLinks2 =
                new HBox(25);

        footerLinks2.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label marketNews =
                new Label("Market News");

        Label terms =
                new Label("Terms of Service");


        footerLinks2.getChildren().addAll(
                marketNews,
                terms
        );


        footerRight.getChildren().addAll(
                footerLinks1,
                footerLinks2
        );


        footer.setLeft(
                footerLeft
        );

        footer.setRight(
                footerRight
        );


        // =====================================================
        // BORDER PANE
        // =====================================================

        borderPane.setTop(
                topBorderPane
        );

        borderPane.setCenter(
                scrollPane
        );

        borderPane.setBottom(
                footer
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


        return scene;
    }


    // =========================================================
    // FARM CATEGORY BOX
    // =========================================================

    public VBox createFarmBox(
            String imagePath,
            String title,
            String description) {


        VBox box =
                new VBox(8);

        box.setPadding(
                new Insets(8)
        );

        box.setPrefWidth(
                230
        );

        box.setMinWidth(
                200
        );

        box.setMaxWidth(
                260
        );

        box.setPrefHeight(
                205
        );

        box.setAlignment(
                Pos.TOP_LEFT
        );

        box.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 12;"
                + "-fx-border-color: #dddddd;"
                + "-fx-border-radius: 12;"
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
                214
        );

        imageView.setFitHeight(
                95
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
                        15
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
                        11
                )
        );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setTextFill(
                Color.GRAY
        );


        // =====================================================
        // BOTTOM ARROW
        // =====================================================

        Region arrowSpace =
                new Region();

        HBox.setHgrow(
                arrowSpace,
                Priority.ALWAYS
        );


        Button arrowButton =
                new Button(
                        "→"
                );

        arrowButton.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-text-fill: #0b5427;"
                + "-fx-font-size: 18px;"
                + "-fx-font-weight: bold;"
        );


        HBox bottomHBox =
                new HBox();

        bottomHBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        bottomHBox.getChildren().add(
                arrowButton
        );


        // =====================================================
        // ADD CARD COMPONENTS
        // =====================================================

        box.getChildren().addAll(
                imageView,
                titleLabel,
                descriptionLabel,
                bottomHBox
        );


        return box;
    }
}

