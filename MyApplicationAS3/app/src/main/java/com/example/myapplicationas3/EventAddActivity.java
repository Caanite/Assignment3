package com.example.myapplicationas3;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
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
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class EventAddActivity extends AppCompatActivity {

    private static final int _Request = 1;
    private EditText eventName;
    private Button repeatButton;
    private TextView repeatOptionText;
    private Button addEvent;
    private Calendar Cal = Calendar.getInstance();
    private int yr = Cal.get(Calendar.YEAR), mon = Cal.get(Calendar.MONTH), day = Cal.get(Calendar.DAY_OF_MONTH);
    private boolean granted = false;
    EventDatabase db = new EventDatabase(this);

    private String[] arrayTimeTexts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText ed = (EditText) findViewById(R.id.editTextDate2);

        repeatButton = (Button) findViewById(R.id.repeatButton);
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
                String num = Integer.toString(dayOfMonth) +"/" + Integer.toString(month+1) +"/" +Integer.toString(year);
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
        db.insertData()

    }

    public void OpenDialog_() {
        OpenDialog op = new OpenDialog();
        op.show(getSupportFragmentManager(), "Example");
    }

    public void setRepeatText(String repeatText) {
        repeatOptionText = findViewById(R.id.repeatOptionText);
        repeatOptionText.setText(repeatText);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, _Request);
        }

    }

}


