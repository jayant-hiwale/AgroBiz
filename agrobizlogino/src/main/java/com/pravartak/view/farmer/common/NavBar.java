package com.pravartak.view.farmer.common;

import com.pravartak.view.farmer.AIAdvisorPage;
import com.pravartak.view.farmer.CommunityPage;
import com.pravartak.view.farmer.ExplorerPage;
import com.pravartak.view.farmer.FarmerDashboard;
import com.pravartak.view.farmer.HomePageFarmer;
import com.pravartak.view.farmer.LearningPage;
import com.pravartak.view.farmer.MarketPlace;
import com.pravartak.view.farmer.SchemesPage;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NavBar {

    // =========================================================
    // LOGGED-IN FARMER INFORMATION
    // =========================================================

    private final int farmerId;
    private final String firebaseUid;

    // =========================================================
    // CONSTRUCTOR WITH FARMER ID + FIREBASE UID
    // =========================================================

    public NavBar(
            int farmerId,
            String firebaseUid) {

        this.farmerId = farmerId;
        this.firebaseUid = firebaseUid;

        System.out.println(
                "NavBar Farmer ID = "
                        + farmerId
        );

        System.out.println(
                "NavBar Firebase UID = "
                        + firebaseUid
        );
    }

    // =========================================================
    // DEFAULT CONSTRUCTOR
    //
    // This is important because many of your existing pages
    // use:
    //
    // new NavBar().createNavbar(...)
    //
    // Instead of setting farmerId = 0, we get the current
    // logged-in farmer information from LoginPage.
    // =========================================================

    public NavBar() {

        this.farmerId =
                LoginPage.getLoggedInFarmerId();

        this.firebaseUid =
                LoginPage.getLoggedInFirebaseUid();

        System.out.println(
                "NavBar default constructor"
        );

        System.out.println(
                "NavBar Farmer ID = "
                        + this.farmerId
        );

        System.out.println(
                "NavBar Firebase UID = "
                        + this.firebaseUid
        );
    }

    // =========================================================
    // CREATE NAVBAR
    // =========================================================

    public HBox createNavbar(
            String currentPage) {

        HBox navbar =
                new HBox();

        navbar.setPadding(
                new Insets(
                        10,
                        20,
                        10,
                        20
                )
        );

        navbar.setAlignment(
                Pos.CENTER
        );

        navbar.setStyle(
                "-fx-background-color:#080c0d;" +
                "-fx-border-color:#1b2021;" +
                "-fx-border-width:0 0 1 0;"
        );

        // =====================================================
        // LOGO
        // =====================================================

        Label logo =
                new Label(
                        "Agro Biz"
                );

        logo.setStyle(
                "-fx-text-fill:#68d34a;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        HBox left =
                new HBox(
                        logo
                );

        left.setAlignment(
                Pos.CENTER_LEFT
        );

        left.setPrefWidth(
                450
        );

        // =====================================================
        // NAVIGATION BUTTONS
        // =====================================================

        Button home =
                navButton("Home");

        Button explorer =
                navButton("Explorer");

        Button marketplace =
                navButton("Marketplace");

        Button community =
                navButton("Community");

        Button learning =
                navButton("Learning");

        Button schemes =
                navButton("Schemes");

        Button aiadvisor =
                navButton("AI Advisor");

        // =====================================================
        // HOME
        // =====================================================

        if ("Home".equals(currentPage)) {

            home.setStyle(
                    navButtonActive()
            );
        }

        home.setOnAction(
                e -> {

                    System.out.println(
                            "Home button clicked"
                    );

                    if (!checkFarmerId()) {
                        return;
                    }

                    HomePageFarmer homePageFarmer =
                            new HomePageFarmer(
                                    farmerId,
                                    firebaseUid
                            );

                    LoginPage.mainStage.setScene(
                            homePageFarmer
                                    .getHomePageFarmer()
                    );
                }
        );

        // =====================================================
        // EXPLORER
        // =====================================================

        if ("Explorer".equals(currentPage)) {

            explorer.setStyle(
                    navButtonActive()
            );
        }

        explorer.setOnAction(
                e -> {

                    System.out.println(
                            "Explorer button clicked"
                    );

                    ExplorerPage explorerPage =
                            new ExplorerPage();

                    LoginPage.mainStage.setScene(
                            explorerPage
                                    .getExplorerPage()
                    );
                }
        );

        // =====================================================
        // MARKETPLACE
        // =====================================================

        if ("Marketplace".equals(currentPage)) {

            marketplace.setStyle(
                    navButtonActive()
            );
        }

        marketplace.setOnAction(
                e -> {

                    System.out.println(
                            "MarketButton Clicked"
                    );

                    System.out.println(
                            "Farmer ID = "
                                    + farmerId
                    );

                    System.out.println(
                            "Firebase UID = "
                                    + firebaseUid
                    );

                    // -----------------------------------------
                    // CHECK FARMER ID
                    // -----------------------------------------

                    if (!checkFarmerId()) {
                        return;
                    }

                    // -----------------------------------------
                    // OPEN MARKETPLACE
                    // -----------------------------------------

                    MarketPlace marketPlace =
                            new MarketPlace(
                                    farmerId,
                                    firebaseUid
                            );

                    LoginPage.mainStage.setScene(
                            marketPlace
                                    .getMarketPlaceScene()
                    );
                }
        );

        // =====================================================
        // COMMUNITY
        // =====================================================

        if ("Community".equals(currentPage)) {

            community.setStyle(
                    navButtonActive()
            );
        }

        community.setOnAction(
                e -> {

                    System.out.println(
                            "Community button clicked"
                    );

                    CommunityPage communityPage =
                            new CommunityPage();

                    LoginPage.mainStage.setScene(
                            communityPage
                                    .getCommunityScene()
                    );
                }
        );

        // =====================================================
        // LEARNING
        // =====================================================

        if ("Learning".equals(currentPage)) {

            learning.setStyle(
                    navButtonActive()
            );
        }

        learning.setOnAction(
                e -> {

                    System.out.println(
                            "Learning button clicked"
                    );

                    LearningPage learningPage =
                            new LearningPage();

                    LoginPage.mainStage.setScene(
                            learningPage
                                    .get_learning_pageScene()
                    );
                }
        );

        // =====================================================
        // SCHEMES
        // =====================================================

        if ("Schemes".equals(currentPage)) {

            schemes.setStyle(
                    navButtonActive()
            );
        }

        schemes.setOnAction(
                e -> {

                    System.out.println(
                            "Schemes button clicked"
                    );

                    SchemesPage schemesPage =
                            new SchemesPage();

                    LoginPage.mainStage.setScene(
                            schemesPage
                                    .getSchemesPage()
                    );
                }
        );

        // =====================================================
        // AI ADVISOR
        // =====================================================

        if ("AI Advisor".equals(currentPage)) {

            aiadvisor.setStyle(
                    navButtonActive()
            );
        }

        aiadvisor.setOnAction(
                e -> {

                    System.out.println(
                            "AI Advisor button clicked"
                    );

                    AIAdvisorPage ai =
                            new AIAdvisorPage();

                    LoginPage.mainStage.setScene(
                            ai.getAIAdvisorScene()
                    );
                }
        );

        // =====================================================
        // CENTER NAVIGATION
        // =====================================================

        HBox center =
                new HBox(
                        25,
                        home,
                        explorer,
                        marketplace,
                        community,
                        learning,
                        schemes,
                        aiadvisor
                );

        center.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // PROFILE BUTTON
        // =====================================================

        Button profile =
                new Button(
                        "◎ Profile"
                );

        if ("◎ Profile".equals(currentPage)) {

            profile.setStyle(
                    navButtonActive()
            );

        } else {

            profile.setStyle(
                    navButtonNormal()
            );
        }

        // =====================================================
        // PROFILE HOVER
        // =====================================================

        profile.setOnMouseEntered(
                e -> {

                    if (!"◎ Profile".equals(currentPage)) {

                        profile.setStyle(
                                navButtonHover()
                        );
                    }
                }
        );

        profile.setOnMouseExited(
                e -> {

                    if (!"◎ Profile".equals(currentPage)) {

                        profile.setStyle(
                                navButtonNormal()
                        );
                    }
                }
        );

        // =====================================================
        // PROFILE CLICK
        // =====================================================

        profile.setOnAction(
                e -> {

                    System.out.println(
                            "Profile button clicked"
                    );

                    System.out.println(
                            "Farmer ID = "
                                    + farmerId
                    );

                    System.out.println(
                            "Firebase UID = "
                                    + firebaseUid
                    );

                    if (!checkFarmerId()) {
                        return;
                    }

                    FarmerDashboard dashboard =
                            new FarmerDashboard(
                                    farmerId,
                                    firebaseUid
                            );

                    LoginPage.mainStage.setScene(
                            dashboard
                                    .getDashboardScene()
                    );
                }
        );

        // =====================================================
        // RIGHT
        // =====================================================
// =====================================================
// LOGOUT BUTTON
// =====================================================

Button logout =
        navButton("Logout");

// Normal logout style
logout.setStyle(
        "-fx-background-color:transparent;" +
        "-fx-text-fill:#aaaaaa;" +
        "-fx-font-size:13px;" +
        "-fx-cursor:hand;" +
        "-fx-padding:5 0 5 0;"
);

// Logout hover
logout.setOnMouseEntered(e -> {

    logout.setStyle(
            "-fx-background-color:transparent;" +
            "-fx-text-fill:#ff4d5a;" +
            "-fx-font-size:13px;" +
            "-fx-font-weight:bold;" +
            "-fx-cursor:hand;" +
            "-fx-padding:5 0 5 0;" +
            "-fx-border-color:#ff4d5a;" +
            "-fx-border-width:0 0 2 0;"
    );
});

logout.setOnMouseExited(e -> {

    logout.setStyle(
            "-fx-background-color:transparent;" +
            "-fx-text-fill:#aaaaaa;" +
            "-fx-font-size:13px;" +
            "-fx-cursor:hand;" +
            "-fx-padding:5 0 5 0;"
    );
});

// =====================================================
// LOGOUT ACTION
// =====================================================

logout.setOnAction(e -> {

    System.out.println(
            "Farmer logged out."
    );

    try {

        LoginPage loginPage =
                new LoginPage();

        /*
         * Start LoginPage again using the
         * existing mainStage.
         */
        loginPage.start(
                LoginPage.mainStage
        );

    } catch (Exception ex) {

        ex.printStackTrace();
    }
});

// =====================================================
// RIGHT
// =====================================================

HBox right =
        new HBox(
                15,
                profile,
                logout
        );

right.setAlignment(
        Pos.CENTER_RIGHT
);

right.setPrefWidth(
        450
);
        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        navbar.getChildren()
                .addAll(
                        left,
                        center,
                        right
                );

        return navbar;
    }

    // =========================================================
    // CHECK FARMER ID
    // =========================================================

    private boolean checkFarmerId() {

        if (farmerId <= 0) {

            System.out.println(
                    "ERROR: Farmer ID is missing."
            );

            System.out.println(
                    "Firebase UID = "
                            + firebaseUid
            );

            return false;
        }

        if (firebaseUid == null ||
                firebaseUid.trim().isEmpty()) {

            System.out.println(
                    "WARNING: Firebase UID is missing."
            );

            // We don't stop Marketplace here because
            // farmerId is the value required for products.
        }

        return true;
    }

    // =========================================================
    // NORMAL STYLE
    // =========================================================

    private String navButtonNormal() {

        return
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#aaaaaa;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 5 0;";
    }

    // =========================================================
    // ACTIVE STYLE
    // =========================================================

    private String navButtonActive() {

        return
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68d34a;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 5 0;" +
                "-fx-border-color:#68d34a;" +
                "-fx-border-width:0 0 2 0;";
    }

    // =========================================================
    // HOVER STYLE
    // =========================================================

    private String navButtonHover() {

        return
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68d34a;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 5 0;" +
                "-fx-border-color:#68d34a;" +
                "-fx-border-width:0 0 2 0;";
    }

    // =========================================================
    // CREATE NAV BUTTON
    // =========================================================

    public Button navButton(
            String text) {

        Button button =
                new Button(
                        text
                );

        String normal =
                navButtonNormal();

        String hover =
                navButtonHover();

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(
                e -> button.setStyle(
                        hover
                )
        );

        button.setOnMouseExited(
                e -> button.setStyle(
                        normal
                )
        );

        return button;
    }
}