package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button EntryAdder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EntryAdder = findViewById(R.id.button);
        EntryAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventAddActivity.class); //WHEREEVER MAIN PAGE IS MEANT TO BE
                startActivity(intent);

            }
        });
    }
}