package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.Footer;
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

        root.setStyle("-fx-background-color: #080c0d;");
        root.setTop(new NavBar().createNavbar("Community"));
        root.setBottom(new Footer().createFooter());
        

        communityScene = new Scene(root);
        return communityScene;
    }

    

   
    
}
