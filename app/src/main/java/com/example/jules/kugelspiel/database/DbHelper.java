package com.example.jules.kugelspiel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Freestyly on 16.12.2016.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "Kugelspiel.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_HIGHSCORE = "highscore";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SECONDS = "seconds";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_HIGHSCORE +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_SECONDS + " INTEGER NOT NULL);";


    public DbHelper(Context context) {
        super(context, DB_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e("DbHelper", "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
