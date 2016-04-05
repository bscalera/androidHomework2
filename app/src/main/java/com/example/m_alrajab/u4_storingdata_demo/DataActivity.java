package com.example.m_alrajab.u4_storingdata_demo;

import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {

    private U5_DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        myDB = new U5_DatabaseHelper(this);
        final EditText username = (EditText) findViewById(R.id.DA_username_editText);
        final EditText update = (EditText) findViewById(R.id.DA_update_editText);
        Button updateMajor = (Button) findViewById(R.id.btn_major_DA);
        Button updateEmail = (Button) findViewById(R.id.btn_email_DA);
        Button updatePassword = (Button) findViewById(R.id.btn_password_DA);

        updateMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.updateMajor((getID(view, username.getText().toString())), update.getText().toString());
            }
        });



    }

    public String getID (View view, String username){
        if (checkRecord(view, username))
        {
            Cursor cursor = myDB.getAllData();
            while (cursor.moveToNext())
            {
                if (cursor.getString(1).equals(username))
                {
                    return cursor.getString(0);
                }
            }
        }
        return null;
    }


    public boolean checkRecord(View view, String username) {
        Cursor cursor = myDB.getRecForUsername(username);
        if (cursor.getCount() == 0)
        {
            Snackbar.make(view, "No record exists for that username", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }
}
