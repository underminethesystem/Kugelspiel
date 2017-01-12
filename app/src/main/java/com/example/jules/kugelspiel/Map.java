package com.example.jules.kugelspiel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.jules.kugelspiel.database.DataSource;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by User on 27.10.2016.
 */


public class Map {

   /* public static class CurrentMap {
        //0= FLOOR,  1=WALL   ,2= LAVA    ,3= START,   4=GOAL
        public static int[][] map = null;
    }*/

    GameMap currentMap;

    public Tile[][] tiles;
    public ImageView iv;
    public static int width =50;
    public static int height =30;
    public static int tileSize=40;

    static public int mapId = 0;

    public Map(int index,ImageView _iv, GameMap gm) {

        currentMap = gm;

        iv=_iv;
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int[] viewCoords = new int[2];
                iv.getLocationOnScreen(viewCoords);

                int xTouch=(Math.round(event.getX()+viewCoords[0]))/40;//WHY not 40?
                int yTouch=(Math.round(event.getY()+viewCoords[1]))/40;
                Log.v("test","Viewcoords"+viewCoords[0]+"/"+viewCoords[1]);
                Log.v("test","X: "+event.getX()/40+" Touch X: "+xTouch+"\nY: "+event.getY()/40
                        +"Touch Y: "+yTouch+"  hit "+getTileAt(xTouch,yTouch).type
                        +"\nRawX: "+event.getRawX()+"  RawY:"+event.getRawY());
                getTileAt(xTouch,yTouch).toggle();
                draw();
                return true;
            }
        });
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
               /* for(int i=0;i<width-5;i++)
                    tiles[5][i] = new Tile(Tile.TileType.Wall);

                for(int i=5;i<width;i++)
                    tiles[7][i] = new Tile(Tile.TileType.Wall);
                for(int i=5;i<width;i++)
                    tiles[i][3] = new Tile(Tile.TileType.Wall);
                for(int i=1;i<height-6;i++)
                    tiles[10][i] = new Tile(Tile.TileType.Lava);
                */
                for(int i=0;i<width-5;i++)
                    tiles[i][3] = new Tile(Tile.TileType.Wall);
                for(int i=5;i<width;i++)
                    tiles[i][7] = new Tile(Tile.TileType.Wall);
                for(int i=5;i<height-7;i++)
                    tiles[5][i] = new Tile(Tile.TileType.Wall);
                for(int i=10;i<width-1;i++)
                    tiles[i][10] = new Tile(Tile.TileType.Lava);
                tiles[1][1]= new Tile(Tile.TileType.Start);
                tiles[12][12]= new Tile(Tile.TileType.Goal);

                break;
            case 2:
                //FROM ARRAY
                tiles=new Tile[width][height];
                for (int x=0;x < width ;x++) {
                    for (int y = 0; y < height; y++) {
                        tiles[x][y] = new Tile(Tile.TileType.Floor);

                        switch(currentMap._tiles[y][x]) {
                            case 0:
                                tiles[x][y] = new Tile(Tile.TileType.Floor);
                                break;
                            case 1:
                                tiles[x][y] = new Tile(Tile.TileType.Wall);
                                break;
                            case 2:
                                tiles[x][y] = new Tile(Tile.TileType.Lava);
                                break;
                            case 3:
                                tiles[x][y] = new Tile(Tile.TileType.Start);
                                break;
                            case 4:
                                tiles[x][y] = new Tile(Tile.TileType.Goal);
                                break;
                        }
                    }
                }

                break;
            default:
                System.out.print("Invalid Map index");
                break;
        }
    }


    public void draw(){
        Bitmap bitmap = Bitmap.createBitmap(tileSize*width, tileSize*height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        for (int x=0;x<width;x++) {
            for (int y = 0; y < height; y++) {
                paint.setColor(tiles[x][y].getColor());
                paint.setStyle(Paint.Style.FILL);
                canvas.drawRect(x*tileSize,y*tileSize,(x+1)*tileSize,(y+1)*tileSize,paint);
                //Border
                paint.setStrokeWidth(3);
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(x*tileSize,y*tileSize,(x+1)*tileSize,(y+1)*tileSize, paint);
            }
        }
        iv.setImageBitmap(bitmap);
    }

    public Tile getTileAt(float x,float y){
        return tiles[(int)x][(int)y];

    }
    public Tile getTileAt(int x,int y){
        return tiles[x][y];

    }
}
