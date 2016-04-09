package com.example.m_alrajab.u4_storingdata_demo;

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


        logActivity();


        Button create = (Button) findViewById(R.id.notes_create_button);
        Button viewNotes = (Button) findViewById(R.id.notes_view_button);
        Button viewSpace = (Button) findViewById(R.id.notes_freeSpace_button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                "usable space: " + usableSpace + " bytes\n" +
                "free space: " + freeSpace + " bytes";

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
