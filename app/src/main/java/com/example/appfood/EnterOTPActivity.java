package com.example.appfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class EnterOTPActivity extends AppCompatActivity {
    private static final String TAG = EnterOTPActivity.class.getName();
    TextInputEditText tedt_otp;
    Button bt_confirm;

    String mPhone;
    String mVerification;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        getDataIntent();
        initId();
        initListener();
    }

    private void initListener() {
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOtp = tedt_otp.getText().toString();
                onClickSendOtpCode(strOtp);
            }
        });
    }

    private void initId() {
        tedt_otp = findViewById(R.id.tedt_otp);
        bt_confirm = findViewById(R.id.bt_confirm);
        mAuth = FirebaseAuth.getInstance();
    }

    private void getDataIntent(){
        mPhone = getIntent().getStringExtra("phone");
        mVerification = getIntent().getStringExtra("verification");
    }

    private void onClickSendOtpCode(String strOtp){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerification, strOtp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            // Update UI
                            goToMainActivity();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    private void goToMainActivity() {
        Intent intent = new Intent(EnterOTPActivity.this,MainActivity.class);
        startActivity(intent);
    }

}