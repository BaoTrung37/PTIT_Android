package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.CartShoppingCartListAdapter;

public class CartShoppingCartFragment extends Fragment {

    RecyclerView recyclerShoppingCartList;
    CartShoppingCartListAdapter cartShoppingCartListAdapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_shopping_cart,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
    }

    private void initData(View view) {
        //find by id
        recyclerShoppingCartList = view.findViewById(R.id.recycler_shopping_cart_list);

        // adapter
        cartShoppingCartListAdapter = new CartShoppingCartListAdapter();
        RecyclerView.LayoutManager cartShoppingCartListLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerShoppingCartList.setAdapter(cartShoppingCartListAdapter);
        recyclerShoppingCartList.setLayoutManager(cartShoppingCartListLayoutManager);
    }
}
