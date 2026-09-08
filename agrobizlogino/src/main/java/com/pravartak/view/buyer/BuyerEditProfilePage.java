
// package com.pravartak.view.buyer;

// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.Firestore;
// import com.pravartak.config.CloudinaryConfig;
// import com.pravartak.config.FirebaseConfig;

// import com.cloudinary.Cloudinary;

// import javafx.application.Platform;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.shape.Circle;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;

// import java.io.File;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

// public class BuyerEditProfilePage {

//     // =========================================================
//     // FORM FIELDS
//     // =========================================================

//     private TextField nameField;
//     private TextField phoneField;
//     private TextField emailField;
//     private TextField locationField;

//     private ComboBox<String> buyerTypeBox;

//     // =========================================================
//     // PROFILE IMAGE
//     // =========================================================

//     private ImageView profileImage;

//     private Label imageStatusLabel;

//     /*
//      * This variable temporarily holds the Cloudinary URL.
//      *
//      * It is NOT saved to Firebase until the user clicks
//      * "Save Profile".
//      */
//     private String selectedProfileImageUrl =
//             BuyerProfilePage.profileImageUrl;

//     // =========================================================
//     // FIREBASE
//     // =========================================================

//     private static final String COLLECTION_NAME =
//             "buyers";

//     private final ExecutorService executor =
//             Executors.newCachedThreadPool();

//     // =========================================================
//     // CONSTRUCTOR
//     // =========================================================

//     public BuyerEditProfilePage() {
//     }

//     // =========================================================
//     // EDIT PROFILE SCENE
//     // =========================================================

//     public Scene getEditProfileScene() {

//         BorderPane root =
//                 new BorderPane();

//         root.setPrefSize(
//                 1368,
//                 768
//         );

//         root.setStyle(
//                 "-fx-background-color: #06110c;"
//         );

//         // =====================================================
//         // HEADER
//         // =====================================================

//         VBox header =
//                 new VBox(4);

//         header.setPadding(
//                 new Insets(18, 35, 18, 35)
//         );

//         header.setStyle(
//                 "-fx-background-color: #0b2613;"
//         );

//         Label title =
//                 new Label(
//                         "Edit Buyer Profile"
//                 );

//         title.setStyle(
//                 "-fx-font-size: 28px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         Label subtitle =
//                 new Label(
//                         "Update your personal and buying information."
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
//         // FORM CARD
//         // =====================================================

//         VBox card =
//                 new VBox(25);

//         card.setMaxWidth(
//                 850
//         );

//         card.setPadding(
//                 new Insets(30)
//         );

//         card.setStyle(
//                 "-fx-background-color: #007d00;" +
//                 "-fx-background-radius: 15;"
//         );

//         // =====================================================
//         // FORM GRID
//         // =====================================================

//         GridPane form =
//                 new GridPane();

//         form.setHgap(
//                 25
//         );

//         form.setVgap(
//                 18
//         );

//         // =====================================================
//         // NAME
//         // =====================================================

//         Label nameLabel =
//                 createLabel(
//                         "Full Name"
//                 );

//         nameField =
//                 createTextField(
//                         BuyerProfilePage.buyerName
//                 );

//         // =====================================================
//         // PHONE
//         // =====================================================

//         Label phoneLabel =
//                 createLabel(
//                         "Phone Number"
//                 );

//         phoneField =
//                 createTextField(
//                         BuyerProfilePage.phoneNumber
//                 );

//         // =====================================================
//         // EMAIL
//         // =====================================================

//         Label emailLabel =
//                 createLabel(
//                         "Gmail"
//                 );

//         emailField =
//                 createTextField(
//                         BuyerProfilePage.email
//                 );

//         // =====================================================
//         // LOCATION
//         // =====================================================

//         Label locationLabel =
//                 createLabel(
//                         "Location"
//                 );

//         locationField =
//                 createTextField(
//                         BuyerProfilePage.location
//                 );

//         // =====================================================
//         // BUYER TYPE
//         // =====================================================

//         Label buyerTypeLabel =
//                 createLabel(
//                         "Buyer Type"
//                 );

//         buyerTypeBox =
//                 new ComboBox<>();

//         buyerTypeBox.getItems().addAll(
//                 "Wholesale Buyer",
//                 "Retail Buyer",
//                 "Regular Buyer",
//                 "Local Buyer"
//         );

//         buyerTypeBox.setValue(
//                 BuyerProfilePage.buyerType
//         );

//         buyerTypeBox.setPrefWidth(
//                 350
//         );

//         buyerTypeBox.setPrefHeight(
//                 40
//         );

