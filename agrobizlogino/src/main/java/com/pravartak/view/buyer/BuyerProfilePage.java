// // package com.pravartak.view.buyer;

// // import com.google.api.core.ApiFuture;
// // import com.google.cloud.firestore.DocumentSnapshot;
// // import com.google.cloud.firestore.Firestore;
// // import com.google.cloud.firestore.SetOptions;
// // import com.pravartak.config.CloudinaryConfig;
// // import com.pravartak.config.FirebaseConfig;
// // import com.pravartak.view.buyer.common.buyerTop;
// // import com.pravartak.view.farmer.common.Footer;
// // import com.cloudinary.Cloudinary;

// // import javafx.application.Platform;
// // import javafx.geometry.Insets;
// // import javafx.geometry.Pos;
// // import javafx.scene.Scene;
// // import javafx.scene.control.Button;
// // import javafx.scene.control.Label;
// // import javafx.scene.control.ProgressIndicator;
// // import javafx.scene.image.Image;
// // import javafx.scene.image.ImageView;
// // import javafx.scene.layout.BorderPane;
// // import javafx.scene.layout.HBox;
// // import javafx.scene.layout.StackPane;
// // import javafx.scene.layout.VBox;
// // import javafx.scene.shape.Circle;
// // import javafx.stage.FileChooser;
// // import javafx.stage.Stage;

// // import java.io.File;
// // import java.util.HashMap;
// // import java.util.Map;
// // import java.util.concurrent.ExecutorService;
// // import java.util.concurrent.Executors;

// // public class BuyerProfilePage {

// //     // =========================================================
// //     // BUYER PROFILE DATA
// //     // =========================================================

// //     public static String buyerName = "Buyer User";
// //     public static String phoneNumber = "+91 98765 43210";
// //     public static String email = "buyer@agrobiz.com";
// //     public static String location = "Maharashtra, India";
// //     public static String buyerType = "Wholesale Buyer";

// //     // Cloudinary image URL
// //     public static String profileImageUrl = "";

// //     // =========================================================
// //     // FIRESTORE COLLECTION
// //     // =========================================================

// //     private static final String COLLECTION_NAME = "buyers";

// //     // =========================================================
// //     // UI VARIABLES
// //     // =========================================================

// //     private ImageView profileImage;

// //     private Label nameLabel;
// //     private Label phoneLabel;
// //     private Label emailLabel;
// //     private Label locationLabel;
// //     private Label buyerTypeLabel;

// //     private Label imageStatusLabel;

// //     // =========================================================
// //     // BACKGROUND THREAD
// //     // =========================================================

// //     private final ExecutorService executor =
// //             Executors.newCachedThreadPool();

// //     public BuyerProfilePage() {
// //     }

// //     // =========================================================
// //     // PROFILE PAGE SCENE
// //     // =========================================================

// //     public Scene getProfilePageScene() {

// //         BorderPane out = new BorderPane();

// //         out.setTop(
// //                 new buyerTop().createBuyerTop("◎ Profile")
// //         );

// //         out.setBottom(
// //                 new Footer().createFooter()
// //         );

// //         out.setPrefSize(1368, 768);

// //         out.setStyle(
// //                 "-fx-background-color: #06110c;"
// //         );

// //         BorderPane root = new BorderPane();

// //         out.setCenter(root);

// //         // =====================================================
// //         // HEADER
// //         // =====================================================

// //         VBox header = new VBox(4);

// //         header.setPadding(
// //                 new Insets(18, 35, 18, 35)
// //         );

// //         header.setStyle(
// //                 "-fx-background-color: #0b2613;"
// //         );

// //         Label title = new Label("Buyer Profile");

// //         title.setStyle(
// //                 "-fx-font-size: 28px;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-text-fill: white;"
// //         );

// //         Label subtitle = new Label(
// //                 "Manage your personal and buying information."
// //         );

// //         subtitle.setStyle(
// //                 "-fx-font-size: 14px;" +
// //                 "-fx-text-fill: #7f9987;"
// //         );

// //         header.getChildren().addAll(
// //                 title,
// //                 subtitle
// //         );

// //         root.setTop(header);

// //         // =====================================================
// //         // MAIN CONTENT
// //         // =====================================================

// //         VBox mainContent = new VBox(22);

// //         mainContent.setPadding(
// //                 new Insets(30, 35, 30, 35)
// //         );

// //         // =====================================================
// //         // PROFILE TOP CARD
// //         // =====================================================

// //         HBox profileCard = new HBox(22);

// //         profileCard.setAlignment(
// //                 Pos.CENTER_LEFT
// //         );

// //         profileCard.setPadding(
// //                 new Insets(20, 25, 20, 25)
// //         );

// //         profileCard.setPrefHeight(150);

// //         profileCard.setStyle(
// //                 "-fx-background-color: #007d00;" +
// //                 "-fx-background-radius: 15;"
// //         );

// //         // =====================================================
// //         // PROFILE IMAGE
// //         // =====================================================

// //         StackPane imageContainer =
// //                 createProfileImage();

// //         // =====================================================
// //         // BUYER BASIC INFORMATION
// //         // =====================================================

// //         VBox buyerInfo = new VBox(5);

// //         buyerInfo.setAlignment(
// //                 Pos.CENTER_LEFT
// //         );

// //         nameLabel = new Label(
// //                 buyerName
// //         );

// //         nameLabel.setStyle(
// //                 "-fx-font-size: 23px;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-text-fill: white;"
// //         );

// //         Label roleLabel = new Label(
// //                 "Buyer"
// //         );

// //         roleLabel.setStyle(
// //                 "-fx-font-size: 15px;" +
// //                 "-fx-text-fill: #e5ffe5;"
// //         );

// //         Label infoLabel = new Label(
// //                 "Manage your personal and buying information."
// //         );

// //         infoLabel.setStyle(
// //                 "-fx-font-size: 13px;" +
// //                 "-fx-text-fill: #d4f0d4;"
// //         );

// //         imageStatusLabel = new Label("");

// //         imageStatusLabel.setStyle(
// //                 "-fx-font-size: 12px;" +
// //                 "-fx-text-fill: #d4f0d4;"
// //         );

// //         buyerInfo.getChildren().addAll(
// //                 nameLabel,
// //                 roleLabel,
// //                 infoLabel,
// //                 imageStatusLabel
// //         );

// //         // =====================================================
// //         // BUTTONS
// //         // =====================================================

// //         VBox buttonBox = new VBox(8);

// //         buttonBox.setAlignment(
// //                 Pos.CENTER
// //         );

