// package com.pravartak.view.farmer;

// import com.pravartak.view.farmer.common.Footer;
// import com.pravartak.view.farmer.common.NavBar;
// import com.pravartak.view.login.LoginPage;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.scene.text.Text;

// public class LearningPage {
//     private Scene learningpagScene;
//     public Scene get_learning_pageScene(){

//         BorderPane borderPane = new BorderPane();
//         borderPane.setStyle("-fx-background-color: #1a2f1c;");
//         borderPane.setStyle("-fx-background-color: #1a2f1c;");
//         borderPane.setTop(new NavBar().createNavbar("Learning"));
//         borderPane.setBottom(new Footer().createFooter());
       
       
//         // poultry
//         VBox poultryBox = new VBox(8);
//         poultryBox.setPadding(new Insets(8));
//         poultryBox.setPrefWidth(230);
//         //poultryBox.setPrefHeight(205);
//         poultryBox.setAlignment(Pos.TOP_LEFT);
//         poultryBox.setStyle("-fx-background-color: darkgreen;" + "-fx-background-radius: 12;" + "-fx-border-color: #eff6f3;" + "-fx-border-radius: 12;");

//         Image poultryImage = new Image(getClass().getResource("/poltry.png").toExternalForm());

//         ImageView poultryImageView = new ImageView(poultryImage);
//         poultryImageView.setFitWidth(214);
//         poultryImageView.setFitHeight(95);

//         Label poultryTitle = new Label("Poultry Farming");
//         poultryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

//         Label poultryDescription = new Label("Advanced systems for optimal bird health.");
//         poultryDescription.setFont(Font.font("Arial", 11));
//         poultryDescription.setWrapText(true);
//         poultryDescription.setTextFill(Color.GRAY);

//         poultryBox.getChildren().addAll(poultryImageView, poultryTitle, poultryDescription);

//         VBox mainVBox = new VBox(28);
//         mainVBox.setPadding(new Insets(18, 18, 30, 18));
//         //mainVBox.setFillWidth(true);
//         mainVBox.getChildren().addAll(poultryBox);
//         borderPane.setCenter(mainVBox);

//         Scene scene = new Scene(borderPane);
//         learningpagScene = scene;
//         return scene;
//     }

    
//     }    

