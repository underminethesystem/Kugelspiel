package com.example.jules.kugelspiel.database;

/**
 * Created by Freestyly on 22.11.2016.
 */

public class Highscore {
    private int seconds;
    private String name;
    private int id;
    private int rank;

    public Highscore(String name, int seconds, int rank, int id) {
        this.seconds = seconds;
        this.name = name;
        this.id = id;
        this.rank = rank;
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

    public int getRank() { return this.rank;}

    public void setRank(int rank) { this.rank = rank;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.seconds);
        sb.append("      ");
        sb.append(this.name);

        return sb.toString() ;
    }
}