// //         // =====================================================
// //         // UPLOAD BUTTON
// //         // =====================================================

// //         Button uploadButton =
// //                 new Button("Upload Image");

// //         uploadButton.setPrefWidth(140);
// //         uploadButton.setPrefHeight(35);

// //         uploadButton.setStyle(
// //                 "-fx-background-color: white;" +
// //                 "-fx-text-fill: #006b00;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-background-radius: 7;" +
// //                 "-fx-cursor: hand;"
// //         );

// //         uploadButton.setOnAction(
// //                 e -> uploadImage(uploadButton)
// //         );

// //         // =====================================================
// //         // EDIT BUTTON
// //         // =====================================================

// //         Button editButton =
// //                 new Button("Edit Profile");

// //         editButton.setPrefWidth(140);
// //         editButton.setPrefHeight(35);

// //         editButton.setStyle(
// //                 "-fx-background-color: #e8e8e8;" +
// //                 "-fx-text-fill: #006b00;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-background-radius: 7;" +
// //                 "-fx-cursor: hand;"
// //         );

// //         editButton.setOnAction(e -> {

// //             BuyerEditProfilePage editPage =
// //                     new BuyerEditProfilePage();

// //             Stage currentStage =
// //                     (Stage) editButton
// //                             .getScene()
// //                             .getWindow();

// //             currentStage.setScene(
// //                     editPage.getEditProfileScene()
// //             );
// //         });

// //         buttonBox.getChildren().addAll(
// //                 uploadButton,
// //                 editButton
// //         );

// //         profileCard.getChildren().addAll(
// //                 imageContainer,
// //                 buyerInfo,
// //                 buttonBox
// //         );

// //         // =====================================================
// //         // INFORMATION BOX
// //         // =====================================================

// //         HBox informationBox =
// //                 new HBox(22);

// //         informationBox.setAlignment(
// //                 Pos.CENTER
// //         );

// //         // =====================================================
// //         // PERSONAL INFORMATION CARD
// //         // =====================================================

// //         VBox personalCard =
// //                 createInformationCard();

// //         Label personalTitle =
// //                 createCardTitle(
// //                         "Personal Information"
// //                 );

// //         VBox personalDetails =
// //                 new VBox(16);

// //         phoneLabel =
// //                 createValueLabel(phoneNumber);

// //         emailLabel =
// //                 createValueLabel(email);

// //         locationLabel =
// //                 createValueLabel(location);

// //         personalDetails.getChildren().addAll(

// //                 createInfoRow(
// //                         "Phone Number",
// //                         phoneLabel
// //                 ),

// //                 createInfoRow(
// //                         "Gmail",
// //                         emailLabel
// //                 ),

// //                 createInfoRow(
// //                         "Location",
// //                         locationLabel
// //                 )
// //         );

// //         personalCard.getChildren().addAll(
// //                 personalTitle,
// //                 personalDetails
// //         );

// //         // =====================================================
// //         // BUYER INFORMATION CARD
// //         // =====================================================

// //         VBox buyerCard =
// //                 createInformationCard();

// //         Label buyerTitle =
// //                 createCardTitle(
// //                         "Buyer Information"
// //                 );

// //         VBox buyerDetails =
// //                 new VBox(16);

// //         buyerTypeLabel =
// //                 createValueLabel(buyerType);

// //         buyerDetails.getChildren().add(
// //                 createInfoRow(
// //                         "Buyer Type",
// //                         buyerTypeLabel
// //                 )
// //         );

// //         buyerCard.getChildren().addAll(
// //                 buyerTitle,
// //                 buyerDetails
// //         );

// //         informationBox.getChildren().addAll(
// //                 personalCard,
// //                 buyerCard
// //         );

// //         mainContent.getChildren().addAll(
// //                 profileCard,
// //                 informationBox
// //         );

// //         root.setCenter(mainContent);

// //         Scene scene =
// //                 new Scene(
// //                         out,
// //                         1368,
// //                         768
// //                 );

// //         // =====================================================
// //         // LOAD FIREBASE PROFILE
// //         // =====================================================

// //         loadProfileFromFirebase();

// //         return scene;
// //     }

// //     // =========================================================
// //     // CREATE PROFILE IMAGE
// //     // =========================================================

// //     private StackPane createProfileImage() {

// //         StackPane container =
// //                 new StackPane();

// //         container.setPrefSize(
// //                 105,
// //                 105
// //         );

// //         container.setMinSize(
// //                 105,
// //                 105
// //         );

// //         container.setMaxSize(
// //                 105,
// //                 105
// //         );

// //         // Dark circular background
// //         Circle backgroundCircle =
// //                 new Circle(
// //                         52.5
// //                 );

// //         backgroundCircle.setStyle(
// //                 "-fx-fill: #092d13;"
// //         );

// //         // =====================================================
// //         // IMAGE VIEW
// //         // =====================================================

// //         profileImage =
// //                 new ImageView();

// //         profileImage.setFitWidth(105);
// //         profileImage.setFitHeight(105);

// //         profileImage.setPreserveRatio(false);

// //         /*
// //          * THIS IS THE IMPORTANT PART.
// //          *
// //          * The image itself is clipped into a circle.
// //          */
// //         Circle imageClip =
// //                 new Circle(
// //                         52.5,
// //                         52.5,
// //                         52.5
// //                 );

// //         profileImage.setClip(imageClip);

// //         // =====================================================
// //         // DEFAULT INITIAL
// //         // =====================================================

// //         Label initial =
// //                 new Label("B");

// //         initial.setStyle(
// //                 "-fx-font-size: 35px;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-text-fill: white;"
// //         );

// //         container.getChildren().addAll(
// //                 backgroundCircle,
// //                 initial,
// //                 profileImage
// //         );

// //         // =====================================================
// //         // LOAD EXISTING IMAGE IF AVAILABLE
// //         // =====================================================

// //         if (profileImageUrl != null
// //                 && !profileImageUrl.trim().isEmpty()) {

// //             setProfileImage(
// //                     profileImageUrl
// //             );
// //         }

// //         return container;
// //     }

// //     // =========================================================
// //     // SET PROFILE IMAGE
// //     // =========================================================

// //     private void setProfileImage(
// //             String imageUrl
// //     ) {

// //         if (imageUrl == null
// //                 || imageUrl.trim().isEmpty()) {

// //             return;
// //         }

// //         try {

// //             Image image =
// //                     new Image(
// //                             imageUrl,
// //                             105,
// //                             105,
// //                             false,
// //                             true
// //                     );

// //             profileImage.setImage(image);

