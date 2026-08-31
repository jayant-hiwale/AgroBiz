package com.pravartak.view.farmer;


import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.File;

public class AIAdvisorPage {
        // private static final AIService aiService =
        // new AIService();

    // =========================================================
    // AGROBIZ DARK THEME
    // =========================================================

    private static final String BACKGROUND = "#080c0d";

    private static final String CARD = "#101817";

    private static final String CARD_LIGHT = "#14201d";

    private static final String GREEN = "#4CAF50";

    private static final String GREEN_DARK = "#2E7D32";

    private static final String GREEN_LIGHT = "#A5D6A7";

    private static final String TEXT = "#F1F5F2";

    private static final String SECONDARY_TEXT = "#A8B5AD";

    private static final String BORDER = "#263A32";


    // =========================================================
    // MAIN SCENE
    // =========================================================

    public static Scene getAIAdvisorScene() {

        BorderPane root =
                new BorderPane();


        // Main background

        root.setStyle(
                "-fx-background-color: " +
                        BACKGROUND +
                        ";"
        );


        // =====================================================
        // TOP NAVBAR
        // =====================================================

        root.setTop(
                new NavBar()
                        .createNavbar("AI Advisor ")
        );


        // =====================================================
        // BOTTOM FOOTER
        // =====================================================

        root.setBottom(
                new Footer()
                        .createFooter()
        );


        // =====================================================
        // CENTER CONTENT
        // =====================================================

        root.setCenter(
                createAIContent()
        );


        return new Scene(
                root,
                1368,
                768
        );
    }


    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private static VBox createAIContent() {

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(
                        30,
                        100,
                        30,
                        100
                )
        );

        main.setAlignment(
                Pos.TOP_CENTER
        );


        // =====================================================
        // PAGE INTRODUCTION
        // =====================================================

        VBox intro =
                new VBox(6);

        intro.setMaxWidth(900);

        intro.setAlignment(
                Pos.CENTER_LEFT
        );


