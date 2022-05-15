package com.example.appfood.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String imageUrl;
    private String title;

    public Category() {
    }

    public Category(String id, String imageUrl, String title) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
