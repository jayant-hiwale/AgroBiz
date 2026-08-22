package com.pravartak.view.farmer;

import java.net.URL;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ExplorerPage {
    private Scene explorepageScene;
    public Scene getExplorerPage() {

        // MAIN BORDER PANE
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #080c0d;");
        borderPane.setTop(new NavBar().createNavbar("Explorer"));
        borderPane.setBottom(new Footer().createFooter());

        // MAIN VBOX
        VBox mainVBox = new VBox(25);
        mainVBox.setPadding(new Insets(35, 45, 45, 45));
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setBackground(new Background(new BackgroundFill(Color.web("#080c0d"), CornerRadii.EMPTY, Insets.EMPTY)));

        // MAIN TITLE
        Label mainTitle = new Label("Discover Livestock\n" + "Innovations");
        mainTitle.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        mainTitle.setTextFill(Color.web("#eeeeee"));
        mainTitle.setAlignment(Pos.CENTER);
        mainTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // DESCRIPTION
        Label description = new Label("Explore high-tech farming guides, trending animal " + "husbandry businesses, and advanced\n" + "livestock categories to elevate your agricultural enterprise.");
        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.web("#aaaaaa"));
        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);
        description.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // SEARCH BAR
        TextField searchField = new TextField();
        searchField.setPromptText("Search breeds, technologies, or guides...");
        searchField.setPrefHeight(48);
        searchField.setPrefWidth(620);
        searchField.setPadding(new Insets(0, 18, 0, 18));
        searchField.setStyle("-fx-background-color: #101516;" +"-fx-text-fill: #eeeeee;" +"-fx-prompt-text-fill: #777777;" +"-fx-border-color: #242b2c;" +"-fx-border-radius: 8;" +"-fx-background-radius: 8;");

        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(90);
        searchButton.setPrefHeight(42);
        searchButton.setStyle("-fx-background-color: #68d34a;" +"-fx-text-fill: #080c0d;" +"-fx-font-weight: bold;" +"-fx-background-radius: 6;" +"-fx-cursor: hand;");

        HBox searchHBox = new HBox(8);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.setMaxWidth(720);
        searchHBox.setPadding(new Insets(5, 8, 5, 8));
