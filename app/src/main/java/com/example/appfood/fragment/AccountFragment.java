package com.example.appfood.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appfood.MainActivity;
import com.example.appfood.R;
import com.example.appfood.SignInActivity;
import com.example.appfood.database.Database;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment implements View.OnClickListener {
    CircleImageView circleInformation;
    LinearLayout tvLogout,tvCustomerService;
    TextView tvUsername;

    String username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
        setData();
    }

    private void setData() {
        username = Database.user.getDisplayName().isEmpty() ? "User124124": Database.user.getDisplayName();
        tvUsername.setText(username);
    }

    private void initData(View view) {
        // find by id
        circleInformation = view.findViewById(R.id.circle_information);
        tvCustomerService = view.findViewById(R.id.linear_customer_service);
        tvLogout = view.findViewById(R.id.linear_more_logout);
        tvUsername = view.findViewById(R.id.tv_username);
        // set event
        circleInformation.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
        tvCustomerService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle_information:
                UpdateInformationFragment updateInformationFragment = new UpdateInformationFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.framelayout, updateInformationFragment)
                        .addToBackStack("updateInformationFragment").commit();
                break;
            case R.id.linear_customer_service:
                ChatMessageFragment chatMessageFragment = new ChatMessageFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.framelayout, chatMessageFragment)
                        .addToBackStack("chatMessageFragment").commit();
                break;
            case R.id.linear_more_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
                break;
        }
    }
}
