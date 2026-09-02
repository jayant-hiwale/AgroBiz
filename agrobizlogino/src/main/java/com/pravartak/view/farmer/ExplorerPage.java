// package com.pravartak.view.farmer;

// import java.net.URL;

// import com.pravartak.view.farmer.common.Footer;
// import com.pravartak.view.farmer.common.NavBar;
// import com.pravartak.view.login.LoginPage;


// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.CornerRadii;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

// public class ExplorerPage {
//     private Scene explorepageScene;
//     public Scene getExplorerPage() {

//         // MAIN BORDER PANE
//         BorderPane borderPane = new BorderPane();
//         borderPane.setStyle("-fx-background-color: #080c0d;");
//         borderPane.setTop(new NavBar().createNavbar("Explorer"));
//         borderPane.setBottom(new Footer().createFooter());

//         // MAIN VBOX
//         VBox mainVBox = new VBox(25);
//         mainVBox.setPadding(new Insets(35, 45, 45, 45));
//         mainVBox.setAlignment(Pos.TOP_CENTER);
//         mainVBox.setBackground(new Background(new BackgroundFill(Color.web("#080c0d"), CornerRadii.EMPTY, Insets.EMPTY)));

//         // MAIN TITLE
//         Label mainTitle = new Label("Discover Livestock\n" + "Innovations");
//         mainTitle.setFont(Font.font("Arial", FontWeight.BOLD, 42));
//         mainTitle.setTextFill(Color.web("#eeeeee"));
//         mainTitle.setAlignment(Pos.CENTER);
//         mainTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

//         // DESCRIPTION
//         Label description = new Label("Explore high-tech farming guides, trending animal " + "husbandry businesses, and advanced\n" + "livestock categories to elevate your agricultural enterprise.");
//         description.setFont(Font.font("Arial", 15));
//         description.setTextFill(Color.web("#aaaaaa"));
//         description.setWrapText(true);
//         description.setAlignment(Pos.CENTER);
//         description.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

//         // SEARCH BAR
//         TextField searchField = new TextField();
//         searchField.setPromptText("Search breeds, technologies, or guides...");
//         searchField.setPrefHeight(48);
//         searchField.setPrefWidth(620);
//         searchField.setPadding(new Insets(0, 18, 0, 18));
//         searchField.setStyle("-fx-background-color: #101516;" +"-fx-text-fill: #eeeeee;" +"-fx-prompt-text-fill: #777777;" +"-fx-border-color: #242b2c;" +"-fx-border-radius: 8;" +"-fx-background-radius: 8;");
//         //searchField.setOnKeyReleased(e -> searchProducts(searchField.getText()));


//         Button searchButton = new Button("Search");
//         searchButton.setPrefWidth(90);
//         searchButton.setPrefHeight(42);
//         searchButton.setStyle("-fx-background-color: #68d34a;" +"-fx-text-fill: #080c0d;" +"-fx-font-weight: bold;" +"-fx-background-radius: 6;" +"-fx-cursor: hand;");

//         HBox searchHBox = new HBox(8);
//         searchHBox.setAlignment(Pos.CENTER);
//         searchHBox.setMaxWidth(720);
//         searchHBox.setPadding(new Insets(5, 8, 5, 8));
// searchHBox.setStyle(
//                 "-fx-background-color: #0d1213;" +
//                 "-fx-background-radius: 10;" +
//                 "-fx-border-color: #242b2c;" +
//                 "-fx-border-radius: 10;"
//         );
//         searchHBox.getChildren().addAll(searchField, searchButton);

//         // CATEGORY FILTER BUTTONS
//         Button allCategories = new Button("All Categories");
//         Button poultry = new Button("Poultry");
//         Button dairy = new Button("Dairy Cattle");
//         Button aquatic = new Button("Aquaculture");
//         Button swine = new Button("Swine");
//         Button smallRuminants = new Button("Small Ruminants");
//         Button plantNursery = new Button("Plant Nursery");

//         allCategories.setStyle(
//                 "-fx-background-color: #68d34a;" +
//                 "-fx-text-fill: #080c0d;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 6;" +
//                 "-fx-padding: 8 14;" +
//                 "-fx-cursor: hand;"
//         );


//         String categoryStyle =
//                 "-fx-background-color: #101516;" +
//                 "-fx-text-fill: #aaaaaa;" +
//                 "-fx-border-color: #242b2c;" +
//                 "-fx-border-radius: 6;" +
//                 "-fx-background-radius: 6;" +
//                 "-fx-padding: 8 14;" +
//                 "-fx-cursor: hand;";
//         poultry.setStyle(categoryStyle);
//         dairy.setStyle(categoryStyle);
//         aquatic.setStyle(categoryStyle);
//         swine.setStyle(categoryStyle);
//         smallRuminants.setStyle(categoryStyle);
//         plantNursery.setStyle(categoryStyle);

//         HBox categoryFilterBox = new HBox(10);
//         categoryFilterBox.setAlignment(Pos.CENTER);
//         categoryFilterBox.getChildren().addAll(allCategories, poultry, dairy, aquatic, swine, smallRuminants, plantNursery);

