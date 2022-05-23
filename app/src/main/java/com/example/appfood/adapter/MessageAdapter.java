package com.example.appfood.adapter;

import static com.example.appfood.database.Database.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    Context context;
    List<Chat> chatList;
    static int MSG_RIGHT = 1;
    static int MSG_LEFT = 0;

    public MessageAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @Override
    public int getItemViewType(int position) {
        if(user.getUid().equals(chatList.get(position).getUserSend()))
            return MSG_RIGHT;
        return MSG_LEFT;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        if(viewType == MSG_RIGHT){
            v = LayoutInflater.from(context).inflate(R.layout.item_message_right,parent, false);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.item_message_left,parent, false);
        }
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.message.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.tv_message);
        }
    }
}
