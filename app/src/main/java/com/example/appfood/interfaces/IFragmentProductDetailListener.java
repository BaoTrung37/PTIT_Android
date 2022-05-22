package com.example.appfood.interfaces;

import com.example.appfood.model.Product;

public interface IFragmentProductDetailListener {
    void onClick(int quantity);
    void onClickProduct(Product product);
}
