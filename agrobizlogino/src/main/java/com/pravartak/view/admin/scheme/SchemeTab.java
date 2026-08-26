package com.pravartak.view.admin.scheme;

import java.util.List;

import com.pravartak.controller.admincontroller.SchemeController;
import com.pravartak.model.adminmodel.Scheme;
import com.pravartak.view.admin.AdminPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SchemeTab {

    private static final SchemeController controller =
            new SchemeController();

    // =========================================================
    // MAIN PAGE
    // =========================================================

    public static VBox getSchemesPage() {

        VBox root =
                new VBox(15);

        root.setPadding(
                new Insets(
                        15,
                        0,
                        20,
                        0));

        root.setStyle(
                "-fx-background-color:#080C0D;");

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox(15);

        header.setAlignment(
                Pos.CENTER_LEFT);

        VBox titleBox =
                new VBox(4);

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS);

        Label title =
                new Label("Government Schemes");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:22px;" +
                "-fx-font-weight:bold;");

        Label subtitle =
                new Label(
                        "Manage agricultural government schemes.");

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:10px;");

        titleBox.getChildren().addAll(
                title,
                subtitle);

        // =====================================================
        // ADD BUTTON
        // =====================================================

        Button addButton =
                new Button("+  Add Scheme");

        addButton.setPrefHeight(38);

        addButton.setPrefWidth(140);

        addButton.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:6;" +
                "-fx-border-radius:6;" +
                "-fx-cursor:hand;");

        addButton.setOnAction(
                e -> openCreateSchemePage());

        header.getChildren().addAll(
                titleBox,
                addButton);

        // =====================================================
        // SCHEME LIST
        // =====================================================

        VBox schemeList =
                new VBox(15);

        schemeList.setPadding(
                new Insets(
                        10,
                        5,
                        25,
                        5));

        schemeList.setFillWidth(true);

        loadSchemes(
                schemeList);

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll =
                new ScrollPane(schemeList);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scroll.setPannable(true);

        scroll.setStyle(
                "-fx-background-color:#080C0D;" +
                "-fx-background:#080C0D;" +
                "-fx-border-color:transparent;");

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS);

        root.getChildren().addAll(
                header,
                scroll);

        return root;
    }

    // =========================================================
    // LOAD
    // =========================================================

    private static void loadSchemes(
            VBox schemeList) {

        schemeList.getChildren().clear();

        try {

            List<Scheme> schemes =
                    controller.getAllSchemes();

            if (schemes == null ||
                    schemes.isEmpty()) {

                schemeList.getChildren().add(
                        createEmptyView());

                return;
            }

            for (Scheme scheme :
                    schemes) {

                if (scheme != null) {

                    schemeList.getChildren().add(
                            createSchemeCard(
                                    scheme));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label error =
                    new Label(
                            "Unable to load schemes.");

            error.setStyle(
                    "-fx-text-fill:#FF6B6B;" +
                    "-fx-font-size:12px;");

            schemeList.getChildren().add(
                    error);
        }
    }

    // =========================================================
    // SCHEME CARD
    // =========================================================

    private static VBox createSchemeCard(
            Scheme scheme) {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(15));

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color:#0B1914;" +
                "-fx-border-color:#193A2D;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;");

        // =====================================================
        // TOP
        // =====================================================

        HBox top =
                new HBox(10);

        top.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // ICON
        // =====================================================

        Label icon =
                new Label("⚙");

        icon.setPrefSize(
                44,
                38);

        icon.setAlignment(
                Pos.CENTER);

        icon.setStyle(
                "-fx-background-color:#0B382A;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-background-radius:6;");

        // =====================================================
        // TITLE
        // =====================================================

        VBox titleBox =
                new VBox(3);

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS);

        Label title =
                new Label(
                        safe(
                                scheme.getSchemeName(),
                                "Unnamed Scheme"));

        title.setWrapText(true);

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;");

        Label info =
                new Label(
                        "Government Agricultural Scheme");

        info.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:9px;");

        titleBox.getChildren().addAll(
                title,
                info);

        // =====================================================
        // ACTIVE BADGE
        // =====================================================

        Label active =
                new Label(
                        scheme.isActive()
                                ? "ACTIVE"
                                : "INACTIVE");

        active.setStyle(
                scheme.isActive()
                        ? "-fx-background-color:#123B28;" +
                          "-fx-text-fill:#68D34A;" +
                          "-fx-font-size:8px;" +
                          "-fx-font-weight:bold;" +
                          "-fx-padding:5 8;" +
                          "-fx-background-radius:10;"
                        : "-fx-background-color:#332020;" +
                          "-fx-text-fill:#FF7777;" +
                          "-fx-font-size:8px;" +
                          "-fx-font-weight:bold;" +
                          "-fx-padding:5 8;" +
                          "-fx-background-radius:10;");

        top.getChildren().addAll(
                icon,
                titleBox,
                active);

        // =====================================================
        // INFORMATION
        // =====================================================

        Label informationTitle =
                new Label(
                        "Scheme Information");

        informationTitle.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;");

        Label information =
                new Label(
                        safe(
                                scheme.getInformation(),
                                "No information available."));

        information.setWrapText(true);

        information.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:10px;");

        // =====================================================
        // ELIGIBILITY BOX
        // =====================================================

        VBox eligibilityBox =
                new VBox(5);

        eligibilityBox.setPadding(
                new Insets(10));

        eligibilityBox.setStyle(
                "-fx-background-color:#071A14;" +
                "-fx-background-radius:6;");

        Label eligibilityTitle =
                new Label(
                        "ⓘ  Eligibility");

        eligibilityTitle.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;");

        Label eligibility =
                new Label(
                        safe(
                                scheme.getEligibility(),
                                "Eligibility information unavailable."));

        eligibility.setWrapText(true);

        eligibility.setStyle(
                "-fx-text-fill:#CCCCCC;" +
                "-fx-font-size:10px;");

        eligibilityBox.getChildren().addAll(
                eligibilityTitle,
                eligibility);

        // =====================================================
        // ACTIONS
        // =====================================================

        HBox actions =
                new HBox(8);

        actions.setAlignment(
                Pos.CENTER_RIGHT);

        Button edit =
                new Button("✎ Edit");

        edit.setPrefWidth(90);

        edit.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-border-color:#2A3A32;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;" +
                "-fx-font-size:9px;" +
                "-fx-cursor:hand;");

        edit.setOnAction(
                e -> openEditPage(scheme));

        Button delete =
                new Button("Delete");

        delete.setPrefWidth(90);

        delete.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#FF6B6B;" +
                "-fx-border-color:#5A2929;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;" +
                "-fx-font-size:9px;" +
                "-fx-cursor:hand;");

        delete.setOnAction(
                e -> deleteScheme(
                        scheme));

        actions.getChildren().addAll(
                edit,
                delete);

        // =====================================================
        // ADD
        // =====================================================

        card.getChildren().addAll(
                top,
                informationTitle,
                information,
                eligibilityBox,
                actions);

        // =====================================================
        // HOVER
        // =====================================================

        card.setOnMouseEntered(
                e -> card.setStyle(
                        "-fx-background-color:#10251C;" +
                        "-fx-border-color:#68D34A;" +
                        "-fx-border-width:1;" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"));

        card.setOnMouseExited(
                e -> card.setStyle(
                        "-fx-background-color:#0B1914;" +
                        "-fx-border-color:#193A2D;" +
                        "-fx-border-width:1;" +
                        "-fx-border-radius:10;" +
                        "-fx-background-radius:10;"));

        return card;
    }

    // =========================================================
    // EMPTY
    // =========================================================

    private static VBox createEmptyView() {

        VBox box =
                new VBox(10);

        box.setAlignment(
                Pos.CENTER);

        box.setPadding(
                new Insets(60));

        Label icon =
                new Label("▣");

        icon.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:30px;");

        Label title =
                new Label(
                        "No schemes available");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;");

        Label message =
                new Label(
                        "Click '+ Add Scheme' to create your first scheme.");

        message.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:10px;");

        box.getChildren().addAll(
                icon,
                title,
                message);

        return box;
    }

    // =========================================================
    // ADD
    // =========================================================

    private static void openCreateSchemePage() {

        CreateSchemeAdmin page =
                new CreateSchemeAdmin();

        LoginPage.mainStage.setScene(
                page.getCreateSchemeScene());
    }

    // =========================================================
    // EDIT
    // =========================================================

    private static void openEditPage(
            Scheme scheme) {

        EditSchemeAdmin page =
                new EditSchemeAdmin(scheme);

        LoginPage.mainStage.setScene(
                page.getEditSchemeScene());
    }

    // =========================================================
    // DELETE
    // =========================================================

    private static void deleteScheme(
            Scheme scheme) {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION);

        alert.setTitle(
                "Delete Scheme");

        alert.setHeaderText(
                "Delete this scheme?");

        alert.setContentText(
                scheme.getSchemeName());

        alert.showAndWait()
                .ifPresent(response -> {

                    if (response ==
                            javafx.scene.control.ButtonType.OK) {

                        boolean success =
                                controller.deleteScheme(
                                        scheme.getSchemeId());

                        if (success) {

                            AdminPage adminPage =
                                    new AdminPage();

                        //     LoginPage.mainStage.setScene(
                        //             adminPage.getAdminPage(
                        //                     "Schemes"));
                        }
                    }
                });
    }

    // =========================================================
    // SAFE TEXT
    // =========================================================

    private static String safe(
            String value,
            String fallback) {

        if (value == null ||
                value.trim().isEmpty()) {

            return fallback;
        }

        return value;
    }
}
