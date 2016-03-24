package com.example.m_alrajab.u4_storingdata_demo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

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


        EditText reg_page_date_picker=(EditText) findViewById(R.id.Reg_dateOfBirth);
        final int day, month, year;
        final Context context;

        reg_page_date_picker.setOnClickListener(new View.OnClickListener() {
            //@Override


            class DatePickerFragment extends DialogFragment
                    implements DatePickerDialog.OnDateSetListener {

                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    // Use the current date as the default date in the picker
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    // Create a new instance of DatePickerDialog and return it
                    return new DatePickerDialog(getActivity(), this, year, month, day);
                }

                public void onDateSet(DatePicker view, int year, int month, int day) {
                    // Do something with the date chosen by the user
                }
            }
        });


    }

}
