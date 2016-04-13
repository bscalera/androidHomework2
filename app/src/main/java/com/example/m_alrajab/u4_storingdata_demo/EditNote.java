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

public class EditNote extends AppCompatActivity {

    String data;
    private String fileName = "noteFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final EditText edit = (EditText) findViewById(R.id.en_note_editText);
        Button saveEdit = (Button) findViewById(R.id.en_save_button);

        //save the time that the page is clicked
        logActivity("You went to the edit note page at: ");

        Bundle extras = getIntent().getExtras();
        String filenameFromEditText;
        filenameFromEditText = extras.getString("filename");

        SharedPreferences userInformation = getSharedPreferences("login", MODE_PRIVATE);
        if (filenameFromEditText.equals(""))
        {
            fileName = userInformation.getString("ID", "") + fileName;
        }
        else
        {
            fileName = userInformation.getString("ID", "") + filenameFromEditText;
        }

        //put the contents of the file into the EditText field
        edit.setText(getFileContent(fileName));

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = edit.getText().toString();

                try {
                    FileOutputStream noteData = openFileOutput(fileName,MODE_PRIVATE);
                    noteData.write(data.getBytes());
                    noteData.close();
                    Snackbar.make(view, "note saved", Snackbar.LENGTH_LONG).show();

                    //save the time that the note was edited
                    logActivity("You edited a note at: ");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                Toast.makeText(EditNote.this, "note loaded", Toast.LENGTH_SHORT).show();
            }
            return temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(EditNote.this, "The file was not found.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void logActivity(String text)
    {
        SharedPreferences userInformation = getSharedPreferences("login", MODE_PRIVATE);
        String preferences = userInformation.getString("ID", "") + "history";

        Date currentTime = new Date();
        String history;
        SharedPreferences sharedPreferences = getSharedPreferences(preferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        history = sharedPreferences.getString("date", "") + text + currentTime.toString() + "\n";
        editor.putString("date", history);
        editor.commit();
    }
}
