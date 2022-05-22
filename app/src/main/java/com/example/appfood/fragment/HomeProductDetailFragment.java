package com.example.appfood.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.HomeProductDetailFragmentListProductAdapter;
import com.example.appfood.database.Database;
import com.example.appfood.interfaces.IFragmentProductDetailListener;
import com.example.appfood.model.Product;
import com.example.appfood.model.ProductItem;
import com.example.appfood.presenter.FragmentProductDetailPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeProductDetailFragment extends Fragment implements View.OnClickListener, IFragmentProductDetailListener {

    ImageView imgImage;
    TextView tvName, tvPrice, tvDescription, tvRate, tvQuantity, tvComment;
    ImageButton imgBack;
    ImageButton imbtPlus,imbtMinus;
    RecyclerView recyclerProductList;
    FloatingActionButton fabAdd;

    HomeProductDetailFragmentListProductAdapter homeProductDetailFragmentListProductAdapter;
    FragmentProductDetailPresenter fragmentProductDetailPresenter;
    List<Product> productList;
    Product productCurrent;
    int quantityCurrent = 1;

    //
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_product_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);
        onBack(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        getProductWithId();
    }

    private void initData(View view) {
        imgBack = view.findViewById(R.id.imbt_back);
        imgImage = view.findViewById(R.id.img_image);
        tvName = view.findViewById(R.id.tv_name);
        tvQuantity = view.findViewById(R.id.tv_quantity);
        tvDescription = view.findViewById(R.id.tv_description);
        tvName = view.findViewById(R.id.tv_name);
        tvPrice = view.findViewById(R.id.tv_price);
        tvRate = view.findViewById(R.id.tv_rating);
        tvComment = view.findViewById(R.id.tv_comment);
        imbtPlus = view.findViewById(R.id.imbt_plus);
        imbtMinus = view.findViewById(R.id.imbt_minus);
        fabAdd = view.findViewById(R.id.fab_add);
        recyclerProductList = view.findViewById(R.id.recycler_product_list);
        productList = new ArrayList<>();
        ///
        homeProductDetailFragmentListProductAdapter = new HomeProductDetailFragmentListProductAdapter(productList);
        fragmentProductDetailPresenter = new FragmentProductDetailPresenter(this);
        ///
        RecyclerView.LayoutManager layoutManagerProduct = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerProductList.setLayoutManager(layoutManagerProduct);
        recyclerProductList.setAdapter(homeProductDetailFragmentListProductAdapter);

        // Xet Listener
        imbtPlus.setOnClickListener(this);
        imbtMinus.setOnClickListener(this);
        tvComment.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
    }

    private void getProductWithId(){
        String id = getArguments().getString("productId");
        productCurrent = new Product();
        db.collection("product").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                productCurrent.setId(document.getId());
                productCurrent.setDescription((String) document.get("description"));
                productCurrent.setName((String) document.get("name"));
                productCurrent.setPrice((document.getDouble("price")));
                productCurrent.setCategory((String) document.get("categoryId"));
                productCurrent.setDiscount(document.getDouble("discount"));
                productCurrent.setImage((String) document.get("image"));
                updateUi();
                initProductRelativeList();
                Log.d("product",productCurrent.getName());
            }
        });

    }
    private void updateUi() {
        Picasso.get().load(productCurrent.getImage()).into(imgImage);
        tvPrice.setText(productCurrent.getPrice()+" đ");
        tvName.setText(productCurrent.getName());
        tvDescription.setText(productCurrent.getDescription());
    }

    private void onBack(View view) {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void initProductRelativeList() {
        List<Product> products = new ArrayList<>();
        Log.d("categoryID",productCurrent.getCategory());
        db.collection("product")
                .whereEqualTo("categoryId",productCurrent.getCategory())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        product.setCategory((String) document.get("categoryId"));
                        product.setImage((String) document.get("image"));
                        Log.d("productname",product.getName());
                        products.add(product);
                        homeProductDetailFragmentListProductAdapter.setList(products);
                    }
                } else {
                }
            }
        });
    }

    private void addToCart(String productId, int quantity){
        Map<String, Object> docData = new HashMap<>();
        docData.put("quantity",quantity);
            db.collection("cart")
                    .document(user.getUid())
                    .collection("product")
                    .document(productId)
                    .set(docData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity().getBaseContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Không thêm được",Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    private void addToCartv2(Product product, int quantity){
//        ProductItem productItem = new ProductItem();
//        productItem.setProduct(product);
//        productItem.setQuantity(quantity);
//        Database.shoppingCartList.add(productItem);
//        Map<String, Object> docData = new HashMap<>();
//        docData.put("shoppingCart",Database.shoppingCartList);
//        db.collection("cart").document(user.getUid()).set(docData)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getActivity().getBaseContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
////                        Log.d(TAG, "DocumentSnapshot successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(),"Không thêm được",Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_comment:
//                Toast.makeText(getContext(),"Click Nhan xet",Toast.LENGTH_SHORT).show();
                ReviewFragment reviewFragment = new ReviewFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, reviewFragment).addToBackStack("ratingFragment").commit();
                break;
            case R.id.imbt_plus:
                fragmentProductDetailPresenter.onClickPlus(quantityCurrent);
                break;
            case R.id.imbt_minus:
                fragmentProductDetailPresenter.onClickMinus(quantityCurrent);
                break;
            case R.id.fab_add:
                addToCart(productCurrent.getId(),quantityCurrent);
//                addToCartv2(productCurrent,quantityCurrent);
                break;

        }
    }

    @Override
    public void onClick(int quantity) {
        quantityCurrent = quantity;
        tvQuantity.setText(quantityCurrent +"");
    }
}
