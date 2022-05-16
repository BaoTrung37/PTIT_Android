package com.example.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.fragment.HomeProductDetailFragment;
import com.example.appfood.model.Product;
import com.example.appfood.presenter.FragmentHomePresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragmentListProductAdapter extends RecyclerView.Adapter<HomeFragmentListProductAdapter.ViewHolder>{
    List<Product> list;
    FragmentHomePresenter fragmentHomePresenter;
    Context context;

    public HomeFragmentListProductAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setFragmentHomePresenter(FragmentHomePresenter fragmentHomePresenter) {
        this.fragmentHomePresenter = fragmentHomePresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmet_home_list_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);
        if(product == null){
            return;
        }
        holder.title.setText(product.getName());
        Picasso.get().load(product.getImage()).into(holder.anh);
        holder.rate.setText("5.0");
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, HomeProductDetailFragment.class);
//                intent.putExtra("product",product);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(list.isEmpty()) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView anh;
        TextView title,rate;
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
            switch (view.getId()){
                case R.id.item:
                    fragmentHomePresenter.onCLickProduct(list.get(getAdapterPosition()).getId());

            }
        }
    }
}