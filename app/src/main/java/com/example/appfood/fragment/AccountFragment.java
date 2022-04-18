package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appfood.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment implements View.OnClickListener {
    CircleImageView circleInformation;

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
    }

    private void initData(View view) {
        // find by id
        circleInformation = view.findViewById(R.id.circle_information);
        // set event
        circleInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle_information:
                UpdateInformationFragment updateInformationFragment = new UpdateInformationFragment();
                getFragmentManager().beginTransaction().replace(R.id.framelayout, updateInformationFragment)
                        .addToBackStack("updateInformationFragment").commit();
                break;
        }
    }
}
