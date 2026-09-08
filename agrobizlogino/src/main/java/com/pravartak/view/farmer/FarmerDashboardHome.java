// package com.pravartak.view.farmer;

// import java.time.Duration;
// import java.time.Instant;
// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.List;

// import com.google.cloud.Timestamp;
// import com.pravartak.controller.farmercontoller.ProductController;
// import com.pravartak.dao.farmer.FarmerLearningDAO;
// import com.pravartak.model.admin.Course;
// import com.pravartak.model.farmer_model.Product;
// import com.pravartak.view.login.LoginPage;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Cursor;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.Border;
// import javafx.scene.layout.BorderStroke;
// import javafx.scene.layout.BorderStrokeStyle;
// import javafx.scene.layout.BorderWidths;
// import javafx.scene.layout.ColumnConstraints;
// import javafx.scene.layout.CornerRadii;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;



// public class FarmerDashboardHome {

//     // =========================================================
//     // FARMER DATA
//     // =========================================================

//     private final int farmerId;

//     private final ProductController productController;

//     private final FarmerLearningDAO farmerLearningDAO;


//     // =========================================================
//     // COLORS
//     // =========================================================

//   private static final Color BACKGROUND =
//         Color.web("#0E1711");

//     private static final Color DARK_GREEN =
//             Color.web("#0B1B12");

//     private static final Color CARD_BACKGROUND =
//             Color.web("#1d3722");

//     private static final Color GREEN =
//             Color.web("#68D34A");

//     private static final Color LIGHT_GREEN =
//             Color.web("#16251A");

//     private static final Color TEXT =
//             Color.web("#F2F7F3");

//     private static final Color GREY =
//             Color.web("#cad3cc");

//     private static final Color BORDER =
//             Color.web("#263A2B");


//     // =========================================================
//     // CONSTRUCTOR
//     // =========================================================

//     public FarmerDashboardHome(
//             int farmerId) {

//         this.farmerId =
//                 farmerId;

//         this.productController =
//                 new ProductController();

//         this.farmerLearningDAO =
//                 new FarmerLearningDAO();
//     }


//     // =========================================================
//     // MAIN DASHBOARD
//     // =========================================================
// // Very light green - recommended
//     public VBox getDashboardHome() {


//         VBox content =
//                 new VBox(22);

//         content.setPadding(
//                 new Insets(
//                         30,
//                         35,
//                         35,
//                         35
//                 )
//         );

//         content.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 BACKGROUND,
//                                 CornerRadii.EMPTY,
//                                 Insets.EMPTY
//                         )
//                 )
//         );
//         content.setBorder(null);


//         // =====================================================
//         // REAL COUNTS
//         // =====================================================

//         int productsCount =
//                 getProductsCount();

//         int learningCount =
//                 getLearningCount();

//         int schemesCount =
//                 SavedSchemesManager.getCount();


//         // =====================================================
//         // WELCOME
//         // =====================================================

//         content.getChildren().add(
//                 createWelcomeBanner()
//         );


//         // =====================================================
//         // STAT CARDS
//         // =====================================================

//         GridPane stats =
//                 new GridPane();

//         stats.setHgap(18);
//         stats.setVgap(18);


//         stats.add(
//                 createStatCard(
//                         "📦",
//                         "My Products",
//                         String.valueOf(
//                                 productsCount
//                         ),
//                         "Products uploaded"
//                 ),
//                 0,
//                 0
//         );


//         stats.add(
//                 createStatCard(
//                         "📚",
//                         "My Learning",
//                         String.valueOf(
//                                 learningCount
//                         ),
//                         "Courses in learning"
//                 ),
//                 1,
//                 0
//         );


//         stats.add(
//                 createStatCard(
//                         "🌱",
//                         "Saved Schemes",
//                         String.valueOf(
//                                 schemesCount
//                         ),
//                         "Government schemes saved"
//                 ),
//                 2,
//                 0
//         );


//         for (int i = 0; i < 3; i++) {

//             ColumnConstraints column =
//                     new ColumnConstraints();

//             column.setPercentWidth(
//                     33.33
//             );

//             stats.getColumnConstraints()
//                     .add(column);
//         }


//         content.getChildren().add(
//                 stats
//         );


//         // =====================================================
//         // AGROBIZ CARD
//         // =====================================================

//         content.getChildren().add(
//                 createAgroBizForFarmersCard()
//         );


//         // =====================================================
//         // QUICK ACTIONS
//         // =====================================================

//         content.getChildren().add(
//                 createQuickActions()
//         );


//         return content;
//     }


//     // =========================================================
//     // WELCOME BANNER
//     // =========================================================

//     private VBox createWelcomeBanner() {

//         VBox card =
//                 new VBox(8);

//         card.setPadding(
//                 new Insets(
//                         28,
//                         32,
//                         28,
//                         32
//                 )
//         );

//         card.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         card.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 DARK_GREEN,
//                                 new CornerRadii(17),
//                                 Insets.EMPTY
//                         )
//                 )
//         );


//         Label title =
//                 new Label(
//                         "Welcome back, Farmer! 🌱"
//                 );

//         title.setTextFill(
//                 Color.WHITE
//         );

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         28
//                 )
//         );


//         Label description =
//                 new Label(
//                         "Manage your products, continue learning, "
//                         + "and discover useful government schemes."
//                 );

//         description.setTextFill(
//                 Color.rgb(
//                         210,
//                         230,
//                         215
//                 )
//         );

//         description.setFont(
//                 Font.font(
//                         "Arial",
//                         14
//                 ));

//         description.setWrapText(
//                 true
//         );


//         card.getChildren().addAll(
//                 title,
//                 description
//         );


//         return card;
//     }


