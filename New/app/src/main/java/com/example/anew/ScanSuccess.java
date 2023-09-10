package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScanSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_success);

        // Find the TextView and Button by their IDs
        TextView scannedDataTextView = findViewById(R.id.scannedDataTextView);
        Button backButton = findViewById(R.id.backButton);

        // Get the scanned data from the intent
        String scannedData = getIntent().getStringExtra("scannedData");

        // Display the scanned data in the TextView
        scannedDataTextView.setText("Successfully check in at " + scannedData);

        // Set an OnClickListener for the button to go back to the main activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert the check-in data into the database
                insertCheckInData(scannedData);

                // Navigate back to the main activity
                Intent intent = new Intent(ScanSuccess.this, ScanQR.class);
                startActivity(intent);
                finish(); // Optional: Finish the current activity
            }
        });
    }

    // Method to insert check-in data into the database
    private void insertCheckInData(String locationName) {
        DBHelper dbHelper = new DBHelper(this); // Create an instance of your DBHelper class
        dbHelper.getWritableDatabase(); // Get a writable database instance

        // Create a ContentValues object to store the values you want to insert
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LOCATION_NAME, locationName);
        values.put(DBHelper.COLUMN_CHECKIN_TIME, getCurrentTime());

        // Insert the data into the database
        dbHelper.insertData(DBHelper.TABLE_CHECKIN, null, values);
    }

    // Method to get the current time as a string
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
