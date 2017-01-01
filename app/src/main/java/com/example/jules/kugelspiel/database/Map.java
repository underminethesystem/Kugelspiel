package com.example.jules.kugelspiel.database;

/**
 * Created by Freestyly on 01.01.2017.
 */

public class Map {
    private int id;
    private String serializedMap;

    public Map(int id, String serializedMap) {
        this.id = id;
        this.serializedMap = serializedMap;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerializedMap() {
        return this.serializedMap;
    }

    public void setSerializedMap(String serializedMap) {
        this.serializedMap = serializedMap;
    }

    @Override
    public String toString() {
        return this.serializedMap ;
    }
}