// //         } catch (Exception e) {

// //             System.err.println(
// //                     "Unable to load profile image."
// //             );

// //             e.printStackTrace();
// //         }
// //     }

// //     // =========================================================
// //     // UPLOAD IMAGE TO CLOUDINARY
// //     // =========================================================

// //     private void uploadImage(
// //             Button uploadButton
// //     ) {

// //         FileChooser fileChooser =
// //                 new FileChooser();

// //         fileChooser.setTitle(
// //                 "Select Buyer Profile Image"
// //         );

// //         fileChooser.getExtensionFilters().add(
// //                 new FileChooser.ExtensionFilter(
// //                         "Image Files",
// //                         "*.png",
// //                         "*.jpg",
// //                         "*.jpeg",
// //                         "*.webp"
// //                 )
// //         );

// //         Stage stage =
// //                 (Stage) uploadButton
// //                         .getScene()
// //                         .getWindow();

// //         File selectedFile =
// //                 fileChooser.showOpenDialog(stage);

// //         if (selectedFile == null) {
// //             return;
// //         }

// //         uploadButton.setDisable(true);

// //         uploadButton.setText(
// //                 "Uploading..."
// //         );

// //         imageStatusLabel.setText(
// //                 "Uploading image to Cloudinary..."
// //         );

// //         // =====================================================
// //         // CLOUDINARY UPLOAD IN BACKGROUND
// //         // =====================================================

// //         executor.submit(() -> {

// //             try {

// //                 Cloudinary cloudinary =
// //                         CloudinaryConfig.getCloudinary();

// //                 Map<String, Object> uploadOptions =
// //                         new HashMap<>();

// //                 /*
// //                  * This creates a separate folder
// //                  * for buyer profile images.
// //                  */
// //                 uploadOptions.put(
// //                         "folder",
// //                         "agrobiz/buyer_profiles"
// //                 );

// //                 Map<?, ?> uploadResult =
// //                         cloudinary.uploader().upload(
// //                                 selectedFile,
// //                                 uploadOptions
// //                         );

// //                 Object secureUrlObject =
// //                         uploadResult.get(
// //                                 "secure_url"
// //                         );

// //                 if (secureUrlObject == null) {

// //                     throw new RuntimeException(
// //                             "Cloudinary did not return image URL."
// //                     );
// //                 }

// //                 String imageUrl =
// //                         secureUrlObject.toString();

// //                 // Save URL in memory
// //                 profileImageUrl =
// //                         imageUrl;

// //                 Platform.runLater(() -> {

// //                     // Display image
// //                     setProfileImage(
// //                             imageUrl
// //                     );

// //                     uploadButton.setDisable(
// //                             false
// //                     );

// //                     uploadButton.setText(
// //                             "Upload Image"
// //                     );

// //                     imageStatusLabel.setText(
// //                             "Image uploaded successfully."
// //                     );
// //                 });

// //                 // =================================================
// //                 // ALSO SAVE IMAGE URL TO FIREBASE
// //                 // =================================================

// //                 saveImageUrlToFirebase(
// //                         imageUrl
// //                 );

// //             } catch (Exception ex) {

// //                 ex.printStackTrace();

// //                 Platform.runLater(() -> {

// //                     uploadButton.setDisable(
// //                             false
// //                     );

// //                     uploadButton.setText(
// //                             "Upload Image"
// //                     );

// //                     imageStatusLabel.setText(
// //                             "Image upload failed."
// //                     );
// //                 });
// //             }
// //         });
// //     }

// //     // =========================================================
// //     // SAVE IMAGE URL TO FIREBASE
// //     // =========================================================

// //     private void saveImageUrlToFirebase(
// //             String imageUrl
// //     ) {

// //         executor.submit(() -> {

// //             try {

// //                 Firestore db =
// //                         FirebaseConfig.getFirestore();

// //                 String documentId =
// //                         getBuyerDocumentId();

// //                 Map<String, Object> data =
// //                         new HashMap<>();

// //                 data.put(
// //                         "profileImageUrl",
// //                         imageUrl
// //                 );

// //                 ApiFuture<?> future =
// //                         db.collection(
// //                                 COLLECTION_NAME
// //                         )
// //                         .document(documentId)
// //                         .set(
// //                                 data,
// //                                 SetOptions.merge()
// //                         );

// //                 future.get();

// //                 System.out.println(
// //                         "Profile image URL saved to Firebase."
// //                 );

// //             } catch (Exception e) {

// //                 System.err.println(
// //                         "Unable to save image URL to Firebase."
// //                 );

// //                 e.printStackTrace();
// //             }
// //         });
// //     }

// //     // =========================================================
// //     // LOAD PROFILE FROM FIREBASE
// //     // =========================================================

// //     private void loadProfileFromFirebase() {

// //         executor.submit(() -> {

// //             try {

// //                 Firestore db =
// //                         FirebaseConfig.getFirestore();

// //                 String documentId =
// //                         getBuyerDocumentId();

// //                 ApiFuture<DocumentSnapshot> future =
// //                         db.collection(
// //                                 COLLECTION_NAME
// //                         )
// //                         .document(documentId)
// //                         .get();

// //                 DocumentSnapshot document =
// //                         future.get();

// //                 if (!document.exists()) {

// //                     System.out.println(
// //                             "No buyer profile found in Firebase."
// //                     );

// //                     return;
// //                 }

// //                 String loadedName =
// //                         getString(
// //                                 document,
// //                                 "name",
// //                                 buyerName
// //                         );

// //                 String loadedPhone =
// //                         getString(
// //                                 document,
// //                                 "phone",
// //                                 phoneNumber
// //                         );

// //                 String loadedEmail =
// //                         getString(
// //                                 document,
// //                                 "email",
// //                                 email
// //                         );

// //                 String loadedLocation =
// //                         getString(
// //                                 document,
// //                                 "location",
// //                                 location
// //                         );

// //                 String loadedBuyerType =
// //                         getString(
// //                                 document,
// //                                 "buyerType",
// //                                 buyerType
// //                         );

// //                 String loadedImageUrl =
// //                         getString(
// //                                 document,
// //                                 "profileImageUrl",
// //                                 profileImageUrl
// //                         );

// //                 // Update static values
// //                 buyerName =
// //                         loadedName;

// //                 phoneNumber =
// //                         loadedPhone;

// //                 email =
// //                         loadedEmail;

// //                 location =
// //                         loadedLocation;

// //                 buyerType =
// //                         loadedBuyerType;

// //                 profileImageUrl =
// //                         loadedImageUrl;