        Label title =
                new Label(
                        "🌱 AI Farming Advisor"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        26
                )
        );

        title.setTextFill(
                Color.web(GREEN_LIGHT)
        );


        Label subtitle =
                new Label(
                        "Get simple and smart guidance for your farming needs."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );


        intro.getChildren().addAll(
                title,
                subtitle
        );


        // =====================================================
        // QUESTION CARD
        // =====================================================

        VBox questionCard =
                new VBox(14);

        questionCard.setMaxWidth(900);

        questionCard.setPadding(
                new Insets(22)
        );

        questionCard.setStyle(
                "-fx-background-color: " +
                        CARD +
                        ";" +

                "-fx-background-radius: 14;" +

                "-fx-border-color: " +
                        BORDER +
                        ";" +

                "-fx-border-radius: 14;" +

                "-fx-border-width: 1;"
        );


        Label questionTitle =
                new Label(
                        "Ask your farming question"
                );

        questionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        questionTitle.setTextFill(
                Color.web(TEXT)
        );


        Label questionHint =
                new Label(
                        "Ask about crops, soil, irrigation, fertilizers, pests or farming practices."
                );

        questionHint.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        questionHint.setTextFill(
                Color.web(SECONDARY_TEXT)
        );


        // =====================================================
        // QUESTION TEXT AREA
        // =====================================================

        TextArea questionBox =
                new TextArea();

        questionBox.setPromptText(
                "Example: Which fertilizer is suitable for wheat?"
        );

        questionBox.setWrapText(true);

        questionBox.setPrefHeight(105);

        questionBox.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        questionBox.setStyle(

                "-fx-control-inner-background: #0B1210;" +

                "-fx-text-fill: #F1F5F2;" +

                "-fx-prompt-text-fill: #718078;" +

                "-fx-border-color: #263A32;" +

                "-fx-border-radius: 10;" +

                "-fx-background-radius: 10;" +

                "-fx-focus-color: #4CAF50;" +

                "-fx-faint-focus-color: transparent;"
        );


        // =====================================================
        // BUTTON AREA
        // =====================================================

        HBox buttonBox =
                new HBox(10);

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT
        );


        // Clear button

        Button clearButton =
                new Button(
                        "Clear"
                );

        clearButton.setPrefSize(
                90,
                40
        );

        clearButton.setStyle(

                "-fx-background-color: #1A2421;" +

                "-fx-text-fill: #B7C3BC;" +

                "-fx-font-weight: bold;" +

                "-fx-background-radius: 20;" +

                "-fx-border-color: #34473F;" +

                "-fx-border-radius: 20;"
        );


        // Ask AI button

        Button askButton =
                new Button(
                        "Ask AI  ✦"
                );

        askButton.setPrefSize(
                120,
                40
        );

        askButton.setStyle(

                "-fx-background-color: " +
                        GREEN_DARK +
                        ";" +

                "-fx-text-fill: white;" +

                "-fx-font-weight: bold;" +

                "-fx-font-size: 14;" +

                "-fx-background-radius: 20;"
        );
        


        // =====================================================
        // RESPONSE TITLE
        // =====================================================

        Label responseTitle =
                new Label(
                        "AI Response"
                );

        responseTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        responseTitle.setTextFill(
                Color.web(TEXT)
        );


        // =====================================================
        // RESPONSE BOX
        // =====================================================

        TextArea responseBox =
                new TextArea();

        responseBox.setEditable(
                false
        );

        responseBox.setWrapText(
                true
        );

        responseBox.setPrefHeight(
                170
        );

        responseBox.setPromptText(
                "Your AI farming advice will appear here..."
        );

        responseBox.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        responseBox.setStyle(

                "-fx-control-inner-background: #14201D;" +

                "-fx-text-fill: #E8F5E9;" +

                "-fx-prompt-text-fill: #718078;" +

                "-fx-border-color: #263A32;" +

                "-fx-border-radius: 10;" +

                "-fx-background-radius: 10;"
        );


        // =====================================================
        // ASK AI ACTION
        // =====================================================

        askButton.setOnAction(event -> {

    String question =
            questionBox.getText().trim();


    if (question.isEmpty()) {

        responseBox.setText(
                "Please enter a farming question first."
        );

        return;
    }


    responseBox.setText(
            "AI is thinking... 🌱"
    );


//     String answer =
//             aiService.askAI(question);


//     responseBox.setText(
//             answer
//     );
});


        // =====================================================
        // CLEAR ACTION
        // =====================================================

        clearButton.setOnAction(event -> {

            questionBox.clear();

            responseBox.clear();
        });


        buttonBox.getChildren().addAll(
                clearButton,
                askButton
        );


        // =====================================================
        // ADD QUESTION CARD CONTENT
        // =====================================================

        questionCard.getChildren().addAll(

                questionTitle,

                questionHint,

                questionBox,

                buttonBox,

                responseTitle,

                responseBox
        );


        // =====================================================
        // QUICK QUESTIONS
        // =====================================================

        Label quickTitle =
                new Label(
                        "Quick Questions"
                );

        quickTitle.setMaxWidth(
                900
        );

        quickTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        quickTitle.setTextFill(
                Color.web(TEXT)
        );


        HBox quickQuestions =
                new HBox(10);

        quickQuestions.setMaxWidth(
                900
        );

        quickQuestions.setAlignment(
                Pos.CENTER_LEFT
        );


        Button cropButton =
                createQuickButton(
                        "🌾 Crop Advice",
                        questionBox
                );


        Button soilButton =
                createQuickButton(
                        "🌱 Soil Health",
                        questionBox
                );


        Button irrigationButton =
                createQuickButton(
                        "💧 Irrigation",
                        questionBox
                );


        Button fertilizerButton =
                createQuickButton(
                        "🌿 Fertilizer",
                        questionBox
                );


        quickQuestions.getChildren().addAll(

                cropButton,

                soilButton,

                irrigationButton,

                fertilizerButton
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        main.getChildren().addAll(

                intro,

                questionCard,

                quickTitle,

                quickQuestions
        );


        return main;
    }


    // =========================================================
    // QUICK QUESTION BUTTON
    // =========================================================

    private static Button createQuickButton(
            String text,
            TextArea questionBox) {


        Button button =
                new Button(text);


        button.setPrefHeight(
                38
        );


        button.setStyle(

                "-fx-background-color: #14201D;" +

                "-fx-text-fill: #A5D6A7;" +

                "-fx-font-weight: bold;" +

                "-fx-font-size: 12;" +

                "-fx-background-radius: 20;" +

                "-fx-border-color: #2C4238;" +

                "-fx-border-radius: 20;"
        );


        button.setOnAction(event -> {


            if (text.contains("Crop")) {

                questionBox.setText(
                        "Which crop is suitable for my farm?"
                );


            } else if (text.contains("Soil")) {

                questionBox.setText(
                        "How can I improve my soil health?"
                );


            } else if (text.contains("Irrigation")) {

                questionBox.setText(
                        "Which irrigation method is suitable for my crop?"
                );


            } else {

                questionBox.setText(
                        "Which fertilizer should I use for my crop?"
                );
            }
        });


        return button;
    }
}

