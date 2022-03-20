package com.example.appfood.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.HomeFragmentListCategoryAdapter;
import com.example.appfood.adapter.HomeFragmentListFavoriteAdapter;
import com.example.appfood.adapter.HomeFragmentListFlashSaleAdapter;
import com.example.appfood.model.Category;
import com.example.appfood.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    //widget
    RecyclerView recyclerListCategory;
    RecyclerView recyclerListFlashSale;
    RecyclerView recyclerListFavorite;
    // adapter
    HomeFragmentListCategoryAdapter homeFragmentListCategoryAdapter;
    HomeFragmentListFlashSaleAdapter homeFragmentListFlashSaleAdapter;
    HomeFragmentListFavoriteAdapter homeFragmentListFavoriteAdapter;
    // list
    List<Category> categoryList;
    List<Product> productList;
    List<Product> favoriteList;
    //Firebase
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inItData(view);
        fakeData();
//        test();
    }

    private void test() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }

    private void inItData(View view) {
        //
        categoryList = new ArrayList<>();
        productList = new ArrayList<>();
        favoriteList = new ArrayList<>();
        // find by id
        recyclerListCategory = view.findViewById(R.id.recycler_category);
        recyclerListFlashSale = view.findViewById(R.id.recycler_flash_sale);
        recyclerListFavorite = view.findViewById(R.id.recycler_favorite);

        homeFragmentListCategoryAdapter = new HomeFragmentListCategoryAdapter(categoryList);
        homeFragmentListFlashSaleAdapter = new HomeFragmentListFlashSaleAdapter(productList);
        homeFragmentListFavoriteAdapter = new HomeFragmentListFavoriteAdapter(favoriteList);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerFlashsale = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManagerFavorite = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);

        recyclerListCategory.setLayoutManager(gridLayoutManager);
        recyclerListCategory.setAdapter(homeFragmentListCategoryAdapter);

        recyclerListFlashSale.setLayoutManager(layoutManagerFlashsale);
        recyclerListFlashSale.setAdapter(homeFragmentListFlashSaleAdapter);

        recyclerListFavorite.setLayoutManager(layoutManagerFavorite);
        recyclerListFavorite.setAdapter(homeFragmentListFavoriteAdapter);
    }

    private void fakeData() {
        categoryList.add(new Category("1", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "Banh kem"));
        categoryList.add(new Category("2", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "hehe"));
        categoryList.add(new Category("3", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "hehe"));
        categoryList.add(new Category("4", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "hehe"));

        productList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("2", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));

        favoriteList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        favoriteList.add(new Product("2", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        favoriteList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));



    }
}
