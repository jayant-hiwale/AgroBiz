// package com.pravartak.view.farmer;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;

// public class AddProductPage {

//     private Scene addProductScene;

//     public Scene getAddProductScene(Runnable callback) {

//         BorderPane root = new BorderPane();

//         root.setStyle(
//                 "-fx-background-color: #080c0d;");

//         // TOP
//         root.setTop(createHeader(callback));

//         // CENTER
//         root.setCenter(createForm());

//         addProductScene = new Scene(root, 1200, 700);

//         return addProductScene;
//     }

//     // ============================================================
//     // HEADER
//     // ============================================================

//     private HBox createHeader(Runnable callback) {

//         HBox header = new HBox();

//         header.setPadding(new Insets(15, 25, 15, 25));

//         header.setAlignment(Pos.CENTER_LEFT);

//         header.setStyle("-fx-background-color: #080c0d;" + "-fx-border-color: #1b2021;" + "-fx-border-width: 0 0 1 0;");

//         Label title = new Label("Add Product");

//         title.setStyle("-fx-text-fill: #eeeeee;" + "-fx-font-size: 25px;" + "-fx-font-weight: bold;");

//         Button backButton = new Button("← Back");

//         backButton.setStyle(
//                 "-fx-background-color: transparent;" +
//                         "-fx-text-fill: #68d34a;" +
//                         "-fx-border-color: #68d34a;" +
//                         "-fx-border-radius: 5;" +
//                         "-fx-cursor: hand;");

//         backButton.setOnAction(e -> {

//             if (callback != null) {
//                 callback.run();
//             }

//         });

//         header.setSpacing(20);

//         header.getChildren().addAll(backButton, title);

//         return header;
//     }

//     // ============================================================
//     // PRODUCT FORM
//     // ============================================================

//     private VBox createForm() {

//         VBox main = new VBox(20);

//         main.setPadding(new Insets(30));

//         main.setAlignment(Pos.TOP_CENTER);

//         Label heading = new Label("Product Information");

//         heading.setStyle(
//                 "-fx-text-fill: #eeeeee;" +
//                         "-fx-font-size: 24px;" +
//                         "-fx-font-weight: bold;");

//         // ========================================================
//         // GRID
//         // ========================================================

//         GridPane form = new GridPane();

//         form.setHgap(15);
//         form.setVgap(15);

//         form.setAlignment(Pos.CENTER);

//         // ========================================================
//         // PRODUCT NAME
//         // ========================================================

//         Label nameLabel = new Label("Product Name");

//         TextField nameField = new TextField();

//         nameField.setPromptText("Enter product name");

//         // ========================================================
//         // CATEGORY
//         // ========================================================

//         Label categoryLabel = new Label("Category");

//         ComboBox<String> categoryBox = new ComboBox<>();

//         categoryBox.getItems().addAll(
//                 "Vegetables",
//                 "Fruits",
//                 "Grains",
//                 "Livestock",
//                 "Dairy",
//                 "Seeds",
//                 "Fertilizers",
//                 "Equipment",
//                 "Other");

//         categoryBox.setPromptText("Select category");

//         // ========================================================
//         // PRICE
//         // ========================================================

//         Label priceLabel = new Label("Price");

//         TextField priceField = new TextField();

//         priceField.setPromptText("Enter price");

//         // ========================================================
//         // UNIT
//         // ========================================================

//         Label unitLabel = new Label("Unit");

//         ComboBox<String> unitBox = new ComboBox<>();

//         unitBox.getItems().addAll("Kg", "Quintal", "Ton", "Litre", "Piece", "Bag");

//         unitBox.setPromptText("Select unit");

//         // ========================================================
//         // QUANTITY
//         // ========================================================

//         Label quantityLabel = new Label("Quantity");

//         TextField quantityField = new TextField();
//         quantityField.setPromptText("Enter quantity");

//         // ========================================================
//         // LOCATION
//         // ========================================================

//         Label locationLabel = new Label("Location");

//         TextField locationField = new TextField();

//         locationField.setPromptText("Enter location");

//         // ========================================================
//         // DESCRIPTION
//         // ========================================================

//         Label descriptionLabel = new Label("Description");

//         TextField descriptionField = new TextField();

