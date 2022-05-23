package com.example.appfood.fragment;

import static com.example.appfood.database.Database.db;
import static com.example.appfood.database.Database.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.CartFragmentOrderPendingListAdapter;
import com.example.appfood.interfaces.IOrderDetailListener;
import com.example.appfood.model.Order;
import com.example.appfood.model.Product;
import com.example.appfood.model.ProductItem;
import com.example.appfood.presenter.CartFragmentOrderPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartOrdersPendingFragment extends Fragment implements IOrderDetailListener {

    RecyclerView recOrderPendingList;
    CartFragmentOrderPendingListAdapter cartFragmentOrderPendingListAdapter;
    CartFragmentOrderPresenter cartFragmentOrderPresenter;
    List<Order> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_orderspending, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initId(view);
        setData();
    }

    private void setData() {
        cartFragmentOrderPendingListAdapter = new CartFragmentOrderPendingListAdapter(orderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        cartFragmentOrderPresenter = new CartFragmentOrderPresenter();
        cartFragmentOrderPresenter.setIOrderDetailListener(this);
        cartFragmentOrderPendingListAdapter.setCartFragmentOrderPresenter(cartFragmentOrderPresenter);
        recOrderPendingList.setLayoutManager(layoutManager);
        recOrderPendingList.setAdapter(cartFragmentOrderPendingListAdapter);
    }

    private void initId(View view) {
        recOrderPendingList = view.findViewById(R.id.recOderSpendingList);
    }

    private void initData() {
        orderList = new ArrayList<>();

        db.collection("user").document(user.getUid()).collection("order")
                .whereEqualTo("orderStatus", 0)
//                .orderBy("dateCreate", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                Order order = new Order();
                                order.setId(doc.getId());
                                order.setNote(doc.getString("note"));
                                order.setOrderStatus(doc.getDouble("orderStatus"));
                                order.setTotalPrice(doc.getDouble("totalPrice"));
                                order.setTotalPayment(doc.getDouble("totalPayment"));
                                order.setDateCreate(doc.getTimestamp("dateCreate"));
                                order.setAddress(doc.getString("address"));
                                List<Map<String, Object>> cart = (List<Map<String, Object>>) doc.get("productOrder");
                                List<ProductItem> productOrder = new ArrayList<>();
                                for (int i = 0; i < cart.size(); i++) {
                                    ProductItem productItem = new ProductItem();
                                    productItem.setCheck((Boolean) cart.get(i).get("check"));
                                    productItem.setQuantity(Integer.parseInt(cart.get(i).get("quantity").toString()));
                                    Map<String, Object> productMap = (Map<String, Object>) cart.get(i).get("product");
                                    Product product = new Product();
                                    product.setId(productMap.get("id").toString());
                                    product.setCategory(productMap.get("category").toString());
                                    product.setDiscount(Double.parseDouble(productMap.get("discount").toString()));
                                    product.setPrice(Double.parseDouble(productMap.get("price").toString()));
                                    product.setDescription(productMap.get("description").toString());
                                    product.setImage(productMap.get("image").toString());
                                    product.setName(productMap.get("name").toString());
                                    productItem.setProduct(product);

                                    productOrder.add(productItem);
                                }
                                order.setProductOrder(productOrder);
//                                Log.d("order",order.getNote());
                                orderList.add(order);
                                cartFragmentOrderPendingListAdapter.setOrderList(orderList);
                            }
                        }
                    }
                });

    }


    @Override
    public void onClick(Order order) {
        Log.d("hihihi","chay ne");
        DetailOrderFragment detailOrderFragment = new DetailOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("order",order);
        detailOrderFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout,detailOrderFragment)
                .addToBackStack("detailOrderFragment").commit();
    }

    @Override
    public void onChangeStatus(String id,int status) {
        db.collection("user")
                .document(user.getUid())
                .collection("order")
                .document(id)
                .update("orderStatus",status)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        initData();
                    }
                });
    }
}
