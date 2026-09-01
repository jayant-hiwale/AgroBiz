// package com.pravartak.model.farmer_model;

// public class Product {

//     private int productId;
//     private int farmerId;
//     private String productName;
//     private String category;
//     private String description;
//     private double price;
//     private String unit;
//     private double quantity;
//     private String location;
//     private String imagePath;
//     private String status;
//     private int orders;

//     // public Product() {}

//     public Product(
//             int productId,
//             int farmerId,
//             String productName,
//             String category,
//             String description,
//             double price,
//             String unit,
//             double quantity,
//             String location,
//             String imagePath) {

//         this.productId = productId;
//         this.farmerId = farmerId;
//         this.productName = productName;
//         this.category = category;
//         this.description = description;
//         this.price = price;
//         this.unit = unit;
//         this.quantity = quantity;
//         this.location = location;
//         this.imagePath = imagePath;
//         this.status = status;
//         this.orders = orders;
//     }

//     public int getProductId() {
//         return productId;
//     }

//     public void setProductId(int productId) {
//         this.productId = productId;
//     }

//     public int getFarmerId() {
//         return farmerId;
//     }

//     public void setFarmerId(int farmerId) {
//         this.farmerId = farmerId;
//     }

//     public String getProductName() {
//         return productName;
//     }

//     public void setProductName(String productName) {
//         this.productName = productName;
//     }

//     public String getCategory() {
//         return category;
//     }

//     public void setCategory(String category) {
//         this.category = category;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }

//     public String getUnit() {
//         return unit;
//     }

//     public void setUnit(String unit) {
//         this.unit = unit;
//     }

//     public double getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(double quantity) {
//         this.quantity = quantity;
//     }

//     public String getLocation() {
//         return location;
//     }

//     public void setLocation(String location) {
//         this.location = location;
//     }

//     public String getImagePath() {
//         return imagePath;
//     }

//     public void setImagePath(String imagePath) {
//         this.imagePath = imagePath;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public int getOrders() {
//         return orders;
//     }

//     public void setOrders(int orders) {
//         this.orders = orders;
//     }
// }
package com.pravartak.model.farmer_model;


import java.util.HashMap;
import java.util.Map;

public class Product {

    private int productId;
    private int farmerId;

    private String productName;
    private String category;
    private String description;

    private double price;
    private String unit;
    private double quantity;

    private String location;
    private String imagePath;

    private String status;
    private int orders;

    // =====================================================
    // EMPTY CONSTRUCTOR
    // Required by Firestore
    // =====================================================

    public Product() {
    }

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Product(
            int productId,
            int farmerId,
            String productName,
            String category,
            String description,
            double price,
            String unit,
            double quantity,
            String location,
            String imagePath) {

        this.productId = productId;
        this.farmerId = farmerId;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.location = location;
        this.imagePath = imagePath;

        this.status = "Active";
        this.orders = 0;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("productId", productId);
        map.put("farmerId", farmerId);
        map.put("productName", productName);
        map.put("category", category);
        map.put("description", description);
        map.put("price", price);
        map.put("unit", unit);
        map.put("quantity", quantity);
        map.put("location", location);
        map.put("imagePath", imagePath);
        map.put("status", status);
        map.put("orders", orders);

        return map;
    }
}