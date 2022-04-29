package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.interfaces.IFragmentCartShoppingCartListener;
import com.example.appfood.model.Product;

import java.util.List;

// TODO: Implement CartShoppingCart
public class CartShoppingCartListAdapter extends RecyclerView.Adapter<CartShoppingCartListAdapter.ViewHolder> {

    List<Product> list;
    IFragmentCartShoppingCartListener iFragmentCartShoppingCartListener;

    public void setIFragmentCartShoppingCartListener(IFragmentCartShoppingCartListener iFragmentCartShoppingCartListener) {
        this.iFragmentCartShoppingCartListener = iFragmentCartShoppingCartListener;
    }

    public CartShoppingCartListAdapter(List<Product> list) {
        this.list = list;
    }

    public void setCheckAll(boolean isCheck){
        for(Product product: list){
            product.setCheck(isCheck);
        }
        iFragmentCartShoppingCartListener.setTotalPrice(getTotalPrice());
        notifyDataSetChanged();
    }
    public boolean isCheckAll(){
        for(Product product: list){
            if(!product.isCheck()){
                return false;
            }
        }
        return true;
    }
    public double getTotalPrice(){
        double totalPrice = 0;
        for(Product product: list){
            if(product.isCheck()){
                totalPrice += product.getPrice() * (100 - product.getDiscount()) / 100;
            }
        }
        return totalPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_cart_shopping_cart_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //
        Product product = list.get(position);
        holder.check.setChecked(product.isCheck());
        holder.price.setText(product.getPrice() * (100 - product.getDiscount()) / 100 + " đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
            check = itemView.findViewById(R.id.item_cb_check);
            imbt_minus = itemView.findViewById(R.id.imbt_minus);
            imbt_plus = itemView.findViewById(R.id.imbt_plus);

            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    list.get(getAdapterPosition()).setCheck(b);
                    iFragmentCartShoppingCartListener.setTotalPrice(getTotalPrice());
                    iFragmentCartShoppingCartListener.setCheckedAll(isCheckAll());
                }
            });
        }
    }
}
