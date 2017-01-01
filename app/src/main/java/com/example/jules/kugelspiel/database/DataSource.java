package com.example.jules.kugelspiel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.jules.kugelspiel.*;

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
            DbHelper.COLUMN_SECONDS,
            DbHelper.COLUMN_RANK
    };

    private String[] columns_map = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_SERIALIZEDMAP
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


    public Highscore createHighscore(String name, int seconds, int rank) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, name);
        values.put(DbHelper.COLUMN_SECONDS, seconds);
        values.put(DbHelper.COLUMN_RANK, rank);

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
        int idRank = cursor.getColumnIndex(DbHelper.COLUMN_RANK);

        String name = cursor.getString(idName);
        int seconds = cursor.getInt(idSeconds);
        int id = cursor.getInt(idIndex);
        int rank = cursor.getInt(idRank);

        Highscore highscore = new Highscore(name, seconds, rank, id);

        return highscore;
    }

    public List<Highscore> getAllHighscores() {
        List<Highscore> highscores = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_HIGHSCORE,
                columns, null, null, null, null, dbHelper.COLUMN_RANK + " ASC");

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

    public void deleteHighscore(Highscore high) {
        int id = high.getId();
        database.delete(dbHelper.TABLE_HIGHSCORE, dbHelper.COLUMN_ID + "=" + id, null);
    }

    public void updateHighscore(Highscore high) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_NAME, high.getName());
        values.put(dbHelper.COLUMN_SECONDS, high.getSeconds());
        values.put(dbHelper.COLUMN_RANK, high.getRank());

        database.update(dbHelper.TABLE_HIGHSCORE,
                values,
                dbHelper.COLUMN_ID + "=" + high.getId(),
                null);
    }

    public Map createMap(String serializedMap) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_SERIALIZEDMAP, serializedMap);

        long insertId = database.insert(DbHelper.TABLE_MAP, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_MAP,
                columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Map map = cursorToMap(cursor);
        cursor.close();

        return map;
    }

    private Map cursorToMap(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idSerialized = cursor.getColumnIndex(DbHelper.COLUMN_SERIALIZEDMAP);

        String ser = cursor.getString(idSerialized);
        int id = cursor.getInt(idIndex);

        Map map = new Map(id, ser);

        return map;
    }

    public List<Map> getAllMaps() {
        List<Map> maps = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_MAP,
                columns, null, null, null, null, dbHelper.COLUMN_RANK + " ASC");

        cursor.moveToFirst();
        Map map;

        while(!cursor.isAfterLast()) {
            map = cursorToMap(cursor);
            maps.add(map);
            cursor.moveToNext();
        }
        cursor.close();

        return maps;
    }

    public void deleteMap(Map map) {
        int id = map.getId();
        database.delete(dbHelper.TABLE_MAP, dbHelper.COLUMN_ID + "=" + id, null);
    }

    public void updateMap(Map map) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_SERIALIZEDMAP, map.getSerializedMap());

        database.update(dbHelper.TABLE_MAP,
                values,
                dbHelper.COLUMN_ID + "=" + map.getId(),
                null);
    }
}
