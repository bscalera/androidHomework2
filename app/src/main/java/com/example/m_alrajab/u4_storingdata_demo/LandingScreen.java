package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Context;
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
        textView.setText(textView.getText().toString()+
                        sharedPreferences.getString("admin","Not found")
        );




    }

}
