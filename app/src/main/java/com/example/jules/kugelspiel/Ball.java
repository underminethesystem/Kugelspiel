package com.example.jules.kugelspiel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by jules on 18.11.2016.
 */

public class Ball {

    public float x, y;
    public Map map;
    public static int COLLISION_CHECK_AMOUNT=20; //amt of points to check on ball

    // TODO: start x and start y depending on map
    public Ball(float _x, float _y,Map m){
        x = _x;
        y = _y;
        map =m;
    }
    public boolean collision(){
        float r=0.5f;
        float centerX= x+DirectionManager.xDir+0.5f;//simulate move
        float centerY= y+DirectionManager.yDir+ 0.5f;
        for(int i=0;i<COLLISION_CHECK_AMOUNT;i++){
            float xPos= r*(float)Math.cos(Math.toRadians(360/COLLISION_CHECK_AMOUNT*i))+centerX;
            float yPos= r*(float)Math.sin(Math.toRadians(360/COLLISION_CHECK_AMOUNT*i))+centerY;
            if (!map.getTileAt(xPos,yPos).isWalkable){
                //System.out.println("Collision detected at X:"+xPos+" Y: "+yPos);
                return true;
            }
        }
        return false;
    }
    public void move() {
        if (collision()) {//would collide next frame TODO: move ball to wall as close as possible and/or roll along wall
            //probably set either xDir,yDir or both(corner) to 0 to move along wall
           // System.out.println("Collision detected");
        } else {
            x += DirectionManager.xDir;
            y += DirectionManager.yDir;
        }
        if(map.getTileAt(x,y).type== Tile.TileType.Goal){
            for (int i=0;i<10;i++)
             System.out.println("YOU WIN!!!!!!!");

        }
    }

    public static final int c = Color.GREEN;

    public void draw(ImageView iv){
        //System.out.println("Ball.draw called X: "+x+ " Y: "+y);
        Bitmap bitmap = Bitmap.createBitmap(Map.tileSize*Map.width, Map.tileSize*Map.height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(c);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(x*Map.tileSize,y*Map.tileSize,(x+1)*Map.tileSize,(y+1)*Map.tileSize,paint);
        iv.setImageBitmap(bitmap);
        iv.bringToFront();
    }
}
