package com.example.appfood.presenter;

import com.example.appfood.interfaces.IFragmentHomeListener;

public class FragmentHomePresenter {
    private IFragmentHomeListener iFragmentHomeListener;

    public FragmentHomePresenter(IFragmentHomeListener iFragmentHomeListener) {
        this.iFragmentHomeListener = iFragmentHomeListener;
    }
    public void onCLickProduct(String productId){
        iFragmentHomeListener.onCLickProduct(productId);
    }

    public void onCLickCategory(String categoryId){
        iFragmentHomeListener.onClickCategory(categoryId);
    }
}
