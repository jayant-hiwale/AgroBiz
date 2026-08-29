package com.pravartak.view.farmer;

import java.io.File;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AddProductPage {

    private Scene addProductScene;

    private ProductController productController;
    private String selectedImagePath = "";

    // ============================================================
    // FARMER ID
    // ============================================================

    /*
     * TEMPORARY farmer ID
     *
     * This MUST match the farmerId used in MarketPlace.
     *
     * Your current MarketPlace uses:
     *
     * private int farmerId = 101;
     *
     * So we use 101 here also.
     *
     * Later we will replace this with the actual
     * logged-in farmer ID.
     */
    private int farmerId = 101;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public AddProductPage() {

        productController =
                new ProductController();
    }

    // ============================================================
    // SCENE
    // ============================================================

    public Scene getAddProductScene(
            Runnable callback) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        // TOP
        root.setTop(
                createHeader(callback)
        );

        // CENTER
        root.setCenter(
                createForm(callback)
        );

        addProductScene =
                new Scene(
                        root,
                        1200,
                        700
                );

        return addProductScene;
    }

    // ============================================================
    // HEADER
    // ============================================================

    private HBox createHeader(
            Runnable callback) {

        HBox header =
                new HBox();

        header.setPadding(
                new Insets(
                        15,
                        25,
                        15,
                        25
                )
        );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setSpacing(20);

        header.setStyle(
                "-fx-background-color:#080C0D;" +
                "-fx-border-color:#1B2021;" +
                "-fx-border-width:0 0 1 0;"
        );

        Label title =
                new Label(
                        "Add Product"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:25px;" +
                "-fx-font-weight:bold;"
        );

        Button backButton =
                new Button(
                        "← Back"
                );

        backButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:5;" +
                "-fx-cursor:hand;"
        );

        backButton.setOnAction(
                e -> {

                    if (callback != null) {
                        callback.run();
                    }
                }
        );

        header.getChildren()
                .addAll(
                        backButton,
                        title
                );

        return header;
    }

    // ============================================================
    // PRODUCT FORM
    // ============================================================

    private VBox createForm(
            Runnable callback) {

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(30)
        );

        main.setAlignment(
                Pos.TOP_CENTER
        );

        main.setStyle(
                "-fx-background-color:#080C0D;"
        );

        Label heading =
                new Label(
                        "Product Information"
                );

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        // ========================================================
        // GRID
        // ========================================================

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.setAlignment(
                Pos.CENTER
        );

        // ========================================================
        // PRODUCT NAME
        // ========================================================

        Label nameLabel =
                new Label(
                        "Product Name"
                );

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter product name"
        );

        // ========================================================
        // CATEGORY
        // ========================================================

        Label categoryLabel =
                new Label(
                        "Category"
                );

        ComboBox<String> categoryBox =
                new ComboBox<>();

        categoryBox.getItems()
                .addAll(
                        "Vegetables",
                        "Fruits",
                        "Grains",
                        "Livestock",
                        "Dairy",
                        "Seeds",
                        "Fertilizers",
                        "Equipment",
                        "Other"
                );

        categoryBox.setPromptText(
                "Select category"
        );

        // ========================================================
        // PRICE
        // ========================================================

        Label priceLabel =
                new Label(
                        "Price"
                );

        TextField priceField =
                new TextField();

        priceField.setPromptText(
                "Enter price"
        );

        // ========================================================
        // UNIT
        // ========================================================

        Label unitLabel =
                new Label(
                        "Unit"
                );

        ComboBox<String> unitBox =
                new ComboBox<>();

        unitBox.getItems()
                .addAll(
                        "Kg",
                        "Quintal",
                        "Ton",
                        "Litre",
                        "Piece",
                        "Bag"
                );

        unitBox.setPromptText(
                "Select unit"
        );

        // ========================================================
        // QUANTITY
        // ========================================================

        Label quantityLabel =
                new Label(
                        "Available Quantity"
                );

        TextField quantityField =
                new TextField();

        quantityField.setPromptText(
                "Enter available quantity"
        );

        // ========================================================
        // LOCATION
        // ========================================================

        Label locationLabel =
                new Label(
                        "Location"
                );

        TextField locationField =
                new TextField();

        locationField.setPromptText(
                "Enter location"
        );

        // ========================================================
        // DESCRIPTION
        // ========================================================

        Label descriptionLabel =
                new Label(
                        "Description"
                );

        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Enter product description"
        );

// ========================================================
// PRODUCT IMAGE
// ========================================================

Label imageLabel =
        new Label("Product Image");

styleLabel(imageLabel);

Label imageName =
        new Label("No image selected");

imageName.setStyle(
        "-fx-text-fill:#777777;" +
        "-fx-font-size:12px;"
);

Button uploadImageButton =
        new Button("📷 Upload Product Image");

uploadImageButton.setStyle(
        "-fx-background-color:#101516;" +
        "-fx-text-fill:#68D34A;" +
        "-fx-border-color:#68D34A;" +
        "-fx-border-radius:5;" +
        "-fx-background-radius:5;" +
        "-fx-padding:9 15;" +
        "-fx-cursor:hand;"
);

