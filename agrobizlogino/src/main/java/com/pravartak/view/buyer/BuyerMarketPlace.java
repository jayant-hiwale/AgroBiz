// package com.pravartak.view.buyer;

// import com.pravartak.controller.farmercontoller.ProductController;
// import com.pravartak.model.farmer_model.Product;
// import com.pravartak.view.buyer.common.buyerTop;
// import com.pravartak.view.farmer.common.Footer;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.FlowPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.VBox;

// import java.util.List;

// public class BuyerMarketPlace {

//     private final ProductController controller;

//     private FlowPane productGrid;

//     private Label resultLabel;

//     // =====================================================
//     // CONSTRUCTOR
//     // =====================================================

//     public BuyerMarketPlace() {

//         controller = new ProductController();
//     }

//     // =====================================================
//     // PAGE
//     // =====================================================

//     public BorderPane getMarketplacePage() {

//         // =================================================
//         // MAIN BORDERPANE
//         // =================================================

//         BorderPane root = new BorderPane();

//         root.setStyle(
//                 "-fx-background-color:#0D1117;"
//         );

//         // =================================================
//         // TOP NAVBAR
//         // =================================================

//         root.setTop(
//                 new buyerTop().createBuyerTop("Marketplace")
//         );

//         // =================================================
//         // FOOTER
//         // =================================================

//         root.setBottom(
//                 new Footer().createFooter()
//         );

//         // =================================================
//         // MAIN CONTENT
//         // =================================================

//         VBox content = new VBox(20);

//         content.setPadding(
//                 new Insets(
//                         25,
//                         35,
//                         25,
//                         35
//                 )
//         );

//         // =================================================
//         // TITLE
//         // =================================================

//         Label title = new Label(
//                 "Marketplace"
//         );

//         title.setStyle(
//                 "-fx-text-fill:#EEEEEE;" +
//                 "-fx-font-size:32px;" +
//                 "-fx-font-weight:bold;"
//         );

//         // =================================================
//         // SUBTITLE
//         // =================================================

//         Label subtitle = new Label(
//                 "Buy fresh agricultural products directly from farmers."
//         );

//         subtitle.setStyle(
//                 "-fx-text-fill:#AAAAAA;" +
//                 "-fx-font-size:14px;"
//         );

//         // =================================================
//         // SEARCH BAR
//         // =================================================

//         HBox searchBar = new HBox(10);

//         searchBar.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         TextField search = new TextField();

//         search.setPromptText(
//                 "Search products..."
//         );

//         search.setPrefWidth(
//                 350
//         );

//         search.setPrefHeight(
//                 38
//         );

//         search.setStyle(
//                 "-fx-background-color:#161B22;" +
//                 "-fx-text-fill:#FFFFFF;" +
//                 "-fx-prompt-text-fill:#777777;" +
//                 "-fx-border-color:#30363D;" +
//                 "-fx-border-radius:7;" +
//                 "-fx-background-radius:7;" +
//                 "-fx-padding:8 12;"
//         );

//         resultLabel = new Label(
//                 "Products"
//         );

//         resultLabel.setStyle(
//                 "-fx-text-fill:#AAAAAA;" +
//                 "-fx-font-size:13px;"
//         );

//         search.setOnKeyReleased(
//                 e -> searchProducts(
//                         search.getText()
//                 )
//         );

//         searchBar.getChildren().addAll(
//                 search,
//                 resultLabel
//         );

//         // =================================================
//         // PRODUCT GRID
//         // =================================================

//         productGrid = new FlowPane();

//         productGrid.setHgap(
//                 20
//         );

//         productGrid.setVgap(
//                 20
//         );

//         productGrid.setPadding(
//                 new Insets(5)
//         );

//         productGrid.setAlignment(
//                 Pos.TOP_LEFT
//         );

//         // =================================================
//         // SCROLL PANE
//         // =================================================

//         ScrollPane scroll = new ScrollPane(
//                 productGrid
//         );

//         scroll.setFitToWidth(
//                 true
//         );

//         scroll.setFitToHeight(
//                 false
//         );

//         scroll.setStyle(
//                 "-fx-background:#0D1117;" +
//                 "-fx-background-color:#0D1117;" +
//                 "-fx-control-inner-background:#0D1117;" +
//                 "-fx-border-color:transparent;"
//         );

//         VBox.setVgrow(
//                 scroll,
//                 Priority.ALWAYS
//         );

