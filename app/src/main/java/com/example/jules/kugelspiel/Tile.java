package com.example.jules.kugelspiel;

import android.graphics.Color;

/**
 * Created by User on 27.10.2016.
 */

public class Tile {
    public static final int COLOR_WALL = Color.rgb(30,30,10) ;
    public static final int COLOR_FLOOR = Color.rgb(0,111,111);
    public static final int COLOR_START = Color.GRAY;
    public static final int COLOR_GOAL = Color.RED;
    public static final int COLOR_LAVA = Color.rgb(200,50,50);

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
