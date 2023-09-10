package com.example.anew;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {


    private EditText editTextName,editTextPassword,  editTextPhoneNumber, editTextCarPlate, editTextEmail;
    private Button buttonRegister;
    private RadioGroup radioGroupUserRole;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        dbHelper = new DatabaseHelper(this);

        radioGroupUserRole = findViewById(R.id.radioGroupUserRole);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextCarPlate = findViewById(R.id.editTextCarPlate);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Add a listener to the RadioGroup to show/hide Car Plate field
        radioGroupUserRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonUser) {
                    // User is selected, show the Car Plate field
                    editTextCarPlate.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.radioButtonGuard) {
                    // Guard is selected, hide the Car Plate field
                    editTextCarPlate.setVisibility(View.GONE);
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Get user input
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String carPlate = editTextCarPlate.getText().toString();
                String email = editTextEmail.getText().toString();

                // Check if any of the input fields is empty, validation
                if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                            Register.this,
                            "Please fill in all fields.",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    // Save user data to the database
                    try {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put(DatabaseHelper.COLUMN_NAME, name);
                        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
                        values.put(DatabaseHelper.COLUMN_PHONE_NUMBER, phoneNumber);
                        values.put(DatabaseHelper.COLUMN_CAR_PLATE, carPlate);
                        values.put(DatabaseHelper.COLUMN_EMAIL, email);

                        long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);


                        // Check if data was inserted successfully
                        if (newRowId != -1) {
                            Toast.makeText(
                                    Register.this,
                                    "Registration successful!",
                                    Toast.LENGTH_SHORT
                            ).show();
                            Intent backLogin=new Intent(Register.this,MainActivity.class);
                            startActivity(backLogin);
                        } else {
                            Toast.makeText(
                                    Register.this,
                                    "Registration failed.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                        db.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
