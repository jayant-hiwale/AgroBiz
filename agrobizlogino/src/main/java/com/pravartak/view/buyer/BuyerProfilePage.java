package com.pravartak.view.buyer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

public class BuyerProfilePage {

   
    // =========================================================
    // BUYER PROFILE DATA
    // Later you can load these values from Firestore
    // =========================================================

    public static String buyerName = "Buyer User";
    public static String phoneNumber = "+91 98765 43210";
    public static String email = "buyer@agrobiz.com";
    public static String location = "Maharashtra, India";
    public static String buyerType = "Wholesale Buyer";

    private ImageView profileImage;

    private Label nameLabel;
    private Label phoneLabel;
    private Label emailLabel;
    private Label locationLabel;
    private Label buyerTypeLabel;
    
    

    public BuyerProfilePage() {
       
}

        
    

    // =========================================================
    // PROFILE PAGE SCENE
    // =========================================================

    public Scene getProfilePageScene() {

        BorderPane out = new BorderPane();
        
        //out.setStyle("-fx-background-color: #F4F8F3;");
        out.setTop(new buyerTop().createBuyerTop("◎ Profile"));
        out.setBottom(new Footer().createFooter());
        out.setPrefSize(1368, 768);

        // Main dark background
        out.setStyle(
                "-fx-background-color: #06110c;"
        );
        BorderPane root = new BorderPane();
        
        out.setCenter(root);
        // =====================================================
        // TOP HEADER
        // =====================================================

        VBox header = new VBox(4);

        header.setPadding(
                new Insets(18, 35, 18, 35)
        );

        header.setStyle(
                "-fx-background-color: #0b2613;"
        );

        Label title = new Label("Buyer Profile");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label subtitle = new Label(
                "Manage your personal and buying information."
        );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #7f9987;"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        root.setTop(header);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent = new VBox(22);

        mainContent.setPadding(
                new Insets(30, 35, 30, 35)
        );

        // =====================================================
        // PROFILE TOP CARD
        // =====================================================

        HBox profileCard = new HBox(22);

        profileCard.setAlignment(
                Pos.CENTER_LEFT
        );

        profileCard.setPadding(
                new Insets(20, 25, 20, 25)
        );

        profileCard.setPrefHeight(150);

        profileCard.setStyle(
                "-fx-background-color: #007d00;" +
                "-fx-background-radius: 15;"
        );

        // =====================================================
        // PROFILE IMAGE
        // =====================================================

        StackPane imageContainer = createProfileImage();

        // =====================================================
        // BUYER BASIC INFORMATION
        // =====================================================

        VBox buyerInfo = new VBox(5);

        buyerInfo.setAlignment(
                Pos.CENTER_LEFT
        );

        nameLabel = new Label(
                buyerName
        );

        nameLabel.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label roleLabel = new Label(
                "Buyer"
        );

        roleLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #e5ffe5;"
        );

        Label infoLabel = new Label(
                "Manage your personal and buying information."
        );

        infoLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #d4f0d4;"
        );

        buyerInfo.getChildren().addAll(
                nameLabel,
                roleLabel,
                infoLabel
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        VBox buttonBox = new VBox(8);

        buttonBox.setAlignment(
                Pos.CENTER
        );

        Button uploadButton =
                new Button("Upload Image");

        uploadButton.setPrefWidth(140);
        uploadButton.setPrefHeight(35);

        uploadButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #006b00;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        uploadButton.setOnAction(
                e -> uploadImage()
        );

        Button editButton =
                new Button("Edit Profile");

        editButton.setPrefWidth(140);
        editButton.setPrefHeight(35);

        editButton.setStyle(
                "-fx-background-color: #e8e8e8;" +
                "-fx-text-fill: #006b00;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

     editButton.setOnAction(e -> {

    BuyerEditProfilePage editPage =
            new BuyerEditProfilePage();

    Stage currentStage =
            (Stage) editButton.getScene().getWindow();

    currentStage.setScene(
            editPage.getEditProfileScene()
    );
});

        buttonBox.getChildren().addAll(
                uploadButton,
                editButton
        );

        profileCard.getChildren().addAll(
                imageContainer,
                buyerInfo,
                buttonBox
        );

        // =====================================================
        // INFORMATION CARDS
        // =====================================================

        HBox informationBox =
                new HBox(22);

        informationBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // PERSONAL INFORMATION CARD
        // =====================================================

        VBox personalCard =
                createInformationCard();

        Label personalTitle =
                createCardTitle("Personal Information");

        VBox personalDetails =
                new VBox(16);

        phoneLabel =
                createValueLabel(phoneNumber);

        emailLabel =
                createValueLabel(email);

        locationLabel =
                createValueLabel(location);

        personalDetails.getChildren().addAll(

                createInfoRow(
                        "Phone Number",
                        phoneLabel
                ),

                createInfoRow(
                        "Gmail",
                        emailLabel
                ),

                createInfoRow(
                        "Location",
                        locationLabel
                )
        );

        personalCard.getChildren().addAll(
                personalTitle,
                personalDetails
        );

        // =====================================================
        // BUYER INFORMATION CARD
        // =====================================================

        VBox buyerCard =
                createInformationCard();

        Label buyerTitle =
                createCardTitle("Buyer Information");

        VBox buyerDetails =
                new VBox(16);

        buyerTypeLabel =
                createValueLabel(buyerType);

        buyerDetails.getChildren().add(
                createInfoRow(
                        "Buyer Type",
                        buyerTypeLabel
                )
        );

        buyerCard.getChildren().addAll(
                buyerTitle,
                buyerDetails
        );

        informationBox.getChildren().addAll(
                personalCard,
                buyerCard
        );

        mainContent.getChildren().addAll(
                profileCard,
                informationBox
        );

        root.setCenter(mainContent);

        return new Scene(
                out,
                1368,
                768
        );
    }

    // =========================================================
    // PROFILE IMAGE
    // =========================================================

    private StackPane createProfileImage() {

        StackPane container =
                new StackPane();

        container.setPrefSize(
                105,
                105
        );

        container.setMaxSize(
                105,
                105
        );

        container.setStyle(
                "-fx-background-color: #092d13;" +
                "-fx-background-radius: 100;"
        );

        profileImage =
                new ImageView();

        profileImage.setFitWidth(105);
        profileImage.setFitHeight(105);

        profileImage.setPreserveRatio(false);

        Label initial =
                new Label("B");

        initial.setStyle(
                "-fx-font-size: 35px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        container.getChildren().addAll(
                initial,
                profileImage
        );

        return container;
    }

    // =========================================================
    // UPLOAD IMAGE
    // =========================================================

    private void uploadImage() {

        FileChooser fileChooser =
                new FileChooser();

        fileChooser.setTitle(
                "Select Buyer Profile Image"
        );

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        // File selectedFile =
        //         fileChooser.showOpenDialog(mainStage);
        File selectedFile =
        fileChooser.showOpenDialog(
                (javafx.stage.Stage) profileImage
                        .getScene()
                        .getWindow()
        );

        if (selectedFile != null) {

            Image image =
                    new Image(
                            selectedFile.toURI().toString()
                    );

            profileImage.setImage(image);

            profileImage.setFitWidth(105);
            profileImage.setFitHeight(105);
        }
    }

    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private VBox createInformationCard() {

        VBox card =
                new VBox(20);

        card.setPadding(
                new Insets(25)
        );

        card.setPrefWidth(630);
        card.setMinHeight(280);

        card.setStyle(
                "-fx-background-color: #007d00;" +
                "-fx-background-radius: 15;"
        );

        return card;
    }

    // =========================================================
    // CARD TITLE
    // =========================================================

    private Label createCardTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        return label;
    }

    // =========================================================
    // INFORMATION ROW
    // =========================================================

    private VBox createInfoRow(
            String title,
            Label value
    ) {

        VBox box =
                new VBox(4);

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #d5efd5;"
        );

        box.getChildren().addAll(
                titleLabel,
                value
        );

        return box;
    }

    // =========================================================
    // VALUE LABEL
    // =========================================================

    private Label createValueLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: white;"
        );

        return label;
    }
    
}

