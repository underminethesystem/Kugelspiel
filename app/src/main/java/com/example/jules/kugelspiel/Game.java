package com.example.jules.kugelspiel;

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

    public Game(ImageView _map, ImageView _ball){
        mapView = _map;
        ballView = _ball;
        dm = new DirectionManager(MainActivity.sm);
    }

    public DirectionManager dm;

    Map m;
    Ball b;
    public void start(){
        m = new Map(2);
        b = new Ball(10, 10);
        m.draw(mapView);
        b.draw(ballView);

        // TODO: redraw correctly
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            ImageView iv = new ImageView(R.layout.activity_main);
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.mapView);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            rl.addView(iv, lp);

            while (true) {
                b.draw(ballView);
                b.move();
            }
        }
    }, 0, 1000 / 40);
}

}
