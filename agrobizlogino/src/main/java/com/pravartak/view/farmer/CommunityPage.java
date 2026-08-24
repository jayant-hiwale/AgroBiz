// package com.pravartak.view.farmer;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.Border;
// import javafx.scene.layout.BorderStroke;
// import javafx.scene.layout.BorderStrokeStyle;
// import javafx.scene.layout.BorderWidths;
// import javafx.scene.layout.CornerRadii;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

// public class CommunityPage {

//     private static final Color BG = Color.rgb(53, 164, 131);
//     private static final Color CARD = Color.rgb(63, 133, 112);
//     private static final Color GREEN = Color.rgb(72, 210, 91);
//     private static final Color LIGHT = Color.rgb(225, 235, 220);
//     private static final Color MUTED = Color.rgb(170, 190, 175);

//     public static Scene getCommunityScene() {

//         BorderPane root = new BorderPane();
//         root.setBackground(new Background(new BackgroundFill(BG, CornerRadii.EMPTY, Insets.EMPTY)));

//         VBox page = new VBox(22);
//         page.setPadding(new Insets(24, 35, 40, 35));

//         HBox topBar = createTopBar();

//         VBox feed = new VBox(24);
//         feed.setFillWidth(true);

//         VBox postBox = createPostBox(feed);

//         HBox filters = createFilters();

//         addExistingPosts(feed);

//         page.getChildren().addAll(topBar, postBox, filters, feed);

//         ScrollPane scroll = new ScrollPane(page);
//         scroll.setFitToWidth(true);
//         scroll.setStyle("-fx-background: #051611; -fx-background-color: #051611;");

//         root.setCenter(scroll);

//         return new Scene(root, 1368, 768);
//     }

//     private static HBox createTopBar() {

//         HBox bar = new HBox(20);
//         bar.setAlignment(Pos.CENTER_LEFT);
//         bar.setPadding(new Insets(0, 0, 18, 0));

//         bar.setBorder(new Border(
//                 new BorderStroke(
//                         Color.rgb(50, 70, 60),
//                         BorderStrokeStyle.SOLID,
//                         CornerRadii.EMPTY,
//                         new BorderWidths(0, 0, 1, 0)
//                 )
//         ));

//         Label logo = new Label("AgroBiz");
//         logo.setTextFill(GREEN);
//         logo.setFont(Font.font("Arial", FontWeight.BOLD, 25));

//         Region space = new Region();
//         HBox.setHgrow(space, Priority.ALWAYS);

//         TextField search = new TextField();
//         search.setPromptText("⌕  Search Community...");
//         search.setPrefSize(270, 40);
//         search.setFont(Font.font("Arial", 15));
//         search.setBackground(new Background(
//                 new BackgroundFill(Color.WHITE, new CornerRadii(25), Insets.EMPTY)
//         ));
//         search.setBorder(Border.EMPTY);
//         search.setPadding(new Insets(0, 18, 0, 18));

//         Label notification = new Label("♧");
//         notification.setTextFill(LIGHT);
//         notification.setFont(Font.font("Arial", 25));

//         Label profile = new Label("👨‍🌾");
//         profile.setFont(Font.font("Arial", 25));

//         bar.getChildren().addAll(
//                 logo,
//                 space,
//                 search,
//                 notification,
//                 profile
//         );

//         return bar;
//     }

//     private static VBox createPostBox(VBox feed) {

//         VBox box = new VBox(15);
//         box.setPadding(new Insets(22));

//         box.setBackground(new Background(
//                 new BackgroundFill(
//                         CARD,
//                         new CornerRadii(18),
//                         Insets.EMPTY
//                 )
//         ));

//         box.setBorder(new Border(
//                 new BorderStroke(
//                         Color.rgb(25, 65, 52),
//                         BorderStrokeStyle.SOLID,
//                         new CornerRadii(18),
//                         new BorderWidths(1)
//                 )
//         ));

//         HBox row = new HBox(15);
//         row.setAlignment(Pos.TOP_LEFT);

//         Label avatar = new Label("👨‍🌾");
//         avatar.setFont(Font.font("Arial", 27));
//         avatar.setMinSize(50, 50);
//         avatar.setAlignment(Pos.CENTER);

//         TextArea postField = new TextArea();

//         postField.setPromptText(
//                 "Post to Community: Share your harvest updates, field observations, or link your farm data..."
//         );

//         postField.setWrapText(true);
//         postField.setPrefHeight(78);
//         postField.setFont(Font.font("Arial", 16));

//         postField.setStyle(
//                 "-fx-control-inner-background: #082a20;" +
//                 "-fx-text-fill: white;" +
//                 "-fx-prompt-text-fill: #b5c5ba;" +
//                 "-fx-border-color: #758f82;" +
//                 "-fx-border-radius: 12;" +
//                 "-fx-background-radius: 12;"
//         );

//         HBox.setHgrow(postField, Priority.ALWAYS);

//         row.getChildren().addAll(
//                 avatar,
//                 postField
//         );

