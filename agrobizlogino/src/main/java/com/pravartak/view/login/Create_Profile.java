
package com.pravartak.view.login;

import java.net.URL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Create_Profile {

    //private Scene createprofilScene;
    public Scene getCreateProfilePageScene(Runnable callbacktologin) {

        // MAIN HBOX
        // Left VBox + Right VBox
        HBox mainHBox = new HBox();
        mainHBox.setPrefSize(1365, 768);

        // LEFT VBOX
        // IMAGE AS BACKGROUND + TEXT
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(690);
        leftVBox.setAlignment(Pos.BOTTOM_LEFT);
        leftVBox.setPadding(new Insets(0, 45, 60, 45));
        leftVBox.setSpacing(18);

        // LEFT SIDE BACKGROUND IMAGE
        URL imageURL = getClass().getResource("/image copy.png");

        if (imageURL == null) {
            throw new RuntimeException("image copy.png not found!\n" + "Put it inside:\n" + "src/main/resources/assets/image/image.png");
        }

        Image farmImage = new Image(imageURL.toExternalForm());

        BackgroundImage backgroundImage = new BackgroundImage(farmImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));

        leftVBox.setBackground(new Background(backgroundImage));

        // AGRO BIZ
        Label agroBiz = new Label("♧  Agro Biz");
        agroBiz.setTextFill(Color.WHITE);
        agroBiz.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // MAIN HEADING
        Label heading = new Label("Empowering your\nfarming journey.");
        heading.setTextFill(Color.WHITE);
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 48));

        // DESCRIPTION
        Label description = new Label("Join the digital revolution in agriculture. Manage your\n" + "crops, connect with buyers, and leverage AI insights—all in\n" + "one place.");
        description.setTextFill(Color.WHITE);
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        // LEFT VBOX
        // ONLY TEXT COMPONENTS
        leftVBox.getChildren().addAll(agroBiz, heading, description);

        // RIGHT VBOX
        // Labels + TextFields + Buttons
        VBox rightVBox = new VBox();
        rightVBox.setPrefWidth(675);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setPadding(new Insets(40, 80, 40, 80));
        rightVBox.setSpacing(15);
        rightVBox.setStyle("-fx-background-color: #fffde9;");

        // WHITE ACCOUNT CARD
        VBox accountBox = new VBox();
        accountBox.setPrefWidth(515);
        accountBox.setMaxWidth(515);
        accountBox.setPadding(new Insets(42, 44, 42, 44));
        accountBox.setSpacing(12);
        accountBox.setStyle("-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 20, 0, 0, 5);");

        // TITLE
        Label title = new Label("Create an Account");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 27));
        title.setTextFill(Color.web("#171717"));

        // SUBTITLE
        Label subtitle = new Label("Get started with Agro Biz today.");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 17));
        subtitle.setTextFill(Color.web("#3d433a"));

        // FULL NAME LABEL
        Label fullNameLabel = new Label("Full Name");
        fullNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        fullNameLabel.setTextFill(Color.web("#283027"));

        // FULL NAME TEXT FIELD
        TextField fullName = new TextField();
        fullName.setPromptText("Enter your full name");
        fullName.setPrefHeight(51);
        fullName.setStyle("-fx-background-color: white;" + "-fx-border-color: #bec8b8;" + "-fx-border-width: 2;" + "-fx-border-radius: 9;" + "-fx-background-radius: 9;" + "-fx-font-size: 17;" + "-fx-padding: 0 15 0 15;");

        // EMAIL LABEL
        Label emailLabel = new Label("Email or Phone Number");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        emailLabel.setTextFill(Color.web("#283027"));

        // EMAIL TEXT FIELD
        TextField email = new TextField();
        email.setPromptText("Enter email or phone");
        email.setPrefHeight(51);
        email.setStyle("-fx-background-color: white;" + "-fx-border-color: #bec8b8;" + "-fx-border-width: 2;" + "-fx-border-radius: 9;" + "-fx-background-radius: 9;" + "-fx-font-size: 17;" + "-fx-padding: 0 15 0 15;");

        // PASSWORD LABEL
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        passwordLabel.setTextFill(Color.web("#283027"));

        // PASSWORD FIELD
        PasswordField password = new PasswordField();
        password.setPromptText("Create a strong password");
        password.setPrefHeight(51);
        password.setStyle("-fx-background-color: white;" + "-fx-border-color: #bec8b8;" + "-fx-border-width: 2;" + "-fx-border-radius: 9;" + "-fx-background-radius: 9;" + "-fx-font-size: 17;" + "-fx-padding: 0 15 0 15;");

        // CREATE ACCOUNT BUTTON
        Button createAccount = new Button("Create Account     →");
        createAccount.setPrefHeight(52);
        createAccount.setMaxWidth(Double.MAX_VALUE);
        createAccount.setFont(Font.font("Arial", FontWeight.BOLD, 19));
        createAccount.setTextFill(Color.WHITE);
        createAccount.setStyle("-fx-background-color: #0b4f0d;" + "-fx-background-radius: 30;" + "-fx-cursor: hand;");

        // OR CONTINUE WITH
        Line line1 = new Line(0, 0, 135, 0);
        line1.setStroke(Color.web("#c6cdc1"));

        Label orLabel = new Label("or continue with");
        orLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        orLabel.setTextFill(Color.web("#353b33"));

        Line line2 = new Line(0, 0, 135, 0);
        line2.setStroke(Color.web("#c6cdc1"));

        HBox orHBox = new HBox();
        orHBox.setAlignment(Pos.CENTER);
        orHBox.setSpacing(16);
        orHBox.getChildren().addAll(line1, orLabel, line2);

        // GOOGLE BUTTON
        Button googleButton = new Button("G  Google");
        googleButton.setPrefHeight(51);
        googleButton.setMaxWidth(Double.MAX_VALUE);
        googleButton.setFont(Font.font("Arial", FontWeight.NORMAL, 17));

        googleButton.setStyle("-fx-background-color: orange;" + "-fx-border-color: #e7eeee;" + "-fx-border-width: 2;" + "-fx-border-radius: 28;" + "-fx-background-radius: 28;" + "-fx-text-fill: #ebe8e8;" + "-fx-font-weight :bold ;");

        // LOGIN
        Label loginText = new Label("Already have an account? ");
        loginText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        loginText.setTextFill(Color.web("#3d433a"));

        Button login = new Button("Log in");
        login.setTextFill(Color.web("#0b4f0d"));
        login.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        login.setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-cursor: hand;");

        login.setOnAction(e -> {
            System.out.println("login button clicked");
            callbacktologin.run();
        });

        HBox loginHBox = new HBox();
        loginHBox.setAlignment(Pos.CENTER);
        loginHBox.getChildren().addAll(loginText, login);

        // ADD ALL COMPONENTS TO ACCOUNT BOX
        accountBox.getChildren().addAll(title, subtitle, new Label(""), fullNameLabel, fullName, emailLabel, email, passwordLabel, password, createAccount, new Label(""), orHBox, googleButton, new Label(""), loginHBox);

        // ADD ACCOUNT BOX TO RIGHT VBOX
        rightVBox.getChildren().add(accountBox);

        // ADD LEFT + RIGHT VBOX TO HBOX
        mainHBox.getChildren().addAll(leftVBox, rightVBox);
        HBox.setHgrow(leftVBox, Priority.ALWAYS);
        HBox.setHgrow(rightVBox, Priority.ALWAYS);

        Scene scene = new Scene(mainHBox, 1100, 768);

        return scene;
    }
}