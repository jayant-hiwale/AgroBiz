
package com.pravartak.view.admin;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.controller.admincontroller.CourseController;
import com.pravartak.controller.admincontroller.UserController;
import com.pravartak.controller.farmercontoller.CommunityController;
import com.pravartak.controller.farmercontoller.ProductController;
import com.pravartak.dao.farmer.CommunityDAO;
import com.pravartak.model.admin.Course;
import com.pravartak.model.admin.User;
import com.pravartak.model.farmer_model.CommunityPost;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.controller.buyercontroller.ReviewController;
import com.pravartak.model.buyer_model.Order;
import com.pravartak.model.buyer_model.Review;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.concurrent.Task;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Admin Dashboard
 *
 * Displays live statistics and charts from Firebase.
 *
 * Data sources:
 *
 * users          -> UserController
 * courses        -> CourseController
 * products       -> ProductController
 * communityPosts -> CommunityController
 */
public class AdminDashBoard extends BorderPane {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String BACKGROUND = "#080C0D";
    private static final String CARD = "#111719";
    private static final String CARD_HOVER = "#172022";
    private static final String BORDER = "#263336";

    private static final String TEXT_PRIMARY = "#F4F7F7";
    private static final String TEXT_SECONDARY = "#91A0A3";

    private static final String GREEN = "#54C77A";
    private static final String GREEN_DARK = "#193D2A";

    private static final String BLUE = "#5B9DF9";
    private static final String BLUE_DARK = "#1B3150";

    private static final String ORANGE = "#F2A65A";
    private static final String ORANGE_DARK = "#4A321C";

    private static final String PURPLE = "#A978F2";
    private static final String PURPLE_DARK = "#35224F";

    private static final String RED = "#EF6B73";
    private static final String RED_DARK = "#492326";

    // =========================================================
    // CONTROLLERS
    // =========================================================

    private final UserController userController;
    private final CourseController courseController;
    private final ProductController productController;
    private final CommunityController communityController;
    private final OrderController orderController;
private final ReviewController reviewController;

    // =========================================================
    // STAT LABELS
    // =========================================================

    private final Label totalUsersValue = createValueLabel("0");
    private final Label farmersValue = createValueLabel("0");
    private final Label buyersValue = createValueLabel("0");
    private final Label productsValue = createValueLabel("0");
    private final Label coursesValue = createValueLabel("0");
    private final Label postsValue = createValueLabel("0");
    private final Label ordersValue = createValueLabel("0");
private final Label pendingOrdersValue = createValueLabel("0");
private final Label reviewsValue = createValueLabel("0");
private final Label revenueValue = createValueLabel("₹0");

    // =========================================================
    // STATUS LABEL
    // =========================================================

    private final Label statusLabel =
            new Label("Loading dashboard...");

    // =========================================================
    // CHART CONTAINERS
    // =========================================================

    private final PieChart userPieChart =
            new PieChart();

    private final BarChart<String, Number> productCategoryChart =
            createBarChart(
                    "Product Category",
                    "Products"
            );

    private final BarChart<String, Number> courseCategoryChart =
            createBarChart(
                    "Course Category",
                    "Courses"
            );

    private final BarChart<String, Number> communityLikesChart =
            createBarChart(
                    "Community Posts",
                    "Likes"
            );

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminDashBoard() {

        userController =
                new UserController();

        courseController =
                new CourseController();

        productController =
                new ProductController();

        Firestore db =
                FirebaseConfig.getFirestore();

        communityController =
                new CommunityController(
                        new CommunityDAO(db)
                );
                orderController =
        new OrderController();

reviewController =
        new ReviewController();

        createLayout();

        loadDashboardData();
    }

    // =========================================================
    // MAIN LAYOUT
    // =========================================================

    private void createLayout() {

        setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        VBox mainContainer =
                new VBox(22);

        mainContainer.setPadding(
                new Insets(28, 32, 35, 32)
        );

        mainContainer.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        HBox header =
                createHeader();

        // -----------------------------------------------------
        // STAT CARDS
        // -----------------------------------------------------

        GridPane statsGrid =
                createStatsGrid();

        // -----------------------------------------------------
        // SEPARATOR
        // -----------------------------------------------------

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color: " + BORDER + ";"
        );

        // -----------------------------------------------------
        // FIRST CHART ROW
        // -----------------------------------------------------

        HBox firstChartRow =
                new HBox(18);

        firstChartRow.setAlignment(
                Pos.CENTER
        );

