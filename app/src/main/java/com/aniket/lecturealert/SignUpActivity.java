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
                registerUser(view);

            }
        });

    }

    private boolean validateName() {
        String val = edtName.getEditText().getText().toString();

        if (val.isEmpty()) {
            edtName.setError("Field cannot be empty");
            return false;
        } else {
            edtName.setError(null);
            edtName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String val = edtUserName.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            edtUserName.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            edtUserName.setError("Username too long");
            return false;
        }else if (!val.matches(noWhiteSpace)) {
            edtUserName.setError("Spaces not allowed");
            return false;
        }else
        {
            edtUserName.setError(null);
            edtUserName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = edtEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            edtEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            edtEmail.setError("Invalid email address");
            return false;
        } else {
            edtEmail.setError(null);
            edtEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = edtMobileNumber.getEditText().getText().toString();

        if (val.isEmpty()) {
            edtMobileNumber.setError("Field cannot be empty");
            return false;
        }else if (val.length() != 10){
            edtMobileNumber.setError("Enter a Valid Indian Mobile Number");
            return false;
        }
        else {
            edtMobileNumber.setError(null);
            edtMobileNumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = edtPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            edtPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            edtPassword.setError("Password is too weak");
            return false;
        } else {
            edtPassword.setError(null);
            edtPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {

        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUserName()){
            return;

        }
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
}