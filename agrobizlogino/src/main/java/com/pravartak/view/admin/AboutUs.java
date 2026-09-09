package com.pravartak.view.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AboutUs {

    public ScrollPane getAboutUsPage() {

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        VBox main = new VBox(25);
        main.setPadding(new Insets(35, 45, 40, 45));
        main.setStyle("-fx-background-color:#080C0D;");

        // =========================================================
        // PAGE TITLE
        // =========================================================

        Label title = new Label("About Us");

        title.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle = new Label(
                "The people behind our journey"
        );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:15px;"
        );

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        // =========================================================
        // SHASHI SIR SECTION
        // =========================================================

        HBox sirSection = new HBox(35);
        sirSection.setPadding(new Insets(25));
        sirSection.setAlignment(Pos.CENTER_LEFT);

        sirSection.setStyle(
                "-fx-background-color:#111718;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#26302A;" +
                "-fx-border-radius:15;"
        );

        // ---------------------------------------------------------
        // PHOTO
        // ---------------------------------------------------------

        ImageView sirImage = new ImageView();

        try {

            Image image = new Image(
                    getClass()
                            .getResource("/shashi_sir.jpg")
                            .toExternalForm()
            );

            sirImage.setImage(image);

        } catch (Exception e) {

            System.out.println(
                    "Shashi Sir image not found."
            );
        }

        sirImage.setFitWidth(170);
        sirImage.setFitHeight(170);
        sirImage.setPreserveRatio(true);

        Circle clip = new Circle(85, 85, 85);
        sirImage.setClip(clip);

        VBox imageBox = new VBox(sirImage);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPrefWidth(190);

        // ---------------------------------------------------------
        // TEXT
        // ---------------------------------------------------------

        Label thanks = new Label(
                "Thank You, Shashi Sir !"
        );

        thanks.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:25px;" +
                "-fx-font-weight:bold;"
        );

        Label core2web = new Label(
                "Core2Web"
        );

        core2web.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label sirDescription = new Label(
                "We sincerely thank Shashi Sir and Core2Web " +
                "for providing us with valuable knowledge, " +
                "guidance, motivation and continuous support " +
                "throughout our learning and project journey."
        );

        sirDescription.setWrapText(true);

        sirDescription.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:15px;"
        );

        VBox sirInfo = new VBox(
                10,
                thanks,
                core2web,
                sirDescription
        );

        sirInfo.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(
                sirInfo,
                Priority.ALWAYS
        );

        sirSection.getChildren().addAll(
                imageBox,
                sirInfo
        );

        // =========================================================
        // PROJECT DESCRIPTION
        // =========================================================

        VBox projectCard = createCard(
                "🌱  Project Description",

                "Our project is an agriculture-focused digital " +
                "platform designed to connect Farmers, Buyers " +
                "and Administrators through a single system. " +
                "The platform provides features such as " +
                "and main Facous on teaching to farmers"+
                "marketplace, product management, orders, " +
                "government schemes, learning, community " +
                "interaction and other useful services."
        );

        // =========================================================
        // TEAM INFORMATION
        // =========================================================

        VBox teamCard = createPeopleCard(
                "👥  Team Information",

                "Gaurav Kekan\n"+
                "Jayant Hiwale\n"+
                "Vivek Bhosale\n"+
                "Akshay Chobe"
        );

        // =========================================================
        // INSTRUCTORS
        // =========================================================

        VBox instructorCard = createPeopleCard(
                "👨‍🏫  Thanks to Instructors",

                "Sachin Sir\n"+
                "Pramod Sir\n"+
                "Akshay Sir"
        );

        // =========================================================
        // SUPER MENTORS
        // =========================================================

        VBox superMentorCard = createPeopleCard(
                "⭐  Thanks to Super Mentors",

                "Shiv Sir\n"+
                "Subodh Sir"
        );

        // =========================================================
        // MENTORS & TEAM LEADS
        // =========================================================

        VBox mentorCard = createPeopleCard(
                "🤝  Thanks to Mentors & Team Leads",

            "Sumit Dada \n"+
                "Mansi di \n"+
                "Dhanashri di \n"+
                "Mauli Dada \n"+
                "Harshvardhan Dada"
        );
        

        // =========================================================
        // ADD ALL CONTENT
        // =========================================================

        main.getChildren().addAll(
                titleBox,
                sirSection,
                projectCard,
                teamCard,
                instructorCard,
                superMentorCard,
                mentorCard
        );

        // =========================================================
        // SCROLL PANE
        // =========================================================

        ScrollPane scrollPane = new ScrollPane(main);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background:#080C0D;" +
                "-fx-background-color:#080C0D;" +
                "-fx-border-color:transparent;"
        );

        return scrollPane;
    }

    // =============================================================
    // NORMAL CARD
    // =============================================================

    private VBox createCard(
            String headingText,
            String descriptionText) {

        Label heading = new Label(headingText);

        heading.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        Label description = new Label(
                descriptionText
        );

        description.setWrapText(true);

        description.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:15px;"
        );

        VBox card = new VBox(
                12,
                heading,
                description
        );

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color:#111718;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#26302A;" +
                "-fx-border-radius:15;"
        );

        return card;
    }

    // =============================================================
    // PEOPLE CARD
    // =============================================================

    private VBox createPeopleCard(
            String headingText,
            String... people) {

        Label heading = new Label(
                headingText
        );

        heading.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        HBox peopleBox = new HBox(15);

        peopleBox.setAlignment(
                Pos.CENTER_LEFT
        );

        for (String person : people) {

            Label personLabel = new Label(
                    person
            );

           personLabel.setStyle(
        "-fx-text-fill:#EEEEEE;" +
        "-fx-font-size:15px;" +
        "-fx-font-weight:bold;"
);

            peopleBox.getChildren().add(
                    personLabel
            );
        }

        VBox card = new VBox(
                15,
                heading,
                peopleBox
        );

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color:#111718;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#26302A;" +
                "-fx-border-radius:15;"
        );

        return card;
    }
}