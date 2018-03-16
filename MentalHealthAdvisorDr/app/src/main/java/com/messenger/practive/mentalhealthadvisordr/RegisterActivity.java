package com.messenger.practive.mentalhealthadvisordr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTxtUserName, editTxtEmail, editTxtPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.loginLink).setOnClickListener(this);
        editTxtUserName = (EditText) findViewById(R.id.userNameTxt);
        editTxtEmail = (EditText) findViewById(R.id.emailText);
        editTxtPassword = (EditText) findViewById(R.id.passwordTxt);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registerBtn).setOnClickListener(this);

    }

    private void registerUser() {
        String username = editTxtUserName.getText().toString().trim();
        String email = editTxtEmail.getText().toString().trim();
        String password = editTxtPassword.getText().toString().trim();

        if(username.isEmpty()) {
            editTxtUserName.setError("Username is required");
            editTxtUserName.requestFocus();
            return;
        }


        if(email.isEmpty()) {
            editTxtEmail.setError("Email is required");
            editTxtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTxtEmail.setError("Please enter a valid email.");
            editTxtEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTxtPassword.setError("Password is required");
            editTxtPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTxtPassword.setError("Minimum length of password should be 6");
            editTxtPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerBtn:
                registerUser();
                break;

            case R.id.loginLink:

                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
    }


}