uploadImageButton.setOnAction(e -> {

    FileChooser fileChooser =
            new FileChooser();

    fileChooser.setTitle(
            "Select Product Image"
    );

    fileChooser.getExtensionFilters()
            .add(
                    new FileChooser.ExtensionFilter(
                            "Image Files",
                            "*.png",
                            "*.jpg",
                            "*.jpeg",
                            "*.webp"
                    )
            );

    File file =
            fileChooser.showOpenDialog(
                    addProductScene.getWindow()
            );

    if (file != null) {

        selectedImagePath =
                file.toURI().toString();

        imageName.setText(
                file.getName()
        );

        imageName.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:12px;"
        );

        System.out.println(
                "Selected image: "
                + file.getAbsolutePath()
        );
    }
});

        // ========================================================
        // ADD TO GRID
        // ========================================================

        form.add(
                nameLabel,
                0,
                0
        );

        form.add(
                nameField,
                1,
                0
        );

        form.add(
                categoryLabel,
                0,
                1
        );

        form.add(
                categoryBox,
                1,
                1
        );

        form.add(
                priceLabel,
                0,
                2
        );

        form.add(
                priceField,
                1,
                2
        );

        form.add(
                unitLabel,
                0,
                3
        );

        form.add(
                unitBox,
                1,
                3
        );

        form.add(
                quantityLabel,
                0,
                4
        );

        form.add(
                quantityField,
                1,
                4
        );

        form.add(
                locationLabel,
                0,
                5
        );

        form.add(
                locationField,
                1,
                5
        );

        form.add(
                descriptionLabel,
                0,
                6
        );

        form.add(
                descriptionField,
                1,
                6
        );
        form.add(imageLabel, 0, 7);

HBox imageBox =
        new HBox(
                10,
                uploadImageButton,
                imageName
        );

imageBox.setAlignment(
        Pos.CENTER_LEFT
);

form.add(imageBox, 1, 7);

        // ========================================================
        // CONTROL SIZES
        // ========================================================

        nameField.setPrefWidth(300);
        priceField.setPrefWidth(300);
        quantityField.setPrefWidth(300);
        locationField.setPrefWidth(300);
        descriptionField.setPrefWidth(300);

        categoryBox.setPrefWidth(300);
        unitBox.setPrefWidth(300);

        // ========================================================
        // STYLING
        // ========================================================

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

        // ========================================================
        // ADD PRODUCT BUTTON
        // ========================================================

        Button addButton =
                new Button(
                        "Add Product"
                );

        addButton.setPrefWidth(150);
        addButton.setPrefHeight(42);

        addButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:10 25;" +
                "-fx-background-radius:5;" +
                "-fx-cursor:hand;"
        );

        // ========================================================
        // SAVE PRODUCT
        // ========================================================

        addButton.setOnAction(
                e -> {

                    addProduct(
                            nameField,
                            categoryBox,
                            priceField,
                            unitBox,
                            quantityField,
                            locationField,
                            descriptionField,
                            callback
                    );
                }
        );

        main.getChildren()
                .addAll(
                        heading,
                        form,
                        addButton
                );

        return main;
    }

    // ============================================================
    // ADD PRODUCT TO FIRESTORE
    // ============================================================

    private void addProduct(
            TextField nameField,
            ComboBox<String> categoryBox,
            TextField priceField,
            ComboBox<String> unitBox,
            TextField quantityField,
            TextField locationField,
            TextField descriptionField,
            Runnable callback) {

        // ========================================================
        // READ VALUES
        // ========================================================

        String productName =
                nameField
                        .getText()
                        .trim();

        String category =
                categoryBox
                        .getValue();

        String priceText =
                priceField
                        .getText()
                        .trim();

        String unit =
                unitBox
                        .getValue();

        String quantityText =
                quantityField
                        .getText()
                        .trim();

        String location =
                locationField
                        .getText()
                        .trim();

        String description =
                descriptionField
                        .getText()
                        .trim();

        // ========================================================
        // VALIDATION
        // ========================================================

        if (productName.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter product name."
            );

            return;
        }

        if (category == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please select a category."
            );

            return;
        }

        if (priceText.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter price."
            );

            return;
        }

        if (unit == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please select unit."
            );

            return;
        }

        if (quantityText.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter quantity."
            );

            return;
        }

        if (location.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter location."
            );

            return;
        }

        // ========================================================
        // CONVERT NUMBER
        // ========================================================

        double price;

        double quantity;

        try {

            price =
                    Double.parseDouble(
                            priceText
                    );

            quantity =
                    Double.parseDouble(
                            quantityText
                    );

        } catch (NumberFormatException ex) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Price and quantity must be numbers."
            );

            return;
        }

        if (price < 0) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Price cannot be negative."
            );

            return;
        }

        if (quantity <= 0) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Quantity must be greater than 0."
            );

            return;
        }

        // ========================================================
        // GENERATE PRODUCT ID
        // ========================================================

        int productId =
                (int) (
                        System.currentTimeMillis()
                                % Integer.MAX_VALUE
                );

        // ========================================================
        // IMAGE
        // ========================================================

        /*
         * Image upload will be connected to Firebase Storage.
         *
         * For now this is empty.
         */
        String imagePath = "";

        // ========================================================
        // CREATE PRODUCT
        // ========================================================

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
                        imagePath
                );

        // ========================================================
        // SAVE TO FIRESTORE
        // ========================================================

        boolean saved =
                productController
                        .addProduct(
                                product
                        );

        // ========================================================
        // RESULT
        // ========================================================

        if (saved) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Product added successfully!"
            );

            /*
             * Go back to Marketplace.
             *
             * The callback will call loadProducts()
             * in MarketPlace.
             */

            if (callback != null) {

                callback.run();
            }

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Product could not be added.\n"
                    + "Please check Firebase connection."
            );
        }
    }

    // ============================================================
    // TEXT FIELD STYLE
    // ============================================================

    private void styleTextField(
            TextField field) {

        field.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#303839;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;"
        );
    }

    // ============================================================
    // COMBO BOX STYLE
    // ============================================================

    private void styleComboBox(
            ComboBox<String> box) {

        box.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-border-color:#303839;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;"
        );
    }

    // ============================================================
    // LABEL STYLE
    // ============================================================

    private void styleLabel(
            Label label) {

        label.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );
    }

    // ============================================================
    // ALERT
    // ============================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "Marketplace"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}