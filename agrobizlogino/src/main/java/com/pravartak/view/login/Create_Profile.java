// package com.pravartak.view.login;

// import com.pravartak.controller.authentication_contr.AuthController;
// import com.pravartak.dao.UserDAO;
// import com.pravartak.model.UserModel;
// import java.net.URL;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;

// import javafx.scene.layout.HBox;

// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;

// import javafx.scene.shape.Line;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

// public class Create_Profile {

//         private String selectedRole = "";

//         private final AuthController authController = new AuthController();

//         private final UserDAO userDAO = new UserDAO();

//         public Scene getCreateProfilePageScene(Runnable callbacktologin) {

//                 // =====================================================
//                 // FULL SCREEN ROOT
//                 // =====================================================

//                 StackPane root = new StackPane();

//                 root.setPrefSize(1365, 768);

//                 // =====================================================
//                 // BACKGROUND IMAGE
//                 // =====================================================

//                 URL imageURL = getClass().getResource("/create-profile-background.png");

//                 if (imageURL == null) {
//                         throw new RuntimeException(
//                                         "create-profile-background.png not found!\n"
//                                                         + "Put it inside: src/main/resources/create-profile-background.png");
//                 }

//                 Image backgroundImage = new Image(imageURL.toExternalForm());

//                 javafx.scene.image.ImageView backgroundView = new javafx.scene.image.ImageView(backgroundImage);

//                 backgroundView.setPreserveRatio(false);

//                 backgroundView.fitWidthProperty()
//                                 .bind(root.widthProperty());

//                 backgroundView.fitHeightProperty()
//                                 .bind(root.heightProperty());

//                 // =====================================================
//                 // DARK OVERLAY
//                 // =====================================================

//                 Region darkOverlay = new Region();

//                 darkOverlay.setStyle(
//                                 "-fx-background-color: rgba(0,0,0,0.34);");

//                 darkOverlay.prefWidthProperty()
//                                 .bind(root.widthProperty());

//                 darkOverlay.prefHeightProperty()
//                                 .bind(root.heightProperty());

//                 // =====================================================
//                 // SUBTLE GREEN OVERLAY
//                 // =====================================================

//                 Region greenOverlay = new Region();

//                 greenOverlay.setStyle(
//                                 "-fx-background-color: rgba(4,35,20,0.12);");

//                 greenOverlay.prefWidthProperty()
//                                 .bind(root.widthProperty());

//                 greenOverlay.prefHeightProperty()
//                                 .bind(root.heightProperty());

//                 // =====================================================
//                 // RIGHT SIDE DARK AREA
//                 // =====================================================

//                 StackPane rightArea = new StackPane();

//                 rightArea.setPrefWidth(600);

//                 rightArea.setMaxWidth(600);

//                 rightArea.setStyle(
//                                 "-fx-background-color: transparent);");

//                 // createAnimatedBackground(rightArea);

//                 // =====================================================
//                 // ACCOUNT BOX - COMPACT
//                 // =====================================================

//                 VBox accountBox = new VBox();

//                 accountBox.setPrefWidth(465);
//                 accountBox.setMinWidth(465);
//                 accountBox.setMaxWidth(465);

//                 accountBox.setPrefHeight(650);
//                 accountBox.setMinHeight(650);
//                 accountBox.setMaxHeight(650);

//                 accountBox.setPadding(
//                                 new Insets(25, 35, 22, 35));

//                 accountBox.setSpacing(7);

//                 accountBox.setStyle(
//                                 "-fx-background-color: rgba(5,18,12,0.96);"
//                                                 + "-fx-background-radius: 20;"
//                                                 + "-fx-border-color: rgba(104,211,74,0.45);"
//                                                 + "-fx-border-width: 1.2;"
//                                                 + "-fx-border-radius: 20;"
//                                                 + "-fx-effect: dropshadow("
//                                                 + "gaussian, rgba(0,0,0,0.65), 28, 0, 0, 10);");

//                 // =====================================================
//                 // TITLE
//                 // =====================================================

//                 Label title = new Label("Create an Account");

//                 title.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 27));

