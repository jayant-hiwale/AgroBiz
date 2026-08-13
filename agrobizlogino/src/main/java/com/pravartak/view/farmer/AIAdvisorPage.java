package com.pravartak.view.farmer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AIAdvisorPage {

    private final Stage stage;

    // Colors
    private final Color DARK_GREEN =
            Color.rgb(18, 82, 24);

    private final Color GREEN =
            Color.rgb(48, 125, 55);

    private final Color CREAM =
            Color.rgb(248, 249, 232);

    private final Color LIGHT_GREEN =
            Color.rgb(226, 239, 219);

    private final Color DARK_TEXT =
            Color.rgb(35, 45, 35);

    private final Color GREY =
            Color.rgb(105, 110, 105);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AIAdvisorPage(Stage stage) {

        this.stage = stage;
    }


    // =========================================================
    // GET SCENE
    // =========================================================

    public Scene getAIAdvisorScene() {

        BorderPane root =
                new BorderPane();

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                CREAM,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // TOP BAR
        HBox topBar =
                createTopBar();

        root.setTop(topBar);


        // MAIN CONTENT
        VBox content =
                createContent();

        root.setCenter(content);


        return new Scene(
                root,
                1368,
                768
        );
    }


    // =========================================================
    // TOP BAR
    // =========================================================

    private HBox createTopBar() {

        HBox topBar =
                new HBox();

        topBar.setPrefHeight(82);

        topBar.setPadding(
                new Insets(
                        18,
                        30,
                        18,
                        30
                )
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        Label title =
                new Label(
                        "AI Farming Advisor"
                );

        title.setTextFill(
                DARK_TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );


        Label subtitle =
                new Label(
                        "Get smart recommendations for your farm"
                );

        subtitle.setTextFill(
                GREY
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );


        VBox titleBox =
                new VBox(
                        title,
                        subtitle
                );

        titleBox.setSpacing(3);


        topBar.getChildren().add(
                titleBox
        );


        return topBar;
    }


    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private VBox createContent() {

        VBox content =
                new VBox();

        content.setSpacing(20);

        content.setPadding(
                new Insets(
                        30
                )
        );


        // -----------------------------------------------------
        // HEADER CARD
        // -----------------------------------------------------

        VBox headerCard =
                new VBox();

        headerCard.setSpacing(8);

        headerCard.setPadding(
                new Insets(
                        25
                )
        );

        headerCard.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(16),
                                Insets.EMPTY
                        )
                )
        );


        Label heading =
                new Label(
                        "🌱 Your Personal AI Farming Advisor"
                );

        heading.setTextFill(
                Color.WHITE
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );


        Label description =
                new Label(
                        "Ask questions about crops, irrigation, soil, "
                        + "fertilizers, diseases and farming decisions."
                );

        description.setTextFill(
                Color.rgb(
                        220,
                        235,
                        220
                )
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );


        headerCard.getChildren().addAll(
                heading,
                description
        );


        // -----------------------------------------------------
        // ASK AI AREA
        // -----------------------------------------------------

        VBox questionCard =
                new VBox();

        questionCard.setSpacing(12);

        questionCard.setPadding(
                new Insets(
                        25
                )
        );

        questionCard.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(14),
                                Insets.EMPTY
                        )
                )
        );


        Label questionTitle =
                new Label(
                        "Ask your farming question"
                );

        questionTitle.setTextFill(
                DARK_TEXT
        );

        questionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );


        TextArea questionBox =
                new TextArea();

        questionBox.setPromptText(
                "Example: Which fertilizer should I use for wheat?"
        );

        questionBox.setPrefHeight(
                120
        );

        questionBox.setWrapText(
                true
        );


        Button askButton =
                new Button(
                        "Ask AI  ✦"
                );

        askButton.setPrefHeight(
                48
        );

        askButton.setPrefWidth(
                150
        );

        askButton.setTextFill(
                Color.WHITE
        );

        askButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        askButton.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(25),
                                Insets.EMPTY
                        )
                )
        );


        askButton.setOnAction(event -> {

            String question =
                    questionBox.getText();

            if (question.isEmpty()) {

                questionBox.setPromptText(
                        "Please enter your farming question."
                );

            } else {

                System.out.println(
                        "Question: " + question
                );

                questionBox.setText("");

                questionBox.setPromptText(
                        "AI recommendation will appear here..."
                );
            }
        });


        questionCard.getChildren().addAll(
                questionTitle,
                questionBox,
                askButton
        );


        // -----------------------------------------------------
        // QUICK QUESTIONS
        // -----------------------------------------------------

        Label quickTitle =
                new Label(
                        "Quick Farming Questions"
                );

        quickTitle.setTextFill(
                DARK_TEXT
        );

        quickTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );


        HBox quickQuestions =
                new HBox();

        quickQuestions.setSpacing(15);


        quickQuestions.getChildren().addAll(

                createQuestionButton(
                        "🌾 Crop Recommendation"
                ),

                createQuestionButton(
                        "💧 Irrigation Advice"
                ),

                createQuestionButton(
                        "🌱 Soil Health"
                ),

                createQuestionButton(
                        "🐛 Disease Detection"
                )
        );


        content.getChildren().addAll(
                headerCard,
                questionCard,
                quickTitle,
                quickQuestions
        );


        return content;
    }


    // =========================================================
    // QUICK QUESTION BUTTON
    // =========================================================

    private Button createQuestionButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(
                55
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                button,
                Priority.ALWAYS
        );

        button.setTextFill(
                DARK_GREEN
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                LIGHT_GREEN,
                                new CornerRadii(12),
                                Insets.EMPTY
                        )
                )
        );


        button.setOnAction(event -> {

            System.out.println(
                    text + " clicked"
            );

        });


        return button;
    }
}