package com.pravartak.controller.farmercontoller;

import java.util.ArrayList;
import java.util.List;

import com.pravartak.model.farmer_model.Product;

public class ProductController {

    private List<Product> products;

    public ProductController() {

        products = new ArrayList<>();

        loadSampleProducts();
    }

    // =====================================================
    // GET FARMER PRODUCTS
    // =====================================================

    public List<Product> getFarmerProducts(int farmerId) {

        List<Product> farmerProducts =
                new ArrayList<>();

        for (Product product : products) {

            if (product.getFarmerId() == farmerId) {

                farmerProducts.add(product);
            }
        }

        return farmerProducts;
    }


    // =====================================================
    // ADD PRODUCT
    // =====================================================

    public boolean addProduct(Product product) {

        if (product == null) {
            return false;
        }

        products.add(product);

        return true;
    }


    // =====================================================
    // UPDATE PRODUCT
    // =====================================================

    public boolean updateProduct(Product updatedProduct) {

        for (int i = 0; i < products.size(); i++) {

            Product product =
                    products.get(i);

            if (product.getProductId()
                    == updatedProduct.getProductId()) {

                products.set(i, updatedProduct);

                return true;
            }
        }

        return false;
    }


    // =====================================================
    // DELETE PRODUCT
    // =====================================================

    public boolean deleteProduct(int productId) {

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).getProductId()
                    == productId) {

                products.remove(i);

                return true;
            }
        }

        return false;
    }


    // =====================================================
    // CHANGE PRODUCT STATUS
    // =====================================================

    public boolean changeStatus(
            int productId,
            String status) {

        for (Product product : products) {

            if (product.getProductId()
                    == productId) {

                product.setStatus(status);

                return true;
            }
        }

        return false;
    }


    // =====================================================
    // SEARCH
    // =====================================================

    public List<Product> searchProducts(
            int farmerId,
            String search) {

        List<Product> result =
                new ArrayList<>();

        for (Product product :
                getFarmerProducts(farmerId)) {

            if (product.getProductName()
                    .toLowerCase()
                    .contains(search.toLowerCase())) {

                result.add(product);
            }
        }

        return result;
    }


    // =====================================================
    // SORT
    // =====================================================

    public List<Product> sortProducts(
            List<Product> list,
            String sortType) {

        List<Product> result =
                new ArrayList<>(list);

        if (sortType.equals(
                "Price: Low to High")) {

            result.sort(
                    (a, b) ->
                            Double.compare(
                                    a.getPrice(),
                                    b.getPrice()));
        }

        else if (sortType.equals(
                "Price: High to Low")) {

            result.sort(
                    (a, b) ->
                            Double.compare(
                                    b.getPrice(),
                                    a.getPrice()));
        }

        else if (sortType.equals(
                "Newest")) {

            result.sort(
                    (a, b) ->
                            Integer.compare(
                                    b.getProductId(),
                                    a.getProductId()));
        }

        return result;
    }


    // =====================================================
    // SAMPLE DATA
    // =====================================================

    private void loadSampleProducts() {

        products.add(
                new Product(
                        1,
                        101,
                        "Fresh Tomatoes",
                        "Vegetables",
                        "Fresh farm grown tomatoes.",
                        40,
                        "kg",
                        120,
                        "Pune",
                        "",
                        "Active",
                        12));


        products.add(
                new Product(
                        2,
                        101,
                        "Wheat",
                        "Grains",
                        "High quality wheat.",
                        2500,
                        "quintal",
                        10,
                        "Pune",
                        "",
                        "Active",
                        5));


        products.add(
                new Product(
                        3,
                        101,
                        "Organic Onion",
                        "Vegetables",
                        "Fresh organic onions.",
                        45,
                        "kg",
                        0,
                        "Pune",
                        "",
                        "Sold Out",
                        18));


        products.add(
                new Product(
                        4,
                        101,
                        "Potatoes",
                        "Vegetables",
                        "Fresh farm potatoes.",
                        30,
                        "kg",
                        200,
                        "Pune",
                        "",
                        "Active",
                        8));


        products.add(
                new Product(
                        5,
                        101,
                        "Soybean",
                        "Grains",
                        "Premium quality soybean.",
                        4800,
                        "quintal",
                        20,
                        "Pune",
                        "",
                        "Active",
                        6));


        products.add(
                new Product(
                        6,
                        101,
                        "Sugarcane",
                        "Crops",
                        "Fresh sugarcane crop.",
                        3500,
                        "ton",
                        5,
                        "Pune",
                        "",
                        "Active",
                        4));
    }
}