//     // =========================================================
//     // STAT CARD
//     // =========================================================

//     private VBox createStatCard(
//             String icon,
//             String title,
//             String value,
//             String description) {

//         VBox card =
//                 new VBox(7);

//         card.setPadding(
//                 new Insets(20)
//         );

//         card.setMinHeight(
//                 145
//         );

//         card.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         card.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 CARD_BACKGROUND,
//                                 new CornerRadii(14),
//                                 Insets.EMPTY
//                         )
//                 )
//         );

//         card.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 BORDER,
//                                 BorderStrokeStyle.SOLID,
//                                 new CornerRadii(14),
//                                 new BorderWidths(1)
//                         )
//                 )
//         );


//         Label iconLabel =
//                 new Label(
//                         icon
//                 );

//         iconLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         25
//                 )
//         );


//         Label titleLabel =
//                 new Label(
//                         title
//                 );

//         titleLabel.setTextFill(
//                 GREY
//         );

//         titleLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         12
//                 )
//         );


//         Label valueLabel =
//                 new Label(
//                         value
//                 );

//         valueLabel.setTextFill(
//                 GREEN
//         );

//         valueLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         27
//                 )
//         );


//         Label descriptionLabel =
//                 new Label(
//                         description
//                 );

//         descriptionLabel.setTextFill(
//                 GREY
//         );

//         descriptionLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         10
//                 ));

//         descriptionLabel.setWrapText(
//                 true
//         );


//         card.getChildren().addAll(
//                 iconLabel,
//                 titleLabel,
//                 valueLabel,
//                 descriptionLabel
//         );


//         return card;
//     }


//     // =========================================================
//     // AGROBIZ FOR FARMERS
//     // =========================================================

//     private VBox createAgroBizForFarmersCard() {

//         VBox card =
//                 new VBox(18);

//         card.setPadding(
//                 new Insets(25)
//         );

//         card.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         card.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 CARD_BACKGROUND,
//                                 new CornerRadii(15),
//                                 Insets.EMPTY
//                         )
//                 )
//         );

//         card.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 BORDER,
//                                 BorderStrokeStyle.SOLID,
//                                 new CornerRadii(15),
//                                 new BorderWidths(1)
//                         )
//                 )
//         );


//         // =====================================================
//         // HEADER
//         // =====================================================

//         VBox titleBox =
//                 new VBox(4);


//         Label title =
//                 new Label(
//                         "🌱  AgroBiz for Farmers"
//                 );

//         title.setTextFill(
//                 TEXT
//         );

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         21
//                 )
//         );


//         Label description =
//                 new Label(
//                         "Everything you need to manage your "
//                         + "AgroBiz activities in one place."
//                 );

//         description.setTextFill(
//                 GREY
//         );

//         description.setFont(
//                 Font.font(
//                         "Arial",
//                         11
//                 ));

//         description.setWrapText(
//                 true
//         );


//         titleBox.getChildren().addAll(
//                 title,
//                 description
//         );


//         card.getChildren().add(
//                 titleBox
//         );


//         // =====================================================
//         // RECENT ACTIVITY TITLE
//         // =====================================================

//         Label activityTitle =
//                 new Label(
//                         "Recent Activity"
//                 );

//         activityTitle.setTextFill(
//                 TEXT
//         );

//         activityTitle.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         15
//                 ));


//         card.getChildren().add(
//                 activityTitle
//         );


//         // =====================================================
//         // RECENT ACTIVITY
//         // =====================================================

//         card.getChildren().add(
//                 createRecentActivity()
//         );




//         return card;
//     }


//     // =========================================================
//     // REAL RECENT ACTIVITY
//     // =========================================================

//     private VBox createRecentActivity() {

//         VBox activityBox =
//                 new VBox(10);


//         List<ActivityItem> activities =
//                 new ArrayList<>();


//         // =====================================================
//         // 1. PRODUCT ACTIVITIES
//         // =====================================================

//         try {

//             List<Product> products =
//                     productController
//                             .getFarmerProducts(
//                                     farmerId
//                             );


//             if (products != null) {

//                 for (Product product :
//                         products) {

//                     if (product == null) {
//                         continue;
//                     }


//                     Timestamp timestamp =
//                             product.getCreatedAt();


//                     // Only products with a real timestamp
//                     if (timestamp == null) {
//                         continue;
//                     }


//                     String productName =
//                             product.getProductName();


//                     if (productName == null ||
//                             productName.trim().isEmpty()) {

//                         productName =
//                                 "Product";
//                     }


//                     activities.add(
//                             new ActivityItem(
//                                     "📦",
//                                     productName,
//                                     "Product uploaded",
//                                     timestamp
//                                             .toDate()
//                                             .toInstant()
//                             )
//                     );
//                 }
//             }

//         } catch (Exception e) {

//             // Continue with other activities
//         }


//         // =====================================================
//         // 2. LEARNING ACTIVITIES
//         // =====================================================

//         try {

//             List<FarmerLearningDAO.LearningActivity>
//                     learningActivities =
//                     farmerLearningDAO
//                             .getMyLearningActivities(
//                                     farmerId
//                             );


//             if (learningActivities != null) {

//                 for (
//                         FarmerLearningDAO.LearningActivity
//                         learningActivity :
//                         learningActivities) {

//                     if (learningActivity == null ||
//                             learningActivity.getCourse() == null ||
//                             learningActivity.getAddedAt() == null) {

//                         continue;
//                     }


//                     Course course =
//                             learningActivity
//                                     .getCourse();


//                     String courseTitle =
//                             course.getTitle();


//                     if (courseTitle == null ||
//                             courseTitle.trim().isEmpty()) {