searchHBox.setStyle(
                "-fx-background-color: #0d1213;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #242b2c;" +
                "-fx-border-radius: 10;"
        );
        searchHBox.getChildren().addAll(searchField, searchButton);

        // CATEGORY FILTER BUTTONS
        Button allCategories = new Button("All Categories");
        Button poultry = new Button("Poultry");
        Button dairy = new Button("Dairy Cattle");
        Button aquatic = new Button("Aquaculture");
        Button swine = new Button("Swine");
        Button smallRuminants = new Button("Small Ruminants");
        Button plantNursery = new Button("Plant Nursery");

        allCategories.setStyle(
                "-fx-background-color: #68d34a;" +
                "-fx-text-fill: #080c0d;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 8 14;" +
                "-fx-cursor: hand;"
        );


        String categoryStyle =
                "-fx-background-color: #101516;" +
                "-fx-text-fill: #aaaaaa;" +
                "-fx-border-color: #242b2c;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 8 14;" +
                "-fx-cursor: hand;";
        poultry.setStyle(categoryStyle);
        dairy.setStyle(categoryStyle);
        aquatic.setStyle(categoryStyle);
        swine.setStyle(categoryStyle);
        smallRuminants.setStyle(categoryStyle);
        plantNursery.setStyle(categoryStyle);

        HBox categoryFilterBox = new HBox(10);
        categoryFilterBox.setAlignment(Pos.CENTER);
        categoryFilterBox.getChildren().addAll(allCategories, poultry, dairy, aquatic, swine, smallRuminants, plantNursery);

        // CATEGORY TITLE
        Label categoryTitle = new Label("Explore Categories");
        categoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        categoryTitle.setTextFill(Color.web("#eeeeee"));

        // FIRST CATEGORY HBOX
        HBox firstCategoryHBox = new HBox(18);
        firstCategoryHBox.setAlignment(Pos.CENTER);

        // POULTRY CARD
        VBox poultryCard = new VBox(10);
        poultryCard.setPadding(new Insets(10));
        poultryCard.setPrefWidth(220);
        poultryCard.setPrefHeight(220);
        poultryCard.setAlignment(Pos.TOP_LEFT);
        poultryCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12" + "-fx-border-color: #242c2c;" + "-fx-border-radius: 12;");

        URL poultryURL = getClass().getResource("/poltry.png");

        if (poultryURL == null) {
            throw new RuntimeException("poltry.png not found!");
        }

        Image poultryImage = new Image(poultryURL.toExternalForm());

        ImageView poultryImageView = new ImageView(poultryImage);
        poultryImageView.setFitWidth(198);
        poultryImageView.setFitHeight(105);
        poultryImageView.setPreserveRatio(false);

        Label poultryTitle = new Label("Poultry Farming");
        poultryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        poultryTitle.setTextFill(Color.web("#eeeeee"));

        Label poultryDescription = new Label("Advanced systems for optimal bird health.");
        poultryDescription.setFont(Font.font("Arial", 12));
        poultryDescription.setTextFill(Color.web("#888888"));
        poultryDescription.setWrapText(true);

        poultryCard.getChildren().addAll(poultryImageView, poultryTitle, poultryDescription);

        // DAIRY CARD
        VBox dairyCard = new VBox(10);
        dairyCard.setPadding(new Insets(10));
        dairyCard.setPrefWidth(220);
        dairyCard.setPrefHeight(220);
        dairyCard.setAlignment(Pos.TOP_LEFT);
        dairyCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        URL dairyURL = getClass().getResource("/Dairy.png");

        if (dairyURL == null) {
            throw new RuntimeException("Dairy.png not found!");
        }

        Image dairyImage = new Image(dairyURL.toExternalForm());

        ImageView dairyImageView = new ImageView(dairyImage);
        dairyImageView.setFitWidth(198);
        dairyImageView.setFitHeight(105);
        dairyImageView.setPreserveRatio(false);

        Label dairyTitle = new Label("Dairy Cattle");
        dairyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        dairyTitle.setTextFill(Color.web("#eeeeee"));

        Label dairyDescription = new Label("Modern techniques for sustainable milk production.");
        dairyDescription.setFont(Font.font("Arial", 12));
        dairyDescription.setTextFill(Color.web("#888888"));
        dairyDescription.setWrapText(true);

        dairyCard.getChildren().addAll(dairyImageView, dairyTitle, dairyDescription);

        // AQUACULTURE CARD
        VBox aquaticCard = new VBox(10);
        aquaticCard.setPadding(new Insets(10));
        aquaticCard.setPrefWidth(220);
        aquaticCard.setPrefHeight(220);
        aquaticCard.setAlignment(Pos.TOP_LEFT);
        aquaticCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        Image aquaticImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView aquaticImageView = new ImageView(aquaticImage);
        aquaticImageView.setFitWidth(198);
        aquaticImageView.setFitHeight(105);

        Label aquaticTitle = new Label("Aquaculture");
        aquaticTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        aquaticTitle.setTextFill(Color.web("#eeeeee"));

        Label aquaticDescription = new Label("Smart aquatic farming and sustainable fish production.");
        aquaticDescription.setFont(Font.font("Arial", 12));
        aquaticDescription.setTextFill(Color.web("#888888"));
        aquaticDescription.setWrapText(true);

        aquaticCard.getChildren().addAll(aquaticImageView, aquaticTitle, aquaticDescription);

        // SWINE CARD
        VBox swineCard = new VBox(10);
        swineCard.setPadding(new Insets(10));
        swineCard.setPrefWidth(220);
        swineCard.setPrefHeight(220);
        swineCard.setAlignment(Pos.TOP_LEFT);
        swineCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        Image swineImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView swineImageView = new ImageView(swineImage);
        swineImageView.setFitWidth(198);
        swineImageView.setFitHeight(105);

        Label swineTitle = new Label("Swine Farming");
        swineTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        swineTitle.setTextFill(Color.web("#eeeeee"));

        Label swineDescription = new Label("Efficient livestock systems and smart feeding.");
        swineDescription.setFont(Font.font("Arial", 12));
        swineDescription.setTextFill(Color.web("#888888"));
        swineDescription.setWrapText(true);

        swineCard.getChildren().addAll(swineImageView, swineTitle, swineDescription);

        firstCategoryHBox.getChildren().addAll(poultryCard, dairyCard, aquaticCard, swineCard);

        // SECOND CATEGORY HBOX
        HBox secondCategoryHBox = new HBox(18);
        secondCategoryHBox.setAlignment(Pos.CENTER);

        // SMALL RUMINANTS CARD
        VBox ruminantsCard = new VBox(10);
        ruminantsCard.setPadding(new Insets(10));
        ruminantsCard.setPrefWidth(220);
        ruminantsCard.setPrefHeight(220);
        ruminantsCard.setAlignment(Pos.TOP_LEFT);
        ruminantsCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        Image ruminantsImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView ruminantsImageView = new ImageView(ruminantsImage);
        ruminantsImageView.setFitWidth(198);
        ruminantsImageView.setFitHeight(105);

        Label ruminantsTitle = new Label("Small Ruminants");
        ruminantsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        ruminantsTitle.setTextFill(Color.web("#eeeeee"));

        Label ruminantsDescription = new Label("Modern goat and sheep farming solutions.");
        ruminantsDescription.setFont(Font.font("Arial", 12));
        ruminantsDescription.setTextFill(Color.web("#888888"));
        ruminantsDescription.setWrapText(true);

        ruminantsCard.getChildren().addAll(ruminantsImageView, ruminantsTitle, ruminantsDescription);

        // PLANT NURSERY CARD
        VBox nurseryCard = new VBox(10);
        nurseryCard.setPadding(new Insets(10));
        nurseryCard.setPrefWidth(220);
        nurseryCard.setPrefHeight(220);
        nurseryCard.setAlignment(Pos.TOP_LEFT);
        nurseryCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        Image nurseryImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView nurseryImageView = new ImageView(nurseryImage);
        nurseryImageView.setFitWidth(198);
        nurseryImageView.setFitHeight(105);

        Label nurseryTitle = new Label("Plant Nursery");
        nurseryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        nurseryTitle.setTextFill(Color.web("#eeeeee"));

        Label nurseryDescription = new Label("Healthy seedlings and modern nursery management.");
        nurseryDescription.setFont(Font.font("Arial", 12));
        nurseryDescription.setTextFill(Color.web("#888888"));
        nurseryDescription.setWrapText(true);

        nurseryCard.getChildren().addAll(nurseryImageView, nurseryTitle, nurseryDescription);

        // SMART MACHINERY CARD
        VBox machineryCard = new VBox(10);
        machineryCard.setPadding(new Insets(10));
        machineryCard.setPrefWidth(220);
        machineryCard.setPrefHeight(220);
        machineryCard.setAlignment(Pos.TOP_LEFT);
        machineryCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        Image machineryImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView machineryImageView = new ImageView(machineryImage);
        machineryImageView.setFitWidth(198);
        machineryImageView.setFitHeight(105);

        Label machineryTitle = new Label("Smart Machinery");
        machineryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        machineryTitle.setTextFill(Color.web("#eeeeee"));

        Label machineryDescription = new Label("Automated agricultural equipment and technology.");
        machineryDescription.setFont(Font.font("Arial", 12));
        machineryDescription.setTextFill(Color.web("#888888"));
        machineryDescription.setWrapText(true);

        machineryCard.getChildren().addAll(machineryImageView, machineryTitle, machineryDescription);

        // PRECISION AGRICULTURE CARD
        VBox precisionCard = new VBox(10);
        precisionCard.setPadding(new Insets(10));
        precisionCard.setPrefWidth(220);
        precisionCard.setPrefHeight(220);
        precisionCard.setAlignment(Pos.TOP_LEFT);
        precisionCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

        Image precisionImage = new Image(getClass().getResource("/any.png").toExternalForm());

        ImageView precisionImageView = new ImageView(precisionImage);
        precisionImageView.setFitWidth(198);
        precisionImageView.setFitHeight(105);

        Label precisionTitle = new Label("Precision Agriculture");
        precisionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        precisionTitle.setTextFill(Color.web("#eeeeee"));

        Label precisionDescription = new Label("Data-driven technology for better farm decisions.");
        precisionDescription.setFont(Font.font("Arial", 12));
        precisionDescription.setTextFill(Color.web("#888888"));
        precisionDescription.setWrapText(true);

        precisionCard.getChildren().addAll(precisionImageView, precisionTitle, precisionDescription);

        secondCategoryHBox.getChildren().addAll(ruminantsCard, nurseryCard, machineryCard, precisionCard);

        // ADD ALL CONTENT
        mainVBox.getChildren().addAll(mainTitle, description, searchHBox, categoryFilterBox, categoryTitle, firstCategoryHBox, secondCategoryHBox);

        // SCROLL PANE
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #080c0d;-fx-background: #080c0d;");

        
        borderPane.setCenter(scrollPane);
     
        // SCENE
        Scene scene = new Scene(borderPane, 1100, 768);

        return scene;
    }
    public void backtoexplorer(){
        LoginPage.mainStage.setScene(explorepageScene);
    }
}