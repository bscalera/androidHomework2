package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences=getSharedPreferences("U4_Data", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        Button reg_page_btn=(Button) findViewById(R.id.btn_Reg);
        final EditText userName_EditText=(EditText) findViewById(R.id.Reg_UserName);
        final EditText password_EditText=(EditText) findViewById(R.id.Reg_pass1);


        reg_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString(userName_EditText.getText().toString(),
                        password_EditText.getText().toString());
                editor.commit();

                Intent intent=new Intent(RegistrationPage.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
