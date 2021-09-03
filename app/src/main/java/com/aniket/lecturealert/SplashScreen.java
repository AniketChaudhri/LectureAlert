package com.aniket.lecturealert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private ImageView imgTeacher;
    private TextView txtHeading, txtDeveloper;
    Animation imgAnimation, headingAnimation;
    private static final int SPLASH_SCREEN_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        imgAnimation = AnimationUtils.loadAnimation(this, R.anim.image_animation);
        headingAnimation = AnimationUtils.loadAnimation(this, R.anim.heading_animation);

        imgTeacher = findViewById(R.id.imgTeacher);
        txtHeading = findViewById(R.id.txtHeading);
        txtDeveloper = findViewById(R.id.txtDeveloper);

        imgTeacher.setAnimation(imgAnimation);
        txtHeading.setAnimation(headingAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(imgTeacher, "logo_name");
                pairs[1] = new Pair<View, String>(txtDeveloper, "logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        }, SPLASH_SCREEN_TIME);


    }
}