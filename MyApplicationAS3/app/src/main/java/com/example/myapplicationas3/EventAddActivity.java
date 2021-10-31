//Programmed by Sam Spark (18040422) and Sarbjot Singh (17190067)
package com.example.myapplicationas3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class EventAddActivity extends AppCompatActivity {

    public static String USERNAME;

    private static final int _Request = 1;
    private EditText eventName;
    private Button repeatButton;
    private TextView repeatOptionText;
    private Button addEvent;
    private Button UpdateButton;
    private Calendar Cal = Calendar.getInstance();
    private int yr = Cal.get(Calendar.YEAR), mon = Cal.get(Calendar.MONTH), day = Cal.get(Calendar.DAY_OF_MONTH);
    private boolean granted = false;
    private int repeatId = 0;
    EventDatabase db = new EventDatabase(this);

    private String[] arrayTimeTexts;
    private String num = ""; //FOR THE DATE of EVENT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText ed = (EditText) findViewById(R.id.editTextDate2);
        eventName = (EditText) findViewById(R.id.eventName);
        addEvent = (Button) findViewById(R.id.addEvent);
        UpdateButton = (Button) findViewById(R.id.UpdateButton);

        Intent extraIntent = getIntent();

        String username = extraIntent.getStringExtra(USERNAME);

        repeatButton = (Button) findViewById(R.id.repeatButton);
        SQLiteDatabase dbs = db.getWritableDatabase();
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog_();
            }
        });

        DatePickerDialog.OnDateSetListener dt = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)  {
                Cal.set(Calendar.YEAR, year);
                Cal.set(Calendar.MONTH, month);
                Cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                Date current = new Date();
                num = Integer.toString(dayOfMonth) +"/" + Integer.toString(month+1) +"/" +Integer.toString(year);
                Date ED = null;
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    ED = sdf.parse(num);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (ED.after(current) || ED.equals(current)) {
                    InputLabel();
                }else if (year == yr && month ==mon && day == dayOfMonth ) { //Top condition takes into account hours and minutes
                    InputLabel();
                }else{
                    Toast WrongDateToast = Toast.makeText(getApplicationContext(), "Please make events for the future, not the past", Toast.LENGTH_SHORT);
                    WrongDateToast.show();
                }


            }

            private void InputLabel() {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

                ed.setText(sdf.format(Cal.getTime()));

            }
        };


        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EventAddActivity.this, dt, Cal.get(Calendar.YEAR), Cal.get(Calendar.MONTH), Cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EventName = eventName.getText().toString();
                db.insertData(EventName, num, "Never", username);
                Toast EventAddedToast = Toast.makeText(getApplicationContext(), "Event added successfully", Toast.LENGTH_SHORT);
                EventAddedToast.show();
                Date date = Cal.getTime(); //get time from user selected date for notification time
                Cursor findRowId = dbs.rawQuery("Select * from events", null); //Selects the rows to find the event id
                findRowId.moveToLast(); //moves to last added event
                scheduleNotification(getNotification(EventName), date.getTime(), findRowId.getPosition(), repeatId); //schedules the notification
                findRowId.close(); //Closes cursor
            }
        });
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EventAddActivity e = new EventAddActivity();
                //boolean updateTable = db.updateTable(eventName.toString(), num, repeatOptionText.toString());
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                intent.putExtra(MainActivity.USERNAME, username);
                startActivity(intent);
            }
        });

    }


    /*public void UpdateData() {

    }*/
    public void OpenDialog_() {
        OpenDialog op = new OpenDialog();
        op.show(getSupportFragmentManager(), "Example");
    }

    public void setRepeatText(int id, String repeatText) {
        repeatOptionText = findViewById(R.id.repeatOptionText);
        repeatOptionText.setText(repeatText); //Changes repeat text to user-selected options
        repeatId = id; //sets the repeatId for the Notification scheduling code to recognise it
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, _Request);
        }

    }
  
    private void scheduleNotification (Notification notification, long timeDue, int id, int repeatId)
    {
        Intent notificationIntent = new Intent(this, DateNotificationBroadcast.class);//Intent to send it to DataNotificationBroadcast class
        notificationIntent.putExtra(DateNotificationBroadcast.NOTIFICATION_ID, id); //sends the id to the Notification
        notificationIntent.putExtra(DateNotificationBroadcast.NOTIFICATION, notification); //sends the notification to the Notification
        PendingIntent pendingBroadcast = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT); //sets up the intent to be sent in the future
        AlarmManager notificationTimer = (AlarmManager)getSystemService(Context.ALARM_SERVICE); //Set up AlarmManager for the timing of notification
        assert notificationTimer != null; //ensure it exists
        switch(repeatId) //repeats based on the repeatId user selected
        {
            case 1: notificationTimer.setRepeating(AlarmManager.ELAPSED_REALTIME, timeDue, AlarmManager.INTERVAL_DAY, pendingBroadcast);

            case 2: notificationTimer.setRepeating(AlarmManager.ELAPSED_REALTIME, timeDue, AlarmManager.INTERVAL_DAY * 2, pendingBroadcast);

            case 3: notificationTimer.setRepeating(AlarmManager.ELAPSED_REALTIME, timeDue, AlarmManager.INTERVAL_DAY * 7, pendingBroadcast);

            case 4: notificationTimer.setRepeating(AlarmManager.ELAPSED_REALTIME, timeDue, AlarmManager.INTERVAL_DAY * 14, pendingBroadcast);

            case 5: notificationTimer.setRepeating(AlarmManager.ELAPSED_REALTIME, timeDue, AlarmManager.INTERVAL_DAY * 30, pendingBroadcast);

            case 6: notificationTimer.setRepeating(AlarmManager.ELAPSED_REALTIME, timeDue, AlarmManager.INTERVAL_DAY * 365, pendingBroadcast);

            default: notificationTimer.set(AlarmManager.ELAPSED_REALTIME, timeDue, pendingBroadcast);
        }
    }

    private Notification getNotification (String content)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default"); //sets up new notification
        builder.setContentTitle("Event Today!");
        builder.setContentText(content); //adds content
        builder.setAutoCancel(true); //ensures user can cancel it
        builder.setChannelId("10001"); //sets the channel ID to the default one
        return builder.build(); //builds notification
    }

}


