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
import com.example.appfood.model.ProductItem;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailProductAdapter extends RecyclerView.Adapter<OrderDetailProductAdapter.ViewHolder> {
    List<ProductItem> list;

    public OrderDetailProductAdapter(List<ProductItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_order_detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductItem productItem = list.get(position);
        holder.tvName.setText(productItem.getProduct().getName());
        DecimalFormat dfVND = new DecimalFormat("###,###,###,###");
        holder.tvPrice.setText(dfVND.format(productItem.getProduct().getPrice()) + " đ");
        Picasso.get().load(productItem.getProduct().getImage()).into(holder.image);
        holder.tvQuantity.setText(productItem.getQuantity() +"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        CardView item;
        TextView tvName,tvPrice,tvQuantity;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPrice = itemView.findViewById(R.id.item_tv_price);
            tvQuantity = itemView.findViewById(R.id.item_tv_quantity);
            image = itemView.findViewById(R.id.item_image);
        }
    }
}
