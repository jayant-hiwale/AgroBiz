package com.pravartak.view.login;

import com.pravartak.controller.authentication_contr.AuthController;
import com.pravartak.dao.UserDAO;
import com.pravartak.model.UserModel;
// import com.pravartak.view.buyer.BuyerAIAdvisorPage;
import com.pravartak.view.buyer.BuyerHomepage;

import com.pravartak.view.buyer.BuyerProfilePage;
// import com.pravartak.view.farmer.AIAdvisorPage;
import com.pravartak.view.farmer.CommunityPage;

import java.net.URL;
import java.util.Random;

import com.pravartak.view.farmer.HomePageFarmer;
import com.pravartak.controller.buyercontroller.Contactcontroller;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.farmer.MarketPlace;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPage extends Application {


        private final AuthController authController = new AuthController();

        private UserDAO userDAO;

    //HomePageFarmer homepagefarmer = new HomePageFarmer();
    //private HomePageFarmer homepagefarmer;
    public static Stage mainStage;
    private Scene loginPageScene;
    private String selectedRole = "";

    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;

        userDAO = new UserDAO();


        HBox mainLayout = new HBox();
        mainLayout.setPrefSize(1368, 768);

        // =====================================================
        // LEFT SECTION
        // =====================================================

        StackPane leftSection = new StackPane();

        URL imageURL = getClass().getResource("/farm-image.png");

        if (imageURL == null) {
            throw new RuntimeException("ERROR: farm-image.png was not found.\n\n"
                    + "Put the image here:\nsrc/main/resources/farm-image.png");
        }

        Image farmImage = new Image(imageURL.toExternalForm());
        ImageView farmImageView = new ImageView(farmImage);
        farmImageView.setPreserveRatio(false);
        farmImageView.fitWidthProperty().bind(leftSection.widthProperty());
        farmImageView.fitHeightProperty().bind(leftSection.heightProperty());

        Rectangle darkOverlay = new Rectangle();
        darkOverlay.setFill(Color.rgb(0, 0, 0, 0.30));
        darkOverlay.widthProperty().bind(leftSection.widthProperty());
        darkOverlay.heightProperty().bind(leftSection.heightProperty());

        Label title = new Label("Cultivating the Future\nof Agriculture");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setWrapText(true);

        Label description = new Label(
                "Empowering farming entrepreneurs with AI-driven insights\n"
                + "and a vibrant marketplace. Join the growing community\ntoday.");
        description.setTextFill(Color.WHITE);
        description.setFont(Font.font("Arial", 17));
        description.setWrapText(true);

        VBox textContainer = new VBox(14, title, description);
        textContainer.setAlignment(Pos.BOTTOM_LEFT);
        textContainer.setPadding(new Insets(0, 45, 55, 45));

        leftSection.getChildren().addAll(farmImageView, darkOverlay, textContainer);
        StackPane.setAlignment(textContainer, Pos.BOTTOM_LEFT);

        // =====================================================
        // RIGHT SECTION WITH ANIMATED BACKGROUND
        // =====================================================

        StackPane rightSection = new StackPane();
        rightSection.setPadding(new Insets(30));

        rightSection.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, "
                + "#050908 0%, #08130d 50%, #0b1b12 100%);");

        createRightBackgroundAnimation(rightSection);

        // =====================================================
        // LOGIN CONTAINER
        // =====================================================

        VBox loginContainer = new VBox();
        loginContainer.setSpacing(0);
        loginContainer.setPrefWidth(480);
        loginContainer.setMaxWidth(480);

        // =====================================================
        // LOGO
        // =====================================================

        Label logoIcon = new Label("🚜");
        logoIcon.setAlignment(Pos.CENTER);
        logoIcon.setPrefSize(58, 58);
        logoIcon.setFont(Font.font("Arial", 26));
        logoIcon.setTextFill(Color.WHITE);
        logoIcon.setBackground(
                new Background(new BackgroundFill(
                        Color.web("#0f5215"), new CornerRadii(12), Insets.EMPTY)));

        Label logoText = new Label("Agro Biz");
        logoText.setTextFill(Color.web("#68d34a"));
        logoText.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        HBox logoContainer = new HBox(14, logoIcon, logoText);
        logoContainer.setAlignment(Pos.CENTER_LEFT);

        ScaleTransition logoAnimation = new ScaleTransition(Duration.seconds(2.5), logoIcon);
        logoAnimation.setFromX(1.0);
        logoAnimation.setFromY(1.0);
        logoAnimation.setToX(1.08);
        logoAnimation.setToY(1.08);
        logoAnimation.setAutoReverse(true);
        logoAnimation.setCycleCount(Animation.INDEFINITE);
        logoAnimation.play();

        // =====================================================
        // WELCOME
        // =====================================================

        Label welcomeTitle = new Label("Welcome back");
        welcomeTitle.setTextFill(Color.WHITE);
        welcomeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        Label welcomeDescription = new Label("Please select your role to sign in.");
        welcomeDescription.setTextFill(Color.web("#aab8ae"));
        welcomeDescription.setFont(Font.font("Arial", 16));

        

        // =====================================================
        // CONTACT
        // =====================================================

        Label contactLabel = new Label("Email");
        contactLabel.setTextFill(Color.WHITE);
        contactLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        TextField contactField = new TextField();
        contactField.setPromptText("Enter your registered email");
        contactField.setPrefHeight(52);
        contactField.setFont(Font.font("Arial", 15));
        contactField.setPadding(new Insets(0, 15, 0, 15));
        contactField.setBackground(
                new Background(new BackgroundFill(Color.WHITE, new CornerRadii(8), Insets.EMPTY)));
        contactField.setBorder(
                new Border(new BorderStroke(Color.rgb(70, 90, 75), BorderStrokeStyle.SOLID,
                        new CornerRadii(8), new BorderWidths(1.5))));
        
        

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Color.WHITE);
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Button forgotPasswordButton = new Button("Forgot password?");
        forgotPasswordButton.setTextFill(Color.web("#68d34a"));
        forgotPasswordButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        forgotPasswordButton.setBackground(Background.EMPTY);
        forgotPasswordButton.setBorder(Border.EMPTY);
        forgotPasswordButton.setCursor(Cursor.HAND);

        Region passwordSpace = new Region();
        HBox.setHgrow(passwordSpace, Priority.ALWAYS);

        HBox passwordHeader = new HBox(passwordLabel, passwordSpace, forgotPasswordButton);
        passwordHeader.setAlignment(Pos.CENTER_LEFT);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefHeight(52);
        passwordField.setFont(Font.font("Arial", 15));
        passwordField.setPadding(new Insets(0, 15, 0, 15));
        passwordField.setBackground(
                new Background(new BackgroundFill(Color.WHITE, new CornerRadii(8), Insets.EMPTY)));
        passwordField.setBorder(
                new Border(new BorderStroke(Color.rgb(70, 90, 75), BorderStrokeStyle.SOLID,
                        new CornerRadii(8), new BorderWidths(1.5))));
        contactField.setOnAction(e -> passwordField.requestFocus());


        // =====================================================
        // LOGIN MESSAGE / ERROR LABEL
        // =====================================================

        Label messageLabel = new Label();

        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(Double.MAX_VALUE);
        messageLabel.setAlignment(Pos.CENTER);

        messageLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 14));

        messageLabel.setTextFill(Color.web("#ff6b6b"));

        messageLabel.setVisible(false);
        messageLabel.setManaged(false);

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton = new Button("Log In  →");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        loginButton.setPrefHeight(56);
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setBackground(
                new Background(new BackgroundFill(Color.web("#0f5215"), new CornerRadii(30), Insets.EMPTY)));
        loginButton.setCursor(Cursor.HAND);

        loginButton.setOnMouseEntered(e -> loginButton.setBackground(
                new Background(new BackgroundFill(Color.web("#176b20"), new CornerRadii(30), Insets.EMPTY))));

        loginButton.setOnMouseExited(e -> loginButton.setBackground(
                new Background(new BackgroundFill(Color.web("#0f5215"), new CornerRadii(30), Insets.EMPTY))));
        loginButton.setOnAction(event -> {

        String contact =
                contactField.getText().trim();

        String password =
                passwordField.getText();

        // ==========================================
        // EMPTY CHECK
        // ==========================================

        if (contact.isEmpty() || password.isEmpty()) {

        messageLabel.setText(
                "⚠ Please enter your email and password.");

        messageLabel.setTextFill(
                Color.web("#ffb74d"));

        messageLabel.setVisible(true);
        messageLabel.setManaged(true);

        return;
        }

        // ==========================================
        // EMAIL VALIDATION
        // ==========================================

        if (!contact.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                messageLabel.setText(
                        "⚠ Please enter a valid email address.");

                messageLabel.setTextFill(
                        Color.web("#ffb74d"));

                messageLabel.setVisible(true);
                messageLabel.setManaged(true);

                return;
        }


        // ==========================================
        // FIREBASE LOGIN
        // ==========================================

        String uid =
                authController.signIn(
                        contact,
                        password);

        if (uid != null) {
        BuyerProfilePage.currentBuyerUid = uid;
        }

       if (uid == null) {

                messageLabel.setText(
                        "❌ Invalid email or password.");

                messageLabel.setTextFill(
                        Color.web("#ff6b6b"));

                messageLabel.setVisible(true);
                messageLabel.setManaged(true);

                passwordField.clear();

                return;
        }
        
        // Login successful - hide error message
        messageLabel.setText("");
        messageLabel.setVisible(false);
        messageLabel.setManaged(false);

        System.out.println(
                "Firebase login successful.");

        // System.out.println(
        //         "UID = " + uid);

        // ==========================================
        // GET USER PROFILE
        // ==========================================

        UserModel user =
                userDAO.getUserByUid(uid);

        if (user == null) {

                messageLabel.setText(
                        "❌ Account profile not found. Please contact the administrator.");

                messageLabel.setTextFill(
                        Color.web("#ff6b6b"));

                messageLabel.setVisible(true);
                messageLabel.setManaged(true);

                return;
        }
        String role =
                user.getRole();

        System.out.println(
                "Logged in user: "
                        + user.getFullName());

        System.out.println(
                "Role: " + role);

        // ==========================================
        // ROLE ROUTING
        // ==========================================

        if ("FARMER".equalsIgnoreCase(role)) {

                System.out.println(
                        "Opening Farmer Home.");

                HomePageFarmer farmerHomePage =
                        new HomePageFarmer();

                mainStage.setScene(
                        farmerHomePage.getHomePageFarmer());

                return;
        }

        if ("BUYER".equalsIgnoreCase(role)) {

                System.out.println(
                        "Opening Buyer Home.");

                BuyerHomepage buyerHomepage =
                        new BuyerHomepage(null);

                mainStage.setScene(
                        buyerHomepage.getBuyerHomePage());

                return;
        }

        if ("ADMIN".equalsIgnoreCase(role)) {

                System.out.println(
                        "Opening Admin Page.");

                AdminPage adminPage =
                        new AdminPage();

                mainStage.setScene(
                        adminPage.getAdminPage("default"));

                return;
        }

        messageLabel.setText(
        "❌ Your account has an invalid role. Please contact the administrator.");

        messageLabel.setTextFill(
                Color.web("#ff6b6b"));

        messageLabel.setVisible(true);
        messageLabel.setManaged(true);

        System.out.println(
                "Unknown user role: " + role);
        });

        // =====================================================
        // CREATE ACCOUNT
        // =====================================================

        Button createAccountButton = new Button("Create an account");
        createAccountButton.setTextFill(Color.WHITE);
        createAccountButton.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        createAccountButton.setPrefHeight(56);
        createAccountButton.setMaxWidth(Double.MAX_VALUE);
        createAccountButton.setBackground(
                new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(30), Insets.EMPTY)));
        createAccountButton.setBorder(
                new Border(new BorderStroke(Color.web("#68d34a"), BorderStrokeStyle.SOLID,
                        new CornerRadii(30), new BorderWidths(1.5))));
        createAccountButton.setCursor(Cursor.HAND);

        createAccountButton.setOnMouseEntered(e -> {
            createAccountButton.setTextFill(Color.web("#68d34a"));
            createAccountButton.setBackground(
                    new Background(new BackgroundFill(Color.rgb(104, 211, 74, 0.08),
                            new CornerRadii(30), Insets.EMPTY)));
        });

        createAccountButton.setOnMouseExited(e -> {
            createAccountButton.setTextFill(Color.WHITE);
            createAccountButton.setBackground(
                    new Background(new BackgroundFill(Color.TRANSPARENT,
                            new CornerRadii(30), Insets.EMPTY)));
        });

        createAccountButton.setOnAction(event -> {
            System.out.println("Create account clicked.");

            Create_Profile createprofile = new Create_Profile();

            Runnable callbacktologin = new Runnable() {
                public void run() {
                    backLoginPage();
                }
            };

            LoginPage.mainStage.setScene(
                    createprofile.getCreateProfilePageScene(callbacktologin));
        });

        // =====================================================
        // SPACING
        // =====================================================

        Region space35 = new Region();
        space35.setPrefHeight(35);

        Region space8a = new Region();
        space8a.setPrefHeight(8);

        Region space32 = new Region();
        space32.setPrefHeight(32);

        Region space8b = new Region();
        space8b.setPrefHeight(8);

        Region space23a = new Region();
        space23a.setPrefHeight(23);

        Region space23b = new Region();
        space23b.setPrefHeight(23);

        Region space28 = new Region();
        space28.setPrefHeight(28);

        Region space16 = new Region();
        space16.setPrefHeight(16);

        Region space30 = new Region();
        space30.setPrefHeight(30);

        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        loginContainer.getChildren().addAll(
                logoContainer, space35, welcomeTitle, space8a,
                welcomeDescription, space32, contactLabel,
                contactField, space23a, passwordHeader, space8b,
                passwordField,messageLabel,space23b, space28, loginButton,
                space16, createAccountButton, space30);

        rightSection.getChildren().add(loginContainer);
        StackPane.setAlignment(loginContainer, Pos.CENTER);

        // =====================================================
        // MAIN LAYOUT
        // =====================================================

        leftSection.setPrefWidth(684);
        leftSection.setMinWidth(500);

        rightSection.setPrefWidth(684);
        rightSection.setMinWidth(500);

        HBox.setHgrow(leftSection, Priority.ALWAYS);
        HBox.setHgrow(rightSection, Priority.ALWAYS);

        mainLayout.getChildren().addAll(leftSection, rightSection);

        Scene loginScene = new Scene(mainLayout, 1368, 768);
        loginPageScene = loginScene;

        mainStage.setScene(loginPageScene);
        mainStage.setTitle("Agro Biz - Login");
        mainStage.setWidth(1368);
        mainStage.setHeight(768);
        // mainStage.setMinWidth(1553);
        // mainStage.setMinHeight(839);

        SplashScreen splash = new SplashScreen();

        mainStage.setScene(splash.getSplashScene(() -> {
            mainStage.setScene(loginPageScene);
        }));

        mainStage.show();
    }

    // =========================================================
    // ANIMATED RIGHT BACKGROUND
    // =========================================================

    private void createRightBackgroundAnimation(StackPane pane) {

        Circle glow1 = new Circle(210);

        glow1.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(104, 211, 74, 0.13)),
                new Stop(1, Color.TRANSPARENT)));

        glow1.setMouseTransparent(true);

        StackPane.setAlignment(glow1, Pos.TOP_RIGHT);
        StackPane.setMargin(glow1, new Insets(-100, -100, 0, 0));

        Circle glow2 = new Circle(160);

        glow2.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(45, 140, 70, 0.12)),
                new Stop(1, Color.TRANSPARENT)));

        glow2.setMouseTransparent(true);

        StackPane.setAlignment(glow2, Pos.BOTTOM_LEFT);
        StackPane.setMargin(glow2, new Insets(0, 0, -80, -80));

        Circle glow3 = new Circle(120);

        glow3.setFill(new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(104, 211, 74, 0.08)),
                new Stop(1, Color.TRANSPARENT)));

        glow3.setMouseTransparent(true);

        StackPane.setAlignment(glow3, Pos.CENTER_RIGHT);
        StackPane.setMargin(glow3, new Insets(0, -80, 0, 0));

        TranslateTransition move1 = new TranslateTransition(Duration.seconds(9), glow1);
        move1.setToX(-100);
        move1.setToY(80);
        move1.setAutoReverse(true);
        move1.setCycleCount(Animation.INDEFINITE);
        move1.play();

        TranslateTransition move2 = new TranslateTransition(Duration.seconds(11), glow2);
        move2.setToX(90);
        move2.setToY(-70);
        move2.setAutoReverse(true);
        move2.setCycleCount(Animation.INDEFINITE);
        move2.play();

        TranslateTransition move3 = new TranslateTransition(Duration.seconds(8), glow3);
        move3.setToX(-70);
        move3.setToY(60);
        move3.setAutoReverse(true);
        move3.setCycleCount(Animation.INDEFINITE);
        move3.play();

        pane.getChildren().addAll(glow1, glow2, glow3);

        Random random = new Random();

        for (int i = 0; i < 18; i++) {

            Circle particle = new Circle(1.5 + random.nextDouble() * 2);

            particle.setFill(Color.rgb(
                    104, 211, 74,
                    0.18 + random.nextDouble() * 0.25));

            particle.setMouseTransparent(true);

            particle.setTranslateX(random.nextDouble() * 550 - 275);
            particle.setTranslateY(random.nextDouble() * 700 - 350);

            pane.getChildren().add(particle);

            TranslateTransition move = new TranslateTransition(
                    Duration.seconds(5 + random.nextDouble() * 6), particle);

            move.setByX(-35 + random.nextDouble() * 70);
            move.setByY(-50 - random.nextDouble() * 80);
            move.setAutoReverse(true);
            move.setCycleCount(Animation.INDEFINITE);
            move.play();

            FadeTransition fade = new FadeTransition(
                    Duration.seconds(2.5 + random.nextDouble() * 3), particle);

            fade.setFromValue(0.15);
            fade.setToValue(0.7);
            fade.setAutoReverse(true);
            fade.setCycleCount(Animation.INDEFINITE);
            fade.play();
        }
    }

    public void backLoginPage() {
        mainStage.setScene(loginPageScene);
    }
}