//                         courseTitle =
//                                 "Course";
//                     }


//                     activities.add(
//                             new ActivityItem(
//                                     "📚",
//                                     courseTitle,
//                                     "Added to My Learning",
//                                     learningActivity
//                                             .getAddedAt()
//                                             .toDate()
//                                             .toInstant()
//                             )
//                     );
//                 }
//             }

//         } catch (Exception e) {

//             // Continue with scheme activities
//         }


//         // =====================================================
//         // 3. SAVED SCHEME ACTIVITIES
//         // =====================================================

//         try {

//             List<SavedSchemesManager.SavedScheme>
//                     savedSchemes =
//                     SavedSchemesManager
//                             .getSavedSchemes();


//             if (savedSchemes != null) {

//                 for (
//                         SavedSchemesManager.SavedScheme
//                         savedScheme :
//                         savedSchemes) {

//                     if (savedScheme == null ||
//                             savedScheme.getScheme() == null ||
//                             savedScheme.getSavedAt() == null) {

//                         continue;
//                     }


//                     String schemeName =
//                             savedScheme
//                                     .getScheme()
//                                     .getSchemeName();


//                     if (schemeName == null ||
//                             schemeName.trim().isEmpty()) {

//                         schemeName =
//                                 "Government Scheme";
//                     }


//                     activities.add(
//                             new ActivityItem(
//                                     "🌱",
//                                     schemeName,
//                                     "Scheme saved",
//                                     savedScheme
//                                             .getSavedAt()
//                             )
//                     );
//                 }
//             }

//         } catch (Exception e) {

//             // Continue with available activities
//         }


//         // =====================================================
//         // SORT NEWEST FIRST
//         // =====================================================

//         activities.sort(
//                 Comparator.comparing(
//                         ActivityItem::getTime
//                 ).reversed()
//         );


//         // =====================================================
//         // EMPTY ACTIVITY
//         // =====================================================

//         if (activities.isEmpty()) {

//             VBox emptyBox =
//                     new VBox(5);

//             emptyBox.setPadding(
//                     new Insets(
//                             8,
//                             0,
//                             8,
//                             0
//                     )
//             );


//             Label emptyTitle =
//                     new Label(
//                             "No recent activity yet."
//                     );

//             emptyTitle.setTextFill(
//                     TEXT
//             );

//             emptyTitle.setFont(
//                     Font.font(
//                             "Arial",
//                             FontWeight.BOLD,
//                             12
//                     ));


//             Label emptyDescription =
//                     new Label(
//                             "Upload a product, add a course to "
//                             + "My Learning, or save a scheme."
//                     );

//             emptyDescription.setTextFill(
//                     GREY
//             );

//             emptyDescription.setFont(
//                     Font.font(
//                             "Arial",
//                             11
//                     ));

//             emptyDescription.setWrapText(
//                     true
//             );


//             emptyBox.getChildren().addAll(
//                     emptyTitle,
//                     emptyDescription
//             );


//             activityBox.getChildren().add(
//                     emptyBox
//             );


//             return activityBox;
//         }


//         // =====================================================
//         // SHOW ONLY LATEST 5
//         // =====================================================

//         int limit =
//                 Math.min(
//                         activities.size(),
//                         5
//                 );


//         for (int i = 0; i < limit; i++) {

//             ActivityItem item =
//                     activities.get(i);


//             activityBox.getChildren().add(
//                     createActivityRow(
//                             item
//                     )
//             );
//         }


//         return activityBox;
//     }


//     // =========================================================
//     // ACTIVITY ROW
//     // =========================================================

//     private HBox createActivityRow(
//             ActivityItem item) {

//         HBox row =
//                 new HBox(12);

//         row.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         row.setPadding(
//                 new Insets(
//                         11,
//                         12,
//                         11,
//                         12
//                 )
//         );

//         row.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 LIGHT_GREEN,
//                                 new CornerRadii(9),
//                                 Insets.EMPTY
//                         )
//                 )
//         );


//         Label icon =
//                 new Label(
//                         item.getIcon()
//                 );

//         icon.setPrefWidth(
//                 30
//         );

//         icon.setAlignment(
//                 Pos.CENTER
//         );

//         icon.setFont(
//                 Font.font(
//                         "Arial",
//                         18
//                 ));


//         VBox details =
//                 new VBox(3);

//         HBox.setHgrow(
//                 details,
//                 Priority.ALWAYS
//         );


//         Label name =
//                 new Label(
//                         item.getName()
//                 );

//         name.setTextFill(
//                 TEXT
//         );

//         name.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         12
//                 ));

//         name.setWrapText(
//                 true
//         );


//         Label action =
//                 new Label(
//                         item.getAction()
//                         + "  •  "
//                         + getTimeAgo(
//                                 item.getTime()
//                         )
//                 );

//         action.setTextFill(
//                 GREY
//         );

//         action.setFont(
//                 Font.font(
//                         "Arial",
//                         10
//                 ));


//         details.getChildren().addAll(
//                 name,
//                 action
//         );


//         row.getChildren().addAll(
//                 icon,
//                 details
//         );


//         return row;
//     }


//     // =========================================================
//     // QUICK ACTIONS
//     // =========================================================

//     private VBox createQuickActions() {

//         VBox section =
//                 new VBox(14);


//         Label title =
//                 new Label(
//                         "Quick Actions"
//                 );

//         title.setTextFill(
//                 TEXT
//         );

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         17
//                 ));


//         HBox actions =
//                 new HBox(14);


//         Button schemes =
//                 createQuickAction(
//                         "🌱",
//                         "Government Schemes",
//                         "Explore available schemes"
//                 );


//         schemes.setOnAction(
//                 event -> {

