package com.pravartak.view.buyer;

import com.pravartak.controller.buyercontroller.OrderController;
import com.pravartak.model.buyer_model.CartItem;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;
import com.pravartak.controller.buyercontroller.RazorpayController;
import com.pravartak.controller.buyercontroller.RazorpayBrowserServer;
import com.pravartak.controller.buyercontroller.RazorpayController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

import java.util.List;

public class CheckoutPage {

    private TextField nameField;
    private TextField phoneField;
    private TextArea addressField;

    private RadioButton codRadio;
    private RadioButton razorpayRadio;
    private String razorpayPaymentLinkId;

    private Label totalLabel;

    // =====================================================
    // PAGE
    // =====================================================

    public BorderPane getCheckoutPage() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // NAVBAR
        // =================================================

        root.setTop(
                new buyerTop().createBuyerTop("Checkout")
        );

        // =================================================
        // FOOTER
        // =================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =================================================
        // MAIN
        // =================================================

        VBox main =
                new VBox(20);

        main.setPadding(
                new Insets(
                        25,
                        40,
                        30,
                        40
                )
        );

        // =================================================
        // TITLE
        // =================================================

        Label title =
                new Label(
                        "Checkout"
                );

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Enter your delivery details and select a payment method."
                );

        subtitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:14px;"
        );

        // =================================================
        // CHECKOUT CONTENT
        // =================================================

        HBox checkoutContent =
                new HBox(25);

        VBox customerSection =
                createCustomerSection();

        VBox orderSection =
                createOrderSection();

        customerSection.setPrefWidth(
                550
        );

        orderSection.setPrefWidth(
                360
        );

        HBox.setHgrow(
                customerSection,
                Priority.ALWAYS
        );

        checkoutContent.getChildren().addAll(
                customerSection,
                orderSection
        );

        // =================================================
        // BACK BUTTON
        // =================================================

        Button back =
                new Button(
                        "← Back to Cart"
                );

        back.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );

        back.setOnAction(
                e -> goBackToCart()
        );

        main.getChildren().addAll(
                back,
                title,
                subtitle,
                checkoutContent
        );

        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scroll =
                new ScrollPane(
                        main
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        root.setCenter(
                scroll
        );

        return root;
    }

    // =====================================================
    // CUSTOMER SECTION
    // =====================================================

    private VBox createCustomerSection() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(22)
        );

        box.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        Label heading =
                new Label(
                        "Delivery Information"
                );

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // NAME
        // =================================================

        Label nameLabel =
                createFieldLabel(
                        "Full Name"
                );

        nameField =
                new TextField();

        nameField.setPromptText(
                "Enter your full name"
        );

        styleTextField(
                nameField
        );

        // =================================================
        // PHONE
        // =================================================

        Label phoneLabel =
                createFieldLabel(
                        "Phone Number"
                );

        phoneField =
                new TextField();

        phoneField.setPromptText(
                "Enter phone number"
        );

        styleTextField(
                phoneField
        );

        // =================================================
        // ADDRESS
        // =================================================

        Label addressLabel =
                createFieldLabel(
                        "Delivery Address"
                );

        addressField =
                new TextArea();

        addressField.setPromptText(
                "Enter complete delivery address"
        );

        addressField.setPrefRowCount(
                5
        );

        addressField.setWrapText(
                true
        );

        styleTextArea(
                addressField
        );

        // =================================================
        // BUYER PROFILE DATA
        // =================================================

        loadBuyerInformation();

        // =================================================
        // PAYMENT
        // =================================================

        Label paymentHeading =
                new Label(
                        "Payment Method"
                );

        paymentHeading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        ToggleGroup paymentGroup =
                new ToggleGroup();

        codRadio =
                new RadioButton(
                        "💵 Cash on Delivery"
                );

        razorpayRadio =
                new RadioButton(
                        "💳 Razorpay"
                );

        codRadio.setToggleGroup(
                paymentGroup
        );

        razorpayRadio.setToggleGroup(
                paymentGroup
        );

        codRadio.setSelected(
                true
        );

        styleRadioButton(
                codRadio
        );

        styleRadioButton(
                razorpayRadio
        );

        Label paymentInfo =
                new Label(
                        "Choose how you want to pay for your order."
                );

        paymentInfo.setWrapText(
                true
        );

        paymentInfo.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:12px;"
        );

        VBox paymentBox =
                new VBox(
                        10,
                        codRadio,
                        razorpayRadio,
                        paymentInfo
                );

        paymentBox.setPadding(
                new Insets(12)
        );

        paymentBox.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );

        // =================================================
        // ADD
        // =================================================

        box.getChildren().addAll(
                heading,

                nameLabel,
                nameField,

                phoneLabel,
                phoneField,

                addressLabel,
                addressField,

                paymentHeading,
                paymentBox
        );

        return box;
    }

    // =====================================================
    // ORDER SUMMARY
    // =====================================================

    private VBox createOrderSection() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(22)
        );

        box.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        Label heading =
                new Label(
                        "Order Summary"
                );

        heading.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // ITEMS
        // =================================================

        VBox itemsBox =
                new VBox(10);

        List<CartItem> items =
                CartManager.getCartItems();

        for (CartItem item :
                items) {

            itemsBox.getChildren().add(
                    createSummaryItem(
                            item
                    )
            );
        }

        // =================================================
        // SEPARATOR
        // =================================================

        Label separator =
                new Label();

        separator.setPrefHeight(
                1
        );

        separator.setMaxWidth(
                Double.MAX_VALUE
        );

        separator.setStyle(
                "-fx-background-color:#30363D;"
        );

        // =================================================
        // SUBTOTAL
        // =================================================

        HBox subtotal =
                createPriceRow(
                        "Subtotal",
                        String.format(
                                "₹%.2f",
                                CartManager.getTotal()
                        )
                );

        // =================================================
        // DELIVERY
        // =================================================

        HBox delivery =
                createPriceRow(
                        "Delivery",
                        "Free"
                );

        // =================================================
        // TOTAL
        // =================================================

        HBox total =
                new HBox();

        total.setAlignment(
                Pos.CENTER_LEFT
        );

        Label totalText =
                new Label(
                        "Total"
                );

        totalText.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        totalLabel =
                new Label(
                        String.format(
                                "₹%.2f",
                                CartManager.getTotal()
                        )
                );

        totalLabel.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:21px;" +
                "-fx-font-weight:bold;"
        );

        HBox.setHgrow(
                totalText,
                Priority.ALWAYS
        );

        total.getChildren().addAll(
                totalText,
                totalLabel
        );

        // =================================================
        // PLACE ORDER
        // =================================================

        Button placeOrder =
                new Button(
                        "Place Order Request"
                );

        placeOrder.setMaxWidth(
                Double.MAX_VALUE
        );

        placeOrder.setPrefHeight(
                45
        );

        placeOrder.setStyle(
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-cursor:hand;"
        );

        placeOrder.setOnAction(
                e -> placeOrder()
        );

        // =================================================
        // NOTE
        // =================================================

        Label note =
                new Label(
                        "Your order will first be sent to the farmer "
                        + "for acceptance."
                );

        note.setWrapText(
                true
        );

        note.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:11px;"
        );

        box.getChildren().addAll(
                heading,
                itemsBox,
                separator,
                subtotal,
                delivery,
                total,
                placeOrder,
                note
        );

        return box;
    }

    // =====================================================
    // SUMMARY ITEM
    // =====================================================

    private HBox createSummaryItem(
            CartItem item) {

        HBox row =
                new HBox(8);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label name =
                new Label(
                        item.getProduct()
                                .getProductName()
                );

        name.setWrapText(
                true
        );

        name.setStyle(
                "-fx-text-fill:#DDDDDD;" +
                "-fx-font-size:13px;"
        );

        Label quantity =
                new Label(
                        "x"
                                + formatNumber(
                                item.getQuantity()
                        )
                );

        quantity.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:12px;"
        );

        Label price =
                new Label(
                        String.format(
                                "₹%.2f",
                                item.getTotal()
                        )
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        HBox.setHgrow(
                name,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                name,
                quantity,
                price
        );

        return row;
    }

    // =====================================================
    // PRICE ROW
    // =====================================================

    private HBox createPriceRow(
            String title,
            String value) {

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:13px;"
        );

        HBox.setHgrow(
                titleLabel,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return row;
    }

    // =====================================================
    // LOAD BUYER INFORMATION
    // =====================================================

    private void loadBuyerInformation() {

        nameField.setText(
                BuyerProfilePage.buyerName
        );

        phoneField.setText(
                BuyerProfilePage.phoneNumber
        );

        addressField.setText(
                BuyerProfilePage.location
        );
    }

    // =====================================================
    // PLACE ORDER
    // =====================================================

    private void placeOrder() {

        // =================================================
        // VALIDATE CART
        // =================================================

        if (CartManager.getCartItems().isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Your cart is empty."
            );

            return;
        }

        // =================================================
        // NAME
        // =================================================

        String name =
                nameField
                        .getText()
                        .trim();

        if (name.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter your name."
            );

            return;
        }

        // =================================================
        // PHONE
        // =================================================

        String phone =
                phoneField
                        .getText()
                        .trim();

        if (phone.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter your phone number."
            );

            return;
        }

        // =================================================
        // ADDRESS
        // =================================================

        String address =
                addressField
                        .getText()
                        .trim();

        if (address.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter your delivery address."
            );

            return;
        }

        // =================================================
        // PAYMENT
        // =================================================

        String paymentMethod;

        if (codRadio.isSelected()) {

            paymentMethod =
                    "COD";

        } else {

            paymentMethod =
                    "RAZORPAY";
        }

        // =================================================
        // RAZORPAY
        // =================================================

      if (razorpayRadio.isSelected()) {

    startRazorpayPayment(
            name,
            phone,
            address
    );

    return;
}

        // =================================================