//         HBox bottom = new HBox(22);
//         bottom.setAlignment(Pos.CENTER_LEFT);

//         Label image = new Label("▧");
//         Label link = new Label("🔗");
//         Label data = new Label("▣ Link Data");

//         image.setTextFill(LIGHT);
//         link.setTextFill(LIGHT);
//         data.setTextFill(LIGHT);

//         Region spacer = new Region();
//         HBox.setHgrow(spacer, Priority.ALWAYS);

//         Button postButton = new Button("Post");

//         postButton.setPrefSize(85, 38);
//         postButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//         postButton.setTextFill(Color.BLACK);

//         postButton.setBackground(new Background(
//                 new BackgroundFill(
//                         GREEN,
//                         new CornerRadii(25),
//                         Insets.EMPTY
//                 )
//         ));

//         postButton.setOnAction(e -> {

//             String text = postField.getText().trim();

//             if (!text.isEmpty()) {

//                 VBox newPost = createPostCard(
//                         "You",
//                         "Farmer",
//                         "Just now",
//                         text,
//                         false
//                 );

//                 feed.getChildren().add(0, newPost);

//                 postField.clear();
//             }
//         });

//         bottom.getChildren().addAll(
//                 image,
//                 link,
//                 data,
//                 spacer,
//                 postButton
//         );

//         box.getChildren().addAll(
//                 row,
//                 bottom
//         );

//         return box;
//     }

//     private static HBox createFilters() {

//         HBox filters = new HBox(12);
//         filters.setAlignment(Pos.CENTER_LEFT);

//         String[] names = {
//                 "All Farmer Posts",
//                 "Verified Farmers",
//                 "Harvest Reports",
//                 "Field Notes"
//         };

//         for (int i = 0; i < names.length; i++) {

//             Button button = new Button(names[i]);

//             button.setFont(Font.font("Arial", 14));
//             button.setPadding(new Insets(9, 17, 9, 17));
//             button.setCursor(javafx.scene.Cursor.HAND);

//             if (i == 0) {

//                 button.setTextFill(Color.BLACK);

//                 button.setBackground(new Background(
//                         new BackgroundFill(
//                                 Color.rgb(140, 220, 160),
//                                 new CornerRadii(25),
//                                 Insets.EMPTY
//                         )
//                 ));

//             } else {

//                 button.setTextFill(LIGHT);

//                 button.setBackground(new Background(
//                         new BackgroundFill(
//                                 Color.TRANSPARENT,
//                                 new CornerRadii(25),
//                                 Insets.EMPTY
//                         )
//                 ));

//                 button.setBorder(new Border(
//                         new BorderStroke(
//                                 Color.rgb(120, 140, 125),
//                                 BorderStrokeStyle.SOLID,
//                                 new CornerRadii(25),
//                                 new BorderWidths(1)
//                         )
//                 ));
//             }

//             filters.getChildren().add(button);
//         }

//         return filters;
//     }

//     private static void addExistingPosts(VBox feed) {

//         feed.getChildren().add(
//                 createPostCard(
//                         "Dr. Elena Postova",
//                         "Senior Agronomist",
//                         "2h ago",
//                         "Noticing an unusual pattern of yellowing in the lower leaves of early-stage winter wheat across several test plots in Region 4. Initial soil tests indicate potential nitrogen lock-up due to recent cold, wet spells rather than a primary deficiency. Has anyone else observed this in similar clay-heavy soils recently?",
//                         true
//                 )
//         );

//         feed.getChildren().add(
//                 createPostCard(
//                         "Marcus Vance",
//                         "Precision Farming Lead",
//                         "5h ago",
//                         "Just integrated the new AI yield prediction module into our dashboard. The preliminary data suggests a 15% increase in harvesting efficiency if we adjust our watering schedules based on the micro-climate sensor data. Highly recommend everyone check out the latest update! #PrecisionAg #AgriTech #SmartFarming",
//                         false
//                 )
//         );
//     }

//     private static VBox createPostCard(
//             String name,
//             String role,
//             String time,
//             String text,
//             boolean verified
//     ) {

//         VBox card = new VBox(16);

//         card.setPadding(new Insets(22));

//         card.setBackground(new Background(
//                 new BackgroundFill(
//                         CARD,
//                         new CornerRadii(18),
//                         Insets.EMPTY
//                 )
//         ));

//         card.setBorder(new Border(
//                 new BorderStroke(
//                         Color.rgb(25, 65, 52),
//                         BorderStrokeStyle.SOLID,
//                         new CornerRadii(18),
//                         new BorderWidths(1)
//                 )
//         ));

//         HBox userRow = new HBox(12);
//         userRow.setAlignment(Pos.CENTER_LEFT);

//         Label avatar = new Label("👨‍🌾");
//         avatar.setFont(Font.font("Arial", 25));
//         avatar.setMinSize(48, 48);
//         avatar.setAlignment(Pos.CENTER);

//         VBox userInfo = new VBox(3);

