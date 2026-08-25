package com.pravartak.view.admin;

import com.pravartak.view.admin.course.AdminLearning;
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

        private Scene AdminPageScene;

        public Scene getAdminPage() {

                BorderPane bp = new BorderPane();

                // =========================
                // LEFT SIDE - SIDEBAR
                // =========================

                VBox sidebar = new VBox();

                sidebar.setPrefWidth(220);

                sidebar.setPadding(
                                new Insets(15, 10, 15, 10));

                sidebar.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-border-color:#1B2021;" +
                                                "-fx-border-width:0 1 0 0;");

                // =========================
                // LOGO
                // =========================

                Label logo = new Label("AgroBiz Hub");

                logo.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:26px;" +
                                                "-fx-font-weight:bold;");

                logo.setPadding(
                                new Insets(10, 10, 20, 10));

                // =========================
                // LIST VIEW
                // =========================

                ListView<String> lv = new ListView<>();

                lv.getItems().addAll(
                                "Dashboard",
                                "Users",
                                "Marketplace",
                                "Manage Course",
                                "Community",
                                "Government Schemes",
                                "Analytics",
                                "Settings");

                lv.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-control-inner-background:#0D1213;" +
                                                "-fx-border-color:transparent;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-cursor: hand;");

                // =========================
                // LIST VIEW CELL STYLE
                // =========================

                lv.setCellFactory(list -> {

                        return new ListCell<String>() {

                                @Override
                                protected void updateItem(
                                                String item,
                                                boolean empty) {

                                        super.updateItem(
                                                        item,
                                                        empty);

                                        if (empty || item == null) {

                                                setText(null);

                                                setStyle(
                                                                "-fx-background-color:#0D1213;");

                                        } else {

                                                setText(item);

                                                // Normal item
                                                setTextFill(
                                                                Color.web("#AAAAAA"));

                                                setStyle(
                                                                "-fx-background-color:#0D1213;" +
                                                                                "-fx-padding:12 15;" +
                                                                                "-fx-font-size:14px;");

                                                // Selected item
                                                if (isSelected()) {

                                                        setTextFill(
                                                                        Color.web("#68D34A"));

                                                        setStyle(
                                                                        "-fx-background-color:#245D35;" +
                                                                                        "-fx-text-fill:#68D34A;" +
                                                                                        "-fx-padding:12 15;" +
                                                                                        "-fx-font-size:14px;" +
                                                                                        "-fx-font-weight:bold;" +
                                                                                        "-fx-background-radius:6;");
                                                }
                                        }
                                }
                        };
                });

                // =========================
                // DEFAULT SELECTION
                // =========================

                lv.getSelectionModel().select(0);

                // =========================
                // LIST VIEW CLICK
                // =========================

                lv.setOnMouseClicked(event -> {

                        String selectedItem = lv.getSelectionModel().getSelectedItem();

                        System.out.println(
                                        "Selected item: " + selectedItem);

                        if (selectedItem.equals("Dashboard")) {

                                Text t1 = new Text(
                                                "Good Morning, Admin 👋");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);

                        }

                        else if (selectedItem.equals("Users")) {

                                Text t1 = new Text("Users");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);

                        }

                        else if (selectedItem.equals("Marketplace")) {

                                Text t1 = new Text("Marketplace");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);

                        }

                        else if (selectedItem.equals("Manage Course")) {

                                bp.setCenter(AdminLearning.getLearningPage());

                        }

                        else if (selectedItem.equals("Community")) {

                                Text t1 = new Text("Community");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);

                        }

                        else if (selectedItem.equals("Government Schemes")) {

                                Text t1 = new Text(
                                                "Government Schemes");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);

                        }

                        else if (selectedItem.equals("Analytics")) {

                                Text t1 = new Text("Analytics");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);

                        }

                        else if (selectedItem.equals("Settings")) {

                                Text t1 = new Text("Settings");

                                t1.setStyle(
                                                "-fx-font-size:34px;" +
                                                                "-fx-font-weight:bold;" +
                                                                "-fx-fill:#EEEEEE;");

                                bp.setCenter(t1);
                        }

                });

                // =========================
                // LOGOUT BUTTON
                // =========================

                Button logout = new Button("⇥   Logout");

                logout.setMaxWidth(Double.MAX_VALUE);

                logout.setAlignment(Pos.CENTER_LEFT);

                logout.setPadding(new Insets(12, 15, 12, 15));

                logout.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#E57373;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:6;");
                logout.setOnAction(e->{
                        LoginPage loginPage =new LoginPage();
                        //LoginPage.mainStage.setScene(loginPage);
                });

                // =========================
                // LOGOUT HOVER
                // =========================

                logout.setOnMouseEntered(event -> {

                        logout.setStyle(
                                        "-fx-background-color:#633333;" +
                                                        "-fx-text-fill:#E57373;" +
                                                        "-fx-font-size:14px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;");

                });

                logout.setOnMouseExited(event -> {

                        logout.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-text-fill:#E57373;" +
                                                        "-fx-font-size:14px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:6;");

                });

                // =========================
                // SIDEBAR GROW
                // =========================

                VBox.setVgrow(
                                lv,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                // logo,
                                lv,
                                logout);

                bp.setLeft(sidebar);

                // ==========================================
                // TOP BAR
                // ==========================================

                HBox topBar = new HBox();

                topBar.setAlignment(Pos.CENTER_RIGHT);

                topBar.setSpacing(25);

                topBar.setPadding(
                                new Insets(8, 30, 8, 30));

                topBar.setPrefHeight(62);

                topBar.setMinHeight(62);

                topBar.setMaxHeight(62);

                topBar.setStyle(
                                "-fx-background-color:#080C0D;" +
                                                "-fx-border-color:#1B2021;" +
                                                "-fx-border-width:0 0 1 0;");

                // =========================
                // SEARCH
                // =========================

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

                Region topSpace = new Region();

                HBox.setHgrow(
                                topSpace,
                                Priority.ALWAYS);

                // =========================
                // NOTIFICATION
                // =========================

                Label notification = new Label("♧");

                notification.setStyle(
                                "-fx-text-fill:#AAAAAA;" +
                                                "-fx-font-size:23px;");

                // =========================
                // PROFILE
                // =========================

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

                topBar.getChildren().addAll(
                                logo,
                                topSpace,
                                search,
                                notification,
                                profile);

                bp.setTop(topBar);

                // =========================
                // DEFAULT PAGE
                // =========================

                Text welcome = new Text(
                                "Good Morning, Admin 👋");

                welcome.setStyle(
                                "-fx-font-size:34px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-fill:#EEEEEE;");

                bp.setCenter(welcome);

                // =========================
                // ROOT STYLE
                // =========================

                bp.setStyle("-fx-background-color:#080C0D;");

                // =========================
                // SCENE
                // =========================

                Scene sc = new Scene(bp,1000,700);
                AdminPageScene = sc;

                return AdminPageScene;
                

        }
}