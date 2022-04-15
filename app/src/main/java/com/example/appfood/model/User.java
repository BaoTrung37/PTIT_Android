package com.example.appfood.model;

import java.io.Serializable;

// TODO: Fix class User
public class User implements Serializable {
    private String id;
    private String name;
    private String address;
    private String numberPhone;
    private String avatarStr;

    public User(String id, String name, String address, String numberPhone, String avatarStr) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberPhone = numberPhone;
        this.avatarStr = avatarStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAvatarStr() {
        return avatarStr;
    }

    public void setAvatarStr(String avatarStr) {
        this.avatarStr = avatarStr;
    }
}
