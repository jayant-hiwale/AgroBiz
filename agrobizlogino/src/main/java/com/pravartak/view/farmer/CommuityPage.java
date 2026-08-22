package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.NavBar;

// import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

public class CommuityPage {
    private Scene communityScene;

    public Scene getCommunityScene() {
 
        BorderPane root = new BorderPane();

        // TOP
        root.setTop(new NavBar().createNavbar("Community"));

        // CENTER
        // root.setCenter(createMarketplaceContent());

        // BOTTOM
        root.setBottom(createFooter());

        communityScene = new Scene(root);
        return communityScene;
    }

    // ################## NAVBAR -- TOP ##############################

//     private static HBox createNavbar(Runnable callBackAction) {

//         HBox navbar = new HBox(25);

//         navbar.setPadding(new Insets(10, 20, 10, 20));
//         navbar.setAlignment(Pos.CENTER_LEFT);

//         navbar.setStyle(
//                 "-fx-background-color: #080c0d;" +
//                         "-fx-border-color: #1b2021;" +
//                         "-fx-border-width: 0 0 1 0;");

//         Label logo = new Label("AgroBiz ");

//         logo.setStyle(
//                 "-fx-text-fill: #68d34a;" +
//                         "-fx-font-size: 24px;" +
//                         "-fx-font-weight: bold;");

//         Button explorer = navButton("Explorer");
//         Button marketplaceButton = navButton("Marketplace");
//         Button community = navButton("Community");
//         Button learning = navButton("Learning");
//         Button schemes = navButton("Schemes");
        
        
//         marketplaceButton.setOnAction(e -> {
//             System.out.println("marketplace button clicked ");
//             callBackAction.run();
            
//             // LoginPage.mainStage.setScene(communityPage.getCommunityScene());
//         });

//         Region spacer = new Region();

//         HBox.setHgrow(spacer, Priority.ALWAYS);

//         TextField search = new TextField();
//         search.setPromptText("Search marketplace...");

//         search.setPrefWidth(170);

//         search.setStyle(
//                 "-fx-background-color: #101516;" +
//                         "-fx-text-fill: white;" +
//                         "-fx-prompt-text-fill: #777;" +
//                         "-fx-border-color: #303738;" +
//                         "-fx-border-radius: 6;" +
//                         "-fx-background-radius: 6;");

//         Button sellButton = new Button("◇ List for Sale");

//         sellButton.setStyle(
//                 "-fx-background-color: transparent;" +
//                         "-fx-text-fill: #68d34a;" +
//                         "-fx-border-color: #68d34a;" +
//                         "-fx-border-radius: 5;");

//         Label notification = new Label("♧");
//         Label profile = new Label("◎");
//         Label login = new Label("Login");

//         notification.setStyle("-fx-text-fill: #bbbbbb; -fx-font-size: 18px;");
//         profile.setStyle("-fx-text-fill: #bbbbbb; -fx-font-size: 18px;");
//         login.setStyle("-fx-text-fill: #bbbbbb;");

//         navbar.getChildren().addAll(
//                 logo,
//                 explorer,
//                 marketplaceButton,
//                 community,
//                 learning,
//                 schemes,
//                 spacer,
//                 search,
//                 sellButton,
//                 notification,
//                 profile,
//                 login);

//         return navbar;
//     }

//     // button global style
//     private static Button navButton(String text) {
//         Button button = new Button(text);
//         button.setStyle(
//                 "-fx-background-color: transparent;" +
//                         "-fx-text-fill: #aaaaaa;" +
//                         "-fx-font-size: 13px;" +
//                         "-fx-cursor: hand;" +
//                         "-fx-padding: 5 0 5 0;");

//         button.setOnMouseEntered(e -> {
//             button.setStyle(
//                     "-fx-background-color: transparent;" +
//                             "-fx-text-fill: #68d34a;" +
//                             "-fx-font-size: 13px;" +
//                             "-fx-cursor: hand;" +
//                             "-fx-padding: 5 0 5 0;" +
//                             "-fx-border-color: #68d34a;" +
//                             "-fx-border-width: 0 0 2 0;");
//         });

//         button.setOnMouseExited(e -> {
//             button.setStyle(
//                     "-fx-background-color: transparent;" +
//                             "-fx-text-fill: #aaaaaa;" +
//                             "-fx-font-size: 13px;" +
//                             "-fx-cursor: hand;" +
//                             "-fx-padding: 5 0 5 0;" +
//                             "-fx-border-color: transparent;" +
//                             "-fx-border-width: 0 0 2 0;");
//         });

//         return button;
//     }

    // ############################## FOOTER ##############################
    private static HBox createFooter() {

        HBox footer = new HBox();

        footer.setPadding(new Insets(15, 20, 15, 20));
        footer.setAlignment(Pos.CENTER);

        footer.setStyle(
                "-fx-background-color: #080c0d;" +
                        "-fx-border-color: #1b2021;" +
                        "-fx-border-width: 1 0 0 0;");

        Label text = new Label( "© 2026 AgriBiz Hub | Empowering Modern Agriculture");

        text.setStyle(
                "-fx-text-fill: #777777;" +
                        "-fx-font-size: 12px;");

        footer.getChildren().add(text);

        return footer;
    }
}
