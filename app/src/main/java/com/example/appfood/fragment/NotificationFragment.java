package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.NotificationListAdapter;
import com.example.appfood.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {
    RecyclerView recNotification;
    NotificationListAdapter notificationListAdapter;
    List<Notification> notificationList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initId(view);
        getData();
    }

    private void getData() {
        notificationList = new ArrayList<>();

        notificationListAdapter = new NotificationListAdapter(notificationList);
        notificationListAdapter.inItData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recNotification.setLayoutManager(layoutManager);
        recNotification.setAdapter(notificationListAdapter);
    }

    private void initId(View view) {
        recNotification = view.findViewById(R.id.rec_notification);
    }
}
