package com.pravartak.view.farmer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import com.pravartak.controller.farmercontoller.FarmerProfileController;
import com.pravartak.dao.UserDAO;
import com.pravartak.model.UserModel;
import com.pravartak.model.admin.Scheme;
import com.pravartak.model.farmer_model.FarmerProfile;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;


/**
 * Farmer Dashboard
 *
 * AI Farming Advisor has been moved to:
 *
 *     PlanGenerator.java
 *
 * FarmerDashboard only opens PlanGenerator when
 * "AI Farming Advisor" is clicked.
 */
public class FarmerDashboard {

    // =========================================================
    // FARMER INFORMATION
    // =========================================================

    private final int farmerId;
    private final String firebaseUid;

    private final FarmerProfileController profileController;
    private final UserDAO userDAO;

    private String selectedProfileImageBase64;

    // =========================================================
    // PROFILE FIELDS
    // =========================================================

    private TextField nameField;
    private TextField emailField;
    private TextField phoneField;

    private TextField addressField;
    private TextField villageField;
    private TextField districtField;
    private TextField stateField;

    private TextField farmNameField;
    private TextField farmAreaField;

    private ComboBox<String> farmingTypeBox;

    private TextField primaryCropsField;

    private String imageBase64 = "";

    // =========================================================
    // COLORS
    // =========================================================

    private final Color DARK_GREEN =
            Color.web("#050B0A");

    private static final Color GREEN =
            Color.web("#7ED957");

    private final Color LIGHT_GREEN =
            Color.web("#B8D8B8");

    private final Color CREAM =
            Color.web("#0B1F14");

    private final Color DARK_TEXT =
            Color.web("#F3F8F3");

    private final Color GREY =
            Color.web("#A9B8AC");

    private final Color CARD_BACKGROUND =
            Color.web("#15331F");

    private final Color BORDER_COLOR =
            Color.web("#294734");

    // =========================================================
    // SIDEBAR BUTTONS
    // =========================================================

    private Button homepageButton;
    private Button dashboardButton;
    private Button profileButton;
    private Button aiAdvisorButton;
    private Button investmentButton;
    private Button schemesButton;
    private Button reviewsButton;

    private Button orderRequestsButton;
    private Button myOrdersButton;

    // =========================================================
    // MAIN ROOT
    // =========================================================

    private BorderPane root;

    private StackPane profileImageContainer;

    // =========================================================
    // PLAN GENERATOR
    // =========================================================

    /*
     * IMPORTANT:
     *
     * AI code is NOT inside FarmerDashboard anymore.
     *
     * PlanGenerator.java contains:
     * - AI Advisor UI
     * - Farming type selection
     * - Farming questions
     * - Groq API
     * - Farming plan generation
     *
     * FarmerDashboard only creates this object.
     */
    private final PlanGenerator planGenerator;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public FarmerDashboard(
            int farmerId,
            String firebaseUid) {

        if (farmerId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid farmer ID: " + farmerId);
        }

        if (firebaseUid == null ||
                firebaseUid.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Firebase UID is missing.");
        }

        this.farmerId = farmerId;

        this.firebaseUid = firebaseUid;

        this.profileController =
                new FarmerProfileController();

        this.userDAO =
                new UserDAO();

        /*
         * AI PAGE
         *
         * The old:
         *
         * GroqService
         * farmingPlanAnswers
         * farming questions
         * createAIAdvisorPage()
         *
         * are no longer here.
         */
        this.planGenerator =
                new PlanGenerator();

        System.out.println(
                "Farmer ID = " + this.farmerId);

        System.out.println(
                "Firebase UID = " + this.firebaseUid);
    }


    // =========================================================
    // GET FARMER ID
    // =========================================================

    public int getFarmerId() {

        return farmerId;
    }


    // =========================================================
    // GET FIREBASE UID
    // =========================================================

    public String getFirebaseUid() {

        return firebaseUid;
    }


    // =========================================================
    // DASHBOARD SCENE
    // =========================================================

    public Scene getDashboardScene() {

        root = new BorderPane();

        root.setPrefSize(
                1368,
                768);

        // LEFT SIDEBAR
        VBox sidebar =
                createSidebar();

        root.setLeft(sidebar);

        // DEFAULT PAGE
        root.setCenter(
                createDashboardPage());

        return new Scene(
                root,
                1368,
                768);
    }
    // =========================================================
// OPEN AI ADVISOR FROM HOMEPAGE
// =========================================================

public Scene getAIAdvisorScene() {

    root = new BorderPane();

    root.setPrefSize(
            1368,
            768
    );

    // Create the normal dashboard sidebar
    VBox sidebar = createSidebar();

    root.setLeft(sidebar);

    // Select AI Advisor in sidebar
    setSelectedMenuButton(
            aiAdvisorButton
    );

    // Open AI Advisor page
  root.setCenter(
        planGenerator.getPage()
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
                        20));

        sidebar.setSpacing(7);

        sidebar.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#0A1710"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));

        // =====================================================
        // LOGO
        // =====================================================

        Label logo =
                new Label("🌱  Agro Biz");

        logo.setTextFill(
                Color.WHITE);

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28));

        sidebar.getChildren()
                .add(logo);

        sidebar.getChildren()
                .add(
                        createSpace(30));


        // =====================================================
        // MENU TITLE
        // =====================================================

        Label menu =
                new Label("FARMER MENU");

        menu.setTextFill(
                Color.rgb(
                        175,
                        210,
                        175));

        menu.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        menu.setPadding(
                new Insets(
                        0,
                        0,
                        8,
                        15));

        sidebar.getChildren()
                .add(menu);


        // =====================================================
        // HOME
        // =====================================================

        homepageButton =
                createMenuButton(
                        "⌂",
                        "Home");

        homepageButton.setOnAction(
                event -> {

                    HomePageFarmer homePageFarmer =
                            new HomePageFarmer(
                                    farmerId,
                                    firebaseUid);

                    LoginPage.mainStage.setScene(
                            homePageFarmer
                                    .getHomePageFarmer());
                });


        // =====================================================
        // DASHBOARD
        // =====================================================

        dashboardButton =
                createMenuButton(
                        "⌂",
                        "Dashboard");

        dashboardButton.setOnAction(
                event ->
                        showPage("dashboard"));


        // =====================================================
        // PROFILE
        // =====================================================

        profileButton =
                createMenuButton(
                        "♟",
                        "Profile");

        profileButton.setOnAction(
                event ->
                        showPage("profile"));


        // =====================================================
        // AI FARMING ADVISOR
        // =====================================================

        aiAdvisorButton =
                createMenuButton(
                        "✦",
                        "AI Farming Advisor");

        aiAdvisorButton.setOnAction(
                event ->
                        showPage("ai"));


        // =====================================================
        // INVESTMENT
        // =====================================================

        investmentButton =
                createMenuButton(
                        "₹",
                        "Investment Calculator");

        investmentButton.setOnAction(
                event ->
                        showPage("investment"));


        // =====================================================
        // SCHEMES
        // =====================================================

        schemesButton =
                createMenuButton(
                        "◇",
                        "Schemes & Subsidies");

        schemesButton.setOnAction(
                event ->
                        showPage("schemes"));


        // =====================================================
        // ORDER REQUESTS
        // =====================================================

        orderRequestsButton =
                createMenuButton(
                        "📦",
                        "Order Requests");

        orderRequestsButton.setOnAction(
                event ->
                        showPage("orderRequests"));


        // =====================================================
        // MY ORDERS
        // =====================================================

        myOrdersButton =
                createMenuButton(
                        "📋",
                        "My Orders");

        myOrdersButton.setOnAction(
                event ->
                        showPage("myOrders"));


        // =====================================================
        // CUSTOMER REVIEWS
        // =====================================================

        reviewsButton =
                createMenuButton(
                        "⭐",
                        "Customer Reviews");

        reviewsButton.setOnAction(
                event ->
                        showPage("reviews"));


        // =====================================================
        // ADD BUTTONS
        // =====================================================

        sidebar.getChildren().addAll(

                homepageButton,

                dashboardButton,

                profileButton,

                aiAdvisorButton,

                investmentButton,

                schemesButton,

                

                myOrdersButton,

                reviewsButton
        );


        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS);

        sidebar.getChildren()
                .add(spacer);


        // =====================================================
        // LOGOUT
        // =====================================================

        Button logout =
                new Button(
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

        logout.setOnAction(
                e -> {

                    try {

                        LoginPage loginPage =
                                new LoginPage();

                        loginPage.start(
                                LoginPage.mainStage);

                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }
                });


        logout.setOnMouseEntered(
                event -> {

                    logout.setStyle(
                            "-fx-background-color:#4A2525;" +
                            "-fx-text-fill:#E57373;" +
                            "-fx-font-size:14px;" +
                            "-fx-font-weight:bold;" +
                            "-fx-background-radius:6;" +
                            "-fx-cursor:hand;");
                });


        logout.setOnMouseExited(
                event -> {

                    logout.setStyle(
                            "-fx-background-color:transparent;" +
                            "-fx-text-fill:#E57373;" +
                            "-fx-font-size:14px;" +
                            "-fx-font-weight:bold;" +
                            "-fx-background-radius:6;" +
                            "-fx-cursor:hand;");
                });


        /*
         * Keep the same behavior as your uploaded code.
         *
         * If you want Logout visible, uncomment:
         *
         * sidebar.getChildren().add(logout);
         */


        // Dashboard selected by default

        setSelectedMenuButton(
                dashboardButton);

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
                        icon + "    " + text);

        button.setPrefHeight(55);

        button.setMaxWidth(
                Double.MAX_VALUE);

        button.setAlignment(
                Pos.CENTER_LEFT);

        button.setPadding(
                new Insets(
                        0,
                        14,
                        0,
                        14));

        button.setCursor(
                Cursor.HAND);

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14));

        button.setTextFill(
                Color.rgb(
                        235,
                        245,
                        235));

        button.setBackground(
                Background.EMPTY);

        button.setBorder(
                Border.EMPTY);

        return button;
    }


    // =========================================================
    // SELECTED MENU BUTTON
    // =========================================================

    private void setSelectedMenuButton(
            Button selectedButton) {

        Button[] buttons = {

                homepageButton,

                dashboardButton,

                profileButton,

                aiAdvisorButton,

                investmentButton,

                schemesButton,

                orderRequestsButton,

                myOrdersButton,

                reviewsButton
        };


        for (Button button : buttons) {

            if (button == null) {
                continue;
            }

            button.setTextFill(
                    Color.rgb(
                            235,
                            245,
                            235));

            button.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            14));

            button.setBackground(
                    Background.EMPTY);
        }


        if (selectedButton == null) {
            return;
        }


        selectedButton.setTextFill(
                Color.WHITE);

        selectedButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));


        selectedButton.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(10),
                                Insets.EMPTY)));
    }


    // =========================================================
    // PAGE NAVIGATION
    // =========================================================

    private void showPage(
            String page) {

        switch (page) {

            // =================================================
            // DASHBOARD
            // =================================================

            case "dashboard":

                setSelectedMenuButton(
                        dashboardButton);

                root.setCenter(
                        createDashboardPage());

                break;


            // =================================================
            // PROFILE
            // =================================================

            case "profile":

                setSelectedMenuButton(
                        profileButton);

                root.setCenter(
                        createProfileView());

                break;


            // =================================================
            // EDIT PROFILE
            // =================================================

            case "editProfile":

                setSelectedMenuButton(
                        profileButton);

                root.setCenter(
                        createProfilePage());

                break;


            // =================================================
            // AI FARMING ADVISOR
            // =================================================

            case "ai":

                setSelectedMenuButton(
                        aiAdvisorButton);

                /*
                 * IMPORTANT:
                 *
                 * AI page is now completely separated.
                 *
                 * OLD:
                 *
                 * createAIAdvisorPage()
                 *
                 * NEW:
                 *
                 * planGenerator.getPage()
                 */

                root.setCenter(
                        planGenerator.getPage());

                break;


            // =================================================
            // INVESTMENT
            // =================================================

            case "investment":

                setSelectedMenuButton(
                        investmentButton);

                root.setCenter(
                        createInvestmentPage());

                break;


            // =================================================
            // SCHEMES
            // =================================================

            case "schemes":

                setSelectedMenuButton(
                        schemesButton);

                root.setCenter(
                        createSavedSchemesSection());

                break;


            // =================================================
            // ORDER REQUESTS
            // =================================================

            case "orderRequests":

                setSelectedMenuButton(
                        orderRequestsButton);

                root.setCenter(
                        new FarmerOrderRequestsPage(
                                farmerId)
                                .getOrderRequestsPage());

                break;


            // =================================================
            // REVIEWS
            // =================================================

            case "reviews":

                setSelectedMenuButton(
                        reviewsButton);

                root.setCenter(
                        new FarmerReviewsPage(
                                farmerId)
                                .getReviewsPage());

                break;


            // =================================================
            // MY ORDERS
            // =================================================

            case "myOrders":

                setSelectedMenuButton(
                        myOrdersButton);

                root.setCenter(
                        new FarmerOrdersPage(
                                farmerId)
                                .getOrdersPage());

                break;


            // =================================================
            // HOMEPAGE
            // =================================================

            case "Homepage":

                setSelectedMenuButton(
                        homepageButton);

                HomePageFarmer homePageFarmer =
                        new HomePageFarmer(
                                farmerId,
                                firebaseUid);

                LoginPage.mainStage.setScene(
                        homePageFarmer
                                .getHomePageFarmer());

                break;


            // =================================================
            // DEFAULT
            // =================================================

            default:

                setSelectedMenuButton(
                        dashboardButton);

                root.setCenter(
                        createDashboardPage());

                break;
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
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox topBar =
                createTopBar(
                        "Farmer Dashboard",
                        "Manage your farm and make smarter decisions.");


        VBox content =
                createDashboardContent();

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        ScrollPane scroll =
                new ScrollPane(
                        content);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-border-color:transparent;" +
                "-fx-focus-color:transparent;" +
                "-fx-faint-focus-color:transparent;");

        scroll.setFocusTraversable(
                false);

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);


        main.getChildren().addAll(
                topBar,
                scroll);

        return main;
    }


    // =========================================================
    // DASHBOARD CONTENT
    // =========================================================

