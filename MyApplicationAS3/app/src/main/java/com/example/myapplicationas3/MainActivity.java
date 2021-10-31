package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button EntryAdder;
    //SQLiteDatabase db = SQLiteDatabase.openDatabase("Database.db", null, SQLiteDatabase.OPEN_READONLY);
    EventDatabase db;
    EventAdapter eventAdapter;
    ListView events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new EventDatabase(this);
        SQLiteDatabase dbs = db.getWritableDatabase();


        Cursor resultSet = dbs.rawQuery("Select * from events", null);
        events = findViewById(R.id.events);
        eventAdapter = new EventAdapter(this, resultSet);
        events.setAdapter(eventAdapter);


        EntryAdder = findViewById(R.id.button);
        EntryAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventAddActivity.class);
                startActivity(intent);

            }
        });


    }

    /*private void scheduleNotification (Notification notification, long delay)
    {
        Intent notificationIntent = new Intent(this, DateNotificationBroadcast.class);
        notificationIntent.putExtra()
    }*/
}