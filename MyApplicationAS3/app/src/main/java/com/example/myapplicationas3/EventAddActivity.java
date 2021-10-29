package com.example.myapplicationas3;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Year;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class EventAddActivity extends AppCompatActivity {

    private EditText eventName;
    private EditText eventDate;
    private Button repeatButton;
    private TextView repeatOptionText;
    private Button addEvent;
    private Calendar Cal = Calendar.getInstance();
    private int yr = Cal.get(Calendar.YEAR), mon = Cal.get(Calendar.MONTH), day = Cal.get(Calendar.DAY_OF_MONTH);

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
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Cal.set(Calendar.YEAR, year);
                Cal.set(Calendar.MONTH, month);
                Cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (year>=yr && dayOfMonth>=day && month>= mon) {
                    InputLabel();
                }else{
                    Toast WrongDateToast = Toast.makeText(getApplicationContext(), "Please make events for the future, not the past", Toast.LENGTH_SHORT);
                    WrongDateToast.show();
                }

            }

            private void InputLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
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

    }

    public void OpenDialog_() {
        OpenDialog op = new OpenDialog();
        op.show(getSupportFragmentManager(), "Example");
    }

    public void setRepeatText(String repeatText) {
        repeatOptionText = findViewById(R.id.repeatOptionText);
        repeatOptionText.setText(repeatText);
    }


}

