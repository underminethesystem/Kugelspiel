package com.example.jules.kugelspiel;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.StrictMode;

/**
 * Created by jules on 17.11.2016.
 */


// TODO: multithreading and unmake this an activity
public class DirectionManager extends Thread implements SensorEventListener {
//    public class DirectionManager extends AppCompatActivity implements SensorEventListener {
    private SensorManager sManager;

    public static float yDir;
    public static float xDir;

    public DirectionManager(SensorManager sm)
    {
        sManager = sm;
        sManager.registerListener(
                this, sManager.getDefaultSensor(
                        Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
    }

    // should start RIGHT BEFORE game is started
    // won't need Acc before that
    protected void onResume()
    {
        /*register the sensor listener to listen to the gyroscope sensor, use the
        callbacks defined in this class, and gather the sensor information as quick
        as possible*/
        sManager.registerListener(
                this, sManager.getDefaultSensor(
                        Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onStop()
    {
        sManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        // TODO: Do nothing.
    }

    @Override
    public void run(){
        // TODO: hier public direction Variablen __anhand__ der gemessenen Werte setzen
        while (true){
            xDir = 1;
            yDir = 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        // TODO: somehow always unreliable :/
        /*if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }*/

        float x = event.values[2];
        float y = event.values[1];
        float z = event.values[0];

        //else it will output the Roll, Pitch and Yawn values
        String ot = "X (Roll) :"+ Float.toString(x) +"\n"+
                    "Y (Pitch) :"+ Float.toString(y) +"\n"+
                    "Z (Yaw) :"+ Float.toString(z);
        System.out.println(ot);
        xDir = 1;
        yDir = 1;
    }
}