//         buyerTypeBox.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-background-radius: 7;"
//         );

//         // =====================================================
//         // ADD FORM
//         // =====================================================

//         form.add(
//                 nameLabel,
//                 0,
//                 0
//         );

//         form.add(
//                 nameField,
//                 0,
//                 1
//         );

//         form.add(
//                 phoneLabel,
//                 1,
//                 0
//         );

//         form.add(
//                 phoneField,
//                 1,
//                 1
//         );

//         form.add(
//                 emailLabel,
//                 0,
//                 2
//         );

//         form.add(
//                 emailField,
//                 0,
//                 3
//         );

//         form.add(
//                 locationLabel,
//                 1,
//                 2
//         );

//         form.add(
//                 locationField,
//                 1,
//                 3
//         );

//         form.add(
//                 buyerTypeLabel,
//                 0,
//                 4
//         );

//         form.add(
//                 buyerTypeBox,
//                 0,
//                 5
//         );

//         // =====================================================
//         // PROFILE IMAGE SECTION
//         // =====================================================

//         Label imageTitle =
//                 createLabel(
//                         "Profile Image"
//                 );

//         StackPane imageContainer =
//                 createProfileImage();

//         Button uploadButton =
//                 new Button(
//                         "Upload Image"
//                 );

//         uploadButton.setPrefWidth(
//                 140
//         );

//         uploadButton.setPrefHeight(
//                 35
//         );

//         uploadButton.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-text-fill: #006b00;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 7;" +
//                 "-fx-cursor: hand;"
//         );

//         uploadButton.setOnAction(
//                 e -> uploadImage(uploadButton)
//         );

//         imageStatusLabel =
//                 new Label("");

//         imageStatusLabel.setStyle(
//                 "-fx-font-size: 12px;" +
//                 "-fx-text-fill: #d4f0d4;"
//         );

//         VBox imageBox =
//                 new VBox(10);

//         imageBox.setAlignment(
//                 Pos.CENTER_LEFT
//         );

//         imageBox.getChildren().addAll(
//                 imageTitle,
//                 imageContainer,
//                 uploadButton,
//                 imageStatusLabel
//         );

//         card.getChildren().addAll(
//                 form,
//                 imageBox
//         );

//         // =====================================================
//         // BUTTON BOX
//         // =====================================================

//         HBox buttonBox =
//                 new HBox(15);

//         buttonBox.setAlignment(
//                 Pos.CENTER_RIGHT
//         );

//         // =====================================================
//         // CANCEL
//         // =====================================================

//         Button cancelButton =
//                 new Button(
//                         "Cancel"
//                 );

//         cancelButton.setPrefWidth(
//                 120
//         );

//         cancelButton.setPrefHeight(
//                 40
//         );

//         cancelButton.setStyle(
//                 "-fx-background-color: #eeeeee;" +
//                 "-fx-text-fill: #333333;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 7;" +
//                 "-fx-cursor: hand;"
//         );

//         cancelButton.setOnAction(e -> {

//             BuyerProfilePage profilePage =
//                     new BuyerProfilePage();

//             Stage currentStage =
//                     (Stage) cancelButton
//                             .getScene()
//                             .getWindow();

//             currentStage.setScene(
//                     profilePage.getProfilePageScene()
//             );
//         });

//         // =====================================================
//         // SAVE
//         // =====================================================

//         Button saveButton =
//                 new Button(
//                         "Save Profile"
//                 );

//         saveButton.setPrefWidth(
//                 140
//         );

//         saveButton.setPrefHeight(
//                 40
//         );

//         saveButton.setStyle(
//                 "-fx-background-color: #063b0f;" +
//                 "-fx-text-fill: white;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 7;" +
//                 "-fx-cursor: hand;"
//         );

//         saveButton.setOnAction(
//                 e -> saveProfile(saveButton)
//         );

//         buttonBox.getChildren().addAll(
//                 cancelButton,
//                 saveButton
//         );

//         card.getChildren().add(
//                 buttonBox
//         );

//         // =====================================================
//         // CENTER
//         // =====================================================

//         VBox centerBox =
//                 new VBox();

//         centerBox.setAlignment(
//                 Pos.TOP_CENTER
//         );

//         centerBox.setPadding(
//                 new Insets(35)
//         );

//         centerBox.getChildren().add(
//                 card
//         );

//         root.setCenter(
//                 centerBox
//         );

//         return new Scene(
//                 root,
//                 1368,
//                 768
//         );
//     }

//     // =========================================================
//     // CREATE CIRCULAR IMAGE
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

