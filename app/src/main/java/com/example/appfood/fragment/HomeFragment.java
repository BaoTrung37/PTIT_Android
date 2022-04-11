package com.example.appfood.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.HomeFragmentListCategoryAdapter;
import com.example.appfood.adapter.HomeFragmentListFavoriteAdapter;
import com.example.appfood.adapter.HomeFragmentListFlashSaleAdapter;
import com.example.appfood.adapter.HomeFragmentListProductAdapter;
import com.example.appfood.interfaces.IFragmentHome;
import com.example.appfood.model.Category;
import com.example.appfood.model.Product;
import com.example.appfood.presenter.FragmentHomePresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements IFragmentHome {
    ViewFlipper viewFlipper;
    //widget
    RecyclerView recyclerListCategory;
    RecyclerView recyclerListFlashSale;
    RecyclerView recyclerListFavorite;
    RecyclerView recyclerListProduct;
    // adapter
    HomeFragmentListCategoryAdapter homeFragmentListCategoryAdapter;
    HomeFragmentListFlashSaleAdapter homeFragmentListFlashSaleAdapter;
    HomeFragmentListFavoriteAdapter homeFragmentListFavoriteAdapter;
    HomeFragmentListProductAdapter homeFragmentListProductAdapter;
    // list
    List<Category> categoryList;
    List<Product> flashsaleProductList;
    List<Product> favoriteProductList;
    List<Product> productList;
    List<String> imageQCList;
    //presenter
    FragmentHomePresenter fragmentHomePresenter;
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
        inItToolBar(view);
        inItData(view);
        fakeData();
//        test();
        hienQuangCao();
    }

    private void inItToolBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolBar);
//
//
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setIcon(R.drawable.ic_baseline_arrow_back_24);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

//        if(((AppCompatActivity) getActivity()).getSupportActionBar() != null){
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
    }

    private void hienQuangCao() {
        for(String image: imageQCList){
            ImageView imageView = new ImageView(getContext());
            TextView textView = new TextView(getContext());
            textView.setText("hhahaha");
            Picasso.get().load(image).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.advertisement_slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.advertisement_slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
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
        flashsaleProductList = new ArrayList<>();
        favoriteProductList = new ArrayList<>();
        productList = new ArrayList<>();
        // find by id
        recyclerListCategory = view.findViewById(R.id.recycler_category);
        recyclerListFlashSale = view.findViewById(R.id.recycler_flash_sale);
        recyclerListFavorite = view.findViewById(R.id.recycler_favorite);
        recyclerListProduct = view.findViewById(R.id.recycler_product);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        //
        fragmentHomePresenter = new FragmentHomePresenter(this);
        //
        homeFragmentListCategoryAdapter = new HomeFragmentListCategoryAdapter(categoryList);
        homeFragmentListFlashSaleAdapter = new HomeFragmentListFlashSaleAdapter(flashsaleProductList);
        homeFragmentListFavoriteAdapter = new HomeFragmentListFavoriteAdapter(favoriteProductList);
        homeFragmentListProductAdapter = new HomeFragmentListProductAdapter(productList);
        imageQCList = new ArrayList<>();
        // xét presenter
        homeFragmentListProductAdapter.setFragmentHomePresenter(fragmentHomePresenter);
        //
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerFlashsale = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManagerFavorite = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManagerProduct = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerListCategory.setLayoutManager(gridLayoutManager);
        recyclerListCategory.setAdapter(homeFragmentListCategoryAdapter);

        recyclerListFlashSale.setLayoutManager(layoutManagerFlashsale);
        recyclerListFlashSale.setAdapter(homeFragmentListFlashSaleAdapter);

        recyclerListFavorite.setLayoutManager(layoutManagerFavorite);
        recyclerListFavorite.setAdapter(homeFragmentListFavoriteAdapter);

        recyclerListProduct.setLayoutManager(layoutManagerProduct);
        recyclerListProduct.setAdapter(homeFragmentListProductAdapter);
    }

    private void fakeData() {
        categoryList.add(new Category("1", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "Banh kem"));
        categoryList.add(new Category("2", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "hehe"));
        categoryList.add(new Category("3", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "hehe"));
        categoryList.add(new Category("4", "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "hehe"));

        flashsaleProductList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        flashsaleProductList.add(new Product("2", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        flashsaleProductList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));

        favoriteProductList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        favoriteProductList.add(new Product("2", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        favoriteProductList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));

        productList.add(new Product("1", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("2", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));
        productList.add(new Product("3", "Ga chien xao sa ơt",
                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "đíaádsda"));

        imageQCList.add("https://intphcm.com/data/upload/poster-do-an.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-do-an-dong-gia.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-tra-sua-doc-dao.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-do-an-nhanh.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-tra-sua-gongcha.jpg");
    }

    @Override
    public void onCLick() {
        HomeProductDetailFragment homeProductDetailFragment = new HomeProductDetailFragment();
        getFragmentManager().beginTransaction().replace(R.id.framelayout, homeProductDetailFragment).addToBackStack("homeProductDetailFragment").commit();
    }
}