package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LearningPage {

    private Scene learningpagScene;

    public Scene get_learning_pageScene() {

        // =========================
        // MAIN BORDER PANE
        // =========================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #102417;"
        );

        // Navbar
        borderPane.setTop(
                new NavBar().createNavbar("Learning")
        );

        // Footer
        borderPane.setBottom(
                new Footer().createFooter()
        );

        // =========================
        // MAIN CONTENT
        // =========================

        VBox mainContent = new VBox(22);

        mainContent.setPadding(
                new Insets(28, 40, 35, 40)
        );

        // =========================
        // PAGE HEADER
        // =========================

        VBox headingBox = new VBox(6);

        Label title = new Label("My Learning");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "Continue learning from the courses you have enrolled in."
        );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web("#AFC4B2")
        );

        headingBox.getChildren().addAll(
                title,
                subtitle
        );

        // =========================
        // COURSE SECTION TITLE
        // =========================

        HBox sectionHeader = new HBox();

        sectionHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        Label enrolledTitle = new Label(
                "Enrolled Courses"
        );

        enrolledTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        enrolledTitle.setTextFill(
                Color.WHITE
        );

        sectionHeader.getChildren().add(
                enrolledTitle
        );

        // =========================
        // COURSE CONTAINER
        // =========================

        FlowPane courseContainer = new FlowPane();

        courseContainer.setHgap(22);
        courseContainer.setVgap(22);

        courseContainer.setPrefWrapLength(1000);

        // =========================
        // COURSE 1
        // =========================

        VBox poultryCourse = createCourseCard(
                "Poultry Farming",
                "Advanced systems for optimal bird health.",
                "/poltry.png",
                0.65,
                "65%"
        );

        // =========================
        // COURSE 2
        // =========================

        VBox cropCourse = createCourseCard(
                "Modern Crop Farming",
                "Learn modern techniques for better crop production.",
                "/crop.png",
                0.40,
                "40%"
        );

        // =========================
        // COURSE 3
        // =========================

        VBox organicCourse = createCourseCard(
                "Organic Farming",
                "Learn sustainable and chemical-free farming methods.",
                "/organic.png",
                0.80,
                "80%"
        );

        courseContainer.getChildren().addAll(
                poultryCourse,
                cropCourse,
                organicCourse
        );

        // =========================
        // ADD EVERYTHING
        // =========================

        mainContent.getChildren().addAll(
                headingBox,
                sectionHeader,
                courseContainer
        );
        mainContent.setStyle("-fx-background-color: #080c0d;");


        borderPane.setCenter(mainContent);

        // =========================
        // SCENE
        // =========================

        Scene scene = new Scene(
                borderPane,
                1200,
                750
        );

        learningpagScene = scene;

        return scene;
    }

    // =========================================================
    // CREATE COURSE CARD
    // =========================================================

    private VBox createCourseCard(
            String courseTitle,
            String description,
            String imagePath,
            double progress,
            String progressText) {

        VBox card = new VBox(12);

        card.setPrefWidth(270);
        card.setPrefHeight(330);

        card.setPadding(
                new Insets(12)
        );

        card.setStyle(
                "-fx-background-color: #1B3A24;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #365D3E;" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1;"
        );

        // =========================
        // COURSE IMAGE
        // =========================

        ImageView imageView;

        try {

            Image image = new Image(
                    getClass()
                            .getResource(imagePath)
                            .toExternalForm()
            );

            imageView = new ImageView(image);

            imageView.setFitWidth(246);
            imageView.setFitHeight(120);

            imageView.setPreserveRatio(false);

        } catch (Exception e) {

            imageView = new ImageView();

            imageView.setFitWidth(246);
            imageView.setFitHeight(120);
        }

        // =========================
        // COURSE TITLE
        // =========================

        Label title = new Label(
                courseTitle
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

        // =========================
        // DESCRIPTION
        // =========================

        Label desc = new Label(
                description
        );

        desc.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        desc.setTextFill(
                Color.web("#B7C9BA")
        );

        desc.setWrapText(true);

        desc.setPrefHeight(40);

        // =========================
        // PROGRESS HEADER
        // =========================

        HBox progressHeader = new HBox();

        progressHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        Label progressLabel = new Label(
                "Course Progress"
        );

        progressLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        progressLabel.setTextFill(
                Color.web("#D8E6D9")
        );

        HBox.setHgrow(
                progressLabel,
                javafx.scene.layout.Priority.ALWAYS
        );

        Label percentage = new Label(
                progressText
        );

        percentage.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        percentage.setTextFill(
                Color.web("#8BCF8E")
        );

        progressHeader.getChildren().addAll(
                progressLabel,
                percentage
        );

        // =========================
        // PROGRESS BAR
        // =========================

        ProgressBar progressBar = new ProgressBar(
                progress
        );

        progressBar.setPrefWidth(
                246
        );

        progressBar.setPrefHeight(
                8
        );

        progressBar.setStyle(
                "-fx-accent: #5FAF68;"
        );

        // =========================
        // CONTINUE BUTTON
        // =========================

        Button continueButton = new Button(
                "Continue Learning"
        );

        continueButton.setPrefWidth(
                246
        );

        continueButton.setPrefHeight(
                38
        );

        continueButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        continueButton.setTextFill(
                Color.WHITE
        );

        continueButton.setStyle(
                "-fx-background-color: #3F8F4A;" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        continueButton.setOnMouseEntered(e ->
                continueButton.setStyle(
                        "-fx-background-color: #55A85E;" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        continueButton.setOnMouseExited(e ->
                continueButton.setStyle(
                        "-fx-background-color: #3F8F4A;" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );
       continueButton.setOnAction(e -> {

    ModulesPage modulesPage =
            new ModulesPage(courseTitle);

        ((Stage) continueButton.getScene().getWindow()).setScene(
            modulesPage.getModulesPageScene()
    );

});
        // =========================
        // ADD TO CARD
        // =========================

        card.getChildren().addAll(
                imageView,
                title,
                desc,
                progressHeader,
                progressBar,
                continueButton
        );

        return card;
    }
}