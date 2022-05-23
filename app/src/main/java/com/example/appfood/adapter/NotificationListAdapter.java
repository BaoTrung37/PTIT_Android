package com.example.appfood.adapter;

import static com.example.appfood.database.Database.db;
import static com.example.appfood.database.Database.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.model.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    List<Notification> list;

    public NotificationListAdapter(List<Notification> list) {
        this.list = list;
    }

    public void inItData(){
        db.collection("user")
                .document(user.getUid())
                .collection("order")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                String id = doc.getId();
                                double status = doc.getDouble("orderStatus");
                                Timestamp orderCreate = doc.getTimestamp("dateCreate");
                                if(status == 0){
                                    Notification notification = new Notification();
                                    notification.setTitle("Đơn hàng " + id + " đã được đặt thành công");
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                                    notification.setDate(dateFormat.format(orderCreate.toDate()));
                                    list.add(notification);
                                }
                                notifyDataSetChanged();
                            }
                        }

                    }
                });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = list.get(position);
        holder.title.setText(notification.getTitle());
        holder.date.setText(notification.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_tv_title);
            date = itemView.findViewById(R.id.item_tv_date);
        }
    }
}