//                 title.setTextFill(Color.WHITE);

//                 Label subtitle = new Label(
//                                 "Create your Agro Biz account to get started.");

//                 subtitle.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.NORMAL,
//                                                 14));

//                 subtitle.setTextFill(
//                                 Color.web("#aab8ae"));

//                 // =====================================================
//                 // FULL NAME
//                 // =====================================================

//                 Label fullNameLabel = new Label("Full Name");

//                 fullNameLabel.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 14));

//                 fullNameLabel.setTextFill(Color.WHITE);

//                 TextField fullName = new TextField();

//                 fullName.setPromptText(
//                                 "Enter your full name");

//                 fullName.setPrefHeight(47);

//                 fullName.setStyle(
//                                 "-fx-background-color: rgba(255,255,255,0.035);"
//                                                 + "-fx-text-fill: white;"
//                                                 + "-fx-prompt-text-fill: #84948a;"
//                                                 + "-fx-border-color: #385044;"
//                                                 + "-fx-border-width: 1;"
//                                                 + "-fx-border-radius: 10;"
//                                                 + "-fx-background-radius: 10;"
//                                                 + "-fx-font-size: 14px;"
//                                                 + "-fx-padding: 0 15 0 15;");

//                 // =====================================================
//                 // EMAIL
//                 // =====================================================

//                 Label emailLabel = new Label("Email");

//                 emailLabel.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 14));

//                 emailLabel.setTextFill(Color.WHITE);

//                 TextField email = new TextField();

//                 email.setPromptText(
//                                 "Enter your email");

//                 email.setPrefHeight(47);

//                 email.setStyle(
//                                 "-fx-background-color: rgba(255,255,255,0.035);"
//                                                 + "-fx-text-fill: white;"
//                                                 + "-fx-prompt-text-fill: #84948a;"
//                                                 + "-fx-border-color: #385044;"
//                                                 + "-fx-border-width: 1;"
//                                                 + "-fx-border-radius: 10;"
//                                                 + "-fx-background-radius: 10;"
//                                                 + "-fx-font-size: 14px;"
//                                                 + "-fx-padding: 0 15 0 15;");

//                 // =====================================================
//                 // PASSWORD
//                 // =====================================================

//                 Label passwordLabel = new Label("Password");

//                 passwordLabel.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 14));

//                 passwordLabel.setTextFill(Color.WHITE);

//                 PasswordField password = new PasswordField();

//                 password.setPromptText(
//                                 "Create a strong password");

//                 password.setPrefHeight(47);

//                 password.setStyle(
//                                 "-fx-background-color: rgba(255,255,255,0.035);"
//                                                 + "-fx-text-fill: white;"
//                                                 + "-fx-prompt-text-fill: #84948a;"
//                                                 + "-fx-border-color: #385044;"
//                                                 + "-fx-border-width: 1;"
//                                                 + "-fx-border-radius: 10;"
//                                                 + "-fx-background-radius: 10;"
//                                                 + "-fx-font-size: 14px;"
//                                                 + "-fx-padding: 0 15 0 15;");

//                 // =====================================================
//                 // ROLE SELECTION
//                 // =====================================================

//                 Label roleLabel = new Label("Choose your role");

//                 roleLabel.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 14));

//                 roleLabel.setTextFill(Color.WHITE);

//                 // =====================================================
//                 // FARMER CARD
//                 // =====================================================

//                 Label farmerIcon = new Label("🚜");

//                 farmerIcon.setStyle(
//                                 "-fx-font-size: 25px;");

//                 Label farmerText = new Label("Farmer / Learner");

//                 farmerText.setTextFill(Color.WHITE);

//                 farmerText.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 13));

//                 VBox farmerCard = new VBox(
//                                 5,
//                                 farmerIcon,
//                                 farmerText);

//                 farmerCard.setAlignment(Pos.CENTER);

//                 farmerCard.setPrefWidth(185);
//                 farmerCard.setPrefHeight(76);

