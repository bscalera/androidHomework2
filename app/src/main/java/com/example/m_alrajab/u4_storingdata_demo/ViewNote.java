package com.example.m_alrajab.u4_storingdata_demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class ViewNote extends AppCompatActivity {

    private String file_name = "noteFile";
    TextView viewNote = (TextView) findViewById(R.id.viewNote_textView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        logActivity();


        viewNote.setText(getFileContent(file_name));
    }

    public String getFileContent (String fileName)
    {
        try{
            FileInputStream noteData = openFileInput(fileName);
            int c;
            String temp="";

            while ((c = noteData.read()) != -1)
            {
                temp = temp + Character.toString((char)c);
                Toast.makeText(ViewNote.this, "note loaded", Toast.LENGTH_SHORT).show();
            }
            return temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ViewNote.this, "The file was not found.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void logActivity()
    {
        Date currentTime = new Date();
        String history;
        SharedPreferences sharedPreferences = getSharedPreferences("history", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        history = sharedPreferences.getString("date", "") + "You viewed a note at: " + currentTime.toString() + "\n";
        editor.putString("date", history);
        editor.commit();
    }
}
