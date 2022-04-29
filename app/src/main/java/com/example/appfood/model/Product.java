package com.example.appfood.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String title;
    private double price;
    private String image;
    private String type;
    private double discount;
    private String description;
    private boolean isCheck;

    public Product(String id, String title, double price, String image, String type, double discount, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.type = type;
        this.discount = discount;
        this.description = description;
        this.isCheck = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}

