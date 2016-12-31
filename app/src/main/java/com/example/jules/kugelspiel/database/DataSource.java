package com.example.jules.kugelspiel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Freestyly on 31.12.2016.
 */

public class DataSource {

    private SQLiteDatabase database;
    private DbHelper dbHelper;

    private String[] columns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_NAME,
            DbHelper.COLUMN_SECONDS
    };


    public DataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public Highscore createHighscore(String name, int seconds) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, name);
        values.put(DbHelper.COLUMN_SECONDS, seconds);

        long insertId = database.insert(DbHelper.TABLE_HIGHSCORE, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_HIGHSCORE,
                columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Highscore highscore = cursorToHighscore(cursor);
        cursor.close();

        return highscore;
    }

    private Highscore cursorToHighscore(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(DbHelper.COLUMN_NAME);
        int idSeconds = cursor.getColumnIndex(DbHelper.COLUMN_SECONDS);

        String name = cursor.getString(idName);
        int seconds = cursor.getInt(idSeconds);
        int id = cursor.getInt(idIndex);

        Highscore highscore = new Highscore(name, seconds, id);

        return highscore;
    }

    public List<Highscore> getAllHighscores() {
        List<Highscore> highscores = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_HIGHSCORE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Highscore highscore;

        while(!cursor.isAfterLast()) {
            highscore = cursorToHighscore(cursor);
            highscores.add(highscore);
            cursor.moveToNext();
        }
        cursor.close();

        return highscores;
    }


}
