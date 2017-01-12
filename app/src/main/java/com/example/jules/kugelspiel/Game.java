package com.example.jules.kugelspiel;

import android.app.Activity;
import android.hardware.SensorManager;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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

    Date startTime;
    Date endTime;

    public void start(){
   /*     Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                // Your database code here
            }
        },1000);//every second
*/
        startTime=new Date();
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

    long won(){
        endTime=new Date();
        long diffInMs=endTime.getTime()-startTime.getTime();
        long diffInSec=TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        Log.v("Time",diffInSec+" sec");
        return (diffInSec);

    }
    void lost(){


    }

}