//         HBox nameRow = new HBox(8);
//         nameRow.setAlignment(Pos.CENTER_LEFT);

//         Label nameLabel = new Label(name);
//         nameLabel.setTextFill(LIGHT);
//         nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

//         nameRow.getChildren().add(nameLabel);

//         if (verified) {

//             Label verifiedLabel = new Label("✓ Verified Farmer");

//             verifiedLabel.setTextFill(
//                     Color.rgb(120, 230, 150)
//             );

//             verifiedLabel.setFont(
//                     Font.font("Arial", 11)
//             );

//             nameRow.getChildren().add(verifiedLabel);
//         }

//         Label roleLabel = new Label(
//                 role + " • " + time
//         );

//         roleLabel.setTextFill(MUTED);
//         roleLabel.setFont(Font.font("Arial", 12));

//         userInfo.getChildren().addAll(
//                 nameRow,
//                 roleLabel
//         );

//         Region space = new Region();
//         HBox.setHgrow(space, Priority.ALWAYS);

//         Label menu = new Label("⋮");
//         menu.setTextFill(LIGHT);
//         menu.setFont(Font.font("Arial", 25));

//         userRow.getChildren().addAll(
//                 avatar,
//                 userInfo,
//                 space,
//                 menu
//         );

//         Label postText = new Label(text);

//         postText.setTextFill(LIGHT);
//         postText.setFont(Font.font("Arial", 16));
//         postText.setWrapText(true);

//         HBox actions = new HBox(30);

//         actions.setAlignment(Pos.CENTER_LEFT);
//         actions.setPadding(new Insets(12, 0, 0, 0));

//         actions.setBorder(new Border(
//                 new BorderStroke(
//                         Color.rgb(35, 70, 58),
//                         BorderStrokeStyle.SOLID,
//                         CornerRadii.EMPTY,
//                         new BorderWidths(1, 0, 0, 0)
//                 )
//         ));

//         Label like = new Label("♡  124");
//         Label comment = new Label("▢  32");
//         Label share = new Label("♧  12");

//         like.setTextFill(LIGHT);
//         comment.setTextFill(LIGHT);
//         share.setTextFill(LIGHT);

//         actions.getChildren().addAll(
//                 like,
//                 comment,
//                 share
//         );

//         card.getChildren().addAll(
//                 userRow,
//                 postText,
//                 actions
//         );

//         return card;
//     }
// }

// package com.pravartak.view.farmer;

// import com.pravartak.view.farmer.common.Footer;
// import com.pravartak.view.farmer.common.NavBar;
// import com.pravartak.view.login.LoginPage;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.Border;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.BorderStroke;
// import javafx.scene.layout.BorderStrokeStyle;
// import javafx.scene.layout.BorderWidths;
// import javafx.scene.layout.CornerRadii;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

// public class CommunityPage {

//     private static Scene communityScene;

//     private static final Color BG = Color.rgb(53, 164, 131);
//     private static final Color CARD = Color.rgb(63, 133, 112);
//     private static final Color GREEN = Color.rgb(72, 210, 91);
//     private static final Color LIGHT = Color.rgb(225, 235, 220);
//     private static final Color MUTED = Color.rgb(170, 190, 175);

//     public static Scene getCommunityScene() {

//         // MAIN BORDER PANE
//         BorderPane borderPane = new BorderPane();

//         borderPane.setStyle(
//                 "-fx-background-color: #35a483;"
//         );

//         // NAVBAR
//         borderPane.setTop(
//                 new NavBar().createNavbar("Community")
//         );

//         // FOOTER
//         borderPane.setBottom(
//                 new Footer().createFooter()
//         );

//         // MAIN VBOX
//         VBox page = new VBox(22);

//         page.setPadding(
//                 new Insets(24, 35, 40, 35)
//         );

//         page.setAlignment(
//                 Pos.TOP_LEFT
//         );

//         page.setStyle(
//                 "-fx-background-color: #35a483;"
//         );

//         // COMMUNITY TOP BAR
//         HBox topBar = createTopBar();

//         // FEED
//         VBox feed = new VBox(24);

//         feed.setFillWidth(true);

//         // CREATE POST
//         VBox postBox = createPostBox(feed);

//         // FILTERS
//         HBox filters = createFilters();

//         // EXISTING POSTS
//         addExistingPosts(feed);

//         // ADD CONTENT
//         page.getChildren().addAll(
//                 topBar,
//                 postBox,
//                 filters,
//                 feed
//         );

//         // SCROLL PANE
//         ScrollPane scrollPane = new ScrollPane();

//         scrollPane.setContent(page);

//         scrollPane.setFitToWidth(true);

//         scrollPane.setHbarPolicy(
//                 ScrollPane.ScrollBarPolicy.NEVER
//         );

//         scrollPane.setVbarPolicy(
//                 ScrollPane.ScrollBarPolicy.AS_NEEDED
//         );

//         scrollPane.setStyle(
//                 "-fx-background-color: #35a483;"
//                         + "-fx-background: #35a483;"
//         );