//         Circle background =
//                 new Circle(
//                         52.5
//                 );

//         background.setStyle(
//                 "-fx-fill: #092d13;"
//         );

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

//         // =====================================================
//         // CIRCULAR CLIP
//         // =====================================================

//         Circle clip =
//                 new Circle(
//                         52.5,
//                         52.5,
//                         52.5
//                 );

//         profileImage.setClip(
//                 clip
//         );

//         // =====================================================
//         // DEFAULT LETTER
//         // =====================================================

//         Label initial =
//                 new Label("B");

//         initial.setStyle(
//                 "-fx-font-size: 35px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         container.getChildren().addAll(
//                 background,
//                 initial,
//                 profileImage
//         );

//         // =====================================================
//         // EXISTING IMAGE
//         // =====================================================

//         if (selectedProfileImageUrl != null
//                 && !selectedProfileImageUrl
//                         .trim()
//                         .isEmpty()) {

//             setProfileImage(
//                     selectedProfileImageUrl
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

//         Image image =
//                 new Image(
//                         imageUrl,
//                         105,
//                         105,
//                         false,
//                         true
//                 );

//         profileImage.setImage(
//                 image
//         );
//     }

//     // =========================================================
//     // UPLOAD TO CLOUDINARY
//     // =========================================================

//     private void uploadImage(
//             Button uploadButton
//     ) {

//         FileChooser fileChooser =
//                 new FileChooser();

//         fileChooser.setTitle(
//                 "Select Buyer Profile Image"
//         );

//         fileChooser.getExtensionFilters().add(
//                 new FileChooser.ExtensionFilter(
//                         "Image Files",
//                         "*.png",
//                         "*.jpg",
//                         "*.jpeg",
//                         "*.webp"
//                 )
//         );

//         Stage stage =
//                 (Stage) uploadButton
//                         .getScene()
//                         .getWindow();

//         File selectedFile =
//                 fileChooser.showOpenDialog(
//                         stage
//                 );

//         if (selectedFile == null) {
//             return;
//         }

//         uploadButton.setDisable(
//                 true
//         );

//         uploadButton.setText(
//                 "Uploading..."
//         );

//         imageStatusLabel.setText(
//                 "Uploading image to Cloudinary..."
//         );

//         executor.submit(() -> {

//             try {

//                 // =================================================
//                 // GET CLOUDINARY
//                 // =================================================

//                 Cloudinary cloudinary =
//                         CloudinaryConfig
//                                 .getCloudinary();

//                 // =================================================
//                 // CLOUDINARY OPTIONS
//                 // =================================================

//                 Map<String, Object> uploadOptions =
//                         new HashMap<>();

//                 uploadOptions.put(
//                         "folder",
//                         "agrobiz/buyer_profiles"
//                 );

//                 // =================================================
//                 // UPLOAD
//                 // =================================================

//                 Map<?, ?> result =
//                         cloudinary.uploader().upload(
//                                 selectedFile,
//                                 uploadOptions
//                         );

//                 Object secureUrl =
//                         result.get(
//                                 "secure_url"
//                         );

//                 if (secureUrl == null) {

//                     throw new RuntimeException(
//                             "Cloudinary did not return an image URL."
//                     );
//                 }

//                 String imageUrl =
//                         secureUrl.toString();

//                 /*
//                  * IMPORTANT:
//                  *
//                  * We DO NOT save to Firebase here.
//                  *
//                  * We only keep the URL temporarily.
//                  */
//                 selectedProfileImageUrl =
//                         imageUrl;

//                 Platform.runLater(() -> {

//                     setProfileImage(
//                             imageUrl
//                     );

//                     uploadButton.setDisable(
//                             false
//                     );

//                     uploadButton.setText(
//                             "Upload Image"
//                     );

//                     imageStatusLabel.setText(
//                             "Image selected successfully. Click Save Profile."
//                     );
//                 });

//             } catch (Exception e) {

//                 e.printStackTrace();

//                 Platform.runLater(() -> {

//                     uploadButton.setDisable(
//                             false
//                     );

//                     uploadButton.setText(
//                             "Upload Image"
//                     );

//                     imageStatusLabel.setText(
//                             "Image upload failed."
//                     );

//                     showAlert(
//                             "Upload Failed",
//                             "Unable to upload image to Cloudinary."
//                     );
//                 });
//             }
//         });
//     }

//     // =========================================================
//     // SAVE PROFILE
//     // =========================================================

//     private void saveProfile(
//             Button saveButton
//     ) {

//         // =====================================================
//         // GET VALUES
//         // =====================================================

//         String newName =
//                 nameField.getText().trim();

