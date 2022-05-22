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
import com.example.appfood.model.Category;
import com.example.appfood.presenter.FragmentHomePresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragmentListCategoryAdapter extends RecyclerView.Adapter<HomeFragmentListCategoryAdapter.ViewHolder> {
    List<Category> list;
    FragmentHomePresenter fragmentHomePresenter;

    public HomeFragmentListCategoryAdapter(List<Category> list) {
        this.list = list;
    }

    public void setFragmentHomePresenter(FragmentHomePresenter fragmentHomePresenter) {
        this.fragmentHomePresenter = fragmentHomePresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmet_home_list_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = list.get(position);
        if(category == null){
            return;
        }
        holder.title.setText(category.getTitle());
        Picasso.get().load(category.getImageUrl()).into(holder.anh);
    }

    @Override
    public int getItemCount() {
        if(list.isEmpty()) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        ImageView anh;
        TextView title;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            item = itemView.findViewById(R.id.item);

            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.item:
                    fragmentHomePresenter.onCLickCategory(list.get(getAdapterPosition()).getId());
            }
        }
    }
}
