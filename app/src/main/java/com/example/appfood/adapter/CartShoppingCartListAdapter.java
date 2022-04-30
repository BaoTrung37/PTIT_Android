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
import com.example.appfood.model.ProductItem;

import java.util.List;

// TODO: Implement CartShoppingCart
public class CartShoppingCartListAdapter extends RecyclerView.Adapter<CartShoppingCartListAdapter.ViewHolder> {

    List<ProductItem> list;
    IFragmentCartShoppingCartListener iFragmentCartShoppingCartListener;

    public void setIFragmentCartShoppingCartListener(IFragmentCartShoppingCartListener iFragmentCartShoppingCartListener) {
        this.iFragmentCartShoppingCartListener = iFragmentCartShoppingCartListener;
    }

    public CartShoppingCartListAdapter(List<ProductItem> list) {
        this.list = list;
    }

    public void setCheckAll(boolean isCheck){
        for(ProductItem productItem: list){
            productItem.setCheck(isCheck);
        }
        iFragmentCartShoppingCartListener.setTotalPrice(getTotalPrice());
        notifyDataSetChanged();
    }
    public boolean isCheckAll(){
        for(ProductItem productItem: list){
            if(!productItem.isCheck()){
                return false;
            }
        }
        return true;
    }
    public double getTotalPrice(){
        double totalPrice = 0;
        for(ProductItem productItem: list){
            if(productItem.isCheck()){
                totalPrice += (productItem.getProduct().getPrice() * (100 - productItem.getProduct().getDiscount()) / 100) * productItem.getQuantity();
            }
        }
        return totalPrice;
    }
    public void updateQuantity(int position, int quantity){
        ProductItem productItem = list.get(position);
        productItem.setQuantity(quantity);
        list.remove(position);
        list.add(position,productItem);
        iFragmentCartShoppingCartListener.setTotalPrice(getTotalPrice());

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
        ProductItem productItem = list.get(position);
        holder.check.setChecked(productItem.isCheck());
        holder.quantity.setText(productItem.getQuantity() +"");
        double totalPrice = (productItem.getProduct().getPrice() * (100 - productItem.getProduct().getDiscount()) / 100) * productItem.getQuantity();
        holder.price.setText( totalPrice + " đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView item;
        ImageView image;
        ImageButton imbt_minus,imbt_plus;
        TextView title,price,quantity;
        CheckBox check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            image = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
            quantity = itemView.findViewById(R.id.tv_quantity);
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
            imbt_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int q = list.get(getAdapterPosition()).getQuantity();
                    if(q == 1) return;
                    q -= 1;
                    updateQuantity(getAdapterPosition(),q);
                    notifyDataSetChanged();
//                    quantity.setText(q + "");
                }
            });
            imbt_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int q = list.get(getAdapterPosition()).getQuantity() + 1;
                    updateQuantity(getAdapterPosition(),q);
//                    quantity.setText(q + "");
                    notifyDataSetChanged();
                }
            });
        }
    }
}