//                 String normalCardStyle = "-fx-background-color: #101718;"
//                                 + "-fx-background-radius: 10;"
//                                 + "-fx-border-color: #344b39;"
//                                 + "-fx-border-width: 1.2;"
//                                 + "-fx-border-radius: 10;"
//                                 + "-fx-cursor: hand;";

//                 String selectedCardStyle = "-fx-background-color: #1f5c2b;"
//                                 + "-fx-background-radius: 10;"
//                                 + "-fx-border-color: #68d34a;"
//                                 + "-fx-border-width: 2;"
//                                 + "-fx-border-radius: 10;"
//                                 + "-fx-cursor: hand;";

//                 farmerCard.setStyle(normalCardStyle);

//                 // =====================================================
//                 // BUYER CARD
//                 // =====================================================

//                 Label buyerIcon = new Label("🛒");

//                 buyerIcon.setStyle(
//                                 "-fx-font-size: 25px;");

//                 Label buyerText = new Label("Buyer");

//                 buyerText.setTextFill(Color.WHITE);

//                 buyerText.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 13));

//                 VBox buyerCard = new VBox(
//                                 5,
//                                 buyerIcon,
//                                 buyerText);

//                 buyerCard.setAlignment(Pos.CENTER);

//                 buyerCard.setPrefWidth(185);
//                 buyerCard.setPrefHeight(76);

//                 buyerCard.setStyle(normalCardStyle);

//                 farmerCard.setOnMouseClicked(e -> {

//                         selectedRole = "FARMER";

//                         farmerCard.setStyle(selectedCardStyle);
//                         buyerCard.setStyle(normalCardStyle);
//                 });

//                 buyerCard.setOnMouseClicked(e -> {

//                         selectedRole = "BUYER";

//                         buyerCard.setStyle(selectedCardStyle);
//                         farmerCard.setStyle(normalCardStyle);
//                 });

//                 HBox roleCards = new HBox(
//                                 12,
//                                 farmerCard,
//                                 buyerCard);

//                 roleCards.setAlignment(Pos.CENTER);

//                 // =====================================================
//                 // CREATE ACCOUNT BUTTON
//                 // =====================================================

//                 Button createAccount = new Button("Create Account     →");

//                 createAccount.setPrefHeight(48);

//                 createAccount.setMaxWidth(
//                                 Double.MAX_VALUE);

//                 createAccount.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 16));

//                 createAccount.setTextFill(
//                                 Color.web("#07100a"));

//                 createAccount.setStyle(
//                                 "-fx-background-color: #68d34a;"
//                                                 + "-fx-background-radius: 28;"
//                                                 + "-fx-cursor: hand;");

//                 createAccount.setOnMouseEntered(e -> createAccount.setStyle(
//                                 "-fx-background-color: #7be85b;"
//                                                 + "-fx-background-radius: 28;"
//                                                 + "-fx-cursor: hand;"
//                                                 + "-fx-effect: dropshadow("
//                                                 + "gaussian, rgba(104,211,74,0.35),"
//                                                 + "15,0,0,0);"));

//                 createAccount.setOnMouseExited(e -> createAccount.setStyle(
//                                 "-fx-background-color: #68d34a;"
//                                                 + "-fx-background-radius: 28;"
//                                                 + "-fx-cursor: hand;"));

//                 // =====================================================
//                 // CREATE ACCOUNT LOGIC - SAME AS YOUR CODE
//                 // =====================================================

//                 createAccount.setOnAction(e -> {

//                         String name = fullName.getText().trim();

//                         String userEmail = email.getText().trim();

//                         String userPassword = password.getText();

//                         if (name.isEmpty()
//                                         || userEmail.isEmpty()
//                                         || userPassword.isEmpty()) {

//                                 System.out.println(
//                                                 "Please fill all fields.");

//                                 return;
//                         }

//                         if (selectedRole.isEmpty()) {

//                                 System.out.println(
//                                                 "Please select Farmer or Buyer.");

//                                 return;
//                         }

//                         if (userPassword.length() < 6) {

//                                 System.out.println(
//                                                 "Password must contain at least 6 characters.");

//                                 return;
//                         }

//                         String uid = authController.signUp(
//                                         userEmail,
//                                         userPassword);

