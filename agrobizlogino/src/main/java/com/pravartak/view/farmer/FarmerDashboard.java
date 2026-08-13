package com.pravartak.view.farmer;

import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
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

public class FarmerDashboard {

    private final Stage stage;
    private final Runnable logoutAction;

    //private final LoginPage loginPage;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color DARK_GREEN =
            Color.rgb(18, 82, 24);

    private final Color GREEN =
            Color.rgb(48, 125, 55);

    private final Color LIGHT_GREEN =
            Color.rgb(226, 239, 219);

    private final Color CREAM =
            Color.rgb(248, 249, 232);

    private final Color DARK_TEXT =
            Color.rgb(35, 45, 35);

    private final Color GREY =
            Color.rgb(105, 110, 105);

    // =========================================================
    // SIDEBAR BUTTONS
    // =========================================================

    private Button dashboardButton;
    private Button profileButton;
    private Button aiAdvisorButton;
    private Button learningButton;
    private Button wishlistButton;
    private Button investmentButton;
    private Button schemesButton;

    // =========================================================
    // MAIN BORDER PANE
    // =========================================================

    private BorderPane root;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

        public FarmerDashboard(Stage stage, Runnable logoutAction) {
        this.stage = stage;
        this.logoutAction = logoutAction;
   }


    // =========================================================
    // SCENE
    // =========================================================

