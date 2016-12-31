package com.example.jules.kugelspiel.database;

/**
 * Created by Freestyly on 22.11.2016.
 */

public class Highscore {
    private int seconds;
    private String name;
    private int id;

    public Highscore(String name, int seconds, int id) {
        this.seconds = seconds;
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
