package com.example.appfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = SignInActivity.class.getName();

    TextInputEditText tedtAccount,tedtPassword;


    TextView tvMoveToSignUp;
    TextView tvSignInOTP;
    Button btSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tvMoveToSignUp = findViewById(R.id.tv_move_to_signup);
        tvSignInOTP = findViewById(R.id.tv_sign_in_otp);
        btSignIn = findViewById(R.id.bt_signin);
        tedtAccount = findViewById(R.id.tedt_account);
        tedtPassword = findViewById(R.id.tedt_password);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.w(TAG,"Asdasd");
                    return;
                }
                String token = task.getResult();
                Log.e(TAG,token);

            }
        });

        setEvent();
    }

    private void setEvent() {
        tvMoveToSignUp.setOnClickListener(this);
        tvSignInOTP.setOnClickListener(this);
        btSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_move_to_signup:
                moveToSignUp();
                break;
            case R.id.tv_sign_in_otp:
                moveToSignInWithOtp();
                break;
            case R.id.bt_signin:
                moveToMainActivity();
                break;
        }
    }

    private void moveToMainActivity() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String account = tedtAccount.getText().toString();
        String password = tedtPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(account,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    private void moveToSignInWithOtp() {
        Intent intent = new Intent(SignInActivity.this,SignInWithOTPActivity.class);
        startActivity(intent);
    }

    private void moveToSignUp() {
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}