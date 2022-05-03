package com.example.appfood.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;
import com.example.appfood.interfaces.IFragmentPaymentListener;

public class PaymentFragment extends Fragment implements View.OnClickListener, IFragmentPaymentListener {
    
    TextView tvAddAddress,tvAddress;
    Button btConfirm;

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
        initData(view);
        setToolBar(view);
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

    private void moveToPageOrderSpending() {
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void setAddress(String address) {
        tvAddress.setText(address);
    }
}
