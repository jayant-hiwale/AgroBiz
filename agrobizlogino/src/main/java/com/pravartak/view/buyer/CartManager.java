package com.pravartak.view.buyer;

import com.pravartak.model.buyer_model.CartItem;
import com.pravartak.model.farmer_model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final List<CartItem> cartItems =
            new ArrayList<>();

    // =====================================================
    // ADD PRODUCT
    // =====================================================

    public static void addProduct(
            Product product,
            double quantity) {

        if (product == null || quantity <= 0) {
            return;
        }

        for (CartItem item : cartItems) {

            if (item.getProduct().getProductId()
                    == product.getProductId()) {

                double newQuantity =
                        item.getQuantity() + quantity;

                if (newQuantity > product.getQuantity()) {
                    newQuantity = product.getQuantity();
                }

                item.setQuantity(newQuantity);

                return;
            }
        }

        cartItems.add(
                new CartItem(
                        product,
                        quantity
                )
        );
    }

    // =====================================================
    // REMOVE PRODUCT
    // =====================================================

    public static void removeProduct(
            Product product) {

        if (product == null) {
            return;
        }

        cartItems.removeIf(
                item ->
                        item.getProduct()
                                .getProductId()
                                == product.getProductId()
        );
    }

    // =====================================================
    // UPDATE QUANTITY
    // =====================================================

    public static void updateQuantity(
            Product product,
            double quantity) {

        if (product == null) {
            return;
        }

        for (CartItem item : cartItems) {

            if (item.getProduct().getProductId()
                    == product.getProductId()) {

                if (quantity <= 0) {

                    removeProduct(product);

                } else {

                    if (quantity > product.getQuantity()) {
                        quantity = product.getQuantity();
                    }

                    item.setQuantity(quantity);
                }

                return;
            }
        }
    }

    // =====================================================
    // GET CART
    // =====================================================

    public static List<CartItem> getCartItems() {

        return new ArrayList<>(
                cartItems
        );
    }

    // =====================================================
    // CART COUNT
    // =====================================================

    public static int getCount() {

        int count = 0;

        for (CartItem item : cartItems) {

            count += (int) item.getQuantity();
        }

        return count;
    }

    // =====================================================
    // TOTAL
    // =====================================================

    public static double getTotal() {

        double total = 0;

        for (CartItem item : cartItems) {

            total += item.getTotal();
        }

        return total;
    }

    // =====================================================
    // CLEAR CART
    // =====================================================

    public static void clear() {

        cartItems.clear();
    }

    // =====================================================
    // IS IN CART
    // =====================================================

    public static boolean isInCart(
            Product product) {

        if (product == null) {
            return false;
        }

        for (CartItem item : cartItems) {

            if (item.getProduct().getProductId()
                    == product.getProductId()) {

                return true;
            }
        }

        return false;
    }
}