package com.example.appfood.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.CartShoppingCartListAdapter;
import com.example.appfood.database.Database;
import com.example.appfood.interfaces.IFragmentCartShoppingCartListener;
import com.example.appfood.model.Product;
import com.example.appfood.model.ProductItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartShoppingCartFragment extends Fragment implements View.OnClickListener, IFragmentCartShoppingCartListener {

    RecyclerView recyclerShoppingCartList;
    CheckBox cbCheckAll;
    Button btnOrder;
    TextView tvTotalPrice;

    CartShoppingCartListAdapter cartShoppingCartListAdapter;
    List<ProductItem> cartProductList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_shopping_cart, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        initData(view);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initData(View view) {
        //find by id
        recyclerShoppingCartList = view.findViewById(R.id.recycler_shopping_cart_list);
        cbCheckAll = view.findViewById(R.id.cb_checkall);
        btnOrder = view.findViewById(R.id.btn_order);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        // adapter
        cartShoppingCartListAdapter = new CartShoppingCartListAdapter(cartProductList);
        cartShoppingCartListAdapter.setIFragmentCartShoppingCartListener(this);
        RecyclerView.LayoutManager cartShoppingCartListLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerShoppingCartList.setLayoutManager(cartShoppingCartListLayoutManager);
        recyclerShoppingCartList.setAdapter(cartShoppingCartListAdapter);

        // set event
        btnOrder.setOnClickListener(this);
        checkAll();
        //
        setTotalPrice();
    }

    private void setTotalPrice() {
        tvTotalPrice.setText(cartShoppingCartListAdapter.getTotalPrice() + " đ");
    }

    private void checkAll() {
        cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cartShoppingCartListAdapter.setCheckAll(b);
                cbCheckAll.setChecked(b);
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_order:
                PaymentFragment paymentFragment = new PaymentFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, paymentFragment)
                        .addToBackStack("paymentFragment").commit();
                break;

        }
    }

    private void getShoppingCart() {
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        db.collection("cart")
                .document(user.getUid())
                .collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ProductItem> productItems = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ProductItem productItem = new ProductItem();
                                Product product = Database.getProduct(document.getId());
                                int quantity = Integer.parseInt(document.getLong("quantity").toString());
                                productItem.setProduct(product);
                                productItem.setQuantity(quantity);
                                cartProductList.add(productItem);
                                cartShoppingCartListAdapter.setList(cartProductList);
                            }
                            setTotalPrice();
                            progress.dismiss();
                        }
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        cartShoppingCartListAdapter.setList(cartProductList);
    }

    private void getData() {
        //
        cartProductList = new ArrayList<>();

        //
        getShoppingCart();

//        cartProductList.add(new ProductItem("1", new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"), 1));
//        cartProductList.add(new ProductItem("1", new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"), 1));
//        cartProductList.add(new ProductItem("1", new Product("1", "Ga chien xao sa ơt",
//                402223, "https://cdn-icons-png.flaticon.com/512/7088/7088397.png", "", 10, "asdasda"), 1));
    }


    @Override
    public void setCheckedAll(boolean isCheck) {
        cbCheckAll.setChecked(isCheck);
    }

    @Override
    public void setTotalPrice(double totalPrice) {
        tvTotalPrice.setText(totalPrice + " đ");
    }

}
