// package com.pravartak.view.farmer;
// import java.net.URL;
// import java.util.Random;
// import com.pravartak.view.farmer.common.Footer;
// import com.pravartak.view.farmer.common.NavBar;
// import com.pravartak.view.login.LoginPage;
// import javafx.animation.Animation;
// import javafx.animation.FadeTransition;
// import javafx.animation.TranslateTransition;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.CycleMethod;
// import javafx.scene.paint.RadialGradient;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Rectangle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.util.Duration;
// public class HomePageFarmer {
//     private Scene homepagescene;
    
//     // ANIMATED BACKGROUND
    
//     public HomePageFarmer(int farmerId, String firebaseUid) {
       
// }

//     private StackPane createAnimatedBackground() {
//         StackPane backgroundPane = new StackPane();
//         // Main dark green background
//         // backgroundPane.setStyle( "-fx-background-color: linear-gradient("
//         //                          + "to bottom right," + "rgb(3, 49, 13)%0," + "#123a1e 45%," 
//         //                         + "#0b1b12 100%);" );
//         backgroundPane.setStyle(
//         "-fx-background-color: linear-gradient(to bottom right, #050908 0%, #08130d 45%, #0b1b12 100%);"
// );
//         // GLOWING CIRCLE 1
//         Circle glow1 = new Circle(200);
//         glow1.setMouseTransparent(true);
//         glow1.setFill( new RadialGradient( 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new javafx.scene.paint.Stop( 0, Color.web("#0e5123") ), new javafx.scene.paint.Stop( 1, Color.TRANSPARENT ) ) );
//         StackPane.setAlignment( glow1, Pos.TOP_LEFT );
//         StackPane.setMargin( glow1, new Insets(-100, 0, 0, -100) );
        
//         // GLOWING CIRCLE 2
//         Circle glow2 = new Circle(180);
//         glow2.setMouseTransparent(true);
//         glow2.setFill( new RadialGradient( 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new javafx.scene.paint.Stop( 0, Color.web("#0e5123") ), new javafx.scene.paint.Stop( 1, Color.TRANSPARENT ) ) );
//         StackPane.setAlignment( glow2, Pos.BOTTOM_RIGHT );
//         StackPane.setMargin( glow2, new Insets(0, -90, -90, 0) );

//         // GLOWING CIRCLE 3

//         Circle glow3 = new Circle(130);
//         glow3.setMouseTransparent(true);
//         glow3.setFill( new RadialGradient( 0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new javafx.scene.paint.Stop( 0, Color.web("#0e5123") ), new javafx.scene.paint.Stop( 1, Color.TRANSPARENT ) ) );
//         StackPane.setAlignment( glow3, Pos.CENTER_RIGHT );
//         StackPane.setMargin( glow3, new Insets(0, -100, 0, 0) );

//         // GLOW ANIMATION
//         TranslateTransition glowMove1 =new TranslateTransition( Duration.seconds(10), glow1 );
//         glowMove1.setFromX(0);
//         glowMove1.setFromY(0);
//         glowMove1.setToX(120);
//         glowMove1.setToY(70);
//         glowMove1.setAutoReverse(true);
//         glowMove1.setCycleCount( Animation.INDEFINITE );
//         glowMove1.play();
//         // -----------------------------------------------------
//         TranslateTransition glowMove2 =
//                 new TranslateTransition( Duration.seconds(12), glow2 );
//         glowMove2.setFromX(0);
//         glowMove2.setFromY(0);
//         glowMove2.setToX(-110);
//         glowMove2.setToY(-70);
//         glowMove2.setAutoReverse(true);
//         glowMove2.setCycleCount( Animation.INDEFINITE );
//         glowMove2.play();
//         // -----------------------------------------------------
//         TranslateTransition glowMove3 =
//                 new TranslateTransition( Duration.seconds(8), glow3 );
//         glowMove3.setFromX(0);
//         glowMove3.setFromY(0);
//         glowMove3.setToX(-70);
//         glowMove3.setToY(80);
//         glowMove3.setAutoReverse(true);
//         glowMove3.setCycleCount( Animation.INDEFINITE );
//         glowMove3.play();
//         backgroundPane.getChildren().addAll( glow1, glow2, glow3 );
//         // FLOATING PARTICLES
//         Random random = new Random();
//         for (int i = 0; i < 30; i++) {
//             Circle particle =
//                     new Circle( 1.5 + random.nextDouble() * 2.5 );
//                     particle.setMouseTransparent(true);
//             particle.setFill( Color.rgb( 104, 211, 74, 0.15 + random.nextDouble() * 0.30 ) );
//             double startX =
//                     random.nextDouble() * 1200;
//             double startY =
//                     random.nextDouble() * 900;
//             particle.setTranslateX(startX);
//             particle.setTranslateY(startY);
//             backgroundPane
//                     .getChildren()
//                     .add(particle);
//             // Particle movement
//             TranslateTransition move =
//                     new TranslateTransition( Duration.seconds( 5 + random.nextDouble() * 7 ), particle );
//             move.setByX( -40 + random.nextDouble() * 80 );
//             move.setByY( -80 - random.nextDouble() * 100 );
//             move.setAutoReverse(true);
//             move.setCycleCount( Animation.INDEFINITE );
//             move.play();
//             // Particle fade
//             FadeTransition fade =
//                     new FadeTransition( Duration.seconds( 2.5 + random.nextDouble() * 5 ), particle );
//             fade.setFromValue(0.15);
//             fade.setToValue(0.75);
//             fade.setAutoReverse(true);
//             fade.setCycleCount( Animation.INDEFINITE );
//             fade.play();
//         }
//         return backgroundPane;
//     }
    
