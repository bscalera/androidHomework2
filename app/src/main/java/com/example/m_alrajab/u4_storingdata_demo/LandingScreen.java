package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class LandingScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences=getApplication().getSharedPreferences("U4_Data", Context.MODE_PRIVATE);

        TextView textView=(TextView)findViewById(R.id.landingScreen_textView);
        /*
        textView.setText(textView.getText().toString()+
        sharedPreferences.getString(getResources().getString(R.string.U4_Username),"Not found")
        );
        */
        //String message = intent.getStringExtra("username");
        Intent intent = getIntent();
        //String showUsername = intent.getStringExtra(MainActivity."username");
        //String showUsername = (MainActivity)getIntent().getExtras().get("username");
        Bundle extras = getIntent().getExtras();
        String showUsername;
        showUsername = extras.getString("username");
        textView.setText(textView.getText().toString() + showUsername);
    }

}
