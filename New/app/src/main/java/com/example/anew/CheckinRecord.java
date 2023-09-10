package com.example.anew;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CheckinRecord extends AppCompatActivity {

    private DBHelper dbHelper;
    private TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_record);

        displayTextView = findViewById(R.id.displayTextView);
        dbHelper = new DBHelper(this);

        // Retrieve data from the database
        Cursor cursor = dbHelper.getAllData(DBHelper.TABLE_CHECKIN);
        StringBuilder dataStringBuilder = new StringBuilder();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                @SuppressLint("Range") String locationName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOCATION_NAME));
                @SuppressLint("Range") String checkinTime = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CHECKIN_TIME));

                // Append the retrieved data to the StringBuilder
                dataStringBuilder.append("ID: ").append(id).append("\n");
                dataStringBuilder.append("Location Name: ").append(locationName).append("\n");
                dataStringBuilder.append("Check-in Time: ").append(checkinTime).append("\n\n");
            }
            cursor.close();
        } else {
            dataStringBuilder.append("No data available.");
        }

        // Display the retrieved data in the TextView
        displayTextView.setText(dataStringBuilder.toString());
    }
}