//     // HOME PAGE
    
//     public Scene getHomePageFarmer() {
//         // MAIN BORDER PANE
//         BorderPane borderPane =new BorderPane();
//         borderPane.setStyle( "-fx-background-color: #080c0d;" );
//         borderPane.setTop( new NavBar().createNavbar("Home"));
      
//         borderPane.setBottom( new Footer().createFooter() );
        
//         // MAIN VBOX
//         VBox mainVBox =new VBox(28);
//         mainVBox.setPadding( new Insets( 18, 18, 30, 18 ) );
//         mainVBox.setFillWidth(true);

//         // HERO SECTION
//         StackPane firstHBox =new StackPane();
//         firstHBox.setPrefHeight(300);
//         firstHBox.setMinHeight(400);
//         firstHBox.setPrefWidth( Double.MAX_VALUE );

//         // HERO IMAGE
//         URL imageURL =
//                 getClass().getResource( "/ChatGPT Image Aug 23, 2026, 03_16_50 PM.png" );
//         if (imageURL == null) {
//             throw new RuntimeException( "image.png not found!\n" + "Put it inside:\n" + "src/main/resources/image.png" );
//         }
//         Image farmImage =new Image( imageURL.toExternalForm() );
//         ImageView farmImageView =new ImageView( farmImage );
//         farmImageView.setFitHeight( 450 );
//         farmImageView.setFitWidth( 1300 );
//         farmImageView.setPreserveRatio( false );

//         // HERO TITLE
//         Label mainTitle =new Label( "     Revolutionize Your\n" + "Livestock Management with AI" );
//         mainTitle.setFont( Font.font( "Arial", FontWeight.BOLD, 38 ) );
//         mainTitle.setTextFill( Color.web("#111010") );
//         mainTitle.setWrapText(true);
//         mainTitle.setAlignment( Pos.CENTER );
//         mainTitle.setMaxWidth(850);

//         // SEARCH FIELD
//         TextField farmSearch =
//                 new TextField();
//         farmSearch.setPromptText( "Search products, livestock, or farming guides..." );
//         farmSearch.setPrefWidth( 430 );
//         farmSearch.setPrefHeight( 42 );
//         farmSearch.setStyle( "-fx-background-color: #101516;" + "-fx-text-fill: #eeeeee;" + "-fx-prompt-text-fill: #777777;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8;" );
        
//         // EXPLORE BUTTON
//         Button explorerButton =
//                 new Button("Explore");
//         explorerButton.setPrefHeight( 42 );
//         explorerButton.setPrefWidth( 90 );
//         explorerButton.setStyle( "-fx-background-color: #68d34a;" + "-fx-text-fill: #080c0d;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-cursor: hand;" );
//         explorerButton.setOnAction( event -> {
//                  ExplorerPage explorerpage = new ExplorerPage();
//                 LoginPage.mainStage.setScene( explorerpage.getExplorerPage() );
//                 } 
//                 );

//         // SEARCH HBOX
//         HBox searchHBox = new HBox(8);
//         searchHBox.setAlignment( Pos.CENTER );
//         searchHBox.getChildren().addAll( farmSearch, explorerButton );

//         // HERO CONTENT
//         VBox heroContent =new VBox(15);
//         heroContent.setAlignment( Pos.CENTER );
//         heroContent.getChildren().addAll( mainTitle, searchHBox );
//         firstHBox.getChildren().addAll( farmImageView, heroContent );
//         StackPane.setAlignment( heroContent, Pos.CENTER );

//         // TRENDING CATEGORY SECTION
//         VBox secondVBox =new VBox(14);
//         secondVBox.setPrefWidth( Double.MAX_VALUE );

//         Label trendingLabel =new Label( "Trending Categories" );
//         trendingLabel.setFont( Font.font( "Arial", FontWeight.BOLD, 23 ) );
//         trendingLabel.setTextFill( Color.web("#eeeeee") );

//         Region headingSpace =new Region();
//         HBox.setHgrow( headingSpace, Priority.ALWAYS );

//         Button viewButton =new Button("View All");
//         viewButton.setStyle( "-fx-background-color: transparent;" + "-fx-text-fill: #68d34a;" + "-fx-font-weight: bold;" + "-fx-cursor: hand;" );
//         viewButton.setOnMouseEntered( e -> { viewButton.setStyle( "-fx-background-color: #101516;" + "-fx-text-fill: #68d34a;" + "-fx-border-color: #68d34a;" + "-fx-border-radius: 6;" + "-fx-background-radius: 6;" + "-fx-padding: 7 12;" + "-fx-cursor: hand;" ); } );
//         viewButton.setOnMouseExited( e -> { viewButton.setStyle( "-fx-background-color: transparent;" + "-fx-text-fill: #68d34a;" + "-fx-font-weight: bold;" + "-fx-cursor: hand;" ); } );
//         viewButton.setOnAction( event -> { ExplorerPage explorerpage = new ExplorerPage(); LoginPage.mainStage.setScene( explorerpage.getExplorerPage() ); } );
        
//         HBox headingHBox =new HBox();
//         headingHBox.setAlignment( Pos.CENTER_LEFT );
//         headingHBox.setMaxWidth( Double.MAX_VALUE );
//         headingHBox.getChildren().addAll( trendingLabel, headingSpace, viewButton );

