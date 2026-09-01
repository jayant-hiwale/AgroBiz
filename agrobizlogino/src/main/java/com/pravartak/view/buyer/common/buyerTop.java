// package com.pravartak.view.buyer.common;

// import com.pravartak.view.buyer.Ai;
// import com.pravartak.view.buyer.BuyerHomepage;
// import com.pravartak.view.buyer.BuyerProfilePage;
// import com.pravartak.view.buyer.BuyerMarketPlace;
// import com.pravartak.view.buyer.Watchlist;
// import com.pravartak.view.login.LoginPage;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.HBox;

// public class buyerTop {
//     public HBox createBuyerTop(String currentPage) {

//         HBox navbar = new HBox();
//         navbar.setPadding(new Insets(10, 20, 10, 20));
//         navbar.setAlignment(Pos.CENTER);
//         navbar.setStyle("-fx-background-color: #080c0d;" +"-fx-border-color: #1b2021;" + "-fx-border-width: 0 0 1 0;");

//         // Logo
//         Label logo = new Label("Agro Biz");
//         logo.setStyle("-fx-text-fill: #68d34a;" +"-fx-font-size: 24px;" +"-fx-font-weight: bold;");

//         HBox left = new HBox(logo);
//         left.setAlignment(Pos.CENTER_LEFT);
//         left.setPrefWidth(450);

//         // Navigation
//         Button home =  navButton("Home");
//         Button market = navButton("Market");
//         Button watchlist = navButton("Watchlist");
//         Button Aiadvisor = navButton("AI Advisor");
        

//         if (currentPage.equals("Home")) {
//             home.setStyle(navButtonActive());
//         }
//         home.setOnAction(e -> {
//             BuyerHomepage buyerHomePage = new BuyerHomepage(null);
//             LoginPage.mainStage.setScene(buyerHomePage.getBuyerHomePage());
//         });

//         // Current page
//         if (currentPage.equals("Market")) {
//             market.setStyle(navButtonActive());
//         }
//         market.setOnAction(e -> {
            
//              BuyerMarketPlace marketplace = new BuyerMarketPlace();
//             LoginPage.mainStage.setScene(new Scene(marketplace.getMarketplacePage()));
//          });

//         if (currentPage.equals("Watchlist")) {
//             watchlist.setStyle(navButtonActive());
//         }
//         watchlist.setOnAction(e -> {
//            Watchlist watchlistPage = new Watchlist();
//            LoginPage.mainStage.setScene(watchlistPage.getWatchlistPage());
//         });
        
//          if (currentPage.equals("AI Advisor")) {
//             Aiadvisor.setStyle(navButtonActive());
//         }
//         Aiadvisor.setOnAction(e -> {
//            Ai AiPage = new Ai();
//            LoginPage.mainStage.setScene(AiPage.gatAiScene());
//         });

        
//         HBox center = new HBox(25,home,market,watchlist,Aiadvisor);
//         center.setAlignment(Pos.CENTER);

//         Button profile = navButton("◎ Profile");
//         if (currentPage.equals("◎ Profile")) {
//             profile.setStyle(navButtonActive());
//         }
//         profile.setOnAction(e -> {
//            BuyerProfilePage bpp = new BuyerProfilePage();
//            LoginPage.mainStage.setScene(bpp.getProfilePageScene());
//         });

//         HBox right = new HBox(15,profile);

//         right.setAlignment(Pos.CENTER_RIGHT);
//         right.setPrefWidth(450);

//         navbar.getChildren().addAll(left, center, right);

//         return navbar;
//     }

//     private String navButtonActive() {

//         return "-fx-background-color: transparent;" +
//                 "-fx-text-fill: #68d34a;" +
//                 "-fx-font-size: 13px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-cursor: hand;" +
//                 "-fx-padding: 5 0 5 0;" +
//                 "-fx-border-color: #68d34a;" +
//                 "-fx-border-width: 0 0 2 0;";
//     }

//     public Button navButton(String text) {

//         Button button = new Button(text);

//         String normal = "-fx-background-color: transparent;" +
//                 "-fx-text-fill: #aaaaaa;" +
//                 "-fx-font-size: 13px;" +
//                 "-fx-cursor: hand;" +
//                 "-fx-padding: 5 0 5 0;";

//         String hover = "-fx-background-color: transparent;" +
//                 "-fx-text-fill: #68d34a;" +
//                 "-fx-font-size: 13px;"+
//                 "-fx-cursor: hand;" +
//                 "-fx-font-weight: bold;"+
//                 "-fx-padding: 5 0 5 0;" +
//                 "-fx-border-color: #68d34a;" +
//                 "-fx-border-width: 0 0 2 0;";

//         button.setStyle(normal);
//         button.setOnMouseEntered(e -> {
//             button.setStyle(hover);
//         });
//         button.setOnMouseExited(e -> button.setStyle(normal));

//         return button;
//     }
// }
package com.pravartak.view.buyer.common;

