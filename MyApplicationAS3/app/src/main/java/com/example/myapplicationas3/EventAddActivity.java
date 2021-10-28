package com.example.myapplicationas3;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EventAddActivity extends AppCompatActivity {

    private EditText eventName;
    private EditText eventDate;
    private Button repeatButton;
    private TextView repeatOptionText;
    private Button addEvent;

    private String[] arrayTimeTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        repeatButton = (Button) findViewById(R.id.repeatButton);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog_();
            }
        });
    }

    public void OpenDialog_(){
        OpenDialog op = new OpenDialog();
        op.show(getSupportFragmentManager(), "Example");
    }

    public void setRepeatText(String repeatText)
    {
        repeatOptionText = findViewById(R.id.repeatOptionText);
        repeatOptionText.setText(repeatText);
    }
}
