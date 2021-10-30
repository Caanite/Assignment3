package com.example.myapplicationas3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class EventDatabase  extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public EventDatabase(Context context) {
        super(context, "Database.db", null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table events(eventName TEXT primary key, eventDate TEXT, repeat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists events");

    }

    public Boolean insertData(String eventName, String eventDate, String repeat) { //Inputting data from user into db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventName", eventName);
        contentValues.put("eventDate", eventDate); //might need to see if this works
        contentValues.put("repeat", repeat);
        long result = db.insert("events", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    /*public Boolean checkEvents(String event) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from events where eventName = ?", new String[]{event});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean checkUserNamePassword(String username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, Password} );
        if (cursor.getCount()> 0) {
            return true;
        }else{
            return false;
        }

    }*/

}

