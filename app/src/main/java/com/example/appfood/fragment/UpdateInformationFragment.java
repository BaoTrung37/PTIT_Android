package com.example.appfood.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;
import com.example.appfood.database.Database;
import com.example.appfood.interfaces.IFragmentUpdateInformationListener;
import com.example.appfood.presenter.UpdateInformationPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UpdateInformationFragment extends Fragment implements View.OnClickListener , IFragmentUpdateInformationListener {
    UpdateInformationPresenter updateInformationPresenter;

    LinearLayout linearName, linearSex, linearEmail, linearPhone, linearDateOfBirth, linearChangePassword;
    TextView tvUsername,tvSave;
    String username, gender, email, dateOfBirth, password;

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
        setToolBar(view);
        setData();
    }

    private void setData() {
        username = Database.user.getDisplayName().isEmpty() ? "User124124": Database.user.getDisplayName();
        tvUsername.setText(username);
    }

    private void initData(View view) {
        // find by id
        linearName = view.findViewById(R.id.linear_name);
        linearPhone = view.findViewById(R.id.linear_phone);
        linearSex = view.findViewById(R.id.linear_sex);
        linearEmail = view.findViewById(R.id.linear_email);
        linearChangePassword = view.findViewById(R.id.linear_change_password);
        linearDateOfBirth = view.findViewById(R.id.linear_date_of_birth);
        tvUsername = view.findViewById(R.id.tv_username);
        tvSave = view.findViewById(R.id.tv_save);
        updateInformationPresenter = new UpdateInformationPresenter();
        updateInformationPresenter.setListener(this);

        // set event
        linearName.setOnClickListener(this);
        linearPhone.setOnClickListener(this);
        linearSex.setOnClickListener(this);
        linearEmail.setOnClickListener(this);
        linearChangePassword.setOnClickListener(this);
        linearDateOfBirth.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        //

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_name:
                UpdateNameFragment updateNameFragment = new UpdateNameFragment();
                updateNameFragment.setUpdateInformationPresenter(updateInformationPresenter);
                getParentFragmentManager().beginTransaction()
                        .add(R.id.framelayout,updateNameFragment)
                        .addToBackStack("updateNameFragment").commit();
                break;
            case R.id.linear_phone:
                Log.d("phone","aa");
                Toast.makeText(getActivity().getBaseContext()
                        ,"Không thể thay đổi số điện thoại",Toast.LENGTH_LONG).show();
                break;
            case R.id.linear_sex:
                UpdateSexFragment updateSexFragment = new UpdateSexFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateSexFragment)
                        .addToBackStack("updateSexFragment").commit();
                break;
            case R.id.linear_email:
                UpdateEmailFragment updateEmailFragment = new UpdateEmailFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateEmailFragment)
                        .addToBackStack("updateEmailFragment").commit();
                break;
            case R.id.linear_change_password:
                UpdatePasswordFragment updatePasswordFragment = new UpdatePasswordFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updatePasswordFragment)
                        .addToBackStack("updatePasswordFragment").commit();
                break;
            case R.id.linear_date_of_birth:
                UpdateDateOfBirthFragment updateDateOfBirthFragment = new UpdateDateOfBirthFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,updateDateOfBirthFragment)
                        .addToBackStack("updateDateOfBirthFragment").commit();
                break;
            case R.id.tv_save:
                updateProfile();
                break;
        }
    }

    private void updateProfile() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        Database.user
                .updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Thông báo cập nhật");
                            builder.setMessage("Cập nhật thành công");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    getParentFragmentManager().popBackStack();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                });

    }

    @Override
    public void setImage() {

    }

    @Override
    public void setName(String name) {
        username = name;
        tvUsername.setText(username);
    }

    @Override
    public void setGender(int pos) {

    }

    @Override
    public void setDateOfBirth(String dateOfBirth) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPhone(String phone) {

    }
}