//         // CATEGORY TITLE
//         Label categoryTitle = new Label("Explore Categories");
//         categoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
//         categoryTitle.setTextFill(Color.web("#eeeeee"));

//         // FIRST CATEGORY HBOX
//         HBox firstCategoryHBox = new HBox(18);
//         firstCategoryHBox.setAlignment(Pos.CENTER);

//         // POULTRY CARD
//         VBox poultryCard = new VBox(10);
//         poultryCard.setPadding(new Insets(10));
//         poultryCard.setPrefWidth(220);
//         poultryCard.setPrefHeight(220);
//         poultryCard.setAlignment(Pos.TOP_LEFT);
//         poultryCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         URL poultryURL = getClass().getResource("/poltry.png");

//         if (poultryURL == null) {
//             throw new RuntimeException("poltry.png not found!");
//         }

//         Image poultryImage = new Image(poultryURL.toExternalForm());

//         ImageView poultryImageView = new ImageView(poultryImage);
//         poultryImageView.setFitWidth(198);
//         poultryImageView.setFitHeight(105);
//         poultryImageView.setPreserveRatio(false);

//         Label poultryTitle = new Label("Poultry Farming");
//         poultryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         poultryTitle.setTextFill(Color.web("#eeeeee"));

//         Label poultryDescription = new Label("Advanced systems for optimal bird health.");
//         poultryDescription.setFont(Font.font("Arial", 12));
//         poultryDescription.setTextFill(Color.web("#888888"));
//         poultryDescription.setWrapText(true);

//         poultryCard.getChildren().addAll(poultryImageView, poultryTitle, poultryDescription);

//         // DAIRY CARD
//         VBox dairyCard = new VBox(10);
//         dairyCard.setPadding(new Insets(10));
//         dairyCard.setPrefWidth(220);
//         dairyCard.setPrefHeight(220);
//         dairyCard.setAlignment(Pos.TOP_LEFT);
//         dairyCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         URL dairyURL = getClass().getResource("/Dairy.png");

//         if (dairyURL == null) {
//             throw new RuntimeException("Dairy.png not found!");
//         }

//         Image dairyImage = new Image(dairyURL.toExternalForm());

//         ImageView dairyImageView = new ImageView(dairyImage);
//         dairyImageView.setFitWidth(198);
//         dairyImageView.setFitHeight(105);
//         dairyImageView.setPreserveRatio(false);

//         Label dairyTitle = new Label("Dairy Cattle");
//         dairyTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         dairyTitle.setTextFill(Color.web("#eeeeee"));

//         Label dairyDescription = new Label("Modern techniques for sustainable milk production.");
//         dairyDescription.setFont(Font.font("Arial", 12));
//         dairyDescription.setTextFill(Color.web("#888888"));
//         dairyDescription.setWrapText(true);

//         dairyCard.getChildren().addAll(dairyImageView, dairyTitle, dairyDescription);

//         // AQUACULTURE CARD
//         VBox aquaticCard = new VBox(10);
//         aquaticCard.setPadding(new Insets(10));
//         aquaticCard.setPrefWidth(220);
//         aquaticCard.setPrefHeight(220);
//         aquaticCard.setAlignment(Pos.TOP_LEFT);
//         aquaticCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         Image aquaticImage = new Image(getClass().getResource("/any.png").toExternalForm());

//         ImageView aquaticImageView = new ImageView(aquaticImage);
//         aquaticImageView.setFitWidth(198);
//         aquaticImageView.setFitHeight(105);

//         Label aquaticTitle = new Label("Aquaculture");
//         aquaticTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         aquaticTitle.setTextFill(Color.web("#eeeeee"));

//         Label aquaticDescription = new Label("Smart aquatic farming and sustainable fish production.");
//         aquaticDescription.setFont(Font.font("Arial", 12));
//         aquaticDescription.setTextFill(Color.web("#888888"));
//         aquaticDescription.setWrapText(true);

//         aquaticCard.getChildren().addAll(aquaticImageView, aquaticTitle, aquaticDescription);

//         // SWINE CARD
//         VBox swineCard = new VBox(10);
//         swineCard.setPadding(new Insets(10));
//         swineCard.setPrefWidth(220);
//         swineCard.setPrefHeight(220);
//         swineCard.setAlignment(Pos.TOP_LEFT);
//         swineCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         Image swineImage = new Image(getClass().getResource("/any.png").toExternalForm());

//         ImageView swineImageView = new ImageView(swineImage);
//         swineImageView.setFitWidth(198);
//         swineImageView.setFitHeight(105);

//         Label swineTitle = new Label("Swine Farming");
//         swineTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         swineTitle.setTextFill(Color.web("#eeeeee"));

//         Label swineDescription = new Label("Efficient livestock systems and smart feeding.");
//         swineDescription.setFont(Font.font("Arial", 12));
//         swineDescription.setTextFill(Color.web("#888888"));
//         swineDescription.setWrapText(true);

