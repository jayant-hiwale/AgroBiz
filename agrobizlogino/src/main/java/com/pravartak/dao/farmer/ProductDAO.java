// // package com.pravartak.dao.farmer;

// // import com.google.cloud.firestore.Firestore;
// // import com.pravartak.model.farmer_model.Product;

// // import java.util.ArrayList;
// // import java.util.List;

// // public class ProductDAO {

// //     private final Firestore db;

// //     public ProductDAO(Firestore db) {

// //         if (db == null) {
// //             throw new IllegalArgumentException(
// //                     "Firestore cannot be null."
// //             );
// //         }

// //         this.db = db;
// //     }

// //     // =====================================================
// //     // ADD
// //     // =====================================================

// //     public boolean addProduct(Product product) {

// //         try {

// //             db.collection("products")
// //                     .document(
// //                             String.valueOf(
// //                                     product.getProductId()
// //                             )
// //                     )
// //                     .set(product)
// //                     .get();

// //             return true;

// //         } catch (Exception e) {

// //             e.printStackTrace();
// //             return false;
// //         }
// //     }

// //     // =====================================================
// //     // ALL PRODUCTS - BUYER
// //     // =====================================================

// //     public List<Product> getAllProducts() {

// //         List<Product> products =
// //                 new ArrayList<>();

// //         try {

// //             var snapshot =
// //                     db.collection("products")
// //                             .get()
// //                             .get();

// //             for (var document :
// //                     snapshot.getDocuments()) {

// //                 Product product =
// //                         document.toObject(
// //                                 Product.class
// //                         );

// //                 if (product != null) {
// //                     products.add(product);
// //                 }
// //             }

// //         } catch (Exception e) {
// //             e.printStackTrace();
// //         }

// //         return products;
// //     }

// //     // =====================================================
// //     // ONLY CURRENT FARMER PRODUCTS
// //     // =====================================================

// //     public List<Product> getFarmerProducts(
// //             int farmerId) {

// //         List<Product> products =
// //                 new ArrayList<>();

// //         try {

// //             var snapshot =
// //                     db.collection("products")
// //                             .whereEqualTo(
// //                                     "farmerId",
// //                                     farmerId
// //                             )
// //                             .get()
// //                             .get();

// //             for (var document :
// //                     snapshot.getDocuments()) {

// //                 Product product =
// //                         document.toObject(
// //                                 Product.class
// //                         );

// //                 if (product != null) {
// //                     products.add(product);
// //                 }
// //             }

// //         } catch (Exception e) {
// //             e.printStackTrace();
// //         }

// //         return products;
// //     }

// //     // =====================================================
// //     // SINGLE PRODUCT
// //     // =====================================================

// //     public Product getProduct(int productId) {

// //         try {

// //             var document =
// //                     db.collection("products")
// //                             .document(
// //                                     String.valueOf(
// //                                             productId
// //                                     )
// //                             )
// //                             .get()
// //                             .get();

// //             if (!document.exists()) {
// //                 return null;
// //             }

// //             return document.toObject(
// //                     Product.class
// //             );

// //         } catch (Exception e) {

// //             e.printStackTrace();
// //             return null;
// //         }
// //     }

// //     // =====================================================
// //     // DELETE
// //     // =====================================================

// //     public boolean deleteProduct(int productId) {

// //         try {

// //             db.collection("products")
// //                     .document(
// //                             String.valueOf(productId)
// //                     )
// //                     .delete()
// //                     .get();

// //             return true;

// //         } catch (Exception e) {

// //             e.printStackTrace();
// //             return false;
// //         }
// //     }

// //     // =====================================================
// //     // UPDATE
// //     // =====================================================

// //     public boolean updateProduct(Product product) {

// //         try {

// //             db.collection("products")
// //                     .document(
// //                             String.valueOf(
// //                                     product.getProductId()
// //                             )
// //                     )
// //                     .set(product)
// //                     .get();

// //             return true;

// //         } catch (Exception e) {

// //             e.printStackTrace();
// //             return false;
// //         }
// //     }
// // }
// package com.pravartak.dao.farmer;

// import com.google.cloud.firestore.Firestore;
// import com.pravartak.model.farmer_model.Product;

// import java.util.ArrayList;
// import java.util.List;

// public class ProductDAO {

//     private final Firestore db;

//     public ProductDAO(Firestore db) {

//         if (db == null) {
//             throw new IllegalArgumentException(
//                     "Firestore cannot be null."
//             );
//         }

//         this.db = db;
//     }

//     // =====================================================
//     // ADD PRODUCT
//     // =====================================================