//                     LoginPage.mainStage.setScene(
//                             SchemesPage.getSchemesPage()
//                     );
//                 }
//         );


//         Button learning =
//                 createQuickAction(
//                         "📚",
//                         "Learning",
//                         "Continue your courses"
//                 );


//         learning.setOnAction(
//                 event -> {

//                     LearningPage page =
//                             new LearningPage();

//                     LoginPage.mainStage.setScene(
//                             page.get_learning_pageScene()
//                     );
//                 }
//         );


//         Button products =
//                 createQuickAction(
//                         "📦",
//                         "My Products",
//                         "Manage your products"
//                 );


//         products.setOnAction(
//                 event -> {

//                     showProductMessage();
//                 }
//         );


//         actions.getChildren().addAll(
//                 schemes,
//                 learning,
//                 products
//         );


//         HBox.setHgrow(
//                 schemes,
//                 Priority.ALWAYS
//         );

//         HBox.setHgrow(
//                 learning,
//                 Priority.ALWAYS
//         );

//         HBox.setHgrow(
//                 products,
//                 Priority.ALWAYS
//         );


//         section.getChildren().addAll(
//                 title,
//                 actions
//         );


//         return section;
//     }


//     // =========================================================
//     // QUICK ACTION CARD
//     // =========================================================

//     private Button createQuickAction(
//             String icon,
//             String title,
//             String description) {

//         Button button =
//                 new Button();


//         button.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         button.setPrefHeight(
//                 82
//         );

//         button.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         button.setPadding(
//                 new Insets(12)
//         );


//         button.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 CARD_BACKGROUND,
//                                 new CornerRadii(10),
//                                 Insets.EMPTY
//                         )
//                 )
//         );


//         button.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 BORDER,
//                                 BorderStrokeStyle.SOLID,
//                                 new CornerRadii(10),
//                                 new BorderWidths(1)
//                         )
//                 )
//         );


//         VBox content =
//                 new VBox(3);


//         Label titleLabel =
//                 new Label(
//                         icon + "  " + title
//                 );

//         titleLabel.setTextFill(
//                 TEXT
//         );

//         titleLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         12
//                 ));


//         Label descriptionLabel =
//                 new Label(
//                         description
//                 );

//         descriptionLabel.setTextFill(
//                 GREY
//         );

//         descriptionLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         10
//                 ));

//         descriptionLabel.setWrapText(
//                 true
//         );


//         content.getChildren().addAll(
//                 titleLabel,
//                 descriptionLabel
//         );


//         button.setGraphic(
//                 content
//         );

//         button.setCursor(
//                 Cursor.HAND
//         );


//         button.setOnMouseEntered(
//                 event -> {

//                     button.setStyle(
//                             "-fx-background-color:#16251A;"
//                     );
//                 }
//         );


//         button.setOnMouseExited(
//                 event -> {

//                     button.setStyle(
//                             "-fx-background-color:#101718;"
//                     );
//                 }
//         );


//         return button;
//     }


//     // =========================================================
//     // ACTION BUTTON
//     // =========================================================

//     private Button createActionButton(
//             String text) {

//         Button button =
//                 new Button(
//                         text
//                 );


//         button.setPrefHeight(
//                 36
//         );

//         button.setPadding(
//                 new Insets(
//                         0,
//                         16,
//                         0,
//                         16
//                 )
//         );


//         button.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         11
//                 ));


//         button.setTextFill(
//                 Color.web("#06100B")
//         );


//         button.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 GREEN,
//                                 new CornerRadii(7),
//                                 Insets.EMPTY
//                         )
//                 )
//         );


//         button.setCursor(
//                 Cursor.HAND
//         );


//         return button;
//     }


//     // =========================================================
//     // GET PRODUCTS COUNT
//     // =========================================================

//     private int getProductsCount() {

//         try {

//             List<Product> products =
//                     productController
//                             .getFarmerProducts(
//                                     farmerId
//                             );

//             if (products == null) {
//                 return 0;
//             }

//             return products.size();

//         } catch (Exception e) {

//             return 0;
//         }
//     }


//     // =========================================================
//     // GET LEARNING COUNT
//     // =========================================================

//     private int getLearningCount() {

//         try {

//             List<Course> courses =
//                     farmerLearningDAO
//                             .getMyLearningCourses(
//                                     farmerId
//                             );

//             if (courses == null) {
//                 return 0;
//             }

//             return courses.size();

//         } catch (Exception e) {

//             return 0;
//         }
//     }


//     // =========================================================
//     // TIME AGO
//     // =========================================================

//     private String getTimeAgo(
//             Instant activityTime) {

//         if (activityTime == null) {

//             return "";
//         }


//         Duration duration =
//                 Duration.between(
//                         activityTime,
//                         Instant.now()
//                 );


//         long seconds =
//                 duration.getSeconds();


//         if (seconds < 60) {

//             return "Just now";
//         }


//         long minutes =
//                 seconds / 60;


//         if (minutes < 60) {

//             return minutes == 1
//                     ? "1 minute ago"
//                     : minutes + " minutes ago";
//         }


//         long hours =
//                 minutes / 60;


//         if (hours < 24) {

//             return hours == 1
//                     ? "1 hour ago"
//                     : hours + " hours ago";
//         }


//         long days =
//                 hours / 24;


//         if (days < 7) {

//             return days == 1
//                     ? "1 day ago"
//                     : days + " days ago";
//         }


//         long weeks =
//                 days / 7;


//         if (weeks < 4) {

//             return weeks == 1
//                     ? "1 week ago"
//                     : weeks + " weeks ago";
//         }


//         long months =
//                 days / 30;


//         if (months < 12) {

