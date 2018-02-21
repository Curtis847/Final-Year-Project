package com.messenger.practive.mentalhealthadvisordr;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rLayout;
    private LinearLayout lLayout;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        lLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Button mButton = (Button) findViewById(R.id.addBtn);

    }

    public void submitChoice(View view) {
        RadioButton radioBtn = new RadioButton(this);
        radioBtn.setTextSize(40);
        radioBtn.setText("text");

        lLayout.addView(radioBtn);
        lLayout.invalidate();
    }


}
