package com.pravartak.view.farmer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SchemesPage {

    // =========================================================
    // STAGE
    // =========================================================

    private Stage mainStage;

    // Scene of this page
    private Scene schemesScene;


    // =========================================================
    // COLORS
    // =========================================================

    private final Color darkGreen =
            Color.rgb(0, 82, 28);

    private final Color mainGreen =
            Color.rgb(0, 105, 35);

    private final Color pageBackground =
            Color.rgb(250, 249, 246);

    private final Color textColor =
            Color.rgb(25, 35, 30);

    private final Color grayText =
            Color.rgb(90, 95, 90);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SchemesPage(Stage mainStage) {

        this.mainStage = mainStage;

        createSchemesPage();
    }


    // =========================================================
    // CREATE PAGE
    // =========================================================

    private void createSchemesPage() {

        BorderPane mainLayout =
                new BorderPane();


        // -----------------------------------------------------
        // PAGE BACKGROUND
        // -----------------------------------------------------

        mainLayout.setBackground(
                new Background(
                        new BackgroundFill(
                                pageBackground,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // -----------------------------------------------------
        // TOP NAVIGATION
        // -----------------------------------------------------

        HBox topNavigation =
                createTopNavigation();

        mainLayout.setTop(
                topNavigation
        );


        // -----------------------------------------------------
        // MAIN CONTENT
        // -----------------------------------------------------

        VBox mainContent =
                createMainContent();

        mainLayout.setCenter(
                mainContent
        );


        // -----------------------------------------------------
        // FOOTER
        // -----------------------------------------------------

        HBox footer =
                createFooter();

        mainLayout.setBottom(
                footer
        );


        // -----------------------------------------------------
        // SCENE
        // -----------------------------------------------------

        schemesScene =
                new Scene(
                        mainLayout,
                        1368,
                        768
                );
    }


    // =========================================================
    // TOP NAVIGATION
    // =========================================================

    private HBox createTopNavigation() {

        HBox topNavigation =
                new HBox();

        topNavigation.setAlignment(
                Pos.CENTER_LEFT
        );

        topNavigation.setPadding(
                new Insets(
                        8,
                        18,
                        8,
                        18
                )
        );

        topNavigation.setSpacing(20);

        topNavigation.setPrefHeight(52);


        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        Label logo =
                new Label(
                        "AgriBiz Hub"
                );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        logo.setTextFill(
                darkGreen
        );


        // -----------------------------------------------------
        // NAVIGATION BUTTONS
        // -----------------------------------------------------

        Button explorerButton =
                createNavigationButton(
                        "Explorer"
                );

        Button marketplaceButton =
                createNavigationButton(
                        "Marketplace"
                );

        Button schemesButton =
                createNavigationButton(
                        "Schemes"
                );

        Button newsButton =
                createNavigationButton(
                        "News"
                );

        Button communityButton =
                createNavigationButton(
                        "Community"
                );


        // Highlight Schemes
        schemesButton.setTextFill(
                mainGreen
        );

        schemesButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );


        // -----------------------------------------------------
        // NAVIGATION ACTIONS
        // -----------------------------------------------------

        explorerButton.setOnAction(
                event -> showMessage(
                        "Explorer",
                        "Explorer page will be added later."
                )
        );


        marketplaceButton.setOnAction(
                event -> showMessage(
                        "Marketplace",
                        "Marketplace page will be added later."
                )
        );


        schemesButton.setOnAction(
                event -> {
                    // Already on Schemes Page
                }
        );


        newsButton.setOnAction(
                event -> showMessage(
                        "News",
                        "News page will be added later."
                )
        );


        communityButton.setOnAction(
                event -> showMessage(
                        "Community",
                        "Community page will be added later."
                )
        );


        // -----------------------------------------------------
         // SEARCH FIELD
         // -----------------------------------------------------

        // TextField searchField =
        //         new TextField();

        // searchField.setPromptText(
        //         "Search schemes..."
        // );

        // searchField.setPrefWidth(180);

        // searchField.setPrefHeight(32);

        // searchField.setFont(
        //         Font.font(
        //                 "Arial",
        //                 11
        //         )
        // );

        // searchField.setStyle(
        //         "-fx-background-color: #F4F4F2;"
        //         + "-fx-background-radius: 18;"
        //         + "-fx-border-color: #DDDDD8;"
        //         + "-fx-border-radius: 18;"
        //         + "-fx-padding: 0 12 0 12;"
        // );


        // -----------------------------------------------------
        // SPACE
        // -----------------------------------------------------

        Region navigationSpace =
                new Region();

        HBox.setHgrow(
                navigationSpace,
                Priority.ALWAYS
        );


        // -----------------------------------------------------
        // NOTIFICATION BUTTON
        // -----------------------------------------------------

        Button notificationButton =
                createSmallButton(
                        "♧"
                );

        notificationButton.setOnAction(
                event -> showMessage(
                        "Notifications",
                        "You have no new notifications."
                )
        );


        // -----------------------------------------------------
        // PROFILE BUTTON
        // -----------------------------------------------------

        Button profileButton =
                createSmallButton(
                        "◎"
                );

        profileButton.setOnAction(
                event -> showMessage(
                        "Profile",
                        "Profile page will be added later."
                )
        );


        // -----------------------------------------------------
        // LOGIN BUTTON
        // -----------------------------------------------------

        Button loginButton =
                new Button(
                        "Login"
                );

        loginButton.setPrefWidth(62);

        loginButton.setPrefHeight(30);

        loginButton.setCursor(
                Cursor.HAND
        );

        loginButton.setTextFill(
                Color.WHITE
        );

        loginButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        loginButton.setBackground(
                new Background(
                        new BackgroundFill(
                                darkGreen,
                                new CornerRadii(5),
                                Insets.EMPTY
                        )
                )
        );


        loginButton.setOnAction(
                event -> showMessage(
                        "Login",
                        "You are already logged in."
                )
        );


        // -----------------------------------------------------
        // ADD ALL
        // -----------------------------------------------------

        topNavigation.getChildren().addAll(

                logo,

                explorerButton,
                marketplaceButton,
                schemesButton,
                newsButton,
                communityButton,

                navigationSpace,

               // searchField,

                notificationButton,
                profileButton,
                loginButton
        );


        return topNavigation;
    }


    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private VBox createMainContent() {

        VBox mainContent =
                new VBox();

        mainContent.setSpacing(16);

        mainContent.setPadding(
                new Insets(
                        25,
                        35,
                        25,
                        35
                )
        );


        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        Label pageTitle =
                new Label(
                        "Government & Industry Schemes"
                );

        pageTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        26
                )
        );

        pageTitle.setTextFill(
                darkGreen
        );


        // -----------------------------------------------------
        // DESCRIPTION
        // -----------------------------------------------------

        Label pageDescription =
                new Label(
                        "Explore available programs, subsidies, and insurance "
                        + "schemes designed to support sustainable agriculture "
                        + "and animal husbandry. Discover opportunities to "
                        + "enhance your farming operations."
                );

        pageDescription.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        pageDescription.setTextFill(
                grayText
        );

        pageDescription.setWrapText(true);

        pageDescription.setMaxWidth(900);


        // -----------------------------------------------------
        // CATEGORY BUTTONS
        // -----------------------------------------------------

        HBox categoryButtons =
                createCategoryButtons();


        // -----------------------------------------------------
        // SCHEME CARDS
        // -----------------------------------------------------

        HBox schemeCards =
                createSchemeCards();


        // -----------------------------------------------------
        // ADD
        // -----------------------------------------------------

        mainContent.getChildren().addAll(

                pageTitle,

                pageDescription,

                categoryButtons,

                schemeCards
        );


        return mainContent;
    }


    // =========================================================
    // CATEGORY BUTTONS
    // =========================================================

    private HBox createCategoryButtons() {

        HBox categoryBox =
                new HBox();

        categoryBox.setSpacing(10);

        categoryBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Button allSchemesButton =
                createCategoryButton(
                        "All Schemes",
                        true
                );


        Button animalButton =
                createCategoryButton(
                        "Animal Husbandry",
                        false
                );


        Button equipmentButton =
                createCategoryButton(
                        "Equipment Subsidy",
                        false
                );


        Button insuranceButton =
                createCategoryButton(
                        "Livestock Insurance",
                        false
                );


        // -----------------------------------------------------
        // BUTTON ACTIONS
        // -----------------------------------------------------

        allSchemesButton.setOnAction(
                event -> showMessage(
                        "All Schemes",
                        "Showing all available schemes."
                )
        );


        animalButton.setOnAction(
                event -> showMessage(
                        "Animal Husbandry",
                        "Animal husbandry schemes selected."
                )
        );


        equipmentButton.setOnAction(
                event -> showMessage(
                        "Equipment Subsidy",
                        "Equipment subsidy schemes selected."
                )
        );


        insuranceButton.setOnAction(
                event -> showMessage(
                        "Livestock Insurance",
                        "Livestock insurance schemes selected."
                )
        );


        categoryBox.getChildren().addAll(

                allSchemesButton,
                animalButton,
                equipmentButton,
                insuranceButton
        );


        return categoryBox;
    }


    // =========================================================
    // SCHEME CARDS
    // =========================================================

    private HBox createSchemeCards() {

        HBox schemeCards =
                new HBox();

        schemeCards.setSpacing(18);

        schemeCards.setAlignment(
                Pos.TOP_LEFT
        );


        // -----------------------------------------------------
        // CARD 1
        // -----------------------------------------------------

        VBox livestockCard =
                createSchemeCard(

                        "🐄",

                        "National Livestock\nMission (NLM)",

                        "Government support for livestock "
                        + "entrepreneurship, breed improvement "
                        + "and animal productivity.",

                        "Eligibility",

                        "• Individual farmers\n"
                        + "• Farmer Producer Organisations\n"
                        + "• Eligible livestock entrepreneurs",

                        "Check Eligibility",

                        true
                );


        // -----------------------------------------------------
        // CARD 2
        // -----------------------------------------------------

        VBox machineryCard =
                createSchemeCard(

                        "⚙",

                        "Sub-Mission on\nAgricultural\nMechanization (SMAM)",

                        "Financial assistance for agricultural "
                        + "machinery and modern farm equipment.",

                        "Eligibility",

                        "• Farmers\n"
                        + "• Farmer groups\n"
                        + "• Registered agricultural organisations",

                        "Apply Now",

                        false
                );


        // -----------------------------------------------------
        // CARD 3
        // -----------------------------------------------------

        VBox cropInsuranceCard =
                createSchemeCard(

                        "🛡",

                        "Pradhan Mantri\nFasal Bima Yojana",

                        "Crop insurance support designed "
                        + "to protect farmers against eligible "
                        + "crop losses and risks.",

                        "Eligibility",

                        "• Eligible farmers\n"
                        + "• Farmers growing notified crops\n"
                        + "• Applicable geographical areas",

                        "Check Eligibility",

                        true
                );


        // -----------------------------------------------------
        // ADD CARDS
        // -----------------------------------------------------

        schemeCards.getChildren().addAll(

                livestockCard,

                machineryCard,

                cropInsuranceCard
        );


        return schemeCards;
    }


    // =========================================================
    // CREATE SCHEME CARD
    // =========================================================

    private VBox createSchemeCard(

            String icon,

            String schemeName,

            String description,

            String eligibilityTitle,

            String eligibilityDetails,

            String actionText,

            boolean greenButton) {


        VBox schemeCard =
                new VBox();


        schemeCard.setSpacing(10);

        schemeCard.setPadding(
                new Insets(14)
        );

        schemeCard.setPrefWidth(350);

        schemeCard.setMinWidth(300);

        schemeCard.setMaxWidth(350);


        // -----------------------------------------------------
        // CARD STYLE
        // -----------------------------------------------------

        schemeCard.setStyle(

                "-fx-background-color: white;"
                + "-fx-background-radius: 12;"
                + "-fx-border-color: #E0E0DB;"
                + "-fx-border-radius: 12;"
        );


        // -----------------------------------------------------
        // TOP ROW
        // -----------------------------------------------------

        HBox topRow =
                new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefSize(
                42,
                38
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        iconLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        255,
                                        225,
                                        215
                                ),
                                new CornerRadii(6),
                                Insets.EMPTY
                        )
                )
        );


        Region badgeSpace =
                new Region();

        HBox.setHgrow(
                badgeSpace,
                Priority.ALWAYS
        );


        Label schemeBadge =
                new Label(
                        getBadgeText(schemeName)
                );

        schemeBadge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        8
                )
        );

        schemeBadge.setTextFill(
                Color.rgb(
                        80,
                        85,
                        80
                )
        );

        schemeBadge.setPadding(
                new Insets(
                        4,
                        8,
                        4,
                        8
                )
        );

        schemeBadge.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        235,
                                        235,
                                        231
                                ),
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );


        topRow.getChildren().addAll(

                iconLabel,

                badgeSpace,

                schemeBadge
        );


        // -----------------------------------------------------
        // SCHEME NAME
        // -----------------------------------------------------

        Label nameLabel =
                new Label(
                        schemeName
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        nameLabel.setTextFill(
                textColor
        );

        nameLabel.setWrapText(true);


        // -----------------------------------------------------
        // DESCRIPTION
        // -----------------------------------------------------

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

        descriptionLabel.setTextFill(
                grayText
        );

        descriptionLabel.setWrapText(true);

        descriptionLabel.setMinHeight(62);


        // -----------------------------------------------------
        // ELIGIBILITY BOX
        // -----------------------------------------------------

        VBox eligibilityBox =
                new VBox();

        eligibilityBox.setSpacing(6);

        eligibilityBox.setPadding(
                new Insets(10)
        );

        eligibilityBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        246,
                                        246,
                                        243
                                ),
                                new CornerRadii(7),
                                Insets.EMPTY
                        )
                )
        );


        Label eligibilityHeading =
                new Label(
                        "ⓘ  " + eligibilityTitle
                );

        eligibilityHeading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        eligibilityHeading.setTextFill(
                darkGreen
        );


        Label eligibilityLabel =
                new Label(
                        eligibilityDetails
                );

        eligibilityLabel.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );

        eligibilityLabel.setTextFill(
                grayText
        );

        eligibilityLabel.setWrapText(true);


        eligibilityBox.getChildren().addAll(

                eligibilityHeading,

                eligibilityLabel
        );


        // -----------------------------------------------------
        // ACTION BUTTON
        // -----------------------------------------------------

        Button actionButton =
                new Button(
                        actionText
                );

        actionButton.setPrefHeight(34);

        actionButton.setMaxWidth(
                Double.MAX_VALUE
        );

        actionButton.setCursor(
                Cursor.HAND
        );

        actionButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );


        // -----------------------------------------------------
        // GREEN BUTTON
        // -----------------------------------------------------

        if (greenButton) {

            actionButton.setTextFill(
                    Color.WHITE
            );

            actionButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    darkGreen,
                                    new CornerRadii(5),
                                    Insets.EMPTY
                            )
                    )
            );

        }

        // -----------------------------------------------------
        // OUTLINE BUTTON
        // -----------------------------------------------------

        else {

            actionButton.setTextFill(
                    darkGreen
            );

            actionButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.WHITE,
                                    new CornerRadii(5),
                                    Insets.EMPTY
                            )
                    )
            );

            actionButton.setBorder(
                    new Border(
                            new BorderStroke(
                                    Color.rgb(
                                            150,
                                            105,
                                            90
                                    ),
                                    BorderStrokeStyle.SOLID,
                                    new CornerRadii(5),
                                    new BorderWidths(1)
                            )
                    )
            );
        }


        // -----------------------------------------------------
        // BUTTON ACTION
        // -----------------------------------------------------

        actionButton.setOnAction(
                event -> showMessage(
                        schemeName.replace(
                                "\n",
                                " "
                        ),
                        "Scheme details and application "
                        + "page will be connected later."
                )
        );


        // -----------------------------------------------------
        // ADD CARD COMPONENTS
        // -----------------------------------------------------

        schemeCard.getChildren().addAll(

                topRow,

                nameLabel,

                descriptionLabel,

                eligibilityBox,

                actionButton
        );


        return schemeCard;
    }


    // =========================================================
    // BADGE
    // =========================================================

    private String getBadgeText(
            String schemeName) {

        if (schemeName.contains(
                "Mechanization")) {

            return "RECOMMENDED";
        }

        return "CENTRAL GOVT";
    }


    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private Button createNavigationButton(
            String buttonText) {

        Button navigationButton =
                new Button(
                        buttonText
                );

        navigationButton.setBackground(
                Background.EMPTY
        );

        navigationButton.setBorder(
                Border.EMPTY
        );

        navigationButton.setTextFill(
                Color.rgb(
                        65,
                        70,
                        65
                )
        );

        navigationButton.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        navigationButton.setCursor(
                Cursor.HAND
        );

        return navigationButton;
    }


    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private Button createSmallButton(
            String icon) {

        Button smallButton =
                new Button(
                        icon
                );

        smallButton.setPrefSize(
                30,
                30
        );

        smallButton.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        smallButton.setBackground(
                Background.EMPTY
        );

        smallButton.setBorder(
                Border.EMPTY
        );

        smallButton.setTextFill(
                darkGreen
        );

        smallButton.setCursor(
                Cursor.HAND
        );

        return smallButton;
    }


    // =========================================================
    // CATEGORY BUTTON
    // =========================================================

    private Button createCategoryButton(

            String buttonText,

            boolean selected) {


        Button categoryButton =
                new Button(
                        buttonText
                );

        categoryButton.setPrefHeight(
                30
        );

        categoryButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        categoryButton.setCursor(
                Cursor.HAND
        );


        // -----------------------------------------------------
        // SELECTED BUTTON
        // -----------------------------------------------------

        if (selected) {

            categoryButton.setTextFill(
                    Color.WHITE
            );

            categoryButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    darkGreen,
                                    new CornerRadii(15),
                                    Insets.EMPTY
                            )
                    )
            );
        }


        // -----------------------------------------------------
        // NORMAL BUTTON
        // -----------------------------------------------------

        else {

            categoryButton.setTextFill(
                    Color.rgb(
                            65,
                            70,
                            65
                    )
            );

            categoryButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.WHITE,
                                    new CornerRadii(15),
                                    Insets.EMPTY
                            )
                    )
            );

            categoryButton.setBorder(
                    new Border(
                            new BorderStroke(
                                    Color.rgb(
                                            190,
                                            190,
                                            185
                                    ),
                                    BorderStrokeStyle.SOLID,
                                    new CornerRadii(15),
                                    new BorderWidths(1)
                            )
                    )
            );
        }


        return categoryButton;
    }


    // =========================================================
    // FOOTER
    // =========================================================

    private HBox createFooter() {

        HBox footer =
                new HBox();

        footer.setPadding(
                new Insets(
                        18,
                        25,
                        18,
                        25
                )
        );

        footer.setSpacing(70);

        footer.setAlignment(
                Pos.TOP_LEFT
        );

        footer.setPrefHeight(
                100
        );

        footer.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        235,
                                        235,
                                        233
                                ),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // -----------------------------------------------------
        // BRAND
        // -----------------------------------------------------

        VBox brandColumn =
                new VBox();

        brandColumn.setSpacing(5);


        Label brandName =
                new Label(
                        "AgriBiz Hub"
                );

        brandName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        brandName.setTextFill(
                darkGreen
        );


        Label brandDescription =
                new Label(
                        "Empowering Sustainable Agriculture."
                );

        brandDescription.setFont(
                Font.font(
                        "Arial",
                        9
                )
        );

        brandDescription.setTextFill(
                grayText
        );


        Label copyright =
                new Label(
                        "© 2024 AgriBiz Hub."
                );

        copyright.setFont(
                Font.font(
                        "Arial",
                        9
                )
        );

        copyright.setTextFill(
                grayText
        );


        brandColumn.getChildren().addAll(

                brandName,

                brandDescription,

                copyright
        );


        // -----------------------------------------------------
        // OTHER FOOTER COLUMNS
        // -----------------------------------------------------

        VBox governmentColumn =
                createFooterColumn(
                        "Government Schemes",
                        "Market News"
                );


        VBox farmerColumn =
                createFooterColumn(
                        "Farmer Community",
                        "Terms of Service"
                );


        VBox privacyColumn =
                createFooterColumn(
                        "Privacy Policy"
                );


        // -----------------------------------------------------
        // ADD FOOTER
        // -----------------------------------------------------

        footer.getChildren().addAll(

                brandColumn,

                governmentColumn,

                farmerColumn,

                privacyColumn
        );


        return footer;
    }


    // =========================================================
    // FOOTER COLUMN
    // =========================================================

    private VBox createFooterColumn(
            String... footerItems) {

        VBox footerColumn =
                new VBox();

        footerColumn.setSpacing(8);


        for (String item : footerItems) {

            Label footerLabel =
                    new Label(
                            item
                    );

            footerLabel.setFont(
                    Font.font(
                            "Arial",
                            9
                    )
            );

            footerLabel.setTextFill(
                    Color.rgb(
                            70,
                            75,
                            70
                    ));


            footerColumn.getChildren().add(
                    footerLabel
            );
        }


        return footerColumn;
    }


    // =========================================================
    // SHOW MESSAGE
    // =========================================================

    private void showMessage(

            String title,

            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // =========================================================
    // GET SCENE
    // =========================================================

    public Scene getScene() {

        return schemesScene;
    }
}