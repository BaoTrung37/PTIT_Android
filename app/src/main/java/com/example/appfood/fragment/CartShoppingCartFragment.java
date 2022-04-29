package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.CartShoppingCartListAdapter;
import com.example.appfood.interfaces.IFragmentCartShoppingCartListener;
import com.example.appfood.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartShoppingCartFragment extends Fragment implements View.OnClickListener, IFragmentCartShoppingCartListener{

    RecyclerView recyclerShoppingCartList;
    CheckBox cbCheckAll;
    Button btnOrder;
    TextView tvTotalPrice;

    CartShoppingCartListAdapter cartShoppingCartListAdapter;
    List<Product> cartProductList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_shopping_cart, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
    }

    private void initData(View view) {
        //
        cartProductList = new ArrayList<>();
        //
        fakeData();
        //find by id
        recyclerShoppingCartList = view.findViewById(R.id.recycler_shopping_cart_list);
        cbCheckAll = view.findViewById(R.id.cb_checkall);
        btnOrder = view.findViewById(R.id.btn_order);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        // adapter
        cartShoppingCartListAdapter = new CartShoppingCartListAdapter(cartProductList);
        cartShoppingCartListAdapter.setIFragmentCartShoppingCartListener(this);
        RecyclerView.LayoutManager cartShoppingCartListLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerShoppingCartList.setAdapter(cartShoppingCartListAdapter);
        recyclerShoppingCartList.setLayoutManager(cartShoppingCartListLayoutManager);

        // set event
        btnOrder.setOnClickListener(this);
        checkAll();
        //
        tvTotalPrice.setText(cartShoppingCartListAdapter.getTotalPrice() + " đ");
    }

    private void checkAll() {
        cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cartShoppingCartListAdapter.setCheckAll(b);
                cbCheckAll.setChecked(b);
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_order:
                PaymentFragment paymentFragment = new PaymentFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, paymentFragment)
                        .addToBackStack("paymentFragment").commit();
                break;

        }
    }

    private void fakeData() {
        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
//        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
//        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
//        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
//        cartProductList.add(new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"));
    }


    @Override
    public void setCheckedAll(boolean isCheck) {
        cbCheckAll.setChecked(isCheck);
    }

    @Override
    public void setTotalPrice(double totalPrice) {
        tvTotalPrice.setText(totalPrice + " đ");
    }
}
