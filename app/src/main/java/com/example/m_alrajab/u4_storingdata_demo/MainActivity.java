package com.example.m_alrajab.u4_storingdata_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button reg_button=(Button) findViewById(R.id.btn_register_MA);
        reg_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        EditText editText_password=(EditText) findViewById(R.id.editText_password_MA2);
        editText_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Intent intent=new Intent(MainActivity.this, RegistrationPage.class);
                startActivity(intent);
                return false;
            }
        });

    }


}
