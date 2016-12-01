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

    // TODO: start x and start y depending on map
    public Ball(float _x, float _y){
        x = _x;
        y = _y;
    }

    public void move(){
        x += DirectionManager.xDir;
        y += DirectionManager.yDir;
    }

    public static final int c = Color.GREEN;

    public void draw(ImageView iv){
        System.out.println("Ball.draw called X: "+x+ " Y: "+y);
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
