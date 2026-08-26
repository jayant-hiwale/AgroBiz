package com.pravartak.view.buyer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BuyerAIAdvisorPage {

    private static BorderPane root;

    // Colors
    private static final Color BACKGROUND = Color.rgb(3, 27, 21);
    private static final Color DARK_GREEN = Color.rgb(5, 48, 32);
    private static final Color GREEN = Color.rgb(46, 190, 78);
    private static final Color LIGHT_GREEN = Color.rgb(38, 105, 67);
    private static final Color CARD_GREEN = Color.rgb(7, 55, 38);
    private static final Color INPUT_BACKGROUND = Color.rgb(27, 46, 40);
    private static final Color BORDER = Color.rgb(58, 82, 68);
    private static final Color TEXT = Color.rgb(220, 235, 227);
    private static final Color GREY_TEXT = Color.rgb(175, 195, 184);

    // GET SCENE
    public static Scene getAIAdvisorScene() {
        root = new BorderPane();

        root.setBackground(new Background(new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setTop(createTopBar());
        root.setCenter(createMainContent());

        return new Scene(root, 1368, 768);
    }

    // TOP BAR
    private static HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setPrefHeight(80);
        topBar.setPadding(new Insets(18, 30, 18, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("AgroBiz");
        logo.setTextFill(GREEN);
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label separator = new Label("|");
        separator.setTextFill(Color.rgb(100, 120, 110));
        separator.setFont(Font.font("Arial", 18));

        Label pageName = new Label("AI ADVISOR");
        pageName.setTextFill(GREY_TEXT);
        pageName.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        HBox titleBox = new HBox(15, logo, separator, pageName);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("←   Back to Dashboard");
        backButton.setPrefHeight(46);
        backButton.setPrefWidth(205);
        backButton.setTextFill(TEXT);
        backButton.setFont(Font.font("Arial", 14));
        backButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(25), Insets.EMPTY)));
        backButton.setStyle("-fx-border-color: #7D9185;-fx-border-width: 1;-fx-border-radius: 25;");

        backButton.setOnAction(event -> {
            System.out.println("Back to Buyer Dashboard");
        });

        HBox.setHgrow(titleBox, Priority.ALWAYS);
        topBar.getChildren().addAll(titleBox, backButton);

        return topBar;
    }

    // MAIN CONTENT
    private static VBox createMainContent() {
        VBox content = new VBox();
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(20);
        content.setPadding(new Insets(35, 100, 40, 100));

        Label title = new Label("What are you looking for today?");
        title.setTextFill(TEXT);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 42));

        Label subtitle = new Label("Your AI purchasing assistant. Ask for real-time market prices, discover local farmers, or\nfind bulk deals on specific crops.");
        subtitle.setTextFill(GREY_TEXT);
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        subtitle.setAlignment(Pos.CENTER);

        HBox questionArea = createQuestionArea();
        HBox quickQuestions = createQuickQuestions();

        content.getChildren().addAll(title, subtitle, questionArea, quickQuestions);

        return content;
    }

    // QUESTION AREA
    private static HBox createQuestionArea() {
        HBox questionArea = new HBox();
        questionArea.setPrefHeight(65);
        questionArea.setMaxWidth(950);
        questionArea.setAlignment(Pos.CENTER_LEFT);
        questionArea.setPadding(new Insets(5, 8, 5, 20));
        questionArea.setBackground(new Background(new BackgroundFill(INPUT_BACKGROUND, new CornerRadii(35), Insets.EMPTY)));

        Label icon = new Label("♙");
        icon.setTextFill(GREEN);
        icon.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        TextArea questionBox = new TextArea();
        questionBox.setPromptText("e.g., Show me low-cost mushrooms near me...");
        questionBox.setWrapText(true);
        questionBox.setPrefHeight(55);
        questionBox.setBackground(Background.EMPTY);
        questionBox.setStyle("-fx-text-fill: #DCEBE3;-fx-prompt-text-fill: #B0C3B8;-fx-background-color: transparent;-fx-border-color: transparent;");

        HBox.setHgrow(questionBox, Priority.ALWAYS);

        Button sendButton = new Button("➤");
        sendButton.setPrefWidth(52);
        sendButton.setPrefHeight(52);
        sendButton.setTextFill(Color.rgb(2, 50, 25));
        sendButton.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        sendButton.setBackground(new Background(new BackgroundFill(GREEN, new CornerRadii(30), Insets.EMPTY)));

        sendButton.setOnAction(event -> {
            String question = questionBox.getText().trim();

            if (question.isEmpty()) {
                questionBox.setPromptText("Please enter what you want to buy...");
            } else {
                showAIResult(question);
            }
        });

        questionArea.getChildren().addAll(icon, questionBox, sendButton);

        return questionArea;
    }

    // QUICK QUESTIONS
    private static HBox createQuickQuestions() {
        HBox quickQuestions = new HBox();
        quickQuestions.setSpacing(15);
        quickQuestions.setAlignment(Pos.CENTER);

        quickQuestions.getChildren().addAll(
                createQuickButton("⌁", "Low-cost\nFruits", "Show me low-cost fruits"),
                createQuickButton("⌖", "Nearby\nPoultry", "Find poultry suppliers near me"),
                createQuickButton("▣", "Bulk\nMushrooms", "Show me bulk mushrooms"),
                createQuickButton("✥", "Recently\nUploaded", "Show recently uploaded products")
        );

        return quickQuestions;
    }

    // QUICK BUTTON
    private static Button createQuickButton(String icon, String text, String question) {
        Button button = new Button(icon + "   " + text);
        button.setPrefHeight(48);
        button.setPrefWidth(205);
        button.setTextFill(GREEN);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        button.setWrapText(true);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(7, 48, 32), new CornerRadii(25), Insets.EMPTY)));
        button.setStyle("-fx-border-color: #277541;-fx-border-width: 1;-fx-border-radius: 25;");

        button.setOnAction(event -> {
            showAIResult(question);
        });

        return button;
    }

    // AI RESULT
    private static void showAIResult(String question) {
        VBox content = new VBox();
        content.setSpacing(15);
        content.setPadding(new Insets(15, 180, 30, 180));

        HBox userBox = new HBox();
        userBox.setAlignment(Pos.CENTER_RIGHT);

        Label userQuestion = new Label(question);
        userQuestion.setTextFill(TEXT);
        userQuestion.setFont(Font.font("Arial", 15));
        userQuestion.setPadding(new Insets(18));
        userQuestion.setBackground(new Background(new BackgroundFill(Color.rgb(44, 62, 56), new CornerRadii(15), Insets.EMPTY)));

        userBox.getChildren().add(userQuestion);

        VBox responseCard = createAIResponseCard();

        content.getChildren().addAll(userBox, responseCard);

        root.setCenter(content);
    }

    // AI RESPONSE CARD
    private static VBox createAIResponseCard() {
        VBox responseCard = new VBox();
        responseCard.setSpacing(15);
        responseCard.setPadding(new Insets(22));
        responseCard.setMaxWidth(1000);
        responseCard.setBackground(new Background(new BackgroundFill(CARD_GREEN, new CornerRadii(15), Insets.EMPTY)));
        responseCard.setStyle("-fx-border-color: #17683C;-fx-border-width: 1;-fx-border-radius: 15;");

        HBox heading = new HBox();
        heading.setSpacing(12);
        heading.setAlignment(Pos.CENTER_LEFT);

        Label aiIcon = new Label("♙");
        aiIcon.setTextFill(GREEN);
        aiIcon.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        Label responseTitle = new Label("I found several options for bulk chicken near you.");
        responseTitle.setTextFill(TEXT);
        responseTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        heading.getChildren().addAll(aiIcon, responseTitle);

        Label description = new Label("Based on your location, I've analyzed 14 local suppliers. The current market average is $2.80/lb. Here are the best bulk deals within a 50-mile radius, prioritizing those below the average price.");
        description.setTextFill(GREY_TEXT);
        description.setFont(Font.font("Arial", 14));
        description.setWrapText(true);

        HBox marketTrend = createMarketTrend();

        HBox suppliers = new HBox();
        suppliers.setSpacing(15);

        suppliers.getChildren().addAll(
                createSupplierCard("Oak Ridge Farms", "$2.15/lb", "12 miles away", "Lowest Price", "Min. Order: 500 lbs"),
                createSupplierCard("Valley Poultry Co.", "$2.40/lb", "28 miles away", "Top Rated", "Min. Order: 250 lbs")
        );

        HBox actions = new HBox();
        actions.setSpacing(12);

        Button filterButton = new Button("☷   Filter by Organic only");
        Button negotiateButton = new Button("◉   Negotiate Price");

        setupActionButton(filterButton);
        setupActionButton(negotiateButton);

        actions.getChildren().addAll(filterButton, negotiateButton);

        responseCard.getChildren().addAll(heading, description, marketTrend, suppliers, actions);

        return responseCard;
    }

    // MARKET TREND
    private static HBox createMarketTrend() {
        HBox marketTrend = new HBox();
        marketTrend.setPrefHeight(75);
        marketTrend.setPadding(new Insets(12));
        marketTrend.setAlignment(Pos.CENTER_LEFT);
        marketTrend.setBackground(new Background(new BackgroundFill(Color.rgb(10, 31, 26), new CornerRadii(12), Insets.EMPTY)));

        VBox marketInfo = new VBox();
        marketInfo.setSpacing(3);

        Label marketLabel = new Label("MARKET TREND");
        marketLabel.setTextFill(GREY_TEXT);
        marketLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Label productName = new Label("Bulk Chicken (Whole)");
        productName.setTextFill(TEXT);
        productName.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        marketInfo.getChildren().addAll(marketLabel, productName);

        HBox.setHgrow(marketInfo, Priority.ALWAYS);

        VBox price = new VBox();
        price.setAlignment(Pos.CENTER_RIGHT);

        Label average = new Label("Avg Price");
        average.setTextFill(GREY_TEXT);
        average.setFont(Font.font("Arial", 11));

        Label priceValue = new Label("$2.80/lb");
        priceValue.setTextFill(Color.rgb(145, 230, 40));
        priceValue.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        price.getChildren().addAll(average, priceValue);

        marketTrend.getChildren().addAll(marketInfo, price);

        return marketTrend;
    }

    // SUPPLIER CARD
    private static VBox createSupplierCard(String name, String price, String distance, String badge, String minimumOrder) {
        VBox card = new VBox();
        card.setSpacing(8);
        card.setPadding(new Insets(15));
        card.setPrefWidth(420);
        card.setBackground(new Background(new BackgroundFill(Color.rgb(15, 38, 32), new CornerRadii(12), Insets.EMPTY)));
        card.setStyle("-fx-border-color: #315344;-fx-border-width: 1;-fx-border-radius: 12;");

        Label image = new Label("FARM PRODUCT IMAGE");
        image.setAlignment(Pos.CENTER);
        image.setPrefHeight(105);
        image.setMaxWidth(Double.MAX_VALUE);
        image.setTextFill(GREY_TEXT);
        image.setBackground(new Background(new BackgroundFill(Color.rgb(33, 57, 50), CornerRadii.EMPTY, Insets.EMPTY)));

        Label badgeLabel = new Label("★ " + badge);
        badgeLabel.setTextFill(GREEN);
        badgeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        HBox namePrice = new HBox();
        namePrice.setAlignment(Pos.CENTER_LEFT);

        Label farmName = new Label(name);
        farmName.setTextFill(TEXT);
        farmName.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox.setHgrow(farmName, Priority.ALWAYS);

        Label productPrice = new Label(price);
        productPrice.setTextFill(GREEN);
        productPrice.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        namePrice.getChildren().addAll(farmName, productPrice);

        Label distanceLabel = new Label("⌖  " + distance);
        distanceLabel.setTextFill(GREY_TEXT);
        distanceLabel.setFont(Font.font("Arial", 12));

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_LEFT);
        bottom.setPadding(new Insets(8, 0, 0, 0));

        Label minimum = new Label(minimumOrder);
        minimum.setTextFill(GREY_TEXT);
        minimum.setFont(Font.font("Arial", 12));

        HBox.setHgrow(minimum, Priority.ALWAYS);

        Button viewDeal = new Button("View Deal");
        viewDeal.setTextFill(TEXT);
        viewDeal.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        viewDeal.setBackground(new Background(new BackgroundFill(Color.rgb(40, 65, 57), new CornerRadii(8), Insets.EMPTY)));

        bottom.getChildren().addAll(minimum, viewDeal);

        card.getChildren().addAll(image, badgeLabel, namePrice, distanceLabel, bottom);

        return card;
    }

    // ACTION BUTTON
    private static void setupActionButton(Button button) {
        button.setPrefHeight(40);
        button.setTextFill(TEXT);
        button.setFont(Font.font("Arial", 13));
        button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(8), Insets.EMPTY)));
        button.setStyle("-fx-border-color: #708579;-fx-border-width: 1;-fx-border-radius: 8;");
    }
}