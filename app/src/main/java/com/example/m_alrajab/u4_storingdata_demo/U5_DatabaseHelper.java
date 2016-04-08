package com.example.m_alrajab.u4_storingdata_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
                FeedEntry.COL_PASS + " TEXT" + ")";

        db.execSQL(SQL_String);
//FeedEntry.COL_BIRTHDAY + "TEXT" + ")";

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
    public boolean insertData(String username, String major, String password, String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COL_USERNAME, username);
        contentValues.put(FeedEntry.COL_MAJOR, major);
        contentValues.put(FeedEntry.COL_PASS, password);
        contentValues.put(FeedEntry.COL_EMAIL, email);
        //contentValues.put(FeedEntry.COL_BIRTHDAY, date);
        long result = db.insert(FeedEntry.TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + FeedEntry.TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String username, String major, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COL_ID, id);
        contentValues.put(FeedEntry.COL_USERNAME, username);
        contentValues.put(FeedEntry.COL_MAJOR, major);
        contentValues.put(FeedEntry.COL_PASS, password);
        db.update(FeedEntry.TABLE_NAME, contentValues, "ID=? ", new String[]{id});
        return true;
    }

    public boolean updateMajor(String id, String major)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COL_ID, id);
        contentValues.put(FeedEntry.COL_MAJOR, major);
        db.update(FeedEntry.TABLE_NAME, contentValues, "entryid=? ", new String[]{id});
        return true;
    }

    public boolean updateEmail(String id, String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COL_ID, id);
        contentValues.put(FeedEntry.COL_EMAIL, email);
        db.update(FeedEntry.TABLE_NAME, contentValues, "entryid=? ", new String[]{id});
        return true;
    }

    public boolean updatePassword(String id, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COL_ID, id);
        contentValues.put(FeedEntry.COL_PASS, password);
        db.update(FeedEntry.TABLE_NAME, contentValues, "entryid=? ", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        System.out.println("deleteData(" + id + ")");
        return db.delete(FeedEntry.TABLE_NAME, "entryid = ?", new String[]{id}); // return 0 or less if nothing d
    }

    public void deleteDatabase(String dbName){
        this.deleteDatabase(dbName);
    }

    public Cursor getRecForUsername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            Cursor res = db.rawQuery("SELECT " + FeedEntry.COL_ID + " FROM " + FeedEntry.TABLE_NAME + " WHERE " + FeedEntry.COL_USERNAME + " LIKE '" + username + "' ; ", null);
            return res;
        } catch (Exception e){
            Log.e("Hmm", "fix it");
            return null;
        }
    }

}
