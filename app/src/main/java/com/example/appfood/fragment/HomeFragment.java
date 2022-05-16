package com.example.appfood.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

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
import com.example.appfood.adapter.HomeFragmentListProductAdapter;
import com.example.appfood.interfaces.IFragmentHomeListener;
import com.example.appfood.model.Category;
import com.example.appfood.model.Product;
import com.example.appfood.presenter.FragmentHomePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements IFragmentHomeListener {

    private static final String TAG = HomeFragment.class.getName();

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
        fakeData();
        inItData(view);
        hienQuangCao(view);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void hienQuangCao(View view) {
        ViewFlipper viewFlipper;
        viewFlipper = view.findViewById(R.id.viewFlipper);

        for(String image: imageQCList){
            ImageView imageView = new ImageView(getContext());
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

    private void inItData(View view) {
        // find by id
        recyclerListCategory = view.findViewById(R.id.recycler_category);
        recyclerListFlashSale = view.findViewById(R.id.recycler_flash_sale);
        recyclerListFavorite = view.findViewById(R.id.recycler_favorite);
        recyclerListProduct = view.findViewById(R.id.recycler_product);
        //
        fragmentHomePresenter = new FragmentHomePresenter(this);
        //
        homeFragmentListCategoryAdapter = new HomeFragmentListCategoryAdapter(categoryList);
        homeFragmentListFlashSaleAdapter = new HomeFragmentListFlashSaleAdapter(flashsaleProductList);
        homeFragmentListFavoriteAdapter = new HomeFragmentListFavoriteAdapter(favoriteProductList);
        homeFragmentListProductAdapter = new HomeFragmentListProductAdapter(productList, getContext());
        // xét presenter
        homeFragmentListFlashSaleAdapter.setFragmentHomePresenter(fragmentHomePresenter);
        homeFragmentListProductAdapter.setFragmentHomePresenter(fragmentHomePresenter);
        homeFragmentListCategoryAdapter.setFragmentHomePresenter(fragmentHomePresenter);
        //
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerFlashSale = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManagerFavorite = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManagerProduct = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

        recyclerListCategory.setLayoutManager(gridLayoutManager);
        recyclerListCategory.setAdapter(homeFragmentListCategoryAdapter);

        recyclerListFlashSale.setLayoutManager(layoutManagerFlashSale);
        recyclerListFlashSale.setAdapter(homeFragmentListFlashSaleAdapter);

        recyclerListFavorite.setLayoutManager(layoutManagerFavorite);
        recyclerListFavorite.setAdapter(homeFragmentListFavoriteAdapter);

        recyclerListProduct.setLayoutManager(layoutManagerProduct);
        recyclerListProduct.setAdapter(homeFragmentListProductAdapter);
    }

    private void fakeData() {
        //
        categoryList = new ArrayList<>();
        flashsaleProductList = new ArrayList<>();
        favoriteProductList = new ArrayList<>();
        productList = new ArrayList<>();
        imageQCList = new ArrayList<>();

        initCategoryList();
        initFlashSaleProductList();
        initFavoriteProductList();
        initProductList();

        imageQCList.add("https://intphcm.com/data/upload/poster-do-an.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-do-an-dong-gia.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-tra-sua-doc-dao.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-do-an-nhanh.jpg");
        imageQCList.add("https://intphcm.com/data/upload/poster-tra-sua-gongcha.jpg");
    }

    private void initCategoryList() {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        db.collection("category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Category category = new Category();
                        category.setId(document.getId());
                        category.setTitle((String) document.get("title"));
                        category.setImageUrl((String) document.get("imageUrl"));
                        categoryList.add(category);
                        Log.d("test", "test");
                        homeFragmentListCategoryAdapter.notifyDataSetChanged();
                    }
                    progress.dismiss();
                } else {
                }
            }
        });
    }

    private void initProductList() {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        db.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Product product = new Product();
                        product.setId(document.getId());
                        product.setDescription((String) document.get("description"));
                        product.setName((String) document.get("name"));
                        product.setPrice(document.getDouble("price"));
                        product.setDiscount(document.getDouble("discount"));
                        product.setImage((String) document.get("image"));
                        product.setCategory((String) document.get("categoryId"));
                        productList.add(product);
                    }
                    Collections.shuffle(productList);
                    homeFragmentListProductAdapter.notifyDataSetChanged();
                    progress.dismiss();
                } else {
                }
            }
        });
    }

    private void initFlashSaleProductList() {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        db.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Product product = new Product();
                        product.setId(document.getId());
                        product.setDescription((String) document.get("description"));
                        product.setName((String) document.get("name"));
                        product.setPrice(document.getDouble("price"));
                        product.setDiscount(document.getDouble("discount"));
                        product.setImage((String) document.get("image"));
                        product.setCategory((String) document.get("categoryId"));
                        flashsaleProductList.add(product);
                    }
                    Collections.shuffle(flashsaleProductList);
                    homeFragmentListFlashSaleAdapter.notifyDataSetChanged();
                    progress.dismiss();
                } else {
                }
            }
        });
    }

    private void initFavoriteProductList() {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        db.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Product product = new Product();
                        product.setId(document.getId());
                        product.setDescription((String) document.get("description"));
                        product.setName((String) document.get("name"));
                        product.setPrice(document.getDouble("price"));
                        product.setDiscount(document.getDouble("discount"));
                        product.setImage((String) document.get("image"));
                        product.setCategory((String) document.get("categoryId"));
                        favoriteProductList.add(product);
                    }
                    Collections.shuffle(favoriteProductList);
                    homeFragmentListFavoriteAdapter.notifyDataSetChanged();
                    progress.dismiss();
                } else {
                }
            }
        });
    }

    @Override
    public void onCLickProduct(String productId) {
        HomeProductDetailFragment homeProductDetailFragment = new HomeProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("productId", productId);
        homeProductDetailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout, homeProductDetailFragment)
                .addToBackStack("homeProductDetailFragment").commit();
    }

    @Override
    public void onClickCategory(String categoryId) {
        SearchProductFragment searchProductFragment = new SearchProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("categoryId", categoryId);
        searchProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout, searchProductFragment)
                .addToBackStack("homeProductDetailFragment").commit();
    }


}
