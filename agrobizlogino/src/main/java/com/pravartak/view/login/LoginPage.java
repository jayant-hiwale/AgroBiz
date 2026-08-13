/*package com.pravartak.view.login;

import java.net.URL;

import com.pravartak.view.farmer.HomePageFarmer;

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

    // The ONE stage used by the application
    private Stage mainStage;
    HomePageFarmer homepagefarmer = new HomePageFarmer();


    // =========================================================
    // START METHOD
    // =========================================================

    @Override
    public void start(Stage stage) {

        // Store the stage
        mainStage = stage;

        HBox mainLayout = new HBox();

        mainLayout.setPrefSize(1368, 768);
        


        // Create left and right sections
        StackPane leftSection = createLeftSection();

        VBox rightSection = createRightSection();


        // IMPORTANT:
        // Give each section exactly half of the window

        leftSection.setPrefWidth(684);
        leftSection.setMinWidth(500);

        rightSection.setPrefWidth(684);
        rightSection.setMinWidth(500);


        // Allow them to grow
        HBox.setHgrow(leftSection, Priority.ALWAYS);
        HBox.setHgrow(rightSection, Priority.ALWAYS);

 
        // Add both sections
        mainLayout.getChildren().addAll(
                leftSection,
                rightSection
        );
        */


        /*return new Scene(
                mainLayout,
                1368,
                768
        );
    }


    // =========================================================
    // LEFT SECTION
    // =========================================================

    private StackPane createLeftSection() {

        StackPane leftSection = new StackPane();


        // -----------------------------------------------------
        // FARM IMAGE
        // -----------------------------------------------------

        Image farmImage = loadFarmImage();

        ImageView farmImageView =
                new ImageView(farmImage);


        farmImageView.setPreserveRatio(false);


        // Make image fill the left section
        farmImageView.fitWidthProperty().bind(
                leftSection.widthProperty()
        );

        farmImageView.fitHeightProperty().bind(
                leftSection.heightProperty()
        );


        // -----------------------------------------------------
        // DARK OVERLAY
        // -----------------------------------------------------

        Rectangle darkOverlay =
                new Rectangle();

        darkOverlay.setFill(
                Color.rgb(0, 0, 0, 0.30)
        );

        darkOverlay.widthProperty().bind(
                leftSection.widthProperty()
        );

        darkOverlay.heightProperty().bind(
                leftSection.heightProperty()
        );


        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        Label title =
                new Label(
                        "Cultivating the Future\n"
                        + "of Agriculture"
                );

        title.setTextFill(Color.WHITE);

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );

        title.setWrapText(true);


        // -----------------------------------------------------
        // DESCRIPTION
        // -----------------------------------------------------

        Label description =
                new Label(
                        "Empowering farming entrepreneurs "
                        + "with AI-driven insights\n"
                        + "and a vibrant marketplace. "
                        + "Join the growing community\n"
                        + "today."
                );

        description.setTextFill(Color.WHITE);

        description.setFont(
                Font.font(
                        "Arial",
                        17
                )
        );

        description.setWrapText(true);


        // -----------------------------------------------------
        // TEXT CONTAINER
        // -----------------------------------------------------

        VBox textContainer =
                new VBox();

        textContainer.setSpacing(14);

        textContainer.setAlignment(
                Pos.BOTTOM_LEFT
        );

        textContainer.setPadding(
                new Insets(
                        0,
                        45,
                        55,
                        45
                )
        );


        textContainer.getChildren().addAll(
                title,
                description
        );


        // -----------------------------------------------------
        // IMPORTANT
        // -----------------------------------------------------
        // Order matters in StackPane.
        //
        // Image
        // Overlay
        // Text
        //
        // Text must be added LAST.

        leftSection.getChildren().addAll(
                farmImageView,
                darkOverlay,
                textContainer
        );


        StackPane.setAlignment(
                textContainer,
                Pos.BOTTOM_LEFT
        );


        return leftSection;
    }


    // =========================================================
    // RIGHT SECTION
    // =========================================================

    private VBox createRightSection() {

        VBox rightSection = new VBox();


        rightSection.setAlignment(
                Pos.CENTER
        );


        rightSection.setPadding(
                new Insets(30)
        );


        // -----------------------------------------------------
        // BACKGROUND
        // -----------------------------------------------------

        rightSection.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        248,
                                        249,
                                        220
                                ),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // -----------------------------------------------------
        // LOGIN CONTAINER
        // -----------------------------------------------------

        VBox loginContainer =
                new VBox();

        loginContainer.setSpacing(0);

        loginContainer.setPrefWidth(480);

        loginContainer.setMaxWidth(480);


        // =====================================================
        // LOGO
        // =====================================================

        Label logoIcon =
                new Label("🚜");

        logoIcon.setAlignment(
                Pos.CENTER
        );

        logoIcon.setPrefSize(
                52,
                52
        );

        logoIcon.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );

        logoIcon.setTextFill(Color.WHITE);

        logoIcon.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        15,
                                        82,
                                        21
                                ),
                                new CornerRadii(9),
                                Insets.EMPTY
                        )
                )
        );


        Label logoText =
                new Label("Agro Biz");

        logoText.setTextFill(
                Color.rgb(
                        12,
                        65,
                        20
                )
        );

        logoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );


        HBox logoContainer =
                new HBox();

        logoContainer.setSpacing(14);

        logoContainer.setAlignment(
                Pos.CENTER_LEFT
        );

        logoContainer.getChildren().addAll(
                logoIcon,
                logoText
        );


        // =====================================================
        // WELCOME TITLE
        // =====================================================

        Label welcomeTitle =
                new Label(
                        "Welcome back"
                );

        welcomeTitle.setTextFill(
                Color.rgb(30, 30, 30)
        );

        welcomeTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );


        // =====================================================
        // WELCOME DESCRIPTION
        // =====================================================

        Label welcomeDescription =
                new Label(
                        "Please enter your details to sign in."
                );

        welcomeDescription.setTextFill(
                Color.rgb(70, 70, 70)
        );

        welcomeDescription.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );


        // =====================================================
        // CONTACT LABEL
        // =====================================================

        Label contactLabel =
                new Label(
                        "Phone number or Email"
                );

        contactLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );


        // =====================================================
        // CONTACT FIELD
        // =====================================================

        TextField contactField =
                new TextField();

        contactField.setPromptText(
                "Enter your registered contact"
        );

        styleTextField(
                contactField
        );


        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        Label passwordLabel =
                new Label(
                        "Password"
                );

        passwordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );


        // =====================================================
        // FORGOT PASSWORD
        // =====================================================

        Button forgotPasswordButton =
                new Button(
                        "Forgot password?"
                );

        styleLinkButton(
                forgotPasswordButton
        );


        // =====================================================
        // PASSWORD HEADER
        // =====================================================

        Region passwordSpace =
                new Region();

        HBox.setHgrow(
                passwordSpace,
                Priority.ALWAYS
        );


        HBox passwordHeader =
                new HBox();

        passwordHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        passwordHeader.getChildren().addAll(
                passwordLabel,
                passwordSpace,
                forgotPasswordButton
        );


        // =====================================================
        // PASSWORD FIELD
        // =====================================================

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        styleTextField(
                passwordField
        );


        // =====================================================
        // REMEMBER ME
        // =====================================================

        CheckBox rememberMe =
                new CheckBox(
                        "Remember me for 30 days"
                );

        rememberMe.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );


        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button(
                        "Login   →"
                );

        styleLoginButton(
                loginButton
        );


        // =====================================================
        // LOGIN BUTTON ACTION
        // =====================================================

        loginButton.setOnAction(
                event -> {

                    String contact =
                            contactField.getText();

                    String password =
                            passwordField.getText();


                    if (contact.isEmpty()
                            || password.isEmpty()) {

                        System.out.println(
                                "Please enter your contact and password."
                        );

                    } else {

                        System.out.println(
                                "Login successful!"
                        );

                        // Later we will use Runnable here
                        // to navigate to Dashboard.
                    }
                }
        );


        // =====================================================
        // CREATE ACCOUNT BUTTON
        // =====================================================

        Button createAccountButton =
                new Button(
                        "Create an account"
                );

        styleCreateAccountButton(
                createAccountButton
        );


        createAccountButton.setOnAction(
                event -> {

                    System.out.println(
                            "Create account clicked."
                    );

                    // Later we will use Runnable
                    // to navigate to RegistrationPage.
                }
        );


        // =====================================================
        // SUPPORT BOX
        // =====================================================

        Label helpIcon =
                new Label("?");

        helpIcon.setPrefSize(
                22,
                22
        );

        helpIcon.setAlignment(
                Pos.CENTER
        );

        helpIcon.setTextFill(
                Color.rgb(
                        5,
                        105,
                        20
                )
        );


        helpIcon.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        5,
                                        105,
                                        20
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(3),
                                new BorderWidths(1.5)
                        )
                )
        );


        Label supportText =
                new Label(
                        "Need help logging in?"
                );

        supportText.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );


        Button supportButton =
                new Button(
                        "Contact Support"
                );

        styleLinkButton(
                supportButton
        );


        HBox supportBox =
                new HBox();

        supportBox.setSpacing(10);

        supportBox.setAlignment(
                Pos.CENTER
        );

        supportBox.setPadding(
                new Insets(18)
        );

        supportBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        242,
                                        243,
                                        216
                                ),
                                new CornerRadii(12),
                                Insets.EMPTY
                        )
                )
        );

        supportBox.getChildren().addAll(
                helpIcon,
                supportText,
                supportButton
        );


        // =====================================================
        // ADD EVERYTHING
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

                createSpace(23),

                rememberMe,

                createSpace(28),

                loginButton,

                createSpace(16),

                createAccountButton,

                createSpace(30),

                supportBox
        );


        // Add form to right section
        rightSection.getChildren().add(
                loginContainer
        );


        return rightSection;
    }


    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField textField) {

        textField.setPrefHeight(52);

        textField.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        textField.setPadding(
                new Insets(
                        0,
                        15,
                        0,
                        15
                )
        );

        textField.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(8),
                                Insets.EMPTY
                        )
                )
        );

        textField.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        190,
                                        195,
                                        185
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(8),
                                new BorderWidths(1.5)
                        )
                )
        );
    }


    // =========================================================
    // LINK BUTTON STYLE
    // =========================================================

    private void styleLinkButton(
            Button button) {

        button.setTextFill(
                Color.rgb(
                        5,
                        105,
                        20
                )
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setBackground(
                Background.EMPTY
        );

        button.setBorder(
                Border.EMPTY
        );

        button.setCursor(
                Cursor.HAND
        );
    }


    // =========================================================
    // LOGIN BUTTON STYLE
    // =========================================================

    private void styleLoginButton(
            Button button) {

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        button.setPrefHeight(56);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        15,
                                        82,
                                        21
                                ),
                                new CornerRadii(30),
                                Insets.EMPTY
                        )
                )
        );

        button.setCursor(
                Cursor.HAND
        );
    }


    // =========================================================
    // CREATE ACCOUNT BUTTON STYLE
    // =========================================================

    private void styleCreateAccountButton(
            Button button) {

        button.setTextFill(
                Color.rgb(
                        30,
                        30,
                        30
                )
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        button.setPrefHeight(56);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(30),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        110,
                                        110,
                                        110
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(30),
                                new BorderWidths(1.5)
                        )
                )
        );

        button.setCursor(
                Cursor.HAND
        );
    }


    // =========================================================
    // LOAD IMAGE
    // =========================================================

    private Image loadFarmImage() {

        URL imageURL =
                getClass().getResource(
                        "/farm-image.png"
                );


        if (imageURL == null) {

            throw new RuntimeException(
                    "ERROR: farm-image.png was not found.\n\n"
                    + "Put the image here:\n"
                    + "src/main/resources/farm-image.png"
            );
        }


        return new Image(
                imageURL.toExternalForm()
        );
    }


    // =========================================================
    // CREATE SPACE
    // =========================================================

    private Region createSpace(
            double height) {

        Region space =
                new Region();

        space.setMinHeight(height);

        space.setPrefHeight(height);

        space.setMaxHeight(height);

        return space;

        HBox mainLayout = new HBox();

        mainLayout.setPrefSize(1368, 768);

        // Add both sections
        mainLayout.getChildren().addAll(leftSection,rightSection);

        // Create login scene
        Scene loginScene = new Scene(mainLayout);

        // Set scene
        mainStage.setScene(loginScene);

        // Window properties
        mainStage.setTitle("Agro Biz - Login");

        mainStage.setWidth(1368);
        mainStage.setHeight(768);

        mainStage.setMinWidth(1000);
        mainStage.setMinHeight(650);

        // Show window
        mainStage.show();
    }


    
}*/

