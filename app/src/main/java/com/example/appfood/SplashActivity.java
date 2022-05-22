package com.example.appfood;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 1000;
    Animation topAnim, botAnim;

    ImageView splashscreenLogo;
    TextView splashscreenTitle;
    TextView splashscreenSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashscreenLogo = findViewById(R.id.splashscreen_logo);
        splashscreenTitle = findViewById(R.id.splashscreen_title);
        splashscreenSlogan = findViewById(R.id.splashscreen_slogan);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_top);
        botAnim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_bottom);

        splashscreenLogo.setAnimation(topAnim);
        splashscreenTitle.setAnimation(botAnim);
        splashscreenSlogan.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  nextActivity();
              }
          },SPLASH_SCREEN);
    }

    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent;
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(splashscreenLogo, "logo_image");
        pairs[1] = new Pair<View, String>(splashscreenTitle, "logo_text");
        if(user != null){
//             Login success
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }else{
            intent = new Intent(SplashActivity.this, SignInActivity.class);
        }
        ActivityOptions options = ActivityOptions.
                makeSceneTransitionAnimation(SplashActivity.this, pairs);
        startActivity(intent, options.toBundle());
        finishAffinity();
    }
}