//             return months == 1
//                     ? "1 month ago"
//                     : months + " months ago";
//         }


//         long years =
//                 days / 365;


//         return years == 1
//                 ? "1 year ago"
//                 : years + " years ago";
//     }


//     // =========================================================
//     // PRODUCT INFORMATION
//     // =========================================================

//     private void showProductMessage() {

//         Alert alert =
//                 new Alert(
//                         Alert.AlertType.INFORMATION
//                 );


//         alert.setTitle(
//                 "My Products"
//         );


//         alert.setHeaderText(
//                 null
//         );


//         alert.setContentText(
//                 "You currently have "
//                 + getProductsCount()
//                 + " product(s) uploaded."
//         );


//         alert.showAndWait();
//     }


//     // =========================================================
//     // ACTIVITY ITEM
//     // =========================================================

//     private static class ActivityItem {

//         private final String icon;

//         private final String name;

//         private final String action;

//         private final Instant time;


//         public ActivityItem(
//                 String icon,
//                 String name,
//                 String action,
//                 Instant time) {

//             this.icon =
//                     icon;

//             this.name =
//                     name;

//             this.action =
//                     action;

//             this.time =
//                     time;
//         }


//         public String getIcon() {

//             return icon;
//         }


//         public String getName() {

//             return name;
//         }


//         public String getAction() {

//             return action;
//         }


//         public Instant getTime() {

