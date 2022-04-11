package com.example.appfood.presenter;

import com.example.appfood.interfaces.IFragmentHome;

public class FragmentHomePresenter {
    private IFragmentHome iFragmentHome;

    public FragmentHomePresenter(IFragmentHome iFragmentHome) {
        this.iFragmentHome = iFragmentHome;
    }
    public void onCLickProduct(String id){
        iFragmentHome.onCLick();
    }
}
