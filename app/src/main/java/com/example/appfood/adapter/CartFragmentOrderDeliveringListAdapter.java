package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Order;
import com.example.appfood.presenter.CartFragmentOrderPresenter;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CartFragmentOrderDeliveringListAdapter extends RecyclerView.Adapter<CartFragmentOrderDeliveringListAdapter.ViewHolder> {
    List<Order> orderList;
    CartFragmentOrderPresenter cartFragmentOrderPresenter;

    public CartFragmentOrderDeliveringListAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setCartFragmentOrderPresenter(CartFragmentOrderPresenter cartFragmentOrderPresenter) {
        this.cartFragmentOrderPresenter = cartFragmentOrderPresenter;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_cart_order_delivering_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.id.setText(order.getId());
        holder.name.setText(order.getProductOrder().get(0).getProduct().getName());
        Picasso.get().load(order.getProductOrder().get(0).getProduct().getImage()).into(holder.image);
        DecimalFormat dfVND = new DecimalFormat("###,###,###,###");
        holder.totalPrice.setText(dfVND.format(order.getTotalPrice()) + " đ");
        holder.totalPayment.setText(dfVND.format(order.getTotalPayment()) + " đ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        holder.timeCreate.setText(dateFormat.format(order.getDateCreate().toDate()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView id,name,quantity,totalPrice,totalPayment,timeCreate;
        Button btConfirm;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_tv_id);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_tv_name);
            quantity = itemView.findViewById(R.id.item_tv_quantity);
            totalPrice = itemView.findViewById(R.id.item_tv_totalPrice);
            totalPayment = itemView.findViewById(R.id.item_tv_totalPayment);
            timeCreate = itemView.findViewById(R.id.item_tv_timeCreate);
            btConfirm = itemView.findViewById(R.id.item_bt_confirm);
            item = itemView.findViewById(R.id.item);

            item.setOnClickListener(this);
            btConfirm.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.item:
                    cartFragmentOrderPresenter.onClickOrder(orderList.get(getAdapterPosition()));
                    break;
                    case R.id.item_bt_confirm:
                    cartFragmentOrderPresenter.onClickCompleted( orderList.get(getAdapterPosition()).getId());
                    break;

            }
        }
    }
}