//         borderPane.setCenter(scrollPane);

//         // SCENE
//         Scene scene = new Scene(
//                 borderPane,
//                 1368,
//                 768
//         );

//         communityScene = scene;

//         return scene;
//     }

//     // COMMUNITY TOP BAR
//     private static HBox createTopBar() {

//         HBox bar = new HBox(20);

//         bar.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         bar.setPadding(
//                 new Insets(0, 0, 18, 0)
//         );

//         bar.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 Color.rgb(50, 70, 60),
//                                 BorderStrokeStyle.SOLID,
//                                 CornerRadii.EMPTY,
//                                 new BorderWidths(
//                                         0, 0, 1, 0
//                                 )
//                         )
//                 )
//         );

//         // TITLE
//         Label title = new Label(
//                 "Farmer Community"
//         );

//         title.setTextFill(
//                 LIGHT
//         );

//         title.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         25
//                 )
//         );

//         Region space = new Region();

//         HBox.setHgrow(
//                 space,
//                 Priority.ALWAYS
//         );

//         // SEARCH
//         TextField search = new TextField();

//         search.setPromptText(
//                 "⌕  Search Community..."
//         );

//         search.setPrefSize(
//                 270,
//                 40
//         );

//         search.setFont(
//                 Font.font(
//                         "Arial",
//                         15
//                 )
//         );

//         search.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 Color.WHITE,
//                                 new CornerRadii(25),
//                                 Insets.EMPTY
//                         )
//                 )
//         );

//         search.setBorder(
//                 Border.EMPTY
//         );

//         search.setPadding(
//                 new Insets(
//                         0, 18, 0, 18
//                 )
//         );

//         bar.getChildren().addAll(
//                 title,
//                 space,
//                 search
//         );

//         return bar;
//     }

//     // CREATE POST BOX
//     private static VBox createPostBox(
//             VBox feed) {

//         VBox box = new VBox(15);

//         box.setPadding(
//                 new Insets(22)
//         );

//         box.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 CARD,
//                                 new CornerRadii(18),
//                                 Insets.EMPTY
//                         )
//                 )
//         );

//         box.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 Color.rgb(25, 65, 52),
//                                 BorderStrokeStyle.SOLID,
//                                 new CornerRadii(18),
//                                 new BorderWidths(1)
//                         )
//                 )
//         );

//         // POST ROW
//         HBox row = new HBox(15);

//         row.setAlignment(
//                 Pos.TOP_LEFT
//         );

//         Label avatar = new Label(
//                 "👨‍🌾"
//         );

//         avatar.setFont(
//                 Font.font(
//                         "Arial",
//                         27
//                 )
//         );

//         avatar.setMinSize(
//                 50,
//                 50
//         );

//         avatar.setAlignment(
//                 Pos.CENTER
//         );

//         TextArea postField = new TextArea();

//         postField.setPromptText(
//                 "Post to Community: Share your harvest updates, "
//                         + "field observations, or link your farm data..."
//         );

//         postField.setWrapText(true);

//         postField.setPrefHeight(
//                 78
//         );

//         postField.setFont(
//                 Font.font(
//                         "Arial",
//                         16
//                 )
//         );

//         postField.setStyle(
//                 "-fx-control-inner-background: #082a20;"
//                         + "-fx-text-fill: white;"
//                         + "-fx-prompt-text-fill: #b5c5ba;"
//                         + "-fx-border-color: #758f82;"
//                         + "-fx-border-radius: 12;"
//                         + "-fx-background-radius: 12;"
//         );

//         HBox.setHgrow(
//                 postField,
//                 Priority.ALWAYS
//         );

//         row.getChildren().addAll(
//                 avatar,
//                 postField
//         );

//         // BOTTOM ROW
//         HBox bottom = new HBox(22);

//         bottom.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         Label image = new Label(
//                 "▧"
//         );

//         Label link = new Label(
//                 "🔗"
//         );

//         Label data = new Label(
//                 "▣ Link Data"
//         );

//         image.setTextFill(
//                 LIGHT
//         );

//         link.setTextFill(
//                 LIGHT
//         );

//         data.setTextFill(
//                 LIGHT
//         );

//         Region spacer = new Region();

//         HBox.setHgrow(
//                 spacer,
//                 Priority.ALWAYS
//         );

//         // POST BUTTON
//         Button postButton = new Button(
//                 "Post"
//         );

//         postButton.setPrefSize(
//                 85,
//                 38
//         );

//         postButton.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         14
//                 )
//         );

//         postButton.setTextFill(
//                 Color.BLACK
//         );

//         postButton.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 GREEN,
//                                 new CornerRadii(25),
//                                 Insets.EMPTY
//                         )
//                 )
//         );

//         // POST ACTION
//         postButton.setOnAction(
//                 e -> {

//                     String text =
//                             postField.getText().trim();

//                     if (!text.isEmpty()) {