        VBox userDistributionCard =
                createChartCard(
                        "User Distribution",
                        "Farmers vs buyers",
                        userPieChart
                );

        VBox productCategoryCard =
                createChartCard(
                        "Marketplace",
                        "Products by category",
                        productCategoryChart
                );

        HBox.setHgrow(
                userDistributionCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                productCategoryCard,
                Priority.ALWAYS
        );

        firstChartRow.getChildren().addAll(
                userDistributionCard,
                productCategoryCard
        );

        // -----------------------------------------------------
        // SECOND CHART ROW
        // -----------------------------------------------------

        // HBox secondChartRow =
        //         new HBox(18);

        // secondChartRow.setAlignment(
        //         Pos.CENTER
        // );

        // VBox courseCategoryCard =
        //         createChartCard(
        //                 "Learning Platform",
        //                 "Courses by category",
        //                 courseCategoryChart
        //         );

        // VBox communityCard =
        //         createChartCard(
        //                 "Community Engagement",
        //                 "Likes on community posts",
        //                 communityLikesChart
        //         );

        // HBox.setHgrow(
        //         courseCategoryCard,
        //         Priority.ALWAYS
        // );

        // HBox.setHgrow(
        //         communityCard,
        //         Priority.ALWAYS
        // );

        // secondChartRow.getChildren().addAll(
        //         courseCategoryCard,
        //         communityCard
        // );

        // -----------------------------------------------------
        // STATUS
        // -----------------------------------------------------

        HBox statusBox =
                createStatusBox();

        // -----------------------------------------------------
        // ADD EVERYTHING
        // -----------------------------------------------------

        mainContainer.getChildren().addAll(
                header,
                statsGrid,
                separator,
                firstChartRow,
                // secondChartRow,
                statusBox
        );

