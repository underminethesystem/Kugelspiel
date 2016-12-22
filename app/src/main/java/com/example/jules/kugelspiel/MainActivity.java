package com.example.jules.kugelspiel;
//
//      import android.support.v7.app.AppCompatActivity;
//      import android.os.Bundle;
//      import android.util.Log;
//      import android.widget.ImageView;

import static android.R.attr.id;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//  protected void onCreate(Bundle savedInstanceState) {
//super.onCreate(savedInstanceState);
//setContentView(R.layout.activity_main);

//ImageView iv= (ImageView) findViewById(R.id.imageView);
//Log.d("test","main");
//Map m=new Map(2);
//m.draw(iv);
//}
//}

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.jules.kugelspiel.fragments.FragmentMenu;

public class MainActivity extends AppCompatActivity {

    boolean status = false;

    public static SensorManager sm;

    public static AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        act = this;

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fM = new FragmentMenu();
        fragmentTransaction.add(R.id.fragment_container, fM);
        fragmentTransaction.commit();
        status = true;

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //dm = new DirectionManager((SensorManager) getSystemService(SENSOR_SERVICE));
    }

    /*
    @Override
    public void onPause() {
        super.onPause();
        if (Game.dm != null) {
            Game.dm.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Game.dm != null) {
            Game.dm.onResume();
        }
    }
    */
}