package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    EditText userName, Password, ReEnterPassword;
    Button Register, SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userName = (EditText) findViewById(R.id.userName);
        Password = (EditText) findViewById(R.id.Password);
        ReEnterPassword = (EditText) findViewById(R.id.ReEnterPassword);
        Register = (Button) findViewById(R.id.Register);
        SignIn = (Button) findViewById(R.id.SignIn);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}