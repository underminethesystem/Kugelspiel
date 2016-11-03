package com.example.jules.kugelspiel;

import android.graphics.Color;

/**
 * Created by User on 27.10.2016.
 */

public class Tile {
    public static final int COLOR_WALL = Color.BLACK ;
    public static final int COLOR_FLOOR = Color.GREEN;
    public static final int COLOR_START = Color.YELLOW;
    public static final int COLOR_GOAL = Color.WHITE;
    public static final int COLOR_LAVA = Color.RED;

    public enum TileType{Floor,Wall,Start,Goal,Lava};

    public Tile(Tile.TileType t){
        type=t;
        if(t==TileType.Wall)
            isWalkable = false;
        else
            isWalkable= true;
    }

    public TileType type;
    public boolean isWalkable;

    public int getColor(){
        switch (type){
            case Floor: return COLOR_FLOOR ;
            case Wall: return COLOR_WALL ;
            case Start: return COLOR_START ;
            case Goal: return COLOR_GOAL ;
            case Lava: return COLOR_LAVA ;
            default: System.out.print("Invalid Tiletype");
                return COLOR_FLOOR;

        }



    }
}
