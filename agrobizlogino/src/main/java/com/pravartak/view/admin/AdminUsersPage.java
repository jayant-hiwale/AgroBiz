package com.pravartak.view.admin;

import com.pravartak.controller.admincontroller.UserController;
import com.pravartak.model.admin.User;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


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
    // CONSTRUCTOR
    // ============================================================

    public AdminUsersPage() {

        controller =
                new UserController();
    }


    // ============================================================
    // MAIN USERS PAGE
    // ============================================================

    public VBox getUsersPage() {

        // --------------------------------------------------------
        // OUTER ROOT
        // --------------------------------------------------------

        VBox root =
                new VBox();

        root.setStyle(
                "-fx-background-color:#080C0D;"
        );


        // --------------------------------------------------------
        // CONTENT
        // --------------------------------------------------------

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color:#080C0D;"
        );


        // ========================================================
        // PAGE TITLE
        // ========================================================

        Label title =
                new Label(
                        "User Management"
                );

        title.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        // ========================================================
        // PAGE SUBTITLE
        // ========================================================

        Label subtitle =
                new Label(
                        "View and monitor all farmers and buyers."
                );

        subtitle.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );


        // ========================================================
        // STAT CARDS
        // ========================================================

        HBox stats =
                new HBox(15);

        stats.setAlignment(
                Pos.CENTER
        );


        // --------------------------------------------------------
        // LABEL VALUES
        // --------------------------------------------------------

        totalUsersLabel =
                new Label("0");

        farmersLabel =
                new Label("0");

        buyersLabel =
                new Label("0");


        // --------------------------------------------------------
        // TOTAL USERS CARD
        // --------------------------------------------------------

        VBox totalCard =
                createStatCard(
                        "👥",
                        "Total Users",
                        totalUsersLabel
                );


        // --------------------------------------------------------
        // FARMER CARD
        // --------------------------------------------------------

        VBox farmerCard =
                createStatCard(
                        "👨‍🌾",
                        "Farmers",
                        farmersLabel
                );


        // --------------------------------------------------------
        // BUYER CARD
        // --------------------------------------------------------

        VBox buyerCard =
                createStatCard(
                        "🛒",
                        "Buyers",
                        buyersLabel
                );


        stats.getChildren()
                .addAll(
                        totalCard,
                        farmerCard,
                        buyerCard
                );


        // ========================================================
        // FARMER GRAPH
        // ========================================================

        LineChart<String, Number>
                farmerGraph =
                createFarmerGrowthGraph();


        // ========================================================
        // BUYER GRAPH
        // ========================================================

        LineChart<String, Number>
                buyerGraph =
                createBuyerGrowthGraph();


        // ========================================================
        // ALL USERS TABLE
        // ========================================================

        VBox table =
                createUserTable();


        // ========================================================
        // ADD CONTENT
        // ========================================================

        content.getChildren()
                .addAll(
                        title,
                        subtitle,
                        stats,
                        farmerGraph,
                        buyerGraph,
                        table
                );


        // ========================================================
        // LOAD USER COUNTS
        // ========================================================

        loadUserCounts();


        // ========================================================
        // SCROLL PANE
        // ========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );


        scrollPane.setFitToWidth(true);

        scrollPane.setFitToHeight(false);


        // --------------------------------------------------------
        // VERTICAL SCROLL
        // --------------------------------------------------------

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );


        // --------------------------------------------------------
        // HORIZONTAL SCROLL
        // --------------------------------------------------------

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );


        // --------------------------------------------------------
        // SCROLLPANE STYLE
        // --------------------------------------------------------

        scrollPane.setStyle(
                "-fx-background:#080C0D;" +
                "-fx-background-color:#080C0D;" +
                "-fx-border-color:transparent;"
        );


        // ========================================================
        // PUT SCROLLPANE INSIDE ROOT
        // ========================================================

        root.getChildren()
                .add(
                        scrollPane
                );


        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );


        return root;
    }


    // ============================================================
    // STAT CARD
    // ============================================================

    private VBox createStatCard(
            String icon,
            String title,
            Label value) {


        VBox card =
                new VBox(8);


        card.setPrefWidth(300);

        card.setMinHeight(130);

        card.setPadding(
                new Insets(20)
        );


        card.setAlignment(
                Pos.CENTER_LEFT
        );


        card.setStyle(
                "-fx-background-color:#101718;" +
                "-fx-border-color:#293334;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );


        // --------------------------------------------------------
        // ICON
        // --------------------------------------------------------

        Label iconLabel =
                new Label(icon);


        iconLabel.setStyle(
                "-fx-font-size:25px;"
        );


        // --------------------------------------------------------
        // TITLE
        // --------------------------------------------------------

        Label titleLabel =
                new Label(title);


        titleLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );


        // --------------------------------------------------------
        // VALUE
        // --------------------------------------------------------

        value.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );


        card.getChildren()
                .addAll(
                        iconLabel,
                        titleLabel,
                        value
                );


        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );


        return card;
    }


    // ============================================================
    // FARMER GROWTH GRAPH
    // ============================================================

    private LineChart<String, Number>
    createFarmerGrowthGraph() {


        // --------------------------------------------------------
        // AXIS
        // --------------------------------------------------------

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();


        xAxis.setLabel(
                "Month"
        );


        yAxis.setLabel(
                "Registered Farmers"
        );


        // --------------------------------------------------------
        // GRAPH
        // --------------------------------------------------------

        LineChart<String, Number>
                chart =
                new LineChart<>(
                        xAxis,
                        yAxis
                );


        chart.setTitle(
                "Farmer Growth"
        );


        chart.setAnimated(false);

        chart.setCreateSymbols(true);

        chart.setLegendVisible(false);


        chart.setPrefHeight(
                330
        );


        chart.setMinHeight(
                330
        );


        chart.setStyle(
                "-fx-background-color:#101718;" +
                "-fx-border-color:#293334;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );


        // --------------------------------------------------------
        // SERIES
        // --------------------------------------------------------

        XYChart.Series<String, Number>
                farmerSeries =
                new XYChart.Series<>();


        farmerSeries.setName(
                "Farmers"
        );


        // --------------------------------------------------------
        // GET FARMERS
        // --------------------------------------------------------

        List<User> farmers =
                controller.getFarmers();


        // --------------------------------------------------------
        // CURRENT MONTH
        // --------------------------------------------------------

        YearMonth currentMonth =
                YearMonth.now();


        // --------------------------------------------------------
        // LAST 6 MONTHS
        // --------------------------------------------------------

        for (
                int i = 5;
                i >= 0;
                i--
        ) {


            YearMonth month =
                    currentMonth.minusMonths(i);


            int count =
                    getCumulativeCount(
                            farmers,
                            month
                    );


            String monthName =
                    getMonthName(
                            month
                    );


            farmerSeries
                    .getData()
                    .add(
                            new XYChart.Data<>(
                                    monthName,
                                    count
                            )
                    );
        }


        // --------------------------------------------------------
        // ADD SERIES
        // --------------------------------------------------------

        chart.getData()
                .add(
                        farmerSeries
                );


        return chart;
    }


    // ============================================================
    // BUYER GROWTH GRAPH
    // ============================================================

    private LineChart<String, Number>
    createBuyerGrowthGraph() {


        // --------------------------------------------------------
        // AXIS
        // --------------------------------------------------------

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();


        xAxis.setLabel(
                "Month"
        );


        yAxis.setLabel(
                "Registered Buyers"
        );


        // --------------------------------------------------------
        // GRAPH
        // --------------------------------------------------------

        LineChart<String, Number>
                chart =
                new LineChart<>(
                        xAxis,
                        yAxis
                );


        chart.setTitle(
                "Buyer Growth"
        );


        chart.setAnimated(false);

        chart.setCreateSymbols(true);

        chart.setLegendVisible(false);


        chart.setPrefHeight(
                330
        );


        chart.setMinHeight(
                330
        );


        chart.setStyle(
                "-fx-background-color:#101718;" +
                "-fx-border-color:#293334;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );


        // --------------------------------------------------------
        // SERIES
        // --------------------------------------------------------

        XYChart.Series<String, Number>
                buyerSeries =
                new XYChart.Series<>();


        buyerSeries.setName(
                "Buyers"
        );


        // --------------------------------------------------------
        // GET BUYERS
        // --------------------------------------------------------

        List<User> buyers =
                controller.getBuyers();


        // --------------------------------------------------------
        // CURRENT MONTH
        // --------------------------------------------------------

        YearMonth currentMonth =
                YearMonth.now();


        // --------------------------------------------------------
        // LAST 6 MONTHS
        // --------------------------------------------------------

        for (
                int i = 5;
                i >= 0;
                i--
        ) {


            YearMonth month =
                    currentMonth.minusMonths(i);


            int count =
                    getCumulativeCount(
                            buyers,
                            month
                    );


            String monthName =
                    getMonthName(
                            month
                    );


            buyerSeries
                    .getData()
                    .add(
                            new XYChart.Data<>(
                                    monthName,
                                    count
                            )
                    );
        }


        // --------------------------------------------------------
        // ADD SERIES
        // --------------------------------------------------------

        chart.getData()
                .add(
                        buyerSeries
                );


        return chart;
    }


    // ============================================================
    // GET MONTH NAME
    // ============================================================

    private String getMonthName(
            YearMonth month) {


        String monthName =
                month.getMonth()
                        .toString();


        return monthName
                .substring(
                        0,
                        1
                )
                .toUpperCase()
                +
                monthName
                        .substring(
                                1,
                                3
                        )
                        .toLowerCase();
    }


    // ============================================================
    // CUMULATIVE USER COUNT
    // ============================================================

    private int getCumulativeCount(
            List<User> users,
            YearMonth selectedMonth) {


        int count = 0;


        for (User user : users) {


            // ----------------------------------------------------
            // CREATED DATE CHECK
            // ----------------------------------------------------

            if (
                    user.getCreatedAt()
                            == null
            ) {

                continue;
            }


            // ----------------------------------------------------
            // FIRESTORE TIMESTAMP
            // ----------------------------------------------------

            LocalDate date =
                    user.getCreatedAt()
                            .toDate()
                            .toInstant()
                            .atZone(
                                    java.time.ZoneId
                                            .systemDefault()
                            )
                            .toLocalDate();


            // ----------------------------------------------------
            // USER MONTH
            // ----------------------------------------------------

            YearMonth userMonth =
                    YearMonth.from(
                            date
                    );


            // ----------------------------------------------------
            // CUMULATIVE COUNT
            // ----------------------------------------------------

            if (
                    !userMonth.isAfter(
                            selectedMonth
                    )
            ) {

                count++;
            }
        }


        return count;
    }


    // ============================================================
    // LOAD USER COUNTS
    // ============================================================

    private void loadUserCounts() {


        // --------------------------------------------------------
        // ALL
        // --------------------------------------------------------

        List<User> allUsers =
                controller.getAllUsers();


        // --------------------------------------------------------
        // FARMERS
        // --------------------------------------------------------

        List<User> farmers =
                controller.getFarmers();


        // --------------------------------------------------------
        // BUYERS
        // --------------------------------------------------------

        List<User> buyers =
                controller.getBuyers();


        // --------------------------------------------------------
        // DISPLAY
        // --------------------------------------------------------

        totalUsersLabel.setText(
                String.valueOf(
                        allUsers.size()
                )
        );


        farmersLabel.setText(
                String.valueOf(
                        farmers.size()
                )
        );


        buyersLabel.setText(
                String.valueOf(
                        buyers.size()
                )
        );
    }


    // ============================================================
    // USER TABLE
    // ============================================================

    private VBox createUserTable() {


        VBox box =
                new VBox(10);


        box.setPadding(
                new Insets(15)
        );


        box.setStyle(
                "-fx-background-color:#101718;" +
                "-fx-border-color:#293334;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );


        // --------------------------------------------------------
        // TITLE
        // --------------------------------------------------------

        Label title =
                new Label(
                        "All Users"
                );


        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:19px;" +
                "-fx-font-weight:bold;"
        );


        // --------------------------------------------------------
        // TABLE
        // --------------------------------------------------------

        TableView<User> table =
                new TableView<>();


        table.setPrefHeight(
                350
        );


        table.setMinHeight(
                350
        );


        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // ========================================================
        // NAME
        // ========================================================

        TableColumn<User, String>
                name =
                new TableColumn<>(
                        "Name"
                );


        name.setCellValueFactory(
                new PropertyValueFactory<>(
                        "fullName"
                )
        );


        // ========================================================
        // EMAIL
        // ========================================================

        TableColumn<User, String>
                email =
                new TableColumn<>(
                        "Email"
                );


        email.setCellValueFactory(
                new PropertyValueFactory<>(
                        "email"
                )
        );


        // ========================================================
        // ROLE
        // ========================================================

        TableColumn<User, String>
                role =
                new TableColumn<>(
                        "Role"
                );


        role.setCellValueFactory(
                new PropertyValueFactory<>(
                        "role"
                )
        );


        // ========================================================
        // REGISTERED DATE
        // ========================================================

        TableColumn<User, String>
                date =
                new TableColumn<>(
                        "Registered"
                );


        date.setCellValueFactory(
                data -> {


                    User user =
                            data.getValue();


                    // ------------------------------------------------
                    // NO DATE
                    // ------------------------------------------------

                    if (
                            user.getCreatedAt()
                                    == null
                    ) {

                        return new SimpleStringProperty(
                                "Unknown"
                        );
                    }


                    // ------------------------------------------------
                    // DATE
                    // ------------------------------------------------

                    LocalDate localDate =
                            user.getCreatedAt()
                                    .toDate()
                                    .toInstant()
                                    .atZone(
                                            java.time.ZoneId
                                                    .systemDefault()
                                    )
                                    .toLocalDate();


                    return new SimpleStringProperty(
                            localDate.toString()
                    );
                }
        );


        // ========================================================
        // ADD COLUMNS
        // ========================================================

        table.getColumns()
                .addAll(
                        name,
                        email,
                        role,
                        date
                );


        // ========================================================
        // GET USERS
        // ========================================================

        List<User> users =
                controller.getAllUsers();


        // ========================================================
        // ADD USERS
        // ========================================================

        table.setItems(
                FXCollections.observableArrayList(
                        users
                )
        );


        // ========================================================
        // ADD TABLE
        // ========================================================

        box.getChildren()
                .addAll(
                        title,
                        table
                );


        return box;
    }
}

// package com.pravartak.view.admin;

// import com.pravartak.controller.admincontroller.UserController;
// import com.pravartak.model.admin.User;

// import javafx.beans.property.SimpleStringProperty;
// import javafx.collections.FXCollections;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.chart.CategoryAxis;
// import javafx.scene.chart.LineChart;
// import javafx.scene.chart.NumberAxis;
// import javafx.scene.chart.XYChart;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.Tooltip;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.VBox;

// import java.time.LocalDate;
// import java.time.YearMonth;
// import java.time.ZoneId;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Locale;


// /**
//  * Admin Users Page
//  *
//  * Displays:
//  *  - Total users
//  *  - Total farmers
//  *  - Total buyers
//  *  - Farmer registration growth
//  *  - Buyer registration growth
//  *  - All users table
//  */
// public class AdminUsersPage {
        

//     // ============================================================
//     // CONTROLLER
//     // ============================================================

//     private final UserController controller;


//     // ============================================================
//     // DATA
//     // ============================================================

//     private List<User> allUsers = new ArrayList<>();
//     private List<User> farmers = new ArrayList<>();
//     private List<User> buyers = new ArrayList<>();


//     // ============================================================
//     // STAT LABELS
//     // ============================================================

//     private Label totalUsersLabel;
//     private Label farmersLabel;
//     private Label buyersLabel;


//     // ============================================================
//     // CONSTRUCTOR
//     // ============================================================

//     public AdminUsersPage() {

//         controller = new UserController();

//         loadUsers();
//     }


//     // ============================================================
//     // LOAD USERS
//     // ============================================================

//     private void loadUsers() {

//         try {

//             allUsers = controller.getAllUsers();

//             farmers = controller.getFarmers();

//             buyers = controller.getBuyers();

//             if (allUsers == null) {
//                 allUsers = new ArrayList<>();
//             }

//             if (farmers == null) {
//                 farmers = new ArrayList<>();
//             }

//             if (buyers == null) {
//                 buyers = new ArrayList<>();
//             }

//         //     System.out.println(
//         //             "======================================"
//         //     );

//         //     System.out.println(
//         //             "ADMIN USER DATA"
//         //     );

//         //     System.out.println(
//         //             "Total Users : " + allUsers.size()
//         //     );

//         //     System.out.println(
//         //             "Farmers     : " + farmers.size()
//         //     );

//         //     System.out.println(
//         //             "Buyers      : " + buyers.size()
//         //     );

//         //     System.out.println(
//         //             "======================================"
//         //     );

//         } catch (Exception e) {

//             e.printStackTrace();

//             allUsers = new ArrayList<>();
//             farmers = new ArrayList<>();
//             buyers = new ArrayList<>();
//         }
//     }


//     // ============================================================
//     // MAIN USERS PAGE
//     // ============================================================

//     public VBox getUsersPage() {

//         VBox root = new VBox();

//         root.setStyle(
//                 "-fx-background-color:#080C0D;"
//         );


//         // ========================================================
//         // CONTENT
//         // ========================================================

//         VBox content = new VBox(20);

//         content.setPadding(
//                 new Insets(30)
//         );

//         content.setStyle(
//                 "-fx-background-color:#080C0D;"
//         );


//         // ========================================================
//         // PAGE TITLE
//         // ========================================================

//         Label title = new Label(
//                 "User Management"
//         );

//         title.setStyle(
//                 "-fx-text-fill:#68D34A;" +
//                 "-fx-font-size:30px;" +
//                 "-fx-font-weight:bold;"
//         );


//         // ========================================================
//         // SUBTITLE
//         // ========================================================

//         Label subtitle = new Label(
//                 "View and monitor all farmers and buyers."
//         );

//         subtitle.setStyle(
//                 "-fx-text-fill:#AAAAAA;" +
//                 "-fx-font-size:14px;"
//         );


//         // ========================================================
//         // STAT CARDS
//         // ========================================================

//         HBox stats = new HBox(15);

//         stats.setAlignment(
//                 Pos.CENTER
//         );


//         totalUsersLabel = new Label(
//                 String.valueOf(allUsers.size())
//         );

//         farmersLabel = new Label(
//                 String.valueOf(farmers.size())
//         );

//         buyersLabel = new Label(
//                 String.valueOf(buyers.size())
//         );


//         VBox totalCard = createStatCard(
//                 "👥",
//                 "Total Users",
//                 totalUsersLabel
//         );


//         VBox farmerCard = createStatCard(
//                 "👨‍🌾",
//                 "Farmers",
//                 farmersLabel
//         );


//         VBox buyerCard = createStatCard(
//                 "🛒",
//                 "Buyers",
//                 buyersLabel
//         );


//         stats.getChildren().addAll(
//                 totalCard,
//                 farmerCard,
//                 buyerCard
//         );


//         // ========================================================
//         // FARMER GRAPH
//         // ========================================================

//         LineChart<String, Number> farmerGraph =
//                 createGrowthGraph(
//                         "Farmer Registration Growth",
//                         "Registered Farmers",
//                         farmers
//                 );


//         // ========================================================
//         // BUYER GRAPH
//         // ========================================================

//         LineChart<String, Number> buyerGraph =
//                 createGrowthGraph(
//                         "Buyer Registration Growth",
//                         "Registered Buyers",
//                         buyers
//                 );


//         // ========================================================
//         // USER TABLE
//         // ========================================================

//         VBox table = createUserTable();


//         // ========================================================
//         // ADD CONTENT
//         // ========================================================

//         content.getChildren().addAll(
//                 title,
//                 subtitle,
//                 stats,
//                 farmerGraph,
//                 buyerGraph,
//                 table
//         );


//         // ========================================================
//         // SCROLL PANE
//         // ========================================================

//         ScrollPane scrollPane =
//                 new ScrollPane(content);

//         scrollPane.setFitToWidth(true);

//         scrollPane.setFitToHeight(false);

//         scrollPane.setVbarPolicy(
//                 ScrollPane.ScrollBarPolicy.AS_NEEDED
//         );

//         scrollPane.setHbarPolicy(
//                 ScrollPane.ScrollBarPolicy.NEVER
//         );

//         scrollPane.setStyle(
//                 "-fx-background:#080C0D;" +
//                 "-fx-background-color:#080C0D;" +
//                 "-fx-border-color:transparent;"
//         );


//         // ========================================================
//         // ROOT
//         // ========================================================

//         root.getChildren().add(
//                 scrollPane
//         );

//         VBox.setVgrow(
//                 scrollPane,
//                 Priority.ALWAYS
//         );


//         return root;
//     }


//     // ============================================================
//     // STAT CARD
//     // ============================================================

//     private VBox createStatCard(
//             String icon,
//             String title,
//             Label value) {

//         VBox card = new VBox(8);

//         card.setPrefWidth(300);

//         card.setMinHeight(130);

//         card.setPadding(
//                 new Insets(20)
//         );

//         card.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         card.setStyle(
//                 "-fx-background-color:#101718;" +
//                 "-fx-border-color:#293334;" +
//                 "-fx-border-radius:12;" +
//                 "-fx-background-radius:12;"
//         );


//         Label iconLabel = new Label(icon);

//         iconLabel.setStyle(
//                 "-fx-font-size:25px;"
//         );


//         Label titleLabel = new Label(title);

//         titleLabel.setStyle(
//                 "-fx-text-fill:#AAAAAA;" +
//                 "-fx-font-size:14px;"
//         );


//         value.setStyle(
//                 "-fx-text-fill:#EEEEEE;" +
//                 "-fx-font-size:30px;" +
//                 "-fx-font-weight:bold;"
//         );


//         card.getChildren().addAll(
//                 iconLabel,
//                 titleLabel,
//                 value
//         );


//         HBox.setHgrow(
//                 card,
//                 Priority.ALWAYS
//         );


//         return card;
//     }


//     // ============================================================
//     // CREATE GROWTH GRAPH
//     // ============================================================

//     private LineChart<String, Number> createGrowthGraph(
//             String title,
//             String yAxisLabel,
//             List<User> users) {


//         // ========================================================
//         // X AXIS
//         // ========================================================

//         CategoryAxis xAxis =
//                 new CategoryAxis();

//         xAxis.setLabel(
//                 "Month"
//         );


//         // ========================================================
//         // Y AXIS
//         // ========================================================

//         NumberAxis yAxis =
//                 new NumberAxis();

//         yAxis.setLabel(
//                 yAxisLabel
//         );


//         // ========================================================
//         // CHART
//         // ========================================================

//         LineChart<String, Number> chart =
//                 new LineChart<>(
//                         xAxis,
//                         yAxis
//                 );


//         chart.setTitle(title);

//         chart.setAnimated(false);

//         chart.setCreateSymbols(true);

//         chart.setLegendVisible(false);

//         chart.setAlternativeRowFillVisible(false);

//         chart.setAlternativeColumnFillVisible(false);

//         chart.setVerticalGridLinesVisible(false);

//         chart.setHorizontalGridLinesVisible(true);


//         chart.setPrefHeight(350);

//         chart.setMinHeight(350);

//         chart.setMaxWidth(
//                 Double.MAX_VALUE
//         );


//         chart.setStyle(
//                 "-fx-background-color:#101718;" +
//                 "-fx-border-color:#293334;" +
//                 "-fx-border-radius:12;" +
//                 "-fx-background-radius:12;" +
//                 "-fx-padding:15;"
//         );


//         // ========================================================
//         // SERIES
//         // ========================================================

//         XYChart.Series<String, Number> series =
//                 new XYChart.Series<>();


//         series.setName(
//                 yAxisLabel
//         );


//         // ========================================================
//         // LAST 6 MONTHS
//         // ========================================================

//         YearMonth currentMonth =
//                 YearMonth.now();


//         for (int i = 5; i >= 0; i--) {

//             YearMonth month =
//                     currentMonth.minusMonths(i);


//             int count =
//                     getMonthlyRegistrationCount(
//                             users,
//                             month
//                     );


//             String monthName =
//                     month.format(
//                             DateTimeFormatter.ofPattern(
//                                     "MMM",
//                                     Locale.ENGLISH
//                             )
//                     );


//             XYChart.Data<String, Number> data =
//                     new XYChart.Data<>(
//                             monthName,
//                             count
//                     );


//             series.getData().add(data);
//         }


//         // ========================================================
//         // ADD SERIES
//         // ========================================================

//         chart.getData().add(series);


//         // ========================================================
//         // ADD TOOLTIPS
//         // ========================================================

//         for (
//                 XYChart.Data<String, Number> data :
//                 series.getData()
//         ) {

//             if (data.getNode() != null) {

//                 addTooltip(
//                         data,
//                         yAxisLabel
//                 );
//             }


//             data.nodeProperty()
//                     .addListener(
//                             (obs, oldNode, newNode) -> {

//                                 if (newNode != null) {

//                                     addTooltip(
//                                             data,
//                                             yAxisLabel
//                                     );
//                                 }
//                             }
//                     );
//         }


//         return chart;
//     }


//     // ============================================================
//     // TOOLTIP
//     // ============================================================

//     private void addTooltip(
//             XYChart.Data<String, Number> data,
//             String label) {

//         Tooltip tooltip =
//                 new Tooltip(
//                         data.getXValue()
//                                 + "\n"
//                                 + label
//                                 + ": "
//                                 + data.getYValue()
//                 );


//         Tooltip.install(
//                 data.getNode(),
//                 tooltip
//         );
//     }


//     // ============================================================
//     // MONTHLY REGISTRATION COUNT
//     // ============================================================

//     private int getMonthlyRegistrationCount(
//             List<User> users,
//             YearMonth selectedMonth) {


//         int count = 0;


//         if (users == null) {
//             return 0;
//         }


//         for (User user : users) {

//             if (user == null) {
//                 continue;
//             }


//             if (user.getCreatedAt() == null) {
//                 continue;
//             }


//             try {

//                 LocalDate date =
//                         user.getCreatedAt()
//                                 .toDate()
//                                 .toInstant()
//                                 .atZone(
//                                         ZoneId.systemDefault()
//                                 )
//                                 .toLocalDate();


//                 YearMonth userMonth =
//                         YearMonth.from(date);


//                 if (
//                         userMonth.equals(
//                                 selectedMonth
//                         )
//                 ) {

//                     count++;
//                 }

//             } catch (Exception e) {

//                 System.err.println(
//                         "Could not read user createdAt: "
//                                 + e.getMessage()
//                 );
//             }
//         }


//         return count;
//     }


//     // ============================================================
//     // USER TABLE
//     // ============================================================

//     private VBox createUserTable() {

//         VBox box = new VBox(10);

//         box.setPadding(
//                 new Insets(15)
//         );

//         box.setStyle(
//                 "-fx-background-color:#101718;" +
//                 "-fx-border-color:#293334;" +
//                 "-fx-border-radius:12;" +
//                 "-fx-background-radius:12;"
//         );


//         // ========================================================
//         // TITLE
//         // ========================================================

//         Label title = new Label(
//                 "All Users"
//         );

//         title.setStyle(
//                 "-fx-text-fill:#EEEEEE;" +
//                 "-fx-font-size:19px;" +
//                 "-fx-font-weight:bold;"
//         );


//         // ========================================================
//         // TABLE
//         // ========================================================

//         TableView<User> table =
//                 new TableView<>();

//         table.setPrefHeight(350);

//         table.setMinHeight(350);

//         table.setMaxWidth(
//                 Double.MAX_VALUE
//         );

//         table.setColumnResizePolicy(
//                 TableView.CONSTRAINED_RESIZE_POLICY
//         );


//         // ========================================================
//         // NAME
//         // ========================================================

//         TableColumn<User, String> name =
//                 new TableColumn<>(
//                         "Name"
//                 );

//         name.setCellValueFactory(
//                 data ->
//                         new SimpleStringProperty(
//                                 safeString(
//                                         data.getValue()
//                                                 .getFullName()
//                                 )
//                         )
//         );


//         // ========================================================
//         // EMAIL
//         // ========================================================

//         TableColumn<User, String> email =
//                 new TableColumn<>(
//                         "Email"
//                 );

//         email.setCellValueFactory(
//                 data ->
//                         new SimpleStringProperty(
//                                 safeString(
//                                         data.getValue()
//                                                 .getEmail()
//                                 )
//                         )
//         );


//         // ========================================================
//         // ROLE
//         // ========================================================

//         TableColumn<User, String> role =
//                 new TableColumn<>(
//                         "Role"
//                 );

//         role.setCellValueFactory(
//                 data ->
//                         new SimpleStringProperty(
//                                 safeString(
//                                         data.getValue()
//                                                 .getRole()
//                                 )
//                         )
//         );


//         // ========================================================
//         // REGISTERED DATE
//         // ========================================================

//         TableColumn<User, String> date =
//                 new TableColumn<>(
//                         "Registered"
//                 );


//         date.setCellValueFactory(
//                 data -> {

//                     User user =
//                             data.getValue();


//                     if (
//                             user == null
//                             ||
//                             user.getCreatedAt() == null
//                     ) {

//                         return new SimpleStringProperty(
//                                 "Unknown"
//                         );
//                     }


//                     try {

//                         LocalDate localDate =
//                                 user.getCreatedAt()
//                                         .toDate()
//                                         .toInstant()
//                                         .atZone(
//                                                 ZoneId.systemDefault()
//                                         )
//                                         .toLocalDate();


//                         return new SimpleStringProperty(
//                                 localDate.toString()
//                         );

//                     } catch (Exception e) {

//                         return new SimpleStringProperty(
//                                 "Unknown"
//                         );
//                     }
//                 }
//         );


//         // ========================================================
//         // ADD COLUMNS
//         // ========================================================

//         table.getColumns().addAll(
//                 name,
//                 email,
//                 role,
//                 date
//         );


//         // ========================================================
//         // ADD USERS
//         // ========================================================

//         table.setItems(
//                 FXCollections.observableArrayList(
//                         allUsers
//                 )
//         );


//         // ========================================================
//         // ADD TABLE
//         // ========================================================

//         box.getChildren().addAll(
//                 title,
//                 table
//         );


//         VBox.setVgrow(
//                 table,
//                 Priority.ALWAYS
//         );


//         return box;
//     }


//     // ============================================================
//     // SAFE STRING
//     // ============================================================

//     private String safeString(String value) {

//         if (value == null || value.trim().isEmpty()) {

//             return "Unknown";
//         }

//         return value;
//     }
// }

