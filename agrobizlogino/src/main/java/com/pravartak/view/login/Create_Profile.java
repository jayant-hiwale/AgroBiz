// package com.pravartak.view.login;

// import com.pravartak.controller.authentication_contr.AuthController;
// import com.pravartak.dao.UserDAO;
// import com.pravartak.model.UserModel;
// import java.net.URL;
// import java.util.Random;

// import javafx.animation.Animation;
// import javafx.animation.FadeTransition;
// import javafx.animation.TranslateTransition;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.BackgroundImage;
// import javafx.scene.layout.BackgroundPosition;
// import javafx.scene.layout.BackgroundRepeat;
// import javafx.scene.layout.BackgroundSize;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.CycleMethod;
// import javafx.scene.paint.RadialGradient;
// import javafx.scene.paint.Stop;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Line;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.util.Duration;

// public class Create_Profile {

//         private String selectedRole = "";

//         private final AuthController authController =new AuthController();

//         private final UserDAO userDAO = new UserDAO();

//     public Scene getCreateProfilePageScene(Runnable callbacktologin) {

//         HBox mainHBox = new HBox();
//         mainHBox.setPrefSize(1365, 768);

//         // =====================================================
//         // LEFT SIDE
//         // =====================================================

//         VBox leftVBox = new VBox();
//         leftVBox.setPrefWidth(690);
//         leftVBox.setAlignment(Pos.BOTTOM_LEFT);
//         leftVBox.setPadding(new Insets(0, 45, 60, 45));
//         leftVBox.setSpacing(18);

//         URL imageURL = getClass().getResource("/image copy.png");

//         if (imageURL == null) {
//             throw new RuntimeException(
//                     "image copy.png not found!\n"
//                             + "Put it inside:\nsrc/main/resources/assets/image/image.png");
//         }

//         Image farmImage = new Image(imageURL.toExternalForm());

//         BackgroundImage backgroundImage = new BackgroundImage(
//                 farmImage,
//                 BackgroundRepeat.NO_REPEAT,
//                 BackgroundRepeat.NO_REPEAT,
//                 BackgroundPosition.CENTER,
//                 new BackgroundSize(100, 100, true, true, false, true));

//         leftVBox.setBackground(new Background(backgroundImage));

//         // Dark overlay effect
//         Region imageOverlay = new Region();
//         imageOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.30);");

//         StackPane leftStack = new StackPane();
//         leftStack.setPrefWidth(690);
//         leftStack.setMaxWidth(Double.MAX_VALUE);
//         leftStack.setAlignment(Pos.BOTTOM_LEFT);
//         leftStack.setBackground(new Background(backgroundImage));

//         Label agroBiz = new Label("♧  Agro Biz");
//         agroBiz.setTextFill(Color.WHITE);
//         agroBiz.setFont(Font.font("Arial", FontWeight.BOLD, 24));

//         Label heading = new Label("Empowering your\nfarming journey.");
//         heading.setTextFill(Color.WHITE);
//         heading.setFont(Font.font("Arial", FontWeight.BOLD, 48));

//         Label description = new Label(
//                 "Join the digital revolution in agriculture. Manage your\n"
//                         + "crops, connect with buyers, and leverage AI insights—all in\n"
//                         + "one place.");
//         description.setTextFill(Color.WHITE);
//         description.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
//         description.setWrapText(true);

//         VBox leftText = new VBox(18, agroBiz, heading, description);
//         leftText.setAlignment(Pos.BOTTOM_LEFT);
//         leftText.setPadding(new Insets(0, 45, 60, 45));

//         leftStack.getChildren().addAll(imageOverlay, leftText);

//         StackPane.setAlignment(imageOverlay, Pos.CENTER);
//         StackPane.setAlignment(leftText, Pos.BOTTOM_LEFT);

//         // =====================================================
//         // RIGHT SIDE
//         // =====================================================

//         StackPane rightVBox = new StackPane();
//         rightVBox.setPrefWidth(675);
//         rightVBox.setAlignment(Pos.CENTER);
//         rightVBox.setPadding(new Insets(35, 70, 35, 70));

//         rightVBox.setStyle(
//                 "-fx-background-color: linear-gradient(to bottom right,"
//                         + "#050908 0%, #08130d 50%, #0b1b12 100%);");

//         createAnimatedBackground(rightVBox);

//         // =====================================================
//         // ACCOUNT BOX
//         // =====================================================

//         VBox accountBox = new VBox();
//         accountBox.setPrefWidth(515);
//         accountBox.setMaxWidth(515);
//         accountBox.setPadding(new Insets(35, 40, 35, 40));
//         accountBox.setSpacing(12);
// //account set style
//         accountBox.setStyle(
//         "-fx-background-color: rgba(10,20,15,0.94);"
//         + "-fx-background-radius: 18;"
//         + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.55), 25, 0, 0, 8);"
// );

