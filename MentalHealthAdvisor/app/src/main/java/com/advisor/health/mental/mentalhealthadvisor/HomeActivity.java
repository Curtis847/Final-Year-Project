package com.advisor.health.mental.mentalhealthadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button mentalHealthBtn = (Button)findViewById(R.id.mentalHealthBtn);
        Button selfCheckerBtn = (Button)findViewById(R.id.selfCheckerBtn);
        Button findLocationBtn = (Button)findViewById(R.id.findOrganizationBtn);

        mentalHealthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DisplayIssuesActivity.class);
                startActivity(intent);
            }
        });

        selfCheckerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent);
            }
        });

        findLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FindLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}