//         // =================================================
//         // ADD CONTENT
//         // =================================================

//         content.getChildren().addAll(
//                 title,
//                 subtitle,
//                 searchBar,
//                 scroll
//         );

//         // =================================================
//         // CENTER
//         // =================================================

//         root.setCenter(
//                 content
//         );

//         // =================================================
//         // LOAD PRODUCTS
//         // =================================================

//         loadProducts();

//         return root;
//     }

//     // =====================================================
//     // LOAD ALL PRODUCTS
//     // =====================================================

//     private void loadProducts() {

//         productGrid
//                 .getChildren()
//                 .clear();

//         List<Product> products =
//                 controller.getAllProducts();

//         resultLabel.setText(
//                 products.size()
//                         + " Products"
//         );

//         for (Product product :
//                 products) {

//             productGrid
//                     .getChildren()
//                     .add(
//                             createProductCard(
//                                     product
//                             )
//                     );
//         }
//     }

//     // =====================================================
//     // SEARCH PRODUCTS
//     // =====================================================

//     private void searchProducts(
//             String text) {

//         List<Product> products;

//         if (text == null ||
//                 text.trim().isEmpty()) {

//             products =
//                     controller.getAllProducts();

//         } else {

//             products =
//                     controller.searchAllProducts(
//                             text
//                     );
//         }

//         productGrid
//                 .getChildren()
//                 .clear();

//         resultLabel.setText(
//                 products.size()
//                         + " Products"
//         );

//         for (Product product :
//                 products) {

//             productGrid
//                     .getChildren()
//                     .add(
//                             createProductCard(
//                                     product
//                             )
//                     );
//         }
//     }

//     // =====================================================
//     // PRODUCT CARD
//     // =====================================================

//     private VBox createProductCard(
//             Product product) {

//         VBox card =
//                 new VBox();

//         card.setPrefWidth(
//                 280
//         );

//         card.setStyle(
//                 "-fx-background-color:#101516;" +
//                 "-fx-border-color:#242B2C;" +
//                 "-fx-border-radius:12;" +
//                 "-fx-background-radius:12;"
//         );

//         // =================================================
//         // IMAGE
//         // =================================================

//         VBox imageBox =
//                 new VBox();

//         imageBox.setPrefHeight(
//                 165
//         );

//         imageBox.setAlignment(
//                 Pos.CENTER
//         );

//         imageBox.setStyle(
//                 "-fx-background-color:#1B2425;" +
//                 "-fx-background-radius:12 12 0 0;"
//         );

//         String imagePath =
//                 product.getImagePath();

//         if (imagePath != null &&
//                 !imagePath.trim().isEmpty()) {

//             try {

//                 Image image =
//                         new Image(
//                                 imagePath,
//                                 280,
//                                 165,
//                                 true,
//                                 true
//                         );

//                 if (!image.isError()) {

//                     ImageView imageView =
//                             new ImageView(
//                                     image
//                             );

//                     imageView.setPreserveRatio(
//                             true
//                     );

//                     imageView.setFitWidth(
//                             280
//                     );

//                     imageView.setFitHeight(
//                             165
//                     );

//                     imageBox
//                             .getChildren()
//                             .add(
//                                     imageView
//                             );

//                 } else {

//                     addPlaceholder(
//                             imageBox
//                     );
//                 }

//             } catch (Exception e) {

//                 addPlaceholder(
//                         imageBox
//                 );
//             }

//         } else {

//             addPlaceholder(
//                     imageBox
//             );
//         }

//         // =================================================
//         // DETAILS
//         // =================================================

//         VBox details =
//                 new VBox(8);

//         details.setPadding(
//                 new Insets(15)
//         );

//         Label name =
//                 new Label(
//                         safe(
//                                 product.getProductName()
//                         )
//                 );

//         name.setStyle(
//                 "-fx-text-fill:#EEEEEE;" +
//                 "-fx-font-size:18px;" +
//                 "-fx-font-weight:bold;"
//         );

//         Label category =
//                 new Label(
//                         safe(
//                                 product.getCategory()
//                         )
//                 );

//         category.setStyle(
//                 "-fx-text-fill:#888888;" +
//                 "-fx-font-size:12px;"
//         );

//         Label price =
//                 new Label(
//                         "₹"
//                                 + product.getPrice()
//                                 + " / "
//                                 + safe(
//                                 product.getUnit()
//                         )
//                 );

