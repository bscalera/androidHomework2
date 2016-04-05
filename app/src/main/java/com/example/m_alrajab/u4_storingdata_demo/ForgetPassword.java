package com.example.m_alrajab.u4_storingdata_demo;

import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    private U5_DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        myDB=new U5_DatabaseHelper(this);

        Button allBtn=(Button) findViewById(R.id.fp_all_btn);
        //display all button
        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllData(view);
            }
        });

        final EditText usernameToCheck = (EditText) findViewById(R.id.fp_username_editText);
        //check record button
        Button recBtn=(Button) findViewById(R.id.fp_chckRec_btn);

        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRecord(view, usernameToCheck.getText().toString());
            }
        });

        final EditText emailToCheck = (EditText) findViewById(R.id.fp_email_editText);
        //send password button
        Button passwordButton=(Button) findViewById(R.id.fp_send_button);

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPassword(view, usernameToCheck.getText().toString(), emailToCheck.getText().toString());
            }
        });
    }

    //show the data in the database
    public void showAllData(View v){
        Cursor cursor = myDB.getAllData();
        if(cursor.getCount()==0) return;

        StringBuffer allData=new StringBuffer("This is what is in the first 3 records: ");
        while(cursor.moveToNext()) {
            allData.append("\nID: " + cursor.getString(0) +
                "  username: " + cursor.getString(1) +
                " "); //you can add more columns here

            if (Integer.parseInt(cursor.getString(0))>3) break;
        }
        Toast.makeText(ForgetPassword.this, allData.toString(), Toast.LENGTH_LONG).show();
    }

    public void checkRecord(View view, String username) {
        Cursor cursor = myDB.getRecForUsername(username);

        if (cursor.getCount() == 0)
            Snackbar.make(view, "No record exists", Snackbar.LENGTH_LONG).show();
        else
            Snackbar.make(view, "Record exists", Snackbar.LENGTH_LONG).show();
    }

    public void viewPassword(View view, String username, String email)
    {
        Cursor cursorCheck = myDB.getRecForUsername(username);
        if (cursorCheck.getCount() == 0)
        {
            Snackbar.make(view, "That username does not exist", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Cursor cursor = myDB.getAllData();
            while (cursor.moveToNext()) {
                if (cursor.getString(1).equals(username))
                {
                    try {
                        if (cursor.getString(3).equals(email)) {
                            Snackbar.make(view, "Your password is: " + cursor.getString(4), Snackbar.LENGTH_INDEFINITE).show();
                        } else {
                            Snackbar.make(view, "The username and email do not match what is in the database.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e)  //This will be used if there is no email address
                    {
                        Snackbar.make(view, "Error: Most likely the problem is that there is no email address or no password for that username in the database.",
                                Snackbar.LENGTH_INDEFINITE).show();
                    }
                }
            }
        }
    }
}
