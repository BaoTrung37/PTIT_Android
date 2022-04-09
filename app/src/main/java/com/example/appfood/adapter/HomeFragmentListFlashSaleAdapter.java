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

import java.text.DecimalFormat;
import java.util.List;

public class HomeFragmentListFlashSaleAdapter extends RecyclerView.Adapter<HomeFragmentListFlashSaleAdapter.ViewHolder> {
    List<Product> list;


    public HomeFragmentListFlashSaleAdapter(List<Product> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmet_home_list_flash_sale,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);
        if(product == null){
            return;
        }
        holder.title.setText(product.getTitle());
        Picasso.get().load(product.getImage()).into(holder.anh);
        DecimalFormat dfVND = new DecimalFormat("###,###,###,###");
        holder.price.setText(dfVND.format(product.getPrice()) + " đ");
        holder.price_discount.setText(dfVND.format(product.getPrice()) + " đ");
        holder.discount.setText("Giảm " + product.getDiscount() + " %");
    }

    @Override
    public int getItemCount() {
        if(list.isEmpty()) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anh;
        TextView title,price_discount,price,discount;
        CardView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            anh = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
            price_discount = itemView.findViewById(R.id.item_price_discount);
            discount = itemView.findViewById(R.id.item_discount);
        }
    }
}
