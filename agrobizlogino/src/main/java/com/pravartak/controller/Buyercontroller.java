package com.pravartak.controller;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Buyercontroller {

    private final BorderPane mainLayout = new BorderPane();

    public Buyercontroller() {
        mainLayout.setStyle("-fx-background-color: #F4F8F3;");
    }

    public BorderPane getMainLayout() {
        return mainLayout;
    }

    public void show(Parent page) {
        mainLayout.setCenter(page);
    }
}