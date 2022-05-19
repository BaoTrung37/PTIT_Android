package com.example.appfood.presenter;

import com.example.appfood.interfaces.IOnClickItem;

public class FragmentSearchPresenter {
    private IOnClickItem iOnClickItem;

    public FragmentSearchPresenter(IOnClickItem iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
    }
    public void onCLickCategory(String categoryId){
        iOnClickItem.onClickCategory(categoryId);
    }
    public void onClickProduct(String productId) {
        iOnClickItem.onClickProduct(productId);
    }
}
