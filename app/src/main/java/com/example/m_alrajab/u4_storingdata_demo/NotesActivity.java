package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    private String fileName = "noteFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //save the time that this page is viewed
        logActivity("You went to the notes page at: ");


        Button create = (Button) findViewById(R.id.notes_create_button);
        final EditText filename = (EditText) findViewById(R.id.notes_fileName_text);
        Button edit = (Button) findViewById(R.id.notes_edit_button);
        Button viewNote = (Button) findViewById(R.id.notes_view_button);
        Button getDirectory = (Button) findViewById(R.id.notes_list_button);
        Button deleteNote = (Button) findViewById(R.id.notes_delete_button);
        Button viewSpace = (Button) findViewById(R.id.notes_freeSpace_button);
        Button viewBlockSize = (Button) findViewById(R.id.notes_blockSize_button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, CreateNote.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, EditNote.class);
                intent.putExtra("filename", filename.getText().toString());
                startActivity(intent);
            }
        });

        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, ViewNote.class);
                intent.putExtra("filename", filename.getText().toString());
                startActivity(intent);
            }
        });

        getDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listOfFiles[] = fileList();
                //http://developer.android.com/guide/topics/data/data-storage.html#filesInternal
                Toast.makeText(NotesActivity.this, Arrays.toString(listOfFiles), Toast.LENGTH_LONG).show();
                System.out.println(Arrays.toString(listOfFiles));
                //http://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
            }
        });

        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences userInformation = getSharedPreferences("login", MODE_PRIVATE);
                if ((filename.getText().toString().equals("")) == false)
                {
                    fileName = userInformation.getString("ID", "") + filename.getText().toString();
                }

                String path = getFilesDir().getAbsolutePath();
                File file = new File(path, fileName);
                boolean delete = file.delete();
                String deleteMessage = "File deleted: " + delete + "\n" + path + "/" + fileName + " ";
                Log.w("Delete Check", deleteMessage);
                //http://stackoverflow.com/questions/5486529/delete-file-from-internal-storage
                Toast.makeText(NotesActivity.this, deleteMessage, Toast.LENGTH_LONG).show();

                if (delete)
                    logActivity("You deleted a note at: ");
            }
        });

        viewSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSpace();
            }
        });

        viewBlockSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File path = Environment.getDataDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                //http://stackoverflow.com/questions/4595334/get-free-space-on-internal-memory

                String block = String.format("blocksize: " + blockSize + " bytes per block");
                Toast.makeText(NotesActivity.this, block, Toast.LENGTH_LONG).show();
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