//         descriptionField.setPromptText(
//                 "Enter product description");

//         // ========================================================
//         // ADD TO GRID
//         // ========================================================

//         form.add(nameLabel, 0, 0);
//         form.add(nameField, 1, 0);

//         form.add(categoryLabel, 0, 1);
//         form.add(categoryBox, 1, 1);

//         form.add(priceLabel, 0, 2);
//         form.add(priceField, 1, 2);

//         form.add(unitLabel, 0, 3);
//         form.add(unitBox, 1, 3);

//         form.add(quantityLabel, 0, 4);
//         form.add(quantityField, 1, 4);

//         form.add(locationLabel, 0, 5);
//         form.add(locationField, 1, 5);

//         form.add(descriptionLabel, 0, 6);
//         form.add(descriptionField, 1, 6);

//         // ========================================================
//         // STYLE FORM CONTROLS
//         // ========================================================

//         nameField.setPrefWidth(300);
//         priceField.setPrefWidth(300);
//         quantityField.setPrefWidth(300);
//         locationField.setPrefWidth(300);
//         descriptionField.setPrefWidth(300);

//         categoryBox.setPrefWidth(300);
//         unitBox.setPrefWidth(300);

//         styleTextField(nameField);
//         styleTextField(priceField);
//         styleTextField(quantityField);
//         styleTextField(locationField);
//         styleTextField(descriptionField);

//         styleComboBox(categoryBox);
//         styleComboBox(unitBox);

//         styleLabel(nameLabel);
//         styleLabel(categoryLabel);
//         styleLabel(priceLabel);
//         styleLabel(unitLabel);
//         styleLabel(quantityLabel);
//         styleLabel(locationLabel);
//         styleLabel(descriptionLabel);

//         // ========================================================
//         // BUTTON
//         // ========================================================

//         Button addButton = new Button("Add Product");

//         addButton.setStyle(
//                 "-fx-background-color: #68d34a;" +
//                         "-fx-text-fill: #080c0d;" +
//                         "-fx-font-weight: bold;" +
//                         "-fx-padding: 10 25;" +
//                         "-fx-border-radius: 5;" +
//                         "-fx-cursor: hand;");

//         addButton.setOnAction(e -> {

//             System.out.println("Add Product clicked");

//             System.out.println("Product: " + nameField.getText());

//         });

//         main.getChildren().addAll(heading, form, addButton);

//         return main;
//     }

//     // ============================================================
//     // TEXT FIELD STYLE
//     // ============================================================

//     private void styleTextField(TextField field) {

//         field.setStyle(
//                 "-fx-background-color: #101516;" +
//                         "-fx-text-fill: #eeeeee;" +
//                         "-fx-prompt-text-fill: #777777;" +
//                         "-fx-border-color: #303839;" +
//                         "-fx-border-radius: 5;" +
//                         "-fx-background-radius: 5;");
//     }

//     // ============================================================
//     // COMBO BOX STYLE
//     // ============================================================

//     private void styleComboBox(ComboBox<String> box) {

//         box.setStyle(
//                 "-fx-background-color: #101516;" +
//                         "-fx-text-fill: #eeeeee;" +
//                         "-fx-border-color: #303839;" +
//                         "-fx-border-radius: 5;" +
//                         "-fx-background-radius: 5;");
//     }

//     private void styleLabel(Label label) {

//         label.setStyle("-fx-text-fill: #bbbbbb;" + "-fx-font-size: 13px;");
//     }
// }

package com.pravartak.view.farmer;

