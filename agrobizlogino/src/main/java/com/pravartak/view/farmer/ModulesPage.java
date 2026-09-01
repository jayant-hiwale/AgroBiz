package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ModulesPage {

    private Scene modulesPageScene;

    private final String courseTitle;

    private BorderPane mainBorderPane;

    public ModulesPage(String courseTitle) {

        this.courseTitle = courseTitle;
    }


    // =========================================================
    // MAIN MODULE PAGE
    // =========================================================

    public Scene getModulesPageScene() {

        mainBorderPane = new BorderPane();

        mainBorderPane.setStyle(
        "-fx-background-color: #080c0d;"
);

        // -----------------------------------------------------
        // NAVBAR
        // -----------------------------------------------------

        mainBorderPane.setTop(
                new NavBar().createNavbar("Learning")
        );

        // -----------------------------------------------------
        // FOOTER
        // -----------------------------------------------------

        mainBorderPane.setBottom(
                new Footer().createFooter()
        );


        // -----------------------------------------------------
        // MAIN CONTENT
        // -----------------------------------------------------

        VBox mainContent = new VBox(20);

        mainContent.setPadding(
                new Insets(25, 40, 30, 40)
        );


        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton = new Button(
                "← Back to Learning"
        );

        backButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        backButton.setTextFill(
                Color.web("#DCEBDD")
        );

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #4B7354;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> {

    LearningPage learningPage =
            new LearningPage();

    ((Stage) mainBorderPane.getScene().getWindow()).setScene(
            learningPage.get_learning_pageScene()
    );
});


        // =====================================================
        // COURSE HEADER
        // =====================================================

        VBox courseHeader = new VBox(8);

        Label courseLabel = new Label(
                "COURSE"
        );

        courseLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        courseLabel.setTextFill(
                Color.web("#78C47E")
        );


        Label courseName = new Label(
                courseTitle
        );

        courseName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        courseName.setTextFill(
                Color.WHITE
        );


        Label courseDescription = new Label(
                "Learn step by step through modules and lessons."
        );

        courseDescription.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        courseDescription.setTextFill(
                Color.web("#AFC4B2")
        );


        courseHeader.getChildren().addAll(
                courseLabel,
                courseName,
                courseDescription
        );


        // =====================================================
        // PROGRESS CARD
        // =====================================================

        VBox progressCard = createProgressCard();


        // =====================================================
        // MODULE TITLE
        // =====================================================

        Label moduleHeading = new Label(
                "Course Modules"
        );

        moduleHeading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        moduleHeading.setTextFill(
                Color.WHITE
        );


        Label moduleSubHeading = new Label(
                "Select a module to view its lessons."
        );

        moduleSubHeading.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        moduleSubHeading.setTextFill(
                Color.web("#9FB5A2")
        );


        // =====================================================
        // MODULE CONTAINER
        // =====================================================

        VBox modulesContainer = new VBox(15);

        modulesContainer.setPadding(
                new Insets(5, 0, 20, 0)
        );


        // =====================================================
        // MODULES
        // =====================================================

        VBox module1 = createModule(
                1,
                "Introduction to Poultry Farming",
                "Learn the basic concepts and requirements of poultry farming.",
                3,
                3,
                new String[]{
                        "Introduction to Poultry Farming",
                        "Types of Poultry Birds",
                        "Basic Poultry Farm Requirements"
                }
        );


        VBox module2 = createModule(
                2,
                "Poultry Farm Management",
                "Understand housing, feeding and daily farm management.",
                2,
                4,
                new String[]{
                        "Poultry Housing Management",
                        "Feeding Management",
                        "Water Management",
                        "Daily Farm Management"
                }
        );


        VBox module3 = createModule(
                3,
                "Bird Health and Disease Management",
                "Learn how to maintain bird health and prevent diseases.",
                1,
                4,
                new String[]{
                        "Common Poultry Diseases",
                        "Disease Prevention",
                        "Vaccination Management",
                        "Farm Hygiene"
                }
        );


        VBox module4 = createModule(
                4,
                "Poultry Production",
                "Learn about production, monitoring and improving farm performance.",
                0,
                3,
                new String[]{
                        "Egg Production",
                        "Broiler Production",
                        "Production Monitoring"
                }
        );


        VBox module5 = createModule(
                5,
                "Harvesting and Marketing",
                "Understand harvesting, selling and poultry market management.",
                0,
                3,
                new String[]{
                        "Harvesting Management",
                        "Poultry Market",
                        "Selling and Profit Management"
                }
        );


        modulesContainer.getChildren().addAll(
                module1,
                module2,
                module3,
                module4,
                module5
        );


        // =====================================================
        // ADD CONTENT
        // =====================================================

        mainContent.getChildren().addAll(
                backButton,
                courseHeader,
                progressCard,
                moduleHeading,
                moduleSubHeading,
                modulesContainer
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(
                mainContent
        );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
        "-fx-background: #080c0d;" +
        "-fx-background-color: #080c0d;"
);


        mainBorderPane.setCenter(
                scrollPane
        );


        // =====================================================
        // SCENE
        // =====================================================

        modulesPageScene = new Scene(
                mainBorderPane,
                1200,
                750
        );

        return modulesPageScene;
    }


    // =========================================================
    // PROGRESS CARD
    // =========================================================

    private VBox createProgressCard() {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(16)
        );

        card.setStyle(
                "-fx-background-color: #193522;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #31583A;" +
                "-fx-border-radius: 14;"
        );


        HBox progressHeader = new HBox();

        progressHeader.setAlignment(
                Pos.CENTER_LEFT
        );


        Label progressTitle = new Label(
                "Your Course Progress"
        );

        progressTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        progressTitle.setTextFill(
                Color.WHITE
        );


        HBox.setHgrow(
                progressTitle,
                Priority.ALWAYS
        );


        Label percentage = new Label(
                "40%"
        );

        percentage.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        percentage.setTextFill(
                Color.web("#7ED184")
        );


        progressHeader.getChildren().addAll(
                progressTitle,
                percentage
        );


        ProgressBar progressBar =
                new ProgressBar(0.40);

        progressBar.setPrefHeight(9);

        progressBar.setMaxWidth(
                Double.MAX_VALUE
        );

        progressBar.setStyle(
                "-fx-accent: #55A95D;"
        );


        Label progressInfo = new Label(
                "6 of 15 lessons completed"
        );

        progressInfo.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        progressInfo.setTextFill(
                Color.web("#AFC4B2")
        );


        card.getChildren().addAll(
                progressHeader,
                progressBar,
                progressInfo
        );

        return card;
    }


    // =========================================================
    // CREATE MODULE
    // =========================================================

    private VBox createModule(
            int moduleNumber,
            String moduleTitle,
            String moduleDescription,
            int completedLessons,
            int totalLessons,
            String[] lessons) {


        VBox moduleBox = new VBox();


        moduleBox.setStyle(
                "-fx-background-color: #193522;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #31583A;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 1;"
        );


        // =====================================================
        // MODULE HEADER
        // =====================================================

        HBox moduleHeader = new HBox(14);

        moduleHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        moduleHeader.setPadding(
                new Insets(16)
        );


        // -----------------------------------------------------
        // MODULE NUMBER CIRCLE
        // -----------------------------------------------------

        Circle circle = new Circle(
                21
        );

        circle.setFill(
                Color.web("#32683B")
        );


        Label number = new Label(
                String.valueOf(moduleNumber)
        );

        number.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        number.setTextFill(
                Color.WHITE
        );

        number.setAlignment(
                Pos.CENTER
        );

        number.setMinWidth(42);

        number.setMinHeight(42);


        // -----------------------------------------------------
        // MODULE INFORMATION
        // -----------------------------------------------------

        VBox moduleInfo = new VBox(5);


        Label title = new Label(
                "Module " + moduleNumber +
                "  •  " + moduleTitle
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        title.setTextFill(
                Color.WHITE
        );


        Label description = new Label(
                moduleDescription
        );

        description.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        description.setTextFill(
                Color.web("#AFC4B2")
        );

        description.setWrapText(true);


        Label lessonCount = new Label(
                completedLessons +
                " of " +
                totalLessons +
                " lessons completed"
        );

        lessonCount.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        lessonCount.setTextFill(
                Color.web("#75C77D")
        );


        moduleInfo.getChildren().addAll(
                title,
                description,
                lessonCount
        );


        HBox.setHgrow(
                moduleInfo,
                Priority.ALWAYS
        );


        // =====================================================
        // SHOW MORE BUTTON
        // =====================================================

        Button showMoreButton = new Button(
                "Show More  ▼"
        );

        showMoreButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        showMoreButton.setTextFill(
                Color.web("#CFE4D2")
        );

        showMoreButton.setStyle(
                "-fx-background-color: #285532;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );


        moduleHeader.getChildren().addAll(
                number,
                moduleInfo,
                showMoreButton
        );


        // =====================================================
        // LESSON CONTAINER
        // =====================================================

        VBox lessonsContainer = new VBox(9);

        lessonsContainer.setPadding(
                new Insets(
                        0,
                        18,
                        16,
                        75
                )
        );


        // Initially hidden
        lessonsContainer.setVisible(false);

        lessonsContainer.setManaged(false);


        // =====================================================
        // CREATE LESSONS
        // =====================================================

        for (int i = 0; i < lessons.length; i++) {

            boolean completed =
                    i < completedLessons;


            HBox lesson =
                    createLesson(
                            i + 1,
                            lessons[i],
                            completed
                    );


            lessonsContainer.getChildren().add(
                    lesson
            );
        }


        // =====================================================
        // SHOW MORE ACTION
        // =====================================================

        showMoreButton.setOnAction(e -> {

            boolean currentlyVisible =
                    lessonsContainer.isVisible();


            if (currentlyVisible) {

                lessonsContainer.setVisible(false);

                lessonsContainer.setManaged(false);

                showMoreButton.setText(
                        "Show More  ▼"
                );

            } else {

                lessonsContainer.setVisible(true);

                lessonsContainer.setManaged(true);

                showMoreButton.setText(
                        "Show Less  ▲"
                );
            }
        });


        moduleBox.getChildren().addAll(
                moduleHeader,
                lessonsContainer
        );


        return moduleBox;
    }


    // =========================================================
    // CREATE LESSON
    // =========================================================

    private HBox createLesson(
            int lessonNumber,
            String lessonTitle,
            boolean completed) {


        HBox lessonBox = new HBox(12);

        lessonBox.setAlignment(
                Pos.CENTER_LEFT
        );

        lessonBox.setPadding(
                new Insets(11, 12, 11, 12)
        );


        lessonBox.setStyle(
                "-fx-background-color: #223F2A;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #345A3C;" +
                "-fx-border-radius: 10;"
        );


        // =====================================================
        // LESSON STATUS CIRCLE
        // =====================================================

        Circle statusCircle =
                new Circle(9);


        if (completed) {

            statusCircle.setFill(
                    Color.web("#55B963")
            );

        } else {

            statusCircle.setFill(
                    Color.web("#536A58")
            );
        }


        // =====================================================
        // LESSON NUMBER
        // =====================================================

        Label number = new Label(
                "Lesson " + lessonNumber
        );

        number.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        number.setTextFill(
                Color.web("#82C989")
        );


        // =====================================================
        // LESSON TITLE
        // =====================================================

        Label title = new Label(
                lessonTitle
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        title.setTextFill(
                Color.WHITE
        );

        title.setWrapText(true);


        HBox.setHgrow(
                title,
                Priority.ALWAYS
        );


        // =====================================================
        // STATUS
        // =====================================================

        Label status = new Label();

        if (completed) {

            status.setText(
                    "Completed"
            );

            status.setTextFill(
                    Color.web("#79D181")
            );

        } else {

            status.setText(
                    "Start Lesson"
            );

            status.setTextFill(
                    Color.web("#AFC4B2")
            );


        }


        status.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );


        lessonBox.getChildren().addAll(
                statusCircle,
                number,
                title,
                status
        );


        // =====================================================
        // LESSON CLICK
        // =====================================================

        lessonBox.setOnMouseClicked(e -> {

            openLessonContent(
                    lessonNumber,
                    lessonTitle
            );

        });


        // =====================================================
        // HOVER EFFECT
        // =====================================================

        lessonBox.setOnMouseEntered(e -> {

            lessonBox.setStyle(
                    "-fx-background-color: #2B5134;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: #63A86B;" +
                    "-fx-border-radius: 10;" +
                    "-fx-cursor: hand;"
            );
        });


        lessonBox.setOnMouseExited(e -> {

            lessonBox.setStyle(
                    "-fx-background-color: #223F2A;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: #345A3C;" +
                    "-fx-border-radius: 10;"
            );
        });


        return lessonBox;
    }


    // =========================================================
    // LESSON CONTENT
    // =========================================================

    private void openLessonContent(
            int lessonNumber,
            String lessonTitle) {


        BorderPane lessonPane =
                new BorderPane();


       lessonPane.setStyle(
        "-fx-background-color: #080c0d;"
);


        // =====================================================
        // TOP
        // =====================================================

        VBox lessonTop =
                new VBox(5);

        lessonTop.setPadding(
                new Insets(20, 35, 15, 35)
        );


        Label courseLabel =
                new Label(
                        courseTitle
                );

        courseLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        courseLabel.setTextFill(
                Color.web("#78C47E")
        );


        Label lessonHeading =
                new Label(
                        "Lesson " +
                        lessonNumber +
                        ": " +
                        lessonTitle
                );

        lessonHeading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        lessonHeading.setTextFill(
                Color.WHITE
        );


        lessonTop.getChildren().addAll(
                courseLabel,
                lessonHeading
        );


        lessonPane.setTop(
                lessonTop
        );


        // =====================================================
        // LESSON CONTENT
        // =====================================================

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        25,
                        50,
                        35,
                        50
                )
        );


        Label contentTitle =
                new Label(
                        lessonTitle
                );

        contentTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        contentTitle.setTextFill(
                Color.WHITE
        );


        Label lessonText =
                new Label(
                        "Welcome to this lesson.\n\n" +

                        "This section will contain the complete "
                        + "learning content for the selected lesson.\n\n"

                        + "You can later load the actual lesson "
                        + "content from Firestore, including text, "
                        + "images, videos and other learning material."
                );

        lessonText.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        lessonText.setTextFill(
                Color.web("#C7D8C9")
        );

        lessonText.setWrapText(true);

        lessonText.setLineSpacing(
                5
        );


        Button backButton =
                new Button(
                        "← Back to Modules"
                );

        backButton.setPrefHeight(38);

        backButton.setPrefWidth(170);

        backButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        backButton.setTextFill(
                Color.WHITE
        );

        backButton.setStyle(
                "-fx-background-color: #32683B;" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );


        backButton.setOnAction(e -> {

    ModulesPage modulesPage =
            new ModulesPage(courseTitle);

    ((Stage) lessonPane.getScene().getWindow()).setScene(
            modulesPage.getModulesPageScene()
    );

});


        content.getChildren().addAll(
                contentTitle,
                lessonText,
                backButton
        );


        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

       scrollPane.setStyle(
        "-fx-background: #080c0d;" +
        "-fx-background-color: #080c0d;"
);


        lessonPane.setCenter(
                scrollPane
        );


        // =====================================================
        // FOOTER
        // =====================================================

        lessonPane.setBottom(
                new Footer().createFooter()
        );


        // =====================================================
        // CHANGE ROOT
        // =====================================================

        mainBorderPane.getScene().setRoot(
                lessonPane
        );
    }
}
