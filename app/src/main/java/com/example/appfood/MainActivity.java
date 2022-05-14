package com.example.appfood;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appfood.fragment.AccountFragment;
import com.example.appfood.fragment.CartFragment;
import com.example.appfood.fragment.HomeFragment;
import com.example.appfood.fragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inItData();
    }

    private void inItData() {
        //
        navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnItemSelectedListener(this);
        // start with screen home
        getFragment(new HomeFragment());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragmentSelected;
        switch (item.getItemId()) {
            case R.id.cart:
                fragmentSelected = new CartFragment();
                break;
            case R.id.notification:
                fragmentSelected = new NotificationFragment();
                break;
            case R.id.account:
                fragmentSelected = new AccountFragment();
                break;
            default:
                fragmentSelected = new HomeFragment();
        }
        getFragment(fragmentSelected);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
    }
}