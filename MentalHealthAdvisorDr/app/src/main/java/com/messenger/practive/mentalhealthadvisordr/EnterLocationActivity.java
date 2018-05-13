package com.messenger.practive.mentalhealthadvisordr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
Code by kishor sourced from: https://kishorprogamer.wixsite.com/maps

 */

public class EnterLocationActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase; //Reference for the firebase database
    private Button btnSave;
    private EditText editTextName;
    private EditText editTextLatitude;
    private EditText editTextLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_location);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Locations"); //instance
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextLatitude=(EditText)findViewById(R.id.editTextLatitude);
        editTextLongitude=(EditText)findViewById(R.id.editTextLongitude);
        btnSave=(Button)findViewById(R.id.saveBtn);
        btnSave.setOnClickListener(this);

    }

    private void saveLocationInformation(){
        String name =editTextName.getText().toString().trim();
        double latitude= Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude= Double.parseDouble(editTextLongitude.getText().toString().trim());
        LocationInformation locationInformation =new LocationInformation(name,latitude,longitude);
        mDatabase.child(name).setValue(locationInformation);
        Toast.makeText(this,"Saved to Database",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnSave){
            saveLocationInformation();
            editTextName.getText().clear();
            editTextLatitude.getText().clear();
            editTextLongitude.getText().clear();
        }
    }
}
