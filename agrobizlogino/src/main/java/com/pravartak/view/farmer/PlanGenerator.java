package com.pravartak.view.farmer;

import com.pravartak.services.GroqService;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlanGenerator {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color DARK_GREEN = Color.web("#050B0A");
    private final Color GREEN = Color.web("#38a730");
    private final Color LIGHT_GREEN = Color.web("#B8D8B8");
    private final Color DARK_TEXT = Color.web("#F3F8F3");
    private final Color GREY = Color.web("#A9B8AC");
    private final Color CARD_BACKGROUND = Color.web("#15331F");
    private final Color BORDER_COLOR = Color.web("#294734");

    // =========================================================
    // GROQ SERVICE
    // =========================================================

    private final GroqService groqService = new GroqService();

    // =========================================================
    // FARMING PLAN DATA
    // =========================================================

    private final Map<String, String> farmingPlanAnswers =
            new LinkedHashMap<>();

    private final List<String> currentFarmingQuestionKeys =
            new ArrayList<>();

    private final List<String> currentFarmingQuestions =
            new ArrayList<>();

    private String selectedFarmingType = null;

    private int farmingQuestionIndex = 0;
    private ScrollPane aiMessageScroll;

    // =========================================================
    // AI CONTROLS
    // =========================================================

    private Label aiAssistantText;
    private TextField aiQuestionField;
    private Button aiAskButton;
    private Button generatePlanButton;

    // =========================================================
    // PLAN CONTROLS
    // =========================================================

    private VBox planContent;
    private ScrollPane planScroll;
    private Label planText;

    private VBox aiCard;
    private VBox aiCenter;

    private Button newPlanButton;

    // =========================================================
    // SCENE
    // =========================================================

    public Scene getAIAdvisorScene() {

        BorderPane root = new BorderPane();

        root.setPrefSize(
                1368,
                768
        );

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        root.setCenter(
                createAIAdvisorPage()
        );

        return new Scene(
                root,
                1368,
                768
        );
    }

    // =========================================================
    // AI ADVISOR PAGE
    // =========================================================

    private VBox createAIAdvisorPage() {

        // RESET STATE

        selectedFarmingType = null;

        farmingQuestionIndex = 0;

        farmingPlanAnswers.clear();

        currentFarmingQuestionKeys.clear();

        currentFarmingQuestions.clear();

        // =========================================================
        // MAIN PAGE
        // =========================================================

        VBox page = new VBox();

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =========================================================
        // TOP BAR
        // =========================================================

        HBox topBar = createTopBar(
                "AI Farming Advisor",
                "Get intelligent recommendations for your farm."
        );

        // =========================================================
        // PLAN VIEWER
        // =========================================================

        planContent = new VBox();

        planContent.setSpacing(18);

        planContent.setPadding(
                new Insets(25)
        );

        planContent.setFillWidth(true);

        planScroll = new ScrollPane(
                planContent
        );

        planScroll.setFitToWidth(true);

        planScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        planScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        planScroll.setVisible(false);

        planScroll.setManaged(false);

        planScroll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-control-inner-background: transparent;"
        );

        VBox.setVgrow(
                planScroll,
                Priority.ALWAYS
        );

        // =========================================================
        // CONTENT
        // =========================================================

        VBox content = new VBox();

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        content.setPadding(
                new Insets(
                        0,
                        35,
                        35,
                        35
                )
        );

        content.setSpacing(0);

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        // =========================================================
        // CARD
        // =========================================================

        aiCard = createWhiteCard();

        aiCard.setPrefHeight(650);

        aiCard.setMaxHeight(
                Double.MAX_VALUE
        );

        VBox.setVgrow(
                aiCard,
                Priority.ALWAYS
        );

        // =========================================================
        // CENTER
        // =========================================================

        aiCenter = new VBox();

        aiCenter.setAlignment(
                Pos.TOP_CENTER
        );

        aiCenter.setSpacing(25);

        aiCenter.setPadding(
                new Insets(20)
        );

        aiCenter.setFillWidth(true);

        VBox.setVgrow(
                aiCenter,
                Priority.ALWAYS
        );

        // =========================================================
        // AI ICON
        // =========================================================

        Label aiIcon = new Label("✦");

        aiIcon.setPrefSize(
                70,
                70
        );

        aiIcon.setAlignment(
                Pos.CENTER
        );

        aiIcon.setTextFill(
                Color.WHITE
        );

        aiIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        aiIcon.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(50),
                                Insets.EMPTY
                        )
                )
        );

        // =========================================================
        // PAGE TITLE
        // =========================================================

        Label question = new Label(
                "How can Agro Biz AI help your farm?"
        );

        question.setTextFill(
                Color.WHITE
        );

        question.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        question.setWrapText(true);

        question.setAlignment(
                Pos.CENTER
        );

        // =========================================================
        // QUICK SUGGESTIONS
        // =========================================================

        HBox suggestions = new HBox();

        suggestions.setSpacing(12);

        suggestions.setAlignment(
                Pos.CENTER
        );

        Button cropButton =
                createSuggestionButton(
                        "🌾  Which crop should I grow?"
                );

        Button yieldButton =
                createSuggestionButton(
                        "↗  How can I improve my yield?"
                );

        Button irrigationButton =
                createSuggestionButton(
                        "💧  Optimize irrigation schedule"
                );

        suggestions.getChildren().addAll(
                cropButton,
                yieldButton,
                irrigationButton
        );

        // =========================================================
        // GENERATE PLAN BUTTON
        // =========================================================

        generatePlanButton = new Button(
                "📋  Generate Personalized Farming Plan"
        );

        generatePlanButton.setPrefHeight(48);

        generatePlanButton.setPrefWidth(330);

        generatePlanButton.setTextFill(
                Color.BLACK
        );

        generatePlanButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        generatePlanButton.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );

        generatePlanButton.setCursor(
                Cursor.HAND
        );

        // =========================================================
        // INITIAL CENTER
        // =========================================================

        aiCenter.getChildren().addAll(
                aiIcon,
                question,
                
                generatePlanButton
        );

        // =========================================================
        // MESSAGE AREA
        // =========================================================

        HBox message = new HBox();

        message.setMaxWidth(
                Double.MAX_VALUE
        );

        message.setSpacing(12);

        message.setPadding(
                new Insets(20)
        );

        message.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(7, 22, 10),
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );
        // =====================================================