//     private VBox createDashboardContent() {

//        FarmerDashboardHome dashboardHome =
//         new FarmerDashboardHome(
//                 farmerId,
//                 farmerName
//         );

//         return dashboardHome.getDashboardHome();
//     }
private VBox createDashboardContent() {

    String farmerName = "Farmer";

    try {

        // Get name from saved farmer profile
        FarmerProfile profile =
                profileController.getProfile(
                        farmerId
                );

        if (profile != null &&
                profile.getName() != null &&
                !profile.getName().trim().isEmpty()) {

            farmerName =
                    profile.getName().trim();

        } else {

            // If profile name is not available,
            // get name from Firebase user
            UserModel user =
                    userDAO.getUserByUid(
                            firebaseUid
                    );

            if (user != null &&
                    user.getFullName() != null &&
                    !user.getFullName().trim().isEmpty()) {

                farmerName =
                        user.getFullName().trim();
            }
        }

    } catch (Exception e) {

        e.printStackTrace();

        farmerName = "Farmer";
    }

    FarmerDashboardHome home =
            new FarmerDashboardHome(
                    farmerId,
                    farmerName
            );

    return home.getDashboardHome();
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
                        35));

        bar.setAlignment(
                Pos.CENTER_LEFT);

        bar.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        VBox titleBox =
                new VBox();

        titleBox.setSpacing(3);


        Label title =
                new Label(
                        titleText);

        title.setTextFill(
                DARK_TEXT);

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28));


        Label subtitle =
                new Label(
                        subtitleText);

        subtitle.setTextFill(
                GREY);

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15));


        titleBox.getChildren()
                .addAll(
                        title,
                        subtitle);


        bar.getChildren()
                .addAll(
                        titleBox,
                        createWidthSpace(25));

        return bar;
    }


    // =========================================================
    // PROFILE VIEW
    // =========================================================


    
        private VBox createProfileView() {

                VBox main = new VBox();

                main.setBackground(new Background(
                                new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                HBox topBar = createTopBar(
                                "Farmer Profile",
                                "View your personal and farming information.");

                VBox content = new VBox();

                content.setPadding(
                                new Insets(
                                                30,
                                                35,
                                                35,
                                                35));

                content.setSpacing(22);

                content.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web("#050b0a"),
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                // =====================================================
                // GET PROFILE
                // =====================================================

                FarmerProfile profile = null;

                try {

                        profile = profileController.getProfile(
                                        farmerId);

                } catch (Exception e) {

                        e.printStackTrace();
                }

                // =====================================================
                // GET FIREBASE USER
                // =====================================================

                UserModel firebaseUser = null;

                try {

                        firebaseUser = userDAO.getUserByUid(
                                        firebaseUid);

                } catch (Exception e) {

                        e.printStackTrace();
                }

                // =====================================================
                // NAME
                // =====================================================

                String farmerName = "";

                if (profile != null &&
                                profile.getName() != null &&
                                !profile.getName().trim().isEmpty()) {

                        farmerName = profile.getName().trim();

                } else if (firebaseUser != null &&
                                firebaseUser.getFullName() != null &&
                                !firebaseUser.getFullName().trim().isEmpty()) {

                        farmerName = firebaseUser.getFullName().trim();

                } else {

                        farmerName = "Farmer";
                }

                // =====================================================
                // EMAIL
                // =====================================================

                String farmerEmail = "";

                if (profile != null &&
                                profile.getEmail() != null &&
                                !profile.getEmail().trim().isEmpty()) {

                        farmerEmail = profile.getEmail().trim();

                } else if (firebaseUser != null &&
                                firebaseUser.getEmail() != null &&
                                !firebaseUser.getEmail().trim().isEmpty()) {

                        farmerEmail = firebaseUser.getEmail().trim();

                } else {

                        farmerEmail = "Not provided";
                }

                // =====================================================
                // PROFILE HEADER
                // =====================================================

                HBox profileHeader = new HBox();

                profileHeader.setPrefHeight(
                                135);

                profileHeader.setPadding(
                                new Insets(22));

                profileHeader.setSpacing(
                                18);

                profileHeader.setAlignment(
                                Pos.CENTER_LEFT);

                profileHeader.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.DARKGREEN,
                                                                new CornerRadii(15),
                                                                Insets.EMPTY)));

                // =====================================================
                // PROFILE IMAGE
                // =====================================================

                StackPane profileImage = new StackPane();

                profileImage.setPrefSize(
                                90,
                                90);

                profileImage.setMinSize(
                                90,
                                90);

                profileImage.setMaxSize(
                                90,
                                90);

                profileImage.setStyle(
                                "-fx-background-color:#1B2420;" +
                                                "-fx-background-radius:50;");

                if (profile != null &&
                                profile.getImageBase64() != null &&
                                !profile.getImageBase64()
                                                .trim()
                                                .isEmpty()) {

                        try {

                                byte[] bytes = Base64.getDecoder()
                                                .decode(
                                                                profile.getImageBase64());

                                Image image = new Image(
                                                new java.io.ByteArrayInputStream(
                                                                bytes));

                                ImageView imageView = new ImageView(
                                                image);

                                imageView.setFitWidth(90);
                                imageView.setFitHeight(90);
                                imageView.setPreserveRatio(false);

                                Circle clip = new Circle(
                                                45,
                                                45,
                                                45);

                                imageView.setClip(
                                                clip);

                                profileImage
                                                .getChildren()
                                                .add(
                                                                imageView);

                        } catch (Exception e) {

                                addDefaultProfileIcon(
                                                profileImage,
                                                farmerName);
                        }

                } else {

                        addDefaultProfileIcon(
                                        profileImage,
                                        farmerName);
                }

                // =====================================================
                // PROFILE TEXT
                // =====================================================

                VBox profileText = new VBox(
                                5);

                Label nameLabel = new Label(
                                farmerName);

                nameLabel.setTextFill(
                                DARK_TEXT);

                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));

                Label roleLabel = new Label(
                                "Farm Owner");

                roleLabel.setTextFill(
                                LIGHT_GREEN);

                roleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                Label descriptionLabel = new Label(
                                "Your profile information is visible to buyers.");

                descriptionLabel.setTextFill(
                                GREY);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                profileText
                                .getChildren()
                                .addAll(
                                                nameLabel,
                                                roleLabel,
                                                descriptionLabel);

                // =====================================================
                // SPACER
                // =====================================================

                Region profileSpacer = new Region();

                HBox.setHgrow(
                                profileSpacer,
                                Priority.ALWAYS);

                // =====================================================
                // EDIT BUTTON
                // =====================================================

                Button edit = new Button(
                                "✎  Edit Profile");

                edit.setPrefHeight(
                                42);

                edit.setPrefWidth(
                                130);

                edit.setTextFill(
                                Color.DARKGREEN);

                edit.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                edit.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                GREEN,
                                                                new CornerRadii(7),
                                                                Insets.EMPTY)));

                edit.setCursor(
                                Cursor.HAND);

                edit.setOnAction(
                                e -> {

                                        root.setCenter(
                                                        createProfileCard());
                                });

                profileHeader
                                .getChildren()
                                .addAll(
                                                profileImage,
                                                profileText,
                                                profileSpacer,
                                                edit);

                // =====================================================
                // PERSONAL CARD
                // =====================================================

                VBox personalCard = createProfileViewCard(
                                "♙  Personal Information");

                GridPane personalGrid = new GridPane();

                personalGrid.setHgap(20);
                personalGrid.setVgap(15);

                personalGrid.add(
                                createProfileViewField(
                                                "Full Name",
                                                farmerName),
                                0,
                                0);

                personalGrid.add(
                                createProfileViewField(
                                                "Email Address",
                                                farmerEmail),
                                1,
                                0);

                personalGrid.add(
                                createProfileViewField(
                                                "Phone Number",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getPhone())),
                                0,
                                1);

                personalGrid.add(
                                createProfileViewField(
                                                "Address",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getAddress())),
                                1,
                                1);

                personalGrid.add(
                                createProfileViewField(
                                                "Village",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getVillage())),
                                0,
                                2);

                personalGrid.add(
                                createProfileViewField(
                                                "District",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getDistrict())),
                                1,
                                2);

                personalGrid.add(
                                createProfileViewField(
                                                "State",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getState())),
                                0,
                                3);

                personalCard
                                .getChildren()
                                .add(
                                                personalGrid);

                // =====================================================
                // FARM CARD
                // =====================================================

                VBox farmCard = createProfileViewCard(
                                "♧  Farm Information");

                GridPane farmGrid = new GridPane();

                farmGrid.setHgap(20);
                farmGrid.setVgap(15);

                farmGrid.add(
                                createProfileViewField(
                                                "Farm Name",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getFarmName())),
                                0,
                                0);

                farmGrid.add(
                                createProfileViewField(
                                                "Farm Area",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getFarmArea())),
                                1,
                                0);

                farmGrid.add(
                                createProfileViewField(
                                                "Farming Type",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getFarmingType())),
                                0,
                                1);

                farmGrid.add(
                                createProfileViewField(
                                                "Primary Crops",
                                                getProfileValue(
                                                                profile == null
                                                                                ? null
                                                                                : profile.getPrimaryCrops())),
                                1,
                                1);

                farmCard
                                .getChildren()
                                .add(
                                                farmGrid);

                // =====================================================
                // FARMER ID / UID CARD
                // =====================================================

                VBox accountCard = createProfileViewCard(
                                "🔐  Account Information");

                GridPane accountGrid = new GridPane();

                accountGrid.setHgap(20);
                accountGrid.setVgap(15);

                accountGrid.add(
                                createProfileViewField(
                                                "Farmer ID",
                                                String.valueOf(
                                                                farmerId)),
                                0,
                                0);

                accountGrid.add(
                                createProfileViewField(
                                                "Firebase UID",
                                                firebaseUid),
                                1,
                                0);

                accountCard
                                .getChildren()
                                .add(
                                                accountGrid);

                // =====================================================
                // COLUMN WIDTH
                // =====================================================

                columnConstraintsHelper(
                                personalGrid);

                columnConstraintsHelper(
                                farmGrid);

                columnConstraintsHelper(
                                accountGrid);

                // =====================================================
                // ADD CONTENT
                // =====================================================

                content.getChildren()
                                .addAll(
                                                profileHeader,
                                                personalCard,
                                                farmCard,
                                                accountCard);

                // =====================================================
                // SCROLL
                // =====================================================

                ScrollPane scroll = new ScrollPane(
                                content);

                scroll.setFitToWidth(
                                true);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scroll.setStyle(
                                "-fx-background-color:#050b0a;" +
                                                "-fx-background:#050b0a;" +
                                                "-fx-control-inner-background:#050b0a;");

                VBox.setVgrow(
                                scroll,
                                Priority.ALWAYS);

                main.getChildren()
                                .addAll(
                                                topBar,
                                                scroll);

                return main;
        }

        private void addDefaultProfileIcon(
                        StackPane container,
                        String farmerName) {

                String initial = "F";

                if (farmerName != null &&
                                !farmerName.trim().isEmpty()) {

                        initial = farmerName
                                        .trim()
                                        .substring(0, 1)
                                        .toUpperCase();
                }

                Label icon = new Label(
                                initial);

                icon.setPrefSize(
                                90,
                                90);

                icon.setAlignment(
                                Pos.CENTER);

                icon.setTextFill(
                                Color.WHITE);

                icon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                icon.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(50),
                                                                Insets.EMPTY)));

                container
                                .getChildren()
                                .add(
                                                icon);
        }

        private VBox createProfileViewCard(
                        String titleText) {

                VBox card = new VBox(
                                18);

                card.setPadding(
                                new Insets(
                                                22));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#26382B;" +
                                                "-fx-border-radius:15;" +
                                                "-fx-border-width:1;");

                Label title = new Label(
                                titleText);

                title.setTextFill(
                                Color.WHITE);

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                card.getChildren()
                                .add(
                                                title);

                return card;
        }

        private VBox createProfileViewField(
                        String title,
                        String value) {

                VBox box = new VBox(
                                6);

                Label titleLabel = new Label(
                                title);

                titleLabel.setTextFill(
                                Color.web("#A9B7AC"));

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                Label valueLabel = new Label(
                                getProfileValue(value));

                valueLabel.setTextFill(
                                Color.WHITE);

                valueLabel.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                valueLabel.setWrapText(
                                true);

                valueLabel.setMaxWidth(
                                Double.MAX_VALUE);

                box.setPadding(
                                new Insets(
                                                12));

                box.setStyle(
                                "-fx-background-color:#050B0A;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-border-color:#303839;" +
                                                "-fx-border-radius:7;");

                box.getChildren()
                                .addAll(
                                                titleLabel,
                                                valueLabel);

                GridPane.setHgrow(
                                box,
                                Priority.ALWAYS);

                return box;
        }

        private String getProfileValue(
                        String value) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return "Not provided";
                }

                return value.trim();
        }

        // DASHBOARD PAGE
        // private VBox createDashboardPage() {

        //         VBox main = new VBox();

        //         // Use the same dark background as the dashboard content
        //         main.setBackground(
        //                         new Background(
        //                                         new BackgroundFill(
        //                                                         DARK_GREEN,
        //                                                         CornerRadii.EMPTY,
        //                                                         Insets.EMPTY)));

        //         HBox topBar = createTopBar(
        //                         "Farmer Dashboard",
        //                         "Manage your farm and make smarter decisions.");

        //         VBox content = createDashboardContent();

        //         content.setBackground(
        //                         new Background(
        //                                         new BackgroundFill(
        //                                                         DARK_GREEN,
        //                                                         CornerRadii.EMPTY,
        //                                                         Insets.EMPTY)));

        //         ScrollPane scroll = new ScrollPane(content);

        //         scroll.setFitToWidth(true);
        //         scroll.setHbarPolicy(
        //                         ScrollPane.ScrollBarPolicy.NEVER);

        //         // Remove ScrollPane border
        //         scroll.setStyle(
        //                         "-fx-background-color: transparent;" +
        //                                         "-fx-border-color: transparent;" +
        //                                         "-fx-focus-color: transparent;" +
        //                                         "-fx-faint-focus-color: transparent;");

        //         scroll.setFocusTraversable(false);

        //         VBox.setVgrow(
        //                         scroll,
        //                         Priority.ALWAYS);

        //         main.getChildren().addAll(
        //                         topBar,
        //                         scroll);

        //         return main;
        // }

        // // // // TOP BAR
        // private HBox createTopBar(String titleText, String subtitleText) {
        //         HBox bar = new HBox();
        //         bar.setPrefHeight(100);
        //         bar.setPadding(new Insets(18, 35, 18, 35));
        //         bar.setAlignment(Pos.CENTER_LEFT);
        //         bar.setBackground(new Background(new BackgroundFill(DARK_GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        //         VBox titleBox = new VBox();
        //         titleBox.setSpacing(3);

        //         Label title = new Label(titleText);
        //         title.setTextFill(DARK_TEXT);
        //         title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        //         Label subtitle = new Label(subtitleText);
        //         subtitle.setTextFill(GREY);
        //         subtitle.setFont(Font.font("Arial", 15));

        //         titleBox.getChildren().addAll(title, subtitle);

        //         bar.getChildren().addAll(titleBox, createWidthSpace(25));
        //         return bar;
        // }

        // // DASHBOARD CONTENT
        // // ============================================================
        // // DASHBOARD CONTENT
        // // ============================================================

        // private VBox createDashboardContent() {

        //         FarmerDashboardHome home = new FarmerDashboardHome(
        //                         farmerId);

        //         return home.getDashboardHome();
        // }

        // ============================================================
        // WELCOME CARD
        // ============================================================

        private void loadFirebaseUserData() {

                try {

                        UserModel user = userDAO.getUserByUid(
                                        firebaseUid);

                        if (user == null) {

                                System.out.println(
                                                "Firebase user not found for UID = "
                                                                + firebaseUid);

                                return;
                        }

                        // =================================================
                        // NAME FROM FIREBASE
                        // =================================================

                        if (user.getFullName() != null &&
                                        !user.getFullName().trim().isEmpty()) {

                                nameField.setText(
                                                user.getFullName().trim());
                        }

                        // =================================================
                        // EMAIL FROM FIREBASE
                        // =================================================

                        if (user.getEmail() != null &&
                                        !user.getEmail().trim().isEmpty()) {

                                emailField.setText(
                                                user.getEmail().trim());
                        }

                        System.out.println(
                                        "Firebase name loaded = "
                                                        + user.getFullName());

                        System.out.println(
                                        "Firebase email loaded = "
                                                        + user.getEmail());

                } catch (Exception e) {

                        e.printStackTrace();

                        System.out.println(
                                        "Unable to load Firebase user information.");
                }
        }

        private VBox createProfileCard() {

                VBox card = createCard();

                // =====================================================
                // CARD TITLE
                // =====================================================

                Label cardTitle = new Label(
                                "👨‍🌾 Personal & Farm Profile");

                cardTitle.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:21px;" +
                                                "-fx-font-weight:bold;");

                Label cardSubtitle = new Label(
                                "Your information is saved using your unique farmer ID.");

                cardSubtitle.setStyle(
                                "-fx-text-fill:#888888;" +
                                                "-fx-font-size:13px;");

                // =====================================================
                // PROFILE IMAGE
                // =====================================================

                profileImageContainer = new StackPane();

                profileImageContainer.setPrefSize(
                                150,
                                150);

                profileImageContainer.setMinSize(
                                150,
                                150);

                profileImageContainer.setMaxSize(
                                150,
                                150);

                profileImageContainer.setStyle(
                                "-fx-background-color:#1B2425;" +
                                                "-fx-background-radius:100;");

                showDefaultProfileImage();

                Button changeImage = new Button(
                                "📷 Change Profile Image");

                changeImage.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8 12;" +
                                                "-fx-cursor:hand;");

                changeImage.setOnAction(
                                e -> chooseProfileImage());

                VBox imageBox = new VBox(
                                12,
                                profileImageContainer,
                                changeImage);

                imageBox.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // FORM
                // =====================================================

                GridPane form = new GridPane();

                form.setHgap(
                                20);

                form.setVgap(
                                15);

                form.setPadding(
                                new Insets(
                                                10,
                                                0,
                                                10,
                                                0));

                form.setMaxWidth(
                                Double.MAX_VALUE);

                // =====================================================
                // CREATE FIELDS
                // =====================================================

                nameField = createTextField(
                                "Enter your full name");

                emailField = createTextField(
                                "Enter your email");

                phoneField = createTextField(
                                "Enter phone number");

                addressField = createTextField(
                                "Enter complete address");

                villageField = createTextField(
                                "Enter village");

                districtField = createTextField(
                                "Enter district");

                stateField = createTextField(
                                "Enter state");

                farmNameField = createTextField(
                                "Enter farm name");

                farmAreaField = createTextField(
                                "Example: 5 acres");

                farmingTypeBox = new ComboBox<>();

                farmingTypeBox.getItems().addAll(
                                "Organic",
                                "Conventional",
                                "Mixed Farming",
                                "Natural Farming",
                                "Other");

                farmingTypeBox.setPromptText(
                                "Select farming type");

                farmingTypeBox.setMaxWidth(
                                Double.MAX_VALUE);

                styleComboBox(
                                farmingTypeBox);

                primaryCropsField = createTextField(
                                "Example: Wheat, Onion, Tomato");

                loadExistingProfile();
                loadFirebaseUserData();
                // =====================================================
                // ADD FIELDS
                // =====================================================

                addField(
                                form,
                                "Full Name",
                                nameField,
                                0,
                                0);

                addField(
                                form,
                                "Email",
                                emailField,
                                1,
                                0);

                addField(
                                form,
                                "Phone",
                                phoneField,
                                0,
                                1);

                addField(
                                form,
                                "Address",
                                addressField,
                                1,
                                1);

                addField(
                                form,
                                "Village",
                                villageField,
                                0,
                                2);

                addField(
                                form,
                                "District",
                                districtField,
                                1,
                                2);

                addField(
                                form,
                                "State",
                                stateField,
                                0,
                                3);

                addField(
                                form,
                                "Farm Name",
                                farmNameField,
                                1,
                                3);

                addField(
                                form,
                                "Farm Area",
                                farmAreaField,
                                0,
                                4);

                addField(
                                form,
                                "Farming Type",
                                farmingTypeBox,
                                1,
                                4);

                addField(
                                form,
                                "Primary Crops",
                                primaryCropsField,
                                0,
                                5);

                // =====================================================
                // SAVE BUTTON
                // =====================================================

                Button saveButton = new Button(
                                "✓ Save Profile");

                saveButton.setPrefHeight(
                                42);

                saveButton.setPrefWidth(
                                180);

                saveButton.setStyle(
                                "-fx-background-color:#68D34A;" +
                                                "-fx-text-fill:#080C0D;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-cursor:hand;");

                saveButton.setOnAction(
                                e -> saveProfile());

                // =====================================================
                // FORM LAYOUT
                // =====================================================

                HBox mainProfile = new HBox(
                                35,
                                imageBox,
                                form);

                mainProfile.setAlignment(
                                Pos.TOP_CENTER);

                HBox.setHgrow(
                                form,
                                Priority.ALWAYS);

                card.getChildren()
                                .addAll(
                                                cardTitle,
                                                cardSubtitle,
                                                mainProfile,
                                                saveButton);

                return card;
        }

        // =========================================================
        // LOAD EXISTING PROFILE
        // =========================================================

        // =========================================================
        // LOAD EXISTING FARMER PROFILE
        // =========================================================

        private void loadExistingProfile() {

                try {

                        FarmerProfile profile = profileController.getProfile(
                                        farmerId);

                        if (profile == null) {

                                System.out.println(
                                                "No farmer profile found for Farmer ID = "
                                                                + farmerId);

                                return;
                        }

                        System.out.println(
                                        "Farmer profile loaded.");

                        System.out.println(
                                        "Farmer ID = "
                                                        + farmerId);

                        System.out.println(
                                        "Firebase UID = "
                                                        + firebaseUid);

                        // =================================================
                        // DO NOT USE PROFILE NAME/EMAIL HERE
                        // =================================================
                        //
                        // Name and Email must come from Firebase.
                        // loadFirebaseUserData() is called after this method.
                        // =================================================

                        phoneField.setText(
                                        safeEmpty(
                                                        profile.getPhone()));

                        addressField.setText(
                                        safeEmpty(
                                                        profile.getAddress()));

                        villageField.setText(
                                        safeEmpty(
                                                        profile.getVillage()));

                        districtField.setText(
                                        safeEmpty(
                                                        profile.getDistrict()));

                        stateField.setText(
                                        safeEmpty(
                                                        profile.getState()));

                        farmNameField.setText(
                                        safeEmpty(
                                                        profile.getFarmName()));

                        farmAreaField.setText(
                                        safeEmpty(
                                                        profile.getFarmArea()));

                        // =================================================
                        // FARMING TYPE
                        // =================================================

                        if (profile.getFarmingType() != null
                                        && !profile.getFarmingType()
                                                        .trim()
                                                        .isEmpty()) {

                                farmingTypeBox.setValue(
                                                profile.getFarmingType().trim());
                        }

                        // =================================================
                        // PRIMARY CROPS
                        // =================================================

                        primaryCropsField.setText(
                                        safeEmpty(
                                                        profile.getPrimaryCrops()));

                        // =================================================
                        // PROFILE IMAGE
                        // =================================================

                        imageBase64 = profile.getImageBase64();

                        if (imageBase64 != null
                                        && !imageBase64.trim().isEmpty()) {

                                showProfileImage(
                                                imageBase64);
                        }

                } catch (Exception e) {

                        System.err.println(
                                        "Unable to load farmer profile.");

                        e.printStackTrace();
                }
        }

        private String safeEmpty(
                        String value) {

                if (value == null) {
                        return "";
                }

                return value;
        }

        // =========================================================
        // SAVE PROFILE
        // =========================================================

        private void saveProfile() {

                String name = nameField.getText().trim();

                String email = emailField.getText().trim();

                String phone = phoneField.getText().trim();

                String address = addressField.getText().trim();

                String village = villageField.getText().trim();

                String district = districtField.getText().trim();

                String state = stateField.getText().trim();

                String farmName = farmNameField.getText().trim();

                String farmArea = farmAreaField.getText().trim();

                String farmingType = farmingTypeBox.getValue();

                String primaryCrops = primaryCropsField.getText().trim();

                // =====================================================
                // VALIDATION
                // =====================================================

                if (name.isEmpty()) {

                        showAlert(
                                        Alert.AlertType.WARNING,
                                        "Please enter your name.");

                        return;
                }

                if (email.isEmpty()) {

                        showAlert(
                                        Alert.AlertType.WARNING,
                                        "Please enter your email.");

                        return;
                }

                if (phone.isEmpty()) {

                        showAlert(
                                        Alert.AlertType.WARNING,
                                        "Please enter your phone number.");

                        return;
                }

                // =====================================================
                // CREATE PROFILE
                // =====================================================

                FarmerProfile profile = new FarmerProfile();

                profile.setFarmerId(
                                farmerId);

                profile.setUid(
                                firebaseUid);

                profile.setName(
                                name);

                profile.setEmail(
                                email);

                profile.setPhone(
                                phone);

                profile.setAddress(
                                address);

                profile.setVillage(
                                village);

                profile.setDistrict(
                                district);

                profile.setState(
                                state);

                profile.setFarmName(
                                farmName);

                profile.setFarmArea(
                                farmArea);

                profile.setFarmingType(
                                farmingType);

                profile.setPrimaryCrops(
                                primaryCrops);

                profile.setImageBase64(
                                imageBase64);

                // =====================================================
                // SAVE FIREBASE
                // =====================================================

                boolean saved = profileController.saveProfile(
                                profile);

                if (saved) {

                        System.out.println(
                                        "Profile saved for farmer ID = "
                                                        + farmerId);

                        showAlert(
                                        Alert.AlertType.INFORMATION,
                                        "Profile saved successfully!");

                        // =================================================
                        // GO BACK TO READ-ONLY PROFILE
                        // =================================================

                        root.setCenter(
                                        createProfileView());

                } else {

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Profile could not be saved.\n"
                                                        + "Please check Firebase.");
                }
        }

        // =========================================================
        // CHOOSE PROFILE IMAGE
        // =========================================================

        private void chooseProfileImage() {

                FileChooser chooser = new FileChooser();

                chooser.setTitle(
                                "Select Farmer Profile Image");

                chooser.getExtensionFilters()
                                .add(
                                                new FileChooser.ExtensionFilter(
                                                                "Image Files",
                                                                "*.png",
                                                                "*.jpg",
                                                                "*.jpeg",
                                                                "*.webp"));

                File file = chooser.showOpenDialog(
                                LoginPage.mainStage);

                if (file == null) {
                        return;
                }

                try {

                        FileInputStream input = new FileInputStream(
                                        file);

                        ByteArrayOutputStream output = new ByteArrayOutputStream();

                        byte[] buffer = new byte[8192];

                        int bytesRead;

                        while ((bytesRead = input.read(buffer)) != -1) {

                                output.write(
                                                buffer,
                                                0,
                                                bytesRead);
                        }

                        input.close();

                        byte[] imageBytes = output.toByteArray();

                        imageBase64 = Base64.getEncoder()
                                        .encodeToString(
                                                        imageBytes);

                        showProfileImage(
                                        imageBase64);

                        System.out.println(
                                        "Profile image selected.");

                } catch (Exception e) {

                        e.printStackTrace();

                        showAlert(
                                        Alert.AlertType.ERROR,
                                        "Unable to load profile image.");
                }
        }

        // =========================================================
        // SHOW PROFILE IMAGE
        // =========================================================

        private void showProfileImage(
                        String base64) {

                if (base64 == null ||
                                base64.trim().isEmpty()) {

                        showDefaultProfileImage();

                        return;
                }

                try {

                        byte[] bytes = Base64.getDecoder()
                                        .decode(
                                                        base64);

                        Image image = new Image(
                                        new java.io.ByteArrayInputStream(
                                                        bytes));

                        if (image.isError()) {

                                throw new Exception(
                                                "Invalid image");
                        }

                        ImageView imageView = new ImageView(
                                        image);

                        imageView.setFitWidth(
                                        150);

                        imageView.setFitHeight(
                                        150);

                        imageView.setPreserveRatio(
                                        false);

                        Circle clip = new Circle(
                                        75,
                                        75,
                                        75);

                        imageView.setClip(
                                        clip);

                        profileImageContainer
                                        .getChildren()
                                        .clear();

                        profileImageContainer
                                        .getChildren()
                                        .add(
                                                        imageView);

                } catch (Exception e) {

                        e.printStackTrace();

                        showDefaultProfileImage();
                }
        }

        // =========================================================
        // DEFAULT IMAGE
        // =========================================================

        private void showDefaultProfileImage() {

                profileImageContainer
                                .getChildren()
                                .clear();

                Label icon = new Label(
                                "👨‍🌾");

                icon.setStyle(
                                "-fx-font-size:60px;");

                profileImageContainer
                                .getChildren()
                                .add(
                                                icon);
        }
