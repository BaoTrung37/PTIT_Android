package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;
import com.example.appfood.presenter.FragmentPaymentPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class AddressFragment extends Fragment implements View.OnClickListener{

    TextInputEditText textInputEditTextAddress;
    Button btConfirm;

    //
    PaymentFragment paymentPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
        setToolBar(view);
    }

    private void initData(View view) {
        //
        textInputEditTextAddress = view.findViewById(R.id.textInputEditText_address);
        btConfirm = view.findViewById(R.id.bt_confirm);
        //

        // event
        btConfirm.setOnClickListener(this);
    }

    private void setToolBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }

    public void setPaymentPresenter(PaymentFragment paymentPresenter) {
        this.paymentPresenter = paymentPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_confirm:
                getParentFragmentManager().popBackStack();
                paymentPresenter.setAddress(textInputEditTextAddress.getText().toString());
                break;
        }
    }

}