//         // CATEGORY CARDS
//         HBox secondHBox =new HBox(16);
//         secondHBox.setAlignment( Pos.CENTER );
//         secondHBox.setFillHeight(true);
//         secondHBox.setPrefWidth( Double.MAX_VALUE );
//         secondHBox.setMaxWidth( Double.MAX_VALUE );

//         // POULTRY
//         VBox poultryBox =
//                 new VBox(8);
//         poultryBox.setPadding( new Insets(8) );
//         poultryBox.setPrefWidth(230);
//         poultryBox.setPrefHeight(205);
//         poultryBox.setAlignment( Pos.TOP_LEFT );
//         poultryBox.setStyle( "-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;" );
//         Image poultryImage =
//                 new Image( getClass() .getResource( "/poltry.png" ) .toExternalForm() );
//         ImageView poultryImageView =
//                 new ImageView( poultryImage );
//         poultryImageView.setFitWidth( 180 );
//         poultryImageView.setFitHeight( 95 );
//         Label poultryTitle =
//                 new Label( "Poultry Farming" );
//         poultryTitle.setFont( Font.font( "Arial", FontWeight.BOLD, 15 ) );
//         poultryTitle.setTextFill( Color.web("#eeeeee") );
//         Label poultryDescription =
//                 new Label( "Advanced systems for optimal bird health." );
//         poultryDescription.setFont( Font.font( "Arial", 11 ) );
//         poultryDescription.setWrapText(true);
//         poultryDescription.setTextFill( Color.web("#aaaaaa") );
//         poultryBox.getChildren()
//                 .addAll( poultryImageView, poultryTitle, poultryDescription );
//         // DAIRY
//         VBox dairyBox =
//                 new VBox(8);
//         dairyBox.setPadding( new Insets(8) );
//         dairyBox.setPrefWidth(230);
//         dairyBox.setPrefHeight(205);
//         dairyBox.setAlignment( Pos.TOP_LEFT );
//         dairyBox.setStyle( "-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;" );
//         Image dairyImage =
//                 new Image( getClass() .getResource( "/Dairy.png" ) .toExternalForm() );
//         ImageView dairyImageView =
//                 new ImageView( dairyImage );
//         dairyImageView.setFitWidth( 180 );
//         dairyImageView.setFitHeight( 95 );
//         Label dairyTitle =
//                 new Label( "Dairy Farming" );
//         dairyTitle.setFont( Font.font( "Arial", FontWeight.BOLD, 15 ) );
//         dairyTitle.setTextFill( Color.web("#eeeeee") );
//         Label dairyDescription =
//                 new Label( "Modern techniques for sustainable milk production." );
//         dairyDescription.setFont( Font.font( "Arial", 11 ) );
//         dairyDescription.setWrapText(true);
//         dairyDescription.setTextFill( Color.web("#aaaaaa") );
//         dairyBox.getChildren()
//                 .addAll( dairyImageView, dairyTitle, dairyDescription );
//         // PRECISION DAIRY
//         VBox precisionBox =
//                 new VBox(8);
//         precisionBox.setPadding( new Insets(8) );
//         precisionBox.setPrefWidth(230);
//         precisionBox.setPrefHeight(205);
//         precisionBox.setAlignment( Pos.TOP_LEFT );
//         precisionBox.setStyle( "-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;" );
//         Image precisionImage =
//                 new Image( getClass() .getResource( "/any.png" ) .toExternalForm() );
//         ImageView precisionImageView =
//                 new ImageView( precisionImage );
//         precisionImageView.setFitWidth( 180 );
//         precisionImageView.setFitHeight( 95 );
//         Label precisionTitle =
//                 new Label( "Precision Dairy" );
//         precisionTitle.setFont( Font.font( "Arial", FontWeight.BOLD, 15 ) );
//         precisionTitle.setTextFill( Color.web("#eeeeee") );
//         Label precisionDescription =
//                 new Label( "Data-driven livestock management." );
//         precisionDescription.setFont( Font.font( "Arial", 11 ) );
//         precisionDescription.setWrapText(true);
//         precisionDescription.setTextFill( Color.web("#aaaaaa") );
//         precisionBox.getChildren()
//                 .addAll( precisionImageView, precisionTitle, precisionDescription );
//         // SMART MACHINERY
//         VBox machineryBox =
//                 new VBox(8);
//         machineryBox.setPadding( new Insets(8) );
//         machineryBox.setPrefWidth(230);
//         machineryBox.setPrefHeight(205);
//         machineryBox.setAlignment( Pos.TOP_LEFT );
//         machineryBox.setStyle( "-fx-background-color: #101516;" + "-fx-background-radius: 12;" + "-fx-border-color: #242b2c;" + "-fx-border-radius: 12;" );
//         Image machineryImage =
//                 new Image( getClass() .getResource( "/any.png" ) .toExternalForm() );
//         ImageView machineryImageView =
//                 new ImageView( machineryImage );
//         machineryImageView.setFitWidth( 180 );
//         machineryImageView.setFitHeight( 95 );
//         Label machineryTitle =
//                 new Label( "Smart Machinery" );
//         machineryTitle.setFont( Font.font( "Arial", FontWeight.BOLD, 15 ) );
//         machineryTitle.setTextFill( Color.web("#eeeeee") );
//         Label machineryDescription =
//                 new Label( "Automated equipment for scale." );
//         machineryDescription.setFont( Font.font( "Arial", 11 ) );
//         machineryDescription.setWrapText(true);
//         machineryDescription.setTextFill( Color.web("#aaaaaa") );
//         machineryBox.getChildren()
//                 .addAll( machineryImageView, machineryTitle, machineryDescription );
//         secondHBox.getChildren()
//                 .addAll( poultryBox, dairyBox, precisionBox, machineryBox );
//         secondVBox.getChildren()
//                 .addAll( headingHBox, secondHBox );
//         secondVBox.setPrefWidth( Double.MAX_VALUE );
//         secondHBox.setAlignment( Pos.CENTER );

