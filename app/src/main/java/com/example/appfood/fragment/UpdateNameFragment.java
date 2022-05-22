package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;
import com.example.appfood.presenter.UpdateInformationPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateNameFragment extends Fragment {
    UpdateInformationPresenter updateInformationPresenter;
    String username;
    TextInputEditText tedtUsername;
    Button btConfirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_name, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolBar(view);
        initData(view);
        getData();
    }

    private void getData() {
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = tedtUsername.getText().toString().trim();
                if (username.isEmpty()) {
                    Toast.makeText(getContext(),"Tên không được bỏ trống",Toast.LENGTH_SHORT).show();
                } else {
                    updateInformationPresenter.getUserName(username);
                    getParentFragmentManager().popBackStack("updateNameFragment",1);
                }
            }
        });
    }

    private void initData(View view) {
        tedtUsername = view.findViewById(R.id.tedt_username);
        btConfirm = view.findViewById(R.id.bt_confirm);
    }

    public void setUpdateInformationPresenter(UpdateInformationPresenter updateInformationPresenter) {
        this.updateInformationPresenter = updateInformationPresenter;
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
}
