package com.example.appfood.model;

import java.io.Serializable;

public class Chat implements Serializable {
    private String id;
    private String userSend;
    private String message;

    public Chat() {
    }

    public Chat(String id, String userSend, String message) {
        this.id = id;
        this.userSend = userSend;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public String getUserSend() {
        return userSend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