// //                 // =================================================
// //                 // UPDATE UI
// //                 // =================================================

// //                 Platform.runLater(() -> {

// //                     if (nameLabel != null) {
// //                         nameLabel.setText(
// //                                 buyerName
// //                         );
// //                     }

// //                     if (phoneLabel != null) {
// //                         phoneLabel.setText(
// //                                 phoneNumber
// //                         );
// //                     }

// //                     if (emailLabel != null) {
// //                         emailLabel.setText(
// //                                 email
// //                         );
// //                     }

// //                     if (locationLabel != null) {
// //                         locationLabel.setText(
// //                                 location
// //                         );
// //                     }

// //                     if (buyerTypeLabel != null) {
// //                         buyerTypeLabel.setText(
// //                                 buyerType
// //                         );
// //                     }

// //                     if (profileImageUrl != null
// //                             && !profileImageUrl
// //                             .trim()
// //                             .isEmpty()) {

// //                         setProfileImage(
// //                                 profileImageUrl
// //                         );
// //                     }
// //                 });

// //             } catch (Exception e) {

// //                 System.err.println(
// //                         "Unable to load buyer profile from Firebase."
// //                 );

// //                 e.printStackTrace();
// //             }
// //         });
// //     }

// //     // =========================================================
// //     // GET FIRESTORE STRING
// //     // =========================================================

// //     private String getString(
// //             DocumentSnapshot document,
// //             String field,
// //             String defaultValue
// //     ) {

// //         String value =
// //                 document.getString(field);

// //         if (value == null
// //                 || value.trim().isEmpty()) {

// //             return defaultValue;
// //         }

// //         return value;
// //     }

// //     // =========================================================
// //     // BUYER DOCUMENT ID
// //     // =========================================================

// //     private String getBuyerDocumentId() {

// //         /*
// //          * For the current implementation we use
// //          * buyer email as the Firestore document ID.
// //          *
// //          * Example:
// //          *
// //          * buyers
// //          *    └── buyer@agrobiz.com
// //          */

// //         if (email == null
// //                 || email.trim().isEmpty()) {

// //             return "default_buyer";
// //         }

// //         return email.trim();
// //     }

// //     // =========================================================
// //     // INFORMATION CARD
// //     // =========================================================

// //     private VBox createInformationCard() {

// //         VBox card =
// //                 new VBox(20);

// //         card.setPadding(
// //                 new Insets(25)
// //         );

// //         card.setPrefWidth(630);

// //         card.setMinHeight(
// //                 280
// //         );

// //         card.setStyle(
// //                 "-fx-background-color: #007d00;" +
// //                 "-fx-background-radius: 15;"
// //         );

// //         return card;
// //     }

// //     // =========================================================
// //     // CARD TITLE
// //     // =========================================================

// //     private Label createCardTitle(
// //             String text
// //     ) {

// //         Label label =
// //                 new Label(text);

// //         label.setStyle(
// //                 "-fx-font-size: 18px;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-text-fill: white;"
// //         );

// //         return label;
// //     }

// //     // =========================================================
// //     // INFORMATION ROW
// //     // =========================================================

// //     private VBox createInfoRow(
// //             String title,
// //             Label value
// //     ) {

// //         VBox box =
// //                 new VBox(4);

// //         Label titleLabel =
// //                 new Label(title);

// //         titleLabel.setStyle(
// //                 "-fx-font-size: 13px;" +
// //                 "-fx-font-weight: bold;" +
// //                 "-fx-text-fill: #d5efd5;"
// //         );

// //         box.getChildren().addAll(
// //                 titleLabel,
// //                 value
// //         );

// //         return box;
// //     }

// //     // =========================================================
// //     // VALUE LABEL
// //     // =========================================================

// //     private Label createValueLabel(
// //             String text
// //     ) {

// //         Label label =
// //                 new Label(text);

// //         label.setStyle(
// //                 "-fx-font-size: 15px;" +
// //                 "-fx-text-fill: white;"
// //         );

// //         return label;
// //     }
// // }


// package com.pravartak.view.buyer;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.Firestore;
// import com.pravartak.config.FirebaseConfig;
// import com.pravartak.view.buyer.common.buyerTop;
// import com.pravartak.view.farmer.common.Footer;

// import javafx.application.Platform;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.shape.Circle;
// import javafx.stage.Stage;

// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

// public class BuyerProfilePage {

//     // =========================================================
//     // BUYER PROFILE DATA
//     // =========================================================

//     public static String buyerName = "Buyer User";
//     public static String phoneNumber = "+91 98765 43210";
//     public static String email = "buyer@agrobiz.com";
//     public static String location = "Maharashtra, India";
//     public static String buyerType = "Wholesale Buyer";

//     // Cloudinary image URL
//     public static String profileImageUrl = "";

//     // =========================================================
//     // FIRESTORE
//     // =========================================================

//     private static final String COLLECTION_NAME = "buyers";

//     /*
//      * This stores the original Firestore document ID.
//      *
//      * IMPORTANT:
//      * If the user changes their email, we still update
//      * the same Firestore document.
//      */
//     public static String firestoreDocumentId = "";

//     // =========================================================
//     // UI
//     // =========================================================

//     private ImageView profileImage;

//     private Label nameLabel;
//     private Label phoneLabel;
//     private Label emailLabel;
//     private Label locationLabel;
//     private Label buyerTypeLabel;

//     private final ExecutorService executor =
//             Executors.newCachedThreadPool();

//     // =========================================================
//     // CONSTRUCTOR
//     // =========================================================

//     public BuyerProfilePage() {
//     }

//     // =========================================================
//     // PROFILE PAGE SCENE
//     // =========================================================

//     public Scene getProfilePageScene() {

//         BorderPane out = new BorderPane();

//         out.setTop(
//                 new buyerTop().createBuyerTop("◎ Profile")
//         );

//         out.setBottom(
//                 new Footer().createFooter()
//         );

//         out.setPrefSize(
//                 1368,
//                 768
//         );

//         out.setStyle(
//                 "-fx-background-color: #06110c;"
//         );

//         BorderPane root = new BorderPane();

//         out.setCenter(root);

//         // =====================================================
//         // HEADER
//         // =====================================================

//         VBox header = new VBox(4);

//         header.setPadding(
//                 new Insets(18, 35, 18, 35)
//         );

//         header.setStyle(
//                 "-fx-background-color: #0b2613;"
//         );

//         Label title =
//                 new Label("Buyer Profile");

//         title.setStyle(
//                 "-fx-font-size: 28px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         Label subtitle =
//                 new Label(
//                         "Manage your personal and buying information."
//                 );

