  

// package com.pravartak.view.farmer;


// import java.util.List;

// //import com.pravartak.dao.admindao.FirebaseCourseDAO;
// import com.pravartak.model.admin.Course;
// import com.pravartak.view.farmer.common.Footer;
// import com.pravartak.view.farmer.common.NavBar;
// import com.pravartak.view.login.LoginPage;
// import com.pravartak.dao.admindao.FirebaseCourseDAO;
// import com.pravartak.dao.farmer.FarmerLearningDAO;
// import com.pravartak.view.login.LoginPage;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ProgressIndicator;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.FlowPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Stage;

// public class LearningPage {

//     // =========================================================
//     // SCENE
//     // =========================================================

//     private Scene learningpagScene;

//     // =========================================================
//     // FIREBASE DAO
//     // =========================================================

//     private final FarmerLearningDAO farmerLearningDAO;

//     // =========================================================
//     // MAIN CONTAINER
//     // =========================================================

//     private FlowPane courseContainer;

//     // =========================================================
//     // CONSTRUCTOR
//     // =========================================================

//     public LearningPage() {

//     farmerLearningDAO =
//             new FarmerLearningDAO();
// }

//     // =========================================================
//     // MAIN PAGE
//     // =========================================================

//     public Scene get_learning_pageScene() {

//         // =====================================================
//         // MAIN BORDER PANE
//         // =====================================================

//         BorderPane borderPane = new BorderPane();

//         borderPane.setStyle(
//                 "-fx-background-color: #080c0d;");

//         // =====================================================
//         // NAVBAR
//         // =====================================================

//         borderPane.setTop(
//                 new NavBar().createNavbar("Learning"));

//         // =====================================================
//         // FOOTER
//         // =====================================================

//         borderPane.setBottom(
//                 new Footer().createFooter());

//         // =====================================================
//         // MAIN CONTENT
//         // =====================================================

//         VBox mainContent = new VBox(22);

//         mainContent.setPadding(
//                 new Insets(28, 40, 35, 40));

//         mainContent.setStyle(
//                 "-fx-background-color: #080c0d;");

//         // =====================================================
//         // PAGE HEADER
//         // =====================================================

//         VBox headingBox = createPageHeader();

//         // =====================================================
//         // SECTION HEADER
//         // =====================================================

//         HBox sectionHeader = createSectionHeader();

//         // =====================================================
//         // COURSE CONTAINER
//         // =====================================================

//         courseContainer = new FlowPane();

//         courseContainer.setHgap(22);
//         courseContainer.setVgap(22);

//         courseContainer.setPrefWrapLength(1050);

//         courseContainer.setPadding(
//                 new Insets(5, 0, 25, 0));

//         // =====================================================
//         // LOAD COURSES
//         // =====================================================

//         loadCourses();

//         // =====================================================
//         // ADD CONTENT
//         // =====================================================

//         mainContent.getChildren().addAll(
//                 headingBox,
//                 sectionHeader,
//                 courseContainer);

//         // =====================================================
//         // SCROLL PANE
//         // =====================================================

//         ScrollPane scrollPane = new ScrollPane(
//                 mainContent);

//         scrollPane.setFitToWidth(true);

//         scrollPane.setHbarPolicy(
//                 ScrollPane.ScrollBarPolicy.NEVER);

//         scrollPane.setVbarPolicy(
//                 ScrollPane.ScrollBarPolicy.AS_NEEDED);

//         scrollPane.setStyle(
//                 "-fx-background: #080c0d;" +
//                         "-fx-background-color: #080c0d;");

//         // =====================================================
//         // CENTER
//         // =====================================================

//         borderPane.setCenter(
//                 scrollPane);

//         // =====================================================
//         // SCENE
//         // =====================================================

//         learningpagScene = new Scene(
//                 borderPane,
//                 1200,
//                 750);

//         return learningpagScene;
//     }

