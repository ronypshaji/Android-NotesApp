package com.example.test_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NotesDb";
    public static final String TABLE_NAME = "NotesTable";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_NOTE = "note";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +  " (" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT meals_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY + " VARCHAR(50) NOT NULL, " +
                COLUMN_NOTE + " DOUBLE(10) NOT NULL) ";
        db.execSQL(sql);

    }

    public Boolean AddNote(String category, String note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_NOTE, note);
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    public Cursor getAllCategory() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT distinct "+COLUMN_CATEGORY +" FROM " + TABLE_NAME + ";";
        return db.rawQuery(sql, null);
    }

    public Cursor getNoteswithCategory(String category) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT "+COLUMN_NOTE+","+COLUMN_ID+" FROM " + TABLE_NAME + " where category='" + category + "'";

        return db.rawQuery(sql, null);
    }

    public int deleteNote(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(id)});
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
