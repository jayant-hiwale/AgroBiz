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

// =============================================================
// SCHEMES PAGE
// =============================================================

public class SchemesPage {

        // =========================================================
        // THEME COLORS
        // =========================================================

        private static final String BG = "#050B0A";

        private static final String CARD = "#0B1714";

        private static final String CARD_HOVER = "#10221C";

        private static final String CARD_BORDER = "#19352B";

        private static final String GREEN = "#53D74A";

        private static final String GREEN_LIGHT = "#68D34A";

        private static final String GREEN_DARK = "#163D24";

        private static final String GREEN_DARKER = "#0C2B1E";

        private static final String TEXT = "#FFFFFF";

        private static final String TEXT_PRIMARY = "#EEEEEE";

        private static final String TEXT_SECONDARY = "#91A099";

        private static final String TEXT_MUTED = "#687572";

        private static final String BORDER = "#1C372D";

        // =========================================================
        // SCENE
        // =========================================================

        private static Scene schemesScene;

        // =========================================================
        // CONTROLLER
        // =========================================================

        private static final SchemeController schemeController = new SchemeController();

        // =========================================================
        // GET SCHEMES PAGE
        // =========================================================

        public static Scene getSchemesPage() {

                BorderPane borderPane = new BorderPane();

                borderPane.setStyle(
                                "-fx-background-color:" + BG + ";");

                // =====================================================
                // NAVBAR
                // =====================================================

                borderPane.setTop(
                                new NavBar().createNavbar("Schemes"));

                // =====================================================
                // FOOTER
                // =====================================================

                borderPane.setBottom(
                                new Footer().createFooter());

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                VBox mainVBox = new VBox(18);

                mainVBox.setPadding(
                                new Insets(
                                                30,
                                                36,
                                                30,
                                                36));

                mainVBox.setAlignment(
                                Pos.TOP_LEFT);

                mainVBox.setFillWidth(true);

                mainVBox.setStyle(
                                "-fx-background-color:" + BG + ";");

                // =====================================================
                // TITLE
                // =====================================================

                Label pageTitle = new Label(
                                "Government & Industry Schemes");

                pageTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                30));

                pageTitle.setTextFill(
                                Color.WHITE);

                // =====================================================
                // DESCRIPTION
                // =====================================================

                Label pageDescription = new Label(
                                "Explore available programs, subsidies, and insurance "
                                                + "schemes designed to support sustainable agriculture "
                                                + "and animal husbandry. Discover opportunities to "
                                                + "enhance your farming operations.");

                pageDescription.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                13));

                pageDescription.setTextFill(
                                Color.web(TEXT_SECONDARY));

                pageDescription.setWrapText(true);

                pageDescription.setMaxWidth(
                                1000);

                // =====================================================
                // SCHEME CARDS CONTAINER
                // =====================================================

                VBox schemeCards = new VBox(18);

                schemeCards.setAlignment(
                                Pos.TOP_LEFT);

                schemeCards.setFillWidth(true);

                // =====================================================
                // CATEGORY BUTTONS
                // =====================================================

                HBox categoryButtons = createCategoryButtons(
                                schemeCards);

                // =====================================================
                // INITIAL LOAD
                // =====================================================

                loadSchemesByCategory(
                                "ALL",
                                schemeCards);

                // =====================================================
                // ADD CONTENT
                // =====================================================

                mainVBox.getChildren().addAll(
                                pageTitle,
                                pageDescription,
                                categoryButtons,
                                schemeCards);

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane();

                scrollPane.setContent(
                                mainVBox);

                scrollPane.setFitToWidth(true);

                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background-color:" + BG + ";" +
                                                "-fx-background:" + BG + ";" +
                                                "-fx-control-inner-background:" + BG + ";" +
                                                "-fx-border-color:transparent;");

                borderPane.setCenter(
                                scrollPane);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                                borderPane,
                                1368,
                                768);

                schemesScene = scene;

                return scene;
        }

        // =========================================================
        // CATEGORY BUTTONS
        // =========================================================

        private static HBox createCategoryButtons(
                        VBox schemeCards) {

                HBox categoryBox = new HBox(10);

                categoryBox.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // ALL
                // =====================================================

                Button allSchemesButton = createCategoryButton(
                                "All Schemes",
                                true);

                // =====================================================
                // ANIMAL HUSBANDRY
                // =====================================================

                Button animalButton = createCategoryButton(
                                "Animal Husbandry",
                                false);

                // =====================================================
                // EQUIPMENT
                // =====================================================

                Button equipmentButton = createCategoryButton(
                                "Equipment Subsidy",
                                false);

                // =====================================================
                // INSURANCE
                // =====================================================

                Button insuranceButton = createCategoryButton(
                                "Livestock Insurance",
                                false);

                // =====================================================
                // ALL ACTION
                // =====================================================

                allSchemesButton.setOnAction(
                                e -> {

                                        setSelectedButton(
                                                        categoryBox,
                                                        allSchemesButton);

                                        loadSchemesByCategory(
                                                        "ALL",
                                                        schemeCards);
                                });

                // =====================================================
                // ANIMAL ACTION
                // =====================================================

                animalButton.setOnAction(
                                e -> {

                                        setSelectedButton(
                                                        categoryBox,
                                                        animalButton);

                                        loadSchemesByCategory(
                                                        "Animal Husbandry",
                                                        schemeCards);
                                });

                // =====================================================
                // EQUIPMENT ACTION
                // =====================================================

                equipmentButton.setOnAction(
                                e -> {

                                        setSelectedButton(
                                                        categoryBox,
                                                        equipmentButton);

                                        loadSchemesByCategory(
                                                        "Equipment Subsidy",
                                                        schemeCards);
                                });

                // =====================================================
                // INSURANCE ACTION
                // =====================================================

                insuranceButton.setOnAction(
                                e -> {

                                        setSelectedButton(
                                                        categoryBox,
                                                        insuranceButton);

                                        loadSchemesByCategory(
                                                        "Livestock Insurance",
                                                        schemeCards);
                                });

                // =====================================================
                // ADD BUTTONS
                // =====================================================

                categoryBox.getChildren().addAll(
                                allSchemesButton,
                                animalButton,
                                equipmentButton,
                                insuranceButton);

                return categoryBox;
        }

        // =========================================================
        // LOAD SCHEMES BY CATEGORY
        // =========================================================

        private static void loadSchemesByCategory(
                        String category,
                        VBox schemeCards) {

                schemeCards
                                .getChildren()
                                .clear();

                List<Scheme> allSchemes;

                try {

                        allSchemes = schemeController.getAllSchemes();

                } catch (Exception e) {

                        e.printStackTrace();

                        schemeCards.getChildren().add(
                                        createNoSchemeView());

                        return;
                }

                // =====================================================
                // EMPTY
                // =====================================================

                if (allSchemes == null ||
                                allSchemes.isEmpty()) {

                        schemeCards.getChildren().add(
                                        createNoSchemeView());

                        return;
                }

                // =====================================================
                // FILTER
                // =====================================================

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
                                                createSchemeCard(scheme));

                                count++;

                                continue;
                        }

                        // CATEGORY
                        String schemeCategory = safeText(
                                        scheme.getCategory(),
                                        "");

                        if (schemeCategory.equalsIgnoreCase(
                                        category)) {

                                schemeCards.getChildren().add(
                                                createSchemeCard(scheme));

                                count++;
                        }
                }

                // =====================================================
                // NOTHING FOUND
                // =====================================================

                if (count == 0) {

                        schemeCards.getChildren().add(
                                        createNoSchemeView(
                                                        category));
                }
        }

        // =========================================================
        // SELECTED CATEGORY
        // =========================================================

        private static void setSelectedButton(
                        HBox categoryBox,
                        Button selectedButton) {

                for (javafx.scene.Node node : categoryBox.getChildren()) {

                        if (node instanceof Button) {

                                Button button = (Button) node;

                                if (button == selectedButton) {

                                        styleSelectedButton(
                                                        button);

                                } else {

                                        styleNormalButton(
                                                        button);
                                }
                        }
                }
        }

        // =========================================================
        // SELECTED BUTTON STYLE
        // =========================================================

        private static void styleSelectedButton(
                        Button button) {

                button.setStyle(
                                "-fx-background-color:" + GREEN + ";" +
                                                "-fx-text-fill:#06100B;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-border-color:" + GREEN + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-padding:8 17 8 17;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;" +
                                                "-fx-background-insets:0;" +
                                                "-fx-border-insets:0;");
        }

        // =========================================================
        // NORMAL BUTTON STYLE
        // =========================================================

        private static void styleNormalButton(
                        Button button) {

                button.setStyle(
                                "-fx-background-color:#0B1714;" +
                                                "-fx-text-fill:" + TEXT_SECONDARY + ";" +
                                                "-fx-background-radius:8;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-padding:8 17 8 17;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;" +
                                                "-fx-background-insets:0;" +
                                                "-fx-border-insets:0;");
        }

        // =========================================================
        // CATEGORY BUTTON
        // =========================================================

        private static Button createCategoryButton(
                        String buttonText,
                        boolean selected) {

                Button categoryButton = new Button(
                                buttonText);

                categoryButton.setPrefHeight(
                                34);

                categoryButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                categoryButton.setCursor(
                                javafx.scene.Cursor.HAND);

                // =====================================================
                // INITIAL STYLE
                // =====================================================

                if (selected) {

                        styleSelectedButton(
                                        categoryButton);

                } else {

                        styleNormalButton(
                                        categoryButton);
                }

                // =====================================================
                // HOVER
                // =====================================================

                categoryButton.setOnMouseEntered(
                                e -> {

                                        if (selected) {

                                                categoryButton.setStyle(
                                                                "-fx-background-color:#68E85A;" +
                                                                                "-fx-text-fill:#06100B;" +
                                                                                "-fx-background-radius:8;" +
                                                                                "-fx-border-radius:8;" +
                                                                                "-fx-border-color:#68E85A;" +
                                                                                "-fx-border-width:1;" +
                                                                                "-fx-padding:8 17 8 17;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-cursor:hand;");

                                        } else {

                                                categoryButton.setStyle(
                                                                "-fx-background-color:#163D24;" +
                                                                                "-fx-text-fill:#FFFFFF;" +
                                                                                "-fx-background-radius:8;" +
                                                                                "-fx-border-radius:8;" +
                                                                                "-fx-border-color:#53D74A;" +
                                                                                "-fx-border-width:1;" +
                                                                                "-fx-padding:8 17 8 17;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-cursor:hand;");
                                        }
                                });

                // =====================================================
                // EXIT HOVER
                // =====================================================

                categoryButton.setOnMouseExited(
                                e -> {

                                        if (selected) {

                                                styleSelectedButton(
                                                                categoryButton);

                                        } else {

                                                styleNormalButton(
                                                                categoryButton);
                                        }
                                });

                return categoryButton;
        }

        // =========================================================
        // NO SCHEME VIEW
        // =========================================================

        private static VBox createNoSchemeView() {

                return createNoSchemeView(
                                "this category");
        }

        private static VBox createNoSchemeView(
                        String category) {

                VBox box = new VBox(10);

                box.setAlignment(
                                Pos.CENTER);

                box.setPadding(
                                new Insets(50));

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-background-radius:12;" +
                                                "-fx-border-color:" + CARD_BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-border-width:1;");

                Label icon = new Label(
                                "🌱");

                icon.setFont(
                                Font.font(
                                                "Arial",
                                                30));

                Label title = new Label(
                                "No schemes available");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                title.setTextFill(
                                Color.WHITE);

                Label message = new Label(
                                "No active government schemes were found for "
                                                + category + ".");

                message.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                message.setTextFill(
                                Color.web(TEXT_SECONDARY));

                message.setWrapText(true);

                message.setAlignment(
                                Pos.CENTER);

                box.getChildren().addAll(
                                icon,
                                title,
                                message);

                return box;
        }

        // =========================================================
        // CREATE SCHEME CARD
        // =========================================================

        private static VBox createSchemeCard(
                        Scheme scheme) {

                VBox schemeCard = new VBox(12);

                schemeCard.setPadding(
                                new Insets(18));

                schemeCard.setMaxWidth(
                                Double.MAX_VALUE);

                // =====================================================
                // NORMAL CARD
                // =====================================================

                applyNormalCardStyle(
                                schemeCard);

                // =====================================================
                // TOP ROW
                // =====================================================

                HBox topRow = new HBox();

                topRow.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // ICON
                // =====================================================

                Label iconLabel = new Label(
                                "🌱");

                iconLabel.setPrefSize(
                                45,
                                42);

                iconLabel.setAlignment(
                                Pos.CENTER);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                20));

                iconLabel.setStyle(
                                "-fx-background-color:#103927;" +
                                                "-fx-background-radius:8;");

                // =====================================================
                // SPACE
                // =====================================================

                Region space = new Region();

                HBox.setHgrow(
                                space,
                                Priority.ALWAYS);

                // =====================================================
                // CATEGORY BADGE
                // =====================================================

                String category = safeText(
                                scheme.getCategory(),
                                "GENERAL");

                Label badge = new Label(
                                category.toUpperCase());

                badge.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));

                badge.setTextFill(
                                Color.web("#82D296"));

                badge.setPadding(
                                new Insets(
                                                5,
                                                10,
                                                5,
                                                10));

                badge.setStyle(
                                "-fx-background-color:#0C2B1E;" +
                                                "-fx-background-radius:12;");

                topRow.getChildren().addAll(
                                iconLabel,
                                space,
                                badge);

                // =====================================================
                // SCHEME NAME
                // =====================================================

                String schemeName = safeText(
                                scheme.getSchemeName(),
                                "Government Scheme");

                Label nameLabel = new Label(
                                schemeName);

                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                nameLabel.setTextFill(
                                Color.WHITE);

                nameLabel.setWrapText(
                                true);

                // =====================================================
                // INFORMATION
                // =====================================================

                String information = safeText(
                                scheme.getInformation(),
                                "Information not available.");

                Label informationLabel = new Label(
                                information);

                informationLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                12));

                informationLabel.setTextFill(
                                Color.web(TEXT_SECONDARY));

                informationLabel.setWrapText(
                                true);

                informationLabel.setMaxWidth(
                                Double.MAX_VALUE);

                // =====================================================
                // ELIGIBILITY BOX
                // =====================================================

                VBox eligibilityBox = new VBox(6);

                eligibilityBox.setPadding(
                                new Insets(12));

                eligibilityBox.setStyle(
                                "-fx-background-color:#081C16;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-border-color:#123C2B;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:8;");

                Label eligibilityHeading = new Label(
                                "ⓘ  Eligibility");

                eligibilityHeading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                eligibilityHeading.setTextFill(
                                Color.web(GREEN));

                String eligibility = safeText(
                                scheme.getEligibility(),
                                "Eligibility information not available.");

                Label eligibilityLabel = new Label(
                                eligibility);

                eligibilityLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                11));

                eligibilityLabel.setTextFill(
                                Color.web(TEXT_SECONDARY));

                eligibilityLabel.setWrapText(
                                true);

                eligibilityLabel.setMaxWidth(
                                Double.MAX_VALUE);

                eligibilityBox.getChildren().addAll(
                                eligibilityHeading,
                                eligibilityLabel);

                // =====================================================
                // APPLY + SAVE ROW
                // =====================================================

                HBox actionRow = new HBox(10);

                actionRow.setAlignment(
                                Pos.CENTER_LEFT);

                actionRow.setFillHeight(true);

                // =====================================================
                // APPLY BUTTON
                // =====================================================

                Button actionButton = createApplyButton(
                                scheme);

                HBox.setHgrow(
                                actionButton,
                                Priority.ALWAYS);

                // =====================================================
                // LIKE / SAVE BUTTON
                // =====================================================

                Button likeButton = createLikeButton(
                                scheme);

                actionRow.getChildren().addAll(
                                actionButton,
                                likeButton);

                // =====================================================
                // ADD CONTENT
                // =====================================================

                schemeCard.getChildren().addAll(
                                topRow,
                                nameLabel,
                                informationLabel,
                                eligibilityBox,
                                actionRow);

                // =====================================================
                // HOVER CARD
                // =====================================================

                schemeCard.setOnMouseEntered(
                                event -> {

                                        schemeCard.setStyle(
                                                        "-fx-background-color:" + CARD_HOVER + ";" +
                                                                        "-fx-background-radius:12;" +
                                                                        "-fx-border-color:" + GREEN + ";" +
                                                                        "-fx-border-radius:12;" +
                                                                        "-fx-border-width:1;");
                                });

                schemeCard.setOnMouseExited(
                                event -> {

                                        applyNormalCardStyle(
                                                        schemeCard);
                                });

                return schemeCard;
        }

        // =========================================================
        // NORMAL CARD STYLE
        // =========================================================

        private static void applyNormalCardStyle(
                        VBox card) {

                card.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-background-radius:12;" +
                                                "-fx-border-color:" + CARD_BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-border-width:1;");
        }

        // =========================================================
        // CREATE APPLY BUTTON
        // =========================================================

        private static Button createApplyButton(
                        Scheme scheme) {

                Button button = new Button(
                                "Apply Now");

                button.setPrefHeight(
                                38);

                button.setMinHeight(
                                38);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                button.setCursor(
                                javafx.scene.Cursor.HAND);

                // =====================================================
                // NORMAL
                // =====================================================

                button.setStyle(
                                "-fx-background-color:" + GREEN + ";" +
                                                "-fx-text-fill:#06100B;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-border-color:" + GREEN + ";" +
                                                "-fx-border-width:1;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // HOVER
                // =====================================================

                button.setOnMouseEntered(
                                e -> {

                                        button.setStyle(
                                                        "-fx-background-color:#68E85A;" +
                                                                        "-fx-text-fill:#06100B;" +
                                                                        "-fx-background-radius:7;" +
                                                                        "-fx-border-radius:7;" +
                                                                        "-fx-border-color:#68E85A;" +
                                                                        "-fx-border-width:1;" +
                                                                        "-fx-font-size:12px;" +
                                                                        "-fx-font-weight:bold;" +
                                                                        "-fx-cursor:hand;");
                                });

                // =====================================================
                // EXIT
                // =====================================================

                button.setOnMouseExited(
                                e -> {

                                        button.setStyle(
                                                        "-fx-background-color:" + GREEN + ";" +
                                                                        "-fx-text-fill:#06100B;" +
                                                                        "-fx-background-radius:7;" +
                                                                        "-fx-border-radius:7;" +
                                                                        "-fx-border-color:" + GREEN + ";" +
                                                                        "-fx-border-width:1;" +
                                                                        "-fx-font-size:12px;" +
                                                                        "-fx-font-weight:bold;" +
                                                                        "-fx-cursor:hand;");
                                });

                // =====================================================
                // ACTION
                // =====================================================

                button.setOnAction(
                                event -> {

                                        openSchemeWebsite(
                                                        scheme);
                                });

                return button;
        }

        // =========================================================
        // CREATE LIKE BUTTON
        // =========================================================

        private static Button createLikeButton(
                        Scheme scheme) {

                Button likeButton = new Button();

                likeButton.setPrefHeight(
                                38);

                likeButton.setMinHeight(
                                38);

                likeButton.setPrefWidth(
                                125);

                likeButton.setMinWidth(
                                125);

                likeButton.setMaxWidth(
                                125);

                likeButton.setCursor(
                                javafx.scene.Cursor.HAND);

                likeButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                // =====================================================
                // INITIAL STATE
                // =====================================================

                updateLikeButton(
                                likeButton,
                                scheme);

                // =====================================================
                // CLICK
                // =====================================================

                likeButton.setOnAction(
                                event -> {

                                        if (SavedSchemesManager.isLiked(
                                                        scheme)) {

                                                SavedSchemesManager.removeScheme(
                                                                scheme);

                                        } else {

                                                SavedSchemesManager.addScheme(
                                                                scheme);
                                        }

                                        updateLikeButton(
                                                        likeButton,
                                                        scheme);
                                });

                return likeButton;
        }

        // =========================================================
        // UPDATE LIKE BUTTON
        // =========================================================

        private static void updateLikeButton(
                        Button button,
                        Scheme scheme) {

                boolean liked = SavedSchemesManager.isLiked(
                                scheme);

                if (liked) {

                        // =================================================
                        // SAVED STATE
                        // =================================================

                        button.setText(
                                        "♥  Saved");

                        button.setStyle(
                                        "-fx-background-color:" + GREEN + ";" +
                                                        "-fx-text-fill:#06100B;" +
                                                        "-fx-background-radius:7;" +
                                                        "-fx-border-radius:7;" +
                                                        "-fx-border-color:" + GREEN + ";" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-font-size:12px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-cursor:hand;");

                        button.setOnMouseEntered(
                                        e -> {

                                                button.setStyle(
                                                                "-fx-background-color:#3FB838;" +
                                                                                "-fx-text-fill:#FFFFFF;" +
                                                                                "-fx-background-radius:7;" +
                                                                                "-fx-border-radius:7;" +
                                                                                "-fx-border-color:#3FB838;" +
                                                                                "-fx-border-width:1;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-cursor:hand;");
                                        });

                        button.setOnMouseExited(
                                        e -> {

                                                button.setStyle(
                                                                "-fx-background-color:" + GREEN + ";" +
                                                                                "-fx-text-fill:#06100B;" +
                                                                                "-fx-background-radius:7;" +
                                                                                "-fx-border-radius:7;" +
                                                                                "-fx-border-color:" + GREEN + ";" +
                                                                                "-fx-border-width:1;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-cursor:hand;");
                                        });

                } else {

                        // =================================================
                        // NOT SAVED STATE
                        // =================================================

                        button.setText(
                                        "♡  Save");

                        button.setStyle(
                                        "-fx-background-color:#0B1714;" +
                                                        "-fx-text-fill:" + GREEN + ";" +
                                                        "-fx-background-radius:7;" +
                                                        "-fx-border-radius:7;" +
                                                        "-fx-border-color:" + GREEN + ";" +
                                                        "-fx-border-width:1;" +
                                                        "-fx-font-size:12px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-cursor:hand;");

                        button.setOnMouseEntered(
                                        e -> {

                                                button.setStyle(
                                                                "-fx-background-color:#163D24;" +
                                                                                "-fx-text-fill:#FFFFFF;" +
                                                                                "-fx-background-radius:7;" +
                                                                                "-fx-border-radius:7;" +
                                                                                "-fx-border-color:" + GREEN + ";" +
                                                                                "-fx-border-width:1;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-cursor:hand;");
                                        });

                        button.setOnMouseExited(
                                        e -> {

                                                button.setStyle(
                                                                "-fx-background-color:#0B1714;" +
                                                                                "-fx-text-fill:" + GREEN + ";" +
                                                                                "-fx-background-radius:7;" +
                                                                                "-fx-border-radius:7;" +
                                                                                "-fx-border-color:" + GREEN + ";" +
                                                                                "-fx-border-width:1;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-cursor:hand;");
                                        });
                }
        }

        // =========================================================
        // OPEN APPLICATION WEBSITE
        // =========================================================

        private static void openSchemeWebsite(
                        Scheme scheme) {

                String url = safeText(
                                scheme.getApplyUrl(),
                                "");

                if (url.isEmpty()) {

                        showMessage(
                                        "Application Link",
                                        "Application link is not available for this scheme.");

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
                                                "Your computer does not support opening web links.");

                                return;
                        }

                        Desktop desktop = Desktop.getDesktop();

                        if (!desktop.isSupported(
                                        Desktop.Action.BROWSE)) {

                                showMessage(
                                                "Application Link",
                                                "Your system cannot open web links.");

                                return;
                        }

                        desktop.browse(
                                        new URI(url));

                } catch (Exception e) {

                        e.printStackTrace();

                        showMessage(
                                        "Application Link",
                                        "Unable to open the application website.");
                }
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

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                title);

                alert.setHeaderText(
                                null);

                alert.setContentText(
                                message);

                // =====================================================
                // DARK ALERT
                // =====================================================

                alert.getDialogPane()
                                .setStyle(
                                                "-fx-background-color:#101718;" +
                                                                "-fx-border-color:#293334;");

                alert.showAndWait();
        }

        // =========================================================
        // BACK TO SCHEMES
        // =========================================================

        public void backtoSchemes() {

                if (schemesScene != null &&
                                LoginPage.mainStage != null) {

                        LoginPage.mainStage.setScene(
                                        schemesScene);
                }
        }
}