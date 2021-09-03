package com.aniket.lecturealert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout edtName, edtUserName, edtPassword, edtEmail, edtMobileNumber;
    Button btnRegister, btnRegisterToLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtUserName = findViewById(R.id.edtUserName);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegisterToLogin = findViewById(R.id.btnRegisterToLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = edtName.getEditText().getText().toString();
                String username = edtUserName.getEditText().getText().toString();
                String password = edtPassword.getEditText().getText().toString();
                String email = edtEmail.getEditText().getText().toString();
                String phoneNo = edtMobileNumber.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, password, email, phoneNo);

                reference.child(phoneNo).setValue(helperClass);
            }
        });

    }
    private boolean validateUser(){
        String val = 
    }
}