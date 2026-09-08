package com.pravartak.view.buyer;

import com.pravartak.model.farmer_model.Product;

import java.util.ArrayList;
import java.util.List;

public class WatchlistManager {

    private static final List<Product> watchlist = new ArrayList<>();

    private WatchlistManager() {
        // Prevent object creation
    }

    // =====================================================
    // ADD PRODUCT
    // =====================================================

    public static void addProduct(Product product) {

        if (product == null) {
            return;
        }

        if (!isLiked(product)) {
            watchlist.add(product);
        }
    }

    // =====================================================
    // REMOVE PRODUCT
    // =====================================================

    public static void removeProduct(Product product) {

        if (product == null) {
            return;
        }

        watchlist.removeIf(p ->
                p.getProductName() != null &&
                p.getProductName().equals(product.getProductName()) &&
                p.getFarmerId() == product.getFarmerId()
        );
    }

    // =====================================================
    // CHECK LIKE
    // =====================================================

    public static boolean isLiked(Product product) {

        if (product == null) {
            return false;
        }

        return watchlist.stream().anyMatch(p ->
                p.getProductName() != null &&
                p.getProductName().equals(product.getProductName()) &&
                p.getFarmerId() == product.getFarmerId()
        );
    }

    // =====================================================
    // GET ALL
    // =====================================================

    public static List<Product> getProducts() {

        return new ArrayList<>(watchlist);
    }

    // =====================================================
    // COUNT
    // =====================================================

    public static int getCount() {

        return watchlist.size();
    }

    // =====================================================
    // CLEAR
    // =====================================================

    public static void clear() {

        watchlist.clear();
    }
}