//         subtitle.setStyle(
//                 "-fx-font-size: 14px;" +
//                 "-fx-text-fill: #7f9987;"
//         );

//         header.getChildren().addAll(
//                 title,
//                 subtitle
//         );

//         root.setTop(header);

//         // =====================================================
//         // MAIN CONTENT
//         // =====================================================

//         VBox mainContent =
//                 new VBox(22);

//         mainContent.setPadding(
//                 new Insets(30, 35, 30, 35)
//         );

//         // =====================================================
//         // PROFILE CARD
//         // =====================================================

//         HBox profileCard =
//                 new HBox(22);

//         profileCard.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         profileCard.setPadding(
//                 new Insets(20, 25, 20, 25)
//         );

//         profileCard.setPrefHeight(
//                 150
//         );

//         profileCard.setStyle(
//                 "-fx-background-color: #007d00;" +
//                 "-fx-background-radius: 15;"
//         );

//         // =====================================================
//         // PROFILE IMAGE
//         // =====================================================

//         StackPane imageContainer =
//                 createProfileImage();

//         // =====================================================
//         // BUYER INFORMATION
//         // =====================================================

//         VBox buyerInfo =
//                 new VBox(5);

//         buyerInfo.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         nameLabel =
//                 new Label(
//                         buyerName
//                 );

//         nameLabel.setStyle(
//                 "-fx-font-size: 23px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         Label roleLabel =
//                 new Label("Buyer");

//         roleLabel.setStyle(
//                 "-fx-font-size: 15px;" +
//                 "-fx-text-fill: #e5ffe5;"
//         );

//         Label infoLabel =
//                 new Label(
//                         "Manage your personal and buying information."
//                 );

//         infoLabel.setStyle(
//                 "-fx-font-size: 13px;" +
//                 "-fx-text-fill: #d4f0d4;"
//         );

//         buyerInfo.getChildren().addAll(
//                 nameLabel,
//                 roleLabel,
//                 infoLabel
//         );

//         // =====================================================
//         // EDIT BUTTON ONLY
//         // =====================================================

//         VBox buttonBox =
//                 new VBox(8);

//         buttonBox.setAlignment(
//                 Pos.CENTER
//         );

//         Button editButton =
//                 new Button("Edit Profile");

//         editButton.setPrefWidth(
//                 140
//         );

//         editButton.setPrefHeight(
//                 35
//         );

//         editButton.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-text-fill: #006b00;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 7;" +
//                 "-fx-cursor: hand;"
//         );

//         editButton.setOnAction(e -> {

//             BuyerEditProfilePage editPage =
//                     new BuyerEditProfilePage();

//             Stage currentStage =
//                     (Stage) editButton
//                             .getScene()
//                             .getWindow();

//             currentStage.setScene(
//                     editPage.getEditProfileScene()
//             );
//         });

//         buttonBox.getChildren().add(
//                 editButton
//         );

//         profileCard.getChildren().addAll(
//                 imageContainer,
//                 buyerInfo,
//                 buttonBox
//         );

//         // =====================================================
//         // INFORMATION BOX
//         // =====================================================

//         HBox informationBox =
//                 new HBox(22);

//         informationBox.setAlignment(
//                 Pos.CENTER
//         );

//         // =====================================================
//         // PERSONAL INFORMATION
//         // =====================================================

//         VBox personalCard =
//                 createInformationCard();

//         Label personalTitle =
//                 createCardTitle(
//                         "Personal Information"
//                 );

//         VBox personalDetails =
//                 new VBox(16);

//         phoneLabel =
//                 createValueLabel(
//                         phoneNumber
//                 );

//         emailLabel =
//                 createValueLabel(
//                         email
//                 );

//         locationLabel =
//                 createValueLabel(
//                         location
//                 );

//         personalDetails.getChildren().addAll(

//                 createInfoRow(
//                         "Phone Number",
//                         phoneLabel
//                 ),

//                 createInfoRow(
//                         "Gmail",
//                         emailLabel
//                 ),

//                 createInfoRow(
//                         "Location",
//                         locationLabel
//                 )
//         );

//         personalCard.getChildren().addAll(
//                 personalTitle,
//                 personalDetails
//         );

//         // =====================================================
//         // BUYER INFORMATION
//         // =====================================================

//         VBox buyerCard =
//                 createInformationCard();

//         Label buyerTitle =
//                 createCardTitle(
//                         "Buyer Information"
//                 );

//         VBox buyerDetails =
//                 new VBox(16);

//         buyerTypeLabel =
//                 createValueLabel(
//                         buyerType
//                 );

//         buyerDetails.getChildren().add(
//                 createInfoRow(
//                         "Buyer Type",
//                         buyerTypeLabel
//                 )
//         );

//         buyerCard.getChildren().addAll(
//                 buyerTitle,
//                 buyerDetails
//         );

//         informationBox.getChildren().addAll(
//                 personalCard,
//                 buyerCard
//         );

//         mainContent.getChildren().addAll(
//                 profileCard,
//                 informationBox
//         );

//         root.setCenter(
//                 mainContent
//         );

//         Scene scene =
//                 new Scene(
//                         out,
//                         1368,
//                         768
//                 );

//         // =====================================================
//         // LOAD PROFILE
//         // =====================================================

//         loadProfileFromFirebase();

//         return scene;
//     }

//     // =========================================================
//     // CREATE CIRCULAR PROFILE IMAGE
//     // =========================================================

//     private StackPane createProfileImage() {

//         StackPane container =
//                 new StackPane();

//         container.setPrefSize(
//                 105,
//                 105
//         );

//         container.setMinSize(
//                 105,
//                 105
//         );

//         container.setMaxSize(
//                 105,
//                 105
//         );

//         // =====================================================
//         // BACKGROUND CIRCLE
//         // =====================================================

//         Circle backgroundCircle =
//                 new Circle(
//                         52.5
//                 );

//         backgroundCircle.setStyle(
//                 "-fx-fill: #092d13;"
//         );

//         // =====================================================
//         // IMAGE
//         // =====================================================

//         profileImage =
//                 new ImageView();

//         profileImage.setFitWidth(
//                 105
//         );

//         profileImage.setFitHeight(
//                 105
//         );

//         profileImage.setPreserveRatio(
//                 false
//         );

//         /*
//          * IMPORTANT:
//          * Clip ImageView into a perfect circle.
//          */
//         Circle imageClip =
//                 new Circle(
//                         52.5,
//                         52.5,
//                         52.5
//                 );

