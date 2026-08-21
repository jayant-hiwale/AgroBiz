package com.pravartak.view.farmer;

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
    public Scene get_learning_pageScene(Runnable callbacktoexplorer){

        BorderPane mainborderPane = new BorderPane();
        // top borgderpane
        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setPadding(new Insets(10, 18, 10, 18));
        topBorderPane.setStyle("-fx-background-color: white;-fx-border-color: #283028;-fx-border-width: 0 0 1 0;");

        // logo name 
        Label logo = new Label("Agro Biz ");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 21));
        logo.setTextFill(Color.rgb(21, 137, 66));

        Button homeButton = new Button("Home");
        Button exploreButton = new Button("Explorer ");
        Button marketButton = new Button("MarketPlace ");
        Button communityButton = new Button(" Community");
        Button aiButton = new Button("  AI Advisor");
        Button learningButton = new Button(" Learning ");
        Button schemesButton = new Button(" Schemes ");

        HBox navigationBox = new HBox(8);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(exploreButton, marketButton, communityButton, aiButton, learningButton, schemesButton,homeButton);

        //explorer
        exploreButton.setOnAction(event -> {
            ExplorerPage explorerpage = new ExplorerPage();
            Runnable callbacktohome = new Runnable() {
                @Override
                public void run() {
                    backtolearning();
                }
            };

            LoginPage.mainStage.setScene(explorerpage.getExplorerPage(callbacktohome));
        });

        // back to explorer
        exploreButton.setOnAction(event -> {
            callbacktoexplorer.run();
        });

        // right
        Button loginButton = new Button("Login");
        loginButton.setPrefHeight(32);

        Label profile = new Label("◯");
        profile.setFont(Font.font("Arial", 22));
        profile.setTextFill(Color.DARKGREEN);

        Label profileText = new Label("Profile");
        profileText.setFont(Font.font("Arial", 15));

        HBox profileBox = new HBox(4);
        profileBox.setAlignment(Pos.CENTER);
        profileBox.getChildren().addAll(profile, profileText);

        HBox rightHeader = new HBox(12);
        rightHeader.setAlignment(Pos.CENTER_RIGHT);
        rightHeader.getChildren().addAll(loginButton, profileBox);

        //top border
        topBorderPane.setLeft(logo);
        topBorderPane.setCenter(navigationBox);
        topBorderPane.setRight(rightHeader);

        
        // poultry
        VBox poultryBox = new VBox(8);
        poultryBox.setPadding(new Insets(8));
        poultryBox.setPrefWidth(230);
        poultryBox.setPrefHeight(205);
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

        // ListView  listview = new ListView();
        // listview.getChildrenUnmodifiable().addAll(poultryBox);
        
        // listview.setOnMouseClicked(event->{
        //     VBox  selectItem=listview.getSelectionModel().multipleSlectionModel();
        //     System.out.println(selectItem);

        //     if (selectItem.equals(poultryBox)){
        //         Text t1 = new Text("Ingormation of poultry strp by step");
        //         bp.setCenter(t1);
        //         bp.setStyle("-fx-background-color:skyblue");
        //     }
            // else if (selectItem.equals("Red")){
            //     Text t2 = new Text("Red");
            //     bp.setCenter(t2);
            //     bp.setStyle("-fx-background-color:red");
            // }
            //  else if (selectItem.equals("Orange")){
            //     Text t3 = new Text("Orange");
            //     bp.setCenter(t3);
            //     bp.setStyle("-fx-background-color:orange");
            // }
            //  else if (selectItem.equals("Yellow")){
            //     Text t4 = new Text("Yellow");
            //     bp.setCenter(t4);
            //     bp.setStyle("-fx-background-color:yellow");
            // }
            //  else if (selectItem.equals("Purple")){
            //     Text t5 = new Text("Purple");
            //     bp.setCenter(t5);
            //     bp.setStyle("-fx-background-color:purple");
            // }
        
    //);




        VBox mainVBox = new VBox(28);
        mainVBox.setPadding(new Insets(18, 18, 30, 18));
        mainVBox.setFillWidth(true);
        mainborderPane.setTop(topBorderPane);
        mainVBox.getChildren().addAll(topBorderPane,poultryBox);


        


        Scene scene = new Scene(mainVBox);
        learningpagScene = scene;
        return scene;
    }

    // BACK TO 
    public void backtolearning() {
        LoginPage.mainStage.setScene(learningpagScene);
    }
        
    }    

