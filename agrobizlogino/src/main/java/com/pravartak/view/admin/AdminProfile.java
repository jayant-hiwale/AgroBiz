package com.pravartak.view.admin;

import com.pravartak.view.login.LoginPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

public class AdminProfile {

    public Scene getAdminProfileScene() {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #0b130b;"); // Dark background

        // 1. TOP NAVBAR
        HBox navbar = new HBox();
        navbar.setPadding(new Insets(15, 30, 15, 30));
        navbar.setAlignment(Pos.CENTER);
        navbar.setStyle("-fx-background-color: #060a06; -fx-border-color: #142214; -fx-border-width: 0 0 1 0;");

        Label logo = new Label("Agro Biz");
        logo.setStyle("-fx-text-fill: #52c41a; -fx-font-size: 22px; -fx-font-weight: bold;");

        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);

        HBox navLinks = new HBox(25);
        navLinks.setAlignment(Pos.CENTER);
        
        Button btnHome = createNavLink("Home");
        Button btnMarket = createNavLink("Market");
        Button btnWatchlist = createNavLink("Watchlist");
        Button btnAiAdvisor = createNavLink("AI Advisor");

        btnHome.setOnAction(e -> {
            AdminPage dashBoard = new AdminPage();
            LoginPage.mainStage.setScene(dashBoard.getAdminPage());
        });

        navLinks.getChildren().addAll(btnHome, btnMarket, btnWatchlist, btnAiAdvisor);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        Label profileIcon = new Label("⊙ Profile");
        profileIcon.setStyle("-fx-text-fill: #a6d8a8; -fx-font-size: 14px; -fx-cursor: hand;");

        navbar.getChildren().addAll(logo, spacer1, navLinks, spacer2, profileIcon);
        mainLayout.setTop(navbar);

        // 2. MAIN CONTENT (Scrollable)
        VBox contentArea = new VBox(25);
        contentArea.setPadding(new Insets(30, 60, 40, 60));
        contentArea.setStyle("-fx-background-color: #0b130b;");

        // Title Header
        Label mainTitle = new Label("Admin Profile");
        mainTitle.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Manage your personal and system administrator information.");
        subTitle.setStyle("-fx-text-fill: #7a9a7a; -fx-font-size: 14px;");

        VBox titleBox = new VBox(5, mainTitle, subTitle);

        // Top Header Banner Card
        HBox bannerCard = new HBox(20);
        bannerCard.setPadding(new Insets(25));
        bannerCard.setAlignment(Pos.CENTER_LEFT);
        bannerCard.setStyle("-fx-background-color: #0f6e14; -fx-background-radius: 12px;");

        // Profile Avatar Circle
        StackPane avatarPane = new StackPane();
        Circle circle = new Circle(35);
        circle.setStyle("-fx-fill: #062b08;");
        Label avatarChar = new Label("A");
        avatarChar.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 28px; -fx-font-weight: bold;");
        avatarPane.getChildren().addAll(circle, avatarChar);

        // User Meta Info
        VBox userMeta = new VBox(5);
        Label userName = new Label("Admin User");
        userName.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 22px; -fx-font-weight: bold;");

        Label userRole = new Label("Administrator");
        userRole.setStyle("-fx-text-fill: #d0f0d0; -fx-font-size: 14px;");

        Label userDesc = new Label("Manage your personal and system administrator information.");
        userDesc.setStyle("-fx-text-fill: #a2daa4; -fx-font-size: 13px;");

        userMeta.getChildren().addAll(userName, userRole, userDesc);

        Region bannerSpacer = new Region();
        HBox.setHgrow(bannerSpacer, Priority.ALWAYS);

        // Buttons
        VBox actionBtns = new VBox(10);
        actionBtns.setAlignment(Pos.CENTER);

        Button btnUpload = new Button("Upload Image");
        btnUpload.setPrefWidth(120);
        btnUpload.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-radius: 6px; -fx-cursor: hand;");

        Button btnEdit = new Button("Edit Profile");
        btnEdit.setPrefWidth(120);
        btnEdit.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-radius: 6px; -fx-cursor: hand;");

        actionBtns.getChildren().addAll(btnUpload, btnEdit);

        bannerCard.getChildren().addAll(avatarPane, userMeta, bannerSpacer, actionBtns);

        // Grid for Details Cards
        HBox cardsGrid = new HBox(25);

        // Left Card: Personal Information
        VBox personalCard = createInfoCard("Personal Information");
        personalCard.getChildren().addAll(
                createInfoGroup("Phone Number", "+91 98765 43210"),
                createInfoGroup("Gmail", "admin@agrobiz.com"),
                createInfoGroup("Location", "Maharashtra, India")
        );

        // Right Card: Admin Information
        VBox adminInfoCard = createInfoCard("Admin Information");
        adminInfoCard.getChildren().addAll(
                createInfoGroup("Admin Access Type", "Super Administrator"),
                createInfoGroup("Department", "System Operations & Control")
        );

        HBox.setHgrow(personalCard, Priority.ALWAYS);
        HBox.setHgrow(adminInfoCard, Priority.ALWAYS);

        cardsGrid.getChildren().addAll(personalCard, adminInfoCard);

        contentArea.getChildren().addAll(titleBox, bannerCard, cardsGrid);

        ScrollPane scrollPane = new ScrollPane(contentArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #0b130b; -fx-background: #0b130b; -fx-border-color: transparent;");

        mainLayout.setCenter(scrollPane);

        // 3. BOTTOM FOOTER
        HBox footer = new HBox();
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: #060a06; -fx-border-color: #142214; -fx-border-width: 1 0 0 0;");

        Label footerText = new Label("© 2026 AgriBiz Hub | Empowering Modern Agriculture");
        footerText.setStyle("-fx-text-fill: #4b6e4c; -fx-font-size: 12px;");
        footer.getChildren().add(footerText);

        mainLayout.setBottom(footer);

        return new Scene(mainLayout, 1100, 700);
    }

    // Helper: Nav Button Styling
    private Button createNavLink(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a6d8a8; -fx-font-size: 14px; -fx-cursor: hand;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-cursor: hand;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a6d8a8; -fx-font-size: 14px; -fx-cursor: hand;"));
        return btn;
    }

    // Helper: Green Info Card Container
    private VBox createInfoCard(String cardTitleText) {
        VBox card = new VBox(20);
        card.setPadding(new Insets(25));
        card.setStyle("-fx-background-color: #0f6e14; -fx-background-radius: 12px;");

        Label cardTitle = new Label(cardTitleText);
        cardTitle.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18px; -fx-font-weight: bold;");

        card.getChildren().add(cardTitle);
        return card;
    }

    // Helper: Key-Value Label Pair
    private VBox createInfoGroup(String label, String value) {
        VBox group = new VBox(4);
        Label lbl = new Label(label);
        lbl.setStyle("-fx-text-fill: #a2daa4; -fx-font-size: 13px; -fx-font-weight: bold;");

        Label val = new Label(value);
        val.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 15px;");

        group.getChildren().addAll(lbl, val);
        return group;
    }
}