//         // THIRD SECTION
//         HBox thirdHBox =new HBox(20);
//         thirdHBox.setPadding( new Insets(28) );
//         thirdHBox.setAlignment( Pos.CENTER_LEFT );
//         thirdHBox.setPrefHeight( 250 );
//         thirdHBox.setMaxWidth( Double.MAX_VALUE );
//         thirdHBox.setStyle( "-fx-background-color: #245d35;" + "-fx-background-radius: 12;" + "-fx-border-color: #2d6b3f;" + "-fx-border-radius: 12;" );
        
//         VBox thirdTextVBox =new VBox(8);
//         thirdTextVBox.setAlignment( Pos.CENTER_LEFT );

//         Label intelligentLabel =new Label( "INTELLIGENT PLANNING" );
//         intelligentLabel.setTextFill( Color.web("#68d34a") );
//         intelligentLabel.setFont( Font.font( "Arial", FontWeight.BOLD, 11 ) );
//         Label planTitle =new Label( "AI Business Plan Generator" );
//         planTitle.setTextFill( Color.web("#eeeeee") );
//         planTitle.setFont( Font.font( "Arial", FontWeight.BOLD, 23 ) );
//         Label planDescription =new Label( "Leverage predictive analytics and local market data " + "to craft an optimal business strategy for your next " + "herd expansion. Minimize risk, maximize yield." );
//         planDescription.setTextFill( Color.web("#d0d8d3") );
//         planDescription.setFont( Font.font( "Arial", 13 ) );
//         planDescription.setWrapText(true);
//         planDescription.setMaxWidth( 500 );
//         thirdTextVBox.getChildren().addAll( intelligentLabel, planTitle, planDescription );

//         Region thirdSpace =new Region();
//         HBox.setHgrow( thirdSpace, Priority.ALWAYS );

//         Button generateButton =new Button( "Generate Plan  ✨" );
//         generateButton.setPrefWidth( 125 );
//         generateButton.setPrefHeight( 45 );
//         generateButton.setStyle( "-fx-background-color: #68d34a;" + "-fx-text-fill: #080c0d;" + "-fx-font-weight: bold;" + "-fx-background-radius: 6;" + "-fx-cursor: hand;" );
//         thirdHBox.getChildren().addAll( thirdTextVBox, thirdSpace, generateButton );

//         // ADD MAIN CONTENT
//         mainVBox.getChildren().addAll( firstHBox, secondVBox, thirdHBox );

//         // Transparent because animated background is behind it
//         mainVBox.setStyle( "-fx-background-color: transparent;" + "-fx-padding: 28px;" );
//         mainVBox.setPrefHeight( Region.USE_COMPUTED_SIZE );
//         mainVBox.setPrefWidth( Double.MAX_VALUE );

//         // SCROLL PANE
//         ScrollPane scrollPane =new ScrollPane();
//         scrollPane.setContent( mainVBox );
//         scrollPane.setFitToWidth( true );
//         scrollPane.setFitToHeight( false );
//         scrollPane.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
//         scrollPane.setVbarPolicy( ScrollPane.ScrollBarPolicy.AS_NEEDED );
//         scrollPane.setMaxWidth( Double.MAX_VALUE );
//         scrollPane.setStyle( "-fx-background: transparent;" + "-fx-background-color: transparent;" + "-fx-border-color: transparent;" );
        
//         // ANIMATED BACKGROUND
//         StackPane animatedBackground =createAnimatedBackground();
//         Rectangle clip = new Rectangle();

//                 clip.widthProperty().bind(animatedBackground.widthProperty());
//                 clip.heightProperty().bind(animatedBackground.heightProperty());

//         animatedBackground.setClip(clip);
//         animatedBackground.getChildren().add( scrollPane );
//         StackPane.setAlignment( scrollPane, Pos.CENTER );

//         // SET CENTER
//         borderPane.setCenter( animatedBackground );

//         // SCENE
//         Scene scene =new Scene( borderPane, 800, 768 );
//         homepagescene =scene;
//         return scene;
//     }
    
//     // BACK TO HOME
//     public void backtohome() {
//         LoginPage.mainStage.setScene( homepagescene );
//     }
// }
package com.pravartak.view.farmer;

import java.net.URL;
import java.util.List;
import java.util.Random;

