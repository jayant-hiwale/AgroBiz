package com.pravartak.view.farmer;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import com.pravartak.config.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

import java.io.File;

public class AddProductPage {

    private Scene addProductScene;

    private final ProductController productController;

    private final int farmerId;

    private String selectedImagePath = "";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AddProductPage(
            int farmerId) {

        if (farmerId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid farmer ID: "
                    + farmerId
            );
        }

        this.farmerId = farmerId;

        productController =
                new ProductController();

        System.out.println(
                "AddProductPage Farmer ID = "
                + farmerId
        );
    }

    // =====================================================
    // SCENE
    // =====================================================

    public Scene getAddProductScene(
            Runnable callback) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        root.setTop(
                createHeader(callback)
        );

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

    // =====================================================
    // HEADER
    // =====================================================

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

        header.setSpacing(
                20
        );

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

    // =====================================================
    // FORM
    // =====================================================

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

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.setAlignment(
                Pos.CENTER
        );

        // =================================================
        // NAME
        // =================================================

        Label nameLabel =
                new Label(
                        "Product Name"
                );

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter product name"
        );

        // =================================================
        // CATEGORY
        // =================================================

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

        // =================================================
        // PRICE
        // =================================================

        Label priceLabel =
                new Label(
                        "Price"
                );

        TextField priceField =
                new TextField();

        priceField.setPromptText(
                "Enter price"
        );

        // =================================================
        // UNIT
        // =================================================

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

        // =================================================
        // QUANTITY
        // =================================================

        Label quantityLabel =
                new Label(
                        "Available Quantity"
                );

        TextField quantityField =
                new TextField();

        quantityField.setPromptText(
                "Enter available quantity"
        );

        // =================================================
        // LOCATION
        // =================================================

        Label locationLabel =
                new Label(
                        "Location"
                );

        TextField locationField =
                new TextField();

        locationField.setPromptText(
                "Enter location"
        );

        // =================================================
        // DESCRIPTION
        // =================================================

        Label descriptionLabel =
                new Label(
                        "Description"
                );

        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Enter product description"
        );

        // =================================================
        // IMAGE
        // =================================================

        Label imageLabel =
                new Label(
                        "Product Image"
                );

        Label imageName =
                new Label(
                        "No image selected"
                );

        imageName.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:12px;"
        );

        Button uploadImageButton =
                new Button(
                        "📷 Upload Product Image"
                );

        uploadImageButton.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-border-color:#68D34A;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;" +
                "-fx-padding:9 15;" +
                "-fx-cursor:hand;"
        );

        uploadImageButton.setOnAction(
                e -> {

                    FileChooser fileChooser =
                            new FileChooser();

                    fileChooser.setTitle(
                            "Select Product Image"
                    );

                    fileChooser
                            .getExtensionFilters()
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
                                    addProductScene
                                            .getWindow()
                            );

                    if (file != null) {

    imageName.setText(
            "Uploading..."
    );

    imageName.setStyle(
            "-fx-text-fill:#FFA726;" +
            "-fx-font-size:12px;"
    );

    uploadImageButton.setDisable(true);

    try {

        // =================================================
        // CLOUDINARY UPLOAD
        // =================================================

        Cloudinary cloudinary =
                CloudinaryConfig.getCloudinary();

        Map uploadResult =
                cloudinary.uploader().upload(
                        file,
                        ObjectUtils.asMap(
                                "folder",
                                "agrobiz/products",
                                "resource_type",
                                "image"
                        )
                );

        // =================================================
        // GET CLOUDINARY URL
        // =================================================

        Object secureUrl =
                uploadResult.get("secure_url");

        if (secureUrl == null) {

            throw new RuntimeException(
                    "Cloudinary did not return image URL."
            );
        }

        selectedImagePath =
                secureUrl.toString();

        System.out.println(
                "Image uploaded successfully."
        );

        System.out.println(
                "Cloudinary URL = "
                + selectedImagePath
        );

        // =================================================
        // UI
        // =================================================

        imageName.setText(
                file.getName() + " ✓"
        );

        imageName.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:12px;"
        );

    } catch (Exception ex) {

        ex.printStackTrace();

        selectedImagePath = "";

        imageName.setText(
                "Upload failed"
        );

        imageName.setStyle(
                "-fx-text-fill:#E57373;" +
                "-fx-font-size:12px;"
        );

        showAlert(
                Alert.AlertType.ERROR,
                "Image Upload Failed.\n"
                + ex.getMessage()
        );

    } finally {

        uploadImageButton.setDisable(false);
    }
}
                }
        );

        HBox imageBox =
                new HBox(
                        10,
                        uploadImageButton,
                        imageName
                );

        imageBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // GRID
        // =================================================

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

        form.add(
                imageLabel,
                0,
                7
        );

        form.add(
                imageBox,
                1,
                7
        );

        // =================================================
        // WIDTH
        // =================================================

        nameField.setPrefWidth(300);
        priceField.setPrefWidth(300);
        quantityField.setPrefWidth(300);
        locationField.setPrefWidth(300);
        descriptionField.setPrefWidth(300);

        categoryBox.setPrefWidth(300);
        unitBox.setPrefWidth(300);

        // =================================================
        // STYLE
        // =================================================

        styleTextField(
                nameField
        );

        styleTextField(
                priceField
        );

        styleTextField(
                quantityField
        );

        styleTextField(
                locationField
        );

        styleTextField(
                descriptionField
        );

        styleComboBox(
                categoryBox
        );

        styleComboBox(
                unitBox
        );

        styleLabel(
                nameLabel
        );

        styleLabel(
                categoryLabel
        );

        styleLabel(
                priceLabel
        );

        styleLabel(
                unitLabel
        );

        styleLabel(
                quantityLabel
        );

        styleLabel(
                locationLabel
        );

        styleLabel(
                descriptionLabel
        );

        styleLabel(
                imageLabel
        );

        // =================================================
        // ADD BUTTON
        // =================================================

        Button addButton =
                new Button(
                        "Add Product"
                );

        addButton.setPrefWidth(
                150
        );

        addButton.setPrefHeight(
                42
        );

        addButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:10 25;" +
                "-fx-background-radius:5;" +
                "-fx-cursor:hand;"
        );

        addButton.setOnAction(
                e -> addProduct(
                        nameField,
                        categoryBox,
                        priceField,
                        unitBox,
                        quantityField,
                        locationField,
                        descriptionField,
                        callback
                )
        );

        main.getChildren()
                .addAll(
                        heading,
                        form,
                        addButton
                );

        return main;
    }

    // =====================================================
    // SAVE PRODUCT
    // =====================================================

    private void addProduct(
            TextField nameField,
            ComboBox<String> categoryBox,
            TextField priceField,
            ComboBox<String> unitBox,
            TextField quantityField,
            TextField locationField,
            TextField descriptionField,
            Runnable callback) {

        String productName =
                nameField
                        .getText()
                        .trim();

        String category =
                categoryBox.getValue();

        String priceText =
                priceField
                        .getText()
                        .trim();

        String unit =
                unitBox.getValue();

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

        // =================================================
        // VALIDATION
        // =================================================

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
        if (selectedImagePath == null ||
        selectedImagePath.trim().isEmpty()) {

    showAlert(
            Alert.AlertType.WARNING,
            "Please upload a product image."
    );

    return;
}

        // =================================================
        // NUMBER
        // =================================================

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

        // =================================================
        // PRODUCT ID
        // =================================================

        int productId =
                (int)
                (
                        System.currentTimeMillis()
                        % Integer.MAX_VALUE
                );

        // =================================================
        // CREATE PRODUCT
        // =================================================

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
                        selectedImagePath
                );

        System.out.println(
                "Saving product..."
        );

        System.out.println(
                "Product ID = "
                + productId
        );

        System.out.println(
                "Farmer ID = "
                + farmerId
        );

        // =================================================
        // FIRESTORE
        // =================================================

        boolean saved =
                productController
                        .addProduct(
                                product
                        );

        if (saved) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Product added successfully!"
            );

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

    // =====================================================
    // TEXT FIELD
    // =====================================================

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

    // =====================================================
    // COMBO BOX
    // =====================================================

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

    // =====================================================
    // LABEL
    // =====================================================

    private void styleLabel(
            Label label) {

        label.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;"
        );
    }

    // =====================================================
    // ALERT
    // =====================================================

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