//                         VBox newPost =
//                                 createPostCard(
//                                         "You",
//                                         "Farmer",
//                                         "Just now",
//                                         text,
//                                         false
//                                 );

//                         feed.getChildren().add(
//                                 0,
//                                 newPost
//                         );

//                         postField.clear();
//                     }
//                 }
//         );

//         bottom.getChildren().addAll(
//                 image,
//                 link,
//                 data,
//                 spacer,
//                 postButton
//         );

//         box.getChildren().addAll(
//                 row,
//                 bottom
//         );

//         return box;
//     }

//     // FILTER BUTTONS
//     private static HBox createFilters() {

//         HBox filters = new HBox(12);

//         filters.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         String[] names = {
//                 "All Farmer Posts",
//                 "Verified Farmers",
//                 "Harvest Reports",
//                 "Field Notes"
//         };

//         for (int i = 0;
//              i < names.length;
//              i++) {

//             Button button =
//                     new Button(names[i]);

//             button.setFont(
//                     Font.font(
//                             "Arial",
//                             14
//                     )
//             );

//             button.setPadding(
//                     new Insets(
//                             9, 17, 9, 17
//                     )
//             );

//             button.setCursor(
//                     javafx.scene.Cursor.HAND
//             );

//             if (i == 0) {

//                 button.setTextFill(
//                         Color.BLACK
//                 );

//                 button.setBackground(
//                         new Background(
//                                 new BackgroundFill(
//                                         Color.rgb(
//                                                 140,
//                                                 220,
//                                                 160
//                                         ),
//                                         new CornerRadii(25),
//                                         Insets.EMPTY
//                                 )
//                         )
//                 );

//             } else {

//                 button.setTextFill(
//                         LIGHT
//                 );

//                 button.setBackground(
//                         new Background(
//                                 new BackgroundFill(
//                                         Color.TRANSPARENT,
//                                         new CornerRadii(25),
//                                         Insets.EMPTY
//                                 )
//                         )
//                 );

//                 button.setBorder(
//                         new Border(
//                                 new BorderStroke(
//                                         Color.rgb(
//                                                 120,
//                                                 140,
//                                                 125
//                                         ),
//                                         BorderStrokeStyle.SOLID,
//                                         new CornerRadii(25),
//                                         new BorderWidths(1)
//                                 )
//                         )
//                 );
//             }

//             filters.getChildren().add(
//                     button
//             );
//         }

//         return filters;
//     }

//     // EXISTING POSTS
//     private static void addExistingPosts(
//             VBox feed) {

//         feed.getChildren().add(
//                 createPostCard(
//                         "Dr. Elena Postova",
//                         "Senior Agronomist",
//                         "2h ago",
//                         "Noticing an unusual pattern of yellowing "
//                                 + "in the lower leaves of early-stage "
//                                 + "winter wheat across several test "
//                                 + "plots in Region 4. Initial soil tests "
//                                 + "indicate potential nitrogen lock-up "
//                                 + "due to recent cold, wet spells rather "
//                                 + "than a primary deficiency. Has anyone "
//                                 + "else observed this in similar "
//                                 + "clay-heavy soils recently?",
//                         true
//                 )
//         );

//         feed.getChildren().add(
//                 createPostCard(
//                         "Marcus Vance",
//                         "Precision Farming Lead",
//                         "5h ago",
//                         "Just integrated the new AI yield prediction "
//                                 + "module into our dashboard. The "
//                                 + "preliminary data suggests a 15% increase "
//                                 + "in harvesting efficiency if we adjust "
//                                 + "our watering schedules based on the "
//                                 + "micro-climate sensor data. Highly "
//                                 + "recommend everyone check out the "
//                                 + "latest update! #PrecisionAg "
//                                 + "#AgriTech #SmartFarming",
//                         false
//                 )
//         );
//     }

//     // CREATE POST CARD
//     private static VBox createPostCard(
//             String name,
//             String role,
//             String time,
//             String text,
//             boolean verified) {

//         VBox card = new VBox(16);

//         card.setPadding(
//                 new Insets(22)
//         );

//         card.setBackground(
//                 new Background(
//                         new BackgroundFill(
//                                 CARD,
//                                 new CornerRadii(18),
//                                 Insets.EMPTY
//                         )
//                 )
//         );

//         card.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 Color.rgb(25, 65, 52),
//                                 BorderStrokeStyle.SOLID,
//                                 new CornerRadii(18),
//                                 new BorderWidths(1)
//                         )
//                 )
//         );

//         // USER ROW
//         HBox userRow = new HBox(12);

//         userRow.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         Label avatar = new Label(
//                 "👨‍🌾"
//         );

//         avatar.setFont(
//                 Font.font(
//                         "Arial",
//                         25
//                 )
//         );

//         avatar.setMinSize(
//                 48,
//                 48
//         );

//         avatar.setAlignment(
//                 Pos.CENTER
//         );

//         VBox userInfo = new VBox(3);

//         HBox nameRow = new HBox(8);

