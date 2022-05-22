package com.example.appfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewFragmentReviewListAdapter extends RecyclerView.Adapter<ReviewFragmentReviewListAdapter.ViewHolder> {
    //TODO: Implement ReviewFragmentListReviewAdapter
//    List<Review> list;

    public ReviewFragmentReviewListAdapter(List<Review> list) {
//        this.list = list;
    }

    public ReviewFragmentReviewListAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Review review = list.get(position);
//        Picasso.get().load(review.getUser().getAvatarStr()).into(holder.avatar);
//        Picasso.get().load(review.getImageStr()).into(holder.anh);
//        holder.star.setRating(review.getStar());
//        holder.name.setText(review.getUser().getName());

    }

    @Override
    public int getItemCount() {
//        return list.size();
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item;
        CircleImageView avatar;
        TextView name,comment,timePost;
        RatingBar star;
        ImageView anh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            avatar = itemView.findViewById(R.id.item_cri_avatar);
            name = itemView.findViewById(R.id.item_tv_name);
            comment = itemView.findViewById(R.id.item_tv_comment);
            timePost = itemView.findViewById(R.id.item_tv_timePost);
            star = itemView.findViewById(R.id.item_ratingBar_star);
            anh = itemView.findViewById(R.id.item_image);

        }
    }
}
