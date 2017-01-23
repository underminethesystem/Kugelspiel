package com.example.jules.kugelspiel;

import android.app.Activity;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jules.kugelspiel.fragments.FragmentMenu;
import com.example.jules.kugelspiel.fragments.FragmentWon;

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

    public Game(ImageView _map, ImageView _ball){
        mapView = _map;
        ballView = _ball;
        dm = new DirectionManager(MainActivity.sm);
    }

    public static int seconds = 0;
    public static DirectionManager dm;
    public static int test=0;
    Map m;
    Ball b;
    public static int FPS =60;
    Timer timer;
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
        m = new Map(mapView);
        b = new Ball(10, 10,m,this);
        m.draw();
        b.draw(ballView);
        timer = new Timer();
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
        seconds = (int)diffInSec;

        Log.v("End","WON"+diffInSec+" sec");
        // TODO add Time to Database return to menu?
        FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
        FragmentWon fG = new FragmentWon();

        fragmentTransaction.add(R.id.fragment_container, fG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //MainActivity.act.finish();
      //  MainActivity.act.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
      //  MainActivity.act.dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
        timer.cancel();
        return ( diffInSec);

    }
    void lost(){
        Log.v("End","Game lost");
        timer.cancel();
        //TODO restart Game

    }

}
