package com.pravartak.view.admin.marketplace;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminEditProductPage {

    private final Product product;
    private final ProductController productController;

    private TextField productNameField;
    private TextField categoryField;
    private TextField priceField;
    private TextField unitField;
    private TextField quantityField;
    private TextField locationField;
    private TextField imagePathField;

    private TextArea descriptionArea;

    private Stage stage;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminEditProductPage(Product product) {

        if (product == null) {

            throw new IllegalArgumentException(
                    "Product cannot be null."
            );
        }

        this.product = product;

        this.productController =
                new ProductController();
    }

    // =====================================================
    // SHOW EDIT PAGE
    // =====================================================

    public void show() {

        stage =
                new Stage();

        stage.setTitle(
                "Edit Product"
        );

        stage.initModality(
                Modality.APPLICATION_MODAL
        );

        stage.setMinWidth(
                700
        );

        stage.setMinHeight(
                650
        );

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );

        root.setTop(
                createHeader()
        );

        root.setCenter(
                createForm()
        );

        root.setBottom(
                createButtons()
        );

        Scene scene =
                new Scene(
                        root,
                        750,
                        680
                );

        stage.setScene(
                scene
        );

        stage.showAndWait();
    }

    // =====================================================
    // HEADER
    // =====================================================

    private VBox createHeader() {

        VBox header =
                new VBox(5);

        header.setPadding(
                new Insets(
                        25,
                        30,
                        15,
                        30
                )
        );

        Label title =
                new Label(
                        "Edit Product"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Update product information"
                );

        subtitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:13px;"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        return header;
    }

    // =====================================================
    // FORM
    // =====================================================

    private VBox createForm() {

        VBox container =
                new VBox();

        container.setPadding(
                new Insets(
                        10,
                        30,
                        20,
                        30
                )
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(20);
        grid.setVgap(15);

        // =================================================
        // PRODUCT NAME
        // =================================================

        Label productNameLabel =
                createLabel(
                        "Product Name"
                );

        productNameField =
                createTextField(
                        product.getProductName()
                );

        // =================================================
        // CATEGORY
        // =================================================

        Label categoryLabel =
                createLabel(
                        "Category"
                );

        categoryField =
                createTextField(
                        product.getCategory()
                );

        // =================================================
        // PRICE
        // =================================================

        Label priceLabel =
                createLabel(
                        "Price"
                );

        priceField =
                createTextField(
                        String.valueOf(
                                product.getPrice()
                        )
                );

        // =================================================
        // UNIT
        // =================================================

        Label unitLabel =
                createLabel(
                        "Unit"
                );

        unitField =
                createTextField(
                        product.getUnit()
                );

        // =================================================
        // QUANTITY
        // =================================================

        Label quantityLabel =
                createLabel(
                        "Quantity"
                );

        quantityField =
                createTextField(
                        String.valueOf(
                                product.getQuantity()
                        )
                );

        // =================================================
        // LOCATION
        // =================================================

        Label locationLabel =
                createLabel(
                        "Location"
                );

        locationField =
                createTextField(
                        product.getLocation()
                );

        // =================================================
        // IMAGE PATH
        // =================================================

        Label imagePathLabel =
                createLabel(
                        "Image URL / Path"
                );

        imagePathField =
                createTextField(
                        product.getImagePath()
                );

        // =================================================
        // DESCRIPTION
        // =================================================

        Label descriptionLabel =
                createLabel(
                        "Description"
                );

        descriptionArea =
                new TextArea(
                        safe(
                                product.getDescription()
                        )
                );

        descriptionArea.setPrefRowCount(
                4
        );

        descriptionArea.setWrapText(
                true
        );

        descriptionArea.setStyle(
                "-fx-control-inner-background:#101516;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;"
        );

        // =================================================
        // GRID
        // =================================================

        grid.add(
                productNameLabel,
                0,
                0
        );

        grid.add(
                productNameField,
                1,
                0
        );

        grid.add(
                categoryLabel,
                0,
                1
        );

        grid.add(
                categoryField,
                1,
                1
        );

        grid.add(
                priceLabel,
                0,
                2
        );

        grid.add(
                priceField,
                1,
                2
        );

        grid.add(
                unitLabel,
                0,
                3
        );

        grid.add(
                unitField,
                1,
                3
        );

        grid.add(
                quantityLabel,
                0,
                4
        );

        grid.add(
                quantityField,
                1,
                4
        );

        grid.add(
                locationLabel,
                0,
                5
        );

        grid.add(
                locationField,
                1,
                5
        );

        grid.add(
                imagePathLabel,
                0,
                6
        );

        grid.add(
                imagePathField,
                1,
                6
        );

        grid.add(
                descriptionLabel,
                0,
                7
        );

        grid.add(
                descriptionArea,
                1,
                7
        );

        GridPane.setHgrow(
                productNameField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                categoryField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                priceField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                unitField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                quantityField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                locationField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                imagePathField,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                descriptionArea,
                Priority.ALWAYS
        );

        container.getChildren().add(
                grid
        );

        return container;
    }

    // =====================================================
    // BOTTOM BUTTONS
    // =====================================================

    private HBox createButtons() {

        HBox buttons =
                new HBox(12);

        buttons.setPadding(
                new Insets(
                        15,
                        30,
                        25,
                        30
                )
        );

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button cancel =
                new Button(
                        "Cancel"
                );

        cancel.setPrefWidth(
                100
        );

        cancel.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#AAAAAA;" +
                "-fx-border-color:#444444;" +
                "-fx-border-radius:5;" +
                "-fx-padding:9 15;" +
                "-fx-cursor:hand;"
        );

        cancel.setOnAction(
                e -> stage.close()
        );

        Button save =
                new Button(
                        "Save Changes"
                );

        save.setPrefWidth(
                130
        );

        save.setStyle(
                "-fx-background-color:#245D35;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:5;" +
                "-fx-padding:9 15;" +
                "-fx-cursor:hand;"
        );

        save.setOnAction(
                e -> saveProduct()
        );

        buttons.getChildren().addAll(
                cancel,
                save
        );

        return buttons;
    }

    // =====================================================
    // SAVE PRODUCT
    // =====================================================

    private void saveProduct() {

        // =================================================
        // VALIDATE TEXT
        // =================================================

        String productName =
                productNameField
                        .getText()
                        .trim();

        String category =
                categoryField
                        .getText()
                        .trim();

        String priceText =
                priceField
                        .getText()
                        .trim();

        String unit =
                unitField
                        .getText()
                        .trim();

        String quantityText =
                quantityField
                        .getText()
                        .trim();

        String location =
                locationField
                        .getText()
                        .trim();

        String imagePath =
                imagePathField
                        .getText()
                        .trim();

        String description =
                descriptionArea
                        .getText()
                        .trim();

        if (productName.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Product name is required."
            );

            return;
        }

        if (category.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Category is required."
            );

            return;
        }

        if (unit.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Unit is required."
            );

            return;
        }

        // =================================================
        // PARSE PRICE
        // =================================================

        double price;

        try {

            price =
                    Double.parseDouble(
                            priceText
                    );

            if (price < 0) {

                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Price",
                    "Please enter a valid positive price."
            );

            return;
        }

        // =================================================
        // PARSE QUANTITY
        // =================================================

        double quantity;

        try {

            quantity =
                    Double.parseDouble(
                            quantityText
                    );

            if (quantity < 0) {

                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Quantity",
                    "Please enter a valid positive quantity."
            );

            return;
        }

        // =================================================
        // UPDATE EXISTING PRODUCT
        // =================================================

        product.setProductName(
                productName
        );

        product.setCategory(
                category
        );

        product.setPrice(
                price
        );

        product.setUnit(
                unit
        );

        product.setQuantity(
                quantity
        );

        product.setLocation(
                location
        );

        product.setImagePath(
                imagePath
        );

        product.setDescription(
                description
        );

        // =================================================
        // FIREBASE UPDATE
        // =================================================

        boolean updated =
                productController
                        .updateProduct(
                                product
                        );

        if (updated) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Product Updated",
                    "Product has been updated successfully."
            );

            stage.close();

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Update Failed",
                    "Unable to update the product."
            );
        }
    }

    // =====================================================
    // TEXT FIELD
    // =====================================================

    private TextField createTextField(
            String value) {

        TextField field =
                new TextField(
                        safe(value)
                );

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:6;" +
                "-fx-background-radius:6;" +
                "-fx-padding:9 12;"
        );

        return field;
    }

    // =====================================================
    // LABEL
    // =====================================================

    private Label createLabel(
            String text) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        return label;
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    // =====================================================
    // SAFE
    // =====================================================

    private String safe(
            String value) {

        if (value == null) {

            return "";
        }

        return value;
    }
}