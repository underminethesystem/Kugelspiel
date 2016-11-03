package com.example.jules.kugelspiel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.Settings;
import android.widget.ImageView;

/**
 * Created by User on 27.10.2016.
 */


public class Map {
    public Tile[][] tiles;
    public int width =30;
    public int height =40;
    public int tileSize=40;

    public Map(int index) {
        switch(index) {
            case 1://static Map 1 etc
                tiles=new Tile[width][height];
                for (int x=0;x<width;x++) {
                    for (int y = 0; y < height; y++) {
                        if (x == 0 || y == 0 || x == width-1 || y == height-1)//Border always Wall
                            tiles[x][y] = new Tile(Tile.TileType.Wall);
                        else
                            tiles[x][y] = new Tile(Tile.TileType.Floor);
                    }
                }
                for(int i=0;i<width-5;i++)
                    tiles[5][i] = new Tile(Tile.TileType.Wall);

                for(int i=5;i<width;i++)
                    tiles[7][i] = new Tile(Tile.TileType.Wall);
                for(int i=5;i<width;i++)
                    tiles[i][3] = new Tile(Tile.TileType.Wall);
                for(int i=1;i<height-6;i++)
                    tiles[10][i] = new Tile(Tile.TileType.Lava);


                tiles[1][1]= new Tile(Tile.TileType.Start);
                tiles[12][12]= new Tile(Tile.TileType.Goal);

                break;
            case 2:
                break;
            default:
                System.out.print("Invalid Map index");
                break;
        }
    }

    public void draw(ImageView iv){
        System.out.println("Map.draw called");
        Bitmap bitmap = Bitmap.createBitmap(tileSize*width, tileSize*height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
       for (int x=0;x<width;x++) {
            for (int y = 0; y < height; y++) {
                paint.setColor(tiles[x][y].getColor());
                canvas.drawRect(x*tileSize,y*tileSize,(x+1)*tileSize,(y+1)*tileSize,paint);

            }
        }

        iv.setImageBitmap(bitmap);


    }






}
