package com.example.appfood.presenter;

import com.example.appfood.interfaces.IFragmentHomeListener;

public class FragmentHomePresenter {
    private IFragmentHomeListener iFragmentHomeListener;

    public FragmentHomePresenter(IFragmentHomeListener iFragmentHomeListener) {
        this.iFragmentHomeListener = iFragmentHomeListener;
    }
    public void onCLickProduct(String id){
        iFragmentHomeListener.onCLick();
    }
}