//             return time;
//         }
//     }
// }
package com.pravartak.view.farmer;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.cloud.Timestamp;
import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.dao.farmer.FarmerLearningDAO;
import com.pravartak.model.admin.Course;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.login.LoginPage;
import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.controller.buyercontroller.ReviewController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.model.buyer_model.Review;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FarmerDashboardHome {

    // =========================================================
    // FARMER DATA
    // =========================================================

    private final int farmerId;

    private final ProductController productController;

    private final FarmerLearningDAO farmerLearningDAO;

    private final OrderController orderController;

private final ReviewController reviewController;


    // =========================================================
    // AGROBIZ FARMER THEME
    // =========================================================

    private static final Color BACKGROUND =
            Color.web("#050B0A");

    private static final Color DARK_GREEN =
            Color.web("#122a1d");

    private static final Color CARD_BACKGROUND =
            Color.web("#0D1213");

    private static final Color SECONDARY_CARD =
            Color.web("#050B0A");

    private static final Color GREEN =
            Color.web("#7ED957");

    private static final Color LIGHT_GREEN =
            Color.web("#1B3B25");

    private static final Color TEXT =
            Color.web("#F3F8F3");

    private static final Color GREY =
            Color.web("#A9B8AC");

    private static final Color MUTED =
            Color.web("#788A7D");

    private static final Color BORDER =
            Color.web("#294734");


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

//     public FarmerDashboardHome(
//             int farmerId) {

//         this.farmerId =
//                 farmerId;

//         this.productController =
//                 new ProductController();

//         this.farmerLearningDAO =
//                 new FarmerLearningDAO();
//     }
// public FarmerDashboardHome(
//         int farmerId) {

//     this.farmerId =
//             farmerId;

//     this.productController =
//             new ProductController();

//     this.farmerLearningDAO =
//             new FarmerLearningDAO();

//     this.orderController =
//             new OrderController();

//     this.reviewController =
//             new ReviewController();
//}
private final String farmerName;

public FarmerDashboardHome(
        int farmerId,
        String farmerName) {

    this.farmerId = farmerId;

    this.farmerName =
            (farmerName == null ||
             farmerName.trim().isEmpty())
                    ? "Farmer"
                    : farmerName.trim();

    this.productController =
            new ProductController();

    this.farmerLearningDAO =
            new FarmerLearningDAO();

    this.orderController =
            new OrderController();

    this.reviewController =
            new ReviewController();
}


    // =========================================================
    // MAIN DASHBOARD
    // =========================================================

    public VBox getDashboardHome() {

        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                BACKGROUND,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
        content.setStyle("-fx-background-color:#050B0A;");

        content.setBorder(null);


        // =====================================================
        // REAL COUNTS
        // =====================================================

        int productsCount =
                getProductsCount();

        int learningCount =
                getLearningCount();

        int schemesCount =
                SavedSchemesManager.getCount();

        int totalOrders = getTotalOrders();

double totalRating = getTotalRating();

double totalProfit = getTotalProfit();



        // =====================================================
        // WELCOME
        // =====================================================

        content.getChildren().add(
                createWelcomeBanner()
        );


        // =====================================================
        // STAT CARDS
        // =====================================================

        GridPane stats =
                new GridPane();

        stats.setHgap(18);
        stats.setVgap(18);


        stats.add(
                createStatCard(
                        "📦",
                        "My Products",
                        String.valueOf(
                                productsCount
                        ),
                        "Products uploaded"
                ),
                0,
                0
        );


        stats.add(
                createStatCard(
                        "📚",
                        "My Learning",
                        String.valueOf(
                                learningCount
                        ),
                        "Courses in learning"
                ),
                1,
                0
        );


        stats.add(
                createStatCard(
                        "🌱",
                        "Saved Schemes",
                        String.valueOf(
                                schemesCount
                        ),
                        "Government schemes saved"
                ),
                2,
                0
        );


        for (int i = 0; i < 3; i++) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(
                    33.33
            );

            stats.getColumnConstraints()
                    .add(column);
        }


        content.getChildren().add(
                stats
        );

        // =====================================================
// ORDER / RATING / PROFIT CARDS
// =====================================================

stats.add(
        createStatCard(
                "📦",
                "Total Orders",
                String.valueOf(
                        totalOrders
                ),
                "Orders received"
        ),
        0,
        1
);

stats.add(
        createStatCard(
                "⭐",
                "Total Ratings",
                String.format(
                        "%.1f",
                        totalRating
                ),
                "Average customer rating"
        ),
        1,
        1
);

stats.add(
        createStatCard(
                "₹",
                "Total Profit",
                "₹" + String.format(
                        "%,.0f",
                        totalProfit
                ),
                "Total earnings from orders"
        ),
        2,
        1
);


        // =====================================================
        // AGROBIZ CARD
        // =====================================================

        content.getChildren().add(
                createAgroBizForFarmersCard()
        );


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        content.getChildren().add(
                createQuickActions()
        );


        return content;
    }


    // =========================================================
    // WELCOME BANNER
    // =========================================================

    private VBox createWelcomeBanner() {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(
                        28,
                        32,
                        28,
                        32
                )
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                DARK_GREEN,
                                new CornerRadii(17),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(17),
                                new BorderWidths(1)
                        )
                )
        );


        // Label title =
        //         new Label(
        //                 "Welcome back, Farmer! 🌱"
        //         );
        Label title =
        new Label(
                "Welcome back, "
                + farmerName
                + "! 🌱"
        );

        title.setTextFill(
                TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );


        Label description =
                new Label(
                        "Manage your products, continue learning, "
                        + "and discover useful government schemes."
                );

        description.setTextFill(
                GREY
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        description.setWrapText(
                true
        );


        card.getChildren().addAll(
                title,
                description
        );


        return card;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String icon,
            String title,
            String value,
            String description) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(20)
        );

        card.setMinHeight(
                145
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(14),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(14),
                                new BorderWidths(1)
                        )
                )
        );


        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        25
                )
        );

        iconLabel.setPadding(
                new Insets(2, 0, 2, 0)
        );


        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                GREY
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );


        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setTextFill(
                GREEN
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );


        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setTextFill(
                MUTED
        );

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );

        descriptionLabel.setWrapText(
                true
        );


        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                valueLabel,
                descriptionLabel
        );


        return card;
    }


    // =========================================================
    // AGROBIZ FOR FARMERS
    // =========================================================

    private VBox createAgroBizForFarmersCard() {

        VBox card =
                new VBox(18);

        card.setPadding(
                new Insets(25)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(15),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(15),
                                new BorderWidths(1)
                        )
                )
        );


        // =====================================================
        // HEADER
        // =====================================================

        VBox titleBox =
                new VBox(4);


        Label title =
                new Label(
                        "🌱  AgroBiz for Farmers"
                );

        title.setTextFill(
                TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );


        Label description =
                new Label(
                        "Everything you need to manage your "
                        + "AgroBiz activities in one place."
                );

        description.setTextFill(
                GREY
        );

        description.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        description.setWrapText(
                true
        );


        titleBox.getChildren().addAll(
                title,
                description
        );


        card.getChildren().add(
                titleBox
        );


        // =====================================================
        // RECENT ACTIVITY TITLE
        // =====================================================

        Label activityTitle =
                new Label(
                        "Recent Activity"
                );

        activityTitle.setTextFill(
                TEXT
        );

        activityTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );


        card.getChildren().add(
                activityTitle
        );


        // =====================================================
        // RECENT ACTIVITY
        // =====================================================

        card.getChildren().add(
                createRecentActivity()
        );


        return card;
    }


    // =========================================================
    // REAL RECENT ACTIVITY
    // =========================================================

    private VBox createRecentActivity() {

        VBox activityBox =
                new VBox(10);


        List<ActivityItem> activities =
                new ArrayList<>();


        // =====================================================
        // 1. PRODUCT ACTIVITIES
        // =====================================================

        try {

            List<Product> products =
                    productController
                            .getFarmerProducts(
                                    farmerId
                            );


            if (products != null) {

                for (Product product :
                        products) {

                    if (product == null) {
                        continue;
                    }


                    Timestamp timestamp =
                            product.getCreatedAt();


                    if (timestamp == null) {
                        continue;
                    }


                    String productName =
                            product.getProductName();


                    if (productName == null ||
                            productName.trim().isEmpty()) {

                        productName =
                                "Product";
                    }


                    activities.add(
                            new ActivityItem(
                                    "📦",
                                    productName,
                                    "Product uploaded",
                                    timestamp
                                            .toDate()
                                            .toInstant()
                            )
                    );
                }
            }

        } catch (Exception e) {

            // Continue with other activities
        }


        // =====================================================
        // 2. LEARNING ACTIVITIES
        // =====================================================

        try {

            List<FarmerLearningDAO.LearningActivity>
                    learningActivities =
                    farmerLearningDAO
                            .getMyLearningActivities(
                                    farmerId
                            );


            if (learningActivities != null) {

                for (
                        FarmerLearningDAO.LearningActivity
                        learningActivity :
                        learningActivities) {

                    if (learningActivity == null ||
                            learningActivity.getCourse() == null ||
                            learningActivity.getAddedAt() == null) {

                        continue;
                    }


                    Course course =
                            learningActivity
                                    .getCourse();


                    String courseTitle =
                            course.getTitle();


                    if (courseTitle == null ||
                            courseTitle.trim().isEmpty()) {

                        courseTitle =
                                "Course";
                    }


                    activities.add(
                            new ActivityItem(
                                    "📚",
                                    courseTitle,
                                    "Added to My Learning",
                                    learningActivity
                                            .getAddedAt()
                                            .toDate()
                                            .toInstant()
                            )
                    );
                }
            }

        } catch (Exception e) {

            // Continue with scheme activities
        }


        // =====================================================
        // 3. SAVED SCHEME ACTIVITIES
        // =====================================================

        try {

            List<SavedSchemesManager.SavedScheme>
                    savedSchemes =
                    SavedSchemesManager
                            .getSavedSchemes();


            if (savedSchemes != null) {

                for (
                        SavedSchemesManager.SavedScheme
                        savedScheme :
                        savedSchemes) {

                    if (savedScheme == null ||
                            savedScheme.getScheme() == null ||
                            savedScheme.getSavedAt() == null) {

                        continue;
                    }


                    String schemeName =
                            savedScheme
                                    .getScheme()
                                    .getSchemeName();


                    if (schemeName == null ||
                            schemeName.trim().isEmpty()) {

                        schemeName =
                                "Government Scheme";
                    }


                    activities.add(
                            new ActivityItem(
                                    "🌱",
                                    schemeName,
                                    "Scheme saved",
                                    savedScheme
                                            .getSavedAt()
                            )
                    );
                }
            }

        } catch (Exception e) {

            // Continue with available activities
        }


        // =====================================================
        // SORT NEWEST FIRST
        // =====================================================

        activities.sort(
                Comparator.comparing(
                        ActivityItem::getTime
                ).reversed()
        );


        // =====================================================
        // EMPTY ACTIVITY
        // =====================================================

        if (activities.isEmpty()) {

            VBox emptyBox =
                    new VBox(5);

            emptyBox.setPadding(
                    new Insets(
                            8,
                            0,
                            8,
                            0
                    )
            );


            Label emptyTitle =
                    new Label(
                            "No recent activity yet."
                    );

            emptyTitle.setTextFill(
                    TEXT
            );

            emptyTitle.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            12
                    )
            );


            Label emptyDescription =
                    new Label(
                            "Upload a product, add a course to "
                            + "My Learning, or save a scheme."
                    );

            emptyDescription.setTextFill(
                    GREY
            );

            emptyDescription.setFont(
                    Font.font(
                            "Arial",
                            11
                    )
            );

            emptyDescription.setWrapText(
                    true
            );


            emptyBox.getChildren().addAll(
                    emptyTitle,
                    emptyDescription
            );


            activityBox.getChildren().add(
                    emptyBox
            );


            return activityBox;
        }


        // =====================================================
        // SHOW ONLY LATEST 5
        // =====================================================

        int limit =
                Math.min(
                        activities.size(),
                        5
                );


        for (int i = 0; i < limit; i++) {

            ActivityItem item =
                    activities.get(i);


            activityBox.getChildren().add(
                    createActivityRow(
                            item
                    )
            );
        }


        return activityBox;
    }


    // =========================================================
    // ACTIVITY ROW
    // =========================================================

    private HBox createActivityRow(
            ActivityItem item) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        11,
                        12,
                        11,
                        12
                )
        );

        row.setBackground(
                new Background(
                        new BackgroundFill(
                                SECONDARY_CARD,
                                new CornerRadii(9),
                                Insets.EMPTY
                        )
                )
        );

        row.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(9),
                                new BorderWidths(1)
                        )
                )
        );


        Label icon =
                new Label(
                        item.getIcon()
                );

        icon.setPrefWidth(
                30
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );


        VBox details =
                new VBox(3);

        HBox.setHgrow(
                details,
                Priority.ALWAYS
        );


        Label name =
                new Label(
                        item.getName()
                );

        name.setTextFill(
                TEXT
        );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        name.setWrapText(
                true
        );


        Label action =
                new Label(
                        item.getAction()
                        + "  •  "
                        + getTimeAgo(
                                item.getTime()
                        )
                );

        action.setTextFill(
                GREY
        );

        action.setFont(
                Font.font(
                        "Arial",
                        10
                ));


        details.getChildren().addAll(
                name,
                action
        );


        row.getChildren().addAll(
                icon,
                details
        );


        return row;
    }


    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    private VBox createQuickActions() {

        VBox section =
                new VBox(14);


        Label title =
                new Label(
                        "Quick Actions"
                );

        title.setTextFill(
                TEXT
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                ));


        HBox actions =
                new HBox(14);


        Button schemes =
                createQuickAction(
                        "🌱",
                        "Government Schemes",
                        "Explore available schemes"
                );


        schemes.setOnAction(
                event -> {

                    LoginPage.mainStage.setScene(
                            SchemesPage.getSchemesPage()
                    );
                }
        );


        Button learning =
                createQuickAction(
                        "📚",
                        "Learning",
                        "Continue your courses"
                );


        learning.setOnAction(
                event -> {

                    LearningPage page =
                            new LearningPage();

                    LoginPage.mainStage.setScene(
                            page.get_learning_pageScene()
                    );
                }
        );


        Button products =
                createQuickAction(
                        "📦",
                        "My Products",
                        "Manage your products"
                );


        products.setOnAction(
                event -> {

                    showProductMessage();
                }
        );


        actions.getChildren().addAll(
                schemes,
                learning,
                products
        );


        HBox.setHgrow(
                schemes,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                learning,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                products,
                Priority.ALWAYS
        );


        section.getChildren().addAll(
                title,
                actions
        );


        return section;
    }


    // =========================================================
    // QUICK ACTION CARD
    // =========================================================

    private Button createQuickAction(
            String icon,
            String title,
            String description) {

        Button button =
                new Button();


        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                82
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(12)
        );


        button.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD_BACKGROUND,
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );


        button.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                new BorderWidths(1)
                        )
                )
        );


        VBox content =
                new VBox(3);


        Label titleLabel =
                new Label(
                        icon + "  " + title
                );

        titleLabel.setTextFill(
                TEXT
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                ));


        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setTextFill(
                GREY
        );

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        10
                ));

        descriptionLabel.setWrapText(
                true
        );


        content.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );


        button.setGraphic(
                content
        );

        button.setCursor(
                Cursor.HAND
        );


        button.setOnMouseEntered(
                event -> {

                    button.setStyle(
                            "-fx-background-color:#1B3B25;" +
                            "-fx-border-color:#7ED957;" +
                            "-fx-border-radius:10;" +
                            "-fx-background-radius:10;"
                    );
                }
        );


        button.setOnMouseExited(
                event -> {

                    button.setStyle(
                            "-fx-background-color:#15331F;" +
                            "-fx-border-color:#294734;" +
                            "-fx-border-radius:10;" +
                            "-fx-background-radius:10;"
                    );
                }
        );


        return button;
    }


    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private Button createActionButton(
            String text) {

        Button button =
                new Button(
                        text
                );


        button.setPrefHeight(
                36
        );

        button.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );


        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                ));


        button.setTextFill(
                Color.web("#07110B")
        );


        button.setBackground(
                new Background(
                        new BackgroundFill(
                                GREEN,
                                new CornerRadii(7),
                                Insets.EMPTY
                        )
                )
        );


        button.setCursor(
                Cursor.HAND
        );


        return button;
    }


    // =========================================================
    // GET PRODUCTS COUNT
    // =========================================================

    private int getProductsCount() {

        try {

            List<Product> products =
                    productController
                            .getFarmerProducts(
                                    farmerId
                            );

            if (products == null) {
                return 0;
            }

            return products.size();

        } catch (Exception e) {

            return 0;
        }
    }


    // =========================================================
    // GET LEARNING COUNT
    // =========================================================

    private int getLearningCount() {

        try {

            List<Course> courses =
                    farmerLearningDAO
                            .getMyLearningCourses(
                                    farmerId
                            );

            if (courses == null) {
                return 0;
            }

            return courses.size();

        } catch (Exception e) {

            return 0;
        }
    }

    // =========================================================
