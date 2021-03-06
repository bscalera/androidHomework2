package com.example.m_alrajab.u4_storingdata_demo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrationPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private U5_DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDB =new U5_DatabaseHelper(this);

        final EditText usrname=(EditText) findViewById(R.id.Reg_UserName);
        final Spinner mjr= (Spinner) findViewById(R.id.Reg_SMajor);
        final EditText pss=(EditText) findViewById(R.id.Reg_pass1);

        final EditText userName_EditText = (EditText) findViewById(R.id.Reg_UserName);
        final EditText password_EditText = (EditText) findViewById(R.id.Reg_pass1);
        final EditText email_EditText = (EditText) findViewById(R.id.Reg_Email);
        final EditText confirm_EditText = (EditText) findViewById(R.id.Reg_pass2);

        final EditText name_EditText = (EditText) findViewById(R.id.Reg_SName);
        final EditText birthday_EditText = (EditText) findViewById(R.id.Reg_dateOfBirth);


        sharedPreferences = getSharedPreferences("U4_Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Button regBtn=(Button) findViewById(R.id.btn_Reg);
/*
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/

        Button reg_page_btn = (Button) findViewById(R.id.btn_Reg);



        reg_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make sure all fields are filled in
                if (userName_EditText.getText().length() == 0 || name_EditText.getText().length() == 0
                        || email_EditText.getText().length() == 0 || password_EditText.getText().length() == 0
                        || confirm_EditText.getText().length() == 0 || birthday_EditText.getText().length() == 0)
                {
                    CharSequence text = "All fields must be filled in.";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    //check if the username exists
                    if (checkRecord(userName_EditText.getText().toString())) {
                        CharSequence text = "That username exists";
                        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        /*
                        check if there is a letter and a number in the password
                        http://stackoverflow.com/questions/30327319/how-to-know-if-a-string-only-contains-0-to-9-and-no-other-characters
                        http://stackoverflow.com/questions/10575624/java-string-see-if-a-string-contains-only-numbers-and-not-letters
                        http://stackoverflow.com/questions/11533474/java-how-to-test-if-a-string-contains-both-letter-and-number
                        */
                        if (((password_EditText.getText().toString().matches(".*[a-zA-Z].*")) && (password_EditText.getText().toString().matches(".*[0-9].*"))) == false)
                        {
                            CharSequence text = "Password must contain a letter and a number";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else {
                            if (password_EditText.getText().length() < 6) {
                                /*
                                If the password is not long enough, a message is shown that says that the password needs to be longer.
                                http://developer.android.com/guide/topics/ui/notifiers/toasts.html
                                */
                                Context context = getApplicationContext();
                                CharSequence text = "Password must be at least 6 characters";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                //Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_LONG);
                            } else {
                                //check if the password matches
                                if (password_EditText.getText().toString().equals(confirm_EditText.getText().toString())) {
                                    //http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case
                                    if (email_EditText.getText().toString().toLowerCase().contains("montclair.edu")) {
                                        //insert the data into the database
                                        boolean isInserted = myDB.insertData(
                                                usrname.getText().toString(),
                                                mjr.getSelectedItem().toString(),  //http://stackoverflow.com/questions/1947933/how-to-get-spinner-value
                                                pss.getText().toString(),
                                                email_EditText.getText().toString()
                                                //birthday_EditText.getText().toString()

                                        );
                                        if (isInserted = true)
                                            Toast.makeText(RegistrationPage.this, "Data inserted", Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(RegistrationPage.this, "Data not inserted", Toast.LENGTH_LONG).show();

                                        /*
                                        editor.putString(getResources().getString(R.string.U4_Username),
                                                userName_EditText.getText().toString()
                                                );
                                        editor.putString(getResources().getString(R.string.U4_Password),
                                                userName_EditText.getText().toString()
                                                );
                                        editor.commit();
                                        */
                                        /*
                                        editor.putString(userName_EditText.getText().toString(),
                                        password_EditText.getText().toString());
                                        editor.commit();
                                        */

                                        Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                     /*
                                     If the email is not a montclair email, a message is shown that says that the email needs to be a montclair email.
                                     */

                                        Context context = getApplicationContext();
                                        CharSequence text = "Use a montclair.edu email address";
                                        int duration = Toast.LENGTH_LONG;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Password and confirm password should be the same";
                                    int duration = Toast.LENGTH_LONG;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        }
                    }
                }
            }
        });

        //This should set the title for the spinner.
        Spinner spinner = (Spinner) findViewById(R.id.Reg_SMajor);
        spinner.setPrompt("@string/majorSpinnerDefaultValue");
        //http://stackoverflow.com/questions/6071026/how-to-add-title-in-spinner-in-android
/*


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

        //http://stackoverflow.com/questions/6516320/datetime-datatype-in-java
        //https://examples.javacodegeeks.com/core-java/util/date/java-util-date-example-how-to-use-date
        Date date = new Date();

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

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        Date date = dateFormat.parse(reg_page_date_picker.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public boolean checkRecord (String username)
    {
        Cursor cursor = myDB.getRecForUsername(username);
        if (cursor.getCount() == 0)
            return false;  //The username does not exist
        else
            return true;  //The username exists
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
