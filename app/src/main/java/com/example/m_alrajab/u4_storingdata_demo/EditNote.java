package com.example.m_alrajab.u4_storingdata_demo;

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

public class EditNote extends AppCompatActivity {

    String data;
    private String file_name = "noteFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final EditText edit = (EditText) findViewById(R.id.en_note_editText);
        Button saveEdit = (Button) findViewById(R.id.en_save_button);

        //put the contents of the file into the EditText field
        edit.setText(getFileContent(file_name));

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = edit.getText().toString();

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
}
