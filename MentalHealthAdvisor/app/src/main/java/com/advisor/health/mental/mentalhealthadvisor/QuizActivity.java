package com.advisor.health.mental.mentalhealthadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
    /*final RadioButton yesBtn = (RadioButton)findViewById(R.id.qstnOneYes);
    final RadioButton noBtn = (RadioButton)findViewById(R.id.qstnOneNo);
    final RadioButton sometimesBtn = (RadioButton)findViewById(R.id.qstnOneSmetme);*/



    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.qstnOneYes:
                if (checked)
                    break;

            case R.id.qstnOneNo:
                if (checked)
                    break;

            case R.id.qstnOneSmetme:
                if(checked)
                    break;
        }
        /*yesBtn.equals(10);
        if (yesBtn.isChecked()) {


        }*/

    }
}
