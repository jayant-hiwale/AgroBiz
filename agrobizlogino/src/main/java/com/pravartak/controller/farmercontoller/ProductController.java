// package com.pravartak.controller.farmercontoller;

// import com.google.cloud.firestore.Firestore;
// import com.pravartak.config.FirebaseConfig;
// import com.pravartak.dao.farmer.ProductDAO;
// import com.pravartak.model.farmer_model.Product;

// import java.util.ArrayList;
// import java.util.List;

// public class ProductController {

//     private final ProductDAO productDAO;

//     public ProductController() {

//         Firestore db =
//                 FirebaseConfig.getFirestore();

//         productDAO =
//                 new ProductDAO(db);
//     }

//     // =====================================================
//     // ADD PRODUCT
//     // =====================================================

//     public boolean addProduct(Product product) {

//         return productDAO.addProduct(product);
//     }

//     // =====================================================
//     // GET ALL PRODUCTS
//     // Used by Buyer Marketplace
//     // =====================================================

//     public List<Product> getAllProducts() {

//         return productDAO.getAllProducts();
//     }

//     // =====================================================
//     // GET FARMER PRODUCTS
//     // Used by Farmer Marketplace
//     // =====================================================

//     public List<Product> getFarmerProducts(
//             int farmerId) {

//         return productDAO.getFarmerProducts(
//                 farmerId
//         );
//     }

//     // =====================================================
//     // GET SINGLE PRODUCT
//     // =====================================================

//     public Product getProduct(
//             int productId) {

//         return productDAO.getProduct(
//                 productId
//         );
//     }

//     // =====================================================
//     // UPDATE PRODUCT
//     // =====================================================

//     public boolean updateProduct(
//             Product product) {

//         return productDAO.updateProduct(
//                 product
//         );
//     }

//     // =====================================================
//     // DELETE PRODUCT
//     // =====================================================

//     public boolean deleteProduct(
//             int productId) {

//         return productDAO.deleteProduct(
//                 productId
//         );
//     }

//     // =====================================================
//     // SEARCH ALL PRODUCTS
//     // Used by Buyer Marketplace
//     // =====================================================

//     public List<Product> searchAllProducts(
//             String searchText) {

//         List<Product> allProducts =
//                 productDAO.getAllProducts();

//         if (searchText == null ||
//                 searchText.trim().isEmpty()) {

//             return allProducts;
//         }

//         String search =
//                 searchText
//                         .trim()
//                         .toLowerCase();

//         List<Product> result =
//                 new ArrayList<>();

//         for (Product product :
//                 allProducts) {

//             String name =
//                     product.getProductName();

//             String category =
//                     product.getCategory();

//             String location =
//                     product.getLocation();

//             String description =
//                     product.getDescription();

//             boolean matches =
//                     (name != null &&
//                      name.toLowerCase()
//                          .contains(search))
//                     ||
//                     (category != null &&
//                      category.toLowerCase()
//                          .contains(search))
//                     ||
//                     (location != null &&
//                      location.toLowerCase()
//                          .contains(search))
//                     ||
//                     (description != null &&
//                      description.toLowerCase()
//                          .contains(search));

//             if (matches) {

//                 result.add(product);
//             }
//         }

//         return result;
//     }

//     public List<Product> searchProducts(int farmerId, String text) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'searchProducts'");
//     }

//     public List<Product> sortProducts(List<Product> products, String sortType) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'sortProducts'");
//     }
// }
package com.pravartak.controller.farmercontoller;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.dao.farmer.ProductDAO;
import com.pravartak.model.farmer_model.Product;

import java.util.ArrayList;
import java.util.Comparator;
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
    // ADD
    // =====================================================

    public boolean addProduct(
            Product product) {

        return productDAO.addProduct(
                product
        );
    }

    // =====================================================
    // ALL PRODUCTS
    // BUYER
    // =====================================================

    public List<Product> getAllProducts() {

        return productDAO.getAllProducts();
    }

    // =====================================================
    // CURRENT FARMER PRODUCTS
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
    // BUYER
    // =====================================================

    public List<Product> searchAllProducts(
            String searchText) {

        List<Product> allProducts =
                productDAO.getAllProducts();

        if (searchText == null ||
                searchText.trim().isEmpty()) {

            return allProducts;
        }

        String search =
                searchText.trim().toLowerCase();

        List<Product> result =
                new ArrayList<>();

        for (Product product :
                allProducts) {

            boolean matches =
                    product.getProductName() != null &&
                    product.getProductName()
                            .toLowerCase()
                            .contains(search);

            if (!matches &&
                    product.getCategory() != null) {

                matches =
                        product.getCategory()
                                .toLowerCase()
                                .contains(search);
            }

            if (!matches &&
                    product.getLocation() != null) {

                matches =
                        product.getLocation()
                                .toLowerCase()
                                .contains(search);
            }

            if (!matches &&
                    product.getDescription() != null) {

                matches =
                        product.getDescription()
                                .toLowerCase()
                                .contains(search);
            }

            if (matches) {

                result.add(product);
            }
        }

        return result;
    }

    // =====================================================
    // SEARCH CURRENT FARMER PRODUCTS
    // =====================================================

    public List<Product> searchProducts(
            int farmerId,
            String text) {

        return productDAO.searchFarmerProducts(
                farmerId,
                text
        );
    }

    // =====================================================
    // SORT
    // =====================================================

    public List<Product> sortProducts(
            List<Product> products,
            String sortType) {

        if (products == null) {

            return new ArrayList<>();
        }

        List<Product> result =
                new ArrayList<>(
                        products
                );

        if (sortType == null) {

            return result;
        }

        switch (sortType) {

            case "Price: Low to High":

                result.sort(
                        Comparator.comparingDouble(
                                Product::getPrice
                        )
                );

                break;

            case "Price: High to Low":

                result.sort(
                        Comparator.comparingDouble(
                                Product::getPrice
                        ).reversed()
                );

                break;

            case "Quantity: High to Low":

                result.sort(
                        Comparator.comparingDouble(
                                Product::getQuantity
                        ).reversed()
                );

                break;

            case "Name: A-Z":

                result.sort(
                        Comparator.comparing(
                                Product::getProductName,
                                Comparator.nullsLast(
                                        String.CASE_INSENSITIVE_ORDER
                                )
                        )
                );

                break;

            case "Name: Z-A":

                result.sort(
                        Comparator.comparing(
                                Product::getProductName,
                                Comparator.nullsLast(
                                        String.CASE_INSENSITIVE_ORDER
                                )
                        ).reversed()
                );

                break;

            default:
                break;
        }

        return result;
    }
}