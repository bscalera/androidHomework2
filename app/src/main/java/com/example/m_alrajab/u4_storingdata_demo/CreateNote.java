package com.example.m_alrajab.u4_storingdata_demo;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class CreateNote extends AppCompatActivity {

    String data;
    private String file_name = "noteFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        //save the time that this page is viewed
        logActivity();

        final EditText note = (EditText) findViewById(R.id.cn_note_editText);
        Button create = (Button) findViewById(R.id.cn_create_button);

        //create note button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = note.getText().toString();

                try {
                    FileOutputStream noteData = openFileOutput(file_name,MODE_PRIVATE);
                    noteData.write(data.getBytes());
                    noteData.close();
                    Snackbar.make(view, "note saved", Snackbar.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(CreateNote.this, getFileContent(file_name), Toast.LENGTH_SHORT).show();

            }
        });
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
                //Toast.makeText(CreateNote.this, "note loaded", Toast.LENGTH_SHORT).show();
            }
            return temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(CreateNote.this, "The file was not found.", Toast.LENGTH_LONG).show();
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
        history = sharedPreferences.getString("date", "") + "You went to the create note page at: " + currentTime.toString() + "\n";
        editor.putString("date", history);
        editor.commit();
    }
}
