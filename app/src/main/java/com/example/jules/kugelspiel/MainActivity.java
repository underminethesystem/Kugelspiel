package com.example.jules.kugelspiel;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.jules.kugelspiel.database.DataSource;
import com.example.jules.kugelspiel.fragments.FragmentMenu;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean status = false;

    public static SensorManager sm;

    public static AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        act = this;

        DataSource ds = new DataSource(this);

        MapArchive ma = new MapArchive();
        ArrayList<GameMap> mapList = ma.getMapList();
        ObjectDecoder oj = new ObjectDecoder();

        ds.open();
        // TODO: durch vergleich "maparchive.size mit database.size" ersetzen
        // TODO: create maps neu wenn sich zahl unterscheidet
        if (ds.getAllMaps().size() == 0 ){
            try {
                for (GameMap m: mapList){
                    ds.createMap(oj.toString(m));
                }
            }catch(IOException e){}
        }
        ds.close();

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMenu fM = new FragmentMenu();
        fragmentTransaction.add(R.id.fragment_container, fM);
        fragmentTransaction.commit();
        status = true;

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
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