//         swineCard.getChildren().addAll(swineImageView, swineTitle, swineDescription);

//         firstCategoryHBox.getChildren().addAll(poultryCard, dairyCard, aquaticCard, swineCard);

//         // SECOND CATEGORY HBOX
//         HBox secondCategoryHBox = new HBox(18);
//         secondCategoryHBox.setAlignment(Pos.CENTER);

//         // SMALL RUMINANTS CARD
//         VBox ruminantsCard = new VBox(10);
//         ruminantsCard.setPadding(new Insets(10));
//         ruminantsCard.setPrefWidth(220);
//         ruminantsCard.setPrefHeight(220);
//         ruminantsCard.setAlignment(Pos.TOP_LEFT);
//         ruminantsCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         Image ruminantsImage = new Image(getClass().getResource("/any.png").toExternalForm());

//         ImageView ruminantsImageView = new ImageView(ruminantsImage);
//         ruminantsImageView.setFitWidth(198);
//         ruminantsImageView.setFitHeight(105);

//         Label ruminantsTitle = new Label("Small Ruminants");
//         ruminantsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         ruminantsTitle.setTextFill(Color.web("#eeeeee"));

//         Label ruminantsDescription = new Label("Modern goat and sheep farming solutions.");
//         ruminantsDescription.setFont(Font.font("Arial", 12));
//         ruminantsDescription.setTextFill(Color.web("#888888"));
//         ruminantsDescription.setWrapText(true);

//         ruminantsCard.getChildren().addAll(ruminantsImageView, ruminantsTitle, ruminantsDescription);

//         // PLANT NURSERY CARD
//         VBox nurseryCard = new VBox(10);
//         nurseryCard.setPadding(new Insets(10));
//         nurseryCard.setPrefWidth(220);
//         nurseryCard.setPrefHeight(220);
//         nurseryCard.setAlignment(Pos.TOP_LEFT);
//         nurseryCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         Image nurseryImage = new Image(getClass().getResource("/any.png").toExternalForm());

//         ImageView nurseryImageView = new ImageView(nurseryImage);
//         nurseryImageView.setFitWidth(198);
//         nurseryImageView.setFitHeight(105);

//         Label nurseryTitle = new Label("Plant Nursery");
//         nurseryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         nurseryTitle.setTextFill(Color.web("#eeeeee"));

//         Label nurseryDescription = new Label("Healthy seedlings and modern nursery management.");
//         nurseryDescription.setFont(Font.font("Arial", 12));
//         nurseryDescription.setTextFill(Color.web("#888888"));
//         nurseryDescription.setWrapText(true);

//         nurseryCard.getChildren().addAll(nurseryImageView, nurseryTitle, nurseryDescription);

//         // SMART MACHINERY CARD
//         VBox machineryCard = new VBox(10);
//         machineryCard.setPadding(new Insets(10));
//         machineryCard.setPrefWidth(220);
//         machineryCard.setPrefHeight(220);
//         machineryCard.setAlignment(Pos.TOP_LEFT);
//         machineryCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         Image machineryImage = new Image(getClass().getResource("/any.png").toExternalForm());

//         ImageView machineryImageView = new ImageView(machineryImage);
//         machineryImageView.setFitWidth(198);
//         machineryImageView.setFitHeight(105);

//         Label machineryTitle = new Label("Smart Machinery");
//         machineryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         machineryTitle.setTextFill(Color.web("#eeeeee"));

//         Label machineryDescription = new Label("Automated agricultural equipment and technology.");
//         machineryDescription.setFont(Font.font("Arial", 12));
//         machineryDescription.setTextFill(Color.web("#888888"));
//         machineryDescription.setWrapText(true);

//         machineryCard.getChildren().addAll(machineryImageView, machineryTitle, machineryDescription);

//         // PRECISION AGRICULTURE CARD
//         VBox precisionCard = new VBox(10);
//         precisionCard.setPadding(new Insets(10));
//         precisionCard.setPrefWidth(220);
//         precisionCard.setPrefHeight(220);
//         precisionCard.setAlignment(Pos.TOP_LEFT);
//         precisionCard.setStyle("-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;");

//         Image precisionImage = new Image(getClass().getResource("/any.png").toExternalForm());

//         ImageView precisionImageView = new ImageView(precisionImage);
//         precisionImageView.setFitWidth(198);
//         precisionImageView.setFitHeight(105);

//         Label precisionTitle = new Label("Precision Agriculture");
//         precisionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
//         precisionTitle.setTextFill(Color.web("#eeeeee"));

//         Label precisionDescription = new Label("Data-driven technology for better farm decisions.");
//         precisionDescription.setFont(Font.font("Arial", 12));
//         precisionDescription.setTextFill(Color.web("#888888"));
//         precisionDescription.setWrapText(true);

//         precisionCard.getChildren().addAll(precisionImageView, precisionTitle, precisionDescription);

//         secondCategoryHBox.getChildren().addAll(ruminantsCard, nurseryCard, machineryCard, precisionCard);

