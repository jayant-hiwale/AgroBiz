package com.pravartak.model.buyer_model;

import com.pravartak.model.farmer_model.Product;

public class CartItem {

    private Product product;
    private double quantity;

    public CartItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {

        if (product == null) {
            return 0;
        }

        return product.getPrice() * quantity;
    }
}