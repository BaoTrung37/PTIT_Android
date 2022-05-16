package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Category;
import com.example.appfood.presenter.FragmentSearchPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSearchFragmentCategoryAdapter extends RecyclerView.Adapter<HomeSearchFragmentCategoryAdapter.ViewHolder> {
    List<Category> list;
    FragmentSearchPresenter fragmentSearchPresenter;

    public HomeSearchFragmentCategoryAdapter(List<Category> list) {
        this.list = list;
    }

    public void setList(List<Category> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setFragmentSearchPresenter(FragmentSearchPresenter fragmentSearchPresenter) {
        this.fragmentSearchPresenter = fragmentSearchPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_search_category_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = list.get(position);
        if(category == null){
            return;
        }
        holder.title.setText(category.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            item = itemView.findViewById(R.id.item);

            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            fragmentSearchPresenter.onCLickCategory(list.get(getAdapterPosition()).getId());
        }
    }
}
