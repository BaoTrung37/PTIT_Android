package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.ReviewFragmentReviewListAdapter;

public class ReviewFragment extends Fragment{
    Toolbar toolbar;
    LinearLayout ln_5star,ln_4star,ln_3star,ln_2star,ln_1star;
    RecyclerView recyclerReviewList;

    // List

    // Adapter
    ReviewFragmentReviewListAdapter reviewFragmentReviewListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
        setToolBar(view);
    }

    private void initData(View view) {
        // find by id
        toolbar = view.findViewById(R.id.toolBar);
        ln_5star = view.findViewById(R.id.liner_5star);
        ln_4star = view.findViewById(R.id.liner_4star);
        ln_3star = view.findViewById(R.id.liner_3star);
        ln_2star = view.findViewById(R.id.liner_2star);
        ln_1star = view.findViewById(R.id.liner_1star);
        recyclerReviewList = view.findViewById(R.id.recyclerReviews);

        // list

        // adapter
        reviewFragmentReviewListAdapter = new ReviewFragmentReviewListAdapter();
        RecyclerView.LayoutManager reviewListLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerReviewList.setAdapter(reviewFragmentReviewListAdapter);
        recyclerReviewList.setLayoutManager(reviewListLayoutManager);
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
