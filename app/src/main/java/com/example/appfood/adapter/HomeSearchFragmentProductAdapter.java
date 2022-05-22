package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Category;
import com.example.appfood.model.Product;
import com.example.appfood.presenter.FragmentSearchPresenter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeSearchFragmentProductAdapter extends RecyclerView.Adapter<HomeSearchFragmentProductAdapter.ViewHolder> implements Filterable {
    List<Product> list;
    List<Product> listOld;
    FragmentSearchPresenter fragmentSearchPresenter;

    public HomeSearchFragmentProductAdapter(List<Product> list) {
        this.list = list;
    }

    public void setList(List<Product> list) {
        this.list = list;
        this.listOld = list;
        notifyDataSetChanged();
    }

    public void setFragmentSearchPresenter(FragmentSearchPresenter fragmentSearchPresenter) {
        this.fragmentSearchPresenter = fragmentSearchPresenter;
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
        DecimalFormat dfVND = new DecimalFormat("###,###,###,###");
        holder.price.setText(dfVND.format(product.getPrice() * (1 - (product.getDiscount() / 100))) + " đ");
        Picasso.get().load(product.getImage()).into(holder.anh);

        holder.rate.setText("5.0");
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0: list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView anh;
        TextView title,rate,price;
        CardView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            anh = itemView.findViewById(R.id.item_image);
            price = itemView.findViewById(R.id.item_price);
            title = itemView.findViewById(R.id.item_title);
            rate = itemView.findViewById(R.id.item_rate);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentSearchPresenter.onClickProduct(list.get(getAdapterPosition()).getId());
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    list  = listOld;
                }
                else{
                    List<Product> newList = new ArrayList<>();
                    for(Product product : listOld){
                        if(product.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            newList.add(product);
                        }
                    }
                    list = newList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
