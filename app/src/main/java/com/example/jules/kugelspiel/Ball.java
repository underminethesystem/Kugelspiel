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
    public boolean isCollision(float x,float y){
        float r=0.4f;
        float centerX= x+0.4f;
        float centerY= y+0.4f;
        for(int i=0;i<COLLISION_CHECK_AMOUNT;i++){
            float xPos= r*(float)Math.cos(Math.toRadians(360/COLLISION_CHECK_AMOUNT*i))+centerX;
            float yPos= r*(float)Math.sin(Math.toRadians(360/COLLISION_CHECK_AMOUNT*i))+centerY;
            if (!map.getTileAt(xPos-0.5f,yPos-0.5f).isWalkable){
                //System.out.println("Collision detected at X:"+xPos+" Y: "+yPos);
                return true;
            }
        }
        return false;
    }
    public void move() {
        if (isCollision(x+DirectionManager.xDir,y+DirectionManager.yDir)) {
            //move along wall
            float newXDir=DirectionManager.xDir;
            float newYDir=DirectionManager.yDir;

            if(isCollision(x+DirectionManager.xDir,y))
                newXDir=0;
            if(isCollision(x,y+DirectionManager.yDir))
                newYDir=0;
            x+=newXDir;
            y+=newYDir;
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
        canvas.drawCircle(x*Map.tileSize,y*Map.tileSize,Map.tileSize*0.4f,paint);
        iv.setImageBitmap(bitmap);
        iv.bringToFront();
    }
}