//         price.setStyle(
//                 "-fx-text-fill:#68D34A;" +
//                 "-fx-font-size:18px;" +
//                 "-fx-font-weight:bold;"
//         );

//         Label quantity =
//                 new Label(
//                         "Available: "
//                                 + product.getQuantity()
//                                 + " "
//                                 + safe(
//                                 product.getUnit()
//                         )
//                 );

//         quantity.setStyle(
//                 "-fx-text-fill:#AAAAAA;"
//         );

//         Label location =
//                 new Label(
//                         "📍 "
//                                 + safe(
//                                 product.getLocation()
//                         )
//                 );

//         location.setStyle(
//                 "-fx-text-fill:#AAAAAA;"
//         );

//         // =================================================
//         // BUTTON BOX
//         // =================================================

//         HBox buttonBox =
//                 new HBox(8);

//         buttonBox.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         // =================================================
//         // LIKE BUTTON
//         // =================================================

//         Button likeButton =
//                 new Button();

//         likeButton.setPrefWidth(
//                 55
//         );

//         updateLikeButton(
//                 likeButton,
//                 product
//         );

//         likeButton.setOnAction(e -> {

//             if (WatchlistManager.isLiked(
//                     product
//             )) {

//                 WatchlistManager.removeProduct(
//                         product
//                 );

//             } else {

//                 WatchlistManager.addProduct(
//                         product
//                 );
//             }

//             updateLikeButton(
//                     likeButton,
//                     product
//             );
//         });

//         // =================================================
//         // CONTACT FARMER
//         // =================================================

//         Button contact =
//                 new Button(
//                         "Contact Farmer"
//                 );

//         contact.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         HBox.setHgrow(
//                 contact,
//                 Priority.ALWAYS
//         );

//         contact.setStyle(
//                 "-fx-background-color:#68D34A;" +
//                 "-fx-text-fill:#080C0D;" +
//                 "-fx-font-weight:bold;" +
//                 "-fx-background-radius:7;" +
//                 "-fx-padding:9;" +
//                 "-fx-cursor:hand;"
//         );

//         contact.setOnAction(
//                 e -> showFarmerDetails(
//                         product
//                 )
//         );

//         buttonBox.getChildren().addAll(
//                 likeButton,
//                 contact
//         );

//         // =================================================
//         // DETAILS CHILDREN
//         // =================================================

//         details.getChildren().addAll(
//                 name,
//                 category,
//                 price,
//                 quantity,
//                 location,
//                 buttonBox
//         );

//         card.getChildren().addAll(
//                 imageBox,
//                 details
//         );

//         return card;
//     }

//     // =====================================================
//     // LIKE BUTTON UI
//     // =====================================================

//     private void updateLikeButton(
//             Button button,
//             Product product) {

//         if (WatchlistManager.isLiked(
//                 product
//         )) {

//             button.setText(
//                     "❤️"
//             );

//             button.setStyle(
//                     "-fx-background-color:#3A1518;" +
//                     "-fx-text-fill:#FF4D5A;" +
//                     "-fx-font-size:18px;" +
//                     "-fx-background-radius:7;" +
//                     "-fx-cursor:hand;"
//             );

//         } else {

//             button.setText(
//                     "♡"
//             );

//             button.setStyle(
//                     "-fx-background-color:#212627;" +
//                     "-fx-text-fill:#AAAAAA;" +
//                     "-fx-font-size:22px;" +
//                     "-fx-background-radius:7;" +
//                     "-fx-cursor:hand;"
//             );
//         }
//     }

//     // =====================================================
//     // FARMER DETAILS
//     // =====================================================

//     private void showFarmerDetails(
//             Product product) {

//         int farmerId =
//                 product.getFarmerId();

//         System.out.println(
//                 "Opening farmer details for ID = "
//                         + farmerId
//         );

//         FarmerDetailsPage page =
//                 new FarmerDetailsPage(
//                         farmerId,
//                         product.getProductName()
//                 );

//         page.show();
//     }

//     // =====================================================
//     // PLACEHOLDER
//     // =====================================================

//     private void addPlaceholder(
//             VBox box) {

//         Label label =
//                 new Label(
//                         "Product Image"
//                 );

//         label.setStyle(
//                 "-fx-text-fill:#666666;"
//         );

//         box.getChildren().add(
//                 label
//         );
//     }