//         String newPhone =
//                 phoneField.getText().trim();

//         String newEmail =
//                 emailField.getText().trim();

//         String newLocation =
//                 locationField.getText().trim();

//         String newBuyerType =
//                 buyerTypeBox.getValue();

//         // =====================================================
//         // VALIDATION
//         // =====================================================

//         if (newName.isEmpty()
//                 || newPhone.isEmpty()
//                 || newEmail.isEmpty()
//                 || newLocation.isEmpty()
//                 || newBuyerType == null) {

//             showAlert(
//                     "Missing Information",
//                     "Please fill all profile fields."
//             );

//             return;
//         }

//         // =====================================================
//         // DISABLE BUTTON
//         // =====================================================

//         saveButton.setDisable(
//                 true
//         );

//         saveButton.setText(
//                 "Saving..."
//         );

//         executor.submit(() -> {

//             try {

//                 Firestore db =
//                         FirebaseConfig
//                                 .getFirestore();

//                 // =================================================
//                 // DETERMINE FIRESTORE DOCUMENT
//                 // =================================================

//                 String documentId;

//                 /*
//                  * If this buyer already has a document ID,
//                  * ALWAYS use it.
//                  *
//                  * This prevents a new document from being
//                  * created when the buyer changes their email.
//                  */
//                 if (BuyerProfilePage.firestoreDocumentId != null
//                         && !BuyerProfilePage
//                                 .firestoreDocumentId
//                                 .trim()
//                                 .isEmpty()) {

//                     documentId =
//                             BuyerProfilePage
//                                     .firestoreDocumentId;

//                 } else {

//                     /*
//                      * First-time profile.
//                      *
//                      * Use current email as initial ID.
//                      */
//                     documentId =
//                             BuyerProfilePage.email
//                                     .trim();
//                 }

//                 // =================================================
//                 // PROFILE DATA
//                 // =================================================

//                 Map<String, Object> profileData =
//                         new HashMap<>();

//                 profileData.put(
//                         "name",
//                         newName
//                 );

//                 profileData.put(
//                         "phone",
//                         newPhone
//                 );

//                 profileData.put(
//                         "email",
//                         newEmail
//                 );

//                 profileData.put(
//                         "location",
//                         newLocation
//                 );

//                 profileData.put(
//                         "buyerType",
//                         newBuyerType
//                 );

//                 // =================================================
//                 // CLOUDINARY IMAGE
//                 // =================================================

//                 if (selectedProfileImageUrl != null
//                         && !selectedProfileImageUrl
//                                 .trim()
//                                 .isEmpty()) {

//                     profileData.put(
//                             "profileImageUrl",
//                             selectedProfileImageUrl
//                     );
//                 }

//                 // =================================================
//                 // SAVE EVERYTHING TO ONE DOCUMENT
//                 // =================================================

//                 ApiFuture<?> future =
//                         db.collection(
//                                 COLLECTION_NAME
//                         )
//                         .document(
//                                 documentId
//                         )
//                         .set(
//                                 profileData
//                         );

//                 future.get();

//                 // =================================================
//                 // UPDATE LOCAL DATA ONLY AFTER SUCCESS
//                 // =================================================

//                 BuyerProfilePage.buyerName =
//                         newName;

//                 BuyerProfilePage.phoneNumber =
//                         newPhone;

//                 BuyerProfilePage.email =
//                         newEmail;

//                 BuyerProfilePage.location =
//                         newLocation;

//                 BuyerProfilePage.buyerType =
//                         newBuyerType;

//                 BuyerProfilePage.profileImageUrl =
//                         selectedProfileImageUrl;

//                 BuyerProfilePage.firestoreDocumentId =
//                         documentId;

//                 System.out.println(
//                         "Buyer profile saved successfully."
//                 );

//                 // =================================================
//                 // GO BACK TO PROFILE
//                 // =================================================

//                 Platform.runLater(() -> {

//                     saveButton.setDisable(
//                             false
//                     );

//                     saveButton.setText(
//                             "Save Profile"
//                     );

//                     BuyerProfilePage profilePage =
//                             new BuyerProfilePage();

//                     Stage currentStage =
//                             (Stage) saveButton
//                                     .getScene()
//                                     .getWindow();

//                     currentStage.setScene(
//                             profilePage
//                                     .getProfilePageScene()
//                     );
//                 });

//             } catch (Exception e) {

//                 e.printStackTrace();

//                 Platform.runLater(() -> {

//                     saveButton.setDisable(
//                             false
//                     );

//                     saveButton.setText(
//                             "Save Profile"
//                     );

