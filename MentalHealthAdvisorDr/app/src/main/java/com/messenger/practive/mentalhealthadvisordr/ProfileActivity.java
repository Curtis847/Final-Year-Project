package com.messenger.practive.mentalhealthadvisordr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    private Button createQuizBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        createQuizBtn = (Button) findViewById(R.id.createQuizBtn);


    }

    public void createQuiz(View v) {
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }
}