// AI ANSWER SCROLLPANE
// =====================================================

aiMessageScroll = new ScrollPane();

aiMessageScroll.setFitToWidth(true);
aiMessageScroll.setFitToHeight(false);

aiMessageScroll.setHbarPolicy(
        ScrollPane.ScrollBarPolicy.NEVER);

aiMessageScroll.setVbarPolicy(
        ScrollPane.ScrollBarPolicy.AS_NEEDED);

aiMessageScroll.setPrefHeight(150);
aiMessageScroll.setMinHeight(100);
aiMessageScroll.setMaxHeight(220);

aiMessageScroll.setStyle(
        "-fx-background-color:transparent;" +
        "-fx-background:transparent;" +
        "-fx-control-inner-background:transparent;" +
        "-fx-padding:0;" +
        "-fx-border-width:0;"
);
aiAssistantText = new Label(
        "Hello Farmer! 🌱\n\n"
        + "I can help you make smarter farming "
        + "decisions and create a personalized "
        + "farming plan based on your farm "
        + "resources, capacity and goals.");

aiAssistantText.setWrapText(
        true);
        aiAssistantText.setMaxWidth(
        Double.MAX_VALUE);

aiAssistantText.setPadding(
        Insets.EMPTY);


        // =========================================================
        // ASSISTANT ICON
        // =========================================================

        Label assistantIcon =
                new Label("✦");

        assistantIcon.setPrefSize(
                42,
                42
        );

        assistantIcon.setMinSize(
                42,
                42
        );

        assistantIcon.setAlignment(
                Pos.CENTER
        );

        assistantIcon.setTextFill(
                Color.WHITE
        );

        assistantIcon.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(50),
                                Insets.EMPTY
                        )
                )
        );

        // =========================================================
        // ASSISTANT TEXT
        // =========================================================

        aiAssistantText =
                new Label(
                        "Hello Farmer! 🌱\n\n"
                        + "I can help you make smarter farming "
                        + "decisions and create a personalized "
                        + "farming plan based on your farm "
                        + "resources, capacity and goals."
                );

        aiAssistantText.setWrapText(true);

        aiAssistantText.setTextFill(
                GREY
        );

        aiAssistantText.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

                // message.getChildren().addAll(
                //         assistantIcon,
                //         aiAssistantText
                // );
                // Put only the answer text inside ScrollPane
