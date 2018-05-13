package com.messenger.practive.mentalhealthadvisordr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    public Button createQuizBtn;
    public Button enterLocation;
    public Button enterIssueBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        createQuizBtn = (Button) findViewById(R.id.createQuizBtn);
        enterLocation = (Button) findViewById(R.id.enterLocationBtn);
        enterIssueBtn = (Button) findViewById(R.id.enterIssueBtn);


    }

    public void createQuiz(View v) {
        startActivity(new Intent(ProfileActivity.this, CreateQuizActivity.class));

    }



    public void enterLocation(View v) {
        startActivity(new Intent(ProfileActivity.this, EnterLocationActivity.class));
    }

    public void enterIssue(View v) {
        startActivity(new Intent(ProfileActivity.this, EnterIssueActivity.class));
    }
}
