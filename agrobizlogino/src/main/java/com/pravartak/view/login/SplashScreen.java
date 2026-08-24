package com.pravartak.view.login;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class SplashScreen {

    private Scene splashScene;

    public Scene getSplashScene(Runnable openLogin) {

        // ================= ROOT =================

        StackPane root = new StackPane();

        root.setStyle(
                "-fx-background-color: #080c0d;"
        );


        // ================= CONTENT =================

        VBox content = new VBox(12);

        content.setAlignment(Pos.CENTER);


        // ================= LOGO ICON =================

        StackPane icon = createLogoIcon();


        // ================= LOGO TEXT =================

        Label logo = new Label("AgroBiz");

        logo.setTextFill(
                Color.web("#68d34a")
        );

        logo.setStyle(
                "-fx-font-size: 48px;" +
                "-fx-font-weight: bold;"
        );


        // ================= TAGLINE =================

        Label tagline = new Label(
                "Empowering Modern Agriculture"
        );

        tagline.setTextFill(
                Color.web("#eeeeee")
        );

        tagline.setStyle(
                "-fx-font-size: 16px;"
        );


        // ================= SMALL TEXT =================

        Label description = new Label(
                "Connect  •  Trade  •  Grow"
        );

        description.setTextFill(
                Color.web("#777777")
        );

        description.setStyle(
                "-fx-font-size: 12px;"
        );


        // ================= LOADING =================

        Label loading = new Label(
                "Starting AgroBiz..."
        );

        loading.setTextFill(
                Color.web("#777777")
        );

        loading.setStyle(
                "-fx-font-size: 11px;"
        );


        // ================= CONTENT =================

        content.getChildren().addAll(
                icon,
                logo,
                tagline,
                description,
                loading
        );

        root.getChildren().add(content);


        // ================= SCENE =================

        splashScene = new Scene(
                root,
                1368,
                768
        );


        // ================= FADE IN =================

        FadeTransition fadeIn =
                new FadeTransition(
                        Duration.seconds(0.3),
                        root
                );

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);


        // ================= WAIT =================

        PauseTransition pause =
                new PauseTransition(
                        Duration.seconds(0.7)
                );


        // ================= FADE OUT =================

        FadeTransition fadeOut =
                new FadeTransition(
                        Duration.seconds(0.3),
                        root
                );

        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);


        // ================= OPEN LOGIN =================

        fadeOut.setOnFinished(e -> {

            openLogin.run();

        });


        // ================= PLAY =================

        SequentialTransition animation =
                new SequentialTransition(
                        fadeIn,
                        pause,
                        fadeOut
                );

        animation.play();


        return splashScene;
    }


    // =========================================================
    // LOGO ICON
    // =========================================================

    private StackPane createLogoIcon() {

        StackPane icon = new StackPane();


        // Outer circle

        Circle circle =
                new Circle(40);

        circle.setFill(
                Color.TRANSPARENT
        );

        circle.setStroke(
                Color.web("#68d34a")
        );

        circle.setStrokeWidth(2);


        // Stem

        Line stem =
                new Line(
                        0, 15,
                        0, -12
                );

        stem.setStroke(
                Color.web("#68d34a")
        );

        stem.setStrokeWidth(3);


        // Left leaf

        Line leftLeaf =
                new Line(
                        0, 0,
                        -13, -12
                );

        leftLeaf.setStroke(
                Color.web("#68d34a")
        );

        leftLeaf.setStrokeWidth(3);


        // Right leaf

        Line rightLeaf =
                new Line(
                        0, -5,
                        13, -17
                );

        rightLeaf.setStroke(
                Color.web("#68d34a")
        );

        rightLeaf.setStrokeWidth(3);


        icon.getChildren().addAll(
                circle,
                stem,
                leftLeaf,
                rightLeaf
        );


        return icon;
    }
}