//         profileImage.setClip(
//                 imageClip
//         );

//         // =====================================================
//         // DEFAULT INITIAL
//         // =====================================================

//         Label initial =
//                 new Label("B");

//         initial.setStyle(
//                 "-fx-font-size: 35px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         container.getChildren().addAll(
//                 backgroundCircle,
//                 initial,
//                 profileImage
//         );

//         // Load cached image if available
//         if (profileImageUrl != null
//                 && !profileImageUrl.trim().isEmpty()) {

//             setProfileImage(
//                     profileImageUrl
//             );
//         }

//         return container;
//     }

//     // =========================================================
//     // SET IMAGE
//     // =========================================================

//     private void setProfileImage(
//             String imageUrl
//     ) {

//         if (imageUrl == null
//                 || imageUrl.trim().isEmpty()) {

//             return;
//         }

//         try {

//             Image image =
//                     new Image(
//                             imageUrl,
//                             105,
//                             105,
//                             false,
//                             true
//                     );

//             profileImage.setImage(
//                     image
//             );

//         } catch (Exception e) {

//             System.err.println(
//                     "Unable to load profile image."
//             );

//             e.printStackTrace();
//         }
//     }

//     // =========================================================
//     // LOAD PROFILE FROM FIREBASE
//     // =========================================================

//     private void loadProfileFromFirebase() {

//         executor.submit(() -> {

//             try {

//                 Firestore db =
//                         FirebaseConfig.getFirestore();

//                 /*
//                  * First use stored document ID.
//                  *
//                  * If this is the first run, use the
//                  * current email.
//                  */
//                 String documentId;

//                 if (firestoreDocumentId != null
//                         && !firestoreDocumentId
//                                 .trim()
//                                 .isEmpty()) {

//                     documentId =
//                             firestoreDocumentId;

//                 } else {

//                     documentId =
//                             email.trim();
//                 }

//                 ApiFuture<DocumentSnapshot> future =
//                         db.collection(
//                                 COLLECTION_NAME
//                         )
//                         .document(
//                                 documentId
//                         )
//                         .get();

//                 DocumentSnapshot document =
//                         future.get();

//                 if (!document.exists()) {

//                     System.out.println(
//                             "No buyer profile found."
//                     );

//                     /*
//                      * Keep the current default values.
//                      */
//                     return;
//                 }

//                 // =================================================
//                 // STORE DOCUMENT ID
//                 // =================================================

//                 firestoreDocumentId =
//                         document.getId();

//                 // =================================================
//                 // READ DATA
//                 // =================================================

//                 String loadedName =
//                         getString(
//                                 document,
//                                 "name",
//                                 buyerName
//                         );

//                 String loadedPhone =
//                         getString(
//                                 document,
//                                 "phone",
//                                 phoneNumber
//                         );

//                 String loadedEmail =
//                         getString(
//                                 document,
//                                 "email",
//                                 email
//                         );

//                 String loadedLocation =
//                         getString(
//                                 document,
//                                 "location",
//                                 location
//                         );

//                 String loadedBuyerType =
//                         getString(
//                                 document,
//                                 "buyerType",
//                                 buyerType
//                         );

//                 String loadedImage =
//                         getString(
//                                 document,
//                                 "profileImageUrl",
//                                 profileImageUrl
//                         );

//                 // =================================================
//                 // UPDATE LOCAL DATA
//                 // =================================================

//                 buyerName =
//                         loadedName;

//                 phoneNumber =
//                         loadedPhone;

//                 email =
//                         loadedEmail;

//                 location =
//                         loadedLocation;

//                 buyerType =
//                         loadedBuyerType;

//                 profileImageUrl =
//                         loadedImage;

//                 // =================================================
//                 // UPDATE UI
//                 // =================================================

//                 Platform.runLater(() -> {

//                     if (nameLabel != null) {
//                         nameLabel.setText(
//                                 buyerName
//                         );
//                     }

//                     if (phoneLabel != null) {
//                         phoneLabel.setText(
//                                 phoneNumber
//                         );
//                     }

//                     if (emailLabel != null) {
//                         emailLabel.setText(
//                                 email
//                         );
//                     }

//                     if (locationLabel != null) {
//                         locationLabel.setText(
//                                 location
//                         );
//                     }

//                     if (buyerTypeLabel != null) {
//                         buyerTypeLabel.setText(
//                                 buyerType
//                         );
//                     }

//                     if (profileImageUrl != null
//                             && !profileImageUrl
//                                     .trim()
//                                     .isEmpty()) {

//                         setProfileImage(
//                                 profileImageUrl
//                         );
//                     }
//                 });

//             } catch (Exception e) {

//                 System.err.println(
//                         "Unable to load buyer profile."
//                 );

//                 e.printStackTrace();
//             }
//         });
//     }

//     // =========================================================
//     // GET FIRESTORE STRING
//     // =========================================================

//     private String getString(
//             DocumentSnapshot document,
//             String field,
//             String defaultValue
//     ) {

//         String value =
//                 document.getString(
//                         field
//                 );

//         if (value == null
//                 || value.trim().isEmpty()) {

//             return defaultValue;
//         }

//         return value;
//     }

//     // =========================================================
//     // INFORMATION CARD
//     // =========================================================

//     private VBox createInformationCard() {

//         VBox card =
//                 new VBox(20);

//         card.setPadding(
//                 new Insets(25)
//         );

//         card.setPrefWidth(
//                 630
//         );

//         card.setMinHeight(
//                 280
//         );

//         card.setStyle(
//                 "-fx-background-color: #007d00;" +
//                 "-fx-background-radius: 15;"
//         );

//         return card;
//     }

//     // =========================================================
//     // CARD TITLE
//     // =========================================================

//     private Label createCardTitle(
//             String text
//     ) {

//         Label label =
//                 new Label(
//                         text
//                 );

//         label.setStyle(
//                 "-fx-font-size: 18px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         return label;
//     }

//     // =========================================================
//     // INFORMATION ROW
//     // =========================================================

//     private VBox createInfoRow(
//             String title,
//             Label value
//     ) {

//         VBox box =
//                 new VBox(4);

//         Label titleLabel =
//                 new Label(
//                         title
//                 );

//         titleLabel.setStyle(
//                 "-fx-font-size: 13px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: #d5efd5;"
//         );

//         box.getChildren().addAll(
//                 titleLabel,
//                 value
//         );

//         return box;
//     }

//     // =========================================================
//     // VALUE LABEL
//     // =========================================================

//     private Label createValueLabel(
//             String text
//     ) {

