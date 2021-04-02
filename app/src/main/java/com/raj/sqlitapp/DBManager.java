package com.raj.sqlitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context context){
        this.context=context;
    }

    public DBManager open() throws SQLException{

        dbHelper=new DatabaseHelper(context);
        database=dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String title,String desc){

        ContentValues cv=new ContentValues();
        cv.put(DatabaseHelper.SUBJECT,title);
        cv.put(DatabaseHelper.DESC,desc);
        database.insert(DatabaseHelper.Table_Name,null,cv);

    }

    public Cursor fetch(){
        String[] columns=new String[]{DatabaseHelper._ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC };
        Cursor cursor=database.query(DatabaseHelper.Table_Name,columns,null,null,null,null,null);

        if (cursor!=null){
            cursor.moveToFirst();

        }
        return cursor;
    }


     public int update(long _id,String title,String desc){
        ContentValues cv=new ContentValues();
        cv.put(DatabaseHelper.SUBJECT,title);
        cv.put(DatabaseHelper.DESC,desc);
        int i=database.update(DatabaseHelper.Table_Name,cv,DatabaseHelper._ID +" = "+_id,null);

        return i;
     }

     public void delete(long id){
        database.delete(DatabaseHelper.Table_Name,DatabaseHelper._ID + " = " +id,null);
     }

}
