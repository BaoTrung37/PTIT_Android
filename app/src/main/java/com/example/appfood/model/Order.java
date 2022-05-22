package com.example.appfood.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String id;
    private String username;
    private String note;
    private List<ProductItem> productOrder;
    private double totalPrice;
    private double totalPayment;
    private Timestamp dateCreate;
//    private Timestamp dateConfirm;
//    private Timestamp dateDelivered;
//    private Timestamp dateCanceled;
    private double orderStatus;

    public Order() {
    }

    public Order(String id ,String username, String note, List<ProductItem> productOrder, double totalPrice, double totalPayment, Timestamp dateCreate, int orderStatus) {
        this.id = id;
        this.username = username;
        this.note = note;
        this.productOrder = productOrder;
        this.totalPrice = totalPrice;
        this.totalPayment = totalPayment;
        this.dateCreate = dateCreate;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<ProductItem> getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(List<ProductItem> productOrder) {
        this.productOrder = productOrder;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public double getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(double orderStatus) {
        this.orderStatus = orderStatus;
    }
}
