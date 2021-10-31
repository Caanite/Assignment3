package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button EntryAdder;
    //SQLiteDatabase db = SQLiteDatabase.openDatabase("Database.db", null, SQLiteDatabase.OPEN_READONLY);
    EventDatabase db;
    EventAdapter eventAdapter;
    ListView events;
    Button DeleteButton;
    EditText DeleteEditText;
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
        DeleteEditText = (EditText) findViewById(R.id.DeleteEditText);
        DeleteButton = (Button) findViewById(R.id.DeleteButton);
        EntryAdder = findViewById(R.id.button);
        EntryAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventAddActivity.class);
                startActivity(intent);

            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean DataDeleted = false;
                String checkInput = DeleteEditText.getText().toString();
                if (checkInput.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter the name of event you want deleted", Toast.LENGTH_SHORT).show();
                } else {
                    DataDeleted = db.deleteFromTable(checkInput);
                    if (DataDeleted){
                        Toast.makeText(getApplicationContext(), "Event deleted successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Event does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    /*private void scheduleNotification (Notification notification, long delay)
    {
        Intent notificationIntent = new Intent(this, DateNotificationBroadcast.class);
        notificationIntent.putExtra()
    }*/
}