//         nameRow.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         Label nameLabel = new Label(
//                 name
//         );

//         nameLabel.setTextFill(
//                 LIGHT
//         );

//         nameLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         FontWeight.BOLD,
//                         15
//                 )
//         );

//         nameRow.getChildren().add(
//                 nameLabel
//         );

//         if (verified) {

//             Label verifiedLabel =
//                     new Label(
//                             "✓ Verified Farmer"
//                     );

//             verifiedLabel.setTextFill(
//                     Color.rgb(
//                             120,
//                             230,
//                             150
//                     )
//             );

//             verifiedLabel.setFont(
//                     Font.font(
//                             "Arial",
//                             11
//                     )
//             );

//             nameRow.getChildren().add(
//                     verifiedLabel
//             );
//         }

//         Label roleLabel =
//                 new Label(
//                         role + " • " + time
//                 );

//         roleLabel.setTextFill(
//                 MUTED
//         );

//         roleLabel.setFont(
//                 Font.font(
//                         "Arial",
//                         12
//                 )
//         );

//         userInfo.getChildren().addAll(
//                 nameRow,
//                 roleLabel
//         );

//         Region space = new Region();

//         HBox.setHgrow(
//                 space,
//                 Priority.ALWAYS
//         );

//         Label menu = new Label(
//                 "⋮"
//         );

//         menu.setTextFill(
//                 LIGHT
//         );

//         menu.setFont(
//                 Font.font(
//                         "Arial",
//                         25
//                 )
//         );

//         userRow.getChildren().addAll(
//                 avatar,
//                 userInfo,
//                 space,
//                 menu
//         );

//         // POST TEXT
//         Label postText = new Label(
//                 text
//         );

//         postText.setTextFill(
//                 LIGHT
//         );

//         postText.setFont(
//                 Font.font(
//                         "Arial",
//                         16
//                 )
//         );

//         postText.setWrapText(
//                 true
//         );

//         // ACTIONS
//         HBox actions = new HBox(30);

//         actions.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         actions.setPadding(
//                 new Insets(
//                         12, 0, 0, 0
//                 )
//         );

//         actions.setBorder(
//                 new Border(
//                         new BorderStroke(
//                                 Color.rgb(
//                                         35,
//                                         70,
//                                         58
//                                 ),
//                                 BorderStrokeStyle.SOLID,
//                                 CornerRadii.EMPTY,
//                                 new BorderWidths(
//                                         1, 0, 0, 0
//                                 )
//                         )
//                 )
//         );

//         Label like =
//                 new Label("♡  124");

//         Label comment =
//                 new Label("▢  32");

//         Label share =
//                 new Label("♧  12");

//         like.setTextFill(
//                 LIGHT
//         );

//         comment.setTextFill(
//                 LIGHT
//         );

//         share.setTextFill(
//                 LIGHT
//         );

//         actions.getChildren().addAll(
//                 like,
//                 comment,
//                 share
//         );

//         card.getChildren().addAll(
//                 userRow,
//                 postText,
//                 actions
//         );

//         return card;
//     }

//     // BACK TO COMMUNITY
//     public void backtoCommunity() {

//         LoginPage.mainStage.setScene(
//                 communityScene
//         );
//     }
// }

package com.pravartak.view.farmer;

