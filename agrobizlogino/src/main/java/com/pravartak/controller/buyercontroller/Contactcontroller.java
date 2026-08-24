package com.pravartak.controller.buyercontroller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Contactcontroller {

    private final BorderPane mainLayout = new BorderPane();

    public Contactcontroller() {
        mainLayout.setStyle("-fx-background-color: #F4F8F3;");
    }

    public BorderPane getMainLayout() {
        return mainLayout;
    }

    public void show(Node page) {
        mainLayout.setCenter(page);
    }

    public Object show(Scene marketPage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}