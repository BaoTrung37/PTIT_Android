package com.example.appfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appfood.R;
import com.example.appfood.adapter.CartFragmentViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class CartFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    CartFragmentViewPagerAdapter cartFragmentViewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inIt(view);
        setLayout();
    }

    private void setLayout() {
        viewPager.setAdapter(cartFragmentViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void inIt(View view) {
        tabLayout= view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        cartFragmentViewPagerAdapter = new CartFragmentViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }
}