//                     showAlert(
//                             "Save Failed",
//                             "Unable to save buyer profile to Firebase."
//                     );
//                 });
//             }
//         });
//     }

//     // =========================================================
//     // ALERT
//     // =========================================================

//     private void showAlert(
//             String title,
//             String message
//     ) {

//         Alert alert =
//                 new Alert(
//                         AlertType.ERROR
//                 );

//         alert.setTitle(
//                 title
//         );

//         alert.setHeaderText(
//                 null
//         );

//         alert.setContentText(
//                 message
//         );

//         alert.showAndWait();
//     }

//     // =========================================================
//     // CREATE LABEL
//     // =========================================================

//     private Label createLabel(
//             String text
//     ) {

//         Label label =
//                 new Label(
//                         text
//                 );

//         label.setStyle(
//                 "-fx-font-size: 14px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-text-fill: white;"
//         );

//         return label;
//     }

//     // =========================================================
//     // CREATE TEXT FIELD
//     // =========================================================

//     private TextField createTextField(
//             String value
//     ) {

//         TextField field =
//                 new TextField(
//                         value
//                 );

//         field.setPrefWidth(
//                 350
//         );

//         field.setPrefHeight(
//                 40
//         );

//         field.setStyle(
//                 "-fx-background-color: white;" +
//                 "-fx-background-radius: 7;" +
//                 "-fx-border-radius: 7;" +
//                 "-fx-padding: 8;"
//         );

//         return field;
//     }
// }


package com.pravartak.view.buyer;