import com.pravartak.dao.admindao.FirebaseCourseDAO;
import com.pravartak.model.admin.Course;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.farmer.common.NavBar;
import com.pravartak.view.login.LoginPage;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class HomePageFarmer {

    private Scene homepagescene;

    // =========================================================
    // DAO
    // =========================================================

    private final FirebaseCourseDAO courseDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public HomePageFarmer(int farmerId, String firebaseUid) {

        courseDAO = new FirebaseCourseDAO();
    }

    // =========================================================
    // ANIMATED BACKGROUND
    // =========================================================

    private StackPane createAnimatedBackground() {

        StackPane backgroundPane = new StackPane();

        // Main dark green background
        backgroundPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, "
                        + "#050908 0%, "
                        + "#08130d 45%, "
                        + "#0b1b12 100%);"
        );

        // =====================================================
        // GLOWING CIRCLE 1
        // =====================================================

        Circle glow1 = new Circle(200);

        glow1.setMouseTransparent(true);

        glow1.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.5,
                        0.5,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(
                                0,
                                Color.web("#0e5123")
                        ),
                        new javafx.scene.paint.Stop(
                                1,
                                Color.TRANSPARENT
                        )
                )
        );

        StackPane.setAlignment(
                glow1,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                glow1,
                new Insets(
                        -100,
                        0,
                        0,
                        -100
                )
        );

        // =====================================================
        // GLOWING CIRCLE 2
        // =====================================================

        Circle glow2 = new Circle(180);

        glow2.setMouseTransparent(true);

        glow2.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.5,
                        0.5,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(
                                0,
                                Color.web("#0e5123")
                        ),
                        new javafx.scene.paint.Stop(
                                1,
                                Color.TRANSPARENT
                        )
                )
        );

        StackPane.setAlignment(
                glow2,
                Pos.BOTTOM_RIGHT
        );

        StackPane.setMargin(
                glow2,
                new Insets(
                        0,
                        -90,
                        -90,
                        0
                )
        );

        // =====================================================
        // GLOWING CIRCLE 3
        // =====================================================

        Circle glow3 = new Circle(130);

        glow3.setMouseTransparent(true);

        glow3.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.5,
                        0.5,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(
                                0,
                                Color.web("#0e5123")
                        ),
                        new javafx.scene.paint.Stop(
                                1,
                                Color.TRANSPARENT
                        )
                )
        );

        StackPane.setAlignment(
                glow3,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                glow3,
                new Insets(
                        0,
                        -100,
                        0,
                        0
                )
        );

        // =====================================================
        // GLOW ANIMATION
        // =====================================================

        TranslateTransition glowMove1 =
                new TranslateTransition(
                        Duration.seconds(10),
                        glow1
                );

        glowMove1.setFromX(0);
        glowMove1.setFromY(0);
        glowMove1.setToX(120);
        glowMove1.setToY(70);
        glowMove1.setAutoReverse(true);
        glowMove1.setCycleCount(
                Animation.INDEFINITE
        );
        glowMove1.play();

        TranslateTransition glowMove2 =
                new TranslateTransition(
                        Duration.seconds(12),
                        glow2
                );

        glowMove2.setFromX(0);
        glowMove2.setFromY(0);
        glowMove2.setToX(-110);
        glowMove2.setToY(-70);
        glowMove2.setAutoReverse(true);
        glowMove2.setCycleCount(
                Animation.INDEFINITE
        );
        glowMove2.play();

        TranslateTransition glowMove3 =
                new TranslateTransition(
                        Duration.seconds(8),
                        glow3
                );

        glowMove3.setFromX(0);
        glowMove3.setFromY(0);
        glowMove3.setToX(-70);
        glowMove3.setToY(80);
        glowMove3.setAutoReverse(true);
        glowMove3.setCycleCount(
                Animation.INDEFINITE
        );
        glowMove3.play();

        backgroundPane.getChildren().addAll(
                glow1,
                glow2,
                glow3
        );

        // =====================================================
        // FLOATING PARTICLES
        // =====================================================

        Random random = new Random();

        for (int i = 0; i < 30; i++) {

            Circle particle =
                    new Circle(
                            1.5
                                    + random.nextDouble()
                                    * 2.5
                    );

            particle.setMouseTransparent(true);

            particle.setFill(
                    Color.rgb(
                            104,
                            211,
                            74,
                            0.15
                                    + random.nextDouble()
                                    * 0.30
                    )
            );

            double startX =
                    random.nextDouble() * 1200;

            double startY =
                    random.nextDouble() * 900;

            particle.setTranslateX(startX);
            particle.setTranslateY(startY);

            backgroundPane
                    .getChildren()
                    .add(particle);

            TranslateTransition move =
                    new TranslateTransition(
                            Duration.seconds(
                                    5
                                            + random.nextDouble()
                                            * 7
                            ),
                            particle
                    );

            move.setByX(
                    -40
                            + random.nextDouble()
                            * 80
            );

            move.setByY(
                    -80
                            - random.nextDouble()
                            * 100
            );

            move.setAutoReverse(true);

            move.setCycleCount(
                    Animation.INDEFINITE
            );

            move.play();

            FadeTransition fade =
                    new FadeTransition(
                            Duration.seconds(
                                    2.5
                                            + random.nextDouble()
                                            * 5
                            ),
                            particle
                    );

            fade.setFromValue(0.15);
            fade.setToValue(0.75);
            fade.setAutoReverse(true);
            fade.setCycleCount(
                    Animation.INDEFINITE
            );

            fade.play();
        }

        return backgroundPane;
    }

    // =========================================================
    // HOME PAGE
    // =========================================================

    public Scene getHomePageFarmer() {

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane borderPane =
                new BorderPane();

        borderPane.setStyle(
                "-fx-background-color:#080c0d;"
        );

        borderPane.setTop(
                new NavBar().createNavbar("Home")
        );

        borderPane.setBottom(
                new Footer().createFooter()
        );

        // =====================================================
        // MAIN VBOX
        // =====================================================

        VBox mainVBox =
                new VBox(28);

        mainVBox.setPadding(
                new Insets(
                        18,
                        18,
                        30,
                        18
                )
        );

        mainVBox.setFillWidth(true);

        // =====================================================
        // HERO SECTION
        // =====================================================

        StackPane firstHBox =
                new StackPane();

        firstHBox.setPrefHeight(300);
        firstHBox.setMinHeight(400);
        firstHBox.setPrefWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // HERO IMAGE
        // =====================================================

        URL imageURL =
                getClass().getResource(
                        "/ChatGPT Image Aug 23, 2026, 03_16_50 PM.png"
                );

        if (imageURL == null) {

            throw new RuntimeException(
                    "image.png not found!\n"
                            + "Put it inside:\n"
                            + "src/main/resources/image.png"
            );
        }

        Image farmImage =
                new Image(
                        imageURL.toExternalForm()
                );

        ImageView farmImageView =
                new ImageView(
                        farmImage
                );

        farmImageView.setFitHeight(450);
        farmImageView.setFitWidth(1300);
        farmImageView.setPreserveRatio(false);

        // =====================================================
        // HERO TITLE
        // =====================================================

        Label mainTitle =
                new Label(
                        "     Revolutionize Your\n"
                                + "Livestock Management with AI"
                );

        mainTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        38
                )
        );

        mainTitle.setTextFill(
                Color.web("#111010")
        );

        mainTitle.setWrapText(true);
        mainTitle.setAlignment(Pos.CENTER);
        mainTitle.setMaxWidth(850);

        // =====================================================
        // SEARCH FIELD
        // =====================================================

        TextField farmSearch =
                new TextField();

        farmSearch.setPromptText(
                "Search products, livestock, or farming guides..."
        );

        farmSearch.setPrefWidth(430);
        farmSearch.setPrefHeight(42);

        farmSearch.setStyle(
                "-fx-background-color:#101516;"
                        + "-fx-text-fill:#eeeeee;"
                        + "-fx-prompt-text-fill:#777777;"
                        + "-fx-border-color:#242b2c;"
                        + "-fx-border-radius:8;"
                        + "-fx-background-radius:8;"
        );

        // =====================================================
        // EXPLORE BUTTON
        // =====================================================

        Button explorerButton =
                new Button("Explore");

        explorerButton.setPrefHeight(42);
        explorerButton.setPrefWidth(90);

        explorerButton.setStyle(
                "-fx-background-color:#68d34a;"
                        + "-fx-text-fill:#080c0d;"
                        + "-fx-font-weight:bold;"
                        + "-fx-background-radius:6;"
                        + "-fx-cursor:hand;"
        );

        explorerButton.setOnAction(
                event -> {

                    ExplorerPage explorerPage =
                            new ExplorerPage();

                    LoginPage.mainStage.setScene(
                            explorerPage.getExplorerPage()
                    );
                }
        );

        // =====================================================
        // SEARCH HBOX
        // =====================================================

        HBox searchHBox =
                new HBox(8);

        searchHBox.setAlignment(
                Pos.CENTER
        );

        searchHBox.getChildren().addAll(
                farmSearch,
                explorerButton
        );

        // =====================================================
        // HERO CONTENT
        // =====================================================

        VBox heroContent =
                new VBox(15);

        heroContent.setAlignment(
                Pos.CENTER
        );

        heroContent.getChildren().addAll(
                mainTitle,
                searchHBox
        );

        firstHBox.getChildren().addAll(
                farmImageView,
                heroContent
        );

        StackPane.setAlignment(
                heroContent,
                Pos.CENTER
        );

        // =====================================================
        // TRENDING COURSE SECTION
        // =====================================================

        VBox secondVBox =
                new VBox(14);

        secondVBox.setPrefWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // HEADING
        // =====================================================

        Label trendingLabel =
                new Label(
                        "Trending Courses"
                );

        trendingLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        trendingLabel.setTextFill(
                Color.web("#eeeeee")
        );

        Region headingSpace =
                new Region();

        HBox.setHgrow(
                headingSpace,
                Priority.ALWAYS
        );

        Button viewButton =
                new Button("View All");

        viewButton.setStyle(
                "-fx-background-color:transparent;"
                        + "-fx-text-fill:#68d34a;"
                        + "-fx-font-weight:bold;"
                        + "-fx-cursor:hand;"
        );

        viewButton.setOnMouseEntered(
                e -> viewButton.setStyle(
                        "-fx-background-color:#101516;"
                                + "-fx-text-fill:#68d34a;"
                                + "-fx-border-color:#68d34a;"
                                + "-fx-border-radius:6;"
                                + "-fx-background-radius:6;"
                                + "-fx-padding:7 12;"
                                + "-fx-cursor:hand;"
                )
        );

        viewButton.setOnMouseExited(
                e -> viewButton.setStyle(
                        "-fx-background-color:transparent;"
                                + "-fx-text-fill:#68d34a;"
                                + "-fx-font-weight:bold;"
                                + "-fx-cursor:hand;"
                )
        );

        // =====================================================
        // VIEW ALL ACTION
        // =====================================================

        viewButton.setOnAction(
                event -> {

                    ExplorerPage explorerPage =
                            new ExplorerPage();

                    LoginPage.mainStage.setScene(
                            explorerPage.getExplorerPage()
                    );
                }
        );

        HBox headingHBox =
                new HBox();

        headingHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        headingHBox.setMaxWidth(
                Double.MAX_VALUE
        );

        headingHBox.getChildren().addAll(
                trendingLabel,
                headingSpace,
                viewButton
        );

        // =====================================================
        // DYNAMIC COURSE CARDS
        // =====================================================

        HBox secondHBox =
                new HBox(16);

        secondHBox.setAlignment(
                Pos.CENTER
        );

        secondHBox.setFillHeight(true);

        secondHBox.setPrefWidth(
                Double.MAX_VALUE
        );

        secondHBox.setMaxWidth(
                Double.MAX_VALUE
        );

        loadTrendingCourses(
                secondHBox
        );

        secondVBox.getChildren().addAll(
                headingHBox,
                secondHBox
        );

        secondVBox.setPrefWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // THIRD SECTION
        // =====================================================

        HBox thirdHBox =
                new HBox(20);

        thirdHBox.setPadding(
                new Insets(28)
        );

        thirdHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        thirdHBox.setPrefHeight(250);

        thirdHBox.setMaxWidth(
                Double.MAX_VALUE
        );

        thirdHBox.setStyle(
                "-fx-background-color:#245d35;"
                        + "-fx-background-radius:12;"
                        + "-fx-border-color:#2d6b3f;"
                        + "-fx-border-radius:12;"
        );

        VBox thirdTextVBox =
                new VBox(8);

        thirdTextVBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Label intelligentLabel =
                new Label(
                        "INTELLIGENT PLANNING"
                );

        intelligentLabel.setTextFill(
                Color.web("#68d34a")
        );

        intelligentLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label planTitle =
                new Label(
                        "AI Business Plan Generator"
                );

        planTitle.setTextFill(
                Color.web("#eeeeee")
        );

        planTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        Label planDescription =
                new Label(
                        "Leverage predictive analytics and local market data "
                                + "to craft an optimal business strategy for your next "
                                + "herd expansion. Minimize risk, maximize yield."
                );

        planDescription.setTextFill(
                Color.web("#d0d8d3")
        );

        planDescription.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        planDescription.setWrapText(true);
        planDescription.setMaxWidth(500);

        thirdTextVBox.getChildren().addAll(
                intelligentLabel,
                planTitle,
                planDescription
        );

        Region thirdSpace =
                new Region();

        HBox.setHgrow(
                thirdSpace,
                Priority.ALWAYS
        );

        Button generateButton =
                new Button(
                        "Generate Plan  ✨"
                );

        generateButton.setPrefWidth(125);
        generateButton.setPrefHeight(45);

        generateButton.setStyle(
                "-fx-background-color:#68d34a;"
                        + "-fx-text-fill:#080c0d;"
                        + "-fx-font-weight:bold;"
                        + "-fx-background-radius:6;"
                        + "-fx-cursor:hand;"
        );
        generateButton.setOnAction(
        event -> {

            System.out.println(
                    "Generate Plan button clicked"
            );

            // Get logged-in farmer information
            int loggedInFarmerId =
                    LoginPage.getLoggedInFarmerId();

            String loggedInFirebaseUid =
                    LoginPage.getLoggedInFirebaseUid();


            System.out.println(
                    "Farmer ID = "
                            + loggedInFarmerId
            );

            System.out.println(
                    "Firebase UID = "
                            + loggedInFirebaseUid
            );


            // =====================================================
            // CHECK FARMER ID
            // =====================================================

            if (loggedInFarmerId <= 0) {

                System.out.println(
                        "ERROR: Farmer ID is missing."
                );

                return;
            }


            // =====================================================
            // CHECK FIREBASE UID
            // =====================================================

            if (loggedInFirebaseUid == null ||
                    loggedInFirebaseUid.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Firebase UID is missing."
                );

                return;
            }


            // =====================================================
            // OPEN FARMER DASHBOARD AI ADVISOR
            // =====================================================

            FarmerDashboard dashboard =
                    new FarmerDashboard(
                            loggedInFarmerId,
                            loggedInFirebaseUid
                    );


            LoginPage.mainStage.setScene(
                    dashboard.getAIAdvisorScene()
            );


            LoginPage.mainStage.show();
        }
);

        thirdHBox.getChildren().addAll(
                thirdTextVBox,
                thirdSpace,
                generateButton
        );

        // =====================================================
        // ADD MAIN CONTENT
        // =====================================================

        mainVBox.getChildren().addAll(
                firstHBox,
                secondVBox,
                thirdHBox
        );

        mainVBox.setStyle(
                "-fx-background-color:transparent;"
                        + "-fx-padding:28px;"
        );

        mainVBox.setPrefHeight(
                Region.USE_COMPUTED_SIZE
        );

        mainVBox.setPrefWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                mainVBox
        );

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setMaxWidth(
                Double.MAX_VALUE
        );

        scrollPane.setStyle(
                "-fx-background:transparent;"
                        + "-fx-background-color:transparent;"
                        + "-fx-border-color:transparent;"
        );

        // =====================================================
        // ANIMATED BACKGROUND
        // =====================================================

        StackPane animatedBackground =
                createAnimatedBackground();

        Rectangle clip =
                new Rectangle();

        clip.widthProperty().bind(
                animatedBackground.widthProperty()
        );

        clip.heightProperty().bind(
                animatedBackground.heightProperty()
        );

        animatedBackground.setClip(clip);

        animatedBackground.getChildren().add(
                scrollPane
        );

        StackPane.setAlignment(
                scrollPane,
                Pos.CENTER
        );

        // =====================================================
        // SET CENTER
        // =====================================================

        borderPane.setCenter(
                animatedBackground
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        borderPane,
                        800,
                        768
                );

        homepagescene = scene;

        return scene;
    }

    // =========================================================
    // LOAD TRENDING COURSES
    // =========================================================

    private void loadTrendingCourses(
            HBox container) {

        container.getChildren().clear();

        try {

            List<Course> publishedCourses =
                    courseDAO.getPublishedCourses();

            // =================================================
            // NO COURSES
            // =================================================

            if (publishedCourses == null ||
                    publishedCourses.isEmpty()) {

                Label emptyLabel =
                        new Label(
                                "No courses available yet."
                        );

                emptyLabel.setFont(
                        Font.font(
                                "Arial",
                                14
                        )
                );

                emptyLabel.setTextFill(
                        Color.web("#888888")
                );

                container.getChildren().add(
                        emptyLabel
                );

                return;
            }

            // =================================================
            // SHOW MAXIMUM 4 COURSES
            // =================================================

            int courseCount =
                    Math.min(
                            4,
                            publishedCourses.size()
                    );

            for (int i = 0;
                    i < courseCount;
                    i++) {

                Course course =
                        publishedCourses.get(i);

                if (course == null) {
                    continue;
                }

                VBox card =
                        createTrendingCourseCard(
                                course
                        );

                container.getChildren().add(
                        card
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label errorLabel =
                    new Label(
                            "Unable to load courses."
                    );

            errorLabel.setFont(
                    Font.font(
                            "Arial",
                            14
                    )
            );

            errorLabel.setTextFill(
                    Color.web("#888888")
            );

            container.getChildren().add(
                    errorLabel
            );
        }
    }

    // =========================================================
    // CREATE TRENDING COURSE CARD
    // =========================================================

    private VBox createTrendingCourseCard(
            Course course) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(8)
        );

        card.setPrefWidth(230);
        card.setPrefHeight(205);

        card.setAlignment(
                Pos.TOP_LEFT
        );

        String normalStyle =
                "-fx-background-color:#101516;"
                        + "-fx-background-radius:12;"
                        + "-fx-border-color:#242b2c;"
                        + "-fx-border-radius:12;"
                        + "-fx-cursor:hand;";

        String hoverStyle =
                "-fx-background-color:#17221a;"
                        + "-fx-background-radius:12;"
                        + "-fx-border-color:#68d34a;"
                        + "-fx-border-radius:12;"
                        + "-fx-border-width:1;"
                        + "-fx-cursor:hand;";

        card.setStyle(
                normalStyle
        );

        // =====================================================
        // IMAGE
        // =====================================================

        StackPane imageContainer =
                new StackPane();

        imageContainer.setPrefWidth(214);
        imageContainer.setPrefHeight(95);

        imageContainer.setMaxWidth(214);
        imageContainer.setMaxHeight(95);

        Rectangle clip =
                new Rectangle(
                        214,
                        95
                );

        clip.setArcWidth(14);
        clip.setArcHeight(14);

        imageContainer.setClip(clip);

        imageContainer.setStyle(
                "-fx-background-color:#193522;"
        );

        ImageView imageView =
                new ImageView();

        imageView.setFitWidth(214);
        imageView.setFitHeight(95);

        imageView.setPreserveRatio(false);

        String imageUrl =
                course.getThumbnailUrl();

        if (imageUrl != null &&
                !imageUrl.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                214,
                                95,
                                false,
                                true,
                                true
                        );

                imageView.setImage(
                        image
                );

            } catch (Exception e) {

                addHomePlaceholder(
                        imageContainer
                );
            }

        } else {

            addHomePlaceholder(
                    imageContainer
            );
        }

        imageContainer.getChildren().add(
                imageView
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        safeValue(
                                course.getTitle(),
                                "Untitled Course"
                        )
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        title.setTextFill(
                Color.web("#eeeeee")
        );

        title.setWrapText(true);

        title.setMaxWidth(210);

        // =====================================================
        // CATEGORY
        // =====================================================

        Label category =
                new Label(
                        safeValue(
                                course.getCategory(),
                                "General"
                        )
                );

        category.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        category.setTextFill(
                Color.web("#68d34a")
        );

        category.setWrapText(true);

        // =====================================================
        // CLICK COURSE
        // =====================================================

        card.setOnMouseClicked(
                event -> {

                    ExplorerPage explorerPage =
                            new ExplorerPage(
                                    course.getCourseId()
                            );

                    LoginPage.mainStage.setScene(
                            explorerPage.getExplorerPage()
                    );
                }
        );

        // =====================================================
        // HOVER
        // =====================================================

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(
                            hoverStyle
                    );
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );
                }
        );

        // =====================================================
        // ADD CHILDREN
        // =====================================================

        card.getChildren().addAll(
                imageContainer,
                title,
                category
        );

        return card;
    }

    // =========================================================
    // HOME PLACEHOLDER
    // =========================================================

    private void addHomePlaceholder(
            StackPane container) {

        Label placeholder =
                new Label("🌱");

        placeholder.setFont(
                Font.font(
                        "Arial",
                        35
                )
        );

        placeholder.setTextFill(
                Color.web("#68d34a")
        );

        container.getChildren().add(
                placeholder
        );
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safeValue(
            String value,
            String defaultValue) {

        if (value == null ||
                value.trim().isEmpty()) {

            return defaultValue;
        }

        return value.trim();
    }

    // =========================================================
    // BACK TO HOME
    // =========================================================

    public void backtohome() {

        LoginPage.mainStage.setScene(
                homepagescene
        );
    }
}