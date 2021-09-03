package com.aniket.lecturealert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextView txtWelcome, txtSignin;
    private ImageView logoImage;
    private Button signUp;
    private TextInputLayout edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtWelcome = findViewById(R.id.txtWelcomeSignIn);
        txtSignin = findViewById(R.id.txtSignin);
        signUp = findViewById(R.id.btnSignUp);
        edtUsername = findViewById(R.id.edtUsernameSignIn);
        edtPassword = findViewById(R.id.edtPasswordSignIn);
        logoImage = findViewById(R.id.logoImageSignIn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(logoImage, "logo_name");
                pairs[1] = new Pair<View, String>(txtWelcome, "logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }



}