//     // =========================================================
//     // PAGE HEADER
//     // =========================================================

//     private VBox createPageHeader() {

//         VBox headingBox = new VBox(6);

//         Label title = new Label(
//                 "Learning");

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         30));

//         title.setTextFill(
//                 Color.WHITE);

//         Label subtitle = new Label(
//                 "Courses you have added from the Explorer.");

//         subtitle.setFont(
//                 Font.font(
//                         "Arial",
//                         14));

//         subtitle.setTextFill(
//                 Color.web("#AFC4B2"));

//         headingBox.getChildren().addAll(
//                 title,
//                 subtitle);

//         return headingBox;
//     }

//     // =========================================================
//     // SECTION HEADER
//     // =========================================================

//     private HBox createSectionHeader() {

//         HBox sectionHeader = new HBox();

//         sectionHeader.setAlignment(
//                 Pos.CENTER_LEFT);

//         Label sectionTitle = new Label(
//         "My Courses");
//         sectionTitle.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         20));

//         sectionTitle.setTextFill(
//                 Color.WHITE);

//         sectionHeader.getChildren().add(
//                 sectionTitle);

//         return sectionHeader;
//     }

//     // =========================================================
//     // LOAD COURSES FROM FIREBASE
//     // =========================================================

//     private void loadCourses() {

//     courseContainer.getChildren().clear();

//     ProgressIndicator loading =
//             new ProgressIndicator();

//     loading.setPrefSize(
//             45,
//             45
//     );

//     courseContainer.getChildren().add(
//             loading
//     );

//     try {

//         // =============================================
//         // GET LOGGED-IN FARMER
//         // =============================================

//         int farmerId =
//                 LoginPage.getLoggedInFarmerId();

//         if (farmerId <= 0) {

//             courseContainer.getChildren().clear();

//             showEmptyMessage(
//                     "Farmer account not found."
//             );

//             return;
//         }

//         // =============================================
//         // GET ONLY FARMER'S COURSES
//         // =============================================

//         List<Course> courses =
//                 farmerLearningDAO
//                         .getMyLearningCourses(
//                                 farmerId
//                         );

//         courseContainer.getChildren().clear();

//         // =============================================
//         // CHECK EMPTY
//         // =============================================

//         if (courses == null ||
//                 courses.isEmpty()) {

//             showEmptyMessage(
//                     "You haven't added any courses yet."
//             );

//             return;
//         }

//         // =============================================
//         // DISPLAY COURSES
//         // =============================================

//         for (Course course : courses) {

            
//             VBox courseCard =
//                     createCourseCard(course);

//             courseContainer.getChildren().add(
//                     courseCard
//             );
//         }

//     } catch (Exception e) {

//         e.printStackTrace();

//         courseContainer.getChildren().clear();

//         showEmptyMessage(
//                 "Unable to load your courses."
//         );
//     }
// }

//     // =========================================================
//     // EMPTY MESSAGE
//     // =========================================================

//     private void showEmptyMessage(
//             String message) {

//         VBox emptyBox = new VBox(10);

//         emptyBox.setAlignment(
//                 Pos.CENTER);

//         emptyBox.setPrefWidth(
//                 1050);

//         emptyBox.setPadding(
//                 new Insets(50));

//         Label icon = new Label(
//                 "📚");

//         icon.setFont(
//                 Font.font(
//                         "Arial",
//                         35));

//         Label messageLabel = new Label(
//                 message);

//         messageLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         15));

//         messageLabel.setTextFill(
//                 Color.web("#AFC4B2"));

//         emptyBox.getChildren().addAll(
//                 icon,
//                 messageLabel);

//         courseContainer.getChildren().add(
//                 emptyBox);
//     }

//     // =========================================================
//     // CREATE COURSE CARD
//     // =========================================================

//     private VBox createCourseCard(
//             Course course) {

//         VBox card = new VBox(11);

