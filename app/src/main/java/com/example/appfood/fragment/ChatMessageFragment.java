package com.example.appfood.fragment;

import static com.example.appfood.database.Database.db;
import static com.example.appfood.database.Database.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood.R;
import com.example.appfood.adapter.MessageAdapter;
import com.example.appfood.model.Chat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatMessageFragment extends Fragment {

    MessageAdapter messageAdapter;
    RecyclerView recChatMess;
    EditText edtMessage;
    ImageView btnSend;

    List<Chat> chatList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_mess,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolBar(view);
        initId(view);
        getData();
        setListener();
    }

    private void setToolBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }

    private void setListener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMessage(edtMessage.getText().toString().trim(),user.getUid());
            }
        });
    }

    private void addMessage(String mess, String userSend){
        Chat chat = new Chat();
        chat.setUserSend(userSend);
        chat.setMessage(mess);
        Map<String,Object> docData = new HashMap<>();
        docData.put("message",mess);
        docData.put("userSend",userSend);
        db.collection("chat")
                .document(user.getUid())
                .collection("message")
                .document()
                .set(docData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            chatList.add(chat);
                            edtMessage.setText("");
                            messageAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void getData() {
        chatList = new ArrayList<>();
        messageAdapter = new MessageAdapter(getContext(),chatList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        recChatMess.setLayoutManager(layoutManager);
        recChatMess.setAdapter(messageAdapter);

        db.collection("chat")
                .document(user.getUid())
                .collection("message")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc : task.getResult()){
                                Chat chat = new Chat();
                                chat.setMessage(doc.getString("message"));
                                chat.setId(doc.getId());
                                chat.setUserSend(doc.getString("userSend"));
                                chatList.add(chat);
                            }
                            messageAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initId(View view) {
        btnSend = view.findViewById(R.id.btn_send);
        edtMessage = view.findViewById(R.id.edt_message);
        recChatMess = view.findViewById(R.id.rec_messageLayout);
    }
}