//     public boolean addProduct(
//             Product product) {

//         try {

//             db.collection("products")
//                     .document(
//                             String.valueOf(
//                                     product.getProductId()
//                             )
//                     )
//                     .set(product)
//                     .get();

//             return true;

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =====================================================
//     // GET ALL PRODUCTS
//     // BUYER
//     // =====================================================

//     public List<Product> getAllProducts() {

//         List<Product> products =
//                 new ArrayList<>();

//         try {

//             var snapshot =
//                     db.collection("products")
//                             .get()
//                             .get();

//             for (var document :
//                     snapshot.getDocuments()) {

//                 Product product =
//                         document.toObject(
//                                 Product.class
//                         );

//                 if (product != null) {

//                     products.add(product);
//                 }
//             }

//         } catch (Exception e) {

//             e.printStackTrace();
//         }

//         return products;
//     }

//     // =====================================================
//     // GET ONLY CURRENT FARMER PRODUCTS
//     // =====================================================

//     public List<Product> getFarmerProducts(
//             int farmerId) {

//         List<Product> products =
//                 new ArrayList<>();

//         try {

//             var snapshot =
//                     db.collection("products")
//                             .whereEqualTo(
//                                     "farmerId",
//                                     farmerId
//                             )
//                             .get()
//                             .get();

//             for (var document :
//                     snapshot.getDocuments()) {

//                 Product product =
//                         document.toObject(
//                                 Product.class
//                         );

//                 if (product != null) {

//                     products.add(product);
//                 }
//             }

//         } catch (Exception e) {

//             e.printStackTrace();
//         }

//         return products;
//     }

//     // =====================================================
//     // GET SINGLE PRODUCT
//     // =====================================================

//     public Product getProduct(
//             int productId) {

//         try {

//             var document =
//                     db.collection("products")
//                             .document(
//                                     String.valueOf(
//                                             productId
//                                     )
//                             )
//                             .get()
//                             .get();

//             if (!document.exists()) {

//                 return null;
//             }

//             return document.toObject(
//                     Product.class
//             );

//         } catch (Exception e) {

//             e.printStackTrace();

//             return null;
//         }
//     }

//     // =====================================================
//     // SEARCH FARMER PRODUCTS
//     // =====================================================

//     public List<Product> searchFarmerProducts(
//             int farmerId,
//             String text) {

//         List<Product> products =
//                 getFarmerProducts(farmerId);

//         if (text == null ||
//                 text.trim().isEmpty()) {

//             return products;
//         }

//         String search =
//                 text.trim().toLowerCase();

//         List<Product> result =
//                 new ArrayList<>();

//         for (Product product :
//                 products) {

//             boolean matches = false;

//             if (product.getProductName() != null &&
//                     product.getProductName()
//                             .toLowerCase()
//                             .contains(search)) {

//                 matches = true;
//             }

//             if (!matches &&
//                     product.getCategory() != null &&
//                     product.getCategory()
//                             .toLowerCase()
//                             .contains(search)) {

//                 matches = true;
//             }

//             if (!matches &&
//                     product.getDescription() != null &&
//                     product.getDescription()
//                             .toLowerCase()
//                             .contains(search)) {

//                 matches = true;
//             }

//             if (!matches &&
//                     product.getLocation() != null &&
//                     product.getLocation()
//                             .toLowerCase()
//                             .contains(search)) {

//                 matches = true;
//             }

//             if (matches) {

//                 result.add(product);
//             }
//         }

//         return result;
//     }

//     // =====================================================
//     // DELETE
//     // =====================================================

//     public boolean deleteProduct(
//             int productId) {

//         try {

//             db.collection("products")
//                     .document(
//                             String.valueOf(
//                                     productId
//                             )
//                     )
//                     .delete()
//                     .get();

//             return true;

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =====================================================
//     // UPDATE
//     // =====================================================

//     public boolean updateProduct(
//             Product product) {

//         try {

//             db.collection("products")
//                     .document(
//                             String.valueOf(
//                                     product.getProductId()
//                             )
//                     )
//                     .set(product)
//                     .get();

