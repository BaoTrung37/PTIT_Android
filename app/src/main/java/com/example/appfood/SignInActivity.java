package com.example.appfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvMoveToSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tvMoveToSignUp = findViewById(R.id.tv_move_to_signup);

        setEvent();
    }

    private void setEvent() {
        tvMoveToSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_move_to_signup:
                moveToSignUp();
                break;
        }
    }

    private void moveToSignUp() {
        // Is login

        // Sign Up
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);

    }

}