//         // ADD ALL CONTENT
//         mainVBox.getChildren().addAll(mainTitle, description, searchHBox, categoryFilterBox, categoryTitle, firstCategoryHBox, secondCategoryHBox);

//         // SCROLL PANE
//         ScrollPane scrollPane = new ScrollPane();
//         scrollPane.setContent(mainVBox);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//         scrollPane.setStyle("-fx-background-color: #080c0d;-fx-background: #080c0d;");

        
//         borderPane.setCenter(scrollPane);
     
//         // SCENE
//         Scene scene = new Scene(borderPane, 1100, 768);

//         return scene;
//     }
//     public void backtoexplorer(){
//         LoginPage.mainStage.setScene(explorepageScene);
//     }
// }
package com.pravartak.view.farmer;

import java.util.ArrayList;
import java.util.List;

import com.pravartak.dao.admindao.FirebaseCourseDAO;
import com.pravartak.dao.farmer.FarmerLearningDAO;
import com.pravartak.model.admin.Course;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * =========================================================
 * EXPLORER PAGE
 * =========================================================
 *
 * Displays all active/published courses created by Admin.
 *
 * Farmer can:
 *
 * 1. Search courses
 * 2. Filter courses by category
 * 3. Add course to My Learning
 * 4. See whether a course is already added
 *
 * Courses are loaded from:
 *
 * Firestore
 *      courses
 *
 * Farmer selected courses are stored through:
 *
 * FarmerLearningDAO
 *
 * =========================================================
 */
public class ExplorerPage {

    // =========================================================
    // SCENE
    // =========================================================

    private Scene explorepageScene;

    // =========================================================
    // DAOs
    // =========================================================

    private final FirebaseCourseDAO courseDAO;

    private final FarmerLearningDAO farmerLearningDAO;

    // =========================================================
    // COURSE CONTAINER
    // =========================================================

    private FlowPane courseContainer;

    // =========================================================
    // ALL COURSES
    // =========================================================

    private List<Course> allCourses =
            new ArrayList<>();

    // =========================================================
    // CURRENT CATEGORY
    // =========================================================

    private String selectedCategory =
            "All Categories";

    // =========================================================
    // SEARCH FIELD
    // =========================================================

    private TextField searchField;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ExplorerPage() {

        courseDAO =
                new FirebaseCourseDAO();

        farmerLearningDAO =
                new FarmerLearningDAO();
    }


    // =========================================================
    // GET EXPLORER PAGE
    // =========================================================

