
package com.pravartak.view.farmer;

import java.util.List;

import com.pravartak.controller.admincontroller.SchemeController;
import com.pravartak.model.admin.Scheme;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Border;
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

    // =========================================================
    // SCENE
    // =========================================================

    private static Scene schemesScene;

    // =========================================================
    // SCHEME CONTROLLER
    // =========================================================

    private static final SchemeController schemeController =
            new SchemeController();

    // =========================================================
    // GET SCHEMES PAGE
    // =========================================================

    public static Scene getSchemesPage() {

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color:#050b0a;"
        );

        // =====================================================
        // NAVBAR
        // =====================================================

        borderPane.setTop(
                new NavBar().createNavbar("Schemes")
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

        VBox mainVBox = new VBox(16);

        mainVBox.setPadding(
                new Insets(
                        25,
                        35,
                        25,
                        35
                )
        );

        mainVBox.setAlignment(
                Pos.TOP_LEFT
        );

        mainVBox.setFillWidth(true);

        mainVBox.setStyle(
                "-fx-background-color:#050b0a;"
        );

        // =====================================================
        // PAGE TITLE
        // =====================================================

        Label pageTitle =
                new Label(
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

        // =====================================================
        // PAGE DESCRIPTION
        // =====================================================

        Label pageDescription =
                new Label(
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
                Color.rgb(
                        145,
                        160,
                        153
                )
        );

        pageDescription.setWrapText(
                true
        );

        pageDescription.setMaxWidth(
                950
        );

        // =====================================================
        // CATEGORY BUTTONS
        // =====================================================

        HBox categoryButtons =
                createCategoryButtons();

        // =====================================================
        // SCHEME CARDS
        // =====================================================

        VBox schemeCards =
                createSchemeCards();

        // =====================================================
        // ADD CONTENT
        // =====================================================

        mainVBox.getChildren().addAll(
                pageTitle,
                pageDescription,
                categoryButtons,
                schemeCards
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

        scrollPane.setFitToHeight(
                false
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setStyle(
                "-fx-background-color:#050b0a;" +
                "-fx-background:#050b0a;" +
                "-fx-control-inner-background:#050b0a;" +
                "-fx-border-color:transparent;"
        );

        // =====================================================
        // SET CENTER
        // =====================================================

        borderPane.setCenter(
                scrollPane
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        borderPane,
                        1368,
                        768
                );

        schemesScene =
                scene;

        return scene;
    }

    // =========================================================
    // CATEGORY BUTTONS
    // =========================================================

    private static HBox createCategoryButtons() {

        HBox categoryBox =
                new HBox(10);

        categoryBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // ALL
        // =====================================================

        Button allSchemesButton =
                createCategoryButton(
                        "All Schemes",
                        true
                );

        // =====================================================
        // ANIMAL
        // =====================================================

        Button animalButton =
                createCategoryButton(
                        "Animal Husbandry",
                        false
                );

        // =====================================================
        // EQUIPMENT
        // =====================================================

        Button equipmentButton =
                createCategoryButton(
                        "Equipment Subsidy",
                        false
                );

        // =====================================================
        // INSURANCE
        // =====================================================

        Button insuranceButton =
                createCategoryButton(
                        "Livestock Insurance",
                        false
                );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        allSchemesButton.setOnAction(
                event -> showMessage(
                        "All Schemes",
                        "Showing all available schemes."
                )
        );

        animalButton.setOnAction(
                event -> showMessage(
                        "Animal Husbandry",
                        "Category filtering will be connected after "
                        + "adding a category field to Scheme."
                )
        );

        equipmentButton.setOnAction(
                event -> showMessage(
                        "Equipment Subsidy",
                        "Category filtering will be connected after "
                        + "adding a category field to Scheme."
                )
        );

        insuranceButton.setOnAction(
                event -> showMessage(
                        "Livestock Insurance",
                        "Category filtering will be connected after "
                        + "adding a category field to Scheme."
                )
        );

        // =====================================================
        // ADD BUTTONS
        // =====================================================

        categoryBox.getChildren().addAll(
                allSchemesButton,
                animalButton,
                equipmentButton,
                insuranceButton
        );

        return categoryBox;
    }

    // =========================================================
    // CREATE SCHEME CARDS
    // =========================================================

    private static VBox createSchemeCards() {

        VBox schemeCards =
                new VBox(18);

        schemeCards.setAlignment(
                Pos.TOP_LEFT
        );

        schemeCards.setFillWidth(
                true
        );

        // =====================================================
        // LOAD FROM FIREBASE
        // =====================================================

        List<Scheme> schemes;

        try {

            schemes =
                    schemeController.getAllSchemes();

        } catch (Exception e) {

            e.printStackTrace();

            schemes =
                    null;
        }

        // =====================================================
        // NULL / EMPTY
        // =====================================================

        if (schemes == null ||
                schemes.isEmpty()) {

            schemeCards.getChildren().add(
                    createNoSchemeView()
            );

            return schemeCards;
        }

        // =====================================================
        // ADD SCHEMES
        // =====================================================

        int activeSchemeCount = 0;

        for (Scheme scheme : schemes) {

            if (scheme == null) {
                continue;
            }

            // -------------------------------------------------
            // ONLY ACTIVE SCHEMES
            // -------------------------------------------------

            if (!scheme.isActive()) {
                continue;
            }

            VBox card =
                    createSchemeCard(
                            scheme
                    );

            schemeCards.getChildren().add(
                    card
            );

            activeSchemeCount++;
        }

        // =====================================================
        // NO ACTIVE SCHEMES
        // =====================================================

        if (activeSchemeCount == 0) {

            schemeCards.getChildren().clear();

            schemeCards.getChildren().add(
                    createNoSchemeView()
            );
        }

        return schemeCards;
    }

    // =========================================================
    // NO SCHEME VIEW
    // =========================================================

    private static VBox createNoSchemeView() {

        VBox box =
                new VBox(10);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(50)
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        Label icon =
                new Label("🌱");

        icon.setFont(
                Font.font(
                        "Arial",
                        30
                )
        );

        Label title =
                new Label(
                        "No schemes available"
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

        Label message =
                new Label(
                        "Government schemes will appear here when they "
                        + "are published by the administrator."
                );

        message.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        message.setTextFill(
                Color.rgb(
                        145,
                        160,
                        153
                )
        );

        message.setWrapText(
                true
        );

        message.setAlignment(
                Pos.CENTER
        );

        box.getChildren().addAll(
                icon,
                title,
                message
        );

        return box;
    }

    // =========================================================
    // CREATE SCHEME CARD
    // =========================================================

    private static VBox createSchemeCard(
            Scheme scheme) {

        VBox schemeCard =
                new VBox(10);

        schemeCard.setPadding(
                new Insets(18)
        );

        schemeCard.setMaxWidth(
                Double.MAX_VALUE
        );

        schemeCard.setStyle(
                "-fx-background-color:#0b1714;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#19352b;" +
                "-fx-border-radius:12;" +
                "-fx-border-width:1;"
        );

        // =====================================================
        // TOP ROW
        // =====================================================

        HBox topRow =
                new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // ICON
        // =====================================================

        Label iconLabel =
                new Label("🌱");

        iconLabel.setPrefSize(
                45,
                42
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        20
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

        // =====================================================
        // SPACE
        // =====================================================

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        // =====================================================
        // BADGE
        // =====================================================

        Label badge =
                new Label(
                        "GOVERNMENT SCHEME"
                );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        8
                )
        );

        badge.setTextFill(
                Color.rgb(
                        130,
                        210,
                        150
                )
        );

        badge.setPadding(
                new Insets(
                        4,
                        8,
                        4,
                        8
                )
        );

        badge.setBackground(
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
                space,
                badge
        );

        // =====================================================
        // SCHEME NAME
        // =====================================================

        String schemeName =
                safeText(
                        scheme.getSchemeName(),
                        "Government Scheme"
                );

        Label nameLabel =
                new Label(
                        schemeName
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        nameLabel.setTextFill(
                Color.WHITE
        );

        nameLabel.setWrapText(
                true
        );

        // =====================================================
        // INFORMATION
        // =====================================================

        String information =
                safeText(
                        scheme.getInformation(),
                        "Information not available."
                );

        Label informationLabel =
                new Label(
                        information
                );

        informationLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        informationLabel.setTextFill(
                Color.rgb(
                        145,
                        160,
                        153
                )
        );

        informationLabel.setWrapText(
                true
        );

        informationLabel.setMaxWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // ELIGIBILITY BOX
        // =====================================================

        VBox eligibilityBox =
                new VBox(6);

        eligibilityBox.setPadding(
                new Insets(12)
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

        // =====================================================
        // ELIGIBILITY TITLE
        // =====================================================

        Label eligibilityHeading =
                new Label(
                        "ⓘ  Eligibility"
                );

        eligibilityHeading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        eligibilityHeading.setTextFill(
                Color.rgb(
                        83,
                        215,
                        74
                )
        );

        // =====================================================
        // ELIGIBILITY DETAILS
        // =====================================================

        String eligibility =
                safeText(
                        scheme.getEligibility(),
                        "Eligibility information not available."
                );

        Label eligibilityLabel =
                new Label(
                        eligibility
                );

        eligibilityLabel.setFont(
                Font.font(
                        "Arial",
                        11
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

        eligibilityLabel.setMaxWidth(
                Double.MAX_VALUE
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
                        "Check Eligibility"
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

        actionButton.setStyle(
                "-fx-background-color:#53d74a;" +
                "-fx-text-fill:#06100b;" +
                "-fx-background-radius:5;" +
                "-fx-cursor:hand;"
        );

        // =====================================================
        // ACTION
        // =====================================================

        actionButton.setOnAction(
                event -> {

                    showSchemeDetails(
                            scheme
                    );
                }
        );

        // =====================================================
        // ADD CONTENT
        // =====================================================

        schemeCard.getChildren().addAll(
                topRow,
                nameLabel,
                informationLabel,
                eligibilityBox,
                actionButton
        );

        // =====================================================
        // HOVER EFFECT
        // =====================================================

        schemeCard.setOnMouseEntered(
                event -> {

                    schemeCard.setStyle(
                            "-fx-background-color:#10221c;" +
                            "-fx-background-radius:12;" +
                            "-fx-border-color:#53d74a;" +
                            "-fx-border-radius:12;" +
                            "-fx-border-width:1;"
                    );
                }
        );

        schemeCard.setOnMouseExited(
                event -> {

                    schemeCard.setStyle(
                            "-fx-background-color:#0b1714;" +
                            "-fx-background-radius:12;" +
                            "-fx-border-color:#19352b;" +
                            "-fx-border-radius:12;" +
                            "-fx-border-width:1;"
                    );
                }
        );

        return schemeCard;
    }

    // =========================================================
    // SCHEME DETAILS
    // =========================================================

    private static void showSchemeDetails(
            Scheme scheme) {

        String schemeName =
                safeText(
                        scheme.getSchemeName(),
                        "Government Scheme"
                );

        String eligibility =
                safeText(
                        scheme.getEligibility(),
                        "Not available."
                );

        String information =
                safeText(
                        scheme.getInformation(),
                        "Not available."
                );

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                schemeName
        );

        alert.setHeaderText(
                schemeName
        );

        alert.setContentText(
                "ELIGIBILITY\n\n"
                + eligibility
                + "\n\n"
                + "SCHEME INFORMATION\n\n"
                + information
        );

        alert.showAndWait();
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

        // =====================================================
        // SELECTED
        // =====================================================

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

        // =====================================================
        // NORMAL
        // =====================================================

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
    // SAFE TEXT
    // =========================================================

    private static String safeText(
            String value,
            String defaultValue) {

        if (value == null ||
                value.trim().isEmpty()) {

            return defaultValue;
        }

        return value.trim();
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

        if (schemesScene != null) {

            LoginPage.mainStage.setScene(
                    schemesScene
            );
        }
    }
}