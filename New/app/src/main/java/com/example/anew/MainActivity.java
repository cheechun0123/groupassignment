package com.example.anew;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button signUpBtn, loginBtn;
    RadioButton userRBtn, guardMngRBtn;
    RadioGroup rg;
    EditText emailEditText;
    EditText passwordEditText;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(this);

        signUpBtn = findViewById(R.id.signUpBtn);
        userRBtn = findViewById(R.id.userLogin);
        guardMngRBtn = findViewById(R.id.guardMngLogin);
        loginBtn = findViewById(R.id.loginBtn);
        rg = findViewById(R.id.radioGrp);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if the user exists in the SQLite database
                if (databaseHelper.checkUser(email, password)) {
                    // Login successful, navigate to the main activity
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                   // Intent intent = new Intent(MainActivity.this, MainActivity.class);
                   // startActivity(intent);
                   // finish();

                    if (rg.getCheckedRadioButtonId() == R.id.userLogin) {
                        //redirect
                        Intent userLogin=new Intent(MainActivity.this,userPage.class);
                        //Intent passEmail = new Intent(MainActivity.this,VisitorDetail.class);
                        userLogin.putExtra("email", email);
                        //passEmail.putExtra("email", email);
                        startActivity(userLogin);

                    } else if (rg.getCheckedRadioButtonId() == R.id.guardMngLogin) {
                        //check database

                        //redirect
                        Intent guardMngLogin=new Intent(MainActivity.this,GuardManagementPage.class);
                        startActivity(guardMngLogin);
                    }
                } else {
                    // Login failed, display an error message
                    Toast.makeText(MainActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //register
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(MainActivity.this,Register.class);
                startActivity(signup);
            }
        });
                        }


            }