//                         if (uid == null) {

//                                 System.out.println(
//                                                 "Account creation failed.");

//                                 return;
//                         }

//                         System.out.println(
//                                         "Firebase account created.");

//                         System.out.println(
//                                         "UID = " + uid);

//                         int farmerId = 0;

//                         if ("FARMER".equalsIgnoreCase(
//                                         selectedRole)) {

//                                 farmerId = (int) (System.currentTimeMillis()
//                                                 % Integer.MAX_VALUE);

//                                 System.out.println(
//                                                 "Generated Farmer ID = "
//                                                                 + farmerId);
//                         }

//                         UserModel user = new UserModel(
//                                         uid,
//                                         name,
//                                         userEmail,
//                                         selectedRole,
//                                         farmerId);

//                         boolean saved = userDAO.saveUser(user);

//                         if (!saved) {

//                                 System.out.println(
//                                                 "Account created but profile could not be saved.");

//                                 return;
//                         }

//                         System.out.println(
//                                         "User profile saved.");

//                         System.out.println(
//                                         "Role = " + selectedRole);

//                         callbacktologin.run();
//                 });

//                 // =====================================================
//                 // OR DIVIDER
//                 // =====================================================

//                 Line line1 = new Line(0, 0, 80, 0);

//                 line1.setStroke(
//                                 Color.web("#344b39"));

//                 Label orLabel = new Label("or");

//                 orLabel.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.NORMAL,
//                                                 12));

//                 orLabel.setTextFill(
//                                 Color.web("#8fa094"));

//                 Line line2 = new Line(0, 0, 80, 0);

//                 line2.setStroke(
//                                 Color.web("#344b39"));

//                 HBox orHBox = new HBox(
//                                 10,
//                                 line1,
//                                 orLabel,
//                                 line2);

//                 orHBox.setAlignment(
//                                 Pos.CENTER);

//                 // =====================================================
//                 // LOGIN
//                 // =====================================================

//                 Label loginText = new Label(
//                                 "Already have an account? ");

//                 loginText.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.NORMAL,
//                                                 13));

//                 loginText.setTextFill(
//                                 Color.web("#aab8ae"));

//                 Button login = new Button("Log in");

//                 login.setTextFill(
//                                 Color.web("#68d34a"));

//                 login.setFont(
//                                 Font.font(
//                                                 "Arial",
//                                                 FontWeight.BOLD,
//                                                 13));

//                 login.setStyle(
//                                 "-fx-background-color: transparent;"
//                                                 + "-fx-border-color: transparent;"
//                                                 + "-fx-cursor: hand;");

//                 login.setOnAction(e -> {

//                         System.out.println(
//                                         "login button clicked");

//                         callbacktologin.run();
//                 });

//                 HBox loginHBox = new HBox(
//                                 loginText,
//                                 login);

//                 loginHBox.setAlignment(
//                                 Pos.CENTER);

//                 // =====================================================
//                 // SPACING
//                 // =====================================================

//                 Region spaceTitle = new Region();
//                 spaceTitle.setPrefHeight(10);

//                 Region space1 = new Region();
//                 space1.setPrefHeight(5);

//                 Region space2 = new Region();
//                 space2.setPrefHeight(5);

//                 Region space3 = new Region();
//                 space3.setPrefHeight(5);

//                 Region space4 = new Region();
//                 space4.setPrefHeight(5);

//                 Region spaceButton = new Region();
//                 spaceButton.setPrefHeight(8);

//                 Region spaceOr = new Region();
//                 spaceOr.setPrefHeight(10);

//                 Region spaceLogin = new Region();
//                 spaceLogin.setPrefHeight(5);

//                 // =====================================================
//                 // ACCOUNT CONTENT
//                 // =====================================================

//                 accountBox.getChildren().addAll(

//                                 title,
//                                 subtitle,

//                                 spaceTitle,

//                                 fullNameLabel,
//                                 fullName,

//                                 space1,

//                                 emailLabel,
//                                 email,

//                                 space2,

//                                 passwordLabel,
//                                 password,

//                                 space3,

//                                 roleLabel,

//                                 space4,

