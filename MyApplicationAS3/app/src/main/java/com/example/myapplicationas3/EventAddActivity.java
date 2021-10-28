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

        repeatButton = findViewById(R.id.repeatButton);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
    }

    private void alertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Resources res = getResources();

        arrayTimeTexts = res.getStringArray(R.array.Time_array);

        builder.setTitle(R.string.repeat).setMessage(R.string.repeatText).setItems(R.array.Time_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                repeatOptionText.setText(arrayTimeTexts[which]);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
