package com.example.appfood.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;

public class UpdateInformationFragment extends Fragment implements View.OnClickListener {
    LinearLayout linearName, linearSex, linearEmail, linearPhone, linearDateOfBirth, linearChangePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_information, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);

    }

    private void initData(View view) {
        // find by id
        linearName = view.findViewById(R.id.linear_name);
        linearPhone = view.findViewById(R.id.linear_phone);
        linearSex = view.findViewById(R.id.linear_sex);
        linearEmail = view.findViewById(R.id.linear_email);
        linearChangePassword = view.findViewById(R.id.linear_change_password);
        linearDateOfBirth = view.findViewById(R.id.linear_date_of_birth);
        // set event
        linearName.setOnClickListener(this);
        linearPhone.setOnClickListener(this);
        linearSex.setOnClickListener(this);
        linearEmail.setOnClickListener(this);
        linearChangePassword.setOnClickListener(this);
        linearDateOfBirth.setOnClickListener(this);
        //

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_name:
                UpdateNameFragment updateNameFragment = new UpdateNameFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateNameFragment)
                        .addToBackStack("updateNameFragment").commit();
                break;
            case R.id.linear_phone:
                Log.d("phone","aa");
                Toast.makeText(getActivity().getBaseContext()
                        ,"Không thể thay đổi số điện thoại",Toast.LENGTH_LONG).show();
                break;
            case R.id.linear_sex:
                UpdateSexFragment updateSexFragment = new UpdateSexFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateSexFragment)
                        .addToBackStack("updateSexFragment").commit();
                break;
            case R.id.linear_email:
                UpdateEmailFragment updateEmailFragment = new UpdateEmailFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateEmailFragment)
                        .addToBackStack("updateEmailFragment").commit();
                break;
            case R.id.linear_change_password:
                UpdatePasswordFragment updatePasswordFragment = new UpdatePasswordFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updatePasswordFragment)
                        .addToBackStack("updatePasswordFragment").commit();
                break;
            case R.id.linear_date_of_birth:
                UpdateDateOfBirthFragment updateDateOfBirthFragment = new UpdateDateOfBirthFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateDateOfBirthFragment)
                        .addToBackStack("updateDateOfBirthFragment").commit();
                break;
        }
    }
}