// CREATE ORDER REQUEST
// =================================================

OrderController orderController =
        new OrderController();

boolean created =
        orderController.createOrder(
                BuyerProfilePage.currentBuyerUid,
                name,
                phone,
                address,
                paymentMethod,
                CartManager.getCartItems()
        );

if (created) {

    double total =
            CartManager.getTotal();

    CartManager.clear();

    showAlert(
            Alert.AlertType.INFORMATION,
            "Order Request Sent Successfully!\n\n"
                    + "Total Amount: ₹"
                    + String.format(
                    "%.2f",
                    total
            )
                    + "\n"
                    + "Payment: "
                    + paymentMethod
                    + "\n\n"
                    + "The farmer will review your order request."
    );

    goToMarketplace();

} else {

    showAlert(
            Alert.AlertType.ERROR,
            "Order request could not be created.\n\n"
                    + "Please check your Firebase connection and try again."
    );
}
    }
    

    // =====================================================
    // BACK TO CART
    // =====================================================

    private void goBackToCart() {

        CartPage cartPage =
                new CartPage();

        LoginPage.mainStage.setScene(
                new Scene(
                        cartPage.getCartPage()
                )
        );
    }

    // =====================================================
    // FIELD LABEL
    // =====================================================

    private Label createFieldLabel(
            String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-text-fill:#BBBBBB;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        return label;
    }

    // =====================================================
    // TEXT FIELD STYLE
    // =====================================================

    private void styleTextField(
            TextField field) {

        field.setPrefHeight(
                40
        );

        field.setStyle(
                "-fx-background-color:#161B22;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8 12;"
        );
    }

    // =====================================================
    // TEXT AREA STYLE
    // =====================================================

    private void styleTextArea(
            TextArea area) {

        area.setStyle(
                "-fx-control-inner-background:#161B22;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-prompt-text-fill:#777777;" +
                "-fx-border-color:#30363D;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:8 12;"
        );
    }

    // =====================================================
    // RADIO BUTTON
    // =====================================================

    private void styleRadioButton(
            RadioButton radio) {

        radio.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;"
        );
    }

    // =====================================================
    // FORMAT NUMBER
    // =====================================================

    private String formatNumber(
            double number) {

        if (number == (long) number) {

            return String.valueOf(
                    (long) number
            );
        }

        return String.format(
                "%.2f",
                number
        );
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            Alert.AlertType type,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                "AgroBiz Checkout"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
    private void goToMarketplace() {

    try {

        BuyerMarketPlace marketplacePage =
                new BuyerMarketPlace();

        BorderPane marketplace =
                marketplacePage.getMarketplacePage();

        Scene scene =
                new Scene(
                        marketplace,
                        1400,
                        850
                );

        LoginPage.mainStage.setScene(scene);
        LoginPage.mainStage.show();

    } catch (Exception e) {

        e.printStackTrace();

        showAlert(
                Alert.AlertType.ERROR,
                "Unable to open Marketplace."
        );
    }
}

private void createAgroBizOrderAfterPayment(
        String name,
        String phone,
        String address) {

    try {

        OrderController orderController =
                new OrderController();

        boolean created =
                orderController.createOrder(
                        BuyerProfilePage.currentBuyerUid,
                        name,
                        phone,
                        address,
                        "RAZORPAY",
                        CartManager.getCartItems()
                );

        if (created) {

            double total =
                    CartManager.getTotal();

            CartManager.clear();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Payment Successful! ✓\n\n"
                    + "Your AgroBiz order has been placed.\n\n"
                    + "Amount Paid: ₹"
                    + String.format(
                            "%.2f",
                            total
                    )
            );

            goToMarketplace();

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Payment was successful, "
                    + "but the AgroBiz order could not be created."
            );
        }

    } catch (Exception e) {

        showAlert(
                Alert.AlertType.ERROR,
                "Could not create AgroBiz order.\n\n"
                + e.getMessage()
        );
    }
}
private void startRazorpayPayment(
        String name,
        String phone,
        String address) {

    try {

        if (CartManager.getCartItems().isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Your cart is empty."
            );

            return;
        }

        double total =
                CartManager.getTotal();

        RazorpayController razorpay =
                new RazorpayController();

        if (!razorpay.isConfigured()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Razorpay is not configured.\n\n"
                    + "Please check your "
                    + "RAZORPAY_KEY_ID and "
                    + "RAZORPAY_KEY_SECRET."
            );

            return;
        }

        /*
         * Create Razorpay Order.
         */
        String razorpayOrderId =
                razorpay.createOrder(
                        total,
                        "AGRO"
                                + System.currentTimeMillis()
                );

        /*
         * Open Razorpay in Chrome.
         */
        RazorpayBrowserServer browserServer =
                new RazorpayBrowserServer(

                        razorpay,

                        total,

                        name,

                        BuyerProfilePage.email,

                        phone,

                        razorpayOrderId,

                        // PAYMENT SUCCESS
                        () -> {

                            createAgroBizOrderAfterPayment(
                                    name,
                                    phone,
                                    address
                            );

                        },

                        // PAYMENT FAILED
                        () -> {

                            showAlert(
                                    Alert.AlertType.WARNING,
                                    "Razorpay payment was "
                                    + "cancelled or failed."
                            );

                        }
                );

        browserServer.start();

    } catch (Exception e) {

        e.printStackTrace();

        showAlert(
                Alert.AlertType.ERROR,
                "Razorpay payment could not be started.\n\n"
                + e.getMessage()
        );
    }
}
}