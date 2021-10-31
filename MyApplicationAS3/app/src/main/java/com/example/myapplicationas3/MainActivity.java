//Programmed by Sam Spark (18040422) and Sarbjot Singh (17190067)

package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button EntryAdder;
    //SQLiteDatabase db = SQLiteDatabase.openDatabase("Database.db", null, SQLiteDatabase.OPEN_READONLY); caused errors
    EventDatabase db;
    EventAdapter eventAdapter;
    ListView events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new EventDatabase(this);
        SQLiteDatabase dbs = db.getWritableDatabase();


        Cursor resultSet = dbs.rawQuery("Select * from events", null);//select everything from events
        events = findViewById(R.id.events);
        eventAdapter = new EventAdapter(this, resultSet); //creates adapter and sets it to the ListView
        events.setAdapter(eventAdapter);


        EntryAdder = findViewById(R.id.button);
        EntryAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventAddActivity.class); //button swaps to the event adder screen
                startActivity(intent);

            }
        });


    }
}