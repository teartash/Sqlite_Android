package com.raj.sqlitapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String Table_Name="countries";
    public static final String _ID="_id";
    public static final String SUBJECT="subject";
    public static final String DESC="description";
     static final String DB_NAME="tirtash.db";
     static final int DB_VERSION=1;

     public static final String CREATE_TABLE="create table "+Table_Name+ "("
             +_ID +" INTEGER PRIMARY KEY  AUTOINCREMENT ," +SUBJECT +" TEXT NOT NULL ,"
             +DESC+" TEXT );";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);

    }
}