import java.io.File;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AddProductPage {

    private Scene addProductScene;

    // Product controller
    private ProductController productController;

    // Farmer ID
    private int farmerId = 101;

    public AddProductPage(ProductController productController) {
        this.productController = productController;
    }

    public Scene getAddProductScene(Runnable callback) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #080c0d;");

        // TOP
        root.setTop(createHeader(callback));

        // CENTER
        root.setCenter(createForm(callback));

        addProductScene = new Scene(root, 1200, 700);

        return addProductScene;
    }

    // ============================================================
    // HEADER
    // ============================================================

    private HBox createHeader(Runnable callback) {

        HBox header = new HBox();

        header.setPadding(new Insets(15, 25, 15, 25));

        header.setAlignment(Pos.CENTER_LEFT);

        header.setStyle(
                "-fx-background-color: #080c0d;" +
                        "-fx-border-color: #1b2021;" +
                        "-fx-border-width: 0 0 1 0;");

        Label title = new Label("Add Product");

        title.setStyle(
                "-fx-text-fill: #eeeeee;" +
                        "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;");

        Button backButton = new Button("← Back");

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #68d34a;" +
                        "-fx-border-color: #68d34a;" +
                        "-fx-border-radius: 5;" +
                        "-fx-cursor: hand;");

        backButton.setOnAction(e -> {

            if (callback != null) {
                callback.run();
            }

        });

        header.setSpacing(20);

        header.getChildren().addAll(backButton, title);

        return header;
    }

    // ============================================================
    // PRODUCT FORM
    // ============================================================

    private VBox createForm(Runnable callback) {

        VBox main = new VBox(20);

        main.setPadding(new Insets(30));

        main.setAlignment(Pos.TOP_CENTER);

        Label heading = new Label("Product Information");

        heading.setStyle(
                "-fx-text-fill: #eeeeee;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;");

        // ========================================================
        // GRID
        // ========================================================

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.setAlignment(Pos.CENTER);

        // ========================================================
        // PRODUCT NAME
        // ========================================================

        Label nameLabel = new Label("Product Name");

        TextField nameField = new TextField();

        nameField.setPromptText("Enter product name");

        // ========================================================
        // CATEGORY
        // ========================================================

        Label categoryLabel = new Label("Category");

        ComboBox<String> categoryBox = new ComboBox<>();

        categoryBox.getItems().addAll(
                "Vegetables",
                "Fruits",
                "Grains",
                "Livestock",
                "Dairy",
                "Seeds",
                "Fertilizers",
                "Equipment",
                "Other");

        categoryBox.setPromptText("Select category");

        // ========================================================
        // PRICE
        // ========================================================

        Label priceLabel = new Label("Price");

        TextField priceField = new TextField();

        priceField.setPromptText("Enter price");

        // ========================================================
        // UNIT
        // ========================================================

        Label unitLabel = new Label("Unit");

        ComboBox<String> unitBox = new ComboBox<>();

        unitBox.getItems().addAll(
                "Kg",
                "Quintal",
                "Ton",
                "Litre",
                "Piece",
                "Bag");

        unitBox.setPromptText("Select unit");

        // ========================================================
        // QUANTITY
        // ========================================================

        Label quantityLabel = new Label("Quantity");

        TextField quantityField = new TextField();

        quantityField.setPromptText("Enter quantity");

        // ========================================================
        // LOCATION
        // ========================================================

        Label locationLabel = new Label("Location");

        TextField locationField = new TextField();

        locationField.setPromptText("Enter location");

        // ========================================================
        // DESCRIPTION
        // ========================================================

        Label descriptionLabel = new Label("Description");

        TextField descriptionField = new TextField();

        descriptionField.setPromptText(
                "Enter product description");

        // ========================================================
        // IMAGE UPLOAD
        // ========================================================

        Label imageLabel = new Label("Product Image");

        Button uploadButton = new Button("Choose Image");

        uploadButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #68d34a;" +
                        "-fx-border-color: #68d34a;" +
                        "-fx-border-radius: 5;" +
                        "-fx-padding: 8 15;" +
                        "-fx-cursor: hand;");

        Label imageNameLabel = new Label("No image selected");

        imageNameLabel.setStyle(
                "-fx-text-fill: #888888;" +
                        "-fx-font-size: 12px;");

        // Store selected image path
        final String[] selectedImagePath = { null };

        uploadButton.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Select Product Image");

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Image Files",
                            "*.png",
                            "*.jpg",
                            "*.jpeg",
                            "*.webp"));

            File selectedFile =
                    fileChooser.showOpenDialog(
                            uploadButton.getScene().getWindow());

            if (selectedFile != null) {

                selectedImagePath[0] =
                        selectedFile.getAbsolutePath();

                imageNameLabel.setText(
                        selectedFile.getName());

                imageNameLabel.setStyle(
                        "-fx-text-fill: #68d34a;" +
                                "-fx-font-size: 12px;");
            }
        });

        HBox imageBox = new HBox(
                10,
                uploadButton,
                imageNameLabel);

        imageBox.setAlignment(Pos.CENTER_LEFT);

        // ========================================================
        // ADD TO GRID
        // ========================================================

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(categoryLabel, 0, 1);
        form.add(categoryBox, 1, 1);

        form.add(priceLabel, 0, 2);
        form.add(priceField, 1, 2);

        form.add(unitLabel, 0, 3);
        form.add(unitBox, 1, 3);

        form.add(quantityLabel, 0, 4);
        form.add(quantityField, 1, 4);

        form.add(locationLabel, 0, 5);
        form.add(locationField, 1, 5);

        form.add(descriptionLabel, 0, 6);
        form.add(descriptionField, 1, 6);

        // NEW IMAGE ROW
        form.add(imageLabel, 0, 7);
        form.add(imageBox, 1, 7);

        // ========================================================
        // STYLE FORM CONTROLS
        // ========================================================

        nameField.setPrefWidth(300);
        priceField.setPrefWidth(300);
        quantityField.setPrefWidth(300);
        locationField.setPrefWidth(300);
        descriptionField.setPrefWidth(300);

        categoryBox.setPrefWidth(300);
        unitBox.setPrefWidth(300);

        styleTextField(nameField);
        styleTextField(priceField);
        styleTextField(quantityField);
        styleTextField(locationField);
        styleTextField(descriptionField);

        styleComboBox(categoryBox);
        styleComboBox(unitBox);

        styleLabel(nameLabel);
        styleLabel(categoryLabel);
        styleLabel(priceLabel);
        styleLabel(unitLabel);
        styleLabel(quantityLabel);
        styleLabel(locationLabel);
        styleLabel(descriptionLabel);
        styleLabel(imageLabel);

        // ========================================================
        // BUTTON
        // ========================================================

        Button addButton = new Button("Add Product");

        addButton.setStyle(
                "-fx-background-color: #68d34a;" +
                        "-fx-text-fill: #080c0d;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 25;" +
                        "-fx-border-radius: 5;" +
                        "-fx-cursor: hand;");

        addButton.setOnAction(e -> {

            try {

                String productName =
                        nameField.getText();

                String category =
                        categoryBox.getValue();

                double price =
                        Double.parseDouble(
                                priceField.getText());

                String unit =
                        unitBox.getValue();

                double quantity =
                        Double.parseDouble(
                                quantityField.getText());

                String location =
                        locationField.getText();

                String description =
                        descriptionField.getText();

                String imagePath =
                        selectedImagePath[0];

                // Generate new ID
                int productId =
                        productController.getNextProductId();

                Product product =
                        new Product(
                                productId,
                                farmerId,
                                productName,
                                category,
                                description,
                                price,
                                unit,
                                quantity,
                                location,
                                imagePath,
                                "Active",
                                0);

                boolean added =
                        productController.addProduct(product);

                if (added) {

                    System.out.println(
                            "Product added successfully");

                    System.out.println(
                            "Product: "
                                    + productName);

                    System.out.println(
                            "Image: "
                                    + imagePath);

                    if (callback != null) {
                        callback.run();
                    }
                }

            } catch (NumberFormatException ex) {

                System.out.println(
                        "Please enter valid price and quantity.");

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        main.getChildren().addAll(
                heading,
                form,
                addButton);

        return main;
    }

    // ============================================================
    // TEXT FIELD STYLE
    // ============================================================

    private void styleTextField(TextField field) {

        field.setStyle(
                "-fx-background-color: #101516;" +
                        "-fx-text-fill: #eeeeee;" +
                        "-fx-prompt-text-fill: #777777;" +
                        "-fx-border-color: #303839;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;");
    }

    // ============================================================
    // COMBO BOX STYLE
    // ============================================================

    private void styleComboBox(ComboBox<String> box) {

        box.setStyle(
                "-fx-background-color: #101516;" +
                        "-fx-text-fill: #eeeeee;" +
                        "-fx-border-color: #303839;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;");
    }

    private void styleLabel(Label label) {

        label.setStyle(
                "-fx-text-fill: #bbbbbb;" +
                        "-fx-font-size: 13px;");
    }
}