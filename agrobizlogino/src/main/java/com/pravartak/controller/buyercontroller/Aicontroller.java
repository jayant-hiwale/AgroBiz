package com.pravartak.controller.buyercontroller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class Aicontroller {

    private VBox chatBox;
    private TextField questionField;
    private Label statusLabel;

    public void setChatBox(VBox chatBox) {
        this.chatBox = chatBox;
    }

    public void setQuestionField(TextField questionField) {
        this.questionField = questionField;
    }

    public void setStatusLabel(Label statusLabel) {
        this.statusLabel = statusLabel;
    }

    public void sendQuestion() {
        String question = questionField.getText().trim();

        if (question.isEmpty()) {
            statusLabel.setText("Please type a question first.");
            return;
        }

        Label userQuestion = new Label("You: " + question);
        userQuestion.setWrapText(true);
        userQuestion.setStyle(
                "-fx-background-color: #125C31;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12;" +
                "-fx-font-size: 14px;"
        );

        Label reply = new Label("AI Assistant: " + getAnswer(question));
        reply.setWrapText(true);
        reply.setMaxWidth(760);
        reply.setStyle(
                "-fx-background-color: #163C29;" +
                "-fx-text-fill: #E8F5E9;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12;" +
                "-fx-font-size: 14px;"
        );

        chatBox.getChildren().addAll(userQuestion, reply);
        questionField.clear();
        statusLabel.setText("Response generated.");
    }

    public void uploadImage(Window owner) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Crop Image");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files", "*.png", "*.jpg", "*.jpeg"
                )
        );

        File selectedFile = chooser.showOpenDialog(owner);

        if (selectedFile != null) {
            statusLabel.setText("Selected image: " + selectedFile.getName());
        }
    }

    public void showPastSessions() {
        statusLabel.setText("Past Sessions: No saved conversations yet.");
    }

    private String getAnswer(String question) {
        String text = question.toLowerCase();

        if (text.contains("leaf") || text.contains("disease")) {
            return "Check the leaves for yellow spots, fungal growth, or insect damage. "
                    + "Remove affected leaves and avoid overwatering.";
        }

        if (text.contains("soil")) {
            return "For healthier soil, add compost, test the pH level, and use crop rotation.";
        }

        if (text.contains("water") || text.contains("irrigation")) {
            return "Water early in the morning and keep the soil moist, not flooded.";
        }

        return "Thanks for your question. Please share crop type, symptoms, and your location "
                + "for a more accurate farming recommendation.";
    }
}