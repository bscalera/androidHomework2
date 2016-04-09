package com.example.m_alrajab.u4_storingdata_demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        

        logActivity();
    }

    public void logActivity()
    {
        Date currentTime = new Date();
        String history;
        SharedPreferences sharedPreferences = getSharedPreferences("history", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        history = sharedPreferences.getString("date", "") + "You went to the notes page at: " + currentTime.toString() + "\n";
        editor.putString("date", history);
        editor.commit();
    }
}
