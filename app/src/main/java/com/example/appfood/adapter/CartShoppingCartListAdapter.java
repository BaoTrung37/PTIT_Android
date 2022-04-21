package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;

// TODO: Implement CartShoppingCart
public class CartShoppingCartListAdapter extends RecyclerView.Adapter<CartShoppingCartListAdapter.ViewHolder> {
    // itit
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_cart_shopping_cart_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        ImageView image;
        ImageButton imbt_minus,imbt_plus;
        TextView title,price,amount;
        CheckBox check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            image = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
            amount = itemView.findViewById(R.id.tv_amount);
            check = itemView.findViewById(R.id.cb_check);
            imbt_minus = itemView.findViewById(R.id.imbt_minus);
            imbt_plus = itemView.findViewById(R.id.imbt_plus);
        }
    }
}
