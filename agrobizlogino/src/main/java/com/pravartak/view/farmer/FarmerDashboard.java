
package com.pravartak.view.farmer;

import java.net.URL;

import com.pravartak.view.login.LoginPage;
import javafx.scene.control.ProgressBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FarmerDashboard {

    // LOGOUT CALLBACK
    private final Runnable logoutAction;

    // COLORS
    private final Color DARK_GREEN = Color.rgb(14, 35, 16);
    private static final Color GREEN = Color.DARKGREEN;
    private final Color LIGHT_GREEN = Color.rgb(186, 209, 174);
    private final Color CREAM = Color.rgb(11, 16, 16);
    private final Color DARK_TEXT = Color.rgb(247, 247, 250);
    private final Color GREY = Color.rgb(105, 110, 105);
    private final Color CARD_BACKGROUND = Color.rgb(0,100,0);
    private final Color BORDER_COLOR = Color.rgb(225, 230, 220);

    // SIDEBAR BUTTONS
    private Button homepageButton;
    private Button dashboardButton;
    private Button profileButton;
    private Button aiAdvisorButton;
    private Button learningButton;
    private Button wishlistButton;
    private Button investmentButton;
    private Button schemesButton;

    // MAIN BORDER PANE
    private BorderPane root;

    // CONSTRUCTOR
    public FarmerDashboard(Runnable logoutAction) {
        this.logoutAction = logoutAction;
    }

    // SCENE
    public Scene getDashboardScene() {
        root = new BorderPane();
        root.setPrefSize(1368, 768);

        // LEFT SIDEBAR
        VBox sidebar = createSidebar();
        root.setLeft(sidebar);

        // DEFAULT PAGE
        root.setCenter(createDashboardPage());

        return new Scene(root, 1368, 768);
    }

    // SIDEBAR
    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(300);
        sidebar.setMinWidth(300);
        sidebar.setMaxWidth(300);
        sidebar.setPadding(new Insets(25, 20, 20, 20));
        sidebar.setSpacing(7);
        //sidebar.setBackground(new Background(new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        sidebar.setBackground(new Background(new BackgroundFill(Color.web("#080c0d"), CornerRadii.EMPTY, Insets.EMPTY)));
        // LOGO
        Label logo = new Label("🌱  Agro Biz");
        logo.setTextFill(Color.WHITE);
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        sidebar.getChildren().add(logo);
        sidebar.getChildren().add(createSpace(30));

        // MENU TITLE
        Label menu = new Label("FARMER MENU");
        menu.setTextFill(Color.rgb(175, 210, 175));
        menu.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        menu.setPadding(new Insets(0, 0, 8, 15));
        sidebar.getChildren().add(menu);

        
        // HOMEPAGE
        homepageButton = createMenuButton("⌂", "Home");

        homepageButton.setOnAction(event -> {
            HomePageFarmer homePageFarmer = new HomePageFarmer();
            LoginPage.mainStage.setScene(homePageFarmer.getHomePageFarmer());
        });

        // DASHBOARD
        dashboardButton = createMenuButton("⌂", "Dashboard");
        dashboardButton.setOnAction(event -> showPage("dashboard"));

        // PROFILE
        profileButton = createMenuButton("♟", "Profile");
        profileButton.setOnAction(event -> showPage("profile"));

        // AI FARMING ADVISOR
        aiAdvisorButton = createMenuButton("✦", "AI Farming Advisor");
        aiAdvisorButton.setOnAction(event -> showPage("ai"));

        // MY LEARNING
        learningButton = createMenuButton("▣", "My Learning");
        learningButton.setOnAction(event -> showPage("learning"));

        // WISHLIST
        wishlistButton = createMenuButton("♙", "Wishlist");
        wishlistButton.setOnAction(event -> showPage("wishlist"));

        // INVESTMENT
        investmentButton = createMenuButton("₹", "Investment Calculator");
        investmentButton.setOnAction(event -> showPage("investment"));

        // SCHEMES
        schemesButton = createMenuButton("◇", "Schemes & Subsidies");
        schemesButton.setOnAction(event -> showPage("schemes"));

        // ADD BUTTONS
        sidebar.getChildren().addAll(homepageButton, dashboardButton, profileButton, aiAdvisorButton, learningButton, wishlistButton, investmentButton, schemesButton);

        // SPACER
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        // LOGOUT
        Button logout = new Button(
                                "⇥   Logout");

                logout.setMaxWidth(
                                Double.MAX_VALUE);

                logout.setAlignment(
                                Pos.CENTER_LEFT);

                logout.setPadding(
                                new Insets(
                                                12,
                                                15,
                                                12,
                                                15));

                logout.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#E57373;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-cursor:hand;");
         logout.setOnAction(e -> {
                        try {
                                LoginPage loginPage = new LoginPage();
                                loginPage.start(LoginPage.mainStage);
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                });
                 logout.setOnMouseEntered(event -> {

                        logout.setStyle(
                                        "-fx-background-color:#633333;" +
                                                        "-fx-text-fill:#E57373;" +
                                                        "-fx-font-size:14px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                });

                logout.setOnMouseExited(event -> {

                        logout.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-text-fill:#E57373;" +
                                                        "-fx-font-size:14px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                });


        sidebar.getChildren().add(logout);

        // Dashboard selected by default
        setSelectedMenuButton(dashboardButton);

        return sidebar;
    }

    // MENU BUTTON
    private Button createMenuButton(String icon, String text) {
        Button button = new Button(icon + "    " + text);
        button.setPrefHeight(55);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(0, 14, 0, 14));
        button.setCursor(Cursor.HAND);
        button.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        button.setTextFill(Color.rgb(235, 245, 235));
        button.setBackground(Background.EMPTY);
        button.setBorder(Border.EMPTY);
        return button;
    }

    // SELECTED BUTTON
    private void setSelectedMenuButton(Button selectedButton) {
        Button[] buttons = {homepageButton,dashboardButton, profileButton, aiAdvisorButton, learningButton, wishlistButton, investmentButton, schemesButton};

        for (Button button : buttons) {
            if (button == null) {
                continue;
            }

            button.setTextFill(Color.rgb(235, 245, 235));
            button.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            button.setBackground(Background.EMPTY);
        }

        // selectedButton.setTextFill(DARK_GREEN);
        // selectedButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        // selectedButton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        selectedButton.setTextFill(Color.WHITE);
        selectedButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        selectedButton.setBackground(new Background(new BackgroundFill(GREEN, new CornerRadii(10), Insets.EMPTY)));
    }

    // PAGE NAVIGATION
    private void showPage(String page) {
        if (page.equals("dashboard")) {
            setSelectedMenuButton(dashboardButton);
            root.setCenter(createDashboardPage());
        } else if (page.equals("profile")) {
            setSelectedMenuButton(profileButton);
            root.setCenter(createProfilePage());
        } else if (page.equals("ai")) {
            setSelectedMenuButton(aiAdvisorButton);
            root.setCenter(createAIAdvisorPage());
        } else if (page.equals("learning")) {
            setSelectedMenuButton(learningButton);
            root.setCenter(createLearningPage());
        } else if (page.equals("wishlist")) {
            setSelectedMenuButton(wishlistButton);
            root.setCenter(createWishlistPage());
        } else if (page.equals("investment")) {
            setSelectedMenuButton(investmentButton);
            root.setCenter(createInvestmentPage());
        } else if (page.equals("schemes")) {
            setSelectedMenuButton(schemesButton);
            root.setCenter(createSchemesPage());
        }else if (page.equals("Homepage")) {
            setSelectedMenuButton(homepageButton);
            HomePageFarmer homePageFarmer = new HomePageFarmer();
            LoginPage.mainStage.setScene(homePageFarmer.getHomePageFarmer());
        } else {
            // Default to dashboard if unknown page
            setSelectedMenuButton(dashboardButton);
            root.setCenter(createDashboardPage());  
            
        }
    }

    // DASHBOARD PAGE
    private VBox createDashboardPage() {
        VBox main = new VBox();
        main.setBackground(new Background(new BackgroundFill(GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox topBar = createTopBar("Farmer Dashboard", "Manage your farm and make smarter decisions.");
        VBox content = createDashboardContent();
        content.setBackground(new Background(new BackgroundFill( DARK_GREEN,CornerRadii.EMPTY,Insets.EMPTY )
    ));

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        main.getChildren().addAll(topBar, scroll);
        return main;
    }

    // TOP BAR
    private HBox createTopBar(String titleText, String subtitleText) {
        HBox bar = new HBox();
        bar.setPrefHeight(100);
        bar.setPadding(new Insets(18, 35, 18, 35));
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setBackground(new Background(new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox titleBox = new VBox();
        titleBox.setSpacing(3);

        Label title = new Label(titleText);
        title.setTextFill(DARK_TEXT);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        Label subtitle = new Label(subtitleText);
        subtitle.setTextFill(GREY);
        subtitle.setFont(Font.font("Arial", 15));

        titleBox.getChildren().addAll(title, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label notification = new Label("🔔");
        notification.setPrefSize(55, 55);
        notification.setAlignment(Pos.CENTER);
        notification.setFont(Font.font("Arial", 20));

        Label profile = new Label("G");
        profile.setPrefSize(55, 55);
        profile.setAlignment(Pos.CENTER);
        profile.setTextFill(Color.WHITE);
        profile.setFont(Font.font("Arial", FontWeight.BOLD, 19));
        profile.setBackground(new Background(new BackgroundFill(GREEN, new CornerRadii(50), Insets.EMPTY)));

        Label farmer = new Label("Farmer");
        farmer.setTextFill(DARK_TEXT);
        farmer.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label role = new Label("Farm Owner");
        role.setTextFill(GREY);
        role.setFont(Font.font("Arial", 12));

        VBox userText = new VBox(farmer, role);
        userText.setSpacing(2);

        HBox user = new HBox(profile, userText);
        user.setSpacing(10);
        user.setAlignment(Pos.CENTER_LEFT);

        bar.getChildren().addAll(titleBox, spacer, notification, createWidthSpace(25), user);
        return bar;
    }

    // DASHBOARD CONTENT
    private VBox createDashboardContent() {
        VBox content = new VBox();
        content.setPadding(new Insets(30, 35, 35, 35));
        content.setSpacing(22);

        content.getChildren().add(createWelcomeCard());

        HBox stats = new HBox();
        stats.setSpacing(20);

        stats.getChildren().addAll(
            createStat("🌾", "Active Crops", "4", "Currently growing"),
            createStat("▰", "Expected Yield", "18.5 T", "+12% this season"),
            createStat("₹", "Farm Revenue", "₹2.84L", "+8.4% this month"),
            createStat("●", "Farm Health", "92%", "Excellent condition")
        );

        content.getChildren().add(stats);

        HBox lower = new HBox();
        lower.setSpacing(22);

        VBox crops = createCropsCard();
        VBox activity = createActivityCard();

        HBox.setHgrow(crops, Priority.ALWAYS);
        HBox.setHgrow(activity, Priority.ALWAYS);

        lower.getChildren().addAll(crops, activity);
        content.getChildren().add(lower);
        content.getChildren().add(createQuickActions());

        return content;
    }

    // WELCOME CARD
    private HBox createWelcomeCard() {
        HBox card = new HBox();
        card.setPadding(new Insets(30, 35, 30, 35));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(17), Insets.EMPTY)));

        VBox text = new VBox();
        text.setSpacing(7);

        Label title = new Label("Good evening, Farmer! 🌱");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 29));

        Label description = new Label("Your farm is looking healthy. " + "Let's grow something amazing today.");
        description.setTextFill(Color.rgb(215, 235, 215));
        description.setFont(Font.font("Arial", 16));

        text.getChildren().addAll(title, description);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label plant = new Label("🌿");
        plant.setTextFill(Color.YELLOW);
        plant.setFont(Font.font("Arial", 60));

        card.getChildren().addAll(text, spacer, plant);
        return card;
    }

    // STAT CARD
    private VBox createStat(String icon, String title, String value, String subtitle) {
        VBox card = new VBox();
        card.setSpacing(8);
        card.setPadding(new Insets(20));
        card.setPrefHeight(160);
        HBox.setHgrow(card, Priority.ALWAYS);
        card.setBackground(new Background(new BackgroundFill(GREEN, new CornerRadii(15), Insets.EMPTY)));

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(Color.YELLOW);
        iconLabel.setPrefSize(48, 48);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(10), Insets.EMPTY)));

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(GREY);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        HBox top = new HBox(iconLabel, createWidthSpace(12), titleLabel);
        top.setAlignment(Pos.CENTER_LEFT);

        Label valueLabel = new Label(value);
        valueLabel.setTextFill(DARK_TEXT);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 27));

        Label sub = new Label(subtitle);
        sub.setTextFill(GREEN);
        sub.setFont(Font.font("Arial", 12));

        card.getChildren().addAll(top, valueLabel, sub);
        return card;
    }

    // CROPS CARD
    private VBox createCropsCard() {
        VBox card = createWhiteCard();

        Label title = new Label("My Crops");
        title.setTextFill(DARK_TEXT);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        card.getChildren().add(title);

        card.getChildren().add(createCrop("🌾", "Wheat", "Growing", "65%"));
        card.getChildren().add(createCrop("🌱", "Soybean", "Healthy", "82%"));
        card.getChildren().add(createCrop("🥬", "Vegetables", "Growing", "48%"));

        return card;
    }

    // CROP
    private HBox createCrop(String icon, String name, String status, String progress) {
        HBox row = new HBox();
        row.setSpacing(14);
        row.setPadding(new Insets(14, 0, 8, 0));
        row.setAlignment(Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Arial", 24));

        VBox text = new VBox();

        Label nameLabel = new Label(name);
        nameLabel.setTextFill(DARK_TEXT);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label statusLabel = new Label(status);
        statusLabel.setTextFill(GREEN);
        statusLabel.setFont(Font.font("Arial", 12));

        text.getChildren().addAll(nameLabel, statusLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label percentage = new Label(progress);
        percentage.setTextFill(DARK_TEXT);
        percentage.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        row.getChildren().addAll(iconLabel, text, spacer, percentage);
        return row;
    }

    // ACTIVITY CARD
    private VBox createActivityCard() {
        VBox card = createWhiteCard();

        Label title = new Label("Recent Activity");
        title.setTextFill(DARK_TEXT);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        card.getChildren().add(title);

        card.getChildren().add(createActivity("✓", "Crop health updated", "Wheat field looks healthy", "2h ago"));
        card.getChildren().add(createActivity("₹", "Marketplace opportunity", "Organic wheat buyer nearby", "5h ago"));
        card.getChildren().add(createActivity("✦", "AI recommendation", "Consider irrigation tomorrow", "Yesterday"));

        return card;
    }

    // ACTIVITY
    private HBox createActivity(String icon, String title, String description, String time) {
        HBox row = new HBox();
        row.setSpacing(12);
        row.setPadding(new Insets(12, 0, 8, 0));

        Label iconLabel = new Label(icon);
        iconLabel.setPrefSize(40, 40);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setTextFill(GREEN);
        iconLabel.setBackground(new Background(new BackgroundFill(LIGHT_GREEN, new CornerRadii(9), Insets.EMPTY)));

        VBox text = new VBox();

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(DARK_TEXT);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        Label descriptionLabel = new Label(description);
        descriptionLabel.setTextFill(GREY);
        descriptionLabel.setFont(Font.font("Arial", 11));

        text.getChildren().addAll(titleLabel, descriptionLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label timeLabel = new Label(time);
        timeLabel.setTextFill(GREY);
        timeLabel.setFont(Font.font("Arial", 11));

        row.getChildren().addAll(iconLabel, text, spacer, timeLabel);
        return row;
    }

    // QUICK ACTIONS
    private HBox createQuickActions() {
        HBox actions = new HBox();
        actions.setSpacing(18);

        Button ai = createAction("✦  Ask AI Advisor");
        ai.setOnAction(event -> showPage("ai"));

        Button learning = createAction("▣  My Learning");
        learning.setOnAction(event -> showPage("learning"));

        Button investment = createAction("₹  Investment Calculator");
        investment.setOnAction(event -> showPage("investment"));

        Button schemes = createAction("◇  Schemes");
        schemes.setOnAction(event -> showPage("schemes"));

        actions.getChildren().addAll(ai, learning, investment, schemes);
        return actions;
    }

    // ACTION BUTTON
    private Button createAction(String text) {
        Button button = new Button(text);
        button.setPrefHeight(55);
        HBox.setHgrow(button, Priority.ALWAYS);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setTextFill(DARK_GREEN);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        button.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(11), Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.rgb(215, 225, 210), BorderStrokeStyle.SOLID, new CornerRadii(11), new BorderWidths(1))));
        button.setCursor(Cursor.HAND);
        return button;
    }

    // PROFILE PAGE
    private VBox createProfilePage() {
        VBox main = new VBox();
        main.setBackground(new Background(new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox topBar = createTopBar("Farmer Profile", "Manage your personal and farming information.");

        VBox content = new VBox(); 
        // content.setBackground(new Background(
        //     new BackgroundFill(
        //             DARK_GREEN,
        //             CornerRadii.EMPTY,
        //             Insets.EMPTY
        //     )
    // ));

        content.setBackground(new Background(
        new BackgroundFill(
            Color.web("#050b0a"),
            CornerRadii.EMPTY,
            Insets.EMPTY
        )
    ));
        content.setPadding(new Insets(30, 35, 35, 35));
        content.setSpacing(22);

        HBox profileHeader = new HBox();
        profileHeader.setPrefHeight(125);
        profileHeader.setPadding(new Insets(22));
        profileHeader.setSpacing(18);
        profileHeader.setAlignment(Pos.CENTER_LEFT);
        profileHeader.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(15), Insets.EMPTY)));

        Label profileCircle = new Label("G");
        profileCircle.setPrefSize(78, 78);
        profileCircle.setMinSize(78, 78);
        profileCircle.setMaxSize(78, 78);
        profileCircle.setAlignment(Pos.CENTER);
        profileCircle.setTextFill(Color.WHITE);
        profileCircle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        profileCircle.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(50), Insets.EMPTY)));

        VBox profileText = new VBox();
        profileText.setSpacing(5);

        Label name = new Label("Farmer");
        name.setTextFill(DARK_TEXT);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        Label role = new Label("Farm Owner");
        role.setTextFill(GREEN);
        role.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        Label description = new Label("Manage your profile and farming information.");
        description.setTextFill(GREY);
        description.setFont(Font.font("Arial", 12));

        profileText.getChildren().addAll(name, role, description);

        Region profileSpacer = new Region();
        HBox.setHgrow(profileSpacer, Priority.ALWAYS);

        Button edit = new Button("Edit Profile");
        edit.setPrefHeight(40);
        edit.setPrefWidth(110);
        edit.setTextFill(Color.WHITE);
        edit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        edit.setBackground(new Background(new BackgroundFill(GREEN, new CornerRadii(7), Insets.EMPTY)));
        edit.setCursor(Cursor.HAND);

        profileHeader.getChildren().addAll(profileCircle, profileText, profileSpacer, edit);

        HBox cards = new HBox();
        cards.setSpacing(22);

        VBox personalCard = createWhiteCard();
        personalCard.setPrefHeight(275);
        personalCard.setPadding(new Insets(20));

        Label personalTitle = new Label("♙  Personal Information");
        personalTitle.setTextFill(DARK_TEXT);
        personalTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        GridPane personalGrid = new GridPane();
        personalGrid.setHgap(15);
        personalGrid.setVgap(12);

        personalGrid.add(createProfileField("Full Name", "Farmer User"), 0, 0);
        personalGrid.add(createProfileField("Email Address", "farmer@agrobiz.com"), 1, 0);
        personalGrid.add(createProfileField("Phone Number", "+91 555 123-4567"), 0, 1);
        personalGrid.add(createProfileField("Location", "Maharashtra, India"), 1, 1);

        columnConstraintsHelper(personalGrid);
        personalCard.getChildren().addAll(personalTitle, personalGrid);

        VBox farmCard = createWhiteCard();
        farmCard.setPrefHeight(275);
        farmCard.setPadding(new Insets(20));

        Label farmTitle = new Label("♧  Farm Details");
        farmTitle.setTextFill(DARK_TEXT);
        farmTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        VBox farmName = createProfileField("Farm Name", "Green Valley Farm");

        GridPane farmGrid = new GridPane();
        farmGrid.setHgap(15);
        farmGrid.setVgap(12);

        farmGrid.add(createProfileField("Farm Area", "15.6 Acres"), 0, 0);
        farmGrid.add(createProfileField("Farming Type", "Mixed Farming"), 1, 0);

        columnConstraintsHelper(farmGrid);

        Label cropTitle = new Label("Primary Crops");
        cropTitle.setTextFill(GREY);
        cropTitle.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        HBox cropTags = new HBox();
        cropTags.setSpacing(8);
        cropTags.getChildren().addAll(createCropTag("Wheat"), createCropTag("Soybean"), createCropTag("Vegetables"));

        farmCard.getChildren().addAll(farmTitle, farmName, farmGrid, cropTitle, cropTags);

        HBox.setHgrow(personalCard, Priority.ALWAYS);
        HBox.setHgrow(farmCard, Priority.ALWAYS);

        cards.getChildren().addAll(personalCard, farmCard);

        content.getChildren().addAll(profileHeader, cards);
        

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // scroll.setStyle("-fx-background-color: #0d1414;");
        scroll.setStyle(
        "-fx-background-color: #050b0a;" +
        "-fx-background: #050b0a;" +
        "-fx-control-inner-background: #050b0a;"
    );
        VBox.setVgrow(scroll, Priority.ALWAYS);

        main.getChildren().addAll(topBar, scroll);
        return main;
    }

    // PROFILE FIELD
    private VBox createProfileField(String title, String value) {
        VBox box = new VBox();
        box.setSpacing(5);
        box.setMaxWidth(Double.MAX_VALUE);

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(GREY);
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));

        TextField field = new TextField(value);
        field.setPrefHeight(36);
        field.setMaxWidth(Double.MAX_VALUE);
        field.setEditable(false);
        field.setStyle("-fx-background-color: #F5F7F3;" + "-fx-background-radius: 7;" + "-fx-border-color: #E0E6DC;" + "-fx-border-radius: 7;" + "-fx-padding: 0 10;" + "-fx-font-size: 11px;" + "-fx-text-fill: #4A504A;");

        box.getChildren().addAll(titleLabel, field);
        GridPane.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    // GRID WIDTH
    private void columnConstraintsHelper(GridPane grid) {
        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();

        c1.setPercentWidth(50);
        c2.setPercentWidth(50);

        grid.getColumnConstraints().addAll(c1, c2);
    }

    // CROP TAG
    private Label createCropTag(String text) {
        Label tag = new Label(text);
        tag.setTextFill(DARK_GREEN);
        tag.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        tag.setPadding(new Insets(5, 10, 5, 10));
        tag.setBackground(new Background(new BackgroundFill(Color.rgb(225, 235, 215), new CornerRadii(12), Insets.EMPTY)));
        tag.setBorder(new Border(new BorderStroke(Color.rgb(195, 210, 185), BorderStrokeStyle.SOLID, new CornerRadii(12), new BorderWidths(1))));
        return tag;
    }

