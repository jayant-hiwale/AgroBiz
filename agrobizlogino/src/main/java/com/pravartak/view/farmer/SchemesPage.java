package com.pravartak.view.farmer;

import java.awt.Desktop;
import java.net.URI;
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

    // =========================================================
    // SCENE
    // =========================================================

    private static Scene schemesScene;

    // =========================================================
    // CONTROLLER
    // =========================================================

    private static final SchemeController schemeController =
            new SchemeController();

    // =========================================================
    // GET SCHEMES PAGE
    // =========================================================

    public static Scene getSchemesPage() {

        BorderPane borderPane =
                new BorderPane();

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

        VBox mainVBox =
                new VBox(16);

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
        // TITLE
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
        // DESCRIPTION
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

        pageDescription.setWrapText(true);

        pageDescription.setMaxWidth(950);

        // =====================================================
        // SCHEME CARDS CONTAINER
        // =====================================================

        VBox schemeCards =
                new VBox(18);

        schemeCards.setAlignment(
                Pos.TOP_LEFT
        );

        schemeCards.setFillWidth(true);

        // =====================================================
        // CATEGORY BUTTONS
        // =====================================================

        HBox categoryButtons =
                createCategoryButtons(
                        schemeCards
                );

        // =====================================================
        // INITIAL LOAD
        // =====================================================

        loadSchemesByCategory(
                "ALL",
                schemeCards
        );

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
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                mainVBox
        );

        scrollPane.setFitToWidth(true);

        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color:#050b0a;" +
                "-fx-background:#050b0a;" +
                "-fx-control-inner-background:#050b0a;" +
                "-fx-border-color:transparent;"
        );

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

        schemesScene = scene;

        return scene;
    }

    // =========================================================
    // CATEGORY BUTTONS
    // =========================================================

    private static HBox createCategoryButtons(
            VBox schemeCards) {

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
        // ANIMAL HUSBANDRY
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
        // LIVESTOCK INSURANCE
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
                e -> {

                    setSelectedButton(
                            categoryBox,
                            allSchemesButton
                    );

                    loadSchemesByCategory(
                            "ALL",
                            schemeCards
                    );
                }
        );

        animalButton.setOnAction(
                e -> {

                    setSelectedButton(
                            categoryBox,
                            animalButton
                    );

                    loadSchemesByCategory(
                            "Animal Husbandry",
                            schemeCards
                    );
                }
        );

        equipmentButton.setOnAction(
                e -> {

                    setSelectedButton(
                            categoryBox,
                            equipmentButton
                    );

                    loadSchemesByCategory(
                            "Equipment Subsidy",
                            schemeCards
                    );
                }
        );

        insuranceButton.setOnAction(
                e -> {

                    setSelectedButton(
                            categoryBox,
                            insuranceButton
                    );

                    loadSchemesByCategory(
                            "Livestock Insurance",
                            schemeCards
                    );
                }
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
    // LOAD SCHEMES BY CATEGORY
    // =========================================================

    private static void loadSchemesByCategory(
            String category,
            VBox schemeCards) {

        // -----------------------------------------------------
        // CLEAR OLD CARDS
        // -----------------------------------------------------

        schemeCards.getChildren().clear();

        // -----------------------------------------------------
        // GET FIREBASE DATA
        // -----------------------------------------------------

        List<Scheme> allSchemes;

        try {

            allSchemes =
                    schemeController.getAllSchemes();

        } catch (Exception e) {

            e.printStackTrace();

            schemeCards.getChildren().add(
                    createNoSchemeView()
            );

            return;
        }

        // -----------------------------------------------------
        // NULL / EMPTY
        // -----------------------------------------------------

        if (allSchemes == null ||
                allSchemes.isEmpty()) {

            schemeCards.getChildren().add(
                    createNoSchemeView()
            );

            return;
        }

        // -----------------------------------------------------
        // FILTER
        // -----------------------------------------------------

        int count = 0;

        for (Scheme scheme : allSchemes) {

            if (scheme == null) {
                continue;
            }

            // Only active schemes
            if (!scheme.isActive()) {
                continue;
            }

            // ALL
            if ("ALL".equalsIgnoreCase(category)) {

                schemeCards.getChildren().add(
                        createSchemeCard(scheme)
                );

                count++;

                continue;
            }

            // CATEGORY
            String schemeCategory =
                    safeText(
                            scheme.getCategory(),
                            ""
                    );

            if (schemeCategory.equalsIgnoreCase(
                    category)) {

                schemeCards.getChildren().add(
                        createSchemeCard(scheme)
                );

                count++;
            }
        }

        // -----------------------------------------------------
        // NOTHING FOUND
        // -----------------------------------------------------

        if (count == 0) {

            schemeCards.getChildren().add(
                    createNoSchemeView(
                            category
                    )
            );
        }
    }

    // =========================================================
    // SELECTED CATEGORY BUTTON
    // =========================================================

    private static void setSelectedButton(
            HBox categoryBox,
            Button selectedButton) {

        for (javafx.scene.Node node :
                categoryBox.getChildren()) {

            if (node instanceof Button) {

                Button button =
                        (Button) node;

                if (button == selectedButton) {

                    styleSelectedButton(button);

                }
                 else {

                    styleNormalButton(button);
                }
            }
        }
    }

    // =========================================================
    // SELECTED BUTTON STYLE
    // =========================================================

    private static void styleSelectedButton(
            Button button) {

        button.setTextFill(
                Color.BLACK
        );

        button.setBackground(
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

        button.setBorder(null);
    }

    // =========================================================
    // NORMAL BUTTON STYLE
    // =========================================================

    private static void styleNormalButton(
            Button button) {

        button.setTextFill(
                Color.rgb(
                        145,
                        160,
                        153
                )
        );

        button.setBackground(
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

        button.setBorder(
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

    // =========================================================
    // NO SCHEME VIEW
    // =========================================================

    private static VBox createNoSchemeView() {

        return createNoSchemeView(
                "this category"
        );
    }

    private static VBox createNoSchemeView(
            String category) {

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
                        "No active government schemes were found for "
                        + category + "."
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

        message.setWrapText(true);

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
        // CATEGORY BADGE
        // =====================================================

        String category =
                safeText(
                        scheme.getCategory(),
                        "GENERAL"
                );

        Label badge =
                new Label(
                        category.toUpperCase()
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
        // APPLY BUTTON
        // =====================================================

        Button actionButton =
                new Button(
                        "Apply Now"
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

        actionButton.setOnAction(
                event -> {

                    openSchemeWebsite(
                            scheme
                    );
                }
        );
        Button likeButton =
        createLikeButton(
                scheme
        );

        // =====================================================
        // ADD CONTENT
        // =====================================================

        schemeCard.getChildren().addAll(
                topRow,
                nameLabel,
                informationLabel,
                eligibilityBox,
                actionButton,
                likeButton
        );

        // =====================================================
        // HOVER
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
    // OPEN APPLICATION WEBSITE
    // =========================================================

    private static void openSchemeWebsite(
            Scheme scheme) {

        String url =
                safeText(
                        scheme.getApplyUrl(),
                        ""
                );

        if (url.isEmpty()) {

            showMessage(
                    "Application Link",
                    "Application link is not available for this scheme."
            );

            return;
        }

        try {

            if (!url.startsWith("http://") &&
                    !url.startsWith("https://")) {

                url = "https://" + url;
            }

            if (!Desktop.isDesktopSupported()) {

                showMessage(
                        "Application Link",
                        "Your computer does not support opening web links."
                );

                return;
            }

            Desktop desktop =
                    Desktop.getDesktop();

            if (!desktop.isSupported(
                    Desktop.Action.BROWSE)) {

                showMessage(
                        "Application Link",
                        "Your system cannot open web links."
                );

                return;
            }

            desktop.browse(
                    new URI(url)
            );

        } catch (Exception e) {

            e.printStackTrace();

            showMessage(
                    "Application Link",
                    "Unable to open the application website."
            );
        }
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

        if (selected) {

            styleSelectedButton(
                    categoryButton
            );

        } else {

            styleNormalButton(
                    categoryButton
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
    // =========================================================
// LIKE / UNLIKE BUTTON
// =========================================================

private static Button createLikeButton(
        Scheme scheme) {

    Button likeButton = new Button();

    likeButton.setPrefHeight(34);

    likeButton.setPrefWidth(120);

    likeButton.setCursor(
            javafx.scene.Cursor.HAND
    );

    likeButton.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    11
            )
    );

    updateLikeButton(
            likeButton,
            scheme
    );

    likeButton.setOnAction(
            event -> {

                if (SavedSchemesManager.isLiked(scheme)) {

                    // -----------------------------------------
                    // UNLIKE
                    // -----------------------------------------

                    SavedSchemesManager.removeScheme(
                            scheme
                    );

                } else {

                    // -----------------------------------------
                    // LIKE
                    // -----------------------------------------

                    SavedSchemesManager.addScheme(
                            scheme
                    );
                }

                // Update button
                updateLikeButton(
                        likeButton,
                        scheme
                );
            }
    );

    return likeButton;
}
// =========================================================
// UPDATE LIKE BUTTON
// =========================================================

private static void updateLikeButton(
        Button button,
        Scheme scheme) {

    if (SavedSchemesManager.isLiked(scheme)) {

        button.setText("♥  Save ");

        button.setStyle(
                "-fx-background-color:#53d74a;" +
                "-fx-text-fill:#06100b;" +
                "-fx-background-radius:5;" +
                "-fx-border-color:#53d74a;" +
                "-fx-border-radius:5;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );

    } else {

        button.setText("♡  Like");

        button.setStyle(
                "-fx-background-color:#101d18;" +
                "-fx-text-fill:#53d74a;" +
                "-fx-background-radius:5;" +
                "-fx-border-color:#53d74a;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:5;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }
}
}