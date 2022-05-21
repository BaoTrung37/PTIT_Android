package com.example.appfood.fragment;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.HomeSearchFragmentCategoryAdapter;
import com.example.appfood.adapter.HomeSearchFragmentProductAdapter;
import com.example.appfood.interfaces.IOnClickItem;
import com.example.appfood.model.Category;
import com.example.appfood.model.Product;
import com.example.appfood.presenter.FragmentSearchPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchProductFragment extends Fragment implements IOnClickItem {

    List<Category> categoryList;
    List<Product> productList;
    RecyclerView recCategoryList;
    RecyclerView recProductList;
    SearchView searchView;
    TextView tvQuantity;

    FragmentSearchPresenter fragmentSearchPresenter;
    HomeSearchFragmentCategoryAdapter homeSearchFragmentCategoryAdapter;
    HomeSearchFragmentProductAdapter homeSearchFragmentProductAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String categoryId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_product_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolBar(view);
        initId(view);
        setData();
        initData();
        searchView();
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


    private void initData() {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        db.collection("category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Category> categories = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Category category = new Category();
                        category.setId(document.getId());
                        category.setTitle((String) document.get("title"));
                        category.setImageUrl((String) document.get("imageUrl"));
                        categories.add(category);
                    }
                    progress.dismiss();
                    homeSearchFragmentCategoryAdapter.setList(categories);
                } else {

                }

            }
        });
        categoryId = getArguments().getString("categoryId");
        getProductWithId(categoryId);
    }

    private void getProductWithId(String categoryId) {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        db.collection("product")
                .whereEqualTo("categoryId", categoryId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Product> products = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = new Product();
                                product.setId(document.getId());
                                product.setDescription((String) document.get("description"));
                                product.setName((String) document.get("name"));
                                product.setPrice((document.getDouble("price")));
                                product.setCategory((String) document.get("categoryId"));
                                product.setDiscount(document.getDouble("discount"));
                                product.setImage((String) document.get("image"));
                                products.add(product);
                            }
                            progress.dismiss();
                            tvQuantity.setText("Sản phẩm : (" + products.size() + " kết quả)");
                            homeSearchFragmentProductAdapter.setList(products);
                        } else {

                        }

                    }
                });
    }

    private void setData() {
        RecyclerView.LayoutManager categoryLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        recCategoryList.setLayoutManager(categoryLayoutManager);
        recCategoryList.setAdapter(homeSearchFragmentCategoryAdapter);

        RecyclerView.LayoutManager productLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recProductList.setLayoutManager(productLayoutManager);
        recProductList.setAdapter(homeSearchFragmentProductAdapter);
    }

    private void initId(View view) {
        tvQuantity = view.findViewById(R.id.tv_quantity);
        recCategoryList = view.findViewById(R.id.rec_category);
        recProductList = view.findViewById(R.id.rec_product);
        searchView = view.findViewById(R.id.search_view);
        categoryList = new ArrayList<>();
        productList = new ArrayList<>();
        fragmentSearchPresenter = new FragmentSearchPresenter(this);
        homeSearchFragmentCategoryAdapter = new HomeSearchFragmentCategoryAdapter(categoryList);
        homeSearchFragmentCategoryAdapter.setFragmentSearchPresenter(fragmentSearchPresenter);

        homeSearchFragmentProductAdapter = new HomeSearchFragmentProductAdapter(productList);
        homeSearchFragmentProductAdapter.setFragmentSearchPresenter(fragmentSearchPresenter);
    }

    private void searchView() {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService (Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                homeSearchFragmentProductAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homeSearchFragmentProductAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClickCategory(String id) {
        getProductWithId(id);
    }

    @Override
    public void onClickProduct(String id) {
        HomeProductDetailFragment homeProductDetailFragment = new HomeProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("productId", id);
        homeProductDetailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.framelayout, homeProductDetailFragment)
                .addToBackStack("homeProductDetailFragment").commit();
    }
}