package com.pravartak.view.login;

import java.net.URL;

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

    private Stage mainStage;

    @Override
    public void start(Stage stage) {

        // =========================================================
        // STAGE
        // =========================================================

        mainStage = stage;


        // =========================================================
        // MAIN HBOX
        // =========================================================

        HBox mainLayout = new HBox();

        mainLayout.setPrefSize(1368, 768);


        // =========================================================
        // LEFT SECTION - STACKPANE
        // =========================================================

        StackPane leftSection = new StackPane();


        // =========================================================
        // FARM IMAGE
        // =========================================================

        URL imageURL = getClass().getResource("/farm-image.png");

        if (imageURL == null) {

            throw new RuntimeException(
                    "farm-image.png was not found.\n"
                    + "Put the image inside:\n"
                    + "src/main/resources/farm-image.png"
            );
        }

        Image farmImage = new Image(
                imageURL.toExternalForm()
        );

        ImageView farmImageView = new ImageView(
                farmImage
        );

        farmImageView.setPreserveRatio(false);

        farmImageView.fitWidthProperty().bind(
                leftSection.widthProperty()
        );

        farmImageView.fitHeightProperty().bind(
                leftSection.heightProperty()
        );


        // =========================================================
        // DARK OVERLAY
        // =========================================================

        Rectangle darkOverlay = new Rectangle();

        darkOverlay.setFill(
                Color.rgb(0, 0, 0, 0.30)
        );

        darkOverlay.widthProperty().bind(
                leftSection.widthProperty()
        );

        darkOverlay.heightProperty().bind(
                leftSection.heightProperty()
        );


        // =========================================================
        // LEFT TITLE
        // =========================================================

        Label title = new Label(
                "Cultivating the Future\n"
                + "of Agriculture"
        );

        title.setTextFill(
                Color.WHITE
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );

        title.setWrapText(true);


        // =========================================================
        // LEFT DESCRIPTION
        // =========================================================

        Label description = new Label(
                "Empowering farming entrepreneurs "
                + "with AI-driven insights\n"
                + "and a vibrant marketplace. "
                + "Join the growing community\n"
                + "today."
        );

        description.setTextFill(
                Color.WHITE
        );

        description.setFont(
                Font.font(
                        "Arial",
                        17
                )
        );

        description.setWrapText(true);


        // =========================================================
        // TEXT CONTAINER
        // =========================================================

        VBox textContainer = new VBox();

        textContainer.setSpacing(14);

        textContainer.setAlignment(
                Pos.BOTTOM_LEFT
        );

        textContainer.setPadding(
                new Insets(
                        0,
                        45,
                        55,
                        45
                )
        );

        textContainer.getChildren().addAll(
                title,
                description
        );


        // =========================================================
        // ADD IMAGE + OVERLAY + TEXT
        // =========================================================

        leftSection.getChildren().addAll(
                farmImageView,
                darkOverlay,
                textContainer
        );

        StackPane.setAlignment(
                textContainer,
                Pos.BOTTOM_LEFT
        );


        // =========================================================
        // RIGHT SECTION - VBOX
        // =========================================================

        VBox rightSection = new VBox();

        rightSection.setAlignment(
                Pos.CENTER
        );

        rightSection.setPadding(
                new Insets(30)
        );


        // =========================================================
        // RIGHT BACKGROUND
        // =========================================================

        rightSection.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        248,
                                        249,
                                        220
                                ),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // =========================================================
        // LOGIN CONTAINER
        // =========================================================

        VBox loginContainer = new VBox();

        loginContainer.setSpacing(0);

        loginContainer.setPrefWidth(480);

        loginContainer.setMaxWidth(480);


        // =========================================================
        // LOGO ICON
        // =========================================================

        Label logoIcon = new Label(
                "🚜"
        );

        logoIcon.setAlignment(
                Pos.CENTER
        );

        logoIcon.setPrefSize(
                52,
                52
        );

        logoIcon.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );

        logoIcon.setTextFill(
                Color.WHITE
        );

        logoIcon.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        15,
                                        82,
                                        21
                                ),
                                new CornerRadii(9),
                                Insets.EMPTY
                        )
                )
        );


        // =========================================================
        // LOGO TEXT
        // =========================================================

        Label logoText = new Label(
                "Agro Biz"
        );

        logoText.setTextFill(
                Color.rgb(
                        12,
                        65,
                        20
                )
        );

        logoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );


        // =========================================================
        // LOGO CONTAINER
        // =========================================================

        HBox logoContainer = new HBox();

        logoContainer.setSpacing(14);

        logoContainer.setAlignment(
                Pos.CENTER_LEFT
        );

        logoContainer.getChildren().addAll(
                logoIcon,
                logoText
        );


        // =========================================================
        // WELCOME TITLE
        // =========================================================

        Label welcomeTitle = new Label(
                "Welcome back"
        );

        welcomeTitle.setTextFill(
                Color.rgb(
                        30,
                        30,
                        30
                )
        );

        welcomeTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );


        // =========================================================
        // WELCOME DESCRIPTION
        // =========================================================

        Label welcomeDescription = new Label(
                "Please enter your details to sign in."
        );

        welcomeDescription.setTextFill(
                Color.rgb(
                        70,
                        70,
                        70
                )
        );

        welcomeDescription.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );


        // =========================================================
        // CONTACT LABEL
        // =========================================================

        Label contactLabel = new Label(
                "Phone number or Email"
        );

        contactLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );


        // =========================================================
        // CONTACT FIELD
        // =========================================================

        TextField contactField = new TextField();

        contactField.setPromptText(
                "Enter your registered contact"
        );

        contactField.setPrefHeight(
                52
        );

        contactField.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        contactField.setPadding(
                new Insets(
                        0,
                        15,
                        0,
                        15
                )
        );

        contactField.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(8),
                                Insets.EMPTY
                        )
                )
        );

        contactField.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        190,
                                        195,
                                        185
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(8),
                                new BorderWidths(1.5)
                        )
                )
        );


        // =========================================================
        // PASSWORD LABEL
        // =========================================================

        Label passwordLabel = new Label(
                "Password"
        );

        passwordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );


        // =========================================================
        // FORGOT PASSWORD BUTTON
        // =========================================================

        Button forgotPasswordButton = new Button(
                "Forgot password?"
        );

        forgotPasswordButton.setTextFill(
                Color.rgb(
                        5,
                        105,
                        20
                )
        );

        forgotPasswordButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        forgotPasswordButton.setBackground(
                Background.EMPTY
        );

        forgotPasswordButton.setBorder(
                Border.EMPTY
        );

        forgotPasswordButton.setCursor(
                Cursor.HAND
        );


        // =========================================================
        // PASSWORD HEADER
        // =========================================================

        Region passwordSpace = new Region();

        HBox.setHgrow(
                passwordSpace,
                Priority.ALWAYS
        );

        HBox passwordHeader = new HBox();

        passwordHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        passwordHeader.getChildren().addAll(
                passwordLabel,
                passwordSpace,
                forgotPasswordButton
        );


        // =========================================================
        // PASSWORD FIELD
        // =========================================================

        PasswordField passwordField = new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefHeight(
                52
        );

        passwordField.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        passwordField.setPadding(
                new Insets(
                        0,
                        15,
                        0,
                        15
                )
        );

        passwordField.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(8),
                                Insets.EMPTY
                        )
                )
        );

        passwordField.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        190,
                                        195,
                                        185
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(8),
                                new BorderWidths(1.5)
                        )
                )
        );


        // =========================================================
        // REMEMBER ME
        // =========================================================

        CheckBox rememberMe = new CheckBox(
                "Remember me for 30 days"
        );

        rememberMe.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );


        // =========================================================
        // LOGIN BUTTON
        // =========================================================

        Button loginButton = new Button(
                "Login   →"
        );

        loginButton.setTextFill(
                Color.WHITE
        );

        loginButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        loginButton.setPrefHeight(
                56
        );

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        15,
                                        82,
                                        21
                                ),
                                new CornerRadii(30),
                                Insets.EMPTY
                        )
                )
        );

        loginButton.setCursor(
                Cursor.HAND
        );


        // =========================================================
        // LOGIN BUTTON ACTION
        // =========================================================

        loginButton.setOnAction(
                event -> {

                    String contact =
                            contactField.getText();

                    String password =
                            passwordField.getText();

                    if (contact.isEmpty()
                            || password.isEmpty()) {

                        System.out.println(
                                "Please enter your contact and password."
                        );

                    } else {

                        System.out.println(
                                "Login successful!"
                        );
                    }
                }
        );


        // =========================================================
        // CREATE ACCOUNT BUTTON
        // =========================================================

        Button createAccountButton = new Button(
                "Create an account"
        );

        createAccountButton.setTextFill(
                Color.rgb(
                        30,
                        30,
                        30
                )
        );

        createAccountButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        createAccountButton.setPrefHeight(
                56
        );

        createAccountButton.setMaxWidth(
                Double.MAX_VALUE
        );

        createAccountButton.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(30),
                                Insets.EMPTY
                        )
                )
        );

        createAccountButton.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        110,
                                        110,
                                        110
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(30),
                                new BorderWidths(1.5)
                        )
                )
        );

        createAccountButton.setCursor(
                Cursor.HAND
        );


        // =========================================================
        // CREATE ACCOUNT ACTION
        // =========================================================

        createAccountButton.setOnAction(
                event -> {

                    System.out.println(
                            "Create account clicked."
                    );
                }
        );


        // =========================================================
        // SUPPORT ICON
        // =========================================================

        Label helpIcon = new Label(
                "?"
        );

        helpIcon.setPrefSize(
                22,
                22
        );

        helpIcon.setAlignment(
                Pos.CENTER
        );

        helpIcon.setTextFill(
                Color.rgb(
                        5,
                        105,
                        20
                )
        );

        helpIcon.setBorder(
                new Border(
                        new BorderStroke(
                                Color.rgb(
                                        5,
                                        105,
                                        20
                                ),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(3),
                                new BorderWidths(1.5)
                        )
                )
        );


        // =========================================================
        // SUPPORT TEXT
        // =========================================================

        Label supportText = new Label(
                "Need help logging in?"
        );

        supportText.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );


        // =========================================================
        // SUPPORT BUTTON
        // =========================================================

        Button supportButton = new Button(
                "Contact Support"
        );

        supportButton.setTextFill(
                Color.rgb(
                        5,
                        105,
                        20
                )
        );

        supportButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        supportButton.setBackground(
                Background.EMPTY
        );

        supportButton.setBorder(
                Border.EMPTY
        );

        supportButton.setCursor(
                Cursor.HAND
        );


        // =========================================================
        // SUPPORT BOX
        // =========================================================

        HBox supportBox = new HBox();

        supportBox.setSpacing(10);

        supportBox.setAlignment(
                Pos.CENTER
        );

        supportBox.setPadding(
                new Insets(18)
        );

        supportBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        242,
                                        243,
                                        216
                                ),
                                new CornerRadii(12),
                                Insets.EMPTY
                        )
                )
        );

        supportBox.getChildren().addAll(
                helpIcon,
                supportText,
                supportButton
        );


        // =========================================================
        // SPACING REGIONS
        // =========================================================

        Region space35 = new Region();
        space35.setMinHeight(35);
        space35.setPrefHeight(35);
        space35.setMaxHeight(35);

        Region space8_1 = new Region();
        space8_1.setMinHeight(8);
        space8_1.setPrefHeight(8);
        space8_1.setMaxHeight(8);

        Region space32 = new Region();
        space32.setMinHeight(32);
        space32.setPrefHeight(32);
        space32.setMaxHeight(32);

        Region space23_1 = new Region();
        space23_1.setMinHeight(23);
        space23_1.setPrefHeight(23);
        space23_1.setMaxHeight(23);

        Region space23_2 = new Region();
        space23_2.setMinHeight(23);
        space23_2.setPrefHeight(23);
        space23_2.setMaxHeight(23);

        Region space28 = new Region();
        space28.setMinHeight(28);
        space28.setPrefHeight(28);
        space28.setMaxHeight(28);

        Region space16 = new Region();
        space16.setMinHeight(16);
        space16.setPrefHeight(16);
        space16.setMaxHeight(16);

        Region space30 = new Region();
        space30.setMinHeight(30);
        space30.setPrefHeight(30);
        space30.setMaxHeight(30);


        // =========================================================
        // ADD ALL ITEMS TO LOGIN CONTAINER
        // =========================================================

        loginContainer.getChildren().addAll(

                logoContainer,

                space35,

                welcomeTitle,

                space8_1,

                welcomeDescription,

                space32,

                contactLabel,

                space8_1,

                contactField,

                space23_1,

                passwordHeader,

                space8_1,

                passwordField,

                space23_2,

                rememberMe,

                space28,

                loginButton,

                space16,

                createAccountButton,

                space30,

                supportBox
        );


        // =========================================================
        // ADD LOGIN CONTAINER TO RIGHT SECTION
        // =========================================================

        rightSection.getChildren().add(
                loginContainer
        );


        // =========================================================
        // LEFT + RIGHT WIDTH
        // =========================================================

        leftSection.setPrefWidth(
                684
        );

        leftSection.setMinWidth(
                500
        );

        rightSection.setPrefWidth(
                684
        );

        rightSection.setMinWidth(
                500
        );

        HBox.setHgrow(
                leftSection,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                rightSection,
                Priority.ALWAYS
        );


        // =========================================================
        // ADD LEFT AND RIGHT TO MAIN HBOX
        // =========================================================

        mainLayout.getChildren().addAll(
                leftSection,
                rightSection
        );


        // =========================================================
        // CREATE SCENE
        // =========================================================

        Scene loginScene = new Scene(
                mainLayout,
                1368,
                768
        );


        // =========================================================
        // STAGE SETTINGS
        // =========================================================

        mainStage.setScene(
                loginScene
        );

        mainStage.setTitle(
                "Agro Biz - Login"
        );

        mainStage.setWidth(
                1368
        );

        mainStage.setHeight(
                768
        );

        mainStage.setMinWidth(
                1000
        );

        mainStage.setMinHeight(
                650
        );

        mainStage.show();
    }
}