package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //save the time that this page is viewed
        logActivity();


        Button create = (Button) findViewById(R.id.notes_create_button);
        Button viewNote = (Button) findViewById(R.id.notes_view_button);
        Button viewSpace = (Button) findViewById(R.id.notes_freeSpace_button);
        Button viewNote2 = (Button) findViewById(R.id.notes_view_button2);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, CreateNote.class);
                startActivity(intent);
            }
        });

        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, ViewNote.class);
                startActivity(intent);
            }
        });

        viewNote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, ViewNote2.class);
                startActivity(intent);
            }
        });

        viewSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSpace();
            }
        });




    }

    public void showSpace()
    {
        File file = new File(NotesActivity.this.getFilesDir().getPath());
        long totalSpace = file.getTotalSpace();
        long usableSpace = file.getUsableSpace();
        long freeSpace = file.getFreeSpace();

        String space = "total space: " + totalSpace + " bytes\n" +
                totalSpace /1024 /1024 + " megabytes\n\n" +
                "usable space: " + usableSpace + " bytes\n" +
                usableSpace /1024 /1024 + " megabytes\n\n" +
                "free space: " + freeSpace + " bytes\n" +
                freeSpace /1024 /1024 + " megabytes";

        Toast.makeText(NotesActivity.this, space, Toast.LENGTH_LONG).show();



        //http://blog.evizija.si/android-storage
        //http://www.mkyong.com/java/how-to-get-free-disk-space-in-java
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
