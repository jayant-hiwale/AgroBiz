// package com.pravartak.view.buyer;

// import com.pravartak.controller.buyercontroller.Controller;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Parent;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextArea;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;

// public class CropDoctor {

//     private final Controller controller;

//     public CropDoctor(Controller controller) {
//         this.controller = controller;
//     }

//     public Parent createView() {
//         BorderPane root = new BorderPane();
//         root.setStyle("-fx-background-color: #F5F8F4;");

//         root.setTop(createHeader());
//         root.setCenter(createContent(root));

//         return root;
//     }

//     private HBox createHeader() {
//         Label logo = new Label("AgroBiz Hub");
//         logo.setStyle(
//                 "-fx-font-size: 22px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #075B22;"
//         );

//         Label pageName = new Label("AI Crop Doctor");
//         pageName.setStyle(
//                 "-fx-font-size: 17px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #2C3E30;"
//         );

//         Region spacer = new Region();
//         HBox.setHgrow(spacer, Priority.ALWAYS);

//         Label status = new Label("● AI System Online");
//         status.setStyle(
//                 "-fx-text-fill: #167A37;" +
//                 "-fx-background-color: #E5F5E8;" +
//                 "-fx-background-radius: 15;" +
//                 "-fx-padding: 7 12;"
//         );

//         HBox header = new HBox(24, logo, pageName, spacer, status);
//         header.setAlignment(Pos.CENTER_LEFT);
//         header.setPadding(new Insets(20, 38, 20, 38));
//         header.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-border-color: transparent transparent #E2E8E1 transparent;"
//         );

//         return header;
//     }

//     private HBox createContent(BorderPane root) {
//         VBox formCard = createFormCard(root);
//         VBox resultCard = createResultCard();

//         HBox content = new HBox(24, formCard, resultCard);
//         content.setPadding(new Insets(32, 38, 32, 38));
//         HBox.setHgrow(formCard, Priority.ALWAYS);
//         HBox.setHgrow(resultCard, Priority.ALWAYS);

//         return content;
//     }

//     private VBox createFormCard(BorderPane root) {
//         Label title = new Label("Check Your Crop Health");
//         title.setStyle(
//                 "-fx-font-size: 25px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #183D26;"
//         );

//         Label subtitle = new Label(
//                 "Give details about your crop and our AI will suggest possible causes and next steps."
//         );
//         subtitle.setWrapText(true);
//         subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #667368;");

//         Label cropLabel = new Label("Crop Type");
//         cropLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #304235;");

//         ComboBox<String> cropBox = new ComboBox<>();
//         cropBox.getItems().addAll(
//                 "Wheat", "Rice", "Corn", "Tomato", "Potato",
//                 "Soybean", "Cotton", "Chili", "Other"
//         );
//         cropBox.setPromptText("Select your crop");
//         cropBox.setMaxWidth(Double.MAX_VALUE);
//         controller.setCropBox(cropBox);

//         Label imageLabel = new Label("Upload a Leaf or Crop Image");
//         imageLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #304235;");

//         Label imageName = new Label("No image selected");
//         imageName.setStyle("-fx-text-fill: #6E7B70;");
//         controller.setImageNameLabel(imageName);

//         Button upload = new Button("📷  Choose Image");
//         upload.setStyle(
//                 "-fx-background-color: #EAF4EC;" +
//                 "-fx-text-fill: #075B22;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-border-color: #4D9364;" +
//                 "-fx-border-radius: 8;" +
//                 "-fx-background-radius: 8;" +
//                 "-fx-padding: 11 18;"
//         );
//         upload.setOnAction(event ->
//                 controller.selectImage(root.getScene().getWindow())
//         );

//         HBox uploadRow = new HBox(14, upload, imageName);
//         uploadRow.setAlignment(Pos.CENTER_LEFT);

//         Label symptomLabel = new Label("Describe Symptoms");
//         symptomLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #304235;");

