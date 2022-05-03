package com.example.appfood.presenter;

import com.example.appfood.interfaces.IFragmentPaymentListener;

public class FragmentPaymentPresenter {
    private IFragmentPaymentListener listener;

    public FragmentPaymentPresenter(IFragmentPaymentListener listener) {
        this.listener = listener;
    }
    public void setAddress(String address){
        listener.setAddress(address);
    }

}
