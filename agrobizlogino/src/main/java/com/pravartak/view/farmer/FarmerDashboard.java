
package com.pravartak.view.farmer;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.net.URL;

import com.pravartak.controller.farmercontoller.FarmerProfileController;
import com.pravartak.model.farmer_model.FarmerProfile;
import com.pravartak.view.login.LoginPage;
import javafx.scene.control.ProgressBar;
import com.pravartak.dao.UserDAO;
import com.pravartak.model.UserModel;
import com.pravartak.model.admin.Scheme;
import com.pravartak.view.farmer.SavedSchemesManager;

import com.pravartak.services.GroqService;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;
//import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
// import javafx.scene.control.ProgressBar;
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
// import javafx.scene.shape.Arc;
// import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.Base64;

public class FarmerDashboard {
        private final int farmerId;
        private final String firebaseUid;

        private final FarmerProfileController profileController;
        private final UserDAO userDAO;
        // // // LOGOUT CALLBACK
        // // private final Runnable logoutAction;

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

        // // // ADMIN THEME COLORS
        private static final Color BACKGROUND = Color.web("#080C0D");
        private static final Color SIDEBAR = Color.web("#0D1213");
        private static final Color CARD = Color.web("#101718");
        private static final Color CARD_HOVER = Color.web("#172021");
        private static final Color SELECTED = Color.web("#245D35");
        private static final Color ACCENT = Color.web("#68D34A");
        private static final Color TEXT_PRIMARY = Color.web("#EEEEEE");
        private static final Color TEXT_SECONDARY = Color.web("#AAAAAA");
        private static final Color BORDER = Color.web("#242B2C");
        private static final Color ERROR = Color.web("#E57373");

        private static final Color DARK_GREEN = SIDEBAR;
        private static final Color GREEN = ACCENT;
        private static final Color LIGHT_GREEN = TEXT_SECONDARY;
        private static final Color CREAM = BACKGROUND;
        private static final Color DARK_TEXT = TEXT_PRIMARY;
        private static final Color GREY = TEXT_SECONDARY;
        private static final Color CARD_BACKGROUND = CARD;
        private static final Color BORDER_COLOR = BORDER;

        // // // SIDEBAR BUTTONS
        private Button homepageButton;
        private Button dashboardButton;
        private Button profileButton;
        private Button aiAdvisorButton;
        // private Button learningButton;
        // private Button wishlistButton;
        private Button investmentButton;
        private Button schemesButton;

        // // // MAIN BORDER PANE
        private BorderPane root;
        private StackPane profileImageContainer;

        // =========================================================
        // AI FARMING PLAN GENERATOR
        // =========================================================

        private final GroqService groqService = new GroqService();

        private final Map<String, String> farmingPlanAnswers = new LinkedHashMap<>();

        private final List<String> currentFarmingQuestionKeys = new ArrayList<>();

        private final List<String> currentFarmingQuestions = new ArrayList<>();

        private String selectedFarmingType = null;

        private int farmingQuestionIndex = 0;

        // AI PAGE CONTROLS
        private Label aiAssistantText;
        private TextField aiQuestionField;
        private Button aiAskButton;
        private Button generatePlanButton;

        // =============================================================
        // CONSTRUCTOR
        // =============================================================

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

                this.profileController = new FarmerProfileController();

                this.userDAO = new UserDAO();

                System.out.println(
                                "Farmer ID = " + this.farmerId);