    public Scene getExplorerPage() {

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane borderPane =
                new BorderPane();

        borderPane.setStyle(
                "-fx-background-color:#080c0d;"
        );


        // =====================================================
        // NAVBAR
        // =====================================================

        borderPane.setTop(
                new NavBar().createNavbar(
                        "Explorer"
                )
        );


        // =====================================================
        // FOOTER
        // =====================================================

        borderPane.setBottom(
                new Footer().createFooter()
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainVBox =
                new VBox(25);

        mainVBox.setPadding(
                new Insets(
                        35,
                        45,
                        45,
                        45
                )
        );

        mainVBox.setAlignment(
                Pos.TOP_CENTER
        );

        mainVBox.setStyle(
                "-fx-background-color:#080c0d;"
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        Label mainTitle =
                new Label(
                        "Explore Farming\n"
                        + "Courses"
                );

        mainTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        42
                )
        );

        mainTitle.setTextFill(
                Color.web("#eeeeee")
        );

        mainTitle.setAlignment(
                Pos.CENTER
        );

        mainTitle.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label description =
                new Label(
                        "Discover practical farming courses, "
                        + "modern agricultural techniques, and "
                        + "knowledge to grow your farming business."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        description.setTextFill(
                Color.web("#aaaaaa")
        );

        description.setWrapText(true);

        description.setAlignment(
                Pos.CENTER
        );

        description.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        description.setMaxWidth(
                750
        );


        // =====================================================
        // SEARCH FIELD
        // =====================================================

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search courses, categories, difficulty..."
        );

        searchField.setPrefHeight(
                48
        );

        searchField.setPrefWidth(
                620
        );

        searchField.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        searchField.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#eeeeee;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#242b2c;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );


        // =====================================================
        // SEARCH BUTTON
        // =====================================================

        Button searchButton =
                new Button(
                        "Search"
                );

        searchButton.setPrefWidth(
                90
        );

        searchButton.setPrefHeight(
                42
        );

        searchButton.setStyle(
                "-fx-background-color:#68d34a;" +
                "-fx-text-fill:#080c0d;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // SEARCH BUTTON HOVER
        // =====================================================

        searchButton.setOnMouseEntered(
                e -> {

                    searchButton.setStyle(
                            "-fx-background-color:#82df68;" +
                            "-fx-text-fill:#080c0d;" +
                            "-fx-font-weight:bold;" +
                            "-fx-background-radius:6;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        searchButton.setOnMouseExited(
                e -> {

                    searchButton.setStyle(
                            "-fx-background-color:#68d34a;" +
                            "-fx-text-fill:#080c0d;" +
                            "-fx-font-weight:bold;" +
                            "-fx-background-radius:6;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        // =====================================================
        // SEARCH ACTION
        // =====================================================

        searchButton.setOnAction(
                e -> {

                    filterCourses();
                }
        );


        // =====================================================
        // SEARCH WHILE TYPING
        // =====================================================

        searchField.textProperty()
                .addListener(
                        (observable,
                         oldValue,
                         newValue) -> {

                            filterCourses();
                        }
                );


        // =====================================================
        // SEARCH HBOX
        // =====================================================

        HBox searchHBox =
                new HBox(8);

        searchHBox.setAlignment(
                Pos.CENTER
        );

        searchHBox.setMaxWidth(
                720
        );

        searchHBox.setPadding(
                new Insets(
                        5,
                        8,
                        5,
                        8
                )
        );

        searchHBox.setStyle(
                "-fx-background-color:#0d1213;" +
                "-fx-background-radius:10;" +
                "-fx-border-color:#242b2c;" +
                "-fx-border-radius:10;"
        );

        searchHBox.getChildren()
                .addAll(
                        searchField,
                        searchButton
                );


        // =====================================================
        // CATEGORY FILTERS
        // =====================================================

        Button allCategories =
                createCategoryButton(
                        "All Categories",
                        true
                );

        Button poultry =
                createCategoryButton(
                        "Poultry",
                        false
                );

        Button dairy =
                createCategoryButton(
                        "Dairy Cattle",
                        false
                );

        Button aquatic =
                createCategoryButton(
                        "Aquaculture",
                        false
                );

        Button swine =
                createCategoryButton(
                        "Swine",
                        false
                );

        Button ruminants =
                createCategoryButton(
                        "Small Ruminants",
                        false
                );

        Button nursery =
                createCategoryButton(
                        "Plant Nursery",
                        false
                );


        // =====================================================
        // CATEGORY ACTIONS
        // =====================================================

        allCategories.setOnAction(
                e -> selectCategory(
                        "All Categories",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );

        poultry.setOnAction(
                e -> selectCategory(
                        "Poultry",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );

        dairy.setOnAction(
                e -> selectCategory(
                        "Dairy Cattle",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );

        aquatic.setOnAction(
                e -> selectCategory(
                        "Aquaculture",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );

        swine.setOnAction(
                e -> selectCategory(
                        "Swine",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );

        ruminants.setOnAction(
                e -> selectCategory(
                        "Small Ruminants",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );

        nursery.setOnAction(
                e -> selectCategory(
                        "Plant Nursery",
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                )
        );


        // =====================================================
        // CATEGORY FILTER BOX
        // =====================================================

        HBox categoryFilterBox =
                new HBox(10);

        categoryFilterBox.setAlignment(
                Pos.CENTER
        );

        categoryFilterBox.setMaxWidth(
                1050
        );

        categoryFilterBox.getChildren()
                .addAll(
                        allCategories,
                        poultry,
                        dairy,
                        aquatic,
                        swine,
                        ruminants,
                        nursery
                );


        // =====================================================
        // COURSE SECTION TITLE
        // =====================================================

        Label courseSectionTitle =
                new Label(
                        "Available Courses"
                );

        courseSectionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        courseSectionTitle.setTextFill(
                Color.web("#eeeeee")
        );


        // =====================================================
        // COURSE CONTAINER
        // =====================================================

        courseContainer =
                new FlowPane();

        courseContainer.setHgap(
                20
        );

        courseContainer.setVgap(
                20
        );

        courseContainer.setAlignment(
                Pos.TOP_CENTER
        );

        courseContainer.setPrefWrapLength(
                1000
        );

        courseContainer.setPadding(
                new Insets(
                        5,
                        0,
                        25,
                        0
                )
        );


        // =====================================================
        // LOAD COURSES
        // =====================================================

        loadCourses();


        // =====================================================
        // ADD CONTENT
        // =====================================================

        mainVBox.getChildren()
                .addAll(
                        mainTitle,
                        description,
                        searchHBox,
                        categoryFilterBox,
                        courseSectionTitle,
                        courseContainer
                );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                mainVBox
        );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color:#080c0d;" +
                "-fx-background:#080c0d;"
        );


        // =====================================================
        // CENTER
        // =====================================================

        borderPane.setCenter(
                scrollPane
        );


        // =====================================================
        // SCENE
        // =====================================================

        explorepageScene =
                new Scene(
                        borderPane,
                        1200,
                        750
                );

        return explorepageScene;
    }


    // =========================================================
    // CREATE CATEGORY BUTTON
    // =========================================================

    private Button createCategoryButton(
            String text,
            boolean active) {

        Button button =
                new Button(text);

        if (active) {

            button.setStyle(
                    "-fx-background-color:#68d34a;" +
                    "-fx-text-fill:#080c0d;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:6;" +
                    "-fx-padding:8 14;" +
                    "-fx-cursor:hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color:#101516;" +
                    "-fx-text-fill:#aaaaaa;" +
                    "-fx-border-color:#242b2c;" +
                    "-fx-border-radius:6;" +
                    "-fx-background-radius:6;" +
                    "-fx-padding:8 14;" +
                    "-fx-cursor:hand;"
            );
        }

        return button;
    }


    // =========================================================
    // SELECT CATEGORY
    // =========================================================

    private void selectCategory(
            String category,
            Button all,
            Button poultry,
            Button dairy,
            Button aquatic,
            Button swine,
            Button ruminants,
            Button nursery) {

        selectedCategory =
                category;


        // =====================================================
        // RESET ALL BUTTONS
        // =====================================================

        Button[] buttons = {
                all,
                poultry,
                dairy,
                aquatic,
                swine,
                ruminants,
                nursery
        };


        for (Button button : buttons) {

            button.setStyle(
                    "-fx-background-color:#101516;" +
                    "-fx-text-fill:#aaaaaa;" +
                    "-fx-border-color:#242b2c;" +
                    "-fx-border-radius:6;" +
                    "-fx-background-radius:6;" +
                    "-fx-padding:8 14;" +
                    "-fx-cursor:hand;"
            );
        }


        // =====================================================
        // ACTIVE BUTTON
        // =====================================================

        for (Button button : buttons) {

            if (button.getText()
                    .equals(category)) {

                button.setStyle(
                        "-fx-background-color:#68d34a;" +
                        "-fx-text-fill:#080c0d;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:6;" +
                        "-fx-padding:8 14;" +
                        "-fx-cursor:hand;"
                );

                break;
            }
        }


        // =====================================================
        // FILTER
        // =====================================================

        filterCourses();
    }


    // =========================================================
    // LOAD COURSES
    // =========================================================

    private void loadCourses() {

        courseContainer
                .getChildren()
                .clear();


        // =====================================================
        // LOADING INDICATOR
        // =====================================================

        ProgressIndicator loading =
                new ProgressIndicator();

        loading.setPrefSize(
                45,
                45
        );

        courseContainer
                .getChildren()
                .add(
                        loading
                );


        try {

            // =================================================
            // GET PUBLISHED COURSES
            // =================================================

            allCourses =
                    courseDAO
                            .getPublishedCourses();


            courseContainer
                    .getChildren()
                    .clear();


            // =================================================
            // EMPTY
            // =================================================

            if (allCourses == null ||
                    allCourses.isEmpty()) {

                showEmptyMessage(
                        "No courses are available yet."
                );

                return;
            }


            // =================================================
            // DISPLAY
            // =================================================

            displayCourses(
                    allCourses
            );


        } catch (Exception e) {

            e.printStackTrace();


            courseContainer
                    .getChildren()
                    .clear();


            showEmptyMessage(
                    "Unable to load courses."
            );
        }
    }


    // =========================================================
    // FILTER COURSES
    // =========================================================

    private void filterCourses() {

        if (allCourses == null) {

            return;
        }


        String searchText =
                searchField == null
                        ? ""
                        : searchField
                                .getText()
                                .trim()
                                .toLowerCase();


        List<Course> filteredCourses =
                new ArrayList<>();


        for (Course course : allCourses) {

            if (course == null) {

                continue;
            }


            // =================================================
            // CATEGORY FILTER
            // =================================================

            boolean categoryMatches =
                    selectedCategory
                            .equals(
                                    "All Categories"
                            );


            if (!categoryMatches) {

                String courseCategory =
                        safeValue(
                                course.getCategory(),
                                ""
                        ).toLowerCase();


                categoryMatches =
                        courseCategory
                                .contains(
                                        selectedCategory
                                                .toLowerCase()
                                );
            }


            if (!categoryMatches) {

                continue;
            }


            // =================================================
            // SEARCH FILTER
            // =================================================

            if (!searchText.isEmpty()) {

                String title =
                        safeValue(
                                course.getTitle(),
                                ""
                        ).toLowerCase();

                String category =
                        safeValue(
                                course.getCategory(),
                                ""
                        ).toLowerCase();

                String difficulty =
                        safeValue(
                                course.getDifficulty(),
                                ""
                        ).toLowerCase();

                String language =
                        safeValue(
                                course.getLanguage(),
                                ""
                        ).toLowerCase();


                boolean matches =
                        title.contains(searchText)
                        || category.contains(searchText)
                        || difficulty.contains(searchText)
                        || language.contains(searchText);


                if (!matches) {

                    continue;
                }
            }


            filteredCourses
                    .add(course);
        }


        // =====================================================
        // DISPLAY FILTERED COURSES
        // =====================================================

        displayCourses(
                filteredCourses
        );
    }


    // =========================================================
    // DISPLAY COURSES
    // =========================================================

    private void displayCourses(
            List<Course> courses) {

        courseContainer
                .getChildren()
                .clear();


        if (courses == null ||
                courses.isEmpty()) {

            showEmptyMessage(
                    "No courses found."
            );

            return;
        }


        for (Course course : courses) {

            if (course == null) {

                continue;
            }


            VBox card =
                    createCourseCard(
                            course
                    );


            courseContainer
                    .getChildren()
                    .add(
                            card
                    );
        }
    }


    // =========================================================
    // EMPTY MESSAGE
    // =========================================================

    private void showEmptyMessage(
            String message) {

        VBox emptyBox =
                new VBox(10);

        emptyBox.setAlignment(
                Pos.CENTER
        );

        emptyBox.setPrefWidth(
                1050
        );

        emptyBox.setPadding(
                new Insets(50)
        );


        Label icon =
                new Label("📚");

        icon.setFont(
                Font.font(
                        "Arial",
                        35
                )
        );


        Label messageLabel =
                new Label(
                        message
                );

        messageLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        messageLabel.setTextFill(
                Color.web("#AFC4B2")
        );


        emptyBox
                .getChildren()
                .addAll(
                        icon,
                        messageLabel
                );


        courseContainer
                .getChildren()
                .add(
                        emptyBox
                );
    }


    // =========================================================
    // CREATE COURSE CARD
    // =========================================================

    private VBox createCourseCard(
            Course course) {

        VBox card =
                new VBox(10);

        card.setPrefWidth(
                285
        );

        card.setPrefHeight(
                350
        );

        card.setPadding(
                new Insets(12)
        );


        // =====================================================
        // NORMAL CARD STYLE
        // =====================================================

        String normalCardStyle =
                "-fx-background-color:#101516;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#242b2c;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:1;";


        String hoverCardStyle =
                "-fx-background-color:#17221a;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#68d34a;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:1;";


        card.setStyle(
                normalCardStyle
        );


        // =====================================================
        // IMAGE
        // =====================================================

        StackPane imageContainer =
                createCourseImage(
                        course.getThumbnailUrl()
                );


        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        safeValue(
                                course.getTitle(),
                                "Untitled Course"
                        )
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        title.setTextFill(
                Color.WHITE
        );

        title.setWrapText(
                true
        );

        title.setMaxWidth(
                255
        );


        // =====================================================
        // CATEGORY
        // =====================================================

        Label category =
                new Label(
                        safeValue(
                                course.getCategory(),
                                "General"
                        )
                );

        category.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        category.setTextFill(
                Color.web("#68d34a")
        );


        // =====================================================
        // COURSE DESCRIPTION
        //
        // Your current Course model doesn't have a
        // description field, so we use a general message.
        // =====================================================

        Label description =
                new Label(
                        "Learn practical techniques and "
                        + "modern methods for better farming."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        description.setTextFill(
                Color.web("#888888")
        );

        description.setWrapText(
                true
        );

        description.setMaxWidth(
                255
        );


        // =====================================================
        // COURSE INFORMATION
        // =====================================================

        HBox information =
                createCourseInformation(
                        course
                );


        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );


        // =====================================================
        // ADD BUTTON
        // =====================================================

        Button addButton =
                new Button(
                        "+ Add to My Learning"
                );

        addButton.setPrefWidth(
                261
        );

        addButton.setPrefHeight(
                42
        );

        addButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
        ));


        // =====================================================
        // CHECK CURRENT FARMER
        // =====================================================

        int farmerId =
                LoginPage
                        .getLoggedInFarmerId();


        boolean alreadyAdded =
                farmerLearningDAO
                        .isCourseAdded(
                                farmerId,
                                course.getCourseId()
                        );


        if (alreadyAdded) {

            setAddedButtonStyle(
                    addButton
            );

        } else {

            setAddButtonStyle(
                    addButton
            );
        }


        // =====================================================
        // ADD BUTTON ACTION
        // =====================================================

        addButton.setOnAction(
                e -> {

                    int currentFarmerId =
                            LoginPage
                                    .getLoggedInFarmerId();


                    // -----------------------------------------
                    // CHECK FARMER ID
                    // -----------------------------------------

                    if (currentFarmerId <= 0) {

                        System.out.println(
                                "ERROR: Farmer ID is missing."
                        );

                        return;
                    }


                    // -----------------------------------------
                    // ADD COURSE
                    // -----------------------------------------

                    boolean added =
                            farmerLearningDAO
                                    .addCourse(
                                            currentFarmerId,
                                            course.getCourseId()
                                    );


                    // -----------------------------------------
                    // SUCCESS
                    // -----------------------------------------

                    if (added) {

                        setAddedButtonStyle(
                                addButton
                        );

                        addButton.setText(
                                "✓ Added to My Learning"
                        );

                        addButton.setDisable(
                                true
                        );

                        System.out.println(
                                "Course added successfully: "
                                + course.getTitle()
                        );
                    }
                }
        );


        // =====================================================
        // CARD HOVER
        // =====================================================

        card.setOnMouseEntered(
                e -> {

                    card.setStyle(
                            hoverCardStyle
                    );
                }
        );


        card.setOnMouseExited(
                e -> {

                    card.setStyle(
                            normalCardStyle
                    );
                }
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        card.getChildren()
                .addAll(
                        imageContainer,
                        title,
                        category,
                        description,
                        information,
                        spacer,
                        addButton
                );


        return card;
    }


    // =========================================================
    // COURSE INFORMATION
    // =========================================================

    private HBox createCourseInformation(
            Course course) {

        HBox information =
                new HBox(8);

        information.setAlignment(
                Pos.CENTER_LEFT
        );


        // =====================================================
        // DIFFICULTY
        // =====================================================

        Label difficulty =
                new Label(
                        "● "
                        + safeValue(
                                course.getDifficulty(),
                                "Beginner"
                        )
                );

        difficulty.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        difficulty.setTextFill(
                Color.web("#AFC4B2")
        );


        // =====================================================
        // LANGUAGE
        // =====================================================

        Label language =
                new Label(
                        "• "
                        + safeValue(
                                course.getLanguage(),
                                "English"
                        )
                );

        language.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        language.setTextFill(
                Color.web("#AFC4B2")
        );


        information
                .getChildren()
                .addAll(
                        difficulty,
                        language
                );


        return information;
    }


    // =========================================================
    // COURSE IMAGE
    // =========================================================

    private StackPane createCourseImage(
            String imageUrl) {

        StackPane container =
                new StackPane();


        container.setPrefWidth(
                261
        );

        container.setPrefHeight(
                135
        );

        container.setMaxWidth(
                261
        );

        container.setMaxHeight(
                135
        );


        // =====================================================
        // CLIP
        // =====================================================

        Rectangle clip =
                new Rectangle(
                        261,
                        135
                );

        clip.setArcWidth(
                18
        );

        clip.setArcHeight(
                18
        );

        container.setClip(
                clip
        );


        // =====================================================
        // BACKGROUND
        // =====================================================

        container.setStyle(
                "-fx-background-color:#193522;"
        );


        // =====================================================
        // IMAGE VIEW
        // =====================================================

        ImageView imageView =
                new ImageView();


        imageView.setFitWidth(
                261
        );

        imageView.setFitHeight(
                135
        );

        imageView.setPreserveRatio(
                false
        );


        // =====================================================
        // LOAD IMAGE
        // =====================================================

        if (imageUrl != null &&
                !imageUrl.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                261,
                                135,
                                false,
                                true,
                                true
                        );

                imageView.setImage(
                        image
                );

            } catch (Exception e) {

                addExplorerPlaceholder(
                        container
                );
            }

        } else {

            addExplorerPlaceholder(
                    container
            );
        }


        // =====================================================
        // ADD IMAGE VIEW
        // =====================================================

        container
                .getChildren()
                .add(
                        imageView
                );


        return container;
    }


    // =========================================================
    // IMAGE PLACEHOLDER
    // =========================================================

    private void addExplorerPlaceholder(
            StackPane container) {

        Label placeholder =
                new Label(
                        "🌱"
                );

        placeholder.setFont(
                Font.font(
                        "Arial",
                        40
                )
        );

        placeholder.setTextFill(
                Color.web("#68d34a")
        );


        container
                .getChildren()
                .add(
                        placeholder
                );
    }


    // =========================================================
    // ADD BUTTON STYLE
    // =========================================================

    private void setAddButtonStyle(
            Button button) {

        button.setText(
                "+ Add to My Learning"
        );

        button.setDisable(
                false
        );

        button.setTextFill(
                Color.web("#080c0d")
        );

        button.setStyle(
                "-fx-background-color:#68d34a;" +
                "-fx-text-fill:#080c0d;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:9;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // HOVER
        // =====================================================

        button.setOnMouseEntered(
                e -> {

                    if (!button.isDisabled()) {

                        button.setStyle(
                                "-fx-background-color:#82df68;" +
                                "-fx-text-fill:#080c0d;" +
                                "-fx-font-weight:bold;" +
                                "-fx-background-radius:9;" +
                                "-fx-cursor:hand;"
                        );
                    }
                }
        );


        button.setOnMouseExited(
                e -> {

                    if (!button.isDisabled()) {

                        button.setStyle(
                                "-fx-background-color:#68d34a;" +
                                "-fx-text-fill:#080c0d;" +
                                "-fx-font-weight:bold;" +
                                "-fx-background-radius:9;" +
                                "-fx-cursor:hand;"
                        );
                    }
                }
        );
    }


    // =========================================================
    // ADDED BUTTON STYLE
    // =========================================================

    private void setAddedButtonStyle(
            Button button) {

        button.setText(
                "✓ Added to My Learning"
        );

        button.setDisable(
                true
        );

        button.setStyle(
                "-fx-background-color:#193522;" +
                "-fx-text-fill:#68d34a;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:#68d34a;" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;"
        );
    }


    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safeValue(
            String value,
            String defaultValue) {

        if (value == null ||
                value.trim().isEmpty()) {

            return defaultValue;
        }

        return value.trim();
    }


    // =========================================================
    // BACK TO EXPLORER
    // =========================================================

    public void backtoexplorer() {

        LoginPage.mainStage
                .setScene(
                        explorepageScene
                );
    }
}