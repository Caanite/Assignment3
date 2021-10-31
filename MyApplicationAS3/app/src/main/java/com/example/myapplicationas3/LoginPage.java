//Programmed by Sam Spark (18040422) and Sarbjot Singh (17190067)

package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText username, password;
    Button Signin;
    Button Register;
    DataBaseCode db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signingin_layout);

        username = (EditText) findViewById(R.id.SignIn_userName);
        password = (EditText) findViewById(R.id.SignIn_Password);
        Signin = (Button) findViewById(R.id.SignIn_SignIn_button);
        Register = (Button) findViewById(R.id.LoginPageRegister);
        db = new DataBaseCode(this);
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(LoginPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else{
                    Boolean checkuserPass = db.checkUserNamePassword(user,pass);
                    if (checkuserPass){
                        Toast.makeText(LoginPage.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginPage.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

    }
}