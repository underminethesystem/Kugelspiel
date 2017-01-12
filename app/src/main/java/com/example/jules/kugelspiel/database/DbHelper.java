package com.example.jules.kugelspiel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Freestyly on 16.12.2016.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "Kugel_Spiel.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_HIGHSCORE = "highscore";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SECONDS = "seconds";
    public static final String COLUMN_RANK = "rank";

    public static final String TABLE_MAP = "map";
    public static final String COLUMN_SERIALIZEDMAP = "serializedmap";

    public static final String COLUMN_ID = "_id";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_HIGHSCORE +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_RANK + " INTEGER NOT NULL, " +
                    COLUMN_SECONDS + " INTEGER NOT NULL);";

    public static final String SQL_CREATE_MAP =
            "CREATE TABLE " + TABLE_MAP +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_SERIALIZEDMAP + " STRING NOT NULL);";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_HIGHSCORE + ";";

    public static final String SQL_DELETE_MAP =
            "DROP TABLE " + TABLE_MAP + ";";


    public DbHelper(Context context) {
        super(context, DB_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DATABASE ONCREATE CALLED");
        try {
            db.execSQL(SQL_DELETE_MAP);
            System.out.println("SHOULD'VE CREATED MAP");
            db.execSQL(SQL_CREATE_MAP);
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
