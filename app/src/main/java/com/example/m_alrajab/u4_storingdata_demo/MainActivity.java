package com.example.m_alrajab.u4_storingdata_demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private U5_DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        myDB=new U5_DatabaseHelper(this);

        //register button
        Button reg_button=(Button) findViewById(R.id.btn_register_MA);
        reg_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        //forgot password button
        Button forgot_button=(Button) findViewById(R.id.btn_forgetpassword_MA);
        forgot_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        }));

        //data button
        Button data_button=(Button) findViewById(R.id.btn_data_MA);
        data_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
            }
        }));

        //password editText
        final EditText editText_password=(EditText) findViewById(R.id.editText_password_MA2);
        editText_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String password = editText_password.getText().toString();
                EditText loginUsername=(EditText) findViewById(R.id.editText_name_MA1);
                String username = loginUsername.getText().toString();
                Cursor cursorCheck = myDB.getRecForUsername(username);
                if (cursorCheck.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "That username does not exist", Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor cursor = myDB.getAllData();
                    while (cursor.moveToNext()) {
                        if (cursor.getString(1).equals(username)) {
                            try {
                                if (cursor.getString(4).equals(password))
                                {
                                    Intent intent = new Intent(MainActivity.this, LandingScreen.class);
                                    intent.putExtra("username", username);
                                    startActivity(intent);

                                    //save the login ID and username so that it can be used later
                                    login(cursor.getString(0), cursor.getString(1));

                                    return false;
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "The password does not match what is in the database for that username.", Toast.LENGTH_LONG).show();
                                }
                            }
                            catch (Exception e)
                            {
                                Snackbar.make(textView, "Error: Most likely the problem is that there is no email address or no password for that username in the database.",
                                        Snackbar.LENGTH_INDEFINITE).show();
                            }
                        }
                    }
                }
                return false;
            }
        });

    }

    public void login(String ID, String username)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //concatenate the date information to what was already in sharedPreferences
        editor.putString("ID", ID);
        editor.putString("username", username);
        editor.commit();
    }


}
