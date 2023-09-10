package com.example.anew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.TextView;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CHECKIN";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CHECKIN = "checkin_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOCATION_NAME = "location_name";
    public static final String COLUMN_CHECKIN_TIME = "checkin_time";



    private static final String SCRIPT_CREATE_DATABASE = "CREATE TABLE " + TABLE_CHECKIN + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LOCATION_NAME + " TEXT, " +
            COLUMN_CHECKIN_TIME + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This method is called when the database needs to be upgraded.
        // You can implement database schema changes here if needed.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKIN);
        onCreate(db);
    }

    // Method to insert data into the database
    public void insertData(String tableName, String nullColumnHack, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insertOrThrow(tableName, nullColumnHack, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    // Method to retrieve data from the database
    public Cursor getAllData(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + tableName, null);
    }
}