//         Label label =
//                 new Label(
//                         text
//                 );

//         label.setStyle(
//                 "-fx-font-size: 15px;" +
//                 "-fx-text-fill: white;"
//         );

//         return label;
//     }
// }



package com.pravartak.view.buyer;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuyerProfilePage {

    // =========================================================
    // CURRENT LOGGED-IN BUYER UID
    // =========================================================

    /*
     * This is set from LoginPage after successful Firebase login.
     *
     * IMPORTANT:
     * Firestore will use this UID as the document ID.
     */

    public static String currentBuyerUid = "";

    // =========================================================
    // BUYER PROFILE DATA
    // =========================================================

    public static String buyerName = "Buyer User";
    public static String phoneNumber = "+91 98765 43210";
    public static String email = "buyer@agrobiz.com";
    public static String location = "Maharashtra, India";
    public static String buyerType = "Wholesale Buyer";

    // Cloudinary image URL
    public static String profileImageUrl = "";

    // =========================================================
    // FIRESTORE COLLECTION
    // =========================================================

    private static final String COLLECTION_NAME = "buyers";

    // =========================================================
    // UI VARIABLES
    // =========================================================

    private ImageView profileImage;

    private Label nameLabel;
    private Label phoneLabel;
    private Label emailLabel;
    private Label locationLabel;
    private Label buyerTypeLabel;

    private Label imageStatusLabel;

    // =========================================================
    // BACKGROUND THREAD
    // =========================================================

    private final ExecutorService executor =
            Executors.newCachedThreadPool();

    public BuyerProfilePage() {
    }

    // =========================================================
    // PROFILE PAGE SCENE
    // =========================================================

    public Scene getProfilePageScene() {

        BorderPane out =
                new BorderPane();

        out.setTop(
                new buyerTop().createBuyerTop("◎ Profile")
        );

        out.setBottom(
                new Footer().createFooter()
        );

        out.setPrefSize(
                1368,
                768
        );

        out.setStyle(
                "-fx-background-color: #06110c;"
        );

        BorderPane root =
                new BorderPane();

        out.setCenter(root);

        // =====================================================
        // HEADER
        // =====================================================

        VBox header =
                new VBox(4);

        header.setPadding(
                new Insets(18, 35, 18, 35)
        );

        header.setStyle(
                "-fx-background-color: #0b2613;"
        );

        Label title =
                new Label(
                        "Buyer Profile"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label subtitle =
                new Label(
                        "Manage your personal and buying information."
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #7f9987;"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        root.setTop(header);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox mainContent =
                new VBox(22);

        mainContent.setPadding(
                new Insets(30, 35, 30, 35)
        );

        // =====================================================
        // PROFILE TOP CARD
        // =====================================================

        HBox profileCard =
                new HBox(22);

        profileCard.setAlignment(
                Pos.CENTER_LEFT
        );

        profileCard.setPadding(
                new Insets(20, 25, 20, 25)
        );

        profileCard.setPrefHeight(
                150
        );

        profileCard.setStyle(
                "-fx-background-color: #0B2613;" +
                "-fx-background-radius: 15;"
        );

        // =====================================================
        // PROFILE IMAGE
        // =====================================================

        StackPane imageContainer =
                createProfileImage();

        // =====================================================
        // BUYER BASIC INFORMATION
        // =====================================================

        VBox buyerInfo =
                new VBox(5);

        buyerInfo.setAlignment(
                Pos.CENTER_LEFT
        );

        nameLabel =
                new Label(
                        buyerName
                );

        nameLabel.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label roleLabel =
                new Label(
                        "Buyer"
                );

        roleLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #e5ffe5;"
        );

        Label infoLabel =
                new Label(
                        "Manage your personal and buying information."
                );

        infoLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #d4f0d4;"
        );

        imageStatusLabel =
                new Label("");

        imageStatusLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #d4f0d4;"
        );

        buyerInfo.getChildren().addAll(
                nameLabel,
                roleLabel,
                infoLabel,
                imageStatusLabel
        );

        // =====================================================
        // EDIT BUTTON
        // =====================================================

        VBox buttonBox =
                new VBox(8);

        buttonBox.setAlignment(
                Pos.CENTER
        );

        Button editButton =
                new Button(
                        "Edit Profile"
                );

        editButton.setPrefWidth(
                140
        );

        editButton.setPrefHeight(
                40
        );

        editButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #006b00;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        editButton.setOnAction(e -> {

            BuyerEditProfilePage editPage =
                    new BuyerEditProfilePage();

            Stage currentStage =
                    (Stage) editButton
                            .getScene()
                            .getWindow();

            currentStage.setScene(
                    editPage.getEditProfileScene()
            );
        });

        buttonBox.getChildren().add(
                editButton
        );

        profileCard.getChildren().addAll(
                imageContainer,
                buyerInfo,
                buttonBox
        );

        // =====================================================
        // INFORMATION BOX
        // =====================================================

        HBox informationBox =
                new HBox(22);

        informationBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // PERSONAL INFORMATION CARD
        // =====================================================

        VBox personalCard =
                createInformationCard();

        Label personalTitle =
                createCardTitle(
                        "Personal Information"
                );

        VBox personalDetails =
                new VBox(16);

        phoneLabel =
                createValueLabel(
                        phoneNumber
                );

        emailLabel =
                createValueLabel(
                        email
                );

        locationLabel =
                createValueLabel(
                        location
                );

        personalDetails.getChildren().addAll(

                createInfoRow(
                        "Phone Number",
                        phoneLabel
                ),

                createInfoRow(
                        "Gmail",
                        emailLabel
                ),

                createInfoRow(
                        "Location",
                        locationLabel
                )
        );

        personalCard.getChildren().addAll(
                personalTitle,
                personalDetails
        );

        // =====================================================
        // BUYER INFORMATION CARD
        // =====================================================

        VBox buyerCard =
                createInformationCard();

        Label buyerTitle =
                createCardTitle(
                        "Buyer Information"
                );

        VBox buyerDetails =
                new VBox(16);

        buyerTypeLabel =
                createValueLabel(
                        buyerType
                );

        buyerDetails.getChildren().add(
                createInfoRow(
                        "Buyer Type",
                        buyerTypeLabel
                )
        );

        buyerCard.getChildren().addAll(
                buyerTitle,
                buyerDetails
        );

        informationBox.getChildren().addAll(
                personalCard,
                buyerCard
        );

        mainContent.getChildren().addAll(
                profileCard,
                informationBox
        );

        root.setCenter(
                mainContent
        );

        Scene scene =
                new Scene(
                        out,
                        1368,
                        768
                );

        // =====================================================
        // LOAD FIREBASE PROFILE
        // =====================================================

        loadProfileFromFirebase();

        return scene;
    }

    // =========================================================
    // CREATE PROFILE IMAGE
    // =========================================================

    private StackPane createProfileImage() {

        StackPane container =
                new StackPane();

        container.setPrefSize(
                105,
                105
        );

        container.setMinSize(
                105,
                105
        );

        container.setMaxSize(
                105,
                105
        );

        // =====================================================
        // DARK CIRCULAR BACKGROUND
        // =====================================================

        Circle backgroundCircle =
                new Circle(
                        52.5
                );

        backgroundCircle.setStyle(
                "-fx-fill: #092d13;"
        );

        // =====================================================
        // IMAGE VIEW
        // =====================================================

        profileImage =
                new ImageView();

        profileImage.setFitWidth(
                105
        );

        profileImage.setFitHeight(
                105
        );

        profileImage.setPreserveRatio(
                false
        );

        Circle imageClip =
                new Circle(
                        52.5,
                        52.5,
                        52.5
                );

        profileImage.setClip(
                imageClip
        );

        // =====================================================
        // DEFAULT INITIAL
        // =====================================================

        Label initial =
                new Label(
                        "B"
                );

        initial.setStyle(
                "-fx-font-size: 35px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        container.getChildren().addAll(
                backgroundCircle,
                initial,
                profileImage
        );

        // =====================================================
        // LOAD EXISTING IMAGE
        // =====================================================

        if (profileImageUrl != null
                && !profileImageUrl.trim().isEmpty()) {

            setProfileImage(
                    profileImageUrl
            );
        }

        return container;
    }

    // =========================================================
    // SET PROFILE IMAGE
    // =========================================================

    private void setProfileImage(
            String imageUrl
    ) {

        if (imageUrl == null
                || imageUrl.trim().isEmpty()) {

            return;
        }

        try {

            Image image =
                    new Image(
                            imageUrl,
                            105,
                            105,
                            false,
                            true
                    );

            profileImage.setImage(
                    image
            );

        } catch (Exception e) {

            System.err.println(
                    "Unable to load profile image."
            );

            e.printStackTrace();
        }
    }

    // =========================================================
    // LOAD PROFILE FROM FIREBASE
    // =========================================================

    private void loadProfileFromFirebase() {

        // =====================================================
        // CHECK UID
        // =====================================================

        if (currentBuyerUid == null
                || currentBuyerUid.trim().isEmpty()) {

            System.err.println(
                    "Buyer UID is not available."
            );

            Platform.runLater(() -> {

                if (imageStatusLabel != null) {

                    imageStatusLabel.setText(
                            "Buyer authentication information unavailable."
                    );
                }
            });

            return;
        }

        executor.submit(() -> {

            try {

                Firestore db =
                        FirebaseConfig.getFirestore();

                // =================================================
                // IMPORTANT:
                // UID IS THE DOCUMENT ID
                // =================================================

                String documentId =
                        currentBuyerUid.trim();

                ApiFuture<DocumentSnapshot> future =
                        db.collection(
                                COLLECTION_NAME
                        )
                        .document(
                                documentId
                        )
                        .get();

                DocumentSnapshot document =
                        future.get();

                if (!document.exists()) {

                    System.out.println(
                            "No buyer profile found for UID: "
                                    + documentId
                    );

                    return;
                }

                String loadedName =
                        getString(
                                document,
                                "name",
                                buyerName
                        );

                String loadedPhone =
                        getString(
                                document,
                                "phone",
                                phoneNumber
                        );

                String loadedEmail =
                        getString(
                                document,
                                "email",
                                email
                        );

                String loadedLocation =
                        getString(
                                document,
                                "location",
                                location
                        );

                String loadedBuyerType =
                        getString(
                                document,
                                "buyerType",
                                buyerType
                        );

                String loadedImageUrl =
                        getString(
                                document,
                                "profileImageUrl",
                                profileImageUrl
                        );

                // =================================================
                // UPDATE LOCAL VALUES
                // =================================================

                buyerName =
                        loadedName;

                phoneNumber =
                        loadedPhone;

                email =
                        loadedEmail;

                location =
                        loadedLocation;

                buyerType =
                        loadedBuyerType;

                profileImageUrl =
                        loadedImageUrl;

                // =================================================
                // UPDATE UI
                // =================================================

                Platform.runLater(() -> {

                    if (nameLabel != null) {

                        nameLabel.setText(
                                buyerName
                        );
                    }

                    if (phoneLabel != null) {

                        phoneLabel.setText(
                                phoneNumber
                        );
                    }

                    if (emailLabel != null) {

                        emailLabel.setText(
                                email
                        );
                    }

                    if (locationLabel != null) {

                        locationLabel.setText(
                                location
                        );
                    }

                    if (buyerTypeLabel != null) {

                        buyerTypeLabel.setText(
                                buyerType
                        );
                    }

                    if (profileImageUrl != null
                            && !profileImageUrl
                                    .trim()
                                    .isEmpty()) {

                        setProfileImage(
                                profileImageUrl
                        );
                    }
                });

            } catch (Exception e) {

                System.err.println(
                        "Unable to load buyer profile from Firebase."
                );

                e.printStackTrace();
            }
        });
    }

    // =========================================================
    // GET FIRESTORE STRING
    // =========================================================

    private String getString(
            DocumentSnapshot document,
            String field,
            String defaultValue
    ) {

        String value =
                document.getString(
                        field
                );

        if (value == null
                || value.trim().isEmpty()) {

            return defaultValue;
        }

        return value;
    }

    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private VBox createInformationCard() {

        VBox card =
                new VBox(20);

        card.setPadding(
                new Insets(25)
        );

        card.setPrefWidth(
                630
        );

        card.setMinHeight(
                280
        );

        card.setStyle(
                "-fx-background-color: #0B2613;" +
                "-fx-background-radius: 15;"
        );

        return card;
    }

    // =========================================================
    // CARD TITLE
    // =========================================================

    private Label createCardTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        return label;
    }

    // =========================================================
    // INFORMATION ROW
    // =========================================================

    private VBox createInfoRow(
            String title,
            Label value
    ) {

        VBox box =
                new VBox(4);

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #d5efd5;"
        );

        box.getChildren().addAll(
                titleLabel,
                value
        );

        return box;
    }

    // =========================================================
    // VALUE LABEL
    // =========================================================

    private Label createValueLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: white;"
        );

        return label;
    }
}

