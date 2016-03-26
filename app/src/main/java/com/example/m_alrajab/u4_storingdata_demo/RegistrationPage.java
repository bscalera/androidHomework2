package com.example.m_alrajab.u4_storingdata_demo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

public class RegistrationPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("U4_Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Button reg_page_btn = (Button) findViewById(R.id.btn_Reg);
        final EditText userName_EditText = (EditText) findViewById(R.id.Reg_UserName);
        final EditText password_EditText = (EditText) findViewById(R.id.Reg_pass1);


        reg_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString(userName_EditText.getText().toString(),
                        password_EditText.getText().toString());
                editor.commit();

                Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        /*
        Here is the code for the date picker.
        When the EditText field is clicked, the keyboard does not appear on the screen because it has the attribute "android:focusable="false"".
        When the EditText field is clicked the date picker appears.
        After a date is picked, it is put into the EditText field.

        http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
        http://stackoverflow.com/questions/17808373/popup-datepicker-for-edittext
        http://developer.android.com/reference/android/app/DatePickerDialog.html
        http://developer.android.com/guide/topics/ui/controls/pickers.html
        http://stackoverflow.com/questions/7454390/changing-textview-text-on-button-click-android
        */


        final EditText reg_page_date_picker = (EditText) findViewById(R.id.Reg_dateOfBirth);
        final int day, month, year;
        final Context context;

        reg_page_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "date picker");

            }
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
                    reg_page_date_picker.setText(new StringBuilder()

                            //Month is 0 so add 1
                    .append(month +1).append("/").append(day).append("/").append(year));
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RegistrationPage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.m_alrajab.u4_storingdata_demo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RegistrationPage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.m_alrajab.u4_storingdata_demo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
