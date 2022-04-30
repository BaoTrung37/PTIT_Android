package com.example.appfood.model;

import java.io.Serializable;

public class ProductItem implements Serializable {
    private Product product;
    private int quantity;
    private boolean isCheck;

    public ProductItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isCheck = false;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}