import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class CommunityPage {

    // SAME THEME AS YOUR OTHER AGROBIZ PAGES

    private static final Color BG = Color.rgb(8, 12, 13);
    private static final Color CARD = Color.rgb(21, 42, 36);
    private static final Color CARD_LIGHT = Color.rgb(100, 174, 185);
    private static final Color GREEN = Color.rgb(112, 183, 93);
    private static final Color LIGHT = Color.rgb(245, 245, 245);
    private static final Color MUTED = Color.rgb(170, 170, 170);
    private static final Color BORDER = Color.rgb(32, 42, 44);

    private static Scene communityScene;


    // =========================================================
    // COMMUNITY SCENE
    // =========================================================

    public static Scene getCommunityScene() {

        BorderPane root = new BorderPane();

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                BG,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // ================= NAVBAR =================

        NavBar navBar = new NavBar();

        root.setTop(
                navBar.createNavbar("Community")
        );


        // ================= MAIN PAGE =================

        VBox page = new VBox(22);

        page.setPadding(
                new Insets(25, 35, 35, 35)
        );

        page.setFillWidth(true);

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                BG,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );


        // ================= PAGE TITLE =================

        Label title = new Label("Farmer Community");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(LIGHT);


        Label description = new Label(
                "Connect with farmers, share experiences, "
                        + "discover farming insights, and learn from "
                        + "the agricultural community."
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        description.setTextFill(MUTED);

        description.setWrapText(true);


        // ================= TOP BAR =================

        HBox communityTop = createCommunityTop();


        // ================= POST BOX =================

        VBox feed = new VBox(18);

        feed.setFillWidth(true);

        VBox postBox = createPostBox(feed);


        // ================= FILTERS =================

        HBox filters = createFilters();


        // ================= EXISTING POSTS =================

        addExistingPosts(feed);


        // ================= ADD CONTENT =================

        page.getChildren().addAll(
                title,
                description,
                communityTop,
                postBox,
                filters,
                feed
        );


        // ================= SCROLL =================

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(page);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: #080c0d;"
                        + "-fx-background: #080c0d;"
        );


        root.setCenter(scrollPane);


        // ================= FOOTER =================

        Footer footer = new Footer();

        root.setBottom(
                footer.createFooter()
        );


        // ================= SCENE =================

        Scene scene = new Scene(
                root,
                1368,
                768
        );

        communityScene = scene;

        return scene;
    }


    // =========================================================
    // COMMUNITY TOP SEARCH BAR
    // =========================================================

    private static HBox createCommunityTop() {

        HBox box = new HBox();

        box.setAlignment(
                Pos.CENTER_LEFT
        );


        Label heading = new Label(
                "Community Feed"
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        heading.setTextFill(LIGHT);


        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        TextField search = new TextField();

        search.setPromptText(
                "Search community..."
        );

        search.setPrefSize(
                260,
                38
        );

        search.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        search.setStyle(
                "-fx-background-color: #f5fafb;"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #6c7272;"
                        + "-fx-border-color: #7bfe8b;"
                        + "-fx-border-radius: 6;"
                        + "-fx-background-radius: 6;"
        );


        box.getChildren().addAll(
                heading,
                space,
                search
        );

        return box;
    }


    // =========================================================
    // CREATE POST BOX
    // =========================================================

    private static VBox createPostBox(
            VBox feed) {

        VBox box = new VBox(15);

        box.setPadding(
                new Insets(20)
        );

        box.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD,
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );

        box.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                new BorderWidths(1)
                        )
                )
        );


        // ================= POST ROW =================

        HBox row = new HBox(14);

        row.setAlignment(
                Pos.TOP_LEFT
        );


        Label avatar = new Label("👨‍🌾");

        avatar.setFont(
                Font.font(
                        "Arial",
                        25
                )
        );

        avatar.setMinSize(
                48,
                48
        );

        avatar.setAlignment(
                Pos.CENTER
        );


        TextArea postField = new TextArea();

        postField.setPromptText(
                "Share your farming experience, "
                        + "harvest update, field observation, "
                        + "or ask the community..."
        );

        postField.setWrapText(true);

        postField.setPrefHeight(75);

        postField.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        postField.setStyle(
                "-fx-control-inner-background: #0F1516;"
                        + "-fx-text-fill: white;"
                        + "-fx-prompt-text-fill: #b4d8e4;"
                        + "-fx-border-color: #26f742;"
                        + "-fx-border-radius: 6;"
                        + "-fx-background-radius: 6;"
        );

        HBox.setHgrow(
                postField,
                Priority.ALWAYS
        );


        row.getChildren().addAll(
                avatar,
                postField
        );


        // ================= BOTTOM ROW =================

        HBox bottom = new HBox(20);

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );


        Label image = new Label(
                "▧  Add Image"
        );

        image.setTextFill(MUTED);

        image.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        Label link = new Label(
                "🔗  Add Link"
        );

        link.setTextFill(MUTED);

        link.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        Button postButton = new Button(
                "Post"
        );

        postButton.setPrefSize(
                85,
                36
        );

        postButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        postButton.setTextFill(
                Color.GREEN
        );

        postButton.setCursor(
                javafx.scene.Cursor.HAND
        );

        postButton.setBackground(
                new Background(
                        new BackgroundFill(
                                BG,
                                new CornerRadii(5),
                                Insets.EMPTY
                        )
                )
        );


        // ================= POST ACTION =================

        postButton.setOnAction(
                e -> {

                    String text =
                            postField
                                    .getText()
                                    .trim();

                    if (!text.isEmpty()) {

                        VBox newPost =
                                createPostCard(
                                        "You",
                                        "Farmer",
                                        "Just now",
                                        text,
                                        false
                                );

                        feed.getChildren().add(
                                0,
                                newPost
                        );

                        postField.clear();
                    }
                }
        );


        bottom.getChildren().addAll(
                image,
                link,
                spacer,
                postButton
        );


        box.getChildren().addAll(
                row,
                bottom
        );

        return box;
    }


    // =========================================================
    // FILTER BUTTONS
    // =========================================================

    private static HBox createFilters() {

        HBox filters = new HBox(10);

        filters.setAlignment(
                Pos.CENTER_LEFT
        );


        String[] names = {
                "All Posts",
                "Verified Farmers",
                "Harvest Reports",
                "Field Notes"
        };


        for (int i = 0; i < names.length; i++) {

            Button button =
                    new Button(names[i]);

            button.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            11
                    )
            );

            button.setPadding(
                    new Insets(
                            8,
                            15,
                            8,
                            15
                    )
            );

            button.setCursor(
                    javafx.scene.Cursor.HAND
            );


            if (i == 0) {

                button.setTextFill(
                        Color.BLACK
                );

                button.setBackground(
                        new Background(
                                new BackgroundFill(
                                        GREEN,
                                        new CornerRadii(5),
                                        Insets.EMPTY
                                )
                        )
                );

            } else {

                button.setTextFill(
                        MUTED
                );

                button.setBackground(
                        new Background(
                                new BackgroundFill(
                                        CARD,
                                        new CornerRadii(5),
                                        Insets.EMPTY
                                )
                        )
                );

                button.setBorder(
                        new Border(
                                new BorderStroke(
                                        BORDER,
                                        BorderStrokeStyle.SOLID,
                                        new CornerRadii(5),
                                        new BorderWidths(1)
                                )
                        )
                );
            }


            filters.getChildren().add(
                    button
            );
        }

        return filters;
    }


    // =========================================================
    // EXISTING POSTS
    // =========================================================

    private static void addExistingPosts(
            VBox feed) {

        feed.getChildren().add(
                createPostCard(
                        "Dr. Elena Postova",
                        "Senior Agronomist",
                        "2h ago",
                        "Noticing an unusual pattern of yellowing "
                                + "in the lower leaves of early-stage "
                                + "winter wheat across several test plots "
                                + "in Region 4. Initial soil tests indicate "
                                + "potential nitrogen lock-up due to recent "
                                + "cold, wet spells rather than a primary "
                                + "deficiency. Has anyone else observed "
                                + "this in similar clay-heavy soils recently?",
                        true
                )
        );


        feed.getChildren().add(
                createPostCard(
                        "Marcus Vance",
                        "Precision Farming Lead",
                        "5h ago",
                        "Just integrated the new AI yield prediction "
                                + "module into our dashboard. The preliminary "
                                + "data suggests a 15% increase in harvesting "
                                + "efficiency if we adjust our watering "
                                + "schedules based on the micro-climate data. "
                                + "Highly recommend everyone check out the "
                                + "latest update! #PrecisionAg #AgriTech "
                                + "#SmartFarming",
                        false
                )
        );
    }


    // =========================================================
    // CREATE POST CARD
    // =========================================================

    private static VBox createPostCard(
            String name,
            String role,
            String time,
            String text,
            boolean verified) {

        VBox card = new VBox(15);

        card.setPadding(
                new Insets(20)
        );

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                CARD,
                                new CornerRadii(10),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10),
                                new BorderWidths(1)
                        )
                )
        );


        // ================= USER ROW =================

        HBox userRow = new HBox(12);

        userRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label avatar = new Label(
                "👨‍🌾"
        );

        avatar.setFont(
                Font.font(
                        "Arial",
                        23
                )
        );

        avatar.setMinSize(
                45,
                45
        );

        avatar.setAlignment(
                Pos.CENTER
        );


        VBox userInfo = new VBox(3);


        HBox nameRow = new HBox(8);

        nameRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label nameLabel = new Label(
                name
        );

        nameLabel.setTextFill(
                LIGHT
        );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        nameRow.getChildren().add(
                nameLabel
        );


        if (verified) {

            Label verifiedLabel =
                    new Label(
                            "✓ Verified Farmer"
                    );

            verifiedLabel.setTextFill(
                    GREEN
            );

            verifiedLabel.setFont(
                    Font.font(
                            "Arial",
                            11
                    )
            );

            nameRow.getChildren().add(
                    verifiedLabel
            );
        }


        Label roleLabel =
                new Label(
                        role + " • " + time
                );

        roleLabel.setTextFill(
                MUTED
        );

        roleLabel.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );


        userInfo.getChildren().addAll(
                nameRow,
                roleLabel
        );


        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );


        Label menu =
                new Label("⋮");

        menu.setTextFill(
                MUTED
        );

        menu.setFont(
                Font.font(
                        "Arial",
                        22
                )
        );


        userRow.getChildren().addAll(
                avatar,
                userInfo,
                space,
                menu
        );


        // ================= POST TEXT =================

        Label postText =
                new Label(text);

        postText.setTextFill(
                LIGHT
        );

        postText.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        postText.setWrapText(
                true
        );


        // ================= ACTIONS =================

        HBox actions =
                new HBox(30);

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        actions.setPadding(
                new Insets(
                        12,
                        0,
                        0,
                        0
                )
        );

        actions.setBorder(
                new Border(
                        new BorderStroke(
                                BORDER,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(
                                        1,
                                        0,
                                        0,
                                        0
                                )
                        )
                )
        );


        Label like =
                new Label("♡  124");

        Label comment =
                new Label("▢  32");

        Label share =
                new Label("♧  12");


        like.setTextFill(
                MUTED
        );

        comment.setTextFill(
                MUTED
        );

        share.setTextFill(
                MUTED
        );


        like.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        comment.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        share.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        actions.getChildren().addAll(
                like,
                comment,
                share
        );


        card.getChildren().addAll(
                userRow,
                postText,
                actions
        );


        return card;
    }
}