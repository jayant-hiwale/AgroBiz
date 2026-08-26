package com.pravartak.view.login;

import java.net.URL;

import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.farmer.MarketPlace;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPage extends Application {
        public static Stage mainStage;
        private Scene loginPageScene;

        @Override
        public void start(Stage stage) throws Exception {

                // Store the ONE stage
                mainStage = stage;
                mainStage.setTitle("AgroBiz");

                mainStage.setWidth(1368);
                mainStage.setHeight(768);

                mainStage.setMinWidth(1000);
                mainStage.setMinHeight(650);

                loginPageScene = createLoginScene();

                SplashScreen splash = new SplashScreen();

                mainStage.setScene(splash.getSplashScene(() -> {
                        mainStage.setScene(loginPageScene);
                }));

                mainStage.show();
        }

        // =========================================================
        // CREATE LOGIN SCENE
        // =========================================================

        public Scene createLoginScene() {

                HBox mainLayout = new HBox();

                mainLayout.setPrefSize(1368, 768);

                StackPane leftSection = createLeftSection();

                VBox rightSection = createRightSection();

                leftSection.setPrefWidth(684);
                leftSection.setMinWidth(500);

                rightSection.setPrefWidth(684);
                rightSection.setMinWidth(500);

                HBox.setHgrow(leftSection, Priority.ALWAYS);

                HBox.setHgrow(rightSection, Priority.ALWAYS);

                mainLayout.getChildren().addAll(leftSection, rightSection);

                return new Scene(mainLayout, 1368, 768);
        }

        // =========================================================
        // LEFT SECTION
        // =========================================================

        private StackPane createLeftSection() {

                StackPane leftSection = new StackPane();

                // =====================================================
                // FARM IMAGE
                // =====================================================

                Image farmImage = loadFarmImage();

                ImageView farmImageView = new ImageView(farmImage);

                farmImageView.setPreserveRatio(
                                false);

                // Image fills entire left side

                farmImageView.fitWidthProperty().bind(leftSection.widthProperty());

                farmImageView.fitHeightProperty().bind(leftSection.heightProperty());

                // =====================================================
                // DARK OVERLAY
                // =====================================================

                Rectangle darkOverlay = new Rectangle();

                darkOverlay.setFill(Color.rgb(0, 0, 0, 0.45));

                darkOverlay.widthProperty().bind(leftSection.widthProperty());

                darkOverlay.heightProperty().bind(leftSection.heightProperty());

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label(
                                "Cultivating the Future\n"
                                                + "of Agriculture");

                title.setTextFill(Color.WHITE);

                title.setFont(Font.font("Arial", FontWeight.BOLD, 40));

                title.setWrapText(true);

                // DESCRIPTION

                Label description = new Label(
                                "Empowering farming entrepreneurs "
                                                + "with AI-driven insights\n"
                                                + "and a vibrant marketplace. "
                                                + "Join the growing community\n"
                                                + "today.");

                description.setTextFill(
                                Color.rgb(235, 235, 235));

                description.setFont(Font.font("Arial", 17));

                description.setWrapText(true);

                // TEXT CONTAINER

                VBox textContainer = new VBox();

                textContainer.setSpacing(14);

                textContainer.setAlignment(Pos.BOTTOM_LEFT);

                textContainer.setPadding(
                                new Insets(0, 55, 45, 55));

                textContainer.getChildren().addAll(title, description);

                // =====================================================
                // IMPORTANT STACK ORDER
                // =====================================================

                leftSection.getChildren().addAll(farmImageView, darkOverlay, textContainer);

                StackPane.setAlignment(textContainer, Pos.BOTTOM_LEFT);

                return leftSection;
        }

        // =========================================================
        // RIGHT SECTION
        // =========================================================

        private VBox createRightSection() {

                VBox rightSection = new VBox();
                rightSection.setAlignment(Pos.CENTER);

                rightSection.setPadding(new Insets(30));

                // =====================================================
                // DARK BACKGROUND
                // =====================================================

                rightSection.setBackground(
                                new Background(new BackgroundFill(Color.rgb(8, 12, 13),
                                                CornerRadii.EMPTY,
                                                Insets.EMPTY)));

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

                logoIcon.setPrefSize(52, 52);

                logoIcon.setAlignment(Pos.CENTER);

                logoIcon.setFont(Font.font("Arial", 24));

                logoIcon.setTextFill(Color.WHITE);

                logoIcon.setBackground(new Background(
                                new BackgroundFill(Color.rgb(104, 211, 74), new CornerRadii(9), Insets.EMPTY)));

                // =====================================================
                // LOGO TEXT
                // =====================================================

                Label logoText = new Label("AgroBiz");

                logoText.setTextFill(Color.rgb(104, 211, 74));

                logoText.setFont(Font.font("Arial", FontWeight.BOLD, 30));

                // =====================================================
                // LOGO CONTAINER
                // =====================================================

                HBox logoContainer = new HBox();

                logoContainer.setSpacing(14);

                logoContainer.setAlignment(Pos.CENTER_LEFT);

                logoContainer.getChildren().addAll(
                                logoIcon,
                                logoText);

                // =====================================================
                // WELCOME TITLE
                // =====================================================

                Label welcomeTitle = new Label("Welcome back");

                welcomeTitle.setTextFill(Color.rgb(238, 238, 238));

                welcomeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));

                // =====================================================
                // WELCOME DESCRIPTION
                // =====================================================

                Label welcomeDescription = new Label("Please enter your details to sign in.");

                welcomeDescription.setTextFill(Color.rgb(170, 170, 170));

                welcomeDescription.setFont(Font.font("Arial", 16));

                // =====================================================
                // CONTACT LABEL
                // =====================================================

                Label contactLabel = new Label("Phone number or Email");
                contactLabel.setTextFill(Color.rgb(220, 220, 220));

                contactLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

                // =====================================================
                // CONTACT FIELD
                // =====================================================

                TextField contactField = new TextField();

                contactField.setPromptText("Enter your registered contact");

                styleTextField(contactField);

                // =====================================================
                // PASSWORD LABEL
                // =====================================================

                Label passwordLabel = new Label("Password");

                passwordLabel.setTextFill(
                                Color.rgb(220, 220, 220));

                passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

                // =====================================================
                // FORGOT PASSWORD
                // =====================================================

                Button forgotPasswordButton = new Button("Forgot password?");
                styleLinkButton(forgotPasswordButton);

                // =====================================================
                // PASSWORD HEADER
                // =====================================================

                Region passwordSpace = new Region();

                HBox.setHgrow(passwordSpace, Priority.ALWAYS);

                HBox passwordHeader = new HBox();

                passwordHeader.setAlignment(Pos.CENTER_LEFT);

                passwordHeader.getChildren().addAll(
                                passwordLabel,
                                passwordSpace,
                                forgotPasswordButton);

                // =====================================================
                // PASSWORD FIELD
                // =====================================================

                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Enter your password");
                styleTextField(passwordField);

                // =====================================================
                // LOGIN BUTTON
                // =====================================================

                Button loginButton = new Button("Login   →");

                styleLoginButton(loginButton);

                // =====================================================
                // LOGIN BUTTON ACTION
                // =====================================================

                loginButton.setOnAction(event -> {

                        String contact = contactField.getText().trim();
                        String password = passwordField.getText().trim();

                        // Hard-coded Admin credentials
                        String adminContact = "q";      //admin@agrobiz.com
                        String adminPassword = "q";                      //admin123

                        if (contact.isEmpty() || password.isEmpty()) {

                                System.out.println(  "Please enter your contact and password.");

                        } else if (contact.equalsIgnoreCase(adminContact) && password.equals(adminPassword)) {

                                System.out.println("Admin login successful!");

                                AdminPage adminPage = new AdminPage();

                                mainStage.setScene(adminPage.getAdminPage("default"));
                                mainStage.show();

                        } else {

                                System.out.println("Invalid credentials.");
                        }
                });
                // =====================================================
                // CREATE ACCOUNT BUTTON
                // =====================================================

                Button createAccountButton = new Button("Create an account");

                styleCreateAccountButton(createAccountButton);

                createAccountButton.setOnAction(
                                event -> {

                                        System.out.println("Create account clicked.");

                                        MarketPlace marketPlace = new MarketPlace();
                                        mainStage.setScene(marketPlace.getMarketPlaceScene());
                                });

                // =====================================================
                // ADD EVERYTHING TO LOGIN CONTAINER
                // =====================================================

                loginContainer.getChildren().addAll(

                                logoContainer,
                                createSpace(35),
                                welcomeTitle,
                                createSpace(8),
                                welcomeDescription,
                                createSpace(32),
                                contactLabel,
                                createSpace(8),
                                contactField,
                                createSpace(23),
                                passwordHeader,
                                createSpace(8),
                                passwordField,
                                createSpace(28),
                                loginButton,
                                createSpace(16),
                                createAccountButton,
                                createSpace(30));

                // =====================================================
                // ADD LOGIN CONTAINER TO RIGHT
                // =====================================================

                rightSection.getChildren().add(loginContainer);

                return rightSection;
        }

        // =========================================================
        // TEXT FIELD STYLE
        // =========================================================

        private void styleTextField(TextField textField) {

                textField.setPrefHeight(52);

                textField.setFont(Font.font("Arial", 15));

                textField.setPadding(new Insets(0, 15, 0, 15));

                textField.setBackground(new Background(
                                new BackgroundFill(Color.rgb(16, 21, 22), new CornerRadii(8), Insets.EMPTY)));

                textField.setStyle("-fx-text-fill: rgb(238, 238, 238);");

                textField.setBorder(new Border(new BorderStroke(Color.rgb(48, 55, 56),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(8),
                                new BorderWidths(1.5))));
        }

        // =========================================================
        // LINK BUTTON STYLE
        // =========================================================

        private void styleLinkButton(Button button) {

                button.setTextFill(Color.rgb(104, 211, 74));

                button.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                button.setBackground(Background.EMPTY);

                button.setBorder(Border.EMPTY);

                button.setCursor(Cursor.HAND);
        }

        // =========================================================
        // LOGIN BUTTON STYLE
        // =========================================================

        private void styleLoginButton(Button button) {

                button.setTextFill(Color.rgb(8, 12, 13));

                button.setFont(Font.font("Arial", FontWeight.BOLD, 17));

                button.setPrefHeight(56);

                button.setMaxWidth(Double.MAX_VALUE);

                button.setBackground(new Background(
                                new BackgroundFill(Color.rgb(104, 211, 74), new CornerRadii(30), Insets.EMPTY)));

                button.setCursor(Cursor.HAND);
        }

        // =========================================================
        // CREATE ACCOUNT BUTTON STYLE
        // =========================================================

        private void styleCreateAccountButton(Button button) {

                button.setTextFill(Color.rgb(104, 211, 74));

                button.setFont(Font.font("Arial", FontWeight.BOLD, 17));

                button.setPrefHeight(56);

                button.setMaxWidth(Double.MAX_VALUE);

                button.setBackground(new Background(
                                new BackgroundFill(Color.TRANSPARENT, new CornerRadii(30), Insets.EMPTY)));

                button.setBorder(new Border(new BorderStroke(Color.rgb(104, 211, 74), BorderStrokeStyle.SOLID,
                                new CornerRadii(30), new BorderWidths(1.5))));

                button.setCursor(Cursor.HAND);
        }

        // =========================================================
        // LOAD FARM IMAGE
        // =========================================================

        private Image loadFarmImage() {

                URL imageURL = getClass().getResource("/farm-image.png");

                if (imageURL == null) {

                        throw new RuntimeException(
                                        "ERROR: farm-image.png was not found.\n\n"
                                                        + "Put the image here:\n"
                                                        + "src/main/resources/farm-image.png");
                }

                return new Image(imageURL.toExternalForm());
        }

        // =========================================================
        // SPACE
        // =========================================================

        private Region createSpace(double height) {

                Region space = new Region();

                space.setMinHeight(height);

                space.setPrefHeight(height);
                space.setMaxHeight(height);

                return space;
        }

        public void backLoginPage() {
                mainStage.setScene(loginPageScene);
        }
}