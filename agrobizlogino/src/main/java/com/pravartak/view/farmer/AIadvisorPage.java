package com.pravartak.view.farmer;

import java.io.File;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

public class AIAdvisorPage {

        private static final Color BG = Color.rgb(3, 18, 14);
        private static final Color CARD = Color.rgb(7, 39, 30);
        private static final Color DARK_GREEN = Color.rgb(10, 55, 40);
        private static final Color GREEN = Color.rgb(45, 190, 75);
        private static final Color LIGHT_GREEN = Color.rgb(20, 65, 45);
        private static final Color DARK_TEXT = Color.rgb(236, 240, 225);
        private static final Color GREY = Color.rgb(150, 175, 160);
        private static final Color BORDER = Color.rgb(88, 243, 186);

        private static File selectedImage;
        private static ImageView imageView;

        public static Scene getAIAdvisorScene() {

                BorderPane root = new BorderPane();

                root.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                BG,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                root.setTop(createTopBar());
                root.setCenter(createContent());

                return new Scene(root, 1368, 768);
        }

        private static HBox createTopBar() {

                HBox topBar = new HBox();

                topBar.setPrefHeight(82);
                topBar.setPadding(new Insets(18, 30, 18, 30));
                topBar.setAlignment(Pos.CENTER_LEFT);

                topBar.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                CARD,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                topBar.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                CornerRadii.EMPTY,
                                                                new BorderWidths(0, 0, 1, 0))));

                Label title = new Label("AI Farming Advisor");

                title.setTextFill(DARK_TEXT);
                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                24));

                Label subtitle = new Label(
                                "Get smart recommendations for your farm");

                subtitle.setTextFill(GREY);
                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                VBox titleBox = new VBox(3);
                titleBox.getChildren().addAll(title, subtitle);

                topBar.getChildren().add(titleBox);

                return topBar;
        }

        private static VBox createContent() {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(25, 30, 30, 30));

                VBox headerCard = new VBox(7);

                headerCard.setPadding(new Insets(22));

                headerCard.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                DARK_GREEN,
                                                                new CornerRadii(16),
                                                                Insets.EMPTY)));

                Label heading = new Label(
                                "🌱 Your Personal AI Farming Advisor");

                heading.setTextFill(Color.WHITE);
                heading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                24));

                Label description = new Label(
                                "Ask questions about crops, irrigation, soil, fertilizers, diseases and farming decisions.");

                description.setTextFill(
                                Color.rgb(220, 235, 220));

                description.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                description.setWrapText(true);

                headerCard.getChildren().addAll(
                                heading,
                                description);

                VBox questionCard = new VBox(12);

                questionCard.setPadding(new Insets(22));

                questionCard.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                CARD,
                                                                new CornerRadii(14),
                                                                Insets.EMPTY)));

                questionCard.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(14),
                                                                new BorderWidths(1))));

                Label questionTitle = new Label(
                                "Ask your farming question");

                questionTitle.setTextFill(DARK_TEXT);

                questionTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                TextArea questionBox = new TextArea();

                questionBox.setPromptText(
                                "Example: Which fertilizer should I use for wheat?");

                questionBox.setPrefHeight(110);
                questionBox.setWrapText(true);
                questionBox.setFont(
                                Font.font(
                                                "Arial",
                                                15));

                questionBox.setStyle(
                                "-fx-control-inner-background: #07271e;" +
                                                "-fx-text-fill: #e1f0e4;" +
                                                "-fx-prompt-text-fill: #96afa0;" +
                                                "-fx-border-color: #124331;" +
                                                "-fx-border-radius: 10;" +
                                                "-fx-background-radius: 10;");

                HBox imageArea = createImageArea();

                HBox buttons = new HBox(12);

                buttons.setAlignment(Pos.CENTER_LEFT);

                Button attachButton = new Button(
                                "📎 Attach Image");

                styleAttachButton(attachButton);

                Button removeButton = new Button(
                                "Remove Image");

                styleRemoveButton(removeButton);
                removeButton.setVisible(false);

                attachButton.setOnAction(event -> {

                        FileChooser chooser = new FileChooser();

                        chooser.setTitle("Select Farm Image");

                        chooser.getExtensionFilters().add(
                                        new FileChooser.ExtensionFilter(
                                                        "Image Files",
                                                        "*.png",
                                                        "*.jpg",
                                                        "*.jpeg"));

                        File file = chooser.showOpenDialog(null);

                        if (file != null) {

                                selectedImage = file;

                                imageView.setImage(
                                                new Image(
                                                                file.toURI().toString()));

                                imageView.setFitWidth(150);
                                imageView.setFitHeight(100);
                                imageView.setPreserveRatio(true);

                                imageArea.setVisible(true);
                                removeButton.setVisible(true);
                        }
                });

                removeButton.setOnAction(event -> {

                        selectedImage = null;

                        imageView.setImage(null);

                        imageArea.setVisible(false);
                        removeButton.setVisible(false);
                });

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button askButton = new Button(
                                "Ask AI  ✦");

                styleAskButton(askButton);

                askButton.setOnAction(event -> {

                        String question = questionBox.getText().trim();

                        if (question.isEmpty()) {

                                questionBox.setPromptText(
                                                "Please enter your farming question.");

                                return;
                        }

                        if (selectedImage != null) {

                                System.out.println(
                                                "Question: " + question);

                                System.out.println(
                                                "Image: "
                                                                + selectedImage.getAbsolutePath());

                        } else {

                                System.out.println(
                                                "Question: " + question);

                                System.out.println(
                                                "No image attached.");
                        }

                        questionBox.clear();
                });

                buttons.getChildren().addAll(
                                attachButton,
                                removeButton,
                                spacer,
                                askButton);

                questionCard.getChildren().addAll(
                                questionTitle,
                                questionBox,
                                imageArea,
                                buttons);

                Label quickTitle = new Label(
                                "Quick Farming Questions");

                quickTitle.setTextFill(DARK_TEXT);

                quickTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                HBox quickQuestions = new HBox(12);

                quickQuestions.getChildren().addAll(
                                createQuestionButton(
                                                "🌾 Crop Recommendation",
                                                questionBox),
                                createQuestionButton(
                                                "💧 Irrigation Advice",
                                                questionBox),
                                createQuestionButton(
                                                "🌱 Soil Health",
                                                questionBox),
                                createQuestionButton(
                                                "🐛 Disease Detection",
                                                questionBox));

                content.getChildren().addAll(
                                headerCard,
                                questionCard,
                                quickTitle,
                                quickQuestions);

                return content;
        }

        private static HBox createImageArea() {

                HBox area = new HBox();

                area.setPrefHeight(110);
                area.setPadding(new Insets(5));
                area.setAlignment(Pos.CENTER_LEFT);
                area.setSpacing(12);
                area.setVisible(false);

                imageView = new ImageView();

                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);

                Label imageText = new Label(
                                "Attached farm image");

                imageText.setTextFill(GREY);

                imageText.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                area.getChildren().addAll(
                                imageView,
                                imageText);

                return area;
        }

        private static Button createQuestionButton(
                        String text,
                        TextArea questionBox) {

                Button button = new Button(text);

                button.setPrefHeight(48);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                HBox.setHgrow(
                                button,
                                Priority.ALWAYS);

                button.setTextFill(
                                DARK_TEXT);

                button.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                button.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                LIGHT_GREEN,
                                                                new CornerRadii(12),
                                                                Insets.EMPTY)));

                button.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(12),
                                                                new BorderWidths(1))));

                button.setOnAction(event -> {

                        if (text.contains("Crop")) {

                                questionBox.setText(
                                                "Which crop is most suitable for my farm?");

                        } else if (text.contains("Irrigation")) {

                                questionBox.setText(
                                                "What irrigation method should I use for my crop?");

                        } else if (text.contains("Soil")) {

                                questionBox.setText(
                                                "How can I improve the health of my soil?");

                        } else {

                                questionBox.setText(
                                                "What disease is affecting my crop and how can I treat it?");
                        }
                });

                return button;
        }

        private static void styleAskButton(Button button) {

                button.setPrefSize(
                                145,
                                48);

                button.setTextFill(
                                Color.WHITE);

                button.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                button.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                GREEN,
                                                                new CornerRadii(25),
                                                                Insets.EMPTY)));
        }

        private static void styleAttachButton(Button button) {

                button.setPrefHeight(42);

                button.setTextFill(
                                DARK_TEXT);

                button.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                button.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                LIGHT_GREEN,
                                                                new CornerRadii(20),
                                                                Insets.EMPTY)));

                button.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                BORDER,
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(20),
                                                                new BorderWidths(1))));
        }

        private static void styleRemoveButton(Button button) {

                button.setPrefHeight(42);

                button.setTextFill(
                                Color.rgb(230, 120, 120));

                button.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                button.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.rgb(55, 25, 25),
                                                                new CornerRadii(20),
                                                                Insets.EMPTY)));

                button.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                Color.rgb(100, 50, 50),
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(20),
                                                                new BorderWidths(1))));
        }
}