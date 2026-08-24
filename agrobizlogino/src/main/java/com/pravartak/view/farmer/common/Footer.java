package com.pravartak.view.farmer.common;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Footer {

       public HBox createFooter() {

                HBox footer = new HBox();

                footer.setPadding(new Insets(15, 20, 15, 20));
                footer.setAlignment(Pos.CENTER);
                footer.setStyle("-fx-background-color: #080c0d;" +"-fx-border-color: #1b2021;" +"-fx-border-width: 1 0 0 0;");

                Label text = new Label("© 2026 AgriBiz Hub | Empowering Modern Agriculture");
                text.setStyle("-fx-text-fill: #777777;" +"-fx-font-size: 12px;");
                
                footer.getChildren().add(text);
                return footer;
        }
    
}
