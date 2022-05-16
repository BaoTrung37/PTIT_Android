package com.example.appfood.presenter;

import com.example.appfood.interfaces.IOnClickItem;

public class FragmentSearchPresenter {
    private IOnClickItem iOnClickItem;

    public FragmentSearchPresenter(IOnClickItem iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
    }
    public void onCLickCategory(String categoryId){
        iOnClickItem.onClick(categoryId);
    }
}
