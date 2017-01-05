package com.example.jules.kugelspiel;

import android.app.Activity;
import android.hardware.SensorManager;
import android.media.Image;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jules on 24.11.2016.
 */

public class Game {
    //Map m;
    //Ball b;

    ImageView mapView;
    ImageView ballView;

    GameMap currentMap;

    public Game(ImageView _map, ImageView _ball, GameMap _currentMap){
        mapView = _map;
        ballView = _ball;
        currentMap = _currentMap;
        dm = new DirectionManager(MainActivity.sm);
    }

    public static DirectionManager dm;
    public static int test=0;
    Map m;
    Ball b;
    public static int FPS =60;
    public void start(){

        m = new Map(2,mapView,currentMap);
        b = new Ball(10, 10,m);
        m.draw();
        b.draw(ballView);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                MainActivity.act.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        b.draw(ballView);
                        b.move();

                    }
                    });
            }


        }, 0, 1000 / FPS);

}

}
