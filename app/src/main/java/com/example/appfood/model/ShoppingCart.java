package com.example.appfood.model;

import java.io.Serializable;
import java.util.List;

public class ShoppingCart implements Serializable {
    private String id;
    private List<ProductItem> productItems;

    public ShoppingCart() {
    }

    public ShoppingCart(String id, List<ProductItem> productItems) {
        this.id = id;
        this.productItems = productItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }
}
