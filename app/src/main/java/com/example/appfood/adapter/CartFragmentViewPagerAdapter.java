package com.example.appfood.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appfood.fragment.CartCartFragment;
import com.example.appfood.fragment.CartOrdersCanceledFragment;
import com.example.appfood.fragment.CartOrdersCompletedFragment;
import com.example.appfood.fragment.CartOrdersDeliveringFragment;
import com.example.appfood.fragment.CartOrdersPendingFragment;

public class CartFragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numPage = 5;

    public CartFragmentViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CartCartFragment();
            case 1:
                return new CartOrdersPendingFragment();
            case 2:
                return new CartOrdersDeliveringFragment();
            case 3:
                return new CartOrdersCompletedFragment();
            case 4:
                return new CartOrdersCanceledFragment();
            default:
                return new CartCartFragment();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Giỏ hàng";
            case 1:
                return "Chờ xác nhận";
            case 2:
                return "Đang giao";
            case 3:
                return "Đã hoàn thành";
            case 4:
                return "Đã huỷ";
            default:
                return "Giỏ hàng";
        }
    }
}