//         // =====================================================
//         // TITLE
//         // =====================================================

//         Label title = new Label("Create an Account");
//         title.setFont(Font.font("Arial", FontWeight.BOLD, 27));
//         title.setTextFill(Color.WHITE);

//         Label subtitle = new Label("Get started with Agro Biz today.");
//         subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
//         subtitle.setTextFill(Color.web("#aab8ae"));

//         // =====================================================
//         // FULL NAME
//         // =====================================================

//         Label fullNameLabel = new Label("Full Name");
//         fullNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//         fullNameLabel.setTextFill(Color.WHITE);

//         TextField fullName = new TextField();
//         fullName.setPromptText("Enter your full name");
//         fullName.setPrefHeight(51);
//         fullName.setStyle(
//                 "-fx-background-color: #f7f9f7;"
//                         + "-fx-border-color: #45604b;"
//                         + "-fx-border-width: 1.5;"
//                         + "-fx-border-radius: 9;"
//                         + "-fx-background-radius: 9;"
//                         + "-fx-font-size: 16;"
//                         + "-fx-padding: 0 15 0 15;");

//         // =====================================================
//         // EMAIL
//         // =====================================================

//         Label emailLabel = new Label("Email");
//         emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//         emailLabel.setTextFill(Color.WHITE);

//         TextField email = new TextField();
//         email.setPromptText("Enter your email");
//         email.setPrefHeight(51);
//         email.setStyle(
//                 "-fx-background-color: #f7f9f7;"
//                         + "-fx-border-color: #45604b;"
//                         + "-fx-border-width: 1.5;"
//                         + "-fx-border-radius: 9;"
//                         + "-fx-background-radius: 9;"
//                         + "-fx-font-size: 16;"
//                         + "-fx-padding: 0 15 0 15;");

//         // =====================================================
//         // PASSWORD
//         // =====================================================

//         Label passwordLabel = new Label("Password");
//         passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//         passwordLabel.setTextFill(Color.WHITE);

//         PasswordField password = new PasswordField();
//         password.setPromptText("Create a strong password");
//         password.setPrefHeight(51);
//         password.setStyle(
//                 "-fx-background-color: #f7f9f7;"
//                         + "-fx-border-color: #45604b;"
//                         + "-fx-border-width: 1.5;"
//                         + "-fx-border-radius: 9;"
//                         + "-fx-background-radius: 9;"
//                         + "-fx-font-size: 16;"
//                         + "-fx-padding: 0 15 0 15;");


//         // =====================================================
//         // ROLE SELECTION
//         // =====================================================

//         Label roleLabel = new Label("Choose your role");
//         roleLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         15));

//         roleLabel.setTextFill(Color.WHITE);


//         // =====================================================
//         // FARMER CARD
//         // =====================================================

//         Label farmerIcon = new Label("🚜");

//         farmerIcon.setStyle(
//                 "-fx-font-size: 28px;");

//         Label farmerText =
//                 new Label("Farmer / Learner");

//         farmerText.setTextFill(Color.WHITE);

//         farmerText.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         13));

//         VBox farmerCard =
//                 new VBox(
//                         8,
//                         farmerIcon,
//                         farmerText);

//         farmerCard.setAlignment(Pos.CENTER);

//         farmerCard.setPrefWidth(210);
//         farmerCard.setPrefHeight(90);

//         farmerCard.setStyle(
//                 "-fx-background-color: #101718;"
//                 + "-fx-background-radius: 10;"
//                 + "-fx-border-color: #344b39;"
//                 + "-fx-border-width: 1.5;"
//                 + "-fx-border-radius: 10;"
//                 + "-fx-cursor: hand;");


//         // =====================================================
//         // BUYER CARD
//         // =====================================================

//         Label buyerIcon =
//                 new Label("🛒");

//         buyerIcon.setStyle(
//                 "-fx-font-size: 28px;");

//         Label buyerText =
//                 new Label("Buyer");

//         buyerText.setTextFill(Color.WHITE);

//         buyerText.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         13));

//         VBox buyerCard =
//                 new VBox(
//                         8,
//                         buyerIcon,
//                         buyerText);

//         buyerCard.setAlignment(Pos.CENTER);

//         buyerCard.setPrefWidth(210);
//         buyerCard.setPrefHeight(90);

//         buyerCard.setStyle(
//                 "-fx-background-color: #101718;"
//                 + "-fx-background-radius: 10;"
//                 + "-fx-border-color: #344b39;"
//                 + "-fx-border-width: 1.5;"
//                 + "-fx-border-radius: 10;"
//                 + "-fx-cursor: hand;");


