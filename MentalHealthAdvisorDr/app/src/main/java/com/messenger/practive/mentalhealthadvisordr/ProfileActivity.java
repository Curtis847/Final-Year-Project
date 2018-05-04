package com.messenger.practive.mentalhealthadvisordr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private Button createQuizBtn;
    private Button enterLocation;
    private Button createBtn;
    private EditText quizName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        createQuizBtn = (Button) findViewById(R.id.createQuizBtn);
        enterLocation = (Button) findViewById(R.id.enterLocationBtn);


    }

    public void createQuiz(View v) {
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));

    }



    public void enterLocation(View v) {
        startActivity(new Intent(ProfileActivity.this, EnterLocationActivity.class));
    }
}
