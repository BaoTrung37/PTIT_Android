package com.example.appfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvMoveToSignIn;
    Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initData();
        setEvent();
    }

    private void setEvent() {
        tvMoveToSignIn.setOnClickListener(this);
        btSignUp.setOnClickListener(this);
    }

    private void initData() {
        tvMoveToSignIn = findViewById(R.id.tv_move_to_signin);
        btSignUp = findViewById(R.id.bt_signup);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_move_to_signin:
                onMoveToSignIn();
                break;
            case R.id.bt_signup:
                onSignUp();
        }
    }

    private void onSignUp() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String username = "admin@gmail.com";
        String password = "123456";
        FirebaseUser user = mAuth.getCurrentUser();
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }
        });

    }

    private void onMoveToSignIn() {
        finish();
    }
}