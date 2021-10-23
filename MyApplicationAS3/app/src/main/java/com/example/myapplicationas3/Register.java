package com.example.myapplicationas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText userName, Password, ReEnterPassword;
    Button Register, SignIn;
    DataBaseCode db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userName = (EditText) findViewById(R.id.userName);
        Password = (EditText) findViewById(R.id.Password);
        ReEnterPassword = (EditText) findViewById(R.id.ReEnterPassword);
        Register = (Button) findViewById(R.id.Register);
        SignIn = (Button) findViewById(R.id.SignIn);
        db = new DataBaseCode(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = Password.getText().toString();
                String ReEnter = ReEnterPassword.getText().toString();

                if (user.equals("") || pass.equals("")||ReEnter.equals("")){
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    if (pass.equals(ReEnter)){
                        Boolean checkUser = db.checkUsername(user);
                        if (!checkUser){
                            Boolean insert = db.insertData(user, pass);
                            if (insert){
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //WHEREEVER MAIN PAGE IS MEANT TO BE
                            }else{
                                Toast.makeText(Register.this, "Registered unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "User exists, please sign in", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(Register.this, "Incorrect password", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);

            }
        });
    }
}