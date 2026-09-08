package com.pravartak.view.buyer;

import com.pravartak.model.buyer_model.CartItem;
import com.pravartak.model.farmer_model.Product;
import com.pravartak.view.buyer.common.buyerTop;
import com.pravartak.view.farmer.common.Footer;
import com.pravartak.view.login.LoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class CartPage {

    private VBox cartContainer;
    private Label totalLabel;
    private Label itemCountLabel;

    // =====================================================
    // CART PAGE
    // =====================================================

    public BorderPane getCartPage() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D1117;"
        );

        // =================================================
        // TOP NAVBAR
        // =================================================

        root.setTop(
                new buyerTop().createBuyerTop("Cart")
        );

        // =================================================
        // FOOTER
        // =================================================

        root.setBottom(
                new Footer().createFooter()
        );

        // =================================================
        // MAIN CONTENT
        // =================================================

        VBox mainContent = new VBox(20);

        mainContent.setPadding(
                new Insets(25, 35, 30, 35)
        );

        // =================================================
        // HEADER
        // =================================================

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingBox = new VBox(5);

        Label title =
                new Label("Shopping Cart");

        title.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );

        itemCountLabel =
                new Label();

        itemCountLabel.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:14px;"
        );

        headingBox.getChildren().addAll(
                title,
                itemCountLabel
        );

        header.getChildren().add(
                headingBox
        );

        // =================================================
        // CART AREA
        // =================================================

        HBox cartArea =
                new HBox(25);

        VBox.setVgrow(
                cartArea,
                Priority.ALWAYS
        );

        // =================================================
        // CART ITEMS
        // =================================================

        cartContainer =
                new VBox(15);

        cartContainer.setPadding(
                new Insets(5)
        );

        ScrollPane scroll =
                new ScrollPane(
                        cartContainer
                );

        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background:#0D1117;" +
                "-fx-background-color:#0D1117;" +
                "-fx-control-inner-background:#0D1117;" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        // =================================================
        // SUMMARY
        // =================================================

        VBox summary =
                createSummaryBox();

        summary.setPrefWidth(300);
        summary.setMaxWidth(300);

        cartArea.getChildren().addAll(
                scroll,
                summary
        );

        HBox.setHgrow(
                scroll,
                Priority.ALWAYS
        );

        // =================================================
        // ADD TO PAGE
        // =================================================

        mainContent.getChildren().addAll(
                header,
                cartArea
        );

        root.setCenter(
                mainContent
        );

        // =================================================
        // LOAD CART
        // =================================================

        refreshCart();

        return root;
    }

    // =====================================================
    // REFRESH CART
    // =====================================================

    private void refreshCart() {

        cartContainer
                .getChildren()
                .clear();

        List<CartItem> items =
                CartManager.getCartItems();

        itemCountLabel.setText(
                CartManager.getCount()
                        + " item(s) in your cart"
        );

        // =================================================
        // EMPTY CART
        // =================================================

        if (items.isEmpty()) {

            VBox emptyBox =
                    new VBox(15);

            emptyBox.setAlignment(
                    Pos.CENTER
            );

            emptyBox.setPadding(
                    new Insets(60)
            );

            Label icon =
                    new Label("🛒");

            icon.setStyle(
                    "-fx-font-size:50px;"
            );

            Label emptyTitle =
                    new Label(
                            "Your cart is empty"
                    );

            emptyTitle.setStyle(
                    "-fx-text-fill:#EEEEEE;" +
                    "-fx-font-size:22px;" +
                    "-fx-font-weight:bold;"
            );

            Label message =
                    new Label(
                            "Add some fresh products from the marketplace."
                    );

            message.setStyle(
                    "-fx-text-fill:#888888;" +
                    "-fx-font-size:14px;"
            );

            Button shopButton =
                    new Button(
                            "Continue Shopping"
                    );

            shopButton.setStyle(
                    primaryButtonStyle()
            );

            shopButton.setOnAction(e ->
                    goToMarketplace()
            );

            emptyBox.getChildren().addAll(
                    icon,
                    emptyTitle,
                    message,
                    shopButton
            );

            cartContainer.getChildren().add(
                    emptyBox
            );

            totalLabel.setText("₹0.00");

            return;
        }

        // =================================================
        // CART ITEMS
        // =================================================

        for (CartItem item : items) {

            cartContainer.getChildren().add(
                    createCartItem(item)
            );
        }

        totalLabel.setText(
                String.format(
                        "₹%.2f",
                        CartManager.getTotal()
                )
        );
    }

    // =====================================================
    // CART ITEM CARD
    // =====================================================

    private HBox createCartItem(
            CartItem item) {

        Product product =
                item.getProduct();

        HBox card =
                new HBox(15);

        card.setPadding(
                new Insets(15)
        );

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        // =================================================
        // IMAGE
        // =================================================

        VBox imageBox =
                new VBox();

        imageBox.setPrefSize(
                120,
                100
        );

        imageBox.setMinSize(
                120,
                100
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color:#1B2425;" +
                "-fx-background-radius:8;"
        );

        String imagePath =
                product.getImagePath();

        if (imagePath != null &&
                !imagePath.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imagePath,
                                120,
                                100,
                                true,
                                true
                        );

                if (!image.isError()) {

                    ImageView imageView =
                            new ImageView(image);

                    imageView.setPreserveRatio(
                            true
                    );

                    imageView.setFitWidth(
                            120
                    );

                    imageView.setFitHeight(
                            100
                    );

                    imageBox.getChildren().add(
                            imageView
                    );

                } else {

                    addImagePlaceholder(
                            imageBox
                    );
                }

            } catch (Exception e) {

                addImagePlaceholder(
                        imageBox
                );
            }

        } else {

            addImagePlaceholder(
                    imageBox
            );
        }

        // =================================================
        // PRODUCT INFORMATION
        // =================================================

        VBox info =
                new VBox(6);

        HBox.setHgrow(
                info,
                Priority.ALWAYS
        );

        Label name =
                new Label(
                        safe(
                                product.getProductName()
                        )
                );

        name.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        Label category =
                new Label(
                        safe(
                                product.getCategory()
                        )
                );

        category.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:12px;"
        );

        Label farmer =
                new Label(
                        "Farmer ID: "
                                + product.getFarmerId()
                );

        farmer.setStyle(
                "-fx-text-fill:#999999;" +
                "-fx-font-size:12px;"
        );

        Label price =
                new Label(
                        String.format(
                                "₹%.2f / %s",
                                product.getPrice(),
                                safe(product.getUnit())
                        )
                );

        price.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;"
        );

        info.getChildren().addAll(
                name,
                category,
                farmer,
                price
        );

        // =================================================
        // QUANTITY CONTROL
        // =================================================

        VBox quantityBox =
                new VBox(6);

        quantityBox.setAlignment(
                Pos.CENTER
        );

        Label quantityTitle =
                new Label("Quantity");

        quantityTitle.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:11px;"
        );

        HBox quantityControls =
                new HBox(5);

        quantityControls.setAlignment(
                Pos.CENTER
        );

        Button minus =
                new Button("−");

        Button plus =
                new Button("+");

        Label quantityLabel =
                new Label(
                        formatQuantity(
                                item.getQuantity()
                        )
                );

        quantityLabel.setMinWidth(
                35
        );

        quantityLabel.setAlignment(
                Pos.CENTER
        );

        quantityLabel.setStyle(
                "-fx-text-fill:#FFFFFF;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;"
        );

        styleQuantityButton(minus);
        styleQuantityButton(plus);

        minus.setOnAction(e -> {

            double current =
                    item.getQuantity();

            double newQuantity =
                    current - 1;

            if (newQuantity <= 0) {

                CartManager.removeProduct(
                        product
                );

            } else {

                CartManager.updateQuantity(
                        product,
                        newQuantity
                );
            }

            refreshCart();
        });

        plus.setOnAction(e -> {

            double current =
                    item.getQuantity();

            double newQuantity =
                    current + 1;

            if (newQuantity >
                    product.getQuantity()) {

                newQuantity =
                        product.getQuantity();
            }

            CartManager.updateQuantity(
                    product,
                    newQuantity
            );

            refreshCart();
        });

        quantityControls.getChildren().addAll(
                minus,
                quantityLabel,
                plus
        );

        quantityBox.getChildren().addAll(
                quantityTitle,
                quantityControls
        );

        // =================================================
        // ITEM TOTAL
        // =================================================

        VBox totalBox =
                new VBox(5);

        totalBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label itemTotal =
                new Label(
                        String.format(
                                "₹%.2f",
                                item.getTotal()
                        )
                );

        itemTotal.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;"
        );

        Button remove =
                new Button("Remove");

        remove.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#FF4D5A;" +
                "-fx-font-size:12px;" +
                "-fx-cursor:hand;"
        );

        remove.setOnAction(e -> {

            CartManager.removeProduct(
                    product
            );

            refreshCart();
        });

        totalBox.getChildren().addAll(
                itemTotal,
                remove
        );

        // =================================================
        // ADD CARD CHILDREN
        // =================================================

        card.getChildren().addAll(
                imageBox,
                info,
                quantityBox,
                totalBox
        );

        return card;
    }

    // =====================================================
    // SUMMARY BOX
    // =====================================================

    private VBox createSummaryBox() {

        VBox summary =
                new VBox(18);

        summary.setPadding(
                new Insets(20)
        );

        summary.setStyle(
                "-fx-background-color:#101516;" +
                "-fx-border-color:#242B2C;" +
                "-fx-border-radius:12;" +
                "-fx-background-radius:12;"
        );

        Label summaryTitle =
                new Label(
                        "Order Summary"
                );

        summaryTitle.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        // =================================================
        // SUBTOTAL
        // =================================================

        HBox subtotalRow =
                new HBox();

        subtotalRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label subtotalText =
                new Label("Subtotal");

        subtotalText.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        Label subtotalValue =
                new Label();

        subtotalValue.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:14px;"
        );

        HBox.setHgrow(
                subtotalText,
                Priority.ALWAYS
        );

        subtotalRow.getChildren().addAll(
                subtotalText,
                subtotalValue
        );

        // =================================================
        // DELIVERY
        // =================================================

        HBox deliveryRow =
                new HBox();

        deliveryRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label deliveryText =
                new Label("Delivery");

        deliveryText.setStyle(
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:14px;"
        );

        Label deliveryValue =
                new Label("Calculated at checkout");

        deliveryValue.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:11px;"
        );

        HBox.setHgrow(
                deliveryText,
                Priority.ALWAYS
        );

        deliveryRow.getChildren().addAll(
                deliveryText,
                deliveryValue
        );

        // =================================================
        // SEPARATOR
        // =================================================

        Label separator =
                new Label();

        separator.setMinHeight(1);

        separator.setStyle(
                "-fx-background-color:#30363D;"
        );

        // =================================================
        // TOTAL
        // =================================================

        HBox totalRow =
                new HBox();

        totalRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label totalText =
                new Label("Total");

        totalText.setStyle(
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        totalLabel =
                new Label("₹0.00");

        totalLabel.setStyle(
                "-fx-text-fill:#68D34A;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        HBox.setHgrow(
                totalText,
                Priority.ALWAYS
        );

        totalRow.getChildren().addAll(
                totalText,
                totalLabel
        );

        // =================================================
        // CHECKOUT BUTTON
        // =================================================

        Button checkout =
                new Button(
                        "Proceed to Checkout"
                );

        checkout.setMaxWidth(
                Double.MAX_VALUE
        );

        checkout.setStyle(
                primaryButtonStyle()
        );

        checkout.setOnAction(e -> {

            if (CartManager.getCartItems().isEmpty()) {

                return;
            }

            CheckoutPage checkoutPage =
                    new CheckoutPage();

            LoginPage.mainStage.setScene(
                    new Scene(
                            checkoutPage.getCheckoutPage()
                    )
            );
        });

        // =================================================
        // CONTINUE SHOPPING
        // =================================================

        Button continueShopping =
                new Button(
                        "Continue Shopping"
                );

        continueShopping.setMaxWidth(
                Double.MAX_VALUE
        );

        continueShopping.setStyle(
                "-fx-background-color:#212627;" +
                "-fx-text-fill:#AAAAAA;" +
                "-fx-font-size:13px;" +
                "-fx-background-radius:7;" +
                "-fx-padding:10;" +
                "-fx-cursor:hand;"
        );

        continueShopping.setOnAction(
                e -> goToMarketplace()
        );

        // =================================================
        // ADD
        // =================================================

        summary.getChildren().addAll(
                summaryTitle,
                subtotalRow,
                deliveryRow,
                separator,
                totalRow,
                checkout,
                continueShopping
        );

        // =================================================
        // UPDATE SUMMARY
        // =================================================

        updateSummary(
                subtotalValue
        );

        return summary;
    }

    // =====================================================
    // UPDATE SUMMARY
    // =====================================================

    private void updateSummary(
            Label subtotalValue) {

        subtotalValue.setText(
                String.format(
                        "₹%.2f",
                        CartManager.getTotal()
                )
        );
    }

    // =====================================================
    // GO MARKETPLACE
    // =====================================================

    private void goToMarketplace() {

        BuyerMarketPlace marketplace =
                new BuyerMarketPlace();

        LoginPage.mainStage.setScene(
                new Scene(
                        marketplace.getMarketplacePage()
                )
        );
    }

    // =====================================================
    // QUANTITY BUTTON STYLE
    // =====================================================

    private void styleQuantityButton(
            Button button) {

        button.setPrefSize(
                30,
                30
        );

        button.setStyle(
                "-fx-background-color:#212627;" +
                "-fx-text-fill:#EEEEEE;" +
                "-fx-font-size:16px;" +
                "-fx-background-radius:6;" +
                "-fx-cursor:hand;"
        );

        button.setOnMouseEntered(e -> {

            button.setStyle(
                    "-fx-background-color:#303738;" +
                    "-fx-text-fill:#68D34A;" +
                    "-fx-font-size:16px;" +
                    "-fx-background-radius:6;" +
                    "-fx-cursor:hand;"
            );
        });

        button.setOnMouseExited(e -> {

            button.setStyle(
                    "-fx-background-color:#212627;" +
                    "-fx-text-fill:#EEEEEE;" +
                    "-fx-font-size:16px;" +
                    "-fx-background-radius:6;" +
                    "-fx-cursor:hand;"
            );
        });
    }

    // =====================================================
    // PRIMARY BUTTON
    // =====================================================

    private String primaryButtonStyle() {

        return
                "-fx-background-color:#68D34A;" +
                "-fx-text-fill:#080C0D;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-padding:10;" +
                "-fx-cursor:hand;";
    }

    // =====================================================
    // IMAGE PLACEHOLDER
    // =====================================================

    private void addImagePlaceholder(
            VBox box) {

        Label label =
                new Label("Product Image");

        label.setStyle(
                "-fx-text-fill:#666666;" +
                "-fx-font-size:12px;"
        );

        box.getChildren().add(
                label
        );
    }

    // =====================================================
    // FORMAT QUANTITY
    // =====================================================

    private String formatQuantity(
            double quantity) {

        if (quantity == (long) quantity) {

            return String.valueOf(
                    (long) quantity
            );
        }

        return String.format(
                "%.2f",
                quantity
        );
    }

    // =====================================================
    // SAFE
    // =====================================================

    private String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value;
    }
}