//     private VBox createProfileView() {

//         VBox main =
//                 new VBox();

//         main.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 DARK_GREEN,
//                                 CornerRadii.EMPTY,
//                                 Insets.EMPTY)));


//         HBox topBar =
//                 createTopBar(
//                         "Farmer Profile",
//                         "View your personal and farming information.");


//         VBox content =
//                 new VBox(22);

//         content.setPadding(
//                 new Insets(
//                         30,
//                         35,
//                         35,
//                         35));

//         content.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 Color.web("#0B1F14"),
//                                 CornerRadii.EMPTY,
//                                 Insets.EMPTY)));


//         FarmerProfile profile =
//                 null;

//         try {

//             profile =
//                     profileController
//                             .getProfile(farmerId);

//         } catch (Exception e) {

//             e.printStackTrace();
//         }


//         UserModel firebaseUser =
//                 null;

//         try {

//             firebaseUser =
//                     userDAO.getUserByUid(
//                             firebaseUid);

//         } catch (Exception e) {

//             e.printStackTrace();
//         }


//         // =====================================================
//         // NAME
//         // =====================================================

//         String farmerName =
//                 "Farmer";

//         if (profile != null &&
//                 profile.getName() != null &&
//                 !profile.getName()
//                         .trim()
//                         .isEmpty()) {

