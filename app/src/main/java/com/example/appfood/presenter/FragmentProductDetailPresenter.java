package com.example.appfood.presenter;

import com.example.appfood.interfaces.IFragmentProductDetailListener;
import com.example.appfood.model.Product;

public class FragmentProductDetailPresenter {
    private IFragmentProductDetailListener iFragmentProductDetailListener;

    public FragmentProductDetailPresenter(IFragmentProductDetailListener iFragmentProductDetailListener) {
        this.iFragmentProductDetailListener = iFragmentProductDetailListener;
    }
    public void onClickPlus(int quantity){
        iFragmentProductDetailListener.onClick(quantity + 1);
    }
    public void onClickMinus(int quantity){
        iFragmentProductDetailListener.onClick(quantity - 1 >= 0 ? quantity - 1 : 0);
    }
    public void onClickProduct(Product product){
        iFragmentProductDetailListener.onClickProduct(product);
    }
}