// =========================================================
// AI ADVISOR PAGE
// =========================================================
private VBox createAIAdvisorPage() {

    VBox page = new VBox();

    // Same background as the application
    page.setBackground(
        new Background(
            new BackgroundFill(
                DARK_GREEN,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
    );

    // =====================================================
    // TOP BAR
    // =====================================================

    HBox topBar = createTopBar(
        "AI Farming Advisor",
        "Get intelligent recommendations for your farm."
    );

    // IMPORTANT:
    // Remove any bottom margin/gap from the top bar.
    VBox.setMargin(topBar, Insets.EMPTY);


    // =====================================================
    // CONTENT
    // =====================================================

    VBox content = new VBox();

    content.setBackground(
        new Background(
            new BackgroundFill(
                DARK_GREEN,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
    );

    // NO TOP PADDING
    content.setPadding(
        new Insets(0, 35, 35, 35)
    );

    content.setSpacing(0);


    // =====================================================
    // AI CARD
    // =====================================================

    VBox card = createWhiteCard();

    card.setPrefHeight(650);

    // Keep card directly attached to content
    VBox.setMargin(card, Insets.EMPTY);


    // =====================================================
    // CENTER
    // =====================================================

    VBox center = new VBox();

    center.setAlignment(Pos.TOP_CENTER);
    center.setSpacing(25);

    center.setPadding(
        new Insets(20)
    );


    // =====================================================
    // AI ICON
    // =====================================================

    Label aiIcon = new Label("✦");

    aiIcon.setPrefSize(70, 70);
    aiIcon.setAlignment(Pos.CENTER);

    aiIcon.setTextFill(Color.WHITE);

    aiIcon.setFont(
        Font.font(
            "Arial",
            FontWeight.BOLD,
            32
        )
    );

    aiIcon.setBackground(
        new Background(
            new BackgroundFill(
                DARK_GREEN,
                new CornerRadii(50),
                Insets.EMPTY
            )
        )
    );


    // =====================================================
    // QUESTION
    // =====================================================

    Label question = new Label(
        "How can Agro Biz AI help your farm?"
    );

    question.setTextFill(Color.WHITE);

    question.setFont(
        Font.font(
            "Arial",
            FontWeight.BOLD,
            23
        )
    );


    // =====================================================
    // SUGGESTIONS
    // =====================================================

    HBox suggestions = new HBox();

    
        suggestions.setSpacing(12);
        suggestions.setAlignment(Pos.CENTER);

        suggestions.getChildren().addAll(
            createSuggestionButton("🌾  Which crop should I grow?"),
            createSuggestionButton("↗  How can I improve my yield?")
        );

        Button irrigation = createSuggestionButton("💧  Optimize irrigation schedule");



    center.getChildren().addAll(
        aiIcon,
        question,
        suggestions,
        irrigation
    );


    // =====================================================
    // MESSAGE
    // =====================================================

    HBox message = new HBox();

    message.setSpacing(12);

    message.setPadding(
        new Insets(20)
    );

    message.setBackground(
        new Background(
            new BackgroundFill(
                Color.rgb(244, 246, 238),
                new CornerRadii(15),
                Insets.EMPTY
            )
        )
    );


    // Assistant icon
    Label assistantIcon = new Label("✦");

    assistantIcon.setPrefSize(42, 42);
    assistantIcon.setAlignment(Pos.CENTER);

    assistantIcon.setTextFill(Color.WHITE);

    assistantIcon.setBackground(
        new Background(
            new BackgroundFill(
                DARK_GREEN,
                new CornerRadii(50),
                Insets.EMPTY
            )
        )
    );


    // Assistant text
    Label assistantText = new Label(
        "Hello Farmer! 🌱 I can help you make smarter "
        + "farming decisions based on your current "
        + "soil data and local weather forecasts. "
        + "What would you like to analyze today?"
    );

    assistantText.setWrapText(true);

    assistantText.setTextFill(Color.GREY);

    assistantText.setFont(
        Font.font(
            "Arial",
            15
        )
    );

    HBox.setHgrow(
        assistantText,
        Priority.ALWAYS
    );


    message.getChildren().addAll(
        assistantIcon,
        assistantText
    );


    // =====================================================
    // SPACER
    // =====================================================

    Region aiSpacer = new Region();

    VBox.setVgrow(
        aiSpacer,
        Priority.ALWAYS
    );


    // =====================================================
    // QUESTION FIELD
    // =====================================================

    TextField questionField =
        new TextField();

    questionField.setPromptText(
        "Ask your farming question..."
    );

    questionField.setPrefHeight(50);


    // =====================================================
    // ASK AI BUTTON
    // =====================================================

    Button askAI = new Button("Ask AI  ➤");
        askAI.setPrefHeight(50);
        askAI.setPrefWidth(120);
        askAI.setTextFill(Color.WHITE);
        askAI.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        askAI.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(9), Insets.EMPTY)));



    // =====================================================
    // ASK AI ACTION
    // =====================================================

    askAI.setOnAction(event -> {

        if (!questionField.getText().trim().isEmpty()) {

            assistantText.setText(
                "AI Advisor received your question: "
                + questionField.getText()
                + "\n\nThis AI response module is ready "
                + "to be connected with your AI service."
            );

            questionField.clear();
        }
    });


    // =====================================================
    // INPUT
    // =====================================================

    HBox input =
        new HBox(
            questionField,
            askAI
        );

    input.setSpacing(12);

    HBox.setHgrow(
        questionField,
        Priority.ALWAYS
    );


    // =====================================================
    // ADD EVERYTHING
    // =====================================================

    center.getChildren().addAll(
        message,
        aiSpacer,
        input
    );

    card.getChildren().add(center);

    content.getChildren().add(card);


    // =====================================================
    // SCROLL PANE
    // =====================================================

    ScrollPane scroll =
        new ScrollPane(content);

    scroll.setFitToWidth(true);
    scroll.setFitToHeight(true);

    scroll.setHbarPolicy(
        ScrollPane.ScrollBarPolicy.NEVER
    );

    scroll.setVbarPolicy(
        ScrollPane.ScrollBarPolicy.NEVER
    );

    scroll.setStyle(
        "-fx-background-color: transparent;"
        + "-fx-background: transparent;"
        + "-fx-control-inner-background: transparent;"
    );

    VBox.setVgrow(
        scroll,
        Priority.ALWAYS
    );


    // =====================================================
    // FINAL PAGE
    // =====================================================

    page.getChildren().addAll(
        topBar,
        scroll
    );

    return page;
}


// =========================================================
// AI SUGGESTION BUTTON
// =========================================================
private Button createSuggestionButton(String text) {

    Button button = new Button(text);

    button.setPrefHeight(40);

    button.setTextFill(
        LIGHT_GREEN
    );

    button.setFont(
        Font.font(
            "Arial",
            12
        )
    );

    button.setBackground(
        new Background(
            new BackgroundFill(
                Color.WHITE,
                new CornerRadii(20),
                Insets.EMPTY
            )
        )
    );

    button.setBorder(
        new Border(
            new BorderStroke(
                BORDER_COLOR,
                BorderStrokeStyle.SOLID,
                new CornerRadii(20),
                new BorderWidths(1)
            )
        )
    );

    button.setCursor(
        Cursor.HAND
    );

    return button;
}

    // MY LEARNING PAGE
    private VBox createLearningPage() {
        VBox page = new VBox();
        page.setBackground(new Background(new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // TOP BAR
        HBox topBar = createTopBar("My Learning", "Learn farming techniques and improve your knowledge.");

        // CONTENT
        VBox content = new VBox();
        content.setPadding(new Insets(30, 35, 35, 35));
        content.setSpacing(22);

        // LEARNING PROGRESS CARD
        HBox progressCard = createLearningProgressCard();
        content.getChildren().add(progressCard);

        // LEARNING MODULES
        GridPane modules = new GridPane();
        modules.setHgap(22);
        modules.setVgap(22);
        modules.setMaxWidth(Double.MAX_VALUE);

        // Row 1
        modules.add(createLearningModule("🌿", "Crop Management", "Learn advanced techniques for managing crop " + "rotation, yield optimization, and seasonal planning.", 75, true), 0, 0);

        modules.add(createLearningModule("💧", "Irrigation", "Master water management systems, drip irrigation " + "techniques, and drought resilience strategies.", 60, true), 1, 0);

        modules.add(createLearningModule("🌱", "Organic Farming", "Understand organic certification requirements, " + "natural pest control, and sustainable practices.", 45, true), 2, 0);

        // Row 2
        modules.add(createLearningModule("◇", "Soil Management", "Deep dive into soil chemistry, nutrient balancing, " + "erosion prevention, and microbiology.", 30, true), 0, 1);

        modules.add(createLearningModule("🐞", "Pest Management", "Learn integrated pest management (IPM), identifying " + "beneficial insects, and safe mitigation.", 20, true), 1, 1);

        modules.add(createLearningModule("▣", "Farm Business", "Financial planning, market analysis, subsidy " + "applications, and supply chain logistics.", 10, false), 2, 1);

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();

        column1.setPercentWidth(33.33);
        column2.setPercentWidth(33.33);
        column3.setPercentWidth(33.33);

        modules.getColumnConstraints().addAll(column1, column2, column3);
        content.getChildren().add(modules);
        content.setBackground(new Background(new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // SCROLL
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        page.getChildren().addAll(topBar, scroll);
        return page;
    }

    // LEARNING PROGRESS CARD
    private HBox createLearningProgressCard() {
        HBox card = new HBox();
        card.setPrefHeight(190);
        card.setPadding(new Insets(28, 32, 28, 32));
        card.setSpacing(35);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setBackground(new Background(new BackgroundFill(CARD_BACKGROUND, new CornerRadii(17), Insets.EMPTY)));

        // LEFT TEXT
        VBox text = new VBox();
        text.setSpacing(8);

        Label title = new Label("Your Learning Progress");
        title.setTextFill(DARK_TEXT);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        Label description = new Label("You are making steady progress toward\n" + "your farming certifications.");
        description.setTextFill(DARK_TEXT);
        description.setFont(Font.font("Arial", 14));

        text.getChildren().addAll(title, description);
        HBox.setHgrow(text, Priority.ALWAYS);

        // CIRCULAR PROGRESS
        StackPane progressCircle = createCircularProgress(68);

        // PROGRESS DETAILS
        VBox details = new VBox();
        details.setSpacing(18);

        details.getChildren().addAll(
            createProgressDetail("✓", "4 Modules finished", GREEN),
            createProgressDetail("•••", "2 Modules ongoing", DARK_GREEN),
            createProgressDetail("♧", "3 Modules remaining", GREY)
        );

        card.getChildren().addAll(text, progressCircle, details);
        return card;
    }

    // CIRCULAR PROGRESS
    private StackPane createCircularProgress(double percentage) {
        StackPane pane = new StackPane();
        pane.setPrefSize(130, 130);

        Circle background = new Circle(55);
        background.setFill(Color.TRANSPARENT);
        background.setStroke(Color.rgb(220, 224, 216));
        background.setStrokeWidth(10);

        Arc progress = new Arc(0, 0, 55, 55, 90, -(percentage * 3.6));
        progress.setFill(Color.TRANSPARENT);
        progress.setStroke(DARK_GREEN);
        progress.setStrokeWidth(10);
        progress.setType(ArcType.OPEN);

        Label percent = new Label(String.format("%.0f%%", percentage));
        percent.setTextFill(DARK_GREEN);
        percent.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label completed = new Label("Completed");
        completed.setTextFill(DARK_TEXT);
        completed.setFont(Font.font("Arial", 11));

        VBox center = new VBox(percent, completed);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(0);

        pane.getChildren().addAll(background, progress, center);
        return pane;
    }

    // PROGRESS DETAIL
    private HBox createProgressDetail(String icon, String text, Color color) {
        HBox row = new HBox();
        row.setSpacing(12);
        row.setAlignment(Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);
        iconLabel.setPrefSize(25, 25);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setTextFill(color);
        iconLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label textLabel = new Label(text);
        textLabel.setTextFill(DARK_TEXT);
        textLabel.setFont(Font.font("Arial", 14));

        row.getChildren().addAll(iconLabel, textLabel);
        return row;
    }

    // LEARNING MODULE CARD
    private VBox createLearningModule(String icon, String title, String description, int progress, boolean continueLearning) {
        VBox card = new VBox();
        card.setSpacing(14);
        card.setPadding(new Insets(24));
        card.setMinHeight(310);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setBackground(new Background(new BackgroundFill(CARD_BACKGROUND, new CornerRadii(16), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(BORDER_COLOR, BorderStrokeStyle.SOLID, new CornerRadii(16), new BorderWidths(1))));

        // TOP ROW
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);
        iconLabel.setPrefSize(50, 50);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setFont(Font.font("Arial", 23));
        iconLabel.setBackground(new Background(new BackgroundFill(Color.rgb(237, 233, 229), new CornerRadii(9), Insets.EMPTY)));

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        Label percentage = new Label(progress + "%");
        percentage.setPadding(new Insets(5, 13, 5, 13));
        percentage.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        if (progress >= 50) {
            percentage.setTextFill(DARK_GREEN);
            percentage.setBackground(new Background(new BackgroundFill(Color.rgb(174, 242, 170), new CornerRadii(15), Insets.EMPTY)));
        } else {
            percentage.setTextFill(GREY);
            percentage.setBackground(new Background(new BackgroundFill(Color.rgb(225, 228, 220), new CornerRadii(15), Insets.EMPTY)));
        }

        top.getChildren().addAll(iconLabel, topSpacer, percentage);

        // TITLE
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.rgb(20, 25, 20));
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        // DESCRIPTION
        Label descriptionLabel = new Label(description);
        descriptionLabel.setTextFill(DARK_TEXT);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setFont(Font.font("Arial", 13));
        descriptionLabel.setMinHeight(75);

        // PROGRESS BAR
        HBox progressBar = createLearningProgressBar(progress);

        // LEARNING BUTTON
        Button learningButton;

        if (continueLearning) {
            learningButton = new Button("Continue Learning   →");
            learningButton.setTextFill(Color.WHITE);
            learningButton.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(8), Insets.EMPTY)));
        } else {
            learningButton = new Button("Start Learning   ▷");
            learningButton.setTextFill(DARK_TEXT);
            learningButton.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(8), Insets.EMPTY)));
            learningButton.setBorder(new Border(new BorderStroke(BORDER_COLOR, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(1))));
        }

        learningButton.setPrefHeight(48);
        learningButton.setMaxWidth(Double.MAX_VALUE);
        learningButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        learningButton.setCursor(Cursor.HAND);

        learningButton.setOnAction(event -> {
            System.out.println("Learning module selected: " + title);
        });

        card.getChildren().addAll(top, titleLabel, descriptionLabel, progressBar, learningButton);
        GridPane.setHgrow(card, Priority.ALWAYS);

        return card;
    }

    // LEARNING PROGRESS BAR
    private HBox createLearningProgressBar(int percentage) {
        HBox bar = new HBox();
        bar.setPrefHeight(8);
        bar.setMaxWidth(Double.MAX_VALUE);
        bar.setBackground(new Background(new BackgroundFill(Color.rgb(220, 224, 216), new CornerRadii(10), Insets.EMPTY)));

        Region progress = new Region();
        progress.setPrefHeight(8);
        progress.setBackground(new Background(new BackgroundFill(DARK_GREEN, new CornerRadii(10), Insets.EMPTY)));

        double width = Math.max(20, percentage);
        HBox.setHgrow(progress, Priority.NEVER);

        progress.prefWidthProperty().bind(bar.widthProperty().multiply(width / 100.0));

        Region remaining = new Region();
        HBox.setHgrow(remaining, Priority.ALWAYS);

        bar.getChildren().addAll(progress, remaining);
        return bar;
    }

    // LEARNING PAGE FEATURE CARD
    private VBox createLearningFeatureCard(String icon, String title, String description) {
        VBox card = createWhiteCard();

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Arial", 30));

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(DARK_TEXT);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label descriptionLabel = new Label(description);
        descriptionLabel.setTextFill(GREY);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setFont(Font.font("Arial", 12));

        card.getChildren().addAll(iconLabel, titleLabel, descriptionLabel);
        return card;
    }
    // =========================================================
    // WISHLIST PAGE
    // =========================================================

    private VBox createWishlistPage() {
        return createWishlistPage("All");
    }

    // Rebuilds the right-side wishlist area for the selected tab.
    private VBox createWishlistPage(String selectedTab) {

        VBox page = new VBox();

        // page.setBackground(
        //         new Background(
        //                 new BackgroundFill(
        //                         DARK_GREEN,
        //                         CornerRadii.EMPTY,
        //                         Insets.EMPTY
        //                 )
        //         )
        // );
        page.setBackground(new Background(
            new BackgroundFill(CREAM, CornerRadii.EMPTY, Insets.EMPTY)
));

        // Reuse the normal dashboard header on the right side.
        HBox topBar = createTopBar(
                "Wishlist",
                "Your saved farming products and resources."
        );

        VBox content = new VBox(22);
        content.setPadding(new Insets(30, 35, 35, 35));
        // content.setBackground(new Background(
        //         new BackgroundFill(
        //                 DARK_GREEN,
        //                 CornerRadii.EMPTY,
        //                 Insets.EMPTY
        //         )
        // ));
        // content.setBackground(new Background(
        // new BackgroundFill(CREAM, CornerRadii.EMPTY, Insets.EMPTY)
//));
//VBox content = new VBox(22);
//content.setPadding(new Insets(30, 35, 35, 35));
content.setBackground(new Background(
        new BackgroundFill(
                Color.web("#080c0d"),
                CornerRadii.EMPTY,
                Insets.EMPTY
        )
));


        HBox tabs = new HBox(35);
        tabs.setAlignment(Pos.CENTER_LEFT);
        tabs.getChildren().addAll(
                createWishlistTab("All", selectedTab.equals("All")),
                createWishlistTab("Products", selectedTab.equals("Products")),
                createWishlistTab("Courses", selectedTab.equals("Courses")),
                createWishlistTab("Resources", selectedTab.equals("Resources"))
        );

        HBox cards = new HBox(22);

        // Show only the cards that belong to the selected category.
        if (selectedTab.equals("All") || selectedTab.equals("Products")) {
            cards.getChildren().add(createWishlistProductCard());
        }

        if (selectedTab.equals("All") || selectedTab.equals("Courses")) {
            cards.getChildren().add(createWishlistCourseCard());
        }

        content.getChildren().add(tabs);

        if (selectedTab.equals("Resources")) {
            content.getChildren().add(createEmptyWishlistMessage());
        } else {
            content.getChildren().add(cards);
        }

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setStyle("-fx-background-color: #080c0d;  -fx-control-inner-background: #080c0d;");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setBackground(Background.EMPTY);

        scroll.applyCss();

javafx.scene.Node viewport = scroll.lookup(".viewport");

if (viewport != null) {
    viewport.setStyle("-fx-background-color: #080c0d;");
}

VBox.setVgrow(scroll, Priority.ALWAYS);
page.getChildren().addAll(topBar, scroll);

        return page;
    }

    // Creates one simple category button for the wishlist.
    private Button createWishlistTab(String text, boolean selected) {

        Button tab = new Button(text);
        tab.setCursor(Cursor.HAND);
        tab.setFont(Font.font("Arial", selected ? FontWeight.BOLD : FontWeight.NORMAL, 18));
        tab.setTextFill(selected ? LIGHT_GREEN  : DARK_TEXT);
        tab.setPadding(new Insets(0, 0, 10, 0));
        tab.setBackground(Background.EMPTY);

        if (selected) {
            tab.setBorder(new Border(new BorderStroke(
                    DARK_GREEN,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(0, 0, 2, 0)
            )));
        } else {
            tab.setBorder(Border.EMPTY);
        }

        // The stage stays the same; only the dashboard centre changes.
        tab.setOnAction(event -> root.setCenter(createWishlistPage(text)));

        return tab;
    }

    // Product card shown in the wishlist.
    private VBox createWishlistProductCard() {

        VBox card = createWishlistCard();

        StackPane imageBox = new StackPane();
        imageBox.setPrefSize(240, 168);

        ImageView productImage = createWishlistImage("/fertilizer.png");

        Label heart = new Label("♥");
        heart.setTextFill(Color.rgb(200, 20, 25));
        heart.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        heart.setPadding(new Insets(4, 9, 4, 9));
        heart.setBackground(new Background(new BackgroundFill(
                Color.rgb(255, 250, 245), new CornerRadii(20), Insets.EMPTY
        )));

        imageBox.getChildren().addAll(productImage, heart);
        StackPane.setAlignment(heart, Pos.TOP_RIGHT);
        StackPane.setMargin(heart, new Insets(8));

        Label type = createWishlistBadge("AGRICULTURAL INPUT");
        Label price = new Label("₹1,250");
        price.setTextFill(DARK_GREEN);
        price.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Region priceSpace = new Region();
        HBox.setHgrow(priceSpace, Priority.ALWAYS);
        HBox details = new HBox(type, priceSpace, price);
        details.setAlignment(Pos.CENTER_LEFT);

        Label title = createWishlistTitle("Premium Organic\nFertilizer");
        Label description = createWishlistDescription(
                "Enhance soil health and boost\ncrop yield naturally with our..."
        );

        Button action = createWishlistActionButton("View Details");
        action.setOnAction(event -> root.setCenter(createWishlistDetailsPage(
                "Product Details",
                "Premium Organic Fertilizer",
                "₹1,250",
                "/fertilizer.png",
                "A natural agricultural input that improves soil health and supports healthy crop growth."
        )));

        card.getChildren().addAll(imageBox, details, title, description, action);
        return card;
    }

    // Course card shown in the wishlist.
    private VBox createWishlistCourseCard() {

        VBox card = createWishlistCard();

        StackPane courseImage = new StackPane();
        courseImage.setPrefSize(240, 168);
        ImageView courseImageView = createWishlistImage("/irrigation.png");

        Label heart = new Label("♥");
        heart.setTextFill(Color.rgb(200, 20, 25));
        heart.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        heart.setPadding(new Insets(4, 9, 4, 9));
        heart.setBackground(new Background(new BackgroundFill(
                Color.rgb(214, 244, 209), new CornerRadii(20), Insets.EMPTY
        )));

        courseImage.getChildren().addAll(courseImageView, heart);
        StackPane.setAlignment(heart, Pos.TOP_RIGHT);
        StackPane.setMargin(heart, new Insets(8));

        Label type = createWishlistBadge("COURSE");
        Label price = new Label("Free");
        price.setTextFill(DARK_GREEN);
        price.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Region priceSpace = new Region();
        HBox.setHgrow(priceSpace, Priority.ALWAYS);
        HBox details = new HBox(type, priceSpace, price);
        details.setAlignment(Pos.CENTER_LEFT);

        Label title = createWishlistTitle("Modern Irrigation\nTechniques");
        Label description = createWishlistDescription(
                "Learn water-saving strategies and\nadvanced drip irrigation systems..."
        );

        Button action = createWishlistActionButton("View Course");
        action.setOnAction(event -> root.setCenter(createWishlistDetailsPage(
                "Course Details",
                "Modern Irrigation Techniques",
                "Free",
                "/irrigation.png",
                "Learn practical water-saving methods, drip irrigation basics, and ways to manage water efficiently."
        )));

        card.getChildren().addAll(courseImage, details, title, description, action);
        return card;
    }

    // Shared styling for both wishlist cards.
    private VBox createWishlistCard() {

        VBox card = new VBox(13);
        card.setPrefWidth(270);
        card.setPadding(new Insets(22));
        card.setBackground(new Background(new BackgroundFill(
                CARD_BACKGROUND, new CornerRadii(14), Insets.EMPTY
        )));
        card.setBorder(new Border(new BorderStroke(
                BORDER_COLOR, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(1)
        )));

        return card;
    }

    // Loads a wishlist image from src/main/resources.
    private ImageView createWishlistImage(String imagePath) {

        URL imageUrl = getClass().getResource(imagePath);
        ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));
        imageView.setFitWidth(240);
        imageView.setFitHeight(168);
        imageView.setPreserveRatio(false);

        return imageView;
    }

    private Label createWishlistBadge(String text) {

        Label badge = new Label(text);
        badge.setTextFill(DARK_GREEN);
        badge.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        badge.setPadding(new Insets(5, 8, 5, 8));
        badge.setBackground(new Background(new BackgroundFill(
                Color.rgb(229, 240, 222), new CornerRadii(4), Insets.EMPTY
        )));

        return badge;
    }

    private Label createWishlistTitle(String text) {

        Label title = new Label(text);
        title.setTextFill(Color.rgb(15, 20, 15));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        title.setWrapText(true);

        return title;
    }

    private Label createWishlistDescription(String text) {

        Label description = new Label(text);
        description.setTextFill(GREY);
        description.setFont(Font.font("Arial", 13));
        description.setMinHeight(47);

        return description;
    }

    private Button createWishlistActionButton(String text) {

        Button button = new Button(text);
        button.setPrefHeight(42);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setCursor(Cursor.HAND);
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        button.setBackground(new Background(new BackgroundFill(
                DARK_GREEN, new CornerRadii(7), Insets.EMPTY
        )));

        return button;
    }

    // Simple message used until resource items are added later.
    private VBox createEmptyWishlistMessage() {

        VBox message = createWishlistCard();
        message.setPrefWidth(420);

        Label title = createWishlistTitle("No saved resources yet");
        Label text = createWishlistDescription(
                "Resources you save in the future will appear here."
        );

        message.getChildren().addAll(title, text);
        return message;
    }

    // Reusable details page for a product or course.
    private VBox createWishlistDetailsPage(
            String pageTitle,
            String itemTitle,
            String price,
            String imagePath,
            String description) {

        VBox page = new VBox();
        page.setBackground(new Background(new BackgroundFill(
                CREAM, CornerRadii.EMPTY, Insets.EMPTY
        )));

        HBox topBar = createTopBar(pageTitle, "Saved item from your wishlist.");

        VBox content = new VBox(22);
        content.setPadding(new Insets(30, 35, 35, 35));

        Button backButton = new Button("← Back to Wishlist");
        backButton.setCursor(Cursor.HAND);
        backButton.setTextFill(DARK_GREEN);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setBackground(Background.EMPTY);
        backButton.setBorder(Border.EMPTY);
        backButton.setOnAction(event -> root.setCenter(createWishlistPage()));

        HBox detailCard = new HBox(28);
        detailCard.setPadding(new Insets(25));
        detailCard.setBackground(new Background(new BackgroundFill(
                CARD_BACKGROUND, new CornerRadii(14), Insets.EMPTY
        )));
        detailCard.setBorder(new Border(new BorderStroke(
                BORDER_COLOR, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(1)
        )));

        ImageView image = createWishlistImage(imagePath);
        image.setFitWidth(300);
        image.setFitHeight(210);

        VBox information = new VBox(15);
        Label title = createWishlistTitle(itemTitle);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 27));

        Label priceLabel = new Label(price);
        priceLabel.setTextFill(DARK_GREEN);
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Label descriptionLabel = new Label(description);
        descriptionLabel.setTextFill(GREY);
        descriptionLabel.setFont(Font.font("Arial", 15));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(420);

        Button saveButton = createWishlistActionButton("Saved to Wishlist ♥");
        saveButton.setDisable(true);

        information.getChildren().addAll(title, priceLabel, descriptionLabel, saveButton);
        detailCard.getChildren().addAll(image, information);

        content.getChildren().addAll(backButton, detailCard);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
        "-fx-background-color: #050b0a;" +
        "-fx-background: #050b0a;" +
        "-fx-control-inner-background: #050b0a;"
        );;

        VBox.setVgrow(scroll, Priority.ALWAYS);
        page.getChildren().addAll(topBar, scroll);

        return page;
    }

