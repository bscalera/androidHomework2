package com.example.m_alrajab.u4_storingdata_demo;

import android.provider.BaseColumns;

/**
 * Created by benjaminscalera on 4/2/16.
 */
public final class DBContract {

        public DBContract() {}

        /* Inner class that defines the table contents */
        public static abstract class FeedEntry implements BaseColumns {
            public static final String DB_NAME = "U5_Database.db";
            public static final String TABLE_NAME = "DB_Main_Table";
            public static final String COL_ID = "entryid";
            public static final String COL_USERNAME = "username";
            public static final String COL_MAJOR = "major";
            public static final String COL_EMAIL = "email";
            public static final String COL_PASS = "password ";
            public static final int DB_VERSION  = 1 ;

        }
}