aiMessageScroll.setContent(
        aiAssistantText);
message.getChildren().addAll(
        assistantIcon,
        aiMessageScroll);

HBox.setHgrow(
        aiMessageScroll,
        Priority.ALWAYS);
        // =========================================================
        // PLAN TEXT
        // =========================================================

        planText = new Label();

        planText.setWrapText(true);

        planText.setMaxWidth(
                Double.MAX_VALUE
        );

        planText.setTextFill(
                Color.web("#C8D8CC")
        );

        planText.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        planText.setPadding(
                new Insets(5)
        );

        // =========================================================
        // NEW PLAN BUTTON
        // =========================================================

        newPlanButton =
                new Button(
                        "← New Farming Plan"
                );

        newPlanButton.setPrefHeight(45);

        newPlanButton.setPrefWidth(180);

        newPlanButton.setTextFill(
                Color.WHITE
        );

        newPlanButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        newPlanButton.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(9),
                                Insets.EMPTY
                        )
                )
        );

        newPlanButton.setCursor(
                Cursor.HAND
        );

        // =========================================================
        // NEW PLAN ACTION
        // =========================================================

        newPlanButton.setOnAction(
                event -> {

                    resetAIPage(
                            aiCenter,
                            question,
                            suggestions,
                            generatePlanButton
                    );
                }
        );

        // =========================================================
        // QUESTION FIELD
        // =========================================================

        aiQuestionField =
                new TextField();

        aiQuestionField.setPromptText(
                "Ask your farming question..."
        );

        aiQuestionField.setPrefHeight(50);
        aiQuestionField.setPrefHeight(50);