                System.out.println(
                                "Firebase UID = " + this.firebaseUid);
        }

        // =========================================================
        // AI FARMING PLAN UI REFERENCES
        // =========================================================

        private VBox aiPlanContent;
        private ScrollPane aiPlanScroll;
        private Label aiPlanText;
        private VBox aiCard;
        private VBox aiCenter;
        private Button aiNewPlanButton;

        private VBox planContent;
        private ScrollPane planScroll;
        private Label planText;
        private Button newPlanButton;

        // =============================================================
        // GET FARMER ID
        // =============================================================

        public int getFarmerId() {

                return farmerId;
        }

        // =============================================================
        // GET FIREBASE UID
        // =============================================================

        public String getFirebaseUid() {

                return firebaseUid;
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
                // sidebar.setBackground(new Background(new BackgroundFill(DARK_GREEN,
                // CornerRadii.EMPTY, Insets.EMPTY)));
                sidebar.setBackground(new Background(
                                new BackgroundFill(SIDEBAR, CornerRadii.EMPTY, Insets.EMPTY)));
                // LOGO
                Label logo = new Label("🌱  Agro Biz");
                logo.setTextFill(ACCENT);
                logo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
                sidebar.getChildren().add(logo);
                sidebar.getChildren().add(createSpace(30));

                // MENU TITLE
                Label menu = new Label("FARMER MENU");
                menu.setTextFill(TEXT_SECONDARY);
                menu.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                menu.setPadding(new Insets(0, 0, 8, 15));
                sidebar.getChildren().add(menu);

                // HOMEPAGE
                homepageButton = createMenuButton("⌂", "Home");

                homepageButton.setOnAction(event -> {
                        HomePageFarmer homePageFarmer = new HomePageFarmer(farmerId, firebaseUid);
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
                // learningButton = createMenuButton("▣", "My Learning");
                // learningButton.setOnAction(event -> showPage("learning"));

                // WISHLIST
                // wishlistButton = createMenuButton("♙", "Wishlist");
                // wishlistButton.setOnAction(event -> showPage("wishlist"));

                // INVESTMENT
                investmentButton = createMenuButton("₹", "Investment Calculator");
                investmentButton.setOnAction(event -> showPage("investment"));

                // SCHEMES
                schemesButton = createMenuButton("◇", "Schemes & Subsidies");
                schemesButton.setOnAction(event -> showPage("schemes"));

                // ADD BUTTONS
                sidebar.getChildren().addAll(homepageButton, dashboardButton, profileButton, aiAdvisorButton,
                                investmentButton, schemesButton);

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

                // sidebar.getChildren().add(logout);

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
                button.setTextFill(TEXT_SECONDARY);
                button.setBackground(Background.EMPTY);
                button.setBorder(Border.EMPTY);
                return button;
        }

        // SELECTED BUTTON
        private void setSelectedMenuButton(Button selectedButton) {
                Button[] buttons = { homepageButton, dashboardButton, profileButton, aiAdvisorButton, investmentButton,
                                schemesButton };

                for (Button button : buttons) {
                        if (button == null) {
                                continue;
                        }

                        button.setTextFill(TEXT_SECONDARY);
                        button.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                        button.setBackground(Background.EMPTY);
                }

                selectedButton.setTextFill(TEXT_PRIMARY);
                selectedButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                selectedButton.setBackground(
                                new Background(new BackgroundFill(GREEN, new CornerRadii(10), Insets.EMPTY)));
        }

        // PAGE NAVIGATION
        // =========================================================
        // PAGE NAVIGATION
        // =========================================================

        private void showPage(String page) {

                switch (page) {

                        case "dashboard":

                                setSelectedMenuButton(dashboardButton);

                                root.setCenter(
                                                createDashboardPage());

                                break;

                        case "profile":

                                setSelectedMenuButton(profileButton);

                                // IMPORTANT:
                                // Profile menu opens READ-ONLY profile page.
                                root.setCenter(
                                                createProfileView());

                                break;

                        case "editProfile":

                                setSelectedMenuButton(profileButton);

                                // Edit Profile opens editable form.
                                root.setCenter(
                                                createProfileCard());

                                break;

                        case "ai":

                                setSelectedMenuButton(aiAdvisorButton);

                                root.setCenter(
                                                createAIAdvisorPage());

                                break;

                        // case "learning":

                        // setSelectedMenuButton(learningButton);

                        // root.setCenter(
                        // createLearningPage()
                        // );

                        // break;

                        // case "wishlist":

                        // setSelectedMenuButton(wishlistButton);

                        // root.setCenter(
                        // createWishlistPage()
                        // );

                        // break;

                        case "investment":

                                setSelectedMenuButton(investmentButton);

                                root.setCenter(
                                                createInvestmentPage());

                                break;

                        case "schemes":

                                setSelectedMenuButton(schemesButton);

                                root.setCenter(
                                                createSavedSchemesSection());

                                break;

                        case "Homepage":

                                setSelectedMenuButton(homepageButton);

                                HomePageFarmer homePageFarmer = new HomePageFarmer(
                                                farmerId,
                                                firebaseUid);

                                LoginPage.mainStage.setScene(
                                                homePageFarmer.getHomePageFarmer());

                                break;

                        default:

                                setSelectedMenuButton(dashboardButton);

                                root.setCenter(
                                                createDashboardPage());

                                break;
                }
        }
        // =========================================================
        // PROFILE VIEW PAGE - READ ONLY
        // =========================================================

        private VBox createProfileView() {

                VBox main = new VBox();

                main.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

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
                                                                BACKGROUND,
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
                                                                SIDEBAR,
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
                                "-fx-background-color:#101718;" +
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
                                TEXT_PRIMARY);

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
                                "-fx-background-color:#080C0D;" +
                                                "-fx-background:#080C0D;" +
                                                "-fx-control-inner-background:#080C0D;");

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
                                TEXT_PRIMARY);

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
                                "-fx-background-color:#101718;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:15;" +
                                                "-fx-border-width:1;");

                Label title = new Label(
                                titleText);

                title.setTextFill(
                                TEXT_PRIMARY);

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
                                TEXT_SECONDARY);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                Label valueLabel = new Label(
                                getProfileValue(value));

                valueLabel.setTextFill(
                                TEXT_PRIMARY);

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
                                "-fx-background-color:#101718;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-border-color:#242B2C;" +
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
        private VBox createDashboardPage() {
                VBox main = new VBox();
                main.setBackground(new Background(new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

                HBox topBar = createTopBar("Farmer Dashboard", "Manage your farm and make smarter decisions.");
                VBox content = createDashboardContent();
                content.setBackground(new Background(new BackgroundFill(BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                main.getChildren().addAll(topBar, scroll);
                return main;
        }

        // // // TOP BAR
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

                bar.getChildren().addAll(titleBox, createWidthSpace(25));
                return bar;
        }

        // DASHBOARD CONTENT
        // ============================================================
        // DASHBOARD CONTENT
        // ============================================================

        private VBox createDashboardContent() {

                VBox content = new VBox();

                content.setPadding(
                                new Insets(30, 35, 35, 35));

                content.setSpacing(22);

                // ========================================================
                // WELCOME CARD
                // ========================================================

                content.getChildren().add(
                                createWelcomeCard());

                // ========================================================
                // STAT CARDS
                // ========================================================

                HBox stats = new HBox();

                stats.setSpacing(20);

                stats.getChildren().addAll(

                                createStat(
                                                "🛒",
                                                "My Products",
                                                "8",
                                                "Products uploaded"),

                                createStat(
                                                "📚",
                                                "Liked Courses",
                                                "5",
                                                "Courses liked"),

                                createStat(
                                                "🏛",
                                                "Saved Schemes",
                                                "3",
                                                "Schemes saved"),

                                createStat(
                                                "♡",
                                                "Wishlist",
                                                "7",
                                                "Items saved"));

                content.getChildren().add(
                                stats);

                // ========================================================
                // LOWER SECTION
                // ========================================================

                HBox lower = new HBox();

                lower.setSpacing(22);

                // --------------------------------------------------------
                // MY PRODUCTS
                // --------------------------------------------------------

                VBox products = createMyProductsCard();

                // --------------------------------------------------------
                // MY AGRO BIZ
                // --------------------------------------------------------

                VBox agroBiz = createMyAgroBizCard();

                HBox.setHgrow(
                                products,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                agroBiz,
                                Priority.ALWAYS);

                lower.getChildren().addAll(
                                products,
                                agroBiz);

                content.getChildren().add(
                                lower);

                // ========================================================
                // QUICK ACTIONS
                // ========================================================

                content.getChildren().add(
                                createQuickActions());

                return content;
        }

        // ============================================================
        // WELCOME CARD
        // ============================================================

        private HBox createWelcomeCard() {

                HBox card = new HBox();

                card.setPadding(
                                new Insets(30, 35, 30, 35));

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                CARD,
                                                                new CornerRadii(17),
                                                                Insets.EMPTY)));

                card.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(17),
                                                                new BorderWidths(1))));

                // ========================================================
                // TEXT
                // ========================================================

                VBox text = new VBox();

                text.setSpacing(7);

                Label title = new Label(
                                "Good evening, Farmer! 🌱");

                title.setTextFill(
                                Color.WHITE);

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                29));

                Label description = new Label(
                                "Manage your products, courses, schemes and saved items.");

                description.setTextFill(
                                TEXT_SECONDARY);

                description.setFont(
                                Font.font(
                                                "Arial",
                                                16));

                text.getChildren().addAll(
                                title,
                                description);

                // ========================================================
                // SPACER
                // ========================================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // ========================================================
                // ICON
                // ========================================================

                Label plant = new Label(
                                "🌿");

                plant.setFont(
                                Font.font(
                                                "Arial",
                                                60));

                card.getChildren().addAll(
                                text,
                                spacer,
                                plant);

                return card;
        }

        // ============================================================
        // STAT CARD
        // ============================================================

        private VBox createStat(
                        String icon,
                        String title,
                        String value,
                        String subtitle) {

                VBox card = new VBox();

                card.setSpacing(8);

                card.setPadding(
                                new Insets(20));

                card.setPrefHeight(
                                160);

                HBox.setHgrow(
                                card,
                                Priority.ALWAYS);

                // ========================================================
                // CARD BACKGROUND
                // ========================================================

                card.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                CARD,
                                                                new CornerRadii(15),
                                                                Insets.EMPTY)));

                card.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(15),
                                                                new BorderWidths(1))));

                // ========================================================
                // ICON
                // ========================================================

                Label iconLabel = new Label(
                                icon);

                iconLabel.setTextFill(
                                ACCENT);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                22));

                iconLabel.setPrefSize(
                                48,
                                48);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(10),
                                                                Insets.EMPTY)));

                // ========================================================
                // TITLE
                // ========================================================

                Label titleLabel = new Label(
                                title);

                titleLabel.setTextFill(
                                GREY);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                HBox top = new HBox();

                top.setSpacing(12);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                top.getChildren().addAll(
                                iconLabel,
                                titleLabel);

                // ========================================================
                // VALUE
                // ========================================================

                Label valueLabel = new Label(
                                value);

                valueLabel.setTextFill(
                                DARK_TEXT);

                valueLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                27));

                // ========================================================
                // SUBTITLE
                // ========================================================

                Label sub = new Label(
                                subtitle);

                sub.setTextFill(
                                GREEN);

                sub.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                card.getChildren().addAll(
                                top,
                                valueLabel,
                                sub);

                return card;
        }

        // ============================================================
        // MY PRODUCTS CARD
        // ============================================================

        private VBox createMyProductsCard() {

                VBox card = createWhiteCard();

                // ========================================================
                // TITLE
                // ========================================================

                Label title = new Label(
                                "My Products");

                title.setTextFill(
                                DARK_TEXT);

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                card.getChildren().add(
                                title);

                // ========================================================
                // PRODUCT 1
                // ========================================================

                card.getChildren().add(
                                createProductItem(
                                                "🌾",
                                                "Organic Wheat",
                                                "Product uploaded"));

                // ========================================================
                // PRODUCT 2
                // ========================================================

                card.getChildren().add(
                                createProductItem(
                                                "🌱",
                                                "Organic Soybean",
                                                "Product uploaded"));

                // ========================================================
                // PRODUCT 3
                // ========================================================

                card.getChildren().add(
                                createProductItem(
                                                "🥬",
                                                "Fresh Vegetables",
                                                "Product uploaded"));

                return card;
        }

        // ============================================================
        // PRODUCT ITEM
        // ============================================================

        private HBox createProductItem(
                        String icon,
                        String name,
                        String description) {

                HBox row = new HBox();

                row.setSpacing(
                                12);

                row.setPadding(
                                new Insets(
                                                12,
                                                0,
                                                8,
                                                0));

                row.setAlignment(
                                Pos.CENTER_LEFT);

                // ========================================================
                // ICON
                // ========================================================

                Label iconLabel = new Label(
                                icon);

                iconLabel.setPrefSize(
                                42,
                                42);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                20));

                iconLabel.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                LIGHT_GREEN,
                                                                new CornerRadii(9),
                                                                Insets.EMPTY)));

                // ========================================================
                // TEXT
                // ========================================================

                VBox text = new VBox();

                text.setSpacing(
                                3);

                Label nameLabel = new Label(
                                name);

                nameLabel.setTextFill(
                                DARK_TEXT);

                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                Label descriptionLabel = new Label(
                                description);

                descriptionLabel.setTextFill(
                                GREY);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                text.getChildren().addAll(
                                nameLabel,
                                descriptionLabel);

                // ========================================================
                // SPACER
                // ========================================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // ========================================================
                // VIEW
                // ========================================================

                Label view = new Label(
                                "View →");

                view.setTextFill(
                                GREEN);

                view.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                row.getChildren().addAll(
                                iconLabel,
                                text,
                                spacer,
                                view);

                return row;
        }

        // ============================================================
        // MY AGRO BIZ CARD
        // ============================================================

        private VBox createMyAgroBizCard() {

                VBox card = createWhiteCard();

                // ========================================================
                // TITLE
                // ========================================================

                Label title = new Label(
                                "My Agro Biz");

                title.setTextFill(
                                DARK_TEXT);

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                card.getChildren().add(
                                title);

                // ========================================================
                // LIKED COURSES
                // ========================================================

                card.getChildren().add(
                                createDashboardItem(
                                                "📚",
                                                "Liked Courses",
                                                "5 courses liked"));

                // ========================================================
                // SAVED SCHEMES
                // ========================================================

                card.getChildren().add(
                                createDashboardItem(
                                                "🏛",
                                                "Saved Schemes",
                                                "3 schemes saved"));

                // ========================================================
                // WISHLIST
                // ========================================================

                card.getChildren().add(
                                createDashboardItem(
                                                "♡",
                                                "Wishlist",
                                                "7 items saved"));

                // ========================================================
                // AI ADVISOR
                // ========================================================

                card.getChildren().add(
                                createDashboardItem(
                                                "✦",
                                                "AI Farming Advisor",
                                                "Ask for farming guidance"));

                return card;
        }

        // ============================================================
        // DASHBOARD ITEM
        // ============================================================

        private HBox createDashboardItem(
                        String icon,
                        String title,
                        String description) {

                HBox row = new HBox();

                row.setSpacing(
                                12);

                row.setPadding(
                                new Insets(
                                                10,
                                                0,
                                                10,
                                                0));

                row.setAlignment(
                                Pos.CENTER_LEFT);

                // ========================================================
                // ICON
                // ========================================================

                Label iconLabel = new Label(
                                icon);

                iconLabel.setPrefSize(
                                40,
                                40);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                18));

                iconLabel.setTextFill(
                                GREEN);

                iconLabel.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                LIGHT_GREEN,
                                                                new CornerRadii(9),
                                                                Insets.EMPTY)));

                // ========================================================
                // TEXT
                // ========================================================

                VBox text = new VBox();

                text.setSpacing(
                                3);

                Label titleLabel = new Label(
                                title);

                titleLabel.setTextFill(
                                DARK_TEXT);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                Label descriptionLabel = new Label(
                                description);

                descriptionLabel.setTextFill(
                                GREY);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                text.getChildren().addAll(
                                titleLabel,
                                descriptionLabel);

                row.getChildren().addAll(
                                iconLabel,
                                text);

                return row;
        }

        // ============================================================
        // QUICK ACTIONS
        // ============================================================

        private HBox createQuickActions() {

                HBox actions = new HBox();

                actions.setSpacing(
                                18);

                // ========================================================
                // AI ADVISOR
                // ========================================================

                Button ai = createAction(
                                "✦  Ask AI Advisor");

                ai.setOnAction(
                                event -> showPage("ai"));

                // ========================================================
                // LEARNING
                // ========================================================

                Button learning = createAction(
                                "▣  My Learning");

                learning.setOnAction(
                                event -> showPage("learning"));

                // ========================================================
                // INVESTMENT
                // ========================================================

                Button investment = createAction(
                                "₹  Investment Calculator");

                investment.setOnAction(
                                event -> showPage("investment"));

                // ========================================================
                // SCHEMES
                // ========================================================

                Button schemes = createAction(
                                "◇  Schemes");

                schemes.setOnAction(
                                event -> showPage("schemes"));

                actions.getChildren().addAll(
                                ai,
                                learning,
                                investment,
                                schemes);

                return actions;
        }

        // ACTION BUTTON
        private Button createAction(String text) {
                Button button = new Button(text);
                button.setPrefHeight(55);
                HBox.setHgrow(button, Priority.ALWAYS);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setTextFill(ACCENT);
                button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
                button.setBackground(
                                new Background(new BackgroundFill(CARD, new CornerRadii(11), Insets.EMPTY)));
                button.setBorder(new Border(new BorderStroke(ACCENT, BorderStrokeStyle.SOLID,
                                new CornerRadii(11), new BorderWidths(1))));
                button.setCursor(Cursor.HAND);

                button.setOnMouseEntered(event -> {
                        button.setBackground(
                                        new Background(new BackgroundFill(CARD_HOVER, new CornerRadii(11),
                                                        Insets.EMPTY)));
                        button.setBorder(new Border(new BorderStroke(ACCENT, BorderStrokeStyle.SOLID,
                                        new CornerRadii(11), new BorderWidths(1.2))));
                });

                button.setOnMouseExited(event -> {
                        button.setBackground(
                                        new Background(new BackgroundFill(CARD, new CornerRadii(11), Insets.EMPTY)));
                        button.setBorder(new Border(new BorderStroke(ACCENT, BorderStrokeStyle.SOLID,
                                        new CornerRadii(11), new BorderWidths(1))));
                });

                return button;
        }

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
                                "-fx-text-fill:#777777;" +
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
                // LOAD EXISTING PROFILE
                // =====================================================

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
                                                "-fx-text-fill:#0D1213;" +
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

        // =========================================================
        // AI ADVISOR PAGE
        // =========================================================

        private VBox createAIAdvisorPage() {

                // =====================================================
                // RESET FARMING PLAN STATE
                // =====================================================

                selectedFarmingType = null;

                farmingQuestionIndex = 0;

                farmingPlanAnswers.clear();

                currentFarmingQuestionKeys.clear();

                currentFarmingQuestions.clear();

                // =====================================================
                // MAIN PAGE
                // =====================================================

                VBox page = new VBox();

                page.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                // =====================================================
                // FARMING PLAN VIEWER
                // =====================================================

                planContent = new VBox();

                planContent.setSpacing(18);

                planContent.setPadding(
                                new Insets(25));

                planContent.setFillWidth(true);

                planScroll = new ScrollPane(
                                planContent);

                planScroll.setFitToWidth(true);

                planScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                planScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                planScroll.setVisible(false);

                planScroll.setManaged(false);

                planScroll.setStyle(
                                "-fx-background-color: transparent;"
                                                + "-fx-background: transparent;"
                                                + "-fx-control-inner-background: transparent;");

                VBox.setVgrow(
                                planScroll,
                                Priority.ALWAYS);

                // =====================================================
                // TOP BAR
                // =====================================================

                HBox topBar = createTopBar(
                                "AI Farming Advisor",
                                "Get intelligent recommendations for your farm.");

                VBox.setMargin(
                                topBar,
                                Insets.EMPTY);

                // =====================================================
                // CONTENT
                // =====================================================

                VBox content = new VBox();

                content.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                content.setPadding(
                                new Insets(0, 35, 35, 35));

                content.setSpacing(0);

                VBox.setVgrow(
                                content,
                                Priority.ALWAYS);

                // =====================================================
                // AI CARD
                // =====================================================

                VBox card = createWhiteCard();

                card.setPrefHeight(650);

                card.setMaxHeight(
                                Double.MAX_VALUE);

                VBox.setVgrow(
                                card,
                                Priority.ALWAYS);

                VBox.setMargin(
                                card,
                                Insets.EMPTY);

                // =====================================================
                // CENTER
                // =====================================================

                VBox center = new VBox();

                center.setAlignment(
                                Pos.TOP_CENTER);

                center.setSpacing(
                                25);

                center.setPadding(
                                new Insets(20));

                center.setFillWidth(true);

                VBox.setVgrow(
                                center,
                                Priority.ALWAYS);

                // =====================================================
                // AI ICON
                // =====================================================

                Label aiIcon = new Label("✦");

                aiIcon.setPrefSize(
                                70,
                                70);

                aiIcon.setAlignment(
                                Pos.CENTER);

                aiIcon.setTextFill(
                                Color.WHITE);

                aiIcon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                32));

                aiIcon.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(50),
                                                                Insets.EMPTY)));

                // =====================================================
                // PAGE TITLE
                // =====================================================

                Label question = new Label(
                                "How can Agro Biz AI help your farm?");

                question.setTextFill(
                                Color.WHITE);

                question.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                23));

                question.setWrapText(
                                true);

                question.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // QUICK SUGGESTIONS
                // =====================================================

                HBox suggestions = new HBox();

                suggestions.setSpacing(
                                12);

                suggestions.setAlignment(
                                Pos.CENTER);

                Button cropButton = createSuggestionButton(
                                "🌾  Which crop should I grow?");

                Button yieldButton = createSuggestionButton(
                                "↗  How can I improve my yield?");

                Button irrigationButton = createSuggestionButton(
                                "💧  Optimize irrigation schedule");

                suggestions.getChildren().addAll(
                                cropButton,
                                yieldButton,
                                irrigationButton);

                // =====================================================
                // GENERATE PLAN BUTTON
                // =====================================================

                generatePlanButton = new Button(
                                "📋  Generate Personalized Farming Plan");

                generatePlanButton.setPrefHeight(
                                48);

                generatePlanButton.setPrefWidth(
                                330);

                generatePlanButton.setTextFill(
                                Color.WHITE);

                generatePlanButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                generatePlanButton.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(10),
                                                                Insets.EMPTY)));

                generatePlanButton.setCursor(
                                Cursor.HAND);

                // =====================================================
                // INITIAL CENTER CONTENT
                // =====================================================

                center.getChildren().addAll(
                                aiIcon,
                                question,
                                suggestions,
                                generatePlanButton);

                // =====================================================
                // MESSAGE AREA
                // =====================================================

                HBox message = new HBox();

                message.setMaxWidth(
                                Double.MAX_VALUE);

                message.setSpacing(
                                12);

                message.setPadding(
                                new Insets(20));

                message.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                SELECTED,
                                                                new CornerRadii(15),
                                                                Insets.EMPTY)));

                // =====================================================
                // ASSISTANT ICON
                // =====================================================

                Label assistantIcon = new Label("✦");

                assistantIcon.setPrefSize(
                                42,
                                42);

                assistantIcon.setMinSize(
                                42,
                                42);

                assistantIcon.setAlignment(
                                Pos.CENTER);

                assistantIcon.setTextFill(
                                Color.WHITE);

                assistantIcon.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(50),
                                                                Insets.EMPTY)));

                // =====================================================
                // ASSISTANT TEXT
                // =====================================================

                aiAssistantText = new Label(
                                "Hello Farmer! 🌱\n\n"
                                                + "I can help you make smarter farming "
                                                + "decisions and create a personalized "
                                                + "farming plan based on your farm "
                                                + "resources, capacity and goals.");

                aiAssistantText.setWrapText(
                                true);

                aiAssistantText.setTextFill(
                                GREY);

                aiAssistantText.setFont(
                                Font.font(
                                                "Arial",
                                                15));

                HBox.setHgrow(
                                aiAssistantText,
                                Priority.ALWAYS);

                message.getChildren().addAll(
                                assistantIcon,
                                aiAssistantText);

                // =====================================================
                // GENERATED FARMING PLAN TEXT
                // =====================================================

                planText = new Label();

                planText.setWrapText(
                                true);

                planText.setMaxWidth(
                                Double.MAX_VALUE);

                planText.setTextFill(
                                TEXT_PRIMARY);

                planText.setFont(
                                Font.font(
                                                "Arial",
                                                15));

                planText.setPadding(
                                new Insets(5));

                // =====================================================
                // NEW FARMING PLAN BUTTON
                // =====================================================

                newPlanButton = new Button(
                                "← New Farming Plan");

                newPlanButton.setPrefHeight(
                                45);

                newPlanButton.setPrefWidth(
                                180);

                newPlanButton.setTextFill(
                                Color.WHITE);

                newPlanButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                newPlanButton.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(9),
                                                                Insets.EMPTY)));

                newPlanButton.setCursor(
                                Cursor.HAND);

                // =====================================================
                // NEW PLAN ACTION
                // =====================================================

                newPlanButton.setOnAction(
                                event -> {

                                        planContent.getChildren().clear();

                                        planText.setText("");

                                        planScroll.setVisible(
                                                        false);

                                        planScroll.setManaged(
                                                        false);

                                        center.setVisible(
                                                        true);

                                        center.setManaged(
                                                        true);

                                        card.getChildren().clear();

                                        card.getChildren().add(
                                                        center);
                                });

                // =====================================================
                // QUESTION FIELD
                // =====================================================

                aiQuestionField = new TextField();

                aiQuestionField.setPromptText(
                                "Ask your farming question...");

                aiQuestionField.setPrefHeight(
                                50);

                aiQuestionField.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                aiQuestionField.setOnAction(
                                event -> {

                                        if (aiAskButton != null) {

                                                aiAskButton.fire();
                                        }
                                });

                // =====================================================
                // ASK AI BUTTON
                // =====================================================

                aiAskButton = new Button(
                                "Ask AI  ➤");

                aiAskButton.setPrefHeight(
                                50);

                aiAskButton.setPrefWidth(
                                120);

                aiAskButton.setTextFill(
                                Color.WHITE);

                aiAskButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                aiAskButton.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(9),
                                                                Insets.EMPTY)));

                aiAskButton.setCursor(
                                Cursor.HAND);

                // =====================================================
                // ASK AI ACTION
                // =====================================================

                aiAskButton.setOnAction(
                                event -> {

                                        String userQuestion = aiQuestionField
                                                        .getText()
                                                        .trim();

                                        if (userQuestion.isEmpty()) {

                                                return;
                                        }

                                        // =============================================
                                        // FARMING PLAN MODE
                                        // =============================================

                                        if (selectedFarmingType != null) {

                                                processFarmingPlanAnswer();

                                                return;
                                        }

                                        // =============================================
                                        // NORMAL AI MODE
                                        // =============================================

                                        askNormalAIQuestion(
                                                        userQuestion);
                                });

                // =====================================================
                // INPUT
                // =====================================================

                HBox input = new HBox(
                                aiQuestionField,
                                aiAskButton);

                input.setSpacing(
                                12);

                HBox.setHgrow(
                                aiQuestionField,
                                Priority.ALWAYS);

                // =====================================================
                // QUICK QUESTION ACTIONS
                // =====================================================

                cropButton.setOnAction(
                                event -> {

                                        aiQuestionField.setText(
                                                        "Which crop should I grow?");

                                        aiAskButton.fire();
                                });

                yieldButton.setOnAction(
                                event -> {

                                        aiQuestionField.setText(
                                                        "How can I improve my yield?");

                                        aiAskButton.fire();
                                });

                irrigationButton.setOnAction(
                                event -> {

                                        aiQuestionField.setText(
                                                        "How can I optimize my irrigation schedule?");

                                        aiAskButton.fire();
                                });

                // =====================================================
                // START PLAN BUTTON
                // =====================================================

                generatePlanButton.setOnAction(
                                event -> {

                                        startFarmingPlan(
                                                        center,
                                                        question,
                                                        suggestions,
                                                        generatePlanButton);
                                });

                // =====================================================
                // ADD MESSAGE + INPUT
                // =====================================================

                center.getChildren().addAll(
                                message,
                                new Region(),
                                input);

                Region aiSpacer = (Region) center.getChildren()
                                .get(
                                                center.getChildren().size() - 2);

                VBox.setVgrow(
                                aiSpacer,
                                Priority.ALWAYS);

                // =====================================================
                // CARD
                // =====================================================

                card.getChildren().add(
                                center);

                content.getChildren().add(
                                card);

                VBox.setVgrow(
                                card,
                                Priority.ALWAYS);

                // =====================================================
                // MAIN SCROLL
                // =====================================================

                ScrollPane scroll = new ScrollPane(
                                content);

                scroll.setFitToWidth(
                                true);

                scroll.setFitToHeight(
                                true);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scroll.setStyle(
                                "-fx-background-color: transparent;"
                                                + "-fx-background: transparent;"
                                                + "-fx-control-inner-background: transparent;");

                VBox.setVgrow(
                                scroll,
                                Priority.ALWAYS);

                // =====================================================
                // FINAL PAGE
                // =====================================================

                page.getChildren().addAll(
                                topBar,
                                scroll);

                VBox.setVgrow(
                                scroll,
                                Priority.ALWAYS);

                return page;
        }

        // =========================================================
        // AI SUGGESTION BUTTON
        // =========================================================

        private Button createSuggestionButton(
                        String text) {

                Button button = new Button(text);

                button.setPrefHeight(
                                40);

                button.setTextFill(
                                LIGHT_GREEN);

                button.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                button.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.WHITE,
                                                                new CornerRadii(20),
                                                                Insets.EMPTY)));

                button.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER_COLOR,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(20),
                                                                new BorderWidths(1))));

                button.setCursor(
                                Cursor.HAND);

                return button;
        }

        // =========================================================
        // NORMAL AI QUESTION
        // =========================================================

        private void askNormalAIQuestion(
                        String userQuestion) {

                if (userQuestion == null ||
                                userQuestion.trim().isEmpty()) {

                        return;
                }

                aiAssistantText.setText(
                                "🌱 AgroBiz AI is thinking...");

                aiAskButton.setDisable(
                                true);

                aiQuestionField.setDisable(
                                true);

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                return groqService.askQuestion(
                                                userQuestion);
                        }
                };

                task.setOnSucceeded(
                                event -> {

                                        aiAssistantText.setText(
                                                        task.getValue());

                                        aiAskButton.setDisable(
                                                        false);

                                        aiQuestionField.setDisable(
                                                        false);

                                        aiQuestionField.clear();
                                });

                task.setOnFailed(
                                event -> {

                                        Throwable error = task.getException();

                                        String errorMessage = "Unable to contact AgroBiz AI.";

                                        if (error != null &&
                                                        error.getMessage() != null) {

                                                errorMessage += "\n\nError: "
                                                                + error.getMessage();
                                        }

                                        aiAssistantText.setText(
                                                        errorMessage);

                                        aiAskButton.setDisable(
                                                        false);

                                        aiQuestionField.setDisable(
                                                        false);
                                });

                Thread thread = new Thread(task);

                thread.setDaemon(
                                true);

                thread.start();
        }

        // =========================================================
        // START FARMING PLAN
        // =========================================================

        private void startFarmingPlan(
                        VBox center,
                        Label question,
                        HBox suggestions,
                        Button planButton) {

                selectedFarmingType = null;

                farmingQuestionIndex = 0;

                farmingPlanAnswers.clear();

                currentFarmingQuestionKeys.clear();

                currentFarmingQuestions.clear();

                // =====================================================
                // CHANGE TITLE
                // =====================================================

                question.setText(
                                "Let's create your personalized "
                                                + "farming plan 🌱");

                // =====================================================
                // ASSISTANT MESSAGE
                // =====================================================

                aiAssistantText.setText(
                                "Great! 🌱\n\n"
                                                + "I will ask you a few questions about "
                                                + "your farm. Your answers will be used "
                                                + "to prepare a practical farming plan "
                                                + "with setup requirements, estimated "
                                                + "costs, timeline, risks and management steps.");

                // =====================================================
                // HIDE ORIGINAL PLAN BUTTON
                // =====================================================

                planButton.setVisible(
                                false);

                planButton.setManaged(
                                false);

                // =====================================================
                // REMOVE QUICK SUGGESTIONS
                // =====================================================

                suggestions.getChildren().clear();

                // =====================================================
                // SHOW FARMING TYPES
                // =====================================================

                createFarmingTypeButtons(
                                center,
                                question,
                                planButton);

                // =====================================================
                // DISABLE TEXT INPUT UNTIL TYPE SELECTED
                // =====================================================

                aiQuestionField.setDisable(
                                true);

                aiAskButton.setDisable(
                                true);

                aiQuestionField.setPromptText(
                                "Select a farming type above...");
        }

        // =========================================================
        // FARMING TYPE BUTTONS
        // =========================================================

        private void createFarmingTypeButtons(
                        VBox center,
                        Label question,
                        Button planButton) {

                GridPane farmingGrid = new GridPane();

                farmingGrid.setHgap(
                                12);

                farmingGrid.setVgap(
                                12);

                farmingGrid.setAlignment(
                                Pos.CENTER);

                String[][] farmingTypes = {

                                {
                                                "🐔 Poultry",
                                                "Poultry"
                                },

                                {
                                                "🐐 Goat",
                                                "Goat"
                                },

                                {
                                                "🍄 Mushroom",
                                                "Mushroom"
                                },

                                {
                                                "🐄 Dairy / Cow",
                                                "Dairy / Cow"
                                },

                                {
                                                "🦪 Pearl",
                                                "Pearl"
                                },

                                {
                                                "🐟 Fish",
                                                "Fish"
                                },

                                {
                                                "🌿 Moringa",
                                                "Moringa"
                                },

                                {
                                                "🌾 Crop",
                                                "Crop"
                                }
                };

                int column = 0;

                int row = 0;

                for (String[] farmingType : farmingTypes) {

                        Button button = createFarmingTypeButton(
                                        farmingType[0]);

                        button.setOnAction(
                                        event -> {

                                                selectFarmingType(
                                                                farmingType[1],
                                                                question,
                                                                farmingGrid,
                                                                planButton);
                                        });

                        farmingGrid.add(
                                        button,
                                        column,
                                        row);

                        column++;

                        if (column == 2) {

                                column = 0;

                                row++;
                        }
                }

                center.getChildren().add(
                                2,
                                farmingGrid);
        }

        // =========================================================
        // FARMING TYPE BUTTON
        // =========================================================

        private Button createFarmingTypeButton(
                        String text) {

                Button button = new Button(text);

                button.setPrefWidth(
                                230);

                button.setPrefHeight(
                                48);

                button.setTextFill(
                                DARK_GREEN);

                button.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                button.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.WHITE,
                                                                new CornerRadii(12),
                                                                Insets.EMPTY)));

                button.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER_COLOR,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(12),
                                                                new BorderWidths(1))));

                button.setCursor(
                                Cursor.HAND);

                return button;
        }

        // =========================================================
        // SELECT FARMING TYPE
        // =========================================================

        private void selectFarmingType(
                        String farmingType,
                        Label question,
                        GridPane farmingGrid,
                        Button planButton) {

                selectedFarmingType = farmingType;

                farmingQuestionIndex = 0;

                farmingPlanAnswers.clear();

                currentFarmingQuestionKeys.clear();

                currentFarmingQuestions.clear();

                // =====================================================
                // SAVE FARMING TYPE
                // =====================================================

                farmingPlanAnswers.put(
                                "Farming Type",
                                farmingType);

                // =====================================================
                // CREATE QUESTIONS
                // =====================================================

                buildFarmingQuestions(
                                farmingType);

                // =====================================================
                // SHOW FIRST QUESTION
                // =====================================================

                question.setText(
                                "Selected: "
                                                + farmingType
                                                + "\n\n"
                                                + currentFarmingQuestions.get(0));

                aiAssistantText.setText(
                                "Excellent choice! 🌱\n\n"
                                                + "I will now collect the information "
                                                + "required to prepare your "
                                                + farmingType
                                                + " farming plan.");

                aiQuestionField.clear();

                aiQuestionField.setDisable(
                                false);

                aiAskButton.setDisable(
                                false);

                aiAskButton.setText(
                                "Next  ➤");

                aiQuestionField.setPromptText(
                                "Enter your answer...");

                // =====================================================
                // DISABLE FARMING TYPE BUTTONS
                // =====================================================

                farmingGrid.setDisable(
                                true);

                planButton.setVisible(
                                false);

                planButton.setManaged(
                                false);
        }

        // =========================================================
        // BUILD FARMING QUESTIONS
        // =========================================================

        private void buildFarmingQuestions(
                        String farmingType) {

                currentFarmingQuestionKeys.clear();

                currentFarmingQuestions.clear();

                // =====================================================
                // COMMON QUESTIONS
                // =====================================================

                addFarmingQuestion(
                                "Location",
                                "Which district and state is your farm located in?");

                addFarmingQuestion(
                                "Area",
                                "How much land or farming area do you have?");

                addFarmingQuestion(
                                "Capacity",
                                "What capacity are you planning for this farm?");

                addFarmingQuestion(
                                "Budget",
                                "What is your approximate budget in Indian Rupees?");

                addFarmingQuestion(
                                "Water",
                                "Do you have a reliable water source? Please describe it.");

                addFarmingQuestion(
                                "Electricity",
                                "Is electricity available at your farm?");

                addFarmingQuestion(
                                "Infrastructure",
                                "Do you already have any shed, pond, room, equipment or other infrastructure?");

                addFarmingQuestion(
                                "Labour",
                                "How many people can work on the farm?");

                addFarmingQuestion(
                                "Experience",
                                "What is your farming experience level? Beginner, some experience, or experienced?");

                addFarmingQuestion(
                                "Market",
                                "How do you plan to sell your farm products?");

                // =====================================================
                // FARMING-SPECIFIC QUESTIONS
                // =====================================================

                switch (farmingType) {

                        case "Poultry":

                                addFarmingQuestion(
                                                "Poultry Purpose",
                                                "Is your poultry farm for meat, eggs, or both?");

                                addFarmingQuestion(
                                                "Bird Number",
                                                "How many birds are you planning to rear?");

                                addFarmingQuestion(
                                                "Poultry Breed",
                                                "Do you have a preferred poultry breed or type?");

                                addFarmingQuestion(
                                                "Poultry Shed",
                                                "Do you already have a poultry shed? If yes, describe its approximate size.");

                                addFarmingQuestion(
                                                "Feed",
                                                "Do you have access to poultry feed or local feed ingredients?");

                                break;

                        case "Goat":

                                addFarmingQuestion(
                                                "Goat Purpose",
                                                "Is your goat farm mainly for meat, breeding, milk, or a combination?");

                                addFarmingQuestion(
                                                "Goat Number",
                                                "How many goats are you planning to keep?");

                                addFarmingQuestion(
                                                "Goat Breed",
                                                "Do you have a preferred goat breed?");

                                addFarmingQuestion(
                                                "Grazing",
                                                "Do you have grazing land or access to fodder?");

                                addFarmingQuestion(
                                                "Goat Shed",
                                                "Do you already have a goat shed?");

                                break;

                        case "Mushroom":

                                addFarmingQuestion(
                                                "Mushroom Type",
                                                "Which mushroom do you want to cultivate?");

                                addFarmingQuestion(
                                                "Growing Area",
                                                "How much growing-room area is available?");

                                addFarmingQuestion(
                                                "Growing Room",
                                                "Do you already have a suitable mushroom growing room?");

                                addFarmingQuestion(
                                                "Substrate",
                                                "What substrate or agricultural waste materials are available to you?");

                                addFarmingQuestion(
                                                "Temperature",
                                                "Do you have facilities for temperature and humidity management?");

                                break;

                        case "Dairy / Cow":

                                addFarmingQuestion(
                                                "Cattle Number",
                                                "How many cattle are you planning to keep?");

                                addFarmingQuestion(
                                                "Dairy Purpose",
                                                "Is your main goal milk production, breeding, or both?");

                                addFarmingQuestion(
                                                "Cattle Breed",
                                                "Do you have a preferred cattle breed?");

                                addFarmingQuestion(
                                                "Fodder",
                                                "Do you have access to green fodder or other feed resources?");

                                addFarmingQuestion(
                                                "Cattle Shed",
                                                "Do you already have a cattle shed?");

                                break;

                        case "Pearl":

                                addFarmingQuestion(
                                                "Water Area",
                                                "How much pond or suitable water area is available?");

                                addFarmingQuestion(
                                                "Pearl Method",
                                                "Do you have a preferred pearl culture method?");

                                addFarmingQuestion(
                                                "Water Quality",
                                                "Do you know the current water quality or water source?");

                                addFarmingQuestion(
                                                "Mussel Availability",
                                                "Do you have access to suitable freshwater mussels?");

                                break;

                        case "Fish":

                                addFarmingQuestion(
                                                "Pond Area",
                                                "What is the available pond area?");

                                addFarmingQuestion(
                                                "Fish Species",
                                                "Which fish species do you want to culture?");

                                addFarmingQuestion(
                                                "Pond Condition",
                                                "Is the pond already constructed and suitable for fish culture?");

                                addFarmingQuestion(
                                                "Water Source",
                                                "What is the main source of water for the pond?");

                                break;

                        case "Moringa":

                                addFarmingQuestion(
                                                "Moringa Purpose",
                                                "Are you growing moringa mainly for leaves, pods, seed, or another purpose?");

                                addFarmingQuestion(
                                                "Moringa Variety",
                                                "Do you have a preferred moringa variety?");

                                addFarmingQuestion(
                                                "Planting Time",
                                                "When are you planning to start planting?");

                                addFarmingQuestion(
                                                "Irrigation",
                                                "What irrigation facility is available?");

                                break;

                        case "Crop":

                                addFarmingQuestion(
                                                "Crop Type",
                                                "Which crop or group of crops are you considering?");

                                addFarmingQuestion(
                                                "Soil",
                                                "Do you know your soil type or recent soil-test results?");

                                addFarmingQuestion(
                                                "Season",
                                                "Which season are you planning to cultivate?");

                                addFarmingQuestion(
                                                "Irrigation",
                                                "What irrigation facility is available?");

                                break;
                }
        }

        // =========================================================
        // ADD FARMING QUESTION
        // =========================================================

        private void addFarmingQuestion(
                        String key,
                        String question) {

                currentFarmingQuestionKeys.add(
                                key);

                currentFarmingQuestions.add(
                                question);
        }

        // =========================================================
        // PROCESS FARMING PLAN ANSWER
        // =========================================================

        private void processFarmingPlanAnswer() {

                String answer = aiQuestionField
                                .getText()
                                .trim();

                if (answer.isEmpty()) {

                        return;
                }

                // =====================================================
                // SAFETY CHECK
                // =====================================================

                if (farmingQuestionIndex < 0 ||
                                farmingQuestionIndex >= currentFarmingQuestions.size()) {

                        return;
                }

                // =====================================================
                // GET CURRENT QUESTION KEY
                // =====================================================

                String key = currentFarmingQuestionKeys.get(
                                farmingQuestionIndex);

                // =====================================================
                // SAVE ANSWER
                // =====================================================

                farmingPlanAnswers.put(
                                key,
                                answer);

                farmingQuestionIndex++;

                aiQuestionField.clear();

                // =====================================================
                // MORE QUESTIONS
                // =====================================================

                if (farmingQuestionIndex < currentFarmingQuestions.size()) {

                        String nextQuestion = currentFarmingQuestions.get(
                                        farmingQuestionIndex);

                        aiAssistantText.setText(
                                        "Thank you! 🌱\n\n"
                                                        + "Your answer has been recorded.");

                        aiQuestionField.setPromptText(
                                        "Enter your answer...");

                        aiAskButton.setText(
                                        "Next  ➤");

                        updateFarmingQuestionDisplay(
                                        nextQuestion);

                        return;
                }

                // =====================================================
                // ALL QUESTIONS COMPLETE
                // =====================================================

                finishFarmingPlanQuestions();
        }

        // =========================================================
        // UPDATE FARMING QUESTION DISPLAY
        // =========================================================

        private void updateFarmingQuestionDisplay(
                        String question) {

                aiAssistantText.setText(
                                "🌱 " + question);

                aiQuestionField.setPromptText(
                                "Type your answer here...");
        }

        // =========================================================
        // FINISH FARMING QUESTIONS
        // =========================================================

        private void finishFarmingPlanQuestions() {

                StringBuilder summary = new StringBuilder();

                summary.append(
                                "Great! 🌱\n\n");

                summary.append(
                                "I have collected the information needed "
                                                + "for your "
                                                + selectedFarmingType
                                                + " farming plan.\n\n");

                summary.append(
                                "Your information:\n\n");

                for (Map.Entry<String, String> entry : farmingPlanAnswers.entrySet()) {

                        summary.append(
                                        "• "
                                                        + entry.getKey()
                                                        + ": "
                                                        + entry.getValue()
                                                        + "\n");
                }

                summary.append(
                                "\nEverything looks ready.");

                summary.append(
                                "\n\nClick \"Generate Plan\" to create "
                                                + "your personalized farming plan.");

                aiAssistantText.setText(
                                summary.toString());

                aiQuestionField.clear();

                aiQuestionField.setDisable(
                                true);

                aiAskButton.setText(
                                "Generate Plan");

                aiAskButton.setDisable(
                                false);

                aiAskButton.setOnAction(
                                event -> {

                                        generateFinalFarmingPlan();
                                });
        }

        // =========================================================
        // GENERATE FINAL FARMING PLAN
        // =========================================================

        private void generateFinalFarmingPlan() {

                if (selectedFarmingType == null ||
                                selectedFarmingType.isBlank()) {

                        return;
                }

                if (farmingPlanAnswers.isEmpty()) {

                        return;
                }

                // =====================================================
                // UI
                // =====================================================

                aiAssistantText.setText(
                                "🌱 AgroBiz AI is preparing your "
                                                + selectedFarmingType
                                                + " farming plan...\n\n"
                                                + "Please wait.");

                aiAskButton.setDisable(
                                true);

                aiQuestionField.setDisable(
                                true);

                // =====================================================
                // BACKGROUND TASK
                // =====================================================

                Task<String> task = new Task<String>() {

                        @Override
                        protected String call()
                                        throws Exception {

                                return groqService.generateFarmingPlan(
                                                selectedFarmingType,
                                                farmingPlanAnswers);
                        }
                };

                // =====================================================
                // SUCCESS
                // =====================================================

                task.setOnSucceeded(
                                event -> {

                                        String result = task.getValue();

                                        // =============================================
                                        // PUT COMPLETE RESPONSE INTO PLAN TEXT
                                        // =============================================

                                        planText.setText(
                                                        result);

                                        // =============================================
                                        // PUT PLAN TEXT INSIDE SCROLL CONTENT
                                        // =============================================

                                        planContent.getChildren().clear();

                                        planContent.getChildren().add(
                                                        planText);

                                        // =============================================
                                        // SHOW PLAN SCROLLER
                                        // =============================================

                                        planScroll.setVisible(
                                                        true);

                                        planScroll.setManaged(
                                                        true);

                                        aiCenter.setVisible(
                                                        false);

                                        aiCenter.setManaged(
                                                        false);

                                        // =============================================
                                        // MAKE PLAN SCROLLER FILL CARD
                                        // =============================================

                                        VBox.setVgrow(
                                                        planScroll,
                                                        Priority.ALWAYS);

                                        // =============================================
                                        // REPLACE CARD CONTENT
                                        // =============================================

                                        aiCard.getChildren().clear();

                                        aiCard.getChildren().add(
                                                        planScroll);

                                        aiCard.getChildren().add(
                                                        newPlanButton);

                                        // =============================================
                                        // SCROLL TO TOP
                                        // =============================================

                                        planScroll.setVvalue(
                                                        0);

                                        // =============================================
                                        // RESET BUTTON STATE
                                        // =============================================

                                        aiAskButton.setText(
                                                        "Ask AI  ➤");

                                        aiAskButton.setDisable(
                                                        false);

                                        aiQuestionField.setDisable(
                                                        false);

                                        aiQuestionField.clear();

                                        aiQuestionField.setPromptText(
                                                        "Ask another farming question...");

                                        // =============================================
                                        // RETURN TO NORMAL AI MODE
                                        // =============================================

                                        selectedFarmingType = null;

                                        farmingQuestionIndex = 0;

                                        farmingPlanAnswers.clear();

                                        currentFarmingQuestionKeys.clear();

                                        currentFarmingQuestions.clear();

                                        aiAskButton.setOnAction(
                                                        e -> {

                                                                String newQuestion = aiQuestionField
                                                                                .getText()
                                                                                .trim();

                                                                if (!newQuestion.isEmpty()) {

                                                                        askNormalAIQuestion(
                                                                                        newQuestion);
                                                                }
                                                        });
                                });

                // =====================================================
                // FAILURE
                // =====================================================

                task.setOnFailed(
                                event -> {

                                        Throwable error = task.getException();

                                        String errorMessage = "Sorry Farmer, I could not "
                                                        + "generate your farming plan.";

                                        if (error != null &&
                                                        error.getMessage() != null) {

                                                errorMessage += "\n\nError: "
                                                                + error.getMessage();
                                        }

                                        aiAssistantText.setText(
                                                        errorMessage);

                                        aiAskButton.setText(
                                                        "Generate Plan");

                                        aiAskButton.setDisable(
                                                        false);

                                        aiQuestionField.setDisable(
                                                        true);
                                });

                // =====================================================
                // START THREAD
                // =====================================================

                Thread thread = new Thread(task);

                thread.setDaemon(
                                true);

                thread.start();
        }

        // MY LEARNING PAGE

        // =========================================================
        // WISHLIST PAGE
        // =========================================================

        private VBox createWishlistPage() {
                return createWishlistPage("All");
        }

        // Rebuilds the right-side wishlist area for the selected tab.
        private VBox createWishlistPage(String selectedTab) {

                VBox page = new VBox();

                page.setBackground(new Background(
                                new BackgroundFill(CREAM, CornerRadii.EMPTY, Insets.EMPTY)));

                // Reuse the normal dashboard header on the right side.
                HBox topBar = createTopBar(
                                "Wishlist",
                                "Your saved farming products and resources.");

                VBox content = new VBox(22);
                content.setPadding(new Insets(30, 35, 35, 35));
                content.setBackground(new Background(
                                new BackgroundFill(
                                                BACKGROUND,
                                                CornerRadii.EMPTY,
                                                Insets.EMPTY)));

                HBox tabs = new HBox(35);
                tabs.setAlignment(Pos.CENTER_LEFT);
                tabs.getChildren().addAll(
                                createWishlistTab("All", selectedTab.equals("All")),
                                createWishlistTab("Products", selectedTab.equals("Products")),
                                createWishlistTab("Courses", selectedTab.equals("Courses")),
                                createWishlistTab("Resources", selectedTab.equals("Resources")));

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
                scroll.setStyle("-fx-background-color: #080C0D;  -fx-control-inner-background: #080C0D;");
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setBackground(Background.EMPTY);

                scroll.applyCss();

                javafx.scene.Node viewport = scroll.lookup(".viewport");

                if (viewport != null) {
                        viewport.setStyle("-fx-background-color: #080C0D;");
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
                tab.setTextFill(selected ? LIGHT_GREEN : DARK_TEXT);
                tab.setPadding(new Insets(0, 0, 10, 0));
                tab.setBackground(Background.EMPTY);

                if (selected) {
                        tab.setBorder(new Border(new BorderStroke(
                                        DARK_GREEN,
                                        BorderStrokeStyle.SOLID,
                                        CornerRadii.EMPTY,
                                        new BorderWidths(0, 0, 2, 0))));
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
                heart.setTextFill(ERROR);
                heart.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                heart.setPadding(new Insets(4, 9, 4, 9));
                heart.setBackground(new Background(new BackgroundFill(
                                SIDEBAR, new CornerRadii(20), Insets.EMPTY)));

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
                                "Enhance soil health and boost\ncrop yield naturally with our...");

                Button action = createWishlistActionButton("View Details");
                action.setOnAction(event -> root.setCenter(createWishlistDetailsPage(
                                "Product Details",
                                "Premium Organic Fertilizer",
                                "₹1,250",
                                "/fertilizer.png",
                                "A natural agricultural input that improves soil health and supports healthy crop growth.")));

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
                heart.setTextFill(ERROR);
                heart.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                heart.setPadding(new Insets(4, 9, 4, 9));
                heart.setBackground(new Background(new BackgroundFill(
                                SIDEBAR, new CornerRadii(20), Insets.EMPTY)));

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
                                "Learn water-saving strategies and\nadvanced drip irrigation systems...");

                Button action = createWishlistActionButton("View Course");
                action.setOnAction(event -> root.setCenter(createWishlistDetailsPage(
                                "Course Details",
                                "Modern Irrigation Techniques",
                                "Free",
                                "/irrigation.png",
                                "Learn practical water-saving methods, drip irrigation basics, and ways to manage water efficiently.")));

                card.getChildren().addAll(courseImage, details, title, description, action);
                return card;
        }

        // Shared styling for both wishlist cards.
        private VBox createWishlistCard() {

                VBox card = new VBox(13);
                card.setPrefWidth(270);
                card.setPadding(new Insets(22));
                card.setBackground(new Background(new BackgroundFill(
                                CARD_BACKGROUND, new CornerRadii(14), Insets.EMPTY)));
                card.setBorder(new Border(new BorderStroke(
                                BORDER_COLOR, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(1))));

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
                                SELECTED, new CornerRadii(4), Insets.EMPTY)));

                return badge;
        }

        private Label createWishlistTitle(String text) {

                Label title = new Label(text);
                title.setTextFill(TEXT_PRIMARY);
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
                                DARK_GREEN, new CornerRadii(7), Insets.EMPTY)));

                return button;
        }

        // Simple message used until resource items are added later.
        private VBox createEmptyWishlistMessage() {

                VBox message = createWishlistCard();
                message.setPrefWidth(420);

                Label title = createWishlistTitle("No saved resources yet");
                Label text = createWishlistDescription(
                                "Resources you save in the future will appear here.");

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
                                CREAM, CornerRadii.EMPTY, Insets.EMPTY)));

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
                                CARD_BACKGROUND, new CornerRadii(14), Insets.EMPTY)));
                detailCard.setBorder(new Border(new BorderStroke(
                                BORDER_COLOR, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(1))));

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
                                "-fx-background-color: #080C0D;" +
                                                "-fx-background: #080C0D;" +
                                                "-fx-control-inner-background: #080C0D;");
                ;

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
                                "AI in Agriculture");
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
                                "-fx-background-color: #101718;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-border-color: #242B2C;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-padding: 0 12;" +
                                                "-fx-text-fill: #EEEEEE;");

                Label initialLabel = new Label("Initial Investment");
                initialLabel.setTextFill(GREY);
                initialLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));

                TextField initialInvestmentField = new TextField();
                initialInvestmentField.setPromptText("Example: 10000");
                initialInvestmentField.setPrefHeight(45);
                initialInvestmentField.setFont(Font.font("Arial", 14));
                initialInvestmentField.setStyle(
                                "-fx-background-color: #101718;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-border-color: #242B2C;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-padding: 0 12;" +
                                                "-fx-text-fill: #EEEEEE;");

                Button calculate = createWishlistActionButton("Calculate Investment Plan");
                calculate.setPrefHeight(48);
                calculate.setMaxWidth(Double.MAX_VALUE);

                Label errorLabel = new Label();
                errorLabel.setTextFill(ERROR);
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
                                errorLabel);

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
                                "-fx-accent: #68D34A;" +
                                                "-fx-control-inner-background: #dfe7e0;");

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
                                progressLabel);

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
                                "Calculate your plan to see the monthly investment.");
                monthlyInstruction.setTextFill(GREY);
                monthlyInstruction.setFont(Font.font("Arial", 13));

                monthlyList.getChildren().add(monthlyInstruction);

                monthlyCard.getChildren().addAll(
                                monthlyTitle,
                                monthlyList);

                // =====================================================
                // AI SUGGESTION
                // =====================================================

                VBox suggestionCard = new VBox(8);
                suggestionCard.setMaxWidth(700);
                suggestionCard.setPadding(new Insets(22));

                suggestionCard.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                CARD_HOVER,
                                                                new CornerRadii(12),
                                                                Insets.EMPTY)));

                suggestionCard.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(12),
                                                                new BorderWidths(1))));

                Label suggestionTitle = new Label("💡 Investment Suggestion");
                suggestionTitle.setTextFill(GREEN);
                suggestionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                Label suggestionText = new Label(
                                "Enter your investment details to receive a simple monthly investment suggestion.");
                suggestionText.setTextFill(TEXT_SECONDARY);
                suggestionText.setFont(Font.font("Arial", 13));
                suggestionText.setWrapText(true);

                suggestionCard.getChildren().addAll(
                                suggestionTitle,
                                suggestionText);

                // =====================================================
                // CALCULATE ACTION
                // =====================================================

                calculate.setOnAction(event -> {

                        try {

                                String selected = courseBox.getValue();
                                int months = durationBox.getValue();

                                double totalInvestment = Double.parseDouble(
                                                totalInvestmentField.getText().trim());

                                double initialInvestment = Double.parseDouble(
                                                initialInvestmentField.getText().trim());

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
                                                        "Initial investment cannot be greater than total investment.");
                                        return;
                                }

                                double remainingInvestment = totalInvestment - initialInvestment;

                                double monthlyInvestment = remainingInvestment / months;

                                selectedCourse.setText("Course: " + selected);

                                totalValue.setText(
                                                "Total Investment: ₹" +
                                                                String.format("%,.0f", totalInvestment));

                                initialValue.setText(
                                                "Initial Investment: ₹" +
                                                                String.format("%,.0f", initialInvestment));

                                remainingValue.setText(
                                                "Remaining Investment: ₹" +
                                                                String.format("%,.0f", remainingInvestment));

                                monthlyValue.setText(
                                                "Monthly Investment: ₹" +
                                                                String.format("%,.2f", monthlyInvestment));

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
                                                        new Insets(12, 15, 12, 15));

                                        monthRow.setBackground(
                                                        new Background(
                                                                        new BackgroundFill(
                                                                                        CARD_HOVER,
                                                                                        new CornerRadii(8),
                                                                                        Insets.EMPTY)));

                                        Label monthLabel = new Label(
                                                        "Month " + i);

                                        monthLabel.setTextFill(DARK_TEXT);
                                        monthLabel.setFont(
                                                        Font.font(
                                                                        "Arial",
                                                                        FontWeight.BOLD,
                                                                        14));

                                        Region spacer = new Region();
                                        HBox.setHgrow(
                                                        spacer,
                                                        Priority.ALWAYS);

                                        Label amountLabel = new Label(
                                                        "₹" +
                                                                        String.format(
                                                                                        "%,.2f",
                                                                                        currentAmount));

                                        amountLabel.setTextFill(GREEN);
                                        amountLabel.setFont(
                                                        Font.font(
                                                                        "Arial",
                                                                        FontWeight.BOLD,
                                                                        14));

                                        monthRow.getChildren().addAll(
                                                        monthLabel,
                                                        spacer,
                                                        amountLabel);

                                        monthlyList.getChildren().add(
                                                        monthRow);
                                }

                                progressBar.setProgress(
                                                initialInvestment / totalInvestment);

                                int progress = (int) ((initialInvestment / totalInvestment) * 100);

                                progressLabel.setText(
                                                "Investment Progress: " +
                                                                progress +
                                                                "%");

                                suggestionText.setText(
                                                "For " + selected +
                                                                ", your remaining investment is ₹" +
                                                                String.format("%,.0f", remainingInvestment) +
                                                                ". You need approximately ₹" +
                                                                String.format("%,.2f", monthlyInvestment) +
                                                                " per month for " +
                                                                months +
                                                                " months.");

                                errorLabel.setText("");

                        } catch (NumberFormatException exception) {

                                errorLabel.setText(
                                                "Please enter valid investment amounts.");
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
                                suggestionCard);

                wrapper.setAlignment(Pos.TOP_CENTER);

                ScrollPane scroll = new ScrollPane(wrapper);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setStyle(
                                "-fx-background-color: #080C0D;" +
                                                "-fx-background: #080C0D;" +
                                                "-fx-control-inner-background: #080C0D;");

                VBox.setVgrow(
                                scroll,
                                Priority.ALWAYS);

                page.getChildren().addAll(
                                topBar,
                                scroll);

                return page;
        }
        // =========================================================
        // SCHEMES PAGE
        // =========================================================

        private VBox createSavedSchemesSection() {

                VBox section = new VBox(15);

                section.setPadding(
                                new Insets(20));

                section.setStyle(
                                "-fx-background-color:#0b1714;" +
                                                "-fx-background-radius:12;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:12;");

                Label title = new Label("♥ Saved Schemes");

                title.setStyle(
                                "-fx-text-fill:white;" +
                                                "-fx-font-size:20px;" +
                                                "-fx-font-weight:bold;");

                List<Scheme> schemes = SavedSchemesManager.getSchemes();

                section.getChildren().add(title);

                if (schemes.isEmpty()) {

                        Label empty = new Label(
                                        "You haven't liked any schemes yet.");

                        empty.setStyle(
                                        "-fx-text-fill:#AAAAAA;" +
                                                        "-fx-font-size:13px;");

                        section.getChildren().add(empty);

                        return section;
                }

                for (Scheme scheme : schemes) {

                        section.getChildren().add(
                                        createSavedSchemeCard(
                                                        scheme,
                                                        section));
                }

                return section;
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
                                                                Insets.EMPTY)));

                HBox topBar = createTopBar(
                                titleText,
                                subtitleText);

                VBox content = new VBox();

                content.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                BACKGROUND,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                content.setPadding(new Insets(0, 35, 35, 35));

                content.setSpacing(20);

                ScrollPane scroll = new ScrollPane(content);

                scroll.setFitToWidth(true);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-background:#080C0D;" +
                                                "-fx-control-inner-background:#080C0D;");

                VBox.setVgrow(
                                scroll,
                                Priority.ALWAYS);

                page.getChildren().addAll(
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

                VBox card = createWhiteCard();

                HBox.setHgrow(
                                card,
                                Priority.ALWAYS);

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                30));

                Label titleLabel = new Label(title);

                titleLabel.setTextFill(DARK_TEXT);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));

                Label descriptionLabel = new Label(description);

                descriptionLabel.setTextFill(GREY);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                card.getChildren().addAll(
                                iconLabel,
                                titleLabel,
                                descriptionLabel);

                return card;
        }

        // =========================================================
        // GRID COLUMN WIDTH HELPER
        // =========================================================

        private void columnConstraintsHelper(
                        GridPane grid) {

                ColumnConstraints column1 = new ColumnConstraints();

                ColumnConstraints column2 = new ColumnConstraints();

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
        // WHITE CARD
        // =========================================================

        private VBox createWhiteCard() {

                VBox card = new VBox();

                card.setSpacing(12);

                card.setPadding(
                                new Insets(22));

                card.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                SIDEBAR,
                                                                new CornerRadii(15),
                                                                Insets.EMPTY)));

                HBox.setHgrow(
                                card,
                                Priority.ALWAYS);

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

        private String safe(
                        String value) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return "Not provided";
                }

                return value;
        }

        // =========================================================
        // CARD
        // =========================================================

        private VBox createCard() {

                VBox card = new VBox(
                                15);

                card.setPadding(
                                new Insets(
                                                22));

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#242B2C;" +
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

                VBox box = new VBox(
                                6);

                Label label = new Label(
                                title);

                label.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
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

                TextField field = new TextField();

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
                                "-fx-background-color:#101718;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#777777;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8 10;");
        }

        // =========================================================
        // COMBO BOX STYLE
        // =========================================================

        private void styleComboBox(
                        ComboBox<String> box) {

                box.setStyle(
                                "-fx-background-color:#101718;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");
        }

        // =========================================================
        // ALERT
        // =========================================================

        private void showAlert(
                        Alert.AlertType type,
                        String message) {

                Alert alert = new Alert(
                                type);

                alert.setTitle(
                                "AgroBiz");

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                alert.showAndWait();
        }

        private VBox createSavedSchemeCard(
                        Scheme scheme,
                        VBox section) {

                VBox card = new VBox(8);

                card.setPadding(
                                new Insets(15));

                card.setStyle(
                                "-fx-background-color:#172021;" +
                                                "-fx-background-radius:10;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:10;");

                Label name = new Label(
                                scheme.getSchemeName());

                name.setWrapText(true);

                name.setStyle(
                                "-fx-text-fill:white;" +
                                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;");

                Label category = new Label(
                                "Category: " +
                                                scheme.getCategory());

                category.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:11px;");

                Label information = new Label(
                                scheme.getInformation());

                information.setWrapText(true);

                information.setStyle(
                                "-fx-text-fill: #AAAAAA;" +
                                                "-fx-font-size:12px;");

                Button dislikeButton = new Button(
                                "♥  Dislike");

                dislikeButton.setPrefHeight(32);

                dislikeButton.setCursor(
                                javafx.scene.Cursor.HAND);

                dislikeButton.setStyle(
                                "-fx-background-color: #101d18;" +
                                                "-fx-text-fill:#68D34A;" +
                                                "-fx-border-color:#68D34A;" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                dislikeButton.setOnAction(e -> {

                        SavedSchemesManager.removeScheme(
                                        scheme);

                        section.getChildren().clear();

                        VBox refreshed = createSavedSchemesSection();

                        section.getChildren().addAll(
                                        refreshed.getChildren());
                });

                card.getChildren().addAll(
                                name,
                                category,
                                information,
                                dislikeButton);

                return card;
        }
}