//         TextArea symptoms = new TextArea();
//         symptoms.setPromptText(
//                 "Example: Leaves are yellow with brown spots. Plant growth is slow..."
//         );
//         symptoms.setWrapText(true);
//         symptoms.setPrefRowCount(6);
//         symptoms.setStyle(
//                 "-fx-background-radius: 8;" +
//                 "-fx-border-color: #C9D8CB;" +
//                 "-fx-border-radius: 8;"
//         );
//         controller.setSymptomsBox(symptoms);

//         Button analyze = new Button("✦  Analyze Crop");
//         analyze.setMaxWidth(Double.MAX_VALUE);
//         analyze.setStyle(
//                 "-fx-background-color: #075B22;" +
//                 "-fx-text-fill: white;" +
//                 "-fx-font-size: 15px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 8;" +
//                 "-fx-padding: 13;"
//         );
//         analyze.setOnAction(event -> controller.analyzeCrop());

//         Button reset = new Button("Reset Form");
//         reset.setMaxWidth(Double.MAX_VALUE);
//         reset.setStyle(
//                 "-fx-background-color: transparent;" +
//                 "-fx-border-color: #9CB4A2;" +
//                 "-fx-border-radius: 8;" +
//                 "-fx-text-fill: #496251;" +
//                 "-fx-padding: 10;"
//         );
//         reset.setOnAction(event -> controller.resetForm());

//         VBox form = new VBox(
//                 14, title, subtitle, new Region(),
//                 cropLabel, cropBox,
//                 imageLabel, uploadRow,
//                 symptomLabel, symptoms,
//                 analyze, reset
//         );

//         form.setPadding(new Insets(26));
//         form.setPrefWidth(520);
//         form.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-background-radius: 14;" +
//                 "-fx-effect: dropshadow(gaussian, #C9D3C9, 12, 0.18, 0, 3);"
//         );

//         return form;
//     }

//     private VBox createResultCard() {
//         Label smallTitle = new Label("AI DIAGNOSIS");
//         smallTitle.setStyle(
//                 "-fx-font-size: 12px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #438055;"
//         );

//         Label resultTitle = new Label("Waiting for analysis");
//         resultTitle.setWrapText(true);
//         resultTitle.setStyle(
//                 "-fx-font-size: 24px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #183D26;"
//         );
//         controller.setResultTitle(resultTitle);

//         Label resultText = new Label(
//                 "Complete the crop information and click Analyze Crop."
//         );
//         resultText.setWrapText(true);
//         resultText.setStyle(
//                 "-fx-font-size: 15px;" +
//                 "-fx-text-fill: #5D6C60;"
//         );
//         controller.setResultText(resultText);

//         Label recommendationTitle = new Label("Recommended Next Steps");
//         recommendationTitle.setStyle(
//                 "-fx-font-size: 17px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #183D26;"
//         );

//         VBox recommendations = new VBox(10);
//         controller.setRecommendationBox(recommendations);

//         Label note = new Label(
//                 "Important: This is an AI-based preliminary assessment. "
//                         + "Consult an agricultural expert before using treatments."
//         );
//         note.setWrapText(true);
//         note.setStyle(
//                 "-fx-background-color: #FFF5D9;" +
//                 "-fx-text-fill: #715819;" +
//                 "-fx-background-radius: 8;" +
//                 "-fx-padding: 12;" +
//                 "-fx-font-size: 13px;"
//         );

//         VBox result = new VBox(
//                 16, smallTitle, resultTitle, resultText,
//                 new Region(), recommendationTitle, recommendations,
//                 new Region(), note
//         );

//         result.setPadding(new Insets(26));
//         result.setPrefWidth(520);
//         result.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-background-radius: 14;" +
//                 "-fx-effect: dropshadow(gaussian, #C9D3C9, 12, 0.18, 0, 3);"
//         );

//         return result;
//     }
// }