import com.pravartak.view.buyer.Ai;
import com.pravartak.view.buyer.BuyerHomepage;
import com.pravartak.view.buyer.BuyerProfilePage;
import com.pravartak.view.buyer.BuyerMarketPlace;
import com.pravartak.view.buyer.Watchlist;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class buyerTop {

    public HBox createBuyerTop(String currentPage) {

        HBox navbar = new HBox();

        navbar.setPadding(
                new Insets(10, 20, 10, 20)
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

        Label logo = new Label(
                "Agro Biz"
        );

        logo.setStyle(
                "-fx-text-fill:#68d34a;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        HBox left = new HBox(logo);

        left.setAlignment(
                Pos.CENTER_LEFT
        );

        left.setPrefWidth(450);

        // =====================================================
        // NAVIGATION BUTTONS
        // =====================================================

        Button home =
                navButton("Home");

        Button market =
                navButton("Market");

        Button watchlist =
                navButton("Watchlist");

        Button Aiadvisor =
                navButton("AI Advisor");

        // =====================================================
        // HOME
        // =====================================================

        if (currentPage.equals("Home")) {

            home.setStyle(
                    navButtonActive()
            );
        }

        home.setOnAction(e -> {

            BuyerHomepage buyerHomePage =
                    new BuyerHomepage(null);

            LoginPage.mainStage.setScene(
                    buyerHomePage.getBuyerHomePage()
            );
        });

        // =====================================================
        // MARKET
        // =====================================================

        if (currentPage.equals("Market")) {

            market.setStyle(
                    navButtonActive()
            );
        }

        market.setOnAction(e -> {

            BuyerMarketPlace marketplace =
                    new BuyerMarketPlace();

            LoginPage.mainStage.setScene(
                    new Scene(
                            marketplace.getMarketplacePage()
                    )
            );
        });

        // =====================================================
        // WATCHLIST
        // =====================================================

        if (currentPage.equals("Watchlist")) {

            watchlist.setStyle(
                    navButtonActive()
            );
        }

        watchlist.setOnAction(e -> {

            Watchlist watchlistPage =
                    new Watchlist();

            LoginPage.mainStage.setScene(
                    watchlistPage.getWatchlistPage()
            );
        });

        // =====================================================
        // AI ADVISOR
        // =====================================================

        if (currentPage.equals("AI Advisor")) {

            Aiadvisor.setStyle(
                    navButtonActive()
            );
        }

        Aiadvisor.setOnAction(e -> {

            Ai AiPage =
                    new Ai();

            LoginPage.mainStage.setScene(
                    AiPage.gatAiScene()
            );
        });

        // =====================================================
        // CENTER NAVIGATION
        // =====================================================

        HBox center =
                new HBox(
                        25,
                        home,
                        market,
                        watchlist,
                        Aiadvisor
                );

        center.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // PROFILE BUTTON
        // =====================================================

        Button profile =
                navButton("◎ Profile");

        if (currentPage.equals("◎ Profile")) {

            profile.setStyle(
                    navButtonActive()
            );
        }

        profile.setOnAction(e -> {

            BuyerProfilePage bpp =
                    new BuyerProfilePage();

            LoginPage.mainStage.setScene(
                    bpp.getProfilePageScene()
            );
        });

        // =====================================================
// LOGOUT BUTTON
// =====================================================

Button logout = navButton("Logout");

logout.setStyle(
        "-fx-background-color:transparent;" +
        "-fx-text-fill:#aaaaaa;" +
        "-fx-font-size:13px;" +
        "-fx-cursor:hand;" +
        "-fx-padding:5 0 5 0;"
);

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

logout.setOnAction(e -> {

    System.out.println("Buyer logged out.");

    try {

        // Create a new LoginPage instance
        LoginPage loginPage = new LoginPage();

        // Start the login page on the existing stage
        loginPage.start(LoginPage.mainStage);

    } catch (Exception ex) {

        ex.printStackTrace();
    }
});

        // =====================================================
        // RIGHT SIDE
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

        navbar.getChildren().addAll(
                left,
                center,
                right
        );

        return navbar;
    }

    // =====================================================
    // ACTIVE NAV BUTTON
    // =====================================================

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

    // =====================================================
    // NORMAL NAV BUTTON
    // =====================================================

    public Button navButton(String text) {

        Button button =
                new Button(text);

        String normal =
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#aaaaaa;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 5 0;";

        String hover =
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68d34a;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:5 0 5 0;" +
                "-fx-border-color:#68d34a;" +
                "-fx-border-width:0 0 2 0;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e -> {

            button.setStyle(
                    hover
            );
        });

        button.setOnMouseExited(e -> {

            button.setStyle(
                    normal
            );
        });

        return button;
    }

    // =====================================================
    // LOGOUT NORMAL STYLE
    // =====================================================

    private String logoutButtonStyle() {

        return
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#aaaaaa;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 5 0;";
    }

    // =====================================================
    // LOGOUT HOVER STYLE
    // =====================================================

    private String logoutButtonHoverStyle() {

        return
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#ff4d5a;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;" +
                "-fx-padding:5 0 5 0;" +
                "-fx-border-color:#ff4d5a;" +
                "-fx-border-width:0 0 2 0;";
    }
}