//         // =====================================================
//         // FARMER CLICK
//         // =====================================================

//         farmerCard.setOnMouseClicked(e -> {

//         selectedRole = "FARMER";

//         farmerCard.setStyle(
//                 "-fx-background-color: #1f5c2b;"
//                 + "-fx-background-radius: 10;"
//                 + "-fx-border-color: #68d34a;"
//                 + "-fx-border-width: 2;"
//                 + "-fx-border-radius: 10;"
//                 + "-fx-cursor: hand;");

//         buyerCard.setStyle(
//                 "-fx-background-color: #101718;"
//                 + "-fx-background-radius: 10;"
//                 + "-fx-border-color: #344b39;"
//                 + "-fx-border-width: 1.5;"
//                 + "-fx-border-radius: 10;"
//                 + "-fx-cursor: hand;");
//         });


//         // =====================================================
//         // BUYER CLICK
//         // =====================================================

//         buyerCard.setOnMouseClicked(e -> {

//         selectedRole = "BUYER";

//         buyerCard.setStyle(
//                 "-fx-background-color: #1f5c2b;"
//                 + "-fx-background-radius: 10;"
//                 + "-fx-border-color: #68d34a;"
//                 + "-fx-border-width: 2;"
//                 + "-fx-border-radius: 10;"
//                 + "-fx-cursor: hand;");

//         farmerCard.setStyle(
//                 "-fx-background-color: #101718;"
//                 + "-fx-background-radius: 10;"
//                 + "-fx-border-color: #344b39;"
//                 + "-fx-border-width: 1.5;"
//                 + "-fx-border-radius: 10;"
//                 + "-fx-cursor: hand;");
//         });


//         HBox roleCards =
//                 new HBox(
//                         15,
//                         farmerCard,
//                         buyerCard);

//         roleCards.setAlignment(Pos.CENTER);

//         // =====================================================
//         // CREATE ACCOUNT BUTTON
//         // =====================================================

//         Button createAccount = new Button("Create Account     →");

//         createAccount.setPrefHeight(52);
//         createAccount.setMaxWidth(Double.MAX_VALUE);
//         createAccount.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         createAccount.setTextFill(Color.web("#07100a"));
//         createAccount.setStyle(
//                 "-fx-background-color: #258934;"
//                         + "-fx-background-radius: 28;"
//                         + "-fx-cursor: hand;");

//         createAccount.setOnMouseEntered(e -> {
//             createAccount.setStyle(
//                     "-fx-background-color: #7be85b;"
//                             + "-fx-background-radius: 28;"
//                             + "-fx-cursor: hand;"
//                             + "-fx-effect: dropshadow(gaussian, rgba(104,211,74,0.35), 15, 0, 0, 0);");
//         });

//         createAccount.setOnMouseExited(e -> {
//             createAccount.setStyle(
//                     "-fx-background-color: #68d34a;"
//                             + "-fx-background-radius: 28;"
//                             + "-fx-cursor: hand;");
//         });

//         createAccount.setOnAction(e -> {

//         String name =
//                 fullName.getText().trim();

//         String userEmail =
//                 email.getText().trim();

//         String userPassword =
//                 password.getText();

//         // ==========================================
//         // VALIDATION
//         // ==========================================

//         if (name.isEmpty()
//                 || userEmail.isEmpty()
//                 || userPassword.isEmpty()) {

//                 System.out.println(
//                         "Please fill all fields.");

//                 return;
//         }

//         if (selectedRole.isEmpty()) {

//                 System.out.println(
//                         "Please select Farmer or Buyer.");

//                 return;
//         }

//         if (userPassword.length() < 6) {

//                 System.out.println(
//                         "Password must contain at least 6 characters.");

//                 return;
//         }

//         // ==========================================
//         // FIREBASE AUTHENTICATION
//         // ==========================================

//         String uid =
//                 authController.signUp(
//                         userEmail,
//                         userPassword);

//         if (uid == null) {

//                 System.out.println(
//                         "Account creation failed.");

//                 return;
//         }

//         System.out.println(
//                 "Firebase account created.");

//         System.out.println(
//                 "UID = " + uid);

//         // ==========================================
//         // CREATE USER MODEL
//         // ==========================================

//         // UserModel user =
//         //         new UserModel(
//         //                 uid,
//         //                 name,
//         //                 userEmail,
//         //                 selectedRole);
//         // ==========================================
// // CREATE USER MODEL
// // ==========================================

// int farmerId = 0;

// if ("FARMER".equalsIgnoreCase(selectedRole)) {

