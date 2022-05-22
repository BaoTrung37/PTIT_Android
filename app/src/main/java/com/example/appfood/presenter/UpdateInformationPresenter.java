package com.example.appfood.presenter;

import com.example.appfood.interfaces.IFragmentUpdateInformationListener;

public class UpdateInformationPresenter {
    private IFragmentUpdateInformationListener listener;

    public void setListener(IFragmentUpdateInformationListener listener) {
        this.listener = listener;
    }
    public void getUserName(String username){
        listener.setName(username);
    }
}
