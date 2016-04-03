package com.example.m_alrajab.u4_storingdata_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.m_alrajab.u4_storingdata_demo.DBContract.*;

/**
 * Created by benjaminscalera on 4/1/16.
 */
public class U5_DatabaseHelper extends SQLiteOpenHelper {
    public U5_DatabaseHelper(Context context) {
        super(context, FeedEntry.DB_NAME , null, FeedEntry.DB_VERSION );
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase  db) {
        String SQL_String = "CREATE TABLE " + FeedEntry.TABLE_NAME + "(" +
                FeedEntry.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FeedEntry.COL_USERNAME + " TEXT," +
                FeedEntry.COL_MAJOR + " TEXT," +
                FeedEntry.COL_EMAIL + " TEXT," +
                FeedEntry.COL_PASS + " TEXT" +")";
        db.execSQL(SQL_String);

        /*
        http://developer.android.com/training/basics/data-storage/databases.html
        http://stackoverflow.com/questions/25562508/autoincrement-is-only-allowed-on-an-integer-primary-key-android
        */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+FeedEntry.TABLE_NAME);
        onCreate(db);

    }
}
