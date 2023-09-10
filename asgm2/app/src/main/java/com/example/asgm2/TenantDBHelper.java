package com.example.asgm2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TenantDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tenantdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tenants";
    private static final String IC_COL = "icno";
    private static final String NAME_COL = "name";
    private static final String PHONE_COL = "phone_no";
    private static final String EMAIL_COL = "email";

    public TenantDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + IC_COL + " INTEGER PRIMARY KEY,"
                + NAME_COL + " TEXT,"
                + PHONE_COL + " INTEGER,"
                + EMAIL_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewTenant(String icNo, String name, String phoneNo, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IC_COL, icNo);
        values.put(NAME_COL, name);
        values.put(PHONE_COL, phoneNo);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
