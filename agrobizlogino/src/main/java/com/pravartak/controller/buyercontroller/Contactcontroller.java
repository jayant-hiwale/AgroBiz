package com.pravartak.controller.buyercontroller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Contactcontroller {

    private Stage stage;

    // १. Default Constructor (LoginPage साठी)
    public Contactcontroller() {
    }

    // २. Parameterized Constructor
    public Contactcontroller(Stage stage) {
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    
    public void show(Parent root) {
        if (stage != null) {
            if (stage.getScene() == null) {
                stage.setScene(new Scene(root, 800, 600));
            } else {
                stage.getScene().setRoot(root);
            }
            stage.show();
        }
    }

    
    public void show(Scene scene) {
        if (stage != null) {
            stage.setScene(scene);
            stage.show();
        }
    }
}