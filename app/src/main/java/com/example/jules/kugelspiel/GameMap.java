package com.example.jules.kugelspiel;

import java.io.Serializable;

/**
 * Created by jules on 05.01.2017.
 */

public class GameMap implements Serializable{
    public int[][] _tiles;

    public GameMap(int[][] tiles){
        _tiles = tiles;
    }
}