//         card.setPrefWidth(285);
//         card.setPrefHeight(365);

//         card.setPadding(
//                 new Insets(12));

//         card.setStyle(
//                 "-fx-background-color: #193522;" +
//                         "-fx-background-radius: 16;" +
//                         "-fx-border-color: #31583A;" +
//                         "-fx-border-radius: 16;" +
//                         "-fx-border-width: 1;");

//         // =====================================================
//         // IMAGE
//         // =====================================================

//         StackPane imageContainer = createCourseImage(
//                 course.getThumbnailUrl());

//         // =====================================================
//         // COURSE TITLE
//         // =====================================================

//         Label title = new Label(
//                 course.getTitle() == null
//                         ? "Untitled Course"
//                         : course.getTitle());

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         17));

//         title.setTextFill(
//                 Color.WHITE);

//         title.setWrapText(true);

//         title.setMaxWidth(
//                 255);

//         // =====================================================
//         // CATEGORY
//         // =====================================================

//         Label category = new Label(
//                 safeValue(
//                         course.getCategory(),
//                         "General"));

//         category.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         12));

//         category.setTextFill(
//                 Color.web("#7ED184"));

//         // =====================================================
//         // COURSE INFORMATION
//         // =====================================================

//         HBox information = createCourseInformation(
//                 course);

//         // =====================================================
//         // SPACER
//         // =====================================================

//         Region spacer = new Region();

//         VBox.setVgrow(
//                 spacer,
//                 Priority.ALWAYS);

        

//         // =====================================================
//         // CARD HOVER
//         // =====================================================

//         card.setOnMouseEntered(e -> {

//             card.setStyle(
//                     "-fx-background-color: #21452B;" +
//                             "-fx-background-radius: 16;" +
//                             "-fx-border-color: #63A86B;" +
//                             "-fx-border-radius: 16;" +
//                             "-fx-border-width: 1;" +
//                             "-fx-cursor: hand;");
//         });

//         card.setOnMouseExited(e -> {

//             card.setStyle(
//                     "-fx-background-color: #193522;" +
//                             "-fx-background-radius: 16;" +
//                             "-fx-border-color: #31583A;" +
//                             "-fx-border-radius: 16;" +
//                             "-fx-border-width: 1;");
//         });

//         // =====================================================
//         // ADD EVERYTHING
//         // =====================================================

//         card.getChildren().addAll(
//                 imageContainer,
//                 title,
//                 category,
//                 information,
//                 spacer);

//         return card;
//     }

//     // =========================================================
//     // COURSE INFORMATION
//     // =========================================================

//     private HBox createCourseInformation(
//             Course course) {

//         HBox information = new HBox(8);

//         information.setAlignment(
//                 Pos.CENTER_LEFT);

//         // =====================================================
//         // DIFFICULTY
//         // =====================================================

//         Label difficulty = createInfoLabel(
//                 "● " +
//                         safeValue(
//                                 course.getDifficulty(),
//                                 "Beginner"));

//         // =====================================================
//         // LANGUAGE
//         // =====================================================

//         Label language = createInfoLabel(
//                 "• " +
//                         safeValue(
//                                 course.getLanguage(),
//                                 "English"));

//         information.getChildren().addAll(
//                 difficulty,
//                 language);

//         return information;
//     }

//     // =========================================================
//     // INFO LABEL
//     // =========================================================

//     private Label createInfoLabel(
//             String text) {

//         Label label = new Label(text);

//         label.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         11));

//         label.setTextFill(
//                 Color.web("#AFC4B2"));

//         return label;
//     }

//     // =========================================================
//     // COURSE IMAGE
//     // =========================================================

//     private StackPane createCourseImage(
//             String imageUrl) {

//         StackPane container = new StackPane();

//         container.setPrefWidth(
//                 261);

//         container.setPrefHeight(
//                 135);

//         container.setMaxWidth(
//                 261);

//         container.setMaxHeight(
//                 135);