//             farmerName =
//                     profile.getName().trim();

//         } else if (firebaseUser != null &&
//                 firebaseUser.getFullName() != null &&
//                 !firebaseUser.getFullName()
//                         .trim()
//                         .isEmpty()) {

//             farmerName =
//                     firebaseUser.getFullName().trim();
//         }


//         // =====================================================
//         // EMAIL
//         // =====================================================

//         String farmerEmail =
//                 "Not provided";

//         if (profile != null &&
//                 profile.getEmail() != null &&
//                 !profile.getEmail()
//                         .trim()
//                         .isEmpty()) {

//             farmerEmail =
//                     profile.getEmail().trim();

//         } else if (firebaseUser != null &&
//                 firebaseUser.getEmail() != null &&
//                 !firebaseUser.getEmail()
//                         .trim()
//                         .isEmpty()) {

//             farmerEmail =
//                     firebaseUser.getEmail().trim();
//         }


//         // =====================================================
//         // PROFILE HEADER
//         // =====================================================

//         HBox profileHeader =
//                 new HBox(20);

//         profileHeader.setAlignment(
//                 Pos.CENTER_LEFT);


//         StackPane avatar =
//                 new StackPane();

//         avatar.setPrefSize(
//                 100,
//                 100);

//         avatar.setStyle(
//                 "-fx-background-color:#193522;" +
//                 "-fx-background-radius:100;");


//         Label avatarIcon =
//                 new Label("👨‍🌾");

//         avatarIcon.setStyle(
//                 "-fx-font-size:45px;");

//         avatar.getChildren()
//                 .add(avatarIcon);


//         VBox farmerInfo =
//                 new VBox(5);


//         Label name =
//                 new Label(
//                         farmerName);

//         name.setTextFill(
//                 Color.WHITE);

//         name.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         24));


//         Label email =
//                 new Label(
//                         farmerEmail);

//         email.setTextFill(
//                 GREY);

//         email.setFont(
//                 Font.font(
//                         "Arial",
//                         14));


//         Label id =
//                 new Label(
//                         "Farmer ID: " +
//                         farmerId);

//         id.setTextFill(
//                 LIGHT_GREEN);

//         id.setFont(
//                 Font.font(
//                         "Arial",
//                         12));


//         farmerInfo.getChildren()
//                 .addAll(
//                         name,
//                         email,
//                         id);


//         profileHeader.getChildren()
//                 .addAll(
//                         avatar,
//                         farmerInfo);


//         // =====================================================
//         // PERSONAL INFORMATION
//         // =====================================================

//         VBox personalCard =
//                 createProfileViewCard(
//                         "👤  Personal Information");


//         GridPane personalGrid =
//                 new GridPane();

//         personalGrid.setHgap(20);

//         personalGrid.setVgap(15);


//         personalGrid.add(
//                 createProfileViewField(
//                         "Name",
//                         profile == null
//                                 ? farmerName
//                                 : profile.getName()),
//                 0,
//                 0);


//         personalGrid.add(
//                 createProfileViewField(
//                         "Email",
//                         profile == null
//                                 ? farmerEmail
//                                 : profile.getEmail()),
//                 1,
//                 0);


//         personalGrid.add(
//                 createProfileViewField(
//                         "Phone",
//                         profile == null
//                                 ? null
//                                 : profile.getPhone()),
//                 0,
//                 1);


//         personalGrid.add(
//                 createProfileViewField(
//                         "Address",
//                         profile == null
//                                 ? null
//                                 : profile.getAddress()),
//                 1,
//                 1);


//         personalGrid.add(
//                 createProfileViewField(
//                         "Village",
//                         profile == null
//                                 ? null
//                                 : profile.getVillage()),
//                 0,
//                 2);


//         personalGrid.add(
//                 createProfileViewField(
//                         "District",
//                         profile == null
//                                 ? null
//                                 : profile.getDistrict()),
//                 1,
//                 2);


//         personalGrid.add(
//                 createProfileViewField(
//                         "State",
//                         profile == null
//                                 ? null
//                                 : profile.getState()),
//                 0,
//                 3);


//         personalCard.getChildren()
//                 .add(personalGrid);


//         // =====================================================
//         // FARM INFORMATION
//         // =====================================================

//         VBox farmCard =
//                 createProfileViewCard(
//                         "🌾  Farm Information");


//         GridPane farmGrid =
//                 new GridPane();

//         farmGrid.setHgap(20);

//         farmGrid.setVgap(15);


//         farmGrid.add(
//                 createProfileViewField(
//                         "Farm Name",
//                         profile == null
//                                 ? null
//                                 : profile.getFarmName()),
//                 0,
//                 0);


//         farmGrid.add(
//                 createProfileViewField(
//                         "Farm Area",
//                         profile == null
//                                 ? null
//                                 : profile.getFarmArea()),
//                 1,
//                 0);


//         farmGrid.add(
//                 createProfileViewField(
//                         "Farming Type",
//                         profile == null
//                                 ? null
//                                 : profile.getFarmingType()),
//                 0,
//                 1);


//         farmGrid.add(
//                 createProfileViewField(
//                         "Primary Crops",
//                         profile == null
//                                 ? null
//                                 : profile.getPrimaryCrops()),
//                 1,
//                 1);


//         farmCard.getChildren()
//                 .add(farmGrid);


//         // =====================================================
//         // ACCOUNT INFORMATION
//         // =====================================================

//         VBox accountCard =
//                 createProfileViewCard(
//                         "🔐  Account Information");


//         GridPane accountGrid =
//                 new GridPane();

//         accountGrid.setHgap(20);

//         accountGrid.setVgap(15);


