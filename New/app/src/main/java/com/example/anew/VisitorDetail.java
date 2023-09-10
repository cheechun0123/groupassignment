package com.example.anew;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VisitorDetail extends AppCompatActivity {

    private TextView nameTextView, emailTextView, phoneTextView, carPlateTextView;
    private DatabaseHelper dbHelper; // Assuming you have a reference to your SQLite database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_detail);
        getSupportActionBar().setTitle("My Details");


        dbHelper = new DatabaseHelper(this);

        // Initialize TextViews
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        carPlateTextView = findViewById(R.id.carPlateTextView);

        // Retrieve the visitor's email from the intent
        String email = getIntent().getStringExtra("email");

        // Check if a valid visitorId was passed
        if (email != null && !email.isEmpty()) {

            // Use the userEmail to query the database and fetch visitor details
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            try {
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                // Query the database to retrieve visitor details
                Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USERS +
                        " WHERE " + DatabaseHelper.COLUMN_EMAIL + " = ?", new String[]{email});

                if (cursor != null && cursor.moveToFirst()) {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                    @SuppressLint("Range") String userEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
                    @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER));
                    @SuppressLint("Range") String carPlate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CAR_PLATE));

                    // Display visitor details in TextViews
                    nameTextView.setText(" Name: " + name);
                    emailTextView.setText(" Email: " + userEmail);
                    phoneTextView.setText(" Phone: " + phone);
                    carPlateTextView.setText(" Car Plate: " + carPlate);

                    cursor.close();
                } else {
                    // Handle the case where no data is found for the visitorId
                    Toast.makeText(this, "Visitor not found in the database", Toast.LENGTH_SHORT).show();
                }

                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Database error", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case where an invalid visitorId was passed
            Toast.makeText(this, "Invalid visitor ID", Toast.LENGTH_SHORT).show();
        }
    }
}