//         // =====================================================
//         // CLIPPING
//         // =====================================================

//         Rectangle clip = new Rectangle(
//                 261,
//                 135);

//         clip.setArcWidth(18);
//         clip.setArcHeight(18);

//         container.setClip(
//                 clip);

//         // =====================================================
//         // DEFAULT BACKGROUND
//         // =====================================================

//         container.setStyle(
//                 "-fx-background-color: #284B30;");

//         // =====================================================
//         // IMAGE VIEW
//         // =====================================================

//         ImageView imageView = new ImageView();

//         imageView.setFitWidth(
//                 261);

//         imageView.setFitHeight(
//                 135);

//         imageView.setPreserveRatio(
//                 false);

//         // =====================================================
//         // LOAD IMAGE
//         // =====================================================

//         if (imageUrl != null &&
//                 !imageUrl.trim().isEmpty()) {

//             try {

//                 Image image = new Image(
//                         imageUrl,
//                         261,
//                         135,
//                         false,
//                         true,
//                         true);

//                 imageView.setImage(
//                         image);

//                 // -------------------------------------------------
//                 // FALLBACK IF IMAGE FAILED
//                 // -------------------------------------------------

//                 if (image.isError()) {

//                     setImagePlaceholder(
//                             container);
//                 }

//             } catch (Exception e) {

//                 setImagePlaceholder(
//                         container);
//             }

//         } else {

//             setImagePlaceholder(
//                     container);
//         }

//         // =====================================================
//         // ADD IMAGE
//         // =====================================================

//         container.getChildren().add(
//                 imageView);

//         return container;
//     }

//     // =========================================================
//     // IMAGE PLACEHOLDER
//     // =========================================================

//     private void setImagePlaceholder(
//             StackPane container) {

//         Label placeholder = new Label(
//                 "🌱");

//         placeholder.setFont(
//                 Font.font(
//                         "Arial",
//                         38));

//         placeholder.setTextFill(
//                 Color.web("#AFC4B2"));

//         container.getChildren().add(
//                 placeholder);
//     }

//     // =========================================================
//     // OPEN COURSE
//     // =========================================================

//     private void openCourse(
//             Course course) {

//         if (course == null) {
//             return;
//         }

//         System.out.println(
//                 "Opening course: "
//                         + course.getTitle()
//                         + " | Course ID: "
//                         + course.getCourseId());

//         FarmerModulesPage modulesPage = new FarmerModulesPage(
//                 course);

//         Stage stage = (Stage) courseContainer
//                 .getScene()
//                 .getWindow();

//         stage.setScene(
//                 modulesPage.getModulesPageScene());
//     }

//     // =========================================================
//     // SAFE STRING
//     // =========================================================

//     private String safeValue(
//             String value,
//             String defaultValue) {

//         if (value == null ||
//                 value.trim().isEmpty()) {

//             return defaultValue;
//         }

//         return value.trim();
//     }

// }
package com.pravartak.view.farmer;

import java.util.ArrayList;
import java.util.List;

import com.pravartak.dao.farmer.FarmerLearningDAO;
import com.pravartak.model.admin.Course;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
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
 * MY LEARNING PAGE
 * =========================================================
 *
 * This page displays only courses added by the
 * currently logged-in farmer from Explorer.
 *
 * Explorer:
 *
 *      + Add to My Learning
 *
 *              ↓
 *
 *      FarmerLearningDAO
 *
 *              ↓
 *
 *          My Learning
 *
 *              ↓
 *
 *      Continue Learning
 *
 *              ↓
 *
 *       FarmerModulesPage
 *
 * =========================================================
 */
public class LearningPage {

    // =========================================================
    // SCENE
    // =========================================================

    private Scene learningpagScene;

    // =========================================================
    // DAO
    // =========================================================

    private final FarmerLearningDAO farmerLearningDAO;

    // =========================================================
    // COURSE CONTAINER
    // =========================================================

