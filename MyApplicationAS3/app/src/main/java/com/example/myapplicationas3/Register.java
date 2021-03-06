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

public class Register extends AppCompatActivity {

    EditText userName, Password, ReEnterPassword;
    Button Register, Sign_in;
    DataBaseCode db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userName = (EditText) findViewById(R.id.userName);
        Password = (EditText) findViewById(R.id.Password);
        ReEnterPassword = (EditText) findViewById(R.id.ReEnterPassword);
        Register = (Button) findViewById(R.id.Register);
        Sign_in = (Button) findViewById(R.id.SignIn);
        db = new DataBaseCode(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = Password.getText().toString();
                String ReEnter = ReEnterPassword.getText().toString();

                if (user.equals("") || pass.equals("")||ReEnter.equals("")){ //no input
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    if (pass.equals(ReEnter)){ //password and other checkers
                        Boolean checkUser = db.checkUsername(user);
                        if (!checkUser){
                            Boolean insert = db.insertData(user, pass);
                            if (insert){
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //WHEREEVER MAIN PAGE IS MEANT TO BE
                                intent.putExtra(MainActivity.USERNAME, user); //send username to main
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
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

        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity();

            }
        });
    }
    public void OpenActivity(){
        Intent intent = new Intent(this,LoginPage.class); //go ahead into main activity
        startActivity(intent);
    }
}