//             return true;

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }
// }
package com.pravartak.dao.farmer;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.pravartak.model.farmer_model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Firestore db;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ProductDAO(Firestore db) {

        if (db == null) {
            throw new IllegalArgumentException(
                    "Firestore cannot be null."
            );
        }

        this.db = db;
    }

    // =====================================================
    // ADD PRODUCT
    // =====================================================

    public boolean addProduct(
            Product product) {

        try {

            if (product == null) {
                return false;
            }

            // -------------------------------------------------
            // SET UPLOAD TIME
            // -------------------------------------------------

            if (product.getCreatedAt() == null) {

                product.setCreatedAt(
                        Timestamp.now()
                );
            }

            // -------------------------------------------------
            // SAVE PRODUCT
            // -------------------------------------------------

            db.collection("products")
                    .document(
                            String.valueOf(
                                    product.getProductId()
                            )
                    )
                    .set(product)
                    .get();

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    // =====================================================
    // GET ALL PRODUCTS
    // BUYER
    // =====================================================

    public List<Product> getAllProducts() {

        List<Product> products =
                new ArrayList<>();

        try {

            var snapshot =
                    db.collection("products")
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                Product product =
                        document.toObject(
                                Product.class
                        );

                if (product != null) {

                    products.add(product);
                }
            }

        } catch (Exception e) {

            // Return products already loaded
        }

        return products;
    }

    // =====================================================
    // GET ONLY CURRENT FARMER PRODUCTS
    // =====================================================

    public List<Product> getFarmerProducts(
            int farmerId) {

        List<Product> products =
                new ArrayList<>();

        try {

            var snapshot =
                    db.collection("products")
                            .whereEqualTo(
                                    "farmerId",
                                    farmerId
                            )
                            .get()
                            .get();

            for (var document :
                    snapshot.getDocuments()) {

                Product product =
                        document.toObject(
                                Product.class
                        );

                if (product != null) {

                    products.add(product);
                }
            }

        } catch (Exception e) {

            // Return empty/loaded list
        }

        return products;
    }

    // =====================================================
    // GET SINGLE PRODUCT
    // =====================================================

    public Product getProduct(
            int productId) {

        try {

            var document =
                    db.collection("products")
                            .document(
                                    String.valueOf(
                                            productId
                                    )
                            )
                            .get()
                            .get();

            if (!document.exists()) {

                return null;
            }

            return document.toObject(
                    Product.class
            );

        } catch (Exception e) {

            return null;
        }
    }

    // =====================================================
    // SEARCH FARMER PRODUCTS
    // =====================================================

    public List<Product> searchFarmerProducts(
            int farmerId,
            String text) {

        List<Product> products =
                getFarmerProducts(
                        farmerId
                );

        if (text == null ||
                text.trim().isEmpty()) {

            return products;
        }

        String search =
                text.trim().toLowerCase();

        List<Product> result =
                new ArrayList<>();

        for (Product product :
                products) {

            boolean matches =
                    false;

            if (product.getProductName() != null &&
                    product.getProductName()
                            .toLowerCase()
                            .contains(search)) {

                matches = true;
            }

            if (!matches &&
                    product.getCategory() != null &&
                    product.getCategory()
                            .toLowerCase()
                            .contains(search)) {

                matches = true;
            }

            if (!matches &&
                    product.getDescription() != null &&
                    product.getDescription()
                            .toLowerCase()
                            .contains(search)) {

                matches = true;
            }

            if (!matches &&
                    product.getLocation() != null &&
                    product.getLocation()
                            .toLowerCase()
                            .contains(search)) {

                matches = true;
            }

            if (matches) {

                result.add(product);
            }
        }

        return result;
    }

    // =====================================================
    // DELETE
    // =====================================================

    public boolean deleteProduct(
            int productId) {

        try {

            db.collection("products")
                    .document(
                            String.valueOf(
                                    productId
                            )
                    )
                    .delete()
                    .get();

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    // =====================================================
    // UPDATE
    // =====================================================

    public boolean updateProduct(
            Product product) {

        try {

            if (product == null) {
                return false;
            }

            // -------------------------------------------------
            // GET EXISTING PRODUCT
            // -------------------------------------------------

            Product existingProduct =
                    getProduct(
                            product.getProductId()
                    );

            // -------------------------------------------------
            // PRESERVE ORIGINAL UPLOAD TIME
            // -------------------------------------------------

            if (product.getCreatedAt() == null &&
                    existingProduct != null &&
                    existingProduct.getCreatedAt() != null) {

                product.setCreatedAt(
                        existingProduct.getCreatedAt()
                );
            }

            // -------------------------------------------------
            // IF OLD PRODUCT DOES NOT HAVE TIMESTAMP
            // -------------------------------------------------

            if (product.getCreatedAt() == null) {

                product.setCreatedAt(
                        Timestamp.now()
                );
            }

            // -------------------------------------------------
            // UPDATE PRODUCT
            // -------------------------------------------------

            db.collection("products")
                    .document(
                            String.valueOf(
                                    product.getProductId()
                            )
                    )
                    .set(product)
                    .get();

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}