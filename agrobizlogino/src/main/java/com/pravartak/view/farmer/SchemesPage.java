

package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SchemesPage {

    private static Scene schemesScene;

    public static Scene getSchemesPage() {

        // MAIN BORDER PANE
        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #050b0a;"
        );

        // NAVBAR
        borderPane.setTop(
                new NavBar().createNavbar("Schemes")
        );

        // FOOTER
        borderPane.setBottom(
                new Footer().createFooter()
        );

        // MAIN VBOX
        VBox mainVBox = new VBox(16);

        mainVBox.setPadding(
                new Insets(25, 35, 25, 35)
        );

        mainVBox.setAlignment(
                Pos.TOP_LEFT
        );

        mainVBox.setStyle(
                "-fx-background-color: #050b0a;"
        );

        // MAIN TITLE
        Label pageTitle = new Label(
                "Government & Industry Schemes"
        );

        pageTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        26
                )
        );

        pageTitle.setTextFill(
                Color.WHITE
        );

        // DESCRIPTION
        Label pageDescription = new Label(
                "Explore available programs, subsidies, and insurance "
                        + "schemes designed to support sustainable agriculture "
                        + "and animal husbandry. Discover opportunities to "
                        + "enhance your farming operations."
        );

        pageDescription.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        pageDescription.setTextFill(
                Color.rgb(145, 160, 153)
        );

        pageDescription.setWrapText(true);

        pageDescription.setMaxWidth(900);

        // CATEGORY BUTTONS
        HBox categoryButtons =
                createCategoryButtons();

        // SCHEME CARDS
        HBox schemeCards =
                createSchemeCards();

        // ADD CONTENT
        mainVBox.getChildren().addAll(
                pageTitle,
                pageDescription,
                categoryButtons,
                schemeCards
        );

        // SCROLL PANE
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
                "-fx-background-color: #050b0a;"
                        + "-fx-background: #050b0a;"
                        + "-fx-control-inner-background: #050b0a;"
        );

        borderPane.setCenter(
                scrollPane
        );

        // SCENE
        Scene scene = new Scene(
                borderPane,
                1368,
                768
        );

        schemesScene = scene;

        return scene;
    }

    // =========================================================
    // CATEGORY BUTTONS
    // =========================================================

    private static HBox createCategoryButtons() {

        HBox categoryBox =
                new HBox();

        categoryBox.setSpacing(10);

        categoryBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Button allSchemesButton =
                createCategoryButton(
                        "All Schemes",
                        true
                );

        Button animalButton =
                createCategoryButton(
                        "Animal Husbandry",
                        true
                );

        Button equipmentButton =
                createCategoryButton(
                        "Equipment Subsidy",
                        true
                );

        Button insuranceButton =
                createCategoryButton(
                        "Livestock Insurance",
                        true
                );

        allSchemesButton.setOnAction(
                event -> showMessage(
                        "All Schemes",
                        "Showing all available schemes."
                )
        );

        animalButton.setOnAction(
                event -> showMessage(
                        "Animal Husbandry",
                        "Animal husbandry schemes selected."
                )
        );

        equipmentButton.setOnAction(
                event -> showMessage(
                        "Equipment Subsidy",
                        "Equipment subsidy schemes selected."
                )
        );

        insuranceButton.setOnAction(
                event -> showMessage(
                        "Livestock Insurance",
                        "Livestock insurance schemes selected."
                )
        );

        categoryBox.getChildren().addAll(
                allSchemesButton,
                animalButton,
                equipmentButton,
                insuranceButton
        );

        return categoryBox;
    }

    // =========================================================
    // SCHEME CARDS
    // =========================================================

    private static HBox createSchemeCards() {

        HBox schemeCards =
                new HBox();

        schemeCards.setSpacing(18);

        schemeCards.setAlignment(
                Pos.TOP_LEFT
        );

        // CARD 1
        VBox livestockCard =
                createSchemeCard(
                        "🐄",
                        "National Livestock\nMission (NLM)",
                        "Government support for livestock "
                                + "entrepreneurship, breed improvement "
                                + "and animal productivity.",
                        "Eligibility",
                        "• Individual farmers\n"
                                + "• Farmer Producer Organisations\n"
                                + "• Eligible livestock entrepreneurs",
                        "Check Eligibility",
                        true
                );

        // CARD 2
        VBox machineryCard =
                createSchemeCard(
                        "⚙",
                        "Sub-Mission on\nAgricultural\nMechanization (SMAM)",
                        "Financial assistance for agricultural "
                                + "machinery and modern farm equipment.",
                        "Eligibility",
                        "• Farmers\n"
                                + "• Farmer groups\n"
                                + "• Registered agricultural organisations",
                        "Apply Now",
                        false
                );

        // CARD 3
        VBox cropInsuranceCard =
                createSchemeCard(
                        "🛡",
                        "Pradhan Mantri\nFasal Bima Yojana",
                        "Crop insurance support designed "
                                + "to protect farmers against eligible "
                                + "crop losses and risks.",
                        "Eligibility",
                        "• Eligible farmers\n"
                                + "• Farmers growing notified crops\n"
                                + "• Applicable geographical areas",
                        "Check Eligibility",
                        true
                );

        schemeCards.getChildren().addAll(
                livestockCard,
                machineryCard,
                cropInsuranceCard
        );

        return schemeCards;
    }

    // =========================================================
    // CREATE SCHEME CARD
    // =========================================================

    private static VBox createSchemeCard(
            String icon,
            String schemeName,
            String description,
            String eligibilityTitle,
            String eligibilityDetails,
            String actionText,
            boolean greenButton) {

        VBox schemeCard =
                new VBox();

        schemeCard.setSpacing(10);

        schemeCard.setPadding(
                new Insets(14)
        );

        schemeCard.setPrefWidth(
                350
        );

        schemeCard.setMinWidth(
                300
        );

        schemeCard.setMaxWidth(
                350
        );

        // DARK GREEN CARD
        schemeCard.setStyle(
                "-fx-background-color: #0b1714;"
                        + "-fx-background-radius: 12;"
                        + "-fx-border-color: #19352b;"
                        + "-fx-border-radius: 12;"
                        + "-fx-border-width: 1;"
        );

        // =====================================================
        // TOP ROW
        // =====================================================

        HBox topRow =
                new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // ICON
        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefSize(
                42,
                38
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        iconLabel.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        16,
                                        55,
                                        39
                                ),
                                new CornerRadii(6),
                                Insets.EMPTY
                        )
                )
        );

        // SPACE
        Region badgeSpace =
                new Region();

        HBox.setHgrow(
                badgeSpace,
                Priority.ALWAYS
        );

        // BADGE
        Label schemeBadge =
                new Label(
                        getBadgeText(schemeName)
                );

        schemeBadge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        8
                )
        );

        schemeBadge.setTextFill(
                Color.rgb(
                        130,
                        210,
                        150
                )
        );

        schemeBadge.setPadding(
                new Insets(
                        4,
                        8,
                        4,
                        8
                )
        );

        schemeBadge.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        12,
                                        43,
                                        30
                                ),
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );

        topRow.getChildren().addAll(
                iconLabel,
                badgeSpace,
                schemeBadge
        );

        // =====================================================
        // SCHEME NAME
        // =====================================================

        Label nameLabel =
                new Label(
                        schemeName
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        nameLabel.setTextFill(
                Color.WHITE
        );

        nameLabel.setWrapText(
                true
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        descriptionLabel.setTextFill(
                Color.rgb(
                        145,
                        160,
                        153
                )
        );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setMinHeight(
                62
        );

        // =====================================================
        // ELIGIBILITY BOX
        // =====================================================

        VBox eligibilityBox =
                new VBox();

        eligibilityBox.setSpacing(
                6
        );

        eligibilityBox.setPadding(
                new Insets(10)
        );

        eligibilityBox.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(
                                        8,
                                        28,
                                        22
                                ),
                                new CornerRadii(7),
                                Insets.EMPTY
                        )
                )
        );

        // ELIGIBILITY HEADING
        Label eligibilityHeading =
                new Label(
                        "ⓘ  " + eligibilityTitle
                );

        eligibilityHeading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        eligibilityHeading.setTextFill(
                Color.rgb(
                        83,
                        215,
                        74
                )
        );

        // ELIGIBILITY DETAILS
        Label eligibilityLabel =
                new Label(
                        eligibilityDetails
                );

        eligibilityLabel.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );

        eligibilityLabel.setTextFill(
                Color.rgb(
                        145,
                        160,
                        153
                )
        );

        eligibilityLabel.setWrapText(
                true
        );

        eligibilityBox.getChildren().addAll(
                eligibilityHeading,
                eligibilityLabel
        );

        // =====================================================
        // ACTION BUTTON
        // =====================================================

        Button actionButton =
                new Button(
                        actionText
                );

        actionButton.setPrefHeight(
                34
        );

        actionButton.setMaxWidth(
                Double.MAX_VALUE
        );

        actionButton.setCursor(
                javafx.scene.Cursor.HAND
        );

        actionButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        // =====================================================
        // GREEN BUTTON
        // =====================================================

        if (greenButton) {

            actionButton.setTextFill(
                    Color.BLACK
            );

            actionButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.rgb(
                                            83,
                                            215,
                                            74
                                    ),
                                    new CornerRadii(5),
                                    Insets.EMPTY
                            )
                    )
            );

            actionButton.setStyle(
                    "-fx-background-color: #53d74a;"
                            + "-fx-text-fill: #06100b;"
                            + "-fx-background-radius: 5;"
                            + "-fx-cursor: hand;"
            );

        }

        // =====================================================
        // OUTLINE BUTTON
        // =====================================================

        else {

            actionButton.setTextFill(
                    Color.rgb(
                            83,
                            215,
                            74
                    )
            );

            actionButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.TRANSPARENT,
                                    new CornerRadii(5),
                                    Insets.EMPTY
                            )
                    )
            );

            actionButton.setBorder(
                    new Border(
                            new BorderStroke(
                                    Color.rgb(
                                            55,
                                            180,
                                            80
                                    ),
                                    BorderStrokeStyle.SOLID,
                                    new CornerRadii(5),
                                    new BorderWidths(1)
                            )
                    )
            );
        }

        // ACTION
        actionButton.setOnAction(
                event -> showMessage(
                        schemeName.replace(
                                "\n",
                                " "
                        ),
                        "Scheme details and application "
                                + "page will be connected later."
                )
        );

        // ADD CARD CONTENT
        schemeCard.getChildren().addAll(
                topRow,
                nameLabel,
                descriptionLabel,
                eligibilityBox,
                actionButton
        );

        return schemeCard;
    }

    // =========================================================
    // BADGE
    // =========================================================

    private static String getBadgeText(
            String schemeName) {

        if (schemeName.contains(
                "Mechanization")) {

            return "RECOMMENDED";
        }

        return "CENTRAL GOVT";
    }

    // =========================================================
    // CATEGORY BUTTON
    // =========================================================

    private static Button createCategoryButton(
            String buttonText,
            boolean selected) {

        Button categoryButton =
                new Button(
                        buttonText
                );

        categoryButton.setPrefHeight(
                30
        );

        categoryButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        categoryButton.setCursor(
                javafx.scene.Cursor.HAND
        );

        // SELECTED BUTTON
        if (selected) {

            categoryButton.setTextFill(
                    Color.BLACK
            );

            categoryButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.rgb(
                                            83,
                                            215,
                                            74
                                    ),
                                    new CornerRadii(15),
                                    Insets.EMPTY
                            )
                    )
            );

        }

        // NORMAL BUTTON
        else {

            categoryButton.setTextFill(
                    Color.rgb(
                            145,
                            160,
                            153
                    )
            );

            categoryButton.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.rgb(
                                            8,
                                            23,
                                            19
                                    ),
                                    new CornerRadii(15),
                                    Insets.EMPTY
                            )
                    )
            );

            categoryButton.setBorder(
                    new Border(
                            new BorderStroke(
                                    Color.rgb(
                                            28,
                                            55,
                                            45
                                    ),
                                    BorderStrokeStyle.SOLID,
                                    new CornerRadii(15),
                                    new BorderWidths(1)
                            )
                    )
            );
        }

        return categoryButton;
    }

    // =========================================================
    // SHOW MESSAGE
    // =========================================================

    private static void showMessage(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

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

    // =========================================================
    // BACK TO SCHEMES
    // =========================================================

    public void backtoSchemes() {

        LoginPage.mainStage.setScene(
                schemesScene
        );
    }
}