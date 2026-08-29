package com.pravartak.controller.farmercontoller;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.dao.farmer.ProductDAO;
import com.pravartak.model.farmer_model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private final ProductDAO productDAO;

    public ProductController() {

        Firestore db =
                FirebaseConfig.getFirestore();

        productDAO =
                new ProductDAO(db);
    }

    // =====================================================
    // ALL PRODUCTS - BUYER
    // =====================================================

    public List<Product> getAllProducts() {

        return productDAO.getAllProducts();
    }

    // =====================================================
    // FARMER PRODUCTS
    // =====================================================

    public List<Product> getFarmerProducts(
            int farmerId) {

        return productDAO.getFarmerProducts(
                farmerId
        );
    }

    // =====================================================
    // SINGLE PRODUCT
    // =====================================================

    public Product getProduct(
            int productId) {

        return productDAO.getProduct(
                productId
        );
    }

    // =====================================================
    // ADD
    // =====================================================

    public boolean addProduct(
            Product product) {

        return productDAO.addProduct(
                product
        );
    }

    // =====================================================
    // UPDATE
    // =====================================================

    public boolean updateProduct(
            Product product) {

        return productDAO.updateProduct(
                product
        );
    }

    // =====================================================
    // DELETE
    // =====================================================

    public boolean deleteProduct(
            int productId) {

        return productDAO.deleteProduct(
                productId
        );
    }

    // =====================================================
    // SEARCH ALL PRODUCTS
    // =====================================================

    public List<Product> searchAllProducts(
            String searchText) {

        List<Product> all =
                getAllProducts();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            return all;
        }

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        List<Product> result =
                new ArrayList<>();

        for (Product product : all) {

            if (product.getProductName()
                    .toLowerCase()
                    .contains(search)
                    ||
                    product.getCategory()
                            .toLowerCase()
                            .contains(search)
                    ||
                    product.getLocation()
                            .toLowerCase()
                            .contains(search)) {

                result.add(product);
            }
        }

        return result;
    }
}