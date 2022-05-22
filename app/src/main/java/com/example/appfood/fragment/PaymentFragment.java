package com.example.appfood.fragment;

import static com.example.appfood.database.Database.db;
import static com.example.appfood.database.Database.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;
import com.example.appfood.database.Database;
import com.example.appfood.interfaces.IFragmentPaymentListener;
import com.example.appfood.model.ProductItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFragment extends Fragment implements View.OnClickListener, IFragmentPaymentListener {
    
    TextView tvAddAddress,tvAddress,tvTotalPrice, tvTotalPayment;
    EditText edtNote;
    Button btConfirm;

    List<ProductItem> productItemList = new ArrayList<>();
    double totalPrice = 0;
    double totalPayment = 0;

    //    double shipment = 200000;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        initData(view);
        setData();
        setToolBar(view);
    }

    private void setData() {
        tvTotalPrice.setText(totalPrice + " đ");
        tvTotalPayment.setText(totalPayment + " đ");
    }

    private void getData() {
        productItemList = (List<ProductItem>) getArguments().getSerializable("cart");
        totalPrice = getArguments().getDouble("totalPrice");

        totalPayment = totalPrice;
    }

    private void setToolBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack("paymentFragment",1);
            }
        });
    }

    private void initData(View view) {
        tvAddAddress = view.findViewById(R.id.tv_add_address);
        tvAddress = view.findViewById(R.id.tv_address);
        btConfirm = view.findViewById(R.id.bt_confirm);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        tvTotalPayment = view.findViewById(R.id.tv_total_payment);
        edtNote = view.findViewById(R.id.edt_note);
        // event
        tvAddAddress.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_add_address:
                AddressFragment addressFragment = new AddressFragment();
                addressFragment.setPaymentPresenter(this);
                getParentFragmentManager().beginTransaction()
                        .add(R.id.framelayout,addressFragment)
                        .addToBackStack("addressFragment")
                        .commit();
                break;
            case R.id.bt_confirm:
                doOrder();
                break;
        }
    }

    private void doOrder() {

        Map<String, Object> data = new HashMap<>();
        data.put("productOrder", productItemList);
        data.put("note", edtNote.getText().toString().trim());
        data.put("totalPrice", totalPrice);
        data.put("address",tvAddress.getText());
        data.put("totalPayment", totalPayment);
        data.put("dateCreate", new Timestamp(new Date()));
        data.put("orderStatus", 0);

        db.collection("user").document(user.getUid()).collection("order").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                for (ProductItem productItem : productItemList) {
                    db.collection("cart").document(user.getUid()).collection("product").document(productItem.getProduct().getId()).delete();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Đặt hàng thành công");
                builder.setMessage("Đặt hàng thành công vui lòng kiểm tra hoá đơn");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        moveToPageOrderSpending();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }

    private void moveToPageOrderSpending() {
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void setAddress(String address) {
        tvAddress.setText(address);
    }
}