// =========================================================
// INVESTMENT PAGE
// =========================================================

private VBox createInvestmentPage() {
    VBox page = new VBox();
    page.setBackground(new Background(new BackgroundFill(CREAM, CornerRadii.EMPTY, Insets.EMPTY)));

    HBox topBar = createTopBar("Course Investment Planner", "Plan your course investment month by month.");

    VBox content = new VBox(22);
    content.setPadding(new Insets(25, 35, 35, 35));
    content.setBackground(new Background(new BackgroundFill(CREAM, CornerRadii.EMPTY, Insets.EMPTY)));

    // =====================================================
    // INPUT CARD
    // =====================================================

    VBox inputCard = createWhiteCard();
    inputCard.setMaxWidth(700);
    inputCard.setPadding(new Insets(28));
    inputCard.setSpacing(12);

    Label title = new Label("Course Investment Details");
    title.setTextFill(DARK_TEXT);
    title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

    Label courseLabel = new Label("Select Course");
    courseLabel.setTextFill(GREY);
    courseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

    ComboBox<String> courseBox = new ComboBox<>();
    courseBox.getItems().addAll(
            "Poultry Farming",
            "Dairy Farming",
            "Aquaculture",
            "Smart Agriculture",
            "Precision Agriculture",
            "AI in Agriculture"
    );
    courseBox.setValue("Poultry Farming");
    courseBox.setPrefHeight(45);
    courseBox.setMaxWidth(Double.MAX_VALUE);

    Label durationLabel = new Label("Course Duration (Months)");
    durationLabel.setTextFill(GREY);
    durationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

    ComboBox<Integer> durationBox = new ComboBox<>();
    durationBox.getItems().addAll(3, 6, 9, 12);
    durationBox.setValue(6);
    durationBox.setPrefHeight(45);
    durationBox.setMaxWidth(Double.MAX_VALUE);

    Label totalLabel = new Label("Total Investment");
    totalLabel.setTextFill(GREY);
    totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

    TextField totalInvestmentField = new TextField();
    totalInvestmentField.setPromptText("Example: 60000");
    totalInvestmentField.setPrefHeight(45);
    totalInvestmentField.setFont(Font.font("Arial", 14));
    totalInvestmentField.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #263a2b;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 0 12;" +
            "-fx-text-fill: #172018;"
    );

    Label initialLabel = new Label("Initial Investment");
    initialLabel.setTextFill(GREY);
    initialLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

    TextField initialInvestmentField = new TextField();
    initialInvestmentField.setPromptText("Example: 10000");
    initialInvestmentField.setPrefHeight(45);
    initialInvestmentField.setFont(Font.font("Arial", 14));
    initialInvestmentField.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #263a2b;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 0 12;" +
            "-fx-text-fill: #172018;"
    );

    Button calculate = createWishlistActionButton("Calculate Investment Plan");
    calculate.setPrefHeight(48);
    calculate.setMaxWidth(Double.MAX_VALUE);

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.web("#e57373"));
    errorLabel.setFont(Font.font("Arial", 12));

    inputCard.getChildren().addAll(
            title,
            createSpace(5),
            courseLabel,
            courseBox,
            durationLabel,
            durationBox,
            totalLabel,
            totalInvestmentField,
            initialLabel,
            initialInvestmentField,
            createSpace(5),
            calculate,
            errorLabel
    );

    // =====================================================
    // SUMMARY CARD
    // =====================================================

    VBox summaryCard = createWhiteCard();
    summaryCard.setMaxWidth(700);
    summaryCard.setPadding(new Insets(25));
    summaryCard.setSpacing(12);

    Label summaryTitle = new Label("Investment Summary");
    summaryTitle.setTextFill(DARK_TEXT);
    summaryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 21));

    Label selectedCourse = new Label("Course: Poultry Farming");
    selectedCourse.setTextFill(GREY);
    selectedCourse.setFont(Font.font("Arial", 13));

    Label totalValue = new Label("Total Investment: ₹0");
    totalValue.setTextFill(DARK_TEXT);
    totalValue.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    Label initialValue = new Label("Initial Investment: ₹0");
    initialValue.setTextFill(DARK_TEXT);
    initialValue.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    Label remainingValue = new Label("Remaining Investment: ₹0");
    remainingValue.setTextFill(DARK_TEXT);
    remainingValue.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    Label monthlyValue = new Label("Monthly Investment: ₹0");
    monthlyValue.setTextFill(GREEN);
    monthlyValue.setFont(Font.font("Arial", FontWeight.BOLD, 18));

    ProgressBar progressBar = new ProgressBar(0);
    progressBar.setPrefHeight(18);
    progressBar.setMaxWidth(Double.MAX_VALUE);
    progressBar.setStyle(
            "-fx-accent: #68d34a;" +
            "-fx-control-inner-background: #dfe7e0;"
    );

    Label progressLabel = new Label("Investment Progress: 0%");
    progressLabel.setTextFill(GREY);
    progressLabel.setFont(Font.font("Arial", 12));

    summaryCard.getChildren().addAll(
            summaryTitle,
            selectedCourse,
            totalValue,
            initialValue,
            remainingValue,
            monthlyValue,
            progressBar,
            progressLabel
    );

    // =====================================================
    // MONTH-WISE PLAN
    // =====================================================

    VBox monthlyCard = createWhiteCard();
    monthlyCard.setMaxWidth(700);
    monthlyCard.setPadding(new Insets(25));
    monthlyCard.setSpacing(12);

    Label monthlyTitle = new Label("Month-wise Investment Plan");
    monthlyTitle.setTextFill(DARK_TEXT);
    monthlyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 21));

    VBox monthlyList = new VBox(8);

    Label monthlyInstruction = new Label(
            "Calculate your plan to see the monthly investment."
    );
    monthlyInstruction.setTextFill(GREY);
    monthlyInstruction.setFont(Font.font("Arial", 13));

    monthlyList.getChildren().add(monthlyInstruction);

    monthlyCard.getChildren().addAll(
            monthlyTitle,
            monthlyList
    );

    // =====================================================
    // AI SUGGESTION
    // =====================================================

    VBox suggestionCard = new VBox(8);
    suggestionCard.setMaxWidth(700);
    suggestionCard.setPadding(new Insets(22));

    suggestionCard.setBackground(
            new Background(
                    new BackgroundFill(
                            Color.web("#102517"),
                            new CornerRadii(12),
                            Insets.EMPTY
                    )
            )
    );

    suggestionCard.setBorder(
            new Border(
                    new BorderStroke(
                            Color.web("#2d6b3f"),
                            BorderStrokeStyle.SOLID,
                            new CornerRadii(12),
                            new BorderWidths(1)
                    )
            )
    );

    Label suggestionTitle = new Label("💡 Investment Suggestion");
    suggestionTitle.setTextFill(GREEN);
    suggestionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    Label suggestionText = new Label(
            "Enter your investment details to receive a simple monthly investment suggestion."
    );
    suggestionText.setTextFill(Color.web("#c8d5ca"));
    suggestionText.setFont(Font.font("Arial", 13));
    suggestionText.setWrapText(true);

    suggestionCard.getChildren().addAll(
            suggestionTitle,
            suggestionText
    );

    // =====================================================
    // CALCULATE ACTION
    // =====================================================

    calculate.setOnAction(event -> {

        try {

            String selected = courseBox.getValue();
            int months = durationBox.getValue();

            double totalInvestment = Double.parseDouble(
                    totalInvestmentField.getText().trim()
            );

            double initialInvestment = Double.parseDouble(
                    initialInvestmentField.getText().trim()
            );

            if (totalInvestment <= 0) {
                errorLabel.setText("Total investment must be greater than 0.");
                return;
            }

            if (initialInvestment < 0) {
                errorLabel.setText("Initial investment cannot be negative.");
                return;
            }

            if (initialInvestment > totalInvestment) {
                errorLabel.setText(
                        "Initial investment cannot be greater than total investment."
                );
                return;
            }

            double remainingInvestment =
                    totalInvestment - initialInvestment;

            double monthlyInvestment =
                    remainingInvestment / months;

            selectedCourse.setText("Course: " + selected);

            totalValue.setText(
                    "Total Investment: ₹" +
                    String.format("%,.0f", totalInvestment)
            );

            initialValue.setText(
                    "Initial Investment: ₹" +
                    String.format("%,.0f", initialInvestment)
            );

            remainingValue.setText(
                    "Remaining Investment: ₹" +
                    String.format("%,.0f", remainingInvestment)
            );

            monthlyValue.setText(
                    "Monthly Investment: ₹" +
                    String.format("%,.2f", monthlyInvestment)
            );

            monthlyList.getChildren().clear();

            for (int i = 1; i <= months; i++) {

                double currentAmount = monthlyInvestment;

                if (i == months) {
                    currentAmount = remainingInvestment -
                            (monthlyInvestment * (months - 1));
                }

                HBox monthRow = new HBox();
                monthRow.setAlignment(Pos.CENTER_LEFT);
                monthRow.setPadding(
                        new Insets(12, 15, 12, 15)
                );

                monthRow.setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.web("#101914"),
                                        new CornerRadii(8),
                                        Insets.EMPTY
                                )
                        )
                );

                Label monthLabel = new Label(
                        "Month " + i
                );

                monthLabel.setTextFill(DARK_TEXT);
                monthLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                14
                        )
                );

                Region spacer = new Region();
                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label amountLabel = new Label(
                        "₹" +
                        String.format(
                                "%,.2f",
                                currentAmount
                        )
                );

                amountLabel.setTextFill(GREEN);
                amountLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                14
                        )
                );

                monthRow.getChildren().addAll(
                        monthLabel,
                        spacer,
                        amountLabel
                );

                monthlyList.getChildren().add(
                        monthRow
                );
            }

            progressBar.setProgress(
                    initialInvestment / totalInvestment
            );

            int progress =
                    (int) ((initialInvestment / totalInvestment) * 100);

            progressLabel.setText(
                    "Investment Progress: " +
                    progress +
                    "%"
            );

            suggestionText.setText(
                    "For " + selected +
                    ", your remaining investment is ₹" +
                    String.format("%,.0f", remainingInvestment) +
                    ". You need approximately ₹" +
                    String.format("%,.2f", monthlyInvestment) +
                    " per month for " +
                    months +
                    " months."
            );

            errorLabel.setText("");

        } catch (NumberFormatException exception) {

            errorLabel.setText(
                    "Please enter valid investment amounts."
            );
        }
    });

    // =====================================================
    // PAGE LAYOUT
    // =====================================================

    VBox wrapper = new VBox(
            20,
            inputCard,
            summaryCard,
            monthlyCard,
            suggestionCard
    );

    wrapper.setAlignment(Pos.TOP_CENTER);

    ScrollPane scroll = new ScrollPane(wrapper);
    scroll.setFitToWidth(true);
    scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
    );

    scroll.setStyle(
            "-fx-background-color: #080c0d;" +
            "-fx-background: #080c0d;" +
            "-fx-control-inner-background: #080c0d;"
    );

    VBox.setVgrow(
            scroll,
            Priority.ALWAYS
    );

    page.getChildren().addAll(
            topBar,
            scroll
    );

    return page;
}
    // =========================================================
    // SCHEMES PAGE
    // =========================================================

    private VBox createSchemesPage() {

        VBox page =
                createSimplePage(
                        "Schemes & Subsidies",
                        "Explore government schemes and agricultural subsidies."
                );

        VBox card =
                createWhiteCard();

        Label title =
                new Label(
                        "Government & Agriculture Schemes"
                );

        title.setTextFill(DARK_TEXT);

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label scheme1 =
                new Label(
                        "National Livestock Mission (NLM)\n"
                                + "Support for livestock and animal husbandry."
                );

        Label scheme2 =
                new Label(
                        "Sub-Mission on Agricultural Mechanization (SMAM)\n"
                                + "Financial assistance for agricultural machinery."
                );

        Label scheme3 =
                new Label(
                        "Pradhan Mantri Fasal Bima Yojana\n"
                                + "Crop insurance support for farmers."
                );

        scheme1.setTextFill(DARK_TEXT);
        scheme2.setTextFill(DARK_TEXT);
        scheme3.setTextFill(DARK_TEXT);

        scheme1.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        scheme2.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        scheme3.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        card.getChildren().addAll(
                title,
                scheme1,
                scheme2,
                scheme3
        );

        page.getChildren().add(card);

        return page;
    }

    // =========================================================
    // SIMPLE PAGE
    // =========================================================

    private VBox createSimplePage(
            String titleText,
            String subtitleText) {

        VBox page = new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        HBox topBar =
                createTopBar(
                        titleText,
                        subtitleText
                );

        // VBox content = new VBox();

        // content.setPadding(new Insets(30, 35, 35, 35));

        // content.setSpacing(20);

        // ScrollPane scroll =
        //         new ScrollPane(content);

        // scroll.setFitToWidth(true);

        // scroll.setHbarPolicy(
        //         ScrollPane.ScrollBarPolicy.NEVER
        // );

        // scroll.setStyle(
        //         "-fx-background-color: transparent;"
        // );

        VBox content = new VBox();

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#050b0a"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        content.setPadding(new Insets(0, 35, 35, 35));

        content.setSpacing(20);

        ScrollPane scroll =
                new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color: DarkGreen;" +
                "-fx-background: DarkGreen;" +
                "-fx-control-inner-background: DARKGREEN;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        page.getChildren().addAll(
                topBar,
                scroll
        );

        return page;
    }

    // =========================================================
    // FEATURE CARD
    // =========================================================

    private VBox createFeatureCard(
            String icon,
            String title,
            String description) {

        VBox card =
                createWhiteCard();

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        30
                )
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(DARK_TEXT);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setTextFill(GREY);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                descriptionLabel
        );

        return card;
    }

    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card = new VBox();

        card.setSpacing(12);

        card.setPadding(
                new Insets(22)
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.DARKGREEN,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    // =========================================================
    // VERTICAL SPACE
    // =========================================================

    private Region createSpace(
            double height) {

        Region space = new Region();

        space.setMinHeight(height);

        space.setPrefHeight(height);

        space.setMaxHeight(height);

        return space;
    }

    // =========================================================
    // HORIZONTAL SPACE
    // =========================================================

    private Region createWidthSpace(
            double width) {

        Region space = new Region();

        space.setMinWidth(width);

        space.setPrefWidth(width);

        space.setMaxWidth(width);

        return space;
    }
}
