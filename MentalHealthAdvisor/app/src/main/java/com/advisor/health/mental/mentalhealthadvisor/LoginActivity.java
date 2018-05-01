package com.advisor.health.mental.mentalhealthadvisor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText editTxtEmail, editTxtPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registerLink).setOnClickListener(this);
        findViewById(R.id.loginBtn).setOnClickListener(this);

        editTxtEmail = (EditText) findViewById(R.id.emailTxt);
        editTxtPassword = (EditText) findViewById(R.id.passwordTxt);
        progressBar = (ProgressBar) findViewById(R.id.registerProgressBar);
    }

    private void userLogin() {
        String email = editTxtEmail.getText().toString().trim();
        String password = editTxtPassword.getText().toString().trim();


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

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Intent intent = new Intent (LoginActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerLink:

                startActivity(new Intent(this, RegisterActivity.class));

                break;

            case R.id.loginBtn:
                userLogin();
                break;
        }
    }


}
