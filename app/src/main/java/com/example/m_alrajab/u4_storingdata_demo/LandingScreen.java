package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        Bundle extras = getIntent().getExtras();
        String showUsername;
        showUsername = extras.getString("username");
        //http://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        textView.setText(textView.getText().toString() + showUsername);

        //history button
        Button history_button=(Button) findViewById(R.id.landingScreen_history_btn);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingScreen.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

}