//     farmerId =
//             (int) (
//                     System.currentTimeMillis()
//                     % Integer.MAX_VALUE
//             );

//     System.out.println(
//             "Generated Farmer ID = "
//             + farmerId
//     );
// }

// UserModel user =
//         new UserModel(
//                 uid,
//                 name,
//                 userEmail,
//                 selectedRole,
//                 farmerId
//         );

//         // ==========================================
//         // SAVE USER TO FIRESTORE
//         // ==========================================

//         boolean saved =
//                 userDAO.saveUser(user);

//         if (!saved) {

//                 System.out.println(
//                         "Account created but profile could not be saved.");

//                 return;
//         }

//         System.out.println(
//                 "User profile saved.");

//         System.out.println(
//                 "Role = " + selectedRole);

//         // ==========================================
//         // GO TO LOGIN
//         // ==========================================

//         callbacktologin.run();
//         });

//         // =====================================================
//         // OR CONTINUE
//         // =====================================================

//         Line line1 = new Line(0, 0, 115, 0);
//         line1.setStroke(Color.web("#344b39"));

//         Label orLabel = new Label("or continue with");
//         orLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//         orLabel.setTextFill(Color.web("#8fa094"));

//         Line line2 = new Line(0, 0, 115, 0);
//         line2.setStroke(Color.web("#344b39"));

//         HBox orHBox = new HBox(14, line1, orLabel, line2);
//         orHBox.setAlignment(Pos.CENTER);

//         // =====================================================
//         // GOOGLE BUTTON
//         // =====================================================

//         // Button googleButton = new Button("G   Google");
//         // googleButton.setPrefHeight(51);
//         // googleButton.setMaxWidth(Double.MAX_VALUE);
//         // googleButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//         // googleButton.setTextFill(Color.WHITE);

//         // googleButton.setStyle(
//         //         "-fx-background-color: #101718;"
//         //                 + "-fx-border-color: #344b39;"
//         //                 + "-fx-border-width: 1.5;"
//         //                 + "-fx-border-radius: 28;"
//         //                 + "-fx-background-radius: 28;"
//         //                 + "-fx-cursor: hand;");

//         // googleButton.setOnMouseEntered(e -> {
//         //     googleButton.setStyle(
//         //             "-fx-background-color: #18241b;"
//         //                     + "-fx-border-color: #68d34a;"
//         //                     + "-fx-border-width: 1.5;"
//         //                     + "-fx-border-radius: 28;"
//         //                     + "-fx-background-radius: 28;"
//         //                     + "-fx-cursor: hand;");
//         // });

//         // googleButton.setOnMouseExited(e -> {
//         //     googleButton.setStyle(
//         //             "-fx-background-color: #101718;"
//         //                     + "-fx-border-color: #344b39;"
//         //                     + "-fx-border-width: 1.5;"
//         //                     + "-fx-border-radius: 28;"
//         //                     + "-fx-background-radius: 28;"
//         //                     + "-fx-cursor: hand;");
//         // });

//         // =====================================================
//         // LOGIN
//         // =====================================================

//         Label loginText = new Label("Already have an account? ");
//         loginText.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
//         loginText.setTextFill(Color.web("#aab8ae"));

//         Button login = new Button("Log in");
//         login.setTextFill(Color.web("#68d34a"));
//         login.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//         login.setStyle(
//                 "-fx-background-color: transparent;"
//                         + "-fx-border-color: transparent;"
//                         + "-fx-cursor: hand;");

//         login.setOnAction(e -> {
//             System.out.println("login button clicked");
//             callbacktologin.run();
//         });

//         HBox loginHBox = new HBox(loginText, login);
//         loginHBox.setAlignment(Pos.CENTER);

//         // =====================================================
//         // SPACING
//         // =====================================================

//         Region space18a = new Region();
//         space18a.setPrefHeight(18);

//         Region space18b = new Region();
//         space18b.setPrefHeight(18);

//         Region space8a = new Region();
//         space8a.setPrefHeight(8);

//         Region space8b = new Region();
//         space8b.setPrefHeight(8);

//         Region space8c = new Region();
//         space8c.setPrefHeight(8);

//         Region space8e = new Region();
//         space8e.setPrefHeight(8);

//         Region space8d = new Region();
//         space8d.setPrefHeight(8);

//         // =====================================================
//         // ACCOUNT CONTENT
//         // =====================================================

//         accountBox.getChildren().addAll(
//         title,
//         subtitle,
//         space18a,

//         fullNameLabel,
//         fullName,

//         space8a,

//         emailLabel,
//         email,

//         space8b,

//         passwordLabel,
//         password,

//         space8c,

//         roleLabel,
//         roleCards,

//         space8e,

//         createAccount,

