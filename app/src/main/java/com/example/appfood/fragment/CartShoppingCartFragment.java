package com.example.appfood.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.CartShoppingCartListAdapter;

public class CartShoppingCartFragment extends Fragment implements View.OnClickListener{

    RecyclerView recyclerShoppingCartList;
    CheckBox cbCheck;
    Button btnOrder;

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
//        Log.d("onViewCreated0","onViewCreated0");
        initData(view);
    }

    private void initData(View view) {
        //find by id
        recyclerShoppingCartList = view.findViewById(R.id.recycler_shopping_cart_list);
        cbCheck = view.findViewById(R.id.cb_check);
        btnOrder = view.findViewById(R.id.btn_order);
        // adapter
        cartShoppingCartListAdapter = new CartShoppingCartListAdapter();
        RecyclerView.LayoutManager cartShoppingCartListLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerShoppingCartList.setAdapter(cartShoppingCartListAdapter);
        recyclerShoppingCartList.setLayoutManager(cartShoppingCartListLayoutManager);

        // set event
        btnOrder.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cb_check:
                break;
            case R.id.btn_order:
                PaymentFragment paymentFragment = new PaymentFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,paymentFragment)
                        .addToBackStack("paymentFragment").commit();
                break;

        }
    }

    private void checkAll(){

    }
}