// GET TOTAL ORDERS
// =========================================================

// =========================================================
// GET TOTAL ORDERS
// =========================================================

private int getTotalOrders() {

    try {

        List<Order> orders =
                orderController.getFarmerOrders(
                        farmerId
                );

        if (orders == null) {
            return 0;
        }

        return orders.size();

    } catch (Exception e) {

        e.printStackTrace();

        return 0;
    }
}


// =========================================================
// GET TOTAL RATING
// =========================================================

// =========================================================
// GET TOTAL RATING
// =========================================================

private double getTotalRating() {

    try {

        List<Review> reviews =
                reviewController.getFarmerReviews(
                        farmerId
                );

        if (reviews == null ||
                reviews.isEmpty()) {

            return 0.0;
        }

        double totalRating = 0.0;

        for (Review review : reviews) {

            if (review == null) {
                continue;
            }

            totalRating +=
                    review.getRating();
        }

        return totalRating / reviews.size();

    } catch (Exception e) {

        e.printStackTrace();

        return 0.0;
    }
}


// =========================================================
// GET TOTAL PROFIT
// =========================================================

// =========================================================
// GET TOTAL PROFIT
// =========================================================

private double getTotalProfit() {

    try {

        List<Order> orders =
                orderController.getFarmerOrders(
                        farmerId
                );

        if (orders == null ||
                orders.isEmpty()) {

            return 0.0;
        }

        double totalProfit = 0.0;

        for (Order order : orders) {

            if (order == null) {
                continue;
            }

            totalProfit +=
                    order.getTotalAmount();
        }

        return totalProfit;

    } catch (Exception e) {

        e.printStackTrace();

        return 0.0;
    }
}


    // =========================================================
    // TIME AGO
    // =========================================================

    private String getTimeAgo(
            Instant activityTime) {

        if (activityTime == null) {

            return "";
        }


        Duration duration =
                Duration.between(
                        activityTime,
                        Instant.now()
                );


        long seconds =
                duration.getSeconds();


        if (seconds < 60) {

            return "Just now";
        }


        long minutes =
                seconds / 60;


        if (minutes < 60) {

            return minutes == 1
                    ? "1 minute ago"
                    : minutes + " minutes ago";
        }


        long hours =
                minutes / 60;


        if (hours < 24) {

            return hours == 1
                    ? "1 hour ago"
                    : hours + " hours ago";
        }


        long days =
                hours / 24;


        if (days < 7) {

            return days == 1
                    ? "1 day ago"
                    : days + " days ago";
        }


        long weeks =
                days / 7;


        if (weeks < 4) {

            return weeks == 1
                    ? "1 week ago"
                    : weeks + " weeks ago";
        }


        long months =
                days / 30;


        if (months < 12) {

            return months == 1
                    ? "1 month ago"
                    : months + " months ago";
        }


        long years =
                days / 365;


        return years == 1
                ? "1 year ago"
                : years + " years ago";
    }


    // =========================================================
    // PRODUCT INFORMATION
    // =========================================================

    private void showProductMessage() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(
                "My Products"
        );


        alert.setHeaderText(
                null
        );


        alert.setContentText(
                "You currently have "
                + getProductsCount()
                + " product(s) uploaded."
        );


        alert.showAndWait();
    }


    // =========================================================
    // ACTIVITY ITEM
    // =========================================================

    private static class ActivityItem {

        private final String icon;

        private final String name;

        private final String action;

        private final Instant time;


        public ActivityItem(
                String icon,
                String name,
                String action,
                Instant time) {

            this.icon =
                    icon;

            this.name =
                    name;

            this.action =
                    action;

            this.time =
                    time;
        }


        public String getIcon() {

            return icon;
        }


        public String getName() {

            return name;
        }


        public String getAction() {

            return action;
        }


        public Instant getTime() {

            return time;
        }
    }
}