//         accountGrid.add(
//                 createProfileViewField(
//                         "Farmer ID",
//                         String.valueOf(
//                                 farmerId)),
//                 0,
//                 0);


//         accountGrid.add(
//                 createProfileViewField(
//                         "Firebase UID",
//                         firebaseUid),
//                 1,
//                 0);


//         accountCard.getChildren()
//                 .add(accountGrid);


//         // =====================================================
//         // EDIT BUTTON
//         // =====================================================

//         Button editButton =
//                 new Button(
//                         "✎  Edit Profile");

//         editButton.setPrefWidth(
//                 180);

//         editButton.setPrefHeight(
//                 42);

//         editButton.setCursor(
//                 Cursor.HAND);

//         editButton.setStyle(
//                 "-fx-background-color:#7ED957;" +
//                 "-fx-text-fill:#102A18;" +
//                 "-fx-font-size:14px;" +
//                 "-fx-font-weight:bold;" +
//                 "-fx-background-radius:7;");

//         editButton.setOnAction(
//                 e ->
//                         showPage(
//                                 "editProfile"));


//         content.getChildren()
//                 .addAll(
//                         profileHeader,
//                         personalCard,
//                         farmCard,
//                         accountCard,
//                         editButton);


//         ScrollPane scroll =
//                 new ScrollPane(
//                         content);

//         scroll.setFitToWidth(true);

//         scroll.setHbarPolicy(
//                 ScrollPane.ScrollBarPolicy.NEVER);

//         scroll.setStyle(
//                 "-fx-background-color:#0B1F14;" +
//                 "-fx-control-inner-background:#0B1F14;");

//         VBox.setVgrow(
//                 scroll,
//                 Priority.ALWAYS);


//         main.getChildren()
//                 .addAll(
//                         topBar,
//                         scroll);

//         return main;
//     }


//     // =========================================================
//     // PROFILE VIEW CARD
//     // =========================================================

//     private VBox createProfileViewCard(
//             String titleText) {

//         VBox card =
//                 new VBox(18);

//         card.setPadding(
//                 new Insets(22));

//         card.setMaxWidth(
//                 Double.MAX_VALUE);

//         card.setStyle(
//                 "-fx-background-color:#12291A;" +
//                 "-fx-background-radius:15;" +
//                 "-fx-border-color:#294734;" +
//                 "-fx-border-radius:15;" +
//                 "-fx-border-width:1;");


//         Label title =
//                 new Label(
//                         titleText);

//         title.setTextFill(
//                 Color.WHITE);

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         18));


//         card.getChildren()
//                 .add(title);

//         return card;
//     }


//     // =========================================================
//     // PROFILE VIEW FIELD
//     // =========================================================

//     private VBox createProfileViewField(
//             String title,
//             String value) {

//         VBox box =
//                 new VBox(6);


//         Label titleLabel =
//                 new Label(title);

//         titleLabel.setTextFill(
//                 Color.web("#A9B7AC"));

//         titleLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         11));


//         Label valueLabel =
//                 new Label(
//                         getProfileValue(value));

//         valueLabel.setTextFill(
//                 Color.WHITE);

//         valueLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         14));

//         valueLabel.setWrapText(
//                 true);

//         valueLabel.setMaxWidth(
//                 Double.MAX_VALUE);


//         box.setPadding(
//                 new Insets(12));

//         box.setStyle(
//                 "-fx-background-color:#0F2116;" +
//                 "-fx-background-radius:7;" +
//                 "-fx-border-color:#294734;" +
//                 "-fx-border-radius:7;");


//         box.getChildren()
//                 .addAll(
//                         titleLabel,
//                         valueLabel);


//         GridPane.setHgrow(
//                 box,
//                 Priority.ALWAYS);

//         return box;
//     }


//     // =========================================================
//     // PROFILE VALUE
//     // =========================================================

//     private String getProfileValue(
//             String value) {

//         if (value == null ||
//                 value.trim().isEmpty()) {

//             return "Not provided";
//         }

//         return value.trim();
//     }


//     // =========================================================
//     // EDIT PROFILE PAGE
//     // =========================================================

    private VBox createProfilePage() {

        VBox page =
                new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox topBar =
                createTopBar(
                        "Edit Farmer Profile",
                        "Update your personal and farming information.");


        VBox wrapper =
                new VBox(20);

        wrapper.setPadding(
                new Insets(
                        25,
                        35,
                        35,
                        35));

        wrapper.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#0B1F14"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        VBox card =
                createProfileCard();


        wrapper.getChildren()
                .add(card);


        ScrollPane scroll =
                new ScrollPane(
                        wrapper);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color:#0B1F14;" +
                "-fx-control-inner-background:#0B1F14;");

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);


        page.getChildren()
                .addAll(
                        topBar,
                        scroll);

        return page;
    }


//     // =========================================================
//     // PROFILE CARD
//     // =========================================================

//     private VBox createProfileCard() {

//         VBox card =
//                 createCard();


//         Label cardTitle =
//                 new Label(
//                         "👨‍🌾 Personal & Farm Profile");

//         cardTitle.setStyle(
//                 "-fx-text-fill:#7ED957;" +
//                 "-fx-font-size:21px;" +
//                 "-fx-font-weight:bold;");


//         Label cardSubtitle =
//                 new Label(
//                         "Your information is saved using your unique farmer ID.");

//         cardSubtitle.setStyle(
//                 "-fx-text-fill:#888888;" +
//                 "-fx-font-size:13px;");


//         // =====================================================
//         // PROFILE IMAGE
//         // =====================================================

//         profileImageContainer =
//                 new StackPane();

//         profileImageContainer.setPrefSize(
//                 150,
//                 150);

//         profileImageContainer.setMinSize(
//                 150,
//                 150);

//         profileImageContainer.setMaxSize(
//                 150,
//                 150);

//         profileImageContainer.setStyle(
//                 "-fx-background-color:#193522;" +
//                 "-fx-background-radius:100;");


//         showDefaultProfileImage();


//         Button changeImage =
//                 new Button(
//                         "📷 Change Profile Image");

//         changeImage.setStyle(
//                 "-fx-background-color:transparent;" +
//                 "-fx-text-fill:#7ED957;" +
//                 "-fx-border-color:#7ED957;" +
//                 "-fx-border-radius:6;" +
//                 "-fx-background-radius:6;" +
//                 "-fx-padding:8 12;" +
//                 "-fx-cursor:hand;");


//         changeImage.setOnAction(
//                 e ->
//                         chooseProfileImage());


//         VBox imageBox =
//                 new VBox(
//                         12,
//                         profileImageContainer,
//                         changeImage);

//         imageBox.setAlignment(
//                 Pos.CENTER);


//         // =====================================================
//         // FORM
//         // =====================================================

//         GridPane form =
//                 new GridPane();

//         form.setHgap(20);

//         form.setVgap(15);

//         form.setPadding(
//                 new Insets(
//                         10,
//                         0,
//                         10,
//                         0));

//         form.setMaxWidth(
//                 Double.MAX_VALUE);


//         // =====================================================
//         // CREATE FIELDS
//         // =====================================================

//         nameField =
//                 createTextField(
//                         "Enter your full name");

//         emailField =
//                 createTextField(
//                         "Enter your email");

//         phoneField =
//                 createTextField(
//                         "Enter phone number");

//         addressField =
//                 createTextField(
//                         "Enter complete address");

//         villageField =
//                 createTextField(
//                         "Enter village");

//         districtField =
//                 createTextField(
//                         "Enter district");

//         stateField =
//                 createTextField(
//                         "Enter state");

//         farmNameField =
//                 createTextField(
//                         "Enter farm name");

//         farmAreaField =
//                 createTextField(
//                         "Example: 5 acres");


//         farmingTypeBox =
//                 new ComboBox<>();

//         farmingTypeBox.getItems()
//                 .addAll(
//                         "Organic",
//                         "Conventional",
//                         "Mixed Farming",
//                         "Natural Farming",
//                         "Other");

//         farmingTypeBox.setPromptText(
//                 "Select farming type");

//         farmingTypeBox.setMaxWidth(
//                 Double.MAX_VALUE);

//         styleComboBox(
//                 farmingTypeBox);


//         primaryCropsField =
//                 createTextField(
//                         "Example: Wheat, Onion, Tomato");


//         // =====================================================
//         // LOAD EXISTING PROFILE
//         // =====================================================

//         loadExistingProfile();

//         loadFirebaseUserData();


//         // =====================================================
//         // ADD FIELDS
//         // =====================================================

//         addField(
//                 form,
//                 "Full Name",
//                 nameField,
//                 0,
//                 0);

//         addField(
//                 form,
//                 "Email",
//                 emailField,
//                 1,
//                 0);

//         addField(
//                 form,
//                 "Phone",
//                 phoneField,
//                 0,
//                 1);

//         addField(
//                 form,
//                 "Address",
//                 addressField,
//                 1,
//                 1);

//         addField(
//                 form,
//                 "Village",
//                 villageField,
//                 0,
//                 2);

//         addField(
//                 form,
//                 "District",
//                 districtField,
//                 1,
//                 2);

//         addField(
//                 form,
//                 "State",
//                 stateField,
//                 0,
//                 3);

//         addField(
//                 form,
//                 "Farm Name",
//                 farmNameField,
//                 1,
//                 3);

//         addField(
//                 form,
//                 "Farm Area",
//                 farmAreaField,
//                 0,
//                 4);

//         addField(
//                 form,
//                 "Farming Type",
//                 farmingTypeBox,
//                 1,
//                 4);

//         addField(
//                 form,
//                 "Primary Crops",
//                 primaryCropsField,
//                 0,
//                 5);


//         // =====================================================
//         // SAVE BUTTON
//         // =====================================================

//         Button saveButton =
//                 new Button(
//                         "✓ Save Profile");

//         saveButton.setPrefHeight(
//                 42);

//         saveButton.setPrefWidth(
//                 180);

//         saveButton.setStyle(
//                 "-fx-background-color:#7ED957;" +
//                 "-fx-text-fill:#080C0D;" +
//                 "-fx-font-size:14px;" +
//                 "-fx-font-weight:bold;" +
//                 "-fx-background-radius:7;" +
//                 "-fx-cursor:hand;");


//         saveButton.setOnAction(
//                 e ->
//                         saveProfile());


//         // =====================================================
//         // FORM LAYOUT
//         // =====================================================

//         HBox mainProfile =
//                 new HBox(
//                         35,
//                         imageBox,
//                         form);

//         mainProfile.setAlignment(
//                 Pos.TOP_CENTER);


//         card.getChildren()
//                 .addAll(
//                         cardTitle,
//                         cardSubtitle,
//                         mainProfile,
//                         saveButton);

//         return card;
//     }


//     // =========================================================
//     // LOAD EXISTING PROFILE
//     // =========================================================

//     private void loadExistingProfile() {

//         try {

//             FarmerProfile profile =
//                     profileController
//                             .getProfile(farmerId);

//             if (profile == null) {
//                 return;
//             }


//             nameField.setText(
//                     safeEmpty(
//                             profile.getName()));

//             emailField.setText(
//                     safeEmpty(
//                             profile.getEmail()));

//             phoneField.setText(
//                     safeEmpty(
//                             profile.getPhone()));

//             addressField.setText(
//                     safeEmpty(
//                             profile.getAddress()));

//             villageField.setText(
//                     safeEmpty(
//                             profile.getVillage()));

//             districtField.setText(
//                     safeEmpty(
//                             profile.getDistrict()));

//             stateField.setText(
//                     safeEmpty(
//                             profile.getState()));

//             farmNameField.setText(
//                     safeEmpty(
//                             profile.getFarmName()));

//             farmAreaField.setText(
//                     safeEmpty(
//                             profile.getFarmArea()));

//             if (profile.getFarmingType() != null) {

//                 farmingTypeBox.setValue(
//                         profile.getFarmingType());
//             }

//             primaryCropsField.setText(
//                     safeEmpty(
//                             profile.getPrimaryCrops()));


//             imageBase64 =
//                     profile.getImageBase64();


//             if (imageBase64 != null &&
//                     !imageBase64.trim().isEmpty()) {

//                 showProfileImage(
//                         imageBase64);
//             }

//         } catch (Exception e) {

//             System.err.println(
//                     "Unable to load farmer profile.");

//             e.printStackTrace();
//         }
//     }


//     // =========================================================
//     // LOAD FIREBASE USER
//     // =========================================================

//     private void loadFirebaseUserData() {

//         try {

//             UserModel user =
//                     userDAO.getUserByUid(
//                             firebaseUid);

//             if (user == null) {

//                 System.out.println(
//                         "Firebase user not found for UID = "
//                         + firebaseUid);

