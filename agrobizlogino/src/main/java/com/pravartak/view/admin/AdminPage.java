package com.pravartak.view.admin;

import com.pravartak.view.admin.course.AdminLearning;
import com.pravartak.view.admin.scheme.SchemeTab;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AdminPage {

        private Scene adminPageScene;

        // =========================================================
        // MAIN ADMIN PAGE
        // =========================================================

        public Scene getAdminPage(String currentPage) {

                BorderPane bp = new BorderPane();

                // =====================================================
                // DEFAULT PAGE
                // =====================================================

                if (currentPage == null || currentPage.isEmpty()) {
                        currentPage = "Dashboard";
                }

                // =====================================================
                // SIDEBAR
                // =====================================================

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(220);

                sidebar.setPadding(
                                new Insets(15, 10, 15, 10));

                sidebar.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#1B2021;" +
                                                "-fx-border-width:0 1 0 0;");

                // =====================================================
                // LIST VIEW
                // =====================================================

                ListView<String> lv = new ListView<>();

                lv.getItems().addAll(
                                "Dashboard",
                                "Users",
                                "Marketplace",
                                "Manage Course",
                                "Community",
                                "Government Schemes",
                                "Settings");

                lv.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-control-inner-background:#0D1213;" +
                                                "-fx-border-color:transparent;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // LIST VIEW CELL STYLE
                // =====================================================

                lv.setCellFactory(list -> {

                        ListCell<String> cell = new ListCell<String>() {

                                @Override
                                protected void updateItem(String item, boolean empty) {

                                        super.updateItem(item, empty);

                                        if (empty || item == null) {

                                                setText(null);

                                                setStyle("-fx-background-color:#0D1213;");

                                        } else {

                                                setText(item);
                                                updateCellStyle();
                                        }
                                }

                                private void updateCellStyle() {

                                        if (isSelected()) {

                                                setTextFill(Color.web("#68D34A"));

                                                setStyle(
                                                                "-fx-background-color:#245D35;" +
                                                                                "-fx-text-fill:#68D34A;" +
                                                                                "-fx-padding:12 15;" +
                                                                                "-fx-font-size:14px;" +
                                                                                "-fx-font-weight:bold;" +
                                                                                "-fx-background-radius:6;");

                                        } else {

                                                setTextFill(Color.web("#AAAAAA"));

                                                setStyle("-fx-background-color:#0D1213;" + "-fx-padding:12 15;"
                                                                + "-fx-font-size:14px;");
                                        }
                                }
                        };

                        return cell;
                });

                // =====================================================
                // SIDEBAR GROW
                // =====================================================

                VBox.setVgrow(
                                lv,
                                Priority.ALWAYS);

                // =====================================================
                // LOGOUT BUTTON
                // =====================================================

                Button logout = new Button(
                                "⇥   Logout");

                logout.setMaxWidth(
                                Double.MAX_VALUE);

                logout.setAlignment(
                                Pos.CENTER_LEFT);

                logout.setPadding(
                                new Insets(
                                                12,
                                                15,
                                                12,
                                                15));

                logout.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#E57373;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // LOGOUT ACTION
                // =====================================================

                logout.setOnAction(e -> {
                        try{
                                 LoginPage loginPage = new LoginPage();
                                 
                                loginPage.start(LoginPage.mainStage);

                        }catch(Exception ex){
                                ex.printStackTrace();
                        }
                       
                });

                // =====================================================
                // LOGOUT HOVER
                // =====================================================

                logout.setOnMouseEntered(event -> {

                        logout.setStyle(
                                        "-fx-background-color:#633333;" +
                                                        "-fx-text-fill:#E57373;" +
                                                        "-fx-font-size:14px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                });

                logout.setOnMouseExited(event -> {

                        logout.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-text-fill:#E57373;" +
                                                        "-fx-font-size:14px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-cursor:hand;");
                });

                // =====================================================
                // SIDEBAR COMPONENTS
                // =====================================================

                sidebar.getChildren().addAll(
                                lv,
                                logout);

                bp.setLeft(sidebar);

                // =====================================================
                // TOP BAR
                // =====================================================

                HBox topBar = new HBox();

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                topBar.setSpacing(20);

                topBar.setPadding(
                                new Insets(
                                                8,
                                                30,
                                                8,
                                                30));

                topBar.setPrefHeight(62);

                topBar.setMinHeight(62);

                topBar.setMaxHeight(62);

                topBar.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-border-color:#1B2021;" +
                                                "-fx-border-width:0 0 1 0;");

                // =====================================================
                // HEADER LOGO
                // =====================================================

                Label headerLogo = new Label("AgroBiz Hub");

                headerLogo.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:20px;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // TOP SPACE
                // =====================================================

                Region topSpace = new Region();

                HBox.setHgrow(
                                topSpace,
                                Priority.ALWAYS);

                // =====================================================
                // SEARCH
                // =====================================================

                TextField search = new TextField();

                search.setPromptText(
                                "⌕   Search across AgroBiz Hub...");

                search.setPrefWidth(440);

                search.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-prompt-text-fill:#777777;" +
                                                "-fx-border-color:#242B2C;" +
                                                "-fx-border-radius:22;" +
                                                "-fx-background-radius:22;" +
                                                "-fx-padding:10 18;");

                // =====================================================
                // NOTIFICATION
                // =====================================================

                Label notification = new Label("♧");

                notification.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:23px;" +
                                                "-fx-cursor:hand;");

                // =====================================================
                // PROFILE
                // =====================================================

                Label profile = new Label("A");

                profile.setAlignment(
                                Pos.CENTER);

                profile.setPrefSize(
                                35,
                                35);

                profile.setStyle(
                                "-fx-background-color:#245D35;" +
                                                "-fx-background-radius:50%;" +
                                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-weight:bold;");

                // =====================================================
                // TOP BAR COMPONENTS
                // =====================================================

                topBar.getChildren().addAll(
                                headerLogo,
                                topSpace,
                                search,
                                notification,
                                profile);

                bp.setTop(topBar);

                // =====================================================
                // SHOW SELECTED PAGE
                // =====================================================

                showPage(
                                currentPage,
                                bp);

                // =====================================================
                // SELECT CURRENT PAGE
                // IMPORTANT: DO THIS AFTER CELL FACTORY
                // =====================================================

                int selectedIndex = lv.getItems().indexOf(currentPage);

                if (selectedIndex >= 0) {

                        lv.getSelectionModel().select(selectedIndex);
                } else {

                        lv.getSelectionModel().select(0);
                }

                // =====================================================
                // LIST VIEW SELECTION
                // =====================================================

                lv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                        if (newValue == null) {
                                return;
                        }

                        System.out.println(
                                        "Selected item: "
                                                        + newValue);

                        showPage(
                                        newValue,
                                        bp);
                });

                // =====================================================
                // ROOT STYLE
                // =====================================================

                bp.setStyle("-fx-background-color:#080C0D;");

                // =====================================================
                // SCENE
                // =====================================================

                adminPageScene = new Scene(
                                bp,
                                1000,
                                700);

                return adminPageScene;
        }

        // =========================================================
        // SHOW PAGE
        // =========================================================

        private void showPage(
                        String page,
                        BorderPane bp) {

                switch (page) {

                        // =================================================
                        // DASHBOARD
                        // =================================================

                        case "Dashboard":

                                Text dashboard = createPageTitle(
                                                "Good Morning, Admin 👋");

                                bp.setCenter(dashboard);

                                break;

                        // =================================================
                        // USERS
                        // =================================================

                        case "Users":

                                Text users = createPageTitle(
                                                "Users");

                                bp.setCenter(users);

                                break;

                        // =================================================
                        // MARKETPLACE
                        // =================================================

                        case "Marketplace":

                                Text marketplace = createPageTitle(
                                                "Marketplace");

                                bp.setCenter(marketplace);

                                break;

                        // =================================================
                        // MANAGE COURSE
                        // =================================================

                        case "Manage Course":

                                bp.setCenter(AdminLearning.getLearningPage());

                                break;

                        // =================================================
                        // COMMUNITY
                        // =================================================

                        case "Community":

                                Text community = createPageTitle(
                                                "Community");

                                bp.setCenter(community);

                                break;

                        // =================================================
                        // GOVERNMENT SCHEMES
                        // =================================================

                        case "Government Schemes":

                                Text schemes = createPageTitle( "Government Schemes");

                                bp.setCenter(schemes);

                                break;

                        // =================================================
                        // SETTINGS
                        // =================================================

                        case "Settings":

                                Text settings = createPageTitle(
                                                "Settings");

                                bp.setCenter(settings);

                                break;

                        // =================================================
                        // DEFAULT
                        // =================================================

                        default:

                                Text defaultPage = createPageTitle(
                                                "Good Morning, Admin 👋");

                                bp.setCenter(
                                                defaultPage);

                                break;
                }
        }

        // =========================================================
        // CREATE PAGE TITLE
        // =========================================================

        private Text createPageTitle(
                        String title) {

                Text text = new Text(title);

                text.setStyle(
                                "-fx-font-size:34px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-fill:#EEEEEE;");

                return text;
        }
}