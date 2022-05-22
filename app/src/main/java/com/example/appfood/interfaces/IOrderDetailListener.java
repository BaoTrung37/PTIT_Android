package com.example.appfood.interfaces;

import com.example.appfood.model.Order;

public interface IOrderDetailListener {
    void onClick(Order order);
    void onChangeStatus(String id,int status);
}