    public Scene getDashboardScene() {

        root = new BorderPane();

        root.setPrefSize(
                1368,
                768
        );

        // LEFT SIDEBAR
        VBox sidebar =
                createSidebar();

        root.setLeft(sidebar);

        // DEFAULT PAGE
        root.setCenter(
                createDashboardPage()
        );

        return new Scene(
                root,
                1368,
                768
        );
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox sidebar =
                new VBox();

        sidebar.setPrefWidth(300);
        sidebar.setMinWidth(300);
        sidebar.setMaxWidth(300);

        sidebar.setPadding(
                new Insets(
                        25,
                        20,
                        20,
                        20
                )
        );

        sidebar.setSpacing(7);

        sidebar.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =====================================================
        // LOGO
        // =====================================================

        Label logo =
                new Label(
                        "🌱  Agro Biz"
                );

        logo.setTextFill(
                Color.WHITE
        );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        sidebar.getChildren().add(logo);

        sidebar.getChildren().add(
                createSpace(30)
        );

        // =====================================================
        // MENU TITLE
        // =====================================================

        Label menu =
                new Label(
                        "FARMER MENU"
                );

        menu.setTextFill(
                Color.rgb(
                        175,
                        210,
                        175
                )
        );

        menu.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        menu.setPadding(
                new Insets(
                        0,
                        0,
                        8,
                        15
                )
        );

        sidebar.getChildren().add(menu);

        // =====================================================
        // DASHBOARD
        // =====================================================

        dashboardButton =
                createMenuButton(
                        "⌂",
                        "Dashboard"
                );

        dashboardButton.setOnAction(
                event -> showPage("dashboard")
        );

        // =====================================================
        // PROFILE
        // =====================================================

        profileButton =
                createMenuButton(
                        "♟",
                        "Profile"
                );

        profileButton.setOnAction(
                event -> showPage("profile")
        );

        // =====================================================
        // AI FARMING ADVISOR
        // =====================================================

        aiAdvisorButton =
                createMenuButton(
                        "✦",
                        "AI Farming Advisor"
                );

        aiAdvisorButton.setOnAction(
                event -> showPage("ai")
        );

        // =====================================================
        // MY LEARNING
        // =====================================================

        learningButton =
                createMenuButton(
                        "▣",
                        "My Learning"
                );

        learningButton.setOnAction(
                event -> showPage("learning")
        );

        // =====================================================
        // WISHLIST
        // =====================================================

        wishlistButton =
                createMenuButton(
                        "♙",
                        "Wishlist"
                );

        wishlistButton.setOnAction(
                event -> showPage("wishlist")
        );

        // =====================================================
        // INVESTMENT CALCULATOR
        // =====================================================

        investmentButton =
                createMenuButton(
                        "₹",
                        "Investment Calculator"
                );

        investmentButton.setOnAction(
                event -> showPage("investment")
        );

        // =====================================================
        // SCHEMES
        // =====================================================

        schemesButton =
                createMenuButton(
                        "◇",
                        "Schemes & Subsidies"
                );

        schemesButton.setOnAction(
                event -> showPage("schemes")
        );

        // =====================================================
        // ADD BUTTONS
        // =====================================================

        sidebar.getChildren().addAll(
                dashboardButton,
                profileButton,
                aiAdvisorButton,
                learningButton,
                wishlistButton,
                investmentButton,
                schemesButton
        );

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(spacer);

        // =====================================================
        // LOGOUT
        // =====================================================

        Button logout =
                createMenuButton(
                        "↪",
                        "Logout"
                );

        logout.setOnAction(event -> {

                logoutAction.run();

        });
        sidebar.getChildren().add(logout);

        // Dashboard selected by default
        setSelectedMenuButton(
                dashboardButton
        );

        return sidebar;
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    private Button createMenuButton(
            String icon,
            String text) {

        Button button =
                new Button(
                        icon + "    " + text
                );

        button.setPrefHeight(55);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(
                        0,
                        14,
                        0,
                        14
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        button.setTextFill(
                Color.rgb(
                        235,
                        245,
                        235
                )
        );

        button.setBackground(
                Background.EMPTY
        );

        button.setBorder(
                Border.EMPTY
        );

        return button;
    }

    // =========================================================
    // SELECTED BUTTON
    // =========================================================

    private void setSelectedMenuButton(
            Button selectedButton) {

        Button[] buttons = {

                dashboardButton,
                profileButton,
                aiAdvisorButton,
                learningButton,
                wishlistButton,
                investmentButton,
                schemesButton
        };

        for (Button button : buttons) {

            if (button == null) {
                continue;
            }

            button.setTextFill(
                    Color.rgb(
                            235,
                            245,
                            235
                    )
            );

            button.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            14
                    )
            );

            button.setBackground(
                    Background.EMPTY
            );
        }

        // SELECTED BUTTON
        selectedButton.setTextFill(
                DARK_GREEN
        );

        selectedButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        selectedButton.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );
    }

    // =========================================================
    // PAGE NAVIGATION
    // =========================================================

    private void showPage(
            String page) {

        if (page.equals("dashboard")) {

            setSelectedMenuButton(
                    dashboardButton
            );

            root.setCenter(
                    createDashboardPage()
            );

        } else if (page.equals("profile")) {

            setSelectedMenuButton(
                    profileButton
            );

            root.setCenter(
                    createProfilePage()
            );

        } else if (page.equals("ai")) {

            setSelectedMenuButton(
                    aiAdvisorButton
            );

            root.setCenter(
                    createAIAdvisorPage()
            );

        } else if (page.equals("learning")) {

            setSelectedMenuButton(
                    learningButton
            );

            root.setCenter(
                    createLearningPage()
            );

        } else if (page.equals("wishlist")) {

            setSelectedMenuButton(
                    wishlistButton
            );

            root.setCenter(
                    createWishlistPage()
            );

        } else if (page.equals("investment")) {

            setSelectedMenuButton(
                    investmentButton
            );

            root.setCenter(
                    createInvestmentPage()
            );

        } else if (page.equals("schemes")) {

            setSelectedMenuButton(
                    schemesButton
            );

            root.setCenter(
                    createSchemesPage()
            );
        }
    }

    // =========================================================
    // DASHBOARD PAGE
    // =========================================================

    private VBox createDashboardPage() {

        VBox main =
                new VBox();

        main.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        HBox topBar =
                createTopBar(
                        "Farmer Dashboard",
                        "Manage your farm and make smarter decisions."
                );

        VBox content =
                createDashboardContent();

        ScrollPane scroll =
                new ScrollPane(
                        content
                );

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color: transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                topBar,
                scroll
        );

        return main;
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    private HBox createTopBar(
            String titleText,
            String subtitleText) {

        HBox bar =
                new HBox();

        bar.setPrefHeight(100);

        bar.setPadding(
                new Insets(
                        18,
                        35,
                        18,
                        35
                )
        );

        bar.setAlignment(
                Pos.CENTER_LEFT
        );

        bar.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        VBox titleBox =
                new VBox();

        titleBox.setSpacing(3);

        Label title =
                new Label(titleText);

        title.setTextFill(
                DARK_TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        Label subtitle =
                new Label(subtitleText);

        subtitle.setTextFill(
                GREY
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label notification =
                new Label("🔔");

        notification.setPrefSize(
                55,
                55
        );

        notification.setAlignment(
                Pos.CENTER
        );

        notification.setFont(
                Font.font(
                        "Arial",
                        20
                )
        );

        notification.setBackground(
                new Background(
                        new BackgroundFill(
                                LIGHT_GREEN,
                                new CornerRadii(12),
                                Insets.EMPTY
                        )
                )
        );

        Label profile =
                new Label("G");

        profile.setPrefSize(
                55,
                55
        );

        profile.setAlignment(
                Pos.CENTER
        );

        profile.setTextFill(
                Color.WHITE
        );

        profile.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        profile.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(50),
                                Insets.EMPTY
                        )
                )
        );

        Label farmer =
                new Label("Farmer");

        farmer.setTextFill(
                DARK_TEXT
        );

        farmer.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label role =
                new Label("Farm Owner");

        role.setTextFill(
                GREY
        );

        role.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        VBox userText =
                new VBox(
                        farmer,
                        role
                );

        userText.setSpacing(2);

        HBox user =
                new HBox(
                        profile,
                        userText
                );

        user.setSpacing(10);

        user.setAlignment(
                Pos.CENTER_LEFT
        );

        bar.getChildren().addAll(
                titleBox,
                spacer,
                notification,
                createWidthSpace(25),
                user
        );

        return bar;
    }

    // =========================================================
    // DASHBOARD CONTENT
    // =========================================================

    private VBox createDashboardContent() {

        VBox content =
                new VBox();

        content.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        content.setSpacing(22);

        content.getChildren().add(
                createWelcomeCard()
        );

        HBox stats =
                new HBox();

        stats.setSpacing(20);

        stats.getChildren().addAll(

                createStat(
                        "🌾",
                        "Active Crops",
                        "4",
                        "Currently growing"
                ),

                createStat(
                        "▰",
                        "Expected Yield",
                        "18.5 T",
                        "+12% this season"
                ),

                createStat(
                        "₹",
                        "Farm Revenue",
                        "₹2.84L",
                        "+8.4% this month"
                ),

                createStat(
                        "●",
                        "Farm Health",
                        "92%",
                        "Excellent condition"
                )
        );

        content.getChildren().add(stats);

        HBox lower =
                new HBox();

        lower.setSpacing(22);

        VBox crops =
                createCropsCard();

        VBox activity =
                createActivityCard();

        HBox.setHgrow(
                crops,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activity,
                Priority.ALWAYS
        );

        lower.getChildren().addAll(
                crops,
                activity
        );

        content.getChildren().add(
                lower
        );

        content.getChildren().add(
                createQuickActions()
        );

        return content;
    }

    // =========================================================
    // WELCOME CARD
    // =========================================================

    private HBox createWelcomeCard() {

        HBox card =
                new HBox();

        card.setPadding(
                new Insets(
                        30,
                        35,
                        30,
                        35
                )
        );

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(17),
                                Insets.EMPTY
                        )
                )
        );

        VBox text =
                new VBox();

        text.setSpacing(7);

        Label title =
                new Label(
                        "Good evening, Farmer! 🌱"
                );

        title.setTextFill(
                Color.WHITE
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        29
                )
        );

        Label description =
                new Label(
                        "Your farm is looking healthy. "
                        + "Let's grow something amazing today."
                );

        description.setTextFill(
                Color.rgb(
                        215,
                        235,
                        215
                )
        );

        description.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        text.getChildren().addAll(
                title,
                description
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label plant =
                new Label("🌿");

        plant.setFont(
                Font.font(
                        "Arial",
                        60
                )
        );

        card.getChildren().addAll(
                text,
                spacer,
                plant
        );

        return card;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStat(
            String icon,
            String title,
            String value,
            String subtitle) {

        VBox card =
                new VBox();

        card.setSpacing(8);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(160);

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefSize(
                48,
                48
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                LIGHT_GREEN,
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                GREY
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        HBox top =
                new HBox(
                        iconLabel,
                        createWidthSpace(12),
                        titleLabel
                );

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setTextFill(
                DARK_TEXT
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        Label sub =
                new Label(subtitle);

        sub.setTextFill(
                GREEN
        );

        sub.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        card.getChildren().addAll(
                top,
                valueLabel,
                sub
        );

        return card;
    }

    // =========================================================
    // CROPS CARD
    // =========================================================

    private VBox createCropsCard() {

        VBox card =
                createWhiteCard();

        Label title =
                new Label("My Crops");

        title.setTextFill(
                DARK_TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        card.getChildren().add(title);

        card.getChildren().add(
                createCrop(
                        "🌾",
                        "Wheat",
                        "Growing",
                        "65%"
                )
        );

        card.getChildren().add(
                createCrop(
                        "🌱",
                        "Soybean",
                        "Healthy",
                        "82%"
                )
        );

        card.getChildren().add(
                createCrop(
                        "🥬",
                        "Vegetables",
                        "Growing",
                        "48%"
                )
        );

        return card;
    }

    // =========================================================
    // CROP
    // =========================================================

    private HBox createCrop(
            String icon,
            String name,
            String status,
            String progress) {

        HBox row =
                new HBox();

        row.setSpacing(14);

        row.setPadding(
                new Insets(
                        14,
                        0,
                        8,
                        0
                )
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );

        VBox text =
                new VBox();

        Label nameLabel =
                new Label(name);

        nameLabel.setTextFill(
                DARK_TEXT
        );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label statusLabel =
                new Label(status);

        statusLabel.setTextFill(
                GREEN
        );

        statusLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        text.getChildren().addAll(
                nameLabel,
                statusLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label percentage =
                new Label(progress);

        percentage.setTextFill(
                DARK_TEXT
        );

        percentage.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        row.getChildren().addAll(
                iconLabel,
                text,
                spacer,
                percentage
        );

        return row;
    }

    // =========================================================
    // ACTIVITY CARD
    // =========================================================

    private VBox createActivityCard() {

        VBox card =
                createWhiteCard();

        Label title =
                new Label(
                        "Recent Activity"
                );

        title.setTextFill(
                DARK_TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        card.getChildren().add(title);

        card.getChildren().add(
                createActivity(
                        "✓",
                        "Crop health updated",
                        "Wheat field looks healthy",
                        "2h ago"
                )
        );

        card.getChildren().add(
                createActivity(
                        "₹",
                        "Marketplace opportunity",
                        "Organic wheat buyer nearby",
                        "5h ago"
                )
        );

        card.getChildren().add(
                createActivity(
                        "✦",
                        "AI recommendation",
                        "Consider irrigation tomorrow",
                        "Yesterday"
                )
        );

        return card;
    }

    // =========================================================
    // ACTIVITY
    // =========================================================

    private HBox createActivity(
            String icon,
            String title,
            String description,
            String time) {

        HBox row =
                new HBox();

        row.setSpacing(12);

        row.setPadding(
                new Insets(
                        12,
                        0,
                        8,
                        0
                )
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefSize(
                40,
                40
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setTextFill(
                GREEN
        );

        iconLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                LIGHT_GREEN,
                                new CornerRadii(9),
                                Insets.EMPTY
                        )
                )
        );

        VBox text =
                new VBox();

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                DARK_TEXT
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setTextFill(
                GREY
        );

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        text.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setTextFill(
                GREY
        );

        timeLabel.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        row.getChildren().addAll(
                iconLabel,
                text,
                spacer,
                timeLabel
        );

        return row;
    }

    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    private HBox createQuickActions() {

        HBox actions =
                new HBox();

        actions.setSpacing(18);

        Button ai =
                createAction(
                        "✦  Ask AI Advisor"
                );

        ai.setOnAction(
                event -> showPage("ai")
        );

        Button learning =
                createAction(
                        "▣  My Learning"
                );

        learning.setOnAction(
                event -> showPage("learning")
        );

        Button investment =
                createAction(
                        "₹  Investment Calculator"
                );

        investment.setOnAction(
                event -> showPage("investment")
        );

        Button schemes =
                createAction(
                        "◇  Schemes"
                );

        schemes.setOnAction(
                event -> showPage("schemes")
        );

        actions.getChildren().addAll(
                ai,
                learning,
                investment,
                schemes
        );

        return actions;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private Button createAction(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(55);

        HBox.setHgrow(
                button,
                Priority.ALWAYS
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setTextFill(
                DARK_GREEN
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(11),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        215,
                                        225,
                                        210
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(11),
                                new BorderWidths(1)
                        )
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        return button;
    }

    // =========================================================
    // PROFILE PAGE
    // =========================================================

    private VBox createProfilePage() {

        VBox main =
                new VBox();

        main.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        HBox topBar =
                createTopBar(
                        "Farmer Profile",
                        "Manage your personal and farming information."
                );

        VBox content =
                new VBox();

        content.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        content.setSpacing(22);

        // =====================================================
        // PROFILE HEADER
        // =====================================================

        HBox profileHeader =
                new HBox();

        profileHeader.setPadding(
                new Insets(25)
        );

        profileHeader.setSpacing(20);

        profileHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        profileHeader.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        Label profileCircle =
                new Label("G");

        profileCircle.setPrefSize(
                80,
                80
        );

        profileCircle.setAlignment(
                Pos.CENTER
        );

        profileCircle.setTextFill(
                Color.WHITE
        );

        profileCircle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        profileCircle.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(50),
                                Insets.EMPTY
                        )
                )
        );

        VBox profileText =
                new VBox();

        profileText.setSpacing(5);

        Label name =
                new Label(
                        "Farmer"
                );

        name.setTextFill(
                DARK_TEXT
        );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        Label role =
                new Label(
                        "Farm Owner"
                );

        role.setTextFill(
                GREEN
        );

        role.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        Label description =
                new Label(
                        "Manage your profile and farming information."
                );

        description.setTextFill(
                GREY
        );

        description.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        profileText.getChildren().addAll(
                name,
                role,
                description
        );

        Region profileSpacer =
                new Region();

        HBox.setHgrow(
                profileSpacer,
                Priority.ALWAYS
        );

        Button edit =
                new Button(
                        "Edit Profile"
                );

        edit.setPrefHeight(42);

        edit.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        edit.setTextFill(
                Color.WHITE
        );

        edit.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        edit.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(8),
                                Insets.EMPTY
                        )
                )
        );

        profileHeader.getChildren().addAll(
                profileCircle,
                profileText,
                profileSpacer,
                edit
        );

        // =====================================================
        // PERSONAL INFORMATION
        // =====================================================

        VBox personalCard =
                createWhiteCard();

        Label personalTitle =
                new Label(
                        "Personal Information"
                );

        personalTitle.setTextFill(
                DARK_TEXT
        );

        personalTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        GridPane personalGrid =
                new GridPane();

        personalGrid.setHgap(25);
        personalGrid.setVgap(18);

        personalGrid.add(
                createProfileField(
                        "Full Name",
                        "Farmer"
                ),
                0,
                0
        );

        personalGrid.add(
                createProfileField(
                        "Email",
                        "farmer@example.com"
                ),
                1,
                0
        );

        personalGrid.add(
                createProfileField(
                        "Phone Number",
                        "+91 XXXXX XXXXX"
                ),
                0,
                1
        );

        personalGrid.add(
                createProfileField(
                        "Location",
                        "Maharashtra, India"
                ),
                1,
                1
        );

        ColumnConstraintsHelper(
                personalGrid
        );

        personalCard.getChildren().addAll(
                personalTitle,
                personalGrid
        );

        // =====================================================
        // FARM INFORMATION
        // =====================================================

        VBox farmCard =
                createWhiteCard();

        Label farmTitle =
                new Label(
                        "Farm Information"
                );

        farmTitle.setTextFill(
                DARK_TEXT
        );

        farmTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        GridPane farmGrid =
                new GridPane();

        farmGrid.setHgap(25);
        farmGrid.setVgap(18);

        farmGrid.add(
                createProfileField(
                        "Farm Name",
                        "Green Valley Farm"
                ),
                0,
                0
        );

        farmGrid.add(
                createProfileField(
                        "Farm Area",
                        "15.6 Acres"
                ),
                1,
                0
        );

        farmGrid.add(
                createProfileField(
                        "Primary Crops",
                        "Wheat, Soybean, Vegetables"
                ),
                0,
                1
        );

        farmGrid.add(
                createProfileField(
                        "Farming Type",
                        "Mixed Farming"
                ),
                1,
                1
        );

        ColumnConstraintsHelper(
                farmGrid
        );

        farmCard.getChildren().addAll(
                farmTitle,
                farmGrid
        );

        content.getChildren().addAll(
                profileHeader,
                personalCard,
                farmCard
        );

        ScrollPane scroll =
                new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color: transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                topBar,
                scroll
        );

        return main;
    }

    // =========================================================
    // PROFILE FIELD
    // =========================================================

    private VBox createProfileField(
            String title,
            String value) {

        VBox box =
                new VBox();

        box.setSpacing(6);

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                GREY
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        TextField field =
                new TextField(value);

        field.setPrefHeight(42);

        field.setEditable(false);

        field.setStyle(
                "-fx-background-color: #F5F7F3;"
                + "-fx-background-radius: 8;"
                + "-fx-border-color: #E0E6DC;"
                + "-fx-border-radius: 8;"
                + "-fx-padding: 0 12;"
                + "-fx-font-size: 13px;"
        );

        box.getChildren().addAll(
                titleLabel,
                field
        );

        return box;
    }

    // =========================================================
    // GRID WIDTH
    // =========================================================

    private void ColumnConstraintsHelper(
            GridPane grid) {

        javafx.scene.layout.ColumnConstraints c1 =
                new javafx.scene.layout.ColumnConstraints();

        javafx.scene.layout.ColumnConstraints c2 =
                new javafx.scene.layout.ColumnConstraints();

        c1.setPercentWidth(50);
        c2.setPercentWidth(50);

        grid.getColumnConstraints().addAll(
                c1,
                c2
        );
    }

    // =========================================================
    // AI ADVISOR PAGE
    // =========================================================

    private VBox createAIAdvisorPage() {

        VBox page =
                createSimplePage(
                        "AI Farming Advisor",
                        "Get intelligent recommendations for your farm."
                );

        Label message =
                new Label(
                        "AI Farming Advisor\n\n"
                        + "Get recommendations about crops, irrigation, "
                        + "fertilizers, soil management and farm planning."
                );

        message.setTextFill(
                DARK_TEXT
        );

        message.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        message.setWrapText(true);

        VBox card =
                createWhiteCard();

        card.getChildren().add(message);

        page.getChildren().add(card);

        return page;
    }

    // =========================================================
    // LEARNING PAGE
    // =========================================================

    private VBox createLearningPage() {

        VBox page =
                createSimplePage(
                        "My Learning",
                        "Learn farming techniques and improve your knowledge."
                );

        HBox cards =
                new HBox();

        cards.setSpacing(20);

        cards.getChildren().addAll(
                createFeatureCard(
                        "🌾",
                        "Crop Management",
                        "Learn modern crop management techniques."
                ),

                createFeatureCard(
                        "💧",
                        "Irrigation",
                        "Understand efficient irrigation methods."
                ),

                createFeatureCard(
                        "🌱",
                        "Organic Farming",
                        "Learn sustainable organic farming."
                )
        );

        page.getChildren().add(cards);

        return page;
    }

    // =========================================================
    // WISHLIST PAGE
    // =========================================================

    private VBox createWishlistPage() {

        VBox page =
                createSimplePage(
                        "Wishlist",
                        "Your saved farming products and resources."
                );

        VBox card =
                createWhiteCard();

        Label title =
                new Label(
                        "Saved Items"
                );

        title.setTextFill(
                DARK_TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label empty =
                new Label(
                        "Your wishlist is currently empty."
                );

        empty.setTextFill(
                GREY
        );

        card.getChildren().addAll(
                title,
                empty
        );

        page.getChildren().add(card);

        return page;
    }

    // =========================================================
    // INVESTMENT PAGE
    // =========================================================

    private VBox createInvestmentPage() {

        VBox page =
                createSimplePage(
                        "Investment Calculator",
                        "Estimate your farming investment and returns."
                );

        VBox card =
                createWhiteCard();

        Label title =
                new Label(
                        "Farm Investment Calculator"
                );

        title.setTextFill(
                DARK_TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        TextField investment =
                new TextField();

        investment.setPromptText(
                "Enter investment amount"
        );

        investment.setPrefHeight(45);

        Button calculate =
                new Button(
                        "Calculate"
                );

        calculate.setPrefHeight(45);

        calculate.setTextFill(
                Color.WHITE
        );

        calculate.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(8),
                                Insets.EMPTY
                        )
                )
        );

        Label result =
                new Label(
                        "Estimated return will appear here."
                );

        result.setTextFill(
                GREY
        );

        calculate.setOnAction(
                event -> {

                    if (!investment.getText().isEmpty()) {

                        result.setText(
                                "Calculation feature ready for implementation."
                        );
                    }
                }
        );

        card.getChildren().addAll(
                title,
                investment,
                calculate,
                result
        );

        page.getChildren().add(card);

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

        title.setTextFill(
                DARK_TEXT
        );

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
                Font.font("Arial", 14)
        );

        scheme2.setFont(
                Font.font("Arial", 14)
        );

        scheme3.setFont(
                Font.font("Arial", 14)
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

        VBox page =
                new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
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

        VBox content =
                new VBox();

        content.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        content.setSpacing(20);

        ScrollPane scroll =
                new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color: transparent;"
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

        titleLabel.setTextFill(
                DARK_TEXT
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setTextFill(
                GREY
        );

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

        VBox card =
                new VBox();

        card.setSpacing(12);

        card.setPadding(
                new Insets(22)
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
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

        Region space =
                new Region();

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

        Region space =
                new Region();

        space.setMinWidth(width);
        space.setPrefWidth(width);
        space.setMaxWidth(width);

        return space;
    }
}