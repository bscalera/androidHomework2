package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

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

        //start the notes activity when the image is clicked
        ImageView notes_button=(ImageView) findViewById(R.id.landingScreen_notes_btn);
        notes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingScreen.this, NotesActivity.class);
                startActivity(intent);
            }
        });
        //http://stackoverflow.com/questions/4617898/how-can-i-give-imageview-click-effect-like-a-button-in-android

        //Make a record that the user logged in.
        logActivity();
    }
    public void logActivity()
    {
        //saved preferences that should be accessed
        SharedPreferences userInformation = getSharedPreferences("login", MODE_PRIVATE);
        String preferences = userInformation.getString("ID", "") + "history";

        //get the time and then save it
        Date currentTime = new Date();
        String history;
        SharedPreferences sharedPreferences = getSharedPreferences(preferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //concatenate the date information to what was already in sharedPreferences
        history = sharedPreferences.getString("date", "") + "You logged in at: " + currentTime.toString() + "\n";
        editor.putString("date", history);
        editor.apply();
        //https://www.youtube.com/watch?v=8byyh8Lb_xc
    }

}
