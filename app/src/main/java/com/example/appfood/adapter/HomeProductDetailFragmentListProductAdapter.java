package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeProductDetailFragmentListProductAdapter extends RecyclerView.Adapter<HomeProductDetailFragmentListProductAdapter.ViewHolder> {
    List<Product> list;

    public HomeProductDetailFragmentListProductAdapter(List<Product> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeProductDetailFragmentListProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmet_home_product_detail_list_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductDetailFragmentListProductAdapter.ViewHolder holder, int position) {

        Product product = list.get(position);
        if (product == null) {
            return;
        }
        holder.title.setText(product.getTitle());
        Picasso.get().load(product.getImage()).into(holder.anh);
        holder.rate.setText("5.0");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView anh;
        TextView title, rate;
        CardView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            anh = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            rate = itemView.findViewById(R.id.item_rate);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
