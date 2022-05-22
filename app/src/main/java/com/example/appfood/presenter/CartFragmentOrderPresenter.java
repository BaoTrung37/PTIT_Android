package com.example.appfood.presenter;

import com.example.appfood.interfaces.IOrderDetailListener;
import com.example.appfood.model.Order;

public class CartFragmentOrderPresenter {
    private IOrderDetailListener iOrderDetailListener;

    public void setIOrderDetailListener(IOrderDetailListener iOrderDetailListener) {
        this.iOrderDetailListener = iOrderDetailListener;
    }
    public void onClickOrder(Order order){
        iOrderDetailListener.onClick(order);
    }
}