//                                 roleCards,

//                                 spaceButton,

//                                 createAccount,

//                                 spaceOr,

//                                 orHBox,

//                                 spaceLogin,

//                                 loginHBox);

//                 // =====================================================
//                 // POSITION CARD
//                 // =====================================================

//                 rightArea.getChildren().add(
//                                 accountBox);

//                 StackPane.setAlignment(
//                                 rightArea,
//                                 Pos.CENTER_RIGHT);

//                 StackPane.setMargin(
//                                 rightArea,
//                                 new Insets(
//                                                 0,
//                                                 35,
//                                                 0,
//                                                 0));

//                 // =====================================================
//                 // ROOT
//                 // =====================================================

//                 root.getChildren().addAll(
//                                 backgroundView,
//                                 darkOverlay,
//                                 greenOverlay,
//                                 rightArea);

//                 StackPane.setAlignment(
//                                 backgroundView,
//                                 Pos.CENTER);

//                 StackPane.setAlignment(
//                                 darkOverlay,
//                                 Pos.CENTER);

//                 StackPane.setAlignment(
//                                 greenOverlay,
//                                 Pos.CENTER);

//                 // =====================================================
//                 // SCENE
//                 // =====================================================

//                 return new Scene(
//                                 root,
//                                 1365,
//                                 768);
//         }

// }
package com.pravartak.view.login;