import com.cloudinary.Cloudinary;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.pravartak.config.CloudinaryConfig;
import com.pravartak.config.FirebaseConfig;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuyerEditProfilePage {

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private TextField nameField;
    private TextField phoneField;
    private TextField emailField;
    private TextField locationField;

    private ComboBox<String> buyerTypeBox;

    // =========================================================
    // PROFILE IMAGE UI
    // =========================================================

    private ImageView profileImageView;

    private Label imageStatusLabel;

    private Button uploadImageButton;

    // =========================================================
    // FIREBASE
    // =========================================================

    private static final String COLLECTION_NAME =
            "buyers";

    private final ExecutorService executor =
            Executors.newCachedThreadPool();

    public BuyerEditProfilePage() {
    }

    // =========================================================
    // EDIT PROFILE SCENE
    // =========================================================

    public Scene getEditProfileScene() {

        BorderPane root =
                new BorderPane();

        root.setPrefSize(
                1368,
                768
        );

        root.setStyle(
                "-fx-background-color: #06110c;"
        );

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
                        "Edit Buyer Profile"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label subtitle =
                new Label(
                        "Update your personal and buying information."
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #7f9987;"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        root.setTop(
                header
        );

        // =====================================================
        // FORM CARD
        // =====================================================

        VBox card =
                new VBox(25);

        card.setMaxWidth(
                900
        );

        card.setPadding(
                new Insets(30)
        );

        card.setStyle(
                "-fx-background-color: #007d00;" +
                "-fx-background-radius: 15;"
        );

        // =====================================================
        // PROFILE IMAGE SECTION
        // =====================================================

        HBox imageSection =
                new HBox(20);

        imageSection.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane imageContainer =
                createProfileImage();

        VBox imageInfo =
                new VBox(8);

        imageInfo.setAlignment(
                Pos.CENTER_LEFT
        );

        Label imageTitle =
                new Label(
                        "Profile Picture"
                );

        imageTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label imageDescription =
                new Label(
                        "Upload a profile image for your buyer account."
                );

        imageDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #d4f0d4;"
        );

        uploadImageButton =
                new Button(
                        "Upload Image"
                );

        uploadImageButton.setPrefWidth(
                140
        );

        uploadImageButton.setPrefHeight(
                38
        );

        uploadImageButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #006b00;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        uploadImageButton.setOnAction(
                e -> uploadImage()
        );

        imageStatusLabel =
                new Label("");

        imageStatusLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #d4f0d4;"
        );

        imageInfo.getChildren().addAll(
                imageTitle,
                imageDescription,
                uploadImageButton,
                imageStatusLabel
        );

        imageSection.getChildren().addAll(
                imageContainer,
                imageInfo
        );

        // =====================================================
        // FORM GRID
        // =====================================================

        GridPane form =
                new GridPane();

        form.setHgap(
                25
        );

        form.setVgap(
                18
        );

        // =====================================================
        // NAME
        // =====================================================

        Label nameLabel =
                createLabel(
                        "Full Name"
                );

        nameField =
                createTextField(
                        BuyerProfilePage.buyerName
                );

        // =====================================================
        // PHONE
        // =====================================================

        Label phoneLabel =
                createLabel(
                        "Phone Number"
                );

        phoneField =
                createTextField(
                        BuyerProfilePage.phoneNumber
                );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                createLabel(
                        "Gmail"
                );

        emailField =
                createTextField(
                        BuyerProfilePage.email
                );

        // =====================================================
        // LOCATION
        // =====================================================

        Label locationLabel =
                createLabel(
                        "Location"
                );

        locationField =
                createTextField(
                        BuyerProfilePage.location
                );

        // =====================================================
        // BUYER TYPE
        // =====================================================

        Label buyerTypeLabel =
                createLabel(
                        "Buyer Type"
                );

        buyerTypeBox =
                new ComboBox<>();

        buyerTypeBox.getItems().addAll(
                "Wholesale Buyer",
                "Retail Buyer",
                "Regular Buyer",
                "Local Buyer"
        );

        buyerTypeBox.setValue(
                BuyerProfilePage.buyerType
        );

        buyerTypeBox.setPrefWidth(
                350
        );

        buyerTypeBox.setPrefHeight(
                40
        );

        buyerTypeBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;"
        );

        // =====================================================
        // ADD TO GRID
        // =====================================================

        form.add(
                nameLabel,
                0,
                0
        );

        form.add(
                nameField,
                0,
                1
        );

        form.add(
                phoneLabel,
                1,
                0
        );

        form.add(
                phoneField,
                1,
                1
        );

        form.add(
                emailLabel,
                0,
                2
        );

        form.add(
                emailField,
                0,
                3
        );

        form.add(
                locationLabel,
                1,
                2
        );

        form.add(
                locationField,
                1,
                3
        );

        form.add(
                buyerTypeLabel,
                0,
                4
        );

        form.add(
                buyerTypeBox,
                0,
                5
        );

        // =====================================================
        // BUTTON BOX
        // =====================================================

        HBox buttonBox =
                new HBox(15);

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // CANCEL BUTTON
        // =====================================================

        Button cancelButton =
                new Button(
                        "Cancel"
                );

        cancelButton.setPrefWidth(
                120
        );

        cancelButton.setPrefHeight(
                40
        );

        cancelButton.setStyle(
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        cancelButton.setOnAction(e -> {

            BuyerProfilePage profilePage =
                    new BuyerProfilePage();

            Stage currentStage =
                    (Stage) cancelButton
                            .getScene()
                            .getWindow();

            currentStage.setScene(
                    profilePage.getProfilePageScene()
            );
        });

        // =====================================================
        // SAVE BUTTON
        // =====================================================

        Button saveButton =
                new Button(
                        "Save Profile"
                );

        saveButton.setPrefWidth(
                140
        );

        saveButton.setPrefHeight(
                40
        );

        saveButton.setStyle(
                "-fx-background-color: #063b0f;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        saveButton.setOnAction(
                e -> saveProfile(saveButton)
        );

        buttonBox.getChildren().addAll(
                cancelButton,
                saveButton
        );

        card.getChildren().addAll(
                imageSection,
                form,
                buttonBox
        );

        // =====================================================
        // CENTER
        // =====================================================

        VBox centerBox =
                new VBox();

        centerBox.setAlignment(
                Pos.TOP_CENTER
        );

        centerBox.setPadding(
                new Insets(25)
        );

        centerBox.getChildren().add(
                card
        );

        root.setCenter(
                centerBox
        );

        return new Scene(
                root,
                1368,
                768
        );
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

        Circle backgroundCircle =
                new Circle(
                        52.5
                );

        backgroundCircle.setStyle(
                "-fx-fill: #092d13;"
        );

        profileImageView =
                new ImageView();

        profileImageView.setFitWidth(
                105
        );

        profileImageView.setFitHeight(
                105
        );

        profileImageView.setPreserveRatio(
                false
        );

        Circle imageClip =
                new Circle(
                        52.5,
                        52.5,
                        52.5
                );

        profileImageView.setClip(
                imageClip
        );

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
                profileImageView
        );

        // =====================================================
        // LOAD CURRENT IMAGE
        // =====================================================

        if (BuyerProfilePage.profileImageUrl != null
                && !BuyerProfilePage
                        .profileImageUrl
                        .trim()
                        .isEmpty()) {

            setImage(
                    BuyerProfilePage.profileImageUrl
            );
        }

        return container;
    }

    // =========================================================
    // SET IMAGE
    // =========================================================

    private void setImage(
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

            profileImageView.setImage(
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
    // UPLOAD IMAGE
    // =========================================================

    private void uploadImage() {

        // =====================================================
        // CHECK AUTHENTICATED BUYER
        // =====================================================

        if (BuyerProfilePage.currentBuyerUid == null
                || BuyerProfilePage.currentBuyerUid
                        .trim()
                        .isEmpty()) {

            showAlert(
                    "Authentication Error",
                    "Buyer authentication information is not available."
            );

            return;
        }

        // =====================================================
        // FILE CHOOSER
        // =====================================================

        FileChooser fileChooser =
                new FileChooser();

        fileChooser.setTitle(
                "Select Buyer Profile Image"
        );

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files",
                        "*.png",
                        "*.jpg",
                        "*.jpeg",
                        "*.webp"
                )
        );

        Stage stage =
                (Stage) uploadImageButton
                        .getScene()
                        .getWindow();

        File selectedFile =
                fileChooser.showOpenDialog(
                        stage
                );

        if (selectedFile == null) {

            return;
        }

        // =====================================================
        // DISABLE BUTTON
        // =====================================================

        uploadImageButton.setDisable(
                true
        );

        uploadImageButton.setText(
                "Uploading..."
        );

        imageStatusLabel.setText(
                "Uploading image to Cloudinary..."
        );

        // =====================================================
        // CLOUDINARY UPLOAD
        // =====================================================

        executor.submit(() -> {

            try {

                Cloudinary cloudinary =
                        CloudinaryConfig.getCloudinary();

                Map<String, Object> uploadOptions =
                        new HashMap<>();

                uploadOptions.put(
                        "folder",
                        "agrobiz/buyer_profiles"
                );

                Map<?, ?> uploadResult =
                        cloudinary.uploader().upload(
                                selectedFile,
                                uploadOptions
                        );

                Object secureUrlObject =
                        uploadResult.get(
                                "secure_url"
                        );

                if (secureUrlObject == null) {

                    throw new RuntimeException(
                            "Cloudinary did not return image URL."
                    );
                }

                String imageUrl =
                        secureUrlObject.toString();

                // =================================================
                // UPDATE LOCAL VALUE
                // =================================================

                BuyerProfilePage.profileImageUrl =
                        imageUrl;

                // =================================================
                // SAVE IMAGE URL TO SAME FIREBASE DOCUMENT
                // =================================================

                saveImageUrlToFirebase(
                        imageUrl
                );

                Platform.runLater(() -> {

                    setImage(
                            imageUrl
                    );

                    uploadImageButton.setDisable(
                            false
                    );

                    uploadImageButton.setText(
                            "Change Image"
                    );

                    imageStatusLabel.setText(
                            "Image uploaded successfully."
                    );
                });

            } catch (Exception ex) {

                ex.printStackTrace();

                Platform.runLater(() -> {

                    uploadImageButton.setDisable(
                            false
                    );

                    uploadImageButton.setText(
                            "Upload Image"
                    );

                    imageStatusLabel.setText(
                            "Image upload failed."
                    );
                });
            }
        });
    }

    // =========================================================
    // SAVE IMAGE URL TO FIREBASE
    // =========================================================

    private void saveImageUrlToFirebase(
            String imageUrl
    ) {

        executor.submit(() -> {

            try {

                Firestore db =
                        FirebaseConfig.getFirestore();

                // =================================================
                // IMPORTANT:
                // UID IS THE DOCUMENT ID
                // =================================================

                String documentId =
                        BuyerProfilePage
                                .currentBuyerUid
                                .trim();

                Map<String, Object> data =
                        new HashMap<>();

                data.put(
                        "profileImageUrl",
                        imageUrl
                );

                ApiFuture<?> future =
                        db.collection(
                                COLLECTION_NAME
                        )
                        .document(
                                documentId
                        )
                        .set(
                                data,
                                SetOptions.merge()
                        );

                future.get();

                System.out.println(
                        "Profile image URL saved to buyer UID document."
                );

            } catch (Exception e) {

                System.err.println(
                        "Unable to save image URL to Firebase."
                );

                e.printStackTrace();
            }
        });
    }

    // =========================================================
    // SAVE PROFILE
    // =========================================================

    private void saveProfile(
            Button saveButton
    ) {

        // =====================================================
        // CHECK UID
        // =====================================================

        if (BuyerProfilePage.currentBuyerUid == null
                || BuyerProfilePage.currentBuyerUid
                        .trim()
                        .isEmpty()) {

            showAlert(
                    "Authentication Error",
                    "Buyer authentication information is not available."
            );

            return;
        }

        // =====================================================
        // GET VALUES
        // =====================================================

        String newName =
                nameField.getText().trim();

        String newPhone =
                phoneField.getText().trim();

        String newEmail =
                emailField.getText().trim();

        String newLocation =
                locationField.getText().trim();

        String newBuyerType =
                buyerTypeBox.getValue();

        // =====================================================
        // BASIC VALIDATION
        // =====================================================

        if (newName.isEmpty()
                || newPhone.isEmpty()
                || newEmail.isEmpty()
                || newLocation.isEmpty()
                || newBuyerType == null) {

            showAlert(
                    "Missing Information",
                    "Please fill all profile fields."
            );

            return;
        }

        // =====================================================
        // EMAIL VALIDATION
        // =====================================================

        if (!newEmail.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            showAlert(
                    "Invalid Email",
                    "Please enter a valid email address."
            );

            return;
        }

        // =====================================================
        // DISABLE BUTTON
        // =====================================================

        saveButton.setDisable(
                true
        );

        saveButton.setText(
                "Saving..."
        );

        // =====================================================
        // UPDATE LOCAL VALUES
        // =====================================================

        BuyerProfilePage.buyerName =
                newName;

        BuyerProfilePage.phoneNumber =
                newPhone;

        BuyerProfilePage.email =
                newEmail;

        BuyerProfilePage.location =
                newLocation;

        BuyerProfilePage.buyerType =
                newBuyerType;

        // =====================================================
        // FIREBASE SAVE
        // =====================================================

        executor.submit(() -> {

            try {

                Firestore db =
                        FirebaseConfig.getFirestore();

                // =================================================
                // VERY IMPORTANT
                //
                // NEVER USE EMAIL AS DOCUMENT ID
                //
                // ALWAYS USE FIREBASE UID
                // =================================================

                String documentId =
                        BuyerProfilePage
                                .currentBuyerUid
                                .trim();

                // =================================================
                // PROFILE DATA
                // =================================================

                Map<String, Object> profileData =
                        new HashMap<>();

                profileData.put(
                        "uid",
                        documentId
                );

                profileData.put(
                        "name",
                        newName
                );

                profileData.put(
                        "phone",
                        newPhone
                );

                profileData.put(
                        "email",
                        newEmail
                );

                profileData.put(
                        "location",
                        newLocation
                );

                profileData.put(
                        "buyerType",
                        newBuyerType
                );

                // =================================================
                // KEEP CLOUDINARY IMAGE URL
                // =================================================

                if (BuyerProfilePage.profileImageUrl != null
                        && !BuyerProfilePage
                                .profileImageUrl
                                .trim()
                                .isEmpty()) {

                    profileData.put(
                            "profileImageUrl",
                            BuyerProfilePage.profileImageUrl
                    );
                }

                // =================================================
                // SAVE TO SAME UID DOCUMENT
                // =================================================

                ApiFuture<?> future =
                        db.collection(
                                COLLECTION_NAME
                        )
                        .document(
                                documentId
                        )
                        .set(
                                profileData,
                                SetOptions.merge()
                        );

                future.get();

                System.out.println(
                        "Buyer profile saved successfully."
                );

                System.out.println(
                        "Buyer document ID = "
                                + documentId
                );

                // =================================================
                // UI
                // =================================================

                Platform.runLater(() -> {

                    saveButton.setDisable(
                            false
                    );

                    saveButton.setText(
                            "Save Profile"
                    );

                    BuyerProfilePage profilePage =
                            new BuyerProfilePage();

                    Stage currentStage =
                            (Stage) saveButton
                                    .getScene()
                                    .getWindow();

                    currentStage.setScene(
                            profilePage
                                    .getProfilePageScene()
                    );
                });

            } catch (Exception ex) {

                ex.printStackTrace();

                Platform.runLater(() -> {

                    saveButton.setDisable(
                            false
                    );

                    saveButton.setText(
                            "Save Profile"
                    );

                    showAlert(
                            "Save Failed",
                            "Unable to save buyer profile to Firebase."
                    );
                });
            }
        });
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        AlertType.ERROR
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    // =========================================================
    // CREATE LABEL
    // =========================================================

    private Label createLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        return label;
    }

    // =========================================================
    // CREATE TEXT FIELD
    // =========================================================

    private TextField createTextField(
            String value
    ) {

        TextField field =
                new TextField(
                        value
                );

        field.setPrefWidth(
                350
        );

        field.setPrefHeight(
                40
        );

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;" +
                "-fx-border-radius: 7;" +
                "-fx-padding: 8;"
        );

        return field;
    }
}
