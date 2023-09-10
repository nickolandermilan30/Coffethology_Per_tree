package com.example.coffeetectapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ResultHistory.db";
    private static final String TABLE_NAME = "results";
    private static final String COL_ID = "id";
    private static final String COL_DISEASE_NAME = "disease_name";
    private static final String COL_SEVERITY_LEVEL = "severity_level";
    private static final String COL_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DISEASE_NAME + " TEXT, " +
                COL_SEVERITY_LEVEL + " TEXT, " +
                COL_IMAGE + " BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertResult(String diseaseName, String severityLevel, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DISEASE_NAME, diseaseName);
        contentValues.put(COL_SEVERITY_LEVEL, severityLevel);
        contentValues.put(COL_IMAGE, image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public Cursor getAllResults() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
}}