import com.pravartak.controller.authentication_contr.AuthController;
import com.pravartak.dao.UserDAO;
import com.pravartak.model.UserModel;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Create_Profile {

    private String selectedRole = "";

    private final AuthController authController = new AuthController();
    private final UserDAO userDAO = new UserDAO();

    public Scene getCreateProfilePageScene(Runnable callbacktologin) {

        StackPane root = new StackPane();
        root.setPrefSize(1365, 768);

        // Background image
        URL imageURL = getClass().getResource("/create-profile-background.png");

        if (imageURL == null) {
            throw new RuntimeException(
                    "create-profile-background.png not found!\n"
                            + "Put it inside: src/main/resources/create-profile-background.png");
        }

        Image backgroundImage = new Image(imageURL.toExternalForm());
        ImageView backgroundView = new ImageView(backgroundImage);

        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        // Background overlays
        Region darkOverlay = new Region();
        darkOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.34);");
        darkOverlay.prefWidthProperty().bind(root.widthProperty());
        darkOverlay.prefHeightProperty().bind(root.heightProperty());

        Region greenOverlay = new Region();
        greenOverlay.setStyle("-fx-background-color: rgba(4,35,20,0.12);");
        greenOverlay.prefWidthProperty().bind(root.widthProperty());
        greenOverlay.prefHeightProperty().bind(root.heightProperty());

        StackPane rightArea = new StackPane();
        rightArea.setPrefWidth(600);
        rightArea.setMaxWidth(600);
        rightArea.setStyle("-fx-background-color: transparent;");

        // Registration card
        VBox accountBox = new VBox();
        accountBox.setPrefWidth(465);
        accountBox.setMinWidth(465);
        accountBox.setMaxWidth(465);
        accountBox.setPrefHeight(650);
        accountBox.setMinHeight(650);
        accountBox.setMaxHeight(650);
        accountBox.setPadding(new Insets(25, 35, 22, 35));
        accountBox.setSpacing(7);
        accountBox.setStyle(
                "-fx-background-color: rgba(5,18,12,0.96);"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-color: rgba(104,211,74,0.45);"
                        + "-fx-border-width: 1.2;"
                        + "-fx-border-radius: 20;"
                        + "-fx-effect: dropshadow("
                        + "gaussian, rgba(0,0,0,0.65), 28, 0, 0, 10);");

        // Title
        Label title = new Label("Create an Account");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 27));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "Create your Agro Biz account to get started.");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitle.setTextFill(Color.web("#aab8ae"));

        // Full name
        Label fullNameLabel = new Label("Full Name");
        fullNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        fullNameLabel.setTextFill(Color.WHITE);

        TextField fullName = new TextField();
        fullName.setPromptText("Enter your full name");
        fullName.setPrefHeight(47);
        fullName.setStyle(
                "-fx-background-color: rgba(255,255,255,0.035);"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #84948a;"
                        + "-fx-border-color: #385044;"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-font-size: 14px;"
                        + "-fx-padding: 0 15 0 15;");

        // Email
        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        emailLabel.setTextFill(Color.WHITE);

        TextField email = new TextField();
        email.setPromptText("Enter your email");
        email.setPrefHeight(47);
        email.setStyle(
                "-fx-background-color: rgba(255,255,255,0.035);"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #84948a;"
                        + "-fx-border-color: #385044;"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-font-size: 14px;"
                        + "-fx-padding: 0 15 0 15;");

        // Password
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        passwordLabel.setTextFill(Color.WHITE);

        PasswordField password = new PasswordField();
        password.setPromptText("Create a strong password");
        password.setPrefHeight(47);
        password.setStyle(
                "-fx-background-color: rgba(255,255,255,0.035);"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #84948a;"
                        + "-fx-border-color: #385044;"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-font-size: 14px;"
                        + "-fx-padding: 0 15 0 15;");

        // Role selection
        Label roleLabel = new Label("Choose your role");
        roleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        roleLabel.setTextFill(Color.WHITE);

        Label farmerIcon = new Label("🚜");
        farmerIcon.setStyle("-fx-font-size: 25px;");

        Label farmerText = new Label("Farmer / Learner");
        farmerText.setTextFill(Color.WHITE);
        farmerText.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox farmerCard = new VBox(5, farmerIcon, farmerText);
        farmerCard.setAlignment(Pos.CENTER);
        farmerCard.setPrefWidth(185);
        farmerCard.setPrefHeight(76);

        String normalCardStyle =
                "-fx-background-color: #101718;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-color: #344b39;"
                        + "-fx-border-width: 1.2;"
                        + "-fx-border-radius: 10;"
                        + "-fx-cursor: hand;";

        String selectedCardStyle =
                "-fx-background-color: #1f5c2b;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-color: #68d34a;"
                        + "-fx-border-width: 2;"
                        + "-fx-border-radius: 10;"
                        + "-fx-cursor: hand;";

        farmerCard.setStyle(normalCardStyle);

        Label buyerIcon = new Label("🛒");
        buyerIcon.setStyle("-fx-font-size: 25px;");

        Label buyerText = new Label("Buyer");
        buyerText.setTextFill(Color.WHITE);
        buyerText.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        VBox buyerCard = new VBox(5, buyerIcon, buyerText);
        buyerCard.setAlignment(Pos.CENTER);
        buyerCard.setPrefWidth(185);
        buyerCard.setPrefHeight(76);
        buyerCard.setStyle(normalCardStyle);

        farmerCard.setOnMouseClicked(e -> {
            selectedRole = "FARMER";
            farmerCard.setStyle(selectedCardStyle);
            buyerCard.setStyle(normalCardStyle);
        });

        buyerCard.setOnMouseClicked(e -> {
            selectedRole = "BUYER";
            buyerCard.setStyle(selectedCardStyle);
            farmerCard.setStyle(normalCardStyle);
        });

        HBox roleCards = new HBox(12, farmerCard, buyerCard);
        roleCards.setAlignment(Pos.CENTER);

        // Create account button
        Button createAccount = new Button("Create Account     →");
        createAccount.setPrefHeight(48);
        createAccount.setMaxWidth(Double.MAX_VALUE);
        createAccount.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        createAccount.setTextFill(Color.web("#07100a"));
        createAccount.setStyle(
                "-fx-background-color: #68d34a;"
                        + "-fx-background-radius: 28;"
                        + "-fx-cursor: hand;");

        createAccount.setOnMouseEntered(e -> createAccount.setStyle(
                "-fx-background-color: #7be85b;"
                        + "-fx-background-radius: 28;"
                        + "-fx-cursor: hand;"
                        + "-fx-effect: dropshadow("
                        + "gaussian, rgba(104,211,74,0.35),15,0,0,0);"));

        createAccount.setOnMouseExited(e -> createAccount.setStyle(
                "-fx-background-color: #68d34a;"
                        + "-fx-background-radius: 28;"
                        + "-fx-cursor: hand;"));

        // Registration logic
        createAccount.setOnAction(e -> {

            String name = fullName.getText().trim();
            String userEmail = email.getText().trim();
            String userPassword = password.getText();

            if (name.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }

            if (selectedRole.isEmpty()) {
                System.out.println("Please select Farmer or Buyer.");
                return;
            }

            if (userPassword.length() < 6) {
                System.out.println("Password must contain at least 6 characters.");
                return;
            }

            String uid = authController.signUp(userEmail, userPassword);

            if (uid == null) {
                System.out.println("Account creation failed.");
                return;
            }

            System.out.println("Firebase account created.");
            System.out.println("UID = " + uid);

            int farmerId = 0;

            if ("FARMER".equalsIgnoreCase(selectedRole)) {
                farmerId = (int) (System.currentTimeMillis()
                        % Integer.MAX_VALUE);

                System.out.println("Generated Farmer ID = " + farmerId);
            }

            UserModel user = new UserModel(
                    uid,
                    name,
                    userEmail,
                    selectedRole,
                    farmerId);

            boolean saved = userDAO.saveUser(user);

            if (!saved) {
                System.out.println(
                        "Account created but profile could not be saved.");
                return;
            }

            System.out.println("User profile saved.");
            System.out.println("Role = " + selectedRole);

            callbacktologin.run();
        });

        // OR divider
        Line line1 = new Line(0, 0, 80, 0);
        line1.setStroke(Color.web("#344b39"));

        Label orLabel = new Label("or");
        orLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        orLabel.setTextFill(Color.web("#8fa094"));

        Line line2 = new Line(0, 0, 80, 0);
        line2.setStroke(Color.web("#344b39"));

        HBox orHBox = new HBox(10, line1, orLabel, line2);
        orHBox.setAlignment(Pos.CENTER);

        // Login button
        Label loginText = new Label("Already have an account? ");
        loginText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        loginText.setTextFill(Color.web("#aab8ae"));

        Button login = new Button("Log in");
        login.setTextFill(Color.web("#68d34a"));
        login.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        login.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: transparent;"
                        + "-fx-cursor: hand;");

        login.setOnAction(e -> {
            System.out.println("login button clicked");
            callbacktologin.run();
        });

        HBox loginHBox = new HBox(loginText, login);
        loginHBox.setAlignment(Pos.CENTER);

        // Spacing
        Region spaceTitle = new Region();
        spaceTitle.setPrefHeight(10);

        Region space1 = new Region();
        space1.setPrefHeight(5);

        Region space2 = new Region();
        space2.setPrefHeight(5);

        Region space3 = new Region();
        space3.setPrefHeight(5);

        Region space4 = new Region();
        space4.setPrefHeight(5);

        Region spaceButton = new Region();
        spaceButton.setPrefHeight(8);

        Region spaceOr = new Region();
        spaceOr.setPrefHeight(10);

        Region spaceLogin = new Region();
        spaceLogin.setPrefHeight(5);

        accountBox.getChildren().addAll(
                title,
                subtitle,
                spaceTitle,
                fullNameLabel,
                fullName,
                space1,
                emailLabel,
                email,
                space2,
                passwordLabel,
                password,
                space3,
                roleLabel,
                space4,
                roleCards,
                spaceButton,
                createAccount,
                spaceOr,
                orHBox,
                spaceLogin,
                loginHBox);

        rightArea.getChildren().add(accountBox);

        StackPane.setAlignment(rightArea, Pos.CENTER_RIGHT);
        StackPane.setMargin(rightArea, new Insets(0, 35, 0, 0));

        root.getChildren().addAll(
                backgroundView,
                darkOverlay,
                greenOverlay,
                rightArea);

        StackPane.setAlignment(backgroundView, Pos.CENTER);
        StackPane.setAlignment(darkOverlay, Pos.CENTER);
        StackPane.setAlignment(greenOverlay, Pos.CENTER);

        return new Scene(root, 1365, 768);
    }
}