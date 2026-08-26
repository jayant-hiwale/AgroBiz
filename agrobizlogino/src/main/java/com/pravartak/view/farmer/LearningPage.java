package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LearningPage {
    private Scene learningpagScene;
    public Scene get_learning_pageScene(){

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #1a2f1c;");
        borderPane.setStyle("-fx-background-color: #1a2f1c;");
        borderPane.setTop(new NavBar().createNavbar("Learning"));
        borderPane.setBottom(new Footer().createFooter());
       
       
        // poultry
        VBox poultryBox = new VBox(8);
        poultryBox.setPadding(new Insets(8));
        poultryBox.setPrefWidth(230);
        //poultryBox.setPrefHeight(205);
        poultryBox.setAlignment(Pos.TOP_LEFT);
        poultryBox.setStyle("-fx-background-color: darkgreen;" + "-fx-background-radius: 12;" + "-fx-border-color: #eff6f3;" + "-fx-border-radius: 12;");

        Image poultryImage = new Image(getClass().getResource("/poltry.png").toExternalForm());

        ImageView poultryImageView = new ImageView(poultryImage);
        poultryImageView.setFitWidth(214);
        poultryImageView.setFitHeight(95);

        Label poultryTitle = new Label("Poultry Farming");
        poultryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label poultryDescription = new Label("Advanced systems for optimal bird health.");
        poultryDescription.setFont(Font.font("Arial", 11));
        poultryDescription.setWrapText(true);
        poultryDescription.setTextFill(Color.GRAY);

        poultryBox.getChildren().addAll(poultryImageView, poultryTitle, poultryDescription);

        VBox mainVBox = new VBox(28);
        mainVBox.setPadding(new Insets(18, 18, 30, 18));
        //mainVBox.setFillWidth(true);
        mainVBox.getChildren().addAll(poultryBox);
        borderPane.setCenter(mainVBox);

        Scene scene = new Scene(borderPane);
        learningpagScene = scene;
        return scene;
    }

    
    }    