    private FlowPane courseContainer;

    // =========================================================
    // COURSE LIST
    // =========================================================

    private List<Course> myCourses =
            new ArrayList<>();


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LearningPage() {

        farmerLearningDAO =
                new FarmerLearningDAO();
    }


    // =========================================================
    // GET LEARNING PAGE
    // =========================================================

    public Scene get_learning_pageScene() {

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
                        "Learning"
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

        VBox mainContent =
                new VBox(25);

        mainContent.setPadding(
                new Insets(
                        30,
                        45,
                        45,
                        45
                )
        );

        mainContent.setStyle(
                "-fx-background-color:#080c0d;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        VBox header =
                createPageHeader();


        // =====================================================
        // LEARNING SUMMARY
        // =====================================================

        HBox summary =
                createLearningSummary();


        // =====================================================
        // SECTION HEADER
        // =====================================================

        HBox sectionHeader =
                createSectionHeader();


        // =====================================================
        // COURSE CONTAINER
        // =====================================================

        courseContainer =
                new FlowPane();

        courseContainer.setHgap(
                22
        );

        courseContainer.setVgap(
                22
        );

        courseContainer.setAlignment(
                Pos.TOP_LEFT
        );

        courseContainer.setPrefWrapLength(
                1050
        );

        courseContainer.setPadding(
                new Insets(
                        5,
                        0,
                        30,
                        0
                )
        );


        // =====================================================
        // LOAD COURSES
        // =====================================================

        loadMyCourses();


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        mainContent
                .getChildren()
                .addAll(
                        header,
                        summary,
                        sectionHeader,
                        courseContainer
                );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
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

        learningpagScene =
                new Scene(
                        borderPane,
                        1200,
                        750
                );


        return learningpagScene;
    }


    // =========================================================
    // PAGE HEADER
    // =========================================================

    private VBox createPageHeader() {

        VBox header =
                new VBox(7);

        header.setPadding(
                new Insets(
                        5,
                        0,
                        5,
                        0
                )
        );


        // =====================================================
        // TITLE ROW
        // =====================================================

        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label title =
                new Label(
                        "My Learning"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        title.setTextFill(
                Color.WHITE
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        Button refreshButton =
                new Button(
                        "↻ Refresh"
                );

        refreshButton.setPrefHeight(
                36
        );

        refreshButton.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );

        refreshButton.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-text-fill:#AFC4B2;" +
                "-fx-border-color:#2A3530;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );


        refreshButton.setOnMouseEntered(
                e -> {

                    refreshButton.setStyle(
                            "-fx-background-color:#193522;" +
                            "-fx-text-fill:#68d34a;" +
                            "-fx-border-color:#68d34a;" +
                            "-fx-border-radius:8;" +
                            "-fx-background-radius:8;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        refreshButton.setOnMouseExited(
                e -> {

                    refreshButton.setStyle(
                            "-fx-background-color:#101516;" +
                            "-fx-text-fill:#AFC4B2;" +
                            "-fx-border-color:#2A3530;" +
                            "-fx-border-radius:8;" +
                            "-fx-background-radius:8;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        refreshButton.setOnAction(
                e -> loadMyCourses()
        );


        titleRow
                .getChildren()
                .addAll(
                        title,
                        spacer,
                        refreshButton
                );


        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle =
                new Label(
                        "Continue learning from the courses "
                        + "you have added from Explorer."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#8FA397")
        );


        header
                .getChildren()
                .addAll(
                        titleRow,
                        subtitle
                );


        return header;
    }


    // =========================================================
    // LEARNING SUMMARY
    // =========================================================

    private HBox createLearningSummary() {

        HBox summary =
                new HBox(15);

        summary.setAlignment(
                Pos.CENTER_LEFT
        );


        // =====================================================
        // COURSES CARD
        // =====================================================

        VBox coursesBox =
                createSummaryCard(
                        "📚",
                        "My Courses",
                        "Courses added from Explorer"
                );


        // =====================================================
        // LEARNING CARD
        // =====================================================

        VBox learningBox =
                createSummaryCard(
                        "🌱",
                        "Keep Growing",
                        "Build your farming knowledge"
                );


        // =====================================================
        // EXPLORE CARD
        // =====================================================

        VBox exploreBox =
                createSummaryCard(
                        "🔎",
                        "Discover More",
                        "Find new farming courses"
                );


        summary
                .getChildren()
                .addAll(
                        coursesBox,
                        learningBox,
                        exploreBox
                );


        return summary;
    }


    // =========================================================
    // SUMMARY CARD
    // =========================================================

    private VBox createSummaryCard(
            String iconText,
            String titleText,
            String descriptionText) {

        VBox card =
                new VBox(7);

        card.setPrefWidth(
                300
        );

        card.setPrefHeight(
                90
        );

        card.setPadding(
                new Insets(
                        14,
                        18,
                        14,
                        18
                )
        );

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#202A27;" +
                "-fx-border-radius:12;"
        );


        HBox titleRow =
                new HBox(10);

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label icon =
                new Label(
                        iconText
                );

        icon.setFont(
                Font.font(
                        "Arial",
                        19
                )
        );


        Label title =
                new Label(
                        titleText
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        title.setTextFill(
                Color.WHITE
        );


        titleRow
                .getChildren()
                .addAll(
                        icon,
                        title
                );


        Label description =
                new Label(
                        descriptionText
                );

        description.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        description.setTextFill(
                Color.web("#7F9086")
        );


        card
                .getChildren()
                .addAll(
                        titleRow,
                        description
                );


        return card;
    }


    // =========================================================
    // SECTION HEADER
    // =========================================================

    private HBox createSectionHeader() {

        HBox section =
                new HBox();

        section.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox textBox =
                new VBox(3);


        Label title =
                new Label(
                        "Continue Learning"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        title.setTextFill(
                Color.WHITE
        );


        Label subtitle =
                new Label(
                        "Pick up a course and continue "
                        + "where your learning begins."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        subtitle.setTextFill(
                Color.web("#77867D")
        );


        textBox
                .getChildren()
                .addAll(
                        title,
                        subtitle
                );


        section
                .getChildren()
                .add(
                        textBox
                );


        return section;
    }


    // =========================================================
    // LOAD MY COURSES
    // =========================================================

    private void loadMyCourses() {

        if (courseContainer == null) {
            return;
        }


        // =====================================================
        // CLEAR
        // =====================================================

        courseContainer
                .getChildren()
                .clear();


        // =====================================================
        // LOADING
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
            // GET FARMER ID
            // =================================================

            int farmerId =
                    LoginPage
                            .getLoggedInFarmerId();


            System.out.println(
                    "Loading My Learning for Farmer ID = "
                    + farmerId
            );


            // =================================================
            // CHECK FARMER
            // =================================================

            if (farmerId <= 0) {

                courseContainer
                        .getChildren()
                        .clear();

                showEmptyMessage(
                        "Farmer account could not be found."
                );

                return;
            }


            // =================================================
            // GET ONLY FARMER COURSES
            // =================================================

            myCourses =
                    farmerLearningDAO
                            .getMyLearningCourses(
                                    farmerId
                            );


            courseContainer
                    .getChildren()
                    .clear();


            // =================================================
            // EMPTY
            // =================================================

            if (myCourses == null ||
                    myCourses.isEmpty()) {

                showEmptyMessage(
                        "You haven't added any courses yet."
                );

                return;
            }


            // =================================================
            // DISPLAY
            // =================================================

            for (Course course : myCourses) {

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


        } catch (Exception e) {

            e.printStackTrace();


            courseContainer
                    .getChildren()
                    .clear();


            showEmptyMessage(
                    "Unable to load your learning courses."
            );
        }
    }


    // =========================================================
    // EMPTY MESSAGE
    // =========================================================

    private void showEmptyMessage(
            String message) {

        VBox emptyBox =
                new VBox(15);

        emptyBox.setAlignment(
                Pos.CENTER
        );

        emptyBox.setPrefWidth(
                1050
        );

        emptyBox.setPadding(
                new Insets(
                        55
                )
        );

        emptyBox.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#202A27;" +
                "-fx-border-radius:15;"
        );


        Label icon =
                new Label(
                        "📚"
                );

        icon.setFont(
                Font.font(
                        "Arial",
                        42
                )
        );


        Label title =
                new Label(
                        "Your Learning Library is Empty"
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


        Label description =
                new Label(
                        message
                        + "\n\nGo to Explorer and add a course "
                        + "to start learning."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        description.setTextFill(
                Color.web("#8FA397")
        );

        description.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );


        Button explorerButton =
                new Button(
                        "Explore Courses  →"
                );

        explorerButton.setPrefHeight(
                40
        );

        explorerButton.setPadding(
                new Insets(
                        0,
                        22,
                        0,
                        22
                )
        );

        explorerButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        explorerButton.setStyle(
                "-fx-background-color:#68d34a;" +
                "-fx-text-fill:#080c0d;" +
                "-fx-background-radius:9;" +
                "-fx-cursor:hand;"
        );


        explorerButton.setOnAction(
                e -> {

                    ExplorerPage explorerPage =
                            new ExplorerPage();

                    LoginPage.mainStage
                            .setScene(
                                    explorerPage
                                            .getExplorerPage()
                            );
                }
        );


        emptyBox
                .getChildren()
                .addAll(
                        icon,
                        title,
                        description,
                        explorerButton
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
                new VBox(11);

        card.setPrefWidth(
                300
        );

        card.setPrefHeight(
                365
        );

        card.setPadding(
                new Insets(
                        12
                )
        );


        // =====================================================
        // CARD STYLES
        // =====================================================

        String normalStyle =
                "-fx-background-color:#101516;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#242b2c;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:1;";


        String hoverStyle =
                "-fx-background-color:#142019;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#68d34a;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:1;";


        card.setStyle(
                normalStyle
        );


        // =====================================================
        // IMAGE
        // =====================================================

        StackPane image =
                createCourseImage(
                        course.getThumbnailUrl()
                );


        // =====================================================
        // CATEGORY BADGE
        // =====================================================

        Label category =
                new Label(
                        safeValue(
                                course.getCategory(),
                                "General"
                        )
                );

        category.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9
                )
        );

        category.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        category.setTextFill(
                Color.web("#68d34a")
        );

        category.setStyle(
                "-fx-background-color:#193522;" +
                "-fx-background-radius:20;"
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
                270
        );


        // =====================================================
        // SHORT DESCRIPTION
        // =====================================================

        Label description =
                new Label(
                        "Continue exploring practical "
                        + "knowledge and modern farming "
                        + "techniques."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        description.setTextFill(
                Color.web("#7F9086")
        );

        description.setWrapText(
                true
        );

        description.setMaxWidth(
                270
        );


        // =====================================================
        // INFORMATION
        // =====================================================

        HBox information =
                new HBox(8);

        information.setAlignment(
                Pos.CENTER_LEFT
        );


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
        // CONTINUE BUTTON
        // =====================================================

        Button continueButton =
                new Button(
                        "Continue Learning  →"
                );

        continueButton.setPrefWidth(
                276
        );

        continueButton.setPrefHeight(
                43
        );

        continueButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        continueButton.setTextFill(
                Color.web("#080c0d")
        );

        continueButton.setStyle(
                "-fx-background-color:#68d34a;" +
                "-fx-background-radius:9;" +
                "-fx-cursor:hand;"
        );

        continueButton.setCursor(
                Cursor.HAND
        );


        // =====================================================
        // BUTTON HOVER
        // =====================================================

        continueButton.setOnMouseEntered(
                e -> {

                    continueButton.setStyle(
                            "-fx-background-color:#82df68;" +
                            "-fx-text-fill:#080c0d;" +
                            "-fx-background-radius:9;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        continueButton.setOnMouseExited(
                e -> {

                    continueButton.setStyle(
                            "-fx-background-color:#68d34a;" +
                            "-fx-text-fill:#080c0d;" +
                            "-fx-background-radius:9;" +
                            "-fx-cursor:hand;"
                    );
                }
        );


        // =====================================================
        // CONTINUE LEARNING ACTION
        // =====================================================

        continueButton.setOnAction(
                e -> {

                    openCourse(
                            course
                    );
                }
        );


        // =====================================================
        // CARD CLICK
        // =====================================================

        card.setOnMouseClicked(
                e -> {

                    if (e.getTarget()
                            != continueButton) {

                        openCourse(
                                course
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
                            hoverStyle
                    );
                }
        );


        card.setOnMouseExited(
                e -> {

                    card.setStyle(
                            normalStyle
                    );
                }
        );


        // =====================================================
        // ADD CONTENT
        // =====================================================

        card.getChildren()
                .addAll(
                        image,
                        category,
                        title,
                        description,
                        information,
                        spacer,
                        continueButton
                );


        return card;
    }


    // =========================================================
    // COURSE IMAGE
    // =========================================================

    private StackPane createCourseImage(
            String imageUrl) {

        StackPane container =
                new StackPane();

        container.setPrefWidth(
                276
        );

        container.setPrefHeight(
                145
        );

        container.setMaxWidth(
                276
        );

        container.setMaxHeight(
                145
        );


        // =====================================================
        // CLIP
        // =====================================================

        Rectangle clip =
                new Rectangle(
                        276,
                        145
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


        container.setStyle(
                "-fx-background-color:#193522;"
        );


        // =====================================================
        // IMAGE
        // =====================================================

        ImageView imageView =
                new ImageView();

        imageView.setFitWidth(
                276
        );

        imageView.setFitHeight(
                145
        );

        imageView.setPreserveRatio(
                false
        );


        boolean imageLoaded =
                false;


        if (imageUrl != null &&
                !imageUrl.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                276,
                                145,
                                false,
                                true,
                                true
                        );


                if (!image.isError()) {

                    imageView.setImage(
                            image
                    );

                    imageLoaded = true;
                }

            } catch (Exception e) {

                System.out.println(
                        "Course image failed to load."
                );
            }
        }


        // =====================================================
        // PLACEHOLDER
        // =====================================================

        if (!imageLoaded) {

            Label placeholder =
                    new Label(
                            "🌱"
                    );

            placeholder.setFont(
                    Font.font(
                            "Arial",
                            42
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


        // =====================================================
        // ADD IMAGE
        // =====================================================

        container
                .getChildren()
                .add(
                        imageView
                );


        return container;
    }


    // =========================================================
    // OPEN COURSE MODULES
    // =========================================================

    private void openCourse(
            Course course) {

        if (course == null) {

            return;
        }


        System.out.println(
                "================================"
        );

        System.out.println(
                "Opening Course"
        );

        System.out.println(
                "Course ID = "
                + course.getCourseId()
        );

        System.out.println(
                "Course Title = "
                + course.getTitle()
        );

        System.out.println(
                "================================"
        );


        try {

            // =================================================
            // OPEN MODULE PAGE
            // =================================================

            FarmerModulesPage modulesPage =
                    new FarmerModulesPage(
                            course
                    );


            LoginPage.mainStage
                    .setScene(
                            modulesPage
                                    .getModulesPageScene()
                    );


        } catch (Exception e) {

            e.printStackTrace();

            System.out.println(
                    "Unable to open course modules."
            );
        }
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
}