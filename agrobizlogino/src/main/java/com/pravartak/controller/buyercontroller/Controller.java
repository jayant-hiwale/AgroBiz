package com.pravartak.controller.buyercontroller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class Controller {private VBox chatBox;
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

public void showPastSessions() {
    if (statusLabel != null) {
        statusLabel.setText("Past Sessions: No saved sessions available.");
    }
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

    if (selectedFile != null && statusLabel != null) {
        statusLabel.setText("Selected image: " + selectedFile.getName());
    }
}

public void sendQuestion() {
    if (questionField == null || chatBox == null) {
        return;
    }

    String question = questionField.getText().trim();

    if (question.isEmpty()) {
        if (statusLabel != null) {
            statusLabel.setText("Please type a question first.");
        }
        return;
    }

    Label userMessage = new Label("You: " + question);
    userMessage.setWrapText(true);
    userMessage.setStyle(
            "-fx-background-color: #125C31;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 12;" +
            "-fx-padding: 12;"
    );

    Label aiMessage = new Label(
            "AI Assistant: Thank you for your question. "
                    + "Please share crop type, symptoms, and location for a better recommendation."
    );
    aiMessage.setWrapText(true);
    aiMessage.setStyle(
            "-fx-background-color: #163C29;" +
            "-fx-text-fill: #E8F5E9;" +
            "-fx-background-radius: 12;" +
            "-fx-padding: 12;"
    );

    chatBox.getChildren().addAll(userMessage, aiMessage);
    questionField.clear();

    if (statusLabel != null) {
        statusLabel.setText("Response generated.");
    }
}

    private ComboBox<String> cropBox;
    private TextArea symptomsBox;
    private Label imageNameLabel;
    private Label resultTitle;
    private Label resultText;
    private VBox recommendationBox;

    public void setCropBox(ComboBox<String> cropBox) {
        this.cropBox = cropBox;
    }

    public void setSymptomsBox(TextArea symptomsBox) {
        this.symptomsBox = symptomsBox;
    }

    public void setImageNameLabel(Label imageNameLabel) {
        this.imageNameLabel = imageNameLabel;
    }

    public void setResultTitle(Label resultTitle) {
        this.resultTitle = resultTitle;
    }

    public void setResultText(Label resultText) {
        this.resultText = resultText;
    }

    public void setRecommendationBox(VBox recommendationBox) {
        this.recommendationBox = recommendationBox;
    }

    public void selectImage(Window owner) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Crop or Leaf Image");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files", "*.png", "*.jpg", "*.jpeg"
                )
        );

        File file = chooser.showOpenDialog(owner);

        if (file != null) {
            imageNameLabel.setText("Selected: " + file.getName());
        }
    }

    public void analyzeCrop() {
        String crop = cropBox.getValue();
        String symptoms = symptomsBox.getText().trim();

        if (crop == null || crop.isEmpty()) {
            resultTitle.setText("Select a crop first");
            resultText.setText("Please select the crop type before running analysis.");
            return;
        }

        if (symptoms.isEmpty()) {
            resultTitle.setText("Describe the symptoms");
            resultText.setText("For better results, add symptoms such as yellow leaves, spots, or wilting.");
            return;
        }

        String lowerSymptoms = symptoms.toLowerCase();

        if (lowerSymptoms.contains("yellow") || lowerSymptoms.contains("yellowing")) {
            resultTitle.setText("Possible Nutrient Deficiency");
            resultText.setText(
                    "Your " + crop + " may have nitrogen deficiency or watering stress. "
                    + "Yellowing often begins on older leaves."
            );
            showRecommendations(
                    "Test soil nitrogen and pH level.",
                    "Apply balanced organic compost or nitrogen fertilizer.",
                    "Check drainage and avoid overwatering."
            );
        } else if (lowerSymptoms.contains("spot") || lowerSymptoms.contains("fungus")) {
            resultTitle.setText("Possible Fungal Leaf Disease");
            resultText.setText(
                    "The symptoms may indicate early fungal infection. "
                    + "Inspect both sides of leaves and remove severely affected parts."
            );
            showRecommendations(
                    "Remove infected leaves using clean tools.",
                    "Keep leaves dry and improve air circulation.",
                    "Use a suitable approved fungicide if symptoms spread."
            );
        } else if (lowerSymptoms.contains("insect") || lowerSymptoms.contains("pest")) {
            resultTitle.setText("Possible Pest Activity");
            resultText.setText(
                    "Check under leaves for aphids, mites, caterpillars, or eggs. "
                    + "Look for holes, curling, or sticky residue."
            );
            showRecommendations(
                    "Inspect leaves early morning or evening.",
                    "Use neem oil or another appropriate treatment.",
                    "Remove weeds around the crop to reduce pest habitat."
            );
        } else {
            resultTitle.setText("Initial Crop Health Review");
            resultText.setText(
                    "Your " + crop + " needs a closer field inspection. "
                    + "Share clearer symptoms or a leaf image for a more accurate result."
            );
            showRecommendations(
                    "Check soil moisture before watering.",
                    "Inspect roots, stems, and both sides of leaves.",
                    "Record changes for the next 3 to 5 days."
            );
        }
    }

    public void resetForm() {
        cropBox.setValue(null);
        symptomsBox.clear();
        imageNameLabel.setText("No image selected");
        resultTitle.setText("Waiting for analysis");
        resultText.setText("Complete the crop information and click Analyze Crop.");
        recommendationBox.getChildren().clear();
    }

    private void showRecommendations(String first, String second, String third) {
        recommendationBox.getChildren().clear();

        recommendationBox.getChildren().addAll(
                createRecommendation("1", first),
                createRecommendation("2", second),
                createRecommendation("3", third)
        );
    }

    private Label createRecommendation(String number, String text) {
        Label label = new Label(number + "   " + text);
        label.setWrapText(true);
        label.setStyle(
                "-fx-background-color: #E8F5E9;" +
                "-fx-text-fill: #165B2E;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 14px;"
        );

        return label;
    }
}