        ScrollPane scrollPane =
                new ScrollPane(mainContainer);

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: " + BACKGROUND + ";" +
                "-fx-background: " + BACKGROUND + ";" +
                "-fx-border-color: transparent;"
        );

        setCenter(scrollPane);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        VBox titleBox =
                new VBox(5);

        Label title =
                new Label("Admin Dashboard");

        title.setStyle(
                "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle =
                new Label(
                        "Overview of your AgroBiz platform"
                );

        subtitle.setStyle(
                "-fx-text-fill: " + TEXT_SECONDARY + ";" +
                "-fx-font-size: 14px;"
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        // -----------------------------------------------------
        // REFRESH BUTTON
        // -----------------------------------------------------

        Button refreshButton =
                new Button("⟳  Refresh");

        refreshButton.setPrefHeight(40);

        refreshButton.setPadding(
                new Insets(0, 18, 0, 18)
        );

        refreshButton.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        refreshButton.setOnMouseEntered(e ->
                refreshButton.setStyle(
                        "-fx-background-color: " + CARD_HOVER + ";" +
                        "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-color: " + GREEN + ";" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                )
        );

        refreshButton.setOnMouseExited(e ->
                refreshButton.setStyle(
                        "-fx-background-color: " + CARD + ";" +
                        "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                )
        );

        refreshButton.setOnAction(e ->
                loadDashboardData()
        );

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                titleBox,
                refreshButton
        );

        return header;
    }

    // =========================================================
    // STAT GRID
    // =========================================================

    private GridPane createStatsGrid() {

    GridPane grid =
            new GridPane();

    grid.setHgap(16);
    grid.setVgap(16);

    VBox totalUsers =
            createStatCard(
                    "Total Users",
                    totalUsersValue,
                    "Registered accounts",
                    "👥",
                    GREEN,
                    GREEN_DARK
            );

    VBox farmers =
            createStatCard(
                    "Farmers",
                    farmersValue,
                    "Farmer accounts",
                    "🌾",
                    GREEN,
                    GREEN_DARK
            );

    VBox buyers =
            createStatCard(
                    "Buyers",
                    buyersValue,
                    "Buyer accounts",
                    "🛒",
                    BLUE,
                    BLUE_DARK
            );

    VBox products =
            createStatCard(
                    "Products",
                    productsValue,
                    "Marketplace listings",
                    "📦",
                    ORANGE,
                    ORANGE_DARK
            );

    VBox courses =
            createStatCard(
                    "Courses",
                    coursesValue,
                    "Learning resources",
                    "📚",
                    PURPLE,
                    PURPLE_DARK
            );

    VBox posts =
            createStatCard(
                    "Community Posts",
                    postsValue,
                    "Farmer discussions",
                    "💬",
                    RED,
                    RED_DARK
            );

    VBox orders =
            createStatCard(
                    "Orders",
                    ordersValue,
                    "Marketplace orders",
                    "🛍",
                    BLUE,
                    BLUE_DARK
            );

    VBox pendingOrders =
            createStatCard(
                    "Pending Orders",
                    pendingOrdersValue,
                    "Awaiting action",
                    "⏳",
                    ORANGE,
                    ORANGE_DARK
            );

    VBox reviews =
            createStatCard(
                    "Reviews",
                    reviewsValue,
                    "Customer reviews",
                    "⭐",
                    GREEN,
                    GREEN_DARK
            );

    VBox revenue =
            createStatCard(
                    "Revenue",
                    revenueValue,
                    "Total order value",
                    "₹",
                    PURPLE,
                    PURPLE_DARK
            );

    addGridItem(grid, totalUsers, 0, 0);
    addGridItem(grid, farmers, 1, 0);
    addGridItem(grid, buyers, 2, 0);

    addGridItem(grid, products, 0, 1);
    addGridItem(grid, courses, 1, 1);
    addGridItem(grid, posts, 2, 1);

    addGridItem(grid, orders, 0, 2);
    addGridItem(grid, pendingOrders, 1, 2);
    addGridItem(grid, reviews, 2, 2);

    addGridItem(grid, revenue, 0, 3);

    return grid;
}

    private void addGridItem(
            GridPane grid,
            Node node,
            int column,
            int row) {

        grid.add(
                node,
                column,
                row
        );

        GridPane.setHgrow(
                node,
                Priority.ALWAYS
        );

        GridPane.setFillWidth(
                node,
                true
        );
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String title,
            Label value,
            String description,
            String icon,
            String accent,
            String iconBackground) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(18)
        );

        card.setMinHeight(135);

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // -----------------------------------------------------
        // TOP
        // -----------------------------------------------------

        HBox top =
                new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill: " + TEXT_SECONDARY + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setMinSize(38, 38);

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setStyle(
                "-fx-background-color: " +
                        iconBackground + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 18px;"
        );

        HBox.setHgrow(
                titleLabel,
                Priority.ALWAYS
        );

        top.getChildren().addAll(
                titleLabel,
                iconLabel
        );

        // -----------------------------------------------------
        // VALUE
        // -----------------------------------------------------

        value.setStyle(
                "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;"
        );

        // -----------------------------------------------------
        // DESCRIPTION
        // -----------------------------------------------------

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-text-fill: " + TEXT_SECONDARY + ";" +
                "-fx-font-size: 12px;"
        );

        card.getChildren().addAll(
                top,
                value,
                descriptionLabel
        );

        return card;
    }

    // =========================================================
    // CHART CARD
    // =========================================================

    private VBox createChartCard(
            String title,
            String subtitle,
            Node chart) {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(20)
        );

        card.setMinHeight(360);

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setStyle(
                "-fx-text-fill: " + TEXT_SECONDARY + ";" +
                "-fx-font-size: 12px;"
        );

        VBox heading =
                new VBox(3);

        heading.getChildren().addAll(
                titleLabel,
                subtitleLabel
        );

        VBox.setVgrow(
                chart,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                heading,
                chart
        );

        return card;
    }

    // =========================================================
    // STATUS
    // =========================================================

    private HBox createStatusBox() {

        HBox box =
                new HBox(10);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        Label dot =
                new Label("●");

        dot.setStyle(
                "-fx-text-fill: " + GREEN + ";" +
                "-fx-font-size: 12px;"
        );

        statusLabel.setStyle(
                "-fx-text-fill: " + TEXT_SECONDARY + ";" +
                "-fx-font-size: 12px;"
        );

        box.getChildren().addAll(
                dot,
                statusLabel
        );

        return box;
    }

    // =========================================================
    // CREATE BAR CHART
    // =========================================================

    private static BarChart<String, Number> createBarChart(
            String xLabel,
            String yLabel) {

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);

        BarChart<String, Number> chart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );

        chart.setLegendVisible(false);
        chart.setAnimated(false);

        chart.setVerticalGridLinesVisible(false);
        chart.setHorizontalGridLinesVisible(true);

        chart.setPrefHeight(270);

        chart.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + TEXT_SECONDARY + ";"
        );

        return chart;
    }

    // =========================================================
    // LOAD DASHBOARD DATA
    // =========================================================

    private void loadDashboardData() {

        statusLabel.setText(
                "Loading latest data from Firebase..."
        );

        Task<DashboardData> task =
                new Task<>() {

                    @Override
                    protected DashboardData call()
                            throws Exception {

                        // -----------------------------
                        // USERS
                        // -----------------------------

                        List<User> users =
                                userController.getAllUsers();

                        List<User> farmers =
                                userController.getFarmers();

                        List<User> buyers =
                                userController.getBuyers();

                        // -----------------------------
                        // PRODUCTS
                        // -----------------------------

                        List<Product> products =
                                productController
                                        .getAllProducts();

                        // -----------------------------
                        // COURSES
                        // -----------------------------

                        List<Course> courses =
                                courseController
                                        .getAllCourses();

                        // -----------------------------
                        // COMMUNITY
                        // -----------------------------

                        List<CommunityPost> posts =
                                communityController
                                        .getPosts();

                        // -----------------------------
// ORDERS
// -----------------------------

List<Order> orders =
        orderController.getAllOrders();

// -----------------------------
// REVIEWS
// -----------------------------

List<Review> reviews =
        reviewController.getAllReviews();

                       return new DashboardData(
        users,
        farmers,
        buyers,
        products,
        courses,
        posts,
        orders,
        reviews
);
                    }
                };

        task.setOnSucceeded(event -> {

            DashboardData data =
                    task.getValue();

            updateDashboard(data);

            statusLabel.setText(
                    "Dashboard updated successfully"
            );
        });

        task.setOnFailed(event -> {

            Throwable error =
                    task.getException();

            error.printStackTrace();

            statusLabel.setText(
                    "Unable to load dashboard data"
            );
        });

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.start();
    }

    // =========================================================
    // UPDATE DASHBOARD
    // =========================================================

    private void updateDashboard(
            DashboardData data) {

        Platform.runLater(() -> {

            // -------------------------------------------------
            // STATISTICS
            // -------------------------------------------------

            totalUsersValue.setText(
                    String.valueOf(
                            data.users.size()
                    )
            );

            farmersValue.setText(
                    String.valueOf(
                            data.farmers.size()
                    )
            );

            buyersValue.setText(
                    String.valueOf(
                            data.buyers.size()
                    )
            );

            productsValue.setText(
                    String.valueOf(
                            data.products.size()
                    )
            );

            coursesValue.setText(
                    String.valueOf(
                            data.courses.size()
                    )
            );

            postsValue.setText(
                    String.valueOf(
                            data.posts.size()
                    )
            );

            // -------------------------------------------------
            // CHARTS
            // -------------------------------------------------

            updateUserDistribution(
                    data.farmers,
                    data.buyers
            );

            updateProductCategories(
                    data.products
            );

            updateCourseCategories(
                    data.courses
            );

            updateCommunityLikes(
                    data.posts
            );
           // -------------------------------------------------
// ORDER & REVIEW STATISTICS
// -------------------------------------------------

ordersValue.setText(
        String.valueOf(
                data.orders.size()
        )
);

// Pending Orders
long pendingOrders = data.orders.stream()
        .filter(order ->
                "PENDING".equalsIgnoreCase(
                        order.getOrderStatus()
                )
        )
        .count();

pendingOrdersValue.setText(
        String.valueOf(pendingOrders)
);

// Reviews
reviewsValue.setText(
        String.valueOf(
                data.reviews.size()
        )
);

// Revenue
double revenue = data.orders.stream()
        .filter(order -> {
            String status = order.getOrderStatus();

            return !"CANCELLED".equalsIgnoreCase(status)
                    && !"REJECTED".equalsIgnoreCase(status);
        })
        .mapToDouble(Order::getTotalAmount)
        .sum();

revenueValue.setText(
        "₹" + String.format("%.2f", revenue)
);
        });
    }

    // =========================================================
    // USER PIE CHART
    // =========================================================

    private void updateUserDistribution(
            List<User> farmers,
            List<User> buyers) {

        ObservableList<PieChart.Data> chartData =
                FXCollections.observableArrayList();

        if (!farmers.isEmpty()) {

            chartData.add(
                    new PieChart.Data(
                            "Farmers (" +
                                    farmers.size() +
                                    ")",
                            farmers.size()
                    )
            );
        }

        if (!buyers.isEmpty()) {

            chartData.add(
                    new PieChart.Data(
                            "Buyers (" +
                                    buyers.size() +
                                    ")",
                            buyers.size()
                    )
            );
        }

        userPieChart.setData(
                chartData
        );

        userPieChart.setLegendVisible(true);
        userPieChart.setLabelsVisible(true);
        userPieChart.setAnimated(false);

        userPieChart.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + TEXT_SECONDARY + ";"
        );
    }

    // =========================================================
    // PRODUCT CATEGORY CHART
    // =========================================================

    private void updateProductCategories(
            List<Product> products) {

        Map<String, Integer> categories =
                new TreeMap<>(
                        String.CASE_INSENSITIVE_ORDER
                );

        for (Product product : products) {

            String category =
                    product.getCategory();

            if (category == null ||
                    category.trim().isEmpty()) {

                category = "Other";
            }

            categories.put(
                    category,
                    categories.getOrDefault(
                            category,
                            0
                    ) + 1
            );
        }

        productCategoryChart
                .getData()
                .clear();

        BarChart.Series<String, Number> series =
                new BarChart.Series<>();

        series.setName("Products");

        for (Map.Entry<String, Integer> entry :
                categories.entrySet()) {

            series.getData().add(
                    new BarChart.Data<>(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        productCategoryChart
                .getData()
                .add(series);
    }

    // =========================================================
    // COURSE CATEGORY CHART
    // =========================================================

    private void updateCourseCategories(
            List<Course> courses) {

        Map<String, Integer> categories =
                new TreeMap<>(
                        String.CASE_INSENSITIVE_ORDER
                );

        for (Course course : courses) {

            String category =
                    course.getCategory();

            if (category == null ||
                    category.trim().isEmpty()) {

                category = "Other";
            }

            categories.put(
                    category,
                    categories.getOrDefault(
                            category,
                            0
                    ) + 1
            );
        }

        courseCategoryChart
                .getData()
                .clear();

        BarChart.Series<String, Number> series =
                new BarChart.Series<>();

        series.setName("Courses");

        for (Map.Entry<String, Integer> entry :
                categories.entrySet()) {

            series.getData().add(
                    new BarChart.Data<>(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        courseCategoryChart
                .getData()
                .add(series);
    }

    // =========================================================
    // COMMUNITY LIKES
    // =========================================================

    private void updateCommunityLikes(
            List<CommunityPost> posts) {

        communityLikesChart
                .getData()
                .clear();

        if (posts.isEmpty()) {
            return;
        }

        // Sort by likes, highest first
        List<CommunityPost> sortedPosts =
                new java.util.ArrayList<>(
                        posts
                );

        sortedPosts.sort(
                (a, b) ->
                        Long.compare(
                                b.getLikes(),
                                a.getLikes()
                        )
        );

        BarChart.Series<String, Number> series =
                new BarChart.Series<>();

        series.setName("Likes");

        int limit =
                Math.min(
                        sortedPosts.size(),
                        5
                );

        for (int i = 0; i < limit; i++) {

            CommunityPost post =
                    sortedPosts.get(i);

            String name =
                    post.getFarmerName();

            if (name == null ||
                    name.trim().isEmpty()) {

                name = "Post " + (i + 1);
            }

            if (name.length() > 12) {

                name =
                        name.substring(
                                0,
                                12
                        ) + "...";
            }

            series.getData().add(
                    new BarChart.Data<>(
                            name,
                            post.getLikes()
                    )
            );
        }

        communityLikesChart
                .getData()
                .add(series);
    }

    // =========================================================
    // VALUE LABEL
    // =========================================================

    private static Label createValueLabel(
            String value) {

        Label label =
                new Label(value);

        label.setStyle(
                "-fx-text-fill: " + TEXT_PRIMARY + ";" +
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;"
        );

        return label;
    }

    // =========================================================
    // DASHBOARD DATA HOLDER
    // =========================================================

            private static class DashboardData {

        private final List<User> users;
        private final List<User> farmers;
        private final List<User> buyers;

        private final List<Product> products;
        private final List<Course> courses;
        private final List<Order> orders;
private final List<Review> reviews;
        private final List<CommunityPost> posts;

       DashboardData(
        List<User> users,
        List<User> farmers,
        List<User> buyers,
        List<Product> products,
        List<Course> courses,
        List<CommunityPost> posts,
        List<Order> orders,
        List<Review> reviews) {

            this.users = users;
            this.farmers = farmers;
            this.buyers = buyers;
            this.orders = orders;
this.reviews = reviews;

            this.products = products;
            this.courses = courses;
            this.posts = posts;
        }
    }
}