//         space18b,

//         orHBox,
//         //googleButton,

//         space8d,

//         loginHBox
//         );

//         rightVBox.getChildren().add(accountBox);
//         StackPane.setAlignment(accountBox, Pos.CENTER);

//         // =====================================================
//         // MAIN LAYOUT
//         // =====================================================

//         mainHBox.getChildren().addAll(leftStack, rightVBox);

//         HBox.setHgrow(leftStack, Priority.ALWAYS);
//         HBox.setHgrow(rightVBox, Priority.ALWAYS);

//         Scene scene = new Scene(mainHBox, 1100, 768);

//         return scene;
//     }

//     // =========================================================
//     // ANIMATED BACKGROUND
//     // =========================================================

//     private void createAnimatedBackground(StackPane pane) {

//         Circle glow1 = new Circle(200);
//         glow1.setFill(new RadialGradient(
//                 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
//                 new Stop(0, Color.rgb(104, 211, 74, 0.12)),
//                 new Stop(1, Color.TRANSPARENT)));
//         glow1.setMouseTransparent(true);

//         StackPane.setAlignment(glow1, Pos.TOP_RIGHT);
//         StackPane.setMargin(glow1, new Insets(-90, -90, 0, 0));

//         Circle glow2 = new Circle(160);
//         glow2.setFill(new RadialGradient(
//                 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
//                 new Stop(0, Color.rgb(45, 140, 70, 0.11)),
//                 new Stop(1, Color.TRANSPARENT)));
//         glow2.setMouseTransparent(true);

//         StackPane.setAlignment(glow2, Pos.BOTTOM_LEFT);
//         StackPane.setMargin(glow2, new Insets(0, 0, -70, -70));

//         Circle glow3 = new Circle(130);
//         glow3.setFill(new RadialGradient(
//                 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
//                 new Stop(0, Color.rgb(104, 211, 74, 0.07)),
//                 new Stop(1, Color.TRANSPARENT)));
//         glow3.setMouseTransparent(true);

//         StackPane.setAlignment(glow3, Pos.CENTER_RIGHT);
//         StackPane.setMargin(glow3, new Insets(0, -70, 0, 0));

//         TranslateTransition move1 = new TranslateTransition(Duration.seconds(9), glow1);
//         move1.setToX(-90);
//         move1.setToY(70);
//         move1.setAutoReverse(true);
//         move1.setCycleCount(Animation.INDEFINITE);
//         move1.play();

//         TranslateTransition move2 = new TranslateTransition(Duration.seconds(11), glow2);
//         move2.setToX(80);
//         move2.setToY(-60);
//         move2.setAutoReverse(true);
//         move2.setCycleCount(Animation.INDEFINITE);
//         move2.play();

//         TranslateTransition move3 = new TranslateTransition(Duration.seconds(8), glow3);
//         move3.setToX(-60);
//         move3.setToY(60);
//         move3.setAutoReverse(true);
//         move3.setCycleCount(Animation.INDEFINITE);
//         move3.play();

//         pane.getChildren().addAll(glow1, glow2, glow3);

//         Random random = new Random();

//         for (int i = 0; i < 15; i++) {

//             Circle particle = new Circle(1.5 + random.nextDouble() * 2);

//             particle.setFill(Color.rgb(
//                     104,
//                     211,
//                     74,
//                     0.15 + random.nextDouble() * 0.25));

//             particle.setMouseTransparent(true);

//             particle.setTranslateX(
//                     random.nextDouble() * 550 - 275);

//             particle.setTranslateY(
//                     random.nextDouble() * 700 - 350);

//             pane.getChildren().add(particle);

//             TranslateTransition move = new TranslateTransition(
//                     Duration.seconds(5 + random.nextDouble() * 6),
//                     particle);

//             move.setByX(-35 + random.nextDouble() * 70);
//             move.setByY(-50 - random.nextDouble() * 80);
//             move.setAutoReverse(true);
//             move.setCycleCount(Animation.INDEFINITE);
//             move.play();

//             FadeTransition fade = new FadeTransition(
//                     Duration.seconds(2.5 + random.nextDouble() * 3),
//                     particle);

//             fade.setFromValue(0.15);
//             fade.setToValue(0.7);
//             fade.setAutoReverse(true);
//             fade.setCycleCount(Animation.INDEFINITE);
//             fade.play();
//         }
//     }
// }
package com.pravartak.view.login;

