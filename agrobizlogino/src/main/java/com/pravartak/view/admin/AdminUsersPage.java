package com.pravartak.view.admin;

import com.pravartak.controller.admincontroller.UserController;
import com.pravartak.model.admin.User;

import javafx.application.Platform;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.util.Callback;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;

/**
 * ============================================================
 * ADMIN USERS PAGE
 * ============================================================
 *
 * Displays:
 *
 * 1. Total users
 * 2. Total farmers
 * 3. Total buyers
 * 4. Farmer growth graph
 * 5. Buyer growth graph
 * 6. All users table
 * 7. Remove user functionality
 *
 * ============================================================
 */
public class AdminUsersPage {

        // ============================================================
        // CONTROLLER
        // ============================================================

        private final UserController controller;

        // ============================================================
        // STAT LABELS
        // ============================================================

        private Label totalUsersLabel;
        private Label farmersLabel;
        private Label buyersLabel;

        // ============================================================
        // TABLE
        // ============================================================

        private TableView<User> userTable;

        private Label userCountLabel;

        // ============================================================
        // GRAPHS
        // ============================================================

        private LineChart<String, Number> farmerGraph;
        private LineChart<String, Number> buyerGraph;

        // ============================================================
        // CONSTRUCTOR
        // ============================================================

        public AdminUsersPage() {

                controller = new UserController();
        }

        // ============================================================
        // MAIN USERS PAGE
        // ============================================================

        public VBox getUsersPage() {

                // ========================================================
                // ROOT
                // ========================================================

                VBox root = new VBox();

                root.setStyle(
                                "-fx-background-color:#080C0D;");

                // ========================================================
                // CONTENT
                // ========================================================

                VBox content = new VBox(20);

                content.setPadding(
                                new Insets(30));

                content.setStyle(
                                "-fx-background-color:#080C0D;");

                // ========================================================
                // TITLE
                // ========================================================

                Label title = new Label(
                                "User Management");

                title.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:30px;" +
                                                "-fx-font-weight:bold;");

                // ========================================================
                // SUBTITLE
                // ========================================================

                Label subtitle = new Label(
                                "View, monitor and manage all farmers and buyers.");

                subtitle.setStyle(
                                "-fx-text-fill:#8F999A;" +
                                                "-fx-font-size:14px;");

                // ========================================================
                // STAT CARDS
                // ========================================================

                HBox stats = new HBox(15);

                stats.setAlignment(
                                Pos.CENTER);

                // --------------------------------------------------------
                // LABELS
                // --------------------------------------------------------

                totalUsersLabel = new Label("0");

                farmersLabel = new Label("0");

                buyersLabel = new Label("0");

                // --------------------------------------------------------
                // CARDS
                // --------------------------------------------------------

                VBox totalCard = createStatCard(
                                "👥",
                                "Total Users",
                                totalUsersLabel);

                VBox farmerCard = createStatCard(
                                "👨‍🌾",
                                "Farmers",
                                farmersLabel);

                VBox buyerCard = createStatCard(
                                "🛒",
                                "Buyers",
                                buyersLabel);

                stats.getChildren().addAll(
                                totalCard,
                                farmerCard,
                                buyerCard);

                // ========================================================
                // GRAPHS
                // ========================================================

                farmerGraph = createFarmerGrowthGraph();

                buyerGraph = createBuyerGrowthGraph();

                HBox graphs = new HBox(15);

                graphs.setAlignment(
                                Pos.CENTER);

                graphs.getChildren().addAll(
                                farmerGraph,
                                buyerGraph);

                HBox.setHgrow(
                                farmerGraph,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                buyerGraph,
                                Priority.ALWAYS);

                // ========================================================
                // USER TABLE
                // ========================================================

                VBox tableBox = createUserTable();

                // ========================================================
                // ADD CONTENT
                // ========================================================

                content.getChildren().addAll(
                                title,
                                subtitle,
                                stats,
                                graphs,
                                tableBox);

                // ========================================================
                // LOAD DATA
                // ========================================================

                refreshAllData();

                // ========================================================
                // SCROLL PANE
                // ========================================================

                ScrollPane scrollPane = new ScrollPane(
                                content);

                scrollPane.setFitToWidth(
                                true);

                scrollPane.setFitToHeight(
                                false);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setStyle(
                                "-fx-background:#080C0D;" +
                                                "-fx-background-color:#080C0D;" +
                                                "-fx-border-color:transparent;");