aiQuestionField.setMinHeight(50);
aiQuestionField.setMaxHeight(50);

        aiQuestionField.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        aiQuestionField.setStyle(
                "-fx-background-color:#101716;" +
                "-fx-text-fill:#E8F5E9;" +
                "-fx-prompt-text-fill:#7F9185;" +
                "-fx-border-color:#2B4535;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-focus-color:transparent;" +
                "-fx-faint-focus-color:transparent;"
        );

        aiQuestionField.setOnAction(
                event -> {

                    if (aiAskButton != null) {
                        aiAskButton.fire();
                    }
                }
        );

        // =========================================================
        // ASK AI BUTTON
        // =========================================================

        aiAskButton =
                new Button(
                        "Ask AI  ➤"
                );

        aiAskButton.setPrefHeight(50);

        aiAskButton.setPrefWidth(120);

        aiAskButton.setTextFill(
                Color.BLACK
        );

        aiAskButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        aiAskButton.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(9),
                                Insets.EMPTY
                        )
                )
        );

        aiAskButton.setCursor(
                Cursor.HAND
        );

        // =========================================================
        // ASK AI ACTION
        // =========================================================

        aiAskButton.setOnAction(
                event -> {

                    String userQuestion =
                            aiQuestionField
                                    .getText()
                                    .trim();

                    if (userQuestion.isEmpty()) {
                        return;
                    }

                    if (selectedFarmingType != null) {

                        processFarmingPlanAnswer();

                        return;
                    }

                    askNormalAIQuestion(
                            userQuestion
                    );
                }
        );

        // =========================================================
        // INPUT
        // =========================================================

        HBox input =
                new HBox(
                        aiQuestionField,
                        aiAskButton
                );

        input.setSpacing(12);

        HBox.setHgrow(
                aiQuestionField,
                Priority.ALWAYS
        );

        // =========================================================
        // QUICK QUESTION ACTIONS
        // =========================================================

        cropButton.setOnAction(
                event -> {

                    aiQuestionField.setText(
                            "Which crop should I grow?"
                    );

                    aiAskButton.fire();
                }
        );

        yieldButton.setOnAction(
                event -> {

                    aiQuestionField.setText(
                            "How can I improve my yield?"
                    );

                    aiAskButton.fire();
                }
        );

        irrigationButton.setOnAction(
                event -> {

                    aiQuestionField.setText(
                            "How can I optimize my irrigation schedule?"
                    );

                    aiAskButton.fire();
                }
        );

        // =========================================================
        // START FARMING PLAN
        // =========================================================

        generatePlanButton.setOnAction(
                event -> {

                    startFarmingPlan(
                            aiCenter,
                            question,
                            suggestions,
                            generatePlanButton
                    );
                }
        );

        // =========================================================
        // ADD CONTENT
        // =========================================================

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        aiCenter.getChildren().addAll(
                message,
                spacer,
                input
        );

        aiCard.getChildren().add(
                aiCenter
        );

        content.getChildren().add(
                aiCard
        );

        VBox.setVgrow(
                aiCard,
                Priority.ALWAYS
        );

        // =========================================================
        // MAIN SCROLL
        // =========================================================

        ScrollPane scroll =
                new ScrollPane(
                        content
                );

        scroll.setFitToWidth(true);

        scroll.setFitToHeight(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scroll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-control-inner-background: transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        page.getChildren().addAll(
                topBar,
                scroll
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        return page;
    }
     public VBox getPage() {

        return createAIAdvisorPage();
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    private HBox createTopBar(
            String titleText,
            String subtitleText) {

        HBox bar = new HBox();

        bar.setPrefHeight(100);

        bar.setPadding(
                new Insets(
                        18,
                        35,
                        18,
                        35
                )
        );

        bar.setAlignment(
                Pos.CENTER_LEFT
        );

        bar.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        VBox titleBox = new VBox();

        titleBox.setSpacing(3);

        Label title =
                new Label(
                        titleText
                );

        title.setTextFill(
                Color.WHITE
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        Label subtitle =
                new Label(
                        subtitleText
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

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        bar.getChildren().add(
                titleBox
        );

        return bar;
    }

    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox createWhiteCard() {

        VBox card =
                new VBox();

        card.setPadding(
                new Insets(22)
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER_COLOR,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(15),
                                new BorderWidths(1)
                        )
                )
        );

        return card;
    }

    // =========================================================
    // SUGGESTION BUTTON
    // =========================================================

    private Button createSuggestionButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(40);

        // button.setTextFill(
        //         DARK_GREEN
        // );

        button.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        // button.setBackground(
        //         new Background(
        //                 new BackgroundFill(
        //                         Color.web("#173523"),
        //                         new CornerRadii(20),
        //                         Insets.EMPTY
        //                 )
        //         )
        // );
       
        button.setBackground(
        new Background(
                new BackgroundFill(
                        Color.web("#2E7D32"),
                        new CornerRadii(12),
                        Insets.EMPTY)));
        

        button.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER_COLOR,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(20),
                                new BorderWidths(1)
                        )
                )
        );
         button.setTextFill(
                Color.BLACK
        );

        button.setCursor(
                Cursor.HAND
        );

        return button;
    }

    // =========================================================
    // NORMAL AI QUESTION
    // =========================================================

    private void askNormalAIQuestion(
            String userQuestion) {

        if (userQuestion == null ||
                userQuestion.trim().isEmpty()) {

            return;
        }

        aiAssistantText.setText(
                "🌱 AgroBiz AI is thinking..."
        );

        aiAskButton.setDisable(true);

        aiQuestionField.setDisable(true);

        Task<String> task =
                new Task<String>() {

                    @Override
                    protected String call()
                            throws Exception {

                        return groqService.askQuestion(
                                userQuestion
                        );
                    }
                };

        task.setOnSucceeded(
                event -> {

                    aiAssistantText.setText(
                            task.getValue()
                    );

                    aiAskButton.setDisable(false);

                    aiQuestionField.setDisable(false);

                    aiQuestionField.clear();
                }
        );

        task.setOnFailed(
                event -> {

                    Throwable error =
                            task.getException();

                    String errorMessage =
                            "Unable to contact AgroBiz AI.";

                    if (error != null &&
                            error.getMessage() != null) {

                        errorMessage +=
                                "\n\nError: " +
                                error.getMessage();
                    }

                    aiAssistantText.setText(
                            errorMessage
                    );

                    aiAskButton.setDisable(false);

                    aiQuestionField.setDisable(false);
                }
        );

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // =========================================================
    // START FARMING PLAN
    // =========================================================

    private void startFarmingPlan(
            VBox center,
            Label question,
            HBox suggestions,
            Button planButton) {

        selectedFarmingType = null;

        farmingQuestionIndex = 0;

        farmingPlanAnswers.clear();

        currentFarmingQuestionKeys.clear();

        currentFarmingQuestions.clear();

        question.setText(
                "Let's create your personalized farming plan 🌱"
        );

        aiAssistantText.setText(
                "Great! 🌱\n\n"
                + "I will ask you a few questions about "
                + "your farm. Your answers will be used "
                + "to prepare a practical farming plan "
                + "with setup requirements, estimated "
                + "costs, timeline, risks and management steps."
        );

        planButton.setVisible(false);

        planButton.setManaged(false);

        suggestions.getChildren().clear();

        createFarmingTypeButtons(
                center,
                question,
                planButton
        );

        aiQuestionField.setDisable(true);

        aiAskButton.setDisable(true);

        aiQuestionField.setPromptText(
                "Select a farming type above..."
        );
    }

    // =========================================================
    // FARMING TYPE BUTTONS
    // =========================================================

    private void createFarmingTypeButtons(
            VBox center,
            Label question,
            Button planButton) {

        GridPane farmingGrid =
                new GridPane();

        farmingGrid.setHgap(12);

        farmingGrid.setVgap(12);

        farmingGrid.setAlignment(
                Pos.CENTER
        );

        String[][] farmingTypes = {

                {"🐔 Poultry", "Poultry"},
                {"🐐 Goat", "Goat"},
                {"🍄 Mushroom", "Mushroom"},
                {"🐄 Dairy / Cow", "Dairy / Cow"},
                {"🦪 Pearl", "Pearl"},
                {"🐟 Fish", "Fish"},
                {"🌿 Moringa", "Moringa"},
                {"🌾 Crop", "Crop"}
        };

        int column = 0;

        int row = 0;

        for (String[] farmingType :
                farmingTypes) {

            Button button =
                    createFarmingTypeButton(
                            farmingType[0]
                    );

            button.setOnAction(
                    event -> {

                        selectFarmingType(
                                farmingType[1],
                                question,
                                farmingGrid,
                                planButton
                        );
                    }
            );

            farmingGrid.add(
                    button,
                    column,
                    row
            );

            column++;

            if (column == 2) {

                column = 0;

                row++;
            }
        }

        center.getChildren().add(
                2,
                farmingGrid
        );
    }

    // =========================================================
    // FARMING TYPE BUTTON
    // =========================================================

    private Button createFarmingTypeButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefWidth(230);

        button.setPrefHeight(48);

        button.setTextFill(
                Color.BLACK
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        button.setPadding(
                Insets.EMPTY
        );

        button.setBorder(
                Border.EMPTY
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#173523"),
                                new CornerRadii(12),
                                Insets.EMPTY
                        )
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        return button;
    }

    // =========================================================
    // SELECT FARMING TYPE
    // =========================================================

    private void selectFarmingType(
            String farmingType,
            Label question,
            GridPane farmingGrid,
            Button planButton) {

        selectedFarmingType =
                farmingType;

        farmingQuestionIndex = 0;

        farmingPlanAnswers.clear();

        currentFarmingQuestionKeys.clear();

        currentFarmingQuestions.clear();

        farmingPlanAnswers.put(
                "Farming Type",
                farmingType
        );

        buildFarmingQuestions(
                farmingType
        );

        question.setText(
                "Selected: "
                + farmingType
                + "\n\n"
                + currentFarmingQuestions.get(0)
        );

        aiAssistantText.setText(
                "Excellent choice! 🌱\n\n"
                + "I will now collect the information "
                + "required to prepare your "
                + farmingType
                + " farming plan."
        );

        aiQuestionField.clear();

        aiQuestionField.setDisable(false);

        aiAskButton.setDisable(false);

        aiAskButton.setText(
                "Next  ➤"
        );

        aiQuestionField.setPromptText(
                "Enter your answer..."
        );

        farmingGrid.setDisable(true);

        planButton.setVisible(false);

        planButton.setManaged(false);
    }

    // =========================================================
    // BUILD FARMING QUESTIONS
    // =========================================================

    private void buildFarmingQuestions(
            String farmingType) {

        currentFarmingQuestionKeys.clear();

        currentFarmingQuestions.clear();

        // COMMON QUESTIONS

        addFarmingQuestion(
                "Location",
                "Which district and state is your farm located in?"
        );

        addFarmingQuestion(
                "Area",
                "How much land or farming area do you have?"
        );

        addFarmingQuestion(
                "Capacity",
                "What capacity are you planning for this farm?"
        );

        addFarmingQuestion(
                "Budget",
                "What is your approximate budget in Indian Rupees?"
        );

        addFarmingQuestion(
                "Water",
                "Do you have a reliable water source? Please describe it."
        );

        addFarmingQuestion(
                "Electricity",
                "Is electricity available at your farm?"
        );

        addFarmingQuestion(
                "Infrastructure",
                "Do you already have any shed, pond, room, equipment or other infrastructure?"
        );

        addFarmingQuestion(
                "Labour",
                "How many people can work on the farm?"
        );

        addFarmingQuestion(
                "Experience",
                "What is your farming experience level? Beginner, some experience, or experienced?"
        );

        addFarmingQuestion(
                "Market",
                "How do you plan to sell your farm products?"
        );

        // FARMING-SPECIFIC QUESTIONS

        switch (farmingType) {

            case "Poultry":

                addFarmingQuestion(
                        "Poultry Purpose",
                        "Is your poultry farm for meat, eggs, or both?"
                );

                addFarmingQuestion(
                        "Bird Number",
                        "How many birds are you planning to rear?"
                );

                addFarmingQuestion(
                        "Poultry Breed",
                        "Do you have a preferred poultry breed or type?"
                );

                addFarmingQuestion(
                        "Poultry Shed",
                        "Do you already have a poultry shed? If yes, describe its approximate size."
                );

                addFarmingQuestion(
                        "Feed",
                        "Do you have access to poultry feed or local feed ingredients?"
                );

                break;

            case "Goat":

                addFarmingQuestion(
                        "Goat Purpose",
                        "Is your goat farm mainly for meat, breeding, milk, or a combination?"
                );

                addFarmingQuestion(
                        "Goat Number",
                        "How many goats are you planning to keep?"
                );

                addFarmingQuestion(
                        "Goat Breed",
                        "Do you have a preferred goat breed?"
                );

                addFarmingQuestion(
                        "Grazing",
                        "Do you have grazing land or access to fodder?"
                );

                addFarmingQuestion(
                        "Goat Shed",
                        "Do you already have a goat shed?"
                );

                break;

            case "Mushroom":

                addFarmingQuestion(
                        "Mushroom Type",
                        "Which mushroom do you want to cultivate?"
                );

                addFarmingQuestion(
                        "Growing Area",
                        "How much growing-room area is available?"
                );

                addFarmingQuestion(
                        "Growing Room",
                        "Do you already have a suitable mushroom growing room?"
                );

                addFarmingQuestion(
                        "Substrate",
                        "What substrate or agricultural waste materials are available to you?"
                );

                addFarmingQuestion(
                        "Temperature",
                        "Do you have facilities for temperature and humidity management?"
                );

                break;

            case "Dairy / Cow":

                addFarmingQuestion(
                        "Cattle Number",
                        "How many cattle are you planning to keep?"
                );

                addFarmingQuestion(
                        "Dairy Purpose",
                        "Is your main goal milk production, breeding, or both?"
                );

                addFarmingQuestion(
                        "Cattle Breed",
                        "Do you have a preferred cattle breed?"
                );

                addFarmingQuestion(
                        "Fodder",
                        "Do you have access to green fodder or other feed resources?"
                );

                addFarmingQuestion(
                        "Cattle Shed",
                        "Do you already have a cattle shed?"
                );

                break;

            case "Pearl":

                addFarmingQuestion(
                        "Water Area",
                        "How much pond or suitable water area is available?"
                );

                addFarmingQuestion(
                        "Pearl Method",
                        "Do you have a preferred pearl culture method?"
                );

                addFarmingQuestion(
                        "Water Quality",
                        "Do you know the current water quality or water source?"
                );

                addFarmingQuestion(
                        "Mussel Availability",
                        "Do you have access to suitable freshwater mussels?"
                );

                break;

            case "Fish":

                addFarmingQuestion(
                        "Pond Area",
                        "What is the available pond area?"
                );

                addFarmingQuestion(
                        "Fish Species",
                        "Which fish species do you want to culture?"
                );

                addFarmingQuestion(
                        "Pond Condition",
                        "Is the pond already constructed and suitable for fish culture?"
                );

                addFarmingQuestion(
                        "Water Source",
                        "What is the main source of water for the pond?"
                );

                break;

            case "Moringa":

                addFarmingQuestion(
                        "Moringa Purpose",
                        "Are you growing moringa mainly for leaves, pods, seed, or another purpose?"
                );

                addFarmingQuestion(
                        "Moringa Variety",
                        "Do you have a preferred moringa variety?"
                );

                addFarmingQuestion(
                        "Planting Time",
                        "When are you planning to start planting?"
                );

                addFarmingQuestion(
                        "Irrigation",
                        "What irrigation facility is available?"
                );

                break;

            case "Crop":

                addFarmingQuestion(
                        "Crop Type",
                        "Which crop or group of crops are you considering?"
                );

                addFarmingQuestion(
                        "Soil",
                        "Do you know your soil type or recent soil-test results?"
                );

                addFarmingQuestion(
                        "Season",
                        "Which season are you planning to cultivate?"
                );

                addFarmingQuestion(
                        "Irrigation",
                        "What irrigation facility is available?"
                );

                break;
        }
    }

    // =========================================================
    // ADD QUESTION
    // =========================================================

    private void addFarmingQuestion(
            String key,
            String question) {

        currentFarmingQuestionKeys.add(
                key
        );

        currentFarmingQuestions.add(
                question
        );
    }

    // =========================================================
    // PROCESS ANSWER
    // =========================================================

    private void processFarmingPlanAnswer() {

        String answer =
                aiQuestionField
                        .getText()
                        .trim();

        if (answer.isEmpty()) {
            return;
        }

        if (farmingQuestionIndex < 0 ||
                farmingQuestionIndex >=
                        currentFarmingQuestions.size()) {

            return;
        }

        String key =
                currentFarmingQuestionKeys.get(
                        farmingQuestionIndex
                );

        farmingPlanAnswers.put(
                key,
                answer
        );

        farmingQuestionIndex++;

        aiQuestionField.clear();

        if (farmingQuestionIndex <
                currentFarmingQuestions.size()) {

            String nextQuestion =
                    currentFarmingQuestions.get(
                            farmingQuestionIndex
                    );

            aiAssistantText.setText(
                    "Thank you! 🌱\n\n"
                    + "Your answer has been recorded."
            );

            aiQuestionField.setPromptText(
                    "Enter your answer..."
            );

            aiAskButton.setText(
                    "Next  ➤"
            );

            updateFarmingQuestionDisplay(
                    nextQuestion
            );

            return;
        }

        finishFarmingPlanQuestions();
    }

    // =========================================================
    // UPDATE QUESTION
    // =========================================================

    private void updateFarmingQuestionDisplay(
            String question) {

        aiAssistantText.setText(
                "🌱 " + question
        );

        aiQuestionField.setPromptText(
                "Type your answer here..."
        );
    }

    // =========================================================
    // FINISH QUESTIONS
    // =========================================================

    private void finishFarmingPlanQuestions() {

        StringBuilder summary =
                new StringBuilder();

        summary.append(
                "Great! 🌱\n\n"
        );

        summary.append(
                "I have collected the information needed "
                + "for your "
                + selectedFarmingType
                + " farming plan.\n\n"
        );

        summary.append(
                "Your information:\n\n"
        );

        for (Map.Entry<String, String> entry :
                farmingPlanAnswers.entrySet()) {

            summary.append(
                    "• "
                    + entry.getKey()
                    + ": "
                    + entry.getValue()
                    + "\n"
            );
        }

        summary.append(
                "\nEverything looks ready."
        );

        summary.append(
                "\n\nClick \"Generate Plan\" to create "
                + "your personalized farming plan."
        );

        aiAssistantText.setText(
                summary.toString()
        );

        aiQuestionField.clear();

        aiQuestionField.setDisable(true);

        aiAskButton.setText(
                "Generate Plan"
        );

        aiAskButton.setDisable(false);

        aiAskButton.setOnAction(
                event ->
                        generateFinalFarmingPlan()
        );
    }

    // =========================================================
    // GENERATE FINAL FARMING PLAN
    // =========================================================

    private void generateFinalFarmingPlan() {

        if (selectedFarmingType == null ||
                selectedFarmingType.isBlank()) {

            return;
        }

        if (farmingPlanAnswers.isEmpty()) {
            return;
        }

        aiAssistantText.setText(
                "🌱 AgroBiz AI is preparing your "
                + selectedFarmingType
                + " farming plan...\n\n"
                + "Please wait."
        );

        aiAskButton.setDisable(true);

        aiQuestionField.setDisable(true);

        Task<String> task =
                new Task<String>() {

                    @Override
                    protected String call()
                            throws Exception {

                        return groqService
                                .generateFarmingPlan(
                                        selectedFarmingType,
                                        farmingPlanAnswers
                                );
                    }
                };

        task.setOnSucceeded(
                event -> {

                    String result =
                            task.getValue();

                    planText.setText(
                            result
                    );

                    planContent.getChildren().clear();

                    planContent.getChildren().add(
                            planText
                    );

                    planScroll.setVisible(true);

                    planScroll.setManaged(true);

                    aiCenter.setVisible(false);

                    aiCenter.setManaged(false);

                    VBox.setVgrow(
                            planScroll,
                            Priority.ALWAYS
                    );

                    aiCard.getChildren().clear();

                    aiCard.getChildren().add(
                            planScroll
                    );

                    aiCard.getChildren().add(
                            newPlanButton
                    );

                    planScroll.setVvalue(0);

                    aiAskButton.setText(
                            "Ask AI  ➤"
                    );

                    aiAskButton.setDisable(false);

                    aiQuestionField.setDisable(false);

                    aiQuestionField.clear();

                    aiQuestionField.setPromptText(
                            "Ask another farming question..."
                    );

                    selectedFarmingType = null;

                    farmingQuestionIndex = 0;

                    farmingPlanAnswers.clear();

                    currentFarmingQuestionKeys.clear();

                    currentFarmingQuestions.clear();

                    aiAskButton.setOnAction(
                            e -> {

                                String newQuestion =
                                        aiQuestionField
                                                .getText()
                                                .trim();

                                if (!newQuestion.isEmpty()) {

                                    askNormalAIQuestion(
                                            newQuestion
                                    );
                                }
                            }
                    );
                }
        );

        task.setOnFailed(
                event -> {

                    Throwable error =
                            task.getException();

                    String errorMessage =
                            "Sorry Farmer, I could not "
                            + "generate your farming plan.";

                    if (error != null &&
                            error.getMessage() != null) {

                        errorMessage +=
                                "\n\nError: "
                                + error.getMessage();
                    }

                    aiAssistantText.setText(
                            errorMessage
                    );

                    aiAskButton.setText(
                            "Generate Plan"
                    );

                    aiAskButton.setDisable(false);

                    aiQuestionField.setDisable(true);
                }
        );

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // =========================================================
    // RESET AI PAGE
    // =========================================================

    private void resetAIPage(
            VBox center,
            Label question,
            HBox suggestions,
            Button planButton) {

        selectedFarmingType = null;

        farmingQuestionIndex = 0;

        farmingPlanAnswers.clear();

        currentFarmingQuestionKeys.clear();

        currentFarmingQuestions.clear();

        planContent.getChildren().clear();

        planText.setText("");

        planScroll.setVisible(false);

        planScroll.setManaged(false);

        center.setVisible(true);

        center.setManaged(true);

        question.setText(
                "How can Agro Biz AI help your farm?"
        );

        suggestions.getChildren().clear();

        Button cropButton =
                createSuggestionButton(
                        "🌾  Which crop should I grow?"
                );

        Button yieldButton =
                createSuggestionButton(
                        "↗  How can I improve my yield?"
                );

        Button irrigationButton =
                createSuggestionButton(
                        "💧  Optimize irrigation schedule"
                );

        suggestions.getChildren().addAll(
                cropButton,
                yieldButton,
                irrigationButton
        );

        cropButton.setOnAction(
                event -> {

                    aiQuestionField.setText(
                            "Which crop should I grow?"
                    );

                    aiAskButton.fire();
                }
        );

        yieldButton.setOnAction(
                event -> {

                    aiQuestionField.setText(
                            "How can I improve my yield?"
                    );

                    aiAskButton.fire();
                }
        );

        irrigationButton.setOnAction(
                event -> {

                    aiQuestionField.setText(
                            "How can I optimize my irrigation schedule?"
                    );

                    aiAskButton.fire();
                }
        );

        planButton.setVisible(true);

        planButton.setManaged(true);

        aiQuestionField.setDisable(false);

        aiAskButton.setDisable(false);

        aiAskButton.setText(
                "Ask AI  ➤"
        );

        aiQuestionField.clear();

        aiQuestionField.setPromptText(
                "Ask your farming question..."
        );

        aiAskButton.setOnAction(
                e -> {

                    String questionText =
                            aiQuestionField
                                    .getText()
                                    .trim();

                    if (!questionText.isEmpty()) {

                        askNormalAIQuestion(
                                questionText
                        );
                    }
                }
        );

        aiCard.getChildren().clear();

        aiCard.getChildren().add(
                center
        );
    }
}