//     // =====================================================
//     // SAFE
//     // =====================================================

//     private String safe(
//             String value) {

//         if (value == null ||
//                 value.trim().isEmpty()) {

//             return "Not provided";
//         }

//         return value;
//     }
//     public void searchByCategory(String category) {

//     if (category == null || category.trim().isEmpty()) {
//         return;
//     }

//     searchProducts(category);
// }
// }
package com.pravartak.view.buyer;

import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BuyerMarketPlace {

    private final ProductController controller;

    private FlowPane productGrid;

    private Label resultLabel;

    private TextField searchField;

    private ComboBox<String> categoryBox;

    private ComboBox<String> locationBox;

    private ComboBox<String> sortBox;

    private List<Product> allProducts =
            new ArrayList<>();

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public BuyerMarketPlace() {

        controller =
                new ProductController();
    }

    // =====================================================
    // MARKETPLACE PAGE
    // =====================================================

    public BorderPane getMarketplacePage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // TOP
        // =================================================

        root.setTop(
                new buyerTop().createBuyerTop("Market")
        );

        // =================================================
        // FOOTER
        // =================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =================================================
        // MAIN CONTENT
        // =================================================

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        25,
                        35,
                        25,
                        35
                )
        );

        // =================================================
        // TITLE
        // =================================================

        Label title =
                new Label(
                        "Marketplace"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // SUBTITLE
        // =================================================

        Label subtitle =
                new Label(
                        "Buy fresh agricultural products directly from farmers."
                );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        // =================================================
        // SEARCH
        // =================================================

        HBox searchBar =
                new HBox(10);

        searchBar.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "🔍 Search products, category, location..."
        );

        searchField.setPrefWidth(
                380
        );

        searchField.setPrefHeight(
                40
        );

        searchField.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#FFFFFF;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8 12;"
        );

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        resultLabel =
                new Label(
                        "Products"
                );

        resultLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        searchBar.getChildren().addAll(
                searchField,
                resultLabel
        );

        // =================================================
        // FILTER BAR
        // =================================================

        HBox filterBar =
                new HBox(10);

        filterBar.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // CATEGORY
        // =================================================

        categoryBox =
                new ComboBox<>();

        categoryBox.getItems().addAll(
                "All Categories",
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

        categoryBox.setValue(
                "All Categories"
        );

        categoryBox.setPrefWidth(
                170
        );

        // =================================================
        // LOCATION
        // =================================================

        locationBox =
                new ComboBox<>();

        locationBox.getItems().add(
                "All Locations"
        );

        locationBox.setValue(
                "All Locations"
        );

        locationBox.setPrefWidth(
                180
        );

        // =================================================
        // SORT
        // =================================================

        sortBox =
                new ComboBox<>();

        sortBox.getItems().addAll(
                "Default",
                "Price: Low to High",
                "Price: High to Low",
                "Quantity: High to Low",
                "Name: A to Z",
                "Name: Z to A"
        );

        sortBox.setValue(
                "Default"
        );

        sortBox.setPrefWidth(
                190
        );

        styleComboBox(categoryBox);
        styleComboBox(locationBox);
        styleComboBox(sortBox);

        categoryBox.setOnAction(
                e -> applyFilters()
        );

        locationBox.setOnAction(
                e -> applyFilters()
        );

        sortBox.setOnAction(
                e -> applyFilters()
        );

        // =================================================
        // CLEAR FILTERS
        // =================================================

        Button clearButton =
                new Button(
                        "Clear Filters"
                );

        clearButton.setStyle(
                "-fx-background-color:#212627;" +
                "-fx-text-fill:#AAAAAA;" +
                "-fx-border-color:#303738;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:9 14;" +
                "-fx-cursor:hand;"
        );

        clearButton.setOnAction(e -> {

            searchField.clear();

            categoryBox.setValue(
                    "All Categories"
            );

            locationBox.setValue(
                    "All Locations"
            );

            sortBox.setValue(
                    "Default"
            );

            applyFilters();
        });

        filterBar.getChildren().addAll(
                categoryBox,
                locationBox,
                sortBox,
                clearButton
        );

        // =================================================
        // PRODUCT GRID
        // =================================================

        productGrid =
                new FlowPane();

        productGrid.setHgap(
                20
        );

        productGrid.setVgap(
                20
        );

        productGrid.setPadding(
                new Insets(5)
        );

        productGrid.setAlignment(
                Pos.TOP_LEFT
        );

        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scroll =
                new ScrollPane(
                        productGrid
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setFitToHeight(
                false
        );

        scroll.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        // =================================================
        // ADD CONTENT
        // =================================================

        content.getChildren().addAll(
                title,
                subtitle,
                searchBar,
                filterBar,
                scroll
        );

        root.setCenter(
                content
        );

        // =================================================
        // LOAD PRODUCTS
        // =================================================

        loadProducts();

        return root;
    }

    // =====================================================
    // LOAD PRODUCTS
    // =====================================================

    private void loadProducts() {

        allProducts =
                controller.getAllProducts();

        populateLocationFilter();

        applyFilters();
    }

    // =====================================================
    // LOCATION FILTER
    // =====================================================

    private void populateLocationFilter() {

        String currentLocation =
                locationBox.getValue();

        locationBox.getItems().clear();

        locationBox.getItems().add(
                "All Locations"
        );

        for (Product product :
                allProducts) {

            String location =
                    product.getLocation();

            if (location == null ||
                    location.trim().isEmpty()) {

                continue;
            }

            boolean exists =
                    false;

            for (String item :
                    locationBox.getItems()) {

                if (item.equalsIgnoreCase(
                        location.trim()
                )) {

                    exists = true;
                    break;
                }
            }

            if (!exists) {

                locationBox.getItems().add(
                        location.trim()
                );
            }
        }

        if (currentLocation != null &&
                locationBox.getItems().contains(
                        currentLocation
                )) {

            locationBox.setValue(
                    currentLocation
            );

        } else {

            locationBox.setValue(
                    "All Locations"
            );
        }
    }

    // =====================================================
    // APPLY FILTERS
    // =====================================================

    private void applyFilters() {

        if (productGrid == null) {
            return;
        }

        List<Product> filtered =
                new ArrayList<>(
                        allProducts
                );

        // =================================================
        // SEARCH
        // =================================================

        String search =
                searchField == null
                        ? ""
                        : searchField
                                .getText()
                                .trim()
                                .toLowerCase();

        if (!search.isEmpty()) {

            filtered.removeIf(
                    product -> !matchesSearch(
                            product,
                            search
                    )
            );
        }

        // =================================================
        // CATEGORY
        // =================================================

        String category =
                categoryBox.getValue();

        if (category != null &&
                !category.equals(
                        "All Categories"
                )) {

            filtered.removeIf(
                    product ->
                            product.getCategory() == null
                            ||
                            !product.getCategory()
                                    .equalsIgnoreCase(
                                            category
                                    )
            );
        }

        // =================================================
        // LOCATION
        // =================================================

        String location =
                locationBox.getValue();

        if (location != null &&
                !location.equals(
                        "All Locations"
                )) {

            filtered.removeIf(
                    product ->
                            product.getLocation() == null
                            ||
                            !product.getLocation()
                                    .equalsIgnoreCase(
                                            location
                                    )
            );
        }

        // =================================================
        // SORT
        // =================================================

        String sort =
                sortBox.getValue();

        if (sort != null &&
                !sort.equals("Default")) {

            filtered =
                    controller.sortProducts(
                            filtered,
                            convertSortType(sort)
                    );
        }

        displayProducts(
                filtered
        );
    }

    // =====================================================
    // SEARCH MATCH
    // =====================================================

    private boolean matchesSearch(
            Product product,
            String search) {

        if (product == null) {
            return false;
        }

        if (contains(
                product.getProductName(),
                search
        )) {

            return true;
        }

        if (contains(
                product.getCategory(),
                search
        )) {

            return true;
        }

        if (contains(
                product.getLocation(),
                search
        )) {

            return true;
        }

        if (contains(
                product.getDescription(),
                search
        )) {

            return true;
        }

        if (contains(
                product.getUnit(),
                search
        )) {

            return true;
        }

        return false;
    }

    // =====================================================
    // CONTAINS
    // =====================================================

    private boolean contains(
            String value,
            String search) {

        return value != null &&
                value.toLowerCase()
                        .contains(search);
    }

    // =====================================================
    // SORT TYPE
    // =====================================================

    private String convertSortType(
            String sort) {

        switch (sort) {

            case "Price: Low to High":
                return "Price Low-High";

            case "Price: High to Low":
                return "Price High-Low";

            case "Quantity: High to Low":
                return "Quantity High-Low";

            case "Name: A to Z":
                return "Name A-Z";

            case "Name: Z to A":
                return "Name Z-A";

            default:
                return "Default";
        }
    }

    // =====================================================
    // DISPLAY PRODUCTS
    // =====================================================

    private void displayProducts(
            List<Product> products) {

        productGrid
                .getChildren()
                .clear();

        resultLabel.setText(
                products.size()
                        + " Products"
        );

        if (products.isEmpty()) {

            VBox empty =
                    new VBox(12);

            empty.setAlignment(
                    Pos.CENTER
            );

            empty.setPadding(
                    new Insets(70)
            );

            Label icon =
                    new Label("🔍");

            icon.setStyle(
                    "-fx-font-size:40px;"
            );

            Label message =
                    new Label(
                            "No products found"
                    );

            message.setStyle(
                    "-fx-text-fill:#EEEEEE;" +
                    "-fx-font-size:20px;" +
                    "-fx-font-weight:bold;"
            );

            Label hint =
                    new Label(
                            "Try changing your search or filters."
                    );

            hint.setStyle(
                    "-fx-text-fill:#777777;" +
                    "-fx-font-size:13px;"
            );

            empty.getChildren().addAll(
                    icon,
                    message,
                    hint
            );

            productGrid.getChildren().add(
                    empty
            );

            return;
        }

        for (Product product :
                products) {

            productGrid.getChildren().add(
                    createProductCard(
                            product
                    )
            );
        }
    }

    // =====================================================
    // PRODUCT CARD
    // =====================================================

    private VBox createProductCard(
            Product product) {

        VBox card =
                new VBox();

        card.setPrefWidth(
                280
        );

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        // =================================================
        // IMAGE
        // =================================================

        VBox imageBox =
                new VBox();

        imageBox.setPrefHeight(
                165
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color:#1B2425;" +
                "-fx-background-radius:12 12 0 0;"
        );

        String imagePath =
                product.getImagePath();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imagePath,
                                280,
                                165,
                                true,
                                true
                        );

                if (!image.isError()) {

                    ImageView imageView =
                            new ImageView(
                                    image
                            );

                    imageView.setPreserveRatio(
                            true
                    );

                    imageView.setFitWidth(
                            280
                    );

                    imageView.setFitHeight(
                            165
                    );

                    imageBox.getChildren().add(
                            imageView
                    );

                } else {

                    addPlaceholder(
                            imageBox
                    );
                }

            } catch (Exception e) {

                addPlaceholder(
                        imageBox
                );
            }

        } else {

            addPlaceholder(
                    imageBox
            );
        }

        // =================================================
        // DETAILS
        // =================================================

        VBox details =
                new VBox(8);

        details.setPadding(
                new Insets(15)
        );

        Label name =
                new Label(
                        safe(
                                product.getProductName()
                        )
                );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label category =
                new Label(
                        safe(
                                product.getCategory()
                        )
                );

        category.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:12px;"
        );

        Label price =
                new Label(
                        String.format(
                                "₹%.2f / %s",
                                product.getPrice(),
                                safe(product.getUnit())
                        )
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label quantity =
                new Label(
                        "Available: "
                                + formatNumber(
                                product.getQuantity()
                        )
                                + " "
                                + safe(
                                product.getUnit()
                        )
                );

        quantity.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        Label location =
                new Label(
                        "📍 "
                                + safe(
                                product.getLocation()
                        )
                );

        location.setStyle(
                "-fx-text-fill:#AAAAAA;"
        );

        // =================================================
        // BUTTONS
        // =================================================

        HBox buttonBox =
                new HBox(7);

        buttonBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =================================================
        // LIKE
        // =================================================

        Button likeButton =
                new Button();

        likeButton.setPrefWidth(
                48
        );

        updateLikeButton(
                likeButton,
                product
        );

        likeButton.setOnAction(e -> {

            if (WatchlistManager.isLiked(
                    product
            )) {

                WatchlistManager.removeProduct(
                        product
                );

            } else {

                WatchlistManager.addProduct(
                        product
                );
            }

            updateLikeButton(
                    likeButton,
                    product
            );
        });

        // =================================================
        // VIEW DETAILS
        // =================================================

        Button detailsButton =
                new Button(
                        "View Details"
                );

        detailsButton.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                detailsButton,
                Priority.ALWAYS
        );

        detailsButton.setStyle(
                "-fx-background-color:#212627;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:9;" +
                "-fx-cursor:hand;"
        );

        detailsButton.setOnAction(e -> {

            openProductDetails(
                    product
            );
        });

        // =================================================
        // ADD TO CART
        // =================================================

        Button cartButton =
                new Button(
                        "🛒"
                );

        cartButton.setPrefWidth(
                48
        );

        cartButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8;" +
                "-fx-cursor:hand;"
        );

        cartButton.setOnAction(e -> {

            addToCart(
                    product
            );
        });

        buttonBox.getChildren().addAll(
                likeButton,
                detailsButton,
                cartButton
        );

        // =================================================
        // ADD DETAILS
        // =================================================

        details.getChildren().addAll(
                name,
                category,
                price,
                quantity,
                location,
                buttonBox
        );

        card.getChildren().addAll(
                imageBox,
                details
        );

        return card;
    }

    // =====================================================
    // ADD TO CART
    // =====================================================

    private void addToCart(
            Product product) {

        if (product == null) {
            return;
        }

        if (product.getQuantity() <= 0) {

            showMessage(
                    "This product is currently out of stock."
            );

            return;
        }

        CartManager.addProduct(
                product,
                1
        );

        showMessage(
                product.getProductName()
                        + " added to cart."
        );
    }

    // =====================================================
    // PRODUCT DETAILS
    // =====================================================

    private void openProductDetails(
            Product product) {

        ProductDetailsPage page =
                new ProductDetailsPage(
                        product
                );

        LoginPage.mainStage.setScene(
                new Scene(
                        page.getProductDetailsPage()
                )
        );
    }

    // =====================================================
    // LIKE BUTTON
    // =====================================================

    private void updateLikeButton(
            Button button,
            Product product) {

        if (WatchlistManager.isLiked(
                product
        )) {

            button.setText(
                    "❤️"
            );

            button.setStyle(
                    "-fx-background-color:#3A1518;" +
                    "-fx-text-fill:#FF4D5A;" +
                    "-fx-font-size:17px;" +
                    "-fx-background-radius:7;" +
                    "-fx-cursor:hand;"
            );

        } else {

            button.setText(
                    "♡"
            );

            button.setStyle(
                    "-fx-background-color:#212627;" +
                    "-fx-text-fill:#AAAAAA;" +
                    "-fx-font-size:22px;" +
                    "-fx-background-radius:7;" +
                    "-fx-cursor:hand;"
            );
        }
    }

    // =====================================================
    // CATEGORY SEARCH
    // =====================================================

    public void searchByCategory(
            String category) {

        if (category == null ||
                category.trim().isEmpty()) {

            return;
        }

        searchField.setText(
                category
        );
    }

    // =====================================================
    // COMBOBOX STYLE
    // =====================================================

    private void styleComboBox(
            ComboBox<String> box) {

        box.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:4;"
        );
    }

    // =====================================================
    // PLACEHOLDER
    // =====================================================

    private void addPlaceholder(
            VBox box) {

        Label label =
                new Label(
                        "Product Image"
                );

        label.setStyle(
                "-fx-text-fill:#666666;"
        );

        box.getChildren().add(
                label
        );
    }

    // =====================================================
    // FORMAT NUMBER
    // =====================================================

    private String formatNumber(
            double number) {

        if (number == (long) number) {

            return String.valueOf(
                    (long) number
            );
        }

        return String.format(
                "%.2f",
                number
        );
    }

    // =====================================================
    // SAFE
    // =====================================================

    private String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }

    // =====================================================
    // MESSAGE
    // =====================================================

    private void showMessage(
            String message) {

        Label label =
                new Label(
                        message
                );

        label.setStyle(
                "-fx-background-color:#1B2425;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-padding:12 18;" +
                "-fx-background-radius:7;"
        );

        javafx.stage.Popup popup =
                new javafx.stage.Popup();

        popup.getContent().add(
                label
        );

        if (LoginPage.mainStage != null) {

            popup.show(
                    LoginPage.mainStage,
                    LoginPage.mainStage.getX()
                            + 40,
                    LoginPage.mainStage.getY()
                            + 100
            );

            javafx.animation.PauseTransition pause =
                    new javafx.animation.PauseTransition(
                            javafx.util.Duration.seconds(2)
                    );

            pause.setOnFinished(
                    e -> popup.hide()
            );

            pause.play();
        }
    }
}