                // ========================================================
                // ROOT
                // ========================================================

                root.getChildren().add(
                                scrollPane);

                VBox.setVgrow(
                                scrollPane,
                                Priority.ALWAYS);

                return root;
        }

        // ============================================================
        // STAT CARD
        // ============================================================

        private VBox createStatCard(
                        String icon,
                        String title,
                        Label value) {

                VBox card = new VBox(8);

                card.setPrefWidth(
                                300);

                card.setMinHeight(
                                130);

                card.setPadding(
                                new Insets(20));

                card.setAlignment(
                                Pos.CENTER_LEFT);

                card.setStyle(
                                "-fx-background-color:#101718;" +
                                                "-fx-border-color:#293334;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // ========================================================
                // ICON
                // ========================================================

                Label iconLabel = new Label(icon);

                iconLabel.setStyle(
                                "-fx-font-size:25px;");

                // ========================================================
                // TITLE
                // ========================================================

                Label titleLabel = new Label(title);

                titleLabel.setStyle(
                                "-fx-text-fill:#8F999A;" +
                                                "-fx-font-size:14px;");

                // ========================================================
                // VALUE
                // ========================================================

                value.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:30px;" +
                                                "-fx-font-weight:bold;");

                // ========================================================
                // ADD
                // ========================================================

                card.getChildren().addAll(
                                iconLabel,
                                titleLabel,
                                value);

                HBox.setHgrow(
                                card,
                                Priority.ALWAYS);

                return card;
        }

        // ============================================================
        // FARMER GRAPH
        // ============================================================

        private LineChart<String, Number> createFarmerGrowthGraph() {

                CategoryAxis xAxis = new CategoryAxis();

                NumberAxis yAxis = new NumberAxis();

                xAxis.setLabel(
                                "Month");

                yAxis.setLabel(
                                "Registered Farmers");

                LineChart<String, Number> chart = new LineChart<>(
                                xAxis,
                                yAxis);

                chart.setTitle(
                                "Farmer Growth");

                chart.setAnimated(
                                false);

                chart.setCreateSymbols(
                                true);

                chart.setLegendVisible(
                                false);

                chart.setPrefHeight(
                                330);

                chart.setMinHeight(
                                330);

                chart.setPrefWidth(
                                500);

                chart.setMinWidth(
                                400);

                chart.setStyle(
                                "-fx-background-color:#101718;" +
                                                "-fx-border-color:#293334;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                return chart;
        }

        // ============================================================
        // BUYER GRAPH
        // ============================================================

        private LineChart<String, Number> createBuyerGrowthGraph() {

                CategoryAxis xAxis = new CategoryAxis();

                NumberAxis yAxis = new NumberAxis();

                xAxis.setLabel(
                                "Month");

                yAxis.setLabel(
                                "Registered Buyers");

                LineChart<String, Number> chart = new LineChart<>(
                                xAxis,
                                yAxis);

                chart.setTitle(
                                "Buyer Growth");

                chart.setAnimated(
                                false);

                chart.setCreateSymbols(
                                true);

                chart.setLegendVisible(
                                false);

                chart.setPrefHeight(
                                330);

                chart.setMinHeight(
                                330);

                chart.setPrefWidth(
                                500);

                chart.setMinWidth(
                                400);

                chart.setStyle(
                                "-fx-background-color:#101718;" +
                                                "-fx-border-color:#293334;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                return chart;
        }

        // ============================================================
        // REFRESH ALL DATA
        // ============================================================

        private void refreshAllData() {

                try {

                        List<User> allUsers = controller.getAllUsers();

                        List<User> farmers = controller.getFarmers();

                        List<User> buyers = controller.getBuyers();

                        // ----------------------------------------------------
                        // COUNTS
                        // ----------------------------------------------------

                        if (totalUsersLabel != null) {

                                totalUsersLabel.setText(
                                                String.valueOf(
                                                                allUsers.size()));
                        }

                        if (farmersLabel != null) {

                                farmersLabel.setText(
                                                String.valueOf(
                                                                farmers.size()));
                        }

                        if (buyersLabel != null) {

                                buyersLabel.setText(
                                                String.valueOf(
                                                                buyers.size()));
                        }

                        // ----------------------------------------------------
                        // TABLE
                        // ----------------------------------------------------

                        if (userTable != null) {

                                userTable.setItems(
                                                FXCollections.observableArrayList(
                                                                allUsers));
                        }

                        // ----------------------------------------------------
                        // USER COUNT
                        // ----------------------------------------------------

                        updateUserCount(
                                        allUsers.size());

                        // ----------------------------------------------------
                        // GRAPHS
                        // ----------------------------------------------------

                        updateFarmerGraph(
                                        farmers);

                        updateBuyerGraph(
                                        buyers);

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Unable to Load Users",
                                        "Something went wrong while loading user data.");
                }
        }

        // ============================================================
        // CREATE USER TABLE
        // ============================================================

        private VBox createUserTable() {

                VBox box = new VBox(15);

                box.setPadding(
                                new Insets(18));

                box.setStyle(
                                "-fx-background-color:#101718;" +
                                                "-fx-border-color:#293334;" +
                                                "-fx-border-radius:14;" +
                                                "-fx-background-radius:14;");

                // ========================================================
                // TABLE TITLE HEADER
                // ========================================================

                HBox header = new HBox();

                header.setAlignment(
                                Pos.CENTER_LEFT);

                // ========================================================
                // TITLE SECTION
                // ========================================================

                VBox titleBox = new VBox(3);

                Label title = new Label(
                                "All Users");

                title.setStyle(
                                "-fx-text-fill:#EEEEEE;" +
                                                "-fx-font-size:20px;" +
                                                "-fx-font-weight:bold;");

                Label description = new Label(
                                "Manage registered farmers and buyers");

                description.setStyle(
                                "-fx-text-fill:#737D7E;" +
                                                "-fx-font-size:12px;");

                titleBox.getChildren().addAll(
                                title,
                                description);

                // ========================================================
                // USER COUNT
                // ========================================================

                userCountLabel = new Label(
                                "0 Users");

                userCountLabel.setStyle(
                                "-fx-text-fill:#68D34A;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-color:#163D24;" +
                                                "-fx-background-radius:20;" +
                                                "-fx-padding:7 14 7 14;");

                header.getChildren().addAll(
                                titleBox,
                                createSpacer(),
                                userCountLabel);

                // ========================================================
                // TABLE
                // ========================================================

                userTable = new TableView<>();

                userTable.setPrefHeight(
                                390);

                userTable.setMinHeight(
                                390);

                userTable.setColumnResizePolicy(
                                TableView.CONSTRAINED_RESIZE_POLICY);

                Label emptyLabel = new Label(
                                "No users found");

                emptyLabel.setStyle(
                                "-fx-text-fill:#737D7E;" +
                                                "-fx-font-size:13px;");

                userTable.setPlaceholder(
                                emptyLabel);

                // ========================================================
                // TABLE BASE STYLE
                // ========================================================

                userTable.setStyle(
                                "-fx-background-color:#0D1213;" +
                                                "-fx-control-inner-background:#0D1213;" +
                                                "-fx-table-cell-border-color:#202829;" +
                                                "-fx-border-color:#293334;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-padding:0;");

                // ========================================================
                // NAME COLUMN
                // ========================================================

                TableColumn<User, String> nameColumn = new TableColumn<>(
                                "Name");

                nameColumn.setCellValueFactory(
                                data -> new SimpleStringProperty(
                                                safe(
                                                                data.getValue()
                                                                                .getFullName())));

                nameColumn.setPrefWidth(
                                190);

                nameColumn.setMinWidth(
                                150);

                styleTextColumn(
                                nameColumn);

                // ========================================================
                // EMAIL COLUMN
                // ========================================================

                TableColumn<User, String> emailColumn = new TableColumn<>(
                                "Email");

                emailColumn.setCellValueFactory(
                                data -> new SimpleStringProperty(
                                                safe(
                                                                data.getValue()
                                                                                .getEmail())));

                emailColumn.setPrefWidth(
                                260);

                emailColumn.setMinWidth(
                                200);

                styleTextColumn(
                                emailColumn);

                // ========================================================
                // ROLE COLUMN
                // ========================================================

                TableColumn<User, String> roleColumn = new TableColumn<>(
                                "Role");

                roleColumn.setCellValueFactory(
                                data -> new SimpleStringProperty(
                                                safe(
                                                                data.getValue()
                                                                                .getRole())));

                roleColumn.setPrefWidth(
                                130);

                roleColumn.setMinWidth(
                                110);

                roleColumn.setCellFactory(
                                column -> new TableCell<User, String>() {

                                        @Override
                                        protected void updateItem(
                                                        String role,
                                                        boolean empty) {

                                                super.updateItem(
                                                                role,
                                                                empty);

                                                if (empty ||
                                                                role == null) {

                                                        setText(null);

                                                        setGraphic(null);

                                                        setStyle(
                                                                        "-fx-background-color:#0D1213;");

                                                        return;
                                                }

                                                Label badge = new Label(
                                                                role.toUpperCase());

                                                if (role.equalsIgnoreCase(
                                                                "FARMER")) {

                                                        badge.setStyle(
                                                                        "-fx-text-fill:#68D34A;" +
                                                                                        "-fx-background-color:#163D24;"
                                                                                        +
                                                                                        "-fx-background-radius:20;" +
                                                                                        "-fx-padding:5 12 5 12;" +
                                                                                        "-fx-font-size:11px;" +
                                                                                        "-fx-font-weight:bold;");

                                                } else {

                                                        badge.setStyle(
                                                                        "-fx-text-fill:#63B3ED;" +
                                                                                        "-fx-background-color:#102D3D;"
                                                                                        +
                                                                                        "-fx-background-radius:20;" +
                                                                                        "-fx-padding:5 12 5 12;" +
                                                                                        "-fx-font-size:11px;" +
                                                                                        "-fx-font-weight:bold;");
                                                }

                                                setGraphic(
                                                                badge);

                                                setText(
                                                                null);

                                                setAlignment(
                                                                Pos.CENTER_LEFT);

                                                setStyle(
                                                                "-fx-background-color:#0D1213;" +
                                                                                "-fx-border-color:transparent;");
                                        }
                                });

                // ========================================================
                // REGISTERED COLUMN
                // ========================================================

                TableColumn<User, String> dateColumn = new TableColumn<>(
                                "Registered");

                dateColumn.setPrefWidth(
                                150);

                dateColumn.setMinWidth(
                                130);

                dateColumn.setCellValueFactory(
                                data -> {

                                        User user = data.getValue();

                                        if (user == null ||
                                                        user.getCreatedAt() == null) {

                                                return new SimpleStringProperty(
                                                                "Unknown");
                                        }

                                        try {

                                                LocalDate date = user.getCreatedAt()
                                                                .toDate()
                                                                .toInstant()
                                                                .atZone(
                                                                                ZoneId.systemDefault())
                                                                .toLocalDate();

                                                return new SimpleStringProperty(
                                                                date.toString());

                                        } catch (Exception e) {

                                                return new SimpleStringProperty(
                                                                "Unknown");
                                        }
                                });

                styleTextColumn(
                                dateColumn);

                // ========================================================
                // ACTION COLUMN
                // ========================================================

                TableColumn<User, Void> actionColumn = new TableColumn<>(
                                "Action");

                actionColumn.setPrefWidth(
                                125);

                actionColumn.setMinWidth(
                                110);

                actionColumn.setCellFactory(
                                new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {

                                        @Override
                                        public TableCell<User, Void> call(
                                                        TableColumn<User, Void> param) {

                                                return new TableCell<User, Void>() {

                                                        private final Button removeButton = new Button(
                                                                        "Remove");

                                                        {

                                                                removeButton.setPrefHeight(
                                                                                30);

                                                                removeButton.setPrefWidth(
                                                                                82);

                                                                setNormalRemoveStyle(
                                                                                removeButton);

                                                                removeButton.setOnMouseEntered(
                                                                                e -> setHoverRemoveStyle(
                                                                                                removeButton));

                                                                removeButton.setOnMouseExited(
                                                                                e -> setNormalRemoveStyle(
                                                                                                removeButton));

                                                                removeButton.setOnAction(
                                                                                event -> {

                                                                                        if (getIndex() < 0 ||
                                                                                                        getIndex() >= getTableView()
                                                                                                                        .getItems()
                                                                                                                        .size()) {

                                                                                                return;
                                                                                        }

                                                                                        User user = getTableView()
                                                                                                        .getItems()
                                                                                                        .get(
                                                                                                                        getIndex());

                                                                                        showDeleteConfirmation(
                                                                                                        user);
                                                                                });
                                                        }

                                                        @Override
                                                        protected void updateItem(
                                                                        Void item,
                                                                        boolean empty) {

                                                                super.updateItem(
                                                                                item,
                                                                                empty);

                                                                if (empty) {

                                                                        setGraphic(
                                                                                        null);

                                                                } else {

                                                                        setGraphic(
                                                                                        removeButton);
                                                                }

                                                                setAlignment(
                                                                                Pos.CENTER);

                                                                setStyle(
                                                                                "-fx-background-color:#0D1213;" +
                                                                                                "-fx-border-color:transparent;");
                                                        }
                                                };
                                        }
                                });

                // ========================================================
                // ADD COLUMNS
                // ========================================================

                userTable.getColumns().addAll(
                                nameColumn,
                                emailColumn,
                                roleColumn,
                                dateColumn,
                                actionColumn);

                // ========================================================
                // ROW FACTORY
                // ========================================================

                userTable.setRowFactory(
                                tableView -> {

                                        TableRow<User> row = new TableRow<>();

                                        row.setPrefHeight(
                                                        48);

                                        row.setStyle(
                                                        "-fx-background-color:#0D1213;");

                                        row.setOnMouseEntered(
                                                        event -> {

                                                                if (!row.isEmpty()) {

                                                                        row.setStyle(
                                                                                        "-fx-background-color:#172021;");
                                                                }
                                                        });

                                        row.setOnMouseExited(
                                                        event -> {

                                                                if (!row.isEmpty()) {

                                                                        row.setStyle(
                                                                                        "-fx-background-color:#0D1213;");
                                                                }
                                                        });

                                        return row;
                                });

                // ========================================================
                // ADD TABLE
                // ========================================================

                box.getChildren().addAll(
                                header,
                                userTable);

                VBox.setVgrow(
                                userTable,
                                Priority.ALWAYS);

                // ========================================================
                // STYLE TABLE HEADER
                // ========================================================

                /*
                 * JavaFX creates the actual column header nodes internally.
                 * Therefore we style them after the TableView skin has been
                 * created.
                 */
                Platform.runLater(
                                this::styleTableHeader);

                return box;
        }

        // ============================================================
        // STYLE TABLE HEADER
        // ============================================================

        private void styleTableHeader() {

                if (userTable == null) {
                        return;
                }

                // ========================================================
                // COLUMN HEADER BACKGROUND
                // ========================================================

                for (Node node : userTable.lookupAll(
                                ".column-header")) {

                        node.setStyle(
                                        "-fx-background-color:#16201A;" +
                                                        "-fx-border-color:#293334;" +
                                                        "-fx-border-width:0 1 1 0;" +
                                                        "-fx-padding:0;");
                }

                // ========================================================
                // HEADER BACKGROUND
                // ========================================================

                Node headerBackground = userTable.lookup(
                                ".column-header-background");

                if (headerBackground != null) {

                        headerBackground.setStyle(
                                        "-fx-background-color:#16201A;" +
                                                        "-fx-border-color:#293334;" +
                                                        "-fx-border-width:0 0 1 0;");
                }

                // ========================================================
                // FILLER
                // ========================================================

                Node filler = userTable.lookup(
                                ".filler");

                if (filler != null) {

                        filler.setStyle(
                                        "-fx-background-color:#16201A;" +
                                                        "-fx-border-color:#293334;" +
                                                        "-fx-border-width:0 0 1 0;");
                }

                // ========================================================
                // HEADER LABELS
                // ========================================================

                for (Node node : userTable.lookupAll(
                                ".column-header .label")) {

                        node.setStyle(
                                        "-fx-text-fill:#D6DDDE;" +
                                                        "-fx-font-size:12px;" +
                                                        "-fx-font-weight:bold;");
                }

                // ========================================================
                // SORT ARROW
                // ========================================================

                for (Node node : userTable.lookupAll(
                                ".arrow")) {

                        node.setStyle(
                                        "-fx-background-color:#68D34A;");
                }
        }

        // ============================================================
        // NORMAL REMOVE BUTTON STYLE
        // ============================================================

        private void setNormalRemoveStyle(
                        Button button) {

                button.setStyle(
                                "-fx-background-color:#3A1517;" +
                                                "-fx-text-fill:#FF6B6B;" +
                                                "-fx-border-color:#6B2528;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");
        }

        // ============================================================
        // HOVER REMOVE BUTTON STYLE
        // ============================================================

        private void setHoverRemoveStyle(
                        Button button) {

                button.setStyle(
                                "-fx-background-color:#6B2528;" +
                                                "-fx-text-fill:#FFFFFF;" +
                                                "-fx-border-color:#FF6B6B;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-cursor:hand;");
        }

        // ============================================================
        // STYLE TEXT COLUMN
        // ============================================================

        private void styleTextColumn(
                        TableColumn<User, String> column) {

                column.setCellFactory(
                                col -> new TableCell<User, String>() {

                                        @Override
                                        protected void updateItem(
                                                        String item,
                                                        boolean empty) {

                                                super.updateItem(
                                                                item,
                                                                empty);

                                                if (empty ||
                                                                item == null) {

                                                        setText(
                                                                        null);

                                                } else {

                                                        setText(
                                                                        item);
                                                }

                                                setStyle(
                                                                "-fx-background-color:#0D1213;" +
                                                                                "-fx-text-fill:#D6DDDE;" +
                                                                                "-fx-font-size:12px;" +
                                                                                "-fx-padding:0 10 0 10;" +
                                                                                "-fx-border-color:transparent;");

                                                setAlignment(
                                                                Pos.CENTER_LEFT);
                                        }
                                });
        }

        // ============================================================
        // UPDATE USER COUNT
        // ============================================================

        private void updateUserCount(
                        int count) {

                if (userCountLabel == null) {
                        return;
                }

                userCountLabel.setText(
                                count +
                                                (count == 1
                                                                ? " User"
                                                                : " Users"));
        }

        // ============================================================
        // UPDATE FARMER GRAPH
        // ============================================================

        private void updateFarmerGraph(
                        List<User> farmers) {

                if (farmerGraph == null) {
                        return;
                }

                farmerGraph.getData().clear();

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                series.setName(
                                "Farmers");

                YearMonth currentMonth = YearMonth.now();

                for (int i = 5; i >= 0; i--) {

                        YearMonth month = currentMonth.minusMonths(i);

                        int count = getCumulativeCount(
                                        farmers,
                                        month);

                        series.getData().add(
                                        new XYChart.Data<>(
                                                        getMonthName(month),
                                                        count));
                }

                farmerGraph.getData().add(
                                series);
        }

        // ============================================================
        // UPDATE BUYER GRAPH
        // ============================================================

        private void updateBuyerGraph(
                        List<User> buyers) {

                if (buyerGraph == null) {
                        return;
                }

                buyerGraph.getData().clear();

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                series.setName(
                                "Buyers");

                YearMonth currentMonth = YearMonth.now();

                for (int i = 5; i >= 0; i--) {

                        YearMonth month = currentMonth.minusMonths(i);

                        int count = getCumulativeCount(
                                        buyers,
                                        month);

                        series.getData().add(
                                        new XYChart.Data<>(
                                                        getMonthName(month),
                                                        count));
                }

                buyerGraph.getData().add(
                                series);
        }

        // ============================================================
        // MONTH NAME
        // ============================================================

        private String getMonthName(
                        YearMonth month) {

                String monthName = month.getMonth()
                                .toString();

                return monthName.substring(
                                0,
                                1).toUpperCase()
                                +
                                monthName.substring(
                                                1,
                                                3).toLowerCase();
        }

        // ============================================================
        // CUMULATIVE COUNT
        // ============================================================

        private int getCumulativeCount(
                        List<User> users,
                        YearMonth selectedMonth) {

                if (users == null ||
                                users.isEmpty()) {

                        return 0;
                }

                int count = 0;

                for (User user : users) {

                        if (user == null ||
                                        user.getCreatedAt() == null) {

                                continue;
                        }

                        try {

                                LocalDate date = user.getCreatedAt()
                                                .toDate()
                                                .toInstant()
                                                .atZone(
                                                                ZoneId.systemDefault())
                                                .toLocalDate();

                                YearMonth userMonth = YearMonth.from(
                                                date);

                                if (!userMonth.isAfter(
                                                selectedMonth)) {

                                        count++;
                                }

                        } catch (Exception e) {

                                System.err.println(
                                                "Unable to process user date.");

                                e.printStackTrace();
                        }
                }

                return count;
        }

        // ============================================================
        // DELETE CONFIRMATION
        // ============================================================

        private void showDeleteConfirmation(
                        User user) {

                if (user == null) {
                        return;
                }

                String userName = safe(
                                user.getFullName());

                Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION);

                alert.setTitle(
                                "Remove User");

                alert.setHeaderText(
                                "Remove this user?");

                alert.setContentText(
                                "Are you sure you want to remove "
                                                +
                                                userName
                                                +
                                                "?\n\n"
                                                +
                                                "This action will permanently remove "
                                                +
                                                "the user's AgroBiz profile.");

                styleAlert(
                                alert);

                alert.showAndWait()
                                .ifPresent(
                                                result -> {

                                                        if (result == ButtonType.OK) {

                                                                deleteUser(
                                                                                user);
                                                        }
                                                });
        }

        // ============================================================
        // DELETE USER
        // ============================================================

        private void deleteUser(
                        User user) {

                if (user == null) {
                        return;
                }

                try {

                        boolean deleted = controller.deleteUser(
                                        user);

                        if (deleted) {

                                // ------------------------------------------------
                                // REFRESH EVERYTHING
                                // ------------------------------------------------

                                refreshAllData();

                                // ------------------------------------------------
                                // SUCCESS MESSAGE
                                // ------------------------------------------------

                                showInformation(
                                                "User Removed",
                                                "User removed successfully.");

                        } else {

                                showError(
                                                "Remove Failed",
                                                "Unable to remove the user. " +
                                                                "Please try again.");
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        showError(
                                        "Remove Failed",
                                        "Something went wrong while removing " +
                                                        "the user.");
                }
        }

        // ============================================================
        // INFORMATION ALERT
        // ============================================================

        private void showInformation(
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

                styleAlert(
                                alert);

                alert.showAndWait();
        }

        // ============================================================
        // ERROR ALERT
        // ============================================================

        private void showError(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                                title);

                alert.setHeaderText(
                                title);

                alert.setContentText(
                                message);

                styleAlert(
                                alert);

                alert.showAndWait();
        }

        // ============================================================
        // ALERT STYLER
        // ============================================================

        private void styleAlert(
                        Alert alert) {

                alert.getDialogPane().setStyle(
                                "-fx-background-color:#101718;" +
                                                "-fx-border-color:#293334;");

                // --------------------------------------------------------
                // CONTENT
                // --------------------------------------------------------

                Node contentLabel = alert.getDialogPane()
                                .lookup(
                                                ".content.label");

                if (contentLabel != null) {

                        contentLabel.setStyle(
                                        "-fx-text-fill:#CCCCCC;" +
                                                        "-fx-font-size:13px;");
                }

                // --------------------------------------------------------
                // HEADER
                // --------------------------------------------------------

                Node headerPanel = alert.getDialogPane()
                                .lookup(
                                                ".header-panel");

                if (headerPanel != null) {

                        headerPanel.setStyle(
                                        "-fx-background-color:#101718;");
                }

                // --------------------------------------------------------
                // HEADER LABEL
                // --------------------------------------------------------

                Node headerLabel = alert.getDialogPane()
                                .lookup(
                                                ".header-panel .label");

                if (headerLabel != null) {

                        headerLabel.setStyle(
                                        "-fx-text-fill:#68D34A;" +
                                                        "-fx-font-size:17px;" +
                                                        "-fx-font-weight:bold;");
                }

                // --------------------------------------------------------
                // BUTTONS
                // --------------------------------------------------------

                for (Node node : alert.getDialogPane()
                                .lookupAll(
                                                ".button")) {

                        if (node instanceof Button) {

                                Button button = (Button) node;

                                button.setStyle(
                                                "-fx-background-color:#1B2425;" +
                                                                "-fx-text-fill:#EEEEEE;" +
                                                                "-fx-border-color:#344041;" +
                                                                "-fx-border-radius:6;" +
                                                                "-fx-background-radius:6;" +
                                                                "-fx-padding:7 16 7 16;" +
                                                                "-fx-cursor:hand;");
                        }
                }
        }

        // ============================================================
        // SAFE STRING
        // ============================================================

        private String safe(
                        String value) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return "Unknown";
                }

                return value;
        }

        // ============================================================
        // SPACER
        // ============================================================

        private HBox createSpacer() {

                HBox spacer = new HBox();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                return spacer;
        }
}