import com.pravartak.controller.authentication_contr.AuthController;
import com.pravartak.dao.UserDAO;
import com.pravartak.model.UserModel;
import java.net.URL;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Create_Profile {

        private String selectedRole = "";

        private final AuthController authController =new AuthController();

        private final UserDAO userDAO = new UserDAO();

    public Scene getCreateProfilePageScene(Runnable callbacktologin) {

        // =====================================================
        // FULL SCREEN ROOT
        // =====================================================

        StackPane root = new StackPane();

        root.setPrefSize(1365, 768);

        // =====================================================
        // BACKGROUND IMAGE
        // =====================================================

        URL imageURL =
                getClass().getResource("/create-profile-background.png");

        if (imageURL == null) {
            throw new RuntimeException(
                    "create-profile-background.png not found!\n"
                    + "Put it inside: src/main/resources/create-profile-background.png"
            );
        }

        Image backgroundImage =
                new Image(imageURL.toExternalForm());

        javafx.scene.image.ImageView backgroundView =
                new javafx.scene.image.ImageView(backgroundImage);

        backgroundView.setPreserveRatio(false);

        backgroundView.fitWidthProperty()
                .bind(root.widthProperty());

        backgroundView.fitHeightProperty()
                .bind(root.heightProperty());

        // =====================================================
        // DARK OVERLAY
        // =====================================================

        Region darkOverlay = new Region();

        darkOverlay.setStyle(
                "-fx-background-color: rgba(0,0,0,0.34);"
        );

        darkOverlay.prefWidthProperty()
                .bind(root.widthProperty());

        darkOverlay.prefHeightProperty()
                .bind(root.heightProperty());

        // =====================================================
        // SUBTLE GREEN OVERLAY
        // =====================================================

        Region greenOverlay = new Region();

        greenOverlay.setStyle(
                "-fx-background-color: rgba(4,35,20,0.12);"
        );

        greenOverlay.prefWidthProperty()
                .bind(root.widthProperty());

        greenOverlay.prefHeightProperty()
                .bind(root.heightProperty());

        // =====================================================
        // RIGHT SIDE DARK AREA
        // =====================================================

        StackPane rightArea = new StackPane();

        rightArea.setPrefWidth(600);

        rightArea.setMaxWidth(600);

        rightArea.setStyle(
                "-fx-background-color: transparent);"
        );

        //createAnimatedBackground(rightArea);

        // =====================================================
        // ACCOUNT BOX - COMPACT
        // =====================================================

        VBox accountBox = new VBox();

        accountBox.setPrefWidth(465);
        accountBox.setMinWidth(465);
        accountBox.setMaxWidth(465);

        accountBox.setPrefHeight(650);
        accountBox.setMinHeight(650);
        accountBox.setMaxHeight(650);

        accountBox.setPadding(
                new Insets(25, 35, 22, 35)
        );

        accountBox.setSpacing(7);

        accountBox.setStyle(
                "-fx-background-color: rgba(5,18,12,0.96);"
                + "-fx-background-radius: 20;"
                + "-fx-border-color: rgba(104,211,74,0.45);"
                + "-fx-border-width: 1.2;"
                + "-fx-border-radius: 20;"
                + "-fx-effect: dropshadow("
                + "gaussian, rgba(0,0,0,0.65), 28, 0, 0, 10);"
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label("Create an Account");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        title.setTextFill(Color.WHITE);

        Label subtitle =
                new Label(
                        "Create your Agro Biz account to get started."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#aab8ae")
        );

        // =====================================================
        // FULL NAME
        // =====================================================

        Label fullNameLabel =
                new Label("Full Name");

        fullNameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        fullNameLabel.setTextFill(Color.WHITE);

        TextField fullName =
                new TextField();

        fullName.setPromptText(
                "Enter your full name"
        );

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
                + "-fx-padding: 0 15 0 15;"
        );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                new Label("Email");

        emailLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        emailLabel.setTextFill(Color.WHITE);

        TextField email =
                new TextField();

        email.setPromptText(
                "Enter your email"
        );

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
                + "-fx-padding: 0 15 0 15;"
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        passwordLabel.setTextFill(Color.WHITE);

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Create a strong password"
        );

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
                + "-fx-padding: 0 15 0 15;"
        );

        // =====================================================
        // ROLE SELECTION
        // =====================================================

        Label roleLabel =
                new Label("Choose your role");

        roleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        roleLabel.setTextFill(Color.WHITE);

        // =====================================================
        // FARMER CARD
        // =====================================================

        Label farmerIcon =
                new Label("🚜");

        farmerIcon.setStyle(
                "-fx-font-size: 25px;"
        );

        Label farmerText =
                new Label("Farmer / Learner");

        farmerText.setTextFill(Color.WHITE);

        farmerText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        VBox farmerCard =
                new VBox(
                        5,
                        farmerIcon,
                        farmerText
                );

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

        // =====================================================
        // BUYER CARD
        // =====================================================

        Label buyerIcon =
                new Label("🛒");

        buyerIcon.setStyle(
                "-fx-font-size: 25px;"
        );

        Label buyerText =
                new Label("Buyer");

        buyerText.setTextFill(Color.WHITE);

        buyerText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        VBox buyerCard =
                new VBox(
                        5,
                        buyerIcon,
                        buyerText
                );

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

        HBox roleCards =
                new HBox(
                        12,
                        farmerCard,
                        buyerCard
                );

        roleCards.setAlignment(Pos.CENTER);

        // =====================================================
        // CREATE ACCOUNT BUTTON
        // =====================================================

        Button createAccount =
                new Button("Create Account     →");

        createAccount.setPrefHeight(48);

        createAccount.setMaxWidth(
                Double.MAX_VALUE
        );

        createAccount.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        createAccount.setTextFill(
                Color.web("#07100a")
        );

        createAccount.setStyle(
                "-fx-background-color: #68d34a;"
                + "-fx-background-radius: 28;"
                + "-fx-cursor: hand;"
        );

        createAccount.setOnMouseEntered(e ->
                createAccount.setStyle(
                        "-fx-background-color: #7be85b;"
                        + "-fx-background-radius: 28;"
                        + "-fx-cursor: hand;"
                        + "-fx-effect: dropshadow("
                        + "gaussian, rgba(104,211,74,0.35),"
                        + "15,0,0,0);"
                )
        );

        createAccount.setOnMouseExited(e ->
                createAccount.setStyle(
                        "-fx-background-color: #68d34a;"
                        + "-fx-background-radius: 28;"
                        + "-fx-cursor: hand;"
                )
        );

        // =====================================================
        // CREATE ACCOUNT LOGIC - SAME AS YOUR CODE
        // =====================================================

        createAccount.setOnAction(e -> {

            String name =
                    fullName.getText().trim();

            String userEmail =
                    email.getText().trim();

            String userPassword =
                    password.getText();

            if (name.isEmpty()
                    || userEmail.isEmpty()
                    || userPassword.isEmpty()) {

                System.out.println(
                        "Please fill all fields."
                );

                return;
            }

            if (selectedRole.isEmpty()) {

                System.out.println(
                        "Please select Farmer or Buyer."
                );

                return;
            }

            if (userPassword.length() < 6) {

                System.out.println(
                        "Password must contain at least 6 characters."
                );

                return;
            }

            String uid =
                    authController.signUp(
                            userEmail,
                            userPassword
                    );

            if (uid == null) {

                System.out.println(
                        "Account creation failed."
                );

                return;
            }

            System.out.println(
                    "Firebase account created."
            );

            System.out.println(
                    "UID = " + uid
            );

            int farmerId = 0;

            if ("FARMER".equalsIgnoreCase(
                    selectedRole)) {

                farmerId =
                        (int) (
                                System.currentTimeMillis()
                                % Integer.MAX_VALUE
                        );

                System.out.println(
                        "Generated Farmer ID = "
                                + farmerId
                );
            }

            UserModel user =
                    new UserModel(
                            uid,
                            name,
                            userEmail,
                            selectedRole,
                            farmerId
                    );

            boolean saved =
                    userDAO.saveUser(user);

            if (!saved) {

                System.out.println(
                        "Account created but profile could not be saved."
                );

                return;
            }

            System.out.println(
                    "User profile saved."
            );

            System.out.println(
                    "Role = " + selectedRole
            );

            callbacktologin.run();
        });

        // =====================================================
        // OR DIVIDER
        // =====================================================

        Line line1 =
                new Line(0, 0, 80, 0);

        line1.setStroke(
                Color.web("#344b39")
        );

        Label orLabel =
                new Label("or");

        orLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        12
                )
        );

        orLabel.setTextFill(
                Color.web("#8fa094")
        );

        Line line2 =
                new Line(0, 0, 80, 0);

        line2.setStroke(
                Color.web("#344b39")
        );

        HBox orHBox =
                new HBox(
                        10,
                        line1,
                        orLabel,
                        line2
                );

        orHBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // LOGIN
        // =====================================================

        Label loginText =
                new Label(
                        "Already have an account? "
                );

        loginText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        loginText.setTextFill(
                Color.web("#aab8ae")
        );

        Button login =
                new Button("Log in");

        login.setTextFill(
                Color.web("#68d34a")
        );

        login.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        login.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-border-color: transparent;"
                + "-fx-cursor: hand;"
        );

        login.setOnAction(e -> {

            System.out.println(
                    "login button clicked"
            );

            callbacktologin.run();
        });

        HBox loginHBox =
                new HBox(
                        loginText,
                        login
                );

        loginHBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // SPACING
        // =====================================================

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

        // =====================================================
        // ACCOUNT CONTENT
        // =====================================================

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

                loginHBox
        );

        // =====================================================
        // POSITION CARD
        // =====================================================

        rightArea.getChildren().add(
                accountBox
        );

        StackPane.setAlignment(
                rightArea,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                rightArea,
                new Insets(
                        0,
                        35,
                        0,
                        0
                )
        );

        // =====================================================
        // ROOT
        // =====================================================

        root.getChildren().addAll(
                backgroundView,
                darkOverlay,
                greenOverlay,
                rightArea
        );

        StackPane.setAlignment(
                backgroundView,
                Pos.CENTER
        );

        StackPane.setAlignment(
                darkOverlay,
                Pos.CENTER
        );

        StackPane.setAlignment(
                greenOverlay,
                Pos.CENTER
        );

        // =====================================================
        // SCENE
        // =====================================================

        return new Scene(
                root,
                1365,
                768
        );
    }

//     // =========================================================
//     // ANIMATED BACKGROUND
//     // =========================================================

//     private void createAnimatedBackground(StackPane pane) {

//         Circle glow1 = new Circle(200);
//         glow1.setFill(new RadialGradient(
//                 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
//                 new Stop(0, Color.rgb(104, 211, 74, 0.12)),
//                 new Stop(1, Color.TRANSPARENT)));
//         glow1.setMouseTransparent(true);

//         StackPane.setAlignment(glow1, Pos.TOP_RIGHT);
//         StackPane.setMargin(glow1, new Insets(-90, -90, 0, 0));

//         Circle glow2 = new Circle(160);
//         glow2.setFill(new RadialGradient(
//                 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
//                 new Stop(0, Color.rgb(45, 140, 70, 0.11)),
//                 new Stop(1, Color.TRANSPARENT)));
//         glow2.setMouseTransparent(true);

//         StackPane.setAlignment(glow2, Pos.BOTTOM_LEFT);
//         StackPane.setMargin(glow2, new Insets(0, 0, -70, -70));

//         Circle glow3 = new Circle(130);
//         glow3.setFill(new RadialGradient(
//                 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
//                 new Stop(0, Color.rgb(104, 211, 74, 0.07)),
//                 new Stop(1, Color.TRANSPARENT)));
//         glow3.setMouseTransparent(true);