//                 return;
//             }


//             if (user.getFullName() != null &&
//                     !user.getFullName()
//                             .trim()
//                             .isEmpty()) {

//                 nameField.setText(
//                         user.getFullName()
//                                 .trim());
//             }


//             if (user.getEmail() != null &&
//                     !user.getEmail()
//                             .trim()
//                             .isEmpty()) {

//                 emailField.setText(
//                         user.getEmail()
//                                 .trim());
//             }

//         } catch (Exception e) {

//             e.printStackTrace();

//             System.out.println(
//                     "Unable to load Firebase user information.");
//         }
//     }


//     // =========================================================
//     // SAFE EMPTY
//     // =========================================================

//     private String safeEmpty(
//             String value) {

//         if (value == null) {
//             return "";
//         }

//         return value;
//     }


//     // =========================================================
//     // SAVE PROFILE
//     // =========================================================

//     private void saveProfile() {

//         String name =
//                 nameField.getText().trim();

//         String email =
//                 emailField.getText().trim();

//         String phone =
//                 phoneField.getText().trim();

//         String address =
//                 addressField.getText().trim();

//         String village =
//                 villageField.getText().trim();

//         String district =
//                 districtField.getText().trim();

//         String state =
//                 stateField.getText().trim();

//         String farmName =
//                 farmNameField.getText().trim();

//         String farmArea =
//                 farmAreaField.getText().trim();

//         String farmingType =
//                 farmingTypeBox.getValue();

//         String primaryCrops =
//                 primaryCropsField.getText().trim();


//         // =====================================================
//         // VALIDATION
//         // =====================================================

//         if (name.isEmpty()) {

//             showAlert(
//                     Alert.AlertType.WARNING,
//                     "Please enter your name.");

//             return;
//         }


//         if (email.isEmpty()) {

//             showAlert(
//                     Alert.AlertType.WARNING,
//                     "Please enter your email.");

//             return;
//         }


//         if (phone.isEmpty()) {

//             showAlert(
//                     Alert.AlertType.WARNING,
//                     "Please enter your phone number.");

//             return;
//         }


//         // =====================================================
//         // CREATE PROFILE
//         // =====================================================

//         FarmerProfile profile =
//                 new FarmerProfile();


//         profile.setFarmerId(
//                 farmerId);

//         profile.setUid(
//                 firebaseUid);

//         profile.setName(
//                 name);

//         profile.setEmail(
//                 email);

//         profile.setPhone(
//                 phone);

//         profile.setAddress(
//                 address);

//         profile.setVillage(
//                 village);

//         profile.setDistrict(
//                 district);

//         profile.setState(
//                 state);

//         profile.setFarmName(
//                 farmName);

//         profile.setFarmArea(
//                 farmArea);

//         profile.setFarmingType(
//                 farmingType);

//         profile.setPrimaryCrops(
//                 primaryCrops);

//         profile.setImageBase64(
//                 imageBase64);


//         // =====================================================
//         // SAVE FIREBASE
//         // =====================================================

//         boolean saved =
//                 profileController
//                         .saveProfile(profile);


//         if (saved) {

//             System.out.println(
//                     "Profile saved for farmer ID = "
//                     + farmerId);


//             showAlert(
//                     Alert.AlertType.INFORMATION,
//                     "Profile saved successfully!");


//             root.setCenter(
//                     createProfileView());

//         } else {

//             showAlert(
//                     Alert.AlertType.ERROR,
//                     "Profile could not be saved.\n"
//                     + "Please check Firebase.");
//         }
//     }


//     // =========================================================
//     // CHOOSE PROFILE IMAGE
//     // =========================================================

//     private void chooseProfileImage() {

//         FileChooser chooser =
//                 new FileChooser();

//         chooser.setTitle(
//                 "Select Farmer Profile Image");


//         chooser.getExtensionFilters()
//                 .add(
//                         new FileChooser.ExtensionFilter(
//                                 "Image Files",
//                                 "*.png",
//                                 "*.jpg",
//                                 "*.jpeg",
//                                 "*.webp"));


//         File file =
//                 chooser.showOpenDialog(
//                         LoginPage.mainStage);


//         if (file == null) {
//             return;
//         }


//         try {

//             FileInputStream input =
//                     new FileInputStream(file);

//             ByteArrayOutputStream output =
//                     new ByteArrayOutputStream();


//             byte[] buffer =
//                     new byte[8192];

//             int bytesRead;


//             while (
//                     (bytesRead =
//                             input.read(buffer)) != -1) {

//                 output.write(
//                         buffer,
//                         0,
//                         bytesRead);
//             }


//             input.close();


//             byte[] imageBytes =
//                     output.toByteArray();


//             imageBase64 =
//                     Base64.getEncoder()
//                             .encodeToString(
//                                     imageBytes);


//             showProfileImage(
//                     imageBase64);


//         } catch (Exception e) {

//             e.printStackTrace();

//             showAlert(
//                     Alert.AlertType.ERROR,
//                     "Unable to load profile image.");
//         }
//     }


//     // =========================================================
//     // SHOW PROFILE IMAGE
//     // =========================================================

//     private void showProfileImage(
//             String base64) {

//         if (base64 == null ||
//                 base64.trim().isEmpty()) {

//             showDefaultProfileImage();

//             return;
//         }


//         try {

//             byte[] bytes =
//                     Base64.getDecoder()
//                             .decode(base64);


//             Image image =
//                     new Image(
//                             new java.io.ByteArrayInputStream(
//                                     bytes));


//             if (image.isError()) {

//                 throw new Exception(
//                         "Invalid image");
//             }


//             ImageView imageView =
//                     new ImageView(image);


//             imageView.setFitWidth(
//                     150);

//             imageView.setFitHeight(
//                     150);

//             imageView.setPreserveRatio(
//                     false);


//             Circle clip =
//                     new Circle(
//                             75,
//                             75,
//                             75);


//             imageView.setClip(
//                     clip);


//             profileImageContainer
//                     .getChildren()
//                     .clear();


//             profileImageContainer
//                     .getChildren()
//                     .add(
//                             imageView);


//         } catch (Exception e) {

//             e.printStackTrace();

//             showDefaultProfileImage();
//         }
//     }


//     // =========================================================
//     // DEFAULT PROFILE IMAGE
//     // =========================================================

//     private void showDefaultProfileImage() {

//         if (profileImageContainer == null) {
//             return;
//         }


//         profileImageContainer
//                 .getChildren()
//                 .clear();


//         Label icon =
//                 new Label(
//                         "👨‍🌾");

//         icon.setStyle(
//                 "-fx-font-size:60px;");


//         profileImageContainer
//                 .getChildren()
//                 .add(icon);
//     }


    // =========================================================
    // INVESTMENT PAGE
    // =========================================================

    private VBox createInvestmentPage() {

        VBox page =
                new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox topBar =
                createTopBar(
                        "Course Investment Planner",
                        "Plan your course investment month by month.");


        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(
                        25,
                        35,
                        35,
                        35));

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));
        content.setStyle("-fx-backgrounf-color:black;");


        // =====================================================
        // INPUT CARD
        // =====================================================

        VBox inputCard =
                createWhiteCard();

        inputCard.setMaxWidth(
                700);

        inputCard.setPadding(
                new Insets(28));

        inputCard.setSpacing(12);


        Label title =
                new Label(
                        "Course Investment Details");

        title.setTextFill(
                DARK_TEXT);

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22));


        Label courseLabel =
                new Label(
                        "Select Course");

        courseLabel.setTextFill(
                GREY);

        courseLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));


        ComboBox<String> courseBox =
                new ComboBox<>();

        courseBox.getItems()
                .addAll(
                        "Poultry Farming",
                        "Dairy Farming",
                        "Aquaculture",
                        "Smart Agriculture",
                        "Precision Agriculture",
                        "AI in Agriculture");

        courseBox.setValue(
                "Poultry Farming");

        courseBox.setPrefHeight(
                45);

        courseBox.setMaxWidth(
                Double.MAX_VALUE);


        Label durationLabel =
                new Label(
                        "Course Duration (Months)");

        durationLabel.setTextFill(
                GREY);

        durationLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));


        ComboBox<Integer> durationBox =
                new ComboBox<>();

        durationBox.getItems()
                .addAll(
                        3,
                        6,
                        9,
                        12);

        durationBox.setValue(
                6);

        durationBox.setPrefHeight(
                45);

        durationBox.setMaxWidth(
                Double.MAX_VALUE);


        Label totalLabel =
                new Label(
                        "Total Investment");

        totalLabel.setTextFill(
                GREY);

        totalLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));


        TextField totalInvestmentField =
                new TextField();

        totalInvestmentField.setPromptText(
                "Example: 60000");

        totalInvestmentField.setPrefHeight(
                45);

        totalInvestmentField.setFont(
                Font.font(
                        "Arial",
                        14));

        totalInvestmentField.setStyle(
                "-fx-background-color:#ffffff;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:#263a2b;" +
                "-fx-border-radius:8;" +
                "-fx-padding:0 12;" +
                "-fx-text-fill:#0F2116;");


        Label initialLabel =
                new Label(
                        "Initial Investment");

        initialLabel.setTextFill(
                GREY);

        initialLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));


        TextField initialInvestmentField =
                new TextField();

        initialInvestmentField.setPromptText(
                "Example: 10000");

        initialInvestmentField.setPrefHeight(
                45);

        initialInvestmentField.setFont(
                Font.font(
                        "Arial",
                        14));

        initialInvestmentField.setStyle(
                "-fx-background-color:#ffffff;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:#263a2b;" +
                "-fx-border-radius:8;" +
                "-fx-padding:0 12;" +
                "-fx-text-fill:#0F2116;");


        Button calculate =
                createWishlistActionButton(
                        "Calculate Investment Plan");

        calculate.setPrefHeight(
                48);

        calculate.setMaxWidth(
                Double.MAX_VALUE);

        calculate.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        calculate.setStyle(
                "-fx-background-color:#7ED957;" +
                "-fx-text-fill:#102A18;");


        Label errorLabel =
                new Label();

        errorLabel.setTextFill(
                Color.web("#F08080"));

        errorLabel.setFont(
                Font.font(
                        "Arial",
                        12));


        inputCard.getChildren()
                .addAll(
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
                        errorLabel);


        // =====================================================
        // SUMMARY CARD
        // =====================================================

        VBox summaryCard =
                createWhiteCard();

        summaryCard.setMaxWidth(
                700);

        summaryCard.setPadding(
                new Insets(25));

        summaryCard.setSpacing(
                12);


        Label summaryTitle =
                new Label(
                        "Investment Summary");

        summaryTitle.setTextFill(
                DARK_TEXT);

        summaryTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21));


        Label selectedCourse =
                new Label(
                        "Course: Poultry Farming");

        selectedCourse.setTextFill(
                GREY);

        selectedCourse.setFont(
                Font.font(
                        "Arial",
                        13));


        Label totalValue =
                new Label(
                        "Total Investment: ₹0");

        totalValue.setTextFill(
                DARK_TEXT);

        totalValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));


        Label initialValue =
                new Label(
                        "Initial Investment: ₹0");

        initialValue.setTextFill(
                DARK_TEXT);

        initialValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));


        Label remainingValue =
                new Label(
                        "Remaining Investment: ₹0");

        remainingValue.setTextFill(
                DARK_TEXT);

        remainingValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));


        Label monthlyValue =
                new Label(
                        "Monthly Investment: ₹0");

        monthlyValue.setTextFill(
                GREEN);

        monthlyValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));


        ProgressBar progressBar =
                new ProgressBar(0);

        progressBar.setPrefHeight(
                18);

        progressBar.setMaxWidth(
                Double.MAX_VALUE);

        progressBar.setStyle(
                "-fx-accent:#68d34a;" +
                "-fx-control-inner-background:#D6E3D8;");


        Label progressLabel =
                new Label(
                        "Investment Progress: 0%");

        progressLabel.setTextFill(
                GREY);

        progressLabel.setFont(
                Font.font(
                        "Arial",
                        12));


        summaryCard.getChildren()
                .addAll(
                        summaryTitle,
                        selectedCourse,
                        totalValue,
                        initialValue,
                        remainingValue,
                        monthlyValue,
                        progressBar,
                        progressLabel);


        // =====================================================
        // MONTHLY PLAN
        // =====================================================

        VBox monthlyCard =
                createWhiteCard();

        monthlyCard.setMaxWidth(
                700);

        monthlyCard.setPadding(
                new Insets(25));

        monthlyCard.setSpacing(
                12);


        Label monthlyTitle =
                new Label(
                        "Month-wise Investment Plan");

        monthlyTitle.setTextFill(
                DARK_TEXT);

        monthlyTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21));


        VBox monthlyList =
                new VBox(8);


        Label monthlyInstruction =
                new Label(
                        "Calculate your plan to see the monthly investment.");

        monthlyInstruction.setTextFill(
                GREY);

        monthlyInstruction.setFont(
                Font.font(
                        "Arial",
                        13));


        monthlyList.getChildren()
                .add(
                        monthlyInstruction);


        monthlyCard.getChildren()
                .addAll(
                        monthlyTitle,
                        monthlyList);


        // =====================================================
        // SUGGESTION CARD
        // =====================================================

        VBox suggestionCard =
                new VBox(8);

        suggestionCard.setMaxWidth(
                700);

        suggestionCard.setPadding(
                new Insets(22));

        suggestionCard.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#163522"),
                                new CornerRadii(12),
                                Insets.EMPTY)));


        suggestionCard.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web("#356B42"),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(12),
                                new BorderWidths(1))));


        Label suggestionTitle =
                new Label(
                        "💡 Investment Suggestion");

        suggestionTitle.setTextFill(
                GREEN);

        suggestionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));


        Label suggestionText =
                new Label(
                        "Enter your investment details to receive a simple monthly investment suggestion.");

        suggestionText.setTextFill(
                Color.web("#B8CDBB"));

        suggestionText.setFont(
                Font.font(
                        "Arial",
                        13));

        suggestionText.setWrapText(
                true);


        suggestionCard.getChildren()
                .addAll(
                        suggestionTitle,
                        suggestionText);


        // =====================================================
        // CALCULATE
        // =====================================================

        calculate.setOnAction(
                event -> {

                    try {

                        String selected =
                                courseBox.getValue();

                        int months =
                                durationBox.getValue();


                        double totalInvestment =
                                Double.parseDouble(
                                        totalInvestmentField
                                                .getText()
                                                .trim());


                        double initialInvestment =
                                Double.parseDouble(
                                        initialInvestmentField
                                                .getText()
                                                .trim());


                        if (totalInvestment <= 0) {

                            errorLabel.setText(
                                    "Total investment must be greater than 0.");

                            return;
                        }


                        if (initialInvestment < 0) {

                            errorLabel.setText(
                                    "Initial investment cannot be negative.");

                            return;
                        }


                        if (initialInvestment >
                                totalInvestment) {

                            errorLabel.setText(
                                    "Initial investment cannot be greater than total investment.");

                            return;
                        }


                        double remainingInvestment =
                                totalInvestment -
                                initialInvestment;


                        double monthlyInvestment =
                                remainingInvestment /
                                months;


                        selectedCourse.setText(
                                "Course: " + selected);


                        totalValue.setText(
                                "Total Investment: ₹" +
                                String.format(
                                        "%,.0f",
                                        totalInvestment));


                        initialValue.setText(
                                "Initial Investment: ₹" +
                                String.format(
                                        "%,.0f",
                                        initialInvestment));


                        remainingValue.setText(
                                "Remaining Investment: ₹" +
                                String.format(
                                        "%,.0f",
                                        remainingInvestment));


                        monthlyValue.setText(
                                "Monthly Investment: ₹" +
                                String.format(
                                        "%,.2f",
                                        monthlyInvestment));


                        monthlyList.getChildren()
                                .clear();


                        for (
                                int i = 1;
                                i <= months;
                                i++) {

                            double currentAmount =
                                    monthlyInvestment;


                            if (i == months) {

                                currentAmount =
                                        remainingInvestment -
                                        (
                                                monthlyInvestment *
                                                (months - 1)
                                        );
                            }


                            HBox monthRow =
                                    new HBox();


                            monthRow.setAlignment(
                                    Pos.CENTER_LEFT);

                            monthRow.setPadding(
                                    new Insets(
                                            12,
                                            15,
                                            12,
                                            15));


                            monthRow.setBackground(
                                    new Background(
                                            new BackgroundFill(
                                                    Color.web("#101914"),
                                                    new CornerRadii(8),
                                                    Insets.EMPTY)));


                            Label monthLabel =
                                    new Label(
                                            "Month " + i);

                            monthLabel.setTextFill(
                                    DARK_TEXT);

                            monthLabel.setFont(
                                    Font.font(
                                            "Arial",
                                            FontWeight.BOLD,
                                            14));


                            Region spacer =
                                    new Region();

                            HBox.setHgrow(
                                    spacer,
                                    Priority.ALWAYS);


                            Label amountLabel =
                                    new Label(
                                            "₹" +
                                            String.format(
                                                    "%,.2f",
                                                    currentAmount));

                            amountLabel.setTextFill(
                                    GREEN);

                            amountLabel.setFont(
                                    Font.font(
                                            "Arial",
                                            FontWeight.BOLD,
                                            14));


                            monthRow.getChildren()
                                    .addAll(
                                            monthLabel,
                                            spacer,
                                            amountLabel);


                            monthlyList.getChildren()
                                    .add(monthRow);
                        }


                        progressBar.setProgress(
                                initialInvestment /
                                totalInvestment);


                        int progress =
                                (int)
                                (
                                    (
                                        initialInvestment /
                                        totalInvestment
                                    ) * 100
                                );


                        progressLabel.setText(
                                "Investment Progress: " +
                                progress +
                                "%");


                        suggestionText.setText(
                                "For " +
                                selected +
                                ", your remaining investment is ₹" +
                                String.format(
                                        "%,.0f",
                                        remainingInvestment) +
                                ". You need approximately ₹" +
                                String.format(
                                        "%,.2f",
                                        monthlyInvestment) +
                                " per month for " +
                                months +
                                " months.");


                        errorLabel.setText("");


                    } catch (
                            NumberFormatException exception) {

                        errorLabel.setText(
                                "Please enter valid investment amounts.");
                    }
                });


        // =====================================================
        // PAGE LAYOUT
        // =====================================================

        VBox wrapper =
                new VBox(
                        20,
                        inputCard,
                        summaryCard,
                        monthlyCard,
                        suggestionCard);

        wrapper.setAlignment(
                Pos.TOP_CENTER);


        ScrollPane scroll =
                new ScrollPane(
                        wrapper);

        scroll.setFitToWidth(
                true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color:#050B0A;" +
                "-fx-background:#050B0A;" +
                "-fx-control-inner-background:#050B0A;");

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);


        page.getChildren()
                .addAll(
                        topBar,
                        scroll);

        return page;
    }


    // =========================================================
    // SAVED SCHEMES
    // =========================================================

    private VBox createSavedSchemesSection() {

        VBox section =
                new VBox(15);

        section.setPadding(
                new Insets(20));

        section.setStyle(
                "-fx-background-color:#050B0A;" 
               // "-fx-background-radius:12;" +
               // "-fx-border-color:#294734;" +
               // "-fx-border-radius:12;"
               );


        Label title =
                new Label(
                        "♥ Saved Schemes");

        title.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:40px;" +
                "-fx-font-weight:bold;");


        List<Scheme> schemes =
                SavedSchemesManager.getSchemes();


        section.getChildren()
                .add(title);


        if (schemes.isEmpty()) {

            Label empty =
                    new Label(
                            "You haven't liked any schemes yet.");

            empty.setStyle(
                    "-fx-text-fill:#A9B8AC;" +
                    "-fx-font-size:13px;");

            section.getChildren()
                    .add(empty);

            return section;
        }


        for (Scheme scheme : schemes) {

            section.getChildren()
                    .add(
                            createSavedSchemeCard(
                                    scheme,
                                    section));
        }


        return section;
    }


    // =========================================================
    // SAVED SCHEME CARD
    // =========================================================

    private VBox createSavedSchemeCard(
            Scheme scheme,
            VBox section) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(15));

        card.setStyle(
                "-fx-background-color:#15331F;" +
                "-fx-background-radius:10;" +
                "-fx-border-color:#294734;" +
                "-fx-border-radius:10;");


        Label name =
                new Label(
                        scheme.getSchemeName());

        name.setWrapText(
                true);

        name.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;");


        Label category =
                new Label(
                        "Category: " +
                        scheme.getCategory());

        category.setStyle(
                "-fx-text-fill:#7ED957;" +
                "-fx-font-size:11px;");


        Label information =
                new Label(
                        scheme.getInformation());

        information.setWrapText(
                true);

        information.setStyle(
                "-fx-text-fill:#A9B8AC;" +
                "-fx-font-size:12px;");


        Button dislikeButton =
                new Button(
                        "♥  Dislike");

        dislikeButton.setPrefHeight(
                32);

        dislikeButton.setCursor(
                Cursor.HAND);

        dislikeButton.setStyle(
                "-fx-background-color:#101d18;" +
                "-fx-text-fill:#7ED957;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;");


        dislikeButton.setOnAction(
                e -> {

                    SavedSchemesManager
                            .removeScheme(scheme);

                    root.setCenter(
                            createSavedSchemesSection());
                });


        card.getChildren()
                .addAll(
                        name,
                        category,
                        information,
                        dislikeButton);

        return card;
    }


    // =========================================================
    // WISHLIST PAGE
    // =========================================================

    private VBox createWishlistPage() {

        return createWishlistPage(
                "All");
    }


    private VBox createWishlistPage(
            String selectedTab) {

        VBox page =
                new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox topBar =
                createTopBar(
                        "Wishlist",
                        "Your saved farming products and resources.");


        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35));

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#0A1710"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox tabs =
                new HBox(35);

        tabs.setAlignment(
                Pos.CENTER_LEFT);


        tabs.getChildren()
                .addAll(
                        createWishlistTab(
                                "All",
                                selectedTab.equals("All")),

                        createWishlistTab(
                                "Products",
                                selectedTab.equals("Products")),

                        createWishlistTab(
                                "Courses",
                                selectedTab.equals("Courses")),

                        createWishlistTab(
                                "Resources",
                                selectedTab.equals("Resources")));


        HBox cards =
                new HBox(22);


        if (selectedTab.equals("All") ||
                selectedTab.equals("Products")) {

            cards.getChildren()
                    .add(
                            createWishlistProductCard());
        }


        if (selectedTab.equals("All") ||
                selectedTab.equals("Courses")) {

            cards.getChildren()
                    .add(
                            createWishlistCourseCard());
        }


        content.getChildren()
                .add(tabs);


        if (selectedTab.equals("Resources")) {

            content.getChildren()
                    .add(
                            createEmptyWishlistMessage());

        } else {

            content.getChildren()
                    .add(cards);
        }


        ScrollPane scroll =
                new ScrollPane(
                        content);

        scroll.setFitToWidth(
                true);

        scroll.setFitToHeight(
                true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color:#0A1710;" +
                "-fx-control-inner-background:#0A1710;");


        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);


        page.getChildren()
                .addAll(
                        topBar,
                        scroll);

        return page;
    }


    // =========================================================
    // WISHLIST TAB
    // =========================================================

    private Button createWishlistTab(
            String text,
            boolean selected) {

        Button tab =
                new Button(text);

        tab.setCursor(
                Cursor.HAND);

        tab.setFont(
                Font.font(
                        "Arial",
                        selected
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        18));

        tab.setTextFill(
                selected
                        ? LIGHT_GREEN
                        : DARK_TEXT);

        tab.setPadding(
                new Insets(
                        0,
                        0,
                        10,
                        0));

        tab.setBackground(
                Background.EMPTY);


        if (selected) {

            tab.setBorder(
                    new Border(
                            new BorderStroke(
                                    DARK_GREEN,
                                    BorderStrokeStyle.SOLID,
                                    CornerRadii.EMPTY,
                                    new BorderWidths(
                                            0,
                                            0,
                                            2,
                                            0))));

        } else {

            tab.setBorder(
                    Border.EMPTY);
        }


        tab.setOnAction(
                event ->
                        root.setCenter(
                                createWishlistPage(
                                        text)));

        return tab;
    }


    // =========================================================
    // WISHLIST PRODUCT CARD
    // =========================================================

    private VBox createWishlistProductCard() {

        VBox card =
                createWishlistCard();


        StackPane productImage =
                new StackPane();

        productImage.setPrefSize(
                240,
                168);


        ImageView imageView =
                createWishlistImage(
                        "/fertilizer.png");


        Label heart =
                new Label("♥");

        heart.setTextFill(
                Color.rgb(
                        200,
                        20,
                        25));

        heart.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25));

        heart.setPadding(
                new Insets(
                        4,
                        9,
                        4,
                        9));

        heart.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#1B3B25"),
                                new CornerRadii(20),
                                Insets.EMPTY)));


        productImage.getChildren()
                .addAll(
                        imageView,
                        heart);


        StackPane.setAlignment(
                heart,
                Pos.TOP_RIGHT);

        StackPane.setMargin(
                heart,
                new Insets(8));


        Label type =
                createWishlistBadge(
                        "PRODUCT");


        Label price =
                new Label(
                        "₹1,500");

        price.setTextFill(
                GREEN);

        price.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));


        Region priceSpace =
                new Region();

        HBox.setHgrow(
                priceSpace,
                Priority.ALWAYS);


        HBox details =
                new HBox(
                        type,
                        priceSpace,
                        price);

        details.setAlignment(
                Pos.CENTER_LEFT);


        Label title =
                createWishlistTitle(
                        "Organic Fertilizer");


        Label description =
                createWishlistDescription(
                        "Natural fertilizer suitable for improving soil health and crop growth.");


        Button action =
                createWishlistActionButton(
                        "View Product");


        action.setOnAction(
                event ->
                        root.setCenter(
                                createWishlistDetailsPage(
                                        "Product Details",
                                        "Organic Fertilizer",
                                        "₹1,500",
                                        "/fertilizer.png",
                                        "Natural fertilizer suitable for improving soil health and crop growth.")));


        card.getChildren()
                .addAll(
                        productImage,
                        details,
                        title,
                        description,
                        action);

        return card;
    }


    // =========================================================
    // WISHLIST COURSE CARD
    // =========================================================

    private VBox createWishlistCourseCard() {

        VBox card =
                createWishlistCard();


        StackPane courseImage =
                new StackPane();

        courseImage.setPrefSize(
                240,
                168);


        ImageView courseImageView =
                createWishlistImage(
                        "/irrigation.png");


        Label heart =
                new Label("♥");

        heart.setTextFill(
                Color.rgb(
                        200,
                        20,
                        25));

        heart.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25));

        heart.setPadding(
                new Insets(
                        4,
                        9,
                        4,
                        9));

        heart.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#1B3B25"),
                                new CornerRadii(20),
                                Insets.EMPTY)));


        courseImage.getChildren()
                .addAll(
                        courseImageView,
                        heart);


        StackPane.setAlignment(
                heart,
                Pos.TOP_RIGHT);

        StackPane.setMargin(
                heart,
                new Insets(8));


        Label type =
                createWishlistBadge(
                        "COURSE");


        Label price =
                new Label(
                        "Free");

        price.setTextFill(
                GREEN);

        price.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));


        Region priceSpace =
                new Region();

        HBox.setHgrow(
                priceSpace,
                Priority.ALWAYS);


        HBox details =
                new HBox(
                        type,
                        priceSpace,
                        price);

        details.setAlignment(
                Pos.CENTER_LEFT);


        Label title =
                createWishlistTitle(
                        "Modern Irrigation\nTechniques");


        Label description =
                createWishlistDescription(
                        "Learn water-saving strategies and advanced drip irrigation systems...");


        Button action =
                createWishlistActionButton(
                        "View Course");


        action.setOnAction(
                event ->
                        root.setCenter(
                                createWishlistDetailsPage(
                                        "Course Details",
                                        "Modern Irrigation Techniques",
                                        "Free",
                                        "/irrigation.png",
                                        "Learn practical water-saving methods, drip irrigation basics, and ways to manage water efficiently.")));


        card.getChildren()
                .addAll(
                        courseImage,
                        details,
                        title,
                        description,
                        action);

        return card;
    }


    // =========================================================
    // WISHLIST CARD
    // =========================================================

    private VBox createWishlistCard() {

        VBox card =
                new VBox(13);

        card.setPrefWidth(
                270);

        card.setPadding(
                new Insets(22));

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(14),
                                Insets.EMPTY)));

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER_COLOR,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(14),
                                new BorderWidths(1))));

        return card;
    }


    // =========================================================
    // WISHLIST IMAGE
    // =========================================================

    private ImageView createWishlistImage(
            String imagePath) {

        URL imageUrl =
                getClass()
                        .getResource(imagePath);


        if (imageUrl == null) {

            ImageView empty =
                    new ImageView();

            empty.setFitWidth(
                    240);

            empty.setFitHeight(
                    168);

            return empty;
        }


        ImageView imageView =
                new ImageView(
                        new Image(
                                imageUrl
                                        .toExternalForm()));

        imageView.setFitWidth(
                240);

        imageView.setFitHeight(
                168);

        imageView.setPreserveRatio(
                false);

        return imageView;
    }


    // =========================================================
    // WISHLIST BADGE
    // =========================================================

    private Label createWishlistBadge(
            String text) {

        Label badge =
                new Label(text);

        badge.setTextFill(
                DARK_GREEN);

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        badge.setPadding(
                new Insets(
                        5,
                        8,
                        5,
                        8));

        badge.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        229,
                                        240,
                                        222),
                                new CornerRadii(4),
                                Insets.EMPTY)));

        return badge;
    }


    // =========================================================
    // WISHLIST TITLE
    // =========================================================

    private Label createWishlistTitle(
            String text) {

        Label title =
                new Label(text);

        title.setTextFill(
                DARK_TEXT);

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22));

        title.setWrapText(
                true);

        return title;
    }


    // =========================================================
    // WISHLIST DESCRIPTION
    // =========================================================

    private Label createWishlistDescription(
            String text) {

        Label description =
                new Label(text);

        description.setTextFill(
                GREY);

        description.setFont(
                Font.font(
                        "Arial",
                        13));

        description.setWrapText(
                true);

        description.setMinHeight(
                47);

        return description;
    }


    // =========================================================
    // WISHLIST ACTION BUTTON
    // =========================================================

    private Button createWishlistActionButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(
                42);

        button.setMaxWidth(
                Double.MAX_VALUE);

        button.setCursor(
                Cursor.HAND);

        button.setTextFill(
                Color.WHITE);

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(7),
                                Insets.EMPTY)));

        return button;
    }


    // =========================================================
    // EMPTY WISHLIST
    // =========================================================

    private VBox createEmptyWishlistMessage() {

        VBox message =
                createWishlistCard();

        message.setPrefWidth(
                420);


        Label title =
                createWishlistTitle(
                        "No saved resources yet");


        Label text =
                createWishlistDescription(
                        "Resources you save in the future will appear here.");


        message.getChildren()
                .addAll(
                        title,
                        text);

        return message;
    }


    // =========================================================
    // WISHLIST DETAILS
    // =========================================================

    private VBox createWishlistDetailsPage(
            String pageTitle,
            String itemTitle,
            String price,
            String imagePath,
            String description) {

        VBox page =
                new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox topBar =
                createTopBar(
                        pageTitle,
                        "Saved item from your wishlist.");


        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35));


        Button backButton =
                new Button(
                        "← Back to Wishlist");

        backButton.setCursor(
                Cursor.HAND);

        backButton.setTextFill(
                GREEN);

        backButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        backButton.setBackground(
                Background.EMPTY);

        backButton.setBorder(
                Border.EMPTY);

        backButton.setOnAction(
                event ->
                        root.setCenter(
                                createWishlistPage()));


        HBox detailCard =
                new HBox(28);

        detailCard.setPadding(
                new Insets(25));

        detailCard.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(14),
                                Insets.EMPTY)));

        detailCard.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER_COLOR,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(14),
                                new BorderWidths(1))));


        ImageView image =
                createWishlistImage(
                        imagePath);

        image.setFitWidth(
                300);

        image.setFitHeight(
                210);


        VBox information =
                new VBox(15);


        Label title =
                createWishlistTitle(
                        itemTitle);

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27));


        Label priceLabel =
                new Label(price);

        priceLabel.setTextFill(
                GREEN);

        priceLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22));


        Label descriptionLabel =
                new Label(
                        description);

        descriptionLabel.setTextFill(
                GREY);

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        15));

        descriptionLabel.setWrapText(
                true);

        descriptionLabel.setMaxWidth(
                420);


        Button saveButton =
                createWishlistActionButton(
                        "Saved to Wishlist ♥");

        saveButton.setDisable(
                true);


        information.getChildren()
                .addAll(
                        title,
                        priceLabel,
                        descriptionLabel,
                        saveButton);


        detailCard.getChildren()
                .addAll(
                        image,
                        information);


        content.getChildren()
                .addAll(
                        backButton,
                        detailCard);


        ScrollPane scroll =
                new ScrollPane(
                        content);

        scroll.setFitToWidth(
                true);

        scroll.setFitToHeight(
                true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color:#0B1F14;" +
                "-fx-control-inner-background:#0B1F14;");


        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);


        page.getChildren()
                .addAll(
                        topBar,
                        scroll);

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
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        HBox topBar =
                createTopBar(
                        titleText,
                        subtitleText);


        VBox content =
                new VBox();


        content.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#0B1F14"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));


        content.setPadding(
                new Insets(
                        0,
                        35,
                        35,
                        35));

        content.setSpacing(
                20);


        ScrollPane scroll =
                new ScrollPane(
                        content);

        scroll.setFitToWidth(
                true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color:#0B1F14;" +
                "-fx-background:#0B1F14;" +
                "-fx-control-inner-background:#0B1F14;");


        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);


        page.getChildren()
                .addAll(
                        topBar,
                        scroll);

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
                Priority.ALWAYS);


        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        30));


        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                DARK_TEXT);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));


        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setTextFill(
                GREY);

        descriptionLabel.setWrapText(
                true);

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        12));


        card.getChildren()
                .addAll(
                        iconLabel,
                        titleLabel,
                        descriptionLabel);

        return card;
    }


    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card =
                new VBox();

        card.setSpacing(
                12);

        card.setPadding(
                new Insets(22));

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#102A18"),
                                new CornerRadii(15),
                                Insets.EMPTY)));

        HBox.setHgrow(
                card,
                Priority.ALWAYS);

        return card;
    }


    // =========================================================
    // CARD
    // =========================================================

    private VBox createCard() {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(22));

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color:#12291A;" +
                "-fx-border-color:#294734;" +
                "-fx-border-radius:14;" +
                "-fx-background-radius:14;");

        return card;
    }


    // =========================================================
    // ADD FIELD
    // =========================================================

    private void addField(
            GridPane grid,
            String title,
            javafx.scene.Node field,
            int column,
            int row) {

        VBox box =
                new VBox(6);


        Label label =
                new Label(title);

        label.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;");


        box.getChildren()
                .addAll(
                        label,
                        field);


        grid.add(
                box,
                column,
                row);


        GridPane.setHgrow(
                box,
                Priority.ALWAYS);
    }


    // =========================================================
    // TEXT FIELD
    // =========================================================

    private TextField createTextField(
            String prompt) {

        TextField field =
                new TextField();

        field.setPromptText(
                prompt);

        field.setPrefHeight(
                38);

        field.setMaxWidth(
                Double.MAX_VALUE);

        styleTextField(
                field);

        return field;
    }


    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField field) {

        field.setStyle(
                "-fx-background-color:#0F2116;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#294734;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-padding:8 10;" +
                "-fx-focus-color:transparent;" +
                "-fx-faint-focus-color:transparent;");
    }


    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            ComboBox<?> box) {

        box.setStyle(
                "-fx-background-color:#0F2116;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-border-color:#294734;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-focus-color:transparent;" +
                "-fx-faint-focus-color:transparent;");
    }


    // =========================================================
    // GRID COLUMN WIDTH
    // =========================================================

    private void columnConstraintsHelper(
            GridPane grid) {

        ColumnConstraints column1 =
                new ColumnConstraints();

        ColumnConstraints column2 =
                new ColumnConstraints();


        column1.setPercentWidth(
                50);

        column2.setPercentWidth(
                50);


        column1.setHgrow(
                Priority.ALWAYS);

        column2.setHgrow(
                Priority.ALWAYS);


        grid.getColumnConstraints()
                .clear();


        grid.getColumnConstraints()
                .addAll(
                        column1,
                        column2);
    }


    // =========================================================
    // VERTICAL SPACE
    // =========================================================

    private Region createSpace(
            double height) {

        Region space =
                new Region();

        space.setMinHeight(
                height);

        space.setPrefHeight(
                height);

        space.setMaxHeight(
                height);

        return space;
    }


    // =========================================================
    // HORIZONTAL SPACE
    // =========================================================

    private Region createWidthSpace(
            double width) {

        Region space =
                new Region();

        space.setMinWidth(
                width);

        space.setPrefWidth(
                width);

        space.setMaxWidth(
                width);

        return space;
    }


    // =========================================================
    // SAFE
    // =========================================================

    private String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }


    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "AgroBiz");

        alert.setHeaderText(
                null);

        alert.setContentText(
                message);

        alert.showAndWait();
    }
}