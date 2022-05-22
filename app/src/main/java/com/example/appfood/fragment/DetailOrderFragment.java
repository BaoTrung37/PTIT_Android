package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.OrderDetailProductAdapter;
import com.example.appfood.model.Order;
import com.example.appfood.model.ProductItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class DetailOrderFragment extends Fragment {
    DecimalFormat dfVND = new DecimalFormat("###,###,###,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    OrderDetailProductAdapter orderDetailProductAdapter;

    TextView tvAddress,tvTotalPrice,tvTotalPayment,tvDateCreate,tvNote;
    RecyclerView recProductOrderList;
    List<ProductItem> productOrderList;
    Order order;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_order,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initId(view);
        initData();
        setData();
    }

    private void setData() {

        orderDetailProductAdapter = new OrderDetailProductAdapter(productOrderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recProductOrderList.setLayoutManager(layoutManager);
        recProductOrderList.setAdapter(orderDetailProductAdapter);

        tvTotalPrice.setText(dfVND.format(order.getTotalPrice()) + " đ");
        tvTotalPayment.setText(dfVND.format(order.getTotalPayment()) + " đ");
        tvNote.setText(order.getNote());
        tvDateCreate.setText("Đã tạo: " + dateFormat.format(order.getDateCreate().toDate()));
        tvAddress.setText(order.getAddress());
    }

    private void initData() {
        order = (Order) getArguments().getSerializable("order");
        productOrderList = order.getProductOrder();
    }

    private void initId(View view) {
        recProductOrderList =  view.findViewById(R.id.rec_product_order);
        tvAddress = view.findViewById(R.id.tv_address);
        tvDateCreate = view.findViewById(R.id.tv_date_create);
        tvNote = view.findViewById(R.id.tv_note);
        tvTotalPayment = view.findViewById(R.id.tv_total_payment);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        tvAddress = view.findViewById(R.id.tv_address);
    }

}
