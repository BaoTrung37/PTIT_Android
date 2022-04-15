package com.example.appfood.model;

import java.io.Serializable;

// TODO: Fix class Review
public class Review implements Serializable {
    private String id;
    private String comment;
    private int star;
    private String imageStr;
    private String timePost;
    private User user;

    public Review(String id, String comment, int star, String imageStr, String timePost, User user) {
        this.id = id;
        this.comment = comment;
        this.star = star;
        this.imageStr = imageStr;
        this.timePost = timePost;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timeComment) {
        this.timePost = timeComment;
    }
}
