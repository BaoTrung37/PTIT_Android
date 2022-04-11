package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.HomeProductDetailFragmentListProductAdapter;
import com.example.appfood.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeProductDetailFragment extends Fragment {

    ImageView imgImage;
    TextView tvName, tvPrice, tvDescription, tvRate, tvAmount;
    RecyclerView recyclerProductList;

    HomeProductDetailFragmentListProductAdapter homeProductDetailFragmentListProductAdapter;

    List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_product_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(view);
        fakeData();
    }

    private void initData(View view) {
        imgImage = view.findViewById(R.id.img_image);
        tvName = view.findViewById(R.id.tv_name);
        tvAmount = view.findViewById(R.id.tv_amount);
        tvDescription = view.findViewById(R.id.tv_description);
        tvName = view.findViewById(R.id.tv_name);
        tvPrice = view.findViewById(R.id.tv_price);
        tvRate = view.findViewById(R.id.tv_rating);
        recyclerProductList = view.findViewById(R.id.recycler_product_list);
        //
        productList = new ArrayList<>();
        //
        homeProductDetailFragmentListProductAdapter = new HomeProductDetailFragmentListProductAdapter(productList);

        //
        RecyclerView.LayoutManager layoutManagerProduct = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerProductList.setLayoutManager(layoutManagerProduct);
        recyclerProductList.setAdapter(homeProductDetailFragmentListProductAdapter);
    }

    private void fakeData() {
        productList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("2", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
    }
}
