package com.example.m_alrajab.u4_storingdata_demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //make a record of when the page was accessed
        logActivity();

        SharedPreferences userInformation = getSharedPreferences("login", MODE_PRIVATE);
        userInformation.getString("ID", "");
        String preferences = "ID" + "history";

        //show the history that is saved in sharedPreferences
        TextView history=(TextView)findViewById(R.id.history_viewHistory_textView);
        SharedPreferences sharedPreferences = getSharedPreferences(preferences, MODE_PRIVATE);
        history.setText(sharedPreferences.getString("date", "no history"));



    }

    public void logActivity()
    {
        SharedPreferences userInformation = getSharedPreferences("login", MODE_PRIVATE);
        userInformation.getString("ID", "");
        String preferences = "ID" + "history";

        Date currentTime = new Date();
        String history;
        SharedPreferences sharedPreferences = getSharedPreferences(preferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        history = sharedPreferences.getString("date", "") + "You checked your history at: " + currentTime.toString() + "\n";
        editor.putString("date", history);
        editor.commit();
    }
}