//         StackPane.setAlignment(glow3, Pos.CENTER_RIGHT);
//         StackPane.setMargin(glow3, new Insets(0, -70, 0, 0));

//         TranslateTransition move1 = new TranslateTransition(Duration.seconds(9), glow1);
//         move1.setToX(-90);
//         move1.setToY(70);
//         move1.setAutoReverse(true);
//         move1.setCycleCount(Animation.INDEFINITE);
//         move1.play();

//         TranslateTransition move2 = new TranslateTransition(Duration.seconds(11), glow2);
//         move2.setToX(80);
//         move2.setToY(-60);
//         move2.setAutoReverse(true);
//         move2.setCycleCount(Animation.INDEFINITE);
//         move2.play();

//         TranslateTransition move3 = new TranslateTransition(Duration.seconds(8), glow3);
//         move3.setToX(-60);
//         move3.setToY(60);
//         move3.setAutoReverse(true);
//         move3.setCycleCount(Animation.INDEFINITE);
//         move3.play();

//         pane.getChildren().addAll(glow1, glow2, glow3);

//         Random random = new Random();

//         for (int i = 0; i < 15; i++) {

//             Circle particle = new Circle(1.5 + random.nextDouble() * 2);

//             particle.setFill(Color.rgb(
//                     104,
//                     211,
//                     74,
//                     0.15 + random.nextDouble() * 0.25));

//             particle.setMouseTransparent(true);

//             particle.setTranslateX(
//                     random.nextDouble() * 550 - 275);

//             particle.setTranslateY(
//                     random.nextDouble() * 700 - 350);

//             pane.getChildren().add(particle);

//             TranslateTransition move = new TranslateTransition(
//                     Duration.seconds(5 + random.nextDouble() * 6),
//                     particle);

//             move.setByX(-35 + random.nextDouble() * 70);
//             move.setByY(-50 - random.nextDouble() * 80);
//             move.setAutoReverse(true);
//             move.setCycleCount(Animation.INDEFINITE);
//             move.play();

//             FadeTransition fade = new FadeTransition(
//                     Duration.seconds(2.5 + random.nextDouble() * 3),
//                     particle);

//             fade.setFromValue(0.15);
//             fade.setToValue(0.7);
//             fade.setAutoReverse(true);
//             fade.setCycleCount(Animation.INDEFINITE);
//             fade.play();
//         }
//     }
}