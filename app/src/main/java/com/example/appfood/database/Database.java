package com.example.appfood.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appfood.model.Category;
import com.example.appfood.model.Product;
import com.example.appfood.model.ProductItem;
import com.example.appfood.model.ShoppingCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public static List<Product> productList = new ArrayList<>();
    public static List<Category> categoryList = new ArrayList<>();
    public static List<ProductItem> shoppingCartList = new ArrayList<>();

    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        db.collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                Product product = new Product();
                                product.setId(document.getId());
                                product.setDescription((String) document.get("description"));
                                product.setName((String) document.get("name"));
                                product.setPrice(document.getDouble("price"));
                                product.setDiscount(document.getDouble("discount"));
                                product.setCategory((String) document.get("categoryId"));
                                product.setImage((String) document.get("image"));
                                Log.d("productname",product.getName());
                                productList.add(product);
                            }
                        }else{

                        }
                    }
                });
        return productList;
    }
    public static Product getProduct(String productId){
//        Product product = new Product();
//        db.collection("product")
//                .document(productId)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    DocumentSnapshot document = task.getResult();
//                    product.setId(document.getId());
//                    product.setDescription((String) document.get("description"));
//                    product.setName((String) document.get("name"));
//                    product.setPrice(document.getDouble("price"));
//                    product.setDiscount(document.getDouble("discount"));
//                    product.setCategory((String) document.get("categoryId"));
//                    product.setImage((String) document.get("image"));
//                }
//            }
//        });
        for(Product product: productList){
            if(product.getId().equals(productId)){
                return product;
            }
        }
        return null;
    }
    public static List<Category> getCategoryList(){
        List<Category> categoryList = new ArrayList<>();
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
                    }
                } else {
                }
            }
        });
        return categoryList;
    }
    public static List<Product> getProductRelativeList(String categoryID){
        List<Product> products = new ArrayList<>();
        Log.d("categoryID",categoryID);
        db.collection("product")
                .whereEqualTo("categoryId",categoryID)
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
                            }
                        } else {
                        }
                    }
                });
        return products;
    }
    public static List<ProductItem> getShoppingCart(){
        List<ProductItem> productItems = new ArrayList<>();
        db.collection("cart")
                .document(user.getUid())
                .collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for(QueryDocumentSnapshot document: task.getResult()){
                                ProductItem productItem = new ProductItem();
                                Product product = Database.getProduct(document.getId());
                                int quantity = Integer.parseInt(document.getLong("quantity").toString());
//                                Log.d("quantity", document.getLong("quantity").toString());
                                productItem.setProduct(product);
                                productItem.setQuantity(quantity);
                                productItems.add(productItem);
                            }
                        }
                    }
                });
        return productItems;
    }
    public static void addProduct(String productId, int quantity){
        Map<String, Object> docData = new HashMap<>();
        docData.put("quantity",quantity);
        db.collection("cart").document(user.getUid